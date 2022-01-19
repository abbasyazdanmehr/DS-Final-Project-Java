package ds;

import models.Bank;
import models.BankBranch;
import models.Statics;

// Node is a BankBranch Pointer
public class BranchesByTrieTree {
    private final TrieNode root;

    BranchesByTrieTree(TrieNode node) {
        this.root = node;
    }

    public void insert(BankBranch branch) {

        TrieNode temp = root;

        String branchName = branch.getName();
        int length = branchName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = branchName.charAt(i) - 'a';

            if (temp.children[childIndex] == null) {
                temp.children[childIndex] = new TrieNode();
            }

            temp = temp.children[childIndex];
        }

        temp.branch = branch;
    }

    public BankBranch search(String branchName) {
        TrieNode temp = root;

        int length = branchName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = branchName.charAt(i) - 'a';

            if (temp.children[childIndex] == null) return null;

            temp = temp.children[childIndex];
        }

        return temp.branch;
    }

    static boolean isEmpty(TrieNode node) {
        for (int i = 0; i < Statics.SMALL_ALPHABET_SIZE; i++)
            if (node.children[i] != null)
                return false;
        return true;
    }

    public static TrieNode remove(TrieNode node, String branchName, int depth) {
        if (node == null) return null;

        if (depth == branchName.length()) {

            if (node.branch != null) node.branch = null;

            if (isEmpty(node)) node = null;

            return node;
        }

        int childIndex = branchName.charAt(depth) - 'a';

        node.children[childIndex]
                = remove(node.children[childIndex], branchName, depth + 1);

        if (isEmpty(node) && node.branch == null) {
            node = null;
        }

        return node;
    }


}
