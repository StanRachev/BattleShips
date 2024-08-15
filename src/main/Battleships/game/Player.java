package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player {

    private static BufferedReader reader;

    public Player() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private static String readLine() {
        String name = "";
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getPlayerOneName() {
        System.out.print("\nPlease enter your name: ");
        return readLine();
    }

    public int[] chooseCell() {
        boolean isLegitPosition;
        int[] position = new int[2];

        while (true) {
            System.out.print("\nPlease enter position: ");
            isLegitPosition = cellPositionCheck(readLine(), position);

            if (isLegitPosition) {
                break;
            } else {
                System.out.println("Invalid position. Please try again.");
            }
        }
        return position;
    }

    private static boolean cellPositionCheck(String userInputPosition, int[] position) {
        if (userInputPosition.length() < 2 || userInputPosition.length() > 3) {
            return false;
        }

        char userInputLetter = Character.toUpperCase(userInputPosition.charAt(0)); // first char
        if ((userInputLetter >= 'A' && userInputLetter <= 'J')) {
            position[0] = userInputLetter - 'A'; // Converting the letter to number from 0 to 9;

            String userInputNum = userInputPosition.substring(1);
            int userInputColumn = Integer.parseInt(userInputNum) - 1;

            if (userInputColumn >= 0 && userInputColumn < 10) {
                position[1] = userInputColumn;
                return true;
            }
        }
        return false;
    }
}