package ds;

import models.Bank;
import models.BankBranch;
import models.Statics;

// Node is a BankBranch Pointer
public class BranchesByTrieTree {
    private final BankBranch root;

    public BranchesByTrieTree() {
        root = new BankBranch();
    }

    public BankBranch getRoot() {
        return root;
    }

    public void insert(BankBranch branch) {

        BankBranch temp = root;

        String branchName = branch.getName();
        int length = branchName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = branchName.charAt(i) - 'a';

            if (i != length - 1) {
                if (temp.children[childIndex] == null) {
                    temp.children[childIndex] = new BankBranch();
                }
            } else {
                temp.children[childIndex] = new BankBranch(
                        branch.getName(),
                        branch.getBankName(),
                        branch.getLocation()
                );
            }

            temp = temp.children[childIndex];
        }

    }

    public BankBranch search(String branchName) {
        BankBranch temp = root;

        int length = branchName.length();
        int childIndex;

        for (int i = 0; i < length; i++) {
            childIndex = branchName.charAt(i) - 'a';

            if (temp.children[childIndex] == null) return null;

            temp = temp.children[childIndex];
        }

        return temp;
    }

    static boolean isEmpty(BankBranch node) {
        for (int i = 0; i < Statics.SMALL_ALPHABET_SIZE; i++)
            if (node.children[i] != null)
                return false;
        return true;
    }

    public static BankBranch remove(BankBranch node, String branchName, int depth) {
        if (node == null) return null;

        if (depth == branchName.length()) {

            if (node.getName() != null) {
                node.setName(null);
                System.out.println("DELETING BRANCH WAS SUCCESSFUL!");
            }

            if (isEmpty(node)) node = null;

            return node;
        }

        int childIndex = branchName.charAt(depth) - 'a';

        node.children[childIndex]
                = remove(node.children[childIndex], branchName, depth + 1);

        if (isEmpty(node) && node.getName() == null) {
            node = null;
        }

        return node;
    }


}
