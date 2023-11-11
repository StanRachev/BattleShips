import java.util.Scanner;

public class Player {


    public String getUserName() {

        Scanner scan = new Scanner(System.in);
        System.out.print("\nPlease enter your name: ");
        return scan.nextLine();
    }

    public int[] cellPosition() {

        Scanner scan = new Scanner(System.in);

        String userInputPosition;
        int[] inputPositionToNums = new int[3];
        boolean isLegitPosition = false;

        while (!isLegitPosition) {
            userInputPosition = pleaseEnterPosition(scan);
            inputPositionToNums = cellPositionConversion(userInputPosition);
            isLegitPosition = inputPositionToNums[0] == 1;
        }
        return new int[]{inputPositionToNums[1], inputPositionToNums[2]};
    }

    private static String pleaseEnterPosition(Scanner scan) {

        System.out.print("\nPlease enter position: ");
        return scan.next();
    }

    private static int[] cellPositionConversion(String userInputPosition) {

        int isCell = 0;
        int userInputRow = 0;
        int userInputColumn = 0;

        char userInputLetter = userInputPosition.charAt(0); // first char
        if ((userInputLetter >= 'a' && userInputLetter <= 'j') || (userInputLetter >= 'A' && userInputLetter <= 'J')) {
            userInputRow = Character.toUpperCase(userInputLetter) - 'A'; // Converting the letter to number from 0 to 9;
            if (userInputPosition.length() == 3 && (userInputPosition.charAt(1) - '0' == 1) && (userInputPosition.charAt(2) - '0' == 0)) { // if num is 10
                userInputColumn = 9;
                isCell = 1;
            } else if (userInputPosition.length() == 2) {
                char userInputNum = userInputPosition.charAt(1); // second char
                userInputColumn = userInputNum - '1'; // Converting the char to number from 0 to 9;
                if (userInputColumn < 0 || userInputColumn > 10) {
                    return new int[]{isCell, userInputRow, userInputColumn};
                }
                isCell = 1;
            }
        }
        return new int[]{isCell, userInputRow, userInputColumn};
    }
}