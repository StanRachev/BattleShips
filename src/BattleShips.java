import java.util.List;

public class BattleShips {

    public static void main(String[] args) {

        Map map = new Map();
        map.createMap(); // create new Map

        List<Ship> shipList = map.createShips(); // create new ships
        map.populateMap(shipList); // assign the ships to the map
        map.printMap();
        map.printShips(shipList); // print types/sizes of ships

        Player player = new Player();

        boolean isPlaying = true;
        while (isPlaying) {
            Map.cellState_t condition;
            int[] playerGuess = player.makeGuess(); // player makes a guess
            System.out.print("\033[H\033[2J");
            System.out.flush();
            condition = map.hit(playerGuess[0], playerGuess[1]); // returns condition (Hit, Kill) and changing visual aspect on map
            if (map.allDestroyed(shipList)) { // if all ships are destroyed
                isPlaying = false;
            }
            map.printMap();
            map.printShips(shipList);
            System.out.println();
            System.out.println(condition);
        }
    }
}