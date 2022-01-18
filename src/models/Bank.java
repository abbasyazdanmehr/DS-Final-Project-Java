package models;

import java.util.ArrayList;

public class Bank {
    private final String name;
    private final ArrayList<BankBranch> branches = new ArrayList<>();

    Bank(String name, BankBranch mainBranch) {
        this.name = name;
        this.branches.add(mainBranch);
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
}
