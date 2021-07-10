package ir.marhimy;

import java.util.Random;

public class Distribution {

    /**
     * Calculating a random session duration in minutes with the lamest implementation possible.
     * @param averageTime indicates how we go around returning numbers, averageTime has more chance.
     * @return a random session duration
     */
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

    public static int calculateRandomPatientArrivalTime() {
        final int rnd = new Random().nextInt(10);
        if (rnd < 8) return 1;
        if (rnd < 9) return 2;
        return 3;
    }
}
