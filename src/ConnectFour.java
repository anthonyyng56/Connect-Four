/*

    Connect Four (Java)
    by Anthony Ng

*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFour {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String[][] board = new String[6][7];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "|   ";
            }
        }

        int color = 0;
        boolean entered;
        int column;
        int row;

        while ((!checkHorizontal(board)) && (!checkVertical(board)) && (!checkDiagonal(board)) && (!isFull(board))) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println("|");
            }
            System.out.print(".............................\n");
            entered = false;

            do {
                try {
                    if (color % 2 == 0) {
                        System.out.print("\nDrop a red disk at column (0 - 6): ");
                    } else if (color % 2 != 0) {
                        System.out.print("\nDrop a yellow disk at column (0 - 6): ");
                    }
                    column = stdin.nextInt();
                    row = board.length - 1;
                    while (!entered) {
                        if (board[row][column].equals("|   ")) {
                            if (color % 2 == 0) {
                                board[row][column] = "| R ";
                            } else if (color % 2 != 0) {
                                board[row][column] = "| Y ";
                            }
                            entered = true;
                            color++;
                        } else {
                            row--;
                        }
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.print("\nIndex out of bounds. Try a different column!\n");
                    stdin.nextLine();
                } catch (InputMismatchException ex) {
                    System.out.print("\nPlease enter a valid column number as an integer. Try again.\n");
                    stdin.nextLine();
                }
            } while (!entered);

            System.out.println();
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("|");
        }
        System.out.print(".............................\n");

        String realColor;
        if (color % 2 == 0) {
            realColor = "yellow";
        } else {
            realColor = "red";
        }
        if (checkHorizontal(board) || checkVertical(board) || checkDiagonal(board)) {
            System.out.print("\nThe " + realColor + " player has won!\n");
            System.exit(1);
        } else if (isFull(board)) {
            System.out.print("It was a tie!");
        }
    }
    public static boolean checkHorizontal(String[][] a) {
        boolean result = false;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length - 3; j++) {
                if ((!(a[i][j].equals("|   "))) && (a[i][j].equals(a[i][j + 1]) && a[i][j + 1].equals(a[i][j + 2]) && a[i][j + 2].equals(a[i][j + 3]))) {
                    result = true;
                }
            }
        }
        return result;
    }
    public static boolean checkVertical(String[][] a) {
        boolean result = false;
        for (int i = 0; i < a[0].length; i++) {
            for (int k = 0; k < a.length - 3; k++) {
                if ((!(a[k][i].equals("|   "))) && (a[k][i].equals(a[k + 1][i]) && a[k + 1][i].equals(a[k + 2][i]) && a[k + 2][i].equals(a[k + 3][i]))) {
                    result = true;
                }
            }
        }
        return result;
    }
    public static boolean checkDiagonal(String[][] a) {
        boolean result = false;
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                if (((i + 3) < a.length) && ((k + 3) < a[i].length)) {
                    if ((!(a[i][k].equals("|   "))) && (a[i][k].equals(a[i + 1][k + 1]) && a[i + 1][k + 1].equals(a[i + 2][k + 2]) && a[i + 2][k + 2].equals(a[i + 3][k + 3]))) {
                        result = true;
                    }
                }
                if (((i + 3) < a.length) && ((k - 3) >= 0)) {
                    if ((!(a[i][k].equals("|   "))) && (a[i][k].equals(a[i + 1][k - 1]) && a[i + 1][k - 1].equals(a[i + 2][k - 2]) && a[i + 2][k - 2].equals(a[i + 3][k - 3]))) {
                        result = true;
                    }
                }
                if (((i - 3) >= 0) && ((k - 3) >= 0)) {
                    if ((!(a[i][k].equals("|   "))) && (a[i][k].equals(a[i - 1][k - 1]) && a[i - 1][k - 1].equals(a[i - 2][k - 2]) && a[i - 2][k - 2].equals(a[i - 3][k - 3]))) {
                        result = true;
                    }
                }
                if (((i - 3) >= 0) && ((k + 3) < a[i].length)) {
                    if ((!(a[i][k].equals("|   "))) && (a[i][k].equals(a[i - 1][k + 1]) && a[i - 1][k + 1].equals(a[i - 2][k + 2]) && a[i - 2][k + 2].equals(a[i - 3][k + 3]))) {
                        result = true;
                    }
                }
            }
        }
        return result;
    }
    public static boolean isFull(String[][] a) {
        boolean full = true;
        for (int i = 0; i < a[0].length; i++) {
            if (a[0][i].equals("|   ")) {
                full = false;
                break;
            }
        }
        return full;
    }
}