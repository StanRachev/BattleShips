import java.util.Scanner;

public class Player {

    public void makeGuess() {

        String state = "Hit";

        while (state == "Hit") {

            Scanner scan = new Scanner(System.in);
            String userInput;
            char userInputLetter;
            int userInputLetterInt = 0;
            int userInputNumber = 0;

            System.out.println();
            System.out.print("Please enter a letter: ");
            userInput = scan.nextLine(); // Get a String for position from the user and convert it to row and column
            userInputLetter = userInput.charAt(0);

            switch (Character.toUpperCase(userInputLetter)) {
                case 'A' -> userInputLetterInt = 0;
                case 'B' -> userInputLetterInt = 1;
                case 'C' -> userInputLetterInt = 2;
                case 'D' -> userInputLetterInt = 3;
                case 'E' -> userInputLetterInt = 4;
                case 'F' -> userInputLetterInt = 5;
                case 'G' -> userInputLetterInt = 6;
                case 'H' -> userInputLetterInt = 7;
                case 'I' -> userInputLetterInt = 8;
                case 'J' -> userInputLetterInt = 9;
                default -> throw new IllegalArgumentException("Letters from A to J only");
            }

            System.out.print("Please enter a number: ");
            userInputNumber = scan.nextInt();
            userInputNumber -= 1;

            Ships ship = new Ships();
            state = ship.createShips(userInputLetterInt, userInputNumber);
        }

    }
}
