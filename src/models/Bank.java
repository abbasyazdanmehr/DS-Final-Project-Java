package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Bank {
    private final String name;
    private final ArrayList<BankBranch> branches = new ArrayList<>();

    public Bank(String name, BankBranch mainBranch) {
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
        branch.setIndexInBankBranchesList(this.branches.size());
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
}
