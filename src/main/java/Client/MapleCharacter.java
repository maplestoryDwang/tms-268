//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Client;

import Client.anticheat.CheatTracker;
import Client.anticheat.ReportType;
import Client.force.MapleForceAtom;
import Client.force.MapleForceFactory;
import Client.hexa.MapleHexaSkill;
import Client.hexa.MapleHexaStat;
import Client.inventory.EnhanceResultType;
import Client.inventory.Equip;
import Client.inventory.ImpFlag;
import Client.inventory.Item;
import Client.inventory.ItemAttribute;
import Client.inventory.ItemLoader;
import Client.inventory.MapleAndroid;
import Client.inventory.MapleImp;
import Client.inventory.MapleInventory;
import Client.inventory.MapleInventoryIdentifier;
import Client.inventory.MapleInventoryType;
import Client.inventory.MapleMount;
import Client.inventory.MaplePet;
import Client.inventory.MaplePotionPot;
import Client.inventory.MapleRing;
import Client.inventory.ModifyInventory;
import Client.skills.InnerAbillity;
import Client.skills.InnerSkillEntry;
import Client.skills.Skill;
import Client.skills.SkillEntry;
import Client.skills.SkillFactory;
import Client.skills.SkillMacro;
import Client.skills.handler.AbstractSkillHandler;
import Client.skills.handler.SkillClassApplier;
import Client.skills.handler.SkillClassFetcher;
import Client.stat.DeadDebuff;
import Client.stat.MapleHyperStats;
import Client.stat.PlayerStats;
import Config.$Crypto.MapleAESOFB;
import Config.configs.Config;
import Config.configs.FishingConfig;
import Config.configs.ServerConfig;
import Config.constants.GameConstants;
import Config.constants.ItemConstants;
import Config.constants.JobConstants;
import Config.constants.ServerConstants;
import Config.constants.SkillConstants;
import Config.constants.ItemConstants.類型;
import Config.constants.enums.UIReviveType;
import Config.constants.enums.UserChatMessageType;
import Config.constants.skills.凱撒;
import Database.DatabaseLoader.DatabaseConnectionEx;
import Database.tools.SqlTool;
import Net.server.AutobanManager;
import Net.server.MapleActivity;
import Net.server.MapleInventoryManipulator;
import Net.server.MapleItemInformationProvider;
import Net.server.MaplePortal;
import Net.server.MapleStatInfo;
import Net.server.MapleTrade;
import Net.server.MapleTrunk;
import Net.server.MapleUnionData;
import Net.server.RankingTop;
import Net.server.ShutdownServer;
import Net.server.SkillCustomInfo;
import Net.server.StructSetItem;
import Net.server.StructSetItemStat;
import Net.server.VCoreDataEntry;
import Net.server.Timer.CoolDownTimer;
import Net.server.Timer.MapTimer;
import Net.server.Timer.WorldTimer;
import Net.server.buffs.MapleStatEffect;
import Net.server.carnival.MapleCarnivalChallenge;
import Net.server.carnival.MapleCarnivalParty;
import Net.server.cashshop.CashShop;
import Net.server.commands.PlayerRank;
import Net.server.life.Element;
import Net.server.life.MapleLifeFactory;
import Net.server.life.MapleMonster;
import Net.server.life.MapleNPC;
import Net.server.life.MobSkill;
import Net.server.life.PlayerNPC;
import Net.server.maps.AnimatedMapleMapObject;
import Net.server.maps.FieldLimitType;
import Net.server.maps.ForceAtomObject;
import Net.server.maps.MapleDragon;
import Net.server.maps.MapleExtractor;
import Net.server.maps.MapleFoothold;
import Net.server.maps.MapleMap;
import Net.server.maps.MapleMapFactory;
import Net.server.maps.MapleMapItem;
import Net.server.maps.MapleMapObject;
import Net.server.maps.MapleMapObjectType;
import Net.server.maps.MapleReactor;
import Net.server.maps.MapleSkillPet;
import Net.server.maps.MapleSummon;
import Net.server.maps.MechDoor;
import Net.server.maps.SavedLocationType;
import Net.server.maps.TownPortal;
import Net.server.maps.events.Event_PyramidSubway;
import Net.server.maps.field.ActionBarField;
import Net.server.quest.MapleQuest;
import Net.server.shop.BuyLimitData;
import Net.server.shop.MapleShop;
import Net.server.shop.MapleShopFactory;
import Net.server.shop.MapleShopItem;
import Net.server.shop.NpcShopBuyLimit;
import Net.server.shops.HiredFisher;
import Net.server.shops.IMaplePlayerShop;
import Opcode.Opcode.EffectOpcode;
import Opcode.Headler.OutHeader;
import Packet.AdelePacket;
import Packet.AndroidPacket;
import Packet.BuddyListPacket;
import Packet.BuffPacket;
import Packet.CField;
import Packet.CWvsContext;
import Packet.EffectPacket;
import Packet.ForcePacket;
import Packet.GuildPacket;
import Packet.InventoryPacket;
import Packet.MTSCSPacket;
import Packet.MaplePacketCreator;
import Packet.MonsterCarnivalPacket;
import Packet.PetPacket;
import Packet.PlayerShopPacket;
import Packet.SummonPacket;
import Packet.UIPacket;
import Packet.VCorePacket;
import Plugin.script.ScriptManager;
import Plugin.script.binding.ScriptEvent;
import Server.BossEventHandler.Seren;
import Server.channel.ChannelServer;
import Server.channel.handler.AttackInfo;
import Server.login.JobType;
import Server.world.CharacterTransfer;
import Server.world.PlayerBuffStorage;
import Server.world.PlayerBuffValueHolder;
import Server.world.World;
import Server.world.WorldBroadcastService;
import Server.world.WorldFindService;
import Server.world.WorldGuildService;
import Server.world.WorldMessengerService;
import Server.world.World.TemporaryStat;
import Server.world.guild.MapleGuild;
import Server.world.guild.MapleGuildCharacter;
import Server.world.messenger.MapleMessenger;
import Server.world.messenger.MapleMessengerCharacter;
import Server.world.messenger.MessengerRankingWorker;
import SwordieX.client.character.CharacterStat;
import SwordieX.client.character.avatar.AvatarData;
import SwordieX.client.character.avatar.AvatarLook;
import SwordieX.client.character.keys.FuncKeyMap;
import SwordieX.client.character.keys.Keymapping;
import SwordieX.client.party.Party;
import SwordieX.client.party.PartyMember;
import SwordieX.client.party.PartyResult;
import SwordieX.field.ClockPacket;
import SwordieX.field.fieldeffect.FieldEffect;
import SwordieX.overseas.extraequip.ExtraEquipResult;
import connection.OutPacket;
import connection.Packet;
import connection.packet.FieldPacket;
import connection.packet.OverseasPacket;
import connection.packet.UserRemote;
import connection.packet.WvsContext;
import io.netty.channel.Channel;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.DateUtil;
import tools.Pair;
import tools.Randomizer;
import tools.StringTool;
import tools.StringUtil;
import tools.Triple;
import tools.data.MaplePacketLittleEndianWriter;

public class MapleCharacter extends AnimatedMapleMapObject implements Serializable {
    private static final long serialVersionUID = 845748950829L;
    private static final Logger log = LoggerFactory.getLogger(MapleCharacter.class);
    private static int gachexp;
    private static boolean changed_soulcollection;
    private final List<MapleProcess> Process = new LinkedList();
    private final AtomicInteger cMapCount = new AtomicInteger(1);
    private final AtomicInteger vCoreSkillIndex = new AtomicInteger(0);
    private final AtomicLong exp = new AtomicLong();
    private final AtomicLong meso = new AtomicLong();
    private final AtomicLong killMonsterExp = new AtomicLong(0L);
    private final transient PlayerObservable playerObservable = new PlayerObservable(this, this);
    private final Map<Integer, MapleQuestStatus> quests;
    private final Map<Integer, Map<Integer, MapleQuestStatus>> worldAccountQuests = null;
    private final Map<Integer, Map<Integer, String>> worldAccountQuestInfo = null;
    private final Map<Integer, SkillEntry> skills;
    private final Map<Integer, SkillEntry> linkSkills;
    private final Map<Integer, Pair<Integer, SkillEntry>> sonOfLinkedSkills;
    private final transient ReentrantLock effectLock = new ReentrantLock();
    private final PlayerStats stats;
    private final MapleCharacterCards characterCard;
    private final EnumMap<MapleTraitType, MapleTrait> traits;
    private final transient Element elements = null;
    private final Point flamePoint = null;
    private final Long lastSavetime = 0L;
    private final List<Integer> damSkinList;
    private final List<MapleSummon> allLinksummon = new ArrayList();
    private final Map<Integer, Integer> atomsAttackRecords = new HashMap();
    private final ReentrantLock wreckagesLock = new ReentrantLock();
    private final List<Triple<Integer, Long, Point>> evanWreckages = new LinkedList();
    private final ReentrantLock cooldownLock = new ReentrantLock();
    private final CalcDamage calcDamage = new CalcDamage();
    private final Map<Integer, NpcShopBuyLimit> buyLimit = new HashMap();
    private final Map<Integer, NpcShopBuyLimit> accountBuyLimit = new HashMap();
    private final Map<Integer, Integer> soulCollection = new HashMap();
    private final Map<Integer, String> mobCollection = new LinkedHashMap();
    private final List<SecondaryStat> tempStatsToRemove = new ArrayList();
    private final AtomicInteger AIFamiliarID = new AtomicInteger(1);
    private final Lock saveLock = new ReentrantLock();
    private final List<InnerSkillEntry> tempInnerSkills = new ArrayList();
    private final Map<String, Object> tempValues = new HashMap();
    public int lightning;
    public int siphonVitality;
    public int armorSplit;
    public int PPoint;
    public int combination;
    public int killingpoint;
    public int stackbuff;
    public int combinationBuff;
    public int BULLET_SKILL_ID;
    public int SpectorGauge;
    public int LinkofArk;
    public int FlowofFight;
    public int Stigma;
    public int bulletParty;
    public int batt;
    public int clearWeb;
    public int forceBlood;
    public int fightJazzSkill;
    public int nextBlessSkill;
    public int empiricalStack;
    public int adelResonance;
    public int silhouetteMirage;
    public int repeatingCrossbowCatridge;
    public int dojoCoolTime;
    public int dojoStartTime;
    public int SerenStunGauge;
    public int cylindergauge;
    private String name;
    private String chalktext;
    private String BlessOfFairy_Origin;
    private String BlessOfEmpress_Origin;
    private String teleportname;
    private long lastChangeMapTime;
    private long lastfametime;
    private long keydown_skill;
    private long nextConsume;
    private long pqStartTime;
    private long lastSummonTime;
    private long mapChangeTime;
    private long lastFishingTime;
    private long lastFairyTime;
    private long lastmonsterCombo;
    private long lastHPTime;
    private long lastMPTime;
    private long lastExpirationTime;
    private long lastBlessOfDarknessTime;
    private byte gender;
    private byte initialSpawnPoint;
    private byte skinColor;
    private byte guildrank = 5;
    private byte allianceRank = 5;
    private byte world;
    private byte subcategory;
    private short mulung_energy;
    private short fatigue;
    private short hpApUsed;
    private short mpApUsed;
    private short job;
    private short remainingAp;
    private short scrolledPosition;
    private short availableCP;
    private short totalCP;
    private int level;
    private int accountid;
    private int id;
    private int hair;
    private int basecolor;
    private int addColor;
    private int baseProb;
    private int face;
    private int mapid;
    private int fame;
    private int pvpExp;
    private int pvpPoints;
    private int totalWins;
    private int totalLosses;
    private int hitcountbat;
    private int batcount;
    private int guildid = 0;
    private int fallcounter;
    private int itemEffect;
    private int itemEffectType;
    private int points;
    private int vpoints;
    private int criticalgrowth;
    private int rank = 1;
    private int rankMove = 0;
    private int jobRank = 1;
    private int jobRankMove = 0;
    private int marriageId;
    private int marriageItemId;
    private int touchedrune;
    private int monsterCombo;
    private int currentrep;
    private int totalrep;
    private int coconutteam;
    private int followid;
    private int challenge;
    private int guildContribution = 0;
    private int todayonlinetime;
    private int totalonlinetime;
    private int weaponPoint = -1;
    private PortableChair chair;
    private Point old;
    private int[] wishlist;
    private int[] rocks;
    private int[] savedLocations;
    private int[] regrocks;
    private int[] hyperrocks;
    private int[] remainingSp = new int[10];
    private transient AtomicInteger inst;
    private transient AtomicInteger insd;
    private List<Integer> lastmonthfameids;
    private List<Integer> lastmonthbattleids;
    private List<MechDoor> mechDoors;
    private MaplePet[] spawnPets;
    private transient List<MapleShopItem> rebuy;
    private MapleImp[] imps;
    private transient Set<MapleMonster> controlledMonsters;
    private transient Set<MapleMapObject> visibleMapObjects;
    private transient ReentrantReadWriteLock visibleMapObjectsLock;
    private transient ReentrantLock summonsLock;
    private transient Map<Integer, SkillCustomInfo> customInfo;
    private transient ReentrantLock controlMonsterLock;
    private transient ReentrantLock atomRecordsLock;
    private transient ReentrantReadWriteLock itemLock;
    private transient ReentrantLock addhpmpLock;
    private transient Lock rLCheck;
    private transient MapleAndroid android;
    private Map<Integer, String> questinfo;
    private Map<Integer, String> worldShareInfo;
    private Map<String, String> keyValue;
    private InnerSkillEntry[] innerSkills;
    private transient Map<SecondaryStat, List<SecondaryStatValueHolder>> effects;
    private transient Map<Integer, MapleCoolDownValueHolder> skillCooldowns;
    private transient List<MapleSummon> summons;
    private Map<ReportType, Integer> reports;
    private MonsterBook monsterbook;
    private CashShop cs;
    private BuddyList buddylist;
    private transient CheatTracker anticheat;
    private transient MapleClient client;
    private transient Party party;
    private transient MapleMap map;
    private transient MapleShop shop;
    private transient MapleDragon dragon;
    private transient MapleExtractor extractor;
    private transient RockPaperScissors rps;
    private MapleTrunk trunk;
    private transient MapleTrade trade;
    private MapleMount mount;
    private String BattleGrondJobName;
    private MapleMessenger messenger;
    private transient IMaplePlayerShop playerShop;
    private boolean invincible;
    private boolean canTalk;
    private boolean followinitiator;
    private boolean followon;
    private boolean smega;
    private boolean hasSummon;
    private MapleGuildCharacter mgc;
    private transient ScriptEvent eventInstance;
    private MapleInventory[] inventory;
    private SkillMacro[] skillMacros = new SkillMacro[5];
    private Map<Integer, FuncKeyMap> funcKeyMaps;
    private MapleQuickSlot quickslot;
    private transient ScheduledFuture<?> mapTimeLimitTask;
    private transient ScheduledFuture<?> chalkSchedule;
    private transient ScheduledFuture<?> questTimeLimitTask;
    private transient Event_PyramidSubway pyramidSubway = null;
    private transient List<Integer> pendingExpiration = null;
    private transient Map<Integer, SkillEntry> pendingSkills = null;
    private transient Map<Integer, Integer> linkMobs;
    private boolean changed_wishlist;
    private boolean changed_trocklocations;
    private boolean changed_skillmacros;
    private boolean changed_savedlocations;
    private boolean changed_questinfo;
    private boolean changed_worldshareinfo;
    private boolean changed_skills;
    private boolean changed_reports;
    private boolean changed_vcores;
    private boolean changed_innerSkills;
    private boolean changed_keyValue;
    private boolean changed_buylimit;
    private boolean changed_accountbuylimit;
    private boolean changed_mobcollection;
    private boolean changed_familiars;
    private int decorate;
    private boolean isbanned = false;
    private int beans;
    private int warning;
    private int reborns;
    private int reborns1;
    private int reborns2;
    private int reborns3;
    private int apstorage;
    private byte deathcount;
    private byte gmLevel;
    private byte secondgender;
    private byte secondSkinColor;
    private byte cardStack;
    private byte wolfscore;
    private byte sheepscore;
    private byte pandoraBoxFever;
    private byte fairyExp;
    private byte numClones;
    private int honor;
    private Timestamp createDate;
    private int love;
    private long lastlovetime;
    private Map<Integer, Long> lastdayloveids;
    private int playerPoints;
    private int playerEnergy;
    private transient MaplePvpStats pvpStats;
    private int pvpDeaths;
    private int pvpKills;
    private int pvpVictory;
    private MaplePotionPot potionPot;
    private boolean isSaveing;
    private PlayerSpecialStats specialStats;
    private Timestamp todayonlinetimestamp;
    private int mobKills;
    private long lastMobKillTime;
    private int flameMapId;
    private long totDamageToMob;
    private long lastFuWenTime;
    private int lastAttackSkillId;
    private int attackHit;
    private long lasttime = 0L;
    private long currenttime = 0L;
    private long deadtime = 300L;
    private transient ScheduledFuture<?> celebrityCrit;
    private transient Channel chatSession;
    private Map<String, Integer> credit;
    private int soulMP = 0;
    private long lastCheckProcess;
    private int friendshiptoadd;
    private int[] friendshippoints = new int[5];
    private MapleSigninStatus siginStatus;
    private List<Integer> effectSwitch;
    private List<MonsterFamiliar> familiars;
    private MonsterFamiliar summonedFamiliar;
    private volatile long logintime;
    private volatile int lastOnlineTime = -1;
    private WeakReference<MapleReactor> reactor = new WeakReference((Object)null);
    private transient MapleCarnivalParty carnivalParty;
    private transient Deque<MapleCarnivalChallenge> pendingCarnivalRequests;
    private boolean attclimit = false;
    private Map<Byte, List<Item>> extendedSlots;
    private long ltime = 0L;
    private Map<Integer, VCoreSkillEntry> vCoreSkills;
    private transient long lastUseVSkillTime = 0L;
    private int buffValue = 0;
    private long lastComboTime = 0L;
    private HiredFisher hiredFisher;
    private MapleUnion mapleUnion;
    private boolean changed_mapleUnion = false;
    private boolean scriptShop = false;
    private int burningChrType = 0;
    private long burningChrTime = -2L;
    private boolean overMobLevelTip = false;
    private int blackmagewb;
    private long lastSpawnBlindMobtime = System.currentTimeMillis();
    private int allreadyForceGet = 0;
    private transient ScheduledFuture<?> UltimateScheduled;
    private int allForce = 0;
    private int judgementStack;
    private int mobZoneState;
    private int larknessDiraction = 3;
    private int larkness;
    private boolean truthGate;
    private int soulSkillID;
    private short soulOption;
    private boolean showSoulEffect;
    private int maxSoulMP;
    private int linkMobObjectID;
    private boolean inGameCurNode;
    private Map<Integer, VMatrixSlot> vMatrixSlot = new TreeMap();
    private ActionBarField.MapleFieldActionBar actionBar;
    private MapleSkillPet skillPet = null;
    private int moonlightValue;
    private int sunlightValue;
    private long lastChangeFullSoulMP = 0L;
    private int antiMacroFails = 0;
    private long runeNextActionTime = 0L;
    private Map<Integer, List<Integer>> salon = new HashMap();
    private int[] runeStoneAction = null;
    private Map<Integer, Pair<Integer, Long>> fairys;
    private SpecialChairTW specialChairTW;
    private SpecialChair specialChair;
    private boolean stopComboKill = false;
    private List<TownPortal> townportals;
    private long townPortalLeaveTime;
    private int[] deathCounts;
    private final ScriptManager scriptManager = new ScriptManager(this);
    private final Map<Integer, MapleHexaStat> hexaStats = new HashMap();
    private final Map<Integer, MapleHexaSkill> hexaSkills = new HashMap();
    private boolean online;
    private int HowlingGaleCount;
    private int YoyoCount;
    private int WildGrenadierCount;
    private int VerseOfRelicsCount;
    private int BHGCCount;
    private int RandomPortal;
    private int fwolfattackcount;
    private int BlockCount;
    private int BlockCoin;
    private int MesoChairCount;
    private int tempmeso;
    private int eventcount;
    private int duskGauge;
    private long fwolfdamage;
    private long LastMovement;
    private long PlatformerStageEnter;
    private long AggressiveDamage;
    private boolean hasfwolfportal;
    private boolean isfwolfkiller;
    private boolean isWatchingWeb;
    private boolean oneMoreChance;
    private boolean isDuskBlind;
    private boolean eventkillmode;
    private int beholderSkill1;
    private int beholderSkill2;
    private transient Map<SecondaryStat, Pair<MobSkill, Integer>> diseases;
    private int moonGauge;
    private final List<String> scriptManagerDebug = new ArrayList();
    private Map<String, Object> variable = new ConcurrentHashMap();
    private int separation = 1;
    private boolean gmcooldown = false;
    private int autoAttack = -1;
    private ScheduledExecutorService timerInstance;

    protected MapleCharacter(boolean ChannelServer) {
        this.setStance(0);
        this.setPosition(new Point(0, 0));
        this.inventory = new MapleInventory[MapleInventoryType.values().length];
        MapleInventoryType[] var2 = MapleInventoryType.values();
        int i = var2.length;

        int var4;
        for(var4 = 0; var4 < i; ++var4) {
            MapleInventoryType type = var2[var4];
            this.inventory[type.ordinal()] = new MapleInventory(type);
        }

        this.keyValue = new ConcurrentHashMap();
        this.questinfo = new LinkedHashMap();
        this.worldShareInfo = new ConcurrentHashMap();
        this.quests = new LinkedHashMap();
        this.skills = new LinkedHashMap();
        this.linkSkills = new LinkedHashMap();
        this.sonOfLinkedSkills = new LinkedHashMap();
        this.innerSkills = new InnerSkillEntry[3];
        this.stats = new PlayerStats();
        this.characterCard = new MapleCharacterCards();

        for(i = 0; i < this.remainingSp.length; ++i) {
            this.remainingSp[i] = 0;
        }

        this.traits = new EnumMap(MapleTraitType.class);
        MapleTraitType[] var7 = MapleTraitType.values();
        i = var7.length;

        for(var4 = 0; var4 < i; ++var4) {
            MapleTraitType t = var7[var4];
            this.traits.put(t, new MapleTrait(t));
        }

        this.specialStats = new PlayerSpecialStats();
        this.specialStats.resetSpecialStats();
        this.familiars = new LinkedList();
        this.damSkinList = new ArrayList();
        if (ChannelServer) {
            this.isSaveing = false;
            this.changed_reports = false;
            this.changed_skills = false;
            this.changed_wishlist = false;
            this.changed_trocklocations = false;
            this.changed_skillmacros = false;
            this.changed_savedlocations = false;
            this.changed_questinfo = false;
            this.changed_innerSkills = false;
            this.changed_keyValue = false;
            this.changed_vcores = false;
            this.scrolledPosition = 0;
            this.criticalgrowth = 0;
            this.mulung_energy = 0;
            this.keydown_skill = 0L;
            this.nextConsume = 0L;
            this.pqStartTime = 0L;
            this.mapChangeTime = 0L;
            this.lastmonsterCombo = 0L;
            this.monsterCombo = 0;
            this.lastFishingTime = 0L;
            this.lastFairyTime = System.currentTimeMillis();
            this.fairys = new LinkedHashMap();
            this.lastHPTime = 0L;
            this.lastMPTime = 0L;
            this.lastExpirationTime = 0L;
            this.lastBlessOfDarknessTime = 0L;
            this.old = new Point(0, 0);
            this.coconutteam = 0;
            this.followid = 0;
            this.marriageItemId = 0;
            this.fallcounter = 0;
            this.challenge = 0;
            this.lastSummonTime = 0L;
            this.blackmagewb = 1;
            this.townPortalLeaveTime = -1L;
            this.hasSummon = false;
            this.invincible = false;
            this.clearWeb = 0;
            this.SerenStunGauge = 0;
            this.canTalk = true;
            this.moonGauge = 0;
            this.followinitiator = false;
            this.followon = false;
            this.rebuy = new ArrayList();
            this.linkMobs = new HashMap();
            this.reports = new EnumMap(ReportType.class);
            this.teleportname = "";
            this.smega = true;
            this.spawnPets = new MaplePet[3];
            this.wishlist = new int[12];
            this.duskGauge = 0;
            this.isDuskBlind = false;
            this.rocks = new int[10];
            this.regrocks = new int[5];
            this.hyperrocks = new int[13];
            this.imps = new MapleImp[3];
            this.friendshippoints = new int[5];
            this.extendedSlots = new HashMap();
            this.effects = new LinkedHashMap();
            this.skillCooldowns = new LinkedHashMap();
            this.inst = new AtomicInteger(0);
            this.insd = new AtomicInteger(-1);
            this.funcKeyMaps = new LinkedHashMap();
            this.quickslot = new MapleQuickSlot();
            this.townportals = new ArrayList();
            this.mechDoors = new ArrayList();
            this.itemLock = new ReentrantReadWriteLock();
            this.rLCheck = this.itemLock.readLock();
            this.controlledMonsters = new LinkedHashSet();
            this.controlMonsterLock = new ReentrantLock();
            this.summons = new LinkedList();
            this.summonsLock = new ReentrantLock();
            this.visibleMapObjects = new LinkedHashSet();
            this.visibleMapObjectsLock = new ReentrantReadWriteLock();
            this.addhpmpLock = new ReentrantLock();
            this.atomRecordsLock = new ReentrantLock();
            SavedLocationType[] slt_arr = SavedLocationType.values();
            this.savedLocations = new int[slt_arr[slt_arr.length - 1].getValue() + 1];

            for(i = 0; i < slt_arr.length; ++i) {
                this.savedLocations[i] = -1;
            }

            this.todayonlinetimestamp = new Timestamp(System.currentTimeMillis());
            this.credit = new LinkedHashMap();
            this.effectSwitch = new ArrayList();
            this.pendingCarnivalRequests = new LinkedList();
            this.vCoreSkills = new LinkedHashMap();
        }

    }

    public static MapleCharacter getDefault(MapleClient client, JobType type) {
        MapleCharacter ret = new MapleCharacter(false);
        ret.client = client;
        ret.map = null;
        ret.exp.set(0L);
        ret.job = (short)type.job.getId();
        ret.subcategory = (byte)type.job.getSub();
        ret.level = 1;
        ret.remainingAp = 0;
        ret.fame = 0;
        ret.love = 0;
        ret.accountid = client.getAccID();
        ret.buddylist = new BuddyList((byte)20);
        ret.burningChrType = ServerConfig.CREATE_CHAR_BURNING;
        if (ret.burningChrType > 0) {
            ret.burningChrTime = System.currentTimeMillis() + (long)(ServerConfig.CREATE_CHAR_BURNING_DAYS * 24 * 60 * 60) * 1000L;
        }

        ret.stats.str = 12;
        ret.stats.dex = 5;
        ret.stats.int_ = 4;
        ret.stats.luk = 4;
        ret.stats.maxhp = 50;
        ret.stats.hp = 50;
        ret.stats.maxmp = 50;
        ret.stats.mp = 50;
        gachexp = 0;
        ret.friendshiptoadd = 0;
        ret.friendshippoints = new int[]{0, 0, 0, 0, 0};

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
                ps.setInt(1, ret.accountid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    ret.client.setAccountName(rs.getString("name"));
                    ret.client.setMaplePoints(rs.getInt("mPoints"));
                    ret.points = rs.getInt("points");
                    ret.vpoints = rs.getInt("vpoints");
                }

                rs.close();
                ps.close();
            } catch (Throwable var7) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var8) {
            SQLException e = var8;
            System.err.println("Error getting character default" + String.valueOf(e));
        }

        return ret;
    }

    public static MapleCharacter ReconstructChr(CharacterTransfer ct, MapleClient client, boolean isChannel) {
        MapleCharacter ret = new MapleCharacter(true);
        ret.client = client;
        if (client != null) {
            client.setAccID(ct.accountid);
            client.setAccountName(ct.accountname);
            client.setMaplePoints(ct.maplePoint);
            client.setTempIP(ct.tempIP);
            client.setGmLevel(ct.gmLevel);
        }

        if (!isChannel && client != null) {
            ret.client.setChannel(ct.channel);
        }

        ret.id = ct.characterid;
        ret.name = ct.name;
        ret.level = ct.level;
        ret.fame = ct.fame;
        ret.love = ct.love;
        ret.stats.str = ct.str;
        ret.stats.dex = ct.dex;
        ret.stats.int_ = ct.int_;
        ret.stats.luk = ct.luk;
        ret.stats.maxhp = ct.maxhp;
        ret.stats.maxmp = ct.maxmp;
        ret.stats.hp = ct.hp;
        ret.stats.mp = ct.mp;
        ret.chalktext = ct.chalkboard;
        ret.exp.set(ret.level > ServerConfig.CHANNEL_PLAYER_MAXLEVEL ? 0L : ct.exp);
        ret.hpApUsed = ct.hpApUsed;
        ret.mpApUsed = ct.mpApUsed;
        ret.remainingSp = ct.remainingSp;
        ret.remainingAp = ct.remainingAp;
        ret.meso.set(ct.meso);
        ret.skinColor = ct.skinColor;
        ret.gender = ct.gender;
        ret.job = ct.job;
        ret.hair = ct.hair;
        ret.face = ct.face;
        ret.accountid = ct.accountid;
        ret.totalWins = ct.totalWins;
        ret.totalLosses = ct.totalLosses;
        ret.mapid = ct.mapid;
        ret.initialSpawnPoint = ct.initialSpawnPoint;
        ret.world = ct.world;
        ret.guildid = ct.guildid;
        ret.guildrank = ct.guildrank;
        ret.guildContribution = ct.guildContribution;
        ret.allianceRank = ct.alliancerank;
        ret.points = ct.points;
        ret.vpoints = ct.vpoints;
        ret.fairys = ct.fairys;
        ret.marriageId = ct.marriageId;
        ret.currentrep = ct.currentrep;
        ret.totalrep = ct.totalrep;
        gachexp = ct.gachexp;
        ret.pvpExp = ct.pvpExp;
        ret.pvpPoints = ct.pvpPoints;
        ret.decorate = ct.decorate;
        ret.beans = ct.beans;
        ret.warning = ct.warning;
        ret.reborns = ct.reborns;
        ret.reborns1 = ct.reborns1;
        ret.reborns2 = ct.reborns2;
        ret.reborns3 = ct.reborns3;
        ret.apstorage = ct.apstorage;
        ret.honor = ct.honor;
        ret.playerPoints = ct.playerPoints;
        ret.playerEnergy = ct.playerEnergy;
        ret.pvpDeaths = ct.pvpDeaths;
        ret.beholderSkill1 = 0;
        ret.beholderSkill2 = 0;
        ret.pvpKills = ct.pvpKills;
        ret.pvpVictory = ct.pvpVictory;
        if (ret.guildid > 0) {
            ret.mgc = new MapleGuildCharacter(ret);
        }

        ret.fatigue = ct.fatigue;
        ret.buddylist = new BuddyList(ct.buddysize);
        ret.subcategory = ct.subcategory;
        ret.friendshiptoadd = ct.friendshiptoadd;
        ret.friendshippoints = ct.friendshippoints;
        ret.soulMP = ct.soulcount;
        ret.inventory = ct.inventorys;
        ret.ltime = ct.ltime;
        int messengerid = ct.messengerid;
        if (messengerid > 0) {
            ret.messenger = WorldMessengerService.getInstance().getMessenger(messengerid);
        }

        int partyid = ct.partyid;
        if (partyid >= 0) {
            Party party = client.getWorld().getPartybyId(partyid);
            if (party != null && party.getPartyMemberByID(ret.id) != null) {
                ret.party = party;
            }
        }

        Iterator var7 = ct.Quest.entrySet().iterator();

        Map.Entry qs;
        while(var7.hasNext()) {
            qs = (Map.Entry)var7.next();
            MapleQuestStatus queststatus_from = (MapleQuestStatus)qs.getValue();
            queststatus_from.setQuest((Integer)qs.getKey());
            ret.quests.put((Integer)qs.getKey(), queststatus_from);
        }

        var7 = ct.skills.entrySet().iterator();

        while(var7.hasNext()) {
            qs = (Map.Entry)var7.next();
            ret.skills.put((Integer)qs.getKey(), (SkillEntry)qs.getValue());
            if (SkillConstants.isLinkSkills((Integer)qs.getKey()) && ret.linkSkills.size() < 12) {
                ret.linkSkills.put((Integer)qs.getKey(), (SkillEntry)qs.getValue());
            }
        }

        var7 = ct.traits.entrySet().iterator();

        while(var7.hasNext()) {
            qs = (Map.Entry)var7.next();
            ((MapleTrait)ret.traits.get(qs.getKey())).setExp((Integer)qs.getValue());
        }

        var7 = ct.reports.entrySet().iterator();

        while(var7.hasNext()) {
            qs = (Map.Entry)var7.next();
            ret.reports.put(ReportType.getById((Byte)qs.getKey()), (Integer)qs.getValue());
        }

        ct.sonOfLinedSkills.forEach((key, value) -> {
            ret.sonOfLinkedSkills.put(key, value);
        });
        ct.vcoreskills.forEach((key, value) -> {
            ret.vCoreSkills.put(ret.vCoreSkillIndex.getAndIncrement(), value);
        });
        ret.innerSkills = ct.innerSkills;
        ret.BlessOfFairy_Origin = ct.BlessOfFairy;
        ret.BlessOfEmpress_Origin = ct.BlessOfEmpress;
        ret.skillMacros = ct.skillmacro;
        ret.spawnPets = ct.spawnPets;
        ret.funcKeyMaps = ct.funcKeyMaps;
        ret.quickslot = new MapleQuickSlot(ct.quickslot);
        ret.keyValue = ct.KeyValue;
        ret.questinfo = ct.InfoQuest;
        ret.worldShareInfo = ct.worldShareInfo;
        ret.savedLocations = ct.savedlocation;
        ret.wishlist = ct.wishlist;
        ret.rocks = ct.rocks;
        ret.regrocks = ct.regrocks;
        ret.hyperrocks = ct.hyperrocks;
        ret.buddylist.loadFromTransfer(ct.buddies);
        ret.keydown_skill = 0L;
        ret.lastfametime = ct.lastfametime;
        ret.lastmonthfameids = ct.famedcharacters;
        ret.lastmonthbattleids = ct.battledaccs;
        ret.extendedSlots = ct.extendedSlots;
        ret.lastlovetime = ct.lastLoveTime;
        ret.lastdayloveids = ct.loveCharacters;
        ret.trunk = ct.storage;
        ret.pvpStats = ct.pvpStats;
        ret.potionPot = ct.potionPot;
        ret.specialStats = ct.SpecialStats;
        ret.cs = ct.cs;
        ret.imps = ct.imps;
        ret.anticheat = ct.anticheat;
        ret.anticheat.start(ret.getId());
        ret.rebuy = ct.rebuy;
        ret.mount = new MapleMount(ret, ct.mount_itemid, SkillConstants.getSkillByJob(1004, ret.job), ct.mount_Fatigue, ct.mount_level, ct.mount_exp);
        ret.todayonlinetime = ct.todayonlinetime;
        ret.totalonlinetime = ct.totalonlinetime;
        ret.weaponPoint = ct.weaponPoint;
        ret.credit = ct.credit;
        ret.effectSwitch = ct.effectSwitch;
        ret.familiars = ct.familiars;
        ret.mapleUnion = ct.mapleUnion;
        ret.summonedFamiliar = ct.summonedFamiliar;
        ret.vMatrixSlot = ct.vMatrixSlot;
        ret.lastOnlineTime = ct.onlineTime;
        ret.logintime = ct.loginTime;
        ret.soulMP = ct.soulMP;
        ret.soulSkillID = ct.soulSkillID;
        ret.soulOption = ct.soulOptionID;
        ret.maxSoulMP = ct.maxSoulMP;
        ret.salon = ct.salon;
        ret.burningChrType = ct.burningChrType;
        ret.burningChrTime = ct.burningChrTime;
        if (isChannel) {
            MapleMapFactory mapFactory = ChannelServer.getInstance(ct.channel).getMapFactory();
            ret.map = mapFactory.getMap(ret.mapid);
            if (ret.map == null) {
                ret.map = mapFactory.getMap(100000000);
            } else if (ret.map.getForcedReturnId() != 999999999 && ret.map.getForcedReturnMap() != null) {
                ret.map = ret.map.getForcedReturnMap();
            }

            MaplePortal portal = ret.map.getPortal(ret.initialSpawnPoint);
            if (portal == null) {
                portal = ret.map.getPortal(0);
                ret.initialSpawnPoint = 0;
            }

            ret.setPosition(portal.getPosition());
            ret.characterCard.loadCards(client, true);
            ret.stats.recalcLocalStats(true, ret);
        } else {
            ret.messenger = null;
        }

        return ret;
    }

    public static MapleCharacter loadCharFromDB(int charid, MapleClient client, boolean channelserver) {
        return loadCharFromDB(charid, client, channelserver, (Map)null);
    }

    public static MapleCharacter loadCharFromDB(int charid, MapleClient client, boolean channelServer, Map<Integer, CardData> cads) {
        MapleCharacter ret = new MapleCharacter(channelServer);
        ret.client = client;
        ret.id = charid;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                ps = con.prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();
                if (!rs.next()) {
                    rs.close();
                    ps.close();
                    throw new RuntimeException("加載角色失敗原因(角色沒有找到).");
                }

                ret.name = rs.getString("name");
                ret.level = rs.getShort("level");
                ret.fame = rs.getInt("fame");
                ret.love = rs.getInt("love");
                ret.stats.str = rs.getShort("str");
                ret.stats.dex = rs.getShort("dex");
                ret.stats.int_ = rs.getShort("int");
                ret.stats.luk = rs.getShort("luk");
                ret.job = rs.getShort("job");
                ret.stats.maxhp = rs.getInt("maxhp");
                ret.stats.maxmp = JobConstants.isNotMpJob(ret.job) ? GameConstants.getMPByJob(ret.job) : rs.getInt("maxmp");
                ret.stats.hp = rs.getInt("hp");
                ret.stats.mp = rs.getInt("mp");
                ret.exp.set(ret.level >= ServerConfig.CHANNEL_PLAYER_MAXLEVEL ? 0L : rs.getLong("exp"));
                ret.hpApUsed = rs.getShort("hpApUsed");
                ret.mpApUsed = rs.getShort("mpApUsed");
                String[] sp = rs.getString("sp").split(",");

                for(int i = 0; i < ret.remainingSp.length; ++i) {
                    ret.remainingSp[i] = Integer.parseInt(sp[i]);
                }

                ret.remainingAp = rs.getShort("ap");
                ret.meso.set(rs.getLong("meso"));
                ret.gender = rs.getByte("gender");
                byte skin = rs.getByte("skincolor");
                int hair = rs.getInt("hair");
                int face = rs.getInt("face");
                if (!MapleItemInformationProvider.getInstance().isSkinExist(skin)) {
                    skin = 0;
                }

                if (!MapleItemInformationProvider.getInstance().isHairExist(hair)) {
                    hair = ret.gender == 0 ? 30000 : 31000;
                }

                if (!MapleItemInformationProvider.getInstance().isFaceExist(face)) {
                    face = ret.gender == 0 ? 20000 : 21000;
                }

                ret.skinColor = skin;
                ret.hair = hair;
                ret.face = face;
                ret.accountid = rs.getInt("accountid");
                if (client != null) {
                    client.setAccID(ret.accountid);
                }

                ret.mapid = rs.getInt("map");
                if (client.getChannelServer().getMapFactory().getMap(ret.mapid) == null) {
                    ret.mapid = 950000100;
                }

                ret.initialSpawnPoint = rs.getByte("spawnpoint");
                ret.world = rs.getByte("world");
                ret.guildid = rs.getInt("guildid");
                ret.guildrank = rs.getByte("guildrank");
                ret.allianceRank = rs.getByte("allianceRank");
                ret.guildContribution = rs.getInt("guildContribution");
                ret.totalWins = rs.getInt("totalWins");
                ret.totalLosses = rs.getInt("totalLosses");
                ret.currentrep = rs.getInt("currentrep");
                ret.totalrep = rs.getInt("totalrep");
                if (ret.guildid > 0 && client != null) {
                    ret.mgc = new MapleGuildCharacter(ret);
                }

                gachexp = rs.getInt("gachexp");
                ret.buddylist = new BuddyList(rs.getByte("buddyCapacity"));
                ret.subcategory = rs.getByte("subcategory");
                ret.mount = new MapleMount(ret, 0, SkillConstants.getSkillByJob(1004, ret.job), (byte)0, (byte)1, 0);
                ret.rank = rs.getInt("rank");
                ret.rankMove = rs.getInt("rankMove");
                ret.jobRank = rs.getInt("jobRank");
                ret.jobRankMove = rs.getInt("jobRankMove");
                ret.marriageId = rs.getInt("marriageId");
                ret.fatigue = rs.getShort("fatigue");
                ret.pvpExp = rs.getInt("pvpExp");
                ret.pvpPoints = rs.getInt("pvpPoints");
                Iterator var13 = ret.traits.values().iterator();

                while(var13.hasNext()) {
                    MapleTrait t = (MapleTrait)var13.next();
                    t.setExp(rs.getInt(t.getType().name()));
                }

                ret.decorate = rs.getInt("decorate");
                ret.beans = rs.getInt("beans");
                ret.warning = rs.getInt("warning");
                ret.reborns = rs.getInt("reborns");
                ret.reborns1 = rs.getInt("reborns1");
                ret.reborns2 = rs.getInt("reborns2");
                ret.reborns3 = rs.getInt("reborns3");
                ret.apstorage = rs.getInt("apstorage");
                ret.honor = rs.getInt("honor");
                ret.playerPoints = rs.getInt("playerPoints");
                ret.playerEnergy = rs.getInt("playerEnergy");
                ret.pvpDeaths = rs.getInt("pvpDeaths");
                ret.pvpKills = rs.getInt("pvpKills");
                ret.pvpVictory = rs.getInt("pvpVictory");
                ret.todayonlinetime = rs.getInt("todayonlinetime");
                ret.totalonlinetime = rs.getInt("totalonlinetime");
                ret.weaponPoint = rs.getInt("wp");
                ret.friendshiptoadd = rs.getInt("friendshiptoadd");
                String[] points = rs.getString("friendshippoints").split(",");

                for(int i = 0; i < 5; ++i) {
                    ret.friendshippoints[i] = Integer.parseInt(points[i]);
                }

                int nBurnType = rs.getByte("burningChrType");
                Timestamp lBurnTime = rs.getTimestamp("burningChrTime");
                if (nBurnType >= 0 && lBurnTime != null && System.currentTimeMillis() < lBurnTime.getTime()) {
                    ret.burningChrType = nBurnType;
                    ret.burningChrTime = lBurnTime.getTime();
                }

                String[] pets = null;
                int partyid;
                int maxlevel_2;
                int maxlevel;
                int position;
                int i;
                int r;
                if (channelServer && client != null) {
                    ret.pvpStats = MaplePvpStats.loadOrCreateFromDB(ret.accountid);
                    ret.anticheat = new CheatTracker(ret.getId());
                    MapleMapFactory mapFactory = ChannelServer.getInstance(client.getChannel()).getMapFactory();
                    ret.map = mapFactory.getMap(ret.mapid);
                    if (ret.map == null) {
                        ret.map = mapFactory.getMap(100000000);
                    }

                    MaplePortal portal = ret.map.getPortal(ret.initialSpawnPoint);
                    if (portal == null) {
                        portal = ret.map.getPortal(0);
                        ret.initialSpawnPoint = 0;
                    }

                    ret.setPosition(portal.getPosition());
                    partyid = rs.getInt("party");
                    if (partyid >= 0) {
                        Party party = client.getWorld().getPartybyId(partyid);
                        if (party != null && party.getPartyMemberByID(ret.id) != null) {
                            ret.party = party;
                        }
                    }

                    pets = rs.getString("pets").split(",");
                    List<Integer> salon_hair = new LinkedList();
                    maxlevel_2 = Math.min(102, Math.max(rs.getInt("salon_hair"), 3));

                    for(maxlevel = 0; maxlevel < maxlevel_2; ++maxlevel) {
                        salon_hair.add(0);
                    }

                    ret.salon.put(3, salon_hair);
                    List<Integer> salon_face = new LinkedList();
                    position = Math.min(102, Math.max(rs.getInt("salon_face"), 3));

                    for(i = 0; i < position; ++i) {
                        salon_face.add(0);
                    }

                    ret.salon.put(2, salon_face);
                    List<Integer> salon_skin = new LinkedList();
                    i = Math.min(6, Math.max(rs.getInt("salon_skin"), 0));

                    for(r = 0; r < i; ++r) {
                        salon_skin.add(0);
                    }

                    ret.salon.put(1, salon_skin);
                    rs.close();
                    ps.close();
                    ps = con.prepareStatement("SELECT * FROM reports WHERE characterid = ?");
                    ps.setInt(1, charid);
                    rs = ps.executeQuery();

                    while(rs.next()) {
                        if (ReportType.getById(rs.getByte("type")) != null) {
                            ret.reports.put(ReportType.getById(rs.getByte("type")), rs.getInt("count"));
                        }
                    }

                    ret.setLTime();
                }

                rs.close();
                ps.close();
                if (cads != null) {
                    ret.characterCard.setCards(cads);
                } else if (client != null) {
                    ret.characterCard.loadCards(client, channelServer);
                }

                ps = con.prepareStatement("SELECT * FROM character_keyvalue WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();

                while(rs.next()) {
                    if (rs.getString("key") != null) {
                        ret.keyValue.put(rs.getString("key"), rs.getString("value"));
                    }
                }

                rs.close();
                ps.close();
                ps = con.prepareStatement("SELECT * FROM questinfo WHERE characterid = ?");
                ps.setInt(1, charid);
                rs = ps.executeQuery();

                while(rs.next()) {
                    ret.questinfo.put(rs.getInt("quest"), rs.getString("customData"));
                }

                rs.close();
                ps.close();
                ps = con.prepareStatement("SELECT * FROM `accounts_questinfo` WHERE `accounts_id` = ? AND `world` = ?");
                ps.setInt(1, ret.accountid);
                ps.setInt(2, ret.world);
                rs = ps.executeQuery();

                label2104:
                while(true) {
                    if (!rs.next()) {
                        rs.close();
                        ps.close();
                        Iterator var75;
                        Pair mit;
                        if (channelServer) {
                            ps = con.prepareStatement("SELECT * FROM queststatus WHERE account = ? AND world = ?");
                            ps.setInt(1, ret.accountid);
                            ps.setInt(2, ret.world);
                            rs = ps.executeQuery();
                            PreparedStatement pse = con.prepareStatement("SELECT * FROM queststatusmobs WHERE queststatusid = ?");

                            while(true) {
                                MapleQuest q;
                                MapleQuestStatus status;
                                long cTime;
                                ResultSet rse;
                                byte stat;

                                do {
                                    do {
                                        if (!rs.next()) {
                                            rs.close();
                                            ps.close();
                                            ps = con.prepareStatement("SELECT * FROM queststatus WHERE characterid = ? AND account IS NULL");
                                            ps.setInt(1, charid);
                                            rs = ps.executeQuery();

                                            while(true) {
                                                do {
                                                    do {
                                                        do {
                                                            if (!rs.next()) {
                                                                rs.close();
                                                                ps.close();
                                                                pse.close();
                                                                ps = con.prepareStatement("SELECT * FROM inventoryslot WHERE characters_id = ?");
                                                                ps.setInt(1, charid);
                                                                rs = ps.executeQuery();
                                                                if (!rs.next()) {
                                                                    rs.close();
                                                                    ps.close();
                                                                    throw new RuntimeException("No Inventory slot column found in SQL. [inventoryslot]");
                                                                }

                                                                ret.getInventory(MapleInventoryType.EQUIP).setSlotLimit(rs.getShort("equip"));
                                                                ret.getInventory(MapleInventoryType.USE).setSlotLimit(rs.getShort("use"));
                                                                ret.getInventory(MapleInventoryType.SETUP).setSlotLimit(rs.getShort("setup"));
                                                                ret.getInventory(MapleInventoryType.ETC).setSlotLimit(rs.getShort("etc"));
                                                                ret.getInventory(MapleInventoryType.CASH).setSlotLimit(rs.getShort("cash"));
                                                                ret.getInventory(MapleInventoryType.DECORATION).setSlotLimit(rs.getShort("decoration"));
                                                                ps.close();
                                                                rs.close();

                                                                for(byte i1 = 2; i1 <= 4; ++i1) {
                                                                    ret.extendedSlots.put(i1, new ArrayList());
                                                                }

                                                                var75 = ItemLoader.裝備道具.loadItems(false, (long)charid).values().iterator();

                                                                while(var75.hasNext()) {
                                                                    mit = (Pair)var75.next();
                                                                    ret.getInventory((MapleInventoryType)mit.getRight()).addFromDB((Item)mit.getLeft());
                                                                    Object var92 = mit.getLeft();
                                                                    if (var92 instanceof Equip) {
                                                                        Equip eqp = (Equip)var92;
                                                                        if (類型.秘法符文(eqp.getItemId())) {
                                                                            eqp.recalcArcStat(ret.job);
                                                                        }

                                                                        if (類型.真實符文(eqp.getItemId())) {
                                                                            eqp.recalcAutStat(ret.job);
                                                                        }
                                                                    }

                                                                    if (((Item)mit.getLeft()).getExtendSlot() > 0) {
                                                                        ((List)ret.extendedSlots.get(((MapleInventoryType)mit.getRight()).getType())).add((Item)mit.getLeft());
                                                                    }
                                                                }

                                                                int maxlevel_;
                                                                if (pets != null) {
                                                                    MapleQuestStatus mapleQuestStatus = ret.getQuestNAdd(MapleQuest.getInstance(122902));
                                                                    String[] var86 = pets;
                                                                    partyid = pets.length;

                                                                    for(maxlevel_ = 0; maxlevel_ < partyid; ++maxlevel_) {
                                                                        String p = var86[maxlevel_];
                                                                        Item item = ret.getInventory(MapleInventoryType.CASH).getItem(Short.parseShort(p));
                                                                        if (item == null || item.getPet() == null || !類型.寵物(item.getItemId())) {
                                                                            break;
                                                                        }

                                                                        ret.addSpawnPet(item.getPet());
                                                                        item.getPet().setPos(new Point(ret.getPosition()));
                                                                        item.getPet().setCanPickup(mapleQuestStatus.getCustomData() == null || mapleQuestStatus.getCustomData().equals("1"));
                                                                    }
                                                                }

                                                                ps = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
                                                                ps.setInt(1, ret.accountid);
                                                                rs = ps.executeQuery();
                                                                if (rs.next()) {
                                                                    ret.getClient().setAccountName(rs.getString("name"));
                                                                    ret.getClient().setGmLevel(rs.getInt("gm"));
                                                                    ret.getClient().setMaplePoints(rs.getInt("mPoints"));
                                                                    ret.getClient().setSecondPassword(rs.getString("2ndpassword"));
                                                                    if (ret.getClient().getSecondPassword() != null && rs.getString("salt2") != null) {
                                                                        ret.getClient().setSecondPassword(LoginCrypto.rand_r(ret.getClient().getSecondPassword()));
                                                                    }

                                                                    ret.getClient().setSalt2(rs.getString("salt2"));
                                                                    ret.points = rs.getInt("points");
                                                                    ret.vpoints = rs.getInt("vpoints");
                                                                    if (rs.getTimestamp("lastlogon") != null) {
                                                                        Calendar cal = Calendar.getInstance();
                                                                        cal.setTimeInMillis(rs.getTimestamp("lastlogon").getTime());
                                                                        if (cal.get(7) + 1 == Calendar.getInstance().get(7)) {
                                                                        }
                                                                    }

                                                                    if (rs.getInt("banned") > 0) {
                                                                        rs.close();
                                                                        ps.close();
                                                                        ret.getClient().getSession().close();
                                                                        throw new RuntimeException("加載的角色為封號狀態，服務端斷開這個連接...");
                                                                    }

                                                                    rs.close();
                                                                    ps.close();
                                                                    ps = con.prepareStatement("UPDATE accounts SET lastlogon = CURRENT_TIMESTAMP() WHERE id = ?");
                                                                    ps.setInt(1, ret.accountid);
                                                                    ps.executeUpdate();
                                                                } else {
                                                                    rs.close();
                                                                }

                                                                ps.close();
                                                                ps = con.prepareStatement("SELECT * FROM `vmatrixslot` WHERE `characters_id` = ?");
                                                                ps.setInt(1, charid);
                                                                rs = ps.executeQuery();

                                                                while(rs.next()) {
                                                                    VMatrixSlot slot = new VMatrixSlot();
                                                                    slot.setExtend(rs.getInt("extend"));
                                                                    slot.setSlot(rs.getInt("slot"));
                                                                    slot.setUnlock(rs.getInt("unlock"));
                                                                    ret.getVMatrixSlot().put(slot.getSlot(), slot);
                                                                }

                                                                for(i = 0; i < VMatrixOption.EquipSlotMax; ++i) {
                                                                    ret.getVMatrixSlot().putIfAbsent(i, new VMatrixSlot(i));
                                                                }

                                                                rs.close();
                                                                ps.close();
                                                                ps = con.prepareStatement("SELECT * FROM vcoreskill WHERE characterid = ?");
                                                                ps.setInt(1, charid);
                                                                rs = ps.executeQuery();

                                                                while(rs.next()) {
                                                                    ret.addVCoreSkill(new VCoreSkillEntry(rs.getInt("vcoreid"), rs.getInt("level"), rs.getInt("exp"), rs.getInt("skill1"), rs.getInt("skill2"), rs.getInt("skill3"), -1L, rs.getInt("slot"), rs.getInt("index")));
                                                                }

                                                                rs.close();
                                                                ps.close();
                                                                ps = con.prepareStatement("SELECT * FROM skills WHERE characterid = ?");
                                                                ps.setInt(1, charid);
                                                                rs = ps.executeQuery();
                                                                int phantom = 0;
                                                                boolean resetSkill = false;

                                                                while(true) {
                                                                    while(true) {
                                                                        int reg;
                                                                        Skill skil;
                                                                        byte rank;
                                                                        while(rs.next()) {
                                                                            maxlevel_ = rs.getInt("skillid");
                                                                            maxlevel_2 = maxlevel_ / 10000;
                                                                            skil = SkillFactory.getSkill(maxlevel_);
                                                                            maxlevel = rs.getInt("skilllevel");
                                                                            rank = rs.getByte("masterlevel");
                                                                            i = rs.getInt("teachId");
                                                                            i = rs.getInt("teachTimes");
                                                                            position = rs.getByte("position");
                                                                            if (skil != null && SkillConstants.isApplicableSkill(maxlevel_) && (maxlevel <= skil.getMaxLevel() || maxlevel_ >= 92000000 && maxlevel_ <= 99999999) && rank <= skil.getMaxLevel()) {
                                                                                if (skil.is老技能()) {
                                                                                    ret.changed_skills = true;
                                                                                } else if (position >= 0 && position < 15 && phantom < 15) {
                                                                                    if (JobConstants.is幻影俠盜(ret.job) && skil.getSkillByJobBook() != -1) {
                                                                                        rank = skil.isFourthJob() ? (byte)skil.getMasterLevel() : 0;
                                                                                        byte position1 = (byte) position;
                                                                                        ret.skills.put(maxlevel_, new SkillEntry(maxlevel, rank, -1L, i, i, position1));
                                                                                    }

                                                                                    ++phantom;
                                                                                } else {
                                                                                    if (skil.isLinkSkills() && ret.linkSkills.size() < 12) {
                                                                                        maxlevel = SkillConstants.getLinkSkillslevel(skil, i, ret.level);
                                                                                        ret.linkSkills.put(maxlevel_, new SkillEntry(maxlevel, rank, rs.getLong("expiration"), i, i));
                                                                                    } else if (skil.isTeachSkills()) {
                                                                                        maxlevel = SkillConstants.getLinkSkillslevel(skil, i, ret.level);
                                                                                    } else if (skil.getFixLevel() > 0) {
                                                                                        maxlevel = skil.getFixLevel();
                                                                                    } else if (maxlevel_2 >= 40000 && maxlevel_2 <= 40005) {
                                                                                        if (ret.getVCoreSkillLevel(maxlevel_) <= 0) {
                                                                                            ret.changed_skills = true;
                                                                                            continue;
                                                                                        }

                                                                                        maxlevel = ret.getVCoreSkillLevel(maxlevel_);
                                                                                    }

                                                                                    ret.skills.put(maxlevel_, new SkillEntry(maxlevel, rank, rs.getLong("expiration"), i, i));
                                                                                }
                                                                            } else if (!resetSkill && (skil == null || skil.is老技能() || maxlevel > skil.getMaxLevel() || rank > skil.getMaxLevel()) && !JobConstants.notNeedSPSkill(maxlevel_2) && JobConstants.isSameJob(ret.job, maxlevel_2)) {
                                                                                reg = JobConstants.getJobNumber(maxlevel_2);
                                                                                if (reg > 0 && reg < 5) {
                                                                                    resetSkill = true;
                                                                                }
                                                                            }
                                                                        }

                                                                        rs.close();
                                                                        ps.close();
                                                                        if (resetSkill) {
                                                                            ret.spReset(false);
                                                                        }

                                                                        int hyper;
                                                                        if (client != null) {
                                                                            ps = con.prepareStatement("SELECT id, level FROM characters WHERE accountid = ? AND world = ?");
                                                                            ps.setInt(1, ret.accountid);
                                                                            ps.setInt(2, ret.world);
                                                                            rs = ps.executeQuery();

                                                                            while(rs.next()) {
                                                                                maxlevel_ = rs.getInt("id");
                                                                                maxlevel_2 = rs.getInt("level");
                                                                                PreparedStatement psee = con.prepareStatement("SELECT skillid, masterlevel, expiration ,teachId, teachTimes FROM skills WHERE characterid = ?");

                                                                                try {
                                                                                    psee.setInt(1, maxlevel_);
                                                                                    rse = psee.executeQuery();

                                                                                    try {
                                                                                        while(rse.next()) {
                                                                                            i = SkillConstants.getLinkSkillId(rse.getInt("skillid"));
                                                                                            Skill skill = SkillFactory.getSkill(rse.getInt("skillid"));
                                                                                            r = rse.getInt("teachId");
                                                                                            reg = rse.getInt("teachTimes");
                                                                                            hyper = SkillConstants.getLinkSkillslevel(skill, r, maxlevel_2);
                                                                                            if (skill != null && skill.isTeachSkills() && hyper > 0) {
                                                                                                byte masterlevel = rse.getByte("masterlevel");
                                                                                                ret.sonOfLinkedSkills.put(i, new Pair(maxlevel_, new SkillEntry(hyper, masterlevel, rse.getLong("expiration") > 0L && System.currentTimeMillis() < rse.getLong("expiration") + 86400000L ? rse.getLong("expiration") : -2L, r > 0 ? r : maxlevel_, reg)));
                                                                                            }
                                                                                        }
                                                                                    } catch (Throwable var63) {
                                                                                        if (rse != null) {
                                                                                            try {
                                                                                                rse.close();
                                                                                            } catch (Throwable var60) {
                                                                                                var63.addSuppressed(var60);
                                                                                            }
                                                                                        }

                                                                                        throw var63;
                                                                                    }

                                                                                    if (rse != null) {
                                                                                        rse.close();
                                                                                    }
                                                                                } catch (Throwable var64) {
                                                                                    if (psee != null) {
                                                                                        try {
                                                                                            psee.close();
                                                                                        } catch (Throwable var59) {
                                                                                            var64.addSuppressed(var59);
                                                                                        }
                                                                                    }

                                                                                    throw var64;
                                                                                }

                                                                                if (psee != null) {
                                                                                    psee.close();
                                                                                }
                                                                            }
                                                                        }

                                                                        rs.close();
                                                                        ps.close();
                                                                        ret.mapleUnion = new MapleUnion();
                                                                        ps = con.prepareStatement("SELECT `id`,`job`,`level`,`name` FROM characters WHERE accountid = ? ORDER BY level DESC");
                                                                        ps.setInt(1, ret.accountid);
                                                                        rs = ps.executeQuery();

                                                                        while(rs.next()) {
                                                                            maxlevel_ = rs.getInt("id");
                                                                            short job = rs.getShort("job");
                                                                            short level = rs.getShort("level");
                                                                            String name = rs.getString("name");
                                                                            if (level >= (JobConstants.is神之子(job) ? 130 : 60)) {
                                                                                ret.mapleUnion.getAllUnions().put(maxlevel_, new MapleUnionEntry(maxlevel_, name, level, job));
                                                                            }
                                                                        }

                                                                        ps = con.prepareStatement("SELECT `characters_id`, `type`, `rotate`, `boardindex`, `local` FROM `mapleunion` WHERE `accounts_id` = ? AND `world` = ?");
                                                                        ps.setInt(1, ret.accountid);
                                                                        ps.setInt(2, ret.world);
                                                                        rs = ps.executeQuery();

                                                                        while(rs.next()) {
                                                                            maxlevel_ = rs.getInt("characters_id");
                                                                            MapleUnionEntry union = (MapleUnionEntry)ret.mapleUnion.getAllUnions().get(maxlevel_);
                                                                            if (union != null) {
                                                                                maxlevel = rs.getInt("boardindex");
                                                                                position = rs.getInt("rotate");
                                                                                i = rs.getInt("local");
                                                                                i = rs.getInt("type");
                                                                                if (maxlevel > -1) {
                                                                                    MapleUnionEntry union2 = new MapleUnionEntry(maxlevel_, union.getName(), union.getLevel(), union.getJob());
                                                                                    union2.setBoardIndex(maxlevel);
                                                                                    union2.setRotate(position);
                                                                                    union2.setLocal(i);
                                                                                    union2.setType(i);
                                                                                    ret.mapleUnion.getFightingUnions().put(maxlevel_, union2);
                                                                                }
                                                                            }
                                                                        }

                                                                        ps = con.prepareStatement("SELECT skillid, skilllevel, position, `rank` FROM innerskills WHERE characterid = ? LIMIT 3");
                                                                        ps.setInt(1, charid);
                                                                        rs = ps.executeQuery();

                                                                        while(rs.next()) {
                                                                            maxlevel_ = rs.getInt("skillid");
                                                                            skil = SkillFactory.getSkill(maxlevel_);
                                                                            maxlevel_2 = rs.getInt("skilllevel");

                                                                            byte positionByte = rs.getByte("position");
                                                                            rank = (byte)Math.min(Math.max(rs.getByte("rank"), 0), 3);
                                                                            if (skil != null && skil.isInnerSkill() && positionByte >= 1 && positionByte <= 3) {
                                                                                if (maxlevel_2 > skil.getMaxLevel()) {
                                                                                    maxlevel_2 = (byte)skil.getMaxLevel();
                                                                                }

                                                                                InnerSkillEntry InnerSkill = new InnerSkillEntry(maxlevel_, maxlevel_2, positionByte, rank, false);
                                                                                ret.innerSkills[positionByte - 1] = InnerSkill;
                                                                            }
                                                                        }

                                                                        rs.close();
                                                                        ps.close();
                                                                        ps = con.prepareStatement("SELECT * FROM characters WHERE accountid = ? AND id <> ? ORDER BY level DESC");
                                                                        ps.setInt(1, ret.accountid);
                                                                        ps.setInt(2, ret.id);
                                                                        rs = ps.executeQuery();
                                                                        maxlevel_ = 0;
                                                                        maxlevel_2 = 0;

                                                                        while(true) {
                                                                            do {
                                                                                do {
                                                                                    if (!rs.next()) {
                                                                                        if (ret.BlessOfFairy_Origin == null) {
                                                                                            ret.BlessOfFairy_Origin = ret.name;
                                                                                        }

                                                                                        maxlevel = JobConstants.getBOF_ForJob(ret.job);
                                                                                        ret.skills.put(maxlevel, new SkillEntry(maxlevel_, 0, -1L, 0, 0));
                                                                                        if (SkillFactory.getSkill(JobConstants.getEmpress_ForJob(ret.job)) != null) {
                                                                                            if (ret.BlessOfEmpress_Origin == null) {
                                                                                                ret.BlessOfEmpress_Origin = ret.BlessOfFairy_Origin;
                                                                                            }

                                                                                            ret.skills.put(JobConstants.getEmpress_ForJob(ret.job), new SkillEntry(maxlevel_2, 0, -1L, 0, 0));
                                                                                        }

                                                                                        ps.close();
                                                                                        rs.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM skillmacros WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);

                                                                                        SkillMacro macro;
                                                                                        for(rs = ps.executeQuery(); rs.next(); ret.skillMacros[position] = macro) {
                                                                                            position = rs.getInt("position");
                                                                                            macro = new SkillMacro(rs.getInt("skill1"), rs.getInt("skill2"), rs.getInt("skill3"), rs.getString("name"), rs.getInt("shout"), position);
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM familiars WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            i = ret.getAIFamiliarID();
                                                                                            MonsterFamiliar monsterFamiliar = new MonsterFamiliar(i, rs.getInt("familiar"), client.getAccID(), charid, rs.getString("name"), rs.getByte("grade"), rs.getByte("level"), rs.getInt("exp"), rs.getInt("skillid"), rs.getInt("option1"), rs.getInt("option2"), rs.getInt("option3"), rs.getByte("summon") != 0, rs.getByte("lock") != 0);
                                                                                            ret.getFamiliars().add(monsterFamiliar);
                                                                                            if (monsterFamiliar.isSummoned()) {
                                                                                                monsterFamiliar.initPad();
                                                                                                ret.initFamiliar(monsterFamiliar);
                                                                                            }
                                                                                        }

                                                                                        for(i = 0; i < 3; ++i) {
                                                                                            ret.funcKeyMaps = FuncKeyMap.load(con, charid, i, ret.funcKeyMaps);
                                                                                        }

                                                                                        ps = con.prepareStatement("SELECT `index`, `key` FROM quickslot WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();
                                                                                        List<Pair<Integer, Integer>> quickslots = ret.quickslot.Layout();

                                                                                        while(rs.next()) {
                                                                                            quickslots.add(new Pair(rs.getInt("index"), rs.getInt("key")));
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ret.quickslot.unchanged();
                                                                                        ps = con.prepareStatement("SELECT `locationtype`,`map` FROM savedlocations WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            i = rs.getInt("locationtype");
                                                                                            if (i >= 0 && i < ret.savedLocations.length) {
                                                                                                ret.savedLocations[i] = rs.getInt("map");
                                                                                            }
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT `characterid_to`,`when` FROM famelog WHERE characterid = ? AND DATEDIFF(NOW(),`when`) < 30");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();
                                                                                        ret.lastfametime = 0L;
                                                                                        ret.lastmonthfameids = new ArrayList(31);

                                                                                        while(rs.next()) {
                                                                                            ret.lastfametime = Math.max(ret.lastfametime, rs.getTimestamp("when").getTime());
                                                                                            ret.lastmonthfameids.add(rs.getInt("characterid_to"));
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT `characterid_to`,`when` FROM lovelog WHERE characterid = ? AND DATEDIFF(NOW(),`when`) < 1");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();
                                                                                        ret.lastlovetime = 0L;
                                                                                        ret.lastdayloveids = new LinkedHashMap();

                                                                                        while(rs.next()) {
                                                                                            ret.lastlovetime = Math.max(ret.lastlovetime, rs.getTimestamp("when").getTime());
                                                                                            ret.lastdayloveids.put(rs.getInt("characterid_to"), rs.getTimestamp("when").getTime());
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT `accid_to`,`when` FROM battlelog WHERE accid = ? AND DATEDIFF(NOW(),`when`) < 30");
                                                                                        ps.setInt(1, ret.accountid);
                                                                                        rs = ps.executeQuery();
                                                                                        ret.lastmonthbattleids = new ArrayList();

                                                                                        while(rs.next()) {
                                                                                            ret.lastmonthbattleids.add(rs.getInt("accid_to"));
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ret.buddylist.loadFromDb(charid);
                                                                                        ret.trunk = MapleTrunk.loadOrCreateFromDB(ret.accountid);
                                                                                        ret.cs = new CashShop(ret.accountid, charid, ret.getJob());
                                                                                        ps = con.prepareStatement("SELECT sn FROM wishlist WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        for(i = 0; rs.next(); ++i) {
                                                                                            ret.wishlist[i] = rs.getInt("sn");
                                                                                        }

                                                                                        while(i < 12) {
                                                                                            ret.wishlist[i] = 0;
                                                                                            ++i;
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT mapid,vip FROM trocklocations WHERE characterid = ? LIMIT 28");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();
                                                                                        r = 0;
                                                                                        reg = 0;
                                                                                        hyper = 0;

                                                                                        while(rs.next()) {
                                                                                            if (rs.getInt("vip") == 0) {
                                                                                                ret.regrocks[reg] = rs.getInt("mapid");
                                                                                                ++reg;
                                                                                            } else if (rs.getInt("vip") == 1) {
                                                                                                ret.rocks[r] = rs.getInt("mapid");
                                                                                                ++r;
                                                                                            } else if (rs.getInt("vip") == 2) {
                                                                                                ret.hyperrocks[hyper] = rs.getInt("mapid");
                                                                                                ++hyper;
                                                                                            }
                                                                                        }

                                                                                        while(reg < 5) {
                                                                                            ret.regrocks[reg] = 999999999;
                                                                                            ++reg;
                                                                                        }

                                                                                        while(r < 10) {
                                                                                            ret.rocks[r] = 999999999;
                                                                                            ++r;
                                                                                        }

                                                                                        while(hyper < 13) {
                                                                                            ret.hyperrocks[hyper] = 999999999;
                                                                                            ++hyper;
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM imps WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        for(r = 0; rs.next(); ++r) {
                                                                                            ret.imps[r] = new MapleImp(rs.getInt("itemid"));
                                                                                            ret.imps[r].setLevel(rs.getByte("level"));
                                                                                            ret.imps[r].setState(rs.getByte("state"));
                                                                                            ret.imps[r].setCloseness(rs.getShort("closeness"));
                                                                                            ret.imps[r].setFullness(rs.getShort("fullness"));
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM mountdata WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();
                                                                                        if (!rs.next()) {
                                                                                            throw new RuntimeException("在數據庫中沒有找到角色的坐騎信息...");
                                                                                        }

                                                                                        Item mount = ret.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-18);
                                                                                        ret.mount = new MapleMount(ret, mount != null ? mount.getItemId() : 0, 80001000, rs.getByte("Fatigue"), rs.getByte("Level"), rs.getInt("Exp"));
                                                                                        ps.close();
                                                                                        rs.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM character_potionpots WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();
                                                                                        if (rs.next()) {
                                                                                            ret.potionPot = new MaplePotionPot(charid, rs.getInt("itemId"), rs.getInt("hp"), rs.getInt("mp"), rs.getInt("maxValue"), rs.getLong("startDate"), rs.getLong("endDate"));
                                                                                        }

                                                                                        ps.close();
                                                                                        rs.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM character_credit WHERE characterid = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            ret.credit.put(rs.getString("name"), rs.getInt("value"));
                                                                                        }

                                                                                        ps.close();
                                                                                        rs.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM effectswitch WHERE `characterid` = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            ret.effectSwitch.add(rs.getInt("pos"));
                                                                                        }

                                                                                        ps.close();
                                                                                        rs.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM salon WHERE characterid = ? ORDER BY position");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            int type = rs.getInt("type");
                                                                                            if (ret.salon.containsKey(type)) {
                                                                                                int pos = rs.getInt("position");
                                                                                                ((List)ret.salon.get(type)).remove(pos);
                                                                                                ((List)ret.salon.get(type)).add(pos, rs.getInt("itemId"));
                                                                                            }
                                                                                        }

                                                                                        ps.close();
                                                                                        rs.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM `characters_buylimit` WHERE `characters_id` = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            ((NpcShopBuyLimit)ret.buyLimit.computeIfAbsent(rs.getInt("shop_id"), NpcShopBuyLimit::new)).update(rs.getInt("itemid"), rs.getInt("count"), rs.getTimestamp("data").getTime());
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM `accounts_buylimit` WHERE `account_id` = ? AND world = ?");
                                                                                        ps.setInt(1, ret.accountid);
                                                                                        ps.setInt(2, ret.world);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            ((NpcShopBuyLimit)ret.accountBuyLimit.computeIfAbsent(rs.getInt("shop_id"), NpcShopBuyLimit::new)).update(rs.getInt("itemid"), rs.getInt("count"), rs.getTimestamp("data").getTime());
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT * FROM `character_soulcollection` WHERE `characters_id` = ?");
                                                                                        ps.setInt(1, charid);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            ret.soulCollection.put(rs.getInt("page"), rs.getInt("setsoul"));
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        ps = con.prepareStatement("SELECT `recordid`, `data` FROM `accounts_mobcollection` WHERE `accounts_id` = ? AND `world` = ?");
                                                                                        ps.setInt(1, ret.accountid);
                                                                                        ps.setInt(2, ret.world);
                                                                                        rs = ps.executeQuery();

                                                                                        while(rs.next()) {
                                                                                            ret.mobCollection.put(rs.getInt("recordid"), rs.getString("data"));
                                                                                        }

                                                                                        rs.close();
                                                                                        ps.close();
                                                                                        Iterator var114 = ret.getVCoreSkill().entrySet().iterator();

                                                                                        while(true) {
                                                                                            while(true) {
                                                                                                VCoreSkillEntry vcore;
                                                                                                Map.Entry vcores;
                                                                                                do {
                                                                                                    do {
                                                                                                        if (!var114.hasNext()) {
                                                                                                            ret.stats.recalcLocalStats(true, ret);
                                                                                                            break label2104;
                                                                                                        }

                                                                                                        vcores = (Map.Entry)var114.next();
                                                                                                        vcore = (VCoreSkillEntry)vcores.getValue();
                                                                                                    } while(vcore == null);
                                                                                                } while(vcore.getSlot() != 2);

                                                                                                VMatrixSlot slot = (VMatrixSlot)ret.getVMatrixSlot().get(vcore.getIndex());
                                                                                                if (slot != null) {
                                                                                                    slot.setIndex((Integer)vcores.getKey());

                                                                                                    for(int j = 1; j <= 3; ++j) {
                                                                                                        int vskillId = vcore.getSkill(j);
                                                                                                        Skill skill = SkillFactory.getSkill(vskillId);
                                                                                                        if (vskillId > 0 && skill != null) {
                                                                                                            SkillEntry skillEntry;
                                                                                                            if (vcore.getType() != 0 && vcore.getType() != 2) {
                                                                                                                if (vcore.getType() != 1) {
                                                                                                                    continue;
                                                                                                                }

                                                                                                                if (ret.getSkills().containsKey(skill.getId())) {
                                                                                                                    skillEntry = (SkillEntry)ret.getSkills().get(skill.getId());
                                                                                                                    skillEntry.skillevel = Math.min(skillEntry.skillevel + vcore.getLevel() + slot.getExtend(), skill.getTrueMax());
                                                                                                                } else {
                                                                                                                    skillEntry = new SkillEntry(vcore.getLevel() + slot.getExtend(), skill.getMasterLevel(), -1L, 0, 0, (byte)-1);
                                                                                                                }
                                                                                                            } else {
                                                                                                                if (ret.getSkills().containsKey(skill.getId())) {
                                                                                                                    continue;
                                                                                                                }

                                                                                                                skillEntry = new SkillEntry(vcore.getLevel() + slot.getExtend(), skill.getMasterLevel(), -1L, 0, 0, (byte)-1);
                                                                                                            }

                                                                                                            ret.skills.put(skill.getId(), skillEntry);
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    vcore.setSlot(1);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } while(rs.getInt("id") == charid);

                                                                                if (JobConstants.is皇家騎士團(rs.getShort("job"))) {
                                                                                    maxlevel = rs.getShort("level") / 5;
                                                                                    if (maxlevel > 24) {
                                                                                        maxlevel = 24;
                                                                                    }

                                                                                    if (maxlevel > maxlevel_2 || maxlevel_2 == 0) {
                                                                                        maxlevel_2 = maxlevel;
                                                                                        ret.BlessOfEmpress_Origin = rs.getString("name");
                                                                                    }
                                                                                }

                                                                                maxlevel = rs.getShort("level") / 10;
                                                                                if (maxlevel > 20) {
                                                                                    maxlevel = 20;
                                                                                }
                                                                            } while(maxlevel <= maxlevel_ && maxlevel_ != 0);

                                                                            maxlevel_ = maxlevel;
                                                                            ret.BlessOfFairy_Origin = rs.getString("name");
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                            i = rs.getInt("quest");
                                                        } while(ret.quests.containsKey(i));

                                                        q = MapleQuest.getInstance(i);
                                                        stat = rs.getByte("status");
                                                    } while((stat == 1 || stat == 2) && channelServer && (q == null || q.isBlocked()));
                                                } while(stat == 1 && channelServer && !q.canStart(ret, (Integer)null));

                                                status = new MapleQuestStatus(q, stat);
                                                cTime = rs.getLong("time");
                                                if (cTime > -1L) {
                                                    status.setCompletionTime(cTime * 1000L);
                                                }

                                                status.setForfeited(rs.getInt("forfeited"));
                                                status.setCustomData(rs.getString("customData"));
                                                ret.quests.put(i, status);
                                                pse.setLong(1, rs.getLong("queststatusid"));
                                                rse = pse.executeQuery();

                                                try {
                                                    while(rse.next()) {
                                                        status.setMobKills(rse.getInt("mob"), rse.getInt("count"));
                                                    }
                                                } catch (Throwable var65) {
                                                    if (rse != null) {
                                                        try {
                                                            rse.close();
                                                        } catch (Throwable var61) {
                                                            var65.addSuppressed(var61);
                                                        }
                                                    }

                                                    throw var65;
                                                }

                                                if (rse != null) {
                                                    rse.close();
                                                }
                                            }
                                        }

                                        i = rs.getInt("quest");
                                        q = MapleQuest.getInstance(i);
                                        stat = rs.getByte("status");
                                    } while((stat == 1 || stat == 2) && channelServer && (q == null || q.isBlocked()));
                                } while(stat == 1 && channelServer && !q.canStart(ret, (Integer)null));

                                status = new MapleQuestStatus(q, stat);
                                cTime = rs.getLong("time");
                                if (cTime > -1L) {
                                    status.setCompletionTime(cTime * 1000L);
                                }

                                status.setForfeited(rs.getInt("forfeited"));
                                status.setCustomData(rs.getString("customData"));
                                status.setFromChrID(rs.getInt("characterid"));
                                ret.quests.put(i, status);
                                pse.setLong(1, rs.getLong("queststatusid"));
                                rse = pse.executeQuery();

                                try {
                                    while(rse.next()) {
                                        status.setMobKills(rse.getInt("mob"), rse.getInt("count"));
                                    }
                                } catch (Throwable var66) {
                                    if (rse != null) {
                                        try {
                                            rse.close();
                                        } catch (Throwable var62) {
                                            var66.addSuppressed(var62);
                                        }
                                    }

                                    throw var66;
                                }

                                if (rse != null) {
                                    rse.close();
                                }
                            }
                        }

                        var75 = ItemLoader.裝備道具.loadItems(true, (long)charid).values().iterator();

                        while(var75.hasNext()) {
                            mit = (Pair)var75.next();
                            ret.getInventory((MapleInventoryType)mit.getRight()).addFromDB((Item)mit.getLeft());
                        }

                        ret.stats.recalcPVPRank(ret);
                        break;
                    }

                    ret.worldShareInfo.put(rs.getInt("quest"), rs.getString("customData"));
                }
            } catch (Throwable var67) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var58) {
                        var67.addSuppressed(var58);
                    }
                }

                throw var67;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var68) {
            SQLException ess = var68;
            log.error("加載角色數據信息出錯...", ess);
            if (ret.getClient() != null) {
                ret.getClient().getSession().close();
            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException var57) {
            }

        }

        return ret;
    }

    public static void saveNewCharToDB(MapleCharacter chr, JobType type, boolean oldkey) {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement pse = null;
        ResultSet rs = null;

        try {
            con = DatabaseConnectionEx.getInstance().getConnection();
            ps = con.prepareStatement("SELECT count(id) FROM characters WHERE accountid = ?");
            ps.setInt(1, chr.accountid);
            rs = ps.executeQuery();
            int position = 0;
            if (rs.next()) {
                position = rs.getInt(1);
            }

            ps.close();
            rs.close();
            con.setTransactionIsolation(1);
            con.setAutoCommit(false);
            ps = con.prepareStatement("INSERT INTO characters (position, level, str, dex, luk, `int`, hp, mp, maxhp, maxmp, sp, ap, skincolor, gender, job, hair, face, map, meso, party, buddyCapacity, pets, decorate, subcategory, friendshippoints, burningChrType, burningChrTime, accountid, name, world) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 1);
            int index = 0;
            ++index;
            ps.setInt(index, position);
            ++index;
            ps.setInt(index, chr.level);
            PlayerStats stat = chr.stats;
            ++index;
            ps.setShort(index, stat.getStr());
            ++index;
            ps.setShort(index, stat.getDex());
            ++index;
            ps.setShort(index, stat.getInt());
            ++index;
            ps.setShort(index, stat.getLuk());
            ++index;
            ps.setInt(index, stat.getHp());
            ++index;
            ps.setInt(index, stat.getMp());
            ++index;
            ps.setInt(index, stat.getMaxHp(false));
            ++index;
            ps.setInt(index, stat.getMaxMp(false));
            StringBuilder sps = new StringBuilder();

            for(int i = 0; i < chr.remainingSp.length; ++i) {
                sps.append(chr.remainingSp[i]);
                sps.append(",");
            }

            String sp = sps.toString();
            ++index;
            ps.setString(index, sp.substring(0, sp.length() - 1));
            ++index;
            ps.setShort(index, chr.remainingAp);
            ++index;
            ps.setByte(index, chr.skinColor);
            ++index;
            ps.setByte(index, chr.gender);
            ++index;
            ps.setShort(index, chr.job);
            ++index;
            ps.setInt(index, chr.hair);
            ++index;
            ps.setInt(index, chr.face);
            ++index;
            ps.setInt(index, ServerConfig.CHANNEL_PLAYER_BEGINNERMAP > -1 ? ServerConfig.CHANNEL_PLAYER_BEGINNERMAP : type.mapId);
            ++index;
            ps.setLong(index, chr.meso.get());
            ++index;
            ps.setInt(index, -1);
            ++index;
            ps.setByte(index, chr.buddylist.getCapacity());
            ++index;
            ps.setString(index, "-1,-1,-1");
            ++index;
            ps.setInt(index, chr.decorate);
            ++index;
            ps.setInt(index, chr.subcategory);
            ++index;
            int var10002 = chr.friendshippoints[0];
            ps.setString(index, "" + var10002 + "," + chr.friendshippoints[1] + "," + chr.friendshippoints[2] + "," + chr.friendshippoints[3] + "," + chr.friendshippoints[4]);
            ++index;
            ps.setByte(index, (byte)chr.burningChrType);
            if (chr.burningChrTime <= 0L) {
                ++index;
                ps.setNull(index, 93);
            } else {
                ++index;
                ps.setTimestamp(index, new Timestamp(chr.burningChrTime));
            }

            ++index;
            ps.setInt(index, chr.accountid);
            ++index;
            ps.setString(index, chr.name);
            ++index;
            ps.setByte(index, chr.world);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                chr.id = rs.getInt(1);
            } else {
                ps.close();
                rs.close();
                log.error("生成新角色到數據庫出錯...");
            }

            ps.close();
            rs.close();
            ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `account`, `world`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)", 1);
            pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");

            Iterator var12;
            int mob;
            for(var12 = chr.quests.values().iterator(); var12.hasNext(); rs.close()) {
                MapleQuestStatus q = (MapleQuestStatus)var12.next();
                if (q.isWorldShare()) {
                    ps.setInt(1, chr.accountid);
                    ps.setInt(2, chr.world);
                    ps.setNull(3, 4);
                } else {
                    ps.setNull(1, 4);
                    ps.setNull(2, 4);
                    ps.setInt(3, chr.id);
                }

                ps.setInt(4, q.getQuest().getId());
                ps.setInt(5, q.getStatus());
                ps.setInt(6, (int)(q.getCompletionTime() / 1000L));
                ps.setInt(7, q.getForfeited());
                ps.setString(8, q.getCustomData());
                ps.execute();
                rs = ps.getGeneratedKeys();
                if (q.hasMobKills()) {
                    rs.next();
                    Iterator var14 = q.getMobKills().keySet().iterator();

                    while(var14.hasNext()) {
                        mob = (Integer)var14.next();
                        pse.setLong(1, rs.getLong(1));
                        pse.setInt(2, mob);
                        pse.setInt(3, q.getMobKills(mob));
                        pse.execute();
                    }
                }
            }

            ps.close();
            pse.close();
            ps = con.prepareStatement("INSERT INTO character_keyvalue (`characterid`, `key`, `value`) VALUES (?, ?, ?)");
            ps.setInt(1, chr.id);
            ps.setString(2, "DAMAGE_SKIN");
            ps.setString(3, "0");
            ps.execute();
            ps.close();
            ps = con.prepareStatement("INSERT INTO skills (characterid, skillid, skilllevel, masterlevel, expiration, teachId, teachTimes) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            var12 = chr.skills.entrySet().iterator();

            while(var12.hasNext()) {
                Map.Entry<Integer, SkillEntry> skill = (Map.Entry)var12.next();
                if (SkillConstants.isApplicableSkill((Integer)skill.getKey())) {
                    ps.setInt(2, (Integer)skill.getKey());
                    ps.setInt(3, ((SkillEntry)skill.getValue()).skillevel);
                    ps.setByte(4, (byte)((SkillEntry)skill.getValue()).masterlevel);
                    ps.setLong(5, ((SkillEntry)skill.getValue()).expiration);
                    ps.setInt(6, ((SkillEntry)skill.getValue()).teachId);
                    ps.setInt(7, ((SkillEntry)skill.getValue()).teachTimes);
                    ps.execute();
                }
            }

            ps.close();
            ps = con.prepareStatement("INSERT INTO inventoryslot (characters_id, `equip`, `use`, `setup`, `etc`, `cash`, `decoration`) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            ps.setShort(2, (short)48);
            ps.setShort(3, (short)48);
            ps.setShort(4, (short)48);
            ps.setShort(5, (short)48);
            ps.setShort(6, (short)-128);
            ps.setShort(7, (short)-128);
            ps.execute();
            ps.close();
            ps = con.prepareStatement("INSERT INTO mountdata (characterid, `Level`, `Exp`, `Fatigue`) VALUES (?, ?, ?, ?)");
            ps.setInt(1, chr.id);
            ps.setByte(2, (byte)1);
            ps.setInt(3, 0);
            ps.setByte(4, (byte)0);
            ps.execute();
            ps.close();
            FuncKeyMap.init(con, chr.id, oldkey);
            int[] qs_basic = new int[]{42, 82, 71, 73, 29, 83, 79, 81, 2, 3, 4, 5, 16, 17, 18, 19, 6, 7, 8, 9, 20, 30, 31, 32, 10, 11, 33, 34, 37, 38, 49, 50};
            int[] qs_adv = new int[]{16, 17, 18, 19, 30, 31, 32, 33, 2, 3, 4, 5, 29, 56, 44, 45, 6, 7, 8, 9, 46, 22, 23, 36, 10, 11, 37, 38, 24, 25, 49, 50};
            MapleQuickSlot qs = chr.getQuickSlot();
            if (qs == null) {
                qs = new MapleQuickSlot();
            }

            for(mob = 0; mob < 32; ++mob) {
                qs.addQuickSlot(mob, oldkey ? qs_basic[mob] : qs_adv[mob]);
            }

            qs.saveQuickSlots(con, chr.id);
            List<Pair<Item, MapleInventoryType>> itemsWithType = new ArrayList();
            MapleInventory[] var16 = chr.inventory;
            int var17 = var16.length;

            for(int var18 = 0; var18 < var17; ++var18) {
                MapleInventory iv = var16[var18];
                Iterator var20 = iv.list().iterator();

                while(var20.hasNext()) {
                    Item item = (Item)var20.next();
                    itemsWithType.add(new Pair(item, iv.getType()));
                }
            }

            ItemLoader.裝備道具.saveItems(con, itemsWithType, (long)chr.id);
            con.commit();
        } catch (Exception var32) {
            Exception e = var32;
            log.error("[charsave] Error saving character data", e);

            try {
                con.rollback();
            } catch (SQLException var31) {
                SQLException ex = var31;
                log.error("[charsave] Error Rolling Back", ex);
            }
        } finally {
            try {
                if (pse != null) {
                    pse.close();
                }

                if (ps != null) {
                    ps.close();
                }

                if (rs != null) {
                    rs.close();
                }

                con.setAutoCommit(true);
                con.setTransactionIsolation(4);
            } catch (SQLException var30) {
                SQLException e = var30;
                log.error("[charsave] Error going back to autocommit mode", e);
            }

        }

    }

    public static void deleteWhereCharacterId(Connection con, String sql, int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public static void deleteWhereCharacterId_NoLock(Connection con, String sql, int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        ps.close();
    }

    public static boolean ban(String id, String reason, boolean accountId, int gmlevel, boolean hellban) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            boolean ret;
            label90: {
                boolean var20;
                try {
                    PreparedStatement ps;
                    if (id.matches("/[0-9]{1,3}\\..*")) {
                        ps = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
                        ps.setString(1, id);
                        ps.execute();
                        ps.close();
                        ret = true;
                        break label90;
                    }

                    if (accountId) {
                        ps = con.prepareStatement("SELECT id FROM accounts WHERE name = ?");
                    } else {
                        ps = con.prepareStatement("SELECT accountid FROM characters WHERE name = ?");
                    }

                    ret = false;
                    ps.setString(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int z = rs.getInt(1);
                        PreparedStatement psb = con.prepareStatement("UPDATE accounts SET banned = 1, banreason = ? WHERE id = ? AND gm < ?");
                        psb.setString(1, reason);
                        psb.setInt(2, z);
                        psb.setInt(3, gmlevel);
                        psb.execute();
                        psb.close();
                        if (gmlevel > 100) {
                            PreparedStatement psa = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
                            psa.setInt(1, z);
                            ResultSet rsa = psa.executeQuery();
                            if (rsa.next()) {
                                String sessionIP = rsa.getString("sessionIP");
                                if (sessionIP != null && sessionIP.matches("/[0-9]{1,3}\\..*")) {
                                    PreparedStatement psz = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
                                    psz.setString(1, sessionIP);
                                    psz.execute();
                                    psz.close();
                                }

                                String macData = rsa.getString("macs");
                                PreparedStatement pss;
                                if (macData != null && !macData.equalsIgnoreCase("00-00-00-00-00-00") && macData.length() >= 17) {
                                    pss = con.prepareStatement("INSERT INTO macbans VALUES (DEFAULT, ?)");
                                    pss.setString(1, macData);
                                    pss.execute();
                                    pss.close();
                                }

                                if (hellban) {
                                    pss = con.prepareStatement("UPDATE accounts SET banned = 1, banreason = ? WHERE email = ?" + (sessionIP == null ? "" : " OR SessionIP = ?"));
                                    pss.setString(1, reason);
                                    pss.setString(2, rsa.getString("email"));
                                    if (sessionIP != null) {
                                        pss.setString(3, sessionIP);
                                    }

                                    pss.execute();
                                    pss.close();
                                }
                            }

                            rsa.close();
                            psa.close();
                        }

                        ret = true;
                    }

                    rs.close();
                    ps.close();
                    var20 = ret;
                } catch (Throwable var17) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var16) {
                            var17.addSuppressed(var16);
                        }
                    }

                    throw var17;
                }

                if (con != null) {
                    con.close();
                }

                return var20;
            }

            if (con != null) {
                con.close();
            }

            return ret;
        } catch (SQLException var18) {
            SQLException ex = var18;
            System.err.println("Error while banning" + String.valueOf(ex));
            return false;
        }
    }

    public static byte checkExistance(int accid, int cid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            byte var5;
            label101: {
                byte var14;
                try {
                    PreparedStatement ps;
                    label103: {
                        ps = con.prepareStatement("SELECT * FROM hiredmerch WHERE accountid = ? AND characterid = ?");

                        try {
                            ps.setInt(1, accid);
                            ps.setInt(2, cid);
                            ResultSet rs = ps.executeQuery();

                            label88: {
                                try {
                                    if (rs.next()) {
                                        ps.close();
                                        rs.close();
                                        var5 = 1;
                                        break label88;
                                    }
                                } catch (Throwable var10) {
                                    if (rs != null) {
                                        try {
                                            rs.close();
                                        } catch (Throwable var9) {
                                            var10.addSuppressed(var9);
                                        }
                                    }

                                    throw var10;
                                }

                                if (rs != null) {
                                    rs.close();
                                }
                                break label103;
                            }

                            if (rs != null) {
                                rs.close();
                            }
                        } catch (Throwable var11) {
                            if (ps != null) {
                                try {
                                    ps.close();
                                } catch (Throwable var8) {
                                    var11.addSuppressed(var8);
                                }
                            }

                            throw var11;
                        }

                        if (ps != null) {
                            ps.close();
                        }
                        break label101;
                    }

                    if (ps != null) {
                        ps.close();
                    }

                    var14 = 0;
                } catch (Throwable var12) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var7) {
                            var12.addSuppressed(var7);
                        }
                    }

                    throw var12;
                }

                if (con != null) {
                    con.close();
                }

                return var14;
            }

            if (con != null) {
                con.close();
            }

            return var5;
        } catch (SQLException var13) {
            return -1;
        }
    }

    public static void removePartTime(int cid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM parttime WHERE cid = ?");

                try {
                    ps.setInt(1, cid);
                    ps.executeUpdate();
                } catch (Throwable var7) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var6) {
                            var7.addSuppressed(var6);
                        }
                    }

                    throw var7;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var8) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var8.addSuppressed(var5);
                    }
                }

                throw var8;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var9) {
            SQLException ex = var9;
            System.out.println("無法刪除打工信息: " + String.valueOf(ex));
        }

    }

    public static void addPartTime(MaplePartTimeJob partTime) {
        if (partTime.getCharacterId() >= 1) {
            addPartTime(partTime.getCharacterId(), partTime.getJob(), partTime.getTime(), partTime.getReward());
        }
    }

    public static void addPartTime(int cid, byte job, long time, int reward) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO parttime (cid, job, time, reward) VALUES (?, ?, ?, ?)");

                try {
                    ps.setInt(1, cid);
                    ps.setByte(2, job);
                    ps.setLong(3, time);
                    ps.setInt(4, reward);
                    ps.execute();
                } catch (Throwable var11) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var10) {
                            var11.addSuppressed(var10);
                        }
                    }

                    throw var11;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var12) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var9) {
                        var12.addSuppressed(var9);
                    }
                }

                throw var12;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var13) {
            SQLException ex = var13;
            System.out.println("無法添加打工信息: " + String.valueOf(ex));
        }

    }

    public static MaplePartTimeJob getPartTime(int cid) {
        MaplePartTimeJob partTime = new MaplePartTimeJob(cid);

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM parttime WHERE cid = ?");

                try {
                    ps.setInt(1, cid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            partTime.setJob(rs.getByte("job"));
                            partTime.setTime(rs.getLong("time"));
                            partTime.setReward(rs.getInt("reward"));
                        }
                    } catch (Throwable var10) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var9) {
                                var10.addSuppressed(var9);
                            }
                        }

                        throw var10;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var11) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var11.addSuppressed(var8);
                        }
                    }

                    throw var11;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var12) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var12.addSuppressed(var7);
                    }
                }

                throw var12;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var13) {
            Exception ex = var13;
            System.out.println("無法查詢打工信息: " + String.valueOf(ex));
        }

        return partTime;
    }

    public static boolean hasSkill(Map<Integer, SkillEntry> cskills, int skillid, int level) {
        if (cskills.get(skillid) == null) {
            return cskills.containsKey(skillid);
        } else {
            return ((SkillEntry)cskills.get(skillid)).skillevel == level && cskills.containsKey(skillid);
        }
    }

    public static MapleCharacter getOnlineCharacterById(int cid) {
        return WorldFindService.getInstance().findCharacterById(cid);
    }

    public static MapleCharacter getCharacterById(int n2) {
        MapleCharacter player = getOnlineCharacterById(n2);
        return player == null ? loadCharFromDB(n2, new MapleClient((MapleAESOFB)null, (MapleAESOFB)null, (Channel)null), true) : player;
    }

    public static int getLevelbyid(int cid) {
        int level = -1;

        try {
            Connection conn = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = conn.prepareStatement("SELECT `level` FROM characters WHERE id = ?");

                try {
                    ps.setInt(1, cid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            level = rs.getInt("level");
                        }
                    } catch (Throwable var10) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var9) {
                                var10.addSuppressed(var9);
                            }
                        }

                        throw var10;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var11) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var11.addSuppressed(var8);
                        }
                    }

                    throw var11;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var12) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var7) {
                        var12.addSuppressed(var7);
                    }
                }

                throw var12;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var13) {
            SQLException e = var13;
            log.error("讀取角色等級失敗", e);
        }

        return level;
    }

    private static int getJobLvSP(int nJob, int nLevel) {
        if (!JobConstants.is零轉職業(nJob) && !JobConstants.is皮卡啾(nJob) && !JobConstants.is雪吉拉(nJob)) {
            if (nLevel <= 10) {
                return 0;
            } else {
                int num = 0;
                if (nLevel > 100 && (!JobConstants.is神之子(nJob) || nLevel > 200)) {
                    if (nLevel <= 140) {
                        num = 2 + (int)Math.ceil((double)(nLevel % 100) / 10.0);
                        if (nLevel % 10 % 3 == 0) {
                            num *= 2;
                        }
                    }
                } else {
                    num = 3;
                }

                if (JobConstants.is影武者(nJob) && nLevel > 30 && nLevel <= 50) {
                    num = 4;
                }

                return num;
            }
        } else {
            return 0;
        }
    }

    private static Pair<Integer, Integer> getJobChangeSP(int nJob, int nSubcategory, int skillBook) {
        if (!JobConstants.is零轉職業(nJob) && !JobConstants.is管理員(nJob) && !JobConstants.is神之子(nJob) && !JobConstants.is皮卡啾(nJob) && !JobConstants.is雪吉拉(nJob)) {
            int changeSp = 5;
            if (nSubcategory != 1) {
                switch (skillBook) {
                    case 0:
                        if (JobConstants.is夜光(nJob)) {
                            changeSp = 30;
                        } else {
                            changeSp = 5;
                        }
                        break;
                    case 1:
                        if (!JobConstants.is隱月(nJob) && !JobConstants.is劍豪(nJob) && !JobConstants.is陰陽師(nJob)) {
                            if (JobConstants.is菈菈(nJob)) {
                                changeSp = 9;
                            } else {
                                changeSp = 4;
                            }
                        } else {
                            changeSp = 5;
                        }
                        break;
                    case 2:
                        if (!JobConstants.is隱月(nJob) && !JobConstants.is劍豪(nJob) && !JobConstants.is陰陽師(nJob)) {
                            changeSp = 4;
                        } else {
                            changeSp = 5;
                        }
                        break;
                    case 3:
                        changeSp = 3;
                }
            } else {
                switch (skillBook) {
                    case 4 -> changeSp = 0;
                    case 5 -> changeSp = 3;
                }
            }

            return new Pair(skillBook, Integer.valueOf(changeSp));
        } else {
            return null;
        }
    }

    public static void addReward(int accid, int cid, long start, long end, int type, long amount, int itemId, String desc) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO rewards (`accid`, `cid`, `start`, `end`, `type`, `amount`, `itemId`, `desc`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

                try {
                    if (accid > 0) {
                        ps.setInt(1, accid);
                    } else {
                        ps.setNull(1, 4);
                    }

                    if (cid > 0) {
                        ps.setInt(2, cid);
                    } else {
                        ps.setNull(2, 4);
                    }

                    ps.setLong(3, start);
                    ps.setLong(4, end);
                    ps.setInt(5, type);
                    ps.setLong(6, amount);
                    ps.setInt(7, itemId);
                    ps.setString(8, desc);
                    ps.executeUpdate();
                } catch (Throwable var17) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var16) {
                            var17.addSuppressed(var16);
                        }
                    }

                    throw var17;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var18) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var15) {
                        var18.addSuppressed(var15);
                    }
                }

                throw var18;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var19) {
            SQLException e = var19;
            log.error("Unable to obtain reward: ", e);
        }

    }

    public int[] getDeathCounts() {
        return this.deathCounts;
    }

    public void setDeathCounts(int[] deathCounts) {
        this.deathCounts = deathCounts;
    }

    public int liveCounts() {
        int c = 0;

        for(int i = 0; i < this.deathCounts.length; ++i) {
            if (this.deathCounts[i] == 1) {
                ++c;
            }
        }

        return c;
    }

    public int DeadCounts() {
        int c = 0;

        for(int i = 0; i < this.deathCounts.length; ++i) {
            if (this.deathCounts[i] == 2 || this.deathCounts[i] == 0) {
                ++c;
            }
        }

        return c;
    }

    public MapleUnion getMapleUnion() {
        return this.mapleUnion;
    }

    public void setMapleUnionChanged(boolean b) {
        this.changed_mapleUnion = b;
    }

    public void getPercentDamage(MapleMonster monster, int skillid, int skillLevel, int percent, boolean show) {
        MapleCharacter chr = this;
        int reduce = 0;
        int minushp = -((int)(chr.getStat().getCurrentMaxHp() * (long)(percent - reduce) / 100L));
        int effectid = 0;
        this.getMap().broadcastMessage(CField.showEffect(chr, skillid, effectid, 45, 0, 0, (byte)0, true, (Point)null, (String)null, (Item)null, (AttackInfo)null));
        this.getMap().broadcastMessage(CField.showEffect(chr, skillid, effectid, 45, 0, 0, (byte)0, true, (Point)null, (String)null, (Item)null, (AttackInfo)null));
        this.getMap().broadcastMessage(CField.showEffect(chr, 0, minushp, 36, 0, 0, (byte)0, true, (Point)null, (String)null, (Item)null, (AttackInfo)null));
        this.getMap().broadcastMessage(CField.showEffect(chr, 0, minushp, 36, 0, 0, (byte)0, false, (Point)null, (String)null, (Item)null, (AttackInfo)null));
        chr.addHP((long)minushp);
    }

    public Runnable saveToDB(boolean dc, boolean fromcs) {
        ChannelServer.getSaveExecutor().execute(() -> {
            try {
                this.saveToDB0(dc, fromcs);
            } catch (Exception var4) {
                Exception e = var4;
                log.info("[Save char to DataBase Error]", e);
            }

        });
        return null;
    }

    public void saveToDB0(boolean dc, boolean fromcs) {
        this.saveOnlineTime();
        if (!this.isSaveing || dc) {
            Connection con = null;
            PreparedStatement ps = null;
            PreparedStatement pse = null;
            ResultSet rs = null;

            try {
                con = DatabaseConnectionEx.getInstance().getConnection();
                this.isSaveing = true;
                con.setTransactionIsolation(1);
                con.setAutoCommit(false);
                ps = con.prepareStatement("SELECT * FROM characters WHERE id = ?");
                ps.setInt(1, this.id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ps.close();
                    rs.close();
                    ps = con.prepareStatement("UPDATE characters SET level = ?, fame = ?, str = ?, dex = ?, luk = ?, `int` = ?, exp = ?, hp = ?, mp = ?, maxhp = ?, maxmp = ?, sp = ?, ap = ?, skincolor = ?, gender = ?, job = ?, hair = ?, face = ?, map = ?, meso = ?, hpApUsed = ?, mpApUsed = ?, spawnpoint = ?, party = ?, buddyCapacity = ?, pets = ?, subcategory = ?, marriageId = ?, currentrep = ?, totalrep = ?, gachexp = ?, fatigue = ?, charm = ?, charisma = ?, craft = ?, insight = ?, sense = ?, will = ?, totalwins = ?, totallosses = ?, pvpExp = ?, pvpPoints = ?, decorate = ?, beans = ?, warning = ?, reborns = ?, reborns1 = ?, reborns2 = ?, reborns3 = ?, apstorage = ?, honor = ?, love = ?, playerPoints = ?, playerEnergy = ?, pvpDeaths = ?, pvpKills = ?, pvpVictory = ?, todayonlinetime = ?, totalonlinetime = ?, friendshiptoadd = ?, friendshippoints = ?, name = ?, wp = ?, salon_hair = ?, salon_face = ?, salon_skin = ?, burningChrType = ?, burningChrTime = ? WHERE id = ?", 1);
                    int index = 0;
                    ++index;
                    ps.setInt(index, this.level);
                    ++index;
                    ps.setInt(index, this.fame);
                    ++index;
                    ps.setShort(index, this.stats.getStr());
                    ++index;
                    ps.setShort(index, this.stats.getDex());
                    ++index;
                    ps.setShort(index, this.stats.getLuk());
                    ++index;
                    ps.setShort(index, this.stats.getInt());
                    ++index;
                    ps.setLong(index, this.level >= ServerConfig.CHANNEL_PLAYER_MAXLEVEL ? 0L : Math.abs(this.exp.get()));
                    ++index;
                    ps.setInt(index, this.stats.getHp() < 1 ? 50 : this.stats.getHp());
                    ++index;
                    ps.setInt(index, this.stats.getMp());
                    ++index;
                    ps.setInt(index, this.stats.getMaxHp(false));
                    ++index;
                    ps.setInt(index, this.stats.getMaxMp(false));
                    StringBuilder sps = new StringBuilder();
                    int[] var9 = this.remainingSp;
                    int var10 = var9.length;

                    int i;
                    for(i = 0; i < var10; ++i) {
                        i = var9[i];
                        sps.append(i);
                        sps.append(",");
                    }

                    String sp = sps.toString();
                    ++index;
                    ps.setString(index, sp.substring(0, sp.length() - 1));
                    ++index;
                    ps.setShort(index, this.remainingAp);
                    ++index;
                    ps.setByte(index, this.skinColor);
                    ++index;
                    ps.setByte(index, this.gender);
                    ++index;
                    ps.setShort(index, this.job);
                    ++index;
                    ps.setInt(index, this.hair);
                    ++index;
                    ps.setInt(index, this.face);
                    if (!fromcs && this.map != null) {
                        if (this.map.getForcedReturnId() != 999999999 && this.map.getForcedReturnMap() != null) {
                            this.mapid = this.map.getForcedReturnId();
                        } else {
                            this.mapid = this.stats.getHp() < 1 ? this.map.getReturnMapId() : this.map.getId();
                        }
                    }

                    ++index;
                    ps.setInt(index, GameConstants.getOverrideReturnMap(this.mapid));
                    ++index;
                    ps.setLong(index, this.meso.get());
                    ++index;
                    ps.setShort(index, this.hpApUsed);
                    ++index;
                    ps.setShort(index, this.mpApUsed);
                    if (this.map == null) {
                        ++index;
                        ps.setByte(index, (byte)0);
                    } else {
                        MaplePortal closest = this.map.findClosestSpawnpoint(this.getPosition());
                        ++index;
                        ps.setByte(index, (byte)(closest != null ? closest.getId() : 0));
                    }

                    ++index;
                    ps.setInt(index, this.party == null ? -1 : this.party.getId());
                    ++index;
                    ps.setShort(index, (short)this.buddylist.getCapacity());
                    StringBuilder petz = new StringBuilder();

                    for(i = 0; i < 3; ++i) {
                        if (this.spawnPets[i] != null && this.spawnPets[i].getSummoned()) {
                            this.spawnPets[i].saveToDb();
                            petz.append(this.spawnPets[i].getInventoryPosition());
                            petz.append(",");
                        } else {
                            petz.append("-1,");
                        }
                    }

                    String petstring = petz.toString();
                    ++index;
                    ps.setString(index, petstring.substring(0, petstring.length() - 1));
                    ++index;
                    ps.setByte(index, this.subcategory);
                    ++index;
                    ps.setInt(index, this.marriageId);
                    ++index;
                    ps.setInt(index, this.currentrep);
                    ++index;
                    ps.setInt(index, this.totalrep);
                    ++index;
                    ps.setInt(index, gachexp);
                    ++index;
                    ps.setShort(index, this.fatigue);
                    ++index;
                    ps.setInt(index, ((MapleTrait)this.traits.get(MapleTraitType.charm)).getTotalExp());
                    ++index;
                    ps.setInt(index, ((MapleTrait)this.traits.get(MapleTraitType.charisma)).getTotalExp());
                    ++index;
                    ps.setInt(index, ((MapleTrait)this.traits.get(MapleTraitType.craft)).getTotalExp());
                    ++index;
                    ps.setInt(index, ((MapleTrait)this.traits.get(MapleTraitType.insight)).getTotalExp());
                    ++index;
                    ps.setInt(index, ((MapleTrait)this.traits.get(MapleTraitType.sense)).getTotalExp());
                    ++index;
                    ps.setInt(index, ((MapleTrait)this.traits.get(MapleTraitType.will)).getTotalExp());
                    ++index;
                    ps.setInt(index, this.totalWins);
                    ++index;
                    ps.setInt(index, this.totalLosses);
                    ++index;
                    ps.setInt(index, this.pvpExp);
                    ++index;
                    ps.setInt(index, this.pvpPoints);
                    ++index;
                    ps.setInt(index, this.decorate);
                    ++index;
                    ps.setInt(index, this.beans);
                    ++index;
                    ps.setInt(index, this.warning);
                    ++index;
                    ps.setInt(index, this.reborns);
                    ++index;
                    ps.setInt(index, this.reborns1);
                    ++index;
                    ps.setInt(index, this.reborns2);
                    ++index;
                    ps.setInt(index, this.reborns3);
                    ++index;
                    ps.setInt(index, this.apstorage);
                    ++index;
                    ps.setInt(index, this.honor);
                    ++index;
                    ps.setInt(index, this.love);
                    ++index;
                    ps.setInt(index, this.playerPoints);
                    ++index;
                    ps.setInt(index, this.playerEnergy);
                    ++index;
                    ps.setInt(index, this.pvpDeaths);
                    ++index;
                    ps.setInt(index, this.pvpKills);
                    ++index;
                    ps.setInt(index, this.pvpVictory);
                    ++index;
                    ps.setInt(index, this.todayonlinetime + (int)((System.currentTimeMillis() - this.todayonlinetimestamp.getTime()) / 60000L));
                    ++index;
                    ps.setInt(index, this.totalonlinetime + (int)((System.currentTimeMillis() - this.todayonlinetimestamp.getTime()) / 60000L));
                    ++index;
                    ps.setInt(index, this.friendshiptoadd);
                    ++index;
                    int var10002 = this.friendshippoints[0];
                    ps.setString(index, "" + var10002 + "," + this.friendshippoints[1] + "," + this.friendshippoints[2] + "," + this.friendshippoints[3] + "," + this.friendshippoints[4]);
                    ++index;
                    ps.setString(index, this.name);
                    ++index;
                    ps.setInt(index, this.weaponPoint);
                    ++index;
                    ps.setInt(index, !this.salon.containsKey(3) ? 3 : ((List)this.salon.get(3)).size());
                    ++index;
                    ps.setInt(index, !this.salon.containsKey(2) ? 3 : ((List)this.salon.get(2)).size());
                    ++index;
                    ps.setInt(index, !this.salon.containsKey(1) ? 0 : ((List)this.salon.get(1)).size());
                    ++index;
                    ps.setByte(index, (byte)this.burningChrType);
                    if (this.burningChrTime <= 0L) {
                        ++index;
                        ps.setNull(index, 93);
                    } else {
                        ++index;
                        ps.setTimestamp(index, new Timestamp(this.burningChrTime));
                    }

                    ++index;
                    ps.setInt(index, this.id);
                    if (ps.executeUpdate() < 1) {
                        ps.close();
                        log.error("Character not in DatabaseFile.database (" + this.id + ")");
                    }

                    ps.close();
                    this.deleteWhereCharacterId(con, "DELETE FROM salon WHERE characterid = ?");
                    ps = con.prepareStatement("INSERT INTO salon (characterid, type, position, itemId) VALUES (?, ?, ?, ?)");
                    ps.setInt(1, this.id);
                    Iterator var37 = this.salon.entrySet().iterator();

                    Iterator var15;
                    int itemID;
                    while(var37.hasNext()) {
                        Map.Entry<Integer, List<Integer>> entry = (Map.Entry)var37.next();
                        ps.setInt(2, (Integer)entry.getKey());
                        i = 0;

                        for(var15 = ((List)entry.getValue()).iterator(); var15.hasNext(); ++i) {
                            itemID = (Integer)var15.next();
                            if (itemID != 0) {
                                ps.setInt(3, i);
                                ps.setInt(4, itemID);
                                ps.execute();
                            }
                        }
                    }

                    ps.close();
                    if (this.changed_skillmacros) {
                        this.deleteWhereCharacterId(con, "DELETE FROM skillmacros WHERE characterid = ?");

                        for(i = 0; i < 5; ++i) {
                            SkillMacro macro = this.skillMacros[i];
                            if (macro != null) {
                                ps = con.prepareStatement("INSERT INTO skillmacros (characterid, skill1, skill2, skill3, name, shout, position) VALUES (?, ?, ?, ?, ?, ?, ?)");
                                ps.setInt(1, this.id);
                                ps.setInt(2, macro.getSkill1());
                                ps.setInt(3, macro.getSkill2());
                                ps.setInt(4, macro.getSkill3());
                                ps.setString(5, macro.getName());
                                ps.setInt(6, macro.getShout());
                                ps.setInt(7, i);
                                ps.execute();
                                ps.close();
                            }
                        }
                    }

                    this.deleteWhereCharacterId(con, "DELETE FROM inventoryslot WHERE characters_id = ?");
                    ps = con.prepareStatement("INSERT INTO inventoryslot (`characters_id`, `equip`, `use`, `setup`, `etc`, `cash`, `decoration`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, this.id);
                    ps.setShort(2, this.getInventory(MapleInventoryType.EQUIP).getSlotLimit());
                    ps.setShort(3, this.getInventory(MapleInventoryType.USE).getSlotLimit());
                    ps.setShort(4, this.getInventory(MapleInventoryType.SETUP).getSlotLimit());
                    ps.setShort(5, this.getInventory(MapleInventoryType.ETC).getSlotLimit());
                    ps.setShort(6, this.getInventory(MapleInventoryType.CASH).getSlotLimit());
                    ps.setShort(7, this.getInventory(MapleInventoryType.DECORATION).getSlotLimit());
                    ps.execute();
                    ps.close();
                    List<Pair<Item, MapleInventoryType>> itemsWithType = new ArrayList();
                    MapleInventory[] var40 = this.inventory;
                    i = var40.length;

                    for(i = 0; i < i; ++i) {
                        MapleInventory iv = var40[i];
                        Iterator var17 = iv.list().iterator();

                        while(var17.hasNext()) {
                            Item item = (Item)var17.next();
                            itemsWithType.add(new Pair(item, iv.getType()));
                        }
                    }

                    if (this.getTrade() != null && dc) {
                        MapleTrade.cancelTrade(this.getTrade(), this.client, this);
                    }

                    ItemLoader.裝備道具.saveItems(con, itemsWithType, (long)this.id);
                    Iterator var41;
                    Map.Entry q;
                    if (this.changed_keyValue) {
                        this.deleteWhereCharacterId(con, "DELETE FROM character_keyvalue WHERE characterid = ?");
                        ps = con.prepareStatement("INSERT INTO character_keyvalue (`characterid`, `key`, `value`) VALUES (?, ?, ?)");
                        ps.setInt(1, this.id);
                        var41 = this.keyValue.entrySet().iterator();

                        while(var41.hasNext()) {
                            q = (Map.Entry)var41.next();
                            ps.setString(2, (String)q.getKey());
                            ps.setString(3, (String)q.getValue());
                            ps.execute();
                        }

                        ps.close();
                    }

                    if (this.changed_questinfo) {
                        this.deleteWhereCharacterId(con, "DELETE FROM questinfo WHERE characterid = ?");
                        ps = con.prepareStatement("INSERT INTO questinfo (`characterid`, `quest`, `customData`) VALUES (?, ?, ?)");
                        ps.setInt(1, this.id);
                        var41 = this.questinfo.entrySet().iterator();

                        while(var41.hasNext()) {
                            q = (Map.Entry)var41.next();
                            ps.setInt(2, (Integer)q.getKey());
                            ps.setString(3, (String)q.getValue());
                            ps.execute();
                        }

                        ps.close();
                    }

                    if (this.changed_worldshareinfo) {
                        SqlTool.update(con, "DELETE FROM accounts_questinfo WHERE accounts_id = ? and world = ?", new Object[]{this.accountid, this.world});
                        ps = con.prepareStatement("INSERT INTO accounts_questinfo (`accounts_id`, `world`, `quest`, `customData`) VALUES (?, ?, ?, ?)");
                        ps.setInt(1, this.accountid);
                        ps.setInt(2, this.world);
                        var41 = this.worldShareInfo.entrySet().iterator();

                        while(var41.hasNext()) {
                            q = (Map.Entry)var41.next();
                            ps.setInt(3, (Integer)q.getKey());
                            ps.setString(4, (String)q.getValue());
                            ps.execute();
                        }

                        ps.close();
                    }

                    this.deleteWhereCharacterId(con, "DELETE FROM queststatus WHERE characterid = ?");
                    ps = con.prepareStatement("INSERT INTO queststatus (`queststatusid`, `account`, `world`, `characterid`, `quest`, `status`, `time`, `forfeited`, `customData`) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)", 1);
                    pse = con.prepareStatement("INSERT INTO queststatusmobs VALUES (DEFAULT, ?, ?, ?)");
                    List<MapleQuestStatus> lquests = new ArrayList(this.quests.values());
                    ps.setInt(3, this.id);

                    Iterator var45;
                    int shopid;
                    for(var45 = lquests.iterator(); var45.hasNext(); rs.close()) {
                        MapleQuestStatus questStatus = (MapleQuestStatus)var45.next();
                        if (questStatus.isWorldShare()) {
                            ps.setInt(1, this.accountid);
                            ps.setInt(2, this.world);
                        } else {
                            ps.setNull(1, 4);
                            ps.setNull(2, 4);
                        }

                        ps.setInt(4, questStatus.getQuest().getId());
                        ps.setInt(5, questStatus.getStatus());
                        ps.setInt(6, (int)(questStatus.getCompletionTime() / 1000L));
                        ps.setInt(7, questStatus.getForfeited());
                        ps.setString(8, questStatus.getCustomData());
                        ps.execute();
                        rs = ps.getGeneratedKeys();
                        if (questStatus.hasMobKills()) {
                            rs.next();
                            Iterator var49 = questStatus.getMobKills().keySet().iterator();

                            while(var49.hasNext()) {
                                shopid = (Integer)var49.next();
                                pse.setLong(1, rs.getLong(1));
                                pse.setInt(2, shopid);
                                pse.setInt(3, questStatus.getMobKills(shopid));
                                pse.execute();
                            }
                        }
                    }

                    ps.close();
                    pse.close();
                    if (this.changed_skills) {
                        this.deleteWhereCharacterId(con, "DELETE FROM skills WHERE characterid = ?");
                        ps = con.prepareStatement("INSERT INTO skills (characterid, skillid, skilllevel, masterlevel, expiration, teachId, teachTimes, position) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                        ps.setInt(1, this.id);
                        var45 = this.skills.entrySet().iterator();

                        while(var45.hasNext()) {
                            Map.Entry<Integer, SkillEntry> skill = (Map.Entry)var45.next();
                            if (SkillConstants.isApplicableSkill((Integer)skill.getKey())) {
                                ps.setInt(2, (Integer)skill.getKey());
                                ps.setInt(3, ((SkillEntry)skill.getValue()).skillevel);
                                ps.setByte(4, (byte)((SkillEntry)skill.getValue()).masterlevel);
                                ps.setLong(5, ((SkillEntry)skill.getValue()).expiration);
                                ps.setInt(6, ((SkillEntry)skill.getValue()).teachId);
                                ps.setInt(7, ((SkillEntry)skill.getValue()).teachTimes);
                                ps.setByte(8, ((SkillEntry)skill.getValue()).position);
                                ps.execute();
                            }
                        }

                        ps.close();
                    }

                    if (this.changed_innerSkills) {
                        this.deleteWhereCharacterId(con, "DELETE FROM innerskills WHERE characterid = ?");

                        for(i = 0; i < 3; ++i) {
                            InnerSkillEntry InnerSkill = this.innerSkills[i];
                            if (InnerSkill != null) {
                                ps = con.prepareStatement("INSERT INTO innerskills (characterid, skillid, skilllevel, position, `rank`) VALUES (?, ?, ?, ?, ?)");
                                ps.setInt(1, this.id);
                                ps.setInt(2, InnerSkill.getSkillId());
                                ps.setInt(3, InnerSkill.getSkillLevel());
                                ps.setByte(4, InnerSkill.getPosition());
                                ps.setByte(5, InnerSkill.getRank());
                                ps.execute();
                                ps.close();
                            }
                        }
                    }

                    if (this.changed_mapleUnion) {
                        ps = con.prepareStatement("DELETE FROM `mapleunion` WHERE `accounts_id` = ? AND `world` = ?");
                        ps.setInt(1, this.accountid);
                        ps.setInt(2, this.world);
                        ps.execute();
                        ps = con.prepareStatement("INSERT INTO mapleunion (`accounts_id`, `world`, `characters_id`, `type`, `rotate`, `boardindex`, `local`) VALUES (?, ?, ?, ?, ?, ?, ?)");
                        ps.setInt(1, this.accountid);
                        ps.setInt(2, this.world);
                        var45 = this.mapleUnion.getFightingUnions().values().iterator();

                        while(var45.hasNext()) {
                            MapleUnionEntry union = (MapleUnionEntry)var45.next();
                            ps.setInt(3, union.getCharacterId());
                            ps.setInt(4, union.getType());
                            ps.setInt(5, union.getRotate());
                            ps.setInt(6, union.getBoardIndex());
                            ps.setInt(7, union.getLocal());
                            ps.execute();
                        }

                        ps.close();
                    }

                    List<MapleCoolDownValueHolder> coolDownInfo = this.getCooldowns();
                    if (dc && coolDownInfo.size() > 0) {
                        ps = con.prepareStatement("INSERT INTO skills_cooldowns (charid, SkillID, StartTime, length) VALUES (?, ?, ?, ?)");
                        ps.setInt(1, this.getId());
                        var15 = coolDownInfo.iterator();

                        while(var15.hasNext()) {
                            MapleCoolDownValueHolder cooling = (MapleCoolDownValueHolder)var15.next();
                            ps.setInt(2, cooling.skillId);
                            ps.setLong(3, cooling.startTime);
                            ps.setLong(4, (long)cooling.length);
                            ps.execute();
                        }

                        ps.close();
                    }

                    if (this.changed_savedlocations) {
                        this.deleteWhereCharacterId(con, "DELETE FROM savedlocations WHERE characterid = ?");
                        ps = con.prepareStatement("INSERT INTO savedlocations (characterid, `locationtype`, `map`) VALUES (?, ?, ?)");
                        ps.setInt(1, this.id);
                        SavedLocationType[] var55 = SavedLocationType.values();
                        itemID = var55.length;

                        for(shopid = 0; shopid < itemID; ++shopid) {
                            SavedLocationType savedLocationType = var55[shopid];
                            if (savedLocationType.getValue() >= 0 && this.savedLocations.length > savedLocationType.getValue() && this.savedLocations[savedLocationType.getValue()] != -1) {
                                ps.setInt(2, savedLocationType.getValue());
                                ps.setInt(3, this.savedLocations[savedLocationType.getValue()]);
                                ps.execute();
                            }
                        }

                        ps.close();
                    }

                    Map.Entry entry;
                    if (this.changed_reports) {
                        this.deleteWhereCharacterId(con, "DELETE FROM reports WHERE characterid = ?");
                        ps = con.prepareStatement("INSERT INTO reports VALUES(DEFAULT, ?, ?, ?)");
                        var15 = this.reports.entrySet().iterator();

                        while(var15.hasNext()) {
                            entry = (Map.Entry)var15.next();
                            ps.setInt(1, this.id);
                            ps.setByte(2, ((ReportType)entry.getKey()).i);
                            ps.setInt(3, (Integer)entry.getValue());
                            ps.execute();
                        }

                        ps.close();
                    }

                    if (this.buddylist.changed()) {
                        this.deleteWhereCharacterId(con, "DELETE FROM buddies WHERE characterid = ?");
                        ps = con.prepareStatement("INSERT INTO buddies (characterid, `buddyid`, `pending`) VALUES (?, ?, ?)");
                        ps.setInt(1, this.id);
                        Iterator<BuddylistEntry> iterator = this.buddylist.getBuddies().iterator();

                        while(iterator.hasNext()) {
                            BuddylistEntry next = iterator.next();
                            ps.setInt(2, next.getCharacterId());
                            ps.setInt(3, next.isVisible() ? 5 : 7);
                            ps.execute();
                        }

                        ps.close();
                        this.buddylist.setChanged(false);
                    }

                    ps = con.prepareStatement("UPDATE accounts SET `mPoints` = ? WHERE id = ?");
                    ps.setInt(1, this.client.getMaplePoints());
                    ps.setInt(2, this.accountid);
                    ps.execute();
                    ps.close();
                    if (this.trunk != null) {
                        this.trunk.saveToDB(con);
                    }

                    if (this.cs != null) {
                        this.cs.save(con);
                    }

                    PlayerNPC.updateByCharId(con, this);

                    for(i = 0; i < 3; ++i) {
                        ((FuncKeyMap)this.funcKeyMaps.get(i)).save(con, this.id, i);
                    }

                    this.quickslot.saveQuickSlots(con, this.id);
                    if (this.mount != null) {
                        this.mount.saveMount(con, this.id);
                    }

                    if (this.android != null) {
                        this.android.saveToDb(con);
                    }

                    if (this.pvpStats != null) {
                        this.pvpStats.saveToDb(con, this.accountid);
                    }

                    if (this.potionPot != null) {
                        this.potionPot.saveToDb(con);
                    }

                    if (this.changed_familiars) {
                        this.deleteWhereCharacterId(con, "DELETE FROM familiars WHERE characterid = ?");
                        ps = con.prepareStatement("INSERT INTO familiars (characterid, familiar, name, level, exp, grade, skillid, option1, option2, option3, summon, `lock`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        ps.setInt(1, this.id);
                        var15 = this.familiars.iterator();

                        while(var15.hasNext()) {
                            MonsterFamiliar familiar = (MonsterFamiliar)var15.next();
                            if (familiar.getFamiliar() != 0) {
                                ps.setInt(2, familiar.getFamiliar());
                                ps.setString(3, familiar.getName());
                                ps.setInt(4, familiar.getLevel());
                                ps.setInt(5, familiar.getExp());
                                ps.setInt(6, familiar.getGrade());
                                ps.setInt(7, familiar.getSkill());
                                ps.setInt(8, familiar.getOption1());
                                ps.setInt(9, familiar.getOption2());
                                ps.setInt(10, familiar.getOption3());
                                ps.setInt(11, familiar.isSummoned() ? 1 : 0);
                                ps.setInt(12, familiar.isLock() ? 1 : 0);
                                ps.execute();
                            }
                        }

                        ps.close();
                    }

                    this.deleteWhereCharacterId(con, "DELETE FROM imps WHERE characterid = ?");
                    ps = con.prepareStatement("INSERT INTO imps (characterid, itemid, closeness, fullness, state, level) VALUES (?, ?, ?, ?, ?, ?)");
                    ps.setInt(1, this.id);
                    MapleImp[] var62 = this.imps;
                    itemID = var62.length;

                    for(shopid = 0; shopid < itemID; ++shopid) {
                        MapleImp imp = var62[shopid];
                        if (imp != null) {
                            ps.setInt(2, imp.getItemId());
                            ps.setShort(3, imp.getCloseness());
                            ps.setShort(4, imp.getFullness());
                            ps.setByte(5, imp.getState());
                            ps.setByte(6, imp.getLevel());
                            ps.execute();
                        }
                    }

                    ps.close();
                    if (this.changed_wishlist) {
                        this.deleteWhereCharacterId(con, "DELETE FROM wishlist WHERE characterid = ?");

                        for(i = 0; i < this.getWishlistSize(); ++i) {
                            ps = con.prepareStatement("INSERT INTO wishlist(characterid, sn) VALUES(?, ?) ");
                            ps.setInt(1, this.getId());
                            ps.setInt(2, this.wishlist[i]);
                            ps.execute();
                            ps.close();
                        }
                    }

                    if (this.changed_trocklocations) {
                        this.deleteWhereCharacterId(con, "DELETE FROM trocklocations WHERE characterid = ?");
                        int[] var65 = this.regrocks;
                        itemID = var65.length;

                        int hyperrock;
                        for(shopid = 0; shopid < itemID; ++shopid) {
                            hyperrock = var65[shopid];
                            if (hyperrock != 999999999) {
                                ps = con.prepareStatement("INSERT INTO trocklocations(characterid, mapid, vip) VALUES (?, ?, 0)");
                                ps.setInt(1, this.getId());
                                ps.setInt(2, hyperrock);
                                ps.execute();
                                ps.close();
                            }
                        }

                        var65 = this.rocks;
                        itemID = var65.length;

                        for(shopid = 0; shopid < itemID; ++shopid) {
                            hyperrock = var65[shopid];
                            if (hyperrock != 999999999) {
                                ps = con.prepareStatement("INSERT INTO trocklocations(characterid, mapid, vip) VALUES (?, ?, 1)");
                                ps.setInt(1, this.getId());
                                ps.setInt(2, hyperrock);
                                ps.execute();
                                ps.close();
                            }
                        }

                        var65 = this.hyperrocks;
                        itemID = var65.length;

                        for(shopid = 0; shopid < itemID; ++shopid) {
                            hyperrock = var65[shopid];
                            if (hyperrock != 999999999) {
                                ps = con.prepareStatement("INSERT INTO trocklocations(characterid, mapid, vip) VALUES (?, ?, 2)");
                                ps.setInt(1, this.getId());
                                ps.setInt(2, hyperrock);
                                ps.execute();
                                ps.close();
                            }
                        }
                    }

                    this.deleteWhereCharacterId(con, "DELETE FROM character_credit WHERE characterid = ?");
                    ps = con.prepareStatement("INSERT INTO character_credit(characterid, name, value) VALUES(?, ?, ?)");
                    var15 = this.credit.entrySet().iterator();

                    while(var15.hasNext()) {
                        entry = (Map.Entry)var15.next();
                        ps.setInt(1, this.getId());
                        ps.setString(2, (String)entry.getKey());
                        ps.setInt(3, (Integer)entry.getValue());
                        ps.execute();
                    }

                    ps.close();
                    this.deleteWhereCharacterId(con, "DELETE FROM effectswitch WHERE `characterid` = ?");
                    ps = con.prepareStatement("INSERT INTO effectswitch (characterid, pos) VALUES (?, ?)");
                    var15 = this.effectSwitch.iterator();

                    while(var15.hasNext()) {
                        Integer effect = (Integer)var15.next();
                        ps.setInt(1, this.getId());
                        ps.setInt(2, effect);
                        ps.execute();
                    }

                    ps.close();
                    if (this.changed_vcores) {
                        this.deleteWhereCharacterId(con, "DELETE FROM `vcoreskill` WHERE `characterid` = ?");
                        var15 = this.vCoreSkills.values().iterator();

                        while(var15.hasNext()) {
                            VCoreSkillEntry vCoreSkillEntry = (VCoreSkillEntry)var15.next();
                            if (vCoreSkillEntry.getSlot() > 0) {
                                ps = con.prepareStatement("INSERT INTO `vcoreskill` (`characterid`, `vcoreid`, `level`, `exp`, `skill1`, `skill2`, `skill3`, `slot`, `index`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                                ps.setInt(1, this.getId());
                                ps.setInt(2, vCoreSkillEntry.getVcoreid());
                                ps.setInt(3, vCoreSkillEntry.getLevel());
                                ps.setInt(4, vCoreSkillEntry.getExp());
                                ps.setInt(5, vCoreSkillEntry.getSkill1());
                                ps.setInt(6, vCoreSkillEntry.getSkill2());
                                ps.setInt(7, vCoreSkillEntry.getSkill3());
                                ps.setInt(8, vCoreSkillEntry.getSlot());
                                ps.setInt(9, vCoreSkillEntry.getIndex());
                                ps.execute();
                                ps.close();
                            }
                        }

                        this.deleteWhereCharacterId(con, "DELETE FROM `vmatrixslot` WHERE `characters_id` = ?");
                        var15 = this.vMatrixSlot.values().iterator();

                        while(var15.hasNext()) {
                            VMatrixSlot vMatrixSlot = (VMatrixSlot)var15.next();
                            ps = con.prepareStatement("INSERT INTO `vmatrixslot` (`characters_id`, `slot`, `extend`, `unlock`) VALUES (?, ?, ?, ?)");
                            ps.setInt(1, this.getId());
                            ps.setInt(2, vMatrixSlot.getSlot());
                            ps.setInt(3, vMatrixSlot.getExtend());
                            ps.setInt(4, vMatrixSlot.getUnlock());
                            ps.execute();
                            ps.close();
                        }
                    }

                    Map.Entry datas;
                    Iterator var63;
                    if (this.changed_buylimit) {
                        this.deleteWhereCharacterId(con, "DELETE FROM `characters_buylimit` WHERE characters_id = ?");
                        var15 = this.buyLimit.entrySet().iterator();

                        while(var15.hasNext()) {
                            entry = (Map.Entry)var15.next();
                            shopid = (Integer)entry.getKey();
                            var63 = ((NpcShopBuyLimit)entry.getValue()).getData().entrySet().iterator();

                            while(var63.hasNext()) {
                                datas = (Map.Entry)var63.next();
                                ps = con.prepareStatement("INSERT INTO `characters_buylimit` (`characters_id`, `shop_id`, `itemid`, `count`, `data`) VALUES (?, ?, ?, ?, ?)");
                                ps.setInt(1, this.getId());
                                ps.setInt(2, shopid);
                                ps.setInt(3, (Integer)datas.getKey());
                                ps.setInt(4, ((BuyLimitData)datas.getValue()).getCount());
                                ps.setTimestamp(5, new Timestamp(((BuyLimitData)datas.getValue()).getDate()));
                                ps.execute();
                                ps.close();
                            }
                        }
                    }

                    if (this.changed_accountbuylimit) {
                        SqlTool.update(con, "DELETE FROM `accounts_buylimit` WHERE account_id = ? AND world = ?", new Object[]{this.accountid, this.world});
                        var15 = this.accountBuyLimit.entrySet().iterator();

                        while(var15.hasNext()) {
                            entry = (Map.Entry)var15.next();
                            shopid = (Integer)entry.getKey();
                            var63 = ((NpcShopBuyLimit)entry.getValue()).getData().entrySet().iterator();

                            while(var63.hasNext()) {
                                datas = (Map.Entry)var63.next();
                                ps = con.prepareStatement("INSERT INTO `accounts_buylimit` (`account_id`, `world`, `shop_id`, `itemid`, `count`, `data`) VALUES (?, ?, ?, ?, ?, ?)");
                                ps.setInt(1, this.accountid);
                                ps.setInt(2, this.world);
                                ps.setInt(3, shopid);
                                ps.setInt(4, (Integer)datas.getKey());
                                ps.setInt(6, ((BuyLimitData)datas.getValue()).getCount());
                                ps.setTimestamp(6, new Timestamp(((BuyLimitData)datas.getValue()).getDate()));
                                ps.execute();
                                ps.close();
                            }
                        }
                    }

                    if (changed_soulcollection) {
                        this.deleteWhereCharacterId(con, "DELETE FROM `character_soulcollection` WHERE characters_id = ?");
                        var15 = this.soulCollection.entrySet().iterator();

                        while(var15.hasNext()) {
                            entry = (Map.Entry)var15.next();
                            ps = con.prepareStatement("INSERT INTO `character_soulcollection` (`characters_id`, `page`, `setsoul`) VALUES (?, ?, ?)");
                            ps.setInt(1, this.getId());
                            ps.setInt(2, (Integer)entry.getKey());
                            ps.setInt(3, (Integer)entry.getValue());
                            ps.execute();
                            ps.close();
                        }
                    }

                    if (this.changed_mobcollection) {
                        SqlTool.update(con, "DELETE FROM `accounts_mobcollection` WHERE `accounts_id` = ? AND `world` = ?", new Object[]{this.accountid, this.world});
                        var15 = this.mobCollection.entrySet().iterator();

                        while(var15.hasNext()) {
                            entry = (Map.Entry)var15.next();
                            SqlTool.update(con, "INSERT INTO `accounts_mobcollection` (`accounts_id`, `world`, `recordid`, `data`) VALUES (?, ?, ?, ?)", new Object[]{this.accountid, this.world, entry.getKey(), entry.getValue()});
                        }
                    }

                    this.changed_wishlist = false;
                    this.changed_trocklocations = false;
                    this.changed_skillmacros = false;
                    this.changed_savedlocations = false;
                    this.changed_questinfo = false;
                    this.changed_worldshareinfo = false;
                    this.changed_skills = false;
                    this.changed_reports = false;
                    this.changed_keyValue = false;
                    this.changed_vcores = false;
                    this.changed_mapleUnion = false;
                    this.changed_buylimit = false;
                    this.changed_accountbuylimit = false;
                    changed_soulcollection = false;
                    this.changed_mobcollection = false;
                    this.changed_familiars = false;
                    con.commit();
                }
            } catch (SQLException var30) {
                SQLException e = var30;
                log.error("[charsave] 儲存角色數據出現錯誤 .", e);

                try {
                    con.rollback();
                } catch (SQLException var29) {
                    log.error("[charsave] Error Rolling Back", e);
                }
            } finally {
                this.isSaveing = false;

                try {
                    if (ps != null) {
                        ps.close();
                    }

                    if (pse != null) {
                        pse.close();
                    }

                    if (rs != null) {
                        rs.close();
                    }

                    con.setAutoCommit(true);
                    con.setTransactionIsolation(4);
                    con.close();
                } catch (SQLException var28) {
                    SQLException e = var28;
                    log.error("[charsave] Error going back to autocommit mode", e);
                }

            }

        }
    }

    private void deleteWhereCharacterId(Connection con, String sql) throws SQLException {
        deleteWhereCharacterId(con, sql, this.id);
    }

    public final int[] getFriendShipPoints() {
        return this.friendshippoints;
    }

    public final void setFriendShipPoints(int joejoe, int hermoninny, int littledragon, int ika, int Wooden) {
        this.friendshippoints[0] = joejoe;
        this.friendshippoints[1] = hermoninny;
        this.friendshippoints[2] = littledragon;
        this.friendshippoints[3] = ika;
        this.friendshippoints[4] = Wooden;
    }

    public final int getFriendShipToAdd() {
        return this.friendshiptoadd;
    }

    public final void setFriendShipToAdd(int points) {
        this.friendshiptoadd = points;
    }

    public final void addFriendShipToAdd(int points) {
        this.friendshiptoadd += points;
    }

    public PlayerStats getStat() {
        return this.stats;
    }

    public PlayerSpecialStats getSpecialStat() {
        return this.specialStats;
    }

    public void cancelMapTimeLimitTask() {
        if (this.mapTimeLimitTask != null) {
            this.mapTimeLimitTask.cancel(false);
            this.mapTimeLimitTask = null;
        }

    }

    public void startQuestTimeLimitTask(int quest, int time) {
        this.send(MaplePacketCreator.startQuestTimeLimit(quest, time));
        this.questTimeLimitTask = MapTimer.getInstance().register(() -> {
            this.send(MaplePacketCreator.stopQuestTimeLimit(quest));
        }, (long)time);
    }

    public void startMapTimeLimitTask(int time, MapleMap to) {
        if (time <= 0) {
            time = 1;
        }

        this.cancelMapTimeLimitTask();
        to.broadcastMessage(FieldPacket.clock(ClockPacket.secondsClock((long)time)));
        to.broadcastMessage(FieldPacket.clock(ClockPacket.secondsClock((long)time)));
        MapleMap ourMap = this.getMap();
        time *= 1000;
        this.mapTimeLimitTask = MapTimer.getInstance().register(() -> {
            if (ourMap.getId() == 993073000) {
                this.getQuestNAdd(MapleQuest.getInstance(123455)).setCustomData(String.valueOf(System.currentTimeMillis()));
                this.getQuestNAdd(MapleQuest.getInstance(123456)).setCustomData("0");
            }

            this.changeMap(to, to.getPortal(0));
        }, (long)time, (long)time);
    }

    public int getTouchedRune() {
        return this.touchedrune;
    }

    public void setTouchedRune(int type) {
        this.touchedrune = type;
    }

    public long getRuneTimeStamp() {
        return Long.parseLong(this.getKeyValue("LastTouchedRune"));
    }

    public void setRuneTimeStamp(long time) {
        this.setKeyValue("LastTouchedRune", String.valueOf(time));
    }

    public Map<String, String> getKeyValue_Map() {
        return this.keyValue;
    }

    public String getKeyValue(String key) {
        return (String)this.keyValue.get(key);
    }

    public void setKeyValue(String key, String values) {
        if (values == null) {
            this.keyValue.remove(key);
        } else {
            this.keyValue.put(key, values);
        }

        this.changed_keyValue = true;
    }

    public void updateInfoQuest(int questid, String data) {
        this.updateInfoQuest(questid, data, true);
    }

    public void updateInfoQuest(int questid, String data, boolean show) {
        if (data != null && !data.isEmpty()) {
            this.questinfo.put(questid, data);
        } else {
            this.questinfo.remove(questid);
        }

        if (!this.worldShareInfo.containsKey(questid) && !GameConstants.isWorldShareQuest(questid)) {
            this.changed_questinfo = true;
            if (show) {
                this.client.announce(MaplePacketCreator.updateInfoQuest(questid, data));
            }

        } else {
            this.updateWorldShareInfo(questid, data, show);
        }
    }

    public void removeInfoQuest(int questid) {
        if (this.questinfo.containsKey(questid)) {
            this.updateInfoQuest(questid, "");
            this.questinfo.remove(questid);
            this.changed_questinfo = true;
        }

    }

    public String getInfoQuest(int questid) {
        return this.questinfo.containsKey(questid) ? (String)this.questinfo.get(questid) : "";
    }

    public String getInfoQuestStatS(int id, String stat) {
        String info = this.getInfoQuest(id);
        if (info != null && info.length() > 0 && info.contains(stat)) {
            int startIndex = info.indexOf(stat) + stat.length() + 1;
            int until = info.indexOf(";", startIndex);
            return info.substring(startIndex, until != -1 ? until : info.length());
        } else {
            return "";
        }
    }

    public int getInfoQuestStat(int id, String stat) {
        String statz = this.getInfoQuestStatS(id, stat);
        return statz != null && !"".equals(statz) ? Integer.parseInt(statz) : 0;
    }

    public long getInfoQuestValueWithKey(int questId, String key) {
        String questInfo = this.getInfoQuest(questId);
        if (questInfo == null) {
            return -1L;
        } else {
            String[] split = questInfo.split(";");
            String[] var6 = split;
            int var7 = split.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String s = var6[var8];
                if (s.startsWith(key + "=")) {
                    String newkey = s.replace(key + "=", "");
                    String newkey2 = newkey.replace(";", "");
                    long dd = Long.valueOf(newkey2);
                    return dd;
                }
            }

            return -1L;
        }
    }

    public PlayerObservable getPlayerObservable() {
        return this.playerObservable;
    }

    public void setInfoQuestStat(int id, String stat, int statData) {
        this.setInfoQuestStat(id, stat, String.valueOf(statData));
    }

    public void setInfoQuestStat(int id, String stat, String statData) {
        String info = this.getInfoQuest(id);
        if (info.length() != 0 && info.contains(stat)) {
            String newInfo = stat + "=" + statData;
            String beforeStat = info.substring(0, info.indexOf(stat));
            int from = info.indexOf(";", info.indexOf(stat) + stat.length());
            String afterStat = from == -1 ? "" : info.substring(from + 1);
            this.updateInfoQuest(id, beforeStat + newInfo + (afterStat.length() != 0 ? ";" + afterStat : ""));
        } else {
            this.updateInfoQuest(id, stat + "=" + statData + (info.length() == 0 ? "" : ";") + info);
        }

    }

    public boolean containsInfoQuest(int questid, String data) {
        return this.questinfo.containsKey(questid) && ((String)this.questinfo.get(questid)).contains(data);
    }

    public int getNumQuest() {
        int i = 0;
        Iterator var2 = this.quests.values().iterator();

        while(var2.hasNext()) {
            MapleQuestStatus q = (MapleQuestStatus)var2.next();
            if (q.getStatus() == 2 && !q.isCustom()) {
                ++i;
            }
        }

        return i;
    }

    public byte getQuestStatus(int questId) {
        MapleQuest qq = MapleQuest.getInstance(questId);
        return this.getQuestNoAdd(qq) == null ? 0 : this.getQuestNoAdd(qq).getStatus();
    }

    public MapleQuestStatus getQuest(int questId) {
        return this.getQuest(MapleQuest.getInstance(questId));
    }

    public MapleQuestStatus getQuest(MapleQuest quest) {
        return !this.quests.containsKey(quest.getId()) ? new MapleQuestStatus(quest, 0) : (MapleQuestStatus)this.quests.get(quest.getId());
    }

    public boolean needQuestItem(int questId, int itemId) {
        if (questId <= 0) {
            return true;
        } else {
            MapleQuest quest = MapleQuest.getInstance(questId);
            return this.getInventory(ItemConstants.getInventoryType(itemId)).countById(itemId) < quest.getAmountofItems(itemId);
        }
    }

    public void setQuestAdd(MapleQuest quest, byte status, String customData) {
        if (!this.quests.containsKey(quest.getId())) {
            MapleQuestStatus stat = new MapleQuestStatus(quest, status);
            stat.setCustomData(customData);
            this.quests.put(quest.getId(), stat);
        }

    }

    public MapleQuestStatus getQuestNAdd(MapleQuest quest) {
        if (!this.quests.containsKey(quest.getId())) {
            MapleQuestStatus status = new MapleQuestStatus(quest, 0);
            this.quests.put(quest.getId(), status);
            return status;
        } else {
            return (MapleQuestStatus)this.quests.get(quest.getId());
        }
    }

    public MapleQuestStatus getQuestNoAdd(MapleQuest quest) {
        return (MapleQuestStatus)this.quests.get(quest.getId());
    }

    public MapleQuestStatus getQuestRemove(MapleQuest quest) {
        MapleQuestStatus result = (MapleQuestStatus)this.quests.remove(quest.getId());
        if (result != null && result.isWorldShare()) {
            try {
                Connection con = DatabaseConnectionEx.getInstance().getConnection();

                try {
                    PreparedStatement ps = con.prepareStatement("DELETE FROM queststatus WHERE account = ? AND world = ? AND quest = ?");
                    ps.setInt(1, this.accountid);
                    ps.setInt(2, this.world);
                    ps.setInt(3, quest.getId());
                    ps.executeUpdate();
                    ps.close();
                } catch (Throwable var7) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var6) {
                            var7.addSuppressed(var6);
                        }
                    }

                    throw var7;
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException var8) {
            }
        }

        return result;
    }

    public void updateQuest(MapleQuestStatus quest) {
        this.updateQuest(quest, false);
    }

    public void updateQuest(MapleQuestStatus quest, boolean update) {
        if (quest.getStatus() == 0) {
            if (this.quests.containsKey(quest.getQuest().getId())) {
                this.getQuestRemove(quest.getQuest());
            }
        } else {
            this.quests.put(quest.getQuest().getId(), quest);
        }

        if (!quest.isCustom()) {
            this.client.announce(MaplePacketCreator.updateQuest(quest));
            if (quest.getStatus() == 1 && !update) {
                this.client.announce(MaplePacketCreator.updateQuestInfo(quest.getQuest().getId(), quest.getNpc(), 0, quest.getStatus() == 1));
            }
        }

    }

    public Map<Integer, String> getInfoQuest_Map() {
        return this.questinfo;
    }

    public Map<Integer, MapleQuestStatus> getQuest_Map() {
        return this.quests;
    }

    public void startFishingTask() {
        if (FishingConfig.FISHING_ENABLE) {
            this.cancelFishingTask();
            this.lastFishingTime = System.currentTimeMillis();
            int fishingTime = this.isGm() ? FishingConfig.FISHING_TIME_GM : (this.stats.canFishVIP() ? FishingConfig.FISHING_TIME_VIP : FishingConfig.FISHING_TIME);
            this.dropMessage(-1, "開始釣魚，當前釣魚間隔時長為：" + fishingTime / 1000 + "秒。");
            this.dropMessage(-11, "開始釣魚，當前釣魚間隔時長為：" + fishingTime / 1000 + "秒。");
        }

    }

    public boolean canFish(long now) {
        if (!FishingConfig.FISHING_ENABLE) {
            return false;
        } else {
            int fishingTime = this.isGm() ? FishingConfig.FISHING_TIME_GM : (this.stats.canFishVIP() ? FishingConfig.FISHING_TIME_VIP : FishingConfig.FISHING_TIME);
            return this.lastFishingTime > 0L && this.lastFishingTime + (long)fishingTime < now;
        }
    }

    public void cancelFishingTask() {
        this.lastFishingTime = 0L;
    }

    public Map<SecondaryStat, List<SecondaryStatValueHolder>> getEffects() {
        this.effectLock.lock();

        Map var1;
        try {
            var1 = this.effects;
        } finally {
            this.effectLock.unlock();
        }

        return var1;
    }

    public Map<SecondaryStat, List<SecondaryStatValueHolder>> getAllEffects() {
        this.effectLock.lock();

        LinkedHashMap var1;
        try {
            var1 = new LinkedHashMap(this.effects);
        } finally {
            this.effectLock.unlock();
        }

        return var1;
    }

    public final List<SecondaryStatValueHolder> getIndieBuffStatValueHolder(SecondaryStat stat) {
        effectLock.lock();
        try {
            List<SecondaryStatValueHolder> list = new ArrayList<>();
            if (effects.containsKey(stat)) {
                list.addAll(effects.get(stat));
            }
            return list;
        } finally {
            effectLock.unlock();
        }
    }

    public final List<SecondaryStatValueHolder> getChrBuffStatValueHolder(int cid) {
        this.effectLock.lock();

        List var2;
        try {
            var2 = (List)this.effects.values().stream().flatMap(Collection::stream).filter((mbsvh) -> {
                return mbsvh.fromChrID == cid;
            }).collect(Collectors.toList());
        } finally {
            this.effectLock.unlock();
        }

        return var2;
    }

    public SecondaryStatValueHolder getBuffStatValueHolder(SecondaryStat stat) {
        return this.getBuffStatValueHolder(stat, -1);
    }

    public SecondaryStatValueHolder getBuffStatValueHolder(int skillID) {
        effectLock.lock();
        try {
            return effects.values().stream().flatMap(Collection::stream).filter(mbsvh -> mbsvh.effect.getSourceId() == skillID).findFirst().orElse(null);
        } finally {
            effectLock.unlock();
        }
    }


    public SecondaryStatValueHolder getBuffStatValueHolder(SecondaryStat stat, int skillID) {
        this.effectLock.lock();

        SecondaryStatValueHolder secondaryStatValueHolder1;
        try {
            List<SecondaryStatValueHolder> secondaryStatValueHolders = effects.get(stat);
            secondaryStatValueHolder1 = secondaryStatValueHolders == null ? null : secondaryStatValueHolders.stream().filter(secondaryStatValueHolder -> {
                return secondaryStatValueHolder.effect.getSourceId() == skillID || skillID == -1;

            }).findFirst().orElse(null);
        } finally {
            this.effectLock.unlock();
        }

        return secondaryStatValueHolder1;
    }

    public final void setBuffStatValue(SecondaryStat stat, int skillID, int value) {
        this.effectLock.lock();

        try {
            if (this.effects.get(stat) != null) {
                Iterator var4 = ((List)this.effects.get(stat)).iterator();

                while(var4.hasNext()) {
                    SecondaryStatValueHolder mbsvh = (SecondaryStatValueHolder)var4.next();
                    if (mbsvh.effect.getSourceId() == skillID) {
                        mbsvh.value = value;
                        break;
                    }
                }
            }
        } finally {
            this.effectLock.unlock();
        }

    }

    public MapleStatEffect getEffectForBuffStat(SecondaryStat stat) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        return mbsvh == null ? null : mbsvh.effect;
    }

    public void giveDebuff(Map<SecondaryStat, Pair<Integer, Integer>> disease, MobSkill skill) {
        Map<Map<SecondaryStat, Pair<Integer, Integer>>, Pair<Integer, Integer>> diseases = new HashMap();
        diseases.put(disease, new Pair(skill.getX(), skill.getDuration()));
        this.giveDebuff(disease, skill);
    }

    public MapleStatEffect getSkillEffect(int skillID) {
        MapleStatEffect effect = null;
        Skill skill = SkillFactory.getSkill(skillID);
        if (skill != null) {
            effect = skill.getEffect(this.getSkillLevel(SkillConstants.getLinkedAttackSkill(skillID)));
        }

        return effect;
    }

    public int getBuffedIntValue(SecondaryStat stat) {
        Integer value = this.getBuffedValue(stat);
        return value == null ? 0 : value;
    }

    public void giveBlackMageBuff() {
        this.getMap().broadcastMessage(CField.getSelectPower(5, 39));
        this.setSkillCustomInfo(80002625, 1L, 0L);
        SkillFactory.getSkill(80002625).getEffect(1).applyTo(this);
    }

    public final int getBuffedIntX(SecondaryStat stat) {
        Integer value = this.getBuffedX(stat);
        return value == null ? 0 : value;
    }

    public int getBuffedIntZ(SecondaryStat stat) {
        Integer value = this.getBuffedZ(stat);
        return value == null ? 0 : value;
    }

    public final Integer getBuffedX(SecondaryStat stat) {
        this.effectLock.lock();

        Integer var9;
        try {
            int n = 0;
            boolean find = false;
            if (this.effects.containsKey(stat)) {
                Iterator var4 = ((List)this.effects.get(stat)).iterator();

                while(var4.hasNext()) {
                    SecondaryStatValueHolder mbsvh = (SecondaryStatValueHolder)var4.next();
                    find = true;
                    n += mbsvh.x;
                    if (!stat.canStack()) {
                        break;
                    }
                }
            }

            var9 = find ? n : null;
        } finally {
            this.effectLock.unlock();
        }

        return var9;
    }

    public final Integer getBuffedZ(SecondaryStat stat) {
        this.effectLock.lock();

        Integer var9;
        try {
            int n = 0;
            boolean find = false;
            if (this.effects.containsKey(stat)) {
                Iterator var4 = ((List)this.effects.get(stat)).iterator();

                while(var4.hasNext()) {
                    SecondaryStatValueHolder mbsvh = (SecondaryStatValueHolder)var4.next();
                    find = true;
                    n += mbsvh.z;
                    if (!stat.canStack()) {
                        break;
                    }
                }
            }

            var9 = find ? n : null;
        } finally {
            this.effectLock.unlock();
        }

        return var9;
    }

    public final Integer getBuffedValue(final SecondaryStat stat, final int skillID) {
        effectLock.lock();
        try {
            return effects.containsKey(stat) ? effects.get(stat).stream().filter(mbsvh -> mbsvh.effect.getSourceId() == skillID).findFirst().map(mbsvh -> mbsvh.value).orElse(null) : null;
        } finally {
            effectLock.unlock();
        }
    }

    public long getSkillCustomValue0(int skillid) {
        return this.customInfo.containsKey(skillid) ? ((SkillCustomInfo)this.customInfo.get(skillid)).getValue() : 0L;
    }

    public final Integer getBuffedValue(SecondaryStat stat) {
        this.effectLock.lock();

        Integer var9;
        try {
            int n = 0;
            boolean find = false;
            if (this.effects.containsKey(stat)) {
                Iterator var4 = ((List)this.effects.get(stat)).iterator();

                while(var4.hasNext()) {
                    SecondaryStatValueHolder mbsvh = (SecondaryStatValueHolder)var4.next();
                    find = true;
                    n += mbsvh.value;
                    if (!stat.canStack()) {
                        break;
                    }
                }
            }

            var9 = find ? n : null;
        } finally {
            this.effectLock.unlock();
        }

        return var9;
    }

    public int getBuffSource(SecondaryStat stat) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        return mbsvh == null ? 0 : mbsvh.effect.getSourceId();
    }

    public List<SecondaryStat> getBuffStats(MapleStatEffect effect, long startTime) {
        effectLock.lock();
        try {
            List<SecondaryStat> list = new ArrayList<>();
            for (Map.Entry<SecondaryStat, List<SecondaryStatValueHolder>> entry : effects.entrySet()) {
                for (SecondaryStatValueHolder mbsvh : entry.getValue()) {
                    if (mbsvh.effect != null && mbsvh.effect.sameSource(effect) && (startTime == -1L || startTime == mbsvh.startTime)) {
                        list.add(entry.getKey());
                    }
                }
            }
            return list;
        } finally {
            effectLock.unlock();
        }
    }

    public final void removeEffect(final SecondaryStat stat, final int skillID, final long startTime) {
        effectLock.lock();
        try {
            if (effects.get(stat) != null) {
                effects.get(stat).removeIf(mbsvh -> mbsvh.effect.getSourceId() == skillID && (mbsvh.startTime == startTime || startTime == -1L));
            }
        } finally {
            effectLock.unlock();
        }
    }

    public final void removeAllEffect() {
        this.effectLock.lock();

        try {
            TemporaryStat.SaveData(this);
            Iterator<Map.Entry<SecondaryStat, List<SecondaryStatValueHolder>>> iterator = this.effects.entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry<SecondaryStat, List<SecondaryStatValueHolder>> entry = (Map.Entry)iterator.next();

                for(Iterator<SecondaryStatValueHolder> iterator2 = ((List)entry.getValue()).iterator(); iterator2.hasNext(); iterator2.remove()) {
                    SecondaryStatValueHolder mbsvh = (SecondaryStatValueHolder)iterator2.next();
                    if (!TemporaryStat.IsSaveStat((SecondaryStat)entry.getKey(), mbsvh)) {
                        mbsvh.cancel();
                    }
                }

                iterator.remove();
            }

            this.setSoulMP(0);
        } finally {
            this.effectLock.unlock();
        }
    }

    public final void removeDebuffs() {
        List<Integer> list = new ArrayList();
        this.effectLock.lock();

        try {
            Iterator var2 = this.effects.values().iterator();

            while(var2.hasNext()) {
                List<SecondaryStatValueHolder> holders = (List)var2.next();
                Iterator var4 = holders.iterator();

                while(var4.hasNext()) {
                    SecondaryStatValueHolder mbsvh = (SecondaryStatValueHolder)var4.next();
                    if (mbsvh.effect instanceof MobSkill && !mbsvh.effect.isNotRemoved()) {
                        list.add(mbsvh.effect.getSourceId());
                    }
                }
            }
        } finally {
            this.effectLock.unlock();
        }

        list.forEach(this::dispelEffect);
    }

    public final void removeBuffs(boolean normal) {
        List<Integer> list = new ArrayList();
        List<Integer> notRemove = new ArrayList();
        this.effectLock.lock();

        try {
            Iterator var4 = this.effects.entrySet().iterator();

            label104:
            while(var4.hasNext()) {
                Map.Entry<SecondaryStat, List<SecondaryStatValueHolder>> entry = (Map.Entry)var4.next();
                Iterator var6 = ((List)entry.getValue()).iterator();

                while(true) {
                    while(true) {
                        if (!var6.hasNext()) {
                            continue label104;
                        }

                        SecondaryStatValueHolder mbsvh = (SecondaryStatValueHolder)var6.next();
                        if (mbsvh.effect != null && (TemporaryStat.IsNotRemoveSaveStat((SecondaryStat)entry.getKey(), mbsvh) || notRemove.contains(mbsvh.effect.getSourceId()) || mbsvh.effect.getSourceId() == 1320016)) {
                            notRemove.add(mbsvh.effect.getSourceId());
                        } else if (!(mbsvh.effect instanceof MobSkill) && (!normal || !mbsvh.effect.isNotRemoved())) {
                            list.add(mbsvh.effect.getSourceId());
                        }
                    }
                }
            }

            this.setSoulMP(0);
        } finally {
            this.effectLock.unlock();
        }

        list.forEach(this::dispelEffect);
    }

    public final void dispelEffect(int skillID) {
        SecondaryStatValueHolder buffStatValueHolder;
        if ((buffStatValueHolder = this.getBuffStatValueHolder(skillID)) != null) {
            this.cancelEffect(buffStatValueHolder.effect, buffStatValueHolder.startTime);
        }

    }

    public final void dispelEffect(SecondaryStat stat) {
        SecondaryStatValueHolder buffStatValueHolder;
        if ((buffStatValueHolder = this.getBuffStatValueHolder(stat)) != null) {
            this.cancelEffect(buffStatValueHolder.effect, buffStatValueHolder.startTime);
        }

    }

    public void dispelEffect(int skillID, SecondaryStat stat) {
        SecondaryStatValueHolder buffStatValueHolder;
        if ((buffStatValueHolder = this.getBuffStatValueHolder(stat, skillID)) != null) {
            this.cancelEffect(buffStatValueHolder.effect, true, buffStatValueHolder.startTime, Collections.singletonMap(stat, buffStatValueHolder.value));
        }

    }

    public void cancelEffect(MapleStatEffect effect, long startTime) {
        this.cancelEffect(effect, false, startTime, effect.getStatups());
    }

    public void cancelEffect(MapleStatEffect effect, boolean overwrite, long startTime) {
        this.cancelEffect(effect, overwrite, startTime, effect.getStatups());
    }

    public void cancelEffect(MapleStatEffect effect, boolean overwrite, long startTime, Map<SecondaryStat, Integer> statups) {
        if (effect != null) {
            List<SecondaryStat> buffStats;
            if (!overwrite) {
                buffStats = this.getBuffStats(effect, startTime);
                if (((List)buffStats).contains(SecondaryStat.ElementalCharge) && this.getBuffStatValueHolder(SecondaryStat.BlessedHammer) != null) {
                    ((List)buffStats).add(SecondaryStat.BlessedHammer);
                    ((List)buffStats).add(SecondaryStat.BlessedHammerActive);
                }
            } else {
                buffStats = new ArrayList(statups.size());
                ((List)buffStats).addAll(statups.keySet());
            }

            if (!((List)buffStats).isEmpty()) {
                if (!overwrite && effect.getSourceId() == 400051015) {
                    SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(SecondaryStat.SerpentScrew);
                    if (mbsvh != null && mbsvh.value >= 10) {
                        this.reduceSkillCooldown(400051015, mbsvh.value / effect.getU() * effect.getX() * 1000);
                    }
                }

                boolean ow = overwrite;
                Arrays.stream(new SecondaryStat[]{SecondaryStat.Lapidification, SecondaryStat.Attract}).forEach((s) -> {
                    if (!ow && buffStats.contains(s)) {
                        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(s);
                        if (mbsvh == null || mbsvh.getLeftTime() <= 0) {
                            this.addHPMP(-100, 0);
                        }
                    }

                });
                this.deregisterBuffStats((List)buffStats, effect, overwrite, startTime);
                if (effect.getSourceId() == 20031205) {
                    SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(SecondaryStat.Invisible);
                    this.registerSkillCooldown(20031205, this.getSkillEffect(20031205).getCooldown(this), true);
                    if (this.getTempValues().get("skill20031205") != null) {
                        this.getTempValues().put("skill20031205", 0);
                    }
                }

                this.deregisterBuffStats((List)buffStats, effect, overwrite, startTime);
                if (!overwrite && effect.getSourceId() == 80011248) {
                    SkillFactory.getSkill(80011249).getEffect(1).applyTo(this);
                    this.send(EffectPacket.showBuffEffect(this, false, 80011249, 1, 0, (Point)null));
                }

                if (((List)buffStats).contains(SecondaryStat.Larkness) && startTime > 0L && this.larknessDiraction != 3) {
                    if (this.larkness == 0) {
                        this.getSkillEffect(20040217).unprimaryPassiveApplyTo(this);
                    } else if (this.larkness == 10000) {
                        this.getSkillEffect(20040216).unprimaryPassiveApplyTo(this);
                    }
                }

                if (JobConstants.is劍豪(this.job) && ((List)buffStats).contains(SecondaryStat.BladeStanceMode)) {
                    this.applyHayatoStance(0);
                }

                AbstractSkillHandler sh = SkillClassFetcher.getHandlerBySkill(effect.getSourceId());
                if (sh == null) {
                    sh = SkillClassFetcher.getHandlerByJob(this.getJobWithSub());
                }

                if (sh != null) {
                    SkillClassApplier applier = new SkillClassApplier();
                    applier.effect = effect;
                    applier.overwrite = overwrite;
                    applier.localstatups = statups;
                    int result = sh.onAfterCancelEffect(this, applier);
                    if (result != 0 && result == 1) {
                        effect = applier.effect;
                        overwrite = applier.overwrite;
                        statups = applier.localstatups;
                    }
                }

            }
        }
    }

    private void deregisterBuffStats(List<SecondaryStat> stats, MapleStatEffect effect, boolean overwrite, long startTime) {
        List<MapleSummon> summonList = new ArrayList();
        this.effectLock.lock();

        try {
            Iterator var7 = stats.iterator();

            label497:
            while(var7.hasNext()) {
                SecondaryStat stat = (SecondaryStat)var7.next();
                if (stat != SecondaryStat.AMPlanting && this.effects.containsKey(stat)) {
                    List<SecondaryStatValueHolder> holderList = (List)this.effects.get(stat);
                    Iterator<SecondaryStatValueHolder> holderIterator = holderList.iterator();

                    while(true) {
                        SecondaryStatValueHolder mbsvh;
                        do {
                            if (!holderIterator.hasNext()) {
                                if (holderList.isEmpty() || effect.getSourceId() == 14001027) {
                                    this.effects.remove(stat);
                                }
                                continue label497;
                            }

                            mbsvh = (SecondaryStatValueHolder)holderIterator.next();
                        } while((!mbsvh.effect.sameSource(effect) || mbsvh.startTime != startTime && startTime != -1L) && (mbsvh.effect.sameSource(effect) || stat.canStack()));

                        if (!overwrite && stat == SecondaryStat.RevenantGauge) {
                            this.tempValues.put("亡靈怒氣", mbsvh.z);
                        }

                        if (!overwrite && mbsvh.effect.getSourceId() == 80011248) {
                            mbsvh.value = 0;
                            mbsvh.localDuration = 0;
                            this.send(BuffPacket.giveBuff(this, effect, Collections.singletonMap(SecondaryStat.DawnShield_ExHP, effect.getSourceId())));
                        }

                        boolean remove = true;
                        if (effect.is時空門()) {
                            this.removeAllTownPortal();
                        } else if (stat == SecondaryStat.IndieBuffIcon) {
                            this.summonsLock.lock();

                            try {
                                long timeNow = System.currentTimeMillis();
                                Iterator<MapleSummon> summonIterator = this.summons.iterator();

                                label487:
                                while(true) {
                                    while(true) {
                                        MapleSummon summon;
                                        do {
                                            int skillID;
                                            do {
                                                do {
                                                    if (!summonIterator.hasNext()) {
                                                        break label487;
                                                    }

                                                    summon = (MapleSummon)summonIterator.next();
                                                    skillID = mbsvh.effect.getSourceId();
                                                } while(summon.getSkillId() != skillID && summon.getParentSummon() != skillID);
                                            } while(summon.getCreateTime() != startTime && startTime != -1L);

                                            if (!overwrite && summon.getSkillId() == 164121006) {
                                                remove = false;
                                            }
                                        } while(!remove);

                                        if (summon.getSummonMaxCount() != 1 && summon.getCreateTime() + (long)summon.getDuration() > timeNow) {
                                            remove = false;
                                        } else {
                                            if (this.isDebug()) {
                                                this.dropDebugMessage(1, "[Summon] Remove Summon Effect:" + String.valueOf(summon.getEffect()));
                                            }

                                            summonList.add(summon);
                                            summonIterator.remove();
                                        }
                                    }
                                }
                            } finally {
                                this.summonsLock.unlock();
                            }
                        } else if (JobConstants.is陰陽師(this.job) && stat == SecondaryStat.ChangeFoxMan && this.getHaku() != null) {
                            this.getHaku().setState(1);
                            this.getHaku().update(this);
                        }

                        if (remove) {
                            mbsvh.cancel();
                            holderIterator.remove();
                            if (this.isDebug()) {
                                this.dropDebugMessage(1, "[BUFF] Deregister:" + String.valueOf(stat));
                            }
                        }
                    }
                }
            }
        } finally {
            this.effectLock.unlock();
        }

        if (this.map != null && !summonList.isEmpty()) {
            MapleStatEffect eff = this.getSkillEffect(400021114);
            summonList.forEach((summonx) -> {
                this.map.disappearMapObject(summonx);
                if (eff != null && (summonx.getSkillId() == 42001100 || summonx.getSkillId() == 42100000 || summonx.getSkillId() == 42110000 || summonx.getSkillId() == 42120001)) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long time = currentTimeMillis - summonx.getCreateTime();
                    SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(SecondaryStat.KannaFifthAttract);
                    if (mbsvh == null) {
                        this.registerEffect(Collections.singletonMap(SecondaryStat.KannaFifthAttract, new Pair(400021114, 0)), (int)time, this.getId(), currentTimeMillis, 0L, 2100000000, (MapleStatEffect.CancelEffectAction)null);
                        mbsvh = this.getBuffStatValueHolder(SecondaryStat.KannaFifthAttract);
                    } else {
                        if (mbsvh.sourceID != 0 || mbsvh.z >= eff.getU() * 1000) {
                            return;
                        }

                        mbsvh.z = (int)((long)mbsvh.z + time);
                    }

                    mbsvh.sourceID = 0;
                    this.send(BuffPacket.giveBuff(this, (MapleStatEffect)null, Collections.singletonMap(SecondaryStat.KannaFifthAttract, 400021114)));
                }

            });
        }

        this.cancelPlayerBuffs(stats, overwrite);
        if (effect.isSkill() && (effect.getSourceId() == 9001004 || effect.getSourceId() == 9101004)) {
            this.map.broadcastBelowGmLvMessage(this, MaplePacketCreator.spawnPlayerMapobject(this), false);
            this.map.broadcastBelowGmLvMessage(this, EffectPacket.getEffectSwitch(this.getId(), this.getEffectSwitch()), false);
        }

    }

    private void cancelPlayerBuffs(List<SecondaryStat> buffstats, boolean overwrite) {
        ((List)buffstats).removeIf((statx) -> {
            return statx == SecondaryStat.SurplusSupply;
        });
        if (overwrite) {
            List<SecondaryStat> buffStatX = new ArrayList();
            Iterator var4 = ((List)buffstats).iterator();

            while(var4.hasNext()) {
                SecondaryStat stat = (SecondaryStat)var4.next();
                if (stat.canStack()) {
                    switch (stat) {
                        case RideVehicle:
                        case RideVehicleExpire:
                            break;
                        default:
                            buffStatX.add(stat);
                    }
                }
            }

            if (buffStatX.size() <= 0) {
                return;
            }

            buffstats = buffStatX;
        }

        try {
            this.stats.recalcLocalStatsInterrupt(false, this, -1);
        } catch (InterruptedException var6) {
        }

        if (!((List)buffstats).isEmpty() && this.client != null) {
            this.client.announce(BuffPacket.temporaryStatReset((List)buffstats, this));
            if (this.map != null) {
                this.map.broadcastMessage(this, BuffPacket.cancelForeignBuff(this, (List)buffstats), false);
            }

            if (((List)buffstats).contains(SecondaryStat.FifthAdvWarriorShield)) {
                this.send(MaplePacketCreator.userBonusAttackRequest(400001011, 0, Collections.emptyList()));
            }

            if (((List)buffstats).contains(SecondaryStat.RevenantGauge)) {
                this.getSkillEffect(400011129).applyTo(this);
            }

            MapleSummon summon = this.getSummonBySkillID(152101000);
            if (((List)buffstats).contains(SecondaryStat.CrystalChargeBuffIcon) && summon != null) {
                summon.resetAncientCrystal();
                this.client.announce(SummonPacket.SummonedSkillState(summon, 2));
                if (this.map != null) {
                    this.map.broadcastMessage(this, SummonPacket.SummonedStateChange(summon, 2, summon.getAcState1(), summon.getAcState2()), true);
                    this.map.broadcastMessage(this, SummonPacket.SummonedSpecialEffect(summon, 2), true);
                }
            }
        }

    }

    public String getBattleGrondJobName() {
        return this.BattleGrondJobName;
    }

    public void setBattleGrondJobName(String BattleGrondJobName) {
        this.BattleGrondJobName = BattleGrondJobName;
    }

    public final void registerEffect(Map<SecondaryStat, Pair<Integer, Integer>> statups, int z, int chrID, long startTime, long startChargeTime, int duration, MapleStatEffect.CancelEffectAction cancelAction) {
        this.effectLock.lock();

        try {
            boolean hided = false;

            Map.Entry entry;
            for(Iterator var11 = statups.entrySet().iterator(); var11.hasNext(); ((List)this.effects.computeIfAbsent((SecondaryStat)entry.getKey(), (k) -> {
                return new ArrayList();
            })).add(new SecondaryStatValueHolder(chrID, (Integer)((Pair)entry.getValue()).getRight(), z, startTime, startChargeTime, duration, this.getSkillEffect((Integer)((Pair)entry.getValue()).getLeft()), cancelAction))) {
                entry = (Map.Entry)var11.next();
                if (((Integer)((Pair)entry.getValue()).getLeft() == 9001004 || (Integer)((Pair)entry.getValue()).getLeft() == 9101004) && !hided) {
                    hided = !hided;
                    this.map.broadcastBelowGmLvMessage(this, MaplePacketCreator.removePlayerFromMap(this.getId()), false);
                }

                if (this.isDebug()) {
                    String var10002 = String.valueOf(entry.getKey());
                    this.dropDebugMessage(0, "[BUFF] Register Stat:" + var10002 + " value:" + String.valueOf(entry.getValue()));
                }
            }
        } finally {
            this.effectLock.unlock();
        }

        if (statups != null && statups.size() > 0) {
            this.stats.recalcLocalStats(false, this);
        }

    }

    public final void registerEffect(MapleStatEffect effect, Map<SecondaryStat, Integer> statups, int z, int from, long startTime, long startChargeTime, int duration, MapleStatEffect.CancelEffectAction cancelAction) {
        if (effect.isSkill() && (effect.getSourceId() == 9001004 || effect.getSourceId() == 9101004)) {
            this.map.broadcastBelowGmLvMessage(this, MaplePacketCreator.removePlayerFromMap(this.getId()), false);
        }

        this.effectLock.lock();

        try {
            Map.Entry entry;
            SecondaryStat stat;
            String var10002;
            for(Iterator var11 = statups.entrySet().iterator(); var11.hasNext(); ((List)this.effects.computeIfAbsent(stat, (k) -> {
                return new ArrayList();
            })).add(new SecondaryStatValueHolder(from, (Integer)entry.getValue(), z, startTime, startChargeTime, duration, effect, cancelAction))) {
                entry = (Map.Entry)var11.next();
                stat = (SecondaryStat)entry.getKey();
                if (this.isDebug()) {
                    var10002 = String.valueOf(stat);
                    this.dropDebugMessage(0, "[BUFF] Register Stat:" + var10002 + " value:" + String.valueOf(entry.getValue()));
                }
            }

            if (this.isDebug() && !effect.getStatups().isEmpty()) {
                var10002 = String.valueOf(effect);
                this.dropDebugMessage(1, "[BUFF] Register Effect: " + var10002 + " Duration:" + duration);
            }
        } finally {
            this.effectLock.unlock();
        }

        if (statups != null && statups.size() > 0) {
            this.stats.recalcLocalStats(false, this);
        }

    }

    public Integer getBuffedSkill_X(SecondaryStat stat) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        return mbsvh == null ? null : mbsvh.effect.getX();
    }

    public Integer getBuffedSkill_Y(SecondaryStat stat) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        return mbsvh == null ? null : mbsvh.effect.getY();
    }

    public boolean isBuffFrom(SecondaryStat stat, Skill skill) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        return mbsvh != null && mbsvh.effect != null && skill != null && mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skill.getId();
    }

    public boolean hasBuffSkill(int skillId) {
        return this.getBuffStatValueHolder(skillId) != null;
    }

    public void setBuffStatValue(SecondaryStat stat, int value) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        if (mbsvh != null) {
            mbsvh.value = value;
        }
    }

    public void setSchedule(SecondaryStat stat, ScheduledFuture<?> sched) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        if (mbsvh != null) {
            mbsvh.schedule.cancel(false);
            mbsvh.schedule = sched;
        }
    }

    public Long getBuffedStartTime(SecondaryStat stat) {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(stat);
        return mbsvh == null ? null : mbsvh.startTime;
    }

    public void dispel() {
        if (!isHidden()) {
            List<SecondaryStatValueHolder> allBuffs = new LinkedList<>();
            getAllEffects().values().forEach(allBuffs::addAll);
            for (SecondaryStatValueHolder mbsvh : allBuffs) {
                if (mbsvh.effect.isSkill() && mbsvh.schedule != null && !mbsvh.effect.isMorph() && !mbsvh.effect.isGmBuff() && !mbsvh.effect.is騎乘技能() && !mbsvh.effect.is矛之鬥氣() && !mbsvh.effect.is蓄能系統() && !mbsvh.effect.isNotRemoved()) {
                    cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                }
            }
            allBuffs.clear();
        }
    }

    public void dispelSkill(int skillId) {
        List<SecondaryStatValueHolder> allBuffs = new LinkedList<>();
        getAllEffects().values().forEach(allBuffs::addAll);
        for (SecondaryStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() == skillId) {
                cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                break;
            }
        }
        allBuffs.clear();
    }



    /*
     * 清除職業ID所有的BUFF狀態
     */
    public void dispelBuffByJobId(int jobId) {
        List<SecondaryStatValueHolder> allBuffs = new LinkedList<>();
        getAllEffects().values().forEach(allBuffs::addAll);
        for (SecondaryStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isSkill() && mbsvh.effect.getSourceId() / 10000 == jobId) {
                cancelEffect(mbsvh.effect, false, mbsvh.startTime);
            }
        }
        allBuffs.clear();
    }


    /*
     * 清除所有召喚獸
     */
    public void dispelSummons() {
        List<SecondaryStatValueHolder> allBuffs = new LinkedList<>();
        getAllEffects().values().forEach(allBuffs::addAll);
        for (SecondaryStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.getSummonMovementType() != null) {
                cancelEffect(mbsvh.effect, false, mbsvh.startTime);
            }
        }
        allBuffs.clear();
    }

    /*
     * 清除指定技能ID的BUFF狀態
     */
    public void dispelBuff(int buffId) {
        List<SecondaryStatValueHolder> allBuffs = new LinkedList<>();
        getAllEffects().values().forEach(allBuffs::addAll);
        for (SecondaryStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.getSourceId() == buffId) {
                cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                break;
            }
        }
        allBuffs.clear();
    }

    public void cancelMorphs() {
        List<SecondaryStatValueHolder> allBuffs = new LinkedList<>();
        getAllEffects().values().forEach(allBuffs::addAll);
        for (SecondaryStatValueHolder mbsvh : allBuffs) {
            switch (mbsvh.effect.getSourceId()) {
                case 凱撒.終極型態:
                case 凱撒.進階終極形態:
                case 凱撒.超_終極型態:
                    return; // Since we can't have more than 1, save up on loops
                default:
                    if (mbsvh.effect.isMorph()) {
                        cancelEffect(mbsvh.effect, false, mbsvh.startTime);
                    }
            }
        }
        allBuffs.clear();
    }

    /*
     * 獲取變身BUFF狀態的技能ID
     */
    public int getMorphState() {
        List<SecondaryStatValueHolder> allBuffs = new LinkedList<>();
        getAllEffects().values().forEach(allBuffs::addAll);
        for (SecondaryStatValueHolder mbsvh : allBuffs) {
            if (mbsvh.effect.isMorph()) {
                return mbsvh.effect.getSourceId();
            }
        }
        allBuffs.clear();
        return -1;
    }

    public void silentGiveBuffs(List<PlayerBuffValueHolder> buffs) {
        if (buffs != null) {
            Iterator var2 = buffs.iterator();

            while(var2.hasNext()) {
                PlayerBuffValueHolder mbsvh = (PlayerBuffValueHolder)var2.next();
                mbsvh.effect.silentApplyBuff(this, mbsvh.startTime, mbsvh.localDuration, mbsvh.statup, mbsvh.fromChrId);
            }

        }
    }

    public List<PlayerBuffValueHolder> getAllBuffs() {
        List<PlayerBuffValueHolder> ret = new ArrayList();
        Map<Pair<Integer, Byte>, Integer> alreadyDone = new HashMap();
        this.getAllEffects().forEach((k, v) -> {
            v.forEach((mbsvh) -> {
                mbsvh.cancel();
                Pair<Integer, Byte> key = new Pair(mbsvh.effect.getSourceId(), (byte)mbsvh.effect.getLevel());
                if (alreadyDone.containsKey(key)) {
                    ((PlayerBuffValueHolder)ret.get((Integer)alreadyDone.get(key))).statup.put(k, mbsvh.value);
                } else {
                    alreadyDone.put(key, ret.size());
                    Map<SecondaryStat, Integer> map = new EnumMap(SecondaryStat.class);
                    map.put(k, mbsvh.value);
                    ret.add(new PlayerBuffValueHolder(mbsvh.startTime, mbsvh.effect, map, mbsvh.localDuration, mbsvh.fromChrID));
                }

            });
        });
        return ret;
    }

    /*
     * 尖兵能量恢復
     */
    public int getPowerCountByJob() {
        switch (getJob()) {
            case 3610:
                return 10;
            case 3611:
                return 15;
            case 3612:
                return 20;
        }
        return 5;
    }

    public void silentEnforceMaxHpMp() {
        this.stats.setMp(this.stats.getMp());
        this.stats.setHp(this.stats.getHp(), true, this);
    }

    public void enforceMaxHpMp() {
        Map<MapleStat, Long> statup = new EnumMap(MapleStat.class);
        if (this.stats.getMp() > this.stats.getCurrentMaxMP()) {
            this.stats.setMp(this.stats.getMp());
            statup.put(MapleStat.MP, (long)this.stats.getMp());
        }

        if (this.stats.getHp() > this.stats.getCurrentMaxHP()) {
            this.stats.setHp(this.stats.getHp(), this);
            statup.put(MapleStat.HP, (long)this.stats.getHp());
        }

        if (statup.size() > 0) {
            this.client.announce(MaplePacketCreator.updatePlayerStats(statup, this));
        }

    }

    public MapleMap getMap() {
        return this.map;
    }

    public void setMap(int PmapId) {
        this.mapid = PmapId;
    }

    public void setMap(MapleMap newmap) {
        this.map = newmap;
    }

    public MonsterBook getMonsterBook() {
        return this.monsterbook;
    }

    public int getMapId() {
        return this.map != null ? this.map.getId() : this.mapid;
    }

    public byte getInitialSpawnpoint() {
        return this.initialSpawnPoint;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlessOfFairyOrigin() {
        return this.BlessOfFairy_Origin;
    }

    public String getBlessOfEmpressOrigin() {
        return this.BlessOfEmpress_Origin;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int newLevel) {
        if (newLevel <= 0) {
            newLevel = 1;
        } else if (newLevel >= ServerConfig.CHANNEL_PLAYER_MAXLEVEL) {
            newLevel = ServerConfig.CHANNEL_PLAYER_MAXLEVEL;
        }

        this.level = newLevel;
    }

    public int getFame() {
        return this.fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public int getFallCounter() {
        return this.fallcounter;
    }

    public void setFallCounter(int fallcounter) {
        this.fallcounter = fallcounter;
    }

    public int getCriticalGrowth() {
        return this.criticalgrowth;
    }

    public void setCriticalGrowth(int critical) {
        this.criticalgrowth = critical;
    }

    public MapleClient getClient() {
        return this.client;
    }

    public void setClient(MapleClient client) {
        this.client = client;
    }

    public long getExp() {
        return this.exp.get();
    }

    public void setExp(long exp) {
        this.exp.set(exp);
    }

    public short getRemainingAp() {
        return this.remainingAp;
    }

    public void setRemainingAp(short remainingAp) {
        this.remainingAp = remainingAp;
    }

    public int getRemainingSp() {
        return this.remainingSp[JobConstants.getSkillBookByJob(this.job)];
    }

    public void setRemainingSp(int remainingSp) {
        this.remainingSp[JobConstants.getSkillBookByJob(this.job)] = remainingSp;
    }

    public int getRemainingSp(int skillbook) {
        return this.remainingSp[skillbook];
    }

    public int[] getRemainingSps() {
        return this.remainingSp;
    }

    public int getRemainingSpSize() {
        int ret = 0;
        int[] var2 = this.remainingSp;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int aRemainingSp = var2[var4];
            if (aRemainingSp > 0) {
                ++ret;
            }
        }

        return ret;
    }

    public short getHpApUsed() {
        return this.hpApUsed;
    }

    public void setHpApUsed(short hpApUsed) {
        this.hpApUsed = hpApUsed;
        this.stats.recalcLocalStats(this);
    }

    public short getMpApUsed() {
        return this.mpApUsed;
    }

    public void setMpApUsed(short mpApUsed) {
        this.mpApUsed = mpApUsed;
        this.stats.recalcLocalStats(this);
    }

    public void useHpAp(int amount) {
        if (amount != 0) {
            this.setHpApUsed((short)(this.hpApUsed + amount));
            Map<MapleStat, Long> statup = new EnumMap(MapleStat.class);
            statup.put(MapleStat.MAX_HP, (long)this.stats.getMaxHp());
            this.client.announce(MaplePacketCreator.updatePlayerStats(statup, true, this));
        }
    }

    public void useMpAp(int amount) {
        if (amount != 0) {
            this.setMpApUsed((short)(this.mpApUsed + amount));
            Map<MapleStat, Long> statup = new EnumMap(MapleStat.class);
            statup.put(MapleStat.MAX_MP, (long)this.stats.getMaxMp());
            this.client.announce(MaplePacketCreator.updatePlayerStats(statup, true, this));
        }
    }

    public boolean isHidden() {
        return this.getBuffSource(SecondaryStat.DarkSight) / 1000000 == 9;
    }

    public byte getSkinColor() {
        return this.skinColor;
    }

    public void setSkinColor(byte skinColor) {
        this.skinColor = skinColor;
    }

    public short getJob() {
        return this.job;
    }

    public void setJob(int jobId) {
        this.job = (short)jobId;
    }

    public short getJobWithSub() {
        return (short)MapleJob.getIdWithSub(this.job, this.subcategory);
    }

    public void writeJobData(MaplePacketLittleEndianWriter mplew) {
        mplew.writeShort(this.getJob());
        mplew.writeShort(this.getSubcategory());
    }

    public byte getGender() {
        return this.gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public byte getSecondGender() {
        return JobConstants.is神之子(this.job) ? 1 : this.gender;
    }

    public void zeroTag() {
        if (JobConstants.is神之子(this.job)) {
            this.addhpmpLock.lock();

            try {
                int betaMaxHP = this.stats.getBetaMaxHP();
                int hp = this.stats.getHp();
                this.setKeyValue("Zero_Look", this.isBeta() ? "0" : "1");
                this.stats.setBetaMaxHP(hp);
                this.stats.setHp(betaMaxHP);
            } finally {
                this.addhpmpLock.unlock();
            }

            this.updateHPMP(false);
            this.modifiedAvatar();
            this.client.announce(MaplePacketCreator.zeroInfo(this, 65535, this.isBeta()));
        }
    }

    public boolean isBeta() {
        return "1".equals(this.getKeyValue("Zero_Look"));
    }

    public int getHair() {
        return this.hair;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public int getSecondHair() {
        if (JobConstants.is神之子(this.job)) {
            if (this.getKeyValue("Second_Hair") == null) {
                this.setKeyValue("Second_Hair", "37623");
            }

            return Integer.parseInt(this.getKeyValue("Second_Hair"));
        } else if (JobConstants.is天使破壞者(this.job)) {
            if (this.getKeyValue("Second_Hair") == null) {
                this.setKeyValue("Second_Hair", "37141");
            }

            return Integer.parseInt(this.getKeyValue("Second_Hair"));
        } else {
            return this.hair;
        }
    }

    public void setSecondHair(int hair) {
        this.setKeyValue("Second_Hair", String.valueOf(hair));
    }

    public byte getSecondSkinColor() {
        if (!JobConstants.is神之子(this.job) && !JobConstants.is天使破壞者(this.job)) {
            return this.skinColor;
        } else {
            if (this.getKeyValue("Second_Skin") == null) {
                this.setKeyValue("Second_Skin", "0");
            }

            return Byte.parseByte(this.getKeyValue("Second_Skin"));
        }
    }

    public void setSecondSkinColor(byte b) {
        this.setKeyValue("Second_Skin", String.valueOf(b));
    }

    public int getFace() {
        return this.face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getSecondFace() {
        if (JobConstants.is神之子(this.job)) {
            if (this.getKeyValue("Second_Face") == null) {
                this.setKeyValue("Second_Face", "21290");
            }

            return Integer.parseInt(this.getKeyValue("Second_Face"));
        } else if (JobConstants.is天使破壞者(this.job)) {
            if (this.getKeyValue("Second_Face") == null) {
                this.setKeyValue("Second_Face", "21173");
            }

            return Integer.parseInt(this.getKeyValue("Second_Face"));
        } else {
            return this.face;
        }
    }

    public void setSecondFace(int face) {
        this.setKeyValue("Second_Face", String.valueOf(face));
    }

    public boolean changeBeauty(int styleID) {
        return this.changeBeauty(styleID, false);
    }

    public boolean changeBeauty(int styleID, boolean isSecond) {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        if (類型.膚色(styleID)) {
            if (!ii.isSkinExist(styleID)) {
                return false;
            }

            if (isSecond) {
                this.setSecondSkinColor((byte)styleID);
                if (JobConstants.is神之子(this.getJob())) {
                    this.send(MaplePacketCreator.zeroInfo(this, 8, true));
                }
            } else {
                this.setSkinColor((byte)styleID);
                this.updateSingleStat(MapleStat.SKIN, (long)styleID);
            }
        } else if (類型.臉型(styleID)) {
            if (!ii.isFaceExist(styleID)) {
                return false;
            }

            if (isSecond) {
                this.setSecondFace(styleID);
                if (JobConstants.is神之子(this.getJob())) {
                    this.send(MaplePacketCreator.zeroInfo(this, 32, true));
                }
            } else {
                this.setFace(styleID);
                this.updateSingleStat(MapleStat.FACE, (long)styleID);
            }
        } else {
            if (!類型.髮型(styleID)) {
                return false;
            }

            if (!ii.isHairExist(styleID)) {
                return false;
            }

            if (isSecond) {
                this.setSecondHair(styleID);
                if (JobConstants.is神之子(this.getJob())) {
                    this.send(MaplePacketCreator.zeroInfo(this, 16, true));
                }
            } else {
                this.setHair(styleID);
                this.updateSingleStat(MapleStat.HAIR, (long)styleID);
            }
        }

        if (isSecond) {
            if (JobConstants.is天使破壞者(this.getJob())) {
                this.send(MaplePacketCreator.DressUpInfoModified(this));
            }
        } else {
            this.equipChanged();
        }

        return true;
    }

    public boolean changeAndroidBeauty(int styleID) {
        if (this.android == null) {
            return false;
        } else {
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            if (類型.膚色(styleID)) {
                if (!ii.isSkinExist(styleID)) {
                    return false;
                }

                this.android.setSkin(styleID);
            } else if (類型.臉型(styleID)) {
                if (!ii.isFaceExist(styleID)) {
                    return false;
                }

                this.android.setFace(styleID);
            } else {
                if (!類型.髮型(styleID)) {
                    return false;
                }

                if (!ii.isHairExist(styleID)) {
                    return false;
                }

                this.android.setHair(styleID);
            }

            this.android.saveToDb();
            this.updateAndroidLook();
            return true;
        }
    }

    public Point getOldPosition() {
        return this.old;
    }

    public void setOldPosition(Point x) {
        this.old = x;
    }

    public int getLastAttackSkillId() {
        return this.lastAttackSkillId;
    }

    public void setLastAttackSkillId(int skillId) {
        this.lastAttackSkillId = skillId;
    }

    public void setRemainingSp(int remainingSp, int skillbook) {
        this.remainingSp[skillbook] = remainingSp;
    }

    public boolean isInvincible() {
        return this.invincible;
    }

    public void setInvincible(boolean invinc) {
        this.invincible = invinc;
        if (this.invincible) {
            SkillFactory.getSkill(1010).getEffect(1).applyTo(this);
        } else {
            this.dispelBuff(1010);
        }

    }

    public CheatTracker getCheatTracker() {
        return this.anticheat;
    }

    public BuddyList getBuddylist() {
        return this.buddylist;
    }

    public void addFame(int famechange) {
        this.fame += famechange;
        this.getTrait(MapleTraitType.charm).addLocalExp(famechange);
        this.insertRanking("人氣排行", this.fame);
    }

    public void updateFame() {
        this.updateSingleStat(MapleStat.POPULARITY, (long)this.fame);
    }

    public void gainFame(int famechange, boolean show) {
        this.fame += famechange;
        this.updateSingleStat(MapleStat.POPULARITY, (long)this.fame);
        if (show && famechange != 0) {
            this.client.announce(MaplePacketCreator.getShowFameGain(famechange));
        }

    }

    public void updateHair(int hair) {
        this.setHair(hair);
        this.updateSingleStat(MapleStat.HAIR, (long)hair);
        this.equipChanged();
    }

    public void updateFace(int face) {
        this.setFace(face);
        this.updateSingleStat(MapleStat.FACE, (long)face);
        this.equipChanged();
    }

    public void changeMapBanish(int mapid, String portal, String msg) {
        if (msg != null) {
            this.dropMessage(5, msg);
        }

        MapleMap maps = this.client.getChannelServer().getMapFactory().getMap(mapid);
        this.changeMap(maps, maps.getPortal(portal));
    }

    public final void changeMap(int mapID, int portalID) {
        MapleMap map = this.client.getChannelServer().getMapFactory().getMap(mapID);
        this.changeMap(map, map.getPortal(portalID));
    }

    public void changeMapToPosition(MapleMap to, Point pos) {
        this.changeMapInternal(to, pos, MaplePacketCreator.sendFieldToPosition(this, to, pos));
    }

    public void changeMap(MapleMap to, Point pos) {
        this.changeMapInternal(to, pos, MaplePacketCreator.getWarpToMap(this, to, 128, true));
    }

    public void changeMap(MapleMap to) {
        this.changeMapInternal(to, to.getPortal(0).getPosition(), MaplePacketCreator.getWarpToMap(this, to, 0, false));
    }

    public void changeMap(MapleMap to, MaplePortal pto) {
        this.changeMapInternal(to, pto.getPosition(), MaplePacketCreator.getWarpToMap(this, to, pto.getId(), false));
    }

    public final void reviveMap(MapleMap map, MaplePortal portal) {
        this.changeMapInternal(map, portal.getPosition(), MaplePacketCreator.getWarpToMap(this, map, portal.getId(), true));
    }

    private void changeMapInternal(MapleMap to, Point pos, byte[] warpPacket) {
        if (to == null) {
            this.dropMessage(5, "changeMapInternal to Null");
        } else if (MapleAntiMacro.isAntiNow(this.name)) {
            this.dropMessage(5, "被使用測謊機時無法操作。");
        } else if (!this.isIntern() && !to.canEnterField(this.id)) {
            this.dropMessage(5, "地圖已經開啟防搶圖模式。");
        } else {
            int nowmapid = this.map.getId();
            this.saveToDB(false, false);
            boolean pyramid = this.pyramidSubway != null;
            if (this.map.getId() == nowmapid) {
                this.updateLastChangeMapTime();
                this.client.announce(warpPacket);
                boolean shouldChange = this.client.getChannelServer().getPlayerStorage().getCharacterById(this.getId()) != null;
                this.client.announce(EffectPacket.playPortalSE());
                this.map.userLeaveField(this);
                if (shouldChange) {
                    this.map = to;
                    this.setStance(0);
                    this.setPosition(new Point(pos.x, pos.y - 50));
                    to.userEnterField(this);
                    this.stats.recalcLocalStats(this);
                }
            }

            if (pyramid && this.pyramidSubway != null) {
                this.pyramidSubway.onChangeMap(this, to.getId());
            }

            this.setOverMobLevelTip(false);
            this.playerObservable.update();
            if (JobConstants.is凱殷(this.job)) {
                Iterator<Map.Entry<Integer, ForceAtomObject>> iterator = this.getForceAtomObjects().entrySet().iterator();

                while(iterator.hasNext()) {
                    Map.Entry<Integer, ForceAtomObject> sword = (Map.Entry)iterator.next();
                    if (((ForceAtomObject)sword.getValue()).SkillId == 63101006) {
                        iterator.remove();
                    }
                }
            }

            if (this.eventInstance != null) {
                this.eventInstance.getHooks().playerChangedMap(this, this.getMap());
            }

        }
    }

    public void cancelChallenge() {
        if (this.challenge != 0 && this.client.getChannelServer() != null) {
            MapleCharacter chr = this.client.getChannelServer().getPlayerStorage().getCharacterById(this.challenge);
            if (chr != null) {
                chr.dropMessage(6, this.getName() + " 拒絕了您的請求.");
                chr.setChallenge(0);
            }

            this.dropMessage(6, "您的請求被拒絕.");
            this.challenge = 0;
        }

    }

    public void leaveMap(MapleMap map) {
        this.visibleMapObjectsLock.readLock().lock();

        ArrayList toRemove;
        try {
            toRemove = new ArrayList(this.visibleMapObjects);
        } finally {
            this.visibleMapObjectsLock.readLock().unlock();
        }

        Iterator<MapleMapObject> iterator = toRemove.iterator();

        while(iterator.hasNext()) {
            MapleMapObject mmo = (MapleMapObject)iterator.next();
            iterator.remove();
            if (mmo.getType() == MapleMapObjectType.MONSTER) {
                ((MapleMonster)mmo).removeController(this);
            }
        }

        this.clearVisibleMapObject();
        this.cancelMapTimeLimitTask();
        this.clearLinkMid();
        this.cancelFishingTask();
        this.cancelChallenge();
        this.resetConversation();
        if (!this.getMechDoors().isEmpty()) {
            this.removeMechDoor();
        }

        if (this.getTrade() != null) {
            MapleTrade.cancelTrade(this.getTrade(), this.client, this);
        }

        this.setChair((PortableChair)null);
        this.getTempValues().clear();
    }

    public void clearVisibleMapObject() {
        this.visibleMapObjectsLock.writeLock().lock();

        try {
            this.visibleMapObjects.clear();
        } finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }

    }

    public void changeJob(int newJob) {
        try {
            int oldJob = this.job;
            int oldSub = this.subcategory;
            MapleJob[] var4 = MapleJob.values();
            int maxhp = var4.length;

            int maxmp;
            for(maxmp = 0; maxmp < maxhp; ++maxmp) {
                MapleJob j = var4[maxmp];
                if (j.getIdWithSub() == newJob && newJob != j.getId()) {
                    newJob = j.getId();
                    this.setSubcategory(j.getSub());
                    break;
                }
            }

            this.dispelEffect(SecondaryStat.ShadowPartner);
            int tmpJob = newJob;
            if (JobConstants.is管理員(newJob) || JobConstants.getJobBranch(newJob) == 5 && !JobConstants.isSameJob(oldJob, newJob) || JobConstants.getJobBranch(oldJob) != JobConstants.getJobBranch(newJob) || JobConstants.is傑諾(oldJob) && !JobConstants.is傑諾(newJob) || !JobConstants.is傑諾(oldJob) && JobConstants.is傑諾(newJob) || JobConstants.is惡魔復仇者(oldJob) && !JobConstants.is惡魔復仇者(newJob) || !JobConstants.is惡魔復仇者(oldJob) && JobConstants.is惡魔復仇者(newJob)) {
                this.resetStats(4, 4, 4, 4);
            }

            this.job = (short)newJob;
            if (this.getSubcategory() == 1 && newJob == 400) {
                tmpJob = 65936;
            }

            this.updateSingleStat(MapleStat.JOB, (long)tmpJob);
            if (!JobConstants.is零轉職業(newJob) && !JobConstants.is神之子(newJob)) {
                Pair<Integer, Integer> sps = getJobChangeSP(newJob, this.getSubcategory(), JobConstants.getSkillBookByJob(newJob));
                if (sps != null) {
                    int[] var10000 = this.remainingSp;
                    int var10001 = (Integer)sps.getLeft();
                    var10000[var10001] += (Integer)sps.getRight();
                    if (JobConstants.isSeparatedSpJob(newJob)) {
                        this.client.announce(UIPacket.getSPMsg(((Integer)sps.getRight()).byteValue(), (short)newJob));
                    }
                }

                if (newJob % 10 >= 1 && JobConstants.isSameJob(oldJob, newJob)) {
                    this.remainingAp = (short)(this.remainingAp + 5);
                    this.updateSingleStat(MapleStat.AVAILABLE_AP, (long)this.remainingAp);
                }

                if (this.level == 10) {
                    this.resetStats(4, 4, 4, 4);
                }

                if (JobConstants.is龍魔導士(this.job)) {
                    if (this.dragon == null) {
                        this.dragon = new MapleDragon(this);
                    }

                    this.dragon.setJobid(this.job);
                    this.dragon.sendSpawnData(this.client);
                }

                this.updateSingleStat(MapleStat.AVAILABLE_SP, 0L);
            }

            short slot;
            if (JobConstants.is零轉職業(oldJob) && !JobConstants.is零轉職業(newJob) || !JobConstants.isSameJob(oldJob, newJob) || oldSub != this.subcategory) {
                slot = JobConstants.getBeginner((short)oldJob);
                short newBeginner = JobConstants.getBeginner((short)newJob);
                this.spReset(slot != newBeginner ? slot : -1);
            }

            if (!JobConstants.isSameJob(oldJob, newJob)) {
                this.resetVSkills();
                if (!this.isIntern()) {
                    slot = this.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
                    if (slot != -1) {
                        MapleInventoryManipulator.unequip(this.client, (short)-10, slot);
                    }

                    slot = this.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
                    if (slot != -1) {
                        MapleInventoryManipulator.unequip(this.client, (short)-11, slot);
                    }
                }
            }

            maxhp = this.stats.getMaxHp(false);
            maxmp = this.stats.getMaxMp(false);
            switch (this.job) {
                case 100:
                case 1100:
                case 2100:
                case 3200:
                case 5100:
                case 6100:
                    maxhp += Randomizer.rand(200, 250);
                    break;
                case 110:
                case 120:
                case 130:
                case 1110:
                case 2110:
                case 3210:
                case 5110:
                    maxhp += Randomizer.rand(300, 350);
                    break;
                case 200:
                case 2200:
                case 2700:
                    maxmp += Randomizer.rand(100, 150);
                    break;
                case 210:
                case 220:
                case 230:
                case 2710:
                    maxmp += Randomizer.rand(400, 450);
                    break;
                case 300:
                case 400:
                case 500:
                case 501:
                case 509:
                case 2300:
                case 2400:
                case 3300:
                case 3500:
                case 3600:
                    maxhp += Randomizer.rand(100, 150);
                    maxmp += Randomizer.rand(25, 50);
                    break;
                case 310:
                case 320:
                case 410:
                case 420:
                case 430:
                case 510:
                case 520:
                case 530:
                case 580:
                case 590:
                case 1310:
                case 1410:
                case 2310:
                case 2410:
                case 3310:
                case 3510:
                case 3610:
                    maxhp += Randomizer.rand(200, 250);
                    maxhp += Randomizer.rand(150, 200);
                    break;
                case 800:
                case 900:
                    maxhp += 99999;
                    maxmp += 99999;
                    break;
                case 3100:
                    maxhp += Randomizer.rand(200, 250);
                    break;
                case 3101:
                case 3120:
                    maxhp += Randomizer.rand(500, 800);
                    break;
                case 3110:
                    maxhp += Randomizer.rand(300, 350);
                    break;
                case 6110:
                    maxhp += Randomizer.rand(350, 400);
                    maxmp += Randomizer.rand(120, 180);
            }

            if (maxhp >= ServerConfig.CHANNEL_PLAYER_MAXHP) {
                maxhp = ServerConfig.CHANNEL_PLAYER_MAXHP;
            }

            if (maxmp >= ServerConfig.CHANNEL_PLAYER_MAXMP) {
                maxmp = ServerConfig.CHANNEL_PLAYER_MAXMP;
            }

            if (JobConstants.is神之子(this.job)) {
                maxmp = 100;
                this.checkZeroWeapon();
            } else if (JobConstants.is陰陽師(this.job)) {
                maxmp = 100;
            } else if (JobConstants.isNotMpJob(this.job)) {
                maxmp = 10;
            }

            this.stats.setInfo(maxhp, maxmp, this.stats.getCurrentMaxHP(), this.stats.getCurrentMaxMP());
            this.characterCard.recalcLocalStats(this);
            this.stats.recalcLocalStats(this);
            Map<MapleStat, Long> statup = new EnumMap(MapleStat.class);
            statup.put(MapleStat.HP, (long)this.stats.getCurrentMaxHP());
            statup.put(MapleStat.MP, (long)this.stats.getCurrentMaxMP());
            statup.put(MapleStat.MAX_HP, (long)maxhp);
            statup.put(MapleStat.MAX_MP, (long)maxmp);
            this.client.announce(MaplePacketCreator.updatePlayerStats(statup, this));
            this.notifyChanges();
            this.guildUpdate();
            if (this.dragon != null) {
                this.map.broadcastMessage(SummonPacket.removeDragon(this.id));
                this.dragon = null;
            }

            this.baseSkills();
            if (JobConstants.is龍魔導士(newJob)) {
                if (this.getBuffedValue(SecondaryStat.RideVehicle) != null) {
                    this.dispelEffect(SecondaryStat.RideVehicle);
                }

                this.makeDragon();
            }

            if (JobConstants.is陰陽師(newJob)) {
                if (this.getBuffedValue(SecondaryStat.RideVehicle) != null) {
                    this.dispelEffect(SecondaryStat.RideVehicle);
                }

                this.initHaku();
            }

            if (newJob == 3300) {
                String customData = "1=1;2=1;3=1;4=1;5=1;6=1;7=1;8=1;9=1";
                this.setQuestAdd(MapleQuest.getInstance(23008), (byte)1, customData);
                this.client.announce(MaplePacketCreator.updateInfoQuest(23008, customData));
                this.client.announce(MaplePacketCreator.updateJaguar(this));
            }

            this.updateJobItems();
            this.map.broadcastMessage(this, EffectPacket.showJobChanged(this.getId(), this.getJob()), false);
            this.map.broadcastMessage(this, MaplePacketCreator.updateCharLook(this), false);
            if (this.android != null) {
                this.android.showEmotion(this, "job");
            }

            this.playerObservable.update();
        } catch (Exception var9) {
            Exception e = var9;
            log.error("轉職錯誤", e);
        }

    }

    public void updateJobItems() {
        int n = 1;
        int job = this.job;
        int n2 = 0;
        switch (job) {
            case 3001:
                n2 = 1099001;
                break;
            case 3002:
                n2 = 1353000;
                break;
            case 3100:
                n2 = 1099000;
                break;
            case 3101:
                MapleInventoryManipulator.addItemAndEquip(this.getClient(), 1050249, (short)-5, (String)null, -1L, 0, "From System", false);
                MapleInventoryManipulator.addItemAndEquip(this.getClient(), 1070029, (short)-7, (String)null, -1L, 0, "From System", false);
                MapleInventoryManipulator.addItemAndEquip(this.getClient(), 1102505, (short)-9, (String)null, -1L, 0, "From System", false);
                MapleInventoryManipulator.addItemAndEquip(this.getClient(), 1099006, (short)-10, (String)null, -1L, 0, "From System", true);
                MapleInventoryManipulator.addItemAndEquip(this.getClient(), 1232001, (short)-11, (String)null, -1L, 0, "From System", true);
                this.setHair(this.getGender() == 0 ? '蹬' : '鉊');
                this.updateSingleStat(MapleStat.HAIR, (long)this.getHair());
                n2 = 1099006;
                break;
            case 3112:
                n = 2;
            case 3110:
            case 3111:
                n2 = 1099001 + job % 10 + job % 100 / 10;
                break;
            case 3122:
                n = 2;
            case 3120:
            case 3121:
                n2 = 1099005 + job % 10 + job % 100 / 10;
                break;
            case 3612:
                n = 2;
            case 3600:
            case 3610:
            case 3611:
                n2 = 1353001 + job % 10 + job % 100 / 10;
                break;
            case 5112:
                n = 2;
            case 5100:
            case 5110:
            case 5111:
                n2 = 1098000 + job % 10 + job % 100 / 10;
                break;
            case 6000:
                n2 = 1352500;
                break;
            case 6001:
                n2 = 1352600;
                break;
            case 6112:
                n = 2;
            case 6100:
            case 6110:
            case 6111:
                n2 = 1352500 + job % 10 + job % 100 / 10;
                break;
            case 6512:
                n = 2;
            case 6500:
            case 6510:
            case 6511:
                n2 = 1352601 + job % 10 + job % 100 / 10;
        }

        if (n2 != 0) {
            MapleInventoryManipulator.addItemAndEquip(this.getClient(), n2, (short)-10, (String)null, -1L, n, "From System when Change job. Update Item", true);
        }

    }

    public void checkZeroItem() {
        if (this.job == 10112 && this.level >= 100) {
            if (this.getKeyValue("Zero_Item") == null) {
                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
                int[] toRemovePos = new int[]{-9, -5, -7};
                int[] var3 = toRemovePos;
                int var4 = toRemovePos.length;

                int var5;
                int pos;
                for(var5 = 0; var5 < var4; ++var5) {
                    pos = var3[var5];
                    Item toRemove = this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)pos);
                    if (toRemove != null) {
                        MapleInventoryManipulator.removeFromSlot(this.client, MapleInventoryType.EQUIPPED, toRemove.getPosition(), toRemove.getQuantity(), false);
                    }
                }

                int[][] equips = new int[][]{{1003840, -1}, {1032202, -4}, {1052606, -5}, {1072814, -7}, {1082521, -8}, {1102552, -9}, {1113059, -12}, {1113060, -13}, {1113061, -15}, {1113062, -16}, {1122260, -17}, {1132231, -29}, {1152137, -30}};
                int[][] var12 = equips;
                var5 = equips.length;

                for(pos = 0; pos < var5; ++pos) {
                    int[] i = var12[pos];
                    if (ii.itemExists(i[0])) {
                        Equip equip = ii.getEquipById(i[0]);
                        equip.setPosition((short)((byte)i[1]));
                        equip.setQuantity((short)1);
                        if (i[1] != -12 && i[1] != -13 && i[1] != -15 && i[1] != -16 && i[1] != -30) {
                            equip.renewPotential(false);
                        }

                        equip.setGMLog("系統贈送");
                        this.forceReAddItem_NoUpdate(equip, MapleInventoryType.EQUIPPED);
                        this.client.announce(InventoryPacket.modifyInventory(false, Collections.singletonList(new ModifyInventory(0, equip))));
                    }
                }

                this.equipChanged();
                MapleInventoryManipulator.addById(this.client, 1142634, 1, "系統贈送");
                MapleInventoryManipulator.addById(this.client, 2001530, 100, "系統贈");
                Map<Integer, SkillEntry> list = new HashMap();
                int[] skillIds = new int[]{101000103, 101000203};
                int[] var15 = skillIds;
                int var17 = skillIds.length;

                for(int var18 = 0; var18 < var17; ++var18) {
                    int i = var15[var18];
                    Skill skil = SkillFactory.getSkill(i);
                    if (skil != null && this.getSkillLevel(skil) <= 0) {
                        list.put(i, new SkillEntry(8, (byte)skil.getMaxLevel(), -1L));
                    }
                }

                if (!list.isEmpty()) {
                    this.changeSkillsLevel(list);
                }

                this.setKeyValue("Zero_Item", "True");
            }

            if (this.getQuestStatus(40914) != 2) {
                MapleQuest.getInstance(40914).forceComplete(this, 0);
            }

        }
    }

    public void checkZeroWeapon() {
        if (this.level >= 100) {
            int lazuli = this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-11).getItemId();
            int lapis = this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-10).getItemId();
            if (lazuli != this.getZeroWeapon(false) || lapis != this.getZeroWeapon(true)) {
                MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();

                for(int i = 0; i < 2; ++i) {
                    int itemId = i == 0 ? this.getZeroWeapon(false) : this.getZeroWeapon(true);
                    Equip equip = ii.getEquipById(itemId);
                    equip.setPosition((short)(i == 0 ? -11 : -10));
                    equip.setQuantity((short)1);
                    equip.setGMLog("神之子升級贈送, 時間:" + DateUtil.getNowTime());
                    equip.renewPotential(false);
                    this.forceReAddItem_NoUpdate(equip, MapleInventoryType.EQUIPPED);
                    this.client.announce(InventoryPacket.modifyInventory(false, Collections.singletonList(new ModifyInventory(0, equip))));
                }

                this.equipChanged();
            }
        }
    }

    public int getZeroWeapon(boolean lapis) {
        if (this.level < 100) {
            return lapis ? 1562000 : 1572000;
        } else {
            int weapon = lapis ? 1562001 : 1572001;
            if (this.level < 160) {
                weapon += this.level % 100 / 10;
            } else if (this.level < 170) {
                weapon += 5;
            } else {
                weapon += 6;
            }

            return weapon;
        }
    }

    public void baseSkills() {
        this.checkZeroItem();
        this.checkInnerSkill();
        this.checkHyperAP();
        Map<Integer, SkillEntry> skillMap = new HashMap();
        int job = this.job;
        int level = this.level;
        List<Integer> baseSkills = SkillFactory.getSkillsByLowerJob(job);
        Skill skil;
        int i;
        if (baseSkills != null) {
            Iterator var6 = baseSkills.iterator();

            label204:
            while(true) {
                while(true) {
                    do {
                        do {
                            do {
                                do {
                                    if (!var6.hasNext()) {
                                        break label204;
                                    }

                                    i = (Integer)var6.next();
                                    skil = SkillFactory.getSkill(i);
                                } while(skil == null);
                            } while(skil.isInvisible());
                        } while(skil.isBeginnerSkill());
                    } while(this.getLevel() < skil.getReqLevel());

                    SkillEntry entry = (SkillEntry)this.skills.get(skil.getId());
                    if (entry == null) {
                        entry = new SkillEntry(0, 0, SkillFactory.getDefaultSExpiry(skil));
                    }

                    if (skil.getFixLevel() > 0 && skil.getId() != 130000111 && skil.getId() != 130010111) {
                        entry.skillevel = (byte)skil.getFixLevel();
                        int lv = entry.skillevel - this.getSkillLevel(i);
                        if (lv > 0) {
                            ((Map)skillMap).put(i, entry);
                        }
                    }

                    if (skil.isFourthJob() && entry.masterlevel <= 0 && skil.getMasterLevel() > 0) {
                        entry.masterlevel = (byte)skil.getMasterLevel();
                        ((Map)skillMap).put(i, entry);
                    } else if (!skil.isFourthJob() && entry.masterlevel > 0 && skil.getMasterLevel() > 0) {
                        entry.masterlevel = 0;
                        ((Map)skillMap).put(i, entry);
                    }
                }
            }
        }

        int teachskill = JobConstants.getTeachSkillID(job);
        if (teachskill != -1) {
            int maxLinkLevel = 2;
            if (JobConstants.is神之子(job)) {
                i = level < 100 ? 0 : (level < 125 ? 1 : (level < 150 ? 2 : (level < 175 ? 3 : (level < 200 ? 4 : 5))));
                maxLinkLevel = 5;
            } else {
                i = level < 70 ? 0 : (level < 120 ? 1 : 2);
            }

            skil = SkillFactory.getSkill(teachskill);
            if (skil != null) {
                maxLinkLevel = Math.min(skil.getMaxLevel(), maxLinkLevel);
            }

            if (skil == null) {
                if (this.isAdmin()) {
                    this.dropDebugMessage("[傳授技能] 更新傳授技能出錯，技能不存在。");
                }
            } else if (!this.skills.containsKey(skil) || ((SkillEntry)this.skills.get(skil)).skillevel < i || ((SkillEntry)this.skills.get(skil)).skillevel > maxLinkLevel) {
                ((Map)skillMap).put(teachskill, new SkillEntry((byte)Math.min(maxLinkLevel, i), (byte)(i > 0 ? i : -1), SkillFactory.getDefaultSExpiry(skil)));
            }
        }

        int addSlot = 0;
        short beginner = JobConstants.getBeginner((short)job);
        if (!JobConstants.is皮卡啾(job) && !JobConstants.is雪吉拉(job)) {
            if (JobConstants.is冒險家(job) && JobConstants.is海盜(job) || JobConstants.is隱月(job) || JobConstants.is凱內西斯(job)) {
                skil = SkillFactory.getSkill(beginner * 10000 + 112);
                if (skil != null && this.getSkillLevel(skil) < 2 && JobConstants.getJobGrade(job) > 1) {
                    ((Map)skillMap).put(skil.getId(), new SkillEntry(2, 0, SkillFactory.getDefaultSExpiry(skil)));
                    addSlot = 48;
                    skil = SkillFactory.getSkill(beginner * 10000 + 111);
                    if (skil != null && this.getSkillLevel(skil) != 0) {
                        this.changeSkillLevel(skil, (byte)-1, 0);
                    }
                } else if ((skil = SkillFactory.getSkill(beginner * 10000 + 111)) != null && this.getSkillLevel(skil) < 1 && JobConstants.getJobGrade(job) == 1) {
                    ((Map)skillMap).put(skil.getId(), new SkillEntry(1, 0, SkillFactory.getDefaultSExpiry(skil)));
                    addSlot = 36;
                }
            }
        } else {
            skil = SkillFactory.getSkill(beginner * 10000 + 111);
            if (skil != null && this.getSkillLevel(skil) < 1) {
                ((Map)skillMap).put(skil.getId(), new SkillEntry(1, 0, SkillFactory.getDefaultSExpiry(skil)));
                addSlot = 48;
            }
        }

        int handleRes;
        if (addSlot > 0) {
            MapleInventoryType[] var17 = MapleInventoryType.values();
            handleRes = var17.length;

            for(int var11 = 0; var11 < handleRes; ++var11) {
                MapleInventoryType type = var17[var11];
                if (type.getType() > MapleInventoryType.UNDEFINED.getType() && type.getType() < MapleInventoryType.CASH.getType()) {
                    MapleInventory inv = this.getInventory(type);
                    if (inv != null && inv.getSlotLimit() < addSlot) {
                        inv.setSlotLimit((short)((byte)addSlot));
                        this.client.announce(InventoryPacket.updateInventorySlotLimit(type.getType(), (byte)addSlot));
                    }
                }
            }
        }

        AbstractSkillHandler handler = SkillClassFetcher.getHandlerByJob(this.getJobWithSub());
        if (handler != null) {
            SkillClassApplier applier = new SkillClassApplier();
            applier.skillMap = (Map)skillMap;
            handleRes = handler.baseSkills(this, applier);
            if (handleRes == 0) {
                return;
            }

            if (handleRes == 1) {
                skillMap = applier.skillMap;
            }
        }

        if (!((Map)skillMap).isEmpty()) {
            this.changeSkillsLevel((Map)skillMap);
        }

    }

    public void makeDragon() {
        this.dragon = new MapleDragon(this);
    }

    public MapleDragon getDragon() {
        return this.dragon;
    }

    public void setDragon(MapleDragon d) {
        this.dragon = d;
    }

    public MapleSkillPet getSkillPet() {
        return this.skillPet;
    }

    public void setSpawnSkillPet(MapleSkillPet am) {
        this.skillPet = am;
    }

    public void initHaku() {
        this.setSpawnSkillPet(new MapleSkillPet(this));
    }

    public MapleSkillPet getHaku() {
        return this.skillPet;
    }

    public void gainAp(short ap) {
        this.remainingAp += ap;
        this.updateSingleStat(MapleStat.AVAILABLE_AP, (long)this.remainingAp);
    }

    public void gainSP(int sp) {
        int[] var10000 = this.remainingSp;
        int var10001 = JobConstants.getSkillBookByJob(this.job);
        var10000[var10001] += sp;
        this.updateSingleStat(MapleStat.AVAILABLE_AP, 0L);
        this.client.announce(UIPacket.getSPMsg((byte)sp, this.job));
    }

    public void gainSP(int sp, int skillbook) {
        int[] var10000 = this.remainingSp;
        var10000[skillbook] += sp;
        this.updateSingleStat(MapleStat.AVAILABLE_SP, 0L);
        if (sp >= 0) {
            this.client.announce(UIPacket.getSPMsg((byte)sp, (short)0));
        }

    }

    public void resetSP(int sp) {
        for(int i = 0; i < this.remainingSp.length; ++i) {
            this.remainingSp[i] = sp;
        }

        this.updateSingleStat(MapleStat.AVAILABLE_SP, 0L);
    }

    public void resetAPSP() {
        this.resetSP(0);
        this.gainAp((short)(-this.remainingAp));
    }

    public int getHitCountBat() {
        return this.hitcountbat;
    }

    public void setHitCountBat(int hitcount) {
        this.hitcountbat = hitcount;
    }

    public int getBatCount() {
        return this.batcount;
    }

    public void setBatCount(int count) {
        this.batcount = count;
    }

    public List<Integer> getProfessions() {
        List<Integer> prof = new ArrayList();

        for(int i = 9200; i <= 9204; ++i) {
            if (this.getProfessionLevel(i * 10000) > 0) {
                prof.add(i);
            }
        }

        return prof;
    }

    public byte getProfessionLevel(int id) {
        int ret = this.getSkillLevel(id);
        return ret <= 0 ? 0 : (byte)(ret >>> 24 & 255);
    }

    public short getProfessionExp(int id) {
        int ret = this.getSkillLevel(id);
        return ret <= 0 ? 0 : (short)(ret & '\uffff');
    }

    public boolean addProfessionExp(int id, int expGain) {
        int ret = this.getProfessionLevel(id);
        if (ret > 0 && ret < 12) {
            int newExp = this.getProfessionExp(id) + expGain;
            if (newExp >= GameConstants.getProfessionEXP(ret)) {
                this.changeProfessionLevelExp(id, ret + 1, newExp - GameConstants.getProfessionEXP(ret));
                int traitGain = (int)Math.pow(2.0, (double)(ret + 1));
                switch (id) {
                    case 92000000:
                        ((MapleTrait)this.traits.get(MapleTraitType.sense)).addExp(traitGain, this);
                        break;
                    case 92010000:
                        ((MapleTrait)this.traits.get(MapleTraitType.will)).addExp(traitGain, this);
                        break;
                    case 92020000:
                    case 92030000:
                    case 92040000:
                        ((MapleTrait)this.traits.get(MapleTraitType.craft)).addExp(traitGain, this);
                }

                return true;
            } else {
                this.changeProfessionLevelExp(id, ret, newExp);
                return false;
            }
        } else {
            return false;
        }
    }

    public void changeProfessionLevelExp(int id, int level, int exp) {
        this.changeSingleSkillLevel(SkillFactory.getSkill(id), ((level & 255) << 24) + (exp & '\uffff'), 10);
    }

    public void changeSkillLevel(Skill skill, byte newLevel, int newMasterlevel) {
        this.changeSingleSkillLevel(skill, newLevel, newMasterlevel, -1L);
    }

    public void changeSingleSkillLevel(Skill skill, int newLevel, int newMasterlevel) {
        if (skill != null) {
            this.changeSingleSkillLevel(skill, newLevel, newMasterlevel, SkillFactory.getDefaultSExpiry(skill));
        }
    }

    public void changeSingleSkillLevel(int skillid, int newLevel, int newMasterlevel) {
        Skill skill = SkillFactory.getSkill(skillid);
        if (skill != null) {
            this.changeSingleSkillLevel(skill, newLevel, newMasterlevel, SkillFactory.getDefaultSExpiry(skill));
        }

    }

    public void changeSingleSkillLevel(int skillid, int newLevel, int newMasterlevel, long expiration) {
        this.changeSingleSkillLevel(SkillFactory.getSkill(skillid), newLevel, newMasterlevel, expiration);
    }

    public void changeSingleSkillLevel(Skill skill, int newLevel, int newMasterlevel, long expiration) {
        Map<Integer, SkillEntry> list = new HashMap();
        boolean hasRecovery = false;
        boolean recalculate = false;
        if (this.changeSkillData(skill, newLevel, newMasterlevel, expiration)) {
            list.put(skill.getId(), new SkillEntry(newLevel, newMasterlevel, expiration, this.getSkillTeachId(skill), this.getSkillTeachTimes(skill), this.getSkillPosition(skill)));
            if (SkillConstants.isRecoveryIncSkill(skill.getId())) {
                hasRecovery = true;
            }

            if (skill.getId() < 80000000) {
                recalculate = true;
            }
        }

        if (!list.isEmpty()) {
            this.client.announce(MaplePacketCreator.updateSkills(list));
            this.reUpdateStat(hasRecovery, recalculate);
        }
    }

    public void maxSkillsByJob(int jobId) {
        List<Integer> skillIds = new ArrayList();
        HashMap<Integer, SkillEntry> sDate = new HashMap();
        Iterator var4 = SkillFactory.getAllSkills().keySet().iterator();

        while(var4.hasNext()) {
            Integer localSkillId = (Integer)var4.next();
            Skill skill = SkillFactory.getSkill(localSkillId);
            if (skill != null && skill.canBeLearnedBy(this.getJob()) && !JobConstants.is零轉職業(localSkillId / 10000) && !skill.isSpecialSkill() && !skill.isHyperSkill() && !skill.isInvisible()) {
                sDate.put(localSkillId, new SkillEntry((byte)skill.getMaxLevel(), (byte)skill.getMaxLevel(), SkillFactory.getDefaultSExpiry(skill)));
                skillIds.add(localSkillId);
            }
        }

        this.changeSkillsLevel(sDate);
        Collections.sort(skillIds);
        sDate.clear();
        skillIds.clear();
    }

    public void changeTeachSkillsLevel() {
        Iterator var1 = this.getSkills().entrySet().iterator();

        while(var1.hasNext()) {
            Map.Entry<Integer, SkillEntry> entry = (Map.Entry)var1.next();
            Skill skill = SkillFactory.getSkill((Integer)entry.getKey());
            if (skill != null && skill.isTeachSkills()) {
                this.changeSkillData(skill, SkillConstants.getLinkSkillslevel(skill, 0, this.level), (byte)skill.getMasterLevel(), ((SkillEntry)entry.getValue()).expiration);
            }
        }

    }

    public void changeSkillsLevel(Map<Integer, SkillEntry> skills) {
        if (!skills.isEmpty()) {
            Map<Integer, SkillEntry> list = new HashMap();
            boolean hasRecovery = false;
            boolean recalculate = false;
            Iterator var5 = skills.entrySet().iterator();

            while(var5.hasNext()) {
                Map.Entry<Integer, SkillEntry> data = (Map.Entry)var5.next();
                Skill skill = SkillFactory.getSkill((Integer)data.getKey());
                if (this.changeSkillData(skill, ((SkillEntry)data.getValue()).skillevel, ((SkillEntry)data.getValue()).masterlevel, ((SkillEntry)data.getValue()).expiration)) {
                    list.put((Integer)data.getKey(), (SkillEntry)data.getValue());
                    if (SkillConstants.isRecoveryIncSkill((Integer)data.getKey())) {
                        hasRecovery = true;
                    }

                    if ((Integer)data.getKey() < 80000000) {
                        recalculate = true;
                    }
                }
            }

            if (!list.isEmpty()) {
                this.client.announce(MaplePacketCreator.updateSkills(list));
                this.reUpdateStat(hasRecovery, recalculate);
            }
        }
    }

    private void reUpdateStat(boolean hasRecovery, boolean recalculate) {
        this.changed_skills = true;
        if (hasRecovery) {
            this.stats.relocHeal(this);
        }

        if (recalculate) {
            this.stats.recalcLocalStats(this);
        }

    }

    public boolean changeSkillData(Skill skill, int newLevel, int newMasterlevel, long expiration) {
        if (skill != null && (SkillConstants.isApplicableSkill(skill.getId()) || SkillConstants.isApplicableSkill_(skill.getId()))) {
            return this.changeSkillRecord(skill, newLevel, newMasterlevel, expiration, this.getSkillTeachId(skill), this.getSkillTeachTimes(skill), this.getSkillPosition(skill)) != null;
        } else {
            return false;
        }
    }

    public SkillEntry changeSkillRecord(Skill skill, int newLevel, int masterLevel, long expiration, int teachId, int teachTimes, byte pos) {
        SkillEntry skillEntry = null;
        int oldLevel;
        if (newLevel <= 0 && masterLevel <= 0) {
            if (this.skills.containsKey(skill.getId())) {
                skillEntry = (SkillEntry)this.skills.remove(skill.getId());
                oldLevel = skillEntry.skillevel;
                skillEntry.skillevel = newLevel;
                skillEntry.masterlevel = masterLevel;
                skillEntry.expiration = expiration;
                skillEntry.teachId = teachId;
                skillEntry.teachTimes = teachTimes;
                skillEntry.position = pos;
            } else {
                oldLevel = 0;
            }
        } else if (this.skills.containsKey(skill.getId())) {
            skillEntry = (SkillEntry)this.skills.get(skill.getId());
            oldLevel = skillEntry.skillevel;
            skillEntry.skillevel = newLevel;
            skillEntry.masterlevel = masterLevel;
            skillEntry.expiration = expiration;
            skillEntry.teachId = teachId;
            skillEntry.teachTimes = teachTimes;
            skillEntry.position = pos;
        } else {
            oldLevel = 0;
            skillEntry = new SkillEntry(newLevel, masterLevel, expiration, teachId, teachTimes, pos);
            this.skills.put(skill.getId(), skillEntry);
        }

        if (skill.getId() == 400041074) {
            if (newLevel > 0) {
                if (oldLevel <= 0) {
                    MapleStatEffect effect = this.getSkillEffect(skill.getId());
                    if (effect != null) {
                        effect.applyTo(this);
                    }
                }
            } else {
                this.dispelBuff(skill.getId());
            }
        }

        this.changed_skills = true;
        return skillEntry;
    }

    public void changeSkillLevel_Skip(Map<Integer, SkillEntry> skill) {
        this.changeSkillLevel_Skip(skill, false);
    }

    public void changeSkillLevel_Skip(Map<Integer, SkillEntry> skill, boolean write) {
        if (!skill.isEmpty()) {
            Map<Integer, SkillEntry> newlist = new HashMap();
            Iterator var4 = skill.entrySet().iterator();

            while(true) {
                while(true) {
                    Map.Entry date;
                    do {
                        if (!var4.hasNext()) {
                            if (write && !newlist.isEmpty()) {
                                this.client.announce(MaplePacketCreator.updateSkills(newlist));
                            }

                            return;
                        }

                        date = (Map.Entry)var4.next();
                    } while(date.getKey() == null);

                    newlist.put((Integer)date.getKey(), (SkillEntry)date.getValue());
                    if (((SkillEntry)date.getValue()).skillevel == 0 && ((SkillEntry)date.getValue()).masterlevel == 0) {
                        this.skills.remove(date.getKey());
                    } else {
                        this.skills.put((Integer)date.getKey(), (SkillEntry)date.getValue());
                    }
                }
            }
        }
    }

    public void changePetSkillLevel(Map<Integer, SkillEntry> skill) {
        if (!skill.isEmpty()) {
            Map<Integer, SkillEntry> newlist = new HashMap();
            Iterator var3 = skill.entrySet().iterator();

            while(true) {
                while(true) {
                    Map.Entry date;
                    do {
                        if (!var3.hasNext()) {
                            if (!newlist.isEmpty()) {
                                var3 = newlist.entrySet().iterator();

                                while(var3.hasNext()) {
                                    date = (Map.Entry)var3.next();
                                    this.client.announce(MaplePacketCreator.updatePetSkill((Integer)date.getKey(), ((SkillEntry)date.getValue()).skillevel, ((SkillEntry)date.getValue()).masterlevel, ((SkillEntry)date.getValue()).expiration));
                                }

                                this.reUpdateStat(false, true);
                            }

                            return;
                        }

                        date = (Map.Entry)var3.next();
                    } while(date.getKey() == null);

                    if (((SkillEntry)date.getValue()).skillevel == 0 && ((SkillEntry)date.getValue()).masterlevel == 0) {
                        if (this.skills.containsKey(date.getKey())) {
                            this.skills.remove(date.getKey());
                            newlist.put((Integer)date.getKey(), (SkillEntry)date.getValue());
                        }
                    } else if (this.getSkillLevel((Integer)date.getKey()) != ((SkillEntry)date.getValue()).skillevel) {
                        this.skills.put((Integer)date.getKey(), (SkillEntry)date.getValue());
                        newlist.put((Integer)date.getKey(), (SkillEntry)date.getValue());
                    }
                }
            }
        }
    }

    public void changeTeachSkill(int skillid, int teachid, int skillevel, boolean delete) {
        Skill skill = SkillFactory.getSkill(skillid);
        if (skill != null) {
            if (!delete) {
                long timeNow = System.currentTimeMillis();
                int tSkillId = SkillConstants.getTeamTeachSkillId(skillid);
                if (tSkillId > 1) {
                    Skill tSkill = SkillFactory.getSkill(tSkillId);
                    SkillEntry se = (SkillEntry)this.skills.get(tSkillId);
                    if (tSkill != null && se == null) {
                        this.skills.put(tSkillId, new SkillEntry(skillevel, (byte)tSkill.getMasterLevel(), timeNow, this.id, 0));
                    } else {
                        se.skillevel += skillevel;
                    }
                }

                this.skills.put(skillid, new SkillEntry(skillevel, (byte)skill.getMasterLevel(), timeNow, teachid, ((SkillEntry)((Pair)this.sonOfLinkedSkills.get(skillid)).getRight()).teachTimes));
                this.linkSkills.put(skillid, new SkillEntry(skillevel, (byte)skill.getMasterLevel(), timeNow, teachid, ((SkillEntry)((Pair)this.sonOfLinkedSkills.get(skillid)).getRight()).teachTimes));
            } else {
                HashMap<Integer, Integer> hashMap = new HashMap();
                hashMap.put(skillid, teachid);
                this.skills.remove(skillid);
                this.linkSkills.remove(skillid);
                int[] tSkills = SkillConstants.getTeamTeachSkills(skillid);
                if (tSkills != null) {
                    int[] var16 = tSkills;
                    int var17 = tSkills.length;

                    for(int var14 = 0; var14 < var17; ++var14) {
                        int id = var16[var14];
                        Skill linkSkill = SkillFactory.getSkill(id);
                        SkillEntry se = (SkillEntry)this.linkSkills.remove(id);
                        this.skills.remove(id);
                        if (linkSkill != null && se != null) {
                            hashMap.put(id, se.teachId);
                        }
                    }
                }

                this.send(MaplePacketCreator.DeleteLinkSkillResult(hashMap));
            }

            this.changed_skills = true;
        }
    }

    public void changeTeachSkill(int skillId, int toChrId) {
        SkillEntry ret = this.getSkillEntry(skillId);
        if (ret != null) {
            ret.teachId = toChrId;
            this.client.announce(MaplePacketCreator.updateSkill(skillId, toChrId, ret.masterlevel, ret.expiration));
            this.changed_skills = true;
        }

    }

    public void playerDead() {
        SecondaryStat[] resistDeadStats = new SecondaryStat[]{SecondaryStat.HeavensDoor, SecondaryStat.PreReviveOnce, SecondaryStat.ReviveOnce, SecondaryStat.FlareTrick};
        SecondaryStat[] var2 = resistDeadStats;
        int var3 = resistDeadStats.length;
        int var4 = 0;

        MapleStatEffect effect;
        boolean isPassiveEffect;
        int recoveryHPR;
        label93:
        while(true) {
            if (var4 >= var3) {
                if (JobConstants.is黑騎士(this.job) && this.getBuffStatValueHolder(SecondaryStat.ReincarnationOnOff) != null && this.getTotalSkillLevel(1320016) > 0 && !this.isSkillCooling(1320019)) {
                    this.getStat().setHp(this.getStat().getCurrentMaxHP());
                    this.getStat().setMp(this.getStat().getCurrentMaxMP());
                    this.updateHPMP(false);
                    this.getSkillEffect(1320019).applyTo(this, true);
                    return;
                }

                MapleStatEffect skillEffect;
                if ((skillEffect = this.getSkillEffect(80010040)) != null && !this.isSkillCooling(80010040)) {
                    this.registerSkillCooldown(skillEffect, true);
                    this.heal();
                    skillEffect.applyBuffEffect(this, this, 2000, false, false, true, this.getPosition());
                    return;
                }

                if (!this.inEvent()) {
                    DeadDebuff.setDebuff(this);
                }

                if (this.android != null) {
                    this.android.showEmotion(this, "dead");
                }

                if (!this.stats.checkEquipDurabilitys(this, -100)) {
                    this.dropMessage(5, "An item has run out of durability but has no inventory room to go to.");
                }

                this.dispelEffect(SecondaryStat.ShadowPartner);
                this.dispelEffect(SecondaryStat.Morph);
                this.dispelEffect(SecondaryStat.Flying);
                this.dispelEffect(SecondaryStat.RideVehicle);
                this.dispelEffect(SecondaryStat.Mechanic);
                this.dispelEffect(SecondaryStat.Regen);
                this.dispelEffect(SecondaryStat.IndieMHP);
                this.dispelEffect(SecondaryStat.IndieMMP);
                this.dispelEffect(SecondaryStat.EMHP);
                this.dispelEffect(SecondaryStat.EMMP);
                this.dispelEffect(SecondaryStat.MaxHP);
                this.dispelEffect(SecondaryStat.MaxMP);
                this.dispelEffect(SecondaryStat.SpiritLink);
                this.dispelEffect(SecondaryStat.StopForceAtomInfo);
                this.dispelEffect(SecondaryStat.NewFlying);
                this.dispelEffect(SecondaryStat.Frenzy);
                this.dispelSummons();
                this.checkFollow();
                this.specialStats.resetSpecialStats();
                if (this.pyramidSubway != null) {
                    this.stats.setHp(50, this);
                    this.pyramidSubway.fail(this);
                }

                this.playerDeadResponse();
                if (this.getEventInstance() != null) {
                    this.getEventInstance().getHooks().playerDied(this);
                }

                return;
            }

            SecondaryStat stat = var2[var4];
            effect = this.getEffectForBuffStat(stat);
            if (effect != null) {
                isPassiveEffect = false;
                switch (effect.getSourceId()) {
                    case 14111030:
                    case 20050286:
                    case 25111209:
                    case 80000169:
                        isPassiveEffect = true;
                }

                if (!isPassiveEffect || !this.isSkillCooling(effect.getSourceId())) {
                    recoveryHPR = effect.getX() <= 0 ? 100 : effect.getX();
                    boolean coolTime = false;
                    switch (stat) {
                        case FlareTrick:
                            recoveryHPR = effect.getY();
                            break label93;
                        case ReviveOnce:
                            recoveryHPR = 100;
                            break label93;
                        case PreReviveOnce:
                            if (!effect.makeChanceResult(this)) {
                                if (this.isDebug()) {
                                    this.dropMessage(10, "觸發死裡逃生BUFF失敗，概率" + effect.getProp() + "%。");
                                }
                                break;
                            }
                        default:
                            break label93;
                    }
                }
            }

            ++var4;
        }

        this.stats.setHp(Math.min(this.stats.getCurrentMaxHP() * recoveryHPR / 100, this.stats.getCurrentMaxHP()));
        this.dispelEffect(effect.getSourceId());
        this.updateHPMP(false);
        if (effect.getSourceId() != 400001015) {
            effect.unprimaryPassiveApplyTo(this);
        }

        if (isPassiveEffect) {
            this.registerSkillCooldown(effect, true);
        }

        if (effect.getSourceId() == 14111030) {
            effect.applyBuffEffect(this, this, effect.getDuration(), false, false, true, this.getPosition());
        }

    }

    public void playerDeadResponse() {
        int type = 1;
        int value = UIReviveType.UIReviveType_Normal.getType();
        if (this.android == null || this.android.getItemId() != 1662072 && this.android.getItemId() != 1662073) {
            if (this.getItemQuantity(5133000) > 0 || this.getItemQuantity(5133001) > 0) {
                type |= 2;
            }

            if (this.inEvent()) {
                if (this.getDeathCount() > 0) {
                    value = UIReviveType.UIReviveType_MagnusNormalHard.getType();
                } else {
                    value = UIReviveType.UIReviveType_OnUIDeathCountInfo.getType();
                    type &= -3;
                }
            } else if (this.haveItem(5420008)) {
                value = UIReviveType.UIReviveType_PremiumUser.getType();
            } else if (this.getEffectForBuffStat(SecondaryStat.SoulStone) != null) {
                value = UIReviveType.UIReviveType_SoulStone.getType();
            } else if (this.getBossLog("原地復活") < ServerConfig.CHANNEL_PLAYER_RESUFREECOUNT && this.getLevel() >= 70) {
                value = UIReviveType.UIReviveType_PremiumUser2.getType();
            } else if (this.getItemQuantity(5510000) > 0) {
                value = UIReviveType.UIReviveType_UpgradeTombItem.getType();
            } else if (this.getItemQuantity(5511001) > 0) {
                value = UIReviveType.UIReviveType_Nemesis.getType();
            } else if (ServerConfig.partyQuestRevive && this.getPQPoint() >= 10L) {
                value = UIReviveType.UIReviveType_UsingPartyPoint.getType();
            }
        } else {
            this.send(EffectPacket.ProtectBuffGain(this.android.getItemId(), 0));
            value = UIReviveType.UIReviveType_CombatAndroid.getType();
        }

        int autoReviveTime = 0;
        int reviveDelay = 0;
        boolean reviveEnd = false;
        if (this.inEvent() && this.getDeathCount() >= 0) {
            if (this.getDeathCount() > 0) {
                autoReviveTime = 30;
                reviveDelay = 5;
            } else if (this.eventInstance != null && this.getDeathCount() == 0) {
                autoReviveTime = 30;
                reviveEnd = true;
            }
        }

        this.client.announce(EffectPacket.playerDeadConfirm(type, false, value, autoReviveTime, reviveDelay, reviveEnd));
    }

    public void updatePartyMemberHP() {
        if (this.party != null && this.client.getChannelServer() != null) {
            int channel = this.client.getChannel();
            Iterator var2 = this.party.getMembers().iterator();

            while(var2.hasNext()) {
                PartyMember pm = (PartyMember)var2.next();
                if (pm != null && pm.getFieldID() == this.getMapId() && pm.getChannel() == channel) {
                    MapleCharacter other = this.client.getChannelServer().getPlayerStorage().getCharacterByName(pm.getCharName());
                    if (other != null) {
                        other.getClient().write(UserRemote.receiveHP(this));
                    }
                }
            }
        }

    }

    public void receivePartyMemberHP() {
        if (this.party != null) {
            int channel = this.client.getChannel();
            Iterator var2 = this.party.getMembers().iterator();

            while(var2.hasNext()) {
                PartyMember pm = (PartyMember)var2.next();
                if (pm != null && pm.getFieldID() == this.getMapId() && pm.getChannel() == channel) {
                    MapleCharacter other = this.client.getChannelServer().getPlayerStorage().getCharacterByName(pm.getCharName());
                    if (other != null) {
                        this.client.write(UserRemote.receiveHP(other));
                    }
                }
            }

        }
    }

    public void heal() {
        this.stats.heal(this);
    }

    public void healHP(int delta) {
        this.addHP((long)delta);
        if (delta != 0) {
            this.client.announce(EffectPacket.showOwnHpHealed(delta));
            this.getMap().broadcastMessage(this, EffectPacket.showHpHealed(this.getId(), delta), false);
        }

    }

    public void healMP(int delta) {
        this.addMP((long)delta);
        if (delta != 0) {
            this.client.announce(EffectPacket.showOwnHpHealed(delta));
            this.getMap().broadcastMessage(this, EffectPacket.showHpHealed(this.getId(), delta), false);
        }

    }

    public void healHPMP(int deltahp, int deltamp) {
        this.addHPMP(deltahp, deltamp, false, false);
        if (deltahp != 0 && deltamp != 0) {
            this.send(EffectPacket.showOwnHpHealed(deltahp != 0 ? deltahp : deltamp));
            this.getMap().broadcastMessage(this, EffectPacket.showHpHealed_Other(this.getId(), deltahp != 0 ? deltahp : deltamp), false);
        }

    }

    public void addHP(long delta) {
        if (this.stats.setHp((int)((long)this.stats.getHp() + delta), this)) {
            this.updateSingleStat(MapleStat.HP, (long)this.stats.getHp());
        }

    }

    public void addMP(long delta) {
        this.addMP((int)delta, false);
    }

    public void addMP(int delta, boolean ignore) {
        if (!JobConstants.isNotMpJob(this.getJob()) || GameConstants.getMPByJob(this.getJob()) > 0) {
            if ((delta < 0 && JobConstants.is惡魔殺手(this.getJob()) || !JobConstants.is惡魔殺手(this.getJob()) || ignore) && this.stats.setMp(this.stats.getMp() + delta)) {
                this.updateSingleStat(MapleStat.MP, (long)this.stats.getMp());
            }

        }
    }

    public void addDemonMp(int delta) {
        if (delta > 0 && (this.getJob() == 3111 || this.getJob() == 3112) && this.stats.setMp(this.stats.getMp() + delta)) {
            this.updateSingleStat(MapleStat.MP, (long)this.stats.getMp());
        }

    }

    public final void addHPMP(int hpRate, int mpRate) {
        this.addHPMP(this.stats.getCurrentMaxHP() * hpRate / 100, this.stats.getCurrentMaxMP() * mpRate / 100, false, true);
    }

    public final void addHPMP(int hpDiff, int mpDiff, boolean item) {
        this.addHPMP(hpDiff, mpDiff, item, false);
    }

    public void addHPMP(int hpDiff, int mpDiff, boolean item, boolean show) {
        this.addhpmpLock.lock();

        try {
            if (this.isAlive()) {
                if (this.stats.getHp() == this.stats.getCurrentMaxHP()) {
                    show = false;
                }

                SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(SecondaryStat.DawnShield_ExHP, 80011248);
                if (mbsvh != null && mbsvh.value > 0 && hpDiff < 0) {
                    show = false;
                    this.addShieldHP(hpDiff);
                } else {
                    this.stats.setHp(this.stats.getHp() + hpDiff);
                    int maxZ;
                    if ((mbsvh = this.getBuffStatValueHolder(SecondaryStat.RevenantGauge)) != null) {
                        if (this.stats.getHp() <= 0) {
                            this.stats.setHp(1);
                        }

                        if (hpDiff < 0) {
                            maxZ = this.stats.getCurrentMaxHP() * mbsvh.effect.getQ() / 100;
                            if (mbsvh.z < maxZ) {
                                mbsvh.z = Math.min(maxZ, mbsvh.z + Math.abs(hpDiff));
                                this.send(BuffPacket.giveBuff(this, mbsvh.effect, Collections.singletonMap(SecondaryStat.RevenantGauge, mbsvh.effect.getSourceId())));
                            }
                        }
                    }

                    if ((mbsvh = this.getBuffStatValueHolder(SecondaryStat.CrossOverChain)) != null && hpDiff < 0) {
                        maxZ = this.stats.getCurrentMaxHP() * mbsvh.effect.getQ() / 100;
                        if (this.stats.getHp() < maxZ) {
                            mbsvh.value = (int)Math.ceil((double)(this.stats.getHp() * mbsvh.effect.getX()) / (double)maxZ);
                        } else {
                            mbsvh.value = mbsvh.effect.getX();
                        }

                        mbsvh.z = (this.stats.getCurrentMaxHP() - this.stats.getHp()) * mbsvh.effect.getY() / 100;
                        this.send(BuffPacket.giveBuff(this, mbsvh.effect, Collections.singletonMap(SecondaryStat.CrossOverChain, mbsvh.effect.getSourceId())));
                    }
                }

                this.stats.setMp(this.stats.getMp() + mpDiff);
                this.updateHPMP(item);
                this.updatePartyMemberHP();
                if (show && this.client != null && this.map != null && hpDiff != 0) {
                    this.client.announce(EffectPacket.showIncDecHPRegen(-1, hpDiff));
                    this.map.broadcastMessage(this, EffectPacket.showIncDecHPRegen(this.id, hpDiff), this.getPosition());
                }

                if (!this.isAlive()) {
                    this.playerDead();
                }
            }
        } finally {
            this.addhpmpLock.unlock();
        }

    }

    public void updateHPMP(boolean itemReaction) {
        Map<MapleStat, Long> statups = new EnumMap(MapleStat.class);
        statups.put(MapleStat.HP, (long)this.stats.getHp());
        statups.put(MapleStat.MP, (long)this.stats.getMp());
        this.client.announce(MaplePacketCreator.updatePlayerStats(statups, itemReaction, this));
    }

    public void addShieldHP(int hpDiff) {
        if (this.isAlive()) {
            SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(SecondaryStat.DawnShield_ExHP, 80011248);
            MapleStatEffect effect = this.getSkillEffect(80011248);
            if (mbsvh != null && mbsvh.effect != null && mbsvh.value > 0 && effect != null) {
                int shield = mbsvh.value + hpDiff;
                if (hpDiff > 0 && shield < 0 && (mbsvh.value > 0 || Math.abs(mbsvh.value) < hpDiff)) {
                    shield = Integer.MAX_VALUE;
                }

                if (shield > 0) {
                    mbsvh.value = Math.min(shield, this.getStat().getCurrentMaxHP());
                    this.send(BuffPacket.giveBuff(this, effect, Collections.singletonMap(SecondaryStat.DawnShield_ExHP, effect.getSourceId())));
                } else {
                    this.dispelEffect(effect.getSourceId());
                }
            }
        }

    }

    public void updateSingleStat(MapleStat stat, long newval) {
        this.updateSingleStat(stat, newval, true);
    }

    public void updateSingleStat(MapleStat stat, long newval, boolean itemReaction) {
        this.client.announce(MaplePacketCreator.updatePlayerStats(Collections.singletonMap(stat, newval), itemReaction, this));
    }

    public void gainFieldExp(long total, boolean bOnQuest) {
        this.gainExp(total, true, bOnQuest, true);
        this.send(EffectPacket.showFieldExpItemConsumed((int)total));
    }

    public void gainExp(long total, boolean show, boolean bOnQuest, boolean white) {
        if (ServerConfig.WORLD_BANGAINEXP) {
            this.dropMessage(6, "管理員禁止了經驗獲取。");
        } else if (this.eventInstance == null || !this.eventInstance.isPractice()) {
            long needed = this.getExpNeededForLevel();
            if (this.level >= ServerConfig.CHANNEL_PLAYER_MAXLEVEL) {
                this.setExp(0L);
            } else {
                this.exp.addAndGet(total);
                if (this.exp.get() >= needed && this.getLevel() < ServerConfig.CHANNEL_PLAYER_MAXLEVEL) {
                    this.levelUp(true);
                    needed = this.getExpNeededForLevel();
                }

                if (this.level >= ServerConfig.CHANNEL_PLAYER_MAXLEVEL) {
                    this.setExp(0L);
                } else if (this.exp.get() >= needed) {
                    this.setExp(needed - 1L);
                }
            }

            if (total != 0L && this.exp.get() < 0L) {
                if (total > 0L) {
                    this.exp.set(needed - 1L);
                } else {
                    this.exp.set(0L);
                }
            }

            this.updateSingleStat(MapleStat.EXP, this.getExp());
            if (show && this.client != null) {
                this.client.announce(MaplePacketCreator.showGainExp(total, white, bOnQuest, 0, 0L, Collections.emptyMap()));
            }

        }
    }

    public void monsterMultiKill() {
        int multiKill = Math.min(10, this.getCheatTracker().getMultiKill());
        if (multiKill > 2 && this.killMonsterExp.get() != 0L) {
            float rate;
            switch (multiKill) {
                case 3:
                    rate = 0.01F;
                    break;
                case 4:
                    rate = 0.02F;
                    break;
                case 5:
                    rate = 0.03F;
                    break;
                case 6:
                    rate = 0.033F;
                    break;
                case 7:
                    rate = 0.036F;
                    break;
                case 8:
                    rate = 0.039F;
                    break;
                case 9:
                    rate = 0.042F;
                    break;
                default:
                    if (multiKill >= 10) {
                        rate = 0.045F;
                    } else {
                        rate = 0.0F;
                    }
            }

            long multiKillExp = (long)((float)this.killMonsterExp.get() * rate);
            if (this.getRuneUseCooldown() <= 0) {
                int curseRate = 100 - this.map.getRuneCurseRate();
                multiKillExp = (long)((float)multiKillExp * ((float)curseRate / 100.0F));
            }

            this.gainExp(multiKillExp, false, false, false);
            MessageOption option = new MessageOption();
            option.setMode(0);
            option.setObjectId(this.getCheatTracker().getLastKillMobOid());
            option.setLongExp(multiKillExp);
            option.setCombo(multiKill);
            int color = 0;
            switch (World.getHoliday()) {
                case ChineseNewYear -> color = 6;
                case Halloween -> color = 1;
            }

            option.setColor(color);
            this.getClient().announce(CWvsContext.sendMessage(35, option));
        }

        this.killMonsterExp.set(0L);
        if (multiKill != 0 && !this.stopComboKill) {
            int combo = this.getCheatTracker().gainMonsterCombo();
            if (combo > 1) {
                MessageOption option = new MessageOption();
                option.setMode(1);
                option.setCombo(combo);
                option.setObjectId(this.getCheatTracker().getLastKillMobOid());
                int color = 0;
                switch (World.getHoliday()) {
                    case ChineseNewYear -> color = 6;
                    case Halloween -> color = 1;
                }

                option.setColor(color);
                this.getClient().announce(CWvsContext.sendMessage(35, option));
            }

        }
    }

    public void dropComboKillBall(Point pos) {
        if (pos != null) {
            int combo = this.getCheatTracker().getMonsterCombo() + 1;
            boolean candy = false;
            int comboKillDrop;
            if (combo < 300) {
                comboKillDrop = candy ? 2023650 : 2023484;
            } else if (combo < 700) {
                comboKillDrop = candy ? 2023651 : 2023494;
            } else if (combo < 2000) {
                comboKillDrop = candy ? 2023652 : 2023495;
            } else {
                comboKillDrop = 2023669;
            }

            comboKillDrop = combo % 50 == 0 ? comboKillDrop : 0;
            if (comboKillDrop > 0 && this.getMap() != null) {
                Item dropItem = new Item(comboKillDrop, (short)0, (short)1);
                MapleMapItem mdrop = new MapleMapItem(dropItem, pos, this, this, (byte)0, true);
                mdrop.setCollisionPickUp(true);
                mdrop.setOnlySelfID(this.getId());
                mdrop.setEnterType((byte)1);
                mdrop.setDelay(0);
                this.getMap().spawnMobDrop(mdrop, (MapleMonster)null, this);
                mdrop.setEnterType((byte)2);
            }

        }
    }

    public void forceReAddItem_NoUpdate(Item item, MapleInventoryType type) {
        this.getInventory(type).removeSlot(item.getPosition());
        this.getInventory(type).addFromDB(item);
    }

    public void forceReAddItem(Item item) {
        this.forceReAddItem(item, ItemConstants.getInventoryType(item.getItemId(), false) == MapleInventoryType.EQUIP && item.getPosition() < 0 ? MapleInventoryType.EQUIPPED : ItemConstants.getInventoryType(item.getItemId()));
    }

    public void forceReAddItem(Item item, MapleInventoryType type) {
        this.forceReAddItem_NoUpdate(item, type);
        if (type != MapleInventoryType.UNDEFINED) {
            this.client.announce(InventoryPacket.modifyInventory(false, Collections.singletonList(new ModifyInventory(0, item))));
        }

    }

    public void forceUpdateItem(Item item) {
        this.forceUpdateItem(item, false);
    }

    public void petUpdateStats(MaplePet pet, boolean active) {
        List<ModifyInventory> mods = new LinkedList();
        Item Pet = this.getInventory(MapleInventoryType.CASH).getItem(pet.getInventoryPosition());
        if (Pet == null) {
            Logger var10000 = log;
            short var10001 = pet.getInventoryPosition();
            var10000.error("PetItem is null! inventorypos:" + var10001 + "chr:" + this.getName());
        } else {
            mods.add(new ModifyInventory(3, Pet));
            mods.add(new ModifyInventory(0, Pet));
            this.client.announce(InventoryPacket.modifyInventory(false, mods, this, active));
        }
    }

    public void forceUpdateItem(Item item, boolean updateTick) {
        List<ModifyInventory> mods = new LinkedList();
        mods.add(new ModifyInventory(3, item));
        mods.add(new ModifyInventory(0, item));
        this.client.announce(InventoryPacket.modifyInventory(updateTick, mods, this));
    }

    public boolean isIntern() {
        return this.client.isIntern();
    }

    public boolean isGm() {
        return this.client.isGm();
    }

    public boolean isSuperGm() {
        return this.client.isSuperGm();
    }

    public boolean isAdmin() {
        return this.client.isAdmin();
    }

    public int getGmLevel() {
        return this.client.getGmLevel();
    }

    public void setGmLevel(int level) {
        if (this.getGmLevel() != level) {
            this.client.setGmLevel(level);
            this.client.updateGmLevel();
        }
    }

    public boolean hasGmLevel(int level) {
        return this.client.getGmLevel() >= level;
    }

    public boolean isDebug() {
        return this.isAdmin() && Config.isDevelop();
    }

    public MapleInventory getInventory(MapleInventoryType type) {
        return this.inventory[type.ordinal()];
    }

    public MapleInventory getInventory(byte type) {
        return this.inventory[MapleInventoryType.getByType(type).ordinal()];
    }

    public MapleInventory[] getInventorys() {
        return this.inventory;
    }

    public boolean canExpiration(long now) {
        boolean b = this.lastExpirationTime > 0L && this.lastExpirationTime + 60000L < now;
        return b;
    }

    public void expirationTask(boolean logout) {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        MapleQuestStatus stat = this.getQuestNoAdd(MapleQuest.getInstance(122700));
        List<Integer> ret = new ArrayList();
        long currenttimes = System.currentTimeMillis();
        List<Triple<MapleInventoryType, Item, Boolean>> tobeRemoveItem = new ArrayList();
        List<Item> tobeUnlockItem = new ArrayList();
        MapleShopFactory factory = MapleShopFactory.getInstance();
        this.rLCheck.lock();

        MapleInventoryType inv;
        try {
            MapleInventoryType[] var12 = MapleInventoryType.values();
            int var13 = var12.length;

            label447:
            for(int var14 = 0; var14 < var13; ++var14) {
                inv = var12[var14];
                MapleInventory inventory = this.getInventory(inv);
                Iterator var17 = inventory.iterator();

                while(true) {
                    while(true) {
                        while(true) {
                            Item item;
                            do {
                                if (!var17.hasNext()) {
                                    continue label447;
                                }

                                item = (Item)var17.next();
                            } while(ServerConfig.CHEAT_ITEM_EXCLUDES_LIST.contains(item.getItemId()));

                            long expiration = item.getExpiration();
                            if (expiration != -1L && !類型.寵物(item.getItemId()) && currenttimes > expiration || ii.isLogoutExpire(item.getItemId()) && logout) {
                                if (ItemAttribute.Seal.check(item.getAttribute())) {
                                    tobeUnlockItem.add(item);
                                } else if (!ii.isNickSkillTimeLimited(item.getItemId()) && currenttimes > expiration) {
                                    if (item instanceof Equip && ((Equip)item).isMvpEquip()) {
                                        item.setExpiration(-1L);
                                        ((Equip)item).setEnchantBuff((short)(((Equip)item).getEnchantBuff() | EnhanceResultType.EQUIP_MARK.getValue()));
                                        this.forceUpdateItem(item);
                                    } else {
                                        tobeRemoveItem.add(new Triple(inv, item, true));
                                    }
                                }
                            } else if (類型.寵物(item.getItemId())) {
                                if (ii.getLimitedLife(item.getItemId()) > 0) {
                                    if (item.getPet() != null && item.getPet().getSecondsLeft() <= 0) {
                                        tobeRemoveItem.add(new Triple(inv, item, true));
                                    }
                                } else if (expiration >= 0L && currenttimes > expiration && item.getPet() != null) {
                                    tobeRemoveItem.add(new Triple(inv, item, false));
                                }
                            } else if (item.getPosition() == -36 && (stat == null || stat.getCustomData() == null || !"0".equals(stat.getCustomData()) && Long.parseLong(stat.getCustomData()) < currenttimes)) {
                                tobeRemoveItem.add(new Triple(inv, item, false));
                            }
                        }
                    }
                }
            }
        } finally {
            this.rLCheck.unlock();
        }

        Iterator var22 = tobeRemoveItem.iterator();

        Item itemFix;
        short slot;
        while(var22.hasNext()) {
            Triple<MapleInventoryType, Item, Boolean> itemz = (Triple)var22.next();
            itemFix = (Item)itemz.getMid();
            if (itemFix == null) {
                log.error("道具到期 " + this.getName() + " 檢測道具已經過期，但道具為空，無法繼續執行。");
            } else if ((Boolean)itemz.getRight()) {
                if (MapleInventoryManipulator.removeFromSlot(this.client, (MapleInventoryType)itemz.getLeft(), itemFix.getPosition(), itemFix.getQuantity(), false)) {
                    ret.add(itemFix.getItemId());
                }

                if (itemz.getLeft() == MapleInventoryType.EQUIPPED) {
                    this.equipChanged();
                }
            } else if (類型.寵物(itemFix.getItemId())) {
                this.unequipSpawnPet(itemFix.getPet(), true, (byte)2);
            } else if (itemFix.getPosition() == -36) {
                slot = this.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
                if (slot > -1) {
                    MapleInventoryManipulator.unequip(this.client, itemFix.getPosition(), slot);
                }
            }
        }

        var22 = tobeUnlockItem.iterator();

        while(var22.hasNext()) {
            Item itemz = (Item)var22.next();
            itemz.setExpiration(-1L);
            itemz.removeAttribute(ItemAttribute.Seal.getValue());
            this.forceUpdateItem(itemz);
        }

        this.pendingExpiration = ret;
        List<Integer> tobeRemoveSkill = new ArrayList();
        Map<Integer, SkillEntry> tobeRemoveList = new HashMap();
        Iterator var28 = this.skills.entrySet().iterator();

        while(var28.hasNext()) {
            Map.Entry<Integer, SkillEntry> skil = (Map.Entry)var28.next();
            if (((SkillEntry)skil.getValue()).expiration != -1L && currenttimes > ((SkillEntry)skil.getValue()).expiration && SkillConstants.getTeamTeachSkills((Integer)skil.getKey()) == null && !SkillConstants.isLinkSkills((Integer)skil.getKey()) && !SkillConstants.isTeachSkills((Integer)skil.getKey())) {
                tobeRemoveSkill.add((Integer)skil.getKey());
            }
        }

        for(var28 = tobeRemoveSkill.iterator(); var28.hasNext(); this.changed_skills = true) {
            Integer skil = (Integer)var28.next();
            tobeRemoveList.put(skil, new SkillEntry(0, 0, -1L));
            this.skills.remove(skil);
        }

        this.pendingSkills = tobeRemoveList;
        if (stat != null && stat.getCustomData() != null && !"0".equals(stat.getCustomData()) && Long.parseLong(stat.getCustomData()) < currenttimes) {
            this.quests.remove(7830);
            this.quests.remove(122700);
        }

        itemFix = this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-37);
        if (itemFix != null && itemFix.getItemId() / 10000 != 119) {
            slot = this.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
            if (slot > -1) {
                MapleInventoryManipulator.unequip(this.client, itemFix.getPosition(), slot);
                String var10002 = ii.getName(itemFix.getItemId());
                this.dropMessage(5, "裝備道具[" + var10002 + "]由於裝備的位置錯誤已自動取下。");
            }
        }

        List<Item> equipedItems = new LinkedList(this.getInventory(MapleInventoryType.EQUIPPED).getInventory().values());
        Iterator var32 = equipedItems.iterator();

        while(var32.hasNext()) {
            Item equiped = (Item)var32.next();
            if (equiped instanceof Equip && EnhanceResultType.EQUIP_MARK.check(((Equip)equiped).getEnchantBuff())) {
                slot = this.getInventory(MapleInventoryType.EQUIP).getNextFreeSlot();
                if (slot > -1) {
                    MapleInventoryManipulator.unequip(this.client, equiped.getPosition(), slot);
                    this.forceUpdateItem(equiped);
                }
            }
        }

        equipedItems.clear();
        inv = null;
        if (this.pendingExpiration != null && !this.pendingExpiration.isEmpty()) {
            var32 = this.pendingExpiration.iterator();

            while(var32.hasNext()) {
                Integer itemId = (Integer)var32.next();
                if (ii.isCash(itemId)) {
                    this.client.announce(MaplePacketCreator.showCashItemExpired(itemId));
                } else {
                    this.client.announce(MaplePacketCreator.showItemExpired(itemId));
                }
            }
        }

        this.pendingExpiration = null;
        if (this.pendingSkills != null) {
            if (!this.pendingSkills.isEmpty()) {
                this.client.announce(MaplePacketCreator.updateSkills(this.pendingSkills));
                this.client.announce(MaplePacketCreator.showSkillExpired(this.pendingSkills));
            }

            this.pendingSkills = null;
        }

        this.updateReward();
        this.lastExpirationTime = System.currentTimeMillis();
    }

    public MapleShop getShop() {
        return this.shop;
    }

    public void setShop(MapleShop shop) {
        this.shop = shop;
    }

    public boolean isScriptShop() {
        return this.scriptShop;
    }

    public void setScriptShop(boolean scriptShop) {
        this.scriptShop = scriptShop;
    }

    public int[] getSavedLocations() {
        return this.savedLocations;
    }

    public int getSavedLocation(SavedLocationType type) {
        return type.getValue() >= 0 && type.getValue() < this.savedLocations.length ? this.savedLocations[type.getValue()] : 0;
    }

    public void saveLocation(SavedLocationType type) {
        if (type.getValue() >= 0 && type.getValue() < this.savedLocations.length) {
            this.savedLocations[type.getValue()] = this.getMapId();
            this.changed_savedlocations = true;
        }
    }

    public void saveLocation(SavedLocationType type, int mapz) {
        if (type.getValue() >= 0 && type.getValue() < this.savedLocations.length) {
            this.savedLocations[type.getValue()] = mapz;
            this.changed_savedlocations = true;
        }
    }

    public void clearSavedLocation(SavedLocationType type) {
        if (type.getValue() >= 0 && type.getValue() < this.savedLocations.length) {
            this.savedLocations[type.getValue()] = -1;
            this.changed_savedlocations = true;
        }
    }

    public int getCMapCount() {
        return this.cMapCount.getAndIncrement();
    }

    public long getMeso() {
        return this.meso.get();
    }

    public void gainMeso(long gain, boolean show) {
        this.gainMeso(gain, show, false);
    }

    public void gainMeso(long gain, boolean show, boolean inChat) {
        this.gainMeso(gain, show, inChat, true);
    }

    public void gainMeso(long gain, boolean show, boolean inChat, boolean enableAction) {
        if (this.meso.get() + gain < 0L) {
            this.client.sendEnableActions();
        } else {
            if (this.meso.get() + gain > ServerConfig.CHANNEL_PLAYER_MAXMESO) {
                gain = ServerConfig.CHANNEL_PLAYER_MAXMESO - this.meso.get();
            }

            this.meso.addAndGet(gain);
            this.updateSingleStat(MapleStat.MONEY, this.meso.get(), false);
            if (enableAction) {
                this.client.sendEnableActions();
            }

            if (show) {
                this.client.announce(MaplePacketCreator.showMesoGain(gain, inChat));
            }

            this.playerObservable.update();
        }
    }

    public int getAccountID() {
        return this.accountid;
    }

    public int getMobControlledSize() {
        return this.controlledMonsters.size();
    }

    public final boolean isControlMonster(MapleMonster monster) {
        this.controlMonsterLock.lock();

        boolean var2;
        try {
            var2 = this.controlledMonsters.contains(monster);
        } finally {
            this.controlMonsterLock.unlock();
        }

        return var2;
    }

    public void controlMonster(MapleMonster monster) {
        this.controlMonsterLock.lock();

        try {
            this.controlledMonsters.add(monster);
        } finally {
            this.controlMonsterLock.unlock();
        }

    }

    public void controlMonsterRemove(MapleMonster monster) {
        this.controlMonsterLock.lock();

        try {
            this.controlledMonsters.remove(monster);
        } finally {
            this.controlMonsterLock.unlock();
        }

    }

    public void checkMonsterAggro(MapleMonster monster) {
        if (monster != null) {
            if (monster.getController() == this) {
                monster.setControllerHasAggro(true);
            } else {
                monster.switchController(this);
            }

        }
    }

    public List<MapleQuestStatus> getStartedQuests() {
        List<MapleQuestStatus> ret = new LinkedList();
        Iterator var2 = this.quests.values().iterator();

        while(var2.hasNext()) {
            MapleQuestStatus q = (MapleQuestStatus)var2.next();
            if (q.getStatus() == 1 && !q.isCustom() && !q.getQuest().isBlocked()) {
                ret.add(q);
            }
        }

        return ret;
    }

    public List<MapleQuestStatus> getCompletedQuests() {
        List<MapleQuestStatus> ret = new LinkedList();
        Iterator var2 = this.quests.values().iterator();

        while(var2.hasNext()) {
            MapleQuestStatus q = (MapleQuestStatus)var2.next();
            if (q.getStatus() == 2 && !q.isCustom() && !q.getQuest().isBlocked()) {
                ret.add(q);
            }
        }

        return ret;
    }

    public List<Pair<Integer, Long>> getCompletedMedals() {
        List<Pair<Integer, Long>> ret = new ArrayList();
        Iterator var2 = this.quests.values().iterator();

        while(var2.hasNext()) {
            MapleQuestStatus q = (MapleQuestStatus)var2.next();
            if (q.getStatus() == 2 && !q.isCustom() && !q.getQuest().isBlocked() && q.getQuest().getMedalItem() > 0 && ItemConstants.getInventoryType(q.getQuest().getMedalItem(), false) == MapleInventoryType.EQUIP) {
                ret.add(new Pair(q.getQuest().getId(), q.getCompletionTime()));
            }
        }

        return ret;
    }

    public void mobKilled(int id, int skillID) {
        Iterator var3 = this.quests.values().iterator();

        while(true) {
            MapleQuestStatus q;
            do {
                do {
                    do {
                        do {
                            if (!var3.hasNext()) {
                                return;
                            }

                            q = (MapleQuestStatus)var3.next();
                        } while(q.getStatus() != 1);
                    } while(!q.hasMobKills());
                } while(q.isWorldShare() && (this.id != q.getFromChrID() || q.getFromChrID() == -2));
            } while(!q.mobKilled(id, skillID));

            int i = -1;
            Iterator var6 = q.getMobKills().values().iterator();

            while(var6.hasNext()) {
                int kills = (Integer)var6.next();
                ++i;
                if (q.isWorldShare()) {
                    this.updateWorldShareInfo(q.getQuest().getId(), "m" + i, String.valueOf(kills));
                } else {
                    this.updateOneQuestInfo(q.getQuest().getId(), "m" + i, String.valueOf(kills));
                }
            }

            this.client.announce(MaplePacketCreator.updateQuestMobKills(q));
            if (q.getQuest().canComplete(this, (Integer)null)) {
            }
        }
    }

    public Map<Integer, SkillEntry> getSkills() {
        return Collections.unmodifiableMap(this.skills);
    }

    public Map<Integer, SkillEntry> getLinkSkills() {
        return Collections.unmodifiableMap(this.linkSkills);
    }

    public Map<Integer, Pair<Integer, SkillEntry>> getSonOfLinkedSkills() {
        return Collections.unmodifiableMap(this.sonOfLinkedSkills);
    }

    public Map<Integer, SkillEntry> getSkills(boolean packet) {
        if (!packet) {
            return Collections.unmodifiableMap(this.skills);
        } else {
            Map<Integer, SkillEntry> oldlist = new LinkedHashMap(this.skills);
            Map<Integer, SkillEntry> newlist = new LinkedHashMap();
            Iterator var4 = oldlist.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<Integer, SkillEntry> skill = (Map.Entry)var4.next();
                Skill skill1 = SkillFactory.getSkill((Integer)skill.getKey());
                if (skill1 != null && !skill1.isAngelSkill() && !skill1.isLinkedAttackSkill() && !skill1.isDefaultSkill()) {
                    newlist.put((Integer)skill.getKey(), (SkillEntry)skill.getValue());
                }
            }

            return newlist;
        }
    }

    public int getAllSkillLevels() {
        int rett = 0;
        Iterator var2 = this.skills.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Integer, SkillEntry> ret = (Map.Entry)var2.next();
            Skill skill = SkillFactory.getSkill((Integer)ret.getKey());
            if (!skill.isBeginnerSkill() && !skill.isSpecialSkill() && ((SkillEntry)ret.getValue()).skillevel > 0) {
                rett += ((SkillEntry)ret.getValue()).skillevel;
            }
        }

        return rett;
    }

    public long getSkillExpiry(Skill skill) {
        if (skill == null) {
            return 0L;
        } else {
            SkillEntry ret = (SkillEntry)this.skills.get(skill.getId());
            return ret != null && ret.skillevel > 0 ? ret.expiration : 0L;
        }
    }

    public int getSkillLevel(int skillid) {
        return this.getSkillLevel(SkillFactory.getSkill(skillid));
    }

    public boolean hasSkill(int skillid) {
        int skilllv = this.getSkillLevel(SkillFactory.getSkill(skillid));
        return skilllv > 0;
    }

    public int getSkillLevel(Skill skill) {
        if (skill == null) {
            return -1;
        } else if (!SkillConstants.isGeneralSkill(skill.getId()) && !SkillConstants.isExtraSkill(skill.getId()) && !skill.isSoulSkill() && !SkillConstants.isRuneSkill(skill.getId())) {
            int id = skill.getId();
            int skillLevel = 0;
            int root = SkillConstants.getSkillRoot(id);
            if (this.getJob() >= root && this.getJob() < root + 3) {
                Iterator var5 = skill.getRequiredSkills().iterator();

                while(var5.hasNext()) {
                    Pair<String, Byte> require = (Pair)var5.next();
                    switch ((String)require.getLeft()) {
                        case "level":
                            if (this.level < (Byte)require.getRight()) {
                                return 0;
                            }
                        case "reqAmount":
                            break;
                        default:
                            if (this.getSkillLevel(Integer.parseInt((String)require.getLeft())) < (Byte)require.getRight()) {
                                return 0;
                            }
                    }
                }
            }

            switch (id) {
                case 42111103:
                    return this.getSkillLevel(42111100) > 0 ? 1 : 0;
                case 80001089:
                case 80001242:
                case 80001770:
                case 80011133:
                    return 1;
                default:
                    int n2 = id / 10000;
                    int n3 = id % 10000;
                    if (id != 22171095 && !SkillConstants.ej(id) && JobConstants.isSameJob(n2, this.getJob())) {
                        switch (n3) {
                            case 1092:
                            case 1093:
                            case 1094:
                            case 1095:
                                return 1;
                        }
                    }

                    skillLevel += this.stats.getSkillIncrement(skill.getId());
                    skillLevel += this.stats.getEquipmentSkill(skill.getId());
                    int[] tSkills = SkillConstants.getTeamTeachSkills(id);
                    if (tSkills != null) {
                        int[] var15 = tSkills;
                        int var9 = tSkills.length;

                        for(int var10 = 0; var10 < var9; ++var10) {
                            int tID = var15[var10];
                            skillLevel += this.getSkillLevel(SkillConstants.getTeachSkillId(tID));
                        }
                    }

                    if (skillLevel <= 0 && skill.getFixLevel() > 0) {
                        skillLevel = skill.getFixLevel();
                    } else {
                        SkillEntry ret = (SkillEntry)this.skills.get(id);
                        if (ret == null || ret.skillevel <= 0) {
                            return skillLevel;
                        }

                        skillLevel += ret.skillevel;
                        if (id / 10000 >= 9200 && id / 10000 <= 9204 || skill.isRecipe()) {
                            return skillLevel;
                        }

                        if (id % 10000 < 1000 && this.stats.getPassivePlus() > 0) {
                            skillLevel += Math.min(2, this.stats.getPassivePlus());
                        }

                        skillLevel = Math.min(skill.getTrueMax(), skillLevel + (!JobConstants.notNeedSPSkill(SkillConstants.getSkillRoot(id)) && !skill.isVSkill() ? this.stats.combatOrders + (skill.getMaxLevel() > 10 && this.stats.incAllskill + skillLevel <= skill.getMaxLevel() ? this.stats.incAllskill : 0) : 0));
                        if (skillLevel > skill.getMaxLevel() && this.getSummonedFamiliar() == null && this.stats.combatOrders <= 0 && !skill.isVSkill()) {
                            skillLevel = skill.getMaxLevel();
                        }
                    }

                    return skillLevel;
            }
        } else {
            return 1;
        }
    }

    public int getTotalSkillLevel(int skillid) {
        return this.getTotalSkillLevel(SkillFactory.getSkill(skillid));
    }

    public int getTotalSkillLevel(Skill skill) {
        return this.getSkillLevel(skill);
    }

    public int getMasterLevel(int skillId) {
        return this.getMasterLevel(SkillFactory.getSkill(skillId));
    }

    public int getMasterLevel(Skill skill) {
        SkillEntry ret = (SkillEntry)this.skills.get(skill.getId());
        return ret == null ? 0 : ret.masterlevel;
    }

    public int getSkillTeachId(int skillId) {
        return this.getSkillTeachId(SkillFactory.getSkill(skillId));
    }

    public int getSkillTeachId(Skill skill) {
        if (skill == null) {
            return 0;
        } else {
            SkillEntry ret = (SkillEntry)this.skills.get(skill.getId());
            return ret != null && ret.teachId != 0 ? ret.teachId : 0;
        }
    }

    public int getSkillTeachTimes(Skill skill) {
        if (skill == null) {
            return 0;
        } else {
            SkillEntry ret = (SkillEntry)this.skills.get(skill.getId());
            return ret != null && ret.teachTimes != 0 ? ret.teachTimes : 0;
        }
    }

    public byte getSkillPosition(int skillId) {
        return this.getSkillPosition(SkillFactory.getSkill(skillId));
    }

    public byte getSkillPosition(Skill skill) {
        if (skill == null) {
            return -1;
        } else {
            SkillEntry ret = (SkillEntry)this.skills.get(skill.getId());
            return ret != null && ret.position != -1 ? ret.position : -1;
        }
    }

    public SkillEntry getSkillEntry(int skillId) {
        return (SkillEntry)this.skills.get(skillId);
    }

    public void levelUp() {
        this.levelUp(false);
    }

    public void levelUp(boolean canBurning) {
        int job = this.job;
        if (!JobConstants.is管理員(job)) {
            this.remainingAp = (short)(this.remainingAp + 5);
        }

        int maxhp = this.stats.getMaxHp(false);
        int maxmp = this.stats.getMaxMp(false);
        if (JobConstants.is零轉職業(job)) {
            maxhp += Randomizer.rand(12, 16);
            maxmp += Randomizer.rand(10, 12);
        } else if (!JobConstants.is惡魔殺手(job) && !JobConstants.is凱內西斯(job)) {
            if (!JobConstants.is惡魔復仇者(job) && !JobConstants.is陰陽師(job)) {
                if ((job < 100 || job > 132) && (job < 1100 || job > 1112) && (job < 5100 || job > 5112) && !JobConstants.is皮卡啾(job)) {
                    if (job >= 200 && job <= 232 || job >= 1200 && job <= 1212 || job >= 2700 && job <= 2712) {
                        maxhp += Randomizer.rand(10, 14);
                        maxmp += Randomizer.rand(48, 52);
                    } else if (job >= 3200 && job <= 3212) {
                        maxhp += Randomizer.rand(20, 24);
                        maxmp += Randomizer.rand(42, 44);
                    } else if (job >= 300 && job <= 322 || job >= 400 && job <= 434 || job >= 1300 && job <= 1312 || job >= 1400 && job <= 1412 || job >= 2300 && job <= 2312 || job >= 2400 && job <= 2412 || job >= 3300 && job <= 3312 || job >= 3600 && job <= 3612) {
                        maxhp += Randomizer.rand(20, 24);
                        maxmp += Randomizer.rand(14, 16);
                    } else if (job >= 510 && job <= 512 || job >= 580 && job <= 582 || job >= 1510 && job <= 1512 || job >= 6500 && job <= 6512) {
                        maxhp += Randomizer.rand(37, 41);
                        maxmp += Randomizer.rand(18, 22);
                    } else if ((job < 500 || job > 532) && (job < 590 || job > 592) && (job < 3500 || job > 3512) && job != 1500) {
                        if (job >= 2100 && job <= 2112) {
                            maxhp += Randomizer.rand(50, 52);
                            maxmp += Randomizer.rand(4, 6);
                        } else if (JobConstants.is龍魔導士(job)) {
                            maxhp += Randomizer.rand(12, 16);
                            maxmp += Randomizer.rand(50, 52);
                        } else if (job >= 6100 && job <= 6112) {
                            maxhp += Randomizer.rand(68, 74);
                            maxmp += Randomizer.rand(4, 6);
                        } else if (job >= 10100 && job <= 10112) {
                            maxhp += Randomizer.rand(48, 52);
                        } else if (!JobConstants.is隱月(job) && !JobConstants.is劍豪(job)) {
                            if (JobConstants.is爆拳槍神(job)) {
                                maxhp += Randomizer.rand(48, 75);
                                maxmp += Randomizer.rand(8, 18);
                            } else if (JobConstants.is伊利恩(job)) {
                                maxhp += Randomizer.rand(20, 34);
                                maxmp += Randomizer.rand(18, 22);
                            } else if (JobConstants.is卡蒂娜(job)) {
                                maxhp += Randomizer.rand(30, 44);
                                maxmp += Randomizer.rand(8, 18);
                            } else if (JobConstants.is亞克(job)) {
                                maxhp += Randomizer.rand(30, 44);
                                maxmp += Randomizer.rand(8, 18);
                            } else if (JobConstants.is虎影(job)) {
                                maxhp += Randomizer.rand(30, 44);
                                maxmp += Randomizer.rand(8, 18);
                            } else if (JobConstants.is卡莉(job)) {
                                maxhp += Randomizer.rand(30, 44);
                                maxmp += Randomizer.rand(8, 18);
                            } else if (JobConstants.is琳恩(job)) {
                                maxhp += Randomizer.rand(30, 44);
                                maxmp += Randomizer.rand(8, 18);
                            } else {
                                maxhp += Randomizer.rand(24, 38);
                                maxmp += Randomizer.rand(12, 24);
                                if (job != 800 && job != 900 && job != 910) {
                                    System.err.println("出現尚未建立加血數據的角色,職業代號是: " + job);
                                }
                            }
                        } else {
                            maxhp += Randomizer.rand(38, 42);
                            maxmp += Randomizer.rand(20, 24);
                        }
                    } else {
                        maxhp += Randomizer.rand(22, 26);
                        maxmp += Randomizer.rand(18, 22);
                    }
                } else {
                    maxhp += Randomizer.rand(48, 52);
                    maxmp += Randomizer.rand(4, 6);
                }
            } else {
                maxhp += Randomizer.rand(30, 40);
            }
        } else {
            maxhp += Randomizer.rand(48, 52);
        }

        maxmp += JobConstants.isNotMpJob(this.getJob()) ? 0 : this.stats.getTotalInt() / 10;
        if (JobConstants.is夜光(job) && this.getSkillLevel(20040221) > 0) {
            maxmp += 10;
        }

        maxhp = Math.min(ServerConfig.CHANNEL_PLAYER_MAXHP, Math.abs(maxhp));
        maxmp = Math.min(ServerConfig.CHANNEL_PLAYER_MAXMP, Math.abs(maxmp));
        if (JobConstants.is惡魔殺手(job)) {
            maxmp = GameConstants.getMPByJob(job);
        } else if (JobConstants.is神之子(job)) {
            maxmp = 100;
            this.checkZeroWeapon();
        } else if (JobConstants.is陰陽師(job)) {
            maxmp = 100;
        } else if (JobConstants.isNotMpJob(job)) {
            maxmp = 10;
        }

        this.exp.addAndGet(-this.getExpNeededForLevel());
        if (this.exp.get() < 0L) {
            this.exp.set(0L);
        }

        ++this.level;
        if (this.level >= ServerConfig.CHANNEL_PLAYER_MAXLEVEL) {
            this.setExp(0L);
        }

        if (this.level >= 140 && this.getQuestInfo(2498, "hyperstats") == null) {
            this.updateInfoQuest(2498, "hyperstats=0", true);
            this.client.announce(MaplePacketCreator.updateHyperPresets(this, 0, (byte)0));
        }

        Map<MapleStat, Long> statup = new EnumMap(MapleStat.class);
        if (!JobConstants.is皮卡啾(job) && !JobConstants.is雪吉拉(job) && !JobConstants.is管理員(job)) {
            if (!JobConstants.is零轉職業(job)) {
                int spNum = getJobLvSP(this.subcategory == 1 ? 430 : job, this.level);
                int[] var10000;
                if (JobConstants.is神之子(job)) {
                    var10000 = this.remainingSp;
                    var10000[0] += spNum;
                    var10000 = this.remainingSp;
                    var10000[1] += spNum;
                } else if (this.level > 10) {
                    var10000 = this.remainingSp;
                    int var10001 = JobConstants.getSkillBookByLevel(this.subcategory == 1 ? 430 : job, this.level);
                    var10000[var10001] += spNum;
                }
            }

            if (this.level <= 10) {
                PlayerStats var13 = this.stats;
                var13.str += this.remainingAp;
                this.remainingAp = 0;
                statup.put(MapleStat.STR, (long)this.stats.getStr());
            } else if (this.level == 11 && JobConstants.is零轉職業(job)) {
                this.resetStats(4, 4, 4, 4);
                this.setKeyValue("Rest_AP", "True");
            }
        }

        this.stats.setInfo(maxhp, maxmp, this.stats.getCurrentMaxHP(), this.stats.getCurrentMaxMP());
        this.characterCard.recalcLocalStats(this);
        this.stats.recalcLocalStats(this);
        statup.put(MapleStat.MAX_HP, (long)maxhp);
        statup.put(MapleStat.MAX_MP, (long)maxmp);
        statup.put(MapleStat.HP, (long)this.stats.getCurrentMaxHP());
        statup.put(MapleStat.MP, (long)this.stats.getCurrentMaxMP());
        statup.put(MapleStat.EXP, this.exp.get());
        statup.put(MapleStat.LEVEL, (long)this.level);
        statup.put(MapleStat.AVAILABLE_AP, (long)this.remainingAp);
        statup.put(MapleStat.AVAILABLE_SP, (long)this.remainingSp[JobConstants.getSkillBookByLevel(job, this.level)]);
        this.client.announce(MaplePacketCreator.updatePlayerStats(statup, this));
        if (this.map != null) {
            this.map.broadcastMessage(this, EffectPacket.showForeignEffect(this.getId(), EffectOpcode.UserEffect_LevelUp), false);
        }

        this.notifyChanges();
        this.guildUpdate();
        this.baseSkills();
        this.changeTeachSkillsLevel();
        this.insertRanking("等級排行", this.level);
        this.playerObservable.update();
        if (this.level == 60 && this.getWorldShareInfo(18793, "q0") == null) {
            this.updateWorldShareInfo(18793, "q0", "0");
        }

        if (canBurning) {
            int minLv = 10;
            short maxLv;
            switch (this.burningChrType) {
                case 0:
                default:
                    maxLv = 0;
                    break;
                case 1:
                    maxLv = 150;
                    break;
                case 2:
                    maxLv = 130;
                    break;
                case 3:
                    maxLv = 200;
            }

            if (this.level > minLv && this.level < maxLv) {
                long timeNow = System.currentTimeMillis();
                if (timeNow >= this.burningChrTime) {
                    this.burningChrType = 0;
                    this.burningChrTime = -2L;
                } else {
                    int nLvUPTimes = this.burningChrType == 3 ? Math.min(2, maxLv - this.level) : 2;

                    for(int i = 0; i < nLvUPTimes; ++i) {
                        this.levelUp(false);
                    }
                }
            }
        } else if (this.android != null) {
            this.android.showEmotion(this, "levelup");
        }

    }

    public boolean canLevelUp() {
        boolean canLevelUp = true;
        String textinfo = "";
        switch (this.getJob()) {
            case 0:
            case 1000:
            case 2000:
            case 2001:
            case 2002:
            case 2003:
            case 2004:
            case 2005:
            case 3000:
            case 3001:
            case 3002:
            case 4001:
            case 4002:
            case 5000:
            case 6000:
            case 6001:
            case 14000:
                if (this.getLevel() >= 10) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第一次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 100:
            case 200:
            case 300:
            case 500:
            case 501:
            case 1100:
            case 1200:
            case 1300:
            case 1400:
            case 1500:
            case 2100:
            case 2200:
            case 2300:
            case 2400:
            case 2500:
            case 2700:
            case 3100:
            case 3101:
            case 3200:
            case 3300:
            case 3500:
            case 3600:
            case 3700:
            case 4100:
            case 4200:
            case 5100:
            case 6100:
            case 6500:
            case 14200:
                if (this.getLevel() >= 30) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第二次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 110:
            case 120:
            case 130:
            case 210:
            case 220:
            case 230:
            case 310:
            case 320:
            case 410:
            case 420:
            case 510:
            case 520:
            case 530:
            case 570:
            case 1110:
            case 1210:
            case 1310:
            case 1410:
            case 1510:
            case 2110:
            case 2211:
            case 2310:
            case 2410:
            case 2510:
            case 2710:
            case 3110:
            case 3120:
            case 3210:
            case 3310:
            case 3510:
            case 3610:
            case 3710:
            case 4110:
            case 4210:
            case 5110:
            case 6110:
            case 6510:
            case 14210:
                if (this.getLevel() >= 60) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第三次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 111:
            case 121:
            case 131:
            case 211:
            case 221:
            case 231:
            case 311:
            case 321:
            case 411:
            case 421:
            case 511:
            case 521:
            case 531:
            case 571:
            case 1111:
            case 1211:
            case 1311:
            case 1411:
            case 1511:
            case 2111:
            case 2214:
            case 2311:
            case 2411:
            case 2511:
            case 2711:
            case 3111:
            case 3121:
            case 3211:
            case 3311:
            case 3511:
            case 3611:
            case 3711:
            case 4111:
            case 4211:
            case 5111:
            case 6111:
            case 6511:
            case 14211:
                if (this.getLevel() >= 100) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第四次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 400:
                if (this.getSubcategory() == 1) {
                    if (this.getLevel() >= 20) {
                        canLevelUp = false;
                        textinfo = "您現在可以進行第二次轉職了，在右下角點擊拍賣開始轉職吧。";
                    }
                } else if (this.getLevel() >= 30) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第二次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 430:
                if (this.getLevel() >= 30) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第三次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 431:
                if (this.getLevel() >= 45) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第四次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 432:
                if (this.getLevel() >= 60) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第五次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
                break;
            case 433:
                if (this.getLevel() >= 100) {
                    canLevelUp = false;
                    textinfo = "您現在可以進行第六次轉職了，在右下角點擊拍賣開始轉職吧。";
                }
        }

        if (!canLevelUp) {
            this.dropMessage(5, "[轉職提示] " + textinfo);
            return false;
        } else {
            return true;
        }
    }

    public void changeKeybinding(int slot, int key, byte type, int action) {
        if (this.funcKeyMaps.size() > slot) {
            if (type != 0) {
                ((FuncKeyMap)this.funcKeyMaps.get(slot)).getKeymaps().put(key, new Keymapping(type, action));
            } else {
                ((FuncKeyMap)this.funcKeyMaps.get(slot)).getKeymaps().remove(key);
            }

            ((FuncKeyMap)this.funcKeyMaps.get(slot)).setChanged(true);
        }
    }

    public void updateMacros(int position, SkillMacro updateMacro) {
        this.skillMacros[position] = updateMacro;
        this.changed_skillmacros = true;
    }

    public SkillMacro[] getMacros() {
        return this.skillMacros;
    }

    public void tempban(String reason, Calendar duration, int greason, boolean IPMac) {
        if (IPMac) {
            this.client.banMacs();
        }

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps;
                if (IPMac) {
                    ps = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
                    ps.setString(1, this.client.getSessionIPAddress());
                    ps.execute();
                    ps.close();
                }

                this.client.disconnect(true, false);
                this.client.getSession().close();
                ps = con.prepareStatement("UPDATE accounts SET tempban = ?, banreason = ?, greason = ? WHERE id = ?");
                Timestamp TS = new Timestamp(duration.getTimeInMillis());
                ps.setTimestamp(1, TS);
                ps.setString(2, reason);
                ps.setInt(3, greason);
                ps.setInt(4, this.accountid);
                ps.execute();
                ps.close();
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var10) {
            SQLException ex = var10;
            System.err.println("Error while tempbanning" + String.valueOf(ex));
        }

    }

    public boolean ban(String reason, boolean IPMac, boolean autoban, boolean hellban) {
        this.gainWarning(false);

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE accounts SET banned = ?, banreason = ? WHERE id = ?");
                ps.setInt(1, autoban ? 2 : 1);
                ps.setString(2, reason);
                ps.setInt(3, this.accountid);
                ps.execute();
                ps.close();
                if (IPMac) {
                    this.client.banMacs();
                    ps = con.prepareStatement("INSERT INTO ipbans VALUES (DEFAULT, ?)");
                    ps.setString(1, this.client.getSessionIPAddress());
                    ps.execute();
                    ps.close();
                    if (hellban) {
                        PreparedStatement psa = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
                        psa.setInt(1, this.accountid);
                        ResultSet rsa = psa.executeQuery();
                        if (rsa.next()) {
                            PreparedStatement pss = con.prepareStatement("UPDATE accounts SET banned = ?, banreason = ? WHERE email = ? OR SessionIP = ?");
                            pss.setInt(1, autoban ? 2 : 1);
                            pss.setString(2, reason);
                            pss.setString(3, rsa.getString("email"));
                            pss.setString(4, this.client.getSessionIPAddress());
                            pss.execute();
                            pss.close();
                        }

                        rsa.close();
                        psa.close();
                    }
                }
            } catch (Throwable var11) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var10) {
                        var11.addSuppressed(var10);
                    }
                }

                throw var11;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var12) {
            SQLException ex = var12;
            System.err.println("Error while banning" + String.valueOf(ex));
            return false;
        }

        this.client.getSession().close();
        return true;
    }

    public int getObjectId() {
        return this.getId();
    }

    public void setObjectId(int id) {
        throw new UnsupportedOperationException();
    }

    public MapleTrunk getTrunk() {
        return this.trunk;
    }

    public void addVisibleMapObjectEx(MapleMapObject mo) {
        this.visibleMapObjectsLock.writeLock().lock();

        try {
            this.visibleMapObjects.add(mo);
        } finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }

    }

    public void addVisibleMapObject(MapleMapObject mo) {
        this.visibleMapObjectsLock.writeLock().lock();

        try {
            this.visibleMapObjects.add(mo);
            if (this.client != null) {
                mo.sendSpawnData(this.client);
            }
        } finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }

    }

    public void removeVisibleMapObjectEx(MapleMapObject mo) {
        this.visibleMapObjectsLock.writeLock().lock();

        try {
            this.visibleMapObjects.remove(mo);
        } finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }

    }

    public void removeVisibleMapObject(MapleMapObject mo) {
        this.visibleMapObjectsLock.writeLock().lock();

        try {
            this.visibleMapObjects.remove(mo);
            if (mo.getType() == MapleMapObjectType.MONSTER) {
                ((MapleMonster)mo).removeController(this);
            }

            if (this.client != null) {
                mo.sendDestroyData(this.client);
            }
        } finally {
            this.visibleMapObjectsLock.writeLock().unlock();
        }

    }

    public boolean isMapObjectVisible(MapleMapObject mo) {
        this.visibleMapObjectsLock.readLock().lock();

        boolean var2;
        try {
            var2 = this.visibleMapObjects.contains(mo);
        } finally {
            this.visibleMapObjectsLock.readLock().unlock();
        }

        return var2;
    }

    public boolean isAlive() {
        return this.stats.getHp() > 0;
    }

    public void sendDestroyData(MapleClient client) {
        client.announce(MaplePacketCreator.removePlayerFromMap(this.getObjectId()));
    }

    public void sendSpawnData(MapleClient client) {
        if (client != null && client.getSession() != null && client.getPlayer() != null && client.getPlayer().allowedToTarget(this)) {
            client.announce(MaplePacketCreator.spawnPlayerMapobject(this));
            client.announce(MaplePacketCreator.SpecialChairSitResult(this.getId(), false, false, (SpecialChair)null));
            client.announce(EffectPacket.getEffectSwitch(this.getId(), this.getEffectSwitch()));
            if (this.getMap() != null && this.getGuild() != null && this.getGuild().getImageLogo() != null && this.getGuild().getImageLogo().length > 0) {
                this.getMap().broadcastMessage(this, GuildPacket.loadGuildIcon(this), false);
            }

            if (this.getParty() != null) {
                this.updatePartyMemberHP();
                this.receivePartyMemberHP();
            }

            if (this.dragon != null) {
                client.announce(SummonPacket.spawnDragon(this.dragon));
            }

            if (this.android != null) {
                client.announce(AndroidPacket.spawnAndroid(this, this.android));
            }

            if (this.followid > 0 && this.followon) {
                client.announce(MaplePacketCreator.followEffect(this.followinitiator ? this.followid : this.id, this.followinitiator ? this.id : this.followid, (Point)null));
            }

            if (this.getSoulSkillID() > 0) {
                client.announce(MaplePacketCreator.updateSoulEffect(this.id, "1".equals(this.getQuestInfo(26535, "effect"))));
            }

            if (this.getBuffedValue(SecondaryStat.GuidedArrow) != null) {
                client.announce(ForcePacket.forceAtomCreate(this.getSpecialStat().getGuidedArrow()));
            }
        }

    }

    public void equipChanged() {
        if (this.map != null) {
            this.map.broadcastMessage(this, MaplePacketCreator.updateCharLook(this), false);
            this.stats.recalcLocalStats(this);
            if (this.getMessenger() != null) {
                WorldMessengerService.getInstance().updateMessenger(this.getMessenger().getId(), this.getName(), this.client.getChannel());
            }

        }
    }

    public void checkCopyItems() {
        List<Integer> sns = new ArrayList();
        Map<Integer, Integer> checkItems = new HashMap();
        Iterator var3 = this.getInventory(MapleInventoryType.EQUIP).list().iterator();

        Item item;
        int sn;
        while(var3.hasNext()) {
            item = (Item)var3.next();
            sn = item.getSN();
            if (sn > 0) {
                if (checkItems.containsKey(sn)) {
                    if ((Integer)checkItems.get(sn) == item.getItemId()) {
                        sns.add(sn);
                    }
                } else {
                    checkItems.put(sn, item.getItemId());
                }
            }
        }

        var3 = this.getInventory(MapleInventoryType.DECORATION).list().iterator();

        while(var3.hasNext()) {
            item = (Item)var3.next();
            sn = item.getSN();
            if (sn > 0) {
                if (checkItems.containsKey(sn)) {
                    if ((Integer)checkItems.get(sn) == item.getItemId()) {
                        sns.add(sn);
                    }
                } else {
                    checkItems.put(sn, item.getItemId());
                }
            }
        }

        var3 = this.getInventory(MapleInventoryType.EQUIPPED).list().iterator();

        while(var3.hasNext()) {
            item = (Item)var3.next();
            sn = item.getSN();
            if (sn > 0) {
                if (checkItems.containsKey(sn)) {
                    if ((Integer)checkItems.get(sn) == item.getItemId()) {
                        sns.add(sn);
                    }
                } else {
                    checkItems.put(sn, item.getItemId());
                }
            }
        }

        boolean autoban = false;

        for(Iterator var7 = sns.iterator(); var7.hasNext(); autoban = true) {
            sn = (Integer)var7.next();
            MapleInventoryManipulator.removeAllBySN(this.client, sn);
        }

        if (autoban) {
            AutobanManager.getInstance().autoban(this.client, "偵測到複製裝備");
        }

        checkItems.clear();
        sns.clear();
    }

    public List<MaplePet> getPets() {
        List<MaplePet> ret = new ArrayList();
        Iterator var2 = this.getInventory(MapleInventoryType.CASH).newList().iterator();

        while(var2.hasNext()) {
            Item item = (Item)var2.next();
            if (item.getPet() != null) {
                ret.add(item.getPet());
            }
        }

        return ret;
    }

    public MaplePet[] getSpawnPets() {
        return this.spawnPets;
    }

    public MaplePet getSpawnPet(int index) {
        return this.spawnPets != null && index < this.spawnPets.length && index >= 0 ? this.spawnPets[index] : null;
    }

    public byte getPetIndex(int petId) {
        for(byte i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null && this.spawnPets[i].getUniqueId() == petId) {
                return i;
            }
        }

        return -1;
    }

    public byte getPetIndex(MaplePet pet) {
        for(byte i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null && this.spawnPets[i].getUniqueId() == pet.getUniqueId()) {
                return i;
            }
        }

        return -1;
    }

    public byte getPetByItemId(int petItemId) {
        for(byte i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null && this.spawnPets[i].getPetItemId() == petItemId) {
                return i;
            }
        }

        return -1;
    }

    public int getNextEmptyPetIndex() {
        for(int i = 0; i < 3; ++i) {
            if (this.spawnPets[i] == null) {
                return i;
            }
        }

        return 3;
    }

    public int getNoPets() {
        int ret = 0;

        for(int i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null) {
                ++ret;
            }
        }

        return ret;
    }

    public List<MaplePet> getSummonedPets() {
        List<MaplePet> ret = new ArrayList();

        for(byte i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null && this.spawnPets[i].getSummoned()) {
                ret.add(this.spawnPets[i]);
            }
        }

        return ret;
    }

    public void addSpawnPet(MaplePet pet) {
        for(int i = 0; i < 3; ++i) {
            if (this.spawnPets[i] == null) {
                this.spawnPets[i] = pet;
                pet.setSummoned((byte)(i + 1));
                return;
            }
        }

    }

    public void removeSpawnPet(MaplePet pet, boolean shiftLeft) {
        for(int i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null && this.spawnPets[i].getUniqueId() == pet.getUniqueId()) {
                this.spawnPets[i] = null;
                break;
            }
        }

    }

    public void unequipAllSpawnPets() {
        for(int i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null) {
                this.unequipSpawnPet(this.spawnPets[i], true, (byte)0);
            }
        }

    }

    public void spawnPet(short slot) {
        this.spawnPet(slot, false, true);
    }

    public void spawnPet(short slot, boolean lead) {
        this.spawnPet(slot, lead, true);
    }

    public void spawnPet(short slot, boolean lead, boolean broadcast) {
        Item item = this.getInventory(MapleInventoryType.CASH).getItem(slot);
        if (item != null && 類型.寵物(item.getItemId())) {
            MaplePet pet;
            switch (item.getItemId()) {
                case 5000028:
                case 5000047:
                    pet = MaplePet.createPet(item.getItemId() + 1, MapleInventoryIdentifier.getInstance());
                    if (pet != null) {
                        MapleClient var10000 = this.client;
                        int var10001 = item.getItemId() + 1;
                        String var10003 = item.getOwner();
                        int var10006 = item.getItemId();
                        MapleInventoryManipulator.addById(var10000, var10001, 1, var10003, pet, 90L, "雙擊寵物獲得: " + var10006 + " 時間: " + DateUtil.getCurrentDate());
                        MapleInventoryManipulator.removeFromSlot(this.client, MapleInventoryType.CASH, slot, (short)1, false);
                    }
                    break;
                default:
                    pet = item.getPet();
                    if (pet != null && pet.getSecondsLeft() > 0 || item.getExpiration() == -3L || item.getExpiration() == -1L || item.getExpiration() > System.currentTimeMillis()) {
                        if (this.getPetIndex(pet) != -1) {
                            this.unequipSpawnPet(pet, true, (byte)0);
                        } else {
                            if (this.getNoPets() == 3 && this.getSpawnPet(0) != null) {
                                this.unequipSpawnPet(this.getSpawnPet(0), false, (byte)0);
                            } else if (lead) {
                                this.shiftPetsRight();
                            }

                            Point pos = this.getPosition();
                            pos.y -= 12;
                            pet.setPos(pos);

                            try {
                                pet.setFh(this.getMap().getFootholds().findBelow(pet.getPos()).getId());
                            } catch (NullPointerException var11) {
                                pet.setFh(0);
                            }

                            pet.setStance(0);
                            MapleQuestStatus stat = this.getQuestNAdd(MapleQuest.getInstance(122902));
                            if (stat.getCustomData() != null && !stat.getCustomData().equals("1")) {
                                pet.setCanPickup(false);
                            } else {
                                pet.setCanPickup(true);
                                stat.setCustomData("1");
                            }

                            this.addSpawnPet(pet);

                            for(int i = 0; i < pet.getBuffSkills().length; ++i) {
                                String value = this.getOneInfo(101080 + pet.getSummonedValue() - 1, String.valueOf(10 * (pet.getSummonedValue() - 1) + i));
                                int skillId = value != null && value.matches("^\\d+$") ? Integer.valueOf(value) : 0;
                                if (skillId > 0 && this.getSkillLevel(skillId) <= 0) {
                                    skillId = 0;
                                    this.updateOneInfo(101080 + pet.getSummonedValue() - 1, String.valueOf(10 * (pet.getSummonedValue() - 1) + i), "0");
                                }

                                pet.setBuffSkill(i, skillId);
                            }

                            if (this.getMap() != null) {
                                this.petUpdateStats(pet, true);
                                this.getMap().broadcastMessage(this, PetPacket.showPet(this, pet, false, (byte)0), true);
                                this.getClient().announce(PetPacket.loadExceptionList(this, pet));
                                this.getClient().announce(PetPacket.petStatUpdate(this));
                                this.checkPetSkill();
                            }
                        }
                    }
            }

            this.client.sendEnableActions();
        } else {
            this.client.sendEnableActions();
        }
    }

    public void unequipSpawnPet(MaplePet pet, boolean shiftLeft, byte showType) {
        if (this.getPetIndex(pet) != -1 && this.getSpawnPet(this.getPetIndex(pet)) != null) {
            this.getSpawnPet(this.getPetIndex(pet)).setSummoned(0);
            this.getSpawnPet(this.getPetIndex(pet)).saveToDb();
        }

        this.petUpdateStats(pet, false);
        if (this.map != null) {
            this.map.broadcastMessage(this, PetPacket.showPet(this, pet, true, showType), true);
        }

        this.removeSpawnPet(pet, shiftLeft);
        this.checkPetSkill();
        this.client.sendEnableActions();
    }

    public void shiftPetsRight() {
        if (this.spawnPets[2] == null) {
            this.spawnPets[2] = this.spawnPets[1];
            this.spawnPets[1] = this.spawnPets[0];
            this.spawnPets[0] = null;
        }

    }

    public void checkPetSkill() {
        Map<Integer, Integer> setHandling = new HashMap();
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();

        for(int i = 0; i < 3; ++i) {
            if (this.spawnPets[i] != null) {
                int set = ii.getPetSetItemID(this.spawnPets[i].getPetItemId());
                if (set > 0) {
                    int value = 1;
                    if (setHandling.containsKey(set)) {
                        value += (Integer)setHandling.get(set);
                    }

                    setHandling.put(set, value);
                }
            }
        }

        HashMap petSkillData;
        if (setHandling.isEmpty()) {
            petSkillData = new HashMap(this.getSkills());
            Map<Integer, SkillEntry> petSkill = new HashMap();
            Iterator var15 = petSkillData.entrySet().iterator();

            while(var15.hasNext()) {
                Map.Entry<Integer, SkillEntry> skill = (Map.Entry)var15.next();
                Skill skill1 = SkillFactory.getSkill((Integer)skill.getKey());
                if (skill1 != null && skill1.isPetPassive()) {
                    petSkill.put((Integer)skill.getKey(), new SkillEntry(0, 0, -1L));
                }
            }

            if (!petSkill.isEmpty()) {
                this.changePetSkillLevel(petSkill);
            }

        } else {
            petSkillData = new HashMap();
            Iterator var12 = setHandling.entrySet().iterator();

            while(true) {
                StructSetItem setItem;
                Map.Entry entry;
                do {
                    if (!var12.hasNext()) {
                        if (!petSkillData.isEmpty()) {
                            this.changePetSkillLevel(petSkillData);
                        }

                        return;
                    }

                    entry = (Map.Entry)var12.next();
                    setItem = ii.getSetItem((Integer)entry.getKey());
                } while(setItem == null);

                Map<Integer, StructSetItemStat> setItemStats = setItem.getSetItemStats();
                Iterator var8 = setItemStats.entrySet().iterator();

                while(var8.hasNext()) {
                    Map.Entry<Integer, StructSetItemStat> ent = (Map.Entry)var8.next();
                    StructSetItemStat setItemStat = (StructSetItemStat)ent.getValue();
                    if ((Integer)ent.getKey() <= (Integer)entry.getValue()) {
                        if (setItemStat.skillId > 0 && setItemStat.skillLevel > 0 && this.getSkillLevel(setItemStat.skillId) <= 0) {
                            petSkillData.put(setItemStat.skillId, new SkillEntry((byte)setItemStat.skillLevel, 0, -1L));
                        }
                    } else if (setItemStat.skillId > 0 && setItemStat.skillLevel > 0 && this.getSkillLevel(setItemStat.skillId) > 0) {
                        petSkillData.put(setItemStat.skillId, new SkillEntry(0, 0, -1L));
                    }
                }
            }
        }
    }

    public void updateLastChangeMapTime() {
        this.lastChangeMapTime = System.currentTimeMillis();
    }

    public long getLastChangeMapTime() {
        return this.lastChangeMapTime;
    }

    public long getLastFameTime() {
        return this.lastfametime;
    }

    public List<Integer> getFamedCharacters() {
        return this.lastmonthfameids;
    }

    public List<Integer> getBattledCharacters() {
        return this.lastmonthbattleids;
    }

    public enum FameStatus {

        OK,
        NOT_TODAY,
        NOT_THIS_MONTH
    }

    public FameStatus canGiveFame(MapleCharacter from) {
        if (this.lastfametime >= System.currentTimeMillis() - 86400000L) {
            return Client.MapleCharacter.FameStatus.NOT_TODAY;
        } else {
            return from != null && this.lastmonthfameids != null && !this.lastmonthfameids.contains(from.getId()) ? Client.MapleCharacter.FameStatus.OK : Client.MapleCharacter.FameStatus.NOT_THIS_MONTH;
        }
    }

    public void hasGivenFame(MapleCharacter to) {
        this.lastfametime = System.currentTimeMillis();
        this.lastmonthfameids.add(to.getId());

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO famelog (characterid, characterid_to) VALUES (?, ?)");
                ps.setInt(1, this.getId());
                ps.setInt(2, to.getId());
                ps.execute();
                ps.close();
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException e = var7;
            PrintStream var10000 = System.err;
            String var10001 = this.getName();
            var10000.println("ERROR writing famelog for char " + var10001 + " to " + to.getName() + String.valueOf(e));
        }

    }

    public boolean canBattle(MapleCharacter to) {
        return to != null && this.lastmonthbattleids != null && !this.lastmonthbattleids.contains(to.getAccountID());
    }

    public void hasBattled(MapleCharacter to) {
        this.lastmonthbattleids.add(to.getAccountID());

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO battlelog (accid, accid_to) VALUES (?, ?)");
                ps.setInt(1, this.getAccountID());
                ps.setInt(2, to.getAccountID());
                ps.execute();
                ps.close();
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException e = var7;
            PrintStream var10000 = System.err;
            String var10001 = this.getName();
            var10000.println("ERROR writing battlelog for char " + var10001 + " to " + to.getName() + String.valueOf(e));
        }

    }

    public MapleQuickSlot getQuickSlot() {
        return this.quickslot;
    }

    public Party getParty() {
        return this.party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public byte getWorld() {
        return this.world;
    }

    public void setWorld(byte world) {
        this.world = world;
    }

    public MapleTrade getTrade() {
        return this.trade;
    }

    public void setTrade(MapleTrade trade) {
        this.trade = trade;
    }

    public boolean inEvent() {
        return this.eventInstance != null;
    }

    public ScriptEvent getEventInstance() {
        return this.eventInstance;
    }

    public void setEventInstance(ScriptEvent eventInstance) {
        this.eventInstance = eventInstance;
    }

    public boolean checkEvent() {
        return this.eventInstance != null && (this.map == null || this.map.getEvent() != this.eventInstance);
    }

    public void addTownPortal(TownPortal door) {
        this.townportals.add(door);
    }

    public void clearTownPortals() {
        this.townportals.clear();
    }

    public List<TownPortal> getTownPortals() {
        return new ArrayList(this.townportals);
    }

    public void addMechDoor(MechDoor door) {
        this.mechDoors.add(door);
    }

    public void clearMechDoors() {
        this.mechDoors.clear();
    }

    public List<MechDoor> getMechDoors() {
        return new ArrayList(this.mechDoors);
    }

    public void setSmega() {
        if (this.smega) {
            this.smega = false;
            this.dropMessage(5, "You have set megaphone to disabled mode");
        } else {
            this.smega = true;
            this.dropMessage(5, "You have set megaphone to enabled mode");
        }

    }

    public boolean getSmega() {
        return this.smega;
    }

    public PortableChair getChair() {
        return this.chair;
    }

    public void setChair(PortableChair chair) {
        this.chair = chair;
        this.stats.relocHeal(this);
    }

    public int getItemEffect() {
        return this.itemEffect;
    }

    public void setItemEffect(int itemEffect) {
        this.itemEffect = itemEffect;
    }

    public int getItemEffectType() {
        return this.itemEffectType;
    }

    public void setItemEffectType(int itemEffectType) {
        this.itemEffectType = itemEffectType;
    }

    public int getActiveNickItemID() {
        String questInfo;
        return (questInfo = this.getQuestInfo(19019, "id")) == null ? 0 : Integer.parseInt(questInfo);
    }

    public MapleMapObjectType getType() {
        return MapleMapObjectType.PLAYER;
    }

    public int getRange() {
        return GameConstants.maxViewRange();
    }

    public int getCurrentRep() {
        return this.currentrep;
    }

    public int getTotalRep() {
        return this.totalrep;
    }

    public int getTotalWins() {
        return this.totalWins;
    }

    public int getTotalLosses() {
        return this.totalLosses;
    }

    public void increaseTotalWins() {
        ++this.totalWins;
    }

    public void increaseTotalLosses() {
        ++this.totalLosses;
    }

    public int getGuildId() {
        return this.guildid;
    }

    public void setGuildId(int newGuildId) {
        this.guildid = newGuildId;
        if (this.guildid > 0) {
            if (this.mgc == null) {
                this.mgc = new MapleGuildCharacter(this);
            } else {
                this.mgc.setGuildId(this.guildid);
            }
        } else {
            this.mgc = null;
            this.guildContribution = 0;
        }

    }

    public byte getGuildRank() {
        return this.guildrank;
    }

    public void setGuildRank(byte newRank) {
        this.guildrank = newRank;
        if (this.mgc != null) {
            this.mgc.setGuildRank(newRank);
        }

    }

    public int getGuildContribution() {
        return this.guildContribution;
    }

    public void setGuildContribution(int newContribution) {
        this.guildContribution = newContribution;
        if (this.mgc != null) {
            this.mgc.setGuildContribution(newContribution);
        }

    }

    public MapleGuildCharacter getMGC() {
        return this.mgc;
    }

    public byte getAllianceRank() {
        return this.allianceRank;
    }

    public void setAllianceRank(byte newRank) {
        this.allianceRank = newRank;
        if (this.mgc != null) {
            this.mgc.setAllianceRank(newRank);
        }

    }

    public MapleGuild getGuild() {
        return this.getGuildId() <= 0 ? null : WorldGuildService.getInstance().getGuild(this.getGuildId());
    }

    public void guildUpdate() {
        if (this.guildid > 0) {
            this.mgc.setLevel(this.level);
            this.mgc.setJobId(this.job);
            WorldGuildService.getInstance().memberLevelJobUpdate(this.mgc);
        }
    }

    public void saveGuildStatus() {
        MapleGuild.setOfflineGuildStatus(this.guildid, this.guildrank, this.guildContribution, this.allianceRank, this.id);
    }

    public boolean isBronzeIMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP銅牌I.getLevel();
    }

    public boolean isBronzeIIMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP銅牌II.getLevel();
    }

    public boolean isBronzeIIIMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP銅牌III.getLevel();
    }

    public boolean isBronzeIVMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP銅牌IV.getLevel();
    }

    public boolean isSilverMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP銀牌.getLevel();
    }

    public boolean isGoldMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP金牌.getLevel();
    }

    public boolean isDiamondMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP鑽石.getLevel();
    }

    public boolean isRedMvp() {
        return this.getMvpLevel() >= PlayerRank.MVP紅鑽.getLevel();
    }

    public int getMvpLevel() {
        float rate = ServerConfig.MVP_AMOUNT_RATE;
        int amount = this.getMvpPayAmount();
        String nowMonth = DateUtil.getCurrentDate("yyyyMM");
        int level = 0;

        for(int i = 1; i <= 8; ++i) {
            char cost;
            switch (i) {
                case 1:
                    cost = 'd';
                    break;
                case 2:
                    cost = 1000;
                    break;
                case 3:
                    cost = 3000;
                    break;
                case 4:
                    cost = 5000;
                    break;
                case 5:
                    cost = 5000;
                    break;
                case 6:
                    cost = 15000;
                    break;
                case 7:
                    cost = 20000;
                    break;
                case 8:
                default:
                    cost = '鱀';
            }

            if (i >= 5) {
                amount = this.getMvpPayAmountMonthly();
            }

            if ((float)amount < (float)cost * rate) {
                break;
            }

            level = i;
            String sp;
            if (i < 8) {
                sp = this.getWorldShareInfo(6, "sp_" + i);
                if (sp == null) {
                    this.updateWorldShareInfo(6, "sp_" + i, nowMonth);
                }
            } else {
                sp = this.getOneInfo(100561, "rp_" + i);
                if (sp == null) {
                    this.updateOneQuestInfo(100561, "rp_" + i, nowMonth);
                }
            }
        }

        if (level >= 5) {
            String sp;
            if (level < 8) {
                sp = this.getWorldShareInfo(6, "sp_" + level);
                if (!sp.contains(nowMonth) && !sp.equalsIgnoreCase("R" + nowMonth)) {
                    this.updateWorldShareInfo(6, "sp_" + level, nowMonth);
                    this.updateWorldShareInfo(90, "1=1");
                }
            } else {
                sp = this.getWorldShareInfo(6, "sp_5");
                if (!sp.contains(nowMonth) && !sp.equalsIgnoreCase("R" + nowMonth)) {
                    this.updateWorldShareInfo(6, "sp_5", "R" + nowMonth);
                    this.updateWorldShareInfo(90, "1=1");
                }

                String rp = this.getOneInfo(100561, "rp_" + level);
                if (!rp.contains(nowMonth) && !rp.equalsIgnoreCase(nowMonth + "R")) {
                    this.updateOneQuestInfo(100561, "rp_" + level, nowMonth);
                }
            }
        }

        return level;
    }

    public int getMvpPayAmount() {
        float rate = ServerConfig.MVP_AMOUNT_RATE;
        boolean isCustom = rate != 1.0F;
        String sAmount = this.getWorldShareInfo(5, (isCustom ? "c" : "") + "amount");
        return Math.abs(sAmount == null ? 0 : Integer.parseInt(sAmount));
    }

    public int getMvpPayAmountMonthly() {
        float rate = ServerConfig.MVP_AMOUNT_RATE;
        boolean isCustom = rate != 1.0F;
        int amount = 0;

        for(int i = 0; i <= 2; ++i) {
            String month = DateUtil.getPreDate("M", -i).replaceAll("-", "").substring(0, 6);
            String sMonthAmount = this.getWorldShareInfo(4, (isCustom ? "c" : "") + month);
            amount += Math.abs(sMonthAmount == null ? 0 : Integer.parseInt(sMonthAmount));
        }

        return amount;
    }

    public void updateMvpLog(int quantity) {
        if (quantity < 0) {
            float rate = ServerConfig.MVP_AMOUNT_RATE;
            boolean isCustom = rate != 1.0F;
            quantity = Math.abs(quantity);
            String now = DateUtil.getCurrentDate("yyyyMMdd");
            String nowMonth = DateUtil.getCurrentDate("yyyyMM");
            String sNowMonthAmount = this.getWorldShareInfo(4, (isCustom ? "c" : "") + nowMonth);
            String last = this.getWorldShareInfo(4, "last");
            String sTodayAmount = this.getWorldShareInfo(5, (isCustom ? "c" : "") + "todayAmount_" + now);
            if (sTodayAmount == null) {
                sTodayAmount = this.getWorldShareInfo(5, (isCustom ? "c" : "") + "todayAmount_" + last);
            }

            String sAmount = this.getWorldShareInfo(5, (isCustom ? "c" : "") + "amount");
            int todayAmount = Math.abs(sTodayAmount == null ? 0 : Integer.parseInt(sTodayAmount)) + quantity;
            int amount = Math.abs(sAmount == null ? 0 : Integer.parseInt(sAmount));
            int remainQuantity;
            if (amount < (int)(5000.0F * rate)) {
                remainQuantity = Math.max(quantity - ((int)(5000.0F * rate) - amount), 0);
            } else {
                remainQuantity = quantity;
            }

            amount += quantity;
            String amountData = "";
            if (isCustom) {
                amountData = amountData + "ctodayAmount_" + now + "=" + todayAmount + ";camount=" + amount + ";";
                amountData = amountData + "todayAmount_" + now + "=" + (int)((float)todayAmount / rate) + ";amount=" + (int)((float)amount / rate);
            } else {
                amountData = amountData + "todayAmount_" + now + "=" + todayAmount + ";amount=" + amount;
            }

            this.updateWorldShareInfo(5, amountData);
            String payLogData = "";

            int nowMonthAmount;
            for(nowMonthAmount = 12; nowMonthAmount >= 1; --nowMonthAmount) {
                String preMonth = DateUtil.getPreDate("M", -nowMonthAmount).replaceAll("-", "").substring(0, 6);
                String sPreMonthAmount = this.getWorldShareInfo(4, (isCustom ? "c" : "") + preMonth);
                if (sPreMonthAmount != null) {
                    if (isCustom) {
                        payLogData = payLogData + "c" + preMonth + "=" + sPreMonthAmount + ";";
                        payLogData = payLogData + preMonth + "=" + (int)((float)Integer.parseInt(sPreMonthAmount) / rate) + ";";
                    } else {
                        payLogData = payLogData + preMonth + "=" + sPreMonthAmount + ";";
                    }
                }
            }

            nowMonthAmount = Math.abs(sNowMonthAmount == null ? 0 : Integer.parseInt(sNowMonthAmount)) + remainQuantity;
            if (isCustom) {
                payLogData = payLogData + "c" + nowMonth + "=" + nowMonthAmount + ";";
                payLogData = payLogData + nowMonth + "=" + (int)((float)nowMonthAmount / rate) + ";";
            } else {
                payLogData = payLogData + nowMonth + "=" + nowMonthAmount + ";";
            }

            payLogData = payLogData + "last=" + now;
            this.updateWorldShareInfo(4, payLogData);
            this.getMvpLevel();
        }
    }

    public boolean modifyCSPoints(int type, int quantity) {
        return this.modifyCSPoints(type, quantity, false);
    }

    public boolean modifyCSPoints(int type, int quantity, boolean show) {
        return this.modifyCSPoints(type, quantity, show, true);
    }

    public boolean modifyCSPoints(int type, int quantity, boolean show, boolean updateMvp) {
        int itemID;
        switch (type) {
            case 1:
                itemID = 2435892;
                if (quantity > 0 && this.getACash() + quantity < 0) {
                    if (show) {
                        this.send(UIPacket.ScriptProgressItemMessage(itemID, "樂豆點已達到上限！"));
                    }

                    return false;
                }

                this.setACash(this.getACash() + quantity);
                if (updateMvp) {
                    this.updateMvpLog(quantity);
                }
                break;
            case 2:
                itemID = 2432107;
                if (quantity > 0 && this.getMaplePoints(true) + quantity < 0) {
                    if (show) {
                        this.send(UIPacket.ScriptProgressItemMessage(itemID, "楓點已達到上限！"));
                    }

                    return false;
                }

                if (!this.client.modifyCSPoints(type, quantity)) {
                    return false;
                }

                this.playerObservable.update();
                this.client.announce(MaplePacketCreator.showCharCash(this));
                break;
            default:
                return false;
        }

        if (show) {
            this.client.announce(EffectPacket.showSpecialEffect(EffectOpcode.UserEffect_ExpItemConsumed));
        }

        return true;
    }

    public int getCSPoints(int type) {
        switch (type) {
            case -1:
                return this.getACash() + this.getMaplePoints();
            case 0:
            default:
                return 0;
            case 1:
                return this.getACash();
            case 2:
                return this.getMaplePoints();
        }
    }

    public int getTotalTWD() {
        int twd = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT SUM(rmb) FROM paylog WHERE account = ?");
                ps.setString(1, this.getClient().getAccountName());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    twd = rs.getInt(1);
                }

                ps.close();
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException Ex = var7;
            log.error("獲取賬號儲值總數失敗.", Ex);
        }

        return twd;
    }

    public List<Pair<String, Integer>> getTotalTWDRanking(int limit) {
        List<Pair<String, Integer>> ret = new LinkedList();

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                Calendar c = Calendar.getInstance();
                c.set(c.get(1), c.get(2), 1, 0, 0, 0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                PreparedStatement ps = con.prepareStatement("SELECT account, SUM(rmb) FROM paylog WHERE date(`paytime`) >= ? GROUP BY account ORDER BY rmb DESC LIMIT ?");
                ps.setString(1, sdf.format(c.getTime().getTime()));
                ps.setInt(2, limit);
                ResultSet rs = ps.executeQuery();

                while(true) {
                    if (!rs.next()) {
                        ps.close();
                        break;
                    }

                    ret.add(new Pair(rs.getString("account"), rs.getInt("rmb")));
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var10) {
            SQLException Ex = var10;
            log.error("獲取賬號儲值總數失敗.", Ex);
        }

        return ret;
    }

    public int getTWD() {
        int point = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT rmb FROM accounts WHERE name = ?");

                try {
                    ps.setString(1, this.getClient().getAccountName());
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            point = rs.getInt("rmb");
                        }
                    } catch (Throwable var10) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var9) {
                                var10.addSuppressed(var9);
                            }
                        }

                        throw var10;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var11) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var11.addSuppressed(var8);
                        }
                    }

                    throw var11;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var12) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var12.addSuppressed(var7);
                    }
                }

                throw var12;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var13) {
            SQLException e = var13;
            log.error("獲取角色twd失敗。", e);
        }

        return point;
    }

    public void setTWD(int point) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE accounts SET rmb = ? WHERE id = ?");

                try {
                    ps.setInt(1, point);
                    ps.setInt(2, this.getClient().getAccID());
                    ps.executeUpdate();
                } catch (Throwable var8) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var10) {
            SQLException e = var10;
            log.error("角色設置TWD失敗。", e);
        }

        this.playerObservable.update();
    }

    public void gainTWD(int point) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE accounts SET rmb = rmb + ? WHERE id = ?");

                try {
                    ps.setInt(1, point);
                    ps.setInt(2, this.getClient().getAccID());
                    ps.executeUpdate();
                } catch (Throwable var8) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var10) {
            SQLException e = var10;
            log.error("角色增加TWD失敗。", e);
        }

        this.playerObservable.update();
    }

    public boolean hasEquipped(int itemid) {
        return this.inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid) >= 1;
    }

    public boolean haveItem(int itemid, int quantity, boolean checkEquipped, boolean greaterOrEquals) {
        MapleInventoryType type = ItemConstants.getInventoryType(itemid);
        int possesed = this.inventory[type.ordinal()].countById(itemid);
        if (checkEquipped && (type == MapleInventoryType.EQUIP || type == MapleInventoryType.DECORATION)) {
            possesed += this.inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid);
        }

        if (greaterOrEquals) {
            return possesed >= quantity;
        } else {
            return possesed == quantity;
        }
    }

    public boolean haveItem(int itemid, int quantity) {
        return this.haveItem(itemid, quantity, true, true);
    }

    public boolean haveItem(int itemid) {
        return this.haveItem(itemid, 1, true, true);
    }

    public int getItemQuantity(int itemid) {
        MapleInventoryType type = ItemConstants.getInventoryType(itemid);
        return this.getInventory(type).countById(itemid);
    }

    public int getItemQuantity(int itemid, boolean checkEquipped) {
        int possesed = this.inventory[ItemConstants.getInventoryType(itemid).ordinal()].countById(itemid);
        if (checkEquipped) {
            possesed += this.inventory[MapleInventoryType.EQUIPPED.ordinal()].countById(itemid);
        }

        return possesed;
    }

    public int getEquipId(short slot) {
        MapleInventory equip = this.getInventory(MapleInventoryType.EQUIP);
        return equip.getItem(slot).getItemId();
    }

    public int getUseId(short slot) {
        MapleInventory use = this.getInventory(MapleInventoryType.USE);
        return use.getItem(slot).getItemId();
    }

    public int getSetupId(short slot) {
        MapleInventory setup = this.getInventory(MapleInventoryType.SETUP);
        return setup.getItem(slot).getItemId();
    }

    public int getCashId(short slot) {
        MapleInventory cash = this.getInventory(MapleInventoryType.CASH);
        return cash.getItem(slot).getItemId();
    }

    public int getEtcId(short slot) {
        MapleInventory etc = this.getInventory(MapleInventoryType.ETC);
        return etc.getItem(slot).getItemId();
    }

    public int getDecorationId(short slot) {
        MapleInventory etc = this.getInventory(MapleInventoryType.DECORATION);
        return etc.getItem(slot).getItemId();
    }

    public byte getBuddyCapacity() {
        return this.buddylist.getCapacity();
    }

    public void setBuddyCapacity(byte capacity) {
        this.buddylist.setCapacity(capacity);
        this.client.announce(BuddyListPacket.updateBuddyCapacity(capacity));
    }

    public MapleMessenger getMessenger() {
        return this.messenger;
    }

    public void setMessenger(MapleMessenger messenger) {
        this.messenger = messenger;
    }

    public List<MapleSummon> getSummonsReadLock() {
        this.summonsLock.lock();
        return this.summons;
    }

    public int getSummonsSize() {
        return this.summons.size();
    }

    public void unlockSummonsReadLock() {
        this.summonsLock.unlock();
    }

    public void addSummon(MapleSummon s) {
        this.summonsLock.lock();

        try {
            this.summons.add(s);
        } finally {
            this.summonsLock.unlock();
        }

    }

    public int getSummonCountBySkill(int skillID) {
        this.summonsLock.lock();

        int var2;
        try {
            var2 = (int)this.summons.stream().filter((it) -> {
                return it.getSkillId() == skillID;
            }).count();
        } finally {
            this.summonsLock.unlock();
        }

        return var2;
    }

    public final List<Integer> getSummonsOIDsBySkillID(int n) {
        this.summonsLock.lock();

        List var2;
        try {
            var2 = (List)this.summons.stream().filter((summon) -> {
                return summon.getSkillId() == n;
            }).map(MapleMapObject::getObjectId).collect(Collectors.toList());
        } finally {
            this.summonsLock.unlock();
        }

        return var2;
    }

    public MapleSummon getSummonBySkillID(int skillId) {
        summonsLock.lock();
        try {
            return summons.stream().filter(summon -> summon.getSkillId() == skillId).findFirst().orElse(null);
        } finally {
            summonsLock.unlock();
        }
    }

    public final void removeSummonBySkillID(int skillID, int animated) {
        MapleSummon summon;
        if ((summon = this.getSummonBySkillID(skillID)) != null) {
            this.removeSummon(summon, animated);
        }

    }

    public final void removeSummon(MapleSummon summon, int animated) {
        if (summon != null) {
            summon.setAnimated(animated);
            this.cancelEffect(summon.getEffect(), false, summon.getCreateTime(), summon.getEffect().getStatups());
        }

    }

    public final void spawnSummons() {
        this.summonsLock.lock();

        try {
            Iterator var1 = this.summons.iterator();

            while(var1.hasNext()) {
                MapleSummon summon = (MapleSummon)var1.next();
                summon.setAnimated(1);
                summon.setPosition(this.getPosition());
                summon.setCurrentFh(this.getCurrentFH());
                summon.setMap(this.map);
                this.map.addMapObject(summon);
                summon.setAnimated(0);
            }
        } finally {
            this.summonsLock.unlock();
        }

    }

    public final void disappearSummons(boolean b) {
        List<MapleSummon> list = new ArrayList();
        this.summonsLock.lock();

        Iterator var3;
        MapleSummon summon;
        try {
            var3 = this.summons.iterator();

            while(var3.hasNext()) {
                summon = (MapleSummon)var3.next();
                summon.setAnimated(12);
                summon.setMap((MapleMap)null);
                if (this.map != null) {
                    this.map.disappearMapObject(summon);
                }

                switch (summon.getMovementType()) {
                    case STOP:
                    case FIX_V_MOVE:
                    case SMART:
                    case WALK_RANDOM:
                        list.add(summon);
                        break;
                    default:
                        if (b) {
                            list.add(summon);
                        }
                }
            }
        } finally {
            this.summonsLock.unlock();
        }

        var3 = list.iterator();

        while(var3.hasNext()) {
            summon = (MapleSummon)var3.next();
            this.cancelEffect(summon.getEffect(), b, summon.getCreateTime(), summon.getEffect().getStatups());
        }

    }

    public boolean removeSummon(MapleSummon s) {
        this.summonsLock.lock();

        boolean var2;
        try {
            var2 = this.summons.remove(s);
        } finally {
            this.summonsLock.unlock();
        }

        return var2;
    }

    public void removeSummon(int Skillid) {
        this.summonsLock.lock();
        MapleSummon delet = null;

        try {
            Iterator var3 = this.summons.iterator();

            while(true) {
                if (var3.hasNext()) {
                    MapleSummon su = (MapleSummon)var3.next();
                    if (su.getSkillId() != Skillid) {
                        continue;
                    }

                    delet = su;
                }

                if (delet != null) {
                    this.getMap().broadcastMessage(SummonPacket.removeSummon(delet, true));
                    this.getMap().removeMapObject(delet);
                    this.removeVisibleMapObjectEx(delet);
                    this.summons.remove(delet);
                }
                break;
            }
        } finally {
            this.summonsLock.unlock();
        }

    }

    public boolean hasSummonBySkill(int skillId) {
        if (this.summons != null && !this.summons.isEmpty()) {
            this.summonsLock.lock();

            try {
                Iterator var2 = this.summons.iterator();

                while(var2.hasNext()) {
                    MapleSummon summon = (MapleSummon)var2.next();
                    if (summon.getSkillId() == skillId) {
                        boolean var4 = true;
                        return var4;
                    }
                }
            } finally {
                this.summonsLock.unlock();
            }

            return false;
        } else {
            return false;
        }
    }

    public void updateSummonBySkillID(int skillId, int newSkillId, boolean animated) {
        this.summonsLock.lock();

        try {
            this.summons.forEach((summon) -> {
                if (summon.getSkillId() == skillId) {
                    summon.setSkillId(newSkillId);
                    this.getMap().broadcastMessage(SummonPacket.removeSummon(summon, animated));
                }

            });
        } finally {
            this.summonsLock.unlock();
        }

    }

    public int getCooldownLeftTime(int skillId) {
        this.cooldownLock.lock();

        byte var8;
        try {
            Iterator var2 = this.skillCooldowns.values().iterator();

            while(var2.hasNext()) {
                MapleCoolDownValueHolder mcdvh = (MapleCoolDownValueHolder)var2.next();
                if (mcdvh.skillId == skillId) {
                    int var4 = mcdvh.getLeftTime();
                    return var4;
                }
            }

            var8 = 0;
        } finally {
            this.cooldownLock.unlock();
        }

        return var8;
    }

    public void clearCooldown(boolean b) {
        this.cooldownLock.lock();

        try {
            Iterator<Map.Entry<Integer, MapleCoolDownValueHolder>> iterator = this.skillCooldowns.entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry<Integer, MapleCoolDownValueHolder> it = (Map.Entry)iterator.next();
                MapleCoolDownValueHolder mcdvh = (MapleCoolDownValueHolder)it.getValue();
                Skill skill = SkillFactory.getSkill((Integer)it.getKey());
                if (skill != null && (!b || !skill.isHyperSkill() && !skill.isNotCooltimeReduce() && !skill.isNotCooltimeReset() && !skill.isVSkill())) {
                    if (mcdvh != null) {
                        mcdvh.cancel();
                    }

                    if (this.client != null && this.map != null) {
                        this.client.announce(MaplePacketCreator.skillCooltimeSet((Integer)it.getKey(), 0));
                    }

                    iterator.remove();
                }
            }
        } finally {
            this.cooldownLock.unlock();
        }

    }

    public final void reduceAllSkillCooldown(int time, boolean b) {
        this.cooldownLock.lock();
        Map<Integer, Integer> map = new HashMap();

        Iterator iterator;
        Map.Entry it;
        try {
            iterator = this.skillCooldowns.entrySet().iterator();

            label118:
            while(true) {
                MapleCoolDownValueHolder mcdvh;
                Skill skill;
                do {
                    do {
                        if (!iterator.hasNext()) {
                            break label118;
                        }

                        it = (Map.Entry)iterator.next();
                        mcdvh = (MapleCoolDownValueHolder)it.getValue();
                        skill = SkillFactory.getSkill((Integer)it.getKey());
                    } while(skill == null);
                } while(b && (skill.isHyperSkill() || skill.isNotCooltimeReduce() || skill.isNotCooltimeReset() || skill.isVSkill()));

                if (mcdvh != null) {
                    int left = mcdvh.getLeftTime() - time;
                    if (left > 0) {
                        map.put((Integer)it.getKey(), left);
                    } else {
                        mcdvh.cancel();
                        if (this.client != null && this.map != null) {
                            this.client.announce(MaplePacketCreator.skillCooltimeSet((Integer)it.getKey(), 0));
                        }

                        iterator.remove();
                    }
                }
            }
        } finally {
            this.cooldownLock.unlock();
        }

        iterator = map.entrySet().iterator();

        while(iterator.hasNext()) {
            it = (Map.Entry)iterator.next();
            this.registerSkillCooldown((Integer)it.getKey(), (Integer)it.getValue(), true);
        }

    }

    public final void reduceSkillCooldownRate(int skillID, int reduceRate) {
        this.cooldownLock.lock();

        MapleCoolDownValueHolder mcdvh;
        try {
            mcdvh = (MapleCoolDownValueHolder)this.skillCooldowns.get(skillID);
        } finally {
            this.cooldownLock.unlock();
        }

        if (mcdvh != null) {
            if ((reduceRate = mcdvh.getLeftTime() * (100 - reduceRate) / 100) > 0) {
                this.registerSkillCooldown(skillID, reduceRate, true);
                return;
            }

            this.cancelSkillCooldown(skillID);
        }

    }

    public final void reduceSkillCooldown(int skillID, int reduceTime) {
        this.cooldownLock.lock();

        MapleCoolDownValueHolder mcdvh;
        try {
            mcdvh = (MapleCoolDownValueHolder)this.skillCooldowns.get(skillID);
        } finally {
            this.cooldownLock.unlock();
        }

        if (mcdvh != null) {
            if ((reduceTime = mcdvh.getLeftTime() - reduceTime) > 0) {
                this.registerSkillCooldown(skillID, reduceTime, true);
            } else {
                this.cancelSkillCooldown(skillID);
            }
        }

    }

    public final void cancelSkillCooldown(int skillID) {
        this.cooldownLock.lock();

        try {
            MapleCoolDownValueHolder mcdvh = (MapleCoolDownValueHolder)this.skillCooldowns.remove(skillID);
            if (mcdvh != null) {
                mcdvh.cancel();
            }

            if (this.client != null && this.map != null) {
                this.client.announce(MaplePacketCreator.skillCooltimeSet(skillID, 0));
            }
        } finally {
            this.cooldownLock.unlock();
        }

        switch (skillID) {
            case 1320019:
                MapleStatEffect effect = this.getSkillEffect(1320016);
                if (effect != null) {
                    effect.applyBuffEffect(this, this, 100, false, false, false, this.getPosition());
                }
                break;
            case 400051074:
                this.specialStats.setPoolMakerCount(0);
                this.send(MaplePacketCreator.poolMakerInfo(false, 0, 0));
                break;
            default:
                if (skillID == this.soulSkillID) {
                    this.setShowSoulEffect(true);
                }
        }

    }

    public void registerSkillCooldown(MapleStatEffect eff, boolean send) {
        this.registerSkillCooldown(SkillConstants.getCooldownLinkSourceId(eff.getSourceId()), eff.getCooldown(this), send);
    }

    public void registerSkillCooldown(int skillID, int duration, boolean send) {
        if (ServerConfig.CHANNEL_PLAYER_DISABLECOOLDOWN || this.isAdmin() && this.isInvincible() && duration > 350) {
            if (this.isAdmin() && this.isInvincible()) {
            }

            duration = 350;
        }

        this.cooldownLock.lock();

        try {
            MapleCoolDownValueHolder mcdvh = (MapleCoolDownValueHolder)this.skillCooldowns.remove(skillID);
            if (mcdvh != null) {
                mcdvh.cancel();
            }

            if (duration > 0) {
                if (this.isDebug()) {
                    this.dropDebugMessage(0, "[CoolDown] Register CoolDown SkillID:" + skillID + " Duration:" + duration);
                }

                this.skillCooldowns.put(skillID, new MapleCoolDownValueHolder(skillID, duration, CoolDownTimer.getInstance().schedule(new MapleCoolDownValueHolder.CancelCooldownAction(skillID, this), (long)duration)));
                if (send) {
                    this.client.announce(MaplePacketCreator.skillCooltimeSet(skillID, duration));
                }
            }
        } finally {
            this.cooldownLock.unlock();
        }

        if (send && skillID == 1320019 && duration > 0) {
            MapleStatEffect effect = this.getSkillEffect(1320016);
            if (effect != null) {
                effect.applyBuffEffect(this, this, duration, false, false, false, this.getPosition());
            }
        }

    }

    public final Map<Integer, MapleCoolDownValueHolder> getSkillCooldowns() {
        this.cooldownLock.lock();

        Map var1;
        try {
            var1 = this.skillCooldowns;
        } finally {
            this.cooldownLock.unlock();
        }

        return var1;
    }

    public boolean isSkillCooling(int skillid) {
        this.cooldownLock.lock();

        boolean var2;
        try {
            var2 = this.skillCooldowns.containsKey(skillid);
        } finally {
            this.cooldownLock.unlock();
        }

        return var2;
    }

    public void registerSkillCooldown(int skillId, long startTime, long length) {
        this.registerSkillCooldown(skillId, (int)length, true);
    }

    public void giveCoolDowns(List<MapleCoolDownValueHolder> cooldowns) {
        if (cooldowns != null) {
            Iterator var2 = cooldowns.iterator();

            while(var2.hasNext()) {
                MapleCoolDownValueHolder cooldown = (MapleCoolDownValueHolder)var2.next();
                if (cooldown.getLeftTime() <= 0) {
                    this.cancelSkillCooldown(cooldown.skillId);
                } else {
                    this.registerSkillCooldown(cooldown.skillId, cooldown.getLeftTime(), true);
                }
            }
        } else {
            try {
                Connection con = DatabaseConnectionEx.getInstance().getConnection();

                try {
                    PreparedStatement ps = con.prepareStatement("SELECT SkillID,StartTime,length FROM skills_cooldowns WHERE charid = ?");
                    ps.setInt(1, this.getId());
                    ResultSet rs = ps.executeQuery();

                    while(true) {
                        if (!rs.next()) {
                            ps.close();
                            rs.close();
                            this.deleteWhereCharacterId(con, "DELETE FROM skills_cooldowns WHERE charid = ?");
                            break;
                        }

                        if (rs.getLong("length") + rs.getLong("StartTime") - System.currentTimeMillis() > 0L) {
                            this.registerSkillCooldown(rs.getInt("SkillID"), rs.getLong("StartTime"), rs.getLong("length"));
                        }
                    }
                } catch (Throwable var6) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var5) {
                            var6.addSuppressed(var5);
                        }
                    }

                    throw var6;
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException var7) {
                System.err.println("Error while retriving cooldown from SQL storage");
            }
        }

    }

    public List<MapleCoolDownValueHolder> getCooldowns() {
        return this.getCooldowns(false, false);
    }

    public List<MapleCoolDownValueHolder> getCooldowns(boolean reduce) {
        return this.getCooldowns(reduce, false);
    }

    public List<MapleCoolDownValueHolder> getCooldowns(boolean reduce, boolean cancel) {
        List<MapleCoolDownValueHolder> ret = new ArrayList();
        this.cooldownLock.lock();

        try {
            Iterator var4 = this.skillCooldowns.values().iterator();

            while(var4.hasNext()) {
                MapleCoolDownValueHolder mc = (MapleCoolDownValueHolder)var4.next();
                if (mc != null) {
                    Skill skill = SkillFactory.getSkill(mc.skillId);
                    if (!reduce || skill == null || !skill.isHyperSkill()) {
                        ret.add(mc);
                    }
                }
            }

            List var10 = ret;
            return var10;
        } finally {
            this.cooldownLock.unlock();
        }
    }

    public long getCooldownLimit(int skillid) {
        this.cooldownLock.lock();

        long var9;
        try {
            Iterator var2 = this.skillCooldowns.values().iterator();

            while(var2.hasNext()) {
                MapleCoolDownValueHolder mcdvh = (MapleCoolDownValueHolder)var2.next();
                if (mcdvh.skillId == skillid) {
                    long var4 = System.currentTimeMillis() - mcdvh.startTime;
                    return var4;
                }
            }

            var9 = 0L;
        } finally {
            this.cooldownLock.unlock();
        }

        return var9;
    }

    public void resetAllCooldowns(boolean byskill) {
        this.getCooldowns(byskill).forEach((it) -> {
            this.cancelSkillCooldown(it.skillId);
        });
    }

    public int getMulungEnergy() {
        return this.mulung_energy;
    }

    public void mulung_EnergyModify(boolean inc) {
        if (inc) {
            if (this.mulung_energy + 100 > 10000) {
                this.mulung_energy = 10000;
            } else {
                this.mulung_energy = (short)(this.mulung_energy + 100);
            }
        } else {
            this.mulung_energy = 0;
        }

        this.client.announce(MaplePacketCreator.MulungEnergy(this.mulung_energy));
    }

    public void writeMapEventEffect(String inc) {
        this.client.announce(MaplePacketCreator.showEffect(inc));
    }

    public void writeMulungEnergy() {
        this.client.announce(MaplePacketCreator.MulungEnergy(this.mulung_energy));
    }

    public void writeEnergy(String type, String inc) {
        this.client.announce(MaplePacketCreator.sendPyramidEnergy(type, inc));
    }

    public void writeStatus(String type, String inc) {
        this.client.announce(MaplePacketCreator.sendGhostStatus(type, inc));
    }

    public void writePoint(String type, String inc) {
        this.client.announce(MaplePacketCreator.sendGhostPoint(type, inc));
    }

    public int getAranCombo() {
        return this.specialStats.getAranCombo();
    }

    public void gainAranCombo(int count, boolean show) {
        if (this.getAranCombo() + count < 1000) {
            this.setAranCombo(this.getAranCombo() + count, show);
        } else {
            this.setAranCombo(1000, show);
        }

    }

    public void setAranCombo(int count, boolean show) {
        this.specialStats.setAranCombo(Math.max(0, Math.min(1000, count)));
        if (show) {
            this.client.announce(MaplePacketCreator.ShowAranCombo(count));
        }

    }

    public long getKeyDownSkill_Time() {
        return this.keydown_skill;
    }

    public void setKeyDownSkill_Time(long keydown_skill) {
        this.keydown_skill = keydown_skill;
    }

    public void checkBloodContract() {
        if (JobConstants.is惡魔復仇者(this.job)) {
            Skill skill = SkillFactory.getSkill(30010242);
            int skilllevel = this.getTotalSkillLevel(skill);
            if (skilllevel >= 1 && this.map != null) {
                skill.getEffect(skilllevel).applyTo(this);
            }

        }
    }

    public void setMarketChalkboard(String text) {
        if (this.map != null) {
            this.map.broadcastMessage(MTSCSPacket.useChalkboard(this.getId(), text));
            if (this.chalkSchedule != null) {
                this.chalkSchedule.cancel(false);
                this.chalkSchedule = null;
            }

            this.chalkSchedule = WorldTimer.getInstance().schedule(() -> {
                this.setChalkboard((String)null);
            }, 4000L);
        }
    }

    public String getChalkboard() {
        return this.chalktext;
    }

    public void setChalkboard(String text) {
        this.chalktext = text;
        if (this.map != null) {
            this.map.broadcastMessage(MTSCSPacket.useChalkboard(this.getId(), text));
        }

    }

    public MapleMount getMount() {
        return this.mount;
    }

    public int[] getWishlist() {
        return this.wishlist;
    }

    public void setWishlist(int[] wl) {
        this.wishlist = wl;
        this.changed_wishlist = true;
    }

    public void clearWishlist() {
        for(int i = 0; i < 12; ++i) {
            this.wishlist[i] = 0;
        }

        this.changed_wishlist = true;
    }

    public int getWishlistSize() {
        int ret = 0;

        for(int i = 0; i < 12; ++i) {
            if (this.wishlist[i] > 0) {
                ++ret;
            }
        }

        return ret;
    }

    public int[] getRocks() {
        return this.rocks;
    }

    public int getRockSize() {
        int ret = 0;

        for(int i = 0; i < 10; ++i) {
            if (this.rocks[i] != 999999999) {
                ++ret;
            }
        }

        return ret;
    }

    public void deleteFromRocks(int map) {
        for(int i = 0; i < 10; ++i) {
            if (this.rocks[i] == map) {
                this.rocks[i] = 999999999;
                this.changed_trocklocations = true;
                break;
            }
        }

    }

    public void addRockMap() {
        if (this.getRockSize() < 10) {
            this.rocks[this.getRockSize()] = this.getMapId();
            this.changed_trocklocations = true;
        }
    }

    public boolean isRockMap(int id) {
        for(int i = 0; i < 10; ++i) {
            if (this.rocks[i] == id) {
                return true;
            }
        }

        return false;
    }

    public int[] getRegRocks() {
        return this.regrocks;
    }

    public int getRegRockSize() {
        int ret = 0;

        for(int i = 0; i < 5; ++i) {
            if (this.regrocks[i] != 999999999) {
                ++ret;
            }
        }

        return ret;
    }

    public void deleteFromRegRocks(int map) {
        for(int i = 0; i < 5; ++i) {
            if (this.regrocks[i] == map) {
                this.regrocks[i] = 999999999;
                this.changed_trocklocations = true;
                break;
            }
        }

    }

    public void addRegRockMap() {
        if (this.getRegRockSize() < 5) {
            this.regrocks[this.getRegRockSize()] = this.getMapId();
            this.changed_trocklocations = true;
        }
    }

    public boolean isRegRockMap(int id) {
        for(int i = 0; i < 5; ++i) {
            if (this.regrocks[i] == id) {
                return true;
            }
        }

        return false;
    }

    public int[] getHyperRocks() {
        return this.hyperrocks;
    }

    public int getHyperRockSize() {
        int ret = 0;

        for(int i = 0; i < 13; ++i) {
            if (this.hyperrocks[i] != 999999999) {
                ++ret;
            }
        }

        return ret;
    }

    public void deleteFromHyperRocks(int map) {
        for(int i = 0; i < 13; ++i) {
            if (this.hyperrocks[i] == map) {
                this.hyperrocks[i] = 999999999;
                this.changed_trocklocations = true;
                break;
            }
        }

    }

    public void addHyperRockMap() {
        if (this.getRegRockSize() < 13) {
            this.hyperrocks[this.getHyperRockSize()] = this.getMapId();
            this.changed_trocklocations = true;
        }
    }

    public boolean isHyperRockMap(int id) {
        for(int i = 0; i < 13; ++i) {
            if (this.hyperrocks[i] == id) {
                return true;
            }
        }

        return false;
    }

    public MaplePotionPot getPotionPot() {
        return this.potionPot;
    }

    public void setPotionPot(MaplePotionPot p) {
        this.potionPot = p;
    }

    public void dropMessageIfAdmin(int type, String message) {
        if (this.isAdmin()) {
            this.dropMessage(type, message);
        }

    }

    public void dropMessage(int type, String message) {
        if (type == -1) {
            this.client.announce(UIPacket.getTopMsg(message));
        } else if (type == -2) {
            this.client.announce(PlayerShopPacket.playerInterChat(message, 0, this.getName()));
        } else if (type == -3) {
            this.client.announce(MaplePacketCreator.getChatText(this.getId(), message, this.getName(), this.isSuperGm(), 0, true, -1));
        } else if (type == -4) {
            this.client.announce(MaplePacketCreator.getChatText(this.getId(), message, this.getName(), this.isSuperGm(), 1, true, -1));
        } else if (type == -5) {
            this.client.announce(MaplePacketCreator.spouseMessage(UserChatMessageType.getByType(-5), message));
        } else if (type == -6) {
            this.client.announce(MaplePacketCreator.spouseMessage(UserChatMessageType.getByType(9), message));
        } else if (type == -7) {
            this.client.announce(UIPacket.getMidMsg(0, message, false));
        } else if (type == -8) {
            this.client.announce(UIPacket.getMidMsg(0, message, true));
        } else if (type == -9) {
            this.client.announce(MaplePacketCreator.showRedNotice(message));
        } else if (type == -10) {
            this.client.announce(MaplePacketCreator.getFollowMessage(message));
        } else if (type == -11) {
            this.client.announce(MaplePacketCreator.yellowChat(message));
        } else if (type == -12) {
            this.client.announce(UIPacket.addPopupSay(1540488, 10000, message, ""));
        } else if (type == 11) {
            this.client.announce(UIPacket.addPopupSay(1540488, 10000, message, ""));
        } else {
            this.client.announce(MaplePacketCreator.serverNotice(type, message));
        }

    }

    public void dropSpouseMessage(UserChatMessageType type, String message) {
        this.client.announce(MaplePacketCreator.spouseMessage(type, message));
    }

    public void dropDebugMessage(int type, String message) {
        this.dropSpouseMessage(type == 0 ? UserChatMessageType.道具訊息 : (type == 1 ? UserChatMessageType.方塊洗洗樂 : (type == 2 ? UserChatMessageType.淺紫 : UserChatMessageType.頻道喇叭)), message);
    }

    public void dropDebugMessage(String message) {
        this.dropDebugMessage(3, message);
    }

    public IMaplePlayerShop getPlayerShop() {
        return this.playerShop;
    }

    public void setPlayerShop(IMaplePlayerShop playerShop) {
        this.playerShop = playerShop;
    }

    public int getConversation() {
        return this.inst.get();
    }

    public void setConversation(int inst) {
        this.inst.set(inst);
    }

    public void resetConversation() {
        this.setConversation(0);
    }

    public int getDirection() {
        return this.insd.get();
    }

    public void setDirection(int inst) {
        this.insd.set(inst);
    }

    public MapleCarnivalParty getCarnivalParty() {
        return this.carnivalParty;
    }

    public void setCarnivalParty(MapleCarnivalParty party) {
        this.carnivalParty = party;
    }

    public void addCP(int ammount) {
        this.totalCP = (short)(this.totalCP + ammount);
        this.availableCP = (short)(this.availableCP + ammount);
    }

    public void useCP(int ammount) {
        this.availableCP = (short)(this.availableCP - ammount);
    }

    public int getAvailableCP() {
        return this.availableCP;
    }

    public int getTotalCP() {
        return this.totalCP;
    }

    public void resetCP() {
        this.totalCP = 0;
        this.availableCP = 0;
    }

    public void addCarnivalRequest(MapleCarnivalChallenge request) {
        this.pendingCarnivalRequests.add(request);
    }

    public final MapleCarnivalChallenge getNextCarnivalRequest() {
        return (MapleCarnivalChallenge)this.pendingCarnivalRequests.pollLast();
    }

    public void clearCarnivalRequests() {
        this.pendingCarnivalRequests = new LinkedList();
    }

    public void startMonsterCarnival(int enemyavailable, int enemytotal) {
        this.client.announce(MonsterCarnivalPacket.startMonsterCarnival(this, enemyavailable, enemytotal));
    }

    public boolean getCanTalk() {
        return this.canTalk;
    }

    public void canTalk(boolean talk) {
        this.canTalk = talk;
    }

    public double getEXPMod() {
        return this.stats.expCardRate > 0 ? (double)this.stats.expCardRate / 100.0 : 1.0;
    }

    public double getDropMod() {
        return this.stats.dropCardRate > 0 ? (double)this.stats.dropCardRate / 100.0 : 1.0;
    }

    public int getACash() {
        return this.getClient().getACash();
    }

    public void setACash(int point) {
        this.getClient().setACash(point);
        this.playerObservable.update();
    }

    public int getMaplePoints() {
        return this.getMaplePoints(false);
    }

    public int getMaplePoints(boolean onlyMPoint) {
        return this.getClient().getMaplePoints(onlyMPoint);
    }

    public int getMileage() {
        return this.getClient().getMileage();
    }

    public int modifyMileage(int quantity) {
        return this.modifyMileage(quantity, 2, false, true, (String)null);
    }

    public int modifyMileage(int quantity, int type) {
        return this.modifyMileage(quantity, type, false, true, (String)null);
    }

    public int modifyMileage(int quantity, String log) {
        return this.modifyMileage(quantity, 2, false, true, log);
    }

    public int modifyMileage(int quantity, boolean show) {
        return this.modifyMileage(quantity, 2, show, true, (String)null);
    }

    public int modifyMileage(int quantity, int type, boolean show, boolean limitMax, String log) {
        if (quantity == 0) {
            return 0;
        } else {
            int itemID = 2431872;
            if (quantity > 0 && this.getMileage() + quantity < 0) {
                if (show) {
                    this.send(UIPacket.ScriptProgressItemMessage(itemID, "里程已達到上限."));
                }

                return 3;
            } else {
                this.playerObservable.update();
                int result = quantity > 0 ? this.getClient().rechargeMileage(quantity, type, limitMax, log) : this.getClient().modifyMileage(quantity);
                if (show && result > 0 && result < 3) {
                    switch (result) {
                        case 1 -> this.send(UIPacket.ScriptProgressItemMessage(itemID, "里程已達到每日上限！"));
                        case 2 -> this.send(UIPacket.ScriptProgressItemMessage(itemID, "里程已達到每月上限！"));
                    }

                    return result;
                } else {
                    if (result == 0 && show && quantity != 0) {
                        this.send(UIPacket.ScriptProgressItemMessage(itemID, (quantity > 0 ? "獲得 " : "消耗 ") + Math.abs(quantity) + " 里程！"));
                        this.client.announce(EffectPacket.showSpecialEffect(EffectOpcode.UserEffect_ExpItemConsumed));
                    }

                    return result;
                }
            }
        }
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int p) {
        this.points = p;
    }

    public int getVPoints() {
        return this.vpoints;
    }

    public void setVPoints(int p) {
        this.vpoints = p;
    }

    public CashShop getCashInventory() {
        return this.cs;
    }

    public void removeItem(int id, int quantity) {
        MapleInventoryManipulator.removeById(this.client, ItemConstants.getInventoryType(id), id, quantity, true, false);
        this.client.announce(EffectPacket.getShowItemGain(id, (short)(-quantity), true));
    }

    public void removeAll(int id) {
        this.removeAll(id, true, false);
    }

    public void removeAll(int itemId, boolean show, boolean checkEquipped) {
        MapleInventoryType type = ItemConstants.getInventoryType(itemId);
        int possessed = this.getInventory(type).countById(itemId);
        if (possessed > 0) {
            MapleInventoryManipulator.removeById(this.getClient(), type, itemId, possessed, true, false);
            if (show) {
                this.getClient().announce(EffectPacket.getShowItemGain(itemId, (short)(-possessed), true));
            }
        }

        if (checkEquipped && (type == MapleInventoryType.EQUIP || type == MapleInventoryType.DECORATION)) {
            type = MapleInventoryType.EQUIPPED;
            possessed = this.getInventory(type).countById(itemId);
            if (possessed > 0) {
                MapleInventoryManipulator.removeById(this.getClient(), type, itemId, possessed, true, false);
                if (show) {
                    this.getClient().announce(EffectPacket.getShowItemGain(itemId, (short)(-possessed), true));
                }

                this.equipChanged();
            }
        }

    }

    public void removeItem(int itemId) {
        this.removeItem(itemId, false);
    }

    public void removeItem(int itemId, boolean show) {
        MapleInventoryType type = ItemConstants.getInventoryType(itemId);
        if (type == MapleInventoryType.EQUIP || type == MapleInventoryType.DECORATION) {
            type = MapleInventoryType.EQUIPPED;
            int possessed = this.getInventory(type).countById(itemId);
            if (possessed > 0) {
                MapleInventoryManipulator.removeById(this.getClient(), type, itemId, possessed, true, false);
                if (show) {
                    this.getClient().announce(EffectPacket.getShowItemGain(itemId, (short)(-possessed), true));
                }

                this.equipChanged();
            }
        }

    }

    public MapleRing getMarriageRing() {
        MapleInventory iv = this.getInventory(MapleInventoryType.EQUIPPED);
        List<Item> equipped = iv.newList();
        Collections.sort(equipped);
        MapleRing mrings = null;
        Iterator var5 = equipped.iterator();

        MapleRing ring;
        while(var5.hasNext()) {
            Item ite = (Item)var5.next();
            Equip item = (Equip)ite;
            if (item.getRing() != null) {
                ring = item.getRing();
                ring.setEquipped(true);
                if (mrings == null && 類型.結婚戒指(item.getItemId())) {
                    mrings = ring;
                }
            }
        }

        if (mrings == null) {
            MapleInventoryType[] tps = new MapleInventoryType[]{MapleInventoryType.EQUIP, MapleInventoryType.DECORATION};
            MapleInventoryType[] var14 = tps;
            int var15 = tps.length;

            for(int var8 = 0; var8 < var15; ++var8) {
                MapleInventoryType tp = var14[var8];
                iv = this.getInventory(tp);
                Iterator var10 = iv.list().iterator();

                while(var10.hasNext()) {
                    Item ite = (Item)var10.next();
                    Equip item = (Equip)ite;
                    if (item.getRing() != null) {
                        ring = item.getRing();
                        ring.setEquipped(false);
                        if (mrings == null && 類型.結婚戒指(item.getItemId())) {
                            mrings = ring;
                        }
                    }
                }

                if (mrings != null) {
                    break;
                }
            }
        }

        return mrings;
    }

    public Triple<List<MapleRing>, List<MapleRing>, List<MapleRing>> getRings(boolean equip) {
        MapleInventory iv = this.getInventory(MapleInventoryType.EQUIPPED);
        List<Item> equipped = iv.newList();
        Collections.sort(equipped);
        List<MapleRing> crings = new ArrayList();
        List<MapleRing> frings = new ArrayList();
        List<MapleRing> mrings = new ArrayList();
        Iterator var8 = equipped.iterator();

        while(true) {
            while(true) {
                MapleRing ring;
                Equip item;
                do {
                    do {
                        if (!var8.hasNext()) {
                            if (equip) {
                                MapleInventoryType[] tps = new MapleInventoryType[]{MapleInventoryType.EQUIP, MapleInventoryType.DECORATION};
                                MapleInventoryType[] var17 = tps;
                                int var18 = tps.length;

                                for(int var11 = 0; var11 < var18; ++var11) {
                                    MapleInventoryType tp = var17[var11];
                                    iv = this.getInventory(tp);
                                    Iterator var13 = iv.list().iterator();

                                    while(var13.hasNext()) {
                                        Item ite = (Item)var13.next();
                                        item = (Equip)ite;
                                        if (item.getRing() != null && 類型.特效裝備(item.getItemId())) {
                                            ring = item.getRing();
                                            ring.setEquipped(false);
                                            if (類型.戀人裝備(item.getItemId())) {
                                                crings.add(ring);
                                            } else if (類型.友情裝備(item.getItemId())) {
                                                frings.add(ring);
                                            } else if (類型.結婚戒指(item.getItemId())) {
                                                mrings.add(ring);
                                            }
                                        }
                                    }
                                }
                            }

                            frings.sort(new MapleRing.RingComparator());
                            crings.sort(new MapleRing.RingComparator());
                            mrings.sort(new MapleRing.RingComparator());
                            return new Triple(crings, frings, mrings);
                        }

                        Item ite = (Item)var8.next();
                        item = (Equip)ite;
                    } while(item.getRing() == null);
                } while(!類型.特效裝備(item.getItemId()));

                ring = item.getRing();
                ring.setEquipped(true);
                if (equip) {
                    if (類型.戀人裝備(item.getItemId())) {
                        crings.add(ring);
                    } else if (類型.友情裝備(item.getItemId())) {
                        frings.add(ring);
                    } else if (類型.結婚戒指(item.getItemId())) {
                        mrings.add(ring);
                    }
                } else if (crings.isEmpty() && 類型.戀人裝備(item.getItemId())) {
                    crings.add(ring);
                } else if (frings.isEmpty() && 類型.友情裝備(item.getItemId())) {
                    frings.add(ring);
                } else if (mrings.isEmpty() && 類型.結婚戒指(item.getItemId())) {
                    mrings.add(ring);
                }
            }
        }
    }

    public int getFH() {
        MapleFoothold fh = this.getMap().getFootholds().findBelow(this.getPosition());
        return fh != null ? fh.getId() : 0;
    }

    public boolean canHP(long now) {
        if (this.lastHPTime + 5000L < now) {
            this.lastHPTime = now;
            return true;
        } else {
            return false;
        }
    }

    public boolean canMP(long now) {
        if (this.lastMPTime + 5000L < now) {
            this.lastMPTime = now;
            return true;
        } else {
            return false;
        }
    }

    public boolean canHPRecover(long now) {
        if (this.stats.hpRecoverTime > 0 && this.lastHPTime + (long)this.stats.hpRecoverTime < now) {
            this.lastHPTime = now;
            return true;
        } else {
            return false;
        }
    }

    public boolean canMPRecover(long now) {
        if (this.stats.mpRecoverTime > 0 && this.lastMPTime + (long)this.stats.mpRecoverTime < now) {
            this.lastMPTime = now;
            return true;
        } else {
            return false;
        }
    }

    public void checkFairy() {
        long startTime = 0L;
        boolean inChat = true;
        MapleInventory iv = this.getInventory(MapleInventoryType.EQUIPPED);
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        List<Integer> toRemove = new LinkedList();
        Iterator var8 = this.fairys.keySet().iterator();

        while(true) {
            Item item;
            int stage;
            do {
                if (!var8.hasNext()) {
                    var8 = toRemove.iterator();

                    while(var8.hasNext()) {
                        stage = (Integer)var8.next();
                        this.fairys.remove(stage);
                    }

                    long time = 0L;
                    int newStage = 0;
                    Iterator var13 = this.stats.getEquipmentBonusExps().entrySet().iterator();

                    while(var13.hasNext()) {
                        Map.Entry<Integer, List<Integer>> bonusExps = (Map.Entry)var13.next();
                        boolean show = false;
                        item = iv.getItem((short)(-(Integer)bonusExps.getKey()));
                        if (item != null && !ii.getBonusExps(item.getItemId()).isEmpty()) {
                            if (!this.fairys.containsKey(bonusExps.getKey())) {
                                stage = 0;
                                startTime = System.currentTimeMillis();
                                time = 0L;
                                this.fairys.put((Integer)bonusExps.getKey(), new Pair(stage, startTime));
                                show = true;
                            } else {
                                stage = (Integer)((Pair)this.fairys.get(bonusExps.getKey())).getLeft();
                                startTime = (Long)((Pair)this.fairys.get(bonusExps.getKey())).getRight();
                                time = (System.currentTimeMillis() - startTime) / 60000L;
                                newStage = Math.min((int)(time / 60L), ((List)bonusExps.getValue()).size() - 1);
                                this.fairys.put((Integer)bonusExps.getKey(), new Pair(Math.min(newStage, ((List)bonusExps.getValue()).size()), startTime));
                                if (newStage != stage) {
                                    stage = newStage;
                                    show = true;
                                }
                            }

                            if (show) {
                                this.send(MaplePacketCreator.fairyPendantMessage((Integer)bonusExps.getKey() % 100, stage, (Integer)((List)bonusExps.getValue()).get(stage), startTime, time, inChat));
                            }
                        }
                    }

                    return;
                }

                stage = (Integer)var8.next();
                item = iv.getItem((short)(-stage));
            } while(item != null && !ii.getBonusExps(item.getItemId()).isEmpty());

            if (!this.fairys.containsKey(stage)) {
                startTime = System.currentTimeMillis();
            } else {
                startTime = (Long)((Pair)this.fairys.get(stage)).getRight();
            }

            toRemove.add(stage);
            this.send(MaplePacketCreator.fairyPendantMessage(stage % 100, 0, 0, startTime, 0L, inChat));
        }
    }

    public boolean canFairy(long now) {
        return this.lastFairyTime > 0L && this.lastFairyTime + 3600000L < now;
    }

    public void doFairy() {
        this.lastFairyTime = System.currentTimeMillis();
        if (this.getGuildId() > 0) {
            WorldGuildService.getInstance().gainGP(this.getGuildId(), 20, this.id);
            this.client.announce(UIPacket.getGPContribution(20));
        }

        ((MapleTrait)this.traits.get(MapleTraitType.will)).addExp(5, this);
    }

    public Map<Integer, Pair<Integer, Long>> getFairys() {
        return this.fairys;
    }

    public int getTeam() {
        return this.coconutteam;
    }

    public void setTeam(int v) {
        this.coconutteam = v;
    }

    public void clearLinkMid() {
        this.linkMobs.clear();
        this.dispelEffect(SecondaryStat.GuidedBullet);
        this.dispelEffect(SecondaryStat.ArcaneAim);
    }

    public int getFirstLinkMid() {
        Iterator var1 = this.linkMobs.keySet().iterator();
        if (var1.hasNext()) {
            Integer lm = (Integer)var1.next();
            return lm;
        } else {
            return 0;
        }
    }

    public Map<Integer, Integer> getAllLinkMid() {
        return this.linkMobs;
    }

    public void setLinkMid(int lm, int x) {
        this.linkMobs.put(lm, x);
    }

    public int getDamageIncrease(int lm) {
        return this.linkMobs.containsKey(lm) ? (Integer)this.linkMobs.get(lm) : 0;
    }

    public MapleExtractor getExtractor() {
        return this.extractor;
    }

    public void setExtractor(MapleExtractor me) {
        this.removeExtractor();
        this.extractor = me;
    }

    public void removeExtractor() {
        if (this.extractor != null) {
            this.map.broadcastMessage(MaplePacketCreator.removeExtractor(this.id));
            this.map.removeMapObject(this.extractor);
            this.extractor = null;
        }

    }

    public Event_PyramidSubway getPyramidSubway() {
        return this.pyramidSubway;
    }

    public void setPyramidSubway(Event_PyramidSubway ps) {
        this.pyramidSubway = ps;
    }

    public byte getSubcategory() {
        MapleJob mJob = MapleJob.getById(this.getJobWithSub());
        if (mJob != null && mJob.getSub() != this.subcategory) {
            this.subcategory = (byte)mJob.getSub();
        }

        return this.subcategory;
    }

    public void setSubcategory(int z) {
        this.subcategory = (byte)z;
    }

    public int itemQuantity(int itemid) {
        return this.getInventory(ItemConstants.getInventoryType(itemid)).countById(itemid);
    }

    public RockPaperScissors getRPS() {
        return this.rps;
    }

    public void setRPS(RockPaperScissors rps) {
        this.rps = rps;
    }

    public long getNextConsume() {
        return this.nextConsume - 1000L;
    }

    public void setNextConsume(long nc) {
        this.nextConsume = nc;
    }

    public int getRank() {
        return this.rank;
    }

    public int getRankMove() {
        return this.rankMove;
    }

    public int getJobRank() {
        return this.jobRank;
    }

    public int getJobRankMove() {
        return this.jobRankMove;
    }

    public void changeChannel(int channel) {
        ChannelServer toch = ChannelServer.getInstance(channel);
        if (channel != this.client.getChannel() && toch != null && !toch.isShutdown()) {
            this.initialSpawnPoint();
            this.changeRemoval();
            ChannelServer ch = ChannelServer.getInstance(this.client.getChannel());
            if (this.getMessenger() != null) {
                WorldMessengerService.getInstance().silentLeaveMessenger(this.getMessenger().getId(), new MapleMessengerCharacter(this));
            }

            PlayerBuffStorage.addBuffsToStorage(this.getId(), this.getAllBuffs());
            PlayerBuffStorage.addCooldownsToStorage(this.getId(), this.getCooldowns());
            CharacterTransfer ct = new CharacterTransfer(this, channel);
            World.ChannelChange_Data(ct, this.getId(), channel);
            ch.removePlayer(this);
            this.client.updateLoginState(3, this.client.getSessionIPAddress());
            this.client.announce(MaplePacketCreator.getChannelChange(this.client, ch.getPort()));
            this.saveToDB(false, false);
            this.getMap().userLeaveField(this);
            this.client.setPlayer((MapleCharacter)null);
            this.client.setReceiving(false);
        } else {
            this.client.announce(MaplePacketCreator.serverBlocked(1));
        }
    }

    public void initialSpawnPoint() {
        MaplePortal portal = this.map.findClosestSpawnpoint(this.getPosition());
        this.initialSpawnPoint = (byte)(portal == null ? 0 : portal.getId());
    }

    public void expandInventory(byte type, int amount) {
        MapleInventory inv = this.getInventory(MapleInventoryType.getByType(type));
        inv.addSlot((short)((byte)amount));
        this.client.announce(InventoryPacket.updateInventorySlotLimit(type, (byte)inv.getSlotLimit()));
    }

    public boolean allowedToTarget(MapleCharacter other) {
        return other != null && (!other.isHidden() || this.getGmLevel() >= other.getGmLevel());
    }

    public int getFollowId() {
        return this.followid;
    }

    public void setFollowId(int fi) {
        this.followid = fi;
        if (fi == 0) {
            this.followinitiator = false;
            this.followon = false;
        }

    }

    public boolean isFollowOn() {
        return this.followon;
    }

    public void setFollowOn(boolean fi) {
        this.followon = fi;
    }

    public boolean isFollowInitiator() {
        return this.followinitiator;
    }

    public void setFollowInitiator(boolean fi) {
        this.followinitiator = fi;
    }

    public void checkFollow() {
        if (this.followid > 0) {
            if (this.followon) {
                this.map.broadcastMessage(MaplePacketCreator.followEffect(this.id, 0, (Point)null));
                this.map.broadcastMessage(MaplePacketCreator.followEffect(this.followid, 0, (Point)null));
            }

            MapleCharacter tt = this.map.getPlayerObject(this.followid);
            this.client.announce(MaplePacketCreator.getFollowMessage("已停止跟隨。"));
            if (tt != null) {
                tt.setFollowId(0);
                tt.getClient().announce(MaplePacketCreator.getFollowMessage("已停止跟隨。"));
            }

            this.setFollowId(0);
        }
    }

    public int getMarriageId() {
        return this.marriageId;
    }

    public void setMarriageId(int mi) {
        this.marriageId = mi;
    }

    public int getMarriageItemId() {
        return this.marriageItemId;
    }

    public void setMarriageItemId(int mi) {
        this.marriageItemId = mi;
    }

    public boolean startPartyQuest(int questid) {
        boolean ret = false;
        MapleQuest q = MapleQuest.getInstance(questid);
        if (q != null && q.isPartyQuest()) {
            if (!this.quests.containsKey(questid) || !this.questinfo.containsKey(questid)) {
                MapleQuestStatus status = this.getQuestNAdd(q);
                status.setStatus((byte)1);
                this.updateQuest(status);
                switch (questid) {
                    case 1204:
                        this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have0=0;have1=0;have2=0;have3=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                        break;
                    case 1206:
                        this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have0=0;have1=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                        break;
                    case 1300:
                    case 1301:
                    case 1302:
                        this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have=0;rank=F;try=0;cmp=0;CR=0;VR=0;gvup=0;vic=0;lose=0;draw=0");
                        break;
                    case 1303:
                        this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have=0;have1=0;rank=F;try=0;cmp=0;CR=0;VR=0;vic=0;lose=0");
                        break;
                    default:
                        this.updateInfoQuest(questid, "min=0;sec=0;date=0000-00-00;have=0;rank=F;try=0;cmp=0;CR=0;VR=0");
                }

                ret = true;
            }

            return ret;
        } else {
            return false;
        }
    }

    public String getQuestInfo(int questid, String key) {
        return this.getOneInfo(questid, key);
    }

    public String getOneInfo(int questid, String key) {
        if (this.questinfo.containsKey(questid) && key != null && MapleQuest.getInstance(questid) != null) {
            String[] split = ((String)this.questinfo.get(questid)).split(";");
            String[] var4 = split;
            int var5 = split.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String x = var4[var6];
                String[] split2 = x.split("=");
                if (split2.length == 2 && split2[0].equals(key)) {
                    return split2[1];
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public void updateOneQuestInfo(int questid, String key, String value) {
        this.updateOneInfo(questid, key, value);
    }

    public void updateOneInfo(int questid, String key, String value) {
        this.updateOneInfo(questid, key, value, true);
    }

    public void updateOneInfo(int questid, String key, String value, boolean show) {
        if (key != null && MapleQuest.getInstance(questid) != null) {
            if (!this.questinfo.containsKey(questid)) {
                if (value == null) {
                    this.updateInfoQuest(questid, "", show);
                } else {
                    this.updateInfoQuest(questid, key + "=" + value, show);
                }

            } else {
                String[] split = ((String)this.questinfo.get(questid)).split(";");
                boolean changed = false;
                StringBuilder newQuest = new StringBuilder();
                String[] var8 = split;
                int var9 = split.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    String x = var8[var10];
                    String[] split2 = x.split("=");
                    if (split2.length == 2) {
                        if (split2[0].equals(key)) {
                            if (value != null) {
                                newQuest.append(key).append("=").append(value);
                            }

                            changed = true;
                        } else {
                            newQuest.append(x);
                        }

                        newQuest.append(";");
                    }
                }

                if (!changed && value != null) {
                    newQuest.append(key).append("=").append(value);
                }

                this.updateInfoQuest(questid, newQuest.toString().endsWith(";") ? newQuest.substring(0, newQuest.toString().length() - 1) : newQuest.toString(), show);
            }
        }
    }

    public Map<Integer, String> getWorldShareInfo() {
        return this.worldShareInfo;
    }

    public String updateWorldShareInfo(int quest, String data) {
        this.updateWorldShareInfo(quest, data, true);
        return data;
    }

    public void updateWorldShareInfo(int quest, String data, boolean sent) {
        if (data != null && !data.isEmpty()) {
            this.worldShareInfo.put(quest, data);
        } else {
            this.worldShareInfo.remove(quest);
        }

        this.changed_worldshareinfo = true;
        if (this.client != null && !ShutdownServer.getInstance().isShutdown()) {
            if (sent) {
                MessageOption option = new MessageOption();
                option.setObjectId(quest);
                option.setText(data == null ? "" : data);
                this.client.announce(CWvsContext.sendMessage(GameConstants.isWorldShareQuest(quest) ? 14 : 13, option));
            }

        }
    }

    public void updateWorldShareInfo(int quest, String key, String value) {
        this.updateWorldShareInfo(quest, key, value, true);
    }

    public void updateWorldShareInfo(int quest, String key, String value, boolean sent) {
        if (key != null) {
            if (!this.worldShareInfo.containsKey(quest)) {
                this.updateWorldShareInfo(quest, value == null ? null : key + "=" + value, sent);
            } else {
                String[] split = ((String)this.worldShareInfo.get(quest)).split(";");
                boolean b = false;
                StringBuilder sb = new StringBuilder();
                int length = split.length;

                for(int i = 0; i < length; ++i) {
                    String s3 = split[i];
                    String[] split2 = s3.split("=");
                    if (split2.length == 2) {
                        if (split2[0].equals(key)) {
                            if (value != null) {
                                sb.append(key).append("=").append(value);
                            }

                            b = true;
                        } else {
                            sb.append(s3);
                        }

                        sb.append(";");
                    }
                }

                if (!b && value != null) {
                    sb.append(key).append("=").append(value);
                }

                String data = b ? sb.substring(0, sb.toString().length() - 1) : sb.toString();
                this.updateWorldShareInfo(quest, data.isEmpty() ? null : data, sent);
            }
        }
    }

    public String getWorldShareInfo(int n, String s) {
        String info = (String)this.worldShareInfo.get(n);
        if (info == null) {
            return null;
        } else {
            String[] var4 = info.split(";");
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String value = var4[var6];
                String[] split2 = value.split("=");
                if (split2.length == 2 && split2[0].equals(s)) {
                    return split2[1];
                }
            }

            return null;
        }
    }

    public String getWorldShareInfo(int n) {
        return (String)this.worldShareInfo.get(n);
    }

    public void recalcPartyQuestRank(int questid) {
        if (MapleQuest.getInstance(questid) != null && MapleQuest.getInstance(questid).isPartyQuest()) {
            if (!this.startPartyQuest(questid)) {
                String oldRank = this.getOneInfo(questid, "rank");
                if (oldRank == null || oldRank.equals("S")) {
                    return;
                }

                String newRank;
                switch (oldRank) {
                    case "A" -> newRank = "S";
                    case "B" -> newRank = "A";
                    case "C" -> newRank = "B";
                    case "D" -> newRank = "C";
                    case "F" -> newRank = "D";
                    default -> {
                        return;
                    }
                }

                List<Pair<String, Pair<String, Integer>>> questInfo = MapleQuest.getInstance(questid).getInfoByRank(newRank);
                if (questInfo == null) {
                    return;
                }

                Iterator var13 = questInfo.iterator();

                while(var13.hasNext()) {
                    Pair<String, Pair<String, Integer>> q = (Pair)var13.next();
                    boolean found = false;
                    String val = this.getOneInfo(questid, (String)((Pair)q.right).left);
                    if (val == null) {
                        return;
                    }

                    int vall;
                    try {
                        vall = Integer.parseInt(val);
                    } catch (NumberFormatException var12) {
                        return;
                    }

                    switch ((String)q.left) {
                        case "less" -> found = vall < (Integer)((Pair)q.right).right;
                        case "more" -> found = vall > (Integer)((Pair)q.right).right;
                        case "equal" -> found = vall == (Integer)((Pair)q.right).right;
                    }

                    if (!found) {
                        return;
                    }
                }

                this.updateOneInfo(questid, "rank", newRank);
            }

        }
    }

    public void tryPartyQuest(int questid) {
        if (MapleQuest.getInstance(questid) != null && MapleQuest.getInstance(questid).isPartyQuest()) {
            try {
                this.startPartyQuest(questid);
                this.pqStartTime = System.currentTimeMillis();
                this.updateOneInfo(questid, "try", String.valueOf(Integer.parseInt(this.getOneInfo(questid, "try")) + 1));
            } catch (Exception var3) {
                System.out.println("tryPartyQuest error");
            }

        }
    }

    public void endPartyQuest(int questid) {
        if (MapleQuest.getInstance(questid) != null && MapleQuest.getInstance(questid).isPartyQuest()) {
            try {
                this.startPartyQuest(questid);
                if (this.pqStartTime > 0L) {
                    long changeTime = System.currentTimeMillis() - this.pqStartTime;
                    int mins = (int)(changeTime / 1000L / 60L);
                    int secs = (int)(changeTime / 1000L % 60L);
                    int mins2 = Integer.parseInt(this.getOneInfo(questid, "min"));
                    if (mins2 <= 0 || mins < mins2) {
                        this.updateOneInfo(questid, "min", String.valueOf(mins));
                        this.updateOneInfo(questid, "sec", String.valueOf(secs));
                        this.updateOneInfo(questid, "date", DateUtil.getCurrentDate());
                    }

                    int newCmp = Integer.parseInt(this.getOneInfo(questid, "cmp")) + 1;
                    this.updateOneInfo(questid, "cmp", String.valueOf(newCmp));
                    this.updateOneInfo(questid, "CR", String.valueOf((int)Math.ceil((double)newCmp * 100.0 / (double)Integer.parseInt(this.getOneInfo(questid, "try")))));
                    this.recalcPartyQuestRank(questid);
                    this.pqStartTime = 0L;
                }
            } catch (Exception var8) {
                System.out.println("endPartyQuest error");
            }

        }
    }

    public void havePartyQuest(int itemId) {
        int index = -1;
        short questid;
        switch (itemId) {
            case 1002571:
            case 1002572:
            case 1002573:
            case 1002574:
                questid = 1204;
                index = itemId - 1002571;
                break;
            case 1002798:
                questid = 1200;
                break;
            case 1022073:
                questid = 1202;
                break;
            case 1032060:
            case 1032061:
                questid = 1206;
                index = itemId - 1032060;
                break;
            case 1072369:
                questid = 1201;
                break;
            case 1082232:
                questid = 1203;
                break;
            case 1102226:
                questid = 1303;
                break;
            case 1102227:
                questid = 1303;
                index = 0;
                break;
            case 1122007:
                questid = 1301;
                break;
            case 1122010:
                questid = 1205;
                break;
            case 1122058:
                questid = 1302;
                break;
            case 3010018:
                questid = 1300;
                break;
            default:
                return;
        }

        if (MapleQuest.getInstance(questid) != null && MapleQuest.getInstance(questid).isPartyQuest()) {
            this.startPartyQuest(questid);
            this.updateOneInfo(questid, "have" + String.valueOf(index == -1 ? "" : index), "1");
        }
    }

    public boolean hasSummon() {
        return this.hasSummon;
    }

    public void setHasSummon(boolean summ) {
        this.hasSummon = summ;
    }

    public void removeAllTownPortal() {
        Iterator<TownPortal> iterator = this.getTownPortals().iterator();

        while(iterator.hasNext()) {
            TownPortal townPortal = (TownPortal)iterator.next();
            Iterator var3 = townPortal.getFieldMap().getCharacters().iterator();

            MapleCharacter chr;
            while(var3.hasNext()) {
                chr = (MapleCharacter)var3.next();
                townPortal.sendDestroyData(chr.getClient());
            }

            var3 = townPortal.getTownMap().getCharacters().iterator();

            while(var3.hasNext()) {
                chr = (MapleCharacter)var3.next();
                townPortal.sendDestroyData(chr.getClient());
            }

            var3 = this.getTownPortals().iterator();

            while(var3.hasNext()) {
                TownPortal destroyDoor = (TownPortal)var3.next();
                townPortal.getFieldMap().removeMapObject(destroyDoor);
                townPortal.getTownMap().removeMapObject(destroyDoor);
            }
        }

        this.clearTownPortals();
        this.townPortalLeaveTime = -1L;
    }

    public long getTownPortalLeaveTime() {
        return this.townPortalLeaveTime;
    }

    public void setTownPortalLeaveTime(long time) {
        this.townPortalLeaveTime = time;
    }

    public void checkTownPortalLeave() {
        if (this.townPortalLeaveTime > 0L) {
            if (this.townPortalLeaveTime < System.currentTimeMillis()) {
                this.removeAllTownPortal();
            }

        }
    }

    public void removeMechDoor() {
        Iterator var1 = this.getMechDoors().iterator();

        while(var1.hasNext()) {
            MechDoor destroyDoor = (MechDoor)var1.next();
            Iterator var3 = this.getMap().getCharacters().iterator();

            while(var3.hasNext()) {
                MapleCharacter chr = (MapleCharacter)var3.next();
                destroyDoor.sendDestroyData(chr.getClient());
            }

            this.getMap().removeMapObject(destroyDoor);
        }

        this.clearMechDoors();
    }

    public void changeRemoval() {
        this.changeRemoval(false);
    }

    public void changeRemoval(boolean dc) {
        if (this.getCheatTracker() != null && dc) {
            this.getCheatTracker().dispose();
        }

        this.dispelSummons();
        if (!dc) {
            this.dispelEffect(SecondaryStat.Flying);
            this.dispelEffect(SecondaryStat.RideVehicle);
            this.dispelEffect(SecondaryStat.Mechanic);
            this.dispelEffect(SecondaryStat.Regen);
            this.dispelEffect(SecondaryStat.SpiritLink);
            this.dispelEffect(SecondaryStat.NewFlying);
        }

        if (this.getPyramidSubway() != null) {
            this.getPyramidSubway().dispose(this);
        }

        if (this.playerShop != null && !dc) {
            this.playerShop.removeVisitor(this);
            if (this.playerShop.isOwner(this)) {
                this.playerShop.setOpen(true);
            }
        }

        if (!this.getTownPortals().isEmpty()) {
            this.removeAllTownPortal();
        }

        if (!this.getMechDoors().isEmpty()) {
            this.removeMechDoor();
        }

        if (this.map != null && this.summonedFamiliar != null) {
            this.map.disappearMapObject(this.summonedFamiliar);
        }

        if (this.eventInstance != null) {
            this.eventInstance.getHooks().playerDisconnected(this);
        }

    }

    public void updateTick(int newTick) {
        this.anticheat.updateTick(newTick);
    }

    public long getCurrenttime() {
        return this.currenttime;
    }

    public void setCurrenttime(long currenttime) {
        this.currenttime = currenttime;
    }

    public long getDeadtime() {
        return this.deadtime;
    }

    public void setDeadtime(long deadtime) {
        this.deadtime = deadtime;
    }

    public long getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(long lasttime) {
        this.lasttime = lasttime;
    }

    public String getTeleportName() {
        return this.teleportname;
    }

    public void setTeleportName(String tname) {
        this.teleportname = tname;
    }

    public int getGachExp() {
        return gachexp;
    }

    public void setGachExp(int ge) {
        gachexp = ge;
    }

    public boolean isInBlockedMap() {
        if (this.isAlive() && this.getPyramidSubway() == null && !this.checkEvent() && !this.getMap().getEMByMap(this)) {
            return (this.getMapId() < 680000210 || this.getMapId() > 680000502) && this.getMapId() / 10000 != 92507 && this.getMapId() != 993073000 ? ServerConstants.isBlockedMapFM(this.getMapId()) : true;
        } else {
            return true;
        }
    }

    public boolean isInTownMap() {
        if (!this.hasBlockedInventory() && this.getMap().isTown() && !FieldLimitType.TELEPORTITEMLIMIT.check(this.getMap().getFieldLimit()) && !this.checkEvent()) {
            return !ServerConstants.isBlockedMapFM(this.getMapId());
        } else {
            return false;
        }
    }

    public boolean hasBlockedInventory() {
        return !this.isAlive() || this.getTrade() != null || this.getConversation() > 0 || this.getDirection() >= 0 || this.getPlayerShop() != null || this.map == null;
    }

    public int getChallenge() {
        return this.challenge;
    }

    public void setChallenge(int c) {
        this.challenge = c;
    }

    public short getFatigue() {
        return this.fatigue;
    }

    public void setFatigue(int j) {
        this.fatigue = (short)Math.max(0, j);
        this.updateSingleStat(MapleStat.FATIGUE, (long)this.fatigue);
    }

    public void fakeRelog() {
        MapleMap mapp = this.getMap();
        this.stats.recalcLocalStats(this);
        mapp.userLeaveField(this);
        mapp.userEnterField(this);
        this.client.announce(MaplePacketCreator.getWarpToMap(this, this.map, 0, false));
        this.client.announce(MaplePacketCreator.serverNotice(5, "刷新人數據完成..."));
    }

    public boolean canSummon() {
        return this.canSummon(5000);
    }

    public boolean canSummon(int checkTime) {
        if (this.lastSummonTime <= 0L) {
            this.prepareSummonTime();
        }

        return this.lastSummonTime + (long)checkTime < System.currentTimeMillis();
    }

    private void prepareSummonTime() {
        this.lastSummonTime = System.currentTimeMillis();
    }

    public int getIntNoRecord(int questID) {
        MapleQuestStatus stat = this.getQuestNoAdd(MapleQuest.getInstance(questID));
        return stat != null && stat.getCustomData() != null ? Integer.parseInt(stat.getCustomData()) : 0;
    }

    public int getIntRecord(int questID) {
        MapleQuestStatus stat = this.getQuestNAdd(MapleQuest.getInstance(questID));
        if (stat.getCustomData() == null) {
            stat.setCustomData("0");
        }

        return Integer.parseInt(stat.getCustomData());
    }

    public long getLongRecord(int questID) {
        MapleQuestStatus stat = this.getQuestNAdd(MapleQuest.getInstance(questID));
        if (stat.getCustomData() == null) {
            stat.setCustomData("0");
        }

        return Long.parseLong(stat.getCustomData());
    }

    public void setLongRecord(int questID, long record) {
        MapleQuestStatus stat = this.getQuestNAdd(MapleQuest.getInstance(questID));
        if (stat.getCustomData() == null) {
            stat.setCustomData("0");
        }

        stat.setCustomData(String.valueOf(record));
    }

    public void updatePetAuto() {
        this.client.announce(MaplePacketCreator.petAutoHP(this.getIntRecord(122221)));
        this.client.announce(MaplePacketCreator.petAutoMP(this.getIntRecord(122223)));
        this.client.announce(MaplePacketCreator.petAutoBuff(this.getIntRecord(122224)));
    }

    public void setChangeTime() {
        this.getCheatTracker().resetInMapTime();
    }

    public Map<ReportType, Integer> getReports() {
        return this.reports;
    }

    public void addReport(ReportType type) {
        Integer value = (Integer)this.reports.get(type);
        this.reports.put(type, value == null ? 1 : value + 1);
        this.changed_reports = true;
    }

    public void clearReports(ReportType type) {
        this.reports.remove(type);
        this.changed_reports = true;
    }

    public void clearReports() {
        this.reports.clear();
        this.changed_reports = true;
    }

    public int getReportPoints() {
        int ret = 0;

        Integer entry;
        for(Iterator var2 = this.reports.values().iterator(); var2.hasNext(); ret += entry) {
            entry = (Integer)var2.next();
        }

        return ret;
    }

    public String getReportSummary() {
        StringBuilder ret = new StringBuilder();
        List<Pair<ReportType, Integer>> offenseList = new ArrayList();
        Iterator var3 = this.reports.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<ReportType, Integer> entry = (Map.Entry)var3.next();
            offenseList.add(new Pair((ReportType)entry.getKey(), (Integer)entry.getValue()));
        }

        offenseList.sort((o1, o2) -> {
            int thisVal = (Integer)o1.getRight();
            int anotherVal = (Integer)o2.getRight();
            return thisVal < anotherVal ? 1 : (thisVal == anotherVal ? 0 : -1);
        });
        var3 = offenseList.iterator();

        while(var3.hasNext()) {
            Pair<ReportType, Integer> anOffenseList = (Pair)var3.next();
            ret.append(StringUtil.makeEnumHumanReadable(((ReportType)anOffenseList.left).name()));
            ret.append(": ");
            ret.append(anOffenseList.right);
            ret.append(" ");
        }

        return ret.toString();
    }

    public short getScrolledPosition() {
        return this.scrolledPosition;
    }

    public void setScrolledPosition(short s) {
        this.scrolledPosition = s;
    }

    public MapleTrait getTrait(MapleTraitType t) {
        return (MapleTrait)this.traits.get(t);
    }

    public void forceCompleteQuest(int id) {
        MapleQuest.getInstance(id).forceComplete(this, 9270035);
    }

    public Map<Byte, List<Item>> getAllExtendedSlots() {
        return this.extendedSlots;
    }

    public List<Item> getExtendedSlots(byte type) {
        return (List)this.extendedSlots.get(type);
    }

    public int getExtendedItemId(byte type, int slot) {
        if (this.extendedSlots != null && !this.extendedSlots.isEmpty() && this.extendedSlots.get(type) != null) {
            Iterator var3 = ((List)this.extendedSlots.get(type)).iterator();

            Item itm;
            do {
                if (!var3.hasNext()) {
                    return -1;
                }

                itm = (Item)var3.next();
            } while(itm.getExtendSlot() != slot);

            return itm.getItemId();
        } else {
            return -1;
        }
    }

    public MapleAndroid getAndroid() {
        return this.android;
    }

    public void setAndroid(MapleAndroid a) {
        if (this.checkHearts()) {
            this.android = a;
            if (this.map != null && a != null) {
                this.map.broadcastMessage(AndroidPacket.spawnAndroid(this, a));
                this.android.showEmotion(this, "hello");
            }
        }

    }

    public void removeAndroid() {
        if (this.map != null) {
            if (this.android != null) {
                this.android.showEmotion(this, "bye");
            }

            this.map.broadcastMessage(AndroidPacket.deactivateAndroid(this.id));
        }

        if (this.android != null) {
            this.android.saveToDb();
        }

        this.android = null;
    }

    public void updateAndroidLook() {
        if (this.map != null) {
            this.map.broadcastMessage(AndroidPacket.updateAndroidLook(this));
        }

    }

    public void updateAndroidEquip(boolean unequip, Pair<Integer, Integer> item) {
        if (this.map != null) {
            this.map.broadcastMessage(AndroidPacket.updateAndroidEquip(this.getId(), unequip, item));
        }

    }

    public boolean checkHearts() {
        return this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-33) != null;
    }

    public List<MapleShopItem> getRebuy() {
        return this.rebuy;
    }

    public List<MonsterFamiliar> getFamiliars() {
        this.changed_familiars = true;
        return this.familiars;
    }

    public MonsterFamiliar getSummonedFamiliar() {
        return this.summonedFamiliar;
    }

    public MonsterFamiliar removeFamiliarsInfo(int n) {
        MonsterFamiliar mf = (MonsterFamiliar)this.familiars.stream().filter((f) -> {
            return f.getId() == n;
        }).findAny().orElse(null);
        if (mf != null) {
            this.familiars.remove(mf);
        }

        return mf;
    }

    public void addFamiliarsInfo(MonsterFamiliar monsterFamiliar) {
        this.changed_familiars = true;
        this.familiars.add(monsterFamiliar);
    }

    public void updateFamiliar(MonsterFamiliar familiar) {
        this.setFamiliarsChanged(true);
        if (this.client != null) {
            this.client.write(OverseasPacket.extraEquipResult(ExtraEquipResult.updateFamiliarInfo(this.id, this.familiars.size(), this.familiars.contains(familiar) ? Collections.singletonMap(this.familiars.indexOf(familiar), familiar) : Collections.emptyMap())));
        }

    }

    public void updateFamiliars() {
        this.setFamiliarsChanged(true);
        if (this.client != null) {
            MapleClient var10000 = this.client;
            int var10001 = this.id;
            int var10002 = this.familiars.size();
            Stream var10003 = this.familiars.stream();
            List var10004 = this.familiars;
            Objects.requireNonNull(var10004);
            var10000.write(OverseasPacket.extraEquipResult(ExtraEquipResult.updateFamiliarInfo(var10001, var10002, (Map)var10003.collect(Collectors.toMap(var10004::indexOf, Function.identity())))));
        }

    }

    public void spawnFamiliar(MonsterFamiliar monsterFamiliar) {
        boolean old = false;
        if (this.summonedFamiliar != null && this.summonedFamiliar != monsterFamiliar) {
            this.summonedFamiliar.setSummoned(false);
            if (this.map != null) {
                this.map.disappearMapObject(this.summonedFamiliar);
            }

            old = true;
        }

        this.summonedFamiliar = monsterFamiliar;
        this.summonedFamiliar.setSummoned(true);
        this.summonedFamiliar.initPad();
        this.summonedFamiliar.setStance(0);
        this.summonedFamiliar.setPosition(this.getPosition());
        this.summonedFamiliar.setCurrentFh(this.getCurrentFH());
        this.setFamiliarsChanged(true);
        if (this.map != null) {
            this.write(OverseasPacket.extraEquipResult(ExtraEquipResult.spawnFamiliar(this.accountid, this.id, old, monsterFamiliar, this.getPosition(), true)));
            this.map.spawnMapObject(this.getId(), this.summonedFamiliar, OverseasPacket.extraEquipResult(ExtraEquipResult.spawnFamiliar(this.accountid, this.id, old, monsterFamiliar, this.getPosition(), false)).getData());
        }

    }

    public void initFamiliar(MonsterFamiliar cbr) {
        if (this.summonedFamiliar != null) {
            this.summonedFamiliar.setSummoned(false);
        }

        this.summonedFamiliar = cbr;
        this.summonedFamiliar.setSummoned(true);
    }

    public void removeFamiliar() {
        if (this.summonedFamiliar != null) {
            this.summonedFamiliar.setSummoned(false);
            if (this.map != null) {
                this.map.disappearMapObject(this.summonedFamiliar);
            }
        }

        this.summonedFamiliar = null;
        this.write(OverseasPacket.extraEquipResult(ExtraEquipResult.removeFamiliar(this.getId(), true)));
    }

    public MapleImp[] getImps() {
        return this.imps;
    }

    public void sendImp() {
        for(int i = 0; i < this.imps.length; ++i) {
            if (this.imps[i] != null) {
                this.client.announce(MaplePacketCreator.updateImp(this.imps[i], ImpFlag.SUMMONED.getValue(), i, true));
            }
        }

    }

    public int getBattlePoints() {
        return this.pvpPoints;
    }

    public void setBattlePoints(int p) {
        if (p != this.pvpPoints) {
            this.client.announce(UIPacket.getBPMsg(p - this.pvpPoints));
            this.updateSingleStat(MapleStat.BATTLE_POINTS, (long)p);
        }

        this.pvpPoints = p;
    }

    public int getTotalBattleExp() {
        return this.pvpExp;
    }

    public void setTotalBattleExp(int p) {
        int previous = this.pvpExp;
        this.pvpExp = p;
        if (p != previous) {
            this.stats.recalcPVPRank(this);
            this.updateSingleStat(MapleStat.BATTLE_EXP, (long)this.stats.pvpExp);
            this.updateSingleStat(MapleStat.BATTLE_RANK, (long)this.stats.pvpRank);
        }

    }

    public void changeTeam(int newTeam) {
        this.coconutteam = newTeam;
    }

    public boolean inPVP() {
        return this.eventInstance != null && this.eventInstance.getScriptName().startsWith("PVP") && ServerConfig.CHANNEL_OPENPVP;
    }

    public void applyIceGage(int x) {
        this.updateSingleStat(MapleStat.ICE_GAGE, (long)x);
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getPosition().x - 25, this.getPosition().y - 37, 50, 75);
    }

    public void handleForceGain(int forceGain) {
        if (this.isSkillCooling(31121054)) {
            this.allreadyForceGet += forceGain;
            if (this.allreadyForceGet >= 50) {
                this.allreadyForceGet -= 50;
                this.reduceSkillCooldown(31121054, 3000);
            }
        } else {
            this.allreadyForceGet = 0;
        }

    }

    public void handleForceGain(int moboid, int skillid, int extraForce) {
        if (SkillConstants.isForceIncrease(skillid) || extraForce > 0) {
            int forceColor = 3;
            int forceGain = 1;
            if (this.getLevel() >= 30 && this.getLevel() < 70) {
                forceGain = 2;
            } else if (this.getLevel() >= 70 && this.getLevel() < 120) {
                forceGain = 3;
            } else if (this.getLevel() >= 120) {
                forceGain = 4;
            }

            MapleMonster mob = this.getMap().getMonsterByOid(moboid);
            if (mob != null && mob.getStats().isBoss()) {
                forceColor = 10;
            } else if (skillid != 31000004 && skillid != 31001006 && skillid != 31001007 && skillid != 31001008) {
                if (skillid == 30010111) {
                    forceColor = 5;
                }
            } else {
                int skilllevel = this.getSkillLevel(31110009);
                if (skilllevel > 0) {
                    MapleStatEffect effect = SkillFactory.getSkill(31110009).getEffect(skilllevel);
                    if (Randomizer.nextInt(100) > effect.getProp()) {
                        return;
                    }
                }
            }

            forceGain = extraForce > 0 ? extraForce : forceGain;
            this.addDemonMp(forceGain);
            if (this.isSkillCooling(31121054)) {
                this.allreadyForceGet += forceGain;
                if (this.allreadyForceGet >= 50) {
                    this.allreadyForceGet = 0;
                    this.reduceSkillCooldown(31121054, 3000);
                }
            } else {
                this.allreadyForceGet = 0;
            }

            MapleForceAtom force = new MapleForceAtom();
            force.setForceType(MapleForceType.惡魔DF.ordinal());
            force.setFromMob(moboid > 0);
            force.setFromMobOid(moboid);
            force.setInfo(MapleForceFactory.getInstance().getForceInfo_惡魔DF(this, 1, forceColor));
            this.send(ForcePacket.forceAtomCreate(force));
        }
    }

    public int getCardStack() {
        return this.specialStats.getCardStack();
    }

    public void setCardStack(int amount) {
        this.specialStats.setCardStack(amount);
    }

    public int getCarteByJob() {
        if (this.getSkillLevel(20031210) > 0) {
            return 40;
        } else {
            return this.getSkillLevel(20031209) > 0 ? 20 : 0;
        }
    }

    public void handleCarteGain(int moid, boolean is5th) {
        if (is5th) {
            Skill skill = SkillFactory.getSkill(400041010);
            if (skill != null && this.getSkillLevel(400041009) > 0 && skill.getEffect(this.getSkillLevel(400041009)) != null) {
                this.send(ForcePacket.forceAtomCreate(MapleForceFactory.getInstance().getMapleForce(this, this.getSkillEffect(400041009), 0)));
            }
        } else {
            int[] arrn2 = new int[]{24120002, 24100003};
            int[] var4 = arrn2;
            int var5 = arrn2.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                int skillid = var4[var6];
                Skill skill = SkillFactory.getSkill(skillid);
                if (skill != null && this.getSkillLevel(skill) > 0) {
                    MapleStatEffect effect = skill.getEffect(this.getSkillLevel(skill));
                    if (effect.makeChanceResult() && Randomizer.nextInt(100) <= this.getStat().getCriticalRate()) {
                        this.send(ForcePacket.forceAtomCreate(MapleForceFactory.getInstance().getMapleForce(this, this.getSkillEffect(400041010), 0, Collections.singletonList(moid))));
                        if (this.getCardStack() < this.getCarteByJob()) {
                            this.incJudgementStack();
                            this.updateJudgementStack();
                            return;
                        }
                    }
                }
            }
        }

    }

    public int getDecorate() {
        return this.decorate;
    }

    public void setDecorate(int id) {
        if ((id < 1012276 || id > 1012280) && id != 1012361 && id != 1012363 && id != 1012693) {
            this.decorate = 0;
        } else {
            this.decorate = id;
        }

    }

    public int getBossLog(String boss) {
        return this.getBossLog(boss, 0);
    }

    public int getBossLog(String boss, int type) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            int var14;
            label68: {
                try {
                    int count = 0;
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM bosslog WHERE characterid = ? AND bossid = ?");
                    ps.setInt(1, this.id);
                    ps.setString(2, boss);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        count = rs.getInt("count");
                        if (count < 0) {
                            var14 = count;
                            break label68;
                        }

                        Timestamp bossTime = rs.getTimestamp("time");
                        rs.close();
                        ps.close();
                        if (type == 0) {
                            Calendar sqlcal = Calendar.getInstance();
                            if (bossTime != null) {
                                sqlcal.setTimeInMillis(bossTime.getTime());
                            }

                            if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                                count = 0;
                                ps = con.prepareStatement("UPDATE bosslog SET count = 0, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND bossid = ?");
                                ps.setInt(1, this.id);
                                ps.setString(2, boss);
                                ps.executeUpdate();
                            }
                        }
                    } else {
                        PreparedStatement psu = con.prepareStatement("INSERT INTO bosslog (characterid, bossid, count, type) VALUES (?, ?, ?, ?)");
                        psu.setInt(1, this.id);
                        psu.setString(2, boss);
                        psu.setInt(3, 0);
                        psu.setInt(4, type);
                        psu.executeUpdate();
                        psu.close();
                    }

                    rs.close();
                    ps.close();
                    var14 = count;
                } catch (Throwable var10) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (con != null) {
                    con.close();
                }

                return var14;
            }

            if (con != null) {
                con.close();
            }

            return var14;
        } catch (Exception var11) {
            Exception Ex = var11;
            log.error("獲取BOSS挑戰次數.", Ex);
            return -1;
        }
    }

    public void setBossLog(String boss) {
        this.setBossLog(boss, 0);
    }

    public void setBossLog(String boss, int type) {
        this.setBossLog(boss, type, 1);
    }

    public void setBossLog(String boss, int type, int count) {
        int bossCount = this.getBossLog(boss, type);

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE bosslog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND bossid = ?");
                ps.setInt(1, bossCount + count);
                ps.setInt(2, type);
                ps.setInt(3, this.id);
                ps.setString(4, boss);
                ps.executeUpdate();
                ps.close();
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var10) {
            Exception Ex = var10;
            log.error("Error while set bosslog.", Ex);
        }

    }

    public void resetBossLog(String boss) {
        this.resetBossLog(boss, 0);
    }

    public void resetBossLog(String boss, int type) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE bosslog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND bossid = ?");
                ps.setInt(1, 0);
                ps.setInt(2, type);
                ps.setInt(3, this.id);
                ps.setString(4, boss);
                ps.executeUpdate();
                ps.close();
            } catch (Throwable var7) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var8) {
            Exception Ex = var8;
            log.error("重置BOSS次數失敗.", Ex);
        }

    }

    public int getBossLogAcc(String boss) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            int var13;
            label65: {
                try {
                    int count = 0;
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM bosslog WHERE accountid = ? AND bossid = ?");
                    ps.setInt(1, this.accountid);
                    ps.setString(2, boss);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        count = rs.getInt("count");
                        if (count < 0) {
                            var13 = count;
                            break label65;
                        }

                        Timestamp bossTime = rs.getTimestamp("time");
                        rs.close();
                        ps.close();
                        Calendar sqlcal = Calendar.getInstance();
                        if (bossTime != null) {
                            sqlcal.setTimeInMillis(bossTime.getTime());
                        }

                        if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                            count = 0;
                            ps = con.prepareStatement("UPDATE bosslog SET count = 0, time = CURRENT_TIMESTAMP() WHERE accountid = ? AND bossid = ?");
                            ps.setInt(1, this.accountid);
                            ps.setString(2, boss);
                            ps.executeUpdate();
                        }
                    } else {
                        PreparedStatement psu = con.prepareStatement("INSERT INTO bosslog (accountid, characterid, bossid, count) VALUES (?, ?, ?, ?)");
                        psu.setInt(1, this.accountid);
                        psu.setInt(2, 0);
                        psu.setString(3, boss);
                        psu.setInt(4, 0);
                        psu.executeUpdate();
                        psu.close();
                    }

                    rs.close();
                    ps.close();
                    var13 = count;
                } catch (Throwable var9) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (con != null) {
                    con.close();
                }

                return var13;
            }

            if (con != null) {
                con.close();
            }

            return var13;
        } catch (Exception var10) {
            Exception Ex = var10;
            log.error("獲取BOSS挑戰次數.", Ex);
            return -1;
        }
    }

    public void setBossLogAcc(String bossid) {
        this.setBossLogAcc(bossid, 0);
    }

    public void setBossLogAcc(String bossid, int bossCount) {
        bossCount += this.getBossLogAcc(bossid);

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE bosslog SET count = ?, characterid = ?, time = CURRENT_TIMESTAMP() WHERE accountid = ? AND bossid = ?");
                ps.setInt(1, bossCount + 1);
                ps.setInt(2, this.id);
                ps.setInt(3, this.accountid);
                ps.setString(4, bossid);
                ps.executeUpdate();
                ps.close();
            } catch (Throwable var7) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var8) {
            Exception Ex = var8;
            log.error("Error while set bosslog.", Ex);
        }

    }

    public int getEventLogForDay(String event) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            int var17;
            label110: {
                int var18;
                try {
                    int count = 0;
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM eventforday WHERE eventid = ?");
                    ps.setString(1, event);
                    ResultSet rs = ps.executeQuery();

                    label112: {
                        try {
                            if (rs.next()) {
                                count = rs.getInt("count");
                                if (count < 0) {
                                    var18 = count;
                                    break label112;
                                }

                                Timestamp eventTime = rs.getTimestamp("time");
                                rs.close();
                                ps.close();
                                Calendar sqlcal = Calendar.getInstance();
                                if (eventTime != null) {
                                    sqlcal.setTimeInMillis(eventTime.getTime());
                                }

                                if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                                    count = 0;
                                    ps = con.prepareStatement("UPDATE eventforday SET count = 0, time = CURRENT_TIMESTAMP() WHERE eventid = ?");
                                    ps.setString(1, event);
                                    ps.executeUpdate();
                                }
                            } else {
                                PreparedStatement psu = con.prepareStatement("INSERT INTO eventforday (eventid, count) VALUES (?, ?)");

                                try {
                                    psu.setString(1, event);
                                    psu.setInt(2, 0);
                                    psu.executeUpdate();
                                } catch (Throwable var12) {
                                    if (psu != null) {
                                        try {
                                            psu.close();
                                        } catch (Throwable var11) {
                                            var12.addSuppressed(var11);
                                        }
                                    }

                                    throw var12;
                                }

                                if (psu != null) {
                                    psu.close();
                                }
                            }
                        } catch (Throwable var13) {
                            if (rs != null) {
                                try {
                                    rs.close();
                                } catch (Throwable var10) {
                                    var13.addSuppressed(var10);
                                }
                            }

                            throw var13;
                        }

                        if (rs != null) {
                            rs.close();
                        }

                        ps.close();
                        var17 = count;
                        break label110;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var14) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var9) {
                            var14.addSuppressed(var9);
                        }
                    }

                    throw var14;
                }

                if (con != null) {
                    con.close();
                }

                return var18;
            }

            if (con != null) {
                con.close();
            }

            return var17;
        } catch (Exception var15) {
            Exception Ex = var15;
            log.error("Error while get EventLogForDay.", Ex);
            return -1;
        }
    }

    public void setEventLogForDay(String eventid) {
        this.setEventLogForDay(eventid, 0);
    }

    public void setEventLogForDay(String eventid, int eventCount) {
        eventCount += this.getEventLogForDay(eventid);

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE eventforday SET count = ?, time = CURRENT_TIMESTAMP() WHERE eventid = ?");

                try {
                    ps.setInt(1, eventCount + 1);
                    ps.setString(2, eventid);
                    ps.executeUpdate();
                } catch (Throwable var9) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var10) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var11) {
            Exception Ex = var11;
            log.error("Error while set EventLogForDay.", Ex);
        }

    }

    public void resetEventLogForDay(String eventid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE eventforday SET count = ?, time = CURRENT_TIMESTAMP() WHERE eventid = ?");

                try {
                    ps.setInt(1, 0);
                    ps.setString(2, eventid);
                    ps.executeUpdate();
                } catch (Throwable var8) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var10) {
            Exception Ex = var10;
            log.error("Error while reset EventLogForDay.", Ex);
        }

    }

    public void updateCash() {
        this.client.announce(MaplePacketCreator.showCharCash(this));
    }

    public short getSpace(int type) {
        return this.getInventory(MapleInventoryType.getByType((byte)type)).getNumFreeSlot();
    }

    public boolean haveSpace(int type) {
        short slot = this.getInventory(MapleInventoryType.getByType((byte)type)).getNextFreeSlot();
        return slot != -1;
    }

    public boolean haveSpaceForId(int itemid) {
        short slot = this.getInventory(ItemConstants.getInventoryType(itemid)).getNextFreeSlot();
        return slot != -1;
    }

    public boolean canHold() {
        for(int i = 1; i <= 6; ++i) {
            if (this.getInventory(MapleInventoryType.getByType((byte)i)).getNextFreeSlot() <= -1) {
                return false;
            }
        }

        return true;
    }

    public boolean canHoldSlots(int slot) {
        for(int i = 1; i <= 6; ++i) {
            if (this.getInventory(MapleInventoryType.getByType((byte)i)).isFull(slot)) {
                return false;
            }
        }

        return true;
    }

    public boolean canHold(int itemid) {
        return this.getInventory(ItemConstants.getInventoryType(itemid)).getNextFreeSlot() > -1;
    }

    public long getMerchantMeso() {
        long mesos = 0L;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM hiredmerch WHERE characterid = ?");
                ps.setInt(1, this.id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    mesos = rs.getLong("Mesos");
                }

                rs.close();
                ps.close();
            } catch (Throwable var7) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var8) {
            SQLException se = var8;
            log.error("獲取僱傭商店楓幣發生錯誤", se);
        }

        return mesos;
    }

    public void autoban(String reason, int greason) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5) + 3, cal.get(11), cal.get(12));
        Timestamp TS = new Timestamp(cal.getTimeInMillis());

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE accounts SET banreason = ?, tempban = ?, greason = ? WHERE id = ?");
                ps.setString(1, reason);
                ps.setTimestamp(2, TS);
                ps.setInt(3, greason);
                ps.setInt(4, this.accountid);
                ps.executeUpdate();
                ps.close();
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var10) {
            SQLException e = var10;
            log.error("Error while autoban" + String.valueOf(e));
        }

    }

    public boolean isBanned() {
        return this.isbanned;
    }

    public void sendPolice(int greason, String reason, int duration) {
        this.isbanned = true;
        WorldTimer.getInstance().schedule(() -> {
            this.client.disconnect(true, false);
        }, (long)duration);
    }

    public void sendPolice(String text) {
        this.client.announce(MaplePacketCreator.sendPolice(text));
        this.isbanned = true;
        WorldTimer.getInstance().schedule(() -> {
            this.client.disconnect(true, false);
            if (this.client.getSession().isActive()) {
                this.client.getSession().close();
            }

        }, 6000L);
    }

    public Timestamp getChrCreated() {
        if (this.createDate != null) {
            return this.createDate;
        } else {
            try {
                Connection con = DatabaseConnectionEx.getInstance().getConnection();

                Timestamp var4;
                label52: {
                    try {
                        PreparedStatement ps = con.prepareStatement("SELECT createdate FROM characters WHERE id = ?");
                        ps.setInt(1, this.getId());
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            this.createDate = rs.getTimestamp("createdate");
                            rs.close();
                            ps.close();
                            var4 = this.createDate;
                            break label52;
                        }

                        rs.close();
                        ps.close();
                        var4 = null;
                    } catch (Throwable var6) {
                        if (con != null) {
                            try {
                                con.close();
                            } catch (Throwable var5) {
                                var6.addSuppressed(var5);
                            }
                        }

                        throw var6;
                    }

                    if (con != null) {
                        con.close();
                    }

                    return var4;
                }

                if (con != null) {
                    con.close();
                }

                return var4;
            } catch (SQLException var7) {
                SQLException e = var7;
                log.error("獲取角色創建日期出錯", e);
                return new Timestamp(-1L);
            }
        }
    }

    public boolean isInJailMap() {
        return this.getMapId() == 993073000 && !this.isGm();
    }

    public int getWarning() {
        return this.warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    public void gainWarning(boolean warningEnabled) {
        ++this.warning;
        WorldBroadcastService var10000 = WorldBroadcastService.getInstance();
        UserChatMessageType var10001 = UserChatMessageType.淺紫;
        String var10002 = this.getName();
        var10000.broadcastGMMessage(MaplePacketCreator.spouseMessage(var10001, "[GM消息] 截至目前玩家: " + var10002 + " (等級 " + this.getLevel() + ") 該用戶已被警告: " + this.warning + " 次！"));
        if (warningEnabled) {
            if (this.warning == 1) {
                this.dropMessage(5, "這是你的第一次警告！請注意在遊戲中勿使用非法程序！");
            } else if (this.warning == 2) {
                this.dropMessage(5, "警告現在是第 " + this.warning + " 次。如果你再得到一次警告就會封號處理！");
            } else if (this.warning >= 3) {
                this.ban(this.getName() + " 由於警告次數超過: " + this.warning + " 次，系統對其封號處理！", false, true, false);
                var10000 = WorldBroadcastService.getInstance();
                var10002 = this.getName();
                var10000.broadcastMessage(MaplePacketCreator.serverNotice(0, "[系統封號] 玩家 " + var10002 + " (等級 " + this.getLevel() + ") 由於警告次數過多，系統對其封號處理！"));
            }
        }

    }

    public int getBeans() {
        return this.beans;
    }

    public void setBeans(int i) {
        this.beans = i;
    }

    public void gainBeans(int i, boolean show) {
        this.beans += i;
        if (show && i != 0) {
            this.dropMessage(-1, "您" + (i > 0 ? "獲得了 " : "消耗了 ") + Math.abs(i) + " 個豆豆.");
        }

    }

    public int teachSkill(int skillId, int toChrId, boolean notime) {
        int[] tSkills = SkillConstants.getTeamTeachSkills(skillId);
        List<Integer> linkSkills = new LinkedList();
        int skill;
        if (tSkills == null) {
            linkSkills.add(skillId);
        } else {
            int[] var6 = tSkills;
            int var7 = tSkills.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                skill = var6[var8];
                linkSkills.add(skill);
            }
        }

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            byte var50;
            label355: {
                byte var52;
                label356: {
                    byte var56;
                    try {
                        Calendar date = Calendar.getInstance();
                        date.set(11, 0);
                        date.set(12, 0);
                        date.set(13, 0);
                        date.set(14, 0);
                        Iterator var49 = linkSkills.iterator();

                        while(true) {
                            if (!var49.hasNext()) {
                                var50 = 1;
                                break label355;
                            }

                            skill = (Integer)var49.next();
                            if (this.sonOfLinkedSkills.containsKey(skill)) {
                                int cid = (Integer)((Pair)this.sonOfLinkedSkills.get(skill)).getLeft();
                                SkillEntry se = (SkillEntry)((Pair)this.sonOfLinkedSkills.get(skill)).getRight();
                                if (!notime) {
                                    if (se.expiration > 0L && se.expiration >= date.getTimeInMillis()) {
                                        if (se.teachTimes >= ServerConfig.TeachCost.size()) {
                                            var52 = -1;
                                            break label356;
                                        }
                                    } else {
                                        se.expiration = System.currentTimeMillis();
                                        se.teachTimes = 0;
                                    }

                                    long cost = (long)(Integer)ServerConfig.TeachCost.get(se.teachTimes);
                                    if (this.getMeso() < cost) {
                                        var56 = -1;
                                        break;
                                    }

                                    if (cost > 0L) {
                                        this.gainMeso(-cost, true);
                                    }
                                }

                                if (tSkills == null || cid == this.id) {
                                    int tSkill = SkillConstants.getTeamTeachSkillId(skill);
                                    PreparedStatement ps;
                                    if (tSkill > 1) {
                                        List<Integer> CIDs = new LinkedList();
                                        ps = con.prepareStatement("SELECT characterid FROM skills WHERE skillid = ? AND teachId = ?");

                                        try {
                                            ps.setInt(1, skill);
                                            ps.setInt(2, cid);
                                            ResultSet rs = ps.executeQuery();

                                            try {
                                                while(rs.next()) {
                                                    CIDs.add(rs.getInt("characterid"));
                                                }
                                            } catch (Throwable var39) {
                                                if (rs != null) {
                                                    try {
                                                        rs.close();
                                                    } catch (Throwable var33) {
                                                        var39.addSuppressed(var33);
                                                    }
                                                }

                                                throw var39;
                                            }

                                            if (rs != null) {
                                                rs.close();
                                            }
                                        } catch (Throwable var40) {
                                            if (ps != null) {
                                                try {
                                                    ps.close();
                                                } catch (Throwable var25) {
                                                    var40.addSuppressed(var25);
                                                }
                                            }

                                            throw var40;
                                        }

                                        if (ps != null) {
                                            ps.close();
                                        }

                                        List<Pair<Integer, Integer>> infos = new LinkedList();
                                        Iterator var57 = CIDs.iterator();

                                        while(var57.hasNext()) {
                                            int ID = (Integer)var57.next();
                                            ps = con.prepareStatement("SELECT skilllevel FROM skills WHERE skillid = ? AND characterid = ?");

                                            try {
                                                ps.setInt(1, tSkill);
                                                ps.setInt(2, ID);
                                                ResultSet rs = ps.executeQuery();

                                                try {
                                                    if (rs.next()) {
                                                        PreparedStatement pse = con.prepareStatement("SELECT id FROM characters WHERE id = ? AND accountid = ? AND world = ?");

                                                        try {
                                                            pse.setInt(1, ID);
                                                            pse.setInt(2, this.accountid);
                                                            pse.setInt(3, this.world);
                                                            ResultSet rse = pse.executeQuery();

                                                            try {
                                                                if (rse.next()) {
                                                                    infos.add(new Pair(ID, rs.getInt("skilllevel")));
                                                                }
                                                            } catch (Throwable var34) {
                                                                if (rse != null) {
                                                                    try {
                                                                        rse.close();
                                                                    } catch (Throwable var31) {
                                                                        var34.addSuppressed(var31);
                                                                    }
                                                                }

                                                                throw var34;
                                                            }

                                                            if (rse != null) {
                                                                rse.close();
                                                            }
                                                        } catch (Throwable var41) {
                                                            if (pse != null) {
                                                                try {
                                                                    pse.close();
                                                                } catch (Throwable var30) {
                                                                    var41.addSuppressed(var30);
                                                                }
                                                            }

                                                            throw var41;
                                                        }

                                                        if (pse != null) {
                                                            pse.close();
                                                        }
                                                    }
                                                } catch (Throwable var42) {
                                                    if (rs != null) {
                                                        try {
                                                            rs.close();
                                                        } catch (Throwable var29) {
                                                            var42.addSuppressed(var29);
                                                        }
                                                    }

                                                    throw var42;
                                                }

                                                if (rs != null) {
                                                    rs.close();
                                                }
                                            } catch (Throwable var43) {
                                                if (ps != null) {
                                                    try {
                                                        ps.close();
                                                    } catch (Throwable var24) {
                                                        var43.addSuppressed(var24);
                                                    }
                                                }

                                                throw var43;
                                            }

                                            if (ps != null) {
                                                ps.close();
                                            }
                                        }

                                        var57 = infos.iterator();

                                        while(var57.hasNext()) {
                                            Pair<Integer, Integer> pair = (Pair)var57.next();
                                            pair.right = (Integer)pair.right - se.skillevel;
                                            if ((Integer)pair.right <= 0) {
                                                ps = con.prepareStatement("DELETE FROM skills WHERE skillid = ? AND characterid = ?");

                                                try {
                                                    ps.setInt(1, tSkill);
                                                    ps.setInt(2, (Integer)pair.getLeft());
                                                    ps.executeUpdate();
                                                } catch (Throwable var35) {
                                                    if (ps != null) {
                                                        try {
                                                            ps.close();
                                                        } catch (Throwable var26) {
                                                            var35.addSuppressed(var26);
                                                        }
                                                    }

                                                    throw var35;
                                                }

                                                if (ps != null) {
                                                    ps.close();
                                                }
                                            } else {
                                                ps = con.prepareStatement("UPDATE skills SET skilllevel = ? WHERE skillid = ? AND characterid = ?");

                                                try {
                                                    ps.setInt(1, (Integer)pair.getRight());
                                                    ps.setInt(2, tSkill);
                                                    ps.setInt(3, (Integer)pair.getLeft());
                                                    ps.executeUpdate();
                                                } catch (Throwable var36) {
                                                    if (ps != null) {
                                                        try {
                                                            ps.close();
                                                        } catch (Throwable var27) {
                                                            var36.addSuppressed(var27);
                                                        }
                                                    }

                                                    throw var36;
                                                }

                                                if (ps != null) {
                                                    ps.close();
                                                }
                                            }
                                        }
                                    }

                                    ps = con.prepareStatement("DELETE FROM skills WHERE skillid = ? AND teachId = ?");

                                    try {
                                        ps.setInt(1, skill);
                                        ps.setInt(2, cid);
                                        ps.executeUpdate();
                                    } catch (Throwable var38) {
                                        if (ps != null) {
                                            try {
                                                ps.close();
                                            } catch (Throwable var32) {
                                                var38.addSuppressed(var32);
                                            }
                                        }

                                        throw var38;
                                    }

                                    if (ps != null) {
                                        ps.close();
                                    }

                                    int teachId = SkillConstants.getTeachSkillId(skill);
                                    if (teachId != -1) {
                                        if (!notime) {
                                            se.expiration = System.currentTimeMillis();
                                            ++se.teachTimes;
                                        }

                                        se.teachId = notime ? 0 : this.id;
                                        ps = con.prepareStatement("UPDATE skills SET expiration = ?, teachId = ?, teachTimes = ? WHERE skillid = ? AND characterid = ?");

                                        try {
                                            int n5 = 0;
                                            ++n5;
                                            ps.setLong(n5, se.expiration);
                                            ++n5;
                                            ps.setInt(n5, se.teachId);
                                            ++n5;
                                            ps.setInt(n5, se.teachTimes);
                                            ++n5;
                                            ps.setInt(n5, teachId);
                                            ++n5;
                                            ps.setInt(n5, cid);
                                            ps.executeUpdate();
                                        } catch (Throwable var37) {
                                            if (ps != null) {
                                                try {
                                                    ps.close();
                                                } catch (Throwable var28) {
                                                    var37.addSuppressed(var28);
                                                }
                                            }

                                            throw var37;
                                        }

                                        if (ps != null) {
                                            ps.close();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Throwable var44) {
                        if (con != null) {
                            try {
                                con.close();
                            } catch (Throwable var23) {
                                var44.addSuppressed(var23);
                            }
                        }

                        throw var44;
                    }

                    if (con != null) {
                        con.close();
                    }

                    return var56;
                }

                if (con != null) {
                    con.close();
                }

                return var52;
            }

            if (con != null) {
                con.close();
            }

            return var50;
        } catch (Exception var45) {
            log.error("傳授技能失敗.", var45);
            return -1;
        }
    }

    public void giveRebornBuff() {
        Reborn.giveRebornBuff(this);
    }

    public int getReborns() {
        return this.reborns;
    }

    public int getReborns1() {
        return this.reborns1;
    }

    public int getReborns2() {
        return this.reborns2;
    }

    public int getReborns3() {
        return this.reborns3;
    }

    public void gainReborns(int type) {
        if (type == 0) {
            ++this.reborns;
        } else if (type == 1) {
            ++this.reborns1;
        } else if (type == 2) {
            ++this.reborns2;
        } else if (type == 3) {
            ++this.reborns3;
        }

    }

    public int getAPS() {
        return this.apstorage;
    }

    public void gainAPS(int aps) {
        this.apstorage += aps;
    }

    public void doReborn(int type) {
        this.doReborn(type, -1, true);
    }

    public void doReborn(int type, int ap) {
        this.doReborn(type, ap, true);
    }

    public void doReborn(int type, int ap, boolean clearSkill) {
        this.doReborn(type, ap, clearSkill, 4, 50);
    }

    public void doReborn(int type, int ap, boolean clearSkill, int defaultStats, int rebornStat) {
        Map<MapleStat, Long> stat = new EnumMap(MapleStat.class);
        if (clearSkill) {
            this.clearSkills();
        }

        this.gainReborns(type);
        this.setLevel(1);
        this.setExp(0L);
        this.setJob(JobConstants.getBeginner(this.job));
        this.resetStats(defaultStats, defaultStats, defaultStats, defaultStats);
        this.stats.setInfo(rebornStat, rebornStat, rebornStat, rebornStat);
        if (ap == -1) {
            ap = this.getReborns() * 5 + this.getReborns1() * 10 + this.getReborns2() * 15 + this.getReborns3() * 30;
        }

        this.setRemainingAp((short)ap);
        this.stats.recalcLocalStats(this);
        stat.put(MapleStat.AVAILABLE_AP, (long)ap);
        stat.put(MapleStat.MAX_HP, (long)rebornStat);
        stat.put(MapleStat.MAX_MP, (long)rebornStat);
        stat.put(MapleStat.HP, (long)rebornStat);
        stat.put(MapleStat.MP, (long)rebornStat);
        stat.put(MapleStat.LEVEL, 1L);
        stat.put(MapleStat.JOB, (long)this.job);
        stat.put(MapleStat.EXP, 0L);
        this.client.announce(MaplePacketCreator.updatePlayerStats(stat, false, this));
    }

    public void clearSkills() {
        Map<Integer, SkillEntry> chrSkill = new HashMap(this.getSkills());
        Map<Integer, SkillEntry> newList = new HashMap();
        Iterator var3 = chrSkill.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<Integer, SkillEntry> skill = (Map.Entry)var3.next();
            newList.put((Integer)skill.getKey(), new SkillEntry(0, 0, -1L));
        }

        this.changeSkillsLevel(newList);
        newList.clear();
        chrSkill.clear();
    }

    public Map<Integer, Byte> getSkillsWithPos() {
        new HashMap(this.getSkills());
        Map<Integer, Byte> skillsWithPos = new LinkedHashMap();
        return skillsWithPos;
    }

    public int getStealMemorySkill(int position) {
        Iterator var2 = this.skills.entrySet().iterator();

        Map.Entry skill;
        byte pos;
        do {
            if (!var2.hasNext()) {
                return 0;
            }

            skill = (Map.Entry)var2.next();
            pos = ((SkillEntry)skill.getValue()).position;
        } while(pos < 0 || pos >= 16 || pos != position);

        return (Integer)skill.getKey();
    }

    public int getEquippedStealSkill(int skillid) {
        SkillEntry ret = (SkillEntry)this.skills.get(skillid);
        if (ret != null && ret.teachId != 0) {
            return this.skills.get(ret.teachId) != null ? ret.teachId : 0;
        } else {
            return 0;
        }
    }

    public void 修改幻影裝備技能(int skillId, int teachId) {
        SkillEntry ret = (SkillEntry)this.skills.get(skillId);
        if (ret != null) {
            Skill theskill = SkillFactory.getSkill(ret.teachId);
            if (theskill != null && theskill.isBuffSkill()) {
                this.cancelEffect(theskill.getEffect(1), false, -1L);
            }

            ret.teachId = teachId;
            this.changed_skills = true;
            this.client.announce(MaplePacketCreator.修改幻影裝備技能(skillId, teachId));
        }

    }

    public MapleCharacterCards getCharacterCard() {
        return this.characterCard;
    }

    public InnerSkillEntry[] getInnerSkills() {
        return this.innerSkills;
    }

    public int getInnerSkillSize() {
        int ret = 0;

        for(int i = 0; i < 3; ++i) {
            if (this.innerSkills[i] != null) {
                ++ret;
            }
        }

        return ret;
    }

    public int getInnerSkillIdByPos(int position) {
        return this.innerSkills[position] != null ? this.innerSkills[position].getSkillId() : 0;
    }

    public int getHonor() {
        return this.honor;
    }

    public void setHonor(int exp) {
        this.honor = exp;
    }

    public void gainHonor(int amount) {
        if (amount > 0) {
            this.dropMessage(5, "獲得名聲值:(+" + amount + ")");
        }

        this.honor += amount;
        this.client.announce(MaplePacketCreator.updateInnerStats(this));
    }

    public void checkInnerSkill() {
        if (this.level >= 50 && (this.innerSkills[0] == null || this.innerSkills[1] == null || this.innerSkills[2] == null)) {
            this.newPlayerInnerSkill(new InnerSkillEntry(70000000, 1, (byte)1, (byte)0, false));
            this.newPlayerInnerSkill(new InnerSkillEntry(70000001, 3, (byte)2, (byte)0, false));
            this.newPlayerInnerSkill(new InnerSkillEntry(70000002, 5, (byte)3, (byte)0, true));
        }

    }

    public void newPlayerInnerSkill(InnerSkillEntry ise) {
        this.changed_innerSkills = true;
        this.innerSkills[ise.getPosition() - 1] = ise;
        this.client.announce(MaplePacketCreator.updateInnerSkill(this, (byte)0, ise, ise, ise));
        this.client.announce(MaplePacketCreator.updateInnerSkill(this, (byte)0, ise, ise, ise));
        this.client.announce(MaplePacketCreator.updateInnerSkill(this, (byte)0, ise, ise, ise));
    }

    public void changeInnerSkill(MapleCharacter player, byte statPath, InnerSkillEntry ise) {
        this.changed_innerSkills = true;
        this.innerSkills[ise.getPosition() - 1] = ise;
        this.client.announce(MaplePacketCreator.updateInnerSkill(player, statPath, this.innerSkills[0], this.innerSkills[1], this.innerSkills[2]));
    }

    public void checkHyperAP() {
        if (this.level >= 140) {
            Map<Integer, SkillEntry> list = new HashMap();

            for(int i = 80000400; i <= 80000417; ++i) {
                Skill skil = SkillFactory.getSkill(i);
                if (skil != null && this.getSkillLevel(skil) <= 0) {
                    list.put(i, new SkillEntry(0, (byte)skil.getMaxLevel(), -1L));
                }
            }

            if (!list.isEmpty()) {
                this.changeSkillsLevel(list);
            }
        }

    }

    public String getMedalText() {
        String medal = "";
        Item medalItem = this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-26);
        if (medalItem != null) {
            medal = "<" + MapleItemInformationProvider.getInstance().getName(medalItem.getItemId()) + "> ";
        }

        return medal;
    }

    public int getGamePoints() {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            int var13;
            try {
                int gamePoints = 0;
                PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_info WHERE accId = ? AND worldId = ?");
                ps.setInt(1, this.getAccountID());
                ps.setInt(2, this.getWorld());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    gamePoints = rs.getInt("gamePoints");
                    Timestamp updateTime = rs.getTimestamp("updateTime");
                    Calendar sqlcal = Calendar.getInstance();
                    if (updateTime != null) {
                        sqlcal.setTimeInMillis(updateTime.getTime());
                    }

                    if (sqlcal.get(5) + 1 <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                        gamePoints = 0;
                        PreparedStatement psu = con.prepareStatement("UPDATE accounts_info SET gamePoints = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");
                        psu.setInt(1, this.getAccountID());
                        psu.setInt(2, this.getWorld());
                        psu.executeUpdate();
                        psu.close();
                    }
                } else {
                    PreparedStatement psu = con.prepareStatement("INSERT INTO accounts_info (accId, worldId, gamePoints) VALUES (?, ?, ?)");
                    psu.setInt(1, this.getAccountID());
                    psu.setInt(2, this.getWorld());
                    psu.setInt(3, 0);
                    psu.executeUpdate();
                    psu.close();
                }

                rs.close();
                ps.close();
                var13 = gamePoints;
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }

            return var13;
        } catch (Exception var10) {
            Exception Ex = var10;
            log.error("獲取角色帳號的在線時間點出現錯誤 - 數據庫查詢失敗", Ex);
            return -1;
        }
    }

    public void gainGamePoints(int amount) {
        int gamePoints = this.getGamePoints() + amount;
        this.updateGamePoints(gamePoints);
    }

    public void resetGamePoints() {
        this.updateGamePoints(0);
    }

    public void updateGamePoints(int amount) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE accounts_info SET gamePoints = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND worldId = ?");

                try {
                    ps.setInt(1, amount);
                    ps.setInt(2, this.getAccountID());
                    ps.setInt(3, this.getWorld());
                    ps.executeUpdate();
                } catch (Throwable var8) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var10) {
            Exception Ex = var10;
            log.error("更新角色帳號的在線時間出現錯誤 - 數據庫更新失敗.", Ex);
        }

    }

    public long getMaxDamageOver(int skillId) {
        long maxDamage = ServerConfig.DAMAGE_LIMIT;
        return maxDamage;
    }

    public void addTraitExp(int exp) {
        ((MapleTrait)this.traits.get(MapleTraitType.craft)).addExp(exp, this);
    }

    public void setTraitExp(int exp) {
        ((MapleTrait)this.traits.get(MapleTraitType.craft)).addExp(exp, this);
    }

    public int getLove() {
        return this.love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public void addLove(int loveChange) {
        this.love += loveChange;
        MessengerRankingWorker.getInstance().updateRankFromPlayer(this);
    }

    public long getLastLoveTime() {
        return this.lastlovetime;
    }

    public Map<Integer, Long> getLoveCharacters() {
        return this.lastdayloveids;
    }

    public int canGiveLove(MapleCharacter from) {
        if (from != null && this.lastdayloveids != null) {
            if (this.lastdayloveids.containsKey(from.getId())) {
                long lastTime = (Long)this.lastdayloveids.get(from.getId());
                return lastTime >= System.currentTimeMillis() - 86400000L ? 2 : 0;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public void hasGiveLove(MapleCharacter to) {
        this.lastlovetime = System.currentTimeMillis();
        this.lastdayloveids.remove(to.getId());
        this.lastdayloveids.put(to.getId(), System.currentTimeMillis());

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO lovelog (characterid, characterid_to) VALUES (?, ?)");
                ps.setInt(1, this.getId());
                ps.setInt(2, to.getId());
                ps.execute();
                ps.close();
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException e = var7;
            PrintStream var10000 = System.err;
            String var10001 = this.getName();
            var10000.println("ERROR writing lovelog for char " + var10001 + " to " + to.getName() + String.valueOf(e));
        }

    }

    public long getExpNeededForLevel() {
        return GameConstants.getExpNeededForLevel(this.level);
    }

    public int getPlayerStats() {
        return this.getHpApUsed() + this.getMpApUsed() + this.stats.getStr() + this.stats.getDex() + this.stats.getLuk() + this.stats.getInt() + this.getRemainingAp();
    }

    public int getMaxStats() {
        int total = 25;
        if (!JobConstants.is零轉職業(this.job)) {
            total += this.job % 10 == 1 ? 5 : (this.job % 10 == 2 ? 10 : 0);
        }

        total += (this.level - 1) * 5;
        return total;
    }

    public boolean checkMaxStat() {
        return this.getPlayerStats() > this.getMaxStats();
    }

    public void resetStats(int str, int dex, int int_, int luk) {
        this.resetStats(str, dex, int_, luk, true);
    }

    public void resetStats(int str, int dex, int int_, int luk, boolean resetAll) {
        Map<MapleStat, Long> stat = new EnumMap(MapleStat.class);
        int total = this.stats.getStr() + this.stats.getDex() + this.stats.getLuk() + this.stats.getInt() + this.getRemainingAp();
        if (resetAll) {
            total += this.getHpApUsed();
            this.useHpAp(-this.getHpApUsed());
            total += this.getMpApUsed();
            this.useMpAp(-this.getMpApUsed());
        }

        total -= str;
        this.stats.str = (short)str;
        total -= dex;
        this.stats.dex = (short)dex;
        total -= int_;
        this.stats.int_ = (short)int_;
        total -= luk;
        this.stats.luk = (short)luk;
        if (JobConstants.is管理員(this.job)) {
            total = 0;
        }

        this.setRemainingAp((short)total);
        this.stats.recalcLocalStats(this);
        stat.put(MapleStat.STR, (long)str);
        stat.put(MapleStat.DEX, (long)dex);
        stat.put(MapleStat.INT, (long)int_);
        stat.put(MapleStat.LUK, (long)luk);
        stat.put(MapleStat.AVAILABLE_AP, (long)total);
        this.client.announce(MaplePacketCreator.updatePlayerStats(stat, false, this));
    }

    public void spReset() {
        this.spReset(true);
    }

    public void spReset(boolean show) {
        this.spReset(-1, show);
    }

    public void spReset(int resetBeginnerJob) {
        this.spReset(resetBeginnerJob, true);
    }

    public void spReset(int resetBeginnerJob, boolean show) {
        Map<Integer, SkillEntry> oldList = new HashMap(this.getSkills());
        Map<Integer, SkillEntry> newList = new HashMap();
        int teachSkillID = JobConstants.getTeachSkillID(this.job);
        Iterator var8 = oldList.entrySet().iterator();

        while(true) {
            while(true) {
                int sk;
                Map.Entry toRemove;
                Skill skill;
                do {
                    do {
                        do {
                            do {
                                if (!var8.hasNext()) {
                                    if (!newList.isEmpty()) {
                                        this.changeSkillsLevel(newList);
                                    }

                                    oldList.clear();
                                    newList.clear();
                                    this.resetSP(0);
                                    int[] var10000;
                                    if (JobConstants.is管理員(this.job)) {
                                        var10000 = this.remainingSp;
                                        var10000[0] += 11;
                                    } else if (!JobConstants.is零轉職業(this.job)) {
                                        int nJobGrade = JobConstants.getJobGrade(this.job);

                                        for(int g = 0; g < nJobGrade; ++g) {
                                            Pair<Integer, Integer> sps = getJobChangeSP(this.job, this.getSubcategory(), g);
                                            if (sps != null) {
                                                var10000 = this.remainingSp;
                                                int var10001 = (Integer)sps.getLeft();
                                                var10000[var10001] += (Integer)sps.getRight();
                                            }
                                        }

                                        int g;
                                        if (JobConstants.is神之子(this.job)) {
                                            for(int lv = 101; lv <= this.level; ++lv) {
                                                g = getJobLvSP(this.job, lv);
                                                var10000 = this.remainingSp;
                                                var10000[0] += g;
                                                var10000 = this.remainingSp;
                                                var10000[1] += g;
                                                if (lv == 200) {
                                                    break;
                                                }
                                            }
                                        } else {
                                            int[] jobLvs = this.subcategory == 1 ? new int[]{10, 20, 30, 45, 60, 100} : new int[]{10, 30, 60, 100};
                                            g = 0;
                                            int[] var13 = jobLvs;
                                            int var14 = jobLvs.length;

                                            for(int var15 = 0; var15 < var14; ++var15) {
                                                int lvMin = var13[var15];
                                                if (this.level < lvMin) {
                                                    break;
                                                }

                                                int lvMax = jobLvs.length - 1 <= g ? this.level : jobLvs[g + 1];
                                                if (this.level < lvMax) {
                                                    lvMax = this.level;
                                                }

                                                for(int lv = lvMin + 1; lv <= lvMax; ++lv) {
                                                    int spNum = getJobLvSP(this.subcategory == 1 && g == 0 ? 430 : this.job, lv);
                                                    var10000 = this.remainingSp;
                                                    var10000[g] += spNum;
                                                }

                                                ++g;
                                            }
                                        }
                                    }

                                    if (show) {
                                        this.updateSingleStat(MapleStat.AVAILABLE_SP, 0L);
                                        this.client.sendEnableActions();
                                        this.baseSkills();
                                    }

                                    return;
                                }

                                toRemove = (Map.Entry)var8.next();
                                skill = SkillFactory.getSkill((Integer)toRemove.getKey());
                                sk = -1;
                            } while(skill.isBeginnerSkill() && skill.getId() / 10000 != resetBeginnerJob && ((sk = SkillConstants.getLinkSkillId(skill.getId())) == -1 || teachSkillID == skill.getId()));
                        } while(skill.isSpecialSkill() && (!skill.isHyperStat() || resetBeginnerJob == -1));
                    } while(skill.isHyperSkill() && skill.canBeLearnedBy(this.getJobWithSub()));
                } while(skill.isVSkill());

                if (skill.canBeLearnedBy(this.getJobWithSub()) && sk == -1) {
                    newList.put((Integer)toRemove.getKey(), new SkillEntry((byte)(((SkillEntry)toRemove.getValue()).masterlevel > 0 ? 0 : -1), ((SkillEntry)toRemove.getValue()).masterlevel, ((SkillEntry)toRemove.getValue()).expiration));
                } else {
                    newList.put((Integer)toRemove.getKey(), new SkillEntry(-1, 0, -1L));
                }
            }
        }
    }

    public int getPlayerPoints() {
        return this.playerPoints;
    }

    public void setPlayerPoints(int gain) {
        this.playerPoints = gain;
    }

    public void gainPlayerPoints(int gain) {
        if (this.playerPoints + gain >= 0) {
            this.playerPoints += gain;
        }
    }

    public int getPlayerEnergy() {
        return this.playerEnergy;
    }

    public void setPlayerEnergy(int gain) {
        this.playerEnergy = gain;
    }

    public void gainPlayerEnergy(int gain) {
        if (this.playerEnergy + gain >= 0) {
            this.playerEnergy += gain;
        }
    }

    public MaplePvpStats getPvpStats() {
        return this.pvpStats;
    }

    public int getPvpKills() {
        return this.pvpKills;
    }

    public void gainPvpKill() {
        ++this.pvpKills;
        ++this.pvpVictory;
        if (this.pvpVictory == 5) {
            this.map.broadcastMessage(MaplePacketCreator.spouseMessage(UserChatMessageType.管理員對話, "[Pvp] 玩家 " + this.getName() + " 已經達到 5 連斬。"));
        } else if (this.pvpVictory == 10) {
            this.client.getChannelServer().broadcastGMPacket(MaplePacketCreator.spouseMessage(UserChatMessageType.管理員對話, "[Pvp] 玩家 " + this.getName() + " 已經達到 10 連斬。"));
        } else if (this.pvpVictory >= 20) {
            WorldBroadcastService var10000 = WorldBroadcastService.getInstance();
            UserChatMessageType var10001 = UserChatMessageType.管理員對話;
            String var10002 = this.getName();
            var10000.broadcastMessage(MaplePacketCreator.spouseMessage(var10001, "[Pvp] 玩家 " + var10002 + " 已經達到 " + this.pvpVictory + " 連斬。他(她)在頻道 " + this.client.getChannel() + " 地圖 " + this.map.getMapName() + " 中喊道誰能賜我一死."));
        } else {
            this.dropMessage(6, "當前: " + this.pvpVictory + " 連斬.");
        }

    }

    public int getPvpDeaths() {
        return this.pvpDeaths;
    }

    public void gainPvpDeath() {
        ++this.pvpDeaths;
        this.pvpVictory = 0;
    }

    public int getPvpVictory() {
        return this.pvpVictory;
    }

    public void dropTopMsg(String message) {
        this.client.announce(UIPacket.getTopMsg(message));
    }

    public void dropMidMsg(String message) {
        this.client.announce(UIPacket.offStaticScreenMessage());
        this.client.announce(UIPacket.getMidMsg(0, message, true));
    }

    public void clearMidMsg() {
        this.client.announce(UIPacket.offStaticScreenMessage());
    }

    public void dropSpecialTopMsg(String message, int fontSize, int fontColorType) {
        this.dropSpecialTopMsg(message, 0, fontSize, fontColorType, 0);
    }

    public void dropSpecialTopMsg(String msg, int fontNameType, int fontSize, int fontColorType, int fadeOutDelay) {
        if (fontSize < 10) {
            fontSize = 10;
        }

        this.client.announce(UIPacket.getSpecialTopMsg(msg, fontNameType, fontSize, fontColorType, fadeOutDelay));
    }

    public int getEventCount(String eventId) {
        return this.getEventCount(eventId, 0);
    }

    public int getEventCount(String eventId, int type) {
        return this.getEventCount(eventId, type, 1);
    }

    public int getEventCount(String eventId, int type, int resetDay) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            int var16;
            try {
                int count = 0;
                PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts_event WHERE accId = ? AND eventId = ?");
                ps.setInt(1, this.getAccountID());
                ps.setString(2, eventId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                    Timestamp updateTime = rs.getTimestamp("updateTime");
                    if (type == 0) {
                        Calendar sqlcal = Calendar.getInstance();
                        if (updateTime != null) {
                            sqlcal.setTimeInMillis(updateTime.getTime());
                        }

                        if (sqlcal.get(5) + resetDay <= Calendar.getInstance().get(5) || sqlcal.get(2) + 1 <= Calendar.getInstance().get(2) || sqlcal.get(1) + 1 <= Calendar.getInstance().get(1)) {
                            count = 0;
                            PreparedStatement psu = con.prepareStatement("UPDATE accounts_event SET count = 0, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND eventId = ?");
                            psu.setInt(1, this.getAccountID());
                            psu.setString(2, eventId);
                            psu.executeUpdate();
                            psu.close();
                        }
                    }
                } else {
                    PreparedStatement psu = con.prepareStatement("INSERT INTO accounts_event (accId, eventId, count, type) VALUES (?, ?, ?, ?)");
                    psu.setInt(1, this.getAccountID());
                    psu.setString(2, eventId);
                    psu.setInt(3, 0);
                    psu.setInt(4, type);
                    psu.executeUpdate();
                    psu.close();
                }

                rs.close();
                ps.close();
                var16 = count;
            } catch (Throwable var12) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var11) {
                        var12.addSuppressed(var11);
                    }
                }

                throw var12;
            }

            if (con != null) {
                con.close();
            }

            return var16;
        } catch (Exception var13) {
            Exception Ex = var13;
            log.error("獲取 EventCount 次數.", Ex);
            return -1;
        }
    }

    public void setEventCount(String eventId) {
        this.setEventCount(eventId, 0);
    }

    public void setEventCount(String eventId, int type) {
        this.setEventCount(eventId, type, 1);
    }

    public void setEventCount(String eventId, int type, int count) {
        this.setEventCount(eventId, type, count, 1, true);
    }

    public void setEventCount(String eventId, int type, int count, int date, boolean updateTime) {
        int eventCount = this.getEventCount(eventId, type, date);

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps;
                if (updateTime) {
                    ps = con.prepareStatement("UPDATE accounts_event SET count = ?, type = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND eventId = ?");
                } else {
                    ps = con.prepareStatement("UPDATE accounts_event SET count = ?, type = ? WHERE accId = ? AND eventId = ?");
                }

                ps.setInt(1, eventCount + count);
                ps.setInt(2, type);
                ps.setInt(3, this.getAccountID());
                ps.setString(4, eventId);
                ps.executeUpdate();
                ps.close();
            } catch (Throwable var11) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var10) {
                        var11.addSuppressed(var10);
                    }
                }

                throw var11;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var12) {
            Exception Ex = var12;
            log.error("增加 EventCount 次數失敗.", Ex);
        }

    }

    public void resetEventCount(String eventId) {
        this.resetEventCount(eventId, 0);
    }

    public void resetEventCount(String eventId, int type) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE accounts_event SET count = 0, type = ?, updateTime = CURRENT_TIMESTAMP() WHERE accId = ? AND eventId = ?");
                ps.setInt(1, type);
                ps.setInt(2, this.getAccountID());
                ps.setString(3, eventId);
                ps.executeUpdate();
                ps.close();
            } catch (Throwable var7) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (con != null) {
                con.close();
            }
        } catch (Exception var8) {
            Exception Ex = var8;
            log.error("重置 EventCount 次數失敗.", Ex);
        }

    }

    public boolean isSamePartyId(int partyId) {
        return partyId > 0 && this.party != null && this.party.getId() == partyId;
    }

    public long getTotDamageToMob() {
        return this.totDamageToMob;
    }

    public void setTotDamageToMob(long totDamageToMob) {
        this.totDamageToMob = totDamageToMob;
    }

    public void prepareFuWenTime(long time) {
        this.lastFuWenTime = System.currentTimeMillis() + time;
    }

    public int getLastFuWenTime() {
        if (this.lastFuWenTime <= 0L) {
            this.lastFuWenTime = System.currentTimeMillis();
        }

        long time = this.lastFuWenTime - System.currentTimeMillis();
        return time <= 0L ? 0 : (int)time;
    }

    public Map<Integer, Integer> getSkillSkin() {
        Map<Integer, Integer> ret = new LinkedHashMap();
        List<Integer> theList = this.getInventory(MapleInventoryType.EQUIPPED).listSkillSkinIds();
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        Iterator var4 = theList.iterator();

        while(var4.hasNext()) {
            Integer i = (Integer)var4.next();
            int skillId = ii.getSkillSkinFormSkillId(i);
            Skill skill = SkillFactory.getSkill(skillId);
            if (skill != null) {
                ret.put(skillId, i);
            }
        }

        return ret;
    }

    public final int[] StringtoInt(String str) {
        int[] ret = new int[100];
        StringTokenizer toKenizer = new StringTokenizer(str, ",");
        String[] strx = new String[toKenizer.countTokens()];

        for(int i = 0; i < toKenizer.countTokens(); ++i) {
            strx[i] = toKenizer.nextToken();
            ret[i] = Integer.parseInt(strx[i]);
        }

        return ret;
    }

    public final boolean MissionCanMake(int missionid) {
        boolean ret = true;

        for(int i = 1; i < 5; ++i) {
            if (!this.MissionCanMake(missionid, i)) {
                ret = false;
            }
        }

        return ret;
    }

    public final boolean MissionCanMake(int missionid, int checktype) {
        boolean ret = false;
        int minlevel = -1;
        int maxlevel = -1;
        String joblist = "all";
        String itemlist = "none";
        String prelist = "none";

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT minlevel,maxlevel,joblist,itemlist,prelist FROM missionlist WHERE missionid = ?");

                try {
                    ps.setInt(1, missionid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            minlevel = rs.getInt("minlevel");
                            maxlevel = rs.getInt("maxlevel");
                            joblist = rs.getString("joblist");
                            itemlist = rs.getString("itemlist");
                            prelist = rs.getString("prelist");
                        }
                    } catch (Throwable var17) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var16) {
                                var17.addSuppressed(var16);
                            }
                        }

                        throw var17;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var18) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var15) {
                            var18.addSuppressed(var15);
                        }
                    }

                    throw var18;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var19) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var14) {
                        var19.addSuppressed(var14);
                    }
                }

                throw var19;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var20) {
            SQLException ex = var20;
            log.error("Error MissionCanMake:", ex);
        }

        int i;
        int[] var22;
        int var23;
        int var24;
        switch (checktype) {
            case 1:
                if (minlevel > -1 && maxlevel > -1) {
                    if (this.getLevel() >= minlevel && this.getLevel() <= maxlevel) {
                        ret = true;
                    }
                } else if (minlevel > -1 && maxlevel == -1) {
                    if (this.getLevel() >= minlevel) {
                        ret = true;
                    }
                } else if (minlevel == -1 && maxlevel > -1) {
                    if (this.getLevel() <= maxlevel) {
                        ret = true;
                    }
                } else if (minlevel == -1 && maxlevel == -1) {
                    ret = true;
                }
                break;
            case 2:
                if (joblist.equals("all")) {
                    ret = true;
                    break;
                } else {
                    var22 = this.StringtoInt(joblist);
                    var23 = var22.length;

                    for(var24 = 0; var24 < var23; ++var24) {
                        i = var22[var24];
                        if (this.getJob() == i) {
                            ret = true;
                            return ret;
                        }
                    }

                    return ret;
                }
            case 3:
                if (itemlist.equals("none")) {
                    ret = true;
                    break;
                } else {
                    var22 = this.StringtoInt(itemlist);
                    var23 = var22.length;

                    for(var24 = 0; var24 < var23; ++var24) {
                        i = var22[var24];
                        if (!this.haveItem(i)) {
                            ret = false;
                            return ret;
                        }
                    }

                    return ret;
                }
            case 4:
                if (prelist.equals("none")) {
                    ret = true;
                } else {
                    var22 = this.StringtoInt(prelist);
                    var23 = var22.length;

                    for(var24 = 0; var24 < var23; ++var24) {
                        i = var22[var24];
                        if (!this.MissionStatus(this.getId(), i, 0, 1)) {
                            ret = false;
                            break;
                        }
                    }
                }
        }

        return ret;
    }

    public final int MissionGetIntData(int missionid, int checktype) {
        int ret = -1;
        int minlevel = -1;
        int maxlevel = -1;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT minlevel,maxlevel FROM missionlist WHERE missionid = ?");

                try {
                    ps.setInt(1, missionid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            minlevel = rs.getInt("minlevel");
                            maxlevel = rs.getInt("maxlevel");
                        }
                    } catch (Throwable var14) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var13) {
                                var14.addSuppressed(var13);
                            }
                        }

                        throw var14;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var15) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var12) {
                            var15.addSuppressed(var12);
                        }
                    }

                    throw var15;
                }

                if (ps != null) {
                    ps.close();
                }

                switch (checktype) {
                    case 1 -> ret = minlevel;
                    case 2 -> ret = maxlevel;
                }
            } catch (Throwable var16) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var11) {
                        var16.addSuppressed(var11);
                    }
                }

                throw var16;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var17) {
            SQLException ex = var17;
            log.error("Error MissionGetIntData:", ex);
        }

        return ret;
    }

    public final String MissionGetStrData(int missionid, int checktype) {
        String ret = "";
        String missionname = "";
        String joblist = "all";
        String itemlist = "none";
        String prelist = "none";

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT missionname,joblist,itemlist,prelist FROM missionlist WHERE missionid = ?");

                try {
                    ps.setInt(1, missionid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            missionname = rs.getString("missionname");
                            joblist = rs.getString("joblist");
                            itemlist = rs.getString("itemlist");
                            prelist = rs.getString("prelist");
                        }
                    } catch (Throwable var16) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var15) {
                                var16.addSuppressed(var15);
                            }
                        }

                        throw var16;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var17) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var14) {
                            var17.addSuppressed(var14);
                        }
                    }

                    throw var17;
                }

                if (ps != null) {
                    ps.close();
                }

                switch (checktype) {
                    case 1 -> ret = missionname;
                    case 2 -> ret = joblist;
                    case 3 -> ret = itemlist;
                    case 4 -> ret = prelist;
                }
            } catch (Throwable var18) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var13) {
                        var18.addSuppressed(var13);
                    }
                }

                throw var18;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var19) {
            SQLException ex = var19;
            log.error("Error MissionCanMake:", ex);
        }

        return ret;
    }

    public final String MissionGetJoblist(String joblist) {
        StringBuilder ret = new StringBuilder();

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                int[] var4 = this.StringtoInt(joblist);
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    int i = var4[var6];
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM joblist WHERE id = ?");

                    try {
                        ps.setInt(1, i);
                        ResultSet rs = ps.executeQuery();

                        try {
                            if (rs.next()) {
                                ret.append(",").append(rs.getString("jobname"));
                            }
                        } catch (Throwable var15) {
                            if (rs != null) {
                                try {
                                    rs.close();
                                } catch (Throwable var14) {
                                    var15.addSuppressed(var14);
                                }
                            }

                            throw var15;
                        }

                        if (rs != null) {
                            rs.close();
                        }
                    } catch (Throwable var16) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var13) {
                                var16.addSuppressed(var13);
                            }
                        }

                        throw var16;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Throwable var17) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var12) {
                        var17.addSuppressed(var12);
                    }
                }

                throw var17;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var18) {
            SQLException ex = var18;
            log.error("Error MissionGetJoblist:", ex);
        }

        return ret.toString();
    }

    public final void MissionMake(int charid, int missionid, int repeat, long repeattime, int lockmap, int mobid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("INSERT INTO missionstatus VALUES (DEFAULT, ?, ?, ?, ?, ?, 0, DEFAULT, 0, 0, ?, 0, 0)");

                try {
                    ps.setInt(1, missionid);
                    ps.setInt(2, charid);
                    ps.setInt(3, repeat);
                    ps.setLong(4, repeattime);
                    ps.setInt(5, lockmap);
                    ps.setInt(6, mobid);
                    ps.executeUpdate();
                } catch (Throwable var14) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var13) {
                            var14.addSuppressed(var13);
                        }
                    }

                    throw var14;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var15) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var12) {
                        var15.addSuppressed(var12);
                    }
                }

                throw var15;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var16) {
            SQLException ex = var16;
            log.error("Error MissionMake:", ex);
        }

    }

    public final void MissionReMake(int charid, int missionid, int repeat, long repeattime, int lockmap) {
        int finish = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE missionstatus SET `repeat` = ?, repeattime = ?, lockmap = ?, finish = ?, minnum = 0 WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, repeat);
                    ps.setLong(2, repeattime);
                    ps.setInt(3, lockmap);
                    ps.setInt(4, finish);
                    ps.setInt(5, missionid);
                    ps.setInt(6, charid);
                    ps.executeUpdate();
                } catch (Throwable var14) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var13) {
                            var14.addSuppressed(var13);
                        }
                    }

                    throw var14;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var15) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var12) {
                        var15.addSuppressed(var12);
                    }
                }

                throw var15;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var16) {
            SQLException ex = var16;
            log.error("Error MissionFinish:", ex);
        }

    }

    public final void MissionFinish(int charid, int missionid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE missionstatus SET finish = 1, lastdate = CURRENT_TIMESTAMP(), times = times+1, lockmap = 0 WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, missionid);
                    ps.setInt(2, charid);
                    ps.executeUpdate();
                } catch (Throwable var9) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var10) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var11) {
        }

    }

    public final int MissionGetFinish(int charid, int missionid) {
        int ret = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT times FROM missionstatus WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, missionid);
                    ps.setInt(2, charid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            ret = rs.getInt(1);
                        }
                    } catch (Throwable var12) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var11) {
                                var12.addSuppressed(var11);
                            }
                        }

                        throw var12;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var13) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var10) {
                            var13.addSuppressed(var10);
                        }
                    }

                    throw var13;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var14) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var9) {
                        var14.addSuppressed(var9);
                    }
                }

                throw var14;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var15) {
            SQLException ex = var15;
            log.error("Error MissionFinish:", ex);
        }

        return ret;
    }

    public final void MissionDelete(int charid, int missionid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM missionstatus WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, missionid);
                    ps.setInt(2, charid);
                    ps.executeUpdate();
                } catch (Throwable var9) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var10) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var11) {
            SQLException ex = var11;
            log.error("Error MissionDelete:", ex);
        }

    }

    public final void MissionSetMinNum(int charid, int missionid, int num) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE missionstatus SET `minnum` = ? WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, num);
                    ps.setInt(2, missionid);
                    ps.setInt(3, charid);
                    ps.executeUpdate();
                } catch (Throwable var10) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var11) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }

                throw var11;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var12) {
            SQLException ex = var12;
            log.error("Error MissionAddNum:", ex);
        }

    }

    public final void MissionAddMinNum(int charid, int missionid, int num) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE missionstatus SET `minnum` = `minnum` + ? WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, num);
                    ps.setInt(2, missionid);
                    ps.setInt(3, charid);
                    ps.executeUpdate();
                } catch (Throwable var10) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var11) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }

                throw var11;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var12) {
            SQLException ex = var12;
            log.error("Error MissionAddNum:", ex);
        }

    }

    public final int MissionGetMinNum(int charid, int missionid, int mobid) {
        int ret = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                String sql;
                if (mobid == 0) {
                    sql = "SELECT minnum FROM missionstatus WHERE charid = ? AND missionid = ?";
                } else {
                    sql = "SELECT minnum FROM missionstatus WHERE charid = ? AND missionid = ? AND mobid = ?";
                }

                PreparedStatement ps = con.prepareStatement(sql);

                try {
                    if (mobid == 0) {
                        ps.setInt(1, charid);
                        ps.setInt(2, missionid);
                    } else {
                        ps.setInt(1, charid);
                        ps.setInt(2, missionid);
                        ps.setInt(3, mobid);
                    }

                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            ret = rs.getInt("minnum");
                        }
                    } catch (Throwable var14) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var13) {
                                var14.addSuppressed(var13);
                            }
                        }

                        throw var14;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var15) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var12) {
                            var15.addSuppressed(var12);
                        }
                    }

                    throw var15;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var16) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var11) {
                        var16.addSuppressed(var11);
                    }
                }

                throw var16;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var17) {
            SQLException ex = var17;
            log.error("Error MissionMob:", ex);
        }

        return ret;
    }

    public final int MissionGetMaxNum(int charid, int missionid, int mobid) {
        int ret = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                String sql;
                if (mobid == 0) {
                    sql = "SELECT maxnum FROM missionstatus WHERE charid = ? AND missionid = ?";
                } else {
                    sql = "SELECT maxnum FROM missionstatus WHERE charid = ? AND missionid = ? AND mobid = ?";
                }

                PreparedStatement ps = con.prepareStatement(sql);

                try {
                    if (mobid == 0) {
                        ps.setInt(1, charid);
                        ps.setInt(2, missionid);
                    } else {
                        ps.setInt(1, charid);
                        ps.setInt(2, missionid);
                        ps.setInt(3, mobid);
                    }

                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            ret = rs.getInt("maxnum");
                        }
                    } catch (Throwable var14) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var13) {
                                var14.addSuppressed(var13);
                            }
                        }

                        throw var14;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var15) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var12) {
                            var15.addSuppressed(var12);
                        }
                    }

                    throw var15;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var16) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var11) {
                        var16.addSuppressed(var11);
                    }
                }

                throw var16;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var17) {
            SQLException ex = var17;
            log.error("Error MissionMob:", ex);
        }

        return ret;
    }

    public final int MissionGetMobId(int charid, int missionid) {
        int ret = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT mobid FROM missionstatus WHERE charid = ? AND missionid = ?");

                try {
                    ps.setInt(1, charid);
                    ps.setInt(2, missionid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            ret = rs.getInt("mobid");
                        }
                    } catch (Throwable var12) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var11) {
                                var12.addSuppressed(var11);
                            }
                        }

                        throw var12;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var13) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var10) {
                            var13.addSuppressed(var10);
                        }
                    }

                    throw var13;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var14) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var9) {
                        var14.addSuppressed(var9);
                    }
                }

                throw var14;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var15) {
            SQLException ex = var15;
            log.error("Error MissionMob:", ex);
        }

        return ret;
    }

    public final void MissionSetMobId(int charid, int missionid, int mobid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE missionstatus SET `mobid` = ? WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, mobid);
                    ps.setInt(2, missionid);
                    ps.setInt(3, charid);
                    ps.executeUpdate();
                } catch (Throwable var10) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var9) {
                            var10.addSuppressed(var9);
                        }
                    }

                    throw var10;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var11) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }

                throw var11;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var12) {
            SQLException ex = var12;
            log.error("Error MissionAddNum:", ex);
        }

    }

    public final void MissionMaxNum(int missionid, int maxnum) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE missionstatus SET `maxnum` = ? WHERE missionid = ? AND charid = ?");

                try {
                    ps.setInt(1, maxnum);
                    ps.setInt(2, missionid);
                    ps.setInt(3, this.getId());
                    ps.executeUpdate();
                } catch (Throwable var9) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var10) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var11) {
            SQLException ex = var11;
            log.error("Error MissionMaxNum:", ex);
        }

    }

    public final long MissionGetRepeattime(int charid, int missionid) {
        long ret = 0L;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT repeattime FROM missionstatus WHERE charid = ? AND missionid = ?");

                try {
                    ps.setInt(1, charid);
                    ps.setInt(2, missionid);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            ret = rs.getLong("repeattime");
                        }
                    } catch (Throwable var13) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var12) {
                                var13.addSuppressed(var12);
                            }
                        }

                        throw var13;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var14) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var11) {
                            var14.addSuppressed(var11);
                        }
                    }

                    throw var14;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var15) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var10) {
                        var15.addSuppressed(var10);
                    }
                }

                throw var15;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var16) {
            SQLException ex = var16;
            log.error("Error MissionMob:", ex);
        }

        return ret;
    }

    public final void MissionDeleteNotFinish(int charid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM missionstatus WHERE finish = 0 AND charid = ?");

                try {
                    ps.setInt(1, charid);
                    ps.executeUpdate();
                } catch (Throwable var8) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var10) {
            SQLException ex = var10;
            log.error("Error MissionDeleteNotFinish:", ex);
        }

    }

    public final boolean MissionStatus(int charid, int missionid, int maxtimes, int checktype) {
        boolean ret = false;
        boolean MissionMake = false;
        long now = 0L;
        long t = 0L;
        int repeat = 0;
        int repeattime = 0;
        int finish = 0;
        int times = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                String sql;
                if (checktype == 5) {
                    sql = "SELECT * FROM missionstatus WHERE lockmap = 1 AND charid = ?";
                } else {
                    sql = "SELECT * FROM missionstatus WHERE missionid = ? AND charid = ?";
                }

                PreparedStatement ps = con.prepareStatement(sql);

                try {
                    if (checktype == 5) {
                        ps.setInt(1, charid);
                    } else {
                        ps.setInt(1, missionid);
                        ps.setInt(2, charid);
                    }

                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            Timestamp lastdate = rs.getTimestamp("lastdate");
                            repeat = rs.getInt("repeat");
                            repeattime = rs.getInt("repeattime");
                            finish = rs.getInt("finish");
                            times = rs.getInt("times");
                            t = lastdate.getTime();
                            now = System.currentTimeMillis();
                            MissionMake = true;
                        }
                    } catch (Throwable var25) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var24) {
                                var25.addSuppressed(var24);
                            }
                        }

                        throw var25;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var26) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var23) {
                            var26.addSuppressed(var23);
                        }
                    }

                    throw var26;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var27) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var22) {
                        var27.addSuppressed(var22);
                    }
                }

                throw var27;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var28) {
            SQLException ex = var28;
            log.error("Error MissionStatus:", ex);
        }

        switch (checktype) {
            case 0:
                if (finish == 1) {
                    ret = true;
                }
                break;
            case 1:
                if (repeat == 1) {
                    ret = true;
                }
                break;
            case 2:
                if (now - t > (long)repeattime) {
                    ret = true;
                }
                break;
            case 3:
                if (times >= maxtimes) {
                    ret = true;
                }
                break;
            case 4:
                if (MissionMake) {
                    ret = true;
                }
                break;
            case 5:
                if (MissionMake) {
                    ret = true;
                }
        }

        return ret;
    }

    public void gainItem(int itemId, int amount, String log) {
        MapleInventoryManipulator.addById(this.client, itemId, amount, log);
    }

    public void fixOnlineTime() {
        int day = this.getIntRecord(99999);
        int enter = this.getIntNoRecord(99998);
        if (enter > 0 && this.getDay() != day) {
            this.setTodayOnlineTime(0);
            this.initTodayOnlineTime();
        }

        this.updateTodayDate();
        this.updataEnterShop(false);
    }

    public int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(5);
    }

    public void updataEnterShop(boolean enter) {
        this.getQuestNAdd(MapleQuest.getInstance(99998)).setCustomData(enter ? String.valueOf(1) : String.valueOf(0));
    }

    public Calendar getDaybyDay(int n2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5) + n2);
        calendar.set(11, 0);
        calendar.set(9, 0);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }

    public void initOnlineTime() {
        if (this.lastOnlineTime < 0) {
            int o = StringTool.parseInt(this.getWorldShareInfo(900100, "date"));
            int fu = DateUtil.getDate();
            int o2 = 0;
            if (fu == o) {
                o2 = StringTool.parseInt(this.getWorldShareInfo(900100, "OnlineTime"));
            } else {
                this.clearOnlineTime();
            }

            this.lastOnlineTime = Math.max(0, o2);
        }

    }

    public void saveOnlineTime() {
        this.updateWorldShareInfo(900100, "OnlineTime", String.valueOf(this.getOnlineTime()), false);
        this.updateWorldShareInfo(900100, "date", String.valueOf(DateUtil.getDate()), false);
    }

    public void clearOnlineTime() {
        this.updateWorldShareInfo(900100, "OnlineTime", "0", false);
        this.updateWorldShareInfo(900100, "date", String.valueOf(DateUtil.getDate()), false);
    }

    public int getOnlineTime() {
        return this.getNowOnlineTime() + this.lastOnlineTime;
    }

    public int getNowOnlineTime() {
        if (this.lastOnlineTime < 0) {
            this.lastOnlineTime = 0;
        }

        long today = this.getDaybyDay(0).getTimeInMillis();
        if (this.logintime < today) {
            this.logintime = today;
            this.clearOnlineTime();
            this.lastOnlineTime = 0;
        }

        return (int)(System.currentTimeMillis() - this.logintime) / '\uea60';
    }

    public long getLoginTime() {
        return this.logintime;
    }

    public int getLastOnlineTime() {
        return this.lastOnlineTime;
    }

    public void setLastOnlineTime(int onlineTime) {
        this.lastOnlineTime = onlineTime;
    }

    public long getLTime() {
        return this.ltime;
    }

    public void setLTime() {
        this.ltime = System.currentTimeMillis();
    }

    public void updateTodayDate() {
        this.getQuestNAdd(MapleQuest.getInstance(99999)).setCustomData(String.valueOf(this.getDay()));
    }

    public int getTodayOnlineTime() {
        return this.todayonlinetime + (int)((System.currentTimeMillis() - this.todayonlinetimestamp.getTime()) / 60000L);
    }

    public void setTodayOnlineTime(int time) {
        this.todayonlinetime = time;
    }

    public int getTotalOnlineTime() {
        return this.totalonlinetime + (int)((System.currentTimeMillis() - this.todayonlinetimestamp.getTime()) / 60000L);
    }

    public void initTodayOnlineTime() {
        this.todayonlinetimestamp = new Timestamp(System.currentTimeMillis());
    }

    public void finishActivity(int questid) {
        MapleActivity.finish(this, questid);
    }

    public final void openNpc(int id) {
        this.openNpc(id, (String)null);
    }

    public final void openNpc(int id, String mode) {
        MapleNPC npc = MapleLifeFactory.getNPC(id, this.mapid);
        if (npc != null) {
            this.getClient().removeClickedNPC();
            this.getScriptManager().dispose();
            this.getScriptManager().stop(this.getScriptManager().getLastActiveScriptType());
            this.getScriptManager().startNpcScript(id, 0, mode);
        }

    }

    public void updateVisitorKills(int n2, int n3) {
        this.client.announce(MaplePacketCreator.updateVisitorKills(n2, n3));
    }

    public void changeDamageSkin(int id) {
        MapleQuest q = MapleQuest.getInstance(7291);
        if (q != null) {
            MapleQuestStatus status = this.getQuestNAdd(q);
            status.setStatus((byte)1);
            status.setCustomData(String.valueOf(id));
            this.updateQuest(status, true);
            this.send(InventoryPacket.UserDamageSkinSaveResult(4, 0, this));
            this.send(InventoryPacket.UserDamageSkinSaveResult(2, 0, this));
            this.send(InventoryPacket.showDamageSkin(this.getId(), this.getDamageSkin()));
            this.dropMessage(-9, "傷害字型已變更。");
        }
    }

    public int getDamageSkin() {
        if (JobConstants.is神之子(this.job)) {
            return 0;
        } else {
            String data = this.getQuestNAdd(MapleQuest.getInstance(7291)).getCustomData();
            return data == null ? 0 : Integer.parseInt(data);
        }
    }

    public final void insertRanking(String rankingname, int value) {
        RankingTop.getInstance().insertRanking(this, rankingname, value);
    }

    public synchronized Channel getChatSession() {
        return this.chatSession;
    }

    public void setChatSession(Channel session) {
        this.chatSession = session;
    }

    public int getWeaponPoint() {
        return this.weaponPoint;
    }

    public void setWeaponPoint(int wp) {
        this.weaponPoint = wp;
        this.client.announce(MaplePacketCreator.showGainWeaponPoint(wp));
        this.client.announce(MaplePacketCreator.updateWeaponPoint(this.getWeaponPoint()));
    }

    public void gainWeaponPoint(int wp) {
        this.weaponPoint += wp;
        if (wp > 0) {
            this.client.announce(MaplePacketCreator.showGainWeaponPoint(wp));
        }

        this.client.announce(MaplePacketCreator.updateWeaponPoint(this.getWeaponPoint()));
    }

    public Map<String, Integer> getCredits() {
        return this.credit;
    }

    public void setCredit(String name, int value) {
        this.credit.put(name, value);
    }

    public void gainCredit(String name, int value) {
        this.credit.put(name, this.getCredit(name) + value);
    }

    public int getCredit(String name) {
        return this.credit.containsKey(name) ? (Integer)this.credit.get(name) : 0;
    }

    public int getHayatoPoint() {
        return this.specialStats.getHayatoPoint();
    }

    public void setHayatoPoint(int jianqi) {
        this.specialStats.setHayatoPoint(jianqi);
        this.getClient().announce(MaplePacketCreator.updateHayatoPoint(this.specialStats.getHayatoPoint()));
    }

    public boolean checkSoulWeapon() {
        Equip weapon = (Equip)this.getInventory(MapleInventoryType.EQUIPPED).getItem((short)-11);
        return weapon != null && weapon.getSoulSocketID() != 0;
    }

    public long getLastFullSoulMP() {
        return this.lastChangeFullSoulMP;
    }

    public int getSoulMP() {
        return this.soulMP;
    }

    public void setSoulMP(int soulcount) {
        int oldSoulMP = this.soulMP;
        this.soulMP = Math.min(Math.max(0, soulcount), this.maxSoulMP);
        if (this.client != null) {
            if (this.maxSoulMP <= 0 && soulcount <= 0) {
                this.client.announce(BuffPacket.temporaryStatReset(Collections.singletonList(SecondaryStat.SoulMP), this));
            } else {
                this.client.announce(BuffPacket.giveBuff(this, this.getSkillEffect(this.soulSkillID), Collections.singletonMap(SecondaryStat.SoulMP, this.soulSkillID)));
                if (this.soulMP <= oldSoulMP && soulcount <= this.maxSoulMP) {
                    if (this.soulMP < oldSoulMP && oldSoulMP >= this.maxSoulMP) {
                        this.checkSoulState(false);
                    }
                } else {
                    this.lastChangeFullSoulMP = System.currentTimeMillis();
                }
            }
        }

    }

    public void addSoulMP(int count) {
        this.setSoulMP(this.soulMP + count);
    }

    public int getSoulSkillID() {
        return this.soulSkillID;
    }

    public void setSoulSkillID(int soulSkillID) {
        this.soulSkillID = soulSkillID;
    }

    public short getSoulOption() {
        return this.soulOption;
    }

    public void setSoulOption(short soulOption) {
        this.soulOption = soulOption;
    }

    public boolean isShowSoulEffect() {
        return this.showSoulEffect;
    }

    public void setShowSoulEffect(boolean showSoulEffect) {
        this.showSoulEffect = showSoulEffect;
        if (showSoulEffect) {
            if (this.client != null) {
                this.client.announce(BuffPacket.giveBuff(this, this.getSkillEffect(this.soulSkillID), Collections.singletonMap(SecondaryStat.FullSoulMP, this.soulSkillID)));
            }

            if (this.map != null) {
                this.map.broadcastMessage(this, BuffPacket.giveForeignBuff(this, new HashMap(Collections.singletonMap(SecondaryStat.FullSoulMP, this.soulMP))), false);
            }
        } else {
            if (this.client != null) {
                this.client.announce(BuffPacket.temporaryStatReset(Collections.singletonList(SecondaryStat.FullSoulMP), this));
            }

            if (this.map != null) {
                this.map.broadcastMessage(this, BuffPacket.cancelForeignBuff(this, List.of(SecondaryStat.FullSoulMP)), false);
            }
        }

    }

    public int getMaxSoulMP() {
        return this.maxSoulMP;
    }

    public void setMaxSoulMP(int maxSoulMP) {
        this.maxSoulMP = maxSoulMP;
    }

    public void checkSoulState(boolean useskill) {
        MapleStatEffect effect = this.getSkillEffect(this.soulSkillID);
        if (effect != null) {
            if (useskill && !ServerConfig.JMS_SOULWEAPON_SYSTEM && this.getSoulMP() >= effect.getSoulMpCon()) {
                this.addSoulMP((short)(-effect.getSoulMpCon()));
            }

            this.setShowSoulEffect(this.getSoulMP() >= (ServerConfig.JMS_SOULWEAPON_SYSTEM ? this.maxSoulMP : effect.getSoulMpCon()));
        }

    }

    public void handleSoulMP(MapleMonster mob) {
        if (this.checkSoulWeapon()) {
            MapleQuest q;
            MapleQuestStatus status;
            if ((q = MapleQuest.getInstance(26535)) == null || (status = this.getQuestNoAdd(q)) == null || status.getCustomData() == null || status.getCustomData().equalsIgnoreCase("effect=1")) {
                MapleMapItem item;
                (item = new MapleMapItem(new Item(4001536, (short)1, (short)1), new Point(mob.getPosition()), mob, this, (byte)0, false, 0)).setPickedUp(true);
                item.setEnterType((byte)0);
                item.setDelay(0);
                item.setPickUpID(this.getId());
                this.map.spawnMapObject(-1, item, (byte[])null);
                item.setEnterType((byte)2);
                item.setAnimation(2);
                this.map.disappearMapObject(item);
            }

            if (this.isAdmin() && this.isInvincible()) {
            }

            this.addSoulMP(Randomizer.rand(4, 5) * (this.isAdmin() && this.isInvincible() ? 10 : 1));
        }

    }

    public void handleHoYoungValue(int runeDiff, int scrollDiff) {
        if (JobConstants.is虎影(this.job)) {
            if (this.isDebug()) {
                runeDiff = runeDiff > 0 ? 100 : runeDiff;
                scrollDiff = scrollDiff > 0 ? 900 : scrollDiff;
            }

            this.specialStats.gainHoYoungRune(runeDiff);
            this.specialStats.gainHoYoungScroll(scrollDiff);
            this.client.announce(BuffPacket.setHoYoungRune(this));
        }
    }

    public int handleHoYoungState(int type) {
        if (!JobConstants.is虎影(this.job)) {
            return 0;
        } else {
            switch (type) {
                case 1 -> this.specialStats.setHoYoungState1(1);
                case 2 -> this.specialStats.setHoYoungState2(1);
                case 3 -> this.specialStats.setHoYoungState3(1);
            }

            int currentState = this.specialStats.getHoYoungState1() + this.specialStats.getHoYoungState2() + this.specialStats.getHoYoungState3();
            if (currentState >= this.getHoYoungMaxState()) {
                this.specialStats.setHoYoungState1(0);
                this.specialStats.setHoYoungState2(0);
                this.specialStats.setHoYoungState3(0);
                MapleStatEffect effect = this.getSkillEffect(164110013);
                if (effect != null) {
                    this.addHPMP(effect.getX(), effect.getY());
                }
            }

            this.client.announce(BuffPacket.setHoYoungState(this));
            return currentState;
        }
    }

    private int getHoYoungMaxState() {
        switch (this.job) {
            case 16400:
                return 1;
            case 16410:
                return 2;
            case 16411:
            case 16412:
                return 3;
            default:
                return 0;
        }
    }

    public void iNeedSystemProcess() {
        this.setLastCheckProcess(System.currentTimeMillis());
        this.getClient().announce(MaplePacketCreator.SystemProcess());
    }

    public long getLastCheckProcess() {
        return this.lastCheckProcess;
    }

    public void setLastCheckProcess(long lastCheckProcess) {
        this.lastCheckProcess = lastCheckProcess;
    }

    public List<MapleProcess> getProcess() {
        return this.Process;
    }

    public void write(Packet msg) {
        if (this.client != null) {
            this.client.write(msg);
        }

    }

    public String send(byte[] array) {
        if (this.client != null) {
            this.client.announce(array);
        }

        return null;
    }

    public void send_other(byte[] array, boolean b) {
        this.getMap().broadcastMessage(this, array, b);
    }

    public List<Integer> getEffectSwitch() {
        return this.effectSwitch;
    }

    public void updateEffectSwitch(int pos) {
        Iterator var2 = this.effectSwitch.iterator();

        Integer poss;
        do {
            if (!var2.hasNext()) {
                this.effectSwitch.add(pos);
                return;
            }

            poss = (Integer)var2.next();
        } while(poss != pos);

        this.effectSwitch.remove(poss);
    }

    public void gainPP(int pp) {
        this.specialStats.gainPP(pp);
        this.client.announce(BuffPacket.showPP(this));
    }

    public int getMobKills() {
        return this.mobKills;
    }

    public void setMobKills(int mobKills) {
        this.mobKills = mobKills;
    }

    public long getLastMobKillTime() {
        return this.lastMobKillTime;
    }

    public void setLastMobKillTime(long lastMobKillTime) {
        this.lastMobKillTime = lastMobKillTime;
    }

    public MapleSigninStatus getSigninStatus() {
        return this.siginStatus;
    }

    public int getPQLog(String pqName) {
        return this.getPQLog(pqName, 0);
    }

    public int getPQLog(String pqName, int type) {
        return this.getPQLog(pqName, type, 1);
    }

    public int getDaysPQLog(String pqName, int day) {
        return this.getDaysPQLog(pqName, 0, day);
    }

    public int getDaysPQLog(String pqName, int type, int day) {
        return this.getPQLog(pqName, type, day);
    }

    public int getPQLog(String pqName, int type, int day) {
        return this.getPQLog(pqName, type, day, -1);
    }

    public int getPQLog(String pqName, int type, int day, int refreshHour) {
        if (refreshHour < 0) {
            refreshHour = ServerConfig.WORLD_REFRESH_TIME;
        }

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            int var26;
            try {
                int count = 0;
                PreparedStatement ps = con.prepareStatement("SELECT `count`,`time` FROM pqlog WHERE characterid = ? AND pqname = ?");

                try {
                    ps.setInt(1, this.id);
                    ps.setString(2, pqName);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            count = rs.getInt("count");
                            Timestamp timestamp = rs.getTimestamp("time");
                            rs.close();
                            ps.close();
                            if (type == 0) {
                                Calendar calendar = Calendar.getInstance();
                                if (timestamp != null) {
                                    calendar.setTimeInMillis(timestamp.getTime());
                                    calendar.add(7, day);
                                }

                                calendar.set(11, refreshHour);
                                calendar.set(12, 0);
                                calendar.set(13, 0);
                                calendar.set(14, 0);
                                if (calendar.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                                    count = 0;
                                    PreparedStatement psi = con.prepareStatement("UPDATE pqlog SET count = 0, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND pqname = ?");

                                    try {
                                        psi.setInt(1, this.id);
                                        psi.setString(2, pqName);
                                        psi.executeUpdate();
                                    } catch (Throwable var20) {
                                        if (psi != null) {
                                            try {
                                                psi.close();
                                            } catch (Throwable var18) {
                                                var20.addSuppressed(var18);
                                            }
                                        }

                                        throw var20;
                                    }

                                    if (psi != null) {
                                        psi.close();
                                    }
                                }
                            }
                        } else {
                            PreparedStatement pss = con.prepareStatement("INSERT INTO pqlog (characterid, pqname, count, type) VALUES (?, ?, ?, ?)");

                            try {
                                pss.setInt(1, this.id);
                                pss.setString(2, pqName);
                                pss.setInt(3, 0);
                                pss.setInt(4, type);
                                pss.executeUpdate();
                            } catch (Throwable var19) {
                                if (pss != null) {
                                    try {
                                        pss.close();
                                    } catch (Throwable var17) {
                                        var19.addSuppressed(var17);
                                    }
                                }

                                throw var19;
                            }

                            if (pss != null) {
                                pss.close();
                            }
                        }
                    } catch (Throwable var21) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var16) {
                                var21.addSuppressed(var16);
                            }
                        }

                        throw var21;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var22) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var15) {
                            var22.addSuppressed(var15);
                        }
                    }

                    throw var22;
                }

                if (ps != null) {
                    ps.close();
                }

                var26 = count;
            } catch (Throwable var23) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var14) {
                        var23.addSuppressed(var14);
                    }
                }

                throw var23;
            }

            if (con != null) {
                con.close();
            }

            return var26;
        } catch (SQLException var24) {
            SQLException e = var24;
            System.err.println("Error while get pqlog: " + String.valueOf(e));
            return -1;
        }
    }

    public int getDayOfWeekPQLog(String pqName, int day) {
        return this.getDayOfWeekPQLog(pqName, day, -1);
    }

    public int getDayOfWeekPQLog(String pqName, int day, int refreshHour) {
        if (refreshHour < 0) {
            refreshHour = ServerConfig.WORLD_REFRESH_TIME;
        }

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            int var25;
            try {
                int count = 0;
                PreparedStatement ps = con.prepareStatement("SELECT `count`,`time` FROM pqlog WHERE characterid = ? AND pqname = ?");

                try {
                    ps.setInt(1, this.id);
                    ps.setString(2, pqName);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            count = rs.getInt("count");
                            Timestamp timestamp = rs.getTimestamp("time");
                            rs.close();
                            ps.close();
                            if (day >= 0 && day <= 7) {
                                Calendar calendar = Calendar.getInstance();
                                if (timestamp != null) {
                                    calendar.setTimeInMillis(timestamp.getTime());
                                    if (day == 0) {
                                        calendar.add(7, 1);
                                    } else {
                                        if (day == 7) {
                                            day = 1;
                                        } else {
                                            ++day;
                                        }

                                        calendar.set(7, day);
                                        if (calendar.getTimeInMillis() <= timestamp.getTime()) {
                                            calendar.add(7, 7);
                                        }
                                    }
                                }

                                calendar.set(11, refreshHour);
                                calendar.set(12, 0);
                                calendar.set(13, 0);
                                calendar.set(14, 0);
                                if (calendar.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                                    count = 0;
                                    PreparedStatement psi = con.prepareStatement("UPDATE pqlog SET count = 0, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND pqname = ?");

                                    try {
                                        psi.setInt(1, this.id);
                                        psi.setString(2, pqName);
                                        psi.executeUpdate();
                                    } catch (Throwable var19) {
                                        if (psi != null) {
                                            try {
                                                psi.close();
                                            } catch (Throwable var17) {
                                                var19.addSuppressed(var17);
                                            }
                                        }

                                        throw var19;
                                    }

                                    if (psi != null) {
                                        psi.close();
                                    }
                                }
                            }
                        } else {
                            PreparedStatement pss = con.prepareStatement("INSERT INTO pqlog (characterid, pqname, count, type) VALUES (?, ?, ?, ?)");

                            try {
                                pss.setInt(1, this.id);
                                pss.setString(2, pqName);
                                pss.setInt(3, 0);
                                pss.setInt(4, 0);
                                pss.executeUpdate();
                            } catch (Throwable var18) {
                                if (pss != null) {
                                    try {
                                        pss.close();
                                    } catch (Throwable var16) {
                                        var18.addSuppressed(var16);
                                    }
                                }

                                throw var18;
                            }

                            if (pss != null) {
                                pss.close();
                            }
                        }
                    } catch (Throwable var20) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var15) {
                                var20.addSuppressed(var15);
                            }
                        }

                        throw var20;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var21) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var14) {
                            var21.addSuppressed(var14);
                        }
                    }

                    throw var21;
                }

                if (ps != null) {
                    ps.close();
                }

                var25 = count;
            } catch (Throwable var22) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var13) {
                        var22.addSuppressed(var13);
                    }
                }

                throw var22;
            }

            if (con != null) {
                con.close();
            }

            return var25;
        } catch (SQLException var23) {
            SQLException e = var23;
            System.err.println("Error while get pqlog: " + String.valueOf(e));
            return -1;
        }
    }

    public void setPQLog(String pqname) {
        this.setPQLog(pqname, 0);
    }

    public void setPQLog(String pqname, int type) {
        this.setPQLog(pqname, type, 1);
    }

    public void setPQLog(String pqname, int type, int count) {
        this.setPQLog(pqname, type, count, -1);
    }

    public void setPQLog(String pqname, int type, int count, int refresh) {
        this.setPQLog(pqname, type, count, -1, true);
    }

    public void setPQLog(String pqname, int type, int count, int refresh, boolean updateTime) {
        if (refresh < 0) {
            refresh = ServerConfig.WORLD_REFRESH_TIME;
        }

        int times = this.getPQLog(pqname, type, 1, refresh);

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps;
                if (updateTime) {
                    ps = con.prepareStatement("UPDATE pqlog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND pqname = ?");

                    try {
                        ps.setInt(1, times + count);
                        ps.setInt(2, type);
                        ps.setInt(3, this.id);
                        ps.setString(4, pqname);
                        ps.executeUpdate();
                    } catch (Throwable var15) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var13) {
                                var15.addSuppressed(var13);
                            }
                        }

                        throw var15;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                } else {
                    ps = con.prepareStatement("UPDATE pqlog SET count = ?, type = ? WHERE characterid = ? AND pqname = ?");

                    try {
                        ps.setInt(1, times + count);
                        ps.setInt(2, type);
                        ps.setInt(3, this.id);
                        ps.setString(4, pqname);
                        ps.executeUpdate();
                    } catch (Throwable var14) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var12) {
                                var14.addSuppressed(var12);
                            }
                        }

                        throw var14;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Throwable var16) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var11) {
                        var16.addSuppressed(var11);
                    }
                }

                throw var16;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var17) {
            SQLException sQLException = var17;
            System.err.println("Error while set pqlog: " + String.valueOf(sQLException));
        }

    }

    public void resetPQLog(String pqname) {
        this.resetPQLog(pqname, 0);
    }

    public void resetPQLog(String pqname, int type) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("UPDATE pqlog SET count = ?, type = ?, time = CURRENT_TIMESTAMP() WHERE characterid = ? AND pqname = ?");

                try {
                    ps.setInt(1, 0);
                    ps.setInt(2, type);
                    ps.setInt(3, this.id);
                    ps.setString(4, pqname);
                    ps.executeUpdate();
                } catch (Throwable var9) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var10) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var11) {
            SQLException e = var11;
            System.err.println("Error while reset pqlog: " + String.valueOf(e));
        }

    }

    public long getPQPoint() {
        return Long.parseLong(this.getOneInfo(7907, "point") != null ? this.getOneInfo(7907, "point") : "0");
    }

    public void gainPQPoint(long point) {
        this.updateOneInfo(7907, "point", String.valueOf(this.getPQPoint() + point));
    }

    public void gainQuestPoint(int questId, long point) {
        this.updateOneInfo(questId, "point", String.valueOf(Math.max(0L, (long)this.getQuestPoint(questId) + point)));
    }

    public void gainWorldShareQuestPoint(int questId, long point) {
        this.updateWorldShareInfo(questId, "point", String.valueOf(Math.max(0L, (long)this.getQuestPoint(questId) + point)));
    }

    public void doneSailQuestion() {
        for(int i2 = 0; i2 <= 4; ++i2) {
            MapleQuest.getInstance(17003 + i2).complete(this, 0, (Integer)null);
            MapleQuest.getInstance(17003 + i2).complete(this, 0);
        }

        this.updateInfoQuest(17008, "T=0;L=1;E=0");
    }

    public long getLogintime() {
        return this.logintime;
    }

    public void setLogintime(long logintime) {
        this.logintime = logintime;
    }

    public long getQuestDiffTime() {
        return (System.currentTimeMillis() - this.logintime) / 1000L / 60L;
    }

    public boolean isAttclimit() {
        return this.attclimit;
    }

    public void setAttclimit(boolean attclimit) {
        this.attclimit = attclimit;
    }

    public WeakReference<MapleReactor> getReactor() {
        return this.reactor;
    }

    public void setReactor(MapleReactor reactor) {
        this.reactor = new WeakReference(reactor);
    }

    public void sendEnableActions() {
        this.getClient().sendEnableActions();
    }

    public List<Integer> getDamSkinList() {
        return this.damSkinList;
    }

    public void initAllInfo() {
        String info = this.getWorldShareInfo(7);
        if (info == null || info.isEmpty()) {
            this.updateWorldShareInfo(7, "count=0;day=0;date=0");
            this.updateWorldShareInfo(7, "date", DateUtil.getFormatDate(System.currentTimeMillis() - 86400000L, "yyyyMMdd"));
        }

        String questInfo = this.getQuestInfo(502117, "date");
        String a1323 = DateUtil.getFormatDate(System.currentTimeMillis(), "yyyyMMdd");
        if (questInfo == null) {
            this.updateOneQuestInfo(502117, "count", "0");
            this.updateOneQuestInfo(502117, "urus", "0");
            this.updateOneQuestInfo(502117, "date", a1323);
        } else if (!a1323.equals(questInfo)) {
            this.updateOneQuestInfo(502117, "count", "0");
            this.updateOneQuestInfo(502117, "urus", "0");
            this.updateOneQuestInfo(502117, "date", a1323);
        }

    }

    public void initDamageSkinList() {
        String keyValue = this.getKeyValue("DAMAGE_SKIN");
        String count = this.getOneInfo(56829, "count");
        if (count == null || count.isEmpty()) {
            this.updateOneInfo(56829, "count", String.valueOf(ServerConfig.defaultDamageSkinSlot));
        }

        int n = count == null ? ServerConfig.defaultDamageSkinSlot : Integer.parseInt(count);
        if (keyValue != null && !keyValue.isEmpty()) {
            keyValue = keyValue.replace("1050", "115");
            String[] split = keyValue.split(",");

            for(int i = 0; i < split.length && i < n; ++i) {
                if (!split[i].isEmpty()) {
                    int skinId = Integer.parseInt(split[i]);
                    if (!this.getDamSkinList().contains(skinId)) {
                        this.getDamSkinList().add(skinId);
                    }
                }
            }
        }

        MapleQuest q = MapleQuest.getInstance(7291);
        if (q != null) {
            if ("1050".equals(this.getQuestNAdd(q).getCustomData())) {
                q.forceStart(this, 0, "115");
            }

        }
    }

    public Map<Integer, MapleHexaSkill> getHexaSkills() {
        return this.hexaSkills;
    }

    public Map<Integer, MapleHexaSkill> loadHexSkills() {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            Map var9;
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM hexaskills WHERE charid = ?");
                ps.setInt(1, this.getId());
                ResultSet rs = ps.executeQuery();

                while(true) {
                    if (!rs.next()) {
                        rs.close();
                        ps.close();
                        con.close();
                        var9 = this.hexaSkills;
                        break;
                    }

                    MapleHexaSkill mhs = new MapleHexaSkill(rs.getInt("id"), rs.getInt("skilllv"));
                    this.hexaSkills.put(rs.getInt("id"), mhs);
                }
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }

            return var9;
        } catch (SQLException var7) {
            SQLException ex = var7;
            ex.printStackTrace();
            return null;
        }
    }

    public void addHexaSkill(MapleHexaSkill mhs) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement pse = con.prepareStatement("INSERT INTO hexaskills (id, charid, skilllv) VALUES (?, ?, ?)");
                pse.setInt(1, mhs.getId());
                pse.setInt(2, this.getId());
                pse.setInt(3, mhs.getSkilllv());
                pse.executeUpdate();
                pse.close();
                this.hexaSkills.put(mhs.getId(), mhs);
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException ex = var7;
            ex.printStackTrace();
        }

    }

    public void updateHexaSkill(MapleHexaSkill mhs) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = null;
                ps = con.prepareStatement("UPDATE hexaskills SET skilllv = ? WHERE id = ? AND charid = ?");
                ps.setInt(1, mhs.getSkilllv());
                ps.setInt(2, mhs.getId());
                ps.setInt(3, this.getId());
                ps.executeUpdate();
                ps.close();
                ((MapleHexaSkill)this.hexaSkills.get(mhs.getId())).setSkilllv(mhs.getSkilllv());
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException ex = var7;
            ex.printStackTrace();
        }

    }

    public Map<Integer, MapleHexaStat> getHexaStats() {
        return this.hexaStats;
    }

    public Map<Integer, MapleHexaStat> loadHexStats() {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            Map var9;
            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM sixstats WHERE charid = ?");
                ps.setInt(1, this.getId());
                ResultSet rs = ps.executeQuery();

                while(true) {
                    if (!rs.next()) {
                        rs.close();
                        ps.close();
                        con.close();
                        var9 = this.hexaStats;
                        break;
                    }

                    MapleHexaStat mss = new MapleHexaStat(rs.getInt("solt"), rs.getInt("preset"));
                    mss.setStatPreset1(rs.getInt("p0stat1"), rs.getInt("p0stat1lv"), rs.getInt("p0stat2"), rs.getInt("p0stat2lv"), rs.getInt("p0stat3"), rs.getInt("p0stat3lv"));
                    mss.setStatPreset2(rs.getInt("p1stat1"), rs.getInt("p1stat1lv"), rs.getInt("p1stat2"), rs.getInt("p1stat2lv"), rs.getInt("p1stat3"), rs.getInt("p1stat3lv"));
                    this.hexaStats.put(rs.getInt("solt"), mss);
                }
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }

            return var9;
        } catch (SQLException var7) {
            SQLException ex = var7;
            ex.printStackTrace();
            return null;
        }
    }

    public void addHexaStats(MapleHexaStat mss) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement pse = con.prepareStatement("INSERT INTO sixstats (charid, preset, solt, p0stat1, p0stat1lv, p0stat2, p0stat2lv, p0stat3, p0stat3lv, p1stat1, p1stat1lv, p1stat2, p1stat2lv, p1stat3, p1stat3lv) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pse.setInt(1, this.getId());
                pse.setInt(2, mss.getPreset());
                pse.setInt(3, mss.getSolt());
                pse.setInt(4, mss.getMain0());
                pse.setInt(5, mss.getMain0Lv());
                pse.setInt(6, mss.getAddit0S1());
                pse.setInt(7, mss.getAddit0S1Lv());
                pse.setInt(8, mss.getAddit0S2());
                pse.setInt(9, mss.getAddit0S2Lv());
                pse.setInt(10, mss.getMain1());
                pse.setInt(11, mss.getMain1Lv());
                pse.setInt(12, mss.getAddit1S1());
                pse.setInt(13, mss.getAddit1S1Lv());
                pse.setInt(14, mss.getAddit1S2());
                pse.setInt(15, mss.getAddit1S2Lv());
                pse.executeUpdate();
                pse.close();
                this.hexaStats.put(mss.getSolt(), mss);
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException ex = var7;
            ex.printStackTrace();
        }

    }

    public void updateHexaStat(MapleHexaStat mss) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = null;
                ps = con.prepareStatement("UPDATE sixstats SET preset = ?, solt = ?, p0stat1 = ?, p0stat1lv = ? , p0stat2 = ?, p0stat2lv = ? , p0stat3 = ?, p0stat3lv = ?, p1stat1 = ?, p1stat1lv = ? , p1stat2 = ?, p1stat2lv = ? , p1stat3 = ?, p1stat3lv = ? WHERE charid = ?");
                ps.setInt(1, mss.getPreset());
                ps.setInt(2, mss.getSolt());
                ps.setInt(3, mss.getMain0());
                ps.setInt(4, mss.getMain0Lv());
                ps.setInt(5, mss.getAddit0S1());
                ps.setInt(6, mss.getAddit0S1Lv());
                ps.setInt(7, mss.getAddit0S2());
                ps.setInt(8, mss.getAddit0S2Lv());
                ps.setInt(9, mss.getMain1());
                ps.setInt(10, mss.getMain1Lv());
                ps.setInt(11, mss.getAddit1S1());
                ps.setInt(12, mss.getAddit1S1Lv());
                ps.setInt(13, mss.getAddit1S2());
                ps.setInt(14, mss.getAddit1S2Lv());
                ps.setInt(15, this.getId());
                ps.executeUpdate();
                ps.close();
                ((MapleHexaStat)this.hexaStats.get(mss.getSolt())).setStatPreset1(mss.getMain0(), mss.getMain0Lv(), mss.getAddit0S1(), mss.getAddit0S1Lv(), mss.getAddit0S2(), mss.getAddit0S2Lv());
                ((MapleHexaStat)this.hexaStats.get(mss.getSolt())).setStatPreset2(mss.getMain1(), mss.getMain1Lv(), mss.getAddit1S1(), mss.getAddit1S1Lv(), mss.getAddit1S2(), mss.getAddit1S2Lv());
            } catch (Throwable var6) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var5) {
                        var6.addSuppressed(var5);
                    }
                }

                throw var6;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var7) {
            SQLException ex = var7;
            ex.printStackTrace();
        }

    }

    public void addEdraSoul(int addEdraSoul) {
        int nowcount = this.getErda(0) + addEdraSoul;
        int addEdraCount = 0;
        int newEdraSoulCount;
        if (nowcount >= 1000) {
            newEdraSoulCount = nowcount % 1000;
            addEdraCount = nowcount / 1000;
        } else {
            newEdraSoulCount = nowcount;
        }

        int newErda = this.getErda(1) + addEdraCount;
        if (newErda > 20) {
            newErda = 20;
        }

        this.updateInfoQuest(1489, "0exp=" + newEdraSoulCount + ";0=" + newErda, true);
    }

    public void reduceEdra(int edra, int fragments) {
        int fragmentCount = this.getItemQuantity(4009547);
        if (fragmentCount - fragments < 0 && this.getErda(1) - edra < 0) {
            this.getClient().announce(CWvsContext.sendHexaActionResult(8, 4, 0, 0));
        } else {
            Item toRemove = this.getInventory(MapleInventoryType.ETC).findById(4009547);
            if (toRemove != null) {
                MapleInventoryManipulator.removeFromSlot(this.client, MapleInventoryType.ETC, toRemove.getPosition(), (short)fragments, false);
            }

            int var10002 = this.getErda(0);
            this.updateInfoQuest(1489, "0exp=" + var10002 + ";0=" + (this.getErda(1) - edra), true);
        }

    }

    public void gainErda(int itemid) {
        String msg = "";
        switch (itemid) {
            case 2636420:
                msg = "獲得靈魂艾爾達氣息10。";
                this.removeItem(2636420, 1);
                break;
            case 2636421:
                msg = "獲得靈魂艾爾達氣息200。";
                this.removeItem(2636421, 1);
                break;
            default:
                System.out.println("未知的gainErda物品:" + itemid);
                return;
        }

        this.getClient().announce(UIPacket.getSpecialTopMsg(msg, 3, 20, 9, 0));
        this.getClient().announce(EffectPacket.encodeUserEffectByPickUpItem(this, itemid));
    }

    public int getErda(int type) {
        if (Objects.equals(this.getInfoQuest(1489), "")) {
            return 0;
        } else {
            int edraSoul = Integer.parseInt(this.getQuestInfo(1489, "0exp"));
            int edra = Integer.parseInt(this.getQuestInfo(1489, "0"));
            return type == 0 ? edraSoul : edra;
        }
    }

    public List<MapleHyperStats> loadHyperStats(int pos) {
        List<MapleHyperStats> mhp = new LinkedList();

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM hyperstats WHERE charid = ? AND position = ?");
                ps.setInt(1, this.getId());
                ps.setInt(2, pos);
                ResultSet rs = ps.executeQuery();

                while(true) {
                    if (!rs.next()) {
                        rs.close();
                        ps.close();
                        con.close();
                        break;
                    }

                    mhp.add(new MapleHyperStats(pos, rs.getInt("skillid"), rs.getInt("skilllevel")));
                }
            } catch (Throwable var8) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (con != null) {
                con.close();
            }

            return mhp;
        } catch (SQLException var9) {
            SQLException ex = var9;
            ex.printStackTrace();
            return null;
        }
    }

    public MapleHyperStats addHyperStats(int position, int skillid, int skilllevel) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement pse = con.prepareStatement("INSERT INTO hyperstats (charid, position, skillid, skilllevel) VALUES (?, ?, ?, ?)");
                pse.setInt(1, this.getId());
                pse.setInt(2, position);
                pse.setInt(3, skillid);
                pse.setInt(4, skilllevel);
                pse.executeUpdate();
                pse.close();
                con.close();
            } catch (Throwable var8) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var9) {
            SQLException ex = var9;
            ex.printStackTrace();
            return null;
        }

        MapleHyperStats mhs = new MapleHyperStats(position, skillid, skilllevel);
        mhs.setPosition(position);
        mhs.setSkillid(skillid);
        mhs.setSkillLevel(skilllevel);
        return mhs;
    }

    public MapleHyperStats UpdateHyperStats(int position, int skillid, int skilllevel) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = null;
                ps = con.prepareStatement("UPDATE hyperstats SET skilllevel = ? WHERE charid = ? AND position = ? AND skillid = ?");
                ps.setInt(1, skilllevel);
                ps.setInt(2, this.getId());
                ps.setInt(3, position);
                ps.setInt(4, skillid);
                ps.executeUpdate();
                ps.close();
                con.close();
            } catch (Throwable var8) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var9) {
            SQLException ex = var9;
            ex.printStackTrace();
            return null;
        }

        MapleHyperStats mhs = new MapleHyperStats(position, skillid, skilllevel);
        mhs.setPosition(position);
        mhs.setSkillid(skillid);
        mhs.setSkillLevel(skilllevel);
        return mhs;
    }

    public void resetHyperStats(int position, int skillid) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM hyperstats WHERE charid = ? AND position = ?");
                ps.setInt(1, this.id);
                ps.setInt(2, position);
                ps.execute();
                ps.close();
                con.close();
            } catch (Throwable var7) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var8) {
            SQLException ex = var8;
            ex.printStackTrace();
        }

    }

    public int getHyPay(int type) {
        int pay = 0;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM hypay WHERE accname = ?");

                try {
                    ps.setString(1, this.getClient().getAccountName());
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            if (type == 1) {
                                pay = rs.getInt("pay");
                            } else if (type == 2) {
                                pay = rs.getInt("payUsed");
                            } else if (type == 3) {
                                pay = rs.getInt("pay") + rs.getInt("payUsed");
                            } else if (type == 4) {
                                pay = rs.getInt("payReward");
                            } else {
                                pay = 0;
                            }
                        } else {
                            PreparedStatement psu = con.prepareStatement("INSERT INTO hypay (accname, pay, payUsed, payReward) VALUES (?, ?, ?, ?)");

                            try {
                                psu.setString(1, this.getClient().getAccountName());
                                psu.setInt(2, 0);
                                psu.setInt(3, 0);
                                psu.setInt(4, 0);
                                psu.executeUpdate();
                            } catch (Throwable var13) {
                                if (psu != null) {
                                    try {
                                        psu.close();
                                    } catch (Throwable var12) {
                                        var13.addSuppressed(var12);
                                    }
                                }

                                throw var13;
                            }

                            if (psu != null) {
                                psu.close();
                            }
                        }
                    } catch (Throwable var14) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var11) {
                                var14.addSuppressed(var11);
                            }
                        }

                        throw var14;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var15) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var10) {
                            var15.addSuppressed(var10);
                        }
                    }

                    throw var15;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var16) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var9) {
                        var16.addSuppressed(var9);
                    }
                }

                throw var16;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var17) {
            SQLException e = var17;
            log.error("獲取儲值信息發生錯誤", e);
        }

        return pay;
    }

    public int addHyPay(int hypay) {
        int pay = this.getHyPay(1);
        int payUsed = this.getHyPay(2);
        int payReward = this.getHyPay(4);
        if (hypay > pay) {
            return -1;
        } else {
            try {
                Connection con = DatabaseConnectionEx.getInstance().getConnection();

                byte var7;
                try {
                    PreparedStatement ps = con.prepareStatement("UPDATE hypay SET pay = ? WHERE accname = ?");

                    try {
                        ps.setInt(1, pay - hypay);
                        ps.setString(2, this.getClient().getAccountName());
                        ps.executeUpdate();
                        var7 = 1;
                    } catch (Throwable var11) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                } catch (Throwable var12) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (con != null) {
                    con.close();
                }

                return var7;
            } catch (SQLException var13) {
                SQLException e = var13;
                log.error("加減儲值信息發生錯誤", e);
                return -1;
            }
        }
    }

    public int addPayUsed(int hypay) {
        int pay = this.getHyPay(1);
        int payUsed = this.getHyPay(2);
        int payReward = this.getHyPay(4);
        if (hypay > pay) {
            return -1;
        } else {
            try {
                Connection con = DatabaseConnectionEx.getInstance().getConnection();

                byte var7;
                try {
                    PreparedStatement ps = con.prepareStatement("UPDATE hypay SET payUsed = ? WHERE accname = ?");

                    try {
                        ps.setInt(1, payUsed + hypay);
                        ps.setString(2, this.getClient().getAccountName());
                        ps.executeUpdate();
                        var7 = 1;
                    } catch (Throwable var11) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                } catch (Throwable var12) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (con != null) {
                    con.close();
                }

                return var7;
            } catch (SQLException var13) {
                SQLException e = var13;
                log.error("加減儲值信息發生錯誤", e);
                return -1;
            }
        }
    }

    public int addPayReward(int hypay) {
        int pay = this.getHyPay(1);
        int payUsed = this.getHyPay(2);
        int payReward = this.getHyPay(4);
        if (hypay > pay) {
            return -1;
        } else {
            try {
                Connection con = DatabaseConnectionEx.getInstance().getConnection();

                byte var7;
                try {
                    PreparedStatement ps = con.prepareStatement("UPDATE hypay SET payReward = ? WHERE accname = ?");

                    try {
                        ps.setInt(1, payReward + hypay);
                        ps.setString(2, this.getClient().getAccountName());
                        ps.executeUpdate();
                        var7 = 1;
                    } catch (Throwable var11) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                } catch (Throwable var12) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (con != null) {
                    con.close();
                }

                return var7;
            } catch (SQLException var13) {
                SQLException e = var13;
                log.error("加減儲值信息發生錯誤", e);
                return -1;
            }
        }
    }

    public int delPayReward(int pay) {
        int payReward = this.getHyPay(4);
        if (pay <= 0) {
            return -1;
        } else if (pay > payReward) {
            return -1;
        } else {
            try {
                Connection con = DatabaseConnectionEx.getInstance().getConnection();

                byte var5;
                try {
                    PreparedStatement ps = con.prepareStatement("UPDATE hypay SET payReward = ? WHERE accname = ?");

                    try {
                        ps.setInt(1, payReward - pay);
                        ps.setString(2, this.getClient().getAccountName());
                        ps.executeUpdate();
                        var5 = 1;
                    } catch (Throwable var9) {
                        if (ps != null) {
                            try {
                                ps.close();
                            } catch (Throwable var8) {
                                var9.addSuppressed(var8);
                            }
                        }

                        throw var9;
                    }

                    if (ps != null) {
                        ps.close();
                    }
                } catch (Throwable var10) {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (Throwable var7) {
                            var10.addSuppressed(var7);
                        }
                    }

                    throw var10;
                }

                if (con != null) {
                    con.close();
                }

                return var5;
            } catch (SQLException var11) {
                SQLException ex = var11;
                log.error("加減消費獎勵信息發生錯誤", ex);
                return -1;
            }
        }
    }

    public void gainVCraftCore(int quantity) {
        String data = this.getOneInfo(1477, "count");
        if (data != null) {
            int count = Integer.parseInt(data);
            this.updateOneInfo(1477, "count", String.valueOf(count + quantity));
        } else {
            this.updateOneInfo(1477, "count", String.valueOf(quantity));
        }

    }

    public void check5thJobQuest() {
        if (this.getQuestStatus(1465) == 1 && this.ltime > 10000000L) {
            if (this.getQuestStatus(1474) == 1) {
                if (System.currentTimeMillis() - this.ltime >= 3600000L) {
                    this.setLTime();
                    this.updateInfoQuest(1470, "on=1;remain=0;exp=500000000");
                    this.forceCompleteQuest(1474);
                }
            } else if (this.getQuestStatus(1475) == 1) {
                if (System.currentTimeMillis() - this.ltime >= 3600000L) {
                    this.setLTime();
                    this.updateInfoQuest(1471, "on=1;remain=0;exp=500000000");
                    this.forceCompleteQuest(1475);
                }
            } else if (this.getQuestStatus(1476) == 1 && System.currentTimeMillis() - this.ltime >= 3600000L) {
                this.setLTime();
                this.updateInfoQuest(1472, "on=1;remain=0;exp=500000000");
                this.forceCompleteQuest(1476);
            }
        }

    }

    public Map<Integer, VCoreSkillEntry> getVCoreSkill() {
        return this.vCoreSkills;
    }

    public AtomicInteger getvCoreSkillIndex() {
        return this.vCoreSkillIndex;
    }

    public void setVCoreSkills(Map<Integer, VCoreSkillEntry> vCoreSkills) {
        this.vCoreSkills = vCoreSkills;
    }

    public void addVCoreSkill(VCoreSkillEntry ah2) {
        this.vCoreSkills.put(this.vCoreSkillIndex.getAndIncrement(), ah2);
        this.changed_vcores = true;
    }

    public void setVCoreSkillSlot(int coreid, int slot) {
        if (this.vCoreSkills.get(coreid) != null) {
            ((VCoreSkillEntry)this.vCoreSkills.get(coreid)).setSlot(slot);
            this.changed_vcores = true;
        }

    }

    public void removeVCoreSkill(int coreid) {
        if (this.vCoreSkills.get(coreid) != null) {
            ((VCoreSkillEntry)this.vCoreSkills.get(coreid)).setSlot(0);
            this.changed_vcores = true;
        }

    }

    public int getVCoreSkillLevel(int skillid) {
        int level = 0;
        Iterator var3 = this.vCoreSkills.values().iterator();

        while(var3.hasNext()) {
            VCoreSkillEntry vse = (VCoreSkillEntry)var3.next();

            for(int i = 1; i <= 3; ++i) {
                if (vse.getSlot() == 2 && vse.getSkill(i) == skillid) {
                    VMatrixSlot slot = (VMatrixSlot)this.vMatrixSlot.get(vse.getIndex());
                    if (slot != null) {
                        if (vse.getType() == 0 || vse.getType() == 2) {
                            return vse.getLevel() + slot.getExtend();
                        }

                        if (vse.getType() == 1) {
                            level += vse.getLevel() + slot.getExtend();
                        }
                    }
                }
            }
        }

        return level;
    }

    public boolean checkVCoreSkill(int skill) {
        Iterator var2 = this.vCoreSkills.values().iterator();

        VCoreSkillEntry entry;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            entry = (VCoreSkillEntry)var2.next();
        } while(entry.getSlot() != 2 || entry.getSkill(1) != skill);

        return true;
    }

    public final Map<Integer, VMatrixSlot> getVMatrixSlot() {
        return this.vMatrixSlot;
    }

    public final int getNextVMatrixSlot() {
        Iterator var1 = this.vMatrixSlot.values().iterator();

        VMatrixSlot slot;
        do {
            if (!var1.hasNext()) {
                return -1;
            }

            slot = (VMatrixSlot)var1.next();
        } while(slot.getIndex() >= 0);

        return slot.getSlot();
    }

    public final int getVMatrixPoint() {
        int n = Math.min(75, Math.max(0, this.level - 200));

        VMatrixSlot slot;
        for(Iterator var2 = this.vMatrixSlot.values().iterator(); var2.hasNext(); n -= slot.getExtend()) {
            slot = (VMatrixSlot)var2.next();
        }

        return Math.max(n, 0);
    }

    public int getRuneCoolDown() {
        int cooldown = this.getRuneUseCooldown();
        int cooldownAct = (int)(this.getRuneNextActionTime() - System.currentTimeMillis());
        return Math.max(cooldown, cooldownAct);
    }

    public int getRuneUseCooldown() {
        SecondaryStatValueHolder mbsvh = this.getBuffStatValueHolder(SecondaryStat.RuneStoneNoTime);
        return mbsvh == null ? 0 : mbsvh.getLeftTime();
    }

    public void setRuneNextUseTime(long time) {
        this.setKeyValue("RUNE_NEXT_USE_TIME", String.valueOf(System.currentTimeMillis() + time));
    }

    public boolean isBountyHunterCoolDown() {
        return this.getBountyHunterTime() - System.currentTimeMillis() >= 0L;
    }

    public long getBountyHunterCoolDown() {
        return Math.max(this.getBountyHunterTime() - System.currentTimeMillis(), 0L);
    }

    public long getBountyHunterTime() {
        if (this.getTempValues().get("BOUNTY_HUNTER_NEXT_TIME") == null) {
            this.getTempValues().put("BOUNTY_HUNTER_NEXT_TIME", System.currentTimeMillis());
        }

        return (Long)this.getTempValues().get("BOUNTY_HUNTER_NEXT_TIME");
    }

    public void setBountyHunterTime(long time) {
        this.getTempValues().put("BOUNTY_HUNTER_NEXT_TIME", System.currentTimeMillis() + time);
    }

    public boolean isFireWolfCoolDown() {
        return this.getFireWolfTime() - System.currentTimeMillis() >= 0L;
    }

    public long getFireWolfCoolDown() {
        return Math.max(this.getFireWolfTime() - System.currentTimeMillis(), 0L);
    }

    public long getFireWolfTime() {
        if (this.getTempValues().get("FIRE_WOLF_NEXT_TIME") == null) {
            this.getTempValues().put("FIRE_WOLF_NEXT_TIME", System.currentTimeMillis());
        }

        return (Long)this.getTempValues().get("FIRE_WOLF_NEXT_TIME");
    }

    public void setFireWolfTime(long time) {
        this.getTempValues().put("FIRE_WOLF_NEXT_TIME", System.currentTimeMillis() + time);
    }

    public long getRuneNextActionTime() {
        if (this.runeNextActionTime == 0L) {
            this.runeNextActionTime = System.currentTimeMillis();
        }

        return this.runeNextActionTime;
    }

    public void setRuneNextActionTime(long time) {
        this.runeNextActionTime = System.currentTimeMillis() + time;
    }

    public void sendDathCount() {
        if (this.getDeathCount() >= 0) {
            this.client.announce(MaplePacketCreator.IndividualDeathCountInfo(this.getId(), this.getDeathCount()));
        }

    }

    public void updateBuffEffect(MapleStatEffect effect, Map<SecondaryStat, Integer> map) {
        long time = System.currentTimeMillis() - this.getBuffStatValueHolder((SecondaryStat)map.keySet().iterator().next()).startTime;
        this.send(BuffPacket.giveBuff(this, effect, map));
    }

    public long getLastUseVSkillTime() {
        return this.lastUseVSkillTime;
    }

    public void setLastUseVSkillTime(long lastUseVSkillTime) {
        this.lastUseVSkillTime = lastUseVSkillTime;
    }

    public boolean checkVSkillTime(int seconds) {
        if (this.lastUseVSkillTime > System.currentTimeMillis() + 1000L * (long)seconds) {
            return false;
        } else {
            this.lastUseVSkillTime = System.currentTimeMillis();
            return true;
        }
    }

    public void handleAmmoClip(int n2) {
        if (JobConstants.is爆拳槍神(this.job)) {
            int n3 = Math.max(this.specialStats.getBullet() + n2, 0);
            this.specialStats.setBullet(Math.min(n3, this.specialStats.getMaxBullet()));
        }

    }

    public void handleCylinder(int n2) {
        if (JobConstants.is爆拳槍神(this.job)) {
            int n3 = Math.max(this.getCylinder() + n2, 0);
            this.specialStats.setCylinder(Math.min(n3, this.specialStats.getMaxBullet()));
        }

    }

    public int getCylinder() {
        return JobConstants.is爆拳槍神(this.job) ? this.specialStats.getCylinder() : 0;
    }

    public int getBullet() {
        return JobConstants.is爆拳槍神(this.job) ? this.specialStats.getBullet() : (JobConstants.is開拓者(this.job) ? 3 : 0);
    }

    public void handleChargeBlaster() {
        MapleStatEffect effect = SkillFactory.getSkill(37000010).getEffect(this.getSkillLevel(37000010));
        if (JobConstants.is爆拳槍神(this.job) && this.getBullet() <= 0 && effect != null) {
            effect.applyTo(this);
        }

    }

    public int getHurtHP() {
        return this.specialStats.getHurtHP();
    }

    public void handle忍耐之盾(int n2) {
        if (JobConstants.is爆拳槍神(this.job) && this.getEffectForBuffStat(SecondaryStat.RWBarrier) == null) {
            this.specialStats.setHurtHP(n2);
            MapleStatEffect effect = SkillFactory.getSkill(37000006).getEffect(this.getTotalSkillLevel(37000006));
            if (this.isAlive() && effect != null) {
                effect.applyTo(this, true);
            }
        }

    }

    public int getBuffValue(int i) {
        return this.buffValue;
    }

    public void setBuffValue(int buffValue) {
        this.buffValue = buffValue;
    }

    public long getLastComboTime() {
        return this.lastComboTime;
    }

    public void setLastComboTime(long lastComboTime) {
        this.lastComboTime = lastComboTime;
    }

    public HiredFisher getHiredFisher() {
        return this.hiredFisher;
    }

    public void setHiredFisher(HiredFisher hiredFisher) {
        this.hiredFisher = hiredFisher;
    }

    public void handleHayatoPoint(int n) {
        if (JobConstants.is劍豪(this.job) && this.level >= 10) {
            Skill skill1 = SkillFactory.getSkill(40011291);
            Skill skill2 = SkillFactory.getSkill(40011292);
            Skill skill3 = SkillFactory.getSkill(40011288);
            this.specialStats.addHayatoPoint(n);
            int grade = 1;
            int hayatoPoint = this.getHayatoPoint();
            if (hayatoPoint >= 200 && hayatoPoint < 400) {
                grade = 2;
            } else if (hayatoPoint >= 400 && hayatoPoint < 600) {
                grade = 3;
            } else if (hayatoPoint >= 600 && hayatoPoint < 1000) {
                grade = 4;
            } else if (hayatoPoint >= 1000) {
                grade = 5;
            }

            if (this.getBuffedIntValue(SecondaryStat.BladeStanceMode) > 1 && this.getBuffedIntValue(SecondaryStat.BladeStancePower) != grade) {
                skill2.getEffect(grade).applyTo(this);
                skill3.getEffect(grade).applyTo(this);
            } else if (n == -1 && this.getBuffedIntValue(SecondaryStat.BladeStanceMode) <= 0 || this.getBuffedIntValue(SecondaryStat.IndieIgnoreMobpdpR) > 0 && this.getBuffedIntValue(SecondaryStat.IndieIgnoreMobpdpR) / 5 != grade) {
                skill1.getEffect(grade).applyTo(this, true);
            }

            this.send(MaplePacketCreator.updateHayatoPoint(this.getHayatoPoint()));
        }

    }

    public void handlePPCount(int min) {
        if (JobConstants.is凱內西斯(this.job)) {
            if (this.getBuffedIntValue(SecondaryStat.KinesisPsychicOver) > 0 && min < 0) {
                min = Math.min((int)Math.ceil((double)min / 2.0), -1);
            }

            this.specialStats.setPP(Math.min(Math.max(0, this.specialStats.getPP() + min), 30));
            if (this.client != null) {
                this.client.announce(BuffPacket.showPP(this));
            }
        }

    }

    public List<MapleSummon> getAllLinksummon() {
        return this.allLinksummon;
    }

    public void addLinksummon(MapleSummon summon) {
        this.allLinksummon.add(summon);
    }

    public void removeLinksummon(MapleSummon summon) {
        if (this.allLinksummon.size() > 0) {
            this.allLinksummon.remove(summon);
        }

    }

    public void handelAngelReborn(MapleStatEffect effect) {
        if (effect != null) {
            int skillID = effect.getSourceId();
            int linkId = SkillConstants.getLinkedAttackSkill(skillID);
            effect = this.getSkillEffect(linkId);
            if (effect != null) {
                int prop = (Integer)effect.getInfo().get(MapleStatInfo.onActive);
                MapleStatEffect effect1;
                if (linkId == 65111100) {
                    effect1 = this.getSkillEffect(65120044);
                    if (effect1 != null) {
                        prop += 10;
                    }
                }

                if (prop > 0) {
                    effect1 = this.getSkillEffect(65000003);
                    if (effect1 != null) {
                        prop += effect1.getX();
                    }

                    MapleStatEffect effect2 = this.getSkillEffect(65110006);
                    if (effect2 != null && this.specialStats.getAngelReborn() >= 2) {
                        prop += effect2.getX();
                    }

                    boolean suc = Randomizer.isSuccess(prop);
                    MapleStatEffect effect3 = this.getSkillEffect(65120006);
                    if (effect3 != null && !suc) {
                        suc = Randomizer.isSuccess(effect3.getX());
                    }

                    if (this.isDebug()) {
                        this.dropDebugMessage(1, "[天使技能重生] 最終機率：" + prop + " 是否成功：" + suc);
                    }

                    if (suc) {
                        if (effect3 != null && Randomizer.isSuccess(50)) {
                            effect3.unprimaryPassiveApplyTo(this);
                        }

                        this.specialStats.resetAngelReborn();
                        this.client.announce(MaplePacketCreator.skillActive());
                        this.client.announce(EffectPacket.showResetOnStateForOnOffSkill(-1));
                        this.map.broadcastMessage(this, EffectPacket.showResetOnStateForOnOffSkill(this.id), false);
                        return;
                    }

                    if (this.getSkillEffect(65110006) != null) {
                        this.specialStats.gainAngelReborn();
                    }
                }

            }
        }
    }

    public final int addWreckages(Point point, int n) {
        this.wreckagesLock.lock();

        int var4;
        try {
            int dk = this.getSpecialStat().gainForceCounter();
            this.evanWreckages.add(new Triple(dk, System.currentTimeMillis() + (long)n, point));
            var4 = dk;
        } finally {
            this.wreckagesLock.unlock();
        }

        return var4;
    }

    public final Map<Integer, Point> getWreckagesMap() {
        this.wreckagesLock.lock();

        try {
            HashMap<Integer, Point> hashMap = new HashMap();
            Iterator var2 = this.evanWreckages.iterator();

            while(var2.hasNext()) {
                Triple<Integer, Long, Point> evanWreckage = (Triple)var2.next();
                if ((Long)evanWreckage.mid > System.currentTimeMillis()) {
                    hashMap.put((Integer)evanWreckage.left, (Point)evanWreckage.right);
                }
            }

            this.evanWreckages.clear();
            HashMap var7 = hashMap;
            return var7;
        } finally {
            this.wreckagesLock.unlock();
        }
    }

    public final List<Triple<Integer, Long, Point>> getEvanWreckages() {
        this.wreckagesLock.lock();

        List var1;
        try {
            var1 = this.evanWreckages;
        } finally {
            this.wreckagesLock.unlock();
        }

        return var1;
    }

    public final void cleanEvanWreckages() {
        this.wreckagesLock.lock();

        try {
            this.evanWreckages.clear();
        } finally {
            this.wreckagesLock.unlock();
        }

    }

    public int getJudgementStack() {
        return this.judgementStack;
    }

    public void setJudgementStack(int judgementStack) {
        this.judgementStack = judgementStack;
    }

    public final void incJudgementStack() {
        ++this.judgementStack;
    }

    public final void updateJudgementStack() {
        if (this.judgementStack > 0 && this.client != null) {
            if (this.isDebug()) {
                this.dropDebugMessage(1, "[judgement stack] : " + this.judgementStack);
            }

            this.client.announce(MaplePacketCreator.updateCardStack(this.judgementStack));
        }

    }

    public void applyXenonEnegy(int x) {
        this.setBuffStatValue(SecondaryStat.SurplusSupply, this.getBuffedIntValue(SecondaryStat.SurplusSupply) + x);
        this.getSkillEffect(30020232).unprimaryApplyTo(this, (Point)null, true);
    }

    public void applyHayatoStance(int addPoint) {
        if (this.isAdmin() && this.isInvincible() && addPoint > 0) {
            addPoint += 100;
        }

        this.specialStats.addHayatoPoint(addPoint);
        int hayatoPoint = this.specialStats.getHayatoPoint();
        int p = Math.min(hayatoPoint / 100, 1000);
        int level = p < 2 ? 1 : (p < 4 ? 2 : (p < 7 ? 3 : (p < 10 ? 4 : 5)));
        Integer value;
        Skill skill;
        if (this.getBuffedIntValue(SecondaryStat.BladeStanceMode) > 0) {
            value = this.getBuffedValue(SecondaryStat.BladeStancePower, 40011292);
            skill = SkillFactory.getSkill(40011292);
        } else {
            value = this.getBuffedValue(SecondaryStat.IndiePADR, 40011291);
            skill = SkillFactory.getSkill(40011291);
        }

        if (value == null || value != level) {
            skill.getEffect(level).unprimaryPassiveApplyTo(this);
        }

        this.client.announce(MaplePacketCreator.updateHayatoPoint(hayatoPoint));
    }

    public int getMobZoneState() {
        return this.mobZoneState;
    }

    public void setMobZoneState(int mobZoneState) {
        this.mobZoneState = mobZoneState;
    }

    public int getLarknessDiraction() {
        return this.larknessDiraction;
    }

    public void setLarknessDiraction(int larknessDiraction) {
        this.larknessDiraction = larknessDiraction;
    }

    public int getLarkness() {
        return this.larkness;
    }

    public void setLarkness(int larkness) {
        this.larkness = larkness;
    }

    public final void addLarkness(int n) {
        this.larkness = Math.min(10000, Math.max(0, this.larkness + n));
    }

    public final void updateLarknessStack() {
        if (this.client != null && this.map != null) {
            this.client.announce(BuffPacket.updateLuminousGauge(this.getLarkness(), this.getLarknessDiraction()));
        }

    }

    public boolean hasTruthGate() {
        return this.truthGate;
    }

    public void setTruthGate(boolean truthGate) {
        this.truthGate = truthGate;
    }

    public void setActiveEventNameTag(int index, int value) {
        this.updateOneInfo(14489, String.valueOf(index), String.valueOf(value));
    }

    public int getActiveEventNameTag(int index) {
        int intValue = -1;
        if (this.getOneInfo(14489, String.valueOf(index)) != null) {
            intValue = Integer.parseInt(this.getOneInfo(14489, String.valueOf(index)));
        }

        return intValue;
    }

    public CalcDamage getCalcDamage() {
        return this.calcDamage;
    }

    public int getLinkMobObjectID() {
        return this.linkMobObjectID;
    }

    public void setLinkMobObjectID(int linkMobObjectID) {
        this.linkMobObjectID = linkMobObjectID;
    }

    public void doHealPerTime() {
        if (this.anticheat.canNextHealHPMP() && (this.stats.recoverHP > 0 && this.stats.getHp() < this.stats.getCurrentMaxHP() || this.stats.recoverMP > 0 && this.stats.getMp() < this.stats.getCurrentMaxMP())) {
            this.addHPMP(this.stats.recoverHP, this.stats.recoverMP, false, this.stats.recoverHP > 0);
        }

        if (this.getStat().hpRecoverTime > 0 && this.anticheat.canNextHealHPMPS()) {
            this.anticheat.setNextHealHPMPS((long)this.getStat().hpRecoverTime);
            this.addHPMP(this.stats.getCurrentMaxHP() * this.stats.healHPR / 100, this.stats.getCurrentMaxMP() * this.stats.healMPR / 100, false, true);
        }

        if (this.getStat().mpcon_eachSecond < 0) {
            this.addHPMP(0, this.getStat().mpcon_eachSecond, false, false);
        }

    }

    public boolean gainVCoreSkill(int vcoreoid, int nCount, boolean fromMake) {
        if (this.getVCoreSkill().size() + nCount > 99999) {
            this.dropMessage(5, "無法再持有V核心。請透過強化或分解的方式減少持有中的V核心數量後再重新嘗試。");
            return false;
        } else {
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            VCoreDataEntry vcoredata = ii.getCoreData(vcoreoid);
            if (vcoredata == null) {
                this.dropMessage(1, "要製作的核心不存在。");
                return false;
            } else {
                if (fromMake) {
                    if (!vcoredata.haveJob(String.valueOf(this.getJob())) && !vcoredata.haveJob("all") && !vcoredata.haveJob(JobConstants.getJobBranchName(this.getJob()))) {
                        this.dropMessage(1, "要製作的核心不適用這個職業。");
                        return false;
                    }

                    if (vcoredata.isNotAbleCraft()) {
                        this.dropMessage(1, "不能製作這個核心。");
                        return false;
                    }
                }

                if (vcoredata.getType() == 2) {
                    this.dropMessage(1, "暫時不支援製作這個核心。");
                    return false;
                } else {
                    int skill1 = 0;
                    int skill2 = 0;
                    int skill3 = 0;
                    if (vcoredata.getConnectSkill().size() > 0) {
                        skill1 = (Integer)vcoredata.getConnectSkill().get(0);
                    }

                    StringBuilder sb = new StringBuilder();
                    if (vcoredata.getType() == 1) {
                        String skill1Name = "";
                        List<Pair<Integer, String>> list = ii.getCoreJobSkill(this.getJob());
                        Iterator var12 = list.iterator();

                        while(var12.hasNext()) {
                            Pair<Integer, String> skill = (Pair)var12.next();
                            if ((Integer)skill.getLeft() > 0 && (Integer)skill.getLeft() == skill1) {
                                skill1Name = (String)skill.getRight();
                                break;
                            }
                        }

                        for(int i = 0; i < nCount; ++i) {
                            sb.append("已獲得").append(skill1Name);
                            Collections.shuffle(list);
                            int selectedSkills = 0;
                            Iterator var14 = list.iterator();

                            while(var14.hasNext()) {
                                Pair<Integer, String> skill = (Pair)var14.next();
                                if ((Integer)skill.getLeft() > 0 && (Integer)skill.getLeft() != skill1 && selectedSkills < 2) {
                                    sb.append("/").append((String)skill.getRight());
                                    ++selectedSkills;
                                    if (selectedSkills == 1) {
                                        skill2 = (Integer)skill.getLeft();
                                    } else if (selectedSkills == 2) {
                                        skill3 = (Integer)skill.getLeft();
                                    }
                                }
                            }

                            this.addVCoreSkill(new VCoreSkillEntry(vcoreoid, 1, 0, skill1, skill2, skill3, -1L, 1, -1));
                            this.send(VCorePacket.updateVCoreList(this, false, 0, 0));
                            if (!fromMake) {
                                this.send(VCorePacket.showVCoreItemUseEffect(vcoreoid, 1, skill1, skill2, skill3));
                            }

                            sb.append(" 核心。");
                            if (i < nCount - 1) {
                                sb.append("\r\n");
                            }
                        }
                    } else {
                        for(int i = 0; i < nCount; ++i) {
                            this.addVCoreSkill(new VCoreSkillEntry(vcoreoid, 1, 0, skill1, skill2, skill3, -1L, 1, -1));
                            this.send(VCorePacket.updateVCoreList(this, false, 0, 0));
                            if (!fromMake) {
                                this.send(VCorePacket.showVCoreItemUseEffect(vcoreoid, 1, skill1, skill2, skill3));
                            }

                            sb.append("已獲得").append(vcoredata.getName()).append(" 核心。");
                            if (i < nCount - 1) {
                                sb.append("\r\n");
                            }
                        }
                    }

                    if (fromMake) {
                        if (nCount > 1) {
                            nCount = 0;
                            skill2 = 0;
                            skill3 = 0;
                        }

                        if (sb.length() > 0) {
                            this.send(MaplePacketCreator.multiLineMessage(UserChatMessageType.系統, sb.toString()));
                        }

                        this.send(VCorePacket.addVCoreSkillResult(vcoreoid, 1, skill1, skill2, skill3, nCount));
                    }

                    return true;
                }
            }
        }
    }

    public boolean gainRandVSkill(int nCoreType, boolean indieJob, boolean onlyJob) {
        if (this.getVCoreSkill().size() + 1 >= 32767) {
            this.dropMessage(1, "V核心已經達到最大值，無法再使用！");
            return false;
        } else {
            MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            int coreid = 0;
            int skill1 = 0;
            int skill2 = 0;
            int skill3 = 0;
            List<VCoreDataEntry> coreList = new ArrayList(ii.getCoreDatasByJob(nCoreType, String.valueOf(this.getJob()), indieJob));
            if (!Randomizer.isSuccess(40) && !onlyJob) {
                coreList.addAll(ii.getCoreDatasByJob(nCoreType, "all"));
                coreList.addAll(ii.getCoreDatasByJob(nCoreType, JobConstants.getJobBranchName(this.getJob())));
            }

            if (coreList.size() <= 0) {
                return false;
            } else {
                Collections.shuffle(coreList);
                if (nCoreType == 0) {
                    VCoreDataEntry entry = (VCoreDataEntry)coreList.get(Randomizer.nextInt(coreList.size()));
                    if (entry != null) {
                        coreid = entry.getId();
                        skill1 = (Integer)entry.getConnectSkill().get(0);
                    }
                } else if (nCoreType == 1) {
                    Iterator var13 = coreList.iterator();

                    while(var13.hasNext()) {
                        VCoreDataEntry entry = (VCoreDataEntry)var13.next();
                        if (entry.getConnectSkill().size() > 0 && (Integer)entry.getConnectSkill().get(0) > 0) {
                            coreid = entry.getId();
                            skill1 = (Integer)entry.getConnectSkill().get(0);
                            break;
                        }
                    }

                    List<Pair<Integer, String>> list = ii.getCoreJobSkill(this.getJob());
                    Collections.shuffle(list);
                    Iterator var15 = list.iterator();

                    while(var15.hasNext()) {
                        Pair<Integer, String> skill = (Pair)var15.next();
                        if ((Integer)skill.getLeft() > 0 && (Integer)skill.getLeft() != skill1 && (Integer)skill.getLeft() != skill2 && (Integer)skill.getLeft() != skill3 && SkillFactory.getSkill((Integer)skill.getLeft()) != null) {
                            if (skill2 == 0) {
                                skill2 = (Integer)skill.getLeft();
                            } else {
                                skill3 = (Integer)skill.getLeft();
                            }
                        }
                    }
                }

                if (coreid > 0) {
                    this.addVCoreSkill(new VCoreSkillEntry(coreid, 1, 0, skill1, skill2, skill3, -1L, 1, -1));
                    this.client.announce(VCorePacket.updateVCoreList(this, false, 0, 0));
                    this.client.announce(VCorePacket.showVCoreItemUseEffect(coreid, 1, skill1, skill2, skill3));
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public void resetVSkills() {
        Iterator var1 = this.getVCoreSkill().entrySet().iterator();

        while(true) {
            int k;
            VCoreSkillEntry v;
            do {
                do {
                    if (!var1.hasNext()) {
                        return;
                    }

                    Map.Entry<Integer, VCoreSkillEntry> entry = (Map.Entry)var1.next();
                    k = (Integer)entry.getKey();
                    v = (VCoreSkillEntry)entry.getValue();
                } while(v == null);
            } while(v.getSlot() != 2);

            VMatrixSlot slot = (VMatrixSlot)this.getVMatrixSlot().get(v.getIndex());
            if (slot != null && slot.getIndex() == v.getIndex()) {
                slot.setIndex(-1);
            }

            v.setIndex(-1);
            this.setVCoreSkillSlot(k, 1);

            for(int i = 1; i <= 3; ++i) {
                if (v.getSkill(i) > 0) {
                    this.changeSingleSkillLevel(v.getSkill(i), this.getVCoreSkillLevel(v.getSkill(i)) > 0 ? this.getVCoreSkillLevel(v.getSkill(i)) : -1, (byte)(v.getType() == 2 ? 1 : (v.getType() == 0 ? 25 : 50)));
                }
            }

            if (this.client != null) {
                this.client.announce(VCorePacket.updateVCoreList(this, true, 1, k));
            }
        }
    }

    public void extractVCores() {
        MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
        Iterator var2 = this.getVCoreSkill().entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Integer, VCoreSkillEntry> entry = (Map.Entry)var2.next();
            if (entry.getValue() != null && ((VCoreSkillEntry)entry.getValue()).getSlot() > 0 && (Integer)entry.getKey() != 40000000 && (Integer)entry.getKey() != 10000024) {
                this.removeVCoreSkill((Integer)entry.getKey());
                Triple<Integer, Integer, Integer> vcoredata = (Triple)ii.getVcores(((VCoreSkillEntry)entry.getValue()).getType()).get(((VCoreSkillEntry)entry.getValue()).getLevel());
                this.gainVCraftCore((((VCoreSkillEntry)entry.getValue()).getType() == 0 ? 140 : (((VCoreSkillEntry)entry.getValue()).getType() == 1 ? 70 : 250)) * (((VCoreSkillEntry)entry.getValue()).getExp() / (Integer)vcoredata.getMid() + 1));
            }
        }

        this.send(VCorePacket.updateVCoreList(this, true, 5, 0));
    }

    public void modifiedAvatar() {
        if (this.map != null) {
            this.map.broadcastMessage(this, MaplePacketCreator.updateCharLook(this), this.getPosition());
        }

    }

    public boolean isInGameCurNode() {
        return this.inGameCurNode;
    }

    public void setInGameCurNode(boolean inGameCurNode) {
        this.inGameCurNode = inGameCurNode;
    }

    public int getQuestPoint(int questid) {
        String info = this.getOneInfo(questid, "point");
        if (info == null) {
            info = this.getWorldShareInfo(questid, "point");
        }

        return info == null ? 0 : Integer.parseInt(info);
    }

    public final void setBuyLimit(int shopid, int itemid, int count, long resetTime) {
        NpcShopBuyLimit limit = (NpcShopBuyLimit)this.buyLimit.computeIfAbsent(shopid, NpcShopBuyLimit::new);
        limit.update(itemid, count, resetTime);
        this.changed_buylimit = true;
    }

    public Map<Integer, NpcShopBuyLimit> getBuyLimit() {
        return this.buyLimit;
    }

    public int getBuyLimit(int shopid, int itemId) {
        return !this.buyLimit.containsKey(shopid) ? 0 : ((NpcShopBuyLimit)this.buyLimit.get(shopid)).getCount(itemId);
    }

    public final void setAccountBuyLimit(int shopid, int itemid, int count, long resetTime) {
        NpcShopBuyLimit limit = (NpcShopBuyLimit)this.accountBuyLimit.computeIfAbsent(shopid, NpcShopBuyLimit::new);
        limit.update(itemid, count, resetTime);
        this.changed_accountbuylimit = true;
    }

    public Map<Integer, NpcShopBuyLimit> getAccountBuyLimit() {
        return this.accountBuyLimit;
    }

    public int getAccountBuyLimit(int shopid, int itemId) {
        return !this.accountBuyLimit.containsKey(shopid) ? 0 : ((NpcShopBuyLimit)this.accountBuyLimit.get(shopid)).getCount(itemId);
    }

    public final void checkBuyLimit() {
        Iterator<Map.Entry<Integer, NpcShopBuyLimit>> iterator = this.buyLimit.entrySet().iterator();

        Map.Entry buyLimitEntry;
        Iterator dataIterator;
        while(iterator.hasNext()) {
            buyLimitEntry = (Map.Entry)iterator.next();
            dataIterator = ((NpcShopBuyLimit)buyLimitEntry.getValue()).getData().entrySet().iterator();

            while(dataIterator.hasNext()) {
                if (((BuyLimitData)((Map.Entry)dataIterator.next()).getValue()).getCount() <= 0) {
                    dataIterator.remove();
                    if (!this.changed_buylimit) {
                        this.changed_buylimit = true;
                    }
                }
            }

            if (((NpcShopBuyLimit)buyLimitEntry.getValue()).getData().isEmpty()) {
                iterator.remove();
            }
        }

        iterator = this.accountBuyLimit.entrySet().iterator();

        while(iterator.hasNext()) {
            buyLimitEntry = (Map.Entry)iterator.next();
            dataIterator = ((NpcShopBuyLimit)buyLimitEntry.getValue()).getData().entrySet().iterator();

            while(dataIterator.hasNext()) {
                if (((BuyLimitData)((Map.Entry)dataIterator.next()).getValue()).getCount() <= 0) {
                    dataIterator.remove();
                    if (!this.changed_accountbuylimit) {
                        this.changed_accountbuylimit = true;
                    }
                }
            }

            if (((NpcShopBuyLimit)buyLimitEntry.getValue()).getData().isEmpty()) {
                iterator.remove();
            }
        }

    }

    public void dropAlertNotice(String s) {
        this.dropMessage(1, s);
    }

    public Map<Integer, Integer> getSoulCollection() {
        return this.soulCollection;
    }

    public Map<Integer, String> getMobCollection() {
        return this.mobCollection;
    }

    public void updateMobCollection(int questId, String s) {
        this.mobCollection.put(questId, s);
        this.changed_mobcollection = true;
        if (this.client != null) {
            MessageOption option = new MessageOption();
            option.setObjectId(questId);
            option.setText(s);
            this.client.announce(CWvsContext.sendMessage(38, option));
        }

    }

    public void updateMobCollection(int questId, String key, String value) {
        if (!this.mobCollection.containsKey(questId)) {
            this.updateMobCollection(questId, key + "=" + value);
        } else {
            String[] split = ((String)this.mobCollection.get(questId)).split(";");
            boolean b = false;
            StringBuilder sb = new StringBuilder();
            String[] array = split;
            int length = split.length;

            for(int i = 0; i < length; ++i) {
                String s3;
                String[] split2;
                if ((split2 = (s3 = array[i]).split("=")).length == 2) {
                    if (split2[0].equals(key)) {
                        sb.append(key).append("=").append(value);
                        b = true;
                    } else {
                        sb.append(s3);
                    }

                    sb.append(";");
                }
            }

            if (!b) {
                sb.append(key).append("=").append(value);
            }

            this.updateMobCollection(questId, b ? sb.substring(0, sb.toString().length() - 1) : sb.toString());
        }
    }

    public String getMobCollection(int n, String s) {
        if (!this.mobCollection.containsKey(n)) {
            return null;
        } else {
            String[] split;
            int length = (split = ((String)this.mobCollection.get(n)).split(";")).length;

            for(int i = 0; i < length; ++i) {
                String[] split2;
                if ((split2 = split[i].split("=")).length == 2 && split2[0].equals(s)) {
                    return split2[1];
                }
            }

            return null;
        }
    }

    public String getMobCollection(int n) {
        return this.mobCollection.containsKey(n) ? (String)this.mobCollection.get(n) : "";
    }

    public int getFreeMacrossTicket() {
        return 7 - this.getDaysPQLog("FreeMacrossTicket", 7);
    }

    public int getMacrossTicket() {
        String hg;
        if ((hg = this.getQuestNAdd(MapleQuest.getInstance(99997)).getCustomData()) == null) {
            hg = "0";
        }

        return Integer.parseInt(hg);
    }

    public void setMacrossTicket(int n) {
        this.getQuestNAdd(MapleQuest.getInstance(99997)).setCustomData(String.valueOf(n));
    }

    public int getFlameBeads() {
        return this.getSpecialStat().getFlameBeads();
    }

    public void setFlameBeads(int aDb) {
        this.getSpecialStat().setFlameBeads(aDb);
    }

    public int getPureBeads() {
        return this.getSpecialStat().getPureBeads();
    }

    public void setPureBeads(int aAc) {
        this.getSpecialStat().setPureBeads(aAc);
    }

    public void addPureBeads(int n) {
        if (n < 0 || this.getTotalBeads() < 5) {
            this.getSpecialStat().addPureBeads(n);
            if (this.client != null) {
                this.client.announce(BuffPacket.setPureBeads(this));
            }
        }

    }

    public void addFlameBeads(int n) {
        if (n < 0 || this.getTotalBeads() < 5) {
            this.getSpecialStat().addFlameBeads(n);
            if (this.client != null) {
                this.client.announce(BuffPacket.setFlameBeads(this));
            }
        }

    }

    public int getGaleBeads() {
        return this.getSpecialStat().getGaleBeads();
    }

    public void setGaleBeads(int aDc) {
        this.getSpecialStat().setGaleBeads(aDc);
    }

    public void addGaleBeads(int n) {
        if (n < 0 || this.getTotalBeads() < 5) {
            this.getSpecialStat().addGaleBeads(n);
            if (this.client != null) {
                this.client.announce(BuffPacket.setGaleBeads(this));
            }
        }

    }

    public int getAbyssBeads() {
        return this.getSpecialStat().getAbyssBeads();
    }

    public void setAbyssBeads(int aDd) {
        this.getSpecialStat().setAbyssBeads(aDd);
    }

    public void addAbyssBeads(int n) {
        if (n < 0 || this.getTotalBeads() < 5) {
            this.getSpecialStat().addAbyssBeads(n);
            if (this.client != null) {
                this.client.announce(BuffPacket.setAbyssBeads(this));
            }
        }

    }

    public int getTotalBeads() {
        return Math.min(5, this.getAbyssBeads() + this.getGaleBeads() + this.getFlameBeads() + this.getPureBeads());
    }

    public int getErosions() {
        return this.getSpecialStat().getErosions();
    }

    public void setErosions(int erosions) {
        this.getSpecialStat().setErosions(erosions);
    }

    public void addErosions(int n) {
        this.getSpecialStat().addErosions(n);
        if (this.client != null) {
            this.client.announce(BuffPacket.setErosions(this));
        }

    }

    public void sendNote(String name, String text) {
        MapleCharacterUtil.sendNote(MapleCharacterUtil.getIdByName(name), this.name, text, 0);
    }

    public void showNote() {
        MapleCharacterUtil.showNote(this);
    }

    public int getMeisterSkillEff() {
        int intValue = 0;
        if (this.getQuestInfo(25948, "E") != null) {
            intValue = Integer.parseInt(this.getQuestInfo(25948, "E"));
        }

        return intValue > 1 ? 10000 * (intValue + 9200) : 0;
    }

    public List<SecondaryStat> getTempStatsToRemove() {
        return this.tempStatsToRemove;
    }

    public int getAIFamiliarID() {
        return this.AIFamiliarID.getAndIncrement();
    }

    public void updatePlayerStats(Map<MapleStat, Long> map, boolean b) {
        this.updatePlayerStats(map, b, false);
    }

    public void updatePlayerStats(Map<MapleStat, Long> map, boolean b, boolean b2) {
        if (!b2) {
            this.stats.recalcLocalStats(this);
        }

        this.client.announce(MaplePacketCreator.updatePlayerStats(map, b, this));
    }

    public int getInnerRank() {
        byte rank = 0;
        InnerSkillEntry[] var2 = this.getInnerSkills();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            InnerSkillEntry ise = var2[var4];
            if (ise != null && ise.getRank() > rank) {
                rank = ise.getRank();
            }
        }

        return rank;
    }

    public void resetInnerSkill(byte path, int itemId, List<Integer> lockPosition, boolean temp, boolean maxLevel) {
        int innerSkillSize = this.getInnerSkillSize();
        int lines = innerSkillSize > 3 ? 3 : innerSkillSize;
        int maxRank = 3;
        if (itemId == 2702000 || itemId == 2702001 || itemId == 2702002) {
            maxRank = 2;
        }

        InnerAbillity ia = InnerAbillity.getInstance();
        int innerRank = this.getInnerRank();
        InnerSkillEntry newskill;
        int i;
        if (itemId != 2702003 && itemId != 2702004) {
            short upgradeRate;
            short downgradeRate;
            switch (innerRank) {
                case 0:
                    upgradeRate = 300;
                    downgradeRate = 0;
                    break;
                case 1:
                    upgradeRate = 100;
                    downgradeRate = 50;
                    break;
                case 2:
                    upgradeRate = 50;
                    downgradeRate = 500;
                    break;
                case 3:
                    upgradeRate = 0;
                    downgradeRate = 900;
                    break;
                default:
                    upgradeRate = 0;
                    downgradeRate = 0;
            }

            if (itemId > 0 && (itemId != 2702000 || itemId != 2702001 || itemId != 2702002)) {
                itemId = -1;
            }

            if (upgradeRate > Randomizer.nextInt(1000) && innerRank < maxRank && itemId > -2) {
                ++innerRank;
            }

            if (downgradeRate > Randomizer.nextInt(1000) && innerRank > 0 && itemId > -1) {
                --innerRank;
            }

            this.getTempInnerSkills().clear();

            for(i = 0; i < lines; ++i) {
                newskill = null;
                if (!lockPosition.contains(i + 1)) {
                    while(newskill == null) {
                        newskill = ia.renewSkill(innerRank, i + 1, i == lines - 1, maxLevel && i == 0);
                        if (newskill != null) {
                            Iterator var15 = (temp ? this.getTempInnerSkills() : Arrays.asList(this.getInnerSkills())).iterator();

                            while(var15.hasNext()) {
                                InnerSkillEntry ski = (InnerSkillEntry)var15.next();
                                if (ski != null && ski.getSkillId() == newskill.getSkillId()) {
                                    newskill = null;
                                    break;
                                }
                            }
                        }

                        if (newskill != null) {
                            if (temp) {
                                this.tempInnerSkills.add(newskill);
                            } else {
                                this.changeInnerSkill(this, path, newskill);
                            }
                        }
                    }
                }
            }
        } else {
            for(i = 0; i < lines; ++i) {
                newskill = null;
                if (!lockPosition.contains(i + 1)) {
                    int skillId = this.getInnerSkillIdByPos(i);
                    if (skillId != 0) {
                        i = innerRank * 10 - 30 + SkillFactory.getSkill(skillId).getMaxLevel();
                        this.changeInnerSkill(this, path, new InnerSkillEntry(skillId, maxLevel ? i : Randomizer.rand(i == 0 ? i - 9 : 1, i), (byte)(i + 1), (byte)innerRank, false));
                    }
                }
            }
        }

    }

    public final void addByItem(Item item) {
        MapleInventoryManipulator.addbyItem(this.getClient(), item);
    }

    public List<InnerSkillEntry> getTempInnerSkills() {
        return this.tempInnerSkills;
    }

    public ActionBarField.MapleFieldActionBar getActionBar() {
        return this.actionBar;
    }

    public void setActionBar(ActionBarField.MapleFieldActionBar actionBar) {
        this.actionBar = actionBar;
    }

    public AtomicLong getKillMonsterExp() {
        return this.killMonsterExp;
    }

    public void modifyMoonlightValue(int value) {
        this.setMoonlightValue(Math.max(0, Math.min(this.moonlightValue + value, 100)));
        if (this.client != null) {
            value = this.moonlightValue;
            MaplePacketLittleEndianWriter hh = new MaplePacketLittleEndianWriter();
            hh.writeShort(OutHeader.WILL_SET_MOON_GAUGE.getValue());
            hh.writeInt(value);
            this.client.announce(hh.getPacket());
        }

    }

    public int getMoonlightValue() {
        return this.moonlightValue;
    }

    public void setMoonlightValue(int moonlightValue) {
        this.moonlightValue = moonlightValue;
    }

    public SpecialChairTW getSpecialChairTW() {
        return this.specialChairTW;
    }

    public void setSpecialChairTW(SpecialChairTW specialChairTW) {
        this.specialChairTW = specialChairTW;
    }

    public SpecialChair getSpecialChair() {
        return this.specialChair;
    }

    public void setSpecialChair(SpecialChair specialChair) {
        this.specialChair = specialChair;
    }

    public Map<Integer, ForceAtomObject> getForceAtomObjects() {
        return (Map)this.getTempValues().computeIfAbsent("ForceAtom_OBJ", (v) -> {
            return new LinkedHashMap();
        });
    }

    public Map<String, Object> getTempValues() {
        return this.tempValues;
    }

    public void handleAdeleCharge(int diff) {
        if (JobConstants.is阿戴爾(this.getJob())) {
            MapleStatEffect effect = this.getSkillEffect(151120012);
            if (effect == null) {
                effect = this.getSkillEffect(151100017);
            }

            if (effect != null) {
                if (diff > 0) {
                    MapleStatEffect eff = this.getEffectForBuffStat(SecondaryStat.LWRestore);
                    if (eff != null) {
                        diff += diff * eff.getX() / 100;
                    }
                }

                if (this.getBuffedValue(SecondaryStat.LWSwordGauge) == null) {
                    this.getSkillEffect(151100017).applyTo(this, true);
                }

                int max = effect.getSourceId() == 151100017 ? 300 : 400;
                this.specialStats.setAdeleCharge(Math.max(0, Math.min(max, this.specialStats.getAdeleCharge() + diff)));
                this.getClient().announce(BuffPacket.setAdeleCharge(this));
            }

        }
    }

    public void handleMaliceCharge(int diff) {
        if (JobConstants.is凱殷(this.getJob())) {
            MapleStatEffect effect = this.getSkillEffect(63120000);
            if (effect == null) {
                effect = this.getSkillEffect(63101001);
            }

            if (effect != null) {
                if (diff > 0) {
                    MapleStatEffect eff = this.getEffectForBuffStat(SecondaryStat.LWRestore);
                    if (eff != null) {
                        diff += diff * eff.getX() / 100;
                    }
                }

                int max = effect.getV() * 100 + 100;
                if (diff > 0 && this.specialStats.getMaliceCharge() == max) {
                    return;
                }

                this.specialStats.setMaliceCharge(Math.max(0, Math.min(max, this.specialStats.getMaliceCharge() + diff)));
                if (this.isAdmin()) {
                    this.dropMessage(5, "[Malice Charge] Diff: " + diff + " Now: " + this.specialStats.getMaliceCharge());
                }

                this.getClient().announce(BuffPacket.setMaliceCharge(this));
            }

        }
    }

    public void handleAdeleObjectSword(MapleStatEffect effect, List<Integer> targets) {
        Map<Integer, ForceAtomObject> swordsMap = this.getForceAtomObjects();
        List<ForceAtomObject> createList = new ArrayList();
        List<ForceAtomObject> removeList = new ArrayList();
        Point pos = this.getPosition();
        if (this.isDebug()) {
            this.dropMessage(5, "[Adele Sword] Effect: " + String.valueOf(effect));
        }

        int count;
        int i;
        ForceAtomObject sword;
        int x;
        label161:
        switch (effect.getSourceId()) {
            case 151001001:
                count = 0;

                while(true) {
                    if (count >= 5) {
                        break label161;
                    }

                    boolean b = count % 2 == 1;
                    i = (count + 1) / 2;
                    sword = new ForceAtomObject(this.specialStats.gainForceCounter(), 0, count, this.getId(), i * 15 * (b ? -1 : 1), effect.getSourceId());
                    sword.Position = new Point(0, 1);
                    sword.ObjPosition = new Point(pos.x + i * 40 * (b ? -1 : 1), pos.y - 120 + i * 20);
                    sword.EnableDelay = 600;
                    sword.Expire = 2400;
                    sword.CreateDelay = i * 120;
                    if (!targets.isEmpty()) {
                        sword.Target = (Integer)targets.get(Randomizer.nextInt(targets.size()));
                    }

                    createList.add(sword);
                    swordsMap.put(sword.Idx, sword);
                    ++count;
                }
            case 151101007:
                List<ForceAtomObject> swords = new LinkedList();
                Iterator var13 = swordsMap.values().iterator();

                while(var13.hasNext()) {
                    sword = (ForceAtomObject)var13.next();
                    if (sword.SkillId == this.getJob()) {
                        swords.add(sword);
                    }
                }

                if (targets != null) {
                    for(x = 0; x < swords.size(); ++x) {
                        sword = (ForceAtomObject)swords.get(x);
                        if (sword != null) {
                            this.getMap().broadcastMessage(AdelePacket.ForceAtomObjectAttack(this.getId(), sword.Idx, x == 0 ? swords.size() / 2 : 0), this.getPosition());
                        }
                    }
                }
                break;
            case 151111003:
                if (this.getBuffedValue(SecondaryStat.LWCreation) != null && this.specialStats.getAdeleCharge() > 156) {
                    for(count = 0; count < 2; ++count) {
                        x = pos.x + Randomizer.rand(-50, 50);
                        sword = new ForceAtomObject(this.specialStats.gainForceCounter(), 7, 0, this.getId(), 0, effect.getSourceId());
                        sword.Position = new Point(pos.x + Randomizer.rand(-500, 500), pos.y + Randomizer.rand(-400, 50));
                        sword.ObjPosition = new Point(pos.x + Randomizer.rand(-500, 500), pos.y + Randomizer.rand(-400, 50));
                        sword.Expire = 100000;
                        if (!targets.isEmpty()) {
                            sword.Target = (Integer)targets.get(Randomizer.nextInt(targets.size()));
                        }

                        for(int j = 0; j < 3; ++j) {
                            sword.addX(x + Randomizer.rand(-50, 50));
                        }

                        createList.add(sword);
                        swordsMap.put(sword.Idx, sword);
                    }

                    this.handleAdeleCharge(-156);
                }
                break;
            case 400011108:
                for(count = 0; count < effect.getBulletCount(); ++count) {
                    sword = new ForceAtomObject(this.specialStats.gainForceCounter(), 8, 0, this.getId(), 0, effect.getSourceId());
                    sword.Position = new Point(pos.x + Randomizer.rand(-500, 500), pos.y + Randomizer.rand(-400, 50));
                    sword.ObjPosition = new Point(pos.x + Randomizer.rand(-500, 500), pos.y + Randomizer.rand(-400, 50));
                    sword.EnableDelay = 1320;
                    sword.Expire = effect.getY() * 1000;
                    if (!targets.isEmpty()) {
                        sword.Target = (Integer)targets.get(Randomizer.nextInt(targets.size()));
                    }

                    createList.add(sword);
                    swordsMap.put(sword.Idx, sword);
                }
        }

        if (this.getBuffedValue(SecondaryStat.LWCreation) == null) {
            Iterator<Map.Entry<Integer, ForceAtomObject>> iterator = swordsMap.entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry<Integer, ForceAtomObject> swordMap = (Map.Entry)iterator.next();
                if (((ForceAtomObject)swordMap.getValue()).SkillId == this.getJob()) {
                    removeList.add((ForceAtomObject)swordMap.getValue());
                    iterator.remove();
                }
            }
        } else if (this.getBuffedValue(SecondaryStat.LWSwordGauge) != null) {
            count = Math.min(6, this.specialStats.getAdeleCharge() / 100 * 2);
            List<ForceAtomObject> swords = new LinkedList();
            Iterator var19 = swordsMap.values().iterator();

            while(var19.hasNext()) {
                sword = (ForceAtomObject)var19.next();
                if (sword.SkillId == this.getJob()) {
                    swords.add(sword);
                }
            }

            for(i = 0; i < swords.size(); ++i) {
                sword = (ForceAtomObject)swords.get(i);
                if (i >= count) {
                    removeList.add((ForceAtomObject)swordsMap.remove(sword.Idx));
                } else if (sword == null) {
                    sword = new ForceAtomObject(this.specialStats.gainForceCounter(), i + 1, 0, this.getId(), 0, this.getJob());
                    sword.Position = new Point(pos.x - 100, pos.y + 100);
                    sword.ObjPosition = new Point(pos.x, pos.y);
                    swordsMap.put(sword.Idx, sword);
                    createList.add(sword);
                }
            }
        }

        if (!createList.isEmpty()) {
            this.getMap().broadcastMessage(AdelePacket.ForceAtomObject(this.getId(), createList, effect.getSourceId() == 400011108 ? 1 : 0), this.getPosition());
        }

    }

    public void playerIGDead() {
    }

    public long getAggressiveDamage() {
        return this.AggressiveDamage;
    }

    public int getAllForce() {
        return this.allForce;
    }

    public void setAllForce(int allForce) {
        this.allForce = allForce;
    }

    public final void showScreenEffect(String s) {
        this.send(FieldPacket.fieldEffect(FieldEffect.getFieldEffectScreen(s)));
    }

    public final void showTopScreenEffect(String s, int n) {
        this.send(FieldPacket.fieldEffect(FieldEffect.getFieldBackgroundEffectFromWz(s, n)));
    }

    public final void showScreenDelayedEffect(String s, int n) {
        this.send(FieldPacket.fieldEffect(FieldEffect.getOffFieldEffectFromWz(s, n)));
    }

    public final void showScreenAutoLetterBox(String s, int n) {
        this.send(FieldPacket.fieldEffect(FieldEffect.getFieldEffectFromWz(s, n)));
    }

    public final void setVCoreSkillChanged(boolean change) {
        this.changed_vcores = change;
    }

    public final void setSoulCollectionChanged(boolean change) {
        changed_soulcollection = change;
    }

    public final void setMobCollectionChanged(boolean change) {
        this.changed_mobcollection = change;
    }

    public final void setFamiliarsChanged(boolean change) {
        this.changed_familiars = change;
    }

    public SkillMacro[] getSkillMacros() {
        return this.skillMacros;
    }

    public int addAntiMacroFailureTimes() {
        return ++this.antiMacroFails;
    }

    public int getAntiMacroFailureTimes() {
        return this.antiMacroFails;
    }

    public void setAntiMacroFailureTimes(int times) {
        this.antiMacroFails = times;
    }

    public Map<Integer, List<Integer>> getSalon() {
        return this.salon;
    }

    public int[] getRuneStoneAction() {
        return this.runeStoneAction;
    }

    public void setRuneStoneAction(int[] action) {
        this.runeStoneAction = action;
    }

    public void updateReward() {
        List<MapleReward> rewards = new LinkedList();
        List<Integer> toRemove = new LinkedList();

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM rewards WHERE `accid` = ? OR (`accid` IS NULL AND `cid` = ?)");

                try {
                    ps.setInt(1, this.accountid);
                    ps.setInt(2, this.id);
                    ResultSet rs = ps.executeQuery();

                    try {
                        while(rs.next() && rewards.size() < 200) {
                            if (rs.getLong("end") > 0L && rs.getLong("end") <= System.currentTimeMillis()) {
                                toRemove.add(rs.getInt("id"));
                            } else {
                                rewards.add(new MapleReward(rs.getInt("id"), rs.getLong("start"), rs.getLong("end"), rs.getInt("type"), (long)rs.getInt("amount"), rs.getInt("itemId"), rs.getString("desc")));
                            }
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var13) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var14) {
            SQLException e = var14;
            log.error("Unable to update rewards: ", e);
        }

        Iterator var16 = toRemove.iterator();

        while(var16.hasNext()) {
            int i = (Integer)var16.next();
            this.deleteReward(i);
        }

        this.client.announce(MaplePacketCreator.updateReward(0, (byte)9, rewards, 9L));
    }

    public MapleReward getReward(int id) {
        MapleReward reward = null;

        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM rewards WHERE `id` = ? AND (`accid` = ? OR (`accid` IS NULL AND `cid` = ?))");

                try {
                    ps.setInt(1, id);
                    ps.setInt(2, this.accountid);
                    ps.setInt(3, this.id);
                    ResultSet rs = ps.executeQuery();

                    try {
                        if (rs.next()) {
                            reward = new MapleReward(rs.getInt("id"), rs.getLong("start"), rs.getLong("end"), rs.getInt("type"), (long)rs.getInt("amount"), rs.getInt("itemId"), rs.getString("desc"));
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var13) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var14) {
            SQLException e = var14;
            log.error("Unable to obtain reward information: ", e);
        }

        return reward;
    }

    public void addReward(boolean acc, int type, long amount, int item, String desc) {
        this.addReward(acc, 0L, 0L, type, amount, item, desc);
    }

    public void addReward(boolean acc, long start, long end, int type, long amount, int itemId, String desc) {
        addReward(acc ? this.accountid : 0, this.id, start, end, type, amount, itemId, desc);
    }

    public void deleteReward(int id) {
        try {
            Connection con = DatabaseConnectionEx.getInstance().getConnection();

            try {
                PreparedStatement ps = con.prepareStatement("DELETE FROM rewards WHERE `id` = ? AND (`accid` = ? OR (`accid` IS NULL AND `cid` = ?))");

                try {
                    ps.setInt(1, id);
                    ps.setInt(2, this.accountid);
                    ps.setInt(3, this.id);
                    ps.execute();
                } catch (Throwable var8) {
                    if (ps != null) {
                        try {
                            ps.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                if (ps != null) {
                    ps.close();
                }
            } catch (Throwable var9) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var6) {
                        var9.addSuppressed(var6);
                    }
                }

                throw var9;
            }

            if (con != null) {
                con.close();
            }
        } catch (SQLException var10) {
            SQLException e = var10;
            log.error("Unable to delete reward: ", e);
        }

    }

    public int getBurningChrType() {
        return this.burningChrType;
    }

    public void setBurningChrType(int type) {
        this.burningChrType = type;
    }

    public long getBurningChrTime() {
        return this.burningChrTime;
    }

    public void setBurningChrTime(long time) {
        this.burningChrTime = time;
    }

    public Map<Integer, Integer> getForeverBuffs() {
        Map<Integer, Integer> buffs = new LinkedHashMap();
        if (this.getKeyValue("ForeverBuffs") != null) {
            String[] var2 = this.getKeyValue("ForeverBuffs").split(",");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String s = var2[var4];
                if (!s.isEmpty()) {
                    buffs.put(Integer.parseInt(s.split("=")[0]), Integer.parseInt(s.split("=")[1]));
                }
            }
        }

        return buffs;
    }

    public void updateForeverBuffs(Map<Integer, Integer> buffs) {
        String buffsString = "";

        Map.Entry entry;
        for(Iterator var3 = buffs.entrySet().iterator(); var3.hasNext(); buffsString = buffsString + String.valueOf(entry.getKey()) + "=" + String.valueOf(entry.getValue()) + ",") {
            entry = (Map.Entry)var3.next();
        }

        this.setKeyValue("ForeverBuffs", buffsString.isEmpty() ? null : buffsString.substring(0, buffsString.length() - 1));
    }

    public boolean freeJobChange(int newJob) {
        if (JobConstants.is冒險家(this.job) && JobConstants.is冒險家(newJob) && newJob / 100 == this.job / 100) {
            String lastJobChangeDate = this.getQuestNAdd(MapleQuest.getInstance(25957)).getCustomData();
            if (lastJobChangeDate == DateUtil.getCurrentDate("yyyyMMdd")) {
                this.dropMessage(1, "今天已經自由轉職過了.自由轉職1天只能1次. 凌晨12點後請再試一次.");
                return false;
            } else {
                String sCount = this.getOneInfo(25946, "count");
                int count = sCount != null && !sCount.isEmpty() ? Integer.parseInt(sCount) : 0;
                this.updateOneQuestInfo(25946, "count", String.valueOf(count + 1));
                this.changeJob(newJob);
                MapleInventoryManipulator.addById(this.client, 2431849, 1, "自由轉職獲得");
                MapleQuest.getInstance(25957).forceStart(this, 0, DateUtil.getCurrentDate("yyyyMMdd"));
                MaplePacketLittleEndianWriter mplew = new MaplePacketLittleEndianWriter();
                mplew.writeOpcode(OutHeader.LP_JobFreeChangeResult);
                mplew.write(0);
                this.send(mplew.getPacket());
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean isOverMobLevelTip() {
        return this.overMobLevelTip;
    }

    public void setOverMobLevelTip(boolean val) {
        this.overMobLevelTip = val;
    }

    public int getInnerStormValue() {
        return (Integer)this.tempValues.getOrDefault("InnerStormValue", 0);
    }

    public boolean checkInnerStormValue() {
        if (this.getInventory(MapleInventoryType.EQUIPPED).findById(1113228) == null) {
            return false;
        } else {
            if (!this.anticheat.isAttacking()) {
                long lastTime = (Long)this.tempValues.getOrDefault("InnerStormLastTIme", 0L);
                long timeNow = System.currentTimeMillis();
                if (lastTime + 2000L < timeNow) {
                    this.modifyInnerStormValue(-2 * (int)Math.floor((double)((timeNow - lastTime) / 2000L)));
                }
            }

            return true;
        }
    }

    public void modifyInnerStormValue(int quantity) {
        int oldValue = this.getInnerStormValue();
        if (this.getInventory(MapleInventoryType.EQUIPPED).findById(1113228) != null) {
            this.tempValues.put("InnerStormValue", Math.min(Math.max(this.getInnerStormValue() + quantity, 0), 100));
            if (quantity > 0) {
                this.tempValues.put("InnerStormLastTIme", System.currentTimeMillis());
            }
        } else {
            this.tempValues.remove("InnerStormValue");
            this.tempValues.remove("InnerStormLastTIme");
        }

        int nowValue = this.getInnerStormValue();
        int attack = 0;
        if (nowValue != 0) {
            attack = 2 * nowValue + 8;
            attack = attack % 5 == 0 ? attack / 5 : -1;
        }

        if (attack >= 0) {
            this.send(BuffPacket.giveBuff(this, (MapleStatEffect)null, Collections.singletonMap(SecondaryStat.InnerStorm, attack)));
        }

        this.write(OverseasPacket.extraEquipResult(ExtraEquipResult.updateInnerStormValue(this.id, nowValue, nowValue == 0 ? 0 : (oldValue == 0 ? 1 : -1))));
    }

    public int getSelectedFamiliarTeamStat() {
        String sSelectedOption = this.getKeyValue("SelectedFamiliarTeamStat");
        return sSelectedOption == null ? 0 : Integer.valueOf(sSelectedOption);
    }

    public List<Short> getFamiliarTeamStats() {
        List<Short> listVal = new ArrayList();
        String sOptions = this.getKeyValue("FamiliarTeamStat");
        if (sOptions != null) {
            String[] var3 = sOptions.split(",");
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String option = var3[var5];
                if (option.matches("^\\d+$")) {
                    listVal.add(Short.valueOf(option));
                    if (listVal.size() >= 3) {
                        break;
                    }
                }
            }
        }

        if (listVal.size() < 3) {
            for(int i = listVal.size(); i < 3; ++i) {
                listVal.add(Short.valueOf((short)0));
            }
        }

        return listVal;
    }

    public boolean setSelectedFamiliarTeamStat(int selected) {
        if (selected >= 0 && selected <= 2) {
            List<Short> listVal = this.getFamiliarTeamStats();
            if (listVal.size() < selected + 1) {
                return false;
            } else {
                this.setKeyValue("SelectedFamiliarTeamStat", String.valueOf(selected));
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean changeFamiliarTeamStat(int optionIndex) {
        if (optionIndex >= 1 && optionIndex <= 21) {
            int selectedOption = this.getSelectedFamiliarTeamStat();
            List<Short> listVal = this.getFamiliarTeamStats();
            if (listVal.size() < selectedOption + 1 || selectedOption > 0 && (Short)listVal.get(selectedOption - 1) == 0) {
                return false;
            } else {
                listVal.remove(selectedOption);
                listVal.add(selectedOption, (short)optionIndex);
                String sOptions = "";

                short option;
                for(Iterator var5 = listVal.iterator(); var5.hasNext(); sOptions = sOptions + String.valueOf(option) + ",") {
                    option = (Short)var5.next();
                }

                this.setKeyValue("FamiliarTeamStat", sOptions.substring(0, sOptions.length() - 1));
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean isStopComboKill() {
        return this.stopComboKill;
    }

    public void setStopComboKill(boolean b) {
        this.stopComboKill = b;
        if (!b) {
            this.getCheatTracker().setLastAttackTime();
        }

    }

    public int getMapleUnionFightCoin() {
        String coin = this.getWorldShareInfo(18098, "coin");
        if (coin == null || coin.isEmpty()) {
            coin = "0";
            this.updateWorldShareInfo(18098, "coin=0");
        }

        return Integer.parseInt(coin);
    }

    public void checkMapleUnion(boolean onLoad) {
        String oneInfo;
        if (this.getLevel() >= 60) {
            oneInfo = this.getWorldShareInfo(18793, "q0");
            if ("".equals(oneInfo) || oneInfo == null || "1".equals(oneInfo) && this.getQuestStatus(16013) != 2) {
                this.updateWorldShareInfo(18793, "q0", "0");
                MapleQuest.getInstance(16013).reset(this);
            }
        }

        oneInfo = this.getWorldShareInfo(18771, "rank");
        if (oneInfo != null && this.getQuestStatus(16013) != 2) {
            oneInfo = null;
        }

        if (oneInfo == null) {
            oneInfo = "101";
            this.updateWorldShareInfo(18771, "rank", "101");
        }

        if (this.mapleUnion.getState() != Integer.valueOf(oneInfo)) {
            this.mapleUnion.setState(Integer.valueOf(oneInfo));
            this.mapleUnion.update();
        }

        int coin = this.getMapleUnionFightCoin();
        if (onLoad) {
            this.send(MaplePacketCreator.updateMapleUnion(this.getMapleUnion()));
        } else {
            this.send(MaplePacketCreator.openMapleUnion(coin, this.getMapleUnion()));
        }

        MapleUnionData data = MapleUnionData.getInstance();
        int state = this.mapleUnion.getState();
        int level = state / 100;
        int grade = state % 10;
        MapleUnionData.MapleUnionRankData nowRank = null;
        if (data.getRankInfo().containsKey(level) && ((Map)data.getRankInfo().get(level)).containsKey(grade)) {
            nowRank = (MapleUnionData.MapleUnionRankData)((Map)data.getRankInfo().get(level)).get(grade);
        }

        this.client.announce(MaplePacketCreator.getMapleUnionCoinInfo(nowRank != null && coin >= nowRank.getCoinStackMax() ? 0 : -1, nowRank == null ? 200 : nowRank.getCoinStackMax()));
    }

    public void gainMapleUnionPoint(int amount) {
        if (amount != 0) {
            String point;
            if (amount > 0) {
                point = this.getWorldShareInfo(18797, "PT");
                if (point == null || point.isEmpty() || !point.matches("^\\d+$")) {
                    point = "0";
                }

                this.updateWorldShareInfo(18797, "PT", String.valueOf(Integer.valueOf(point) + amount));
            }

            point = this.getWorldShareInfo(500629, "point");
            if (point == null || point.isEmpty() || !point.matches("^\\d+$")) {
                point = "0";
            }

            int nPoint = Integer.valueOf(point);
            nPoint += amount;
            if (nPoint < 0) {
                nPoint = 0;
            }

            this.updateWorldShareInfo(500629, "point", String.valueOf(nPoint));
        }
    }

    public void enableActions() {
        this.enableActions(true);
    }

    public void enableActions(boolean useTriggerForUI) {
        this.send(MaplePacketCreator.enableActions(this, useTriggerForUI));
    }

    public String toString() {
        return this.getName();
    }

    public boolean getDiseases() {
        return this.getDiseases();
    }

    public void modifySunlightValue(int value) {
        this.setSunlightValue(Math.max(0, Math.min(this.sunlightValue + value, 100)));
    }

    public int getSunlightValue() {
        return this.sunlightValue;
    }

    public void setSunlightValue(int sunlightValue) {
        this.sunlightValue = sunlightValue;
    }

    public boolean isDuskBlind() {
        return this.isDuskBlind;
    }

    public void setDuskBlind(boolean duskBlind) {
        this.isDuskBlind = duskBlind;
    }

    public int getDuskGauge() {
        return this.duskGauge;
    }

    public void setDuskGauge(int duskGauge) {
        this.duskGauge = duskGauge;
    }

    private byte[] intToByteArray(int value) {
        return new byte[]{(byte)(value >> 24), (byte)(value >> 16), (byte)(value >> 8), (byte)value};
    }

    public long getLastSpawnBlindMobTime() {
        return this.lastSpawnBlindMobtime;
    }

    public void setLastSpawnBlindMobTime(long lastSpawnBlindMobtime) {
        this.lastSpawnBlindMobtime = lastSpawnBlindMobtime;
    }

    public byte getDeathCount() {
        return this.deathcount;
    }

    public void setDeathCount(int deathcount) {
        this.deathcount = (byte)deathcount;
    }

    public void setDeathCount(byte de) {
        this.deathcount = de;
        this.getMap().broadcastMessage(CField.setDeathCount(this, this.deathcount));
        if (this.getMapId() != 450011990 && this.getMapId() == 262031300) {
            int deathcouint = 15 - this.getDeathCount();
            this.getClient().getSession().writeAndFlush(CWvsContext.onFieldSetVariable("TotalDeathCount", "15"));
            this.getClient().getSession().writeAndFlush(CWvsContext.onFieldSetVariable("DeathCount", String.valueOf(deathcouint)));
            MapleMonster hardHilla = this.getMap().getMobObjectByID(8870100);
            if (hardHilla != null) {
                int hillaSkill = 8877100;
                hardHilla.setForcedMobStat(hillaSkill, 1.0);
                new ArrayList();
            }
        }

        if (de > 0) {
            this.getMap().broadcastMessage(CField.showDeathCount(this, this.getDeathCount()));
        }

    }

    public int getDeathcount() {
        return this.deathcount;
    }

    public Object getSkillCustomValue(int skillid) {
        if (this.customInfo != null && this.customInfo.containsKey(skillid)) {
            if (skillid == 63111009 && this.customInfo.get(skillid) != null && ((SkillCustomInfo)this.customInfo.get(skillid)).getValue() <= 0L) {
                return null;
            } else if (this.customInfo.get(skillid) != null && ((SkillCustomInfo)this.customInfo.get(skillid)).getValue() < 0L) {
                return null;
            } else {
                return this.customInfo.get(skillid) != null ? ((SkillCustomInfo)this.customInfo.get(skillid)).getValue() : null;
            }
        } else {
            return null;
        }
    }

    public void setSkillCustomInfo(int skillid, long value, long time) {
        this.customInfo.put(skillid, new SkillCustomInfo(value, time));
    }

    public int getBlackMageWB() {
        return this.blackmagewb;
    }

    public void setBlackMageWB(int v) {
        this.blackmagewb = v;
    }

    public void dispelDebuffs() {
        new HashMap();
    }

    public int getMoonGauge() {
        return this.moonlightValue;
    }

    public void setMoonGauge(int lunaGauge) {
        this.moonlightValue = lunaGauge;
    }

    public int getSerenStunGauge() {
        return this.SerenStunGauge;
    }

    public void setSerenStunGauge(int SerenStunGauge) {
        this.SerenStunGauge = SerenStunGauge;
    }

    public void addSerenGauge(int add) {
        if (!this.getDiseases(SecondaryStat.MobFlashBang)) {
            this.SerenStunGauge += add;
            if (this.SerenStunGauge >= 1000) {
                this.SerenStunGauge = 0;
                EnumMap<SecondaryStat, Pair<Integer, Integer>> diseases = new EnumMap(SecondaryStat.class);
                diseases.put(SecondaryStat.GiveMeHeal, new Pair(1, 5000));
                diseases.put(SecondaryStat.MobFlashBang, new Pair(1, 5000));
                diseases.put(SecondaryStat.IgnorePriestDispel, new Pair(1, 5000));
                this.map.broadcastMessage(MaplePacketCreator.playSound("Sound/Field.img/SerenDeath/effect"));
                this.map.broadcastMessage(FieldPacket.fieldEffect(FieldEffect.getFieldEffectScreen("UI/UIWindow7.img/SerenDeath")));
                this.getBuffedValue(SecondaryStat.GiveMeHeal);
                MapleMonster seren;
                if (this.getMapId() == 410002060 && (seren = this.getMap().getMobObjectByID(8880602)) != null) {
                    seren.setSerenMidNightSetTotalTime(seren.getSerenMidNightSetTotalTime() - 1);
                    if (seren.getSerenMidNightSetTotalTime() <= 0) {
                        this.getMap().broadcastMessage(Seren.SerenTimer(1, new int[]{120, 120, 0, 120, seren.getSerenTimetype() == 4 ? -1 : 1}));
                        this.getMap().killAllMonsters(false);
                        Iterator var4 = this.getMap().getAllCharactersThreadsafe().iterator();

                        while(var4.hasNext()) {
                            MapleCharacter chr = (MapleCharacter)var4.next();
                            if (chr != null) {
                                chr.warpdelay(410000670, 7);
                            }
                        }
                    } else {
                        switch (seren.getSerenTimetype()) {
                            case 1:
                                seren.setSerenNoonNowTime(seren.getSerenNoonNowTime() + 1);
                                seren.setSerenNoonTotalTime(seren.getSerenNoonTotalTime() + 1);
                                break;
                            case 2:
                                seren.setSerenSunSetNowTime(seren.getSerenSunSetNowTime() + 1);
                                seren.setSerenSunSetTotalTime(seren.getSerenSunSetTotalTime() + 1);
                            case 3:
                            default:
                                break;
                            case 4:
                                seren.setSerenDawnSetNowTime(seren.getSerenDawnSetNowTime() + 1);
                                seren.setSerenDawnSetTotalTime(seren.getSerenDawnSetTotalTime() + 1);
                        }

                        if (seren.getSerenTimetype() != 3) {
                            seren.AddSerenTotalTimeHandler(seren.getSerenTimetype(), 5, seren.getSerenTimetype() == 4 ? -1 : 1);
                        }
                    }
                }
            }

            if (this.SerenStunGauge < 0) {
                this.SerenStunGauge = 0;
            }

            this.map.broadcastMessage(Seren.SerenUserStunGauge(1000, this.SerenStunGauge));
        }

    }

    public void warpdelay(int Mapid, int Delay) {
        MapTimer.getInstance().schedule(() -> {
            ChannelServer cserv = this.getClient().getChannelServer();
            MapleMap target = cserv.getMapFactory().getMap(Mapid);
            this.changeMap(target, target.getPortal(0));
        }, (long)Delay * 1000L);
    }

    public ScriptManager getScriptManager() {
        return this.scriptManager;
    }

    public AvatarData getAvatarData() {
        return new AvatarData(this);
    }

    public CharacterStat getCharacterStat() {
        return new CharacterStat(this);
    }

    public AvatarLook getAvatarLook() {
        return new AvatarLook(this, this.isBeta());
    }

    public AvatarLook getSecondAvatarLook() {
        return new AvatarLook(this, !this.isBeta());
    }

    public void dispose() {
        this.client.removeClickedNPC();
        this.enableActions(true);
    }

    public void clearAtomAttackRecords() {
        this.atomsAttackRecords.clear();
    }

    public Map<Integer, Integer> getAtomAttackRecords() {
        return this.atomsAttackRecords;
    }

    public int getAtomAttackRecord(int key) {
        return (Integer)this.atomsAttackRecords.getOrDefault(key, 0);
    }

    public void removeAtomAttackRecord(int key) {
        this.atomsAttackRecords.remove(key);
    }

    public void putAtomAttackRecord(int key, int value) {
        this.atomRecordsLock.lock();

        try {
            this.atomsAttackRecords.put(key, value);
        } finally {
            this.atomRecordsLock.unlock();
        }

    }

    public void incAtomAttackRecord(int key) {
        this.atomRecordsLock.lock();

        try {
            this.atomsAttackRecords.put(key, (Integer)this.atomsAttackRecords.getOrDefault(key, 0) + 1);
        } finally {
            this.atomRecordsLock.unlock();
        }

    }

    public void reduceAtomAttackRecord(int key) {
        this.atomRecordsLock.lock();

        try {
            this.atomsAttackRecords.put(key, (Integer)this.atomsAttackRecords.getOrDefault(key, 0) - 1);
        } finally {
            this.atomRecordsLock.unlock();
        }

    }

    public boolean isOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        boolean var10000 = online != this.online;
        this.online = online;
        if (this.getParty() != null) {
            PartyMember pm = this.getParty().getPartyMemberByID(this.getId());
            if (pm != null) {
                List<Integer> changedTypes = pm.getChangedTypes(online ? this : null);
                pm.setChr(online ? this : null);
                Iterator var5 = changedTypes.iterator();

                while(var5.hasNext()) {
                    int type = (Integer)var5.next();
                    this.getParty().broadcast(WvsContext.partyResult(PartyResult.setMemberData(pm, type)));
                }

                this.getParty().updateFull();
            }
        }

    }

    public void notifyChanges() {
        Party party = this.getParty();
        if (party != null) {
            party.updatePartyMemberInfoByChr(this);
            this.updatePartyMemberHP();
            this.receivePartyMemberHP();
        }

    }

    public void write(OutPacket outpacket) {
        this.send(outpacket.getData());
    }

    public int getBaseColor() {
        return this.basecolor;
    }

    public int getAddColor() {
        return this.addColor;
    }

    public int getBaseProb() {
        return this.baseProb;
    }

    public List<String> getScriptManagerDebug() {
        return this.scriptManagerDebug;
    }

    @Generated
    public Map<Integer, FuncKeyMap> getFuncKeyMaps() {
        return this.funcKeyMaps;
    }

    @Generated
    public Map<String, Object> getVariable() {
        return this.variable;
    }

    @Generated
    public void setVariable(Map<String, Object> variable) {
        this.variable = variable;
    }

    @Generated
    public int getSeparation() {
        return this.separation;
    }

    @Generated
    public void setSeparation(int separation) {
        this.separation = separation;
    }

    @Generated
    public boolean isGmcooldown() {
        return this.gmcooldown;
    }

    @Generated
    public void setGmcooldown(boolean gmcooldown) {
        this.gmcooldown = gmcooldown;
    }

    @Generated
    public int getAutoAttack() {
        return this.autoAttack;
    }

    @Generated
    public void setAutoAttack(int autoAttack) {
        this.autoAttack = autoAttack;
    }

    @Generated
    public ScheduledExecutorService getTimerInstance() {
        return this.timerInstance;
    }

    @Generated
    public void setTimerInstance(ScheduledExecutorService timerInstance) {
        this.timerInstance = timerInstance;
    }

    public class PlayerObservable extends Observable {
        final MapleCharacter player;

        public PlayerObservable(final MapleCharacter this$0, MapleCharacter player) {
            this.player = player;
        }

        public MapleCharacter getPlayer() {
            return this.player;
        }

        public void update() {
            this.setChanged();
            this.notifyObservers(this.player);
        }
    }
}
