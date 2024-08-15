import game.GameManager;
import UI.UI;

import java.util.Scanner;

public class BattleShips {

    private static boolean isNewGame = true;

    public static void main(String[] args) {

        UI ui = new UI();
        ui.intro();
        ui.chooseGameMode();

        boolean shipPlacement = ui.placeYourShips();
        GameManager myGame = new GameManager();
        while (isNewGame) {

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
//        GameManager.wipeScreen();
    }
}