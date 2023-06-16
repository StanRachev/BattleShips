import java.util.List;

public class GameLauncher {

    private boolean isGameOver = false;
    private boolean isWinner = false;

    public void launchGame(int shipPlacement) throws InterruptedException {

        // Player
        Player player = new Player();
        String userName = player.getUserName();
        Map mapPlayer = new Map();
        mapPlayer.createMap();
        List<Ship> shipList = mapPlayer.createShips(shipPlacement);
        mapPlayer.populateMap(shipList);

        // AI
        AI ai = new AI();
        Map mapAi = new MapAI();
        mapAi.createMap();
        List<Ship> shipListAi = mapAi.createShips(shipPlacement);
        mapAi.populateMap(shipListAi);

        Map.cellState_t conditionPlayer;
        Map.cellState_t conditionAi;
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
            if (conditionPlayer == Map.cellState_t.missed) {
                boolean isPlayingAi = true;
                while (isPlayingAi) {

                    aiGuess = ai.makeGuess(); // return row & column
                    conditionAi = mapPlayer.hit(aiGuess[0], aiGuess[1]); // returns condition (Hit, Kill) and changes visuals on Player map
                    if (conditionAi == Map.cellState_t.Already_hit) {
                        continue;
                    }
                    printGameScreen(mapPlayer, mapAi, shipList, shipListAi, userName);
                    System.out.println("\n" + userName + ": " + conditionPlayer + " [" + lastTurn(playerGuess[0],playerGuess[1]) + "]");
                    Thread.sleep(1000); // wait for 1 second before AI turn
                    System.out.println("AI: " + conditionAi + " [" + lastTurn(aiGuess[0],aiGuess[1]) + "]");

                    if (conditionAi != Map.cellState_t.missed) {
                        Thread.sleep(2000); // if missed, wait for 2 seconds before next AI turn
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

    public void printGameScreen(Map mapPlayer, Map mapAi, List<Ship> shipList, List<Ship> shipListAi, String userName) {

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

    public static void wipeScreen() {

        System.out.print("\033[H\033[2J"); /* Screen is wiped */
        System.out.flush();
    }
}
