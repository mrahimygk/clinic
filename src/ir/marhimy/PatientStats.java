package ir.marhimy;

public class PatientStats implements Comparable<PatientStats> {

    final Integer queuePosition;
    final String selectedDoctor;
    final Integer sessionDuration;
    final Integer timeSpentInQueue;
    final Long enqueueTime;
    final Long acceptTime;

    public PatientStats(Integer queuePosition, String selectedDoctor, Integer sessionDuration, Integer timeSpentInQueue, Long enqueueTime, Long acceptTime) {
        this.queuePosition = queuePosition;
        this.selectedDoctor = selectedDoctor;
        this.sessionDuration = sessionDuration;
        this.timeSpentInQueue = timeSpentInQueue;
        this.enqueueTime = enqueueTime;
        this.acceptTime = acceptTime;
    }

    public PatientStats copyWith(Integer queuePosition, String selectedDoctor, Integer sessionDuration, Integer timeSpentInQueue, Long acceptTime) {
        Integer outQueuePosition;
        String outSelectedDoctor;
        Integer outSessionDuration;
        Integer outTimeSpentInQueue;
        Long outAcceptTime;
        if (queuePosition == null) outQueuePosition = this.queuePosition;
        else outQueuePosition = queuePosition;
        if (selectedDoctor == null) outSelectedDoctor = this.selectedDoctor;
        else outSelectedDoctor = selectedDoctor;
        if (sessionDuration == null) outSessionDuration = this.sessionDuration;
        else outSessionDuration = sessionDuration;
        if (timeSpentInQueue == null) outTimeSpentInQueue = this.timeSpentInQueue;
        else outTimeSpentInQueue = timeSpentInQueue;
        if (acceptTime == null) outAcceptTime = this.acceptTime;
        else outAcceptTime = acceptTime;
        return new PatientStats(
                outQueuePosition,
                outSelectedDoctor,
                outSessionDuration,
                outTimeSpentInQueue,
                enqueueTime,
                outAcceptTime);
    }

    @Override
    public int compareTo(PatientStats o) {
        if (o.enqueueTime == null) return 1;
        if (enqueueTime == null) return -1;
        return enqueueTime.compareTo(o.enqueueTime);
    }

    @Override
    public String toString() {
        return "queuePosition: " + queuePosition +
                ", \tselectedDoctor: '" + selectedDoctor + '\'' +
                ", \tsessionDuration: " + sessionDuration +
                ", \ttimeSpentInQueue: " + timeSpentInQueue;
    }
}
