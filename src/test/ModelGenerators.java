package test;

import models.Bank;
import models.BankBranch;
import models.Coordinate;
import models.Neighbourhood;

public class ModelGenerators {

    public static Neighbourhood[] neighbourhoodsGenerator(int count, int max, int min) {
        Neighbourhood[] neighbourhoods = new Neighbourhood[count];
        for (int i = 0; i < count; i++) {
            Coordinate[] coordinates = coordinatesGenerator(4, max, min);
            String name = "area" + i;
            neighbourhoods[i] = new Neighbourhood(name, coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
        }
        return neighbourhoods;
    }

    public static Coordinate[] coordinatesGenerator(int count, int max, int min) {
        Coordinate[] coordinates = new Coordinate[count];
        for (int i = 0; i < count; i++) {
            int x = getRandom(max, min);
            int y = getRandom(max, min);
            coordinates[i] = new Coordinate(x, y);
        }
        return coordinates;
    }

    public static Bank[] banksGenerator(int count, int max, int min) {
        Coordinate[] coordinates = coordinatesGenerator(count, max, min);
        Bank[] banks = new Bank[count];
        for (int i = 0; i < count; i++) {
            String name = "bank" + i;
            BankBranch mainBranch = new BankBranch(name, name, coordinates[i]);
            banks[i] = new Bank(name, mainBranch);
        }
        return banks;
    }

    public static BankBranch[] bankBranchesGenerator(int count, String bankName, int max, int min) {
        Coordinate[] coordinates = coordinatesGenerator(count, max, min);
        BankBranch[] bankBranches = new BankBranch[count];
        for (int i = 0; i < count; i++) {
            String name = "branch" + i;
            bankBranches[i] = new BankBranch(name, bankName, coordinates[i]);
        }
        return bankBranches;
    }

    public static int getRandom(int max, int min) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

}
