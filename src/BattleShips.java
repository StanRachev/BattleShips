import java.util.Scanner;

public class BattleShips {

    private static boolean isNewGame = true;

    public static void main(String[] args) throws InterruptedException {

        while (isNewGame) {

            GameLauncher myGame = new GameLauncher();

            boolean isGameOver = false;
            while (!isGameOver) {
                myGame.launchGame();
                isGameOver = myGame.isGameOver();
            }
            if (myGame.isWinner()) {
                System.out.println("\nYou win!");
            } else {
                System.out.println("\nGame over");
            }
            anotherGame();
        }
    }

    public static void anotherGame() {

        System.out.println("\nDo you want another game?");
        System.out.print("Y/N: ");

        char userInput = 'V';
        boolean isRightLetter = false;
        while (!isRightLetter) {
            Scanner scan = new Scanner(System.in);
            userInput = scan.next().charAt(0);
            if (Character.toUpperCase(userInput) == 'N' || Character.toUpperCase(userInput) == 'Y') {
                isRightLetter = true;
            }
        }

        if (Character.toUpperCase(userInput) == 'N') {
            isNewGame = false;
        }
        System.out.print("\033[H\033[2J"); /* Screen is wiped */
        System.out.flush();
    }
}