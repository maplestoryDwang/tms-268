package Client;

public enum MapleStat {
    SKIN(1L),
    FACE(2L),
    HAIR(4L),
    PET_LOCKER_SN(8L),
    LEVEL(16L),
    JOB(32L),
    STR(64L),
    DEX(128L),
    INT(256L),
    LUK(512L),
    HP(1024L),
    MAX_HP(2048L),
    MP(4096L),
    MAX_MP(8192L),
    AVAILABLE_AP(16384L),
    AVAILABLE_SP(32768L),
    EXP(65536L),
    POPULARITY(131072L),
    MONEY(262144L),
    FATIGUE(524288L),
    CHARISMA(1048576L),
    INSIGHT(2097152L),
    WILL(4194304L),
    CRAFT(8388608L),
    SENSE(16777216L),
    CHARM(33554432L),
    BATTLE_EXP(268435456L),
    BATTLE_RANK(536870912L),
    BATTLE_POINTS(1073741824L),
    ICE_GAGE(536870912L),
    VIRTUE(1073741824L),
    RECOVERY_POTION_WITH_HP(67109888L),
    RECOVERY_POTION_WITH_MP(67112960L),
    RECOVERY_POTION_WITH_HP_MP(67113984L),
    TEMP_EXP(4294967296L),
    GENDER(8589934592L),
    TODAYS_TRAITS(0x4000000), //今日獲得
    TRAIT_LIMIT(0x8000000),
    性別(0x200000000L),
    PET(1572872),
    RECOVERY_POTION_WITH_HPMP(67113984);
    ;




    private final long i;

    MapleStat(long i) {
        this.i = i;
    }

    public static MapleStat getByValue(long value) {
        for (final MapleStat stat : MapleStat.values()) {
            if (stat.i == value) {
                return stat;
            }
        }
        return null;
    }

    public long getValue() {
        return i;
    }

    public enum Temp {

        力量(0x1),
        敏捷(0x2),
        智力(0x4),
        幸運(0x8),
        物攻(0x10),
        魔攻(0x20),
        物防(0x40),
        魔防(0x80),
        命中(0x100),
        迴避(0x200),
        速度(0x400),
        跳躍(0x800);
        private final int i;

        Temp(int i) {
            this.i = i;
        }

        public int getValue() {
            return i;
        }
    }
}
