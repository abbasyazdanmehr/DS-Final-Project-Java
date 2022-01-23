package controllers;

import ds.BanksByTrieTree;
import ds.BranchesByKDTree;
import ds.BranchesByTrieTree;
import ds.NeighbourhoodList;
import models.*;

import java.util.Scanner;

// This Class Control the inputs
public final class CommandControllers {

    private static final Scanner in = new Scanner(System.in);

    public static NeighbourhoodList neighbourhoods = new NeighbourhoodList();
    public static BanksByTrieTree banksByTrieTree = new BanksByTrieTree();
    public static BranchesByTrieTree branchesByTrieTree = new BranchesByTrieTree();
    public static BranchesByKDTree branchesByKDTree = new BranchesByKDTree();

    public static void bankAppController() {

        while (true) {

            System.out.println("--- WELCOME TO BANKS DS ----");
            System.out.print("please enter your command: ");

            String command = in.nextLine();

            System.out.println();

            if ("addN".equals(command)) {
                addNeighbourhoodController();
            } else if ("addB".equals(command)) {
                addBankController();
                in.nextLine();
            } else if ("addBr".equals(command)) {
                addBankBranchController();
            } else if ("delBr".equals(command)) {
                deleteBankBranchController();
            } else if ("listB".equals(command)) {
                listBanksController();
            } else if ("listBrs".equals(command)) {
                listBankBranchesController();
            } else if ("nearB".equals(command)) {
                nearestBankController();
            } else if ("nearBr".equals(command)) {
                nearestBankBranchController();
            } else if ("availB".equals(command)) {
                isAvailableBankController();
            } else if ("searchB".equals(command)) {
                searchBankController();
            } else if ("searchBr".equals(command)) {
                searchBranchController();
            } else if ("printB".equals(command)) {
                printBankDataController();
            } else if ("printNs".equals(command)) {
                printNeighbourhoodsController();
            } else if ("break".equals(command)) {
                System.out.println("Bye!");
                in.close();
                break;
            } else {
                invalidCommand();
            }

            System.out.println();
        }

    }



    public static void searchBranchController() {
        System.out.print("Name: ");
        String name = in.nextLine();

        BankBranch branch = branchesByTrieTree.search(name);
        System.out.println(branch);
    }

    public static void printNeighbourhoodsController() {
        neighbourhoods.printNeighbourhoods();
    }

    public static void printBankDataController() {
        System.out.println("BANK_ALL_DATA");

        System.out.print("Name: ");
        String name = in.nextLine();

        Bank bank = banksByTrieTree.search(name);
        if (bank == null) {
            System.out.println("BANK NOT FOUND!");
        } else {
            bank.printAllData();
        }
    }

    public static Coordinate addCoordinateController() {

        try {
            System.out.print("   x: ");
            int x = in.nextInt();

            System.out.print("   y: ");
            int y = in.nextInt();

            return new Coordinate(x, y);
        } catch (Exception e) {
            System.out.println("x and y should be integer!");
            return null;
        }

    }

    public static void searchBankController() {
        System.out.println("SEARCH_IN_BANKS");

        System.out.print("Name: ");
        String name = in.nextLine();

        Bank bank = banksByTrieTree.search(name);
        if (bank == null) {
            System.out.println("BANK NOT FOUND!");
        } else {
            System.out.println(bank);
        }
    }

    public static void addNeighbourhoodController() {
        System.out.println("ADD_NEIGHBOURHOOD");

        System.out.print("Name: ");
        String name = in.nextLine();

        if (!Neighbourhood.nameChecker(name)) return;

        System.out.println("Location: ");

        System.out.println("  Left Up: ");
        Coordinate leftUp = addCoordinateController();

        if (leftUp == null) return;

        System.out.println("  Left Down: ");
        Coordinate leftDown = addCoordinateController();

        if (leftDown == null) return;

        if (leftUp.x != leftDown.x || leftUp.y < leftDown.y) {
            System.out.println("Neighbourhood should be a rectangle!");
            return;
        }

        System.out.println("  Right Down: ");
        Coordinate rightDown = addCoordinateController();

        if (rightDown == null) return;

        if (leftDown.y != rightDown.y || leftDown.x > rightDown.x) {
            System.out.println("Neighbourhood should be a rectangle!");
            return;
        }

        System.out.println("  Right Up: ");
        Coordinate rightUp = addCoordinateController();

        if (rightUp == null) return;

        if (rightDown.x != rightUp.x || rightUp.y != leftUp.y || rightDown.y > rightUp.y) {
            System.out.println("Neighbourhood should be a rectangle!");
            return;
        }

        neighbourhoods.add(
                new Neighbourhood(
                        name,
                        leftUp,
                        leftDown,
                        rightDown,
                        rightUp
                )
        );

        System.out.println("Neighbourhood add Successfully!");
    }

    public static void addBankController() {
        System.out.println("ADD_BANK");

        System.out.print("Name: ");
        String name = in.nextLine();

        if (!Bank.nameChecker(name)) return;

        Bank bank = banksByTrieTree.search(name);
        if (bank != null) {
            System.out.println("Name already chosen!");
            return;
        }

        System.out.println("Location: ");
        Coordinate location = addCoordinateController();

        if (location == null) return;

        if (branchesByKDTree.searchByCoordinate(location.x, location.y) != null) {
            System.out.println("This Coordinate is full now!");
            return;
        }

        BankBranch branch = new BankBranch(name, name, location);
        branchesByKDTree.insert(branch);
        branchesByTrieTree.insert(branch);
        banksByTrieTree.insert(new Bank(name, branch));
        // Finally, I understand call by reference in java :)

        System.out.println("Bank added successfully!");
    }

    public static void addBankBranchController() {
        System.out.println("ADD_BANK_BRANCH");

        System.out.print("Bank Name: ");
        String name = in.nextLine();

        Bank bank = banksByTrieTree.search(name);
        if (bank == null) {

            System.out.println("BANK NOT FOUND!");

        } else {

            System.out.print("Branch Name: ");
            String branchName = in.nextLine();

            if (!BankBranch.nameChecker(branchName)) return;

            BankBranch newBranch = branchesByTrieTree.search(branchName);
            if (newBranch != null) {
                System.out.println("Branch Name already chosen!");
                return;
            }

            System.out.println("Location: ");
            Coordinate location = addCoordinateController();

            if (location == null) return;

            if (branchesByKDTree.searchByCoordinate(location.x, location.y) != null) {
                System.out.println("This Coordinate is full now!");
                return;
            }

            BankBranch branch = new BankBranch(branchName, name, location);
            branchesByTrieTree.insert(branch);
            branchesByKDTree.insert(branch);
            bank.addBranch(branch);
            System.out.println("ADDING BRANCH WAS SUCCESSFUL!");
        }

    }

    public static void deleteBankBranchController() {
        System.out.println("DELETE_BANK_BRANCH");

        System.out.println("Location: ");
        Coordinate location = addCoordinateController();

        BankBranch branch = branchesByKDTree.searchByCoordinate(location.x, location.y);
        if (branch == null) {
            System.out.println("Branch Not Found!");
        } else {
            deleteBranch(branch);
        }
    }

    public static void deleteBranch(BankBranch branch) {
        if (branch.getBankName() == branch.getName()) {
            System.out.println("This branch is main branch Bro!");
            return;
        }

        // deleting from k-d tree
        branchesByKDTree.deleteFromKDTree(branch);

        // deleting from trie tree
        branchesByTrieTree.deleteFromTrieTree(
                branchesByTrieTree.getRoot(),
                branch.getName(),
                0
        );

        // deleting from bank
        Bank bank = banksByTrieTree.search(branch.getBankName());
        BankBranch branchToDelete = bank.branches.searchByCoordinate(branch.getLocation().x, branch.getLocation().y);
        bank.branches.deleteFromKDTree(branchToDelete);

        System.out.println("Branch deleted successfully!");
    }

    public static void listBanksController() {
        System.out.println("LIST_BANKS");

        System.out.print("Neighbourhood Name: ");
        String name = in.nextLine();

        // TODO
    }

    public static void listBankBranchesController() {
        System.out.println("BANK_ALL_DATA");

        System.out.print("Name: ");
        String name = in.nextLine();

        Bank bank = banksByTrieTree.search(name);
        if (bank == null) {
            System.out.println("BANK NOT FOUND!");
        } else {
            bank.printAllData();
        }
    }

    public static void nearestBankController() {
        System.out.println("NEAREST_BANK");

        System.out.println("Your Location: ");
        Coordinate location = addCoordinateController();

        BankBranch closest = BranchesByKDTree.closestBranch(branchesByKDTree.getRoot().right, location, 1);
        System.out.println(closest);
    }

    public static void nearestBankBranchController() {
        System.out.println("NEAREST_BANK_BRANCH");

        System.out.print("Bank Name: ");
        String name = in.next();

        Bank bank = banksByTrieTree.search(name);
        if (bank == null) {
            System.out.println("BANK NOT FOUND!");
            return;
        }

        System.out.println("Your Location: ");
        Coordinate location = addCoordinateController();

        BankBranch closest = BranchesByKDTree.closestBranch(bank.branches.getRoot().right, location, 1);
        System.out.println(closest);
    }

    public static void isAvailableBankController() {
        System.out.println("IS_AVAILABLE_BANK");

        System.out.print("Radius: ");
        int radius = in.nextInt();

        // TODO
    }

    public static void invalidCommand() {
        System.out.println("This command is invalid!");
    }

}
