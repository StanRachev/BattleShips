import java.io.IOException;
import java.util.List;

public class GameLauncher {

    private boolean isGameOver = false;
    private boolean isWinner = false;

    public void launchGame(int shipPlacement) throws InterruptedException, IOException {

        // Player
        Player player = new Player();
        String userName = player.getUserName();
        MapPlayer mapPlayer = new MapPlayer();
        mapPlayer.createMap();
        List<Ship> shipList = mapPlayer.createShips(shipPlacement, false);
        mapPlayer.populateMap(shipList);

        // AI
        AI ai = new AI();
        MapPlayer mapAi = new MapAI();
        mapAi.createMap();
        shipPlacement = 2; // automatic placement
        List<Ship> shipListAi = mapAi.createShips(shipPlacement, true);
        mapAi.populateMap(shipListAi);

        MapPlayer.cellState_t conditionPlayer;
        MapPlayer.cellState_t conditionAi;
        int[] playerGuess;
        int[] aiGuess;

        // Game starts
        printGameScreen(mapPlayer, mapAi, shipList, shipListAi, userName);

        boolean isAlive = true;
        while (isAlive) {

            playerGuess = player.makeGuess();
            conditionPlayer = mapAi.hit(playerGuess[0], playerGuess[1]); // returns condition (Hit, Kill) and changes visuals on AI map
            printGameScreen(mapPlayer, mapAi, shipList, shipListAi, userName);
            System.out.println("\n" + userName + ": " + conditionPlayer + " [" + lastTurn(playerGuess[0],playerGuess[1]) + "]");

            if (allDestroyed(shipList) || allDestroyed(shipListAi)) {
                isAlive = false;
                isGameOver = true;
                if (allDestroyed(shipListAi)) {
                    isWinner = true;
                }
            }
            if (conditionPlayer == MapPlayer.cellState_t.missed) {
                boolean isPlayingAi = true;
                while (isPlayingAi) {

                    Thread.sleep(1000); // wait for 1 second before AI turn

                    aiGuess = ai.makeGuess(); // return row & column
                    conditionAi = mapPlayer.hit(aiGuess[0], aiGuess[1]); // returns condition (Hit, Kill) and changes visuals on Player map
                    if (conditionAi == MapPlayer.cellState_t.Already_hit) {
                        continue;
                    }
                    printGameScreen(mapPlayer, mapAi, shipList, shipListAi, userName);
                    System.out.println("\nAI: " + conditionAi + " [" + lastTurn(aiGuess[0],aiGuess[1]) + "]");

                    if (conditionAi != MapPlayer.cellState_t.missed) {
                        Thread.sleep(1500); // if missed, wait for 2 seconds before next AI turn
                    } else {
                        isPlayingAi = false;
                    }
                }
            }
        }
    }

    public boolean allDestroyed(List<Ship> myShips) {

        boolean isAllDead = true;
        for (Ship ship : myShips) {
            if (!ship.isDead()) {
                isAllDead = false;
            }
        }
        if (isAllDead) {
            return isAllDead;
        }

        return isAllDead;
    }

    public void printGameScreen(MapPlayer mapPlayer, MapPlayer mapAi, List<Ship> shipList, List<Ship> shipListAi, String userName) throws IOException, InterruptedException {

        wipeScreen();
        //System.out.println(playerName);
        System.out.println("\n" + userName);
        mapPlayer.printMap();
        mapPlayer.printShips(shipList);
        System.out.println("\nAI");
        mapAi.printMap();
        mapPlayer.printShips(shipListAi);
    }

    public String lastTurn(int letter, int number) {

        char letter1 = ((char) (letter + 65));
        String numberString = Integer.toString(number + 1);

        return letter1 + numberString;
    }

    public boolean isGameOver() {

        return isGameOver;
    }

    public boolean isWinner() {

        return isWinner;
    }

    public static void wipeScreen() throws IOException, InterruptedException {

//        System.out.print("\033[H\033[2J"); /* Screen is wiped */
//        System.out.flush();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
