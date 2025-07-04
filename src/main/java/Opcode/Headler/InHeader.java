package Opcode.Headler;

import Server.ExternalCodeTableGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.data.WritableIntValueHolder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
public enum InHeader implements WritableIntValueHolder {
    CP_AutoBuff,
    BOSS_KALOS_ONPACKET,
    MUKHYUN_SYSTERM_LOCK,
    CP_SPIRT_WEAPON,
    BOSS_ANGEL_ONPACK,
    CP_EXTRA_REQUEST,
    CP_Client_Crash_rep,
    CP_SECURITY_REQUEST,
    CP_COMBO_TIME,
    CP_UNION_WEAPON_POINT,
    BOSS_KALOS_ONPACKET_SPC,
    CP_OPEN_UI_SP_JOB,
    CASH_SHOP_CHECK_BUY_REWARD_ITEM_KEY,
    CP_USE_VCORE_AMUNT,
    JIN_BLACK_HAND_RECV,
    BOSS_CARNING_PARTY_RECV,
    LP_AliveReq,
    CP_UNK_749,
    CP_AliveAck,
    CP_MonsterDely_CRC,
    INGAME_PONG,
    HID_VID_PID_CHECK,
    CHECK_FIVE_MINUTE,
    CTX_BOSS_UI_ENTER,
    CHECK_FIVE_MINUTE2,
    HACKSHIELD,
    CLIENT_HELLO,
    HEARTBEAT_RECV,
    HEARTBEAT_RECV_FIRST,
    CODY_PRESET_RECV,
    FLASH_MIRAGE,
    BOSS_UI_ENTER,
    BOSS_UI_CLOSE,
    CURSE_ENCHANT,
    CURSE_ENCHANT_SELECT,
    URS_RECV,
    URS_STUN,
    WHITE_BACKGROUND_LODING,
    CHECK_HOTFIX,
    LOAD_WZ_DATA,
    SESSION_CHECK,
    RESET_SECOND_PW,
    OTP_SETTING,
    INPUT_OTP,
    LOGIN_PASSWORD,
    SELECT_CHANNEL_LIST,
    LEAVING_WORLD,
    CHARLIST_REQUEST,
    CHECK_CHAR_NAME,
    SESSION_DC_EVENT,
    CREATE_CHAR,
    CHAR_NAME_CHANGE,
    DELETE_CHAR,
    CHAR_SELECT,
    ACTIVATE_RECV,
    LOGIN_WITH_CREATE_CHAR,
    LOGIN_REQUEST,
    AUTH_LOGIN_WITH_SPW,
    ONLY_REG_SECOND_PASSWORD,
    AUTH_SECOND_PASSWORD,
    NEW_PASSWORD_CHECK,
    PACKET_ERROR,
    NAME_CHANGER_SPW,
    NAME_CHANGER,
    VICIOUS_HAMMER_RES,
    VICIOUS_HAMMER_RESULT,
    PLAYER_LOGGEDIN,
    CHANGE_MAP,
    CHANGE_CHANNEL,
    CHANGE_ROOM_CHANNEL,
    ENTER_CASH_SHOP,
    MOVE_PLAYER,
    CANCEL_CHAIR,
    USE_TITLE,
    USE_CHAIR,
    CLOSE_RANGE_ATTACK,
    RANGED_ATTACK,
    MAGIC_ATTACK,
    UNSTABLE_MEMORIZE,
    MIRROR_DUNGEON,
    PASSIVE_ENERGY,
    CHILLING_ATTACK,
    ORBITAL_ATTACK,
    ULTIMATE_MATERIAL,
    LIFE,
    PSYCHIC_GRAB,
    BLACK_MAGE_RECV,
    BLACK_MAGE_BALL_RECV,
    PSYCHIC_GRAB_PREPARATION,
    TAKE_DAMAGE,
    GENERAL_CHAT,
    CLOSE_CHALKBOARD,
    FACE_EXPRESSION,
    USE_ITEMEFFECT,
    NPC_TALK,
    NPC_TALK_MORE,
    NPC_SHOP,
    STORAGE,
    USE_HIRED_MERCHANT,
    MAPLE_CABINET,
    DUEY_ACTION,
    ITEM_SORT,
    ITEM_GATHER,
    ITEM_MOVE,
    ITEM_MAKER,
    MANNEQUIN,
    CONTENTS_GUIDE,
    USE_ITEM,
    CANCEL_ITEM_EFFECT,
    USE_SUMMON_BAG,
    PET_FOOD,
    USE_KAISER_COLOR,
    USE_MOUNT_FOOD,
    USE_SOUL_ENCHANTER,
    USE_SOUL_SCROLL,
    USE_SCRIPTED_NPC_ITEM,
    USE_CONSUME_ITEM,
    USE_CASH_ITEM,
    CHANGED_COLOR,
    MIX_HAIR,
    USE_CATCH_ITEM,
    USE_SKILL_BOOK,
    CP_PLAYER_USE_GO_HOME_SCROLL,
    ANDROID_EAR,
    USE_PET_LOOT,
    USE_PET_SKILL,
    USE_UPGRADE_SCROLL,
    DISTRIBUTE_AP,
    AUTO_ASSIGN_AP,
    HEAL_OVER_TIME,
    DISTRIBUTE_SP,
    SPECIAL_MOVE,
    POSION_LEGION,
    CANCEL_BUFF,
    SKILL_EFFECT,
    MESO_DROP,
    GIVE_FAME,
    CHAR_INFO_REQUEST_ME,
    CHAR_INFO_REQUEST_OTHER,
    SPAWN_PET,
    CHANGE_MAP_SPECIAL,
    USE_INNER_PORTAL,
    TROCK_ADD_MAP,
    QUEST_ACTION,
    LP_SKILL_MACRO,
    REWARD_ITEM,
    PARTYCHAT,
    WHISPER,
    MESSENGER,
    PLAYER_INTERACTION,
    PARTY_OPERATION,
    DENY_PARTY_REQUEST,
    GUILD_OPERATION,
    DENY_GUILD_REQUEST,
    GUILD_REGISTER_REQUEST,
    GUILD_REGISTER_ACCEPT,
    GUILD_REGISTER_CANCEL,
    GUILD_REGISTER_DENY,
    REQUEST_GUILD,
    CANCEL_GUILD_REQUEST,
    GUILD_OPTION,
    BUDDYLIST_MODIFY,
    NOTE_ACTION,
    USE_DOOR,
    CHANGE_KEYMAP,
    PET_BUFF,
    ALLIANCE_OPERATION,
    DENY_ALLIANCE_REQUEST,
    REQUEST_FAMILY,
    OPEN_FAMILY,
    FAMILY_OPERATION,
    MARRIAGE_ITEM,
    DELETE_JUNIOR,
    DELETE_SENIOR,
    ACCEPT_FAMILY,
    USE_FAMILY,
    BOSS_MATCHING,
    BOSS_WARP,
    FAMILY_PRECEPT,
    FAMILY_SUMMON,
    CYGNUS_SUMMON,
    AranCombo,
    LOSE_AranCombo,
    BLESS_OF_DARKNESS,
    MOVE_PET,
    PET_CHAT,
    PET_COMMAND,
    PET_LOOT,
    PET_AUTO_POT,
    MOVE_SUMMON,
    SUMMON_ATTACK,
    DAMAGE_SUMMON,
    MOVE_LIFE,
    AUTO_AGGRO,
    FRIENDLY_DAMAGE,
    MONSTER_BOMB,
    HYPNOTIZE_DMG,
    SPIRIT_HIT,
    NPC_ACTION,
    ITEM_PICKUP,
    DAMAGE_REACTOR,
    SNOWBALL,
    DAMAGE_SKIN,
    DAMAGE_SKIN_SAVE,
    LEFT_KNOCK_BACK,
    COCONUT,
    MONSTER_CARNIVAL,
    SHIP_OBJECT,
    CS_CHARGE,
    CS_UPDATE,
    CS_GIFT,
    COODINATION_RESULT,
    MVP_SPECIAL_PACK,
    MVP_GIFT_PACK,
    BUY_CS_ITEM,
    COUPON_CODE,
    MOVE_DRAGON,
    REPAIR,
    REPAIR_ALL,
    USE_MAGNIFY_GLASS,
    USE_STAMP,
    USE_EDITIONAL_STAMP,
    USE_CHOOSE_CUBE,
    USE_POTENTIAL_SCROLL,
    USE_EQUIP_SCROLL,
    USE_EDITIONAL_SCROLL,
    USE_EXCEPTIONAL_SCROLL,
    OWL,
    OWL_WARP,
    USE_OWL_MINERVA,
    AUCTION_RESULT,
    AUCTION_EXIT,
    RPS_GAME,
    USE_NAME_CHANGE,
    LINK_PRESET,
    LINK_PRESETS_APPLY,
    MEMORY_CHOICE_R,
    UPDATE_QUEST,
    USE_ITEM_QUEST,
    FOLLOW_REQUEST,
    RANDOM_MONSTER_ATTACK,
    SKILL_ALERT,
    FOLLOW_REPLY,
    MOB_NODE,
    DISPLAY_NODE,
    TOUCH_REACTOR,
    SPACE_REACTOR,
    RING_ACTION,
    WEDDING_PRESENT,
    EXPEDITION_OPERATION,
    EXPEDITION_LISTING,
    PARTY_SEARCH_START,
    PARTY_SEARCH_STOP,
    USE_TELE_ROCK,
    SUB_SUMMON,
    USE_MECH_DOOR,
    MECH_CANCEL,
    REMOVE_SUMMON,
    AUTO_FOLLOW_REPLY,
    REPORT,
    MOB_BOMB,
    CREATE_ULTIMATE,
    USE_POT,
    CLEAR_POT,
    FEED_POT,
    CURE_POT,
    CRAFT_MAKE,
    CRAFT_DONE,
    CRAFT_EFFECT,
    STOP_HARVEST,
    START_HARVEST,
    MOVE_BAG,
    USE_BAG,
    MOVE_ANDROID,
    FACE_ANDROID,
    REISSUE_MEDAL,
    CLICK_REACTOR,
    USE_RECIPE,
    DENY_SIDEKICK_REQUEST,
    ALLOW_PARTY_INVITE,
    SPECIAL_STAT,
    MAKE_EXTRACTOR,
    USE_FLAG_SCROLL,
    SWITCH_BAG,
    REWARD_POT,
    PVP_INFO,
    ENTER_PVP,
    ENTER_PVP_PARTY,
    LEAVE_PVP,
    PVP_RESPAWN,
    PVP_ATTACK,
    PVP_SUMMON,
    PQ_REWARD,
    INNER_CHANGE,
    INNER_PRESET,
    MAPLE_EXIT,
    RUNE_TOUCH,
    RUNE_USE,
    ABSORB_REGEN,
    ZERO_SCROLL,
    ZERO_SCROLL_LUCKY,
    ZERO_SCROLL_START,
    ZERO_WEAPON_INFO,
    UI_OPEN,
    ZERO_WEAPON_UPGRADE,
    ZERO_TAG,
    SUB_ACTIVE_SKILL,
    ZERO_CLOTHES,
    ChillingStep,
    WILL_OF_SWORD_COMBO,
    WILL_OF_SWORD,
    FIELD_ATTACK_OBJ_ACTION,
    ORBITAL_FLAME,
    VIEW_SKILLS,
    SKILL_SWIPE,
    CHOOSE_SKILL,
    VOYD_PRESSURE,
    EQUIPMENT_ENCHANT,
    RAINBOW_RUSH_START,
    RAINBOW_RUSH_TIMER,
    RAINBOW_RUSH_DEAD,
    RAINBOW_RUSH_RETURN_MAP,
    RAINBOW_RUSH_TIMER_SECOND,
    UserTowerChairSetting,
    BlockGameRes,
    ExitBlockGame,
    ClickBingoCell,
    ClickBingo,
    SPECIAL_GAME_EXIT,
    ORGEL_HIT,
    DREAM_BREAKER_SKILL,
    HDetectiveGameInput,
    BALANCE_EXIT,
    UserClientResolutionResult,
    PlatformerEnter,
    ENTER_DUNGEN,
    EXIT_PLATFORMER,
    NETT_PYRAMID_CHECK,
    EXIT_GAME,
    HYPER_R,
    BIND_LIFE,
    MEDAL_DISPLAY,
    TITLE_DISPLAY,
    ADD_HYPERSTAT,
    ADD_HYPERSKILL,
    USE_CORE_JAMSTONE,
    USE_TRADE,
    PSYCHIC_ATTACK_R,
    PSYCHIC_DAMAGE_R,
    CANCEL_PSYCHIC_GRAB_R,
    MATRIX_SKILL,
    MATRIX_SKILL2,
    JOKER_R,
    MINICONNONBALL,
    SELECT_DICE,
    SYMBOL_LEVELUP,
    AUT_SYMBOL_LEVELUP,
    UPDATE_CORE,
    USE_REBIRTH_SCROLL,
    UNLINK_SKILL,
    LINK_SKILL,
    SHOW_SOULEFFECT_R,
    SET_BURNING_CHAR,
    SOUL_EFFECT_RECIVE,
    ENTER_AUCTION,
    USE_CUBE,
    USE_ADI_CUBE,
    BUFF_ATTACK,
    INCREASE_DURATION,
    RESPAWN,
    MEGA_SMASHER,
    FIELD_ATTACK_OBJ_ATTACK,
    MOB_ZONE,
    SOUL_MATCH,
    DAILY_GIFT,
    SHADOW_SERVENT_EXTEND,
    WEAPON_MOTION_CHANGE,
    HAMMER_OF_TODD,
    NPC_OF_TODD,
    OPEN_UNION,
    UNION_ARTIPACT,
    DOT_ATTACK,
    PRAY,
    DRESSUP_TIME,
    DRESS_UP,
    INHUMAN_SPEED,
    RELEASE_ROCK,
    SET_UNION,
    MOVE_HAKU,
    TOUCH_MIST,
    UPDATE_JAGUAR,
    CHAINARTS_CHASE,
    GENERAL_CHAT_ITEM,
    WHISPERITEM,
    PARTYCHATITEM,
    AURA_WEAPON,
    SPOTLIGHT_ATTACK,
    REMOVE_MIST,
    ZERO_TAG_REMOVE,
    PEACE_MAKER_1,
    PEACE_MAKER_2,
    DEMON_FRENZY,
    UNION_ARTIPACT_SET,
    KEYDOWN_MANAGEMENT,
    SKILL_SUB_EFFECT,
    ARK_LINK,
    FLOW_OF_FIGHT,
    FLYING_SWORD,
    USE_CHOOSE_ABILITY,
    ZERO_SCROLL_UI,
    ZERO_REBIRTH_SCROLL,
    CHARACTER_ORDER,
    REPLACE_SUMMON,
    ICBM,
    DIMENTION_SWORD,
    REMOVE_SUMMON2,
    EFFECT_SUMMON,
    CANCEL_EFFECT_SUMMON,
    SPECIAL_SUMMON,
    AFTER_CANCEL,
    AUTO_SKILL,
    RESPAWN_LUCID,
    USE_SILVER_KARMA,
    BIND_SUCCESS,
    UPDATE_CORE_2,
    POISON_NOVA,
    USE_MOON_GAUGE,
    WILL_SPIDER_TOUCH,
    SKILL_TO_Crystal,
    RETURN_RESULT,
    MINIGAME_OPERATION,
    BUFF_FREEZER,
    LOTUS_AIR_ATTACK,
    QUICK_SLOT,
    PET_EXCEPTION_LIST,
    UNLOCK_TRINITY,
    HYPERSTAT_PRESETS,
    RESET_HYPERSTAT,
    RESET_HYPERSKILL,
    UPDATE_MIST,
    CHARGE_SKILL,
    SMOKE_SHELL_3_SECOND,
    BLOOD_FIST,
    RESULT_CHAIR,
    INVITE_CHAIR,
    CHECK_CORE_SECONDPW,
    FISHING,
    ADD_BULLET,
    REVOLVING_BUNKER_CANCEL,
    USE_RANDOM_DOOR,
    GAME_EXIT,
    ARCANE_CATALYST,
    ARCANE_CATALYST3,
    ARCANE_CATALYST2,
    ARCANE_CATALYST4,
    RETURN_SYNTHESIZING,
    HASTE_BOX,
    FISHING_END,
    DEMIAN_BIND,
    DEMIAN_ATTACKED,
    STIGMA_INCINERATE_USE,
    STONE_ATTACKED,
    SPOTLIGHT_BUFF,
    BLESS_5TH,
    UNION_FREESET,
    USE_BLACK_REBIRTH_SCROLL,
    BLACK_HAND,
    USE_CORE_SELECT_VALUE,
    FATE_SHUFFLE,
    USE_ITEM_SELECT_VALUE,
    TOUCH_ALTER,
    QUICK_MOVE,
    UNK_JINHILLIA,
    DIMENTION_MIRROR,
    USE_BLACK_REBIRTH_RESULT,
    CANCEL_SUB_EFFECT,
    CHANGE_SUB_EFFECT,
    SHOW_ICBM,
    ARK_GAUGE,
    LUCID_STATE_CHANGE,
    FOLLOW_CANCEL,
    QUICK_PASS,
    BATTLE_STATISTICS,
    EVENTUI_RESULT,
    WILL_MOON,
    MOBSKILL_DELAY,
    REMOVE_SECOND_ATOM,
    ROPE_CONNECT,
    PSYCHIC_ULTIMATE_R,
    ENTER_FARM,
    WARP_GUILD_MAP,
    AURA_PARTY_BUFF,
    EXIT_FARM,
    UPDATE_FARM_IMG,
    GUILD_RANKING_REQUEST,
    FPS_SHOOT_REQUEST,
    FORCE_INFO,
    COURTSHIP_COMMAND,
    VSKILL_SPECIAL,
    SPAWN_ORB,
    MOVE_ORB,
    REMOVE_ORB,
    ORIGIN_ANNIHULATION,
    REVENANT_DAMAGE,
    MECH_CARRIER,
    PHOTON_RAY,
    CRYSTAL_GATE,
    COOLTIME_END,
    CANCEL_BUFF_FORCE,
    KINESIS_GROUND_R,
    SPECIAL_SUMMON_5TH,
    ZERO_LUCKY_SCROLL,
    INFO_SECOND_ATOM,
    SILHOUETTE_MIRAGE,
    EVENT_UI_SKILL,
    COMMAND_LOCK,
    COMMAND_LOCK2,
    YOYO_STACK,
    ROLLING_GRADE,
    STACK_BUFF,
    CHANGE_DRAGON_ATTACK_IMG,
    ATTACK_DRAGON_IMG,
    PHANTOM_SHRUOD,
    LIFT_BREAK,
    REVENANT,
    MAGUNM_BLOW,
    RESOTRE,
    HARMONY_LINK,
    FORCEATOM_EFFECT,
    COLOR_CARD_COMMAND,
    CONTENTS_WAITING,
    MAPLE_YUT_HANDLER,
    MESSENGER_SEARCH,
    SELECT_REINCARNATION,
    SELECT_HOLY_UNITY,
    PYRET_BLESS,
    LIE_DETECTOR,
    SEND_LIE_DETECTOR,
    ZERO_SHOCK_WAVE,
    PSYCHIC_OVER,
    BATTLEGROUND_ATTACK,
    BATTLEGROUND_ATTACK_REFRESH,
    BATTLEGROUND_MOVE_ATTACK,
    BATTLEGROUND_DAMAGE,
    BATTLEGROUND_TAKE_DAMAGE,
    BATTLEGROUND_UPGRADE_SKILL,
    BATTLEGROUND_TIME,
    BATTLE_GROUND_RESPAWN,
    DEBUFF_OBJ_HIT,
    POISON_REGION,
    SPAWN_MIST_MONSTER,
    PAPULATUS_PINCERS,
    PAPULATUS_PINCERS_RESET,
    PAPULATUS_BOMB,
    PAPULATUS_BOMB_DAMAGE,
    BLOOD_QUEEN_BREATH,
    TOUCH_MONSTER,
    MOVE_MONSTER_SPAWN_MIST,
    HEXA_CORE_USE,
    VANVAN_TIMEOVER,
    SPAWN_BELLUM_MIST,
    HEXA_SOL_JANUS,
    REMOVE_OBSTACLE,
    REMOVE_ENERGYSPHERE,
    DEMIAN_SWORD_HANDLE,
    NOTE_HANDLE,
    NOTE_HANDLER,
    SET_UNION_FREESET,
    EXP_POCKET,
    CHAT_EMOTICON,
    BATTLEGROUND_SELECT_AVATER,
    CREATE_CORE,
    USE_AP_RESET_SCROLL,
    ITEM_SORT_LOCK,
    USE_ITEM_LOCK,
    HIT_ERDA_SPECTRUM,
    ACT_ERDA_SPECTRUM,
    BALL_ERDA_SPECTRUM,
    INFINITY_FLAME_CIRCLE,
    AFTER_CANCEL2,
    SP_PORTAL_USE,
    START_QUEST,
    FREE_CHANGE_JOB,
    ITEMMAKER_COOLDOWN,
    SUDDEN_MISSION_CLEAR,
    TYOONKITCHEN_COOK_SUC,
    TYOONKITCHEN_COOK_MAKE,
    ICE_AURA,
    LARA_POINT,
    USE_CIRCULATOR,
    USE_CHAOS_CIRCULATOR,
    USE_LEVEL_CIRCULATOR,
    Lotus,
    Lotus2,
    TANGYOON_COOKING,
    USE_SECOND_ATOM,
    CHARGE_SEED,
    DOJANG_HANDLER,
    KALOS_RECV,
    CHECK_CONNECT,
    CREATE,
    LOGIN,
    CHECK_FILE,
    CP_WARP_TO_MAP_REF,
    CTX_FIELD_CHECK,
    CTX_FIELD_CHECK_SCRIPT,
    CP_REQUEST_STATUS_CHECK,
    CTX_GOD_ELF_TALK,
    CTX_GOD_ELF_TALK1,
    CTX_GOD_ELF_TALK2,
    CTX_GOD_ELF_WEAPON_UPLEVEL,
    CTX_GOD_ELF_TALK4,
    CTX_USER_SIT,
    CTX_USER_STAND,
    CTX_PLAYER_ENTER_EVENT,
    CTX_OPEN_CORE,
    CP_USER_POTENTIAL_SKILL_RAND_RAND_SET_UI,
    CTX_SPAWN_ATTACK,
    USER_USE_SKILL_LOCK_OUT,
    CP_FUNCKEYMAP_UNIONKEY_BLOCK_NUMLOCK,
    CP_CHAR_USE_WARP_ITEM,
    CP_UserSkillUseRequest_II,
    EXTRA_QUEST_TIME_LOG,
    HYPER_PING_TIME,
    CTX_CHARACTER_CRC_KEY_PONG,
    BOSS_DEMIAN_FLY_ON_PACKET,
    BOSS_SMILE_UI_ACTION,
    BOSS_JIN_BLACK_HAND_RECV,
    MAP_HEART_BEAT,
    CRASH_REPORT,
    AUTH_SECURITY_1794,
    AUTH_SECURITY_1805,
    USER_USE_PARTY_KEYBOARD,
    BOSS_LUCID_USE_HORN,
    BOSS_BLACK_MAGE_ACTION,
    BOSS_KARLOS_ONPACKET,
    BOSS_CARNING_WARP_BOSS,
    CHAT_SERVER_PONG,
    CHAT_SERVER_REQUEST,
    DUMMY_CODE,
    CP_SecurityPacket,
    CP_PermissionRequest,
    CP_ClientFileLog,
    CP_v255_115,
    CTX_ENTER_ACCOUNT,
    LP_SELECT_WORLD,
    CP_SELECT_WORLD_LOGIN,
    CP_START_HEART_BEART,
    CP_v245_109,
    CP_v255_121,
    CP_ClientUnkLoginLog,
    CP_CREAT_NEW_CHAR_2PW,
    CP_v245_112,
    CP_MigrateIn,
    CP_SelectCharacter,
    CP_SelectAccount,
    CTX_WORLD_RETURN_INFO,
    Creat_New_Char_Check_Name,
    CTX_OUT_WORLD,
    CP_PermissionRequest_Fake,
    CP_CheckLoginAuthInfo_Fake,
    CP_CreateMapleAccount_Fake,
    CP_SelectAccount_Fake,
    CP_SelectWorld_Fake,
    CP_SelectCharacter_Fake,
    CTX_HERO_Phantom_Spirit_Arms,
    CP_CreateNewCharacter_Fake,
    Creat_New_Char,
    CP_CreateNewCharacterInCS,
    CP_CreateNewCharacter_PremiumAdventurer,
    CP_DeleteCharacter,
    CP_ReservedDeleteCharacterConfirm,
    CP_ReservedDeleteCharacterCancel,
    CP_v245_131,
    CP_v245_132,
    CP_ExceptionLog,
    CP_ExceptionTracert,
    CP_v245_136,
    CP_PrivateServerPacket,
    CP_ResetLoginStateOnCheckOTP,
    CP_v245_139,
    CP_v245_140,
    CP_v245_141,
    GET_REV,
    CP_v245_143,
    CP_v245_144,
    CP_v245_145,
    CP_v245_146,
    CP_v245_147,
    CP_GetGuidKey,
    CP__REQUEST__UNPACK_CLIENT__DATA,
    CP_WAIT_FOR_LOAD_FILE_LIST,
    CP_v245_151,
    CP_v255_163,
    CP_v255_164,
    GET_REV_AGAIN,
    CP_v255_166,
    LP_AliveAck,
    CP_ResponseToCheckAliveAck,
    CP_v245_154,
    CTX_DUMP_CLIENT_LOG,
    CP_GetBuyCashPointUI,
    CP_QuickBuyItemByCS,
    REQUEST_CONNECTION,
    CP_v245_159,
    CTX_CLIENT_CRASH,
    CP_v245_161,
    CTX_REGIST,
    CP_v245_163,
    CP_v245_164,
    CP_v245_165,
    CP_v245_166,
    CP_v245_167,
    CP_v245_168,
    CP_v245_169,
    CP_APPLY_HOTFIX,
    CP_CHECK_CLIENT_LOAD_DATA,
    CP_ClientExceptionInfo,
    CP_WvsSetUpStep,
    CP_WvsCrashCallback,
    CP_OpcodeEncryptionError,
    CP_CREAT_NEW_CHARACTER,
    CP_v245_177,
    CP_v245_178,
    CP_DirectGoToField,
    CP_UpdateCharacterSelectList,
    CP_v245_181,
    CP_v245_182,
    CP_v245_183,
    CP_CreateCharVerificationCode,
    CP_UserAntiMacroQuestionResult,
    CP_v245_185,
    CP_v245_186,
    CP_v245_187,
    CP_v245_188,
    CP_v245_189,
    CP_v245_190,
    CP_v245_191,
    CP_v245_192,
    CP_v245_193,
    CP_LockOutPacket,
    CP_v245_195,
    CP_v245_196,
    CP_RenameCharacter,
    CP_v245_198,
    CP_v245_199,
    CHEAT_ENGINE,
    CP_v245_201,
    CP_v245_202,
    CP_v255_218,
    CP_v255_219,
    CP_v255_220,
    CP_BEGIN_USER_DUMMY,
    CP_BEGIN_USER,
    CP_UserTransferFieldRequest,
    CP_UserTransferChannelRequest,
    CP_UserTransferToHubRequest,
    CP_WorldTransferRequest,
    CP_WorldTransferShinningStarRequest,
    CP_UserMigrateToCashShopRequest,
    CP_UserMigrateToAuctionRequest,
    CP_v245_213,
    CP_v245_214,
    CP_v245_215,
    CP_v245_216,
    CP_v245_217,
    CP_v245_218,
    CTX_GIVE_JOB_SKILL,
    CP_UserMove,
    CTX_CHARCTER_SIT,
    CP_USER_CLINET_LOCK,
    CP_UserEmoticonItemUseRequest,
    CP_UserDanceStopRequest,
    CP_UserMeleeAttack,
    CP_UserShootAttack,
    CP_UserMagicAttack,
    CP_UserBodyAttack,
    CP_UserAreaDotAttack,
    UserSpotlightAttack,
    CP_UserHit,
    CP_UserAttackUser,
    CP_UserChat,
    CP_UserItemChat,
    CP_UserADBoardClose,
    CP_UserEmotion,
    CP_AndroidEmotion,
    CP_UserActivateEffectItem,
    CP_UserMonkeyEffectItem,
    CP_UserActivateNickItem,
    NULL_PACKET_271,
    NGS_CHECK_THREETY,
    CP_UserActivateDamageSkin,
    USE_ACTIVATE_DAMAGE_SKIN_PREMIUM,
    CP_USER_OPNE_DAMAGE_SKIN_UI,
    CP_UserDamageSkinSaveRequest,
    CP_UserSetCustomBackgroundRequest,
    CP_UserDefaultWingItem,
    CP_UserKaiserTransformWing,
    CP_UserKaiserTransformTail,
    CP_UserUpgradeTombEffect,
    CP_UserHP,
    CP_Premium,
    CP_UserBanMapByMob,
    CP_UserMonsterBookSetCoverf,
    CP_PLAYER_NPC_TALK,
    CP_UserScriptMessageAnswer,
    CP_UserRemoteShopOpenRequest,
    CP_UserShopRequest,
    CP_UserTrunkRequest,
    CP_UserEntrustedShopRequest,
    CP_UserStoreBankRequest,
    CP_UserParcelRequest,
    CP_UserEffectLocal,
    CP_UserFinalAttackRequest,
    CREATE_AFFECTED_AREA_REQUEST,
    USE_AFFECTED_AREA_REQUEST,
    CP_UserCreateHolidomRequest,
    CP_ReqMakingSkillEff,
    CP_ShopScannerRequest,
    CP_ShopLinkRequest,
    CP_AuctionRequest,
    CP_AuctionExit,
    CP_UserGatherItemRequest,
    CP_UserSortItemRequest,
    CP_UserChangeSlotPositionRequest,
    CP_UserTextEquipInfo,
    CP_UserPopOrPushBagItemToInven,
    CP_UserBagToBagItem,
    CP_UserPourInBagToBag,
    CP_UserStatChangeItemUseRequest,
    CP_UserStatChangeItemCancelRequest,
    CP_UserStatChangeByPortableChairRequest,
    CP_UserMobSummonItemUseRequest,
    CP_UserPetFoodItemUseRequest,
    CP_UserTamingMobFoodItemUseRequest,
    CP_UserScriptItemUseRequest,
    CP_UserRecipeOpenItemUseRequest,
    ITEM_WORLD_WARP_SEEN,
    CP_UserConsumeCashItemUseRequest,
    CP_UserAdditionalSlotExtendItemUseRequest,
    CP_UserCashPetPickUpOnOffRequest,
    CP_UserCashPetSkillSettingRequest,
    CP_UserOptionChangeRequest,
    CP_UserDestroyPetItemRequest,
    CP_UserBridleItemUseRequest,
    CP_UserSkillLearnItemUseRequest,
    CP_UserSkillResetItemUseRequest,
    CP_UserAbilityResetItemUseRequest,
    CP_UserAbilityChangeItemUseRequest,
    CP_UserExpConsumeItemUseRequest,
    CP_UserMonsterLifeInviteItemUseRequest,
    CP_UserExpItemGetRequest,
    CP_UserCharSlotIncItemUseRequest,
    CP_UserKaiserColorChangeItemUseRequest,
    CP_UserCharRenameItemUseRequest,
    CP_UserUpgradeItemUseRequest,
    CP_UserFieldTransferRequest,
    CP_UserUpgradeAssistItemUseRequest,
    CP_UserHyperUpgradeItemUseRequest,
    CP_UserExItemUpgradeItemUseRequest,
    CP_UserItemOptionUpgradeItemUseRequest,
    CP_UserAdditionalOptUpgradeItemUseRequest,
    CP_UserItemSlotExtendItemUseRequest,
    CP_UserWeaponTempItemOptionRequest,
    CP_UserItemSkillSocketUpgradeItemUseRequest,
    CP_UserItemSkillOptionUpgradeItemUseRequest,
    CP_UserFreeMiracleCubeItemUseRequest,
    CTX_FREE_CUBE_ATTACH,
    CP_UserEquipmentEnchantWithSingleUIRequest,
    UserArcaneForceRequest,
    UserAuthenticForceRequest,
    CP_UserUIOpenItemUseRequest,
    CP_UserBagItemUseRequest,
    CP_UserItemReleaseRequest,
    CP_UserMemorialCubeOptionRequest,
    UserCharacterPotentialRequest,
    CP_UserToadsHammerRequest,
    CP_UserToadsHammerHelpRequest,
    CP_UserChangeSoulCollectionRequest,
    CP_UserSelectSoulSkillUpRequest,
    CP_UserAbilityUpRequest,
    CP_UserAbilityMassUpRequest,
    CP_UserDotHeal,
    CP_UserChangeStatRequest,
    CP_CLIENT_CHECK_371,
    DEL_TEACH_SKILL,
    SET_TEACH_SKILL,
    CP_UserSkillUpRequest,
    CP_UserSkillUseRequest,
    CP_UserSkillCancelRequest,
    CP_UserSkillFinishRequest,
    CP_UserSkillPrepareRequest,
    SUPER_CANNON_REQUEST,
    R_USER_DROP_MESO,
    CP_UserGivePopularityRequest,
    CP_UserActivatePetRequest,
    CP_UserRegisterPetAutoBuffRequest,
    CP_UserTemporaryStatUpdateRequest,
    CP_UserPortalScriptRequest,
    CP_UserPortalTeleportRequest,
    CP_UserCallingTeleportRequest,
    CP_UserMapTransferRequest,
    CP_UserAntiMacroItemUseRequest,
    CP_UserAntiMacroSkillUseRequest,
    CP_UserAntiMacroRefreshRequest,
    CP_UserClaimRequest,
    CP_UserQuestRequest,
    CP_UserMedalReissueRequest,
    HEART_BEAT,
    CP_UserCalcDamageStatSetRequest,
    CP_UserB2BodyRequest,
    CP_UserThrowGrenade,
    CP_UserDestroyGrenade,
    CP_UserCreateAuraByGrenade,
    CP_UserSetMoveGrenade,
    CP_UserMacroSysDataModified,
    CP_UserItemMakeRequest,
    CP_UserRepairDurabilityAll,
    CP_UserRepairDurability,
    CP_UserFollowCharacterRequest,
    CP_UserFollowCharacterWithdraw,
    CP_UserSelectPQReward,
    CP_UserRequestPQReward,
    CP_SetPassenserResult,
    CP_UserRequestCreateItemPot,
    CP_UserRequestRemoveItemPot,
    CP_UserRequestInstanceTable,
    CP_UserRequestIncItemPotLifeSatiety,
    CP_UserRequestCureItemPotLifeSick,
    CP_UserRequestComplateToItemPot,
    CP_UserRequestRespawn,
    CP_UserConsumeHairItemUseRequest,
    UserConsumeHairMixItemUseRequest,
    CP_UserForceAtomCollision,
    USER_TRUMP_SKILL_ACTION_REQUEST,
    DOT_HEAL_HP_REQUEST,
    LapidificationStateChange,
    CP_USER_POTENTIAL_SKILL_RAND_SET,
    CP_USER_POTENTIAL_SKILL_RAND_STAT_SET,
    CP_SelPotentialPath,
    CP_UserProtectBuffOnDieItemRequest,
    CP_UserProtectBuffOnDieMaplePointRequest,
    CP_ZeroTag,
    CP_ZeroShareCashEquipPart,
    CP_ZeroLastAssistState,
    CP_UserShootAttackInFPS,
    CP_UserLuckyItemUseRequest,
    CP_UserMobMoveAbilityChange,
    CP_UserDragonAction,
    CP_UserDragonBreathEarthEffect,
    CP_UserRenameRequest,
    CheckSPWOnRename,
    CP_BroadcastMsg,
    CP_GroupMessage,
    CP_GroupItemMessage,
    CP_FieldUniverseMessage,
    CP_Whisper,
    CP_ItemWhisper,
    CP_MiniRoom,
    CP_PartyRequest,
    CP_PartyResult,
    CP_PartyInvitableSet,
    CP_ExpeditionRequest,
    CP_UrusPartyRequest,
    CP_PartyAdverRequest,
    CP_GuildRequest,
    CP_GuildResult,
    CP_GuildJoinRequest,
    CP_GuildJoinCancelRequest,
    CP_Messenger,
    CP_GuildJoinAccept,
    CP_GuildJoinReject,
    CP_TowerRankRequest,
    CP_Admin,
    CP_Log,
    CP_ADD_FRIEND,
    CP_LoadAccountIDOfCharacterFriendRequest,
    CP_MemoRequest,
    CP_MemoFlagRequest,
    CP_EnterTownPortalRequest,
    CP_EnterRandomPortalRequest,
    CP_EnterOpenGateRequest,
    CP_FuncKeyMappedModified,
    CP_RPSGame,
    CP_GSRPSGame,
    CP_GSRPSForceSelect,
    CP_MarriageRequest,
    CP_WeddingWishListRequest,
    CP_GuestBless,
    CP_BoobyTrapAlert,
    CP_StalkBegin,
    CP_AllianceRequest,
    CP_AllianceResult,
    CP_TalkToTutor,
    CP_TalkToPartner,
    CP_UserSwitchRP,
    CP_RequestIncCombo,
    CP_RequestDecCombo,
    CP_RequestSetBlessOfDarkness,
    CP_RequestSetHpBaseDamage,
    CP_MobCrcKeyChangedReply,
    CP_MobCrcDataResult,
    CP_MicroBuffEndTime,
    CP_UserTransferFreeMarketRequest,
    CP_UserRequestSetStealSkillSlot,
    CP_UserRequestStealSkillMemory,
    CP_UserRequestStealSkillList,
    CP_UserRequestStealSkill,
    CP_ResetCrossHunterAlert,
    CP_CrossHunterCompleteRequest,
    CP_CrossHunterShopRequest,
    CP_UserRequestFlyingSwordStart,
    UNLOCKTRINITY,
    CP_UserHyperSkillUpRequest,
    CP_UserHyperSkillResetRequset,
    CP_UserHyperStatSkillUpRequest,
    CP_UserHyperStatSkillResetRequest,
    CP_UserHyperStatSkillChangePresetRequest,
    CP_UserSetDressChangedRequest,
    CP_EntryRecordRequest,
    CP_SetMaxGauge,
    CP_UserReturnEffectResponse,
    BLACK_MAGIC_RECV,
    CLIENT_CHANGE_PLAYER_COOKIE,
    CP_WaitQueueRequest,
    CP_TouchPlayer,
    USER_HOWLING_STORM_STACK,
    CP_UserQuickMoveScript,
    CP_TimeGateRequest,
    CP_SELECT_ANDROID_SHOP,
    CP_UserCompleteNpcSpeech,
    MONSTER_DEAD_COUNT,
    AUTO_USE_JUDGEMENT,
    PoisonAreaRemove,
    JobFreeChangeRequest,
    CHECK_THIS_WEEK_EVENT,
    SOUL_MODE,
    CP_EXTRA_EQUIP_REQUEST,
    USE_TOWERCHAIR_SETTING,
    EventReviveRequest,
    UPDATE_BULLET_COUNT,
    VMATRIX_MAKE_REQUEST,
    VMATRIX_HELP_REQUEST,
    VMATRIX_VERIFY,
    ErosionsrReduce,
    ForceTargetRequest,
    CrystalCharge,
    CP_OPEN_UNION_UI_REQUEST,
    CP_PhysicalCheck,
    PROCESS_REPORT,
    DUSK_SPAWN,
    PREVIEW_CHOICE_BEAUTY_CARD,
    CP_PetMove,
    CP_PetAction,
    CP_PetInteractionRequest,
    CP_PetStatChangeItemUseRequest,
    CP_PetUpdateExceptionListRequest,
    CP_UserSkillSwitchRequest,
    CP_CHECK_PLAYER_STATUS,
    CP_UserCharacterInfoRequest,
    CP_PetFoodItemUseRequest,
    CP_PetOpenShop,
    CP_SkillPetMove,
    CP_SkillPetAction,
    CP_SkillPetState,
    CP_PetDropPickUpRequest,
    CP_SkillPetUpdateExceptionListRequest,
    CP_SummonedMove,
    CP_SummonedAttack,
    CP_SummonedHit,
    CP_SummonedSkill,
    SummonedMagicAltar,
    CP_SummonedRemove,
    CP_SummonedAction,
    CP_SummonedAssistAttackDone,
    SummonedSarahAction,
    SummonedJavelinAction,
    CP_DragonMove,
    CP_DragonGlide,
    ReturnTeleportDebuff,
    CP_AndroidMove,
    CP_AndroidActionSet,
    CP_FoxManMove,
    CP_FoxManActionSetUseRequest,
    CP_QuickslotKeyMappedModified,
    CP_PassiveskillInfoUpdate,
    CP_CloseWindow,
    CLOSE_WINDOW2,
    PLAYER_VIEW_RANGE,
    CP_CheckProcess,
    CP_MemoInGameRequest,
    CP_EgoEquipGaugeCompleteReturn,
    CP_EgoEquipCreateUpgradeItem,
    CP_EgoEquipCreateUpgradeItemCostRequest,
    CP_EgoEquipTalkRequest,
    CP_EgoEquipCheckUpdateItemRequest,
    CP_InheritanceInfoRequest,
    CP_InheritanceUpgradeRequest,
    CP_MirrorReadingSelectBookRequest,
    CP_LikePoint,
    CP_RequestArrowPlaterObj,
    USE_GROWTH_HELPER_REQUEST,
    EVENT_GOLD_DAY,
    UserSetCustomizeEffect,
    USE_CONTENT_MAP_MINI,
    USE_CONTENT_MAP,
    CombingRoomActionReq,
    UserNonTargetForceAtomAttack,
    UserRunScript,
    USER_KEYBOARD_SETTINGS,
    ATTACK_PLAYER_XY,
    BossPartyCheckRequest,
    MonsterCollectionCompleteRewardRequest,
    MonsterCollectionExploreRequest,
    STIGMAINCINERATEUSE,
    ENTER_STARTPLANET,
    TRACK_FLAMES,
    UNION_WAR_EXIT,
    SELECT_JAGUAR,
    CALL_FRIENDS,
    USE_NEBULITE,
    POTION_POT_USE,
    POTION_POT_ADD,
    POTION_POT_MODE,
    POTION_POT_INCR,
    USE_SPECIAL_ITEM,
    PAM_SONG,
    APPLY_HYUNCUBE,
    BBS_OPERATION,
    SELECT_CHAIR,
    Ping_ClientToGamge,
    TRANSFORM_PLAYER,
    OPEN_AVATAR_RANDOM_BOX,
    ENTER_MTS,
    SOLOMON,
    GACH_EXP,
    USE_TREASUER_CHEST,
    MIN_5_CLIENT_CHECK,
    GIVE_KSPSYCHIC,
    ATTACK_KSPSYCHIC,
    CANCEL_KSPSYCHIC,
    GIVE_KSULTIMATE,
    ATTACK_KSULTIMATE,
    MIST_KSULTIMAT,
    CP_DemonUseDargonAttack,
    CANCEL_KSULTIMATE,
    TORNADO_KSULTIMATE,
    SkillStageChangeRequest,
    HAPPY_DAY,
    SKILL_ONOFF,
    AvatarEffectSkillOnOff,
    RevolvingCannonRequest,
    DimensionMirrorMove,
    VAddSkillReadyRequest,
    VAddSkillAttackRequest,
    MULTI_SKILL_ATTACK_REQUEST,
    STACK_MAGIC_REQUEST,
    RemoteControlDiceNumber,
    MapleUnionRequest,
    CHANGE_ANDROID_ANTENNA,
    CP_SAVE_UNION_QUEST,
    CTX_UNION_PRESET_REQUEST,
    MULTI_SKILL_CHARGE_REQUEST,
    ACHIEVEMENT,
    ForceAtomNextTarget,
    CTX_IN_FIELD_UNLOCK,
    SelflessState,
    CHARGE_INFINITE_FLAME,
    CHARGE_PRIMAL_GRENADE,
    ADELE_CHARGE_REQUEST,
    MaliceChargeRequest,
    LaraSkillChargeRequest,
    PoisonAreaCreate,
    USER_OPEN_V_SKILL_UI,
    SET_CHAR_CASH,
    NULL_977,
    OPEN_WORLD_MAP,
    NULL_979,
    NULL_980,
    HoYoungHealRequest,
    Auto5thGoddessBless,
    Auto5thRevenant_ReduceAnger,
    Auto5thRevenant_ReduceHP,
    SilhouetteMirageCharge,
    UseJupiterThunder,
    JupiterThunderAction,
    JupiterThunderEnd,
    MULTI_SKILL_TIMEOUT_CHARGE_REQUEST,
    ReincarnationModeSelect,
    CreateForceAtomObject,
    DivineJudgmentStatReset,
    SpeedMirageObjectCreate,
    ApplyAffectAreaEffect,
    CTX_EVENT_UI_BUTTON_REQUEST,
    CP_USER_CLINET_LOCK_2,
    USER_SHOW_TITLE,
    CP_CashEquipPreset,
    CHAR_ENTER_FIELD_CHECK,
    CP_HexaAction,
    CP_SoulUI,
    RECV_STACK_COOKIE_2,
    SPAWN_MONSTER,
    CP_MobMove,
    CP_MobApplyCtrl,
    CP_MobDropPickUpRequest,
    CP_MobHitByObstacle,
    CP_MobHitByObstacleAtom,
    CP_MobHitByMob,
    CP_MobSelfDestruct,
    CP_MobSelfDestructCollisionGroup,
    CP_MobAttackMob,
    CP_MobSkillDelayEnd,
    CP_MobTimeBombEnd,
    CP_MobEscortCollision,
    CP_MobRequestEscortInfo,
    CP_MobEscortStopEndRequest,
    CP_MobAreaAttackDisease,
    CP_MobExplosionStart,
    CP_MobLiftingEnd,
    CP_MobUpdateFixedMoveDir,
    CP_MobCreateFireWalk,
    CP_MobAfterDeadRequest,
    CP_MobDamageShareInfo,
    CP_MobCreateAffectedArea,
    CP_MobDownResponse,
    DRAGON_HIT,
    CP_FieldNpcAction,
    CP_Field_Npc_Action,
    CP_DropPickUpRequest,
    CP_ReactorHit,
    CP_ReactorClick,
    CP_ReactorRectInMob,
    CP_ReactorOnKey,
    CP_GatherRequest,
    UN_PACKET_8,
    CP_DecomposerRequest,
    CP_PartyMemberCandidateRequest,
    CP_UrusPartyMemberCandidateRequest,
    CP_PartyCandidateRequest,
    CP_GatherEndNotice,
    CP_ActChangeReactorUseRequst,
    CP_MakeEnterFieldPacketForQuickMove,
    CP_RuneStoneUseReq,
    CP_RuneStoneSkillReq,
    USE_RUNE_ACTION,
    MOBZONESTATE_RESULT,
    OBTACLE_ATOM_COLLISION,
    PeacemakerHeal,
    PeacemakerBuff,
    ExitGaintBoss,
    BOSS_DEMIAN_FLYING_NODE,
    FLYING_SWORD_ATTACK_MODE,
    DEMIANOBJECT_MAKE_ENTER_ACK,
    DEMIANOBJECT_NODE_END,
    DEMIANOBJECT_ERR_RECREATE,
    DEMIAN_FLY_SWORD_END,
    END_FIELD,
    LucidSpecialAttackEnd,
    LucidSpecialHorn,
    PopulatusCraneRequest,
    WaterWarpIsland,
    WaterGunExit,
    WILL_POSTION,
    DiceMasterMoveDone,
    DiceMasterExit,
    DiceMasterThrowDice,
    DiceMasterGameStart,
    CaptainNomadAttack,
    CaptainNomadStart,
    CaptainNomadExit,
    BOSS_WILL_USE_MOONGAUGE,
    HIT_ERDAS_PEC_TRUM,
    BLACK_MAGE_BALL_DOWN,
    BLACKMAGEBALLRECV,
    ACT_ERDAS_PEC_TRUM,
    BALL_ERDAS_PEC_TRUM,
    SEREN_ON_PACKET,
    SEREN_USE_SUNLIGHT_SKILL,
    SEREN_UPDATE_SUNLIGHT_INFO,
    Sword_Action,
    Sword_Remove,
    Sword_Move,
    BOSS_KALOS_ACTION_BAR,
    CP_CashShopChargeParamRequest,
    CP_CashShopQueryCashRequest,
    CP_CashShopCashItemRequest,
    CP_CashShopCheckCouponRequest,
    CP_CashShopMemberShopRequest,
    CP_CashShopGiftMateInfoRequest,
    CP_CashShopSearchLog,
    CP_CashShopCoodinationRequest,
    CP_CashShopCheckMileageRequest,
    CP_MVP_DailyPack_Request,
    CP_MVP_SpecialPack_Request,
    MVP_RoyalPack_Request,
    MVP_GradePack_Request,
    CASH_SHOP_KEY,
    CP_18thMiniGame,
    CP_GoldHammerRequest,
    CP_GoldHammerComplete,
    CP_PlatinumHammerRequest,
    CP_StartDamageRecord,
    CP_BattleRecordSkillDamageLog,
    HIDDEN_TAIL_ADN_EAR,
    REWARD_REQUEST,
    EFFECT_SWITCH,
    TMSEquipmentEnchantRequest,
    CHAR_INFO_WARP_MY_HOME,
    TMSExtraSystemRequest,
    MUKHYUN_POWER_CHANGED_RESULT,
    JENO_ENERGY_STORAGE_SYSTEM,
    Count,
    R_CLIENT_SECURITY_IN_GAME,
    R_CLIENT_SECURITY_IN_GAME_ONE_HOURS_FORTY_MINUTES,
    DUSK_HAND_ATTACK,
    TAP_JOY_RESPONSE,
    TAP_JOY_DONE,
    TAP_JOY_NEXT_STAGE,
    CP_UserShopScannerItemUseRequest,
    CHANGE_CHANNEL_AUTH,
    Event_Golden_Carriage,
    UNKNOWN,
    CP_ChangeMapCheckingPacket;

    private static final Logger log = LoggerFactory.getLogger(InHeader.class);
    private short code = -2;
    private static long lastModifiedTime = 0L;
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "sAGlLlC3iCiIVnQ2".getBytes();

    private InHeader() {
    }

    public static void main(String[] args) throws Exception {
        new File("res/InHeader.properties");
    }

    public static void startCheck() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InHeader.checkForChanges();
            }
        }, 0L, 500L);
    }

    private static void checkForChanges() {
        try {
            File file = new File("res/InHeader.properties");
            long currentModifiedTime = file.lastModified();
            if (currentModifiedTime > lastModifiedTime) {
                reloadValues();
                log.info("[InHeader]已套用新的資訊。");
                lastModifiedTime = currentModifiedTime;
            }

        } catch (Exception var3) {
            Exception e = var3;
            throw new RuntimeException("Failed to load InHeader", e);
        }
    }

    public static Properties getDefaultProperties() throws IOException {
        Properties props = new Properties();
        FileInputStream fileInputStream = new FileInputStream("res/InHeader.properties");

        try {
            props.load(fileInputStream);
        } catch (Throwable var5) {
            try {
                fileInputStream.close();
            } catch (Throwable var4) {
                var5.addSuppressed(var4);
            }

            throw var5;
        }

        fileInputStream.close();
        return props;
    }

    public static final void reloadValues() {
        try {
            ExternalCodeTableGetter.populateValues(getDefaultProperties(), values());
        } catch (IOException var1) {
            IOException e = var1;
            throw new RuntimeException("Failed to load InHeader", e);
        }
    }

    public static String getOpcodeName(int value) {
        InHeader[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            InHeader opcode = var1[var3];
            if (opcode.getValue() == value) {
                return opcode.name();
            }
        }

        return "UNKNOWN";
    }

    public final short getValue() {
        return this.code;
    }

    public short getCode() {
        return 0;
    }

    public void setValue(short code) {
        this.code = code;
    }

    public void setValue(Short code) {
        this.code = code;
    }
}