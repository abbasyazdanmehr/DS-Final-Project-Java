package ds;

import models.Bank;
import models.Statics;

public class BanksByTrieTree {

    private final Bank root;

    public BanksByTrieTree() {
        root = new Bank();
    }

    public Bank getRoot() {
        return root;
    }

    public void insert(Bank bank) {

        Bank temp = root;

        String bankName = bank.getName();
        int length = bankName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = bankName.charAt(i) - 'a';

            if (i != length - 1) {
                if (temp.children[childIndex] == null) {
                    temp.children[childIndex] = new Bank();
                }
            } else {
                temp.children[childIndex] = new Bank(bank.getName(), bank.getMainBranch());
            }

            temp = temp.children[childIndex];
        }

    }

    public Bank search(String bankName) {
        Bank temp = root;

        int length = bankName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = bankName.charAt(i) - 'a';

            if (temp.children[childIndex] == null) return null;

            temp = temp.children[childIndex];
        }

        if (temp.getName() != null) return temp;
        else return null;
    }

    static boolean isEmpty(Bank node) {
        for (int i = 0; i < Statics.SMALL_ALPHABET_SIZE; i++)
            if (node.children[i] != null)
                return false;
        return true;
    }

    public static Bank remove(Bank node, String bankName, int depth) {
        if (node == null) return null;

        if (depth == bankName.length()) {

            if (node.getName() != null) {
                node.setName(null);
                System.out.println("DELETING BANK WAS SUCCESSFUL!");
            }

            if (isEmpty(node)) node = null;

            return node;
        }

        int childIndex = bankName.charAt(depth) - 'a';

        node.children[childIndex]
                = remove(node.children[childIndex], bankName, depth + 1);

        if (isEmpty(node) && node.getName() == null) {
            node = null;
        }

        return node;
    }
}
