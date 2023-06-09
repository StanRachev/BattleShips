import java.util.List;

public class GameLauncher {

    private boolean isGameOver = false;
    private boolean isWinner = false;

    public void launchGame() throws InterruptedException {

        // Player
        Player player = new Player();
//        String playerName = player.playerName();
        Map mapPlayer = new Map();
        mapPlayer.createMap();
        List<Ship> shipList = mapPlayer.createShips();
        mapPlayer.populateMap(shipList);

        // AI
        AI ai = new AI();
        Map mapAi = new MapAI();
        mapAi.createMap();
        List<Ship> shipListAi = mapAi.createShips();
        mapAi.populateMap(shipListAi);

        printGameScreen(mapPlayer, mapAi, shipList, shipListAi);

        Map.cellState_t conditionPlayer;
        Map.cellState_t conditionAi;
        int[] playerGuess;
        int[] aiGuess;

        boolean isAlive = true;
        while (isAlive) {

            playerGuess = player.makeGuess();
            conditionPlayer = mapAi.hit(playerGuess[0], playerGuess[1]); // returns condition (Hit, Kill) and changes visuals on AI map

            boolean isPlayingAi = true;
            if (conditionPlayer == Map.cellState_t.Already_hit) {

                printGameScreen(mapPlayer, mapAi, shipList, shipListAi);
                System.out.println("\nStan: " + conditionPlayer + " [" + lastTurn(playerGuess[0],playerGuess[1]) + "]");
            }
            if (conditionPlayer == Map.cellState_t.missed) {
                while (isPlayingAi) {

                    aiGuess = ai.makeGuess(); // return row & column
                    conditionAi = mapPlayer.hit(aiGuess[0], aiGuess[1]); // returns condition (Hit, Kill) and changes visuals on Player map
                    if (conditionAi == Map.cellState_t.Already_hit) {
                        continue;
                    }
                    printGameScreen(mapPlayer, mapAi, shipList, shipListAi);
                    System.out.println("\nStan: " + conditionPlayer + " [" + lastTurn(playerGuess[0],playerGuess[1]) + "]");
                    Thread.sleep(1000); // wait for 2 seconds before AI turn
                    System.out.println("AI: " + conditionAi + " [" + lastTurn(aiGuess[0],aiGuess[1]) + "]");

                    if (conditionAi != Map.cellState_t.missed) {
                        Thread.sleep(2000); // if missed, wait for 5 seconds before next AI turn
                    }
                    if (conditionAi == Map.cellState_t.missed) {
                        isPlayingAi = false;
                    }
                }
            }
            if (allDestroyed(shipList) || allDestroyed(shipListAi)) {
                isAlive = false;
                isGameOver = true;
                if (allDestroyed(shipListAi)) {
                    isWinner = true;
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

    public void printGameScreen(Map mapPlayer, Map mapAi, List<Ship> shipList, List<Ship> shipListAi) {

        wipeScreen();
        //System.out.println(playerName);
        System.out.println("\nStan");
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

    public void wipeScreen() {

        System.out.print("\033[H\033[2J"); /* Screen is wiped */
        System.out.flush();
    }
}
