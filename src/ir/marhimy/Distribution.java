package ir.marhimy;

import java.util.Random;

public class Distribution {

    public static int calculateRandomSessionDuration(int averageTime) {
        final int rnd = new Random().nextInt(1000);
        if (rnd < 125) return 1;
        if (rnd < 250) return 2;
        if (rnd < 750) return averageTime;
        if (rnd < 875)
            if (averageTime == 3) return 4;
            else return 3;
        return 5;
    }

    public static double calculateRandomPatientArrivalTime() {
        final int rnd = new Random().nextInt(10);
        if (rnd < 2) return 0.5;
        if (rnd < 6) return 0.1;
        return 0.2;
    }
}
