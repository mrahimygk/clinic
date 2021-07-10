package ir.marhimy;

public class PatientStats implements Comparable<PatientStats> {

    final Integer queuePosition;
    final String selectedDoctor;
    final Integer sessionDuration;
    final Integer timeSpentInQueue;

    public PatientStats(Integer queuePosition, String selectedDoctor, Integer sessionDuration, Integer timeSpentInQueue) {
        this.queuePosition = queuePosition;
        this.selectedDoctor = selectedDoctor;
        this.sessionDuration = sessionDuration;
        this.timeSpentInQueue = timeSpentInQueue;
    }

    public PatientStats copyWith(Integer queuePosition, String selectedDoctor, Integer sessionDuration, Integer timeSpentInQueue) {
        Integer outQueuePosition;
        String outSelectedDoctor;
        Integer outSessionDuration;
        Integer outTimeSpentInQueue;
        if (queuePosition == null) outQueuePosition = this.queuePosition;
        else outQueuePosition = queuePosition;
        if (selectedDoctor == null) outSelectedDoctor = this.selectedDoctor;
        else outSelectedDoctor = selectedDoctor;
        if (sessionDuration == null) outSessionDuration = this.sessionDuration;
        else outSessionDuration = sessionDuration;
        if (timeSpentInQueue == null) outTimeSpentInQueue = this.timeSpentInQueue;
        else outTimeSpentInQueue = timeSpentInQueue;
        return new PatientStats(
                outQueuePosition,
                outSelectedDoctor,
                outSessionDuration,
                outTimeSpentInQueue
        );
    }

    @Override
    public int compareTo(PatientStats o) {
        if (o.queuePosition == null) return 1;
        if (queuePosition == null) return -1;
        return queuePosition.compareTo(o.queuePosition);
    }
}
