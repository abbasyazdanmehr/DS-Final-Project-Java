package test;

import ds.BranchesKDTree;
import models.Bank;
import models.BankBranch;
import models.Coordinate;
import models.Neighbourhood;

public class Tests {

    public static void GeneratorsTest() {
        Neighbourhood[] neighbourhoods = ModelGenerators.neighbourhoodsGenerator(10, 100, -100);
        for (Neighbourhood neighbourhood :
                neighbourhoods) {
            System.out.println(neighbourhood);
        }

        Coordinate[] coordinates = ModelGenerators.coordinatesGenerator(10, 100, -100);
        for (Coordinate coordinate :
                coordinates) {
            System.out.println(coordinate);
        }

        Bank[] banks = ModelGenerators.banksGenerator(10, 100, -100);
        for (Bank bank :
                banks) {
            bank.printAllData();
        }

        BankBranch[] branches = ModelGenerators.bankBranchesGenerator(10, "bankName", 100, -100);
        for (BankBranch branch :
                branches) {
            System.out.println(branch.toString());
        }
    }

    public static void KDTreeTest() {
        // Neighbourhood Names : darakAbad, hooshangina, fatishon, mootadan, sbu, daneshgav
        Neighbourhood darakAbad = new Neighbourhood(
                "darakAbad",
                new Coordinate(100, 60),
                new Coordinate(100, 20),
                new Coordinate(200, 20),
                new Coordinate(200, 60)
        );

        BankBranch mainBawnk = new BankBranch(
                "bawnk",
                "bawnk",
                new Coordinate(150, 40)
        );

        Bank bawnk = new Bank("bawnk", mainBawnk);

        darakAbad.addBank(bawnk);

        darakAbad.printBanks();
    }
}
