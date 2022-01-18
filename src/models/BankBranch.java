package models;

public class BankBranch {
    // if name is equal to bankName branch is main branch
    private final String name;
    private final String bankName;
    private final Coordinate location;


    BankBranch(String name, String bankName, Coordinate location) {
        this.name = name;
        this.bankName = bankName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getBankName() {
        return bankName;
    }

    public Coordinate getLocation() {
        return location;
    }
}
