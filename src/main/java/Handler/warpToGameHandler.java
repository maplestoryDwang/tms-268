package Handler;

import Client.MapleCharacter;
import Client.MapleClient;
import Client.MapleQuestStatus;
import Client.SecondaryStat;
import Client.skills.SkillMacro;
import Config.constants.GameConstants;
import Config.constants.JobConstants;
import Config.constants.ServerConstants;
import Config.constants.SkillConstants;
import Config.constants.enums.ScriptType;
import Config.constants.skills.劍豪;
import Config.constants.skills.卡蒂娜;
import Config.constants.skills.爆拳槍神;
import Net.server.buffs.MapleStatEffect;
import Net.server.quest.MapleQuest;
import Opcode.Headler.OutHeader;
import Packet.*;
import Server.channel.handler.StatsHandling;
import Server.world.*;
import Server.world.guild.MapleGuild;
import Server.world.guild.MapleGuildResultOption;
import Server.world.messenger.MapleMessenger;
import Server.world.messenger.MapleMessengerCharacter;
import connection.OutPacket;
import connection.packet.Login;
import connection.packet.OverseasPacket;
import SwordieX.enums.GuildResponseType;
import SwordieX.overseas.extraequip.ExtraEquipResult;
import tools.*;
import tools.data.MaplePacketLittleEndianWriter;

import java.awt.*;
import java.util.*;
import java.util.List;

import static Config.constants.skills.惡魔復仇者.血之限界;
import static Packet.MaplePacketCreator.getWarpToMap;

public class warpToGameHandler {
    private static MapleCharacter chr;

    public warpToGameHandler() {
    }

    public static MapleCharacter getChr() {
        return chr;
    }


    public static byte[] is墨玄招式lock(MapleClient c, int mode, int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.EXTRA_SYSTEM_RESULT.getValue());
        mplew.writeInt(-1289454273);
        mplew.writeShort(36);
        mplew.write(0);
        mplew.writeInt(mode);
        mplew.writeInt(type);
        mplew.writeInt(1);
        mplew.write(1);
        mplew.writeInt(132902);
        mplew.write(0);
        mplew.write(32);
        return mplew.getPacket();
    }

    public static void Start(MapleClient c) {
        c.getPlayer().initDamageSkinList();
        c.write(Login.sendServerValues());
        c.write(Login.sendServerEnvironment());
        c.announce(c.getEncryptOpcodesData(ServerConstants.OpcodeEncryptionKey));
        c.write(LP_SetBackgroundEffect());
        c.announce(setNameTagHide());
        c.write(unk_268_1432());
        c.write(unk_268_1447());
        c.announce(linkSkillNotice(c));
        c.write(loginChecking(c));
        c.write(Enter_Field_Unk_Attach("0_18512449_20250309160605956_"));
        c.announce(MaplePacketCreator.getWarpToMap(c.getPlayer(), c.getPlayer().getMap(), (Point)null, 0, true, false));
        c.write(CHAT_SERVER_RESULT());
        c.announce(MaplePacketCreator.changeHour(6, Calendar.getInstance().get(11)));
        c.write(LOGIN_SUCC());
        c.write(LOGIN_SUCC_ATTACH());
        c.write(LP_SetTamingMobInfo(c));
        c.announce(MaplePacketCreator.getKeymap(c.getPlayer()));
        c.write(LP_LoadSkillAction(false));
        c.getPlayer().updatePetAuto();
        c.write(LP_SKILL_MACRO());
        c.write(LP_SetClaimSvrAvailableTime());
        c.announce(MaplePacketCreator.reportResponse());
        c.announce(MaplePacketCreator.enableReport());
        c.write(Competition());
        c.write(LP_ToadsHammerRequestResult());
        c.write(LP_UNK_ENTER_FIELD_458());
        c.write(LP_UNK_ENTER_FIELD_600());
        c.write(CHANGE_MAP_UNK());
        if (c.getPlayer().hasEquipped(1202193)) {
            c.write(EquipRuneSetting());
        }

        c.getPlayer().getMap().userEnterField(c.getPlayer());
        c.getPlayer().initOnlineTime();
        c.getPlayer().giveCoolDowns(PlayerBuffStorage.getCooldownsFromStorage(c.getPlayer().getId()));
        c.getPlayer().silentGiveBuffs(PlayerBuffStorage.getBuffsFromStorage(c.getPlayer().getId()));
        c.getPlayer().initAllInfo();
        c.getPlayer().setOnline(true);
        c.announce(MaplePacketCreator.onTownPortal(999999999, 999999999, 0, (Point)null));
        c.write(LP_UserCancelChair(c));
        c.getPlayer().send(MaplePacketCreator.temporaryStats_Reset());
        c.write(LP_UserSitResult());
        c.write(LP_SpecialChairSitResult());
        c.write(LP_USER_WARP_TO_MAP(c));
        c.write(setDeathCoountMontser());
        c.write(NirvanaPotentialResult());
        c.announce(CWvsContext.sendHexaEnforcementInfo());
        DailyGiftPacket.addDailyGiftInfo(c);
        if (JobConstants.is惡魔復仇者(c.getPlayer().getJob())) {
            c.getPlayer().getSkillEffect(30010242).applyTo(c.getPlayer());
        }

        if (JobConstants.is墨玄(c.getPlayer().getJob())) {
            c.announce(XuanWarpToMap());
            c.getPlayer().setKeyValue("175101007", "5");
        }

        MapleQuestStatus marr;
        if (JobConstants.is天使破壞者(c.getPlayer().getJob())) {
            marr = c.getPlayer().getQuestNAdd(MapleQuest.getInstance(29015));
            if (marr != null && marr.getStatus() == 0) {
                marr.setStatus((byte)1);
            }
        }

        if (JobConstants.is神之子(c.getPlayer().getJob())) {
            marr = c.getPlayer().getQuestNAdd(MapleQuest.getInstance(40905));
            if (marr != null && marr.getStatus() == 0) {
                marr.setStatus((byte)2);
            }
        }

        MapleStatEffect effect;
        if (JobConstants.is爆拳槍神(c.getPlayer().getJob())) {
            effect = c.getPlayer().getSkillEffect(37000010);
            if (effect != null) {
                c.getPlayer().handleAmmoClip(8);
                effect.applyTo(c.getPlayer());
            }
        }

        if (JobConstants.is卡蒂娜(c.getPlayer().getJob())) {
            effect = c.getPlayer().getSkillEffect(400041074);
            if (effect != null) {
                effect.applyTo(c.getPlayer());
            }
        }

        if (c.getPlayer().getGuild() != null) {
            c.announce(GuildPacket.sendGuildResult(MapleGuildResultOption.setGuildUnk(c.getPlayer())));
            c.announce(GuildPacket.sendGuildResult(new MapleGuildResultOption(GuildResponseType.Res_SetSignInReward)));
            c.announce(GuildPacket.sendGuildResult(MapleGuildResultOption.loadGuild(c.getPlayer())));
            c.announce(GuildPacket.sendGuildResult(new MapleGuildResultOption(GuildResponseType.Res_Authkey_Update)));
        } else {
            c.write(startLoadGuild(c));
        }

        if (c.getPlayer().getGuild() != null) {
            WorldGuildService.getInstance().setGuildMemberOnline(c.getPlayer().getMGC(), true, c.getChannel());
            MapleGuild gs = WorldGuildService.getInstance().getGuild(c.getPlayer().getGuildId());
            if (gs != null) {
                List<byte[]> packetList = WorldAllianceService.getInstance().getAllianceInfo(gs.getAllianceId(), true);
                if (packetList != null) {
                    Iterator var3 = packetList.iterator();

                    while(var3.hasNext()) {
                        byte[] pack = (byte[])var3.next();
                        if (pack != null) {
                            c.announce(pack);
                        }
                    }
                }
            }
        } else {
            c.getPlayer().setGuildId(0);
            c.getPlayer().setGuildRank((byte)5);
            c.getPlayer().setAllianceRank((byte)5);
            c.getPlayer().saveGuildStatus();
        }

        if (c.getPlayer().getJob() == 6001 && c.getPlayer().getLevel() < 10) {
            while(c.getPlayer().getLevel() < 10) {
                c.getPlayer().gainExp(5000L, true, false, true);
            }
        }

        if (JobConstants.is狂豹獵人(c.getPlayer().getJob())) {
            c.announce(MaplePacketCreator.updateJaguar(c.getPlayer()));
            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 1; i <= 9; ++i) {
                stringBuilder.append(i).append("=1");
                if (i != 9) {
                    stringBuilder.append(";");
                }
            }

            c.announce(MaplePacketCreator.updateInfoQuest(23008, stringBuilder.toString()));
        }

        if (JobConstants.is劍豪(c.getPlayer().getJob())) {
            effect = c.getPlayer().getSkillEffect(40011291);
            if (effect != null) {
                effect.applyTo(c.getPlayer());
            }

            c.announce(MaplePacketCreator.updateHayatoPoint(0));
        }

        c.getPlayer().fixOnlineTime();
        c.getPlayer().updateWorldShareInfo(6, "enter", DateUtil.getFormatDate(new Date(), "yyyyMM"));
        c.getPlayer().getStat().recalcLocalStats(c.getPlayer());
        String keyValue = c.getPlayer().getKeyValue("MapTransferItemNextTime");
        String newKeyValue = "";
        int today;
        if (keyValue != null) {
            String[] split = keyValue.split(",");
            String[] var19 = split;
            today = split.length;

            for(int var6 = 0; var6 < today; ++var6) {
                String nextTime = var19[var6];
                if (nextTime != null && nextTime.contains("=")) {
                    String[] split_2 = nextTime.split("=");
                    if (split_2.length >= 2) {
                        long nt = Long.parseLong(split_2[1]);
                        if (System.currentTimeMillis() < nt) {
                            newKeyValue = newKeyValue + nt + ",";
                        }
                    }
                }
            }

            if (newKeyValue.isEmpty()) {
                c.getPlayer().setKeyValue("MapTransferItemNextTime", (String)null);
            } else {
                c.getPlayer().setKeyValue("MapTransferItemNextTime", newKeyValue.substring(0, newKeyValue.length() - 1));
            }
        }

        int mvpLevel = c.getPlayer().getMvpLevel();
        if (mvpLevel > 0) {
            mvpLevel = mvpLevel < 5 ? 4 : mvpLevel;
            String gp = c.getPlayer().getWorldShareInfo(6, "gp");
            today = Integer.parseInt(DateUtil.getCurrentDate("dd"));
            String var10000 = DateUtil.getCurrentDate("yyyyMM");
            String now = var10000 + (today > 20 ? "03" : (today > 10 ? "02" : "01")) + StringUtil.getLeftPaddedStr(String.valueOf(mvpLevel), '0', 2);
            if (!now.equals(gp)) {
                c.announce(MaplePacketCreator.mvpPacketTips());
            }
        }

        if (c.getPlayer().getQuestStatus(7707) == 1) {
            MapleQuest.getInstance(7707).reset(c.getPlayer());
        }

        MapleMessenger messenger = c.getPlayer().getMessenger();
        if (messenger != null) {
            WorldMessengerService.getInstance().silentJoinMessenger(messenger.getId(), new MapleMessengerCharacter(c.getPlayer()));
            WorldMessengerService.getInstance().updateMessenger(messenger.getId(), c.getPlayer().getName(), c.getChannel());
        }

        c.announce(MaplePacketCreator.getMacros(c.getPlayer().getSkillMacros()));
        c.announce(MaplePacketCreator.showCharCash(c.getPlayer()));
        if (!c.getPlayer().getTempStatsToRemove().isEmpty()) {
            c.announce(BuffPacket.temporaryStatReset(c.getPlayer().getTempStatsToRemove(), c.getPlayer()));
            c.getPlayer().getTempStatsToRemove().clear();
        }

        c.getPlayer().expirationTask(true);
        if (c.getPlayer().checkSoulWeapon()) {
            c.announce(BuffPacket.giveBuff(c.getPlayer(), c.getPlayer().getSkillEffect(c.getPlayer().getSoulSkillID()), Collections.singletonMap(SecondaryStat.SoulMP, c.getPlayer().getSoulSkillID())));
        }

        if (JobConstants.is夜光(c.getPlayer().getJob())) {
            c.announce(BuffPacket.updateLuminousGauge(5000, 3));
        }

        World.clearChannelChangeDataByAccountId(c.getPlayer().getAccountID());
        c.getPlayer().getCheatTracker().getLastlogonTime();
        c.getPlayer().updateReward();
        c.getPlayer().updateWorldShareInfo(500606, (String)null);
        MapleQuest.getInstance(500606).reset(c.getPlayer());
        World.TemporaryStat.LoadData(c.getPlayer());
        if (SkillConstants.getHyperAP(c.getPlayer()) < 0) {
            StatsHandling.ResetHyperAP(c, c.getPlayer(), true, -1, 0);
        }

        c.getPlayer().getScriptManager().startScript(0, "enterFieldQuest", ScriptType.Npc);
    }

    public static byte[] XuanWarpToMap() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.EXTRA_SYSTEM_RESULT.getValue());
        mplew.writeInt(1065166003);
        mplew.writeShort(39);
        mplew.write(0);
        mplew.writeInt(111);
        mplew.writeInt(3000);
        mplew.writeInt(1);
        mplew.write(1);
        mplew.write(43);
        mplew.writeInt(519);
        mplew.write(4);
        return mplew.getPacket();
    }

    public static OutPacket NirvanaPotentialResult() {
        OutPacket outPacket = new OutPacket(OutHeader.NirvanaPotentialResult);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket setDeathCoountMontser() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_SetDeathCoountMonster);
        outPacket.encodeString("kill_count");
        outPacket.encodeShort(1);
        outPacket.encodeByte(48);
        return outPacket;
    }

    public static OutPacket LP_USER_WARP_TO_MAP(MapleClient c) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_USER_WARP_TO_MAP);
        outPacket.encodeInt(c.getPlayer().getId());
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket LP_UserSitResult() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UserSitResult);
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket LP_SpecialChairSitResult() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_SpecialChairSitResult);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket LP_UserCancelChair(MapleClient c) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UserCancelChair);
        outPacket.encodeInt(c.getPlayer().getId());
        outPacket.encodeByte(1);
        outPacket.encodeByte(1);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket CHANGE_MAP_UNK() {
        OutPacket outPacket = new OutPacket(OutHeader.CHANGE_MAP_UNK);
        outPacket.encodeInt(17);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeShort(0);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket LP_UNK_ENTER_FIELD_600() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UNK_ENTER_FIELD_600);
        outPacket.encodeInt(-279665325);
        return outPacket;
    }

    public static OutPacket LP_UNK_ENTER_FIELD_458() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UNK_ENTER_FIELD_458);
        outPacket.encodeInt(3);
        outPacket.encodeByte(3);
        outPacket.encodeShort(-30466);
        outPacket.encodeByte(66);
        return outPacket;
    }

    public static OutPacket LP_ToadsHammerRequestResult() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_ToadsHammerRequestResult);
        outPacket.encodeInt(587);
        outPacket.encodeInt(1682029);
        outPacket.encodeInt(1682037);
        outPacket.encodeInt(1682045);
        outPacket.encodeInt(1680110);
        outPacket.encodeInt(1680134);
        outPacket.encodeInt(1680118);
        outPacket.encodeInt(1680142);
        outPacket.encodeInt(1680166);
        outPacket.encodeInt(1680126);
        outPacket.encodeInt(1680150);
        outPacket.encodeInt(1680174);
        outPacket.encodeInt(1680198);
        outPacket.encodeInt(1680158);
        outPacket.encodeInt(1680182);
        outPacket.encodeInt(1680206);
        outPacket.encodeInt(1680230);
        outPacket.encodeInt(1680190);
        outPacket.encodeInt(1680214);
        outPacket.encodeInt(1680238);
        outPacket.encodeInt(1680262);
        outPacket.encodeInt(1680222);
        outPacket.encodeInt(1680246);
        outPacket.encodeInt(1680270);
        outPacket.encodeInt(1680294);
        outPacket.encodeInt(1680254);
        outPacket.encodeInt(1680278);
        outPacket.encodeInt(1680286);
        outPacket.encodeInt(1680358);
        outPacket.encodeInt(1680366);
        outPacket.encodeInt(1680390);
        outPacket.encodeInt(1680350);
        outPacket.encodeInt(1680374);
        outPacket.encodeInt(1680398);
        outPacket.encodeInt(1680422);
        outPacket.encodeInt(1680382);
        outPacket.encodeInt(1680406);
        outPacket.encodeInt(1680430);
        outPacket.encodeInt(1680454);
        outPacket.encodeInt(1680414);
        outPacket.encodeInt(1680438);
        outPacket.encodeInt(1680462);
        outPacket.encodeInt(1680486);
        outPacket.encodeInt(1680446);
        outPacket.encodeInt(1680470);
        outPacket.encodeInt(1680518);
        outPacket.encodeInt(1680478);
        outPacket.encodeInt(1680526);
        outPacket.encodeInt(1680550);
        outPacket.encodeInt(1680582);
        outPacket.encodeInt(1680510);
        outPacket.encodeInt(1680534);
        outPacket.encodeInt(1680558);
        outPacket.encodeInt(1680614);
        outPacket.encodeInt(1680590);
        outPacket.encodeInt(1680542);
        outPacket.encodeInt(1680566);
        outPacket.encodeInt(1680622);
        outPacket.encodeInt(1680598);
        outPacket.encodeInt(1680574);
        outPacket.encodeInt(1680678);
        outPacket.encodeInt(1680654);
        outPacket.encodeInt(1680630);
        outPacket.encodeInt(1680606);
        outPacket.encodeInt(1680710);
        outPacket.encodeInt(1680686);
        outPacket.encodeInt(1680662);
        outPacket.encodeInt(1680638);
        outPacket.encodeInt(1680742);
        outPacket.encodeInt(1680718);
        outPacket.encodeInt(1680694);
        outPacket.encodeInt(1680670);
        outPacket.encodeInt(1680726);
        outPacket.encodeInt(1680702);
        outPacket.encodeInt(1680734);
        outPacket.encodeInt(1682030);
        outPacket.encodeInt(1682038);
        outPacket.encodeInt(1682046);
        outPacket.encodeInt(1680111);
        outPacket.encodeInt(1680135);
        outPacket.encodeInt(1680119);
        outPacket.encodeInt(1680143);
        outPacket.encodeInt(1680167);
        outPacket.encodeInt(1680127);
        outPacket.encodeInt(1680151);
        outPacket.encodeInt(1680175);
        outPacket.encodeInt(1680199);
        outPacket.encodeInt(1680159);
        outPacket.encodeInt(1680183);
        outPacket.encodeInt(1680207);
        outPacket.encodeInt(1680231);
        outPacket.encodeInt(1680191);
        outPacket.encodeInt(1680215);
        outPacket.encodeInt(1680239);
        outPacket.encodeInt(1680263);
        outPacket.encodeInt(1680223);
        outPacket.encodeInt(1680247);
        outPacket.encodeInt(1680271);
        outPacket.encodeInt(1680295);
        outPacket.encodeInt(1680255);
        outPacket.encodeInt(1680279);
        outPacket.encodeInt(1680287);
        outPacket.encodeInt(1680359);
        outPacket.encodeInt(1680367);
        outPacket.encodeInt(1680391);
        outPacket.encodeInt(1680351);
        outPacket.encodeInt(1680375);
        outPacket.encodeInt(1680399);
        outPacket.encodeInt(1680423);
        outPacket.encodeInt(1680383);
        outPacket.encodeInt(1680407);
        outPacket.encodeInt(1680431);
        outPacket.encodeInt(1680455);
        outPacket.encodeInt(1680415);
        outPacket.encodeInt(1680439);
        outPacket.encodeInt(1680463);
        outPacket.encodeInt(1680487);
        outPacket.encodeInt(1680447);
        outPacket.encodeInt(1680471);
        outPacket.encodeInt(1680519);
        outPacket.encodeInt(1680479);
        outPacket.encodeInt(1680503);
        outPacket.encodeInt(1680527);
        outPacket.encodeInt(1680551);
        outPacket.encodeInt(1680583);
        outPacket.encodeInt(1680511);
        outPacket.encodeInt(1680535);
        outPacket.encodeInt(1680559);
        outPacket.encodeInt(1680615);
        outPacket.encodeInt(1680591);
        outPacket.encodeInt(1680543);
        outPacket.encodeInt(1680567);
        outPacket.encodeInt(1680623);
        outPacket.encodeInt(1680575);
        outPacket.encodeInt(1680679);
        outPacket.encodeInt(1680655);
        outPacket.encodeInt(1680631);
        outPacket.encodeInt(1680607);
        outPacket.encodeInt(1680711);
        outPacket.encodeInt(1680687);
        outPacket.encodeInt(1680663);
        outPacket.encodeInt(1680743);
        outPacket.encodeInt(1680719);
        outPacket.encodeInt(1680695);
        outPacket.encodeInt(1680671);
        outPacket.encodeInt(1680727);
        outPacket.encodeInt(1680703);
        outPacket.encodeInt(1680735);
        outPacket.encodeInt(1682031);
        outPacket.encodeInt(1682039);
        outPacket.encodeInt(1682047);
        outPacket.encodeInt(1680128);
        outPacket.encodeInt(1680112);
        outPacket.encodeInt(1680136);
        outPacket.encodeInt(1680160);
        outPacket.encodeInt(1680120);
        outPacket.encodeInt(1680144);
        outPacket.encodeInt(1680168);
        outPacket.encodeInt(1680192);
        outPacket.encodeInt(1680152);
        outPacket.encodeInt(1680176);
        outPacket.encodeInt(1680200);
        outPacket.encodeInt(1680224);
        outPacket.encodeInt(1680184);
        outPacket.encodeInt(1680208);
        outPacket.encodeInt(1680232);
        outPacket.encodeInt(1680256);
        outPacket.encodeInt(1680216);
        outPacket.encodeInt(1680240);
        outPacket.encodeInt(1680264);
        outPacket.encodeInt(1680288);
        outPacket.encodeInt(1680248);
        outPacket.encodeInt(1680272);
        outPacket.encodeInt(1680296);
        outPacket.encodeInt(1680280);
        outPacket.encodeInt(1680352);
        outPacket.encodeInt(1680360);
        outPacket.encodeInt(1680384);
        outPacket.encodeInt(1680368);
        outPacket.encodeInt(1680392);
        outPacket.encodeInt(1680416);
        outPacket.encodeInt(1680376);
        outPacket.encodeInt(1680400);
        outPacket.encodeInt(1680424);
        outPacket.encodeInt(1680448);
        outPacket.encodeInt(1680408);
        outPacket.encodeInt(1680432);
        outPacket.encodeInt(1680456);
        outPacket.encodeInt(1680480);
        outPacket.encodeInt(1680440);
        outPacket.encodeInt(1680464);
        outPacket.encodeInt(1680488);
        outPacket.encodeInt(1680512);
        outPacket.encodeInt(1680472);
        outPacket.encodeInt(1680520);
        outPacket.encodeInt(1680544);
        outPacket.encodeInt(1680576);
        outPacket.encodeInt(1680504);
        outPacket.encodeInt(1680528);
        outPacket.encodeInt(1680552);
        outPacket.encodeInt(1680608);
        outPacket.encodeInt(1680584);
        outPacket.encodeInt(1680536);
        outPacket.encodeInt(1680560);
        outPacket.encodeInt(1680616);
        outPacket.encodeInt(1680592);
        outPacket.encodeInt(1680568);
        outPacket.encodeInt(1680672);
        outPacket.encodeInt(1680624);
        outPacket.encodeInt(1680704);
        outPacket.encodeInt(1680680);
        outPacket.encodeInt(1680656);
        outPacket.encodeInt(1680632);
        outPacket.encodeInt(1680736);
        outPacket.encodeInt(1680712);
        outPacket.encodeInt(1680688);
        outPacket.encodeInt(1680664);
        outPacket.encodeInt(1680744);
        outPacket.encodeInt(1680720);
        outPacket.encodeInt(1680696);
        outPacket.encodeInt(1680728);
        outPacket.encodeInt(1682048);
        outPacket.encodeInt(1682032);
        outPacket.encodeInt(1682040);
        outPacket.encodeInt(1680129);
        outPacket.encodeInt(1680113);
        outPacket.encodeInt(1680137);
        outPacket.encodeInt(1680161);
        outPacket.encodeInt(1680121);
        outPacket.encodeInt(1680145);
        outPacket.encodeInt(1680193);
        outPacket.encodeInt(1680153);
        outPacket.encodeInt(1680177);
        outPacket.encodeInt(1680201);
        outPacket.encodeInt(1680225);
        outPacket.encodeInt(1680185);
        outPacket.encodeInt(1680209);
        outPacket.encodeInt(1680233);
        outPacket.encodeInt(1680257);
        outPacket.encodeInt(1680217);
        outPacket.encodeInt(1680241);
        outPacket.encodeInt(1680265);
        outPacket.encodeInt(1680289);
        outPacket.encodeInt(1680249);
        outPacket.encodeInt(1680273);
        outPacket.encodeInt(1680297);
        outPacket.encodeInt(1680281);
        outPacket.encodeInt(1680353);
        outPacket.encodeInt(1680361);
        outPacket.encodeInt(1680385);
        outPacket.encodeInt(1680369);
        outPacket.encodeInt(1680393);
        outPacket.encodeInt(1680417);
        outPacket.encodeInt(1680377);
        outPacket.encodeInt(1680401);
        outPacket.encodeInt(1680425);
        outPacket.encodeInt(1680449);
        outPacket.encodeInt(1680409);
        outPacket.encodeInt(1680433);
        outPacket.encodeInt(1680457);
        outPacket.encodeInt(1680481);
        outPacket.encodeInt(1680441);
        outPacket.encodeInt(1680465);
        outPacket.encodeInt(1680489);
        outPacket.encodeInt(1680513);
        outPacket.encodeInt(1680473);
        outPacket.encodeInt(1680521);
        outPacket.encodeInt(1680545);
        outPacket.encodeInt(1680577);
        outPacket.encodeInt(1680505);
        outPacket.encodeInt(1680529);
        outPacket.encodeInt(1680553);
        outPacket.encodeInt(1680609);
        outPacket.encodeInt(1680585);
        outPacket.encodeInt(1680537);
        outPacket.encodeInt(1680561);
        outPacket.encodeInt(1680617);
        outPacket.encodeInt(1680593);
        outPacket.encodeInt(1680569);
        outPacket.encodeInt(1680673);
        outPacket.encodeInt(1680625);
        outPacket.encodeInt(1680705);
        outPacket.encodeInt(1680681);
        outPacket.encodeInt(1680657);
        outPacket.encodeInt(1680633);
        outPacket.encodeInt(1680737);
        outPacket.encodeInt(1680713);
        outPacket.encodeInt(1680689);
        outPacket.encodeInt(1680665);
        outPacket.encodeInt(1680745);
        outPacket.encodeInt(1680721);
        outPacket.encodeInt(1680697);
        outPacket.encodeInt(1680729);
        outPacket.encodeInt(1682049);
        outPacket.encodeInt(1682033);
        outPacket.encodeInt(1682041);
        outPacket.encodeInt(1680130);
        outPacket.encodeInt(1680114);
        outPacket.encodeInt(1680138);
        outPacket.encodeInt(1680162);
        outPacket.encodeInt(1680122);
        outPacket.encodeInt(1680146);
        outPacket.encodeInt(1680170);
        outPacket.encodeInt(1680194);
        outPacket.encodeInt(1680154);
        outPacket.encodeInt(1680178);
        outPacket.encodeInt(1680202);
        outPacket.encodeInt(1680226);
        outPacket.encodeInt(1680186);
        outPacket.encodeInt(1680210);
        outPacket.encodeInt(1680234);
        outPacket.encodeInt(1680258);
        outPacket.encodeInt(1680218);
        outPacket.encodeInt(1680242);
        outPacket.encodeInt(1680266);
        outPacket.encodeInt(1680290);
        outPacket.encodeInt(1680250);
        outPacket.encodeInt(1680274);
        outPacket.encodeInt(1680298);
        outPacket.encodeInt(1680282);
        outPacket.encodeInt(1680354);
        outPacket.encodeInt(1680362);
        outPacket.encodeInt(1680386);
        outPacket.encodeInt(1680370);
        outPacket.encodeInt(1680394);
        outPacket.encodeInt(1680418);
        outPacket.encodeInt(1680378);
        outPacket.encodeInt(1680402);
        outPacket.encodeInt(1680426);
        outPacket.encodeInt(1680450);
        outPacket.encodeInt(1680410);
        outPacket.encodeInt(1680434);
        outPacket.encodeInt(1680458);
        outPacket.encodeInt(1680482);
        outPacket.encodeInt(1680442);
        outPacket.encodeInt(1680466);
        outPacket.encodeInt(1680490);
        outPacket.encodeInt(1680514);
        outPacket.encodeInt(1680474);
        outPacket.encodeInt(1680522);
        outPacket.encodeInt(1680546);
        outPacket.encodeInt(1680578);
        outPacket.encodeInt(1680506);
        outPacket.encodeInt(1680530);
        outPacket.encodeInt(1680554);
        outPacket.encodeInt(1680610);
        outPacket.encodeInt(1680586);
        outPacket.encodeInt(1680538);
        outPacket.encodeInt(1680562);
        outPacket.encodeInt(1680618);
        outPacket.encodeInt(1680594);
        outPacket.encodeInt(1680570);
        outPacket.encodeInt(1680674);
        outPacket.encodeInt(1680626);
        outPacket.encodeInt(1680706);
        outPacket.encodeInt(1680682);
        outPacket.encodeInt(1680658);
        outPacket.encodeInt(1680634);
        outPacket.encodeInt(1680738);
        outPacket.encodeInt(1680714);
        outPacket.encodeInt(1680690);
        outPacket.encodeInt(1680666);
        outPacket.encodeInt(1680746);
        outPacket.encodeInt(1680722);
        outPacket.encodeInt(1680698);
        outPacket.encodeInt(1680730);
        outPacket.encodeInt(1682050);
        outPacket.encodeInt(1682034);
        outPacket.encodeInt(1682042);
        outPacket.encodeInt(1680107);
        outPacket.encodeInt(1680131);
        outPacket.encodeInt(1680115);
        outPacket.encodeInt(1680139);
        outPacket.encodeInt(1680163);
        outPacket.encodeInt(1680123);
        outPacket.encodeInt(1680147);
        outPacket.encodeInt(1680171);
        outPacket.encodeInt(1680195);
        outPacket.encodeInt(1680155);
        outPacket.encodeInt(1680179);
        outPacket.encodeInt(1680203);
        outPacket.encodeInt(1680227);
        outPacket.encodeInt(1680187);
        outPacket.encodeInt(1680211);
        outPacket.encodeInt(1680235);
        outPacket.encodeInt(1680259);
        outPacket.encodeInt(1680219);
        outPacket.encodeInt(1680243);
        outPacket.encodeInt(1680267);
        outPacket.encodeInt(1680291);
        outPacket.encodeInt(1680251);
        outPacket.encodeInt(1680275);
        outPacket.encodeInt(1680283);
        outPacket.encodeInt(1680355);
        outPacket.encodeInt(1680363);
        outPacket.encodeInt(1680387);
        outPacket.encodeInt(1680347);
        outPacket.encodeInt(1680371);
        outPacket.encodeInt(1680395);
        outPacket.encodeInt(1680419);
        outPacket.encodeInt(1680379);
        outPacket.encodeInt(1680403);
        outPacket.encodeInt(1680427);
        outPacket.encodeInt(1680451);
        outPacket.encodeInt(1680411);
        outPacket.encodeInt(1680435);
        outPacket.encodeInt(1680459);
        outPacket.encodeInt(1680483);
        outPacket.encodeInt(1680443);
        outPacket.encodeInt(1680467);
        outPacket.encodeInt(1680515);
        outPacket.encodeInt(1680475);
        outPacket.encodeInt(1680523);
        outPacket.encodeInt(1680547);
        outPacket.encodeInt(1680579);
        outPacket.encodeInt(1680507);
        outPacket.encodeInt(1680531);
        outPacket.encodeInt(1680555);
        outPacket.encodeInt(1680611);
        outPacket.encodeInt(1680587);
        outPacket.encodeInt(1680539);
        outPacket.encodeInt(1680563);
        outPacket.encodeInt(1680619);
        outPacket.encodeInt(1680595);
        outPacket.encodeInt(1680571);
        outPacket.encodeInt(1680675);
        outPacket.encodeInt(1680651);
        outPacket.encodeInt(1680627);
        outPacket.encodeInt(1680603);
        outPacket.encodeInt(1680707);
        outPacket.encodeInt(1680683);
        outPacket.encodeInt(1680659);
        outPacket.encodeInt(1680635);
        outPacket.encodeInt(1680739);
        outPacket.encodeInt(1680715);
        outPacket.encodeInt(1680691);
        outPacket.encodeInt(1680667);
        outPacket.encodeInt(1680723);
        outPacket.encodeInt(1680699);
        outPacket.encodeInt(1680731);
        outPacket.encodeInt(1682027);
        outPacket.encodeInt(1682035);
        outPacket.encodeInt(1682043);
        outPacket.encodeInt(1680108);
        outPacket.encodeInt(1680132);
        outPacket.encodeInt(1680116);
        outPacket.encodeInt(1680140);
        outPacket.encodeInt(1680164);
        outPacket.encodeInt(1680124);
        outPacket.encodeInt(1680148);
        outPacket.encodeInt(1680172);
        outPacket.encodeInt(1680196);
        outPacket.encodeInt(1680156);
        outPacket.encodeInt(1680180);
        outPacket.encodeInt(1680204);
        outPacket.encodeInt(1680228);
        outPacket.encodeInt(1680188);
        outPacket.encodeInt(1680212);
        outPacket.encodeInt(1680236);
        outPacket.encodeInt(1680260);
        outPacket.encodeInt(1680220);
        outPacket.encodeInt(1680244);
        outPacket.encodeInt(1680268);
        outPacket.encodeInt(1680292);
        outPacket.encodeInt(1680252);
        outPacket.encodeInt(1680276);
        outPacket.encodeInt(1680284);
        outPacket.encodeInt(1680356);
        outPacket.encodeInt(1680364);
        outPacket.encodeInt(1680388);
        outPacket.encodeInt(1680348);
        outPacket.encodeInt(1680372);
        outPacket.encodeInt(1680396);
        outPacket.encodeInt(1680420);
        outPacket.encodeInt(1680380);
        outPacket.encodeInt(1680404);
        outPacket.encodeInt(1680428);
        outPacket.encodeInt(1680452);
        outPacket.encodeInt(1680412);
        outPacket.encodeInt(1680436);
        outPacket.encodeInt(1680460);
        outPacket.encodeInt(1680484);
        outPacket.encodeInt(1680444);
        outPacket.encodeInt(1680468);
        outPacket.encodeInt(1680516);
        outPacket.encodeInt(1680476);
        outPacket.encodeInt(1680524);
        outPacket.encodeInt(1680548);
        outPacket.encodeInt(1680580);
        outPacket.encodeInt(1680508);
        outPacket.encodeInt(1680532);
        outPacket.encodeInt(1680556);
        outPacket.encodeInt(1680612);
        outPacket.encodeInt(1680588);
        outPacket.encodeInt(1680540);
        outPacket.encodeInt(1680564);
        outPacket.encodeInt(1680620);
        outPacket.encodeInt(1680596);
        outPacket.encodeInt(1680572);
        outPacket.encodeInt(1680676);
        outPacket.encodeInt(1680652);
        outPacket.encodeInt(1680628);
        outPacket.encodeInt(1680604);
        outPacket.encodeInt(1680708);
        outPacket.encodeInt(1680684);
        outPacket.encodeInt(1680660);
        outPacket.encodeInt(1680636);
        outPacket.encodeInt(1680740);
        outPacket.encodeInt(1680716);
        outPacket.encodeInt(1680692);
        outPacket.encodeInt(1680668);
        outPacket.encodeInt(1680724);
        outPacket.encodeInt(1680700);
        outPacket.encodeInt(1680732);
        outPacket.encodeInt(1682028);
        outPacket.encodeInt(1682036);
        outPacket.encodeInt(1682044);
        outPacket.encodeInt(1680109);
        outPacket.encodeInt(1680133);
        outPacket.encodeInt(1680117);
        outPacket.encodeInt(1680141);
        outPacket.encodeInt(1680165);
        outPacket.encodeInt(1680125);
        outPacket.encodeInt(1680149);
        outPacket.encodeInt(1680173);
        outPacket.encodeInt(1680197);
        outPacket.encodeInt(1680157);
        outPacket.encodeInt(1680181);
        outPacket.encodeInt(1680205);
        outPacket.encodeInt(1680229);
        outPacket.encodeInt(1680189);
        outPacket.encodeInt(1680213);
        outPacket.encodeInt(1680237);
        outPacket.encodeInt(1680261);
        outPacket.encodeInt(1680221);
        outPacket.encodeInt(1680245);
        outPacket.encodeInt(1680269);
        outPacket.encodeInt(1680293);
        outPacket.encodeInt(1680253);
        outPacket.encodeInt(1680277);
        outPacket.encodeInt(1680285);
        outPacket.encodeInt(1680357);
        outPacket.encodeInt(1680365);
        outPacket.encodeInt(1680389);
        outPacket.encodeInt(1680349);
        outPacket.encodeInt(1680373);
        outPacket.encodeInt(1680397);
        outPacket.encodeInt(1680421);
        outPacket.encodeInt(1680381);
        outPacket.encodeInt(1680405);
        outPacket.encodeInt(1680429);
        outPacket.encodeInt(1680453);
        outPacket.encodeInt(1680413);
        outPacket.encodeInt(1680437);
        outPacket.encodeInt(1680461);
        outPacket.encodeInt(1680485);
        outPacket.encodeInt(1680445);
        outPacket.encodeInt(1680469);
        outPacket.encodeInt(1680517);
        outPacket.encodeInt(1680477);
        outPacket.encodeInt(1680525);
        outPacket.encodeInt(1680549);
        outPacket.encodeInt(1680581);
        outPacket.encodeInt(1680509);
        outPacket.encodeInt(1680533);
        outPacket.encodeInt(1680557);
        outPacket.encodeInt(1680613);
        outPacket.encodeInt(1680589);
        outPacket.encodeInt(1680541);
        outPacket.encodeInt(1680565);
        outPacket.encodeInt(1680621);
        outPacket.encodeInt(1680597);
        outPacket.encodeInt(1680573);
        outPacket.encodeInt(1680677);
        outPacket.encodeInt(1680653);
        outPacket.encodeInt(1680629);
        outPacket.encodeInt(1680605);
        outPacket.encodeInt(1680709);
        outPacket.encodeInt(1680685);
        outPacket.encodeInt(1680661);
        outPacket.encodeInt(1680637);
        outPacket.encodeInt(1680741);
        outPacket.encodeInt(1680717);
        outPacket.encodeInt(1680693);
        outPacket.encodeInt(1680669);
        outPacket.encodeInt(1680725);
        outPacket.encodeInt(1680701);
        outPacket.encodeInt(1680733);
        return outPacket;
    }

    public static OutPacket Competition() {
        OutPacket outPacket = new OutPacket(OutHeader.Competition);
        outPacket.encodeBoolean(true);
        outPacket.encodeInt(23);
        outPacket.encodeInt(0);
        outPacket.encodeInt(6);
        outPacket.encodeInt(1002357);
        outPacket.encodeInt(1012478);
        outPacket.encodeInt(1022231);
        outPacket.encodeInt(1242099);
        outPacket.encodeInt(2048700);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(1);
        outPacket.encodeInt(6);
        outPacket.encodeInt(1002357);
        outPacket.encodeInt(1012478);
        outPacket.encodeInt(1022231);
        outPacket.encodeInt(1242099);
        outPacket.encodeInt(2048700);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(3);
        outPacket.encodeInt(12);
        outPacket.encodeInt(1072740);
        outPacket.encodeInt(1072741);
        outPacket.encodeInt(1102479);
        outPacket.encodeInt(1102480);
        outPacket.encodeInt(1132167);
        outPacket.encodeInt(1132168);
        outPacket.encodeInt(1132172);
        outPacket.encodeInt(1132173);
        outPacket.encodeInt(1152170);
        outPacket.encodeInt(1182087);
        outPacket.encodeInt(2048700);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(4);
        outPacket.encodeInt(13);
        outPacket.encodeInt(1004217);
        outPacket.encodeInt(1004218);
        outPacket.encodeInt(1052787);
        outPacket.encodeInt(1052788);
        outPacket.encodeInt(1072955);
        outPacket.encodeInt(1072956);
        outPacket.encodeInt(1082596);
        outPacket.encodeInt(1082597);
        outPacket.encodeInt(1162009);
        outPacket.encodeInt(1242035);
        outPacket.encodeInt(1242131);
        outPacket.encodeInt(2048701);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(5);
        outPacket.encodeInt(4);
        outPacket.encodeInt(1032241);
        outPacket.encodeInt(1113149);
        outPacket.encodeInt(2048701);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(10);
        outPacket.encodeInt(4);
        outPacket.encodeInt(1032241);
        outPacket.encodeInt(1113149);
        outPacket.encodeInt(2048701);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(12);
        outPacket.encodeInt(4);
        outPacket.encodeInt(1050253);
        outPacket.encodeInt(1072776);
        outPacket.encodeInt(1122254);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(14);
        outPacket.encodeInt(4);
        outPacket.encodeInt(1032241);
        outPacket.encodeInt(1113149);
        outPacket.encodeInt(2048701);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(15);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1022232);
        outPacket.encodeInt(1132272);
        outPacket.encodeInt(1162025);
        outPacket.encodeInt(2048701);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(17);
        outPacket.encodeInt(11);
        outPacket.encodeInt(1004237);
        outPacket.encodeInt(1004238);
        outPacket.encodeInt(1052807);
        outPacket.encodeInt(1052808);
        outPacket.encodeInt(1072975);
        outPacket.encodeInt(1072976);
        outPacket.encodeInt(1082616);
        outPacket.encodeInt(1082617);
        outPacket.encodeInt(1102716);
        outPacket.encodeInt(1102717);
        outPacket.encodeInt(1242103);
        outPacket.encodeInt(18);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1050253);
        outPacket.encodeInt(1072776);
        outPacket.encodeInt(1122150);
        outPacket.encodeInt(1122254);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(19);
        outPacket.encodeInt(12);
        outPacket.encodeInt(1072740);
        outPacket.encodeInt(1072741);
        outPacket.encodeInt(1102479);
        outPacket.encodeInt(1102480);
        outPacket.encodeInt(1132167);
        outPacket.encodeInt(1132168);
        outPacket.encodeInt(1132172);
        outPacket.encodeInt(1132173);
        outPacket.encodeInt(1152170);
        outPacket.encodeInt(1182087);
        outPacket.encodeInt(2048700);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(21);
        outPacket.encodeInt(3);
        outPacket.encodeInt(1050253);
        outPacket.encodeInt(1072776);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(22);
        outPacket.encodeInt(13);
        outPacket.encodeInt(1004217);
        outPacket.encodeInt(1004218);
        outPacket.encodeInt(1052787);
        outPacket.encodeInt(1052788);
        outPacket.encodeInt(1072955);
        outPacket.encodeInt(1072956);
        outPacket.encodeInt(1082596);
        outPacket.encodeInt(1082597);
        outPacket.encodeInt(1162009);
        outPacket.encodeInt(1242035);
        outPacket.encodeInt(1242131);
        outPacket.encodeInt(2048701);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(23);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1022232);
        outPacket.encodeInt(1132272);
        outPacket.encodeInt(1162025);
        outPacket.encodeInt(2048701);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(24);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1050253);
        outPacket.encodeInt(1072776);
        outPacket.encodeInt(1152112);
        outPacket.encodeInt(1152113);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(25);
        outPacket.encodeInt(9);
        outPacket.encodeInt(1003112);
        outPacket.encodeInt(1004637);
        outPacket.encodeInt(1012478);
        outPacket.encodeInt(1022231);
        outPacket.encodeInt(1102871);
        outPacket.encodeInt(1132296);
        outPacket.encodeInt(1242099);
        outPacket.encodeInt(2048700);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(30);
        outPacket.encodeInt(15);
        outPacket.encodeInt(1050253);
        outPacket.encodeInt(1072740);
        outPacket.encodeInt(1072741);
        outPacket.encodeInt(1072776);
        outPacket.encodeInt(1102479);
        outPacket.encodeInt(1102480);
        outPacket.encodeInt(1102484);
        outPacket.encodeInt(1102485);
        outPacket.encodeInt(1132167);
        outPacket.encodeInt(1132168);
        outPacket.encodeInt(1132172);
        outPacket.encodeInt(1132173);
        outPacket.encodeInt(1152170);
        outPacket.encodeInt(1182087);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(50);
        outPacket.encodeInt(1);
        outPacket.encodeInt(1113313);
        outPacket.encodeInt(52);
        outPacket.encodeInt(1);
        outPacket.encodeInt(1113313);
        outPacket.encodeInt(1000);
        outPacket.encodeInt(4);
        outPacket.encodeInt(1012478);
        outPacket.encodeInt(1022231);
        outPacket.encodeInt(2048700);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(1002);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1032220);
        outPacket.encodeInt(1113072);
        outPacket.encodeInt(1122264);
        outPacket.encodeInt(1132243);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(1003);
        outPacket.encodeInt(7);
        outPacket.encodeInt(1132159);
        outPacket.encodeInt(1132160);
        outPacket.encodeInt(1152097);
        outPacket.encodeInt(1152098);
        outPacket.encodeInt(1242077);
        outPacket.encodeInt(1242078);
        outPacket.encodeInt(2711000);
        outPacket.encodeInt(4);
        outPacket.encodeString("arcane");
        outPacket.encodeInt(0);
        outPacket.encodeString("competition");
        outPacket.encodeInt(0);
        outPacket.encodeString("growth");
        outPacket.encodeInt(0);
        outPacket.encodeString("story");
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket LP_SetClaimSvrAvailableTime() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_SetClaimSvrAvailableTime);
        outPacket.encodeShort(0);
        return outPacket;
    }

    public static OutPacket LP_SKILL_MACRO() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_SKILL_MACRO);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket LP_LoadSkillAction(boolean unk) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_LoadSkillAction);
        outPacket.encodeBoolean(unk);
        return outPacket;
    }

    public static OutPacket LP_SetTamingMobInfo(MapleClient c) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_SetTamingMobInfo);
        outPacket.encodeInt(c.getPlayer().getId());
        outPacket.encodeInt(1);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket LOGIN_SUCC() {
        OutPacket outPacket = new OutPacket(OutHeader.LOGIN_SUCC);
        return outPacket;
    }

    public static OutPacket LOGIN_SUCC_ATTACH() {
        OutPacket outPacket = new OutPacket(OutHeader.LOGIN_SUCC_ATTACH);
        return outPacket;
    }

    public static OutPacket CHAT_SERVER_RESULT() {
        OutPacket outPacket = new OutPacket(OutHeader.CHAT_SERVER_RESULT);
        outPacket.encodeInt(0);
        outPacket.encodeShort(0);
        return outPacket;
    }

    public static OutPacket Enter_Field_Unk_Attach(String key) {
        OutPacket outPacket = new OutPacket(OutHeader.Enter_Field_Unk_Attach);
        outPacket.encodeString(key);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket LP_SetBackgroundEffect() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_SetBackgroundEffect);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1);
        outPacket.encodeInt(99);
        outPacket.encodeInt(2000);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeLong(4600877379321698714L);
        outPacket.encodeInt(100);
        outPacket.encodeInt(199);
        outPacket.encodeInt(4000);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeLong(4600877379321698714L);
        outPacket.encodeInt(200);
        outPacket.encodeInt(259);
        outPacket.encodeInt(8000);
        outPacket.encodeInt(5);
        outPacket.encodeInt(500);
        outPacket.encodeInt(0);
        outPacket.encodeLong(4600877379321698714L);
        outPacket.encodeInt(260);
        outPacket.encodeInt(279);
        outPacket.encodeInt(15000);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1000);
        outPacket.encodeInt(0);
        outPacket.encodeLong(4600877379321698714L);
        outPacket.encodeInt(280);
        outPacket.encodeInt(300);
        outPacket.encodeInt(20000);
        outPacket.encodeInt(5);
        outPacket.encodeInt(1500);
        outPacket.encodeInt(0);
        outPacket.encodeLong(4600877379321698714L);
        return outPacket;
    }

    public static OutPacket startLoadGuild(MapleClient c) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_GuildResult);
        outPacket.encodeInt(128);
        outPacket.encodeInt(0);
        c.write(startLoadGuildN(c));
        return outPacket;
    }

    public static byte[] linkSkillNotice(MapleClient c) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetTeachSkillCost.getValue());
        mplew.writeInt(10);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(5000000);
        mplew.writeInt(6000000);
        mplew.writeInt(7000000);
        mplew.writeInt(8000000);
        mplew.writeInt(9000000);
        mplew.writeInt(10000000);
        return mplew.getPacket();
    }

    public static OutPacket startLoadGuildN(MapleClient c) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_GuildResult);
        outPacket.encodeInt(129);
        outPacket.encodeInt(4);
        outPacket.encodeInt(100);
        outPacket.encodeInt(2000);
        outPacket.encodeInt(60);
        outPacket.encodeInt(1000);
        outPacket.encodeInt(30);
        outPacket.encodeInt(100);
        outPacket.encodeInt(15);
        outPacket.encodeInt(50);
        return outPacket;
    }

    public static OutPacket unk_268_1432() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_ENTER_GAME_UNK);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket unk_268_1447() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_ENTER_GAME_UNK_II);
        outPacket.encodeInt(0);
        return outPacket;
    }

    public static OutPacket loginChecking(MapleClient c) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_LOGIN_ACTION_CHECK);
        return outPacket;
    }

    public static byte[] setNameTagHide() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_EventNameTagInfo.getValue());
        mplew.writeShort(0);
        mplew.write(-1);
        mplew.writeShort(0);
        mplew.write(-1);
        mplew.writeShort(0);
        mplew.write(-1);
        mplew.writeShort(0);
        mplew.write(-1);
        mplew.writeShort(0);
        mplew.write(-1);
        return mplew.getPacket();
    }

    public static OutPacket getShowQuestCompletion(int quest_cid) {
        OutPacket say = new OutPacket(OutHeader.LP_QuestClear.getValue());
        if (getChr() != null) {
            say.encodeInt(quest_cid);
        }

        return say;
    }

    public static OutPacket LP_UNK_LOAD_I() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UNK_LOAD_I);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeShort(0);
        outPacket.encodeInt(16368);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket LP_UNK_LOAD_II(int str) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UNK_LOAD_II);
        outPacket.encodeInt(str);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket LP_UNK_LOAD_III() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UNK_LOAD_III);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket LP_UNK_LOAD_IV(int str) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_UNK_LOAD_IV);
        outPacket.encodeInt(str);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static OutPacket EquipRuneSetting() {
        OutPacket outPacket = new OutPacket(OutHeader.LP_ReincarnationStele);
        outPacket.encodeByte(11);
        outPacket.encodeInt(4);
        outPacket.encodeInt(-1208526693);
        outPacket.encodeInt(65539);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(8);
        outPacket.encodeInt(0);
        outPacket.encodeInt(589824);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(10);
        outPacket.encodeInt(720896);
        outPacket.encodeInt(0);
        outPacket.encodeInt(12);
        outPacket.encodeInt(851968);
        outPacket.encodeInt(0);
        outPacket.encodeInt(251658254);
        outPacket.encodeInt(1048576);
        outPacket.encodeInt(0);
        outPacket.encodeInt(0);
        outPacket.encodeInt(302055441);
        outPacket.encodeInt(1245184);
        outPacket.encodeInt(5121);
        outPacket.encodeInt(0);
        outPacket.encodeShort(0);
        outPacket.encodeByte(0);
        return outPacket;
    }

    public static byte[] getMacros(SkillMacro[] macros) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SKILL_MACRO.getValue());
        int count = 0;

        int i;
        for(i = 0; i < 5; ++i) {
            if (macros[i] != null) {
                ++count;
            }
        }

        mplew.write(count);

        for(i = 0; i < 5; ++i) {
            SkillMacro macro = macros[i];
            if (macro != null) {
                mplew.writeMapleAsciiString(macro.getName());
                mplew.write(macro.getShout());
                mplew.writeInt(macro.getSkill1());
                mplew.writeInt(macro.getSkill2());
                mplew.writeInt(macro.getSkill3());
            }
        }

        return mplew.getPacket();
    }
}

