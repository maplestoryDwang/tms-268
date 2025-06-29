package SwordieX.enums;

public enum AccountType {
    Player(0),
    Intern(1),
    Tester(2),
    GameMaster(4),
    Admin(4);

    private final int val;

    AccountType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
