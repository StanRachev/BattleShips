import java.util.Random;

public class AI {

    public int[] makeGuess() {

        Random random = new Random();
        int InputRowAi = (random.nextInt(10));
        int InputColumnAi = (random.nextInt(10));

        int[] InputAi = new int[2];
        InputAi[0] = InputRowAi;
        InputAi[1] = InputColumnAi;

        return InputAi;
    }
}