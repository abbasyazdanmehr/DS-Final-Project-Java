package models;

import ds.BankBranchList;


public class Neighbourhood {
    private final String name;
    private final Coordinate leftUp;
    private final Coordinate leftDown;
    private final Coordinate rightDown;
    private final Coordinate rightUp;
    private final BankBranchList banks;

    public Neighbourhood(
            String name,
            Coordinate leftUp,
            Coordinate leftDown,
            Coordinate rightDown,
            Coordinate rightUp
    ) {
        this.name = name;
        this.leftUp = leftUp;
        this.leftDown = leftDown;
        this.rightDown = rightDown;
        this.rightUp = rightUp;
        this.banks = new BankBranchList();
    }

    public String getName() {
        return name;
    }

    public Coordinate getLeftUp() {
        return leftUp;
    }

    public Coordinate getLeftDown() {
        return leftDown;
    }

    public Coordinate getRightDown() {
        return rightDown;
    }

    public Coordinate getRightUp() {
        return rightUp;
    }

    public void addBranch(BankBranch branch) {
        banks.add(branch);
    }

    public void printBanks() {
        for (int i = 0; i < banks.size(); i++) {
            System.out.println(banks.get(i));
        }
    }

    @Override
    public String toString() {
        return "Neighbourhood{" +
                "name='" + name + '\'' +
                ", leftUp=" + leftUp +
                ", leftDown=" + leftDown +
                ", rightDown=" + rightDown +
                ", rightUp=" + rightUp +
                '}';
    }

    public static boolean nameChecker(String name) {

        if (name.length() > 20) {
            System.out.println("Name is long, Choose shorter name!");
            return false;
        }

        if (name.length() == 0) {
            System.out.println("Name should have one character at least!");
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) < 'a' || name.charAt(i) > 'z') {
                System.out.println("Name should be contains just a to z!");
                return false;
            }
        }

        return true;
    }
}
