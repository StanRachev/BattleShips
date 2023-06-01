public class BattleShips {

    public static void main(String[] args) {

        Map map = new Map();
        map.createMap(-1, -1);
        map.printMap();
        Player player = new Player();
        player.makeGuess();
    }
}
