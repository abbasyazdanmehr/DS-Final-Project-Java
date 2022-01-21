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
        int x, y;

        System.out.print("   x: ");
        x = in.nextInt();

        System.out.print("   y: ");
        y = in.nextInt();

        System.out.println();

        return new Coordinate(x, y);
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

        System.out.println("Location: ");

        System.out.println("  Left Up: ");
        Coordinate leftUp = addCoordinateController();

        System.out.println("  Left Down: ");
        Coordinate leftDown = addCoordinateController();

        System.out.println("  Right Down: ");
        Coordinate rightDown = addCoordinateController();

        System.out.println("  Right Up: ");
        Coordinate rightUp = addCoordinateController();

        neighbourhoods.add(
                new Neighbourhood(
                        name,
                        leftUp,
                        leftDown,
                        rightDown,
                        rightUp
                )
        );
    }

    public static void addBankController() {
        System.out.println("ADD_BANK");

        System.out.print("Name: ");
        String name = in.nextLine();

        System.out.println("Location: ");
        Coordinate location = addCoordinateController();

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

        // TODO: implement TrieTree in the branch class
        branchesByKDTree.deleteByCoordinate(location.x, location.y);
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

        // TODO
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
