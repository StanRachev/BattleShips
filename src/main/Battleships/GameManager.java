import java.io.IOException;
import java.util.List;

public class GameManager {

    private boolean isGameOver = false;
    private boolean playerWins = false;

    private void sleep(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void launchGame(int shipDistribution) {

        MapPlayer map = new MapPlayer();

        // Player
        Player player = new Player();

        String playerOneName = player.getPlayerOneName();
        map.createMap(false);
        List<Ship> shipListPlayer = map.createShips(shipDistribution, false);
        map.populateMap(shipListPlayer, false);

        // AI
        AI ai = new AI();

        map.createMap(true);
        shipDistribution = 2; // automatic placement
        List<Ship> shipListAi = map.createShips(shipDistribution, true);
        map.populateMap(shipListAi, true);

        MapPlayer.cellState_t cellState;
        MapPlayer.cellState_t cellStateAi;
        int[] cellSelection;
        int[] aiCellSelection;

        // Game starts
        printGameScreen(map, shipListPlayer, shipListAi, playerOneName);

        while (!isGameOver) {
            // Player turn
            cellSelection = player.chooseCell();

            // returns condition (Hit, Kill) and changes visuals on AI map
            cellState = map.hit(cellSelection[0], cellSelection[1], true);
            printGameScreen(map, shipListPlayer, shipListAi, playerOneName);
            System.out.println("\n" + playerOneName + ": " + cellState + " [" + cellNumber(cellSelection[0],cellSelection[1]) + "]");

            if (allShipsDestroyed(shipListPlayer) || allShipsDestroyed(shipListAi)) {
                isGameOver = true;

                if (allShipsDestroyed(shipListAi)) {
                    playerWins = true;
                }
            }

            if (cellState == MapPlayer.cellState_t.missed) {
                // AI turn
                boolean isPlayingAi = true;
                while (isPlayingAi) {
                    // wait for 1 second before AI turn
                    sleep(1000);

                    // return row & column
                    aiCellSelection = ai.chooseCell();

                    // returns condition (Hit, Kill) and changes visuals on Player map
                    cellStateAi = map.hit(aiCellSelection[0], aiCellSelection[1], false);

                    if (cellStateAi == MapPlayer.cellState_t.Already_hit) {
                        continue;
                    }
                    printGameScreen(map, shipListPlayer, shipListAi, playerOneName);
                    System.out.println("\nAI: " + cellStateAi + " [" + cellNumber(aiCellSelection[0],aiCellSelection[1]) + "]");

                    if (cellStateAi != MapPlayer.cellState_t.missed) {
                        // if missed, wait for 1.5 seconds before next AI turn
                        sleep(1500);
                    } else {
                        isPlayingAi = false;
                    }
                }
            }
        }
    }

    public boolean allShipsDestroyed(List<Ship> myShips) {
        boolean isAllDead = true;
        for (Ship ship : myShips) {
            if (!ship.isDead()) {
                isAllDead = false;
            }
        }
        return isAllDead;
    }

    public void printGameScreen(MapPlayer mapPlayer, List<Ship> shipList, List<Ship> shipListAi, String userName) {
        wipeScreen();

        System.out.println("\n" + userName);
        mapPlayer.printMap(false);
        mapPlayer.printShips(shipList);

        System.out.println("\nAI");
        mapPlayer.printMap(true);
        mapPlayer.printShips(shipListAi);
    }

    public String cellNumber(int letter, int number) {
        char letter1 = ((char) (letter + 65));
        String numberString = Integer.toString(number + 1);

        return letter1 + numberString;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean playerWins() {
        return playerWins;
    }

    public static void wipeScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
