//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Packet;

import Client.MapleCharacter;
import Client.MapleClient;
import Client.MapleEnumClass;
import Client.MapleExpStat;
import Client.MapleQuestStatus;
import Client.MapleQuickSlot;
import Client.MapleReward;
import Client.MapleStat;
import Client.MapleTraitType;
import Client.MapleUnion;
import Client.MapleUnionEntry;
import Client.MessageOption;
import Client.PortableChair;
import Client.SecondaryStat;
import Client.SpecialChair;
import Client.SpecialChairTW;
import Client.MapleStat.Temp;
import Client.inventory.ImpFlag;
import Client.inventory.Item;
import Client.inventory.MapleImp;
import Client.inventory.MapleInventory;
import Client.inventory.MapleInventoryType;
import Client.inventory.MaplePet;
import Client.inventory.MapleRing;
import Client.skills.ExtraSkill;
import Client.skills.InnerSkillEntry;
import Client.skills.SkillEntry;
import Client.skills.SkillMacro;
import Client.stat.DeadDebuff;
import Client.stat.MapleHyperStats;
import Config.configs.ServerConfig;
import Config.constants.GameConstants;
import Config.constants.ItemConstants;
import Config.constants.JobConstants;
import Config.constants.ServerConstants;
import Config.constants.SkillConstants;
import Config.constants.ItemConstants.類型;
import Config.constants.enums.UserChatMessageType;
import Database.DatabaseConnectionEx;
import Net.server.MapleDueyActions;
import Net.server.MapleItemInformationProvider;
import Net.server.MapleMulitInfo;
import Net.server.MerchItemPackage;
import Net.server.RankingWorker;
import Net.server.events.MapleSnowball;
import Net.server.life.MapleMonster;
import Net.server.life.MobSkill;
import Net.server.maps.ForceAtomObject;
import Net.server.maps.MapleAffectedArea;
import Net.server.maps.MapleFoothold;
import Net.server.maps.MapleMap;
import Net.server.maps.MapleNodes;
import Net.server.maps.MapleQuickMove;
import Net.server.maps.MapleRandomPortal;
import Net.server.maps.MapleReactor;
import Net.server.maps.MapleRuneStone;
import Net.server.maps.MechDoor;
import Net.server.maps.TaggedObjRegenInfo;
import Net.server.movement.LifeMovementFragment;
import Net.server.shops.HiredFisher;
import Net.server.shops.HiredMerchant;
import Net.server.shops.MaplePlayerShopItem;
import Opcode.Headler.OutHeader;
import Server.channel.ChannelServer;
import Server.channel.handler.AttackInfo;
import Server.channel.handler.AttackMobInfo;
import Server.channel.handler.TakeDamageHandler;
import Server.world.World;
import Server.world.WorldGuildService;
import Server.world.guild.MapleGuild;
import SwordieX.client.character.ExtendSP;
import SwordieX.client.character.avatar.AvatarLook;
import SwordieX.client.character.keys.FuncKeyMap;
import SwordieX.field.fieldeffect.FieldEffect;
import com.fasterxml.jackson.databind.ObjectMapper;
import connection.OutPacket;
import connection.packet.FieldPacket;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.DateUtil;
import tools.HexTool;
import tools.Pair;
import tools.Randomizer;
import tools.StringUtil;
import tools.Triple;
import tools.newCRand32;
import tools.data.MaplePacketLittleEndianWriter;
import tools.data.MaplePacketReader;

public class MaplePacketCreator {
    public static final Map<MapleStat, Long> EMPTY_STATUPDATE = Collections.emptyMap();
    private static final Logger log = LoggerFactory.getLogger(MaplePacketCreator.class);

    public MaplePacketCreator() {
    }

    public static byte[] getWzCheck(String WzCheckPack) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.WZ_CHECK.getValue());
        mplew.write(HexTool.getByteArrayFromHexString(WzCheckPack));
        return mplew.getPacket();
    }

    public static byte[] getClientAuthentication(int fileValue) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_AuthenMessage.getValue());
        mplew.writeInt(fileValue);
        return mplew.getPacket();
    }

    public static byte[] getServerIP(int port, int charId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SelectCharacterResult.getValue());
        mplew.writeInt(0);
        mplew.write(202);
        mplew.write(80);
        mplew.write(104);
        mplew.write(28);
        mplew.writeShort(port);
        mplew.writeInt(charId);
        mplew.writeInt(1);
        mplew.writeInt(1);
        mplew.writeInt(1);
        mplew.writeZeroBytes(10);
        mplew.writeInt(256020);
        mplew.writeInt(0);
        mplew.write(0);
        mplew.writeInt(4);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] DummyGamaniaNat(int port, int charId) {
        MaplePacketLittleEndianWriter DummyNat = new MaplePacketLittleEndianWriter();
        DummyNat.writeShort(OutHeader.LP_SelectCharacterResult.getValue());
        int a2 = 0;
        DummyNat.write(a2);
        DummyNat.writeMapleAsciiString("");
        DummyNat.write(0);
        switch (a2) {
            case 39:
            case 55:
            case 67:
                break;
            case 83:
                int v3 = 1;
                DummyNat.write(v3);
                if (v3 > 0) {
                    DummyNat.writeInt(0); // > 0 && <= 2
                    DummyNat.writeMapleAsciiString("normal");
                    DummyNat.writeMapleAsciiString("normal");
                    DummyNat.writeMapleAsciiString("normal");
                }
                break;
            default:
                DummyNat.write(ServerConstants.getGamaniaServerIP());
                DummyNat.writeShort(port);
                DummyNat.writeInt(charId);
                DummyNat.writeInt(1);
                DummyNat.writeInt(1);
                DummyNat.writeInt(1);
                DummyNat.writeInt(414498149); // CHANGE 267
                DummyNat.writeShort(0);
                DummyNat.writeInt(0);
                DummyNat.write(20);
                DummyNat.writeInt(1000);
                DummyNat.writeInt(775850237); // CHANGE 267
                DummyNat.writeInt(4);
                DummyNat.writeInt(1922946074);
                break;
        }
        return DummyNat.getPacket();
    }

    public static byte[] getChannelChange(MapleClient c, int port) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_MigrateCommand.getValue());
        ChannelServer toch = ChannelServer.getInstance(c.getChannel());
        mplew.write(toch.getChannel());
        mplew.write(ServerConstants.getIPBytes(ServerConstants.getIPv4Address()));
        mplew.writeShort(port);
        return mplew.getPacket();
    }

    public static byte[] sendFieldToPosition(MapleCharacter player, MapleMap to, Point position) {
        return getWarpToMap(player, to, position, 0, false, false);
    }

    public static byte[] getWarpToMap(MapleCharacter player, MapleMap to, int spawnPoint, boolean revive) {
        return getWarpToMap(player, to, (Point)null, spawnPoint, false, revive);
    }

    /**
     * Gets character info for a character.
     *
     * @param player   The character to get info about.
     * @param position
     * @return The character info packet. 42 8B 8E 0F
     */
    public static byte[] getWarpToMap(MapleCharacter player, MapleMap to, Point position, int spawnPoint, boolean load, boolean revive) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SET_FIELD.getValue());
        mplew.writeLong(PacketHelper.getTime(System.currentTimeMillis()));
        mplew.writeInt(player.getClient().getChannel() - 1);
        mplew.write(0);
        mplew.writeInt(0);
        mplew.write(load ? 1 : 2);
        mplew.writeInt(load ? 0 : to.getFieldType());
        mplew.writeInt(Math.abs(to.getBottom() - to.getTop()));
        mplew.writeInt(Math.abs(to.getRight() - to.getLeft()));
        mplew.writeBool(load);
        int nNotifierCheck = 0;
        mplew.writeShort(nNotifierCheck);
        int mapId;
        if (nNotifierCheck != 0) {
            mplew.writeMapleAsciiString("");

            for(mapId = 0; mapId < nNotifierCheck; ++mapId) {
                mplew.writeMapleAsciiString("");
            }
        }

        mapId = to.getId();
        boolean v88;
        if (load) {
            int WarpToMapCrc = Randomizer.nextInt();
            int WarpToMapCrc2 = Randomizer.nextInt();
            int WarpToMapCrc3 = Randomizer.nextInt();
            mplew.writeInt(WarpToMapCrc);
            mplew.writeInt(WarpToMapCrc2);
            mplew.writeInt(WarpToMapCrc3);
            PacketHelper.addCharacterInfo(mplew, player, -1L);
            v88 = true;
            mplew.writeBool(v88);
            if (v88) {
                v88 = false;
                mplew.writeBool(v88);
                if (v88) {
                    mplew.writeInt(0);
                }

                mplew.writeLong(PacketHelper.getTime(-2L));
                mplew.write(0);
                mplew.writeLong(PacketHelper.getTime(-2L));
                mplew.writeLong(0L);
                mplew.writeLong(0L);
            }
        } else {
            mplew.writeBool(revive);
            mplew.writeInt(mapId);
            mplew.write(spawnPoint);
            mplew.writeInt(player.getStat().getHp());
            mplew.writeInt(0);
            mplew.write(position != null);
            if (position != null) {
                mplew.writeInt(position.x);
                mplew.writeInt(position.y);
            }
        }

        boolean bUnk = false;
        mplew.write(bUnk);
        if (bUnk) {
            mplew.writeInt(0);
            mplew.writeInt(0);
        }

        mplew.write(2);
        mplew.writeBool(to.getFieldType() >= 182 && to.getFieldType() <= 184);
        mplew.writeInt(100);
        boolean bCFieldCustom = false;
        mplew.writeBool(bCFieldCustom);
        if (bCFieldCustom) {
            mplew.writeInt(0);
            mplew.writeMapleAsciiString("");
            mplew.writeInt(0);
        }

        mplew.writeBool(false);
        int buffersize_familiar = 4;
        mplew.writeInt(buffersize_familiar);
        mplew.write(new byte[buffersize_familiar]);
        mplew.writeBool(JobConstants.isSeparatedSpJob(player.getJob()));
        mplew.writeLong(0L);
        mplew.writeInt(-1);
        v88 = false;
        mplew.writeBool(v88);
        if (v88) {
            mplew.writeInt(0);
        }

        int i;
        if (mapId / 10 == 10520011 || mapId / 10 == 10520051 || mapId == 105200519) {
            String[] aS = new String[0];
            mplew.write(aS.length);
            String[] var14 = aS;
            int var15 = aS.length;

            for(i = 0; i < var15; ++i) {
                String s = var14[i];
                mplew.writeMapleAsciiString(s);
            }
        }

        mplew.writeInt(0);
        mplew.write(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        boolean setChrMenuItem = true;
        mplew.writeBool(setChrMenuItem);
        if (setChrMenuItem) {
            mplew.writeInt(-1);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(999999999);
            mplew.writeInt(999999999);
            mplew.writeMapleAsciiString("");
        }

        mplew.writeInt(0);
        mplew.writeMapleAsciiString("");
        mplew.writeMapleAsciiString("");
        boolean bSundayMaple = Calendar.getInstance().get(7) == 1;
        mplew.writeBool(bSundayMaple);
        if (bSundayMaple) {
            mplew.writeMapleAsciiString("UI/UIWindowEvent.img/sundayMaple");
            mplew.writeMapleAsciiString("#sunday# #fnNanum Gothic ExtraBold##fs20##fc0xFFFFFFFF#完成怪物公園經驗值新增#fc0xFFFFD800#50%！\\r\\n#sunday# #fs20##fc0xFFFFFFFF#烈焰戰狼消滅經驗值#fc0xFFFFD800#2倍！\\r\\n#sunday# #fnNanum Gothic ExtraBold##fs20##fc0xFFFFFFFF#大波斯菊花瓣獲得量即可獲得量#fc0xFFFFD800#2倍！");
            mplew.writeMapleAsciiString("#fn???? ExtraBold##fs15##fc0xFFB7EC00#2023年10月22日星期天");
            mplew.writeInt(60);
            mplew.writeInt(210);
        }

        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.write(1);
        mplew.writeInt(0);
        int unkSize = 0;
        mplew.writeInt(unkSize);

        for(i = 0; i < unkSize; ++i) {
            mplew.writeMapleAsciiString("");
            mplew.writeInt(0);
        }

        mplew.write(0);
        int size = 0;
        mplew.writeInt(size);

        for(i = 0; i < size; ++i) {
            mplew.writeInt(0);
        }

        return mplew.getPacket();
    }
    public static byte[] enableActions(MapleCharacter chr, boolean useTriggerForUI) {
        return updatePlayerStats(EMPTY_STATUPDATE, useTriggerForUI, chr);
    }

    public static byte[] updatePlayerStats(Map<MapleStat, Long> stats, MapleCharacter chr) {
        return updatePlayerStats(stats, false, chr);
    }

    public static byte[] updatePlayerStats(Map<MapleStat, Long> stats, boolean useTriggerForUI, MapleCharacter chr) {
        OutPacket out = new OutPacket(OutHeader.LP_StatChanged);
        out.encodeByte(useTriggerForUI ? 1 : 0);
        out.encodeByte(0);
        out.encodeByte(1);
        long encodeFlag = 0L;

        Iterator var6;
        MapleStat statupdate;
        for(var6 = stats.keySet().iterator(); var6.hasNext(); encodeFlag |= statupdate.getValue()) {
            statupdate = (MapleStat)var6.next();
        }

        out.encodeLong(encodeFlag);
        var6 = stats.entrySet().iterator();

        while(var6.hasNext()) {
            Map.Entry<MapleStat, Long> statute = (Map.Entry)var6.next();
            switch ((MapleStat)statute.getKey()) {
                case SKIN:
                    out.encodeByte(((Long)statute.getValue()).byteValue());
                    out.encodeInt(0);
                    break;
                case JOB:
                    out.encodeShort(((Long)statute.getValue()).shortValue());
                    out.encodeShort((short)chr.getSubcategory());
                    break;
                case FATIGUE:
                case STR:
                case DEX:
                case INT:
                case LUK:
                case AVAILABLE_AP:
                    out.encodeShort(((Long)statute.getValue()).shortValue());
                    break;
                case AVAILABLE_SP:
                    if (JobConstants.isSeparatedSpJob(chr.getJob())) {
                        (new ExtendSP(chr)).encode(out);
                    } else {
                        out.encodeShort(chr.getRemainingSp());
                    }
                    break;
                case TRAIT_LIMIT:
                    out.encodeZero(37);
                    break;
                case EXP:
                case MONEY:
                case TEMP_EXP:
                    out.encodeLong((Long)statute.getValue());
                    break;
                case BATTLE_POINTS:
                    out.encodeByte(5);
                    out.encodeByte(6);
                    break;
                case PET_LOCKER_SN:
                    out.encodeLong((long)((Long)statute.getValue()).intValue());
                    out.encodeLong((long)((Long)statute.getValue()).intValue());
                    out.encodeLong((long)((Long)statute.getValue()).intValue());
                    break;
                case BATTLE_RANK:
                    out.encodeInt(chr.getStat().pvpExp);
                    out.encodeByte(chr.getStat().pvpRank);
                    out.encodeInt(chr.getBattlePoints());
                    break;
                default:
                    out.encodeInt(((Long)statute.getValue()).intValue());
            }
        }

        out.encodeBoolean(encodeFlag == 0L);
        if (encodeFlag == 0L) {
            out.encodeByte(1);
        }

        boolean unk = chr.getBaseProb() > 0;
        out.encodeBoolean(unk);
        if (unk) {
            out.encodeInt(chr.getBaseColor());
            out.encodeInt(chr.getAddColor());
        }

        return out.getData();
    }

    public static byte[] updateHyperPresets(MapleCharacter chr, int pos, byte action) {
        MaplePacketLittleEndianWriter packet = new MaplePacketLittleEndianWriter();
        packet.writeShort(OutHeader.LP_HyperPreset.getValue());
        packet.write(pos);
        packet.write(action);
        if (action != 0) {
            for(int i = 0; i <= 2; ++i) {
                packet.writeInt(chr.loadHyperStats(i).size());
                Iterator var5 = chr.loadHyperStats(i).iterator();

                while(var5.hasNext()) {
                    MapleHyperStats mhsz = (MapleHyperStats)var5.next();
                    packet.writeInt(mhsz.getPosition());
                    packet.writeInt(mhsz.getSkillid());
                    packet.writeInt(mhsz.getSkillLevel());
                }
            }
        }

        return packet.getPacket();
    }

    public static byte[] instantMapWarp(byte portal) {
        return userTeleport(false, 0, portal, (Point)null);
    }

    public static byte[] sendSkillUseResult(boolean success, int skillId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SkillUseResult.getValue());
        mplew.writeBool(success);
        mplew.writeInt(skillId);
        return mplew.getPacket();
    }

    public static byte[] instantMapWarp(int charId, Point pos) {
        return userTeleport(false, 2, charId, pos);
    }

    public static byte[] userTeleportOnRevive(int charid, Point point) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UserTeleportOnRevive.getValue());
        mplew.writeInt(charid);
        mplew.writePosInt(point.getLocation());
        return mplew.getPacket();
    }

    public static byte[] userTeleport(boolean b, int n, int charid, Point point) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserTeleport.getValue());
        mplew.writeBool(b);
        mplew.write(n);
        mplew.writeInt(charid);
        mplew.writeShort((int)point.getX());
        mplew.writeShort((int)point.getY());
        return mplew.getPacket();
    }

    public static byte[] onTownPortal(int townId, int targetId, int skillId, Point pos) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_TownPortal.getValue());
        mplew.writeInt(townId);
        mplew.writeInt(targetId);
        if (townId != 999999999 && targetId != 999999999) {
            mplew.writeInt(skillId);
            mplew.writePos(pos);
        }

        return mplew.getPacket();
    }

    public static byte[] getRandomPortalCreated(MapleRandomPortal portal) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RandomPortalCreated.getValue());
        mplew.write(portal.getAppearType().ordinal());
        mplew.writeInt(portal.getObjectId());
        mplew.writePos(portal.getPosition());
        mplew.writeInt(portal.getMapid());
        mplew.writeInt(portal.getOwerid());
        return mplew.getPacket();
    }

    public static byte[] getRandomPortalTryEnterRequest() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RandomPortalTryEnterRequest.getValue());
        return mplew.getPacket();
    }

    public static byte[] getRandomPortalRemoved(MapleRandomPortal portal) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RandomPortalRemoved.getValue());
        mplew.write(portal.getAppearType().ordinal());
        mplew.writeInt(portal.getObjectId());
        mplew.writeInt(portal.getMapid());
        return mplew.getPacket();
    }

    public static byte[] getFieldVoice(String patch) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_PlaySound.getValue());
        mplew.writeMapleAsciiString(patch);
        return mplew.getPacket();
    }

    public static byte[] resetScreen() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.RESET_SCREEN.getValue());
        return mplew.getPacket();
    }

    public static byte[] mapBlocked(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.MAP_BLOCKED.getValue());
        mplew.write(type);
        return mplew.getPacket();
    }

    public static byte[] serverBlocked(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SERVER_BLOCKED.getValue());
        mplew.write(type);
        return mplew.getPacket();
    }

    public static byte[] partyBlocked(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.PARTY_BLOCKED.getValue());
        mplew.write(type);
        return mplew.getPacket();
    }

    public static byte[] serverMessage(String message) {
        return serverMessage(4, 0, message, false, (Item)null, Collections.emptyList(), 0);
    }

    public static byte[] serverNotice(int type, String message) {
        return serverMessage(type, 0, message, false, (Item)null, Collections.emptyList(), 0);
    }

    public static byte[] serverNotice(int type, int channel, String message) {
        return serverMessage(type, channel, message, false, (Item)null, Collections.emptyList(), 0);
    }

    public static byte[] serverNotice(int type, int channel, String message, boolean smegaEar) {
        return serverMessage(type, channel, message, smegaEar, (Item)null, Collections.emptyList(), 0);
    }

    private static byte[] serverMessage(int type, int channel, String message, boolean megaEar, Item item, List<String> list, int rareness) {
        return serverMessage(type, channel, message, megaEar, item, list, rareness, "", 0);
    }

    private static byte[] serverMessage(int type, int channel, String message, boolean megaEar, Item item, List<String> list, int rareness, String speakerName, int speakerId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_BroadcastMsg.getValue());
        String speekerName = "";
        int chrId = 0;
        mplew.write(type);
        boolean msg = type != 13 && type != 14 && type != 27;
        if (type == 4 || type == 26) {
            msg = true;
            mplew.writeBool(msg);
        }

        if (type == 35) {
            msg = false;
        }

        if (msg) {
            mplew.writeMapleAsciiString(message);
        }

        switch (type) {
            case 3:
            case 23:
                PacketHelper.addChaterName(mplew, speekerName, message, chrId);
            case 28:
            case 29:
            case 30:
            case 31:
                mplew.write(channel - 1);
                mplew.writeBool(megaEar);
            case 4:
            case 5:
            case 6:
            case 7:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 25:
            case 27:
            case 32:
            default:
                break;
            case 8:
                PacketHelper.addChaterName(mplew, speekerName, message, chrId);
                mplew.write(channel - 1);
                mplew.writeBool(megaEar);
                mplew.writeInt(item == null ? 0 : item.getItemId());
                boolean achievement = false;
                mplew.writeInt(item != null ? 1 : (achievement ? 2 : 0));
                if (item == null && achievement) {
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    mplew.writeDouble(0.0);
                } else if (item != null) {
                    mplew.write(1);
                    PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                    mplew.writeMapleAsciiString(item.getName());
                }
                break;
            case 9:
                PacketHelper.addChaterName(mplew, speekerName, message, chrId);
                mplew.write(channel - 1);
                break;
            case 10:
                PacketHelper.addChaterName(mplew, speekerName, (String)list.get(1));
                mplew.write(list.size());
                if (list.size() > 1) {
                    mplew.writeMapleAsciiString((String)list.get(1));
                    PacketHelper.addChaterName(mplew, speekerName, (String)list.get(1));
                }

                if (list.size() > 2) {
                    mplew.writeMapleAsciiString((String)list.get(2));
                    PacketHelper.addChaterName(mplew, speekerName, (String)list.get(2));
                }

                mplew.write(channel - 1);
                mplew.writeBool(megaEar);
                break;
            case 12:
                mplew.writeInt(1);
                break;
            case 17:
                PacketHelper.addItemPosition(mplew, item, true, false);
                if (item != null) {
                    PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                }
                break;
            case 21:
            case 22:
                PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                break;
            case 24:
                mplew.writeInt(item != null ? item.getItemId() : 0);
                mplew.writeInt(30);
                PacketHelper.addItemPosition(mplew, item, true, false);
                if (item != null) {
                    PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                }
                break;
            case 26:
                mplew.writeInt(0);
                mplew.writeInt(0);
                break;
            case 33:
            case 34:
                mplew.writeInt(0);
                mplew.writeMapleAsciiString(list != null && !list.isEmpty() ? (String)list.get(0) : "");
                PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
        }

        switch (type) {
            case 2:
                PacketHelper.addChaterName(mplew, speekerName, message, chrId);
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 12:
            case 15:
            case 19:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 35:
            default:
                break;
            case 6:
            case 18:
                mplew.writeInt(item != null ? item.getItemId() : 0);
                break;
            case 7:
                mplew.writeInt(1);
                break;
            case 11:
                mplew.writeInt(item == null ? 0 : item.getItemId());
                PacketHelper.addItemPosition(mplew, item, true, false);
                if (item != null) {
                    PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                }
                break;
            case 13:
                mplew.writeInt(0);
                mplew.writeInt(0);
                break;
            case 14:
                mplew.writeInt(0);
                break;
            case 16:
                mplew.writeInt(channel - 1);
                break;
            case 17:
                mplew.writeBool(item != null);
                if (item != null) {
                    PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                }
                break;
            case 20:
                mplew.writeInt(1);
                mplew.writeInt(1);
                break;
            case 21:
            case 22:
                PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                break;
            case 33:
            case 34:
                mplew.writeMapleAsciiString("");
                mplew.writeInt(0);
                break;
            case 36:
                mplew.writeInt(item != null ? item.getItemId() : 0);
                mplew.writeInt(channel > 0 ? channel - 1 : -1);
                mplew.writeInt(rareness);
                PacketHelper.addItemPosition(mplew, item, true, false);
                if (item != null) {
                    PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
                }
        }

        mplew.writeZeroBytes(10);
        return mplew.getPacket();
    }

    public static byte[] getGachaponMega(String name, String message, Item item, int rareness, int channel) {
        List<String> messages = new LinkedList();
        messages.add(name);
        return serverMessage(36, channel, message, false, item, messages, rareness);
    }

    public static byte[] tripleSmega(List<String> message, boolean ear, int channel) {
        String s = (String)message.get(0);
        return serverMessage(10, channel, s == null ? "" : s, ear, (Item)null, message, 0);
    }

    public static byte[] getAvatarMega(MapleCharacter chr, int channel, int itemId, List<String> message, boolean ear) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_AvatarMegaphoneUpdateMessage.getValue());
        mplew.writeInt(itemId);
        String var10001 = chr.getMedalText();
        mplew.writeMapleAsciiString(var10001 + chr.getName());

        for(int i = 0; i < 4; ++i) {
            mplew.writeMapleAsciiString((String)message.get(i));
        }

        StringBuilder sb = new StringBuilder();
        Iterator var7 = message.iterator();

        while(var7.hasNext()) {
            String ignored = (String)var7.next();
            sb.append(sb).append("\n\r");
        }

        PacketHelper.addChaterName(mplew, chr.getName(), sb.toString());
        mplew.writeInt(channel - 1);
        mplew.write(ear ? 1 : 0);
        chr.getAvatarLook().encode(mplew, true);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] gachaponMsg(String msg, Item item) {
        return serverMessage(11, 0, msg, false, item, (List)null, 0);
    }

    public static byte[] itemMegaphone(String msg, boolean whisper, int channel, Item item) {
        return serverMessage(8, channel, msg, whisper, item, (List)null, 0);
    }

    public static byte[] getChatItemText(int speekerId, String text, String speekerName, boolean whiteBG, int show, boolean b, int n, Item item) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserChatItem.getValue());
        mplew.writeInt(speekerId);
        mplew.writeBool(false);
        mplew.writeMapleAsciiString(text);
        PacketHelper.addChaterName(mplew, speekerName, text, speekerId);
        mplew.write(show);
        mplew.writeBool(b);
        mplew.write(n);
        boolean achievement = false;
        mplew.writeInt(item != null ? 1 : (achievement ? 2 : 0));
        if (item == null && achievement) {
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeDouble(0.0);
        } else if (item != null) {
            mplew.write(1);
            PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
            mplew.writeMapleAsciiString(item.getName());
        }

        return mplew.getPacket();
    }

    public static byte[] getChatText(int speekerId, String text, String speekerName, boolean whiteBG, int show, boolean b, int n) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserChat.getValue());
        MapleCharacter talkPlayer = MapleCharacter.getCharacterById(speekerId);
        mplew.writeInt(speekerId);
        mplew.writeBool(whiteBG);
        mplew.writeMapleAsciiString(text);
        mplew.writeMapleAsciiString(speekerName);
        mplew.writeMapleAsciiString(text);
        mplew.writeShort(-4700);
        mplew.writeInt(n);
        mplew.writeShort(0);
        mplew.write(0);
        mplew.writeInt(speekerId);

        for(int x = 0; x < 4; ++x) {
            mplew.writeInt(0);
        }

        mplew.write(show);
        mplew.write(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] GameMaster_Func(int value) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_AdminResult.getValue());
        mplew.write(value);
        mplew.writeZeroBytes(17);
        return mplew.getPacket();
    }

    public static byte[] ShowAranCombo(int combo) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ModCombo.getValue());
        mplew.writeInt(combo);
        return mplew.getPacket();
    }

    public static byte[] comboRecharge(int combo) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_IncComboByComboRecharge.getValue());
        mplew.writeInt(combo);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] rechargeCombo(int value) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserBuffzoneEffect.getValue());
        mplew.writeInt(value);
        return mplew.getPacket();
    }

    public static byte[] getPacketFromHexString(String hex) {
        return HexTool.getByteArrayFromHexString(hex);
    }

    public static byte[] showGainExp(long gain, boolean white, boolean bOnQuest, int diseaseType, long expLost, Map<MapleExpStat, Object> expStats) {
        MessageOption option = new MessageOption();
        option.setColor(white ? 1 : 0);
        option.setLongGain(gain);
        option.setOnQuest(bOnQuest);
        option.setDiseaseType(diseaseType);
        option.setExpLost(expLost);
        option.setExpGainData(expStats);
        return CWvsContext.sendMessage(3, option);
    }

    public static byte[] getShowFameGain(int gain) {
        MessageOption option = new MessageOption();
        option.setAmount(gain);
        return CWvsContext.sendMessage(5, option);
    }

    public static byte[] showMesoGain(long gain, boolean inChat) {
        MessageOption option;
        if (!inChat) {
            gain = Math.min(2147483647L, gain);
            option = new MessageOption();
            option.setMode(1);
            option.setLongGain(gain);
            return CWvsContext.sendMessage(0, option);
        } else {
            option = new MessageOption();
            option.setLongGain(gain);
            option.setMode(-1);
            option.setText("");
            return CWvsContext.sendMessage(6, option);
        }
    }

    public static byte[] getShowItemGain(int itemId, int quantity) {
        return getShowItemGain(itemId, quantity, false);
    }

    public static byte[] getShowItemGain(int itemId, int quantity, boolean inChat) {
        if (inChat) {
            return EffectPacket.getShowItemGain(Collections.singletonList(new Pair(itemId, quantity)));
        } else {
            MessageOption option = new MessageOption();
            option.setMode(0);
            option.setObjectId(itemId);
            option.setAmount(quantity);
            return CWvsContext.sendMessage(0, option);
        }
    }

    public static byte[] showItemExpired(int itemId) {
        MessageOption option = new MessageOption();
        option.setIntegerData(new int[]{itemId});
        return CWvsContext.sendMessage(10, option);
    }

    public static byte[] showSkillExpired(Map<Integer, SkillEntry> update) {
        MessageOption option = new MessageOption();
        option.setIntegerData(update.keySet().stream().mapToInt(Integer::intValue).toArray());
        return CWvsContext.sendMessage(18, option);
    }

    public static byte[] showCashItemExpired(int itemId) {
        MessageOption option = new MessageOption();
        option.setObjectId(itemId);
        return CWvsContext.sendMessage(2, option);
    }

    public static byte[] spawnPlayerMapobject(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserEnterField.getValue());
        mplew.writeInt(chr.getAccountID());
        mplew.writeInt(chr.getId());
        mplew.writeInt(0);
        mplew.writeInt(chr.getLevel());
        mplew.writeMapleAsciiString(chr.getName());
        MapleGuild gs;
        if (chr.getGuildId() > 0) {
            gs = WorldGuildService.getInstance().getGuild(chr.getGuildId());
        } else {
            gs = null;
        }

        mplew.writeShort(0);
        mplew.writeInt(gs == null ? 0 : gs.getId());
        mplew.writeMapleAsciiString(gs == null ? "" : gs.getName());
        mplew.writeShort(gs == null ? 0 : gs.getLogoBG());
        mplew.write(gs == null ? 0 : gs.getLogoBGColor());
        mplew.writeShort(gs == null ? 0 : gs.getLogo());
        mplew.writeInt(gs != null && gs.getImageLogo() != null ? gs.getId() : 0);
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        mplew.write(chr.getGender());
        mplew.writeInt(chr.getFame());
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.write(0);
        mplew.writeHexString("8C 16 05 00 00 00 F4 00 00 00 00 00 00 00 00 00 20 00 00 00 00 00 00 00 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 08 0D 00 00 40 00 00 00 80 00 00 80 00 00 00 00 00 00 10 00 11 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 F0 3F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 01 C1 D8 E6 12 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 01 95 7B 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 64");
        mplew.writeShort(chr.getJob());
        mplew.writeShort(chr.getJobWithSub());
        mplew.writeInt(chr.getStat().getStarForce());
        mplew.writeInt(chr.getStat().getArc());
        mplew.writeInt(chr.getStat().getAut());
        chr.getAvatarLook().encode(mplew, true);
        if (JobConstants.is神之子(chr.getJob())) {
            chr.getSecondAvatarLook().encode(mplew, true);
        }

        mplew.writeInt(0);
        mplew.write(-1);
        mplew.writeInt(0);
        mplew.write(-1);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.write(0);
        int buffSrc = chr.getBuffSource(SecondaryStat.RideVehicle);
        if (chr.getBuffedValue(SecondaryStat.NewFlying) != null && buffSrc > 0) {
            addMountId(mplew, chr, buffSrc);
            mplew.writeInt(chr.getId());
        } else {
            mplew.writeInt(0);
            mplew.writeInt(0);
        }

        mplew.writeInt(0);
        mplew.writeInt(Math.min(250, chr.getInventory(MapleInventoryType.CASH).countById(5110000)));
        mplew.writeInt(chr.getItemEffect());
        mplew.writeInt(chr.getItemEffectType());
        mplew.writeInt(chr.getActiveNickItemID());
        String sUnk = null;
        mplew.writeBool(sUnk != null);
        if (sUnk != null) {
            mplew.writeMapleAsciiString((String)sUnk);
        }

        mplew.writeInt(0);
        mplew.writeMapleAsciiString("");
        mplew.writeMapleAsciiString("");
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.write(1);
        PortableChair chair = chr.getChair();
        mplew.write(chair == null ? 0 : chair.getUnk());
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeShort(-1);
        mplew.writeInt(chair == null ? 0 : chair.getItemId());
        mplew.writeInt(0);
        mplew.writePos(chr.getPosition());
        mplew.write(chr.getStance());
        mplew.writeShort(chr.getCurrentFH());
        mplew.write(1);
        mplew.write(1);
        writeChairData(mplew, chr);

        for(int i = 0; i <= 4; ++i) {
            MaplePet pet = chr.getSpawnPet(i);
            boolean isPetSpawned = pet != null && i != 4;
            mplew.write(isPetSpawned);
            if (!isPetSpawned) {
                break;
            }

            mplew.writeInt(chr.getPetIndex(pet));
            PetPacket.addPetInfo(mplew, pet);
        }

        mplew.writeBool(false);
        mplew.writeInt(chr.getMount() != null ? chr.getMount().getLevel() : 1);
        mplew.writeInt(chr.getMount() != null ? chr.getMount().getExp() : 0);
        mplew.writeInt(chr.getMount() != null ? chr.getMount().getFatigue() : 0);
        mplew.writeBool(false);
        PacketHelper.addAnnounceBox(mplew, chr);
        mplew.writeBool(chr.getChalkboard() != null && chr.getChalkboard().length() > 0);
        if (chr.getChalkboard() != null && chr.getChalkboard().length() > 0) {
            mplew.writeMapleAsciiString(chr.getChalkboard());
        }

        Triple<List<MapleRing>, List<MapleRing>, List<MapleRing>> rings = chr.getRings(true);
        addRingInfo(mplew, (List)rings.getLeft());
        addRingInfo(mplew, (List)rings.getMid());
        addMRingInfo(mplew, (List)rings.getRight(), chr);
        boolean loadObjSwords = true;
        mplew.writeBool(loadObjSwords);
        if (loadObjSwords) {
            Map<Integer, ForceAtomObject> map = chr.getForceAtomObjects();
            mplew.writeInt(map.size());
            Iterator var9 = map.values().iterator();

            while(var9.hasNext()) {
                ForceAtomObject sword = (ForceAtomObject)var9.next();
                AdelePacket.encodeForceAtomObject(mplew, sword);
            }
        }

        int berserk = 0;
        mplew.write(berserk);
        if ((berserk & 8) != 0) {
            mplew.writeInt(0);
        }

        if ((berserk & 16) != 0) {
            mplew.writeInt(0);
        }

        if ((berserk & 32) != 0) {
            mplew.writeInt(0);
        }

        mplew.writeInt(chr.getMount().getItemId());
        if (JobConstants.is凱撒(chr.getJob())) {
            String string2 = chr.getOneInfo(12860, "extern");
            mplew.writeInt(string2 == null ? 0 : Integer.parseInt(string2));
            string2 = chr.getOneInfo(12860, "inner");
            mplew.writeInt(string2 == null ? 0 : Integer.parseInt(string2));
            string2 = chr.getOneInfo(12860, "premium");
            mplew.write(string2 == null ? 0 : Integer.parseInt(string2));
        }

        mplew.writeInt(chr.getMeisterSkillEff());
        mplew.writeHexString("FF FF FF FF FF");
        mplew.writeInt(0);
        mplew.write(1);
        mplew.writeInt(464907);
        mplew.write(0);
        mplew.write(1);
        mplew.writeBool(false);
        mplew.writeInt(0);
        mplew.write(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeBool(JobConstants.is凱內西斯(chr.getJob()) && chr.getBuffedIntValue(SecondaryStat.KinesisPsychicEnergeShield) > 0);
        mplew.write(0);
        mplew.write(0);
        mplew.writeInt(1051291);
        mplew.writeBool(false);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(chr.getSkillSkin().size());
        chr.send(InventoryPacket.showDamageSkin(chr.getId(), chr.getDamageSkin()));
        mplew.writeInt(0);
        mplew.writeLong(0L);
        mplew.writeShort(0);
        mplew.writeInt(-1);
        return mplew.getPacket();
    }

    public static void addMountId(MaplePacketLittleEndianWriter mplew, MapleCharacter chr, int buffSrc) {
        Item c_mount = chr.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-123);
        Item mount = chr.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-18);
        int mountId = GameConstants.getMountItem(buffSrc, chr);
        if (mountId == 0 && c_mount != null && chr.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-124) != null) {
            mplew.writeInt(c_mount.getItemId());
        } else if (mountId == 0 && mount != null && chr.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-19) != null) {
            mplew.writeInt(mount.getItemId());
        } else {
            mplew.writeInt(mountId);
        }

    }

    public static byte[] removePlayerFromMap(int chrId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserLeaveField.getValue());
        mplew.writeInt(chrId);
        return mplew.getPacket();
    }

    public static byte[] facialExpression(MapleCharacter from, int expression) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserEmotion.getValue());
        mplew.writeInt(from.getId());
        mplew.writeInt(expression);
        mplew.writeInt(-1);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] movePlayer(int chrId, List<LifeMovementFragment> moves, int gatherDuration, int nVal1, Point mPos, Point oPos) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserMove.getValue());
        mplew.writeInt(chrId);
        PacketHelper.serializeMovementList(mplew, gatherDuration, nVal1, mPos, oPos, moves, (int[])null);
        return mplew.getPacket();
    }

    public static byte[] UserMeleeAttack(MapleCharacter chr, int skilllevel, int itemId, AttackInfo attackInfo, boolean hasMoonBuff) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserMeleeAttack.getValue());
        addAttackBody(mplew, chr, skilllevel, itemId, attackInfo, hasMoonBuff, false);
        mplew.writeZeroBytes(20);
        return mplew.getPacket();
    }

    public static byte[] UserBodyAttack(MapleCharacter chr, int skilllevel, int itemId, AttackInfo attackInfo, boolean hasMoonBuff) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserBodyAttack.getValue());
        addAttackBody(mplew, chr, skilllevel, itemId, attackInfo, hasMoonBuff, false);
        mplew.writeZeroBytes(20);
        return mplew.getPacket();
    }

    public static byte[] UserShootAttack(MapleCharacter chr, int skilllevel, int itemId, AttackInfo attackInfo) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserShootAttack.getValue());
        addAttackBody(mplew, chr, skilllevel, itemId, attackInfo, false, true);
        if (JobConstants.is神之子(chr.getJob()) && attackInfo.skillId >= 100000000) {
            mplew.writeInt(attackInfo.position.x);
            mplew.writeInt(attackInfo.position.y);
        } else if (attackInfo.skillposition != null) {
            if (attackInfo.skillId == 13121052) {
                mplew.writeLong(0L);
            } else {
                mplew.writePos(attackInfo.skillposition);
            }
        }

        mplew.writeZeroBytes(20);
        return mplew.getPacket();
    }

    public static byte[] UserMagicAttack(MapleCharacter chr, int skilllevel, int itemId, AttackInfo attackInfo) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserMagicAttack.getValue());
        addAttackBody(mplew, chr, skilllevel, itemId, attackInfo, false, false);
        mplew.writeZeroBytes(18);
        return mplew.getPacket();
    }

    public static void addAttackBody(MaplePacketLittleEndianWriter mplew, MapleCharacter chr, int skilllevel, int itemId, AttackInfo ai, boolean hasMoonBuff, boolean isShootAttack) {
        int skillId = ai.skillId;
        mplew.writeInt(chr.getId());
        mplew.writeBool(isShootAttack);
        mplew.write(ai.numAttackedAndDamage);
        mplew.writeInt(chr.getLevel());
        mplew.writeInt(skilllevel > 0 && skillId > 0 ? skilllevel : 0);
        if (skilllevel > 0 && skillId > 0) {
            mplew.writeInt(skillId);
        }

        if (JobConstants.is神之子(chr.getJob()) && skillId >= 100000000) {
            mplew.write(0);
        }

        if (isShootAttack && (skillId == 4121013 || skillId == 5321012 || eA(skillId) > 0)) {
            mplew.writeInt(0);
        }

        if (skillId == 80001850) {
            mplew.writeInt(0);
        } else if (SkillConstants.getLinkedAttackSkill(skillId) == 42001000) {
            mplew.write(0);
        }

        mplew.write(isShootAttack ? 8 : ny(skillId));
        int mask = 0;
        int l = 0;
        int r = 0;
        if (hasMoonBuff) {
            mask = 2;
            l = 11001024;
            r = 20;
        }

        mplew.write(mask);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.write(false);
        if ((mask & 2) != 0) {
            mplew.writeInt(l);
            mplew.writeInt(r);
        }

        if ((mask & 8) != 0) {
            mplew.write(0);
        }

        mplew.write(ai.display);
        mplew.write(ai.direction);
        mplew.write(-1);
        mplew.writeShort(0);
        mplew.writeShort(0);
        mplew.write(0);
        mplew.write(0);
        mplew.write(ai.attackSpeed);
        mplew.write(0);
        mplew.writeInt(itemId);
        Iterator var11 = ai.mobAttackInfo.iterator();

        while(true) {
            AttackMobInfo oned;
            do {
                if (!var11.hasNext()) {
                    if (skillId != 2221052 && skillId != 11121054 && skillId != 12121054 && skillId != 80003075) {
                        if (skillId != 4221052 && skillId != 65121052 && skillId != 80001431 && skillId != 80003084 && skillId != 80011562 && skillId != 100001283 && skillId != 21121057 && skillId != 13121052 && skillId != 14121052 && skillId != 15121052 && skillId != 101000102 && skillId != 101000202 && skillId != 80011561 && skillId != 80002463 && skillId != 80001762 && skillId != 80002212 && skillId != 400041019 && skillId != 400031016 && skillId != 3221019 && skillId != 152110004 && skillId != 152120016 && skillId != 155121003 && skillId != 400021075 && skillId != 400001055 && skillId != 400001056 && skillId != 80002452 && skillId != 400011131 && skillId != 400011132 && skillId != 400021097 && skillId != 400041062 && skillId != 400041079 && skillId != 400051080 && skillId != 400011125 && skillId != 400011126 && skillId != 155121007 && skillId != 80003017 && skillId != 400051065 && skillId != 400051066 && skillId != 63111005 && skillId != 63111105 && skillId != 63111106 && skillId != 162101009 && skillId != 162121017 && skillId != 1221020 && skillId != 5311014 && skillId != 5311015) {
                            if (skillId == 400021107) {
                                mplew.writeInt(0);
                                mplew.writeRect(new Rectangle());
                            } else if (skillId != 13111020 && skillId != 112111016) {
                                if (skillId == 51121009) {
                                    mplew.write(0);
                                } else if (skillId == 112110003) {
                                    mplew.writeInt(0);
                                } else if (skillId == 42100007) {
                                    mplew.writeShort(0);
                                    mplew.write(0);
                                } else if (skillId == 21121029 || skillId == 37121052 || skillId == 400041002 || skillId == 400041003 || skillId == 400041004 || skillId == 400041005 || skillId == 11121014 || skillId == 5121007) {
                                    mplew.write(1);
                                    mplew.writePosInt(ai.skillposition == null ? ai.position : ai.skillposition);
                                }
                            } else {
                                mplew.writeShort(0);
                                mplew.writeShort(0);
                            }
                        } else {
                            mplew.writePosInt(chr.getPosition());
                        }
                    } else {
                        mplew.writeInt(ai.charge);
                    }

                    if (skillId == 400021088) {
                        mplew.writeRect(new Rectangle());
                    }

                    if (sub_8748E0(skillId)) {
                        mplew.writeShort(0);
                        mplew.writeShort(0);
                    }

                    if (sub_874950(skillId)) {
                        mplew.writeInt(0);
                        mplew.write(0);
                    }

                    if (sub_874720(skillId)) {
                        mplew.writeInt(0);
                        mplew.write(0);
                    }

                    if (skillId == 155101104 || skillId == 155101204 || skillId == 400051042 || skillId == 41121022) {
                        mplew.write(0);
                    }

                    if (skillId == 42100007) {
                        mplew.writeShort(0);
                        mplew.write(0);
                    }

                    if (skillId == 3301008) {
                        mplew.writeInt(0);
                        mplew.write(0);
                    }

                    return;
                }

                oned = (AttackMobInfo)var11.next();
            } while(oned.damages == null);

            mplew.writeInt(oned.mobId);
            mplew.write(oned.hitAction);
            mplew.write(oned.left);
            mplew.write(oned.idk3);
            mplew.write(oned.forceActionAndLeft);
            mplew.write(oned.frameIdx);
            mplew.writeInt(0);
            mplew.writeInt(0);
            if (skillId == 142111002 || skillId == 80011050) {
                mplew.write(oned.damages.length);
            }

            long[] var13 = oned.damages;
            int var14 = var13.length;

            for(int var15 = 0; var15 < var14; ++var15) {
                long damage = var13[var15];
                mplew.writeLong(damage);
            }

            if (sub_870CC0(skillId)) {
                mplew.writeInt(0);
            }

            if (skillId == 37111005 || skillId == 175001003) {
                mplew.write(0);
            }

            if (skillId == 164001002) {
                mplew.writeInt(0);
            }
        }
    }

    private static boolean sub_870CC0(int a1) {
        if (a1 > 142111002) {
            return a1 >= 142120000 && (a1 <= 142120002 || a1 == 142120014);
        } else {
            return a1 == 142111002 || a1 == 142100010 || a1 == 142110003 || a1 == 142110015;
        }
    }

    private static boolean sub_874950(int a1) {
        boolean v1;
        if (a1 > 3321005) {
            if (a1 == 3321039) {
                return true;
            }

            v1 = a1 == 400031035;
        } else {
            if (a1 == 3321005 || a1 == 3301004 || a1 == 3311011) {
                return true;
            }

            v1 = a1 == 3311013;
        }

        return v1;
    }

    private static boolean sub_874720(int a1) {
        boolean v1;
        if (a1 > 400021065) {
            if (a1 > 400041034) {
                if (a1 == 400051003 || a1 == 400051008) {
                    return true;
                }

                v1 = a1 - 400051008 == 8;
            } else {
                if (a1 == 400041034) {
                    return true;
                }

                if (a1 > 400041018) {
                    v1 = a1 == 400041020;
                } else {
                    if (a1 >= 400041016 || a1 == 400021078) {
                        return true;
                    }

                    v1 = a1 == 400021080;
                }
            }

            return v1;
        } else {
            if (a1 < 400021064) {
                if (a1 > 400011004) {
                    switch (a1) {
                        case 400021004:
                        case 400021009:
                        case 400021010:
                        case 400021011:
                        case 400021028:
                        case 400021047:
                        case 400021048:
                            return true;
                        default:
                            return false;
                    }
                }

                if (a1 != 400011004) {
                    if (a1 > 152120003) {
                        v1 = a1 == 152121004;
                    } else {
                        if (a1 == 152120003 || a1 == 80002691) {
                            return true;
                        }

                        v1 = a1 == 152001002;
                    }

                    return v1;
                }
            }

            return true;
        }
    }

    private static boolean sub_8748E0(int a1) {
        boolean v1;
        if (a1 <= 64111012) {
            if (a1 == 64111012) {
                return true;
            } else {
                if (a1 > 3311013) {
                    if (a1 == 3321005) {
                        return true;
                    }

                    v1 = a1 == 3321039;
                } else {
                    if (a1 == 3311013 || a1 == 3301004) {
                        return true;
                    }

                    v1 = a1 == 3311011;
                }

                return v1;
            }
        } else if (a1 > 400021053) {
            v1 = a1 == 400031035;
            return v1;
        } else {
            if (a1 != 400021053) {
                if (a1 < 400020009) {
                    return false;
                }

                if (a1 > 400020011) {
                    v1 = a1 == 400021029;
                    return v1;
                }
            }

            return true;
        }
    }

    public static int eA(int n) {
        switch (n) {
            case 1120017:
                return 1120051;
            case 1121008:
                return 1120017;
            case 1211018:
            case 1221021:
                return 1220058;
            case 1221009:
                return 1220048;
            case 1221011:
                return 1220050;
            case 2121006:
                return 2120048;
            case 2221006:
                return 2220048;
            case 3011004:
            case 3300002:
            case 3301003:
            case 3301004:
            case 3310001:
            case 3311002:
            case 3311003:
            case 3311013:
            case 3321004:
            case 3321005:
            case 3321006:
            case 3321007:
                return 3320030;
            case 3121015:
                return 3120048;
            case 3121020:
                return 3120051;
            case 3201011:
            case 3211017:
            case 3220020:
            case 3221019:
            case 3221023:
            case 3221024:
            case 3221027:
                return 3220048;
            case 3221007:
                return 3220051;
            case 4221017:
                return 4220048;
            case 4331011:
                return 4340045;
            case 4341009:
                return 4340048;
            case 5121007:
                return 5120048;
            case 5121016:
                return 5120051;
            case 5221016:
                return 5220047;
            case 5320011:
            case 5321004:
                return 5320043;
            case 5321000:
                return 5320048;
            case 11121014:
                return 11120050;
            case 12000026:
            case 12100028:
            case 12110028:
            case 12120010:
                return 12120045;
            case 12120011:
                return 12120046;
            case 14121002:
                return 14120045;
            case 15111022:
            case 15120003:
                return 15120045;
            case 15121002:
                return 15120048;
            case 21120006:
                return 21120049;
            case 22140023:
                return 22170086;
            case 25121005:
            case 400051043:
                return 25120148;
            case 31121001:
                return 31120050;
            case 35121016:
                return 35120051;
            case 37110002:
            case 37120001:
                return 37120045;
            case 41121001:
                return 41120044;
            case 41121002:
                return 41120050;
            case 41121018:
            case 41121021:
                return 41120048;
            case 42121000:
                return 42120043;
            case 51101005:
                return 51120051;
            case 51120057:
            case 51121009:
                return 51120058;
            case 51121008:
                return 51120048;
            case 61121100:
            case 61121201:
                return 61120045;
            case 65121007:
            case 65121008:
            case 65121101:
                return 65120051;
            case 152001001:
            case 152110004:
            case 152120001:
                return 152120032;
            case 152121004:
                return 152120035;
            case 152121005:
            case 152121006:
                return 152120038;
            default:
                return 0;
        }
    }

    public static int ny(int n2) {
        switch (n2) {
            case 2121054:
            case 31121005:
            case 42121054:
            case 65121052:
                return 4;
            default:
                return 0;
        }
    }

    public static byte[] showSpecialAttack(int chrId, int tickCount, int pot_x, int pot_y, int display, int skillId, int skilllevel, boolean isLeft, int speed) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ThrowGrenade.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(tickCount);
        mplew.writeInt(pot_x);
        mplew.writeInt(pot_y);
        mplew.writeInt(display);
        mplew.writeInt(skillId);
        mplew.writeInt(0);
        mplew.writeInt(skilllevel);
        mplew.write(isLeft ? 1 : 0);
        mplew.writeInt(speed);
        mplew.writeInt(0);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] updateCharLook(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserAvatarModified.getValue());
        mplew.writeInt(chr.getId());
        mplew.write(1);
        chr.getAvatarLook().encode(mplew, false);
        mplew.writeInt(0);
        mplew.write(255);
        mplew.writeInt(0);
        mplew.write(255);
        Triple<List<MapleRing>, List<MapleRing>, List<MapleRing>> rings = chr.getRings(false);
        addRingInfo(mplew, (List)rings.getLeft());
        addRingInfo(mplew, (List)rings.getMid());
        addMRingInfo(mplew, (List)rings.getRight(), chr);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] updateZeroLook(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ZeroTag.getValue());
        mplew.writeInt(chr.getId());
        chr.getAvatarLook().encode(mplew, false);
        mplew.writeHexString("00 00 00 00 FF 00 00 00 00 FF");
        return mplew.getPacket();
    }

    public static byte[] removeZeroFromMap(int chrId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ZeroLastAssistState.getValue());
        mplew.writeInt(chrId);
        return mplew.getPacket();
    }

    public static void addRingInfo(MaplePacketLittleEndianWriter mplew, List<MapleRing> rings) {
        mplew.writeBool(!rings.isEmpty());
        if (rings.size() > 0) {
            mplew.writeInt(rings.size());
            Iterator var2 = rings.iterator();

            while(var2.hasNext()) {
                MapleRing ring = (MapleRing)var2.next();
                mplew.writeLong((long)ring.getRingId());
                mplew.writeLong((long)ring.getPartnerRingId());
                mplew.writeInt(ring.getItemId());
            }
        }

    }

    public static void addMRingInfo(MaplePacketLittleEndianWriter mplew, List<MapleRing> rings, MapleCharacter chr) {
        mplew.write(rings.size() > 0);
        if (rings.size() > 0) {
            MapleRing ring = (MapleRing)rings.get(0);
            mplew.writeInt(chr.getId());
            mplew.writeInt(ring.getPartnerChrId());
            mplew.writeInt(ring.getItemId());
        }

    }

    public static byte[] damagePlayer(int chrId, int type, int monsteridfrom, int damage) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserHit.getValue());
        mplew.writeInt(chrId);
        mplew.write(type);
        mplew.writeInt(damage);
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        mplew.writeInt(monsteridfrom);
        mplew.write(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.write(0);
        mplew.write(0);
        mplew.writeInt(damage);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] damagePlayer(TakeDamageHandler.UserHitInfo info) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserHit.getValue());
        mplew.writeInt(info.getCharacterID());
        mplew.write(info.getType());
        mplew.writeInt(info.getDamage());
        mplew.writeBool(info.isCritical());
        mplew.writeBool(info.isUnkb());
        if (!info.isUnkb()) {
            mplew.write(0);
        }

        if (info.getType() < -1) {
            if (info.getType() == -8) {
                mplew.writeInt(1);
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeBool(false);
            }
        } else {
            mplew.writeInt(info.getTemplateID());
            mplew.write(info.getDirection());
            mplew.writeInt(info.getObjectID());
            mplew.writeInt(info.getSkillID());
            mplew.writeInt(info.getRefDamage());
            mplew.write(info.getDefType());
            if (info.getRefDamage() > 0) {
                mplew.writeBool(info.isRefPhysical());
                mplew.writeInt(info.getRefOid());
                mplew.write(info.getRefType());
                mplew.writePos(info.getPos());
            }

            mplew.write(info.getOffset());
            if ((info.getOffset() & 1) != 0) {
                mplew.writeInt(info.getOffset_d());
            }
        }

        mplew.writeInt(info.getDamage());
        if (info.getDamage() <= 0) {
            mplew.writeInt(info.getOffset_d());
        }

        return mplew.getPacket();
    }

    public static byte[] DamagePlayer2(int dam) {
        MaplePacketLittleEndianWriter pw = new MaplePacketLittleEndianWriter();
        pw.writeShort(OutHeader.DAMAGE_PLAYER2.getValue());
        pw.writeInt(dam);
        return pw.getPacket();
    }

    public static byte[] updateQuest(MapleQuestStatus quest) {
        MessageOption option = new MessageOption();
        option.setQuestStatus(quest);
        return CWvsContext.sendMessage(1, option);
    }

    public static byte[] updateInfoQuest(int quest, String data) {
        MessageOption option = new MessageOption();
        option.setObjectId(quest);
        option.setText(data == null ? "" : data);
        return CWvsContext.sendMessage(13, option);
    }

    public static byte[] updateQuestInfo(int quest, int npc, int nextquest, boolean updata) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserQuestResult.getValue());
        mplew.write(12);
        mplew.writeInt(quest);
        mplew.writeInt(npc);
        mplew.writeInt(nextquest);
        mplew.writeBool(updata);
        return mplew.getPacket();
    }

    public static byte[] startQuestTimeLimit(int n2, int n3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserQuestResult.getValue());
        mplew.write(7);
        mplew.writeShort(1);
        mplew.writeInt(n2);
        mplew.writeInt(n3);
        return mplew.getPacket();
    }

    public static byte[] stopQuestTimeLimit(int n2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserQuestResult.getValue());
        mplew.write(19);
        mplew.writeInt(n2);
        return mplew.getPacket();
    }

    public static byte[] updateMedalQuestInfo(byte op, int itemId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_MedalReissueResult.getValue());
        mplew.write(op);
        mplew.writeInt(itemId);
        return mplew.getPacket();
    }

    public static byte[] updateMount(MapleCharacter chr, boolean levelup) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetTamingMobInfo.getValue());
        mplew.writeInt(chr.getId());
        mplew.writeInt(chr.getMount().getLevel());
        mplew.writeInt(chr.getMount().getExp());
        mplew.writeInt(chr.getMount().getFatigue());
        mplew.write(levelup ? 1 : 0);
        return mplew.getPacket();
    }

    public static byte[] showCharacterInfo(MaplePacketReader slea, MapleCharacter player) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RemoteCharacterInfo.getValue());
        int unk = slea.readInt();
        String name = slea.readMapleAsciiString();
        MapleCharacter TouchP = player.getClient().getChannelServer().getPlayerStorage().getCharacterByName(name);
        mplew.writeInt(0);
        mplew.writeInt((new newCRand32()).random());
        mplew.writeHexString("01 00 01 00 00 00 00 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 00 00 01 00 00 00 00 00 00 00 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 01 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00");
        mplew.writeInt(unk);
        mplew.writeInt(unk);
        mplew.writeInt(unk);
        mplew.writeInt(0);
        mplew.writeShort(0);
        mplew.writeInt(TouchP.getId());
        mplew.writeInt(TouchP.getId());
        mplew.writeInt(3);
        mplew.writeAsciiString(TouchP.getName(), 15);
        mplew.write(TouchP.getGender());
        mplew.writeShort(133);
        mplew.writeInt(TouchP.getFace());
        mplew.writeInt(TouchP.getHair());
        mplew.writeInt(TouchP.getLevel());
        mplew.writeShort(TouchP.getJob());
        mplew.writeShort(TouchP.getStat().getStr());
        mplew.writeShort(TouchP.getStat().getDex());
        mplew.writeShort(TouchP.getStat().getInt());
        mplew.writeShort(TouchP.getStat().getLuk());
        mplew.writeInt(TouchP.getStat().getMaxHp(true));
        mplew.writeInt(TouchP.getStat().getMaxHp());
        mplew.writeInt(TouchP.getStat().getMaxMp(true));
        mplew.writeInt(TouchP.getStat().getMaxMp());
        mplew.writeHexString("37 00 01 01 21 00 00 00 B1 0A 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 6D E4 95 1A DF DA 01 10 27 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 10 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 06 00 00 00 00 00 00 00 00 00 00 00 06 00 00 00 00 00 00 00 00 00 40 E0 FD 3B 37 4F 01 56 D9 34 01 00 00 00 00 0A 00 00 00 00 06 07 00 00 00 00 00 00 00 00 1A DF DA 01 20 94 E4 95 00 80 05 BB 46 E6 17 02 00 40 E0 FD 3B 37 4F 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 32 00 00 00 00 B8 3E 78 47 41 D9 01 00 40 E0 FD 3B 37 4F 01 00 00 00 00 FF 00 00 00 00 FF");
        mplew.writeInt(TouchP.getInventory(MapleInventoryType.EQUIP).getSlotLimit());
        mplew.writeInt(TouchP.getInventory(MapleInventoryType.USE).getSlotLimit());
        mplew.writeInt(TouchP.getInventory(MapleInventoryType.SETUP).getSlotLimit());
        mplew.writeInt(TouchP.getInventory(MapleInventoryType.ETC).getSlotLimit());
        mplew.writeInt(TouchP.getInventory(MapleInventoryType.CASH).getSlotLimit());
        mplew.writeInt(TouchP.getInventory(MapleInventoryType.DECORATION).getSlotLimit());
        MapleInventory iv = TouchP.getInventory(MapleInventoryType.EQUIPPED);
        List<Item> equippedList = iv.newList();
        Collections.sort(equippedList);
        List<Item> equipped = new ArrayList();
        List<Item> equippedCash = new ArrayList();
        List<Item> equippedDragon = new ArrayList();
        List<Item> equippedMechanic = new ArrayList();
        List<Item> equippedAndroid = new ArrayList();
        List<Item> equippedLolitaCash = new ArrayList();
        List<Item> equippedBit = new ArrayList();
        List<Item> equippedZeroBetaCash = new ArrayList();
        List<Item> equippedArcane = new ArrayList();
        List<Item> equippedAuthenticSymbol = new ArrayList();
        List<Item> equippedTotem = new ArrayList();
        List<Item> equippedMonsterEqp = new ArrayList();
        List<Item> equippedHakuFan = new ArrayList();
        List<Item> equippedUnknown = new ArrayList();
        List<Item> equippedCashPreset = new ArrayList();
        Iterator var23 = equippedList.iterator();

        while(var23.hasNext()) {
            Item item = (Item)var23.next();
            if (item.getPosition() < 0 && item.getPosition() > -100) {
                equipped.add(item);
            } else if (item.getPosition() <= -100 && item.getPosition() > -1000) {
                equippedCash.add(item);
            } else if (item.getPosition() <= -1000 && item.getPosition() > -1100) {
                equippedDragon.add(item);
            } else if (item.getPosition() <= -1100 && item.getPosition() > -1200) {
                equippedMechanic.add(item);
            } else if (item.getPosition() <= -1200 && item.getPosition() > -1300) {
                equippedAndroid.add(item);
            } else if (item.getPosition() <= -1300 && item.getPosition() > -1310) {
                equippedLolitaCash.add(item);
            } else if (item.getPosition() <= -1400 && item.getPosition() > -1500) {
                equippedBit.add(item);
            } else if (item.getPosition() <= -1500 && item.getPosition() > -1600) {
                equippedZeroBetaCash.add(item);
            } else if (item.getPosition() <= -1600 && item.getPosition() > -1606) {
                equippedArcane.add(item);
            } else if (item.getPosition() <= -1700 && item.getPosition() > -1706) {
                equippedAuthenticSymbol.add(item);
            } else if (item.getPosition() <= -5100 && item.getPosition() > -5107) {
                equippedMonsterEqp.add(item);
            } else if (item.getPosition() == -5200) {
                equippedHakuFan.add(item);
            } else if (item.getPosition() <= -1800 && item.getPosition() > -1830) {
                equippedCashPreset.add(item);
            } else if (item.getPosition() <= -5000 && item.getPosition() > -5002) {
                equippedTotem.add(item);
            } else if (item.getPosition() <= -6000 && item.getPosition() > -6200) {
                TouchP.getSkillSkin().put(MapleItemInformationProvider.getInstance().getSkillSkinFormSkillId(item.getItemId()), item.getItemId());
                equippedUnknown.add(item);
            }

            mplew.write(0);
            mplew.write(0);
            mplew.write(1);
            PacketHelper.encodeInventory(mplew, equippedList, TouchP);
            mplew.writeHexString("00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 05 00 E9 03 00 00 01 00 00 00 00 80 05 BB 46 E6 17 02 2D DC C4 04 01 00 00 00 00 7A 59 72 DC E2 DA 01 49 00 00 00 00 00 00 00 00 80 05 BB 46 E6 17 02 0C 00 00 00 00 00 00 00 00 80 05 BB 46 E6 17 02 13 BD C4 04 01 00 00 00 00 80 05 BB 46 E6 17 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 FF FF FF FF 02 00 00 00 00 00 00 00 00 FF FF FF FF 03 00 00 00 00 00 00 00 00 FF FF FF FF 04 00 00 00 00 00 00 00 00 FF FF FF FF 05 00 00 00 00 00 00 00 00 FF FF FF FF 06 00 00 00 00 00 00 00 00 FF FF FF FF 07 00 00 00 00 00 00 00 00 FF FF FF FF 08 00 00 00 00 00 00 00 00 FF FF FF FF 09 00 00 00 00 00 00 00 00 FF FF FF FF 0A 00 00 00 00 00 00 00 00 FF FF FF FF 0B 00 00 00 00 00 00 00 00 FF FF FF FF 0C 00 00 00 00 00 00 00 00 FF FF FF FF 0D 00 00 00 00 00 00 00 00 FF FF FF FF 0E 00 00 00 00 00 00 00 00 FF FF FF FF 0F 00 00 00 00 00 00 00 00 FF FF FF FF 10 00 00 00 00 00 00 00 00 FF FF FF FF 11 00 00 00 00 00 00 00 00 FF FF FF FF 12 00 00 00 00 00 00 00 00 FF FF FF FF 13 00 00 00 00 00 00 00 00 FF FF FF FF 14 00 00 00 00 00 00 00 00 FF FF FF FF 15 00 00 00 00 00 00 00 00 FF FF FF FF 16 00 00 00 00 00 00 00 00 FF FF FF FF 17 00 00 00 00 00 00 00 00 FF FF FF FF 18 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 FF FF FF FF 02 00 00 00 00 00 00 00 00 FF FF FF FF 03 00 00 00 00 00 00 00 00 FF FF FF FF 04 00 00 00 00 00 00 00 00 FF FF FF FF 05 00 00 00 00 00 00 00 00 FF FF FF FF 06 00 00 00 00 00 00 00 00 FF FF FF FF 07 00 00 00 00 00 00 00 00 FF FF FF FF 08 00 00 00 00 00 00 00 00 FF FF FF FF 09 00 00 00 00 00 00 00 00 FF FF FF FF 0A 00 00 00 00 00 00 00 00 FF FF FF FF 0B 00 00 00 00 00 00 00 00 FF FF FF FF 0C 00 00 00 00 00 00 00 00 FF FF FF FF 0D 00 00 00 00 00 00 00 00 FF FF FF FF 0E 00 00 00 00 00 00 00 00 FF FF FF FF 0F 00 00 00 00 00 00 00 00 FF FF FF FF 10 00 00 00 00 00 00 00 00 FF FF FF FF 11 00 00 00 00 00 00 00 00 FF FF FF FF 12 00 00 00 00 00 00 00 00 FF FF FF FF 13 00 00 00 00 00 00 00 00 FF FF FF FF 14 00 00 00 00 00 00 00 00 FF FF FF FF 15 00 00 00 00 00 00 00 00 FF FF FF FF 16 00 00 00 00 00 00 00 00 FF FF FF FF 17 00 00 00 00 00 00 00 00 FF FF FF FF 18 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 12 00 00 00 6E 1B 00 00 00 00 68 1E 00 00 00 00 6A 1E 00 00 00 00 D9 3F 00 00 00 00 1E 40 00 00 00 00 7B 41 00 00 00 00 5E 49 00 00 03 00 54 3D 31 4B 4A 00 00 1F 00 65 78 70 69 72 65 64 3D 31 3B 64 61 74 65 3D 30 3B 69 64 3D 30 3B 73 6C 6F 74 70 6F 73 3D 30 63 67 00 00 00 00 9F 69 00 00 00 00 A2 69 00 00 00 00 A3 69 00 00 00 00 6A AD 07 00 00 00 70 74 2F 00 00 00 72 74 2F 00 00 00 74 74 2F 00 00 00 75 74 2F 00 00 00 76 74 2F 00 00 00 01 15 00 00 00 00 00 66 00 00 00 3C 00 00 00 36 00 00 00 36 00 00 00 00 00 00 00 66 03 00 00 E9 02 00 00 66 03 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 F0 3F 00 00 00 00 00 00 F0 3F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 03 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 06 00 00 00 01 00 00 00 00 00 00 00 02 00 00 00 00 00 00 00 03 00 00 00 00 00 00 00 04 00 00 00 00 00 00 00 05 00 00 00 00 00 00 00 06 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 83 FE FF FF F5 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 2C 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 05 00 00 00 FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00");
        }

        return mplew.getPacket();
    }

    public static byte[] updateSkill(int skillid, int level, int masterlevel, long expiration) {
        boolean isProfession = skillid == 92000000 || skillid == 92010000 || skillid == 92020000 || skillid == 92030000 || skillid == 92040000;
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ChangeSkillRecordResult.getValue());
        mplew.writeBool(!isProfession);
        mplew.writeBool(isProfession);
        mplew.write(0);
        mplew.writeShort(1);
        mplew.writeInt(skillid);
        mplew.writeInt(level);
        mplew.writeInt(masterlevel);
        PacketHelper.addExpirationTime(mplew, expiration);
        mplew.write(8);
        return mplew.getPacket();
    }

    public static byte[] updateSkills(Map<Integer, SkillEntry> update) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ChangeSkillRecordResult.getValue());
        mplew.write(1);
        mplew.write(0);
        mplew.write(0);
        mplew.writeShort(update.size());
        Iterator var2 = update.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Integer, SkillEntry> skills = (Map.Entry)var2.next();
            mplew.writeInt((Integer)skills.getKey());
            mplew.writeInt(((SkillEntry)skills.getValue()).skillevel);
            mplew.writeInt(((SkillEntry)skills.getValue()).masterlevel);
            PacketHelper.addExpirationTime(mplew, ((SkillEntry)skills.getValue()).expiration);
        }

        mplew.write((byte)Randomizer.rand(1, 255));
        return mplew.getPacket();
    }

    public static byte[] updatePetSkill(int skillid, int level, int masterlevel, long expiration) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ChangeSkillRecordResult.getValue());
        mplew.write(0);
        mplew.write(1);
        mplew.write(0);
        mplew.writeShort(1);
        mplew.writeInt(skillid);
        mplew.writeInt(level == 0 ? -1 : level);
        mplew.writeInt(masterlevel);
        PacketHelper.addExpirationTime(mplew, expiration);
        mplew.write(8);
        return mplew.getPacket();
    }

    public static byte[] updateQuestMobKills(MapleQuestStatus status) {
        MessageOption option = new MessageOption();
        MapleQuestStatus quest = new MapleQuestStatus(status.getQuest(), 1);
        StringBuilder sb = new StringBuilder();
        Iterator var4 = status.getMobKills().values().iterator();

        while(var4.hasNext()) {
            int kills = (Integer)var4.next();
            sb.append(StringUtil.getLeftPaddedStr(String.valueOf(kills % 1000), '0', 3));
        }

        quest.setCustomData(sb.toString());
        option.setQuestStatus(quest);
        return CWvsContext.sendMessage(1, option);
    }

    public static byte[] getKeymap(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_FuncKeyMappedInit.getValue());

        for(int l = 0; l < 3; ++l) {
            ((FuncKeyMap)chr.getFuncKeyMaps().get(l)).setMode(1);
            ((FuncKeyMap)chr.getFuncKeyMaps().get(l)).encode(mplew);
        }

        mplew.write(0);
        chr.getQuickSlot().writeData(mplew);
        mplew.write(0);
        mplew.writeInt(82);
        mplew.writeInt(71);
        mplew.writeInt(73);
        mplew.writeInt(29);
        mplew.writeInt(83);
        mplew.writeInt(79);
        mplew.writeInt(81);
        mplew.writeInt(2);
        mplew.writeInt(3);
        mplew.writeInt(4);
        mplew.writeInt(5);
        mplew.writeInt(16);
        mplew.writeInt(17);
        mplew.writeInt(18);
        mplew.writeInt(19);
        mplew.writeInt(6);
        mplew.writeInt(7);
        mplew.writeInt(8);
        mplew.writeInt(9);
        mplew.writeInt(20);
        mplew.writeInt(30);
        mplew.writeInt(31);
        mplew.writeInt(32);
        mplew.writeInt(10);
        mplew.writeInt(11);
        mplew.writeInt(33);
        mplew.writeInt(34);
        mplew.writeInt(37);
        mplew.writeInt(38);
        mplew.writeInt(49);
        mplew.writeInt(50);
        return mplew.getPacket();
    }

    public static byte[] petAutoHP(int itemId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_PetConsumeItemInit.getValue());
        mplew.writeInt(itemId);
        return mplew.getPacket();
    }

    public static byte[] petAutoMP(int itemId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_PetConsumeMPItemInit.getValue());
        mplew.writeInt(itemId);
        return mplew.getPacket();
    }

    public static byte[] petAutoBuff(int skillId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_PET_AUTO_BUFF.getValue());
        mplew.writeInt(skillId);
        return mplew.getPacket();
    }

    public static byte[] openFishingStorage(int type, HiredFisher hf, MerchItemPackage pack, int playrId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.FISHING_STORE.getValue());
        mplew.write(type);
        switch (type) {
            case 15:
                mplew.writeInt(0);
                mplew.write(0);
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 24:
            case 25:
            case 26:
            case 27:
            case 29:
            case 31:
            case 32:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 44:
            default:
                break;
            case 22:
                mplew.writeInt(hf.getOwnerId());
                mplew.write(1);
                break;
            case 23:
                mplew.writeInt(hf.getOwnerId());
                break;
            case 28:
            case 30:
                mplew.writeInt(hf.getObjectId());
                writeHiredFisher(mplew, hf, pack, playrId);
                break;
            case 33:
                mplew.writeInt(-1);
                break;
            case 35:
                mplew.writeInt(pack != null ? (int)pack.getMesos() : 0);
                mplew.writeLong(pack != null ? (long)((int)pack.getExp()) : 0L);
                writeHiredFisher(mplew, hf, pack, playrId);
                break;
            case 43:
            case 45:
                mplew.writeLong(DateUtil.getKoreanTimestamp(hf.getStartTime()));
                mplew.writeLong(DateUtil.getKoreanTimestamp(hf.getStopTime()));
        }

        return mplew.getPacket();
    }

    public static void writeHiredFisher(MaplePacketLittleEndianWriter mplew, HiredFisher hf, MerchItemPackage itemPackage, int playrId) {
        long l2 = -1L;
        mplew.writeLong(l2);
        mplew.writeInt(0);
        EnumMap<MapleInventoryType, ArrayList<Item>> items = new EnumMap(MapleInventoryType.class);
        items.put(MapleInventoryType.EQUIP, new ArrayList());
        items.put(MapleInventoryType.USE, new ArrayList());
        items.put(MapleInventoryType.SETUP, new ArrayList());
        items.put(MapleInventoryType.ETC, new ArrayList());
        items.put(MapleInventoryType.CASH, new ArrayList());
        items.put(MapleInventoryType.DECORATION, new ArrayList());
        if (hf != null) {
            hf.getItems().forEach((item) -> {
                ((ArrayList)items.get(ItemConstants.getInventoryType(item.getItem().getItemId()))).add(item.getItem());
            });
        } else if (itemPackage != null) {
            itemPackage.getItems().forEach((item) -> {
                ((ArrayList)items.get(ItemConstants.getInventoryType(item.getItemId()))).add(item);
            });
        }

        items.forEach((key, value) -> {
            mplew.write(value.size());
            value.forEach((item) -> {
                PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
            });
        });
        items.clear();
        mplew.writeInt(hf != null ? hf.getOwnerId() : playrId);
    }

    public static byte[] fairyPendantMessage(int position, int stage, int percent, long startTime, long time, boolean inChat) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_BonusExpRateChanged.getValue());
        mplew.writeInt(Math.abs(position));
        mplew.writeInt(stage);
        mplew.writeInt(percent);
        mplew.writeLong(PacketHelper.getTime(startTime));
        mplew.writeLong(time);
        mplew.write(inChat ? 1 : 0);
        return mplew.getPacket();
    }

    public static byte[] giveFameResponse(int mode, String charname, int newfame) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_GivePopularityResult.getValue());
        mplew.write(0);
        mplew.writeMapleAsciiString(charname);
        mplew.write(mode);
        mplew.writeInt(newfame);
        return mplew.getPacket();
    }

    public static byte[] giveFameErrorResponse(int status) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_GivePopularityResult.getValue());
        mplew.write(status);
        return mplew.getPacket();
    }

    public static byte[] receiveFame(int mode, String charnameFrom) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_GivePopularityResult.getValue());
        mplew.write(5);
        mplew.writeMapleAsciiString(charnameFrom);
        mplew.write(mode);
        return mplew.getPacket();
    }

    public static byte[] stopClock() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_DestroyClock.getValue());
        return mplew.getPacket();
    }

    public static byte[] practiceMode(boolean b) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.PRACTICE_MODE.getValue());
        mplew.writeBool(b);
        return mplew.getPacket();
    }

    public static byte[] spawnMist(MapleAffectedArea mist) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_AffectedAreaCreated.getValue());
        mplew.writeInt(mist.getObjectId());
        mplew.write(mist.getAreaType());
        mplew.writeInt(mist.getOwnerId());
        mplew.writeInt(mist.getSkillID());
        mplew.writeShort(mist.getSkillLevel());
        mplew.writeShort(mist.getSkillDelay());
        mplew.writeRect(mist.getArea());
        if (mist.getSkillID() == 162111000) {
            mplew.writeRect(mist.getArea());
        }

        mplew.writeInt(mist.getSubtype());
        mplew.writePos(mist.getPosition());
        mplew.writeInt(mist.getSkillID() == 227 ? mist.getPosition().x : 0);
        mplew.writeInt(mist.getForce());
        mplew.write(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        switch (mist.getSkillID()) {
            case 4121015:
            case 4221006:
            case 33111013:
            case 33121016:
            case 35121052:
            case 51120057:
            case 131001107:
            case 131001207:
            case 135001012:
            case 152121041:
            case 400001017:
            case 400020046:
            case 400020051:
            case 400041041:
            case 400051092:
                mplew.writeBool(mist.isFacingLeft());
            default:
                mplew.writeInt(mist.getLeftTime());
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.write(mist.BUnk1);
                mplew.write(mist.BUnk2);
                mplew.write(false);
                if (mist.getSkillID() == 2321015) {
                    mplew.writeInt(0);
                }

                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeInt(0);
                return mplew.getPacket();
        }
    }

    public static byte[] removeMist(int oid, boolean eruption) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_AffectedAreaRemoved.getValue());
        mplew.writeInt(oid);
        mplew.writeInt(0);
        mplew.write(eruption);
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] spawnLove(int oid, int itemid, String name, String msg, Point pos, int ft) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_MessageBoxEnterField.getValue());
        mplew.writeInt(oid);
        mplew.writeInt(itemid);
        mplew.writeMapleAsciiString(msg);
        mplew.writeMapleAsciiString(name);
        mplew.writeShort(pos.x);
        mplew.writeShort(pos.y + ft);
        return mplew.getPacket();
    }

    public static byte[] removeLove(int oid, int itemid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_MessageBoxLeaveField.getValue());
        mplew.writeInt(oid);
        mplew.writeInt(itemid);
        return mplew.getPacket();
    }

    public static byte[] itemEffect(int chrId, int itemid, int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSetActiveEffectItem.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(itemid);
        mplew.writeInt(type);
        return mplew.getPacket();
    }

    public static byte[] showTitleEffect(int chrId, int itemid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSetActiveNickItem.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(itemid);
        mplew.writeBool(false);
        return mplew.getPacket();
    }

    public static byte[] UserSetActivePortableChair(MapleCharacter player) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSetActivePortableChair.getValue());
        mplew.writeInt(player.getId());
        PortableChair chair = player.getChair();
        mplew.writeInt(chair == null ? 0 : chair.getItemId());
        writeChairData(mplew, player);
        mplew.write(0);
        return mplew.getPacket();
    }

    private static void writeChairData(MaplePacketLittleEndianWriter mplew, MapleCharacter player) {
        PortableChair chair = player.getChair();
        boolean hasChair = chair != null && !ServerConfig.BLOCK_CHAIRS_SET.contains(chair.getItemId());
        mplew.writeBool(hasChair);
        if (hasChair) {
            int i;
            switch (ItemConstants.getChairType(chair.getItemId())) {
                case TOWER:
                    encodeTowerChairInfo(mplew, player);
                    break;
                case MESO:
                    mplew.writeLong(0L);
                    break;
                case TEXT:
                    mplew.writeMapleAsciiString(chair.getMsg());
                    PacketHelper.addChaterName(mplew, player.getName(), chair.getMsg());
                    break;
                case LV:
                    boolean hasArr = chair.getArr() != null;
                    mplew.writeBool(hasArr);
                    if (hasArr) {
                        mplew.writeInt(chair.getUn2());
                        mplew.writeInt(chair.getArr().length);
                        Triple[] var12 = chair.getArr();
                        int var6 = var12.length;

                        for(int var7 = 0; var7 < var6; ++var7) {
                            Triple triple = var12[var7];
                            mplew.writeInt((Integer)triple.getLeft());
                            Pair right = (Pair)triple.getRight();
                            AvatarLook left = (AvatarLook)right.getLeft();
                            mplew.writeInt(left != null ? left.getJob() : 0);
                            String mid = (String)triple.getMid();
                            mplew.writeMapleAsciiString(mid);
                            mplew.writeBool(left != null);
                            if (left != null) {
                                left.encode(mplew, false);
                            }

                            mplew.writeBool(right.getRight() != null);
                            if (right.getRight() != null) {
                                ((AvatarLook)right.getRight()).encode(mplew, false);
                            }
                        }

                        mplew.writeInt(0);
                    }
                    break;
                case POP:
                    mplew.writeInt(1);

                    for(i = 0; i < 1; ++i) {
                        mplew.writeMapleAsciiString(player.getName());
                        mplew.writeInt(player.getFame());
                    }

                    return;
                case TIME:
                    mplew.writeInt(0);
                case STARFORCE:
                case RANDOM:
                case MIRROR:
                case ANDROID:
                case ROTATED_SLEEPING_BAG_CHAIR:
                case EVENT_POINT:
                case EVENT_POINT_GENDERLY:
                case EVENT_POINT_CLONE:
                case YETI:
                case MAPLE_GLOBE:
                    break;
                case TRICK_OR_TREAT:
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    break;
                case CELEBRATE:
                    mplew.writeInt(chair.getItemId());
                    break;
                case IDENTITY:
                    mplew.writeInt(player.getAccountID());
                    mplew.write(0);
                    mplew.writeInt(0);
                    break;
                case POP_BUTTON:
                    mplew.writeInt(0);
                    break;
                case ROLLING_HOUSE:
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    mplew.write(0);
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    break;
                case MANNEQUIN:
                    mplew.writeInt(0);
                    break;
                case PET:
                    for(i = 0; i < 3; ++i) {
                        mplew.writeInt(0);
                        mplew.writeInt(0);
                        mplew.writeInt(0);
                    }

                    return;
                case SCORE:
                    mplew.writeInt(0);
                    break;
                case SCALE_AVATAR:
                    mplew.writeBool(false);
                    break;
                case WASTE:
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    break;
                case ROLLING_HOUSE_2019:
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    mplew.write(0);
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    mplew.writeInt(0);
                    break;
                case CHAR_LV:
                    mplew.writeInt(player.getLevel());
                    break;
                case HASH_TAG:
                case UN22:
                case TRAITS:
                default:
                    mplew.writeInt(chair.getUn3());
                    mplew.writeInt(chair.getUn4());
                    mplew.write(chair.getUn5());
            }
        }

    }

    public static void encodeTowerChairInfo(MaplePacketLittleEndianWriter mplew, MapleCharacter player) {
        ArrayList<Integer> arrayList = new ArrayList();
        player.getInfoQuest(7266);

        String string;
        for(int i2 = 0; i2 < 6 && (string = player.getOneInfo(7266, String.valueOf(i2))) != null && Integer.valueOf(string) > 0; ++i2) {
            arrayList.add(Integer.valueOf(string));
        }

        mplew.writeInt(arrayList.size());
        Objects.requireNonNull(mplew);
        arrayList.forEach(mplew::writeInt);
    }

    public static byte[] showSitOnTimeCapsule() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSitOnTimeCapsule.getValue());
        return mplew.getPacket();
    }

    public static byte[] addChairMeso(int cid, int value) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSetActivePortableChair.getValue());
        mplew.writeInt(cid);
        mplew.writeInt(value);
        mplew.writeInt(1);
        return mplew.getPacket();
    }

    public static byte[] useTowerChairSetting() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.USE_TOWERCHAIR_SETTING_RESULT.getValue());
        return mplew.getPacket();
    }

    public static byte[] UserSitResult(int playerId, int chairId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CANCEL_CHAIR_TRIGGER_UI.getValue());
        mplew.writeInt(playerId);
        mplew.writeInt(0);
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] spawnReactor(MapleReactor reactor) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ReactorEnterField.getValue());
        mplew.writeInt(reactor.getObjectId());
        mplew.writeInt(reactor.getReactorId());
        mplew.write(reactor.getState());
        mplew.writePos(reactor.getPosition());
        mplew.write(reactor.getFacingDirection());
        mplew.writeMapleAsciiString(reactor.getName());
        return mplew.getPacket();
    }

    public static byte[] triggerReactor(MapleReactor reactor, int stance) {
        return triggerReactor(reactor, stance, 0, 0, 0);
    }

    public static byte[] triggerReactor(MapleReactor reactor, int stance, int n2, int cid, int n4) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ReactorChangeState.getValue());
        mplew.writeInt(reactor.getObjectId());
        mplew.write(reactor.getState());
        mplew.writePos(reactor.getPosition());
        mplew.writeShort(stance);
        mplew.write(n4);
        mplew.writeInt(n2);
        mplew.writeInt(cid);
        return mplew.getPacket();
    }

    public static byte[] triggerReactor(MapleReactor reactor) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ReactorChangeState.getValue());
        mplew.writeInt(reactor.getObjectId());
        mplew.write(reactor.getState());
        mplew.writePos(reactor.getPosition());
        mplew.writeShort(reactor.getHitStart());
        mplew.write(reactor.getProperEventIdx());
        mplew.writeInt(reactor.getStateEnd());
        mplew.writeInt(reactor.getOwnerID());
        return mplew.getPacket();
    }

    public static byte[] destroyReactor(MapleReactor reactor) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ReactorRemove.getValue());
        mplew.writeInt(reactor.getObjectId());
        return mplew.getPacket();
    }

    public static byte[] reactorLeaveField(MapleReactor reactor) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ReactorLeaveField.getValue());
        mplew.writeInt(reactor.getObjectId());
        mplew.write(reactor.getState());
        mplew.write(0);
        mplew.writePos(reactor.getPosition());
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] musicChange(String song) {
        return FieldPacket.fieldEffect(FieldEffect.changeBGM(song, 0, 0, 0));
    }

    public static byte[] showEffect(String effect) {
        return FieldPacket.fieldEffect(FieldEffect.getFieldBackgroundEffectFromWz(effect, 0));
    }

    public static byte[] playSound(String sound) {
        return FieldPacket.fieldEffect(FieldEffect.playSound(sound, 100, 0, 0));
    }

    public static byte[] startMapEffect(String msg, int itemid, boolean active) {
        return startMapEffect(msg, itemid, -1, active);
    }

    public static byte[] startMapEffect(String msg, int itemid, int effectType, boolean active) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_BlowWeather.getValue());
        mplew.write(0);
        mplew.writeInt(itemid);
        if (itemid == 116) {
            mplew.writeInt(effectType);
        }

        if (itemid > 0) {
            mplew.writeMapleAsciiString(msg);
            mplew.writeInt(15);
            mplew.write(0);
        }

        return mplew.getPacket();
    }

    public static byte[] removeMapEffect() {
        return startMapEffect((String)null, 0, -1, false);
    }

    public static byte[] showPredictCard(String name, String otherName, int love, int cardId, int commentId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SHOW_PREDICT_CARD.getValue());
        mplew.writeMapleAsciiString(name);
        mplew.writeMapleAsciiString(otherName);
        mplew.writeInt(love);
        mplew.writeInt(cardId);
        mplew.writeInt(commentId);
        return mplew.getPacket();
    }

    public static byte[] UserSkillPrepare(int fromId, int skillId, byte level, byte display, byte direction, byte speed, Point position) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSkillPrepare.getValue());
        mplew.writeInt(fromId);
        mplew.writeInt(skillId);
        mplew.write(level);
        mplew.write(display);
        mplew.write(direction);
        mplew.write(speed);
        if (position != null) {
            mplew.writePos(position);
        }

        return mplew.getPacket();
    }

    public static byte[] skillCancel(MapleCharacter from, int skillId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSkillCancel.getValue());
        mplew.writeInt(from.getId());
        mplew.writeInt(skillId);
        mplew.writeInt(skillId);
        return mplew.getPacket();
    }

    public static byte[] sendHint(String hint, int width, int time, Point pos) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        if (width < 1) {
            width = hint.length() * 10;
            if (width < 40) {
                width = 40;
            }
        }

        if (time < 5) {
            time = 5;
        }

        mplew.writeShort(OutHeader.LP_UserBalloonMsg.getValue());
        mplew.writeMapleAsciiString(hint);
        mplew.writeShort(width);
        mplew.writeShort(time);
        mplew.writeBool(pos == null);
        if (pos != null) {
            mplew.writePosInt(pos);
        }

        return mplew.getPacket();
    }

    public static byte[] showEquipEffect() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SHOW_EQUIP_EFFECT.getValue());
        return mplew.getPacket();
    }

    public static byte[] showEquipEffect(int team) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SHOW_EQUIP_EFFECT.getValue());
        mplew.writeShort(team);
        return mplew.getPacket();
    }

    public static byte[] useSkillBook(MapleCharacter chr, int skillid, int maxlevel, boolean canuse, boolean success) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SkillLearnItemResult.getValue());
        mplew.write(1);
        mplew.writeInt(chr.getId());
        mplew.write(1);
        mplew.writeInt(skillid);
        mplew.writeInt(maxlevel);
        mplew.writeBool(canuse);
        mplew.writeBool(success);
        return mplew.getPacket();
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

    public static byte[] boatPacket(int effect) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.BOAT_EFFECT.getValue());
        mplew.writeShort(effect);
        return mplew.getPacket();
    }

    public static byte[] boatEffect(int effect) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.BOAT_EFF.getValue());
        mplew.writeShort(effect);
        return mplew.getPacket();
    }

    public static byte[] removeItemFromDuey(boolean remove, int Package) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_Parcel.getValue());
        mplew.write(24);
        mplew.writeInt(Package);
        mplew.write(remove ? 3 : 4);
        return mplew.getPacket();
    }

    public static byte[] sendDuey(byte operation, List<MapleDueyActions> packages) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_Parcel.getValue());
        mplew.write(operation);
        switch (operation) {
            case 9:
                mplew.write(1);
                break;
            case 10:
                mplew.write(0);
                mplew.write(packages.size());
                Iterator var3 = packages.iterator();

                while(var3.hasNext()) {
                    MapleDueyActions dp = (MapleDueyActions)var3.next();
                    mplew.writeInt(dp.getPackageId());
                    mplew.writeAsciiString(dp.getSender(), 15);
                    mplew.writeInt(dp.getMesos());
                    mplew.writeLong(PacketHelper.getTime(dp.getSentTime()));
                    mplew.writeZeroBytes(202);
                    if (dp.getItem() != null) {
                        mplew.write(1);
                        PacketHelper.GW_ItemSlotBase_Encode(mplew, dp.getItem());
                    } else {
                        mplew.write(0);
                    }
                }

                mplew.write(0);
        }

        return mplew.getPacket();
    }

    public static byte[] enableTV() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.ENABLE_TV.getValue());
        mplew.writeInt(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] removeTV() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.REMOVE_TV.getValue());
        return mplew.getPacket();
    }

    public static byte[] sendTV(MapleCharacter chr, List<String> messages, int type, MapleCharacter partner, int delay) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.START_TV.getValue());
        mplew.write(partner != null ? 2 : 1);
        mplew.write(type);
        chr.getAvatarLook().encode(mplew, false);
        mplew.writeMapleAsciiString(chr.getName());
        if (partner != null) {
            mplew.writeMapleAsciiString(partner.getName());
        } else {
            mplew.writeShort(0);
        }

        for(int i = 0; i < messages.size(); ++i) {
            if (i == 4 && ((String)messages.get(4)).length() > 15) {
                mplew.writeMapleAsciiString(((String)messages.get(4)).substring(0, 15));
            } else {
                mplew.writeMapleAsciiString((String)messages.get(i));
            }
        }

        mplew.writeInt(delay);
        if (partner != null) {
            partner.getAvatarLook().encode(mplew, false);
        }

        return mplew.getPacket();
    }

    public static byte[] showQuestMsg(String msg) {
        return serverNotice(5, msg);
    }

    public static byte[] Mulung_Pts(int recv, int total) {
        return showQuestMsg("獲得了 " + recv + " 點修煉點數。總修煉點數為 " + total + " 點。");
    }

    public static byte[] showOXQuiz(int questionSet, int questionId, boolean askQuestion) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_Quiz.getValue());
        mplew.write(askQuestion ? 1 : 0);
        mplew.write(questionSet);
        mplew.writeShort(questionId);
        return mplew.getPacket();
    }

    public static byte[] leftKnockBack() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SnowBallTouch.getValue());
        return mplew.getPacket();
    }

    public static byte[] rollSnowball(int type, MapleSnowball.MapleSnowballs ball1, MapleSnowball.MapleSnowballs ball2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SnowBallState.getValue());
        mplew.write(type);
        mplew.writeInt(ball1 == null ? 0 : ball1.getSnowmanHP() / 75);
        mplew.writeInt(ball2 == null ? 0 : ball2.getSnowmanHP() / 75);
        mplew.writeShort(ball1 == null ? 0 : ball1.getPosition());
        mplew.write(0);
        mplew.writeShort(ball2 == null ? 0 : ball2.getPosition());
        mplew.writeZeroBytes(11);
        return mplew.getPacket();
    }

    public static byte[] enterSnowBall() {
        return rollSnowball(0, (MapleSnowball.MapleSnowballs)null, (MapleSnowball.MapleSnowballs)null);
    }

    public static byte[] hitSnowBall(int team, int damage, int distance, int delay) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SnowBallHit.getValue());
        mplew.write(team);
        mplew.writeShort(damage);
        mplew.write(distance);
        mplew.write(delay);
        return mplew.getPacket();
    }

    public static byte[] snowballMessage(int team, int message) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SnowBallMsg.getValue());
        mplew.write(team);
        mplew.writeInt(message);
        return mplew.getPacket();
    }

    public static byte[] finishedSort(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SortItemResult.getValue());
        mplew.write(1);
        mplew.write(type);
        return mplew.getPacket();
    }

    public static byte[] coconutScore(int[] coconutscore) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_CoconutScore.getValue());
        mplew.writeShort(coconutscore[0]);
        mplew.writeShort(coconutscore[1]);
        return mplew.getPacket();
    }

    public static byte[] hitCoconut(boolean spawn, int id, int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_CoconutHit.getValue());
        if (spawn) {
            mplew.write(0);
            mplew.writeInt(128);
        } else {
            mplew.writeInt(id);
            mplew.write(type);
        }

        return mplew.getPacket();
    }

    public static byte[] finishedGather(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_GatherItemResult.getValue());
        mplew.write(1);
        mplew.write(type);
        return mplew.getPacket();
    }

    public static byte[] yellowChat(String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserChatMsg.getValue());
        mplew.writeShort(7);
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }

    public static byte[] getPeanutResult(int ourItem) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_IncubatorResult.getValue());
        mplew.writeBool(false);
        mplew.writeInt(ourItem);
        return mplew.getPacket();
    }

    public static byte[] getPeanutResult(int itemId, short quantity, int ourItem, int ourSlot) {
        return getPeanutResult(itemId, quantity, ourItem, ourSlot, (Item)null);
    }

    public static byte[] getPeanutResult(int itemId, short quantity, int ourItem, int ourSlot, Item item) {
        return getPeanutResult(itemId, quantity, ourItem, ourSlot, 0, (short)0, (byte)0, item);
    }

    public static byte[] getPeanutResult(int itemId, short quantity, int ourItem, int ourSlot, byte fever) {
        return getPeanutResult(itemId, quantity, ourItem, ourSlot, fever, (Item)null);
    }

    public static byte[] getPeanutResult(int itemId, short quantity, int ourItem, int ourSlot, byte fever, Item item) {
        return getPeanutResult(itemId, quantity, ourItem, ourSlot, 0, (short)0, fever, item);
    }

    public static byte[] getPeanutResult(int itemId, short quantity, int ourItem, int ourSlot, int itemId2, short quantity2) {
        return getPeanutResult(itemId, quantity, ourItem, ourSlot, itemId2, quantity2, (byte)0, (Item)null);
    }

    public static byte[] getPeanutResult(int itemId, short quantity, int ourItem, int ourSlot, int itemId2, short quantity2, byte fever, Item item) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_IncubatorResult.getValue());
        boolean success = true;
        mplew.writeBool(success);
        if (success) {
            mplew.writeInt(itemId);
            mplew.writeShort(quantity);
            mplew.writeInt(1);
            mplew.writeInt(ourItem);
            mplew.writeInt(ourSlot);
            mplew.writeInt(itemId2);
            mplew.writeInt(quantity2);
            mplew.write(fever);
            mplew.write(item != null);
            if (item != null) {
                PacketHelper.GW_ItemSlotBase_Encode(mplew, item);
            }
        } else {
            mplew.writeInt(ourItem);
        }

        return mplew.getPacket();
    }

    public static byte[] sendLevelup(boolean family, int level, String name) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_NotifyLevelUp.getValue());
        mplew.write(family ? 1 : 2);
        mplew.writeInt(level);
        mplew.writeMapleAsciiString(name);
        return mplew.getPacket();
    }

    public static byte[] sendMarriage(boolean family, String name) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_NotifyWedding.getValue());
        mplew.write(family ? 1 : 0);
        mplew.writeMapleAsciiString(name);
        return mplew.getPacket();
    }

    public static byte[] sendJobup(boolean family, int jobid, String name) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_NotifyJobChange.getValue());
        mplew.write(family ? 1 : 0);
        mplew.writeInt(jobid);
        mplew.writeMapleAsciiString(name);
        return mplew.getPacket();
    }

    public static byte[] showDragonFly(int chrId, int type, int mountId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_DragonGlide.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(type);
        if (type == 0) {
            mplew.writeInt(mountId);
        }

        return mplew.getPacket();
    }

    public static byte[] temporaryStats_Aran() {
        Map<MapleStat.Temp, Integer> stats = new EnumMap(MapleStat.Temp.class);
        stats.put(Temp.力量, 999);
        stats.put(Temp.敏捷, 999);
        stats.put(Temp.智力, 999);
        stats.put(Temp.幸運, 999);
        stats.put(Temp.物攻, 255);
        stats.put(Temp.命中, 999);
        stats.put(Temp.迴避, 999);
        stats.put(Temp.速度, 140);
        stats.put(Temp.跳躍, 120);
        return temporaryStats(stats);
    }

    public static byte[] temporaryStats_Balrog(MapleCharacter chr) {
        Map<MapleStat.Temp, Integer> stats = new EnumMap(MapleStat.Temp.class);
        int offset = 1 + (chr.getLevel() - 90) / 20;
        stats.put(Temp.力量, chr.getStat().getTotalStr() / offset);
        stats.put(Temp.敏捷, chr.getStat().getTotalDex() / offset);
        stats.put(Temp.智力, chr.getStat().getTotalInt() / offset);
        stats.put(Temp.幸運, chr.getStat().getTotalLuk() / offset);
        stats.put(Temp.物攻, chr.getStat().getTotalWatk() / offset);
        stats.put(Temp.物防, chr.getStat().getTotalMagic() / offset);
        return temporaryStats(stats);
    }

    public static byte[] temporaryStats(Map<MapleStat.Temp, Integer> mystats) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ForcedStatSet.getValue());
        int updateMask = 0;

        MapleStat.Temp statupdate;
        for(Iterator var3 = mystats.keySet().iterator(); var3.hasNext(); updateMask |= statupdate.getValue()) {
            statupdate = (MapleStat.Temp)var3.next();
        }

        mplew.writeInt(updateMask);
        Iterator var7 = mystats.entrySet().iterator();

        while(var7.hasNext()) {
            Map.Entry<MapleStat.Temp, Integer> statupdateMapEntry = (Map.Entry)var7.next();

            Integer value = ((MapleStat.Temp)statupdateMapEntry.getKey()).getValue();
            if (value >= 1) {
                if (value <= 512) {
                    mplew.writeShort(((Integer)statupdateMapEntry.getValue()).shortValue());
                } else {
                    mplew.write(((Integer)statupdateMapEntry.getValue()).byteValue());
                }
            }
        }

        return mplew.getPacket();
    }

    public static byte[] temporaryStats_Reset() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ForcedStatReset.getValue());
        return mplew.getPacket();
    }

    public static byte[] sendLinkSkillWindow(int skillId) {
        return UIPacket.sendUIWindow(3, skillId);
    }

    public static byte[] sendPartyWindow(int npc) {
        return UIPacket.sendUIWindow(21, npc);
    }

    public static byte[] sendRepairWindow(int npc) {
        return UIPacket.sendUIWindow(33, npc);
    }

    public static byte[] sendProfessionWindow(int npc) {
        return UIPacket.sendUIWindow(42, npc);
    }

    public static byte[] sendRedLeaf(int points, boolean viewonly) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(10);
        mplew.writeShort(OutHeader.LP_UserOpenUIWithOption.getValue());
        mplew.writeInt(115);
        mplew.writeInt(points);
        mplew.writeInt(viewonly ? 1 : 0);
        return mplew.getPacket();
    }

    public static byte[] sendPVPMaps() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_PvPStatusResult.getValue());
        mplew.write(1);
        mplew.writeInt(0);

        int i;
        for(i = 0; i < 3; ++i) {
            mplew.writeInt(1);
        }

        mplew.writeLong(0L);

        for(i = 0; i < 3; ++i) {
            mplew.writeInt(1);
        }

        mplew.writeLong(0L);

        for(i = 0; i < 4; ++i) {
            mplew.writeInt(1);
        }

        for(i = 0; i < 10; ++i) {
            mplew.writeInt(1);
        }

        mplew.writeInt(14);
        mplew.writeShort(100);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] sendPyramidUpdate(int amount) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.PYRAMID_UPDATE.getValue());
        mplew.writeInt(amount);
        return mplew.getPacket();
    }

    public static byte[] sendPyramidResult(byte rank, int amount) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.PYRAMID_RESULT.getValue());
        mplew.write(rank);
        mplew.writeInt(amount);
        return mplew.getPacket();
    }

    public static byte[] sendPyramidEnergy(String type, String amount) {
        return sendString(1, type, amount);
    }

    public static byte[] sendString(int type, String object, String amount) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        switch (type) {
            case 1 -> mplew.writeShort(OutHeader.ENERGY.getValue());
            case 2 -> mplew.writeShort(OutHeader.GHOST_POINT.getValue());
            case 3 -> mplew.writeShort(OutHeader.GHOST_STATUS.getValue());
        }

        mplew.writeMapleAsciiString(object);
        mplew.writeMapleAsciiString(amount);
        return mplew.getPacket();
    }

    public static byte[] sendGhostPoint(String type, String amount) {
        return sendString(2, type, amount);
    }

    public static byte[] sendGhostStatus(String type, String amount) {
        return sendString(3, type, amount);
    }

    public static byte[] MulungEnergy(int energy) {
        return sendPyramidEnergy("energy", String.valueOf(energy));
    }

    public static byte[] showEventInstructions() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_Desc.getValue());
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] getOwlOpen() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ShopScannerResult.getValue());
        mplew.write(10);
        List<Integer> owlItems = RankingWorker.getItemSearch();
        mplew.write(owlItems.size());
        Iterator var2 = owlItems.iterator();

        while(var2.hasNext()) {
            int i = (Integer)var2.next();
            mplew.writeInt(i);
        }

        return mplew.getPacket();
    }

    public static byte[] getOwlSearched(int itemSearch, List<HiredMerchant> hms) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ShopScannerResult.getValue());
        mplew.write(9);
        mplew.writeInt(0);
        mplew.writeShort(0);
        mplew.writeInt(itemSearch);
        int size = 0;

        Iterator var4;
        HiredMerchant hm;
        for(var4 = hms.iterator(); var4.hasNext(); size += hm.searchItem(itemSearch).size()) {
            hm = (HiredMerchant)var4.next();
        }

        mplew.writeInt(size);
        var4 = hms.iterator();

        while(var4.hasNext()) {
            hm = (HiredMerchant)var4.next();
            List<MaplePlayerShopItem> items = hm.searchItem(itemSearch);
            Iterator var7 = items.iterator();

            while(var7.hasNext()) {
                MaplePlayerShopItem item = (MaplePlayerShopItem)var7.next();
                mplew.writeMapleAsciiString(hm.getOwnerName());
                mplew.writeInt(hm.getMap().getId());
                mplew.writeMapleAsciiString(hm.getDescription());
                mplew.writeInt(item.item.getQuantity());
                mplew.writeInt(item.bundles);
                mplew.writeLong(item.price);
                switch (1) {
                    case 0 -> mplew.writeInt(hm.getOwnerId());
                    case 1 -> mplew.writeInt(hm.getStoreId());
                    default -> mplew.writeInt(hm.getObjectId());
                }

                mplew.write(hm.getChannel() - 1);
                mplew.write(ItemConstants.getInventoryType(itemSearch, false).getType());
                if (ItemConstants.getInventoryType(itemSearch, false) == MapleInventoryType.EQUIP) {
                    PacketHelper.GW_ItemSlotBase_Encode(mplew, item.item);
                }
            }
        }

        return mplew.getPacket();
    }

    public static byte[] getRPSMode(byte mode, int mesos, int selection, int answer) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RPSGame.getValue());
        mplew.write(mode);
        switch (mode) {
            case 6:
                if (mesos != -1) {
                    mplew.writeInt(mesos);
                }
                break;
            case 8:
                mplew.writeInt(9000019);
                break;
            case 11:
                mplew.write(selection);
                mplew.write(answer);
        }

        return mplew.getPacket();
    }

    public static byte[] followRequest(int chrid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.FOLLOW_REQUEST.getValue());
        mplew.writeInt(chrid);
        return mplew.getPacket();
    }

    public static byte[] followEffect(int initiator, int replier, Point toMap) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserFollowCharacter.getValue());
        mplew.writeInt(initiator);
        mplew.writeInt(replier);
        if (replier == 0) {
            mplew.write(toMap == null ? 0 : 1);
            if (toMap != null) {
                mplew.writeInt(toMap.x);
                mplew.writeInt(toMap.y);
            }
        }

        return mplew.getPacket();
    }

    public static byte[] getFollowMsg(int opcode) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserFollowCharacterFailed.getValue());
        mplew.writeLong((long)opcode);
        return mplew.getPacket();
    }

    public static byte[] moveFollow(int gatherDuration, int nVal1, Point otherStart, Point myStart, Point otherEnd, List<LifeMovementFragment> moves) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserPassiveMove.getValue());
        PacketHelper.serializeMovementList(mplew, gatherDuration, nVal1, otherStart, myStart, moves, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, (byte)((int)otherEnd.getX() & 255), (byte)((int)otherEnd.getX() >>> 8 & 255), (byte)((int)otherEnd.getY() & 255), (byte)((int)otherEnd.getY() >>> 8 & 255), (byte)((int)otherStart.getX() & 255), (byte)((int)otherStart.getX() >>> 8 & 255), (byte)((int)otherStart.getY() & 255), (byte)((int)otherStart.getY() >>> 8 & 255)});
        return mplew.getPacket();
    }

    public static byte[] getFollowMessage(String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserChatMsg.getValue());
        mplew.writeShort(11);
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }

    public static byte[] getMovingPlatforms(MapleMap map) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_MobOrderFromSvr.getValue());
        mplew.writeInt(map.getPlatforms().size());
        Iterator var2 = map.getPlatforms().iterator();

        while(var2.hasNext()) {
            MapleNodes.MaplePlatform mp = (MapleNodes.MaplePlatform)var2.next();
            mplew.writeMapleAsciiString(mp.name);
            mplew.writeInt(mp.start);
            mplew.writeInt(mp.SN.size());

            for(int x = 0; x < mp.SN.size(); ++x) {
                mplew.writeInt((Integer)mp.SN.get(x));
            }

            mplew.writeInt(mp.speed);
            mplew.writeInt(mp.x1);
            mplew.writeInt(mp.x2);
            mplew.writeInt(mp.y1);
            mplew.writeInt(mp.y2);
            mplew.writeInt(mp.x1);
            mplew.writeInt(mp.y1);
            mplew.writeShort(mp.r);
        }

        return mplew.getPacket();
    }

    public static byte[] sendEngagementRequest(String name, int chrId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.ENGAGE_REQUEST.getValue());
        mplew.write(0);
        mplew.writeMapleAsciiString(name);
        mplew.writeInt(chrId);
        return mplew.getPacket();
    }

    public static byte[] sendEngagement(byte msg, int item, MapleCharacter male, MapleCharacter female) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.ENGAGE_RESULT.getValue());
        mplew.write(msg);
        switch (msg) {
            case 13:
            case 14:
            case 20:
                mplew.writeInt(0);
                mplew.writeInt(male.getId());
                mplew.writeInt(female.getId());
                mplew.writeShort(msg == 14 ? 3 : 1);
                mplew.writeInt(item);
                mplew.writeInt(item);
                mplew.writeAsciiString(male.getName(), 15);
                mplew.writeAsciiString(female.getName(), 15);
            case 15:
            case 16:
            case 18:
            case 19:
            default:
                break;
            case 17:
                mplew.writeMapleAsciiString(male.getName());
                mplew.writeMapleAsciiString(female.getName());
                mplew.writeShort(0);
        }

        return mplew.getPacket();
    }

    public static byte[] updateJaguar(MapleCharacter from) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_WildHunterInfo.getValue());
        PacketHelper.addJaguarInfo(mplew, from);
        return mplew.getPacket();
    }

    public static byte[] teslaTriangle(int chrId, int sum1, int sum2, int sum3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserTeslaTriangle.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(sum1);
        mplew.writeInt(sum2);
        mplew.writeInt(sum3);
        return mplew.getPacket();
    }

    public static byte[] spawnMechDoor(MechDoor md, boolean animated) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_OpenGateCreated.getValue());
        mplew.write(animated ? 0 : 1);
        mplew.writeInt(md.getOwnerId());
        mplew.writePos(md.getPosition());
        mplew.write(md.getId());
        mplew.writeInt(md.getPartyId());
        return mplew.getPacket();
    }

    public static byte[] removeMechDoor(MechDoor md, boolean animated) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_OpenGateRemoved.getValue());
        mplew.write(animated ? 0 : 1);
        mplew.writeInt(md.getOwnerId());
        mplew.write(md.getId());
        return mplew.getPacket();
    }

    public static byte[] useSPReset(int chrId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SkillResetItemResult.getValue());
        mplew.write(1);
        mplew.writeInt(chrId);
        mplew.write(1);
        return mplew.getPacket();
    }

    public static byte[] useAPReset(int chrId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_AbilityResetItemResult.getValue());
        mplew.write(1);
        mplew.writeInt(chrId);
        mplew.write(1);
        return mplew.getPacket();
    }

    public static byte[] report(int err) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.REPORT_RESULT.getValue());
        mplew.write(err);
        if (err == 2) {
            mplew.write(0);
            mplew.writeInt(1);
        }

        return mplew.getPacket();
    }

    public static byte[] getClock(int timesend) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(2);
        mplew.writeInt(timesend);
        return mplew.getPacket();
    }

    public static byte[] getClockTime(int hour, int min, int sec) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(1);
        mplew.write(hour);
        mplew.write(min);
        mplew.write(sec);
        return mplew.getPacket();
    }

    public static byte[] getClock3(int n, int n2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(3);
        mplew.write(1);
        mplew.writeInt(n2);
        return mplew.getPacket();
    }

    public static byte[] getClock40(int n, int n2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(40);
        mplew.writeInt(n2);
        mplew.writeInt(n);
        return mplew.getPacket();
    }

    public static byte[] setClockPause(boolean pause, int duration) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(7);
        mplew.write(0);
        mplew.writeInt(duration);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] getClockMillis(int millis) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(6);
        mplew.writeInt(millis);
        return mplew.getPacket();
    }

    public static byte[] StartClockEvent(int passedSec, int durationSec) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(7);
        mplew.write(0);
        mplew.writeInt(durationSec - passedSec);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] getClockGiantBoss(int duration, int leftTime) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_Event_Field_Timer.getValue());
        mplew.write(103);
        mplew.writeInt(duration);
        mplew.writeInt(leftTime);
        return mplew.getPacket();
    }

    public static byte[] enableReport() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ClaimSvrStatusChanged.getValue());
        mplew.write(false);
        return mplew.getPacket();
    }

    public static byte[] reportResponse() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ClaimSvrStatusChanged_unk.getValue());
        mplew.write(true);
        return mplew.getPacket();
    }

    public static byte[] pamSongUI() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.PAM_SONG.getValue());
        return mplew.getPacket();
    }

    public static byte[] showTraitGain(MapleTraitType trait, int amount) {
        MessageOption option = new MessageOption();
        MapleTraitType[] traitTypes = MapleTraitType.values();
        int[] data = new int[traitTypes.length];
        MapleTraitType[] var5 = traitTypes;
        int var6 = traitTypes.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            MapleTraitType traitType = var5[var7];
            data[traitType.ordinal()] = traitType == trait ? amount : 0;
        }

        option.setIntegerData(data);
        return CWvsContext.sendMessage(19, option);
    }

    public static byte[] showTraitMaxed(MapleTraitType trait) {
        MessageOption option = new MessageOption();
        option.setMask(trait.getStat().getValue());
        return CWvsContext.sendMessage(21, option);
    }

    public static byte[] harvestMessage(int oid, MapleEnumClass.HarvestMsg msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_GatherRequestResult.getValue());
        mplew.writeInt(oid);
        mplew.writeInt(msg.getCode());
        return mplew.getPacket();
    }

    public static byte[] showHarvesting(int chrId, int tool) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_GatherActionSet.getValue());
        mplew.writeInt(chrId);
        mplew.write(tool > 0 ? 1 : 0);
        if (tool > 0) {
            mplew.writeInt(tool);
            mplew.writeInt(0);
        }

        return mplew.getPacket();
    }

    public static byte[] harvestResult(int chrId, boolean success) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserGatherResult.getValue());
        mplew.writeInt(chrId);
        mplew.write(success ? 1 : 0);
        return mplew.getPacket();
    }

    public static byte[] makeExtractor(int chrId, String cname, Point pos, int timeLeft, int itemId, int fee) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_DecomposerEnterField.getValue());
        mplew.writeInt(chrId);
        mplew.writeMapleAsciiString(cname);
        mplew.writeInt(pos.x);
        mplew.writeInt(pos.y);
        mplew.writeShort(timeLeft);
        mplew.writeInt(itemId);
        mplew.writeInt(fee);
        return mplew.getPacket();
    }

    public static byte[] removeExtractor(int chrId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_DecomposerLeaveField.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(1);
        return mplew.getPacket();
    }

    public static byte[] spouseMessage(UserChatMessageType getType, String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserChatMsg.getValue());
        mplew.writeShort(getType.getType());
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }

    public static byte[] multiLineMessage(UserChatMessageType type, String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSetUtilDlg.getValue());
        mplew.writeShort(type.getType());
        mplew.writeMapleAsciiString(type.getMsg(msg));
        return mplew.getPacket();
    }

    public static byte[] openBag(int index, int itemId, boolean firstTime) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserBagItemUseResult.getValue());
        mplew.writeInt(index);
        mplew.writeInt(itemId);
        mplew.writeShort(firstTime ? 1 : 0);
        return mplew.getPacket();
    }

    public static byte[] craftMake(int chrId, int something, int time) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSetOneTimeAction.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(something);
        mplew.writeInt(time);
        return mplew.getPacket();
    }

    public static byte[] craftFinished(int chrId, int craftID, int craftType, int ranking, int itemId, int quantity, int exp) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserMakingSkillResult.getValue());
        mplew.writeInt(chrId);
        mplew.writeInt(craftType);
        if (craftType == 1) {
            mplew.writeInt(ranking);
            mplew.writeBool(true);
            int n = 1;
            mplew.writeInt(n);

            while(n > 0) {
                mplew.writeInt(craftID);
                mplew.writeInt(itemId);
                mplew.writeInt(quantity);
                mplew.writeInt(0);
                mplew.writeInt(0);
                --n;
            }

            mplew.writeInt(exp);
        } else if (craftType == 2) {
            mplew.writeInt(craftID);
            mplew.writeInt(ranking);
            boolean success = ranking == 25 || ranking == 26 || ranking == 27;
            mplew.writeBool(success);
            if (success) {
                mplew.writeInt(itemId);
                mplew.writeInt(quantity);
            }

            mplew.writeInt(exp);
        }

        return mplew.getPacket();
    }

    public static byte[] craftMessage(String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserNoticeMsg.getValue());
        mplew.writeShort(18);
        mplew.writeAsciiString(msg);
        mplew.write(1);
        return mplew.getPacket();
    }

    public static byte[] showItemSkillSocketUpgradeEffect(int cid, boolean result) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserItemSkillSocketUpgradeEffect.getValue());
        mplew.writeInt(cid);
        mplew.writeBool(result);
        return mplew.getPacket();
    }

    public static byte[] showItemSkillOptionUpgradeEffect(int cid, boolean result, boolean destroyed, int itemID, short option) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserItemSkillOptionUpgradeEffect.getValue());
        mplew.writeInt(cid);
        mplew.writeBool(result);
        mplew.writeBool(destroyed);
        mplew.writeInt(itemID);
        mplew.writeInt(option);
        return mplew.getPacket();
    }

    public static byte[] shopDiscount(int percent) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetPotionDiscountRate.getValue());
        mplew.write(percent);
        return mplew.getPacket();
    }

    public static byte[] pendantSlot(boolean p) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetBuyEquipExt.getValue());
        mplew.write(p ? 1 : 0);
        return mplew.getPacket();
    }

    public static byte[] updatePendantSlot(boolean add, int days) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_PendantSlotIncResult.getValue());
        mplew.writeInt(add ? 1 : 0);
        mplew.writeInt(days);
        return mplew.getPacket();
    }

    public static byte[] getBuffBar(long millis) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_NotifyHPDecByField.getValue());
        mplew.writeLong(millis);
        return mplew.getPacket();
    }

    public static byte[] updateGender(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UPDATE_GENDER.getValue());
        mplew.write(chr.getGender());
        return mplew.getPacket();
    }

    public static byte[] achievementRatio(int amount) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetAchieveRate.getValue());
        mplew.writeInt(amount);
        return mplew.getPacket();
    }

    public static byte[] updateSpecialStat(String stat, int array, int mode, boolean unk, int chance) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ResultInstanceTable.getValue());
        mplew.writeMapleAsciiString(stat);
        mplew.writeInt(array);
        mplew.writeInt(mode);
        mplew.write(unk ? 1 : 0);
        mplew.writeInt(chance);
        return mplew.getPacket();
    }

    public static byte[] getQuickSlot(MapleQuickSlot quickslot) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_QuickslotMappedInit.getValue());
        quickslot.writeData(mplew);
        return mplew.getPacket();
    }

    public static byte[] updateImp(MapleImp imp, int mask, int index, boolean login) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ItemPotChange.getValue());
        mplew.write(login ? 0 : 1);
        mplew.writeInt(index + 1);
        mplew.writeInt(mask);
        if ((mask & ImpFlag.SUMMONED.getValue()) != 0) {
            Pair<Integer, Integer> i = MapleItemInformationProvider.getInstance().getPot(imp.getItemId());
            if (i == null) {
                return new byte[0];
            }

            mplew.writeInt((Integer)i.left);
            mplew.write(imp.getLevel());
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.STATE.getValue()) != 0) {
            mplew.write(imp.getState());
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.FULLNESS.getValue()) != 0) {
            mplew.writeInt(imp.getFullness());
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.CLOSENESS.getValue()) != 0) {
            mplew.writeInt(imp.getCloseness());
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.CLOSENESS_LEFT.getValue()) != 0) {
            mplew.writeInt(1);
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.MINUTES_LEFT.getValue()) != 0) {
            mplew.writeInt(0);
            mplew.writeLong(0L);
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.LEVEL.getValue()) != 0) {
            mplew.write(1);
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.FULLNESS_2.getValue()) != 0) {
            mplew.writeInt(imp.getFullness());
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.UPDATE_TIME.getValue()) != 0) {
            mplew.writeLong(PacketHelper.getTime(System.currentTimeMillis()));
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.CREATE_TIME.getValue()) != 0) {
            mplew.writeLong(PacketHelper.getTime(System.currentTimeMillis()));
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.AWAKE_TIME.getValue()) != 0) {
            mplew.writeLong(PacketHelper.getTime(System.currentTimeMillis()));
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.SLEEP_TIME.getValue()) != 0) {
            mplew.writeLong(PacketHelper.getTime(System.currentTimeMillis()));
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.MAX_CLOSENESS.getValue()) != 0) {
            mplew.writeInt(100);
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.MAX_DELAY.getValue()) != 0) {
            mplew.writeInt(1000);
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.MAX_FULLNESS.getValue()) != 0) {
            mplew.writeInt(1000);
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.MAX_ALIVE.getValue()) != 0) {
            mplew.writeInt(1);
        }

        if ((mask & ImpFlag.SUMMONED.getValue()) != 0 || (mask & ImpFlag.MAX_MINUTES.getValue()) != 0) {
            mplew.writeInt(10);
        }

        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] showStatusMessage(String info, String data) {
        MessageOption option = new MessageOption();
        option.setText(info);
        option.setText2(data);
        return CWvsContext.sendMessage(26, option);
    }

    public static byte[] changeTeam(int cid, int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserPvPTeamChanged.getValue());
        mplew.writeInt(cid);
        mplew.write(type);
        return mplew.getPacket();
    }

    public static byte[] setQuickMoveInfo(List<MapleQuickMove> quickMoves) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetQuickMoveInfo.getValue());
        if (quickMoves.size() <= 0) {
            mplew.write(0);
        } else {
            mplew.write(quickMoves.size());
            int i = 0;
            Iterator var3 = quickMoves.iterator();

            while(var3.hasNext()) {
                MapleQuickMove mqm = (MapleQuickMove)var3.next();
                mplew.writeInt(i++);
                mplew.writeInt(mqm.CLOSE_AFTER_CLICK ? 0 : mqm.NPC);
                mplew.writeInt(mqm.VALUE);
                mplew.writeInt(mqm.MIN_LEVEL);
                mplew.writeMapleAsciiString(mqm.DESC);
                mplew.writeInt(0);
                mplew.writeMapleAsciiString("");
                mplew.writeLong(PacketHelper.getTime(-2L));
                mplew.writeLong(PacketHelper.getTime(-1L));
            }
        }

        return mplew.getPacket();
    }

    public static byte[] updateCardStack(int total) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_IncJudgementStack.getValue());
        mplew.write(1);
        mplew.write(total);
        return mplew.getPacket();
    }

    public static byte[] 美洲豹攻擊效果(int skillid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_JaguarSkill.getValue());
        mplew.writeInt(skillid);
        return mplew.getPacket();
    }

    public static byte[] openPantherAttack(boolean on) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_JaguarActive.getValue());
        mplew.writeBool(on);
        return mplew.getPacket();
    }

    public static byte[] showRedNotice(String msg) {
        MessageOption option = new MessageOption();
        option.setText(msg);
        return CWvsContext.sendMessage(11, option);
    }

    public static byte[] monsterBookMessage(String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ScriptProgressMessage.getValue());
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }

    public static byte[] sendloginSuccess() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LOGIN_SUCC.getValue());
        return mplew.getPacket();
    }

    public static byte[] showCharCash(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetMaplePoint.getValue());
        mplew.writeInt(chr.getCSPoints(2));
        return mplew.getPacket();
    }

    public static byte[] showMiracleTime() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetMiracleTime.getValue());
        long time = System.currentTimeMillis();
        mplew.writeLong(PacketHelper.getTime(time));
        mplew.writeLong(PacketHelper.getTime(time + 70000L));
        mplew.writeInt(200);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeMapleAsciiString("");
        mplew.writeMapleAsciiString("夢幻方塊時間到了！！ 從下午4點到6點期間，只要使用商城方塊類商品的話，即會提升道具潛在能力值等級的機率唷！詳細內容請觀看官網公告。");
        return mplew.getPacket();
    }

    public static byte[] showPlayerCash(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SHOW_PLAYER_CASH.getValue());
        mplew.writeInt(chr.getCSPoints(1));
        mplew.writeInt(chr.getCSPoints(2));
        return mplew.getPacket();
    }

    public static byte[] playerCashUpdate(int mode, int toCharge, MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.PLAYER_CASH_UPDATE.getValue());
        mplew.writeInt(mode);
        mplew.writeInt(toCharge == 1 ? chr.getCSPoints(1) : 0);
        mplew.writeInt(chr.getCSPoints(2));
        mplew.write(toCharge);
        mplew.write(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] playerSoltUpdate(int itemid, int acash, int mpoints) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SHOW_CHARSOLE.getValue());
        mplew.writeInt(itemid);
        mplew.writeInt(acash);
        mplew.writeInt(mpoints);
        mplew.write(1);
        mplew.writeShort(0);
        return mplew.getPacket();
    }

    public static byte[] sendTestPacket(String test) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.write(HexTool.getByteArrayFromHexString(test));
        return mplew.getPacket();
    }

    public static byte[] UpdateLinkSkillResult(int skillId, int mode) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UPDATE_LINKSKILL_RESULT.getValue());
        mplew.writeInt(skillId);
        mplew.writeInt(mode);
        return mplew.getPacket();
    }

    public static final byte[] DeleteLinkSkillResult(Map<Integer, Integer> map) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.DELETE_LINKSKILL_RESULT.getValue());
        mplew.writeInt(map.size());
        Iterator var2 = map.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry)var2.next();
            mplew.writeInt((Integer)entry.getKey());
            mplew.writeInt((Integer)entry.getValue());
        }

        return mplew.getPacket();
    }

    public static final byte[] SetLinkSkillResult(int skillId, Pair<Integer, SkillEntry> skillinfo, int linkSkillId, int linkSkillLevel) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SET_LINKSKILL_RESULT.getValue());
        PacketHelper.writeSonOfLinkedSkill(mplew, skillId, skillinfo);
        mplew.writeInt(linkSkillId);
        if (linkSkillId > 0) {
            mplew.writeInt(linkSkillLevel);
        }

        return mplew.getPacket();
    }

    public static byte[] getDojangRanking() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_DojangRanking.getValue());
        mplew.write(0);
        mplew.writeInt(239);
        List<Integer> list = Arrays.asList(0, 1, 2, 8);
        mplew.writeInt(list.size());
        Iterator var2 = list.iterator();

        int i;
        while(var2.hasNext()) {
            i = (Integer)var2.next();
            mplew.write(i);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(i == 1 ? -1 : 0);
            mplew.writeInt(i == 1 ? -1 : 0);
            mplew.writeInt(i == 1 ? -1 : 0);
            mplew.writeInt(i == 1 ? -1 : 101);
            mplew.writeInt(i == 1 ? -1 : 0);
            mplew.writeInt(i == 1 ? -1 : 101);
        }

        mplew.writeInt(list.size());
        var2 = list.iterator();

        while(var2.hasNext()) {
            i = (Integer)var2.next();
            encodeDojangRanking(mplew, i, Collections.emptyList());
        }

        return mplew.getPacket();
    }

    private static void encodeDojangRanking(MaplePacketLittleEndianWriter p, int i, List<AvatarLook> looks) {
        p.write(i);
        p.writeInt(looks.size());

        for(int n = 0; n < looks.size(); ++n) {
            p.writeInt(((AvatarLook)looks.get(n)).getJob());
            p.writeInt(1);
            p.writeInt(1400);
            p.writeInt(n + 1);
            p.writeMapleAsciiString("");
            p.write(1);
            p.write(((AvatarLook)looks.get(n)).getPackedCharacterLook());
        }

    }

    public static byte[] getMulungMessage(boolean dc, String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.MULUNG_MESSAGE.getValue());
        mplew.write(dc ? 1 : 0);
        mplew.writeMapleAsciiString(msg);
        return mplew.getPacket();
    }

    public static byte[] confirmCrossHunter(byte code) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_CrossHunterCompleteResult.getValue());
        mplew.write(code);
        return mplew.getPacket();
    }

    public static byte[] openWeb(byte nValue1, byte nValue2, String web) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserOpenURL.getValue());
        mplew.write(nValue1);
        mplew.write(nValue2);
        mplew.writeMapleAsciiString(web);
        return mplew.getPacket();
    }

    public static byte[] openWebUI(int n, String sUOL, String sURL) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserOpenWebUI.getValue());
        mplew.writeInt(n);
        mplew.writeMapleAsciiString(sUOL);
        mplew.writeMapleAsciiString(sURL);
        return mplew.getPacket();
    }

    public static byte[] updateInnerSkill(MapleCharacter player, byte statPach, InnerSkillEntry ise, InnerSkillEntry ise2, InnerSkillEntry ise3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_CharacterPotentialSet.getValue());
        mplew.write(1);
        mplew.write(statPach);
        mplew.writeShort(3);
        int skillId1 = getRandomSkillIdByRank(ise.getRank());
        int skillId2 = getRandomSkillIdByRank(ise2.getRank());
        int skillId3 = getRandomSkillIdByRank(ise3.getRank());
        int skillLevel1 = writeSkillEntry(mplew, ise, skillId1, ise.getPosition());
        int skillLevel2 = writeSkillEntry(mplew, ise2, skillId2, ise2.getPosition());
        int skillLevel3 = writeSkillEntry(mplew, ise3, skillId3, ise3.getPosition());

        try {
            Connection con = DatabaseConnectionEx.getConnection();

            try {
                PreparedStatement ps;
                if (statPach == 0) {
                    ps = con.prepareStatement("DELETE FROM innerskills WHERE characterid = ? AND position IN (1, 2, 3)");

                    try {
                        ps.setInt(1, player.getId());
                        ps.executeUpdate();
                    } catch (Throwable var28) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var22) {
                                var28.addSuppressed(var22);
                            }
                        }

                        throw var28;
                    }

                    if (ps != null) {
                        ps.close();
                    }

                    ps = con.prepareStatement("INSERT INTO innerskills (skillid, characterid, skilllevel, position, rank) VALUES (?,?,?,?,?)");

                    try {
                        ps.setInt(1, skillId1);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel1);
                        ps.setInt(4, ise.getPosition());
                        ps.setInt(5, ise.getRank());
                        ps.addBatch();
                        ps.setInt(1, skillId2);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel2);
                        ps.setInt(4, ise2.getPosition());
                        ps.setInt(5, ise2.getRank());
                        ps.addBatch();
                        ps.setInt(1, skillId3);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel3);
                        ps.setInt(4, ise3.getPosition());
                        ps.setInt(5, ise3.getRank());
                        ps.addBatch();
                        ps.executeBatch();
                    } catch (Throwable var27) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var21) {
                                var27.addSuppressed(var21);
                            }
                        }

                        throw var27;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                }

                if (statPach == 1) {
                    ps = con.prepareStatement("DELETE FROM innerskills_2 WHERE characterid = ? AND position IN (1, 2, 3)");

                    try {
                        ps.setInt(1, player.getId());
                        ps.executeUpdate();
                    } catch (Throwable var26) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var20) {
                                var26.addSuppressed(var20);
                            }
                        }

                        throw var26;
                    }

                    if (ps != null) {
                        ps.close();
                    }

                    ps = con.prepareStatement("INSERT INTO innerskills_2 (skillid, characterid, skilllevel, position, rank) VALUES (?,?,?,?,?)");

                    try {
                        ps.setInt(1, skillId1);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel1);
                        ps.setInt(4, ise.getPosition());
                        ps.setInt(5, ise.getRank());
                        ps.addBatch();
                        ps.setInt(1, skillId2);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel2);
                        ps.setInt(4, ise2.getPosition());
                        ps.setInt(5, ise2.getRank());
                        ps.addBatch();
                        ps.setInt(1, skillId3);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel3);
                        ps.setInt(4, ise3.getPosition());
                        ps.setInt(5, ise3.getRank());
                        ps.addBatch();
                        ps.executeBatch();
                    } catch (Throwable var25) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var19) {
                                var25.addSuppressed(var19);
                            }
                        }

                        throw var25;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                }

                if (statPach == 2) {
                    ps = con.prepareStatement("DELETE FROM innerskills_3 WHERE characterid = ? AND position IN (1, 2, 3)");

                    try {
                        ps.setInt(1, player.getId());
                        ps.executeUpdate();
                    } catch (Throwable var24) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var18) {
                                var24.addSuppressed(var18);
                            }
                        }

                        throw var24;
                    }

                    if (ps != null) {
                        ps.close();
                    }

                    ps = con.prepareStatement("INSERT INTO innerskills_3 (skillid, characterid, skilllevel, position, rank) VALUES (?,?,?,?,?)");

                    try {
                        ps.setInt(1, skillId1);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel1);
                        ps.setInt(4, ise.getPosition());
                        ps.setInt(5, ise.getRank());
                        ps.addBatch();
                        ps.setInt(1, skillId2);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel2);
                        ps.setInt(4, ise2.getPosition());
                        ps.setInt(5, ise2.getRank());
                        ps.addBatch();
                        ps.setInt(1, skillId3);
                        ps.setInt(2, player.getId());
                        ps.setInt(3, skillLevel3);
                        ps.setInt(4, ise3.getPosition());
                        ps.setInt(5, ise3.getRank());
                        ps.addBatch();
                        ps.executeBatch();
                    } catch (Throwable var23) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var17) {
                                var23.addSuppressed(var17);
                            }
                        }

                        throw var23;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Throwable var29) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var16) {
                        var29.addSuppressed(var16);
                    }
                }

                throw var29;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var30) {
            SQLException e = var30;
            e.printStackTrace();
        }

        return mplew.getPacket();
    }

    private static int getRandomSkillIdByRank(int rank) {
        switch (rank) {
            case 0 -> {
                return Randomizer.rand(70000000, 70000013);
            }
            case 1 -> {
                return Randomizer.rand(70000014, 70000026);
            }
            case 2 -> {
                return Randomizer.rand(70000027, 70000039);
            }
            case 3 -> {
                return Randomizer.rand(70000041, 70000062);
            }
            default -> throw new IllegalArgumentException("Unknown rank: " + rank);
        }
    }

    private static int writeSkillEntry(MaplePacketLittleEndianWriter mplew, InnerSkillEntry entry, int skillId, int index) {
        int skillLevel;
        switch (entry.getRank()) {
            case 0 -> skillLevel = Randomizer.rand(1, 20);
            case 1 -> skillLevel = Randomizer.rand(10, 20);
            case 2 -> skillLevel = Randomizer.rand(20, 30);
            case 3 -> skillLevel = Randomizer.rand(30, 40);
            default -> throw new IllegalArgumentException("Unknown rank: " + entry.getRank());
        }

        mplew.write(index);
        mplew.writeInt(skillId);
        mplew.write(skillLevel);
        mplew.write(entry.getRank());
        return skillLevel;
    }

    public static byte[] updateInnerStats(MapleCharacter chr) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_SHOW_CHARACTER_HONOR_POINT.getValue());
        mplew.writeInt(chr.getHonor());
        return mplew.getPacket();
    }

    public static byte[] sendPolice(String text) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.MAPLE_ADMIN.getValue());
        mplew.writeMapleAsciiString(text);
        return mplew.getPacket();
    }

    public static byte[] testPacket(String testmsg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.write(HexTool.getByteArrayFromHexString(testmsg));
        return mplew.getPacket();
    }

    public static byte[] testPacket(byte[] testmsg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.write(testmsg);
        return mplew.getPacket();
    }

    public static byte[] testPacket(String op, String text) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.write(HexTool.getByteArrayFromHexString(op));
        mplew.writeMapleAsciiString(text);
        return mplew.getPacket();
    }

    public static byte[] ResultStealSkillList(int n, MapleCharacter chr, List<Integer> memorySkills) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ResultStealSkillList.getValue());
        mplew.write(1);
        mplew.writeInt(chr.getId());
        mplew.writeInt(n);
        mplew.writeInt(chr.getJob());
        mplew.writeInt(memorySkills.size());
        Iterator var4 = memorySkills.iterator();

        while(var4.hasNext()) {
            int skill = (Integer)var4.next();
            mplew.writeInt(skill);
        }

        return mplew.getPacket();
    }

    public static byte[] 幻影刪除技能(int skillBook, int position) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ChangeStealMemoryResult.getValue());
        mplew.write(1);
        mplew.write(3);
        mplew.writeInt(skillBook);
        mplew.writeInt(position);
        return mplew.getPacket();
    }

    public static byte[] 修改幻影裝備技能(int skillId, int teachId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ResultSetStealSkill.getValue());
        mplew.write(1);
        mplew.write(1);
        mplew.writeInt(skillId);
        mplew.writeInt(teachId);
        return mplew.getPacket();
    }

    public static byte[] 幻影複製錯誤() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ChangeStealMemoryResult.getValue());
        mplew.write(1);
        mplew.write(1);
        return mplew.getPacket();
    }

    public static byte[] 幻影複製技能(int position, int skillId, int level) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ChangeStealMemoryResult.getValue());
        mplew.write(1);
        mplew.write(0);
        if (position < 4) {
            mplew.writeInt(1);
            mplew.writeInt(position);
        } else if (position < 8) {
            mplew.writeInt(2);
            mplew.writeInt(position - 4);
        } else if (position < 11) {
            mplew.writeInt(3);
            mplew.writeInt(position - 8);
        } else if (position < 13) {
            mplew.writeInt(4);
            mplew.writeInt(position - 11);
        } else if (position < 15) {
            mplew.writeInt(5);
            mplew.writeInt(position - 13);
        }

        mplew.writeInt(skillId);
        mplew.writeInt(level);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] CheckTrickOrTreatRequest() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserTrickOrTreatResult.getValue());
        mplew.writeInt(7);
        return mplew.getPacket();
    }

    public static byte[] SystemProcess() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_CheckProcess.getValue());
        mplew.writeHexString("00 00 00 00 01");
        mplew.writeShort(10);
        mplew.writeHexString("B7 AC A4 A7 A8 A6 AC A1 B0 CA 00");
        mplew.writeHexString("00 00 00 00");
        boolean trigger = false;

        try {
            File file = new File("config/EventList.json");
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map jsonMap = (Map)objectMapper.readValue(file, Map.class);

                assert jsonMap.get("eventList") instanceof List;

                List<Map<String, Object>> eventList = (List)jsonMap.get("eventList");
                int count = 0;
                if (!eventList.isEmpty()) {
                    mplew.writeInt(eventList.size());

                    for(Iterator var7 = eventList.iterator(); var7.hasNext(); ++count) {
                        Map<String, Object> eventMap = (Map)var7.next();
                        String title = eventMap.get("name").toString();
                        Charset big5Charset = Charset.forName("Big5");
                        int len = title.getBytes(big5Charset).length;
                        mplew.write(count);
                        mplew.writeHexString("01 00 00");
                        mplew.writeShort(len);
                        mplew.write(title.getBytes(big5Charset));
                        mplew.writeZeroBytes(2);
                        mplew.writeInt(eventMap.get("timeStart") instanceof Number ? ((Number)eventMap.get("timeStart")).intValue() : 0);
                        mplew.writeInt(eventMap.get("timeEnd") instanceof Number ? ((Number)eventMap.get("timeEnd")).intValue() : 0);
                        mplew.writeInt(eventMap.get("dateStart") instanceof Number ? ((Number)eventMap.get("dateStart")).intValue() : 0);
                        mplew.writeInt(eventMap.get("dateEnd") instanceof Number ? ((Number)eventMap.get("dateEnd")).intValue() : 0);
                        mplew.writeHexString("00 00 00 00 00 00 00 00 00 00 00 00");
                        mplew.writeHexString("01");
                        List<Object> rewards = (List)eventMap.get("rewards");
                        if (rewards != null && !rewards.isEmpty()) {
                            mplew.writeInt(rewards.size());
                            Iterator var13 = rewards.iterator();

                            while(var13.hasNext()) {
                                Object reward = var13.next();
                                mplew.writeInt(reward instanceof Number ? ((Number)reward).intValue() : 2000005);
                            }
                        } else {
                            mplew.writeInt(0);
                        }

                        mplew.writeHexString("00 00 00 00 00 00");
                        trigger = true;
                    }
                }
            } else {
                System.out.println("未讀取EventList.json配置, 活動列表將使用預設值");
            }
        } catch (Exception var15) {
            Exception e = var15;
            System.out.println("讀取活動列表配置(EventList.json)錯誤，活動列表將使用預設值。錯誤訊息：" + String.valueOf(e));
        }

        if (!trigger) {
            mplew = new MaplePacketLittleEndianWriter();
            mplew.writeShort(OutHeader.LP_CheckProcess.getValue());
            mplew.writeHexString("00 00 00 00 01");
            mplew.writeShort(10);
            mplew.writeHexString("B7 AC A4 A7 A8 A6 AC A1 B0 CA 00");
            mplew.writeHexString("00 00 00 00");
            mplew.writeInt(1);
            String title = "[TEST] 測試";
            Charset big5Charset = Charset.forName("Big5");
            int len = title.getBytes(big5Charset).length;
            mplew.write(1);
            mplew.writeHexString("01 00 00");
            mplew.writeShort(len);
            mplew.write(title.getBytes(big5Charset));
            mplew.writeZeroBytes(2);
            mplew.writeInt(0);
            mplew.writeInt(235900);
            mplew.writeInt(20240911);
            mplew.writeInt(20240912);
            mplew.writeHexString("00 00 00 00 00 00 00 00 00 00 00 00");
            mplew.writeHexString("01");
            mplew.writeInt(1);
            mplew.writeInt(2000005);
            mplew.writeHexString("00 00 00 00 00 00");
        }

        mplew.writeHexString("1A 00 00 00 7E 01 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 11 00 00 00 C8 00 00 00 E8 17 34 01 E8 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 21 00 00 00 00 26 00 B3 73 C4 F2 C0 BB B1 FE 43 6F 6D 62 6F 6B 69 6C 6C AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 17 00 00 00 02 00 00 00 E8 17 34 01 E8 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 05 00 00 00 00 00 00 00 EF 17 34 01 EF 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 89 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 41 31 30 AC 50 A1 41 31 35 AC 50 23 6B B6 A5 AC 71 A4 57 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 AB 44 AC A1 B0 CA B9 EF B6 48 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 05 00 00 00 04 00 00 00 32 00 00 00 EF 17 34 01 EF 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 00 00 00 00 10 00 00 00 68 01 00 00 F6 17 34 01 F6 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AB F9 C4 F2 AE C9 B6 A1 BC 57 A5 5B 32 AD BF A1 49 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 13 00 00 00 0B 00 00 00 00 00 00 00 F6 17 34 01 F6 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 FD 17 34 01 FD 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE 20 33 30 25 20 A7 E9 A6 A9 96 00 23 65 AC 50 B4 C1 A4 D1 AD 6E B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E 20 AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B 20 B6 4F A5 CE 20 33 30 25 20 A7 E9 A6 A9 23 6E 0D 0A 28 A8 BE C3 7A B6 4F A5 CE A9 4D B4 4C B6 51 B8 CB B3 C6 B7 7C B1 71 AC A1 B0 CA B9 EF B6 48 B0 A3 A5 7E A1 41 4D 56 50 AC 4F A6 62 A4 77 A7 E9 A6 A9 20 33 30 25 A4 57 B0 6C A5 5B AE 4D A5 CE 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1D 00 00 00 02 00 00 00 FD 17 34 01 FD 17 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 A9 3A 34 01 A9 3A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 17 00 00 00 02 00 00 00 A9 3A 34 01 A9 3A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 B0 3A 34 01 B0 3A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 B7 3A 34 01 B7 3A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 B7 3A 34 01 B7 3A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 06 00 00 00 04 00 00 00 32 00 00 00 BE 3A 34 01 BE 3A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 28 00 00 00 01 00 00 00 0A 3B 34 01 0A 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 7F 00 23 65 AC 50 B4 C1 A4 D1 B7 ED B5 4D B4 4E AC 4F B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA 21 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 B1 C6 B0 A3 A6 62 AC A1 B0 CA B9 EF B6 48 A4 A7 A5 7E 29 00 00 13 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 B1 6A A4 C6 31 2B 31 13 00 00 00 0B 00 00 00 00 00 00 00 0A 3B 34 01 0A 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1C 00 A9 C7 AA AB A6 AC C2 C3 B5 6E BF FD B7 73 A9 C7 AA AB BE F7 B2 76 20 32 AD BF A1 49 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 14 00 00 00 1D 00 00 00 02 00 00 00 11 3B 34 01 11 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 B6 F8 B3 4E A4 A7 AA 65 A8 43 A4 E9 BC FA C0 79 32 AD BF A1 49 00 00 00 00 26 00 B3 73 C4 F2 C0 BB B1 FE 43 6F 6D 62 6F 6B 69 6C 6C AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 07 00 00 00 29 00 00 00 81 A3 07 00 18 3B 34 01 18 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 27 00 3F 3F 3F 20 3F 3F 3F 20 3F 3F 2C 20 3F 3F 3F 20 3F 3F 3F 20 3F 3F 20 35 3F 20 3F 3F 20 3F 3F 3F 20 31 3F 20 3F 3F 21 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1B 00 00 00 63 00 00 00 18 3B 34 01 18 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 1F 3B 34 01 1F 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 96 00 23 65 AC 50 B4 C1 A4 D1 AD 6E B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E 20 AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B 20 B6 4F A5 CE 20 33 30 25 20 A7 E9 A6 A9 23 6E 0D 0A 28 A8 BE C3 7A B6 4F A5 CE A9 4D B4 4C B6 51 B8 CB B3 C6 B7 7C B1 71 AC A1 B0 CA B9 EF B6 48 B0 A3 A5 7E A1 41 4D 56 50 AC 4F A6 62 A4 77 A7 E9 A6 A9 20 33 30 25 A4 57 B0 6C A5 5B AE 4D A5 CE 29 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 03 00 00 00 04 00 00 00 32 00 00 00 74 3B 34 01 75 3B 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 8F 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 A1 41 A6 62 23 6B B9 C1 B8 D5 B1 6A A4 C6 AE C9 A1 41 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A A1 5D B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 5E 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 05 00 00 00 00 00 00 00 7B 3B 34 01 7B 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00");
        mplew.writeHexString("FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 89 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 41 31 30 AC 50 A1 41 31 35 AC 50 23 6B B6 A5 AC 71 A4 57 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 AB 44 AC A1 B0 CA B9 EF B6 48 29 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 05 00 00 00 04 00 00 00 32 00 00 00 7B 3B 34 01 7B 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 B0 B6 A4 6A AA BA C6 46 BB EE C0 F2 B1 6F BE F7 B2 76 35 AD BF 13 00 00 00 0B 00 00 00 00 00 00 00 82 3B 34 01 82 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 1A 00 AF 50 BF 56 BE D4 AF 54 B0 68 B3 F5 BC FA C0 79 B8 67 C5 E7 AD C8 A8 E2 AD BF 14 00 00 00 22 00 00 00 C8 00 00 00 89 3B 34 01 89 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 D5 3B 34 01 D5 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 21 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 13 00 00 00 0B 00 00 00 00 00 00 00 D5 3B 34 01 D5 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1C 00 A9 C7 AA AB A6 AC C2 C3 B5 6E BF FD B7 73 A9 C7 AA AB BE F7 B2 76 20 32 AD BF A1 49 00 00 00 00 2A 00 B3 73 C4 F2 C0 BB B1 FE 43 6F 6D 62 6F 6B 69 6C 6C AF 5D A4 6C AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 04 00 00 00 04 00 00 00 00 00 00 00 DC 3B 34 01 DC 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE 20 33 30 25 20 A7 E9 A6 A9 A1 49 96 00 23 65 AC 50 B4 C1 A4 D1 AD 6E B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E 20 AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B 20 B6 4F A5 CE 20 33 30 25 20 A7 E9 A6 A9 23 6E 0D 0A 28 A8 BE C3 7A B6 4F A5 CE A9 4D B4 4C B6 51 B8 CB B3 C6 B7 7C B1 71 AC A1 B0 CA B9 EF B6 48 B0 A3 A5 7E A1 41 4D 56 50 AC 4F A6 62 A4 77 A7 E9 A6 A9 20 33 30 25 A4 57 B0 6C A5 5B AE 4D A5 CE 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 05 00 00 00 04 00 00 00 32 00 00 00 DC 3B 34 01 DC 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1D 00 00 00 02 00 00 00 E3 3B 34 01 E3 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 B6 F8 B3 4E A4 A7 AA 65 A8 43 A4 E9 BC FA C0 79 32 AD BF A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 03 00 00 00 04 00 00 00 32 00 00 00 EA 3B 34 01 EA 3B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 59 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 A7 E9 A1 49 23 6B 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 06 00 00 00 04 00 00 00 32 00 00 00 37 3C 34 01 37 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1F 00 A8 A4 A6 E2 A4 BA BC E7 AD AB B7 73 B3 5D A9 77 B6 4F A5 CE 20 35 30 25 20 A7 E9 A6 A9 A1 49 00 00 00 00 13 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 B1 6A A4 C6 31 2B 31 14 00 00 00 1E 00 00 00 C8 00 00 00 3E 3C 34 01 3E 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 48 00 43 6F 6D 62 6F 20 6B 69 6C 6C 20 B2 79 B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 20 2B 33 30 30 25 A1 49 5C 72 5C 6E 20 AA 69 C3 B9 BB 50 B4 B6 A8 BD A6 AB AA BA BD E0 AA F7 C2 79 A4 48 B8 67 C5 E7 AD C8 20 32 AD BF A1 49 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 00 00 00 00 19 00 00 00 0A 00 00 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 12 00 00 00 C0 27 09 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 13 00 00 00 C0 27 09 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 12 00 5B 3F 3F 3F 3F 5D 20 3F 20 3F 3F 3F 20 3F 3F 20 3F 3F 00 00 00 00 11 00 00 00 C8 00 00 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 26 00 B3 73 C4 F2 C0 BB B1 FE 43 6F 6D 62 6F 6B 69 6C 6C AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 13 00 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 33 30 25 00 00 00 00 0D 00 00 00 06 00 00 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 B0 B6 A4 6A AA BA C6 46 BB EE C0 F2 B1 6F BE F7 B2 76 35 AD BF 00 00 00 00 23 00 00 00 E8 03 00 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 B0 B6 A4 6A AA BA C6 46 BB EE C0 F2 B1 6F BE F7 B2 76 35 AD BF A1 49 00 00 00 00 12 00 5B 3F 3F 3F 3F 5D 20 3F 3F 3F 3F 20 3F 3F 3F 20 3F 3F 14 00 00 00 22 00 00 00 96 00 00 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 5B 3F 3F 3F 3F 5D 20 3F 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 08 00 00 00 21 00 00 00 1E 00 00 00 41 3C 34 01 4E 3C 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 17 00 00 00 02 00 00 00 45 3C 34 01 45 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 03 00 00 00 04 00 00 00 32 00 00 00 9F 3C 34 01 9F 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 59 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 A7 E9 A1 49 23 6B 00 00 26 00 B3 73 C4 F2 C0 BB B1 FE 43 6F 6D 62 6F 6B 69 6C 6C AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 13 00 00 00 0B 00 00 00 00 00 00 00 A6 3C 34 01 A6 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1C 00 A9 C7 AA AB A6 AC C2 C3 B5 6E BF FD B7 73 A9 C7 AA AB BE F7 B2 76 20 32 AD BF A1 49 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 A6 3C 34 01 A6 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 05 00 00 00 04 00 00 00 32 00 00 00 AD 3C 34 01 AD 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 B4 3C 34 01 B4 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 00 00 00 00 01 3D 34 01 01 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 B0 B6 A4 6A AA BA C6 46 BB EE C0 F2 B1 6F BE F7 B2 76 35 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 01 3D 34 01 01 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 06 00 00 00 04 00 00 00 32 00 00 00 08 3D 34 01 08 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1F 00 A8 A4 A6 E2 A4 BA BC E7 AD AB B7 73 B3 5D A9 77 B6 4F A5 CE 20 35 30 25 20 A7 E9 A6 A9 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 0E 00 00 00 2C 01 00 00 0F 3D 34 01 0F 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 00 AC F0 B5 6F A5 F4 B0 C8 A5 69 A7 B9 A6 A8 A6 B8 BC C6 A1 41 B8 67 C5 E7 AD C8 20 33 AD BF A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 0D 00 00 00 09 00 00 00 0F 3D 34 01 0F 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 00 00 00 00 1B 00 00 00 63 00 00 00 0F 3D 34 01 0F 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 24 00 A8 43 20 39 39 20 43 6F 6D 62 6F B7 7C B0 6C A5 5B B2 A3 A5 58 20 43 6F 6D 62 6F 20 6B 69 6C 6C B2 79 A1 49 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 04 00 00 00 28 00 00 00 01 00 00 00 16 3D 34 01 16 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 23 00 A6 62 31 30 AC 50 A5 48 A4 55 A6 A8 A5 5C AC 50 A4 4F B1 6A A4 C6 AE C9 20 31 2B 31 20 B1 6A A4 C6 A1 49 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 17 00 00 00 02 00 00 00 62 3D 34 01 62 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1D 00 00 00 02 00 00 00 62 3D 34 01 62 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 09 00 00 00 00 00 00 00 AF 3C 34 01 65 3D 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 04 00 00 00 0C 00 00 00 E8 03 00 00 0D 00 00 00 E8 03 00 00 0E 00 00 00 E8 03 00 00 0F 00 00 00 E8 03 00 00 00 00 00 00 0E 00 C2 49 BC C6 31 A4 D1 C0 F2 B1 6F 32 AD BF 00 00 00 00 19 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 00 00 00 00 27 00 00 00 C8 00 00 00 B4 3C 34 01 B4 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 27 00 00 00 C8 00 00 00 01 3D 34 01 01 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 27 00 00 00 C8 00 00 00 08 3D 34 01 08 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 00 00 00 00 27 00 00 00 C8 00 00 00 0F 3D 34 01 0F 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 00 00 00 00 27 00 00 00 C8 00 00 00 16 3D 34 01 16 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 00 00 00 00 27 00 00 00 C8 00 00 00 62 3D 34 01 62 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 03 00 00 00 04 00 00 00 32 00 00 00 69 3D 34 01 69 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 59 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 A7 E9 A1 49 23 6B 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 77 3D 34 01 77 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 ");
        mplew.writeHexString("FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 21 00 43 6F 6D 62 6F 20 6B 69 6C 6C 20 B2 79 B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 20 2B 33 30 30 25 A1 49 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 70 3D 34 01 70 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 89 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 41 31 30 AC 50 A1 41 31 35 AC 50 23 6B B6 A5 AC 71 A4 57 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 AB 44 AC A1 B0 CA B9 EF B6 48 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 70 3D 34 01 70 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 26 00 B3 73 C4 F2 C0 BB B1 FE 43 6F 6D 62 6F 6B 69 6C 6C AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 15 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 A1 41 31 2B 31 B1 6A A4 C6 00 00 00 00 11 00 00 00 C8 00 00 00 77 3D 34 01 77 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 21 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 00 00 00 00 0E 00 00 00 2C 01 00 00 CA 3D 34 01 CA 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 00 AC F0 B5 6F A5 F4 B0 C8 A5 69 A7 B9 A6 A8 A6 B8 BC C6 A1 41 B8 67 C5 E7 AD C8 20 33 AD BF A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 0D 00 00 00 09 00 00 00 CA 3D 34 01 CA 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 00 AC F0 B5 6F A5 F4 B0 C8 A5 69 A7 B9 A6 A8 A6 B8 BC C6 A1 41 B8 67 C5 E7 AD C8 20 33 AD BF A1 49 00 00 00 00 1C 00 AC 50 A4 4F 20 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 00 00 00 00 1B 00 00 00 63 00 00 00 D1 3D 34 01 D1 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 24 00 A8 43 20 39 39 20 43 6F 6D 62 6F B7 7C B0 6C A5 5B B2 A3 A5 58 20 43 6F 6D 62 6F 20 6B 69 6C 6C B2 79 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 17 00 00 00 02 00 00 00 D1 3D 34 01 D1 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 05 00 00 00 04 00 00 00 32 00 00 00 D8 3D 34 01 D8 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1F 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 BA EB AD 5E A9 C7 AA AB B5 6E B3 F5 BE F7 B2 76 BC 57 A5 5B 04 00 00 00 04 00 00 00 00 00 00 00 DF 3D 34 01 DF 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE 20 33 30 25 20 A7 E9 A6 A9 96 00 23 65 AC 50 B4 C1 A4 D1 AD 6E B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E 20 AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B 20 B6 4F A5 CE 20 33 30 25 20 A7 E9 A6 A9 23 6E 0D 0A 28 A8 BE C3 7A B6 4F A5 CE A9 4D B4 4C B6 51 B8 CB B3 C6 B7 7C B1 71 AC A1 B0 CA B9 EF B6 48 B0 A3 A5 7E A1 41 4D 56 50 AC 4F A6 62 A4 77 A7 E9 A6 A9 20 33 30 25 A4 57 B0 6C A5 5B AE 4D A5 CE 29 00 00 1D 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 BD FC A6 41 A6 B8 B5 6E B3 F5 AE C9 B6 A1 C1 59 B5 75 14 00 00 00 1D 00 00 00 02 00 00 00 DF 3D 34 01 DF 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 B6 F8 B3 4E A4 A7 AA 65 A8 43 A4 E9 BC FA C0 79 32 AD BF A1 49 00 00 00 00 19 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 BD FC A7 4E AB 6F AE C9 B6 A1 B4 EE A4 D6 03 00 00 00 04 00 00 00 32 00 00 00 2C 3E 34 01 2C 3E 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 59 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 A7 E9 A1 49 23 6B 00 00 1B 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 BD FC B8 67 C5 E7 AD C8 AE C4 AA 47 BC 57 A5 5B 08 00 00 00 21 00 00 00 1E 00 00 00 33 3E 34 01 33 3E 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1F 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 AC F0 B5 6F A5 F4 B0 C8 A8 43 A4 E9 A6 B8 BC C6 BC 57 A5 5B 04 00 00 00 28 00 00 00 01 00 00 00 33 3E 34 01 33 3E 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 7F 00 23 65 AC 50 B4 C1 A4 D1 B7 ED B5 4D B4 4E AC 4F B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA 21 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 B1 C6 B0 A3 A6 62 AC A1 B0 CA B9 EF B6 48 A4 A7 A5 7E 29 00 00 23 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 AA 69 C3 B9 BB 50 B4 B6 A8 BD A6 AB BE F7 B2 76 B6 7D A9 6C B4 EE A4 D6 06 00 00 00 04 00 00 00 32 00 00 00 3A 3E 34 01 3A 3E 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1F 00 A8 A4 A6 E2 A4 BA BC E7 AD AB B7 73 B3 5D A9 77 B6 4F A5 CE 20 35 30 25 20 A7 E9 A6 A9 A1 49 00 00 00 00 1D 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 AF 50 B5 4B BE D4 AF 54 B8 67 C5 E7 AD C8 BC 57 A5 5B 00 00 00 00 10 00 00 00 68 01 00 00 8D 3E 34 01 8E 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 64 65 73 63 20 74 65 73 74 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 8D 3E 34 01 8E 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 64 65 73 63 20 74 65 73 74 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 0D 00 00 00 09 00 00 00 94 3E 34 01 95 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 0C 00 6D 65 73 73 61 67 65 20 74 65 73 74 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 0E 00 00 00 2C 01 00 00 94 3E 34 01 95 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 64 65 73 63 20 74 65 73 74 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 28 00 00 00 01 00 00 00 9B 3E 34 01 9C 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 09 00 64 65 73 63 20 74 65 73 74 48 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 5C 72 5C 6E 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F 31 2B 31 B1 6A A4 C6 A1 49 5C 6E B0 74 B5 6F AF A6 AA 6B B2 C5 A4 E5 33 30 AD D3 A1 49 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 A2 3E 34 01 A3 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 64 65 73 63 20 74 65 73 74 5C 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 5C 72 5C 6E AC 50 A4 4F A7 E9 A6 A9 33 30 25 A1 49 5C 6E BF 55 BF 4E A6 61 B9 CF B3 CC A6 68 31 35 B6 A5 AC 71 2F AB EC B4 5F B3 74 AB D7 B4 A3 A4 C9 2F AB F9 C4 F2 AE C9 B6 A1 BC 57 A5 5B A1 49 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 16 00 00 00 0F 00 00 00 A2 3E 34 01 A3 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 64 65 73 63 20 74 65 73 74 00 00 00 00 15 00 B0 B6 A4 6A AA BA C6 46 BB EE C0 F2 B1 6F BE F7 B2 76 35 AD BF 00 00 00 00 15 00 00 00 80 4F 12 00 A2 3E 34 01 A3 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 64 65 73 63 20 74 65 73 74 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 14 00 00 00 40 77 1B 00 A2 3E 34 01 A3 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 64 65 73 63 20 74 65 73 74 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 03 00 00 00 04 00 00 00 32 00 00 00 A9 3E 34 01 AA 3E 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1E 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 20 2B 33 30 30 25 52 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 5C 72 5C 6E A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE A7 E9 A6 A9 35 30 25 A1 49 5C 6E A9 47 A4 E5 AA BA B2 AA B8 F1 46 45 56 45 52 20 54 49 4D 45 A1 49 00 00 13 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 B1 6A A4 C6 31 2B 31 04 00 00 00 09 00 00 00 00 00 00 00 97 3E 34 01 F1 3E 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 04 00 00 00 0C 00 00 00 E8 03 00 00 0D 00 00 00 E8 03 00 00 0E 00 00 00 E8 03 00 00 0F 00 00 00 E8 03 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 F6 3E 34 01 F6 3E 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 F6 3E 34 01 F6 3E 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 05 00 00 00 00 00 00 00 FD 3E 34 01 FD 3E 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 05 00 00 00 04 00 00 00 32 00 00 00 FD 3E 34 01 FD 3E 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 14 00 00 00 1D 00 00 00 02 00 00 00 0B 3F 34 01 0B 3F 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 03 00 00 00 04 00 00 00 32 00 00 00 B7 61 34 01 B7 61 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1C 00 AC 50 A4 4F 20 35 A1 42 31 30 A1 42 31 35 AC 50 B1 6A A4 C6 A6 A8 A5 5C 31 30 30 25 00 00 00 00 10 00 00 00 68 01 00 00 BE 61 34 01 BE 61 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 BE 61 34 01 BE 61 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 14 00 A5 AC B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 DB A6 76 B0 74 00 00 00 00 19 00 00 00 0A 00 00 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 32 00 AD 59 A6 B3 B4 5B AD 6E B2 BE B0 CA AA BA B2 7B AA F7 B9 44 A8 E3 A1 41 BD D0 A7 51 A5 CE B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 A7 A7 D6 B1 B6 A1 49 00 00 00 00 1C 00 5B AB E6 B3 74 AC A1 B0 CA 5D BD FC A6 41 A6 B8 B5 6E B3 F5 AE C9 B6 A1 C1 59 B5 75 00 00 00 00 12 00 00 00 C0 27 09 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1E 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 20 2B 33 30 30 25 00 00 00 00 18 00 5B AB E6 B3 74 AC A1 B0 CA 5D BD FC A7 4E AB 6F AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 13 00 00 00 C0 27 09 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1A 00 5B AB E6 B3 74 AC A1 B0 CA 5D BD FC B8 67 C5 E7 AD C8 AE C4 AA 47 BC 57 A5 5B 00 00 00 00 11 00 00 00 C8 00 00 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1E 00 5B AB E6 B3 74 AC A1 B0 CA 5D AC F0 B5 6F A5 F4 B0 C8 A8 43 A4 E9 A6 B8 BC C6 BC 57 A5 5B 00 00 00 00 0D 00 00 00 06 00 00 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 22 00 5B AB E6 B3 74 AC A1 B0 CA 5D AA 69 C3 B9 BB 50 B4 B6 A8 BD A6 AB BE F7 B2 76 B6 7D A9 6C B4 EE A4 D6 00 00 00 00 23 00 00 00 F4 01 00 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1C 00 5B AB E6 B3 74 AC A1 B0 CA 5D AF 50 B5 4B BE D4 AF 54 B8 67 C5 E7 AD C8 BC 57 A5 5B 14 00 00 00 22 00 00 00 96 00 00 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 22 00 5B AB E6 B3 74 AC A1 B0 CA 5D A2 D0 A2 DD A2 E1 A2 E1 B2 D5 B6 A4 A5 5B AB F9 AE C4 AA 47 BC 57 A5 5B 08 00 00 00 21 00 00 00 1E 00 00 00 50 63 34 01 5D 63 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1E 00 AC 50 A4 4F 20 35 A1 42 31 30 A1 42 31 35 AC 50 20 31 30 30 25 20 B1 6A A4 C6 A6 A8 A5 5C 00 00 00 00 11 00 00 00 C8 00 00 00 B4 3C 34 01 B4 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 14 00 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE A7 E9 A6 A9 20 35 30 25 00 00 00 00 25 00 00 00 2C 01 00 00 B4 3C 34 01 B4 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE A7 E9 A6 A9 20 35 30 25 14 00 00 00 1E 00 00 00 C8 00 00 00 01 3D 34 01 01 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 12 00 B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 A7 A7 D6 B1 B6 14 00 00 00 22 00 00 00 C8 00 00 00 01 3D 34 01 01 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 34 00 AD 59 A6 B3 B7 51 AD 6E B2 BE B0 CA AA BA B2 7B AA F7 B9 44 A8 E3 A1 41 BD D0 A7 51 A5 CE A5 AC B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 DB A6 76 B0 74 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 03 00 00 00 04 00 00 00 32 00 00 00 08 3D 34 01 08 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 9E 00 23 65 AC 50 B4 C1 A4 E9 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C B6 67 A4 E9 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE 23 6B A7 E9 A6 A9 33 30 25 23 6E 0D 0A 28 A8 BE C3 7A B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 A5 5D A7 74 A6 62 AC A1 B0 CA B9 EF B6 48 A4 BA A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 B1 4E B0 6C A5 5B AE 4D A5 CE 33 30 25 AA BA A7 E9 A6 A9 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 06 00 00 00 04 00 00 00 32 00 00 00 08 3D 34 01 08 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 18 00 5B B6 67 A4 E9 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 0F 3D 34 01 0F 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AA 00 23 65 AC 50 B4 C1 A4 E9 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C B6 67 A4 E9 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 18 00 5B B6 67 A4 E9 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 00 00 00 00 27 00 00 00 C8 00 00 00 B4 3C 34 01 B4 3C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 18 00 5B B6 67 A4 E9 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 00 00 00 00 27 00 00 00 C8 00 00 00 01 3D 34 01 01 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 14 00 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE A7 E9 A6 A9 20 35 30 25 00 00 00 00 27 00 00 00 C8 00 00 00 08 3D 34 01 08 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 20 35 30 25 A1 49 00 00 00 00 15 00 AC 50 A4 4F 20 31 30 AC 50 A5 48 A4 55 B1 6A A4 C6 20 31 2B 31 00 00 00 00 27 00 00 00 C8 00 00 00 0F 3D 34 01 0F 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE A7 E9 A6 A9 20 35 30 25 00 00 00 00 27 00 00 00 C8 00 00 00 16 3D 34 01 16 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 15 00 B0 B6 A4 6A AA BA C6 46 BB EE C0 F2 B1 6F BE F7 B2 76 35 AD BF 00 00 00 00 27 00 00 00 C8 00 00 00 62 3D 34 01 62 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 B0 B6 A4 6A AA BA C6 46 BB EE C0 F2 B1 6F BE F7 B2 76 35 AD BF A1 49 00 00 00 00 18 00 5B B6 67 A4 E9 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 00 00 00 00 27 00 00 00 C8 00 00 00 69 3D 34 01 69 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 12 00 B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 A7 A7 D6 B1 B6 00 00 00 00 27 00 00 00 C8 00 00 00 70 3D 34 01 70 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 32 00 AD 59 A6 B3 B7 51 AD 6E B2 BE B0 CA AA BA B2 7B AA F7 B9 44 A8 E3 A1 41 BD D0 A7 51 A5 CE B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 A7 A7 D6 B1 B6 A1 49 00 00 00 00 20 00 AC 50 A4 4F 20 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 B1 6A A4 C6 31 30 30 25 A6 A8 A5 5C 04 00 00 00 28 00 00 00 01 00 00 00 62 3D 34 01 62 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 81 00 23 65 AC 50 B4 C1 A4 E9 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C B6 67 A4 E9 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 20 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B B1 6A A4 C6 AE C9 A6 A8 A5 5C B2 76 20 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 69 3D 34 01 69 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 69 3D 34 01 69 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 06 00 00 00 04 00 00 00 32 00 00 00 70 3D 34 01 70 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BC E7 AF E0 AD AB B3 5D B6 4F A5 CE 35 30 25 A7 E9 A6 A9 A1 49 00 00 00 00 18 00 5B B6 67 A4 E9 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 00 00 00 00 27 00 00 00 C8 00 00 00 77 3D 34 01 77 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 05 00 00 00 00 00 00 00 77 3D 34 01 77 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 9C 00 23 65 AC 50 B4 C1 A4 E9 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C B6 67 A4 E9 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE 23 6B A7 E9 A6 A9 33 30 25 23 6E 0D 0A 28 A8 BE C3 7A B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 A5 5D A7 74 A6 62 AC A1 B0 CA B9 EF B6 48 A4 BA A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 B1 4E B0 6C A5 5B AE 4D A5 CE 33 30 25 AA BA A7 E9 A6 A9 29 00 00 1A 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE A7 E9 A6 A9 20 35 30 25 00 00 00 00 27 00 00 00 C8 00 00 00 7E 3D 34 01 7E 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 14 00 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE A7 E9 A6 A9 20 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 7E 3D 34 01 7E 3D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5B 00 23 65 AC 50 B4 C1 A4 E9 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C B6 67 A4 E9 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 BB 79 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 25 00 00 00 2C 01 00 00 D4 88 34 01 D4 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 14 00 3F 3F 3F 20 3F 3F 20 3F 3F 3F 20 3F 3F 3F 20 2B 33 30 30 25 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 14 00 00 00 22 00 00 00 C8 00 00 00 D4 88 34 01 D4 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 3F 3F 3F 3F 20 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 04 00 00 00 28 00 00 00 01 00 00 00 DB 88 34 01 DB 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 12 00 3F 3F 3F 3F 20 31 30 3F 20 3F 3F 20 31 2B 31 20 3F 3F 82 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 A1 41 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 B1 C6 B0 A3 A6 62 AC A1 B0 CA B9 EF B6 48 A4 A7 A5 7E 29 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 10 00 00 00 68 01 00 00 DB 88 34 01 DB 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 09 00 3F 20 3F 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 03 00 00 00 04 00 00 00 32 00 00 00 E2 88 34 01 E2 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 5E 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 BB 79 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 13 00 00 00 0B 00 00 00 64 00 00 00 2E 89 34 01 2E 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 3F 3F 3F 3F 3F 3F 20 3F 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 17 00 00 00 02 00 00 00 2E 89 34 01 2E 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 20 3F 3F 3F 20 32 3F 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 16 00 00 00 0F 00 00 00 35 89 34 01 35 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 20 31 35 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 14 00 00 00 40 77 1B 00 35 89 34 01 35 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 15 00 00 00 80 4F 12 00 35 89 34 01 35 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 14 00 00 00 1D 00 00 00 02 00 00 00 3C 89 34 01 3C 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 3F 3F 3F 3F 3F 20 3F 3F 3F 3F 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 11 00 00 00 C8 00 00 00 43 89 34 01 43 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 3F 20 3F 3F 3F 20 3F 3F 20 3F 3F 20 2B 31 30 30 25 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 1B 00 00 00 63 00 00 00 43 89 34 01 43 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 39 39 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 04 00 00 00 05 00 00 00 00 00 00 00 92 89 34 01 92 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 1A 00 3F 3F 3F 3F 20 35 2C 20 31 30 2C 20 31 35 3F 20 31 30 30 25 20 3F 3F 20 3F 3F 8F 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 A1 41 A6 62 23 6B B9 C1 B8 D5 B1 6A A4 C6 AE C9 A1 41 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A A1 5D B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 5E 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 04 00 00 00 04 00 00 00 00 00 00 00 92 89 34 01 92 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 3F 3F 3F 3F 20 33 30 25 20 3F 3F AE 00 23 65 AC 50 B4 C1 A4 E9 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A A1 5D A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE A1 5E 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 0D 00 00 00 09 00 00 00 99 89 34 01 99 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0D 00 3F 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 5A 00 23 65 AC 50 B4 C1 A4 E9 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C B6 67 A4 E9 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC F0 B5 6F A5 F4 B0 C8 A8 43 A4 E9 A5 69 A7 B9 A6 A8 A6 B8 BC C6 33 AD BF 23 6B 23 6E 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 0E 00 00 00 2C 01 00 00 99 89 34 01 99 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 3F 3F 3F 3F 20 3F 3F 3F 20 3F 3F 3F 20 33 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 14 00 00 00 22 00 00 00 C8 00 00 00 99 89 34 01 99 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 3F 3F 3F 3F 20 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 03 00 00 00 04 00 00 00 32 00 00 00 A0 89 34 01 A0 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 5E 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 BB 79 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 06 00 00 00 04 00 00 00 32 00 00 00 A7 89 34 01 A7 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 3F 20 3F 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 21 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 16 00 00 00 0F 00 00 00 F3 89 34 01 F3 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 20 31 35 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 14 00 00 00 40 77 1B 00 F3 89 34 01 F3 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 15 00 00 00 80 4F 12 00 F3 89 34 01 F3 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 13 00 00 00 0B 00 00 00 64 00 00 00 F3 89 34 01 F3 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 3F 3F 3F 3F 3F 3F 20 3F 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 05 00 00 00 04 00 00 00 32 00 00 00 FA 89 34 01 FA 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 35 30 25 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 17 00 00 00 02 00 00 00 01 8A 34 01 01 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 20 3F 3F 3F 20 32 3F 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 04 00 00 00 28 00 00 00 01 00 00 00 08 8A 34 01 08 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 12 00 3F 3F 3F 3F 20 31 30 3F 20 3F 3F 20 31 2B 31 20 3F 3F 82 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 A1 41 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 B1 C6 B0 A3 A6 62 AC A1 B0 CA B9 EF B6 48 A4 A7 A5 7E 29 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 11 00 00 00 C8 00 00 00 08 8A 34 01 08 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 3F 20 3F 3F 3F 20 3F 3F 20 3F 3F 20 2B 31 30 30 25 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 03 00 00 00 04 00 00 00 32 00 00 00 55 8A 34 01 55 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 5E 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 BB 79 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 25 00 00 00 2C 01 00 00 5C 8A 34 01 5C 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 14 00 3F 3F 3F 20 3F 3F 20 3F 3F 3F 20 3F 3F 3F 20 2B 33 30 30 25 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 14 00 00 00 22 00 00 00 C8 00 00 00 5C 8A 34 01 5C 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 3F 3F 3F 3F 20 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 27 00 00 00 C8 00 00 00 FA 89 34 01 FA 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 20 31 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 27 00 00 00 C8 00 00 00 01 8A 34 01 01 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 20 31 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 27 00 00 00 C8 00 00 00 08 8A 34 01 08 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 20 31 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 27 00 00 00 C8 00 00 00 55 8A 34 01 55 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 20 31 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 27 00 00 00 C8 00 00 00 5C 8A 34 01 5C 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 20 31 3F 20 3F 3F 20 32 3F 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 C6 88 34 01 C6 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 C6 88 34 01 C6 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 C6 88 34 01 C6 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 D4 88 34 01 D4 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 17 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 D4 88 34 01 D4 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 14 00 A5 AC B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 DB A7 D6 BB BC 18 00 00 00 2C 00 00 00 01 00 00 00 41 89 34 01 8D 89 34 01 A0 86 01 00 A0 86 01 00 00 00 00 00 FF FF FF FF 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 34 00 A6 70 AA 47 A6 B3 B7 51 B2 BE B0 CA AA BA B2 7B AA F7 B9 44 A8 E3 A1 41 BD D0 A7 51 A5 CE A5 AC B5 DC BA B8 A4 70 A9 6A AA BA B9 DA A4 DB A7 D6 BB BC A1 49 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 E2 88 34 01 E2 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 E2 88 34 01 E2 88 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 2E 89 34 01 2E 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 89 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B A6 62 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 2E 89 34 01 2E 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 35 89 34 01 35 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 35 89 34 01 35 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 43 89 34 01 43 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 89 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B A6 62 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 11 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF 14 00 00 00 20 00 00 00 05 00 00 00 43 89 34 01 43 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 99 89 34 01 99 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 99 89 34 01 99 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 A0 89 34 01 A0 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 A0 89 34 01 A0 89 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1F 00 5B B3 74 AB D7 BF 45 B5 6F 5D 20 B5 D7 AD 5E A9 C7 AA AB A5 58 B2 7B BE F7 B2 76 BC 57 A5 5B 00 00 00 00 19 00 00 00 0A 00 00 00 CA 8C 34 01 1D 8D 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 5B B3 74 AB D7 BF 45 B5 6F 5D 20 BD FC A6 41 A6 B8 A5 58 B2 7B AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 12 00 00 00 C0 27 09 00 CA 8C 34 01 1D 8D 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 ");
        mplew.writeHexString("FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 5B B3 74 AB D7 BF 45 B5 6F 5D 20 BD FC A7 4E AB 6F AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 13 00 00 00 C0 27 09 00 CA 8C 34 01 1D 8D 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B B3 74 AB D7 BF 45 B5 6F 5D 20 BD FC B8 67 C5 E7 AD C8 AE C4 AA 47 BC 57 A5 5B 00 00 00 00 11 00 00 00 C8 00 00 00 CA 8C 34 01 1D 8D 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1F 00 5B B3 74 AB D7 BF 45 B5 6F 5D 20 AC F0 B5 6F A5 F4 B0 C8 A8 43 A4 E9 A6 B8 BC C6 BC 57 A5 5B 00 00 00 00 0D 00 00 00 06 00 00 00 CA 8C 34 01 1D 8D 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 23 00 5B B3 74 AB D7 BF 45 B5 6F 5D 20 AA 69 C3 B9 A9 4D B4 B6 A8 BD A6 AB BE F7 B2 76 B6 7D A9 6C B4 EE A4 D6 00 00 00 00 23 00 00 00 F4 01 00 00 CA 8C 34 01 1D 8D 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 5B B3 74 AB D7 BF 45 B5 6F 5D 20 AF 50 BF 56 BE D4 AF 54 B8 67 C5 E7 AD C8 BC 57 A5 5B 14 00 00 00 22 00 00 00 96 00 00 00 CA 8C 34 01 1D 8D 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 D2 8A 34 01 D2 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 3F 20 3F 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 21 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 00 00 00 00 11 00 00 00 C8 00 00 00 D2 8A 34 01 D2 8A 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 3F 20 3F 3F 3F 20 3F 3F 20 3F 3F 20 2B 31 30 30 25 00 00 00 00 11 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF 00 00 00 00 1B 00 00 00 63 00 00 00 1F 8B 34 01 1F 8B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 17 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 00 00 00 00 16 00 00 00 0F 00 00 00 2D 8B 34 01 2D 8B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 27 00 5B B5 4C A7 51 BC ED AF 53 B4 66 A1 49 AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 00 00 00 00 14 00 00 00 40 77 1B 00 2D 8B 34 01 2D 8B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 BC 57 AF 71 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 27 00 5B B5 4C A7 51 BC ED AF 53 B4 66 A1 49 AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 00 00 00 00 15 00 00 00 80 4F 12 00 2D 8B 34 01 2D 8B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 27 00 5B B5 4C A7 51 BC ED AF 53 B4 66 A1 49 AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 E9 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 2D 8B 34 01 2D 8B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 03 00 00 00 04 00 00 00 32 00 00 00 34 8B 34 01 34 8B 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 5E 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 BB 79 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1E 00 5B AB E6 B3 74 AC A1 B0 CA 5D B5 D7 AD 5E A9 C7 AA AB B5 6E B3 F5 BE F7 B2 76 BC 57 A5 5B 00 00 00 00 19 00 00 00 0A 00 00 00 6E B1 34 01 7B B1 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 5B A5 5B B3 74 5D 20 BD FC A6 41 A6 B8 B5 6E B3 F5 AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 12 00 00 00 C0 27 09 00 6E B1 34 01 7B B1 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 BD FC A7 4E AB 6F AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 13 00 00 00 C0 27 09 00 6E B1 34 01 7B B1 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 BD FC B8 67 C5 E7 AD C8 AE C4 AA 47 BC 57 A5 5B 00 00 00 00 11 00 00 00 C8 00 00 00 6E B1 34 01 7B B1 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1F 00 5B A5 5B B3 74 5D 20 AA 69 C3 B9 BB 50 B4 B6 A8 BD A6 AB BE F7 B2 76 B0 5F A9 6C B4 EE A4 D6 00 00 00 00 23 00 00 00 F4 01 00 00 6E B1 34 01 7B B1 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 5B A5 5B B3 74 AC A1 B0 CA 5D 20 AF 50 B5 4B BE D4 AF 54 B8 67 C5 E7 AD C8 BC 57 A5 5B 14 00 00 00 22 00 00 00 96 00 00 00 6E B1 34 01 7B B1 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 4A 8C 34 01 4A 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 4A 8C 34 01 4A 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 51 8C 34 01 51 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 51 8C 34 01 51 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 58 8C 34 01 58 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 A7 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B 20 B6 4F A5 CE A7 E9 A6 A9 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE B7 B4 B7 6C B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 AB 44 AC A1 B0 CA B9 EF B6 48 A1 41 4D 56 50 A7 E9 A6 A9 B7 7C A6 62 AD EC A5 BB AA BA 20 33 30 25 A7 E9 A6 A9 BB F9 AE E6 A4 57 C3 42 A5 7E AE 4D A5 CE BA D6 A7 51 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 64 00 00 00 5F 8C 34 01 5F 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 5B AC 50 A4 4F A7 E9 A6 A9 5D 04 00 00 00 04 00 00 00 00 00 00 00 4D 8C 34 01 50 8C 34 01 00 00 00 00 60 5B 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 8C 00 5B AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE A7 E9 A6 A9 5D 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE A7 E9 A6 A9 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE B7 B4 B7 6C B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 AB 44 AC A1 B0 CA B9 EF B6 48 A1 41 4D 56 50 A7 E9 A6 A9 B7 7C A6 62 AD EC A5 BB AA BA 20 33 30 25 A7 E9 A6 A9 BB F9 AE E6 A4 57 C3 42 A5 7E AE 4D A5 CE BA D6 A7 51 29 00 00 17 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 66 8C 34 01 66 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 15 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 A1 41 31 2B 31 B1 6A A4 C6 04 00 00 00 28 00 00 00 01 00 00 00 B9 8C 34 01 B9 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 7B 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 B9 8C 34 01 B9 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 A7 E9 A6 A9 35 30 25 A1 49 23 6B 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 B9 8C 34 01 B9 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 B9 8C 34 01 B9 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF 14 00 00 00 20 00 00 00 05 00 00 00 C0 8C 34 01 C0 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 C0 8C 34 01 C0 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 C0 8C 34 01 C0 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 64 00 00 00 C7 8C 34 01 C7 8C 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 14 8D 34 01 14 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 14 8D 34 01 14 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 1B 8D 34 01 1B 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 1B 8D 34 01 1B 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 22 8D 34 01 22 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 89 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B A6 62 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 17 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 22 8D 34 01 22 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 22 8D 34 01 22 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 A7 E9 A6 A9 35 30 25 A1 49 23 6B 00 00 31 00 5B B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 5D AC 50 B4 C1 A5 7C C0 75 B4 66 A1 49 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 A1 41 31 2B 31 B1 6A A4 C6 04 00 00 00 28 00 00 00 01 00 00 00 E0 AF 34 01 E0 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 69 00 23 65 3C B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 A1 41 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 2D 00 5B B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 5D AC 50 B4 C1 A4 AD C0 75 B4 66 A1 49 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF 14 00 00 00 20 00 00 00 05 00 00 00 E1 AF 34 01 E1 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 37 00 5B B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 5D AC 50 B4 C1 A4 BB C0 75 B4 66 A1 49 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 E2 AF 34 01 E2 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 C7 00 23 65 3C B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 3E AC A1 B0 CA A1 49 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 AA BA 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 A1 41 B1 71 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B B8 D5 B9 CF B1 6A A4 C6 AE C9 A1 41 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 2F 00 5B B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 5D AC 50 B4 C1 A4 BB C0 75 B4 66 A1 49 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 33 30 25 04 00 00 00 04 00 00 00 00 00 00 00 E2 AF 34 01 E2 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 33 30 25 A1 49 00 00 00 00 35 00 5B B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 5D AC 50 B4 C1 A4 40 C0 75 B4 66 A1 49 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 E4 AF 34 01 E4 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 49 00 23 65 3C B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 27 00 5B B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 5D BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 E5 AF 34 01 E5 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 2F 00 5B B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 5D AC 50 B4 C1 A4 54 C0 75 B4 66 A1 49 AC 50 A4 4F B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 33 30 25 04 00 00 00 04 00 00 00 00 00 00 00 E6 AF 34 01 E6 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 98 00 23 65 3C B3 C1 A7 4A A4 4F B6 71 B4 A3 A4 C9 B6 67 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 29 8D 34 01 29 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 29 8D 34 01 29 8D 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 29 8D 34 01 2A 8D 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 29 8D 34 01 2A 8D 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 D5 AF 34 01 D6 AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 D5 AF 34 01 D6 AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 DC AF 34 01 DC AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 DC AF 34 01 DC AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 DC AF 34 01 DD AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 DC AF 34 01 DD AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 E3 AF 34 01 E4 AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 E3 AF 34 01 E4 AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1E 00 5B AF 53 AE ED A5 7E B0 65 5D B5 D7 AD 5E A9 C7 AA AB A5 58 B2 7B BE F7 B2 76 BC 57 A5 5B 00 00 00 00 19 00 00 00 0A 00 00 00 92 B2 34 01 98 B2 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1C 00 5B AF 53 AE ED A5 7E B0 65 5D BD FC A6 41 A6 B8 A5 58 B2 7B AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 12 00 00 00 C0 27 09 00 92 B2 34 01 98 B2 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 18 00 5B AF 53 AE ED A5 7E B0 65 5D BD FC A7 4E AB 6F AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 13 00 00 00 C0 27 09 00 92 B2 34 01 98 B2 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AF 53 AE ED A5 7E B0 65 5D BD FC B8 67 C5 E7 AD C8 AE C4 AA 47 BC 57 A5 5B 00 00 00 00 11 00 00 00 C8 00 00 00 92 B2 34 01 98 B2 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 22 00 5B AF 53 AE ED A5 7E B0 65 5D AA 69 C3 B9 A9 4D B4 B6 A8 BD A6 AB BE F7 B2 76 B6 7D A9 6C B4 EE A4 D6 00 00 00 00 23 00 00 00 F4 01 00 00 92 B2 34 01 98 B2 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 00 5B AF 53 AE ED A5 7E B0 65 5D AF 50 BF 56 BE D4 AF 54 B8 67 C5 E7 AD C8 AE C4 AA 47 BC 57 A5 5B 14 00 00 00 22 00 00 00 96 00 00 00 92 B2 34 01 98 B2 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 17 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 EA AF 34 01 EA AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 EA AF 34 01 EA AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 EA AF 34 01 EB AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 EA AF 34 01 EB AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 F1 AF 34 01 F1 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 F1 AF 34 01 F1 AF 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 F1 AF 34 01 F2 AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 F1 AF 34 01 F2 AF 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 3D B0 34 01 3D B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 89 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B A6 62 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 3D B0 34 01 3E B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 3D B0 34 01 3E B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 44 B0 34 01 45 B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 44 B0 34 01 45 B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 4B B0 34 01 4B B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 4B B0 34 01 4B B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 4B B0 34 01 4C B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 4B B0 34 01 4C B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2B 00 00 00 C8 00 00 00 52 B0 34 01 53 B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 17 00 00 00 2A 00 00 00 C8 00 00 00 52 B0 34 01 53 B0 34 01 00 00 00 00 00 00 00 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 31 A4 D1 C0 F2 B1 6F 32 AD BF C2 49 BC C6 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 52 B0 34 01 52 B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 52 B0 34 01 52 B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 15 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 A1 41 31 2B 31 B1 6A A4 C6 04 00 00 00 28 00 00 00 01 00 00 00 A1 B0 34 01 A1 B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 7D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 A1 41 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 A1 B0 34 01 A1 B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 64 00 00 00 A8 B0 34 01 A8 B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 11 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF 14 00 00 00 20 00 00 00 05 00 00 00 AF B0 34 01 AF B0 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 1A 00 5B B7 73 AC F6 A4 B8 5D BD FC A6 41 A6 B8 A5 58 B2 7B AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 12 00 00 00 40 0D 03 00 EE D6 34 01 11 D8 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B B7 73 AC F6 A4 B8 5D BD FC A6 41 A6 B8 A8 CF A5 CE AE C9 B6 A1 B4 EE A4 D6 00 00 00 00 13 00 00 00 C0 27 09 00 EE D6 34 01 11 D8 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 18 00 5B B7 73 AC F6 A4 B8 5D BD FC B8 67 C5 E7 AD C8 AE C4 AA 47 BC 57 A5 5B 00 00 00 00 11 00 00 00 C8 00 00 00 EE D6 34 01 11 D8 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 00 5B B7 73 AC F6 A4 B8 5D AA 69 C3 B9 A9 4D B4 B6 A8 BD A6 AB BE F7 B2 76 B6 7D A9 6C B4 EE A4 D6 00 00 00 00 23 00 00 00 F4 01 00 00 EE D6 34 01 11 D8 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B B7 73 AC F6 A4 B8 5D AF 50 BF 56 BE D4 AF 54 B8 67 C5 E7 AD C8 BC 57 A5 5B 14 00 00 00 22 00 00 00 96 00 00 00 EE D6 34 01 59 D7 34 01 90 5F 01 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 12 00 5B 3F 3F 3F 3F 5D 20 3F 3F 3F 3F 20 3F 3F 3F 20 3F 3F 14 00 00 00 22 00 00 00 96 00 00 00 5B D7 34 01 11 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 D6 B3 34 01 D6 B3 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 D6 B3 34 01 D6 B3 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 D6 B3 34 01 D6 B3 34 01 00 00 00 00 7C 99 03 00 00 00 00 00");
        mplew.writeHexString("FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 D6 B3 34 01 D6 B3 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 23 B4 34 01 23 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 17 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 2A B4 34 01 2A B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 64 00 00 00 2A B4 34 01 2A B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 31 B4 34 01 31 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 C4 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B A6 62 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 14 00 00 00 20 00 00 00 05 00 00 00 31 B4 34 01 31 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 31 B4 34 01 31 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 38 B4 34 01 39 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 38 B4 34 01 39 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 2C 01 00 00 38 B4 34 01 39 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 2C 01 00 00 38 B4 34 01 39 B4 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 EB D6 34 01 EB D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 DB 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 AA BA 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 A1 41 B1 71 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 6B B8 D5 B9 CF B1 6A A4 C6 AE C9 A1 41 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 EB D6 34 01 EB D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 F2 D6 34 01 F2 D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 F2 D6 34 01 F2 D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 2C 01 00 00 F2 D6 34 01 F2 D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 2C 01 00 00 F2 D6 34 01 F2 D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 A1 41 31 2B 31 B1 6A A4 C6 04 00 00 00 28 00 00 00 01 00 00 00 F9 D6 34 01 F9 D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 4F B1 6A A4 C6 A6 A8 A5 5C AE C9 A1 41 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 29 0D 0A 23 65 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 F9 D6 34 01 F9 D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 17 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 F9 D6 34 01 F9 D6 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 19 00 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 00 D7 34 01 00 D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 1B 00 00 00 63 00 00 00 17 D8 34 01 17 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 06 00 00 00 04 00 00 00 32 00 00 00 17 D8 34 01 17 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 3F 20 3F 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 21 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 03 00 00 00 04 00 00 00 32 00 00 00 1E D8 34 01 1E D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 5E 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 BB 79 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1B 00 AC 50 A4 4F 35 A1 42 31 30 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 05 00 00 00 04 00 00 00 32 00 00 00 25 D8 34 01 25 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 35 30 25 20 3F 3F 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 2C D8 34 01 2C D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 19 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 04 00 00 00 04 00 00 00 00 00 00 00 79 D8 34 01 79 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 3F 3F 3F 3F 20 33 30 25 20 3F 3F 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 16 00 00 00 0F 00 00 00 79 D8 34 01 79 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 A5 5B AB F9 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 14 00 00 00 40 77 1B 00 79 D8 34 01 79 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 15 00 00 00 80 4F 12 00 79 D8 34 01 79 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 64 00 00 00 80 D8 34 01 80 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0E 00 3F 3F 3F 3F 3F 3F 20 3F 3F 3F 3F 20 32 3F 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 17 00 00 00 02 00 00 00 80 D8 34 01 80 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 64 00 00 00 B6 D7 34 01 B6 D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 BD D7 34 01 BD D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 BD FC B8 67 C5 E7 AD C8 42 75 66 66 AE C4 AA 47 2B 31 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 BD D7 34 01 BD D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 2C 01 00 00 BD D7 34 01 BD D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1A 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 2C 01 00 00 BD D7 34 01 BD D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 26 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 BD D7 34 01 BD D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 3D 00 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 28 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE C0 75 B4 66 35 30 25 03 00 00 00 04 00 00 00 32 00 00 00 BD D7 34 01 BD D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 57 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 20 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF 14 00 00 00 20 00 00 00 05 00 00 00 BD D7 34 01 BD D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 15 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 A1 41 31 2B 31 B1 6A A4 C6 04 00 00 00 28 00 00 00 01 00 00 00 C4 D7 34 01 C4 D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 AD 00 20 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 A7 B1 6A A4 C6 A6 A8 A5 5C AE C9 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A9 F3 AC A1 B0 CA B9 EF B6 48 A4 A4 B0 A3 A5 7E 29 0D 0A 23 65 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 30 25 C0 75 B4 66 A1 49 23 6B 00 00 1A 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 20 35 30 25 C0 75 B4 66 03 00 00 00 04 00 00 00 32 00 00 00 C4 D7 34 01 C4 D7 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 30 25 C0 75 B4 66 A1 49 23 6B 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 CB D7 34 01 CB D7 34 01 00 00 00 00 80 A9 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 CB D7 34 01 CB D7 34 01 00 00 00 00 80 A9 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 11 D8 34 01 11 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 11 D8 34 01 11 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 23 00 AC 50 A4 4F B1 6A A4 C6 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 17 D8 34 01 17 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 83 00 23 65 AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E 20 AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 A6 62 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 C4 DD A9 F3 AC A1 B0 CA B9 EF B6 48 A1 43 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 1E D8 34 01 1E D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 42 55 46 46 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 1E D8 34 01 1E D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 26 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 1E D8 34 01 1E D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 3D 00 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 25 D8 34 01 25 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 AC 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 43 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 00 00 00 00 1A 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 20 35 30 25 C0 75 B4 66 03 00 00 00 04 00 00 00 32 00 00 00 25 D8 34 01 25 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 DB 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE C0 75 B4 66 33 30 25 A1 49 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 A6 62 AC A1 B0 CA BD 64 B3 F2 A4 BA A1 41 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 5C 6E 23 65 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 30 25 C0 75 B4 66 A1 49 23 6B 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 2C D8 34 01 2C D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 2C D8 34 01 2C D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF 14 00 00 00 20 00 00 00 05 00 00 00 2C D8 34 01 2C D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 B0 B6 A4 6A C6 46 BB EE C0 F2 B1 6F B2 76 35 AD BF A1 49 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 80 D8 34 01 80 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 42 55 46 46 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 80 D8 34 01 80 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 26 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 80 D8 34 01 80 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 3D 00 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 23 00 AC 50 A4 4F B1 6A A4 C6 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 31 30 30 25 B1 6A A4 C6 A6 A8 A5 5C 04 00 00 00 05 00 00 00 00 00 00 00 87 D8 34 01 87 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 00 00 81 00 23 65 AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E 20 AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 23 B9 C1 B8 D5 B1 6A A4 C6 AE C9 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A4 A3 C4 DD A9 F3 AC A1 B0 CA B9 EF B6 48 A1 43 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 87 D8 34 01 87 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 87 D8 34 01 87 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 15 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 A1 41 31 2B 31 B1 6A A4 C6 04 00 00 00 28 00 00 00 01 00 00 00 8E D8 34 01 8E D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 7D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 A7 B1 6A A4 C6 A6 A8 A5 5C AE C9 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A9 F3 AC A1 B0 CA B9 EF B6 48 A4 A4 B0 A3 A5 7E 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 8E D8 34 01 8E D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 42 55 46 46 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 8E D8 34 01 8E D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 1E 00 00 00 C8 00 00 00 DA D8 34 01 DA D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 14 00 00 00 22 00 00 00 C8 00 00 00 DA D8 34 01 DA D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 13 00 00 00 0B 00 00 00 64 00 00 00 DA D8 34 01 DA D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 04 00 00 00 04 00 00 00 00 00 00 00 E1 D8 34 01 E1 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ED 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 23 65 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 30 25 C0 75 B4 66 A1 49 23 6B 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE 20 23 66 63 30 78 46 46 46 46 43 43 30 30 23 33 30 25 C0 75 B4 66 A1 49 23 6B 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 A6 62 AC A1 B0 CA BD 64 B3 F2 A4 BA A1 41 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 29 00 00 00 00 1A 00 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 20 35 30 25 C0 75 B4 66 03 00 00 00 04 00 00 00 32 00 00 00 E1 D8 34 01 E1 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 ED 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 23 65 A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 23 66 63 30 78 46 46 46 46 43 43 30 30 23 35 30 25 C0 75 B4 66 A1 49 23 6B 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F B1 6A A4 C6 23 6B B6 4F A5 CE 20 23 66 63 30 78 46 46 46 46 43 43 30 30 23 33 30 25 C0 75 B4 66 A1 49 23 6B 23 6E 0D 0A 28 A8 BE A4 EE AF 7D C3 61 B6 4F A5 CE BB 50 B4 4C B6 51 B8 CB B3 C6 A4 A3 A6 62 AC A1 B0 CA BD 64 B3 F2 A4 BA A1 41 B0 A3 AD EC A6 B3 33 30 25 C0 75 B4 66 BB F9 AE E6 A5 7E A1 41 4D 56 50 2F BA F4 A9 40 C0 75 B4 66 A5 69 A6 41 C3 42 A5 7E BE 41 A5 CE 29 29 00 00 26 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 06 00 00 00 04 00 00 00 32 00 00 00 E1 D8 34 01 E1 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 3D 00 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E BC E7 A6 62 AF E0 A4 4F AD AB B3 5D B6 4F A5 CE C0 75 B4 66 35 30 25 A1 49 00 00 00 00 13 00 AC 50 A4 4F 31 30 AC 50 A5 48 A4 55 31 2B 31 B1 6A A4 C6 04 00 00 00 28 00 00 00 01 00 00 00 E8 D8 34 01 E8 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 00 00 00 00 7D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 31 30 AC 50 A5 48 A4 55 AC 50 A4 A7 B1 6A A4 C6 A6 A8 A5 5C AE C9 31 2B 31 B1 6A A4 C6 A1 49 23 6B 23 6E 0D 0A 28 B4 4C B6 51 B8 CB B3 C6 A9 F3 AC A1 B0 CA B9 EF B6 48 A4 A4 B0 A3 A5 7E 29 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 11 00 00 00 C8 00 00 00 E8 D8 34 01 E8 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 16 00 BD FC B8 67 C5 E7 AD C8 42 55 46 46 AE C4 AA 47 20 2B 31 30 30 25 00 00 00 00 1B 00 5B AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 5D 20 AC 50 B4 C1 A4 D1 C0 75 B4 66 A1 49 00 00 00 00 25 00 00 00 2C 01 00 00 E8 D8 34 01 E8 D8 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 B3 73 C4 F2 C0 BB B1 FE AF 5D A4 6C B8 67 C5 E7 AD C8 C0 F2 B1 6F B6 71 2B 33 30 30 25 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2B 00 00 00 C8 00 00 00 13 DA 34 01 13 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 3F 3F 3F 20 31 3F 20 3F 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2A 00 00 00 C8 00 00 00 13 DA 34 01 13 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 3F 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 05 00 00 00 04 00 00 00 32 00 00 00 7C DA 34 01 7C DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 35 30 25 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2B 00 00 00 C8 00 00 00 1A DA 34 01 1A DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 3F 3F 3F 20 31 3F 20 3F 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2A 00 00 00 C8 00 00 00 1A DA 34 01 1A DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 3F 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 14 00 00 00 1E 00 00 00 C8 00 00 00 CF DA 34 01 CF DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1D 00 3F 3F 3F 20 3F 3F 3F 2C 20 3F 3F 3F 3F 3F 3F 20 3F 3F 3F 20 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 1B 00 00 00 63 00 00 00 E4 DA 34 01 E4 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 10 00 39 39 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2B 00 00 00 C8 00 00 00 21 DA 34 01 21 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 3F 3F 3F 20 31 3F 20 3F 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2A 00 00 00 C8 00 00 00 21 DA 34 01 21 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 3F 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 04 00 00 00 04 00 00 00 00 00 00 00 21 DA 34 01 21 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 1E 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0B 00 3F 3F 3F 3F 20 33 30 25 20 3F 3F 5D 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 A4 E5 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 25 00 00 00 2C 01 00 00 DD DA 34 01 DD DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 14 00 3F 3F 3F 20 3F 3F 20 3F 3F 3F 20 3F 3F 3F 20 2B 33 30 30 25 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2B 00 00 00 C8 00 00 00 6E DA 34 01 6E DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 3F 3F 3F 20 31 3F 20 3F 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2A 00 00 00 C8 00 00 00 6E DA 34 01 6E DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 3F 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 03 00 00 00 04 00 00 00 32 00 00 00 D6 DA 34 01 D6 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 13 00 3F 3F 3F 20 3F 3F 20 3F 3F 20 3F 3F 20 35 30 25 20 3F 3F 5E 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 20 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A A9 47 BB 79 AA BA B2 AA B8 F1 B1 6A A4 C6 B6 4F A5 CE 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 C0 75 B4 66 35 30 25 A1 49 23 6B 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2B 00 00 00 C8 00 00 00 75 DA 34 01 75 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 3F 3F 3F 20 31 3F 20 3F 3F 20 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 17 00 00 00 2A 00 00 00 C8 00 00 00 75 DA 34 01 75 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0A 00 3F 3F 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 07 00 00 00 29 00 00 00 81 A3 07 00 75 DA 34 01 75 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 27 00 3F 3F 3F 20 3F 3F 3F 20 3F 3F 2C 20 3F 3F 3F 20 3F 3F 3F 20 3F 3F 20 35 3F 20 3F 3F 20 3F 3F 3F 20 31 3F 20 3F 3F 21 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 13 00 00 00 0B 00 00 00 64 00 00 00 DD DA 34 01 DD DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0F 00 3F 3F 3F 20 3F 3F 3F 20 3F 3F 3F 3F 20 32 3F 00 00 00 00 15 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 20 3F 3F 3F 20 3F 3F 20 3F 3F 00 00 00 00 11 00 00 00 C8 00 00 00 83 DA 34 01 83 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 08 00 3F 20 3F 3F 3F 20 32 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 16 00 00 00 0F 00 00 00 7C DA 34 01 7C DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 20 31 35 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 15 00 00 00 80 4F 12 00 7C DA 34 01 7C DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 00 00 00 00 14 00 00 00 40 77 1B 00 7C DA 34 01 7C DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 0C 00 3F 3F 3F 3F 20 3F 3F 3F 3F 20 3F 3F 00 00 00 00 11 00 5B 3F 3F 3F 20 3F 3F 3F 5D 20 3F 3F 3F 20 3F 3F 21 04 00 00 00 05 00 00 00 00 00 00 00 83 DA 34 01 83 DA 34 01 00 00 00 00 7C 99 03 00 00 00 00 00 FF FF FF FF 01 00 00 00 00 00 00 00 00 00 00 00 00 03 00 00 00 10 00 00 00 E8 03 00 00 0B 00 00 00 E8 03 00 00 06 00 00 00 E8 03 00 00 00 00 00 00 1A 00 3F 3F 3F 3F 20 35 2C 20 31 30 2C 20 31 35 3F 20 31 30 30 25 20 3F 3F 20 3F 3F 8F 00 23 65 AC 50 B4 C1 A4 D1 B4 4E AC 4F AD 6E AA B1 B7 AC A4 A7 A8 A6 A1 49 3C AC 50 B4 C1 A4 D1 B7 AC A4 A7 A8 A6 3E AC A1 B0 CA A1 49 0D 0A 0D 0A 23 66 63 30 78 46 46 46 46 43 43 30 30 23 AC 50 A4 4F 35 AC 50 A1 42 31 30 AC 50 A1 42 31 35 AC 50 A1 41 A6 62 23 6B B9 C1 B8 D5 B1 6A A4 C6 AE C9 A1 41 A6 A8 A5 5C BE F7 B2 76 31 30 30 25 A1 49 23 6E 0D 0A A1 5D B4 4C B6 51 B8 CB B3 C6 A4 A3 BE 41 A5 CE A9 F3 AC A1 B0 CA A4 A4 A1 5E 00 00");
        return mplew.getPacket();
    }

    public static byte[] showGainWeaponPoint(int gainwp) {
        MessageOption option = new MessageOption();
        option.setAmount(gainwp);
        return CWvsContext.sendMessage(33, option);
    }

    public static byte[] updateWeaponPoint(int wp) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ZeroWP.getValue());
        mplew.writeInt(wp);
        return mplew.getPacket();
    }

    public static byte[] FinalAttack(MapleCharacter character, int tick, boolean isSuccess, int skillId, int finalSkillId, int weaponType, List<Integer> monsterIds) {
        MaplePacketLittleEndianWriter packetWriter = new MaplePacketLittleEndianWriter();
        packetWriter.writeShort(OutHeader.LP_UserFinalAttackRequest.getValue());
        packetWriter.writeInt(tick);
        packetWriter.writeInt(isSuccess ? 1 : 0);
        packetWriter.writeInt(skillId);
        packetWriter.writeInt(isSuccess ? finalSkillId : 0);
        packetWriter.writeInt(isSuccess ? weaponType / 10 : 0);
        if (isSuccess) {
            packetWriter.writeInt(monsterIds.size());
            Iterator var8 = monsterIds.iterator();

            while(var8.hasNext()) {
                int monsterId = (Integer)var8.next();
                packetWriter.writeInt(monsterId);
            }
        }

        packetWriter.writeInt(0);
        return packetWriter.getPacket();
    }

    public static byte[] FinalAttackOnp() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UseAttack.getValue());
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] openWorldMap() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.VIEW_WORLDMAP.getValue());
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] skillActive() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ResetOnStateForOnOffSkill.getValue());
        return mplew.getPacket();
    }

    public static byte[] skillNotActive(int skillId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetOffStateForOnOffSkill.getValue());
        mplew.writeInt(skillId);
        return mplew.getPacket();
    }

    public static byte[] poolMakerInfo(boolean result, int count, int cooltime) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.POOLMAKER_INFO.getValue());
        mplew.write(result);
        if (result) {
            mplew.writeInt(count);
            mplew.writeInt(cooltime);
        }

        return mplew.getPacket();
    }

    public static byte[] multiSkillInfo(int skillId, int count, int maxCount, int timeout) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.MULTI_SKILL_INFO.getValue());
        mplew.writeInt(skillId);
        mplew.writeInt(count);
        mplew.writeInt(maxCount);
        mplew.writeInt(timeout);
        return mplew.getPacket();
    }

    public static byte[] updateHayatoPoint(int point) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SwordManPoint.getValue());
        mplew.writeShort(point);
        return mplew.getPacket();
    }

    public static byte[] sendCritAttack() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(32767);
        return mplew.getPacket();
    }

    public static byte[] updateSoulEffect(int chrid, boolean a) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSoulEffect.getValue());
        mplew.writeInt(chrid);
        mplew.write(a);
        return mplew.getPacket();
    }

    public static byte[] RuneStoneClearAndAllRegister(List<MapleRuneStone> runes) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RuneStoneClearAndAllRegister.getValue());
        mplew.writeInt(runes.size());
        boolean runeType = false;
        switch (World.getHoliday()) {
            case ChineseNewYear -> runeType = true;
            case Halloween -> runeType = true;
        }

        mplew.writeInt(0);
        mplew.writeInt(2);
        Iterator var3 = runes.iterator();

        while(var3.hasNext()) {
            MapleRuneStone rune = (MapleRuneStone)var3.next();
            RuneStoneInfo(mplew, rune);
        }

        return mplew.getPacket();
    }

    public static byte[] spawnRuneStone(MapleRuneStone rune) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RuneStoneAppear.getValue());
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(rune.getRuneType());
        RuneStoneInfo(mplew, rune);
        return mplew.getPacket();
    }

    public static void RuneStoneInfo(MaplePacketLittleEndianWriter mplew, MapleRuneStone rune) {
        mplew.writeInt(rune == null ? 0 : rune.getRuneType());
        mplew.writeInt(rune == null ? 0 : (int)((MapleMonster)rune.getMap().getMonsters().getFirst()).getPosition().getX());
        mplew.writeInt(rune == null ? 0 : (int)((MapleMonster)rune.getMap().getMonsters().getFirst()).getPosition().getY());
        mplew.write(rune != null && rune.isFacingLeft());
    }

    public static byte[] removeRuneStone(int charId, int percent, boolean lowerLv, boolean noText) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RuneStoneDisappear.getValue());
        mplew.writeInt(0);
        mplew.writeInt(charId);
        mplew.writeInt(percent);
        mplew.write(lowerLv);
        mplew.write(noText);
        return mplew.getPacket();
    }

    public static byte[] RuneAction(MapleRuneStone.RuneStoneAction action) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RuneStoneUseAck.getValue());
        mplew.writeInt(9);
        mplew.write(0);
        mplew.writeShort(1);
        mplew.write(action.getPacket());
        return mplew.getPacket();
    }

    public static byte[] RuneAction(int type, int time) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RuneStoneUseAck.getValue());
        mplew.writeInt(type);
        if (time > 0) {
            mplew.writeInt(time);
        } else {
            for(int i = 0; i < 4; ++i) {
                mplew.writeInt(Randomizer.nextInt(4));
            }
        }

        return mplew.getPacket();
    }

    public static byte[] showRuneEffect(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RuneStoneSkillAck.getValue());
        mplew.writeInt(type);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] sendRuneCurseMsg(String msg) {
        return sendRuneCurseMsg(msg, false);
    }

    public static byte[] sendRuneCurseMsg(String msg, boolean isRelieve) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.RUNE_CURSE_MSG.getValue());
        mplew.writeMapleAsciiString(msg);
        mplew.writeInt(231);
        mplew.writeShort(0);
        mplew.writeInt(100);
        mplew.writeInt(100);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] RemoveRuneCurseMsg(String msg) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.RUNE_CURSE_MSG.getValue());
        mplew.writeMapleAsciiString(msg);
        mplew.writeInt(231);
        mplew.write(1);
        mplew.write(1);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] startBattleStatistics() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_StartDamageRecord.getValue());
        mplew.write(1);
        return mplew.getPacket();
    }

    public static byte[] CashCheck() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_PingCheckResult_ClientToGame.getValue());
        mplew.writeZeroBytes(46);
        return mplew.getPacket();
    }

    public static byte[] changeHour(int n1, int n2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_HOUR_CHANGED.getValue());
        mplew.writeShort(n1);
        mplew.writeShort(n2);
        return mplew.getPacket();
    }

    public static byte[] createObtacleAtom(int count, int type1, int type2, MapleMap map) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ObtacleAtomCreate.getValue());
        mplew.writeInt(0);
        mplew.writeInt(count);
        mplew.write(0);
        int n5 = Randomizer.nextInt(200000);

        for(int i2 = 0; i2 < count; ++i2) {
            MapleFoothold foothold = (MapleFoothold)map.getFootholds().getAllRelevants().get(Randomizer.nextInt(map.getFootholds().getAllRelevants().size()));
            int n6 = foothold.getY2();
            int n7 = Randomizer.rand(map.getLeft(), map.getRight());
            Point point = map.calcPointBelow(new Point(n7, n6));
            if (point == null) {
                point = new Point(n7, n6);
            }

            mplew.write(1);
            mplew.writeInt(Randomizer.rand(type1, type2));
            mplew.writeInt(n5 + i2);
            mplew.writeInt((int)point.getX());
            if (map.getId() == 220080300) {
                mplew.writeInt(-310);
            } else {
                mplew.writeInt(map.getTop());
            }

            mplew.writeInt((int)point.getX());
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()) + 100);
            mplew.writeInt(Randomizer.rand(5, 5));
            mplew.write(0);
            mplew.writeInt(Randomizer.rand(100, 100));
            mplew.writeInt(0);
            mplew.writeInt(Randomizer.rand(500, 1300));
            mplew.writeInt(0);
            mplew.writeInt(25);
            mplew.writeInt(Randomizer.rand(1, 4));
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()));
            mplew.writeInt(0);
            mplew.writeInt(0);
            int dropspeed = Randomizer.rand(7, 10);
            if (map.getId() == 450009400) {
                mplew.writeInt(15);
            } else {
                mplew.writeInt(dropspeed);
            }

            if (map.getId() == 220080300) {
                mplew.writeInt(310 + map.getBottom());
            } else {
                mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()));
            }

            mplew.writeInt(0);
        }

        return mplew.getPacket();
    }

    public static byte[] DropAttack(int count, int type1, int type2, MapleMap map) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ObtacleAtomCreate.getValue());
        int SpeedRang = Randomizer.rand(4, 13);
        int DamageRang = Randomizer.rand(10, 35);
        mplew.writeInt(0);
        mplew.writeInt(count);
        mplew.write(0);
        int n5 = Randomizer.nextInt(200000);

        for(int i2 = 0; i2 < count; ++i2) {
            MapleFoothold foothold = (MapleFoothold)map.getFootholds().getAllRelevants().get(Randomizer.nextInt(map.getFootholds().getAllRelevants().size()));
            int n6 = foothold.getY2();
            int n7 = Randomizer.rand(map.getLeft(), map.getRight());
            Point point = map.calcPointBelow(new Point(n7, n6));
            if (point == null) {
                point = new Point(n7, n6);
            }

            mplew.write(1);
            mplew.writeInt(Randomizer.rand(type1, type2));
            mplew.writeInt(n5 + i2);
            mplew.writeInt((int)point.getX());
            mplew.writeInt(map.getTop());
            mplew.writeInt((int)point.getX());
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()) + 100);
            mplew.writeInt(Randomizer.rand(5, 5));
            mplew.write(0);
            mplew.writeInt(Randomizer.rand(100, 100));
            mplew.writeInt(0);
            mplew.writeInt(Randomizer.rand(500, 1300));
            mplew.writeInt(0);
            mplew.writeInt(DamageRang);
            mplew.writeInt(Randomizer.rand(1, 4));
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()));
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(SpeedRang);
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()));
            mplew.writeInt(0);
        }

        return mplew.getPacket();
    }

    public static byte[] UpDropAttack(int count, int type1, int type2, MapleMap map) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ObtacleAtomCreate.getValue());
        int SpeedRang = Randomizer.rand(4, 13);
        int DamageRang = Randomizer.rand(10, 35);
        mplew.writeInt(0);
        mplew.writeInt(count);
        mplew.write(0);
        int n5 = Randomizer.nextInt(200000);

        for(int i2 = 0; i2 < count; ++i2) {
            MapleFoothold foothold = (MapleFoothold)map.getFootholds().getAllRelevants().get(Randomizer.nextInt(map.getFootholds().getAllRelevants().size()));
            int n6 = foothold.getY2();
            int n7 = Randomizer.rand(map.getLeft(), map.getRight());
            Point point = map.calcPointBelow(new Point(n7, n6));
            if (point == null) {
                point = new Point(n7, n6);
            }

            mplew.write(1);
            mplew.writeInt(Randomizer.rand(type1, type2));
            mplew.writeInt(n5 + i2);
            mplew.writeInt((int)point.getX());
            mplew.writeInt(map.getTop());
            mplew.writeInt((int)point.getX());
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()) + 100);
            mplew.writeInt(Randomizer.rand(5, 5));
            mplew.write(0);
            mplew.writeInt(Randomizer.rand(100, 100));
            mplew.writeInt(0);
            mplew.writeInt(Randomizer.rand(500, 1300));
            mplew.writeInt(0);
            mplew.writeInt(DamageRang);
            mplew.writeInt(Randomizer.rand(1, 4));
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()));
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(10);
            mplew.writeInt(Math.abs(map.getTop() - (int)point.getY()));
            mplew.writeInt(0);
        }

        return mplew.getPacket();
    }

    public static byte[] sendMarriedBefore(int n2, int n3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_WeddingProgress.getValue());
        mplew.writeInt(n2);
        mplew.writeInt(n3);
        return mplew.getPacket();
    }

    public static byte[] sendMarriedDone() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_WeddingCremonyEnd.getValue());
        return mplew.getPacket();
    }

    public static byte[] showVisitorResult(int type) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SHOW_VISITOR_RESULT.getValue());
        mplew.writeShort(type);
        mplew.writeShort(0);
        return mplew.getPacket();
    }

    public static byte[] updateVisitorKills(int n2, int n3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UPDATE_VISITOR_KILL.getValue());
        mplew.writeShort(n2);
        mplew.writeShort(n3);
        return mplew.getPacket();
    }

    public static byte[] showFieldValue(String str, String act) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_FieldValue.getValue());
        mplew.writeMapleAsciiString(str);
        mplew.writeMapleAsciiString(act);
        return mplew.getPacket();
    }

    public static byte[] DressUpInfoModified(MapleCharacter player) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_DressUpInfoModified.getValue());
        PacketHelper.writeDressUpInfo(mplew, player);
        return mplew.getPacket();
    }

    public static byte[] UserRequestChangeMobZoneState(String data, int b1, List<Point> list) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();

        mplew.writeShort(OutHeader.CHANGE_MOBZONESTATE_REQUEST.getValue());
        mplew.writeMapleAsciiString(data == null ? "" : data);
        mplew.writeInt(b1);
        mplew.writeInt(list.size());
        list.stream().filter(Objects::nonNull).forEach(mplew::writePosInt);

        return mplew.getPacket();
    }


    public static final byte[] LobbyTimeAction(int n, int n2, int n3, int n4, int n5) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserTimerInfo.getValue());
        mplew.writeInt(n);
        mplew.writeInt(n2);
        mplew.writeInt(n3);
        mplew.writeInt(0);
        mplew.writeInt(n4);
        mplew.writeInt(n5);
        return mplew.getPacket();
    }

    public static byte[] SendGiantBossMap(Map<String, String> map) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.GIANT_BOSS_MAP.getValue());
        mplew.writeInt(map.size());
        Iterator var2 = map.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var2.next();
            mplew.writeMapleAsciiString((String)entry.getKey());
            mplew.writeMapleAsciiString((String)entry.getValue());
        }

        return mplew.getPacket();
    }

    public static byte[] ShowPortal(String string, int n2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SHOW_PORTAL.getValue());
        mplew.writeMapleAsciiString(string);
        mplew.writeInt(n2);
        return mplew.getPacket();
    }

    public static byte[] IndividualDeathCountInfo(int cid, int EventDeadCount) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_INDIVIDUAL_DEAD_COUNT_INFO.getValue());
        mplew.writeInt(cid);
        mplew.writeInt(EventDeadCount);
        return mplew.getPacket();
    }

    public static byte[] userBonusAttackRequest(int skillid, int value, List<Integer> list) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserBonusAttackRequest.getValue());
        mplew.writeInt(skillid);
        mplew.writeInt(0);
        mplew.write(1);
        mplew.writeInt(value);
        mplew.writeInt(0);
        mplew.writeInt(0);
        switch (skillid) {
            case 400011074:
            case 400011075:
            case 400011076:
                mplew.writeInt(1120017);
                break;
            case 400041030:
                mplew.writeInt(0);
        }

        return mplew.getPacket();
    }

    public static byte[] SkillFeed(int ms, int n) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SKILL_FEED.getValue());
        mplew.writeInt(ms);
        mplew.write(n);
        return mplew.getPacket();
    }

    public static byte[] SetForceAtomTarget(int skillid, int unk, int size, int objid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SET_FORCE_ATOM_TARGET.getValue());
        mplew.writeInt(skillid);
        mplew.writeInt(unk);
        mplew.writeInt(size);
        mplew.writeInt(objid);
        return mplew.getPacket();
    }

    public static byte[] RegisterExtraSkill(int sourceId, List<ExtraSkill> skills) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_RegisterExtraSkill.getValue());
        mplew.writeInt(sourceId);
        mplew.writeShort(skills.size());
        Iterator var3 = skills.iterator();

        while(var3.hasNext()) {
            ExtraSkill skill = (ExtraSkill)var3.next();
            mplew.writeInt(skill.TriggerSkillID);
            mplew.writeInt(skill.SkillID);
            mplew.writePosInt(skill.Position);
            mplew.writeShort(skill.FaceLeft);
            mplew.writeInt(skill.Delay);
            mplew.writeInt(skill.Value);
            mplew.writeInt(skill.MobOIDs.size());
            Iterator var5 = skill.MobOIDs.iterator();

            int un;
            while(var5.hasNext()) {
                un = (Integer)var5.next();
                mplew.writeInt(un);
            }

            mplew.writeInt(skill.UnkList.size());
            var5 = skill.UnkList.iterator();

            while(var5.hasNext()) {
                un = (Integer)var5.next();
                mplew.writeInt(un);
            }

            mplew.writeInt(skill.TargetOID);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.write(0);
        }

        return mplew.getPacket();
    }

    public static byte[] objSkillEffect(int objId, int skillId, int cid, Point pos) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.OBJ_SKILL_EFFECT.getValue());
        mplew.writeInt(objId);
        mplew.writeInt(skillId);
        mplew.writeInt(cid);
        mplew.writePosInt(pos);
        return mplew.getPacket();
    }

    public static byte[] GameExit() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.GAME_EXIT.getValue());
        return mplew.getPacket();
    }

    public static final byte[] openMapleUnion(int n, MapleUnion ah) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.ShowMapleUnion.getValue());
        mplew.writeInt(n);
        mplew.writeInt(0);
        mplew.write(0);
        addMapleUnionInfo(mplew, ah);
        return mplew.getPacket();
    }

    public static final byte[] updateMapleUnion(MapleUnion mapleUnion) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UpdateUnioMaplen.getValue());
        mplew.writeInt(101);
        addMapleUnionInfo(mplew, mapleUnion);
        return mplew.getPacket();
    }

    public static final void addMapleUnionInfo(MaplePacketLittleEndianWriter mplew, MapleUnion union) {
        mplew.writeInt(union.getAllUnions().size());
        union.getAllUnions().values().forEach((it) -> {
            writeMapleUnionData(mplew, it);
        });
        mplew.writeInt(union.getFightingUnions().size());
        union.getFightingUnions().values().forEach((it) -> {
            writeMapleUnionData(mplew, it);
        });
        boolean unkPuzzle = false;
        mplew.write(unkPuzzle);
        if (unkPuzzle) {
            writeMapleUnionData(mplew, (MapleUnionEntry)null);
        }

        boolean labSS = false;
        mplew.write(labSS);
        if (labSS) {
            writeMapleUnionData(mplew, (MapleUnionEntry)null);
        }

        boolean labSSS = false;
        mplew.write(labSSS);
        if (labSSS) {
            writeMapleUnionData(mplew, (MapleUnionEntry)null);
        }

        boolean unkPuzzle2 = false;
        mplew.write(unkPuzzle2);
        if (unkPuzzle2) {
            writeMapleUnionData(mplew, (MapleUnionEntry)null);
        }

    }

    public static final void writeMapleUnionData(MaplePacketLittleEndianWriter mplew, MapleUnionEntry union) {
        mplew.writeInt(1);
        mplew.writeInt(union.getCharacterId());
        mplew.writeInt(union.getLevel());
        mplew.writeInt(union.getJob() == 900 ? 0 : union.getJob());
        mplew.writeInt(0);
        mplew.writeInt(union.getRotate());
        mplew.writeInt(union.getBoardIndex());
        mplew.writeInt(union.getLocal());
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeMapleAsciiString(union.getName());
    }

    public static byte[] MapleUnionPresetResult(int idx, MapleUnion union) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.MapleUnionPresetResult.getValue());
        mplew.writeInt(1);
        mplew.writeInt(256);
        mplew.writeInt(512);
        mplew.writeInt(768);
        mplew.writeInt(1024);
        mplew.writeInt(1280);
        mplew.writeInt(1536);
        mplew.writeInt(1792);
        mplew.write(0);
        mplew.writeInt(union.getFightingUnions().size());
        union.getFightingUnions().values().forEach((it) -> {
            writeMapleUnionData(mplew, it);
        });
        mplew.write(1);
        mplew.writeInt(0);
        mplew.writeInt(1);
        mplew.writeInt(2);
        mplew.writeInt(3);
        mplew.writeInt(4);
        mplew.writeInt(5);
        mplew.writeInt(6);
        mplew.writeInt(7);

        for(int x = 0; x < 3; ++x) {
            mplew.writeInt(0);
            mplew.writeInt(1);
            mplew.writeInt(256);
            mplew.writeInt(512);
            mplew.writeInt(768);
            mplew.writeInt(1024);
            mplew.writeInt(1280);
            mplew.writeInt(1536);
            mplew.writeInt(1792);
            mplew.write(0);
        }

        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static final byte[] getMapleUnionCoinInfo(int n, int count) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.MapleUnionCoinInfo.getValue());
        mplew.writeInt(n);
        mplew.writeInt(count);
        return mplew.getPacket();
    }

    public static byte[] ArcaneRiverQuickPath() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.ARCANERIVER_QUICKPATH.getValue());
        mplew.writeInt(1);
        return mplew.getPacket();
    }

    public static byte[] MultiSkillResult(int cid, int skillid, int display, int direction, int stance, int Type, int itemid, List<MapleMulitInfo> info) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.ShowMultiSkillAttack.getValue());
        mplew.writeInt(cid);
        mplew.writeInt(skillid);
        mplew.write(display);
        mplew.write(direction);
        mplew.writeShort(stance);
        mplew.write(0);
        mplew.writeInt(Type);
        mplew.writeInt(itemid);
        mplew.writeInt(info.size());
        Iterator var9 = info.iterator();

        while(var9.hasNext()) {
            MapleMulitInfo mapleMulitInfo = (MapleMulitInfo)var9.next();
            mapleMulitInfo.serialize(mplew);
        }

        return mplew.getPacket();
    }

    public static byte[] VSkillObjectAction(int skillid, int skilllv, int display, int info) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.VSkillObjectAction.getValue());
        mplew.write(1);
        mplew.writeInt(skillid);
        mplew.writeInt(skilllv);
        mplew.writeInt(display);
        mplew.writeInt(info);
        return mplew.getPacket();
    }
//    public static byte[] VSkillObjectAction(int skillid, int display, List<Integer> info) {
//        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
//
//        mplew.writeShort(OutHeader.VSkillObjectAction.getValue());
//        mplew.write(1);
//        mplew.writeInt(skillid);
//        mplew.writeInt(display);//V.162 byte=>int
//        mplew.writeInt(info.size());
//        info.forEach(mplew::writeInt);
//
//        return mplew.getPacket();
//    }


    public static byte[] userTossedBySkill(int id, int oid, MobSkill skill) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UserTossedBySkill.getValue());
        mplew.writeInt(id);
        mplew.writeInt(oid);
        mplew.writeInt(skill.getSourceId());
        mplew.writeInt(skill.getLevel());
        mplew.writeInt(skill.getX());
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] summonedBeholderRevengeAttack(int playerID, int summonOid, List<Integer> oids) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SummonedBeholderRevengeAttack.getValue());
        mplew.writeInt(playerID);
        mplew.writeInt(summonOid);
        mplew.writeInt(oids == null ? 0 : oids.size());
        if (oids != null) {
            Iterator var4 = oids.iterator();

            while(var4.hasNext()) {
                int oid = (Integer)var4.next();
                mplew.writeInt(oid);
            }
        }

        return mplew.getPacket();
    }

    public static byte[] summonedBeholderRevengeInfluence(int id, int objectId, int skillID, int b) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SummonedBeholderRevengeInfluence.getValue());
        mplew.writeInt(id);
        mplew.writeInt(objectId);
        mplew.writeInt(skillID);
        mplew.write(b);
        return mplew.getPacket();
    }

    public static byte[] skillCooltimeSet(int skillID, int duration) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SkillCooltimeSetM.getValue());
        mplew.writeInt(1);
        mplew.writeInt(skillID);
        mplew.writeInt(duration);
        return mplew.getPacket();
    }

    public static byte[] RegisterElementalFocus(Map<Integer, Integer> map) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.RegisterElementalFocus.getValue());
        mplew.writeInt(map.size());
        Iterator var2 = map.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Integer, Integer> o = (Map.Entry)var2.next();
            mplew.writeInt((Integer)o.getKey());
            mplew.writeInt((Integer)o.getValue());
        }

        return mplew.getPacket();
    }

    public static byte[] UserElementalFocusResult(int playerId, int sourceId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UserElementalFocusResult.getValue());
        mplew.writeInt(playerId);
        mplew.writeInt(sourceId);
        mplew.writeInt(2);
        return mplew.getPacket();
    }

    public static byte[] showHoyoungHide(int playerId, boolean status) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.HoyoungHide.getValue());
        mplew.writeInt(playerId);
        mplew.writeBool(status);
        return mplew.getPacket();
    }

    public static byte[] sendUnkPacket688() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.UNK_733.getValue());
        mplew.writeZeroBytes(30);
        mplew.writeShort(16368);
        return mplew.getPacket();
    }

    public static byte[] LiftSkillAction(int skillid, int skilllevel, int i2, int x, int y) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LiftSkillAction.getValue());
        mplew.writeInt(skillid);
        mplew.writeInt(skilllevel);
        mplew.writeInt(i2);
        mplew.writeInt(x);
        mplew.writeInt(y);
        if (skillid == 400041080) {
            mplew.writeInt(-1L);
        }

        return mplew.getPacket();
    }

    public static byte[] zeroInfo(MapleCharacter chr, int mask, boolean beta) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_ZeroInfo.getValue());
        chr.getStat().zeroData(mplew, chr, mask, beta);
        return mplew.getPacket();
    }

    public static byte[] mvpPacketTips() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_MVP_REWARD_NOTICE.getValue());
        return mplew.getPacket();
    }

    public static byte[] showMobCollectionComplete(int n, List<Pair<Integer, Integer>> list, int n2, int n3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.MobCollectionCompleteRewardResult.getValue());
        mplew.writeInt(n);
        if (list != null && !list.isEmpty()) {
            mplew.writeInt(list.size());
            Iterator var5 = list.iterator();

            while(var5.hasNext()) {
                Pair<Integer, Integer> pair = (Pair)var5.next();
                mplew.writeInt((Integer)pair.left);
                mplew.writeInt((Integer)pair.right);
            }
        } else {
            mplew.writeInt(0);
            mplew.writeInt(n2);
            mplew.writeInt(n3);
        }

        return mplew.getPacket();
    }

    public static byte[] SetMapTaggedObjectSmoothVisible(ArrayList<TaggedObjRegenInfo> list) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_SetMapTaggedObjectSmoothVisible.getValue());
        mplew.writeInt(list.size());
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            TaggedObjRegenInfo a1298 = (TaggedObjRegenInfo)var2.next();
            mplew.writeMapleAsciiString(a1298.getTag());
            mplew.writeBool(a1298.isVisible());
            mplew.writeInt(a1298.akb);
            mplew.writeInt(0);
        }

        return mplew.getPacket();
    }

    public static byte[] DynamicObjUrusSync(List<Pair<String, Point>> syncFH) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_DynamicObjUrusSync.getValue());
        mplew.writeInt(syncFH.size());
        Iterator var2 = syncFH.iterator();

        while(var2.hasNext()) {
            Pair<String, Point> pair = (Pair)var2.next();
            mplew.writeMapleAsciiString((String)pair.left);
            mplew.write(0);
            mplew.writeInt(1);
            mplew.writePosInt((Point)pair.right);
        }

        return mplew.getPacket();
    }

    public static byte[] UserCreateAreaDotInfo(int n, int skillId, Rectangle rect) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeOpcode(OutHeader.LP_UserCreateAreaDotInfo);
        mplew.writeInt(n);
        mplew.writeInt(skillId);
        mplew.writeInt(0);
        mplew.writeRect(rect);
        return mplew.getPacket();
    }

    public static byte[] UserAreaInfosPrepare(int skillId, int n, Rectangle[] rectangles) {
        MaplePacketLittleEndianWriter p = new MaplePacketLittleEndianWriter(OutHeader.LP_UserAreaInfosPrepare);
        p.writeInt(skillId);
        p.writeInt(n);
        p.writeInt(rectangles.length);

        for(int i = 0; i < rectangles.length; ++i) {
            p.writeInt(i + 1);
            p.writeRect(rectangles[i]);
        }

        return p.getPacket();
    }

    public static byte[] Unknown_42D() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeOpcode(OutHeader.LP_SpecialChairTWSitResult);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(1);
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] CharacterModified(MapleCharacter chr, long l) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CharacterModified.getValue());
        mplew.writeInt(27590330);
        mplew.writeInt(-574354943);
        mplew.writeInt(92536832);
        mplew.writeInt(-33554432);
        mplew.writeInt(16777477);
        mplew.writeInt(1006449);
        mplew.writeInt(259086594);
        mplew.writeInt(-1738538240);
        mplew.writeInt(520355855);
        mplew.writeInt(83890112);
        mplew.writeInt(1054131);
        mplew.writeInt(274909703);
        mplew.writeInt(-2052061184);
        mplew.writeInt(1225326608);
        mplew.writeInt(167776471);
        mplew.writeInt(1352976);
        mplew.writeInt(376902411);
        mplew.writeInt(-60027904);
        mplew.writeInt(-1139998704);
        mplew.writeInt(251662596);
        mplew.writeInt(1115375);
        mplew.writeInt(284947216);
        mplew.writeInt(538841344);
        mplew.writeInt(1611989009);
        mplew.writeInt(369103217);
        mplew.writeInt(1132246);
        mplew.writeInt(461564698);
        mplew.writeInt(-1800266752);
        mplew.writeInt(689897489);
        mplew.writeInt(536875451);
        mplew.writeInt(1662207);
        mplew.writeInt(428049697);
        mplew.writeInt(159851008);
        mplew.writeInt(-1641873390);
        mplew.writeInt(603984426);
        mplew.writeInt(1122267);
        mplew.writeInt(1426784767);
        mplew.writeInt(-33423345);
        mplew.writeInt(50335602);
        mplew.writeInt(1022231);
        mplew.writeInt(269497093);
        mplew.writeInt(1602881280);
        mplew.writeInt(252182544);
        mplew.writeInt(150999173);
        mplew.writeInt(1102796);
        mplew.writeInt(285264909);
        mplew.writeInt(13897472);
        mplew.writeInt(16711697);
        mplew.writeInt(1202199);
        mplew.writeInt(307761409);
        mplew.writeInt(-30212352);
        mplew.writeInt(1997078553);
        mplew.writeInt(-1525678058);
        mplew.writeInt(20);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(-1375731712);
        mplew.writeInt(956320845);
        mplew.writeInt(1476414542);
        mplew.writeInt(19541);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(0);
        mplew.writeInt(419430400);
        mplew.writeInt(604);
        mplew.writeInt(4096);
        chr.getClient().announce(mplew.getPacket());
        return mplew.getPacket();
    }

    public static byte[] ExclRequest() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeOpcode(OutHeader.LP_ExclRequest);
        return mplew.getPacket();
    }

    public static byte[] bossMessage(int mode, int mapid, int mobId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_EliteMobWorldMapNotice.getValue());
        mode = mobId <= 0 ? 1 : mode;
        mplew.write(mode);
        mplew.write(0);
        mplew.writeInt(mapid);
        if (mode != 1) {
            mplew.writeInt(mobId);
            mplew.writeInt(65537);
        }

        mplew.write(0);
        return mplew.getPacket();
    }

    public static byte[] receiveReward(int id, byte mode, long quantity) {
        return updateReward(id, mode, (List)null, quantity);
    }

    public static byte[] updateReward(int id, byte mode, List<MapleReward> rewards, long value) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.REWARD.getValue());
        mplew.write(mode);
        switch (mode) {
            case 9:
                mplew.writeInt(rewards.size());
                if (!rewards.isEmpty()) {
                    Iterator var6 = rewards.iterator();

                    while(true) {
                        MapleReward reward;
                        boolean empty;
                        do {
                            if (!var6.hasNext()) {
                                return mplew.getPacket();
                            }

                            reward = (MapleReward)var6.next();
                            empty = reward.getId() < 1;
                            mplew.writeInt(0);
                            mplew.writeInt(empty ? 0 : reward.getId());
                        } while(empty);

                        if ((value & 1L) != 0L) {
                            mplew.writeLong(PacketHelper.getTime(reward.getReceiveDate() > 0L ? reward.getReceiveDate() : -2L));
                            mplew.writeLong(PacketHelper.getTime(reward.getExpireDate() > 0L ? reward.getExpireDate() : -1L));
                        }

                        if ((value & 2L) != 0L) {
                            mplew.writeInt(0);
                            mplew.writeInt(0);
                            mplew.writeInt(0);
                            mplew.writeInt(0);
                            mplew.writeInt(0);
                            mplew.writeInt(0);
                            mplew.writeMapleAsciiString("");
                            mplew.writeMapleAsciiString("");
                            mplew.writeMapleAsciiString("");
                        }

                        mplew.writeInt(reward.getType());
                        mplew.writeInt(reward.getType() != 1 && reward.getType() != 2 ? 0 : reward.getItemId());
                        mplew.writeInt(reward.getType() != 1 && reward.getType() != 2 ? 0L : reward.getAmount());
                        mplew.writeInt(0);
                        mplew.writeLong(PacketHelper.getTime(-1L));
                        mplew.writeInt(0);
                        mplew.writeInt(reward.getType() == 3 ? reward.getAmount() : 0L);
                        mplew.writeLong(reward.getType() == 4 ? reward.getAmount() : 0L);
                        mplew.writeLong(reward.getType() == 5 ? reward.getAmount() : 0L);
                        mplew.writeInt(-99);
                        mplew.writeInt(-99);
                        mplew.writeMapleAsciiString("");
                        mplew.writeMapleAsciiString("");
                        mplew.writeMapleAsciiString("");
                        if ((value & 4L) != 0L) {
                            mplew.writeMapleAsciiString("");
                        }

                        if ((value & 8L) != 0L) {
                            mplew.writeMapleAsciiString(reward.getDesc());
                        }
                    }
                }
            case 10:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 29:
            case 30:
            case 31:
            case 32:
            default:
                break;
            case 11:
                mplew.writeInt(id);
                mplew.writeInt(value);
                mplew.writeInt(0);
                break;
            case 12:
                mplew.writeInt(id);
                mplew.writeInt(0);
                break;
            case 13:
                mplew.writeInt(id);
                mplew.writeInt(0);
                break;
            case 14:
                mplew.writeInt(id);
                mplew.writeLong(value);
                mplew.writeInt(0);
                break;
            case 15:
                mplew.writeInt(id);
                mplew.writeLong(value);
                mplew.writeInt(0);
                break;
            case 21:
                mplew.write((byte)((int)value));
                break;
            case 22:
                mplew.write((byte)((int)value));
                break;
            case 28:
                mplew.writeDouble((double)value);
                break;
            case 33:
                mplew.write((byte)((int)value));
        }

        return mplew.getPacket();
    }

    public static byte[] getBossPartyCheckDone(int result, int unk_1, int unk_2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_BossPartyCheckDone.getValue());
        mplew.writeInt(result);
        mplew.writeInt(unk_1);
        mplew.writeInt(unk_2);
        return mplew.getPacket();
    }

    public static byte[] getShowBossListWait(MapleCharacter chr, int usType, int[] Value) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserWaitQueueReponse.getValue());
        mplew.writeInt(chr.getId());
        mplew.write(usType);
        switch (usType) {
            case 11:
                mplew.write(Value[0]);
                mplew.writeInt(Value[1]);
                mplew.writeInt(Value[2]);
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeInt(Value[3]);
                break;
            case 12:
            case 15:
            case 16:
            case 17:
            case 19:
            default:
                mplew.write(Value[0]);
                mplew.writeInt(Value[1]);
                mplew.writeInt(Value[2]);
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeInt(0);
                break;
            case 13:
            case 14:
                mplew.write(Value[0]);
                mplew.writeInt(Value[1]);
                mplew.writeInt(Value[2]);
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeInt(0);
                break;
            case 18:
                mplew.write(Value[0]);
                mplew.writeInt(Value[1]);
                mplew.writeInt(Value[2]);
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeInt(0);
                break;
            case 20:
                mplew.write(Value[0]);
                break;
            case 21:
                int v3 = 0;
                mplew.write(v3);

                for(int v34 = 0; v34 < v3; ++v34) {
                    mplew.write(0);
                }
            case 22:
            case 23:
            case 24:
        }

        return mplew.getPacket();
    }

    public static byte[] SpecialChairSitResult(int var0, boolean var1, boolean var2, SpecialChair var3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeOpcode(OutHeader.LP_SpecialChairSitResult);
        mplew.writeInt(0);
        mplew.write(0);
        return mplew.getPacket();
    }

    public static void SpecialChairTWData(MaplePacketLittleEndianWriter mplew, boolean var1, SpecialChair var2) {
        mplew.writeInt(var2.vq());
        mplew.writeBool(var1);
        if (var1) {
            mplew.writeInt(var2.getItemId());
            mplew.writeInt(var2.vt().length);
            mplew.writeRect(var2.vs());
            mplew.writeInt(var2.getPosition().x);
            mplew.writeInt(var2.getPosition().y);
            mplew.writeInt(var2.vt().length);
            int var5 = 0;

            for(int var10000 = var5; var10000 < var2.vt().length; var10000 = var5) {
                int var3 = var2.vt()[var5];
                int var4 = var2.vr()[var5];
                mplew.writeInt(var3);
                mplew.writeBool(var3 == var2.V());
                mplew.writeInt(var3 <= 0 ? -1 : var4);
                ++var5;
            }
        }

    }

    public static byte[] SpecialChairTWRemove(int var0, int var1, int var2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.LP_SpecialChairTWRemove);
        mplew.writeInt(var0);
        mplew.writeInt(var1);
        mplew.writeInt(var2);
        mplew.writeInt(-1);
        mplew.writeBool(false);
        return mplew.getPacket();
    }

    public static byte[] SpecialChairTWSitResult(int var0, Map<Integer, Map<Integer, SpecialChairTW>> var1, List<Integer> var2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.LP_SpecialChairTWSitResult);
        return mplew.getPacket();
    }

    private static void SpecialChairTWData(MaplePacketLittleEndianWriter mplew, SpecialChairTW scTW) {
        mplew.writeInt(scTW.getItemId());
        mplew.writeInt(scTW.getPosition().x);
        mplew.writeInt(scTW.getPosition().y);
        mplew.writeRect(scTW.vs());
        SpecialChairTWData(mplew, scTW.vu(), scTW.vr());
        SpecialChairTWData(mplew, scTW.vv(), scTW.vr());
        mplew.writeBool(true);
    }

    private static void SpecialChairTWData(MaplePacketLittleEndianWriter mplew, Map<Integer, Integer> var1, int[] var2) {
        TreeMap<Integer, Integer> var3 = new TreeMap();
        var1.forEach((var12, var21) -> {
            var3.put(var21, var12);
        });
        mplew.writeInt(var3.size());
        Iterator<Map.Entry<Integer, Integer>> var5 = var3.entrySet().iterator();

        for(Iterator<Map.Entry<Integer, Integer>> var10000 = var5; var10000.hasNext(); var10000 = var5) {
            Map.Entry var6;
            Map.Entry<Integer, Integer> var10001 = var6 = (Map.Entry)var5.next();
            mplew.write(1);
            mplew.writeInt((Integer)var10001.getValue());
            mplew.writeInt((Integer)var6.getKey());
            mplew.writeInt(var2[(Integer)var6.getKey()]);
        }

    }

    public static byte[] SpecialChairTWInviteResult(int var0, int var1, int var2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.LP_SpecialChairTWInviteResult);
        mplew.writeInt(var0);
        mplew.writeInt(var1);
        mplew.writeInt(var2);
        return mplew.getPacket();
    }

    public static byte[] PortableChairUseResult(MapleClient c) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.LP_UserSitResult.getValue());
        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] SpecialChairTWInvite(int var0) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.LP_SpecialChairTWInvite);
        mplew.writeInt(var0);
        return mplew.getPacket();
    }

    public static byte[] SpecialChairTWAction(int var0) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.LP_SpecialChairTWSitResult);
        mplew.writeInt(var0);
        return mplew.getPacket();
    }

    public static byte[] getChangeBeautyResult(int cardItemID, int showLookFlag, long androidSN, List<Pair<Integer, Integer>> beautys) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(showLookFlag == 100 ? OutHeader.CHANGE_ANDROID_BEAUTY_RESULT : OutHeader.CHANGE_BEAUTY_RESULT);
        mplew.writeInt(cardItemID);
        mplew.write(beautys != null && !beautys.isEmpty());
        if (beautys != null && !beautys.isEmpty()) {
            mplew.writeInt(0);
            mplew.writeMapleAsciiString("");
            if (showLookFlag == 101 && beautys.size() < 2) {
                showLookFlag = 0;
            }

            mplew.write(showLookFlag);
            mplew.write(0);
            writeBeautyResult(mplew, showLookFlag == 101 ? 0 : showLookFlag, androidSN, Collections.singletonList((Pair)beautys.getFirst()));
            mplew.write(0);
            if (showLookFlag == 101) {
                writeBeautyResult(mplew, 2, -1L, Collections.singletonList(beautys.get(1)));
            } else {
                writeBeautyResult(mplew, -1, -1L, Collections.emptyList());
            }

            mplew.writeInt(0);
            mplew.write(0);
        } else {
            mplew.writeInt(0);
        }

        return mplew.getPacket();
    }

    public static void writeBeautyResult(MaplePacketLittleEndianWriter mplew, int showLookFlag, long androidSN, List<Pair<Integer, Integer>> beautys) {
        mplew.write(showLookFlag);
        if (showLookFlag == 100) {
            mplew.writeLong(androidSN);
        }

        mplew.writeInt(beautys.size());
        Iterator var5 = beautys.iterator();

        while(var5.hasNext()) {
            Pair<Integer, Integer> pair = (Pair)var5.next();
            mplew.writeInt(類型.膚色(pair.getRight()) ? 1 : (類型.臉型(pair.getRight()) ? 11 : (類型.髮型(pair.getRight()) ? 21 : 0)));
            mplew.writeInt(pair.getRight());
            mplew.writeInt(pair.getLeft());
        }

    }

    public static byte[] getBeautyListFailed(int cardItemID) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.BEAUTY_LIST.getValue());
        mplew.write(false);
        mplew.writeInt(cardItemID);
        return mplew.getPacket();
    }

    public static byte[] getBeautyListAndroid(int cardItemID, long androidSN, List<Integer> beautyList) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.BEAUTY_LIST.getValue());
        mplew.write(true);
        mplew.writeInt(3);
        mplew.writeInt(cardItemID);
        mplew.writeLong(androidSN);
        mplew.write(beautyList.size());
        Iterator var5 = beautyList.iterator();

        while(var5.hasNext()) {
            int beautyID = (Integer)var5.next();
            mplew.writeInt(beautyID);
        }

        return mplew.getPacket();
    }

    public static byte[] getBeautyListZero(int slot, int cardItemID, List<Integer> beautyList, List<Integer> beautyList2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.BEAUTY_LIST.getValue());
        mplew.write(false);
        mplew.writeInt(cardItemID);
        switch (cardItemID / 1000) {
            case 5153:
                int colorSize = 8;
                mplew.writeInt(1);
                mplew.writeInt(slot);
                mplew.write(colorSize);

                for(int x = 0; x < colorSize; ++x) {
                    mplew.writeInt(colorSize);
                }
            default:
                return mplew.getPacket();
        }
    }

    public static byte[] getBeautyList(int showLookFlag, int cardItemID, int slot, long androidSN, List<Integer> beautyList, List<Integer> beautyList2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.BEAUTY_LIST);
        mplew.write(false);
        mplew.writeInt(cardItemID);
        switch (cardItemID / 1000) {
            case 5153:
                int colorSize = 8;
                mplew.writeInt(1);
                mplew.writeInt(slot);
                mplew.write(colorSize);

                for(int x = 0; x < colorSize; ++x) {
                    mplew.writeInt(x);
                }
            default:
                return mplew.getPacket();
        }
    }

    public static byte[] encodeCombingRoomActionRes(int a1, int a2, int a3) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CombingRoomActionRes.getValue());
        mplew.write(a1);
        mplew.write(a2);
        mplew.writeInt(a3);
        return mplew.getPacket();
    }

    public static void encodeCombingRoomChangedHeard(MaplePacketLittleEndianWriter mplew, int styleType, int action, int res) {
        mplew.write(styleType);
        mplew.write(action);
        mplew.write(res);
    }

    public static byte[] encodeCombingRoomRes(int styleType, int action, int res) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CombingRoomChangedRes.getValue());
        encodeCombingRoomChangedHeard(mplew, styleType, action, res);
        return mplew.getPacket();
    }

    public static byte[] encodeUpdateCombingRoomSlotCount(int styleType, int action, int slot, int slot2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CombingRoomChangedRes.getValue());
        encodeCombingRoomChangedHeard(mplew, styleType, action, 1);
        mplew.write(slot);
        mplew.write(slot2);
        return mplew.getPacket();
    }

    public static byte[] encodeUpdateCombingRoomSlotRes(int styleType, int action, int position, int styleID) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CombingRoomChangedRes.getValue());
        encodeCombingRoomChangedHeard(mplew, styleType, action, 2);
        if (action != 6) {
            mplew.write(position);
            PacketHelper.encodeCombingRoomSlot(mplew, styleID);
            mplew.write(0);
        }

        return mplew.getPacket();
    }

    public static byte[] encodeCombingRoomSlotUnknownRes(int styleType, int action, int position, int b1, int b2, int b3, int styleID) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CombingRoomChangedRes.getValue());
        encodeCombingRoomChangedHeard(mplew, styleType, action, 3);
        mplew.write(b1);
        mplew.write(b2);
        mplew.write(b3);
        PacketHelper.encodeCombingRoomSlot(mplew, styleID);
        return mplew.getPacket();
    }

    public static byte[] encodeCombingRoomOldSlotCount(int styleType, int action, int slot) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CombingRoomChangedRes.getValue());
        encodeCombingRoomChangedHeard(mplew, styleType, action, 5);
        mplew.writeInt(slot);
        return mplew.getPacket();
    }

    public static byte[] encodeUpdateCombingRoomInventoryRes(int styleType, int action, Map<Integer, List<Integer>> combingRoomInventorys) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CombingRoomChangedRes.getValue());
        encodeCombingRoomChangedHeard(mplew, styleType, action, 6);
        if (styleType <= 2) {
            PacketHelper.encodeCombingRoomInventory(mplew, combingRoomInventorys.getOrDefault(3 - styleType, new LinkedList()));
        } else if (styleType == 3) {
            for(int i = 3; i > 0; --i) {
                PacketHelper.encodeCombingRoomInventory(mplew, combingRoomInventorys.getOrDefault(i, new LinkedList()));
            }
        }

        return mplew.getPacket();
    }

    public static byte[] createJupiterThunder(int chrid, Point pos, int a1, int a2, int skillID, int bulletCount, int a3, int a4, int a5) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CreateJupiterThunder.getValue());
        mplew.writeInt(chrid);
        int nCount = 1;
        mplew.writeInt(nCount);

        for(int i = 0; i < nCount; ++i) {
            boolean b = true;
            mplew.write(b);
            if (b) {
                mplew.writeInt(i + 1);
                mplew.writeInt(1);
                mplew.writeInt(chrid);
                mplew.writePosInt(pos);
                mplew.writeInt(a1);
                mplew.writeInt(a2);
                mplew.writeInt(skillID);
                mplew.writeInt(bulletCount);
                mplew.writeInt(330);
                mplew.writeInt(40000);
                mplew.writeInt(a3);
                mplew.writeInt(a4);
                mplew.writeInt(a5);
            }
        }

        return mplew.getPacket();
    }

    public static byte[] jupiterThunderEnd(int chrid, int a1, int a2) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.JupiterThunderEnd.getValue());
        mplew.writeInt(chrid);
        mplew.writeInt(a1);
        mplew.writeInt(a2);
        return mplew.getPacket();
    }

    public static byte[] jupiterThunderAction(int chrid, int a1, int a2, int a3, int a4, int a5, int a6, int a7) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.JupiterThunderAction.getValue());
        mplew.writeInt(chrid);
        mplew.writeInt(a1);
        mplew.writeInt(a2);
        mplew.writeInt(a3);
        if (a1 == 1) {
            mplew.writeInt(a4);
            mplew.writeInt(a5);
            mplew.writeInt(a6);
            mplew.writeInt(a7);
        }

        return mplew.getPacket();
    }

    public static byte[] InhumanSpeedAttackeRequest(int chrid, byte a1, int duration) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(-2);
        mplew.writeInt(chrid);
        mplew.write(a1);
        mplew.writeInt(duration);
        return mplew.getPacket();
    }

    public static byte[] onDeadDebuffSet(int type, DeadDebuff deadDebuff) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.DeadDebuffSet);
        mplew.writeShort(type);
        if (type != 2) {
            mplew.writeInt(deadDebuff.Total);
            mplew.writeInt(deadDebuff.getRemain());
            mplew.writeInt(80);
            mplew.writeInt(80);
        }

        return mplew.getPacket();
    }

    public static byte[] sendCTX_DEAD_BUFF_MESSAGE() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.CTX_DEAD_BUFF_MESSAGE.getValue());
        mplew.writeHexString("47 00 AE 4D A5 CE B8 67 C5 E7 AD C8 C0 F2 B1 6F A1 42 B1 BC B8 A8 B2 76 B4 EE A4 D6 38 30 25 AE C4 AA 47 A4 A4 A1 49 0D 0A A8 CF A5 CE C5 40 A8 AD B2 C5 A9 47 B9 44 A8 E3 A1 41 B4 4E AF E0 A5 DF A8 E8 B8 D1 B0 A3 A1 43 52 01 00 00 10 27 00 00 01 B4 00 00 00");
        return mplew.getPacket();
    }

    public static byte[] sendSetTeachSkillCost() {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter(OutHeader.LP_SetTeachSkillCost);
        mplew.writeInt(ServerConfig.TeachCost.size());
        Iterator var1 = ServerConfig.TeachCost.iterator();

        while(var1.hasNext()) {
            int b = (Integer)var1.next();
            mplew.writeInt(b);
        }

        return mplew.getPacket();
    }

    public static byte[] spawnSecondAtoms(MapleCharacter chr, int skillid, int count) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.SPAWN_SECOND_ATOMS.getValue());
        mplew.writeInt(chr.getId());
        mplew.writeInt(2);

        for(int i = 1; i <= 2; ++i) {
            mplew.writeInt(i == 1 ? (count - 1) * 10 : count * 10);
            mplew.writeInt(0);
            mplew.writeInt(i == 1 ? count - 1 : count);
            mplew.writeInt(0);
            mplew.writeInt(chr.getId());
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(skillid);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(1);
            mplew.writeInt(0);
            mplew.writeInt(0);
            mplew.writeInt(chr.getPosition().x);
            mplew.writeInt(1);
            mplew.writeInt(0);
            mplew.writeShort(0);
            mplew.write(0);
        }

        mplew.writeInt(0);
        return mplew.getPacket();
    }

    public static byte[] AttackSecondAtom(MapleCharacter chr, int objid, int count) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.ATTACK_SECOND_ATOM.getValue());
        mplew.writeInt(chr.getId());
        mplew.writeInt(1);
        mplew.writeInt(objid);
        mplew.writeInt(0);
        mplew.writeInt(1);
        return mplew.getPacket();
    }

    public static byte[] removeSecondAtom(int cid, int objectId) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.REMOVE_SECOND_ATOM.getValue());
        mplew.writeInt(cid);
        mplew.writeInt(1);
        mplew.writeInt(objectId);
        mplew.writeInt(0);
        mplew.writeInt(1);
        return mplew.getPacket();
    }
}
