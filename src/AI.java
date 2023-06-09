import java.util.Random;

public class AI {

    public int[] makeGuess() {

        Random randomGenerator = new Random();
        int InputRowAi = (randomGenerator.nextInt(10));
        int InputColumnAi = (randomGenerator.nextInt(10));

        int[] InputAi = new int[2];
        InputAi[0] = InputRowAi;
        InputAi[1] = InputColumnAi;

        return InputAi;
    }
}