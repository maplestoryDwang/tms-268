package Client.force;

import Client.MapleCharacter;
import Client.MapleForceType;
import Client.SecondaryStat;
import Client.inventory.Item;
import Client.inventory.MapleInventoryType;
import Client.status.MonsterStatus;
import Net.server.buffs.MapleStatEffect;
import Net.server.buffs.MapleStatEffectFactory;
import Net.server.life.MapleMonster;
import Net.server.maps.MapleMapItem;
import Net.server.maps.MapleMapObject;
import Plugin.provider.MapleData;
import Plugin.provider.MapleDataProvider;
import Plugin.provider.MapleDataProviderFactory;
import Plugin.provider.MapleDataTool;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import tools.Randomizer;

public class MapleForceFactory {
    private static MapleForceFactory instance;
    private final Map<Integer, Map<Integer, ForceInfoEntry>> forceInfos = new HashMap();

    public MapleForceFactory() {
    }

    public static MapleForceFactory getInstance() {
        if (instance == null) {
            instance = new MapleForceFactory();
        }

        return instance;
    }

    public void initialize() {
        this.forceInfos.clear();
        MapleDataProvider effect = MapleDataProviderFactory.getEffect();
        MapleData forceData = effect.getData("CharacterEff.img").getChildByPath("forceAtom");
        Iterator var2 = forceData.iterator();

        label59:
        while(true) {
            int id;
            int firstImpactMin;
            int firstImpactMax;
            int secondImpactMin;
            int secondImpactMax;
            ArrayList startPoints;
            MapleData atomd;
            do {
                if (!var2.hasNext()) {
                    return;
                }

                MapleData force = (MapleData)var2.next();
                id = Integer.valueOf(force.getName());
                firstImpactMin = 45;
                firstImpactMax = 50;
                secondImpactMin = 30;
                secondImpactMax = 35;
                startPoints = new ArrayList();
                MapleData infod = force.getChildByPath("info");
                if (infod != null) {
                    firstImpactMin = MapleDataTool.getInt(infod.getChildByPath("firstImpactMin"), 45);
                    firstImpactMax = MapleDataTool.getInt(infod.getChildByPath("firstImpactMax"), 50);
                    secondImpactMin = MapleDataTool.getInt(infod.getChildByPath("secondImpactMin"), 30);
                    secondImpactMax = MapleDataTool.getInt(infod.getChildByPath("secondImpactMax"), 35);
                }

                atomd = force.getChildByPath("atom");
            } while(atomd == null);

            Map<Integer, ForceInfoEntry> ret = new HashMap();
            Iterator var13 = atomd.iterator();

            while(true) {
                MapleData data;
                String name;
                do {
                    if (!var13.hasNext()) {
                        this.forceInfos.put(id, ret);
                        continue label59;
                    }

                    data = (MapleData)var13.next();
                    name = data.getName();
                } while(name.length() > 2);

                int id2 = Integer.valueOf(data.getName());
                ForceInfoEntry info = new ForceInfoEntry();
                MapleData startPoint = data.getChildByPath("startPoint");
                if (startPoint != null) {
                    Iterator var19 = startPoint.iterator();

                    while(var19.hasNext()) {
                        MapleData d = (MapleData)var19.next();

                        for(int i = 0; d.getChildByPath(String.valueOf(i)) != null; ++i) {
                            Point point = MapleDataTool.getPoint(d.getChildByPath(String.valueOf(i)));
                            if (point != null) {
                                startPoints.add(point);
                            }
                        }
                    }
                }

                info.startPoints = startPoints;
                info.firstImpactMin = firstImpactMin;
                info.firstImpactMax = firstImpactMax;
                info.secondImpactMin = secondImpactMin;
                info.secondImpactMax = secondImpactMax;
                ret.put(id2, info);
            }
        }
    }

    public final MapleForceAtom getMapleForce(MapleCharacter chr, MapleStatEffect effect, int fromMobOid, List<Integer> toMobOid) {
        return this.getMapleForce(chr, effect, fromMobOid, 0, toMobOid, chr.getPosition());
    }

    public final MapleForceAtom getMapleForce(MapleCharacter chr, MapleStatEffect effect, int fromMobOid, List<Integer> toMobOid, Point pos) {
        return this.getMapleForce(chr, effect, fromMobOid, 0, toMobOid, pos);
    }

    public final MapleForceAtom getMapleForce(MapleCharacter chr, MapleStatEffect effect, int fromMobOid) {
        return this.getMapleForce(chr, effect, fromMobOid, 0, Collections.emptyList(), chr.getPosition());
    }

    public final MapleForceAtom getMapleForce(MapleCharacter chr, MapleStatEffect effect, int attackCount, int fromMobOid) {
        return this.getMapleForce(chr, effect, attackCount, fromMobOid, 0, Collections.emptyList(), chr.getPosition());
    }

    public final MapleForceAtom getMapleForce(MapleCharacter chr, MapleStatEffect effect, int fromMobOid, int firstMobId, List<Integer> toMobOid, Point point) {
        return this.getMapleForce(chr, effect, 1, fromMobOid, firstMobId, toMobOid, point);
    }

    private MapleForceAtom getMapleForce(MapleCharacter chr, MapleStatEffect effect, int attackCount, int fromMobOid, int firstMobId, List<Integer> toMobOid, Point pos) {
        MapleForceAtom force = new MapleForceAtom();
        force.setFromMob(fromMobOid > 0);
        force.setOwnerId(chr.getId());
        force.setBulletItemID(0);
        force.setArriveDir(0);
        force.setArriveRange(500);
        force.setForcedTarget(pos);
        force.setToMobOid(toMobOid);
        Item dartsSlot;
        switch (effect.getSourceId()) {
            case 2121052:
                force.setFromMobOid(0);
                force.setForceType(MapleForceType.審判之焰.ordinal());
                force.setFirstMobID(0);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, 20, 50, 500, pos));
                break;
            case 3011004:
            case 3300002:
                force.setForceType(56);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo_暗紅釋魂(chr, toMobOid.size(), 1));
                break;
            case 3101009:
            case 3120022:
                force.setFromMobOid(0);
                force.setForceType(MapleForceType.三彩箭矢.ordinal());
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId() == 3120022 ? 3120017 : 3100010);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 10, 100, 53, pos));
                break;
            case 3300005:
                force.setForceType(57);
                force.setSkillId(effect.getSourceId());
                force.setRect(effect.calculateBoundingBox3(new Point()));
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, Randomizer.rand(32, 48), Randomizer.rand(32, 48), 60, pos));
                break;
            case 3301008:
            case 3301009:
                force.setForceType(58);
                force.setSkillId(effect.getSourceId());
                force.setRect(effect.calculateBoundingBox3(new Point()));
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, Randomizer.rand(32, 48), Randomizer.rand(32, 48), 120, pos));
                break;
            case 3310004:
                force.setForceType(57);
                force.setSkillId(effect.getSourceId());
                force.setRect(effect.calculateBoundingBox3(new Point()));
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, Randomizer.rand(32, 48), Randomizer.rand(32, 48), 60, pos));
                break;
            case 3321003:
                force.setForceType(56);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo_暗紅釋魂(chr, toMobOid.size(), 2));
                break;
            case 4100011:
            case 4101011:
                force.setFromMobOid(fromMobOid);
                force.setForceType(MapleForceType.刺客刻印.ordinal());
                force.setFirstMobID(0);
                force.setSkillId(4100012);
                force.setRect(MapleStatEffectFactory.calculateBoundingBox(pos, true, new Point(-120, -100), new Point(100, 100), 0));
                dartsSlot = chr.getInventory(MapleInventoryType.CASH).getDartsSlot(chr.getLevel());
                if (dartsSlot == null) {
                    dartsSlot = chr.getInventory(MapleInventoryType.USE).getDartsSlot(chr.getLevel());
                }

                force.setBulletItemID(dartsSlot == null ? 0 : dartsSlot.getItemId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 40, 100, 200, pos));
                break;
            case 4120018:
                force.setFromMobOid(fromMobOid);
                force.setForceType(MapleForceType.刺客刻印.ordinal());
                force.setFirstMobID(0);
                force.setSkillId(4120019);
                dartsSlot = chr.getInventory(MapleInventoryType.CASH).getDartsSlot(chr.getLevel());
                if (dartsSlot == null) {
                    dartsSlot = chr.getInventory(MapleInventoryType.USE).getDartsSlot(chr.getLevel());
                }

                force.setBulletItemID(dartsSlot == null ? 0 : dartsSlot.getItemId());
                force.setRect(MapleStatEffectFactory.calculateBoundingBox(pos, true, new Point(-120, -100), new Point(100, 100), 0));
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, 40, 100, 200, pos));
                break;
            case 12001020:
                force.setSkillId(12000026);
                force.setForceType(MapleForceType.軌道烈焰.ordinal());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 40, 100, 30, pos));
                break;
            case 12100020:
                force.setSkillId(12100028);
                force.setForceType(MapleForceType.軌道烈焰.ordinal());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, 40, 100, 30, pos));
                break;
            case 12110020:
                force.setSkillId(12110028);
                force.setForceType(MapleForceType.軌道烈焰.ordinal());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 3, 40, 100, 30, pos));
                break;
            case 12120006:
                force.setSkillId(12120010);
                force.setForceType(MapleForceType.軌道烈焰.ordinal());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 4, 25, 100, 30, pos));
                break;
            case 13101022:
            case 13110022:
            case 13120003:
                force.setForceType(7);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, 42, chr.isFacingLeft() ? 90 : 280, 48, pos));
                break;
            case 13101027:
            case 13110027:
            case 13120010:
                force.setForceType(7);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 42, chr.isFacingLeft() ? 90 : 280, 48, pos));
                break;
            case 13121055:
                force.setForceType(8);
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 15, chr.isFacingLeft() ? 90 : 280, 60, pos));
                break;
            case 14000027:
            case 14000028:
                force.setForceType(force.isFromMob() ? MapleForceType.SUMMON_暗影蝙蝠_反彈.ordinal() : MapleForceType.Switch_type_Magic_暗影蝙蝠.ordinal());
                force.setSkillId(14000028);
                force.setFromMobOid(fromMobOid);
                force.setFirstMobID(firstMobId);
                force.setRect(MapleStatEffectFactory.calculateBoundingBox(pos, true, new Point(-120, -100), new Point(100, 100), 0));
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), force.isFromMob() ? 1 : 2, 32, 280, force.isFromMob() ? 30 : 300, pos));
                break;
            case 20031209:
            case 20031210:
                force.setForceType(1);
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId() == 20031210 ? 24120002 : 24100003);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), effect.getSourceId() == 20031210 ? 2 : 1, 25, chr.isFacingLeft() ? 90 : 280, 60, pos));
                break;
            case 24100003:
            case 24120002:
                force.setForceType(MapleForceType.幻影卡牌.ordinal());
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), effect.getSourceId() == 24120002 ? 2 : 1, 25, chr.isFacingLeft() ? 90 : 280, 60, pos));
                break;
            case 25100010:
            case 25120115:
                force.setFromMobOid(fromMobOid);
                force.setForceType(force.isFromMob() ? 4 : 13);
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), effect.getSourceId() == 25100010 ? (force.isFromMob() ? 4 : 1) : (force.isFromMob() ? 5 : 2), 25, chr.isFacingLeft() ? 90 : 280, 60, pos));
                break;
            case 31000004:
            case 31001006:
            case 31001007:
            case 31001008:
            case 31121052:
            case 400011007:
            case 400011008:
            case 400011009:
            case 400011018:
                firstMobId = Randomizer.nextInt(chr.getSkillLevel(31110009) > 0 ? 10 : 6);
                force.setFromMobOid(fromMobOid);
                force.setForceType(0);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), firstMobId, 30, 65, 0, pos));
                break;
            case 31221001:
            case 31221014:
                force.setFromMobOid(fromMobOid);
                force.setForceType(force.isFromMob() ? 4 : 3);
                force.setFirstMobID(firstMobId);
                force.setSkillId(31221014);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 3, force.isFromMob() ? 80 : 20, chr.isFacingLeft() ? Randomizer.rand(30, 90) : Randomizer.rand(200, 280), force.isFromMob() ? 60 : 500, pos));
                break;
            case 35101002:
            case 35110017:
                force.setForceType(20);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), effect.getSourceId() == 35101002 ? 0 : 2, 37, chr.isFacingLeft() ? 90 : 280, 512, pos));
                break;
            case 36001005:
                force.setForceType(MapleForceType.傑諾火箭.ordinal());
                force.setSkillId(36001005);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 20, 34, 0, pos));
                break;
            case 36111004:
                force.setForceType(MapleForceType.神盾系統.ordinal());
                force.setSkillId(36110004);
                force.setFromMobOid(fromMobOid);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 32, 96, 256, pos));
                break;
            case 42110002:
                force.setFirstMobID(firstMobId);
                force.setForceType(59);
                force.setSkillId(42110002);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 66, 77, 661, pos));
                break;
            case 61101002:
            case 61120007:
            case 400011058:
                force.setForceType(2);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, 16, chr.isFacingLeft() ? 90 : 280, 16, pos));
                break;
            case 61110211:
            case 61121217:
            case 400011059:
                force.setForceType(2);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 3, 16, chr.isFacingLeft() ? 90 : 280, 16, pos));
                break;
            case 65111007:
            case 65111100:
            case 65121011:
                force.setFromMobOid(fromMobOid);
                force.setForceType(force.isFromMob() ? 4 : 3);
                force.setFirstMobID(firstMobId);
                force.setSkillId(65111007);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, force.isFromMob() ? 32 : 16, chr.isFacingLeft() ? 90 : 280, force.isFromMob() ? 32 : 300, pos));
                break;
            case 80002888:
                force.setFromMobOid(fromMobOid);
                force.setForceType(29);
                force.setSkillId(80002888);
                force.setRect(MapleStatEffectFactory.calculateBoundingBox(pos, true, new Point(-120, -100), new Point(100, 100), 0));
                force.setPos2(chr.getSummonBySkillID(80002888) != null ? new Point(chr.getSummonBySkillID(80002888).getPosition().x, chr.getSummonBySkillID(80002888).getPosition().y - 250) : new Point());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 8, Randomizer.rand(32, 48), Randomizer.rand(32, 48), 0, pos));
                break;
            case 80011585:
            case 80011586:
            case 80011587:
            case 80011588:
            case 80011589:
            case 80011590:
            case 80011635:
                force.setForceType(17);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, 16, chr.isFacingLeft() ? 90 : 280, 16, pos));
                break;
            case 142110011:
                force.setForceType(22);
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 18, chr.isFacingLeft() ? 90 : 280, 300, pos));
                break;
            case 152001001:
            case 152120001:
                force.setForceType(36);
                force.setSkillId(effect.getSourceId());
                force.setRect(effect.getBounds());
                force.setRect2(effect.getBounds2());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, 2, 50, 390, pos));
                break;
            case 152110004:
                force.setForceType(37);
                force.setSkillId(effect.getSourceId());
                force.setRect(effect.getBounds());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 2, 50, 390, pos));
                break;
            case 152120002:
                force.setForceType(39);
                force.setSkillId(effect.getSourceId());
                force.setRect(effect.getBounds());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 64, 24, 310, pos));
                break;
            case 152120017:
                force.setForceType(41);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 48, 8, 310, pos));
                break;
            case 155001000:
                force.setFromMobOid(fromMobOid);
                force.setForceType(43);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 10, 70, 900 + Randomizer.nextInt(10), pos));
                break;
            case 155100009:
                force.setForceType(47);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 3, Randomizer.rand(160, 172), 300, pos));
                break;
            case 155101002:
                force.setFromMobOid(fromMobOid);
                force.setForceType(44);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 10, 70, 900 + Randomizer.nextInt(10), pos));
                break;
            case 155111003:
                force.setFromMobOid(fromMobOid);
                force.setForceType(45);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 10, 70, 900 + Randomizer.nextInt(10), pos));
                break;
            case 155111207:
                force.setForceType(48);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 41, 0, 0, pos));
                break;
            case 155121003:
                force.setFromMobOid(fromMobOid);
                force.setForceType(46);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 10, 70, 900 + Randomizer.nextInt(10), pos));
                break;
            case 164001001:
                force.setForceType(63);
                force.setFirstMobID(chr.getId());
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo_魔封葫蘆符(chr, attackCount, 1));
                break;
            case 164101004:
                force.setForceType(60);
                force.setFromMob(false);
                force.setToMobOid(toMobOid);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 45, 0, 0, pos));
                break;
            case 164120007:
                force.setForceType(61);
                force.setFromMob(false);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), Randomizer.rand(0, 3), Randomizer.rand(32, 48), Randomizer.rand(70, 130), 0, pos));
                break;
            case 175121017:
            case 400051087:
                force.setForceType(60);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 40, 50, 0, pos));
                break;
            case 400011131:
                force.setForceType(67);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 0, 1, 109, 427, pos));
                break;
            case 400021001:
                force.setForceType(28);
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId());
                force.setRect(effect.getBounds());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 36, chr.isFacingLeft() ? 30 : 50, 528, pos));
                List<Integer> t = new LinkedList();

                for(int i = 0; i < force.getInfo().size(); ++i) {
                    t.add(0);
                }

                force.setToMobOid(t);
                break;
            case 400021044:
            case 400021045:
                force.setFromMobOid(fromMobOid);
                force.setForceType(3);
                force.setFirstMobID(firstMobId);
                force.setSkillId(400021044);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 6, 10, 70, 90, pos));
                break;
            case 400021073:
                force.setFromMobOid(fromMobOid);
                force.setForceType(29);
                force.setSkillId(400021073);
                force.setRect(MapleStatEffectFactory.calculateBoundingBox(pos, true, new Point(-120, -100), new Point(100, 100), 0));
                force.setPos2(chr.getSummonBySkillID(400021073) != null ? new Point(chr.getSummonBySkillID(400021073).getPosition().x, chr.getSummonBySkillID(400021073).getPosition().y - 250) : new Point());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, Randomizer.rand(32, 48), Randomizer.rand(32, 48), 0, pos));
                break;
            case 400031000:
                force.setForceType(27);
                force.setSkillId(effect.getSourceId());
                force.setRect(MapleStatEffectFactory.calculateBoundingBox(pos, true, new Point(-120, -100), new Point(100, 100), 0));
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 48, 90, 840, pos));
                break;
            case 400031020:
            case 400031021:
                force.setFromMobOid(0);
                force.setForceType(31);
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 20, 70, 53, pos));
                break;
            case 400031022:
                force.setForceType(34);
                force.setFirstMobID(firstMobId);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 36, chr.isFacingLeft() ? 30 : 50, 528, pos));
                break;
            case 400031028:
            case 400031029:
                force.setForceType(50);
                force.setSkillId(400031029);
                force.setForcedTarget(pos);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, Randomizer.rand(32, 45), Randomizer.rand(1, 64), 56, pos));
                break;
            case 400031031:
                force.setForceType(51);
                force.setSkillId(effect.getSourceId());
                force.setFromMobOid(fromMobOid);
                force.setSkillId(400031031);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 10, 16, Randomizer.rand(48, 70), 0, pos));
                break;
            case 400031054:
                force.setForceType(56);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 3, Randomizer.rand(20, 40), chr.isFacingLeft() ? 90 : 280, 60, pos));
                break;
            case 400041009:
            case 400041010:
                force.setForceType(MapleForceType.幻影卡牌.ordinal());
                force.setFirstMobID(firstMobId);
                force.setSkillId(400041010);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), chr.getSkillEffect(24120002) != null ? 2 : 1, 35, chr.isFacingLeft() ? 90 : 280, 60, pos));
                break;
            case 400041022:
            case 400041023:
                force.setFromMobOid(fromMobOid);
                force.setForceType(33);
                force.setFirstMobID(firstMobId);
                force.setSkillId(400041023);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 32, 70, 70, fromMobOid == 0 ? 760 : 0, pos));
                break;
            case 400041038:
                force.setForceType(49);
                force.setSkillId(400041038);
                force.setForcedTarget(chr.getSummonBySkillID(400041038) != null ? new Point(chr.getSummonBySkillID(400041038).getPosition().x, chr.getSummonBySkillID(400041038).getPosition().y - 250) : new Point());
                force.setArriveDir(chr.getSummonBySkillID(400041038) != null ? chr.getSummonBySkillID(400041038).getObjectId() : 0);
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 2, Randomizer.rand(40, 45), Randomizer.rand(1, 64), 200, pos));
                break;
            case 400041049:
                force.setForceType(60);
                force.setFromMob(false);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 45, 0, 0, pos));
                break;
            case 400051017:
                force.setForceType(30);
                force.setSkillId(effect.getSourceId());
                force.setInfo(this.getForceInfo(chr, effect, attackCount, force.getForceType(), 1, 48, 37, 1440, pos));
        }

        return force;
    }

    private List<MapleForceInfo> getForceInfo(MapleCharacter chr, MapleStatEffect effect, int attackCount, int forceType, int inc, int firstImpact, int angle, int startDelay, Point pos) {
        List<MapleForceInfo> infos = new ArrayList();
        Map<Integer, ForceInfoEntry> map = (Map)this.forceInfos.get(forceType);
        ForceInfoEntry entry;
        if (map != null && (entry = (ForceInfoEntry)map.get(Math.min(map.size(), inc + 1))) != null) {
            int mobCount;
            mobCount = effect.getBulletCount(chr);
            Iterator var16;
            MapleMapObject obj;
            label107:
            switch (effect.getSourceId()) {
                case 3300002:
                case 3321003:
                    var16 = chr.getMap().getMonstersInRect(effect.calculateBoundingBox(chr.getPosition())).iterator();

                    do {
                        if (!var16.hasNext()) {
                            break label107;
                        }

                        obj = (MapleMapObject)var16.next();
                    } while(!((MapleMonster)obj).isBoss());

                    ++mobCount;
                    break;
                case 3300005:
                case 3310004:
                    if (chr.getBuffStatValueHolder(3321034) != null) {
                        ++mobCount;
                    }
                    break;
                case 20031209:
                    mobCount = 5;
                    break;
                case 20031210:
                    mobCount = 10;
                    break;
                case 35110017:
                    MapleStatEffect eff;
                    if ((eff = chr.getEffectForBuffStat(SecondaryStat.BombTime)) != null) {
                        mobCount += eff.getX();
                    }
                    break;
                case 400021001:
                    mobCount = effect.getX();
                    int maxCount = effect.getZ();
                    var16 = chr.getMap().getMonstersInRect(effect.calculateBoundingBox(chr.getPosition())).iterator();

                    while(var16.hasNext()) {
                        obj = (MapleMapObject)var16.next();
                        if (((MapleMonster)obj).getEffectHolder(chr.getId(), MonsterStatus.Burned) != null) {
                            ++mobCount;
                            if (mobCount >= maxCount) {
                                break;
                            }
                        }
                    }
            }

            for(int i = 0; i < mobCount; ++i) {
                MapleForceInfo info = new MapleForceInfo();
                info.setKey(chr.getSpecialStat().gainForceCounter());
                info.setInc(inc);
                info.setFirstImpact(Randomizer.rand(firstImpact - 6, firstImpact));
                info.setSecondImpact(Randomizer.rand(entry.secondImpactMin, entry.secondImpactMax));
                info.setAngle(Randomizer.rand(angle - 20, angle));
                info.setStartDelay(startDelay);
                Point point = new Point();
                if (effect.getSourceId() == 152001001 || effect.getSourceId() == 152120001 || effect.getSourceId() == 152110004) {
                    info.setFirstImpact(50);
                    info.setSecondImpact(50);
                    info.setAngle(0);
                    point = new Point(-48, 7);
                }

                if (effect.getSourceId() == 152120002 || effect.getSourceId() == 3301008 || effect.getSourceId() == 3301009 || effect.getSourceId() == 3300005 || effect.getSourceId() == 3310004) {
                    point = pos;
                }

                if (effect.getSourceId() == 400021001 || effect.getSourceId() == 400031022) {
                    point = new Point(Randomizer.rand(effect.getLt2().x, effect.getRb2().x) + chr.getPosition().x, Randomizer.rand(effect.getLt2().y, effect.getRb2().y) + chr.getPosition().y);
                }

                if (effect.getSourceId() == 400051017) {
                    point = new Point(Randomizer.rand(effect.getLt().x, effect.getRb().x) + chr.getPosition().x, Randomizer.rand(effect.getLt().y, effect.getRb().y) + chr.getPosition().y);
                }

                if (effect.getSourceId() == 400031000 && effect.getSourceId() == 400031000) {
                    info.setFirstImpact(43);
                    info.setSecondImpact(3);
                    info.setAngle(90);
                }

                if (effect.getSourceId() == 164101004) {
                    info.setAngle(Randomizer.nextInt(12) * 30);
                    info.setSecondImpact(3);
                    info.setMaxHitCount(0);
                }

                if (effect.getSourceId() == 400041049) {
                    info.setAngle(Randomizer.nextInt(12) * 30);
                    info.setSecondImpact(3);
                    info.setMaxHitCount(0);
                    info.setInc(i + 1);
                    info.setSecondImpact(3);
                    info.setMaxHitCount(1);
                    info.setStartDelay(45);
                }

                if (effect.getSourceId() == 42110002) {
                    info.setSecondImpact(10);
                }

                if (effect.getSourceId() == 400031054) {
                    point = new Point(-28, -150);
                }

                info.setPosition(point);
                info.setTime(System.currentTimeMillis());
                info.setMaxHitCount(effect.getMobCount());
                info.setEffectIdx(0);
                infos.add(info);
            }
        }

        return infos;
    }

    public List<MapleForceInfo> getForceInfo_惡魔DF(MapleCharacter chr, int attackCount, int inc) {
        List<MapleForceInfo> infos = new ArrayList();
        Map<Integer, ForceInfoEntry> map = (Map)this.forceInfos.get(MapleForceType.惡魔DF.ordinal());
        ForceInfoEntry entry;
        if (map != null && map.size() > 0) {
            entry = (ForceInfoEntry)map.values().iterator().next();
        } else {
            entry = null;
        }

        for(int i = 0; i < attackCount; ++i) {
            MapleForceInfo info = new MapleForceInfo();
            info.setKey(chr.getSpecialStat().gainForceCounter());
            info.setInc(inc);
            info.setFirstImpact(entry == null ? 0 : Randomizer.rand(entry.firstImpactMin, entry.firstImpactMax));
            info.setSecondImpact(entry == null ? 0 : Randomizer.rand(entry.secondImpactMin, entry.secondImpactMax));
            info.setAngle(46);
            info.setStartDelay(0);
            info.setPosition(new Point(0, 0));
            info.setMaxHitCount(0);
            info.setEffectIdx(0);
            infos.add(info);
        }

        return infos;
    }

    public List<MapleForceInfo> getForceInfo_楓幣炸彈(MapleCharacter chr, List<MapleMapItem> list, int n) {
        ArrayList<MapleForceInfo> infos = new ArrayList();
        Map<Integer, ForceInfoEntry> map = (Map)this.forceInfos.get(MapleForceType.楓幣炸彈.ordinal());
        ForceInfoEntry entry;
        if (map != null && (entry = (ForceInfoEntry)map.get(Math.min(map.size(), 2))) != null) {
            Iterator var7 = list.iterator();

            while(var7.hasNext()) {
                MapleMapItem mdrop = (MapleMapItem)var7.next();
                MapleForceInfo info = new MapleForceInfo();
                info.setKey(chr.getSpecialStat().gainForceCounter());
                info.setInc(1);
                info.setFirstImpact(Randomizer.rand(entry.firstImpactMin, entry.firstImpactMax));
                info.setSecondImpact(Randomizer.rand(entry.secondImpactMin, entry.secondImpactMax));
                info.setAngle(Randomizer.rand(20, 45));
                info.setStartDelay(n);
                info.setPosition(new Point(mdrop.getPosition()));
                info.setTime(System.currentTimeMillis());
                info.setMaxHitCount(0);
                info.setEffectIdx(0);
                infos.add(info);
            }
        }

        return infos;
    }

    private List<MapleForceInfo> getForceInfo_暗紅釋魂(MapleCharacter chr, int attackCount, int inc) {
        List<MapleForceInfo> infos = new ArrayList();

        for(int i = 0; i < attackCount; ++i) {
            MapleForceInfo info = new MapleForceInfo();
            info.setKey(chr.getSpecialStat().gainForceCounter());
            info.setInc(inc);
            info.setFirstImpact(Randomizer.rand(20, 40));
            info.setSecondImpact(Randomizer.rand(5, 10));
            info.setAngle(Randomizer.rand(5, 15));
            info.setStartDelay(60);
            info.setPosition(new Point(-70, -10));
            info.setMaxHitCount(0);
            info.setEffectIdx(0);
            infos.add(info);
        }

        return infos;
    }

    private List<MapleForceInfo> getForceInfo_魔封葫蘆符(MapleCharacter chr, int attackCount, int inc) {
        List<MapleForceInfo> infos = new ArrayList();

        for(int i = 0; i < attackCount; ++i) {
            MapleForceInfo info = new MapleForceInfo();
            info.setKey(chr.getSpecialStat().gainForceCounter());
            info.setInc(1);
            info.setFirstImpact(5);
            info.setSecondImpact(30);
            info.setAngle(0);
            info.setStartDelay(0);
            info.setPosition(new Point());
            info.setMaxHitCount(0);
            info.setEffectIdx(0);
            info.setTime(System.currentTimeMillis());
            infos.add(info);
        }

        return infos;
    }

    public MapleForceAtom getMapleForce(MapleCharacter chr, MapleStatEffect effect, List<Integer> oids, Collection<Point> list) {
        MapleForceAtom force = new MapleForceAtom();
        force.setOwnerId(chr.getId());
        force.setForceType(effect.getSourceId() == 155111207 ? 48 : 23);
        force.setFirstMobID(0);
        force.setToMobOid(oids);
        force.setSkillId(effect.getSourceId());
        List<MapleForceInfo> infos = new ArrayList();
        Map<Integer, ForceInfoEntry> map = (Map)this.forceInfos.get(effect.getSourceId() == 155111207 ? 48 : 23);
        ForceInfoEntry entry;
        if (map != null && (entry = (ForceInfoEntry)map.get(Math.min(map.size(), (effect.getSourceId() == 22170070 ? 2 : (effect.getSourceId() == 155111207 ? 0 : 1)) + 1))) != null) {
            Iterator var9 = list.iterator();

            while(var9.hasNext()) {
                Point point = (Point)var9.next();
                MapleForceInfo info = new MapleForceInfo();
                info.setKey(chr.getSpecialStat().gainForceCounter());
                info.setInc(effect.getSourceId() == 22170070 ? 2 : (effect.getSourceId() == 155111207 ? 0 : 1));
                info.setFirstImpact(Randomizer.rand(entry.firstImpactMin, entry.firstImpactMax));
                info.setSecondImpact(effect.getSourceId() == 155111207 ? 100 : Randomizer.rand(entry.secondImpactMin, entry.secondImpactMax));
                info.setAngle(Randomizer.rand(64, 79));
                info.setStartDelay(512);
                info.setPosition(point);
                info.setTime(System.currentTimeMillis());
                info.setMaxHitCount(1);
                info.setEffectIdx(0);
                infos.add(info);
            }
        }

        force.setInfo(infos);
        return force;
    }
}
