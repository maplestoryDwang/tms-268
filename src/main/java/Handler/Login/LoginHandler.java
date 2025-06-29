//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Handler.Login;

import Client.MapleCharacter;
import Client.MapleClient;
import Client.MapleEnumClass.AuthReply;
import Config.configs.ServerConfig;
import Config.constants.GameConstants;
import Config.constants.enums.SecurityCheckType;
import Handler.Handler;
import Net.NetRun.Server;
import Opcode.Headler.InHeader;
import Opcode.Headler.OutHeader;
import Packet.LoginPacket;
import Packet.MaplePacketCreator;
import Packet.PacketHelper;
import Plugin.provider.MapleDataProviderFactory;
import Server.channel.ChannelServer;
import Server.login.LoginServer;
import Server.login.LoginWorker;
import Server.login.handler.LoginPasswordHandler;
import SwordieX.enums.WorldId;
import SwordieX.world.World;
import connection.InPacket;
import connection.OutPacket;
import connection.packet.Login;
import connection.packet.MapLoadable;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import tools.CRC32;
import tools.Pair;
import tools.StringUtil;

public class LoginHandler {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public LoginHandler() {
    }

    @Handler(
            op = InHeader.CP_ClientFileLog
    )
    public static OutPacket clientLog(MapleClient c, InPacket inPacket) {
        OutPacket pong = new OutPacket(OutHeader.LP_AliveReq);
        return pong;
    }

    @Handler(
            op = InHeader.CP_WAIT_FOR_LOAD_FILE_LIST
    )
    public static void LoadWait(MapleClient c, InPacket inPacket) {
        c.write(Login.sendWZCheckList());
    }

    @Handler(
            op = InHeader.CP_v255_163
    )
    public static void handleAliveAck(MapleClient c, InPacket inPacket) {
        c.pongReceived();
    }

    @Handler(
            op = InHeader.CP_SecurityPacket
    )
    public static void handleSecurityPacket(MapleClient c, InPacket inPacket) {
        SecurityCheckType memoryHash = GameConstants.getSecurityMemoryHash(inPacket.decodeByte());
        int clientVersion;
        int majorVersion;
        int minorVersion;
        switch (memoryHash) {
            case SECURITYPACKET_FULL_MEMORY_CHECK:
                clientVersion = inPacket.decodeInt();
                majorVersion = inPacket.decodeInt();
                break;
            case SECURITYPACKET_CHECK_CLIENT_INTEGRITY:
                clientVersion = inPacket.decodeInt();
                majorVersion = clientVersion / 100;
                minorVersion = clientVersion % 100;
                int crc1 = inPacket.decodeInt();
                int crc2 = inPacket.decodeInt();
                int crc3 = inPacket.decodeInt();
                int rnd = inPacket.decodeInt() ^ CRC32.getTable(27);
                int dr0 = inPacket.decodeInt() ^ rnd ^ CRC32.getTable(127);
                int dr1 = inPacket.decodeInt() ^ rnd ^ CRC32.getTable(6);
                int dr2 = inPacket.decodeInt() + CRC32.getTable(4) ^ rnd;
                int dr3 = inPacket.decodeInt() - CRC32.getTable(210) ^ rnd;
                int dr6 = inPacket.decodeInt();
                int var15 = inPacket.decodeInt();
                break;
            case SECURITYPACKET_CLIENT_VERSION:
                clientVersion = inPacket.decodeInt();
                majorVersion = clientVersion / 100;
                minorVersion = clientVersion % 100;
            case SECURITYPACKET_NGS_CLIENTTOSERVER:
        }

    }

    @Handler(
            op = InHeader.CP_PermissionRequest
    )
    public static void handlePermissionRequest(MapleClient c, InPacket inPacket) {
        c.write(Login.sendSecurityPacket());
        c.write(Login.sendServerValues());
        c.write(Login.sendServerEnvironment());
        c.write(Login.sendFileCheckList());
        c.write(Login.sendSecurityCodePacket());
        c.write(Login.sendSecurityCodePacket_Attach());
        c.write(Login.AliveReq());
    }

    @Handler(
            ops = {InHeader.CP_AliveAck}
    )
    public static void AliveAck(MapleClient c, InPacket inPacket) {
        scheduler.schedule(() -> {
            OutPacket outPacket = new OutPacket(OutHeader.LP_AliveReq);
            outPacket.encodeLong(PacketHelper.getTime(System.currentTimeMillis()));
            c.write(outPacket);
        }, 13L, TimeUnit.SECONDS);
    }

    @Handler(
            op = InHeader.CP_GetGuidKey
    )
    public static void getGuildKeyEncry(MapleClient c, InPacket inPacket) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_EncryGuildKey);
        outPacket.encodeArr("4A 00 D0 C0 C4 C8 D4 D8 D8 C8 DC C4 D8 E0 91 DC 85 D8 85 85 89 95 89 89 C0 E4 91 91 DC 95 D8 99 C0 E4 CC 91 99 91 D4 E0 E4 95 85 D0 8D C0 D4 D8 D0 8D 95 DC 89 C4 DC E4 CC DC D0 E0 91 DC D8 C8 95 89 E0 C4 D8 DC CC DC E0 8D 99 CC");
        c.write(outPacket);
    }

    @Handler(
            op = InHeader.CP_BEGIN_USER_DUMMY
    )
    public static void getNowTime(MapleClient c, InPacket inPacket) {
        OutPacket outPacket = new OutPacket(OutHeader.LP_ServerTimeResult);
        outPacket.encodeLong(System.currentTimeMillis());
        c.write(outPacket);
    }

    @Handler(
            op = InHeader.CP_CHECK_CLIENT_LOAD_DATA
    )
    public static void sendLoadCheck(MapleClient c, InPacket inPacket) {
        OutPacket outPacket = new OutPacket(OutHeader.CLIENT_ALIVE);
        c.write(outPacket);
    }

    @Handler(
            op = InHeader.CP_APPLY_HOTFIX
    )
    public static void handleCheckHotfix(MapleClient c, InPacket inPacket) {
        String sha1 = MapleDataProviderFactory.getHotfixCheck();
        String clientDataWzHash = Integer.toHexString(inPacket.decodeInt());
        File dataWz = new File(MapleDataProviderFactory.HOTFIX_DATA_PATH);
        String serverDataWzHash = "";
        int chunkSize = 65536;
        if (dataWz.exists()) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                byte[] data = Files.readAllBytes(dataWz.toPath());
                digest.update(data, 0, data.length);
                serverDataWzHash = (new BigInteger(1, digest.digest())).toString(16);
                String byte1 = serverDataWzHash.substring(0, 2);
                String byte2 = serverDataWzHash.substring(2, 4);
                String byte3 = serverDataWzHash.substring(4, 6);
                String byte4 = serverDataWzHash.substring(6, 8);
                serverDataWzHash = byte4 + byte3 + byte2 + byte1;
                if (!serverDataWzHash.equals(clientDataWzHash)) {
                    for(int i = 0; i < data.length; i += chunkSize) {
                        int end = Math.min(data.length, i + chunkSize);
                        byte[] chunk = Arrays.copyOfRange(data, i, end);
                        c.write(Login.sendHotfix(data.length, i / chunkSize, chunk, serverDataWzHash));
                    }

                    return;
                }
            } catch (Exception var16) {
                Exception e = var16;
                e.printStackTrace();
            }

            c.write(Login.sendHotfix(0, 0, new byte[0], ""));
        }

    }

    @Handler(
            op = InHeader.CP_ClientUnkLoginLog
    )
    public static void handleClientUnkLoginLog(MapleClient c, InPacket inPacket) {
        byte unk = inPacket.decodeByte();
        byte unk1 = inPacket.decodeByte();
        List<Integer> val = new ArrayList();

        for(int i = 0; i < 5; ++i) {
            val.add(inPacket.decodeInt());
        }

    }

    @Handler(
            op = InHeader.CTX_REGIST
    )
    public static void handleSetGenderRequest(MapleClient c, InPacket inPacket) {
        String username = inPacket.decodeString();
        String secondPassword = inPacket.decodeString();
        byte gender = inPacket.decodeByte();
        if (c.getAccountName().equals(username) && c.getSecondPassword() == null && gender >= 0 && gender <= 1) {
            if (secondPassword.length() >= 5) {
                c.setGender(gender);
                c.setSecondPassword(secondPassword);
                c.updateSecondPassword();
                c.write(Login.sendSetGender(true));
                c.announce(LoginPacket.getLoginFailed(AuthReply.GAME_PROTOCOL_INFO));
            } else {
                c.write(Login.sendSetGender(false));
            }

        } else {
            c.write(Login.sendSetGender(false));
        }
    }

    @Handler(
            op = InHeader.CTX_ENTER_ACCOUNT
    )
    public static void handleCheckLogin(MapleClient c, InPacket inPacket) {
        int[] bytes = new int[6];

        for(int i = 0; i < bytes.length; ++i) {
            bytes[i] = inPacket.decodeByte();
        }

        StringBuilder sps = new StringBuilder();
        int[] var4 = bytes;
        int var5 = bytes.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            int aByte = var4[var6];
            sps.append(StringUtil.getLeftPaddedStr(Integer.toHexString(aByte).toUpperCase(), '0', 2));
            sps.append("-");
        }

        String macData = sps.toString();
        macData = macData.substring(0, macData.length() - 1);
        c.setMac(macData);
        inPacket.decodeArr(16);
        String login = inPacket.decodeString();
        String pwd = inPacket.decodeString();
        boolean isIpBan = c.hasBannedIP();
        boolean isMacBan = c.hasBannedMac();
        boolean isBanned = isIpBan || isMacBan;
        c.setTempInfo(login, pwd, isBanned);
        if (isBanned) {
            c.clearInformation();
            c.announce(MaplePacketCreator.serverNotice(1, "您的賬號已被封停！"));
            c.announce(LoginPacket.getLoginFailed(AuthReply.GAME_DEFINITION_INFO));
        } else {
            LoginPasswordHandler.Login(c);
        }
    }

    @Handler(
            op = InHeader.LP_SELECT_WORLD
    )
    public static void handleWorldStatusRequest(MapleClient c, InPacket inPacket) {
        inPacket.decodeByte();
        int n = inPacket.decodeInt();
        int numPlayer = LoginServer.getUsersOn();
        int userLimit = LoginServer.getUserLimit();
        WorldId server = WorldId.getByVal(ServerConfig.WORLD_ID);
        if (numPlayer >= userLimit) {
            c.write(Login.sendWorldStatusResult(server.getVal(), 2));
        } else if ((double)numPlayer >= (double)userLimit * 0.8) {
            c.write(Login.sendWorldStatusResult(server.getVal(), 1));
        } else {
            c.write(Login.sendWorldStatusResult(server.getVal(), 0));
        }

    }

    @Handler(
            op = InHeader.CP_SELECT_WORLD_LOGIN
    )
    public static void handleSelectWorld(MapleClient c, InPacket inPacket) {
        inPacket.decodeByte();
        int server = inPacket.decodeByte() & 255;
        int channel = inPacket.decodeByte() + 1;
        boolean useKey = inPacket.decodeByte() == 1;
        if (useKey) {
            inPacket.decodeByte();
            Pair<String, Integer> loginAuthKey = LoginServer.getLoginAuthKey(inPacket.decodeString(), true);
            if (loginAuthKey == null) {
                c.getSession().close();
                return;
            }

            c.login((String)loginAuthKey.getLeft(), "", false, true);
            c.loginAttempt = 0;
            LoginWorker.registerClient(c, true);
        } else if (!c.isLoggedIn()) {
            c.getSession().close();
            return;
        }

        ChannelServer toch = ChannelServer.getInstance(channel);
        if (Server.getInstance().isOnline() && toch != null) {
            System.out.println("[ NOTICE ] 嘗試連接IP : " + c.getSessionIPAddress() + " 成功連結: " + server + " 頻道: " + channel);
            c.setChannel(channel);
            List<MapleCharacter> chars = c.loadCharacters(server);
            if (chars != null && ChannelServer.getInstance(channel) != null) {
                c.setWorldId(server);
                c.setChannel(channel);
                c.write(Login.sendSetClientKey());
                c.write(Login.sendSetPhysicalWorldID(server));
                c.write(Login.sendSetPhysicalWorldAttach("TW"));
                c.announce(LoginPacket.getCharList(chars, c.getAccCharSlots(), c));
                c.outPacket(OutHeader.CLIENT_ALIVE.getValue(), new Object[0]);
            } else {
                c.getSession().close();
            }

        } else {
            c.announce(LoginPacket.getLoginFailed(AuthReply.GAME_CONNECTION_BUSY));
        }
    }

    @Handler(
            op = InHeader.CP_PrivateServerPacket
    )
    public static void handlePrivateServerPacket(MapleClient c, InPacket inPacket) {
        int pid = inPacket.decodeInt();
        c.write(Login.sendPrivateServerPacket(pid));
    }

    @Handler(
            op = InHeader.CTX_WORLD_RETURN_INFO
    )
    public static void handleWorldListRequest(MapleClient c, InPacket inPacket) {
        Iterator var2 = Server.getInstance().getWorlds().iterator();

        while(var2.hasNext()) {
            World world = (World)var2.next();
            c.write(Login.sendWorldInformation(world, LoginServer.getLoad(), (Set)null));
        }

        c.write(Login.sendWorldInformationEnd());
    }

    @Handler(
            op = InHeader.CTX_OUT_WORLD
    )
    public static void handleLogoutWorld(MapleClient c, InPacket inPacket) {
        c.write(MapLoadable.setMapTaggedObjectVisisble(LoginServer.getRandomWorldSelectBG()));
    }

    @Handler(
            op = InHeader.CTX_IN_FIELD_UNLOCK
    )
    public static void RequestUserChangeMapSec(MapleClient c, InPacket inPacket) {
        OutPacket say = new OutPacket(OutHeader.CTX_IN_FIELD_UNLOCK_PACKET);
        say.encodeByte(1);
        c.write(say);
    }
}
