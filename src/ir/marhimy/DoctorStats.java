package ir.marhimy;

import java.util.ArrayList;
import java.util.List;

public class DoctorStats {

    private final List<DoctorSession> sessionDurations = new ArrayList<>();
    final Doctor doctor;

    public DoctorStats(Doctor doctor) {
        this.doctor = doctor;
    }

    void addPatientStats(DoctorSession session) {
        sessionDurations.add(session);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Dr. ");
        builder.append(doctor.id);
        builder.append(": ");
        builder.append("|");
        sessionDurations.forEach(patientStats -> builder.append(String.format("%"+patientStats.sessionDuration*2+"d%s" , patientStats.sessionDuration, "|")));
        return builder.toString();
    }
}
