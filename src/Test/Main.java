package Test;

import models.Bank;
import models.BankBranch;
import models.Coordinate;
import models.Neighbourhood;


public class Main {
    public static void main(String[] args) {

        System.out.println("Let's Test Something");

        Neighbourhood[] neighbourhoods = neighbourhoodsGenerator(20, 100, -100);
        for (Neighbourhood neighbourhood:
             neighbourhoods) {
            System.out.println(neighbourhood);
        }

        Coordinate[] coordinates = coordinatesGenerator(100, 100, -100);
        for (Coordinate coordinate :
                coordinates) {
            System.out.println(coordinate);
        }

        Bank[] banks = banksGenerator(15, 100, -100);
        for (Bank bank :
                banks) {
            bank.printAllData();
        }

        BankBranch[] branches = bankBranchesGenerator(10, "bankName", 100, -100);
        for (BankBranch branch :
                branches) {
            System.out.println(branch.toString());
        }
    }

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
            BankBranch[] branches = bankBranchesGenerator(3, name, max, min);
            banks[i].addBranches(branches);
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
