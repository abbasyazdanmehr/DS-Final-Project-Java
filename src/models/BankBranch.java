package models;

import controllers.CommandControllers;

public class BankBranch {
    // in main branch of bank name and bank name is equal
    private String name;
    private final String bankName;
    private final Coordinate location;

    // KDTree fields
    public BankBranch parent;
    public BankBranch left;
    public BankBranch right;

    // TrieTree fields
    public BankBranch[] children = new BankBranch[Statics.SMALL_ALPHABET_SIZE];

    public BankBranch(
            String name,
            String bankName,
            Coordinate location
    ) {
        this.name = name;
        this.bankName = bankName;
        this.location = location;
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }

    // TrieTree null data object constructor
    public BankBranch() {
        this.name = null;
        this.bankName = null;
        this.location = null;
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BankBranch{" +
                "name='" + name + '\'' +
                ", bankName='" + bankName + '\'' +
                ", location=" + location +
                '}';
    }

    public boolean isEqualWith(BankBranch branch) {
        return this.getLocation() == branch.getLocation();
    }

    public static boolean nameChecker(String name) {

        if (name.length() > 20) {
            System.out.println("Name is long, Choose shorter name!");
            return false;
        }

        if (name.length() == 0) {
            System.out.println("Name should have one character at least!");
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) < 'a' || name.charAt(i) > 'z') {
                System.out.println("Name should be contains just a to z!");
                return false;
            }
        }

        return true;
    }
}
