package ds;

import models.Bank;
import models.BankBranch;

public class BankBranchList {
    private BankBranch[] branches;
    private int arrayLength;
    private int index;

    public BankBranchList() {
        arrayLength = 10;
        branches = new BankBranch[arrayLength];
        index = 0;
    }

    public int size() {
        return index;
    }

    public BankBranch get(int i) {
        return branches[i];
    }

    public void add(BankBranch bank) {
        if (arrayLength == index) increaseArrayLength();

        branches[index++] = bank;
    }

    public void increaseArrayLength() {
        BankBranch[] newBranch = new BankBranch[2 * arrayLength];
        System.arraycopy(branches, 0, newBranch, 0, arrayLength);
        arrayLength = 2 * arrayLength;
        branches = newBranch;
    }

    public void printBanks() {
        for (int i = 0; i < index; i++) {
            System.out.println(branches[i]);
        }
    }
}
