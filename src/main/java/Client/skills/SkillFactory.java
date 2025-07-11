package Client.skills;

import Client.MapleJob;
import Client.status.MonsterStatus;
import Config.configs.Config;
import Config.configs.ServerConfig;
import Config.constants.JobConstants;
import Config.constants.skills.冒險家_技能群組.夜使者;
import Config.constants.skills.皇家騎士團_技能群組.聖魂劍士;
import Config.constants.skills.陰陽師;
import Database.DatabaseLoader.DatabaseConnection;
import Database.DatabaseLoader.DatabaseConnectionEx;
import Database.tools.SqlTool;
import Net.server.InitializeServer;
import Net.server.buffs.MapleStatEffect;
import Plugin.provider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.Randomizer;
import tools.StringUtil;
import tools.Triple;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SkillFactory {

    private static final Logger log = LoggerFactory.getLogger(SkillFactory.class);
    private static final MapleData delayData = MapleDataProviderFactory.getCharacter().getData("00002000.img");
    private static final MapleData stringData = MapleDataProviderFactory.getString().getData("Skill.img");
    private static final MapleDataProvider datasource = MapleDataProviderFactory.getSkill();
    private static final Map<Integer, Skill> skills = new HashMap<>();
    private static final Map<Integer, CraftingEntry> craftings = new HashMap<>();
    private static final List<Integer> finalAttackSkills = new LinkedList<>();
    private static final Map<String, Integer> delays = new HashMap<>();
    private static final Map<Integer, String> skillName = new HashMap<>();
    private static final Map<Integer, SummonSkillEntry> summonSkillInformation = new HashMap<>();
    private static final Map<Integer, Integer> mountIds = new HashMap<>();
    private static final Map<Integer, FamiliarEntry> familiarInformation = new HashMap<>();
    private static final Map<Integer, List<Integer>> skillsByJob = new HashMap<>();
    private static final Lock nameStringLock = new ReentrantLock();
    public static final Map<Integer, Integer> memorySkills = new HashMap<>();

    public static void loadDelays() {
        DatabaseConnection.domain(con -> {
            if (InitializeServer.WzSqlName.wz_delays.check(con)) {//load from sql
                SqlTool.queryAndGetList(con, "SELECT * FROM `wz_delays`", rs -> delays.put(rs.getString("name"), rs.getInt("del")));
            } else {//load from wz and insert into sql
                InitializeServer.WzSqlName.wz_delays.drop(con);
                SqlTool.update(con, "CREATE TABLE `wz_delays` (`id` int(11) NOT NULL AUTO_INCREMENT,`name` text NOT NULL,`del` int(11) NOT NULL,PRIMARY KEY (`id`))");
                int del = 0;
                for (MapleData delay : delayData) {
                    if (!delay.getName().equals("info")) {
                        String name = delay.getName();
                        delays.put(delay.getName(), del++);
                        SqlTool.update(con, "INSERT INTO `wz_delays` (`name`,`del`) VALUES (?, ?)", name, del);
                    }
                }
                InitializeServer.WzSqlName.wz_delays.update(con);
            }
            return null;
        });
    }

    public static void loadSkillData() {

        MapleDataDirectoryEntry root = datasource.getRoot();
        final ExecutorService initExecutor = Executors.newCachedThreadPool();
        final List<Future<?>> futures = new ArrayList<>();
        for (MapleDataFileEntry topDir : root.getFiles()) { // Loop thru jobs

            futures.add(initExecutor.submit(() -> {
                MapleData summon_data;
                Integer skillid;
                if (topDir.getName().length() <= 10) {
                    for (MapleData data : datasource.getData(topDir.getName())) { // Loop thru each jobs
                        if (data.getName().equals("skill")) {
                            for (MapleData data2 : data) { // Loop thru each jobs
                                if (data2 != null) {
                                    skillid = Integer.parseInt(data2.getName());
                                    List<Integer> job;
                                    job = skillsByJob.computeIfAbsent(skillid / 10000, k -> new ArrayList<>());

                                    job.add(skillid);
                                    String name = getName(skillid, stringData);
                                    skillName.put(skillid, name);
                                    Skill skil = Skill.loadFromData(skillid, data2, delayData);
                                    skills.put(skillid, skil);
                                    summon_data = null;
                                    if (skillid == 聖魂劍士.ATTACK_極樂之境_II || skillid == 351110002 || skillid == 陰陽師.式神炎舞_1 || skillid == 夜使者.達克魯的秘傳) {
                                        summon_data = data2.getChildByPath("summon/die/info");
                                    }
                                    if (summon_data == null) {
                                        summon_data = data2.getChildByPath("summon/attack2/info");
                                    }
                                    if (summon_data == null) {
                                        summon_data = data2.getChildByPath("summon/attack1/info");
                                    }
                                    if (summon_data != null) {
                                        SummonSkillEntry sse = new SummonSkillEntry();
                                        sse.type = (byte) MapleDataTool.getInt("type", summon_data, 0);
                                        sse.mobCount = (byte) MapleDataTool.getInt("mobCount", summon_data, 1);
                                        sse.attackCount = (byte) MapleDataTool.getInt("attackCount", summon_data, 1);
                                        sse.targetPlus = (byte) MapleDataTool.getInt("targetPlus", summon_data, 1);
                                        if (summon_data.getChildByPath("range/lt") != null) {
                                            MapleData ltd = summon_data.getChildByPath("range/lt");
                                            sse.lt = (Point) ltd.getData();
                                            sse.rb = (Point) summon_data.getChildByPath("range/rb").getData();
                                        } else {
                                            sse.lt = new Point(-100, -100);
                                            sse.rb = new Point(100, 100);
                                        }
//                                    sse.range = (short) MapleDataTool.getInt("range/r", summon_data, 0);
//                                    sse.delay = MapleDataTool.getInt("effectAfter", summon_data, 0) + MapleDataTool.getInt("attackAfter", summon_data, 0);
//                                    sse.delay = MapleDataTool.getInt("bulletSpeed", summon_data, 0);
                                        for (MapleData effect : summon_data) {
                                            if (effect.getChildren().size() > 0) {
                                                for (MapleData effectEntry : effect) {
                                                    sse.delay += MapleDataTool.getIntConvert("delay", effectEntry, 0);
                                                }
                                            }
                                        }
                                        MapleData childByPath = data2.getChildByPath("summon/attack1");
                                        if (childByPath != null) {
                                            for (MapleData effect : childByPath) {
                                                sse.delay += MapleDataTool.getIntConvert("delay", effect, 0);
                                            }
                                        }
                                        summonSkillInformation.put(skillid, sse);
                                    }
                                    // 查找所有騎寵關聯ID
                                    for (MapleData data3 : data2) {
                                        if (data3.getName().equals("vehicleID")) {
                                            int vehicleID = MapleDataTool.getInt("vehicleID", data2, 0);
                                            mountIds.put(skillid, vehicleID);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (topDir.getName().startsWith("Familiar")) {
                    for (MapleData data : datasource.getData(topDir.getName())) {
                        FamiliarEntry skil = new FamiliarEntry();
                        skil.prop = (byte) MapleDataTool.getInt("prop", data, 0);
                        skil.time = (byte) MapleDataTool.getInt("time", data, 0);
                        skil.attackCount = (byte) MapleDataTool.getInt("attackCount", data, 1);
                        skil.targetCount = (byte) MapleDataTool.getInt("targetCount", data, 1);
                        skil.speed = (byte) MapleDataTool.getInt("speed", data, 1);
                        skil.knockback = MapleDataTool.getInt("knockback", data, 0) > 0 || MapleDataTool.getInt("attract", data, 0) > 0;
                        if (data.getChildByPath("lt") != null) {
                            skil.lt = (Point) data.getChildByPath("lt").getData();
                            skil.rb = (Point) data.getChildByPath("rb").getData();
                        }
                        if (MapleDataTool.getInt("stun", data, 0) > 0) {
                            skil.status.add(MonsterStatus.Stun);
                        }
                        //if (MapleDataTool.getInt("poison", data, 0) > 0) {
                        //	status.add(MonsterStatus.中毒);
                        //}
                        if (MapleDataTool.getInt("slow", data, 0) > 0) {
                            skil.status.add(MonsterStatus.Speed);
                        }
                        int familiarid = Integer.parseInt(data.getName());
                        familiarInformation.put(familiarid, skil);
                    }
                } else if (topDir.getName().startsWith("Recipe")) {
                    for (MapleData data : datasource.getData(topDir.getName())) {
                        skillid = Integer.parseInt(data.getName());
                        CraftingEntry skil = new CraftingEntry(skillid, (byte) MapleDataTool.getInt("incFatigability", data, 0), (byte) MapleDataTool.getInt("reqSkillLevel", data, 0), (byte) MapleDataTool.getInt("incSkillProficiency", data, 0), MapleDataTool.getInt("needOpenItem", data, 0) > 0, MapleDataTool.getInt("period", data, 0));
                        skil.setRecipe(true);
                        for (MapleData d : data.getChildByPath("target")) {
                            skil.targetItems.add(new Triple<>(MapleDataTool.getInt("item", d, 0), MapleDataTool.getInt("count", d, 0), MapleDataTool.getInt("probWeight", d, 0)));
                        }
                        for (MapleData d : data.getChildByPath("recipe")) {
                            skil.reqItems.put(MapleDataTool.getInt("item", d, 0), MapleDataTool.getInt("count", d, 0));
                        }
                        craftings.put(skillid, skil);
                    }
                }
            }));
        }
        Collections.reverse(finalAttackSkills);
        for (Future<?> future : futures) {
            future.getClass();
        }
        //log.info("共載入 " + skills.size() + " 個技能資訊"); /* 暫時關閉 */
    }

    public static void reloadSkills(int skillid) {
        String path = skillid / 10000 + ".img";
        if (skillid >= 80000000 && skillid < 90000000) {
            path = skillid / 100 + ".img";
        }
        if (datasource.getData(path) != null) {
            Skill skil = Skill.loadFromData(skillid, datasource.getData(path).getChildByPath("skill").getChildByPath(String.valueOf(skillid)), delayData);
            skills.put(skillid, skil);
        }
    }

    public static void loadMemorySkills() {
        try (Connection con = DatabaseConnectionEx.getInstance().getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM memoryskills")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int skillId = rs.getInt("skillid");
                        Skill skill = SkillFactory.getSkill(skillId);
                        /*
                         * 如果複製技能中已經有這個技能 或者 這個技能不存在 或者 這個技能不是冒險家職業技能 就跳過不加載
                         */
                        if (memorySkills.containsKey(skillId) || skill == null || skill.getSkillByJobBook(skillId) == -1) {
                            continue;
                        }
                        memorySkills.put(skillId, skill.getSkillByJobBook(skillId));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getSkillDefaultData(int skillid, String name) {
        MapleData skillData = datasource.getData(skillid / 10000 + ".img");
        if (skillData != null) {
            MapleData skill = skillData.getChildByPath("skill").getChildByPath(String.valueOf(skillid)).getChildByPath("common");
            if (skill != null) {
                for (MapleData data : skill) {
                    if (data.getName().equals(name)) {
                        return String.valueOf(data.getData());
                    }
                }
            }
        }
        return null;
    }

    public static int getIdFromSkillId(int skillId) {
        return memorySkills.getOrDefault(skillId, 0);
    }

    public static boolean isMemorySkill(int skillId) {
        return memorySkills.containsKey(skillId);
    }

    public static List<Integer> getSkillsByJob(int jobId) {
        List<Integer> ret = null;
        try {
            ret = skillsByJob.get(jobId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static List<Integer> getSkillsByLowerJob(int jobId) {
        List<Integer> ret = null;
        for (MapleJob mj : MapleJob.values()) {
            if (JobConstants.is零轉職業(mj.getId())) {
                continue;
            }
            if (JobConstants.isSameJob(mj.getId(), jobId) && mj.getId() <= jobId) {
                List<Integer> skills = getSkillsByJob(mj.getId());
                if (ret == null && skills != null) {
                    ret = new ArrayList<>();
                }
                if (skills != null) {
                    ret.addAll(skills);
                }
            }
        }
        return ret;
    }

    public static List<Integer> getSkillsBySameJob(int jobId) {
        List<Integer> ret = null;
        for (MapleJob mj : MapleJob.values()) {
            if (JobConstants.is零轉職業(mj.getId())) {
                continue;
            }
            if (JobConstants.isSameJob(mj.getId(), jobId)) {
                List<Integer> skills = getSkillsByJob(mj.getId());
                if (ret == null && skills != null) {
                    ret = new ArrayList<>();
                }
                ret.addAll(skills);
            }
        }
        return ret;
    }

    public static String getSkillName(int id) {
        return skillName.get(id);
    }

    public static Integer getDelay(String id) {
        Delay delay = Delay.fromString(id);
        if (delay != null) {
            return delay.i;
        }

        return delays.get(id);
    }

    private static String getName(int id, MapleData stringData) {
        String strId = Integer.toString(id);
        strId = StringUtil.getLeftPaddedStr(strId, '0', 7);
        nameStringLock.lock();
        try {
            MapleData skillroot = stringData.getChildByPath(strId);
            if (skillroot != null) {
                return MapleDataTool.getString(skillroot.getChildByPath("name"), "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            nameStringLock.unlock();
        }
        return "";
    }

    public static String getDesc(int id) {
        String strId = Integer.toString(id);
        strId = StringUtil.getLeftPaddedStr(strId, '0', 7);
        MapleData skillroot = stringData.getChildByPath(strId);
        if (skillroot != null) {
            return MapleDataTool.getString(skillroot.getChildByPath("desc"), "");
        }
        return "";
    }

    public static String getH(int id) {
        String strId = Integer.toString(id);
        strId = StringUtil.getLeftPaddedStr(strId, '0', 7);
        MapleData skillroot = stringData.getChildByPath(strId);
        if (skillroot != null) {
            return MapleDataTool.getString(skillroot.getChildByPath("h"), "");
        }
        return "";
    }

    public static SummonSkillEntry getSummonData(int skillid) {
        return summonSkillInformation.get(skillid);
    }

    /**
     * 獲得所有技能數據
     *
     * @return
     */
    public static Map<Integer, String> getAllSkills() {
        return skillName;
    }

    public static Skill getSkill(int skillid) {
        // redis
//        String craftsdata = RedisUtil.hget(KEYNAMES.CRAFT_DATA.getKeyName(), String.valueOf(skillid));
//        if (skillid >= 92000000 && skillid < 100000000 && craftsdata != null) {
//            try {
//                return mapper.readValue(craftsdata, CraftingEntry.class);
//            } catch (Exception e) {
//                log.error("讀取技能失敗:" + skillid, e);
//            }
//        }
//        String skilldata = RedisUtil.hget(KEYNAMES.SKILL_DATA.getKeyName(), String.valueOf(skillid));
//        try {
//            return mapper.readValue(skilldata, Skill.class);
//        } catch (Exception e) {
//            log.error("讀取技能失敗:" + skillid, e);
//        }
//        return null;
        Skill skill = null;
        if (skillid >= 92000000 && skillid < 100000000 && craftings.containsKey(skillid)) {
            skill = craftings.get(skillid);
        }
        if (skill == null) {
            skill = skills.get(skillid);
        }
        return skill;

//        if (!skills.isEmpty()) {
//            if (skillid >= 92000000 && skillid < 100000000 && craftings.containsKey(skillid)) {
//                return craftings.get(skillid);
//            }
//            return skills.get(skillid);
//        }
//        return null;
    }

    public static MapleStatEffect getSkillEffect(int skillId, int level) {
        Skill skill = getSkill(skillId);
        return skill == null ? null : skill.getEffect(level);
    }

    /**
     * 獲取技能的默認時間
     * 也就是技能是否是有時間限制的
     */
    public static long getDefaultSExpiry(Skill skill) {
        if (skill == null) {
            return -1;
        }
        return (skill.isTimeLimited() ? (System.currentTimeMillis() + 30L * 24L * 60L * 60L * 1000L) : -1);
    }

    public static CraftingEntry getCraft(int id) {
        CraftingEntry ret = null;

        try {
            ret = craftings.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static FamiliarEntry getFamiliar(int id) {
        return familiarInformation.getOrDefault(id, null);
    }

    /**
     * 檢測這個技能是否禁止顯示
     */
    public static boolean isBlockedSkill(int skillId) {
        return skillId > 0 && Arrays.stream(ServerConfig.WORLD_BLOCKSKILLS.split(",")).anyMatch(it -> it.equals(String.valueOf(skillId)));
    }

    public static boolean addBlockedSkill(int skillId) {
        if (isBlockedSkill(skillId)) {
            return false;
        }
        ServerConfig.WORLD_BLOCKSKILLS += "," + skillId;
        Config.setProperty("world.blockskills", ServerConfig.WORLD_BLOCKSKILLS);
        return true;
    }

    /**
     * 找出相同的傳授技能名字
     */
    public static int getTeachSkill(String name) {
        for (Entry<Integer, String> entry : getAllSkills().entrySet()) {
            try {
                if (entry.getValue() != null && entry.getValue().endsWith(name)) {
                    if (entry.getKey() >= 80000000 && entry.getKey() < 90000000) {
                        return entry.getKey();
                    }
                }
            } catch (Exception e) {
                System.out.println(entry.getKey());
            }
        }
        return -1;
    }

    public static int getMountLinkId(int mountid) {
        return mountIds.getOrDefault(mountid, 0);
    }

    public static List<Integer> getFinalAttackSkills() {
        return finalAttackSkills;
    }

    public static boolean isFinalAttackSkills(Integer skillid) {
        return finalAttackSkills.contains(skillid);
    }

    public enum Delay {

        walk1(0x00),
        walk2(0x01),
        stand1(0x02),
        stand2(0x03),
        alert(0x04),
        swingO1(0x05),
        swingO2(0x06),
        swingO3(0x07),
        swingOF(0x08),
        swingT1(0x09),
        swingT2(0x0A),
        swingT3(0x0B),
        swingTF(0x0C),
        swingP1(0x0D),
        swingP2(0x0E),
        swingPF(0x0F),
        stabO1(0x10),
        stabO2(0x11),
        stabOF(0x12),
        stabT1(0x13),
        stabT2(0x14),
        stabTF(0x15),
        swingD1(0x16),
        swingD2(0x17),
        stabD1(0x18),
        swingDb1(0x19),
        swingDb2(0x1A),
        swingC1(0x1B),
        swingC2(0x1C),
        rushBoom(0x1C),
        tripleBlow(0x19),
        quadBlow(0x1A),
        deathBlow(0x1B),
        finishBlow(0x1C),
        finishAttack(0x1D),
        finishlink(0x1E),
        finishlink2(0x1E),
        shoot1(0x1F),
        shoot2(0x20),
        shootF(0x21),
        shootDb2(0x28),
        shotC1(0x29),
        dash(0x25),
        dash2(0x26), //hack. doesn't really exist
        proneStab(0x29),
        prone(0x2A),
        heal(0x2B),
        fly(0x2C),
        jump(0x2D),
        sit(0x2E),
        rope(0x2F),
        dead(0x30),
        ladder(0x31),
        rain(0x32),
        alert2(0x34),
        alert3(0x35),
        alert4(0x36),
        alert5(0x37),
        alert6(0x38),
        alert7(0x39),
        ladder2(0x3A),
        rope2(0x3B),
        shoot6(0x3C),
        magic1(0x3D),
        magic2(0x3E),
        magic3(0x3F),
        magic5(0x40),
        magic6(0x41), //----------------------------------
        explosion(0x41),
        burster1(0x42),
        burster2(0x43),
        savage(0x44),
        avenger(0x45),
        assaulter(0x46),
        prone2(0x47),
        assassination(0x48),
        assassinationS(0x49),
        tornadoDash(0x4C),
        tornadoDashStop(0x4C),
        tornadoRush(0x4C),
        rush(0x4D),
        rush2(0x4E),
        brandish1(0x4F),
        brandish2(0x50),
        braveSlash(0x51),
        braveslash1(0x51),
        braveslash2(0x51),
        braveslash3(0x51),
        braveslash4(0x51),
        darkImpale(0x61),
        sanctuary(0x52),
        meteor(0x53),
        paralyze(0x54),
        blizzard(0x55),
        genesis(0x56),
        blast(0x58),
        smokeshell(0x59),
        showdown(0x5A),
        ninjastorm(0x5B),
        chainlightning(0x5C),
        holyshield(0x5D),
        resurrection(0x5E),
        somersault(0x5F),
        straight(0x60),
        eburster(0x61),
        backspin(0x62),
        eorb(0x63),
        screw(0x64),
        doubleupper(0x65),
        dragonstrike(0x66),
        doublefire(0x67),
        triplefire(0x68),
        fake(0x69),
        airstrike(0x6A),
        edrain(0x6B),
        octopus(0x6C),
        backstep(0x6D),
        shot(0x6E), //----------------------------------
        rapidfire(0x6E),
        fireburner(0x70),
        coolingeffect(0x71),
        fist(0x72), //----------------------------------
        timeleap(0x73),
        homing(0x75),
        ghostwalk(0x76),
        ghoststand(0x77),
        ghostjump(0x78),
        ghostproneStab(0x79),
        ghostladder(0x7A),
        ghostrope(0x7B),
        ghostfly(0x7C),
        ghostsit(0x7D),
        cannon(0x7E),
        torpedo(0x7F),
        darksight(0x80),
        bamboo(0x81),
        pyramid(0x82),
        wave(0x83),
        blade(0x84),
        souldriver(0x85),
        firestrike(0x86),
        flamegear(0x87),
        stormbreak(0x88),
        vampire(0x89),
        swingT2PoleArm(0x8B),
        swingP1PoleArm(0x8C),
        swingP2PoleArm(0x8D),
        doubleSwing(0x8E),
        tripleSwing(0x8F),
        fullSwingDouble(0x90),
        fullSwingTriple(0x91),
        overSwingDouble(0x92),
        overSwingTriple(0x93),
        rollingSpin(0x94),
        comboSmash(0x95),
        comboFenrir(0x96),
        comboTempest(0x97),
        finalCharge(0x98),
        finalBlow(0x9A),
        finalToss(0x9B),
        magicmissile(0x9C),
        lightningBolt(0x9D),
        dragonBreathe(0x9E),
        breathe_prepare(0x9F),
        dragonIceBreathe(0xA0),
        icebreathe_prepare(0xA1),
        blaze(0xA2),
        fireCircle(0xA3),
        illusion(0xA4),
        magicFlare(0xA5),
        elementalReset(0xA6),
        magicRegistance(0xA7),
        magicBooster(0xA8),
        magicShield(0xA9),
        recoveryAura(0xAA),
        flameWheel(0xAB),
        killingWing(0xAC),
        OnixBlessing(0xAD),
        Earthquake(0xAE),
        soulStone(0xAF),
        dragonThrust(0xB0),
        ghostLettering(0xB1),
        darkFog(0xB2),
        slow(0xB3),
        mapleHero(0xB4),
        Awakening(0xB5),
        flyingAssaulter(0xB6),
        tripleStab(0xB7),
        fatalBlow(0xB8),
        slashStorm1(0xB9),
        slashStorm2(0xBA),
        bloodyStorm(0xBB),
        flashBang(0xBC),
        upperStab(0xBD),
        bladeFury(0xBE),
        chainPull(0xC0),
        chainAttack(0xC0),
        owlDead(0xC1),
        monsterBombPrepare(0xC3),
        monsterBombThrow(0xC3),
        finalCut(0xC4),
        finalCutPrepare(0xC4),
        suddenRaid(0xC6), //idk, not in data anymore
        fly2(0xC7),
        fly2Move(0xC8),
        fly2Skill(0xC9),
        knockback(0xCA),
        rbooster_pre(0xCE),
        rbooster(0xCE),
        rbooster_after(0xCE),
        crossRoad(0xD1),
        nemesis(0xD2),
        tank(0xD9),
        tank_laser(0xDD),
        siege_pre(0xDF),
        tank_siegepre(0xDF), //just to make it work with the skill, these two
        sonicBoom(0xE2),
        darkLightning(0xE4),
        darkChain(0xE5),
        cyclone_pre(0),
        cyclone(0), //energy attack
        glacialchain(0xF7),
        flamethrower(0xE9),
        flamethrower_pre(0xE9),
        flamethrower2(0xEA),
        flamethrower_pre2(0xEA),
        gatlingshot(0xEF),
        gatlingshot2(0xF0),
        drillrush(0xF1),
        earthslug(0xF2),
        rpunch(0xF3),
        clawCut(0xF4),
        swallow(0xF7),
        swallow_attack(0xF7),
        swallow_loop(0xF7),
        flashRain(0xF9),
        OnixProtection(0x108),
        OnixWill(0x109),
        phantomBlow(0x10A),
        comboJudgement(0x10B),
        arrowRain(0x10C),
        arrowEruption(0x10D),
        iceStrike(0x10E),
        swingT2Giant(0x111),
        cannonJump(0x127),
        swiftShot(0x128),
        giganticBackstep(0x12A),
        mistEruption(0x12B),
        cannonSmash(0x12C),
        cannonSlam(0x12D),
        flamesplash(0x12E),
        noiseWave(0x132),
        superCannon(0x136),
        jShot(0x138),
        demonSlasher(0x139),
        bombExplosion(0x13A),
        cannonSpike(0x13B),
        speedDualShot(0x13C),
        strikeDual(0x13D),
        bluntSmash(0x13F),
        crossPiercing(0x140),
        piercing(0x141),
        elfTornado(0x143),
        immolation(0x144),
        multiSniping(0x147),
        windEffect(0x148),
        elfrush(0x149),
        elfrush2(0x149),
        dealingRush(0x14E),
        maxForce0(0x150),
        maxForce1(0x151),
        maxForce2(0x152),
        maxForce3(0x153),
        //special: pirate morph attacks
        iceAttack1(0x112),
        iceAttack2(0x113),
        iceSmash(0x114),
        iceTempest(0x115),
        iceChop(0x116),
        icePanic(0x117),
        iceDoubleJump(0x118),
        shockwave(0x124),
        demolition(0x125),
        snatch(0x126),
        windspear(0x127),
        windshot(0x128);
        public final int i;

        Delay(int i) {
            this.i = i;
        }

        public static Delay fromString(String s) {
            for (Delay b : Delay.values()) {
                if (b.name().equalsIgnoreCase(s)) {
                    return b;
                }
            }
            return null;
        }
    }

    public static class CraftingEntry extends Skill {
        //reqSkillProficiency -> always seems to be 0

        public final List<Triple<Integer, Integer, Integer>> targetItems = new ArrayList<>(); // itemId / amount / probability
        public final Map<Integer, Integer> reqItems = new HashMap<>(); // itemId / amount
        public boolean needOpenItem;
        public int period;
        public byte incFatigability;
        public byte reqSkillLevel;
        public byte incSkillProficiency;

        public CraftingEntry() {
        }

        public CraftingEntry(int id, byte incFatigability, byte reqSkillLevel, byte incSkillProficiency, boolean needOpenItem, int period) {
            super(id);
            this.incFatigability = incFatigability;
            this.reqSkillLevel = reqSkillLevel;
            this.incSkillProficiency = incSkillProficiency;
            this.needOpenItem = needOpenItem;
            this.period = period;
        }
    }

    public static class FamiliarEntry {

        public final EnumSet<MonsterStatus> status = EnumSet.noneOf(MonsterStatus.class);
        public byte prop, time, attackCount, targetCount, speed;
        public Point lt, rb;
        public boolean knockback;

        public boolean makeChanceResult() {
            return prop >= 100 || Randomizer.nextInt(100) < prop;
        }
    }
}
