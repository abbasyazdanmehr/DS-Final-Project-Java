package ds;

import models.BankBranch;
import models.Coordinate;
import models.Neighbourhood;

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

    // this method is static except for one specific condition
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

    public BankBranch findParentIfInsert(Coordinate coordinate) {
        BankBranch temp = root;
        int counter = 0;

        while (true) {

            if (counter % 2 == 0) {

                if (coordinate.x < temp.getLocation().x) {
                    if (temp.left == null) return temp;
                    temp = temp.left;
                } else {
                    if (temp.right == null) return temp;
                    temp = temp.right;
                }

            } else {

                if (coordinate.y < temp.getLocation().y) {
                    if (temp.left == null) return temp;
                    temp = temp.left;
                } else {
                    if (temp.right == null) return temp;
                    temp = temp.right;
                }

            }

            counter++;
        }
    }

    public static BankBranch closerDistance(Coordinate pivot, BankBranch branch1, BankBranch branch2) {
        if (branch1 == null) return branch2;

        if (branch2 == null) return branch1;

        int distance1 = Coordinate.distancePower2(pivot, branch1.getLocation());
        int distance2 = Coordinate.distancePower2(pivot, branch2.getLocation());

        if (distance1 < distance2) {
            return branch1;
        } else {
            return branch2;
        }
    }

    // understanding key: direction of traversing in tree is important
    public static BankBranch closestBranch(BankBranch branch, Coordinate coordinate, int depth) {
        if (branch == null) return null;

        BankBranch nextBranch;      // next branch if insert
        BankBranch oppositeBranch;  // opposite branch if insert
        boolean goLeftOrRight;

        if (depth % 2 == 0) goLeftOrRight = (coordinate.x < branch.getLocation().x);
        else goLeftOrRight = (coordinate.y < branch.getLocation().y);

        if (goLeftOrRight) {
            nextBranch = branch.left;
            oppositeBranch = branch.right;
        } else {
            nextBranch = branch.right;
            oppositeBranch = branch.left;
        }

        BankBranch best = closerDistance(
                coordinate,
                closestBranch(nextBranch, coordinate, depth + 1),
                branch
        );

        boolean checkOppositeSide;
        if (depth % 2 == 0)
            checkOppositeSide =
                    Coordinate.distancePower2(coordinate, best.getLocation()) > Coordinate.abs(coordinate.x - branch.getLocation().x);
        else
            checkOppositeSide =
                    Coordinate.distancePower2(coordinate, best.getLocation()) > Coordinate.abs(coordinate.y - branch.getLocation().y);
        if (checkOppositeSide) {
            best = closerDistance(
                    coordinate,
                    closestBranch(oppositeBranch, coordinate, depth + 1),
                    best
            );
        }

        return best;
    }

    // understanding key: direction of traversing in tree is important
    public static void printAvailableBranches(BankBranch branch, Coordinate coordinate, int radius, int depth) {
        if (branch == null) return;

        if (Coordinate.distancePower2(coordinate, branch.getLocation()) < radius * radius)
            System.out.println(branch);

        BankBranch nextBranch;      // next branch if insert
        BankBranch oppositeBranch;  // opposite branch if insert
        boolean goLeftOrRight;

        if (depth % 2 == 0) goLeftOrRight = (coordinate.x < branch.getLocation().x);
        else goLeftOrRight = (coordinate.y < branch.getLocation().y);

        if (goLeftOrRight) {
            nextBranch = branch.left;
            oppositeBranch = branch.right;
        } else {
            nextBranch = branch.right;
            oppositeBranch = branch.left;
        }

        printAvailableBranches(nextBranch, coordinate, radius, depth + 1);

        boolean checkOppositeSide;
        if (depth % 2 == 0)
            checkOppositeSide =
                    radius > Coordinate.abs(coordinate.x - branch.getLocation().x);
        else
            checkOppositeSide =
                    radius > Coordinate.abs(coordinate.y - branch.getLocation().y);

        if (checkOppositeSide) {
            printAvailableBranches(oppositeBranch, coordinate, radius, depth + 1);
        }


    }

    // understanding key: direction of traversing in tree is important
    public static void printNeighbourhoodBranches(BankBranch branch, Neighbourhood neighbourhood, int depth) {
        if (branch == null) return;

        if (neighbourhood.isNeighbourhoodSurround(branch.getLocation())) {
            System.out.println(branch);
        }

        int firstEdge;
        int endEdge;
        int branchParam; // location.x or location.y
        if (depth % 2 == 0) {
            firstEdge = neighbourhood.leftEdge();
            endEdge = neighbourhood.rightEdge();
            branchParam = branch.getLocation().x;
        } else {
            firstEdge = neighbourhood.downEdge();
            endEdge = neighbourhood.upEdge();
            branchParam = branch.getLocation().y;
        }


        if (branchParam < firstEdge) {

            printNeighbourhoodBranches(branch.right, neighbourhood, depth + 1);

        } else if (branchParam > endEdge) {

            printNeighbourhoodBranches(branch.left, neighbourhood, depth + 1);

        } else {

            printNeighbourhoodBranches(branch.left, neighbourhood, depth + 1);
            printNeighbourhoodBranches(branch.right, neighbourhood, depth + 1);

        }


    }

}
