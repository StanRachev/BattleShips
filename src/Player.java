import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {


    public String getUserName() {

        Scanner scan = new Scanner(System.in);
        System.out.print("\nPlease enter your name: ");

        return scan.nextLine();
    }

    public int[] makeGuess() {

        Scanner scanLetter = new Scanner(System.in);
        char userInputLetter = 'm';
        int userInputRow = 0;
        int userInputColumn = 0;

        boolean isLetter = false;
        while (!isLetter) {
            System.out.print("\nPlease enter a letter from A to J: ");
            userInputLetter = scanLetter.next().charAt(0);
            if ((userInputLetter >= 'a' && userInputLetter <= 'j') || (userInputLetter >= 'A' && userInputLetter <= 'J')) {
                isLetter = true;
            }
        }
        userInputRow = Character.toUpperCase(userInputLetter) - 65; // Converting the userInput letter to number from 0 to 9;

        boolean isNumber = false;
        while (!isNumber) {
            System.out.print("Please enter a number: ");
            Scanner scanNumber = new Scanner(System.in);
            try {
                userInputColumn = scanNumber.nextInt();
            } catch(InputMismatchException ar) {
                continue;
            }
            if ((userInputColumn >= 1 && userInputColumn <= 10)) {
                isNumber = true;
            }
        }
        userInputColumn -= 1;

        int[] userInput = new int[2];
        userInput[0] = userInputRow;
        userInput[1] = userInputColumn;

        return userInput;
    }
}