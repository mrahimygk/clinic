package ir.marhimy;

import java.util.UUID;

public class Doctor extends Thread {

    public final int averageTime;
    public final int restTime = 1;
    public boolean isFree = true;
    public int isFreeTime = 0;
    final UUID id;

    public Doctor(int averageTime) {
        this.averageTime = averageTime;
        id = UUID.randomUUID();
    }

    @Override
    public void run() {
        while (true){
            try {
                //rest
                System.out.println("(Doctor "+id+") : will take "+ restTime + " minutes to rest");
                sleep(Utils.mapMinutesToCpuMillis(restTime));

                //get patient
                int sessionDuration = Distribution.calculateRandomSessionDuration(averageTime);
                long duration = Utils.mapMinutesToCpuMillis(sessionDuration);
                System.out.println("(Doctor "+id+") : accepted patient " + producer.get().toJson());
                System.out.println("(Doctor "+id+") : will take "+ sessionDuration + " minutes to consult");
                sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
