import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:");
        printTableMapping();
        char[][] gameTable = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };


        if (new Random().nextBoolean()) {
            makeComputerMove(gameTable);
            printGameTable(gameTable);
        }

        while (true) {
            makeUserMove(gameTable);
            printGameTable(gameTable);
            if (isUserWin(gameTable)) {
                System.out.println("YOU WIN!");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            makeComputerMove(gameTable);
            printGameTable(gameTable);
            if (isComputerWin(gameTable)) {
                System.out.println("COMPUTER WIN!");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry, DRAW!");
                break;
            }
        }
        System.out.println("GAME OVER!");


    }

    private static void printTableMapping() {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}
        };
        printGameTable(mappingTable);
    }


    private static void printGameTable(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + gameTable[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    private static void makeUserMove(char[][] gameTable) {
        while (true) {
            System.out.println("Please enter the number between 1 to 9");
            String string = new Scanner(System.in).nextLine();
            if (string.length() == 1) {
                char digit = string.charAt(0);
                if (digit >= '1' && digit <= '9') {
                    if (makeUserMoveToCell(gameTable, digit)) {
                        return;
                    }
                }
            }
        }

    }

    private static boolean makeUserMoveToCell(char[][] gameTable, char digit) {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}
        };
        for (int i = 0; i < mappingTable.length; i++) {
            for (int j = 0; j < mappingTable[i].length; j++) {
                if (mappingTable[i][j] == digit) {
                    if (gameTable[i][j] == ' ') {
                        gameTable[i][j] = 'X';
                        return true;
                    } else {
                        System.out.println("Can't make move, because the cell is not free! Try again");
                        return false;
                    }
                }
            }

        }
        return false;
    }

    private static void makeComputerMove(char[][] gameTable) {
        Random random = new Random();
        while (true) {

            if (makeComputerMoveAI(gameTable)) {
                return;
            }

            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (gameTable[row][col] == ' ') {
                gameTable[row][col] = 'O';
                System.out.println("Computer made a move.");
                return;
            }
        }
    }

    /////////////////////////////ДОПИСАТЬ////////////////////////////////////////
    private static boolean makeComputerMoveAIOld(char[][] gameTable) {

        if (gameTable[0][0] == 'X' && gameTable[0][2] == 'X' && gameTable[0][1] == ' ') {
            gameTable[0][1] = 'O';
        } else if (gameTable[1][0] == 'X' && gameTable[1][2] == 'X' && gameTable[1][1] == ' ') {
            gameTable[1][1] = 'O';
        } else if (gameTable[2][0] == 'X' && gameTable[2][2] == 'X' && gameTable[2][1] == ' ') {
            gameTable[2][1] = 'O';
        } else if (gameTable[0][0] == 'X' && gameTable[0][1] == 'X' && gameTable[0][2] == ' ') {
            gameTable[0][2] = 'O';
        } else if (gameTable[1][0] == 'X' && gameTable[1][1] == 'X' && gameTable[1][2] == ' ') {
            gameTable[1][2] = 'O';
        } else if (gameTable[2][0] == 'X' && gameTable[2][1] == 'X' && gameTable[2][2] == ' ') {
            gameTable[2][2] = 'O';
        } else if (gameTable[0][1] == 'X' && gameTable[0][2] == 'X' && gameTable[0][0] == ' ') {
            gameTable[0][0] = 'O';
        } else if (gameTable[1][1] == 'X' && gameTable[1][2] == 'X' && gameTable[1][0] == ' ') {
            gameTable[1][0] = 'O';
        } else if (gameTable[2][1] == 'X' && gameTable[2][2] == 'X' && gameTable[2][0] == ' ') {
            gameTable[2][0] = 'O';
        } else if (gameTable[0][0] == 'X' && gameTable[1][1] == 'X' && gameTable[2][2] == ' ') {
            gameTable[2][2] = 'O';
        } else if (gameTable[1][1] == 'X' && gameTable[2][2] == 'X' && gameTable[0][0] == ' ') {
            gameTable[0][0] = 'O';
        } else if (gameTable[0][0] == 'X' && gameTable[2][2] == 'X' && gameTable[1][1] == ' ') {
            gameTable[1][1] = 'O';
        } else if (gameTable[0][2] == 'X' && gameTable[1][1] == 'X' && gameTable[2][0] == ' ') {
            gameTable[2][0] = 'O';
        } else if (gameTable[0][2] == 'X' && gameTable[2][0] == 'X' && gameTable[1][1] == ' ') {
            gameTable[1][1] = 'O';
        } else if (gameTable[1][1] == 'X' && gameTable[2][0] == 'X' && gameTable[0][2] == ' ') {
            gameTable[0][2] = 'O';
        } else {
            return false;
        }
        return true;
    }

    private static boolean makeComputerMoveAI(char[][] gameTable) {

        //for row check
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == 'X' && gameTable[i][1] == 'X' && gameTable[i][2] == ' ') {
                gameTable[i][2] = 'O';
                return true;
            } else if (gameTable[i][0] == 'X' && gameTable[i][1] == ' ' && gameTable[i][2] == 'X') {
                gameTable[i][1] = 'O';
                return true;
            } else if (gameTable[i][0] == ' ' && gameTable[i][1] == 'X' && gameTable[i][2] == 'X') {
                gameTable[i][0] = 'O';
                return true;
            }
        }

        //for col check
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i] == 'X' && gameTable[1][i] == 'X' && gameTable[2][i] == ' ') {
                gameTable[2][i] = 'O';
                return true;
            } else if (gameTable[0][i] == 'X' && gameTable[1][i] == ' ' && gameTable[2][i] == 'X') {
                gameTable[1][i] = 'O';
                return true;
            } else if (gameTable[0][i] == 'X' && gameTable[1][i] == 'X' && gameTable[2][i] == 'X') {
                gameTable[0][i] = 'O';
                return true;
            }
        }

        //for diagonals check
        if (gameTable[0][0] == 'X' && gameTable[1][1] == 'X' && gameTable[2][2] == ' ') {
            gameTable[2][2] = 'O';
            return true;
        } else if (gameTable[0][0] == 'X' && gameTable[1][1] == ' ' && gameTable[2][2] == 'X') {
            gameTable[1][1] = 'O';
            return true;
        } else if (gameTable[0][0] == ' ' && gameTable[1][1] == 'X' && gameTable[2][2] == 'X') {
            gameTable[0][0] = 'O';
            return true;
        } else if (gameTable[0][2] == 'X' && gameTable[1][1] == 'X' && gameTable[2][0] == ' ') {
            gameTable[2][0] = 'O';
            return true;
        } else if (gameTable[0][2] == 'X' && gameTable[1][1] == ' ' && gameTable[2][0] == 'X') {
            gameTable[1][1] = 'O';
            return true;
        } else if (gameTable[0][2] == ' ' && gameTable[1][1] == 'X' && gameTable[2][0] == 'X') {
            gameTable[0][2] = 'O';
            return true;
        } else {
            return false;
        }



    }
    ///////////////////////////////////////////////////////////////////////////////////////

    private static boolean isUserWin(char[][] gameTable) {
        return isWinner(gameTable, 'X');
    }

    private static boolean isComputerWin(char[][] gameTable) {
        return isWinner(gameTable, 'O');
    }

    private static boolean isWinner(char[][] gameTable, char ch) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == ch && gameTable[i][1] == ch && gameTable[i][2] == ch) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (gameTable[0][j] == ch && gameTable[1][j] == ch && gameTable[2][j] == ch) {
                return true;
            }
        }

        if (gameTable[0][0] == ch && gameTable[1][1] == ch && gameTable[2][2] == ch) {
            return true;
        } else return gameTable[0][2] == ch && gameTable[1][1] == ch && gameTable[2][0] == ch;
    }

    private static boolean isDraw(char[][] gameTable) {
        for (char[] chars : gameTable) {
            for (char aChar : chars)
                if (aChar == ' ') {
                    return false;
                }
        }
        return true;
    }
}
