//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Config.configs;

import Net.server.maps.MapleQuickMove;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tools.config.Property;

public final class ServerConfig {
    @Property(
            key = "mvp.amount.rate",
            defaultValue = "1.0"
    )
    public static float MVP_AMOUNT_RATE;
    @Property(
            key = "WellCome.Message",
            defaultValue = "Wellcome"
    )
    public static String WELLCOME_MESSAGE;
    @Property(
            key = "CLIENT.DATA.INPUT.PATH",
            defaultValue = ""
    )
    public static String Client_DATA_INPUT_PATH;
    @Property(
            key = "CashShop.Message",
            defaultValue = "Wellcome"
    )
    public static String Cash_Shop_Message;
    @Property(
            key = "Scroll.Notice.GM",
            defaultValue = "false"
    )
    public static boolean SCROLL_NOTICE;
    @Property(
            key = "DROP.Notice.GM",
            defaultValue = "false"
    )
    public static boolean DROP_NOTICE_ON_OFF;
    @Property(
            key = "World.Config.GUI",
            defaultValue = "false"
    )
    public static boolean On_Off_GUI;
    @Property(
            key = "Skill.Notice.GM",
            defaultValue = "false"
    )
    public static boolean SKILL_NOTICE_ON_OFF;
    @Property(
            key = "encrypt",
            defaultValue = "true"
    )
    public static boolean ENCRYPT;
    @Property(
            key = "world.server.onlyadmin",
            defaultValue = "false"
    )
    public static boolean WORLD_ONLYADMIN;
    @Property(
            key = "world.server.scriptspath",
            defaultValue = "scripts"
    )
    public static String WORLD_SCRIPTSPATH;
    @Property(
            key = "world.server.scriptspath2",
            defaultValue = "scripts_custom"
    )
    public static String WORLD_SCRIPTSPATH2;
    @Property(
            key = "world.server.cachescript",
            defaultValue = "false"
    )
    public static boolean WORLD_CACHE_SCRIPT;
    @Property(
            key = "login.server.minor",
            defaultValue = "1"
    )
    public static String MapleMinor;
    @Property(
            key = "login.server.name",
            defaultValue = "新楓之谷"
    )
    public static String SERVER_NAME;
    @Property(
            key = "login.server.eventmessage",
            defaultValue = ""
    )
    public static String LOGIN_EVENTMESSAGE;
    @Property(
            key = "login.server.message",
            defaultValue = "祝你遊戲愉快！"
    )
    public static String EVENT_MSG;
    @Property(
            key = "login.server.flag",
            defaultValue = "0"
    )
    public static byte WORLD_ID;
    @Property(
            key = "login.server.status",
            defaultValue = "2"
    )
    public static byte LOGIN_SERVERSTATUS;
    @Property(
            key = "login.server.userlimit",
            defaultValue = "5"
    )
    public static int LOGIN_USERLIMIT;
    @Property(
            key = "login.server.defaultuserlimit",
            defaultValue = "0"
    )
    public static int LOGIN_DEFAULTUSERLIMIT;
    @Property(
            key = "world.autoregister",
            defaultValue = "true"
    )
    public static boolean AUTORIGISTER;
    @Property(
            key = "world.bangainexp",
            defaultValue = "false"
    )
    public static boolean WORLD_BANGAINEXP;
    @Property(
            key = "world.bantrade",
            defaultValue = "false"
    )
    public static boolean WORLD_BANTRADE;
    @Property(
            key = "world.bandropitem",
            defaultValue = "false"
    )
    public static boolean WORLD_BANDROPITEM;
    @Property(
            key = "world.disablepotential",
            defaultValue = "false"
    )
    public static boolean DISABLE_POTENTIAL;
    @Property(
            key = "world.equipcheckfame",
            defaultValue = "false"
    )
    public static boolean WORLD_EQUIPCHECKFAME;
    public static boolean ALL_SOCKET = false;
    @Property(
            key = "server.verifydamage",
            defaultValue = "false"
    )
    public static boolean SERVER_VERIFY_DAMAGE;
    @Property(
            key = "server.updatePlayerInGUI",
            defaultValue = "true"
    )
    public static boolean updatePlayerInGUI;
    @Property(
            key = "world.defaultcuttable",
            defaultValue = "-1"
    )
    public static short DEFAULT_CUTTABLE;
    @Property(
            key = "login.server.port",
            defaultValue = "8484"
    )
    public static short LOGIN_PORT;
    @Property(
            key = "login.server.port_bak",
            defaultValue = "5000"
    )
    public static short LOGIN_PORT_備用;
    @Property(
            key = "cash.server.port",
            defaultValue = "8600"
    )
    public static short CASH_PORT;
    @Property(
            key = "chat.server.port",
            defaultValue = "8283"
    )
    public static short CHAT_PORT;
    @Property(
            key = "packet.server.port",
            defaultValue = "7574"
    )
    public static short SEND_PACKET_PORT;
    @Property(
            key = "auction.server.port",
            defaultValue = "8700"
    )
    public static short AUCTION_PORT;
    @Property(
            key = "channel.server.ports",
            defaultValue = "5"
    )
    public static int CHANNELS_PER_WORLD;
    @Property(
            key = "channel.server.port.start",
            defaultValue = "7575"
    )
    public static short CHANNEL_START_PORT;
    @Property(
            key = "channel.rate.baseexp",
            defaultValue = "100"
    )
    public static int CHANNEL_RATE_BASEEXP;
    @Property(
            key = "channel.rate.exp",
            defaultValue = "10"
    )
    public static int CHANNEL_RATE_EXP;
    @Property(
            key = "channel.rate.meso",
            defaultValue = "10"
    )
    public static int CHANNEL_RATE_MESO;
    @Property(
            key = "channel.rate.drop",
            defaultValue = "10"
    )
    public static int CHANNEL_RATE_DROP;
    @Property(
            key = "channel.rate.globaldrop",
            defaultValue = "10"
    )
    public static int CHANNEL_RATE_GLOBALDROP;
    @Property(
            key = "channel.rate.trait",
            defaultValue = "1"
    )
    public static int CHANNEL_RATE_TRAIT;
    @Property(
            key = "channel.rate.potentiallevel",
            defaultValue = "1"
    )
    public static int CHANNEL_RATE_POTENTIALLEVEL;
    @Property(
            key = "channel.player.maxlevel",
            defaultValue = "300"
    )
    public static int CHANNEL_PLAYER_MAXLEVEL;
    @Property(
            key = "channel.player.maxap",
            defaultValue = "30000"
    )
    public static short CHANNEL_PLAYER_MAXAP;
    @Property(
            key = "channel.player.maxhp",
            defaultValue = "500000"
    )
    public static int CHANNEL_PLAYER_MAXHP;
    @Property(
            key = "channel.player.maxmp",
            defaultValue = "500000"
    )
    public static int CHANNEL_PLAYER_MAXMP;
    @Property(
            key = "channel.player.maxmeso",
            defaultValue = "99999999999"
    )
    public static long CHANNEL_PLAYER_MAXMESO;
    @Property(
            key = "channel.player.damageLimit",
            defaultValue = "10000000000"
    )
    public static long DAMAGE_LIMIT;
    @Property(
            key = "channel.player.beginnermap",
            defaultValue = "10000"
    )
    public static int CHANNEL_PLAYER_BEGINNERMAP;
    @Property(
            key = "channel.player.maxcharacters",
            defaultValue = "6"
    )
    public static byte CHANNEL_PLAYER_MAXCHARACTERS;
    @Property(
            key = "channel.player.resufreecount",
            defaultValue = "5"
    )
    public static int CHANNEL_PLAYER_RESUFREECOUNT;
    @Property(
            key = "channel.player.autocompletequest",
            defaultValue = "false"
    )
    public static boolean CHANNEL_PLAYER_AUTOCOMPLETEQUEST;
    @Property(
            key = "channel.player.disablecooldown",
            defaultValue = "false"
    )
    public static boolean CHANNEL_PLAYER_DISABLECOOLDOWN;
    @Property(
            key = "world.refreshtime",
            defaultValue = "0"
    )
    public static int WORLD_REFRESH_TIME;
    @Property(
            key = "world.refreshrank",
            defaultValue = "120"
    )
    public static int WORLD_REFRESHRANK;
    @Property(
            key = "world.autoban",
            defaultValue = "true"
    )
    public static boolean WORLD_AUTOBAN;
    @Property(
            key = "world.blockskills",
            defaultValue = ""
    )
    public static String WORLD_BLOCKSKILLS;
    @Property(
            key = "world.closejobs",
            defaultValue = ""
    )
    public static String WORLD_CLOSEJOBS;
    @Property(
            key = "world.hidenpcs",
            defaultValue = "{}"
    )
    public static String WORLD_HIDENPCS;
    public static final Map<Integer, Set<Integer>> WORLD_HIDENPCS_MAP = new HashMap();
    @Property(
            key = "channel.server.openpvp",
            defaultValue = "false"
    )
    public static boolean CHANNEL_OPENPVP;
    @Property(
            key = "channel.server.pvpmaps",
            defaultValue = "910000018"
    )
    public static String CHANNEL_PVPMAPS;
    @Property(
            key = "channel.server.chalkboard",
            defaultValue = "false"
    )
    public static boolean CHANNEL_CHALKBOARD;
    @Property(
            key = "channel.server.createguildcost",
            defaultValue = "5000000"
    )
    public static int CHANNEL_CREATEGUILDCOST;
    @Property(
            key = "channel.server.enablepointsbuy",
            defaultValue = "true"
    )
    public static boolean CHANNEL_ENABLEPOINTSBUY;
    @Property(
            key = "channel.server.applyplayerdebuff",
            defaultValue = "false"
    )
    public static boolean CHANNEL_APPLYPLAYERDEBUFF;
    @Property(
            key = "channel.server.applymonsterstatus",
            defaultValue = "false"
    )
    public static boolean CHANNEL_APPLYMONSTERSTATUS;
    @Property(
            key = "channel.server.events",
            defaultValue = ""
    )
    public static String CHANNEL_EVENTS;
    @Property(
            key = "channel.monster.elitecount",
            defaultValue = "500"
    )
    public static int ELITE_COUNT;
    @Property(
            key = "db.ip",
            defaultValue = "localhost"
    )
    public static String DB_IP;
    @Property(
            key = "db.port",
            defaultValue = "3306"
    )
    public static String DB_PORT;
    @Property(
            key = "db.name",
            defaultValue = "tms"
    )
    public static String DB_NAME;
    @Property(
            key = "db.user",
            defaultValue = "root"
    )
    public static String DB_USER;
    @Property(
            key = "db.password",
            defaultValue = ""
    )
    public static String DB_PASSWORD;
    @Property(
            key = "db.timeout",
            defaultValue = "300000"
    )
    public static int DB_TIMEOUT;
    public static int DB_MINPOOLSIZE = 20;
    public static int DB_INITIALPOOLSIZE = 30;
    public static int DB_MAXPOOLSIZE = 1000;
    @Property(
            key = "check.cheatitemexcludes",
            defaultValue = ""
    )
    public static String CHEAT_ITEM_EXCLUDES;
    public static final List<Integer> CHEAT_ITEM_EXCLUDES_LIST = new ArrayList();
    @Property(
            key = "pet.defaultflag",
            defaultValue = "7807"
    )
    public static short PET_DEFAULT_FLAG;
    @Property(
            key = "familiar.sealcost",
            defaultValue = "200"
    )
    public static int FAMILIAR_SEAL_COST;
    @Property(
            key = "world.limitednames",
            defaultValue = "royalFace,animaOn"
    )
    public static String WORLD_LIMITEDNAMES;
    public static final List<String> WORLD_LIMITEDNAMES_LIST = new ArrayList();
    @Property(
            key = "starforce.maplepoint.amount",
            defaultValue = "9"
    )
    public static long SF_MP_AMOUNT;
    @Property(
            key = "starforce.maplepointsafe.amount",
            defaultValue = "50"
    )
    public static long SF_MP_SAFE_AMOUNT;
    @Property(
            key = "starforce.curse.enable",
            defaultValue = "true"
    )
    public static boolean SF_ENABLE_CURSE;
    @Property(
            key = "map.maxburningfieldstep",
            defaultValue = "10"
    )
    public static int MAX_BREAKTIMEFIELD_STEP;
    @Property(
            key = "inventory.cancutitems",
            defaultValue = ""
    )
    public static String CAN_CUT_ITEMS;
    public static final List<Integer> CAN_CUT_ITEMS_LIST = new ArrayList();
    @Property(
            key = "inventory.accshareitems",
            defaultValue = ""
    )
    public static String ACCOUNT_SHARE_ITEMS;
    public static final List<Integer> ACCOUNT_SHARE_ITEMS_LIST = new ArrayList();
    @Property(
            key = "tespia",
            defaultValue = "false"
    )
    public static boolean TESPIA;
    @Property(
            key = "testpia.multiplayer",
            defaultValue = "false"
    )
    public static boolean MULTIPLAYER_TEST;
    @Property(
            key = "map.effect",
            defaultValue = "0"
    )
    public static int MAP_EFFECT;
    public static boolean JMS_SOULWEAPON_SYSTEM = true;
    @Property(
            key = "inventory.itemmaxslot",
            defaultValue = "{}"
    )
    public static String ITEM_MAXSLOT;
    public static final Map<Integer, Short> ITEM_MAXSLOT_MAP = new HashMap();
    public static final List<MapleQuickMove> QUICK_MOVE_LIST = new LinkedList();
    @Property(
            key = "world.createcharburning",
            defaultValue = "0"
    )
    public static int CREATE_CHAR_BURNING;
    @Property(
            key = "world.createcharburningdays",
            defaultValue = "7"
    )
    public static int CREATE_CHAR_BURNING_DAYS;
    @Property(
            key = "noEncryptHosts",
            defaultValue = ""
    )
    public static String noEncryptHosts;
    public static List<String> noEncryptHost_List = new LinkedList();
    @Property(
            key = "mobPointMinLv",
            defaultValue = "150"
    )
    public static int mobPointMinLv;
    @Property(
            key = "ptyPointModifier",
            defaultValue = "1"
    )
    public static int ptyPointModifier;
    @Property(
            key = "mileageDailyLimitMax",
            defaultValue = "100"
    )
    public static int mileageDailyLimitMax;
    @Property(
            key = "mileageMonthlyLimitMax",
            defaultValue = "500"
    )
    public static int mileageMonthlyLimitMax;
    @Property(
            key = "mileageAsMaplePoint",
            defaultValue = "false"
    )
    public static boolean mileageAsMaplePoint;
    @Property(
            key = "partyQuestRevive",
            defaultValue = "false"
    )
    public static boolean partyQuestRevive;
    @Property(
            key = "mobPointNeedPickup",
            defaultValue = "true"
    )
    public static boolean mobPointNeedPickup;
    @Property(
            key = "world.player.enablerebornbuff",
            defaultValue = "false"
    )
    public static boolean EnableRebornBuff;
    @Property(
            key = "familiarIncDAMrHard",
            defaultValue = "false"
    )
    public static boolean familiarIncDAMrHard;
    @Property(
            key = "goldenAppleFragmentNoTimeLimit",
            defaultValue = "false"
    )
    public static boolean goldenAppleFragmentNoTimeLimit;
    @Property(
            key = "KMS_NirvanaFlameTier",
            defaultValue = "false"
    )
    public static boolean KMS_NirvanaFlameTier;
    @Property(
            key = "channel.server.dojoMobMaxHpR",
            defaultValue = "100"
    )
    public static int dojoMobMaxHpR;
    @Property(
            key = "channel.server.dojoMobAtkR",
            defaultValue = "100"
    )
    public static int dojoMobAtkR;
    @Property(
            key = "channel.server.dojoMobDefenseRateR",
            defaultValue = "100"
    )
    public static int dojoMobDefenseRateR;
    @Property(
            key = "HideBulbQuest",
            defaultValue = "false"
    )
    public static boolean HideBulbQuest;
    @Property(
            key = "BlockChairs",
            defaultValue = ""
    )
    public static String BLOCK_CHAIRS;
    public static List<Integer> BLOCK_CHAIRS_SET = new LinkedList();
    @Property(
            key = "defaultDamageSkinSlot",
            defaultValue = "30"
    )
    public static int defaultDamageSkinSlot;
    @Property(
            key = "min_donate",
            defaultValue = "100"
    )
    public static int MIN_DONATE;
    @Property(
            key = "TeachCost",
            defaultValue = "0,0,0,0,5000000,6000000,7000000,8000000,9000000,10000000"
    )
    public static String TeachCostData;
    public static List<Integer> TeachCost = new LinkedList();
    @Property(
            key = "map.rune.close",
            defaultValue = "false"
    )
    public static boolean RUNE_CLOSE;
    @Property(
            key = "defaultMeso",
            defaultValue = "true"
    )
    public static boolean ADD_DEFAULT_MESO;
    @Property(
            key = "defaultMaxSlot",
            defaultValue = "false"
    )
    public static boolean DEFAULT_MAX_SLOT;
    @Property(
            key = "ServerSpawnMobSec",
            defaultValue = "7"
    )
    public static int ServerSpawnMobSec;
    @Property(
            key = "HappyDay.NeedKill.MonsterKillCount",
            defaultValue = "300"
    )
    public static int HAPPY_DAY_NEED_KILL_MONSTER_COUNT;
    @Property(
            key = "ComboColor",
            defaultValue = "0"
    )
    public static int Combo_Color;

    public ServerConfig() {
    }
}
