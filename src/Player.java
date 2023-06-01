import java.util.Scanner;

public class Player {

    public int[] makeGuess() {

        Scanner scan = new Scanner(System.in);
        char userInputLetter = 'm';
        int userInputRow = 0;
        int userInputColumn = 0;

        System.out.println();
        boolean isLetter = false;
        while (!isLetter) {
            System.out.print("Please enter a letter from A to J: ");
            userInputLetter = scan.next().charAt(0);
            if ((userInputLetter >= 'a' && userInputLetter <= 'j') || (userInputLetter >= 'A' && userInputLetter <= 'J')) {
                isLetter = true;
            }
        }
        userInputRow = Character.toUpperCase(userInputLetter) - 65; // Convert the userInput letter to number from 0 to 9;
        System.out.print("Please enter a number: ");
        userInputColumn = scan.nextInt();
        userInputColumn -= 1; // subtract -1 from the userInput

        int[] userInput = new int[2];
        userInput[0] = userInputRow;
        userInput[1] = userInputColumn;

        return userInput;
    }
}