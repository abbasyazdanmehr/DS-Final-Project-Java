package models;

import java.util.ArrayList;

public class Bank {
    private String name;
    private final ArrayList<BankBranch> branches;

    // TrieTree fields
    public Bank[] children = new Bank[Statics.SMALL_ALPHABET_SIZE];

    public Bank(String name, BankBranch mainBranch) {
        this.name = name;
        this.branches = new ArrayList<>();
        this.branches.add(mainBranch);
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }

    // TrieTree null bank data constructor
    public Bank() {
        this.name = null;
        this.branches = null;
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }

    public String getName() {
        return name;
    }

    public BankBranch getMainBranch() {
        return this.branches.get(0);
    }

    public ArrayList<BankBranch> getBranches() {
        return branches;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printCoordinates() {
        System.out.println(branches.get(0).getLocation().toString());
    }

    public void printAllData() {
        System.out.println("--------" + this.name + "--------");
        for (BankBranch branch : this.branches) {
            System.out.println("Name: " + branch.getName());
            System.out.println(branch.getLocation().toString());
        }
        System.out.println("------------------------");
    }

    public void addBranch(BankBranch branch) {
        this.branches.add(branch);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", coordinate=" + branches.get(0).getLocation() +
                '}';
    }

    public boolean isEqualWith(Bank bank) {
        return this.branches.get(0) == bank.branches.get(0);
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
