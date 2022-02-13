package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static Scanner sc = new Scanner(System.in);
    static int currentIncome = 0;
    static int totalIncome = 0;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = sc.nextInt();

        String[][] seatsMatrix = new String[rows][seatsInRow]; //empty room
        for (int i = 0; i < seatsMatrix.length; i++) {
            Arrays.fill(seatsMatrix[i], "S");
        }

        int sw;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            sw = sc.nextInt();
            switch (sw) {
                case 1:
                    printMatrix(seatsMatrix);
                    break;
                case 2:
                    boolean isCorrectInput = true;
                    do {
                        System.out.println();
                        System.out.println("Enter a row number:");
                        int tickRow = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int tickSeat = sc.nextInt();
                        try {
                            if (seatsMatrix[tickRow - 1][tickSeat - 1].equals("B")) {
                                System.out.println();
                                System.out.println("That ticket has already been purchased!");
                                isCorrectInput = false;
                            } else {
                                ticketBuy(tickRow, tickSeat, seatsMatrix);
                                isCorrectInput = true;
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println();
                            System.out.println("Wrong input!");
                            isCorrectInput = false;
                        }
                    } while (!isCorrectInput);
                    break;
                case 3:
                    System.out.println();
                    statistic(seatsMatrix);
                case 0:
                    break;
            }

        } while (sw != 0);


        // Write your code here
    }

    static void statistic(String[][] matrix) {
        int purchasedTickets = 0;

        int totalSeats = matrix.length * matrix[0].length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("B")) {
                    purchasedTickets += 1;
                }
            }
        }

        if (totalSeats > 60) {
            totalIncome = matrix[0].length * (matrix.length / 2 * 10 + (matrix.length - matrix.length / 2) * 8);
        } else {
            totalIncome = totalSeats * 10;
        }
        double percent = (double) purchasedTickets / totalSeats * 100;

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", percent) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }

    static void printMatrix(String[][] matrix) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i < matrix[0].length + 1; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    static void ticketBuy(int rowNum, int seat, String[][] matrix) {

        int price = 10;
        if (matrix.length * matrix[0].length > 60 && rowNum > matrix.length / 2) {   //total seats > 60 and seat in back half room
            price = 8;
        }
        matrix[rowNum - 1][seat - 1] = "B";
        System.out.println();
        System.out.println("Ticket price: $" + price);
        currentIncome += price;

    }
}