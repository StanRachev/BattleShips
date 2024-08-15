package game;

import UI.MapPrinter;
import UI.ShipPrinter;
import board.Map;
import board.Ship;
import gamestate.HitProcessor;
import shipmanagement.ShipManager;
import shipmanagement.ShipPlacer;

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

    private String cellNumber(int letter, int number) {
        char letter1 = ((char) (letter + 65));
        String numberString = Integer.toString(number + 1);

        return letter1 + numberString;
    }

    public void launchGame(boolean shipDistribution) {

        Map map = new Map(10);
        map.initializeCells(false);
        map.initializeCells(true);
        MapPrinter mapPrinter = new MapPrinter(map);

        ShipManager shipManager = new ShipManager(map, mapPrinter);
        ShipPlacer shipPlacer = new ShipPlacer(map);
        HitProcessor hitProcessor = new HitProcessor(map);

        List<Ship> playerShips = shipManager.createShips(shipDistribution, false);
        List<Ship> aiShips = shipManager.createShips(false, true);

        shipPlacer.populateMap(playerShips, false);
        shipPlacer.populateMap(aiShips, true);

        Player player = new Player();
        AI ai = new AI();
        String playerOneName = player.getPlayerOneName();

        // Game starts
        printGameScreen(mapPrinter, playerShips, aiShips, playerOneName);

        while (!isGameOver) {
            handlePlayerTurn(player, hitProcessor, mapPrinter, playerShips, aiShips);

            if (isGameOver) {
                break;
            }
            handleAiTurn(ai, player, hitProcessor, mapPrinter, playerShips, aiShips);
        }
    }

    private void handlePlayerTurn(Player player, HitProcessor hitProcessor, MapPrinter mapPrinter, List<Ship> playerShips, List<Ship> aiShips) {
        while (true) {
            int[] cellSelection = player.chooseCell();
            HitProcessor.cellState_t cellState = hitProcessor.hit(cellSelection[0], cellSelection[1], true);

            printGameScreen(mapPrinter, playerShips, aiShips, player.getPlayerOneName());
            System.out.println("\n" + player.getPlayerOneName() + ": " + cellState + " [" + cellNumber(cellSelection[0],cellSelection[1]) + "]");

            if (allShipsDestroyed(playerShips) || allShipsDestroyed(aiShips)) {
                isGameOver = true;
                playerWins = allShipsDestroyed(aiShips);
            }

            if (cellState == HitProcessor.cellState_t.MISSED) {
                break;
            }
        }
    }

    private void handleAiTurn(AI ai, Player player, HitProcessor hitProcessor, MapPrinter mapPrinter, List<Ship> playerShips, List<Ship> aiShips) {
        while (true) {
            // wait for 1 second before AI turn
            sleep(1000);
            int [] aiCellSelection = ai.chooseCell();
            HitProcessor.cellState_t cellState = hitProcessor.hit(aiCellSelection[0], aiCellSelection[1], false);

            if (cellState == HitProcessor.cellState_t.ALREADY_HIT) {
                continue;
            }

            printGameScreen(mapPrinter, playerShips, aiShips, player.getPlayerOneName());
            System.out.println("\nPlayer.AI: " + cellState + " [" + cellNumber(aiCellSelection[0],aiCellSelection[1]) + "]");

            if (cellState == HitProcessor.cellState_t.MISSED) {
                break;
            } else {
                sleep(1500);
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

    public void printGameScreen(MapPrinter mapPrinter, List<Ship> shipList, List<Ship> shipListAi, String userName) {
        ShipPrinter shipPrinter = new ShipPrinter();

//        wipeScreen();

        System.out.println("\n" + userName);
        mapPrinter.printMap(false);
        shipPrinter.printShips(shipList);

        System.out.println("\nPlayer.AI");
        mapPrinter.printMap(true);
        shipPrinter.printShips(shipListAi);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean playerWins() {
        return playerWins;
    }

/*    public static void wipeScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}