import java.util.Scanner;

public class Player {


    public String getUserName() {

        Scanner scan = new Scanner(System.in);
        System.out.print("\nPlease enter your name: ");

        return scan.nextLine();
    }

    public int[] makeGuess() {

        Scanner scanLetter = new Scanner(System.in);
        String userInputPosition = "";
        char userInputCharLetter = 'z';
        char userInputCharNumber = 'y';
        int userInputRow = 0;
        int userInputColumn = 0;

        boolean isLetter = false;
        while (!isLetter) {

            System.out.print("\nPlease enter position: ");
            userInputPosition = scanLetter.next();

            userInputCharLetter = userInputPosition.charAt(0); // firs char
            if ((userInputCharLetter >= 'a' && userInputCharLetter <= 'j') || (userInputCharLetter >= 'A' && userInputCharLetter <= 'J')) {
                userInputRow = Character.toUpperCase(userInputCharLetter) - 65; // Converting the letter to number from 0 to 9;
                if (userInputPosition.length() == 3 && (userInputPosition.charAt(1) - 48 == 1) && (userInputPosition.charAt(2) - 48 == 0)) { // if num is 10
                    userInputColumn = 9;
                    isLetter = true;
                } else if (userInputPosition.length() == 2) {
                    userInputCharNumber = userInputPosition.charAt(1); // second char
                    userInputColumn = userInputCharNumber - 49; // Converting the numChar number from 0 to 9;
                    if (userInputColumn < 0 || userInputColumn > 10) {
                        continue;
                    }
                    isLetter = true;
                }
            }
        }

        int[] userInput = new int[2];
        userInput[0] = userInputRow;
        userInput[1] = userInputColumn;

        return userInput;
    }
}