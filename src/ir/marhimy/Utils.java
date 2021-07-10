package ir.marhimy;

public class Utils {
    public static long mapMinutesToCpuMillis(int duration) {
        return duration * 100L;
    }

    public static long mapLessThanMinuteToCpuMillis(double duration) {
        return (long) (duration * 10L);
    }
}
