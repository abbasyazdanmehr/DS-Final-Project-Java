package ds;

import models.Bank;
import models.Statics;

public class BanksByTrieTree {

    private final BankTrieNode root;

    public BanksByTrieTree() {
        root = new BankTrieNode();
    }

    public BankTrieNode getRoot() {
        return root;
    }

    public void insert(Bank bank) {

        BankTrieNode temp = root;

        String bankName = bank.getName();
        int length = bankName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = bankName.charAt(i) - 'a';

            if (temp.children[childIndex] == null) {
                temp.children[childIndex] = new BankTrieNode();
            }

            temp = temp.children[childIndex];
        }

        temp.bank = bank;
    }

    public Bank search(String bankName) {
        BankTrieNode temp = root;

        int length = bankName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = bankName.charAt(i) - 'a';

            if (temp.children[childIndex] == null) return null;

            temp = temp.children[childIndex];
        }

        return temp.bank;
    }

    static boolean isEmpty(BankTrieNode node) {
        for (int i = 0; i < Statics.SMALL_ALPHABET_SIZE; i++)
            if (node.children[i] != null)
                return false;
        return true;
    }

    public static BankTrieNode remove(BankTrieNode node, String bankName, int depth) {
        if (node == null) return null;

        if (depth == bankName.length()) {

            if (node.bank != null) {
                System.out.println("DELETING WAS SUCCESSFUL!");
                node.bank = null;
            }

            if (isEmpty(node)) node = null;

            return node;
        }

        int childIndex = bankName.charAt(depth) - 'a';

        node.children[childIndex]
                = remove(node.children[childIndex], bankName, depth + 1);

        if (isEmpty(node) && node.bank == null) {
            node = null;
        }

        return node;
    }
}
