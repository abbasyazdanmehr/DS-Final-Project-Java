package ds;

import models.Bank;

public class BankList {
    private Bank[] banks;
    private int arrayLength;
    private int index;

    public BankList() {
        arrayLength = 10;
        banks = new Bank[arrayLength];
        index = 0;
    }

    public int size() {
        return index;
    }

    public Bank get(int i) {
        return banks[i];
    }

    public void add(Bank bank) {
        if (arrayLength == index) increaseArrayLength();

        banks[index++] = bank;
    }

    public void increaseArrayLength() {
        Bank[] newBank = new Bank[2 * arrayLength];
        System.arraycopy(banks, 0, newBank, 0, arrayLength);
        arrayLength = 2 * arrayLength;
        banks = newBank;
    }

    public void printBanks() {
        for (int i = 0; i < index; i++) {
            System.out.println(banks[i]);
        }
    }
}
