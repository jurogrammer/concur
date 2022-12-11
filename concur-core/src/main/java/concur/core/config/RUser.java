package concur.core.config;

public enum RUser {
    INJAE("injae"),
    HONG("hong"),
    BANS("bans"),
    STICK("stick");

    private final String prefix = "reservation:";

    private final String name;

    RUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String reservationName() {
        return prefix + this.name;
    }
}
