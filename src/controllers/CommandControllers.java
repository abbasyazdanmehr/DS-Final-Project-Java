package controllers;

import ds.BanksByTrieTree;
import ds.BranchesByKDTree;
import ds.BranchesByTrieTree;
import models.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// This Class Control the inputs
public final class CommandControllers {

    private static Scanner in = new Scanner(System.in);

    public static ArrayList<Neighbourhood> neighbourhoods = new ArrayList<>();
    public static BanksByTrieTree banks = new BanksByTrieTree();
    public static BranchesByTrieTree branchesByTrieTree = new BranchesByTrieTree();
    public static BranchesByKDTree branchesByKDTree = new BranchesByKDTree();

    public static void bankAppController() {

        while (true) {

            System.out.println("--- WELCOME TO BANKS DS ----");
            System.out.print("please enter your command: ");

            String command = in.nextLine();

            System.out.println();

            if (command.equals("addN")) {

                addNeighbourhoodController();

            } else if (command.equals("addB")) {

                addBankController();
                in.nextLine();

            } else if (command.equals("addBr")) {

                addBankBranchController();

            } else if (command.equals("delBr")) {

                deleteBankBranchController();

            } else if (command.equals("listB")) {

                listBanksController();

            } else if (command.equals("listBrs")) {

                listBankBranchesController();

            } else if (command.equals("nearB")) {

                nearestBankController();

            } else if (command.equals("nearBr")) {

                nearestBankBranchController();

            } else if (command.equals("availB")) {

                isAvailableBankController();

            } else if (command.equals("searchB")) {

                searchBankController();

            } else if (command.equals("delB")) {

                deleteBankController();

            } else if (command.equals("printB")) {

                printBankDataController();

            } else if (command.equals("printNs")) {

                printNeighbourhoodsController();

            } else if (command.equals("break")) {

                System.out.println("Bye!");
                in.close();
                break;

            } else {

                invalidCommand();

            }

            System.out.println();
        }

    }


    public static void printNeighbourhoodsController() {
        System.out.println(Arrays.toString(neighbourhoods.toArray()));
    }

    public static void printBankDataController() {
        System.out.println("BANK_ALL_DATA");

        System.out.print("Name: ");
        String name = in.nextLine();

        Bank bank = banks.search(name);
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

        Bank bank = banks.search(name);
        if (bank == null) {
            System.out.println("BANK NOT FOUND!");
        } else {
            System.out.println(bank);
        }
    }

    public static void deleteBankController() {
        System.out.println("DELETE_BANK");

        System.out.print("Name: ");
        String name = in.nextLine();


        banks.remove(banks.getRoot(), name, 0);
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

        System.out.println("Location: ");
        Coordinate location = addCoordinateController();

        if (location == null) return;

        if (branchesByKDTree.searchByCoordinate(location.x, location.y) == null) {
            System.out.println("This Coordinate is full now!");
            return;
        }

        BankBranch branch = new BankBranch(name, name, location);
        branchesByKDTree.insert(branch);
        branchesByTrieTree.insert(branch);
        banks.insert(new Bank(name, branch));
    }

    public static void addBankBranchController() {
        System.out.println("ADD_BANK_BRANCH");

        System.out.print("Bank Name: ");
        String name = in.nextLine();

        Bank bank = banks.search(name);
        if (bank == null) {

            System.out.println("BANK NOT FOUND!");

        } else {

            System.out.print("Branch Name: ");
            String branchName = in.nextLine();

            System.out.println("Location: ");
            Coordinate location = addCoordinateController();

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

        String name = branchesByKDTree.deleteByCoordinate(location.x, location.y);

        if (name != null) BranchesByTrieTree.remove(branchesByTrieTree.getRoot(), name, 0);
    }

    public static void listBanksController() {
        System.out.println("LIST_BANKS");

        System.out.print("Neighbourhood Name: ");
        String name = in.nextLine();

        // TODO
    }

    public static void listBankBranchesController() {
        System.out.println("LIST_BANK_BRANCHES");

        System.out.print("Bank Name: ");
        String name = in.nextLine();

        Bank bank = banks.search(name);
        for (int i = 0; i < bank.getBranches().size(); i++) {
            System.out.println(bank.getBranches().get(i));
        }
    }

    public static void nearestBankController() {
        System.out.println("NEAREST_BANK");

        System.out.print("Your Location: ");
        Coordinate location = addCoordinateController();

        // TODO
    }

    public static void nearestBankBranchController() {
        System.out.println("NEAREST_BANK_BRANCH");

        System.out.print("Bank Name: ");
        String name = in.next();

        System.out.print("Your Location: ");
        Coordinate location = addCoordinateController();

        // TODO
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
