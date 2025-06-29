/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.login.handler;

import Client.MapleClient;
import Config.constants.ServerConstants;
import Packet.MaplePacketCreator;
import Server.channel.ChannelServer;
import Server.login.JobType;
import Server.login.LoginServer;
import Server.world.World;
import tools.data.MaplePacketReader;

/**
 * @author PlayDK
 */
public class CharSelectedHandler {

    private static boolean loginFailCount(MapleClient c) {
        c.loginAttempt++;
        return c.loginAttempt > 5;
    }

    public static void handlePacket(MaplePacketReader slea, MapleClient c) {
        int charId = slea.readInt();
        if (c.getPlayer() != null) {
            c.getSession().close();
        } else if (c.isLoggedIn() && !loginFailCount(c) && c.login_Auth(charId)) {
            if (ChannelServer.getInstance(c.getChannel()) != null && c.getWorldId() == 0) {
                int job = c.getCharacterJob(charId);
                if (job > -1) {
                    JobType jobt = JobType.getByJob(job);
                    if (jobt == null || !ServerConstants.isOpenJob(jobt.name())) {
                        c.dropMessage("該職業暫未開放,敬請期待!");
                        c.sendEnableActions();
                        return;
                    }
                }

                if (c.getIdleTask() != null) {
                    c.getIdleTask().cancel(true);
                }

                String ip = c.getSessionIPAddress();
                LoginServer.putLoginAuth(charId, ip.substring(ip.indexOf(47) + 1), c.getTempIP(), c.getChannel(), c.getMac());
                c.updateLoginState(1, ip);
                World.clearChannelChangeDataByAccountId(c.getAccID());
                c.announce(MaplePacketCreator.getServerIP(ChannelServer.getInstance(c.getChannel()).getPort(), charId));
            } else {
                c.getSession().close();
            }
        } else {
            c.sendEnableActions();
        }
    }
}
