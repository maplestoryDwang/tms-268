//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Server.world;

import Client.MapleCharacter;
import Client.MapleClient;
import Client.SecondaryStat;
import Client.SecondaryStatValueHolder;
import Config.configs.ServerConfig;
import Config.constants.enums.Holiday;
import Net.server.buffs.MapleStatEffect;
import Net.server.life.MobSkill;
import Net.server.shops.HiredFisher;
import Net.server.shops.HiredMerchant;
import Packet.BuffPacket;
import Server.auction.AuctionServer;
import Server.cashshop.CashShopServer;
import Server.channel.ChannelServer;
import Server.channel.PlayerStorage;
import com.github.heqiao2010.lunar.LunarCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {
    public static final int CASH_SHOP_CHANNEL = -10;
    public static final int AUCTION_CHANNEL = -20;
    public static final int WORLD_TOTAL = 0;
    private static int lastCheckHolidayDay = 0;
    private static Holiday holiday;

    public World() {
    }

    public static void init() {
        WorldFindService.getInstance();
        WorldBroadcastService.getInstance();
        WorldAllianceService.getInstance();
        WorldBuddyService.getInstance();
        WorldGuildService.getInstance();
        WorldMessengerService.getInstance();
    }

    public static String getStatus() {
        StringBuilder ret = new StringBuilder();
        int totalUsers = 0;
        Iterator var2 = ChannelServer.getAllInstances().iterator();

        while(var2.hasNext()) {
            ChannelServer cs = (ChannelServer)var2.next();
            ret.append("頻道 ");
            ret.append(cs.getChannel());
            ret.append(": ");
            int channelUsers = cs.getConnectedClients();
            totalUsers += channelUsers;
            ret.append(channelUsers);
            ret.append(" 玩家\n");
        }

        ret.append("總計在線: ");
        ret.append(totalUsers);
        ret.append("\n");
        return ret.toString();
    }

    public static void updateHoliday() {
        LocalDate currentDate = LocalDate.now();
        if (lastCheckHolidayDay != currentDate.getDayOfMonth()) {
            lastCheckHolidayDay = currentDate.getDayOfMonth();
            Holiday[] var1 = Holiday.values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Holiday d = var1[var3];
                if (d != Holiday.None && isHolidayWeek(currentDate, d)) {
                    holiday = d;
                    break;
                }
            }

        }
    }

    public static Holiday getHoliday() {
        return holiday;
    }

    private static boolean isHolidayWeek(LocalDate date, Holiday holiday) {
        return isDateBetween(date.getYear(), date, holiday) || isDateBetween(date.getYear() + 1, date, holiday);
    }

    private static boolean isDateBetween(int year, LocalDate date, Holiday holiday) {
        LocalDate holidayDate;
        if (holiday.isLunar()) {
            Calendar calendar = LunarCalendar.lunar2Solar(year, holiday.getMonth(), holiday.getDayOfMonth(), false);
            holidayDate = LocalDate.of(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
        } else {
            holidayDate = LocalDate.of(year, holiday.getMonth(), holiday.getDayOfMonth());
        }

        LocalDate startOfWeek = holidayDate.minusDays(14L);
        LocalDate endOfWeek = holidayDate.plusDays(14L);
        return date.isEqual(startOfWeek) || date.isEqual(endOfWeek) || date.isAfter(startOfWeek) && date.isBefore(endOfWeek);
    }

    public static Map<Integer, Integer> getConnected() {
        Map<Integer, Integer> ret = new LinkedHashMap();
        int total = 0;

        int chOnline;
        for(Iterator var2 = ChannelServer.getAllInstances().iterator(); var2.hasNext(); total += chOnline) {
            ChannelServer ch = (ChannelServer)var2.next();
            chOnline = ch.getConnectedClients();
            ret.put(ch.getChannel(), chOnline);
        }

        int csOnline = CashShopServer.getConnectedClients();
        ret.put(-10, csOnline);
        total += csOnline;
        ret.put(0, total);
        return ret;
    }

    public static int getTotalConnected() {
        Map<Integer, Integer> connected = getConnected();
        return (Integer)connected.get(0);
    }

    public static List<CheaterData> getCheaters() {
        List<CheaterData> allCheaters = new ArrayList();
        Iterator var1 = ChannelServer.getAllInstances().iterator();

        while(var1.hasNext()) {
            ChannelServer cs = (ChannelServer)var1.next();
            allCheaters.addAll(cs.getCheaters());
        }

        Collections.sort(allCheaters);
        return allCheaters.subList(0, 20);
    }

    public static List<CheaterData> getReports() {
        List<CheaterData> allCheaters = new ArrayList();
        Iterator var1 = ChannelServer.getAllInstances().iterator();

        while(var1.hasNext()) {
            ChannelServer cs = (ChannelServer)var1.next();
            allCheaters.addAll(cs.getReports());
        }

        Collections.sort(allCheaters);
        return allCheaters.subList(0, 20);
    }

    public static boolean isConnected(String charName) {
        return WorldFindService.getInstance().findChannel(charName) > 0;
    }

    public static void toggleMegaphoneMuteState() {
        Iterator var0 = ChannelServer.getAllInstances().iterator();

        while(var0.hasNext()) {
            ChannelServer cs = (ChannelServer)var0.next();
            cs.toggleMegaphoneMuteState();
        }

    }

    public static void clearChannelChangeDataByAccountId(int accountid) {
        Iterator var1 = ChannelServer.getAllInstances().iterator();

        while(var1.hasNext()) {
            ChannelServer cs = (ChannelServer)var1.next();
            getStorage(cs.getChannel()).deregisterPendingPlayerByAccountId(accountid);
        }

        getStorage(-20).deregisterPendingPlayerByAccountId(accountid);
        getStorage(-10).deregisterPendingPlayerByAccountId(accountid);
    }

    public static void ChannelChange_Data(CharacterTransfer Data, int characterid, int toChannel) {
        getStorage(toChannel).registerPendingPlayer(Data, characterid);
    }

    public static boolean isCharacterListConnected(List<String> charNames) {
        Iterator var1 = ChannelServer.getAllInstances().iterator();

        while(var1.hasNext()) {
            ChannelServer cserv = (ChannelServer)var1.next();
            Iterator var3 = charNames.iterator();

            while(var3.hasNext()) {
                String name = (String)var3.next();
                if (cserv.isConnected(name)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String getAllowLoginTip(List<String> charNames) {
        StringBuilder ret = new StringBuilder("賬號下其他角色在遊戲: ");
        Iterator var2 = ChannelServer.getAllInstances().iterator();

        while(var2.hasNext()) {
            ChannelServer cserv = (ChannelServer)var2.next();
            Iterator var4 = charNames.iterator();

            while(var4.hasNext()) {
                String name = (String)var4.next();
                if (cserv.isConnected(name)) {
                    ret.append(name);
                    ret.append(" ");
                }
            }
        }

        return ret.toString();
    }

    public static boolean hasMerchant(int accountID) {
        Iterator var1 = ChannelServer.getAllInstances().iterator();

        ChannelServer cs;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            cs = (ChannelServer)var1.next();
        } while(!cs.containsMerchant(accountID));

        return true;
    }

    public static boolean hasMerchant(int accountID, int characterID) {
        Iterator var2 = ChannelServer.getAllInstances().iterator();

        ChannelServer cs;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            cs = (ChannelServer)var2.next();
        } while(!cs.containsMerchant(accountID, characterID));

        return true;
    }

    public static HiredMerchant getMerchant(int accountID, int characterID) {
        Iterator var2 = ChannelServer.getAllInstances().iterator();

        ChannelServer cs;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            cs = (ChannelServer)var2.next();
        } while(!cs.containsMerchant(accountID, characterID));

        return cs.getHiredMerchants(accountID, characterID);
    }

    public static PlayerStorage getStorage(int channel) {
        if (channel == -20) {
            return AuctionServer.getInstance().getPlayerStorage();
        } else {
            return channel == -10 ? CashShopServer.getPlayerStorage() : ChannelServer.getInstance(channel).getPlayerStorage();
        }
    }

    public static boolean isChannelAvailable(int ch) {
        return ChannelServer.getInstance(ch) != null && ChannelServer.getInstance(ch).getPlayerStorage() != null && ChannelServer.getInstance(ch).getPlayerStorage().getConnectedClients() < ServerConfig.LOGIN_USERLIMIT;
    }

    public static boolean hasFisher(int accountID, int characterID) {
        Iterator var2 = ChannelServer.getAllInstances().iterator();

        ChannelServer cs;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            cs = (ChannelServer)var2.next();
        } while(!cs.containsFisher(accountID, characterID));

        return true;
    }

    public static HiredFisher getFisher(int accountID, int characterID) {
        Iterator var2 = ChannelServer.getAllInstances().iterator();

        ChannelServer cs;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            cs = (ChannelServer)var2.next();
        } while(!cs.containsFisher(accountID, characterID));

        return cs.getHiredFisher(accountID, characterID);
    }

    static {
        holiday = Holiday.None;
    }

    public static class TemporaryStat {
        public static final Map<Integer, Map<SecondaryStat, List<SecondaryStatValueHolder>>> TemporaryStats = new LinkedHashMap();

        public TemporaryStat() {
        }

        public static boolean IsSaveStat(SecondaryStat stat, SecondaryStatValueHolder mbsvh) {
            if (mbsvh.effect instanceof MobSkill) {
                return false;
            } else {
                switch (stat) {
                    case IndieEXP:
                    case ExpBuffRate:
                    case PlusExpRate:
                        return mbsvh.sourceID < 0;
                    default:
                        return IsNotRemoveSaveStat(stat, mbsvh);
                }
            }
        }

        public static boolean IsNotRemoveSaveStat(SecondaryStat stat, SecondaryStatValueHolder mbsvh) {
            if (mbsvh.effect instanceof MobSkill) {
                return false;
            } else {
                switch (mbsvh.sourceID) {
                    case 2310013:
                    case 25120017:
                        return true;
                    case 2321055:
                        return stat == SecondaryStat.HeavensDoorNotTime;
                    default:
                        switch (stat) {
                            case RuneStoneNoTime:
                            case ViperTimeLeap:
                                return true;
                            default:
                                return false;
                        }
                }
            }
        }

        public static void SaveData(MapleCharacter chr) {
            Map<SecondaryStat, List<SecondaryStatValueHolder>> effects = (Map)TemporaryStats.computeIfAbsent(chr.getId(), (k) -> {
                return new LinkedHashMap();
            });
            chr.getEffects().forEach((key, value) -> {
                value.stream().filter((mbsvh) -> {
                    return IsSaveStat(key, mbsvh);
                }).forEach((mbsvh) -> {
                    ((List)effects.computeIfAbsent(key, (k) -> {
                        return new LinkedList();
                    })).add(mbsvh);
                });
            });
            if (effects.isEmpty()) {
                TemporaryStats.remove(chr.getId());
            }

        }

        public static void LoadData(MapleCharacter chr) {
            if (TemporaryStats.containsKey(chr.getId())) {
                Map<Integer, List<SecondaryStat>> stats = new LinkedHashMap();
                Map<SecondaryStat, List<SecondaryStatValueHolder>> remove = TemporaryStats.remove(chr.getId());
                remove.forEach((mapleBuffStat, v) -> {
                    v.forEach(mbsvh -> {
                        if (mbsvh.getLeftTime() >= 5000 && mbsvh.schedule != null && !mbsvh.schedule.isCancelled() && !mbsvh.schedule.isDone()) {
                            ((List)chr.getEffects().computeIfAbsent(mapleBuffStat, (k) -> {
                                return new LinkedList();
                            })).add(mbsvh);
                            if (mbsvh.CancelAction != null) {
                                mbsvh.CancelAction.changeTarget(chr);
                            }

                            ((List)stats.computeIfAbsent(mbsvh.sourceID, (k) -> {
                                return new LinkedList();
                            })).add(mapleBuffStat);
                        }
                    });
                });

                stats.forEach((key, value) -> {
                    chr.send(BuffPacket.giveBuff(chr, (MapleStatEffect)null, (Map)value.stream().collect(Collectors.toMap((stat) -> {
                        return stat;
                    }, (stat) -> {
                        return key;
                    }, (a, b) -> {
                        return b;
                    }, LinkedHashMap::new))));
                });
            }
        }

        public static void CancelStat(int cid, List<SecondaryStat> stats, MapleStatEffect effect, long startTime) {
            if (TemporaryStats.containsKey(cid)) {
                Map<SecondaryStat, List<SecondaryStatValueHolder>> effects = (Map)TemporaryStats.get(cid);
                Stream var10000 = stats.stream();
                Objects.requireNonNull(effects);

                stats.stream().filter(effects::containsKey).forEach( stat -> {
                    List<SecondaryStatValueHolder> holderList = (List)effects.get(stat);
                    Iterator<SecondaryStatValueHolder> holderIterator = holderList.iterator();

                    while(true) {
                        SecondaryStatValueHolder mbsvh;
                        do {
                            if (!holderIterator.hasNext()) {
                                if (holderList.isEmpty()) {
                                    effects.remove(stat);
                                }

                                return;
                            }

                            mbsvh = (SecondaryStatValueHolder)holderIterator.next();
                        } while((!mbsvh.effect.sameSource(effect) || mbsvh.startTime != startTime && startTime != -1L) && (mbsvh.effect.sameSource(effect) || stat.canStack()));

                        mbsvh.cancel();
                        holderIterator.remove();
                    }
                });
                if (effects.isEmpty()) {
                    TemporaryStats.remove(cid);
                }

            }
        }
    }

    public static class Client {
        private static final Set<MapleClient> clients = new HashSet();

        public Client() {
        }

        public static void addClient(MapleClient c) {
            Class var1 = World.class;
            synchronized(World.class) {
                clients.add(c);
            }
        }

        public static boolean isStuck(MapleClient c, int accId) {
            Class var2 = World.class;
            synchronized(World.class) {
                MapleClient stuckClient = null;
                Iterator var4 = getClients().iterator();

                while(var4.hasNext()) {
                    MapleClient client = (MapleClient)var4.next();
                    if (client != c && client.getAccID() == accId) {
                        stuckClient = client;
                        break;
                    }
                }

                if (stuckClient == null) {
                    return true;
                } else if (stuckClient.getSession() == null) {
                    clients.remove(stuckClient);
                    return true;
                } else {
                    stuckClient.getSession().close();
                    return false;
                }
            }
        }

        public static boolean removeClient(MapleClient c) {
            Class var1 = World.class;
            synchronized(World.class) {
                return clients.remove(c);
            }
        }

        public static ArrayList<MapleClient> getClients() {
            Class var0 = World.class;
            synchronized(World.class) {
                return new ArrayList(clients);
            }
        }
    }
}
