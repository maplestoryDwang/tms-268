package Server.channel.handler;

import Client.MapleCharacter;
import Client.MapleClient;
import Opcode.Headler.InHeader;
import Opcode.Headler.OutHeader;
import tools.data.MaplePacketLittleEndianWriter;
import tools.data.MaplePacketReader;

/**
 * @author 橘子
 * @version 1.0.0
 * @Title
 * @ClassName UNKNOWNHandler.java
 * @Description 处理未知的包
 * @createTime 2025-07-02 22:15
 */

public class UnknownHandler {


    public static void handle(short packetId, MaplePacketReader slea, MapleClient client) {
            if (packetId == 1048) {

                MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
                mplew.writeShort(1048);
                byte[] packet = mplew.getPacket();

                client.announce(packet);
            }

    }
}
