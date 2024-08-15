package UI;

import board.Ship;

import java.util.List;

public class ShipPrinter {

    public ShipPrinter() {}

    public void printShips(List<Ship> myShips) {

        int cntr;
        String shipName = "";

        System.out.println();
        for (int i = 2; i <= 5; i++) {
            switch (i) {
                case 2 -> shipName = "Vanguard ";
                case 3 -> shipName = "Triumph ";
                case 4 -> shipName = "Hercules ";
                case 5 -> shipName = "Dreadnought";
            }
            cntr = 0;
            for (Ship myShip : myShips) {
                if (myShip.getHealth() == 0) {
                    myShip.setSize(0);
                }
                if (myShip.getSize() == i) {
                    cntr++;
                }
            }
            System.out.printf("(%d)%s     \t   %d %s%n", i,  shipName, cntr, "ships");
        }
    }
}