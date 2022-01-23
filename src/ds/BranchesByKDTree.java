package ds;

import models.BankBranch;
import models.Coordinate;

// Node is a BankBranch Object
public class BranchesByKDTree {
    // we assume K is 2 in this project and this ds doesn't work for K != 2
    private final BankBranch root;

    public BranchesByKDTree() {
        this.root = new BankBranch(
                null,
                null,
                new Coordinate(
                        Integer.MIN_VALUE,
                        Integer.MIN_VALUE
                ) // root has just right node
        );
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
                        return;
                    }
                    temp = temp.left;
                } else {
                    if (temp.right == null) {
                        temp.right = branch;
                        branch.parent = temp;
                        return;
                    }
                    temp = temp.right;
                }

            } else {

                if (branch.getLocation().y < temp.getLocation().y) {
                    if (temp.left == null) {
                        temp.left = branch;
                        branch.parent = temp;
                        return;
                    }
                    temp = temp.left;
                } else {
                    if (temp.right == null) {
                        temp.right = branch;
                        branch.parent = temp;
                        return;
                    }
                    temp = temp.right;
                }

            }

            counter++;
        }

    }


    public static void printInorder(BankBranch branch) {
        if (branch == null) return;

        printInorder(branch.left);

        System.out.println(branch.getName());

        printInorder(branch.right);
    }

    public BankBranch searchByCoordinate(int x, int y) {
        BankBranch temp = root;
        boolean findIt = false;
        int counter = 0;

        while (temp != null) {

            if (x == temp.getLocation().x && y == temp.getLocation().y) {
                findIt = true;
                break;
            }

            if (counter % 2 == 0) {

                if (x < temp.getLocation().x) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }

            } else {

                if (y < temp.getLocation().y) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }

            }

            counter++;
        }

        if (findIt) return temp;
        else return null;
    }

    public void deleteFromKDTree(BankBranch temp) {

        if (temp.right == null && temp.left != null) {

            if (temp.parent.left != null && temp.parent.left.isEqualWith(temp)) {
                temp.parent.left = temp.left;
            } else {
                temp.parent.right = temp.left;
            }

            temp.left.parent = temp.parent;

        } else if (temp.right != null && temp.left == null) {

            if (temp.parent.left != null && temp.parent.left.isEqualWith(temp)) {
                temp.parent.left = temp.right;
            } else {
                temp.parent.right = temp.right;
            }

            temp.right.parent = temp.parent;

        } else if (temp.right == null) { // temp.left == null this is leaf node

            if (temp.parent.left != null && temp.parent.left.isEqualWith(temp)) {
                temp.parent.left = null;
            } else {
                temp.parent.right = null;
            }

        } else {

            BankBranch minimumGreater = findMinimumGreater(temp);
            minimumGreater.parent = temp.parent;
            minimumGreater.left = temp.left;
            minimumGreater.right = temp.right;

        }

    }

    public BankBranch findMinimumGreater(BankBranch branch) {
        BankBranch temp = branch.right;
        while (temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

}
