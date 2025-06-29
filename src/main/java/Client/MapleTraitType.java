/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 * @author PlayDK
 */
public enum MapleTraitType {

    charisma(500, MapleStat.CHARISMA),//領袖
    insight(500, MapleStat.INSIGHT),//洞察
    will(500, MapleStat.WILL),//意志
    craft(500, MapleStat.CRAFT),//手技
    sense(500, MapleStat.SENSE),//感性
    charm(5000, MapleStat.CHARM);//魅力
    private final int limit; //獲得的最大限度
    private final MapleStat stat;

    MapleTraitType(int type, MapleStat theStat) {
        this.limit = type;
        this.stat = theStat;
    }

    public static MapleTraitType getByQuestName(String q) {
        String qq = q.substring(0, q.length() - 3); //e.g. charmEXP, charmMin
        for (MapleTraitType t : MapleTraitType.values()) {
            if (t.name().equals(qq)) {
                return t;
            }
        }
        return null;
    }

    public int getLimit() {
        return limit;
    }

    public MapleStat getStat() {
        return stat;
    }
}
