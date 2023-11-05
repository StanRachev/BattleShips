import java.io.IOException;
import java.util.Scanner;

public class BattleShips {

    private static boolean isNewGame = true;

    public static void main(String[] args) throws InterruptedException, IOException {

        int shipPlacement = ui();

        while (isNewGame) {
            Game myGame = new Game();

            boolean isGameOver = false;
            while (!isGameOver) {
                myGame.launchGame(shipPlacement);
                isGameOver = myGame.isGameOver();
            }

            if (myGame.playerWins()) {
                System.out.println("\nYou win!");
            } else {
                System.out.println("\nGame over");
            }
            anotherGame();
        }
    }

    public static int ui() throws InterruptedException {

        UI ui = new UI();
        ui.intro();
        ui.gameMode();

        return ui.placeYourShips();
    }
    public static void anotherGame() throws IOException, InterruptedException {

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
        Game.wipeScreen();
    }
}