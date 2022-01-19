package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Neighbourhood {
    private final String name;
    private final Coordinate leftUp;
    private final Coordinate leftDown;
    private final Coordinate rightDown;
    private final Coordinate rightUp;
    private final ArrayList<Bank> banks;

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
        this.banks = new ArrayList<>();
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

    public void addBank(Bank bank) {
        banks.add(bank);
    }

    public void addBanks(Bank[] banks) {
        this.banks.addAll(Arrays.asList(banks));
    }

    public void printBanks() {
        for (Bank bank : this.banks) {
            System.out.println(bank);
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
}
