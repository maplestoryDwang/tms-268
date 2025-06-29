//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Packet;

import Client.MapleClient;
import Config.configs.ServerConfig;
import Database.DatabaseLoader.DatabaseConnectionEx;
import Net.server.MapleDailyGift;
import Opcode.Headler.OutHeader ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import tools.data.MaplePacketLittleEndianWriter;

public class DailyGiftPacket {
    public DailyGiftPacket() {
    }

    public static void addDailyGiftInfo(MapleClient C) {
        try {
            MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
            mplew.writeShort(OutHeader.DAILY_GIFT.getValue());
            List<MapleDailyGift.DailyGiftInfo> dgs = new ArrayList();
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM zdata_dailygifts WHERE month = ?");
                ps.setInt(1, Calendar.getInstance().get(2) + 1);
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    int id = rs.getInt("id");
                    int month = rs.getInt("month");
                    int day = rs.getInt("day");
                    int itemId = rs.getInt("itemid");
                    int count = rs.getInt("count");
                    int commodityId = rs.getInt("commodityid");
                    int term = rs.getInt("term");
                    dgs.add(new MapleDailyGift.DailyGiftInfo(id, month, day, itemId, count, commodityId, term));
                }
            } catch (Throwable var14) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var13) {
                        var14.addSuppressed(var13);
                    }
                }

                throw var14;
            }

            if (con != null) {
                con.close();
            }

            mplew.write(0);
            mplew.write(1);
            mplew.writeLong(132985152000000000L);
            mplew.writeLong(150842304000000000L);
            mplew.writeInt(28);
            mplew.writeInt(1);
            mplew.writeInt(502117);
            mplew.writeInt(ServerConfig.HAPPY_DAY_NEED_KILL_MONSTER_COUNT);
            mplew.writeMapleAsciiString("mvpTooltip_default");
            mplew.writeInt(dgs.size());
            Iterator var17 = dgs.iterator();

            while(var17.hasNext()) {
                MapleDailyGift.DailyGiftInfo dgi = (MapleDailyGift.DailyGiftInfo)var17.next();
                mplew.writeInt(dgi.getDay());
                mplew.writeInt(dgi.getItemid());
                mplew.writeInt(dgi.getCount());
                mplew.write(dgi.getTerm());
                mplew.writeInt(dgi.getCommodityid());
                mplew.writeInt(0);
                mplew.writeInt(0);
                mplew.writeShort(0);
            }

            mplew.writeInt(61);
            mplew.writeInt(2);
            mplew.writeInt(100);
            mplew.writeInt(40914);
            mplew.writeInt(101);
            mplew.writeInt(40914);
            mplew.writeInt(0);
            mplew.writeInt(1);
            mplew.writeInt(0);
            mplew.writeInt(1);
            mplew.writeInt(0);
            C.announce(mplew.getPacket());
        } catch (Throwable var15) {
            Throwable $ex = var15;
        }
    }

    public static byte[] dailyGiftResult(int n, int n2, int n3, int n4) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.DAILY_GIFT.getValue());
        mplew.write(n);
        if (n > 0) {
            if (n > 1) {
                if (n == 2) {
                    mplew.writeInt(n2);
                    mplew.writeInt(n3);
                    if (n2 == 14) {
                        mplew.writeInt(n4);
                    }
                }
            } else {
                mplew.writeInt(0);
            }
        }

        return mplew.getPacket();
    }

    public static byte[] getSigninReward(int type, int x, int itemid) {
        MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
        mplew.writeShort(OutHeader.DAILY_GIFT.getValue());
        mplew.write(type);
        mplew.write(x);
        mplew.writeInt(itemid);
        return mplew.getPacket();
    }
}
