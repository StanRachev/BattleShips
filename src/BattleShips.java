import java.util.List;

public class BattleShips {

    public static void main(String[] args) {

        Map map = new Map();
        map.createMap();
        map.printMap();

        List<char[]> listOfShips = map.createShips();
        map.addShip(listOfShips);
        map.printMap();
        Player player = new Player();

        boolean isPlaying = true;
        while (isPlaying) {
            Map.cellState_t condition = Map.cellState_t.INVALID;
            int[] playerGuess = player.makeGuess();
            condition = map.hit(playerGuess[0], playerGuess[1]);
            if (condition == Map.cellState_t.MISS) {
                isPlaying = false;
            }
            map.printMap();
        }
    }
}