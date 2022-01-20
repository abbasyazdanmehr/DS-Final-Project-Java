package controllers;

import models.Coordinate;
import models.Statics;

import java.util.Scanner;

public final class CommandControllers {

    private static Scanner in = new Scanner(System.in);

    public static void commandController() {

        while (true) {
            String command = in.nextLine();

            if (command.startsWith(Statics.ADD_NEIGHBOURHOOD_COMMAND)) {
                addNeighbourhoodController();
            } else if (command.startsWith(Statics.ADD_BANK_COMMAND)) {
                addBAnkController();
            } else if (command.startsWith(Statics.ADD_BANK_BRANCH_COMMAND)) {
                addBankBranchController();
            } else if (command.startsWith(Statics.DELETE_BANK_BRANCH_COMMAND)) {
                deleteBankBranchController();
            } else if (command.startsWith(Statics.LIST_BANKS_COMMAND)) {
                listBanksController();
            } else if (command.startsWith(Statics.LIST_BANK_BRANCHES_COMMAND)) {
                listBankBranchesController();
            } else if (command.startsWith(Statics.NEAREST_BANK_COMMAND)) {
                nearestBankController();
            } else if (command.startsWith(Statics.NEAREST_BANK_BRANCH_COMMAND)) {
                nearestBankBranchController();
            } else if (command.startsWith(Statics.IS_AVAILABLE_BANK_COMMAND)) {
                isAvailableBankController();
            } else if (command.startsWith("break")) {
                in.close();
                break;
            } else {
                invalidCommand();
            }
        }

    }

    public static Coordinate addCoordinateController() {
        int x, y;

        System.out.println("COORDINATE");

        System.out.print("x: ");
        x = in.nextInt();

        System.out.print("y: ");
        y = in.nextInt();

        System.out.println();

        return new Coordinate(x, y);
    }

    public static void addNeighbourhoodController() {
        System.out.println("ADD_NEIGHBOURHOOD");

        System.out.print("Name: ");
        String name = in.next();

        System.out.println("Left Up: ");
        Coordinate leftUp = addCoordinateController();

        System.out.print("Left Down: ");
        Coordinate leftDown = addCoordinateController();

        System.out.print("Right Down: ");
        Coordinate rightDown = addCoordinateController();

        System.out.print("Right Up: ");
        Coordinate rightUp = addCoordinateController();

        // TODO
    }

    public static void addBAnkController() {
        System.out.println("ADD_BANK");

        System.out.print("Name: ");
        String name = in.next();

        System.out.print("Location: ");
        Coordinate location = addCoordinateController();

        // TODO
    }

    public static void addBankBranchController() {
        System.out.println("ADD_BANK_BRANCH");

        System.out.print("Bank Name: ");
        String name = in.next();

        System.out.print("Branch Name: ");
        String branchName = in.next();

        System.out.println("Location: ");
        Coordinate location = addCoordinateController();

        // TODO
    }

    public static void deleteBankBranchController() {
        System.out.println("DELETE_BANK_BRANCH");

        System.out.println("Location: ");
        Coordinate location = addCoordinateController();

        // TODO
    }

    public static void listBanksController() {
        System.out.println("LIST_BANKS");

        System.out.print("Neighbourhood Name: ");
        String name = in.next();

        // TODO
    }

    public static void listBankBranchesController() {
        System.out.println("LIST_BANK_BRANCHES");

        System.out.print("Bank Name: ");
        String name = in.next();

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
