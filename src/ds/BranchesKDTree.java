package ds;

import models.BankBranch;

public class BranchesKDTree {
    // we assume K is 2 in this project and this ds doesn't work for K != 2
    private final BankBranch root;

    public BranchesKDTree(BankBranch branch) {
        this.root = branch;
    }

    public BankBranch getRoot() {
        return root;
    }

    public void insert(BankBranch branch) {
        BankBranch temp = root;
        int counter = 0;

        while (true) {
            if (counter % 2 == 0) {

                if (branch.getLocation().x < temp.getLocation().x) {
                    if (temp.left == null) {
                        temp.left = branch;
                        branch.parent = temp;
                        break;
                    }
                    temp = temp.left;
                } else {
                    if (temp.right == null) {
                        temp.right = branch;
                        branch.parent = temp;
                        break;
                    }
                    temp = temp.right;
                }

            } else {

                if (branch.getLocation().y < temp.getLocation().y) {
                    if (temp.left == null) {
                        temp.left = branch;
                        branch.parent = temp;
                        break;
                    }
                    temp = temp.left;
                } else {
                    if (temp.right == null) {
                        temp.right = branch;
                        branch.parent = temp;
                        break;
                    }
                    temp = temp.right;
                }

            }

            counter ++;
        }

    }


    public void printInorder(BankBranch branch) {
        if (branch == null) return;

        printInorder(branch.left);

        System.out.println(branch.getName());

        printInorder(branch.right);
    }


}
