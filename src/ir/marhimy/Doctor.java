package ir.marhimy;

public class Doctor extends Thread {

    public final int averageTime;
    final String id;

    final QueueHolder queueHolder;
    final Simulation simulation;

    final DoctorStats stats;

    public Doctor(String id, int averageTime, QueueHolder queueHolder, Simulation simulation) {
        this.id = id;
        this.averageTime = averageTime;
        this.queueHolder = queueHolder;
        this.simulation = simulation;
        stats = new DoctorStats(this);
    }

    @Override
    public void run() {
        while (true) {
            try {
                int sessionDuration = Distribution.calculateRandomSessionDuration(averageTime);
                long duration = Utils.mapMinutesToCpuMillis(sessionDuration);
                System.out.println("(Dr. " + id + "): accepting next patient");
                final Patient patient = queueHolder.get(this);
                final long acceptTime =System.nanoTime();
                simulation.putPatientDuration(patient, sessionDuration, id, acceptTime);
                stats.addPatientStats(new DoctorSession(patient, sessionDuration, acceptTime));
                System.out.println("(Dr. " + id + "): accepting patient " + patient.id + ". I have " + sessionDuration + " minutes for you");
                sleep(duration);
                System.out.println("(Dr. " + id + "): Done consulting (patient " + patient.id + ")");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return stats.toString();
    }
}
