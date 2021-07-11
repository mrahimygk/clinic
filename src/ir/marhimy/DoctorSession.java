package ir.marhimy;

class DoctorSession {
    final Patient patient;
    final int sessionDuration;
    final long acceptTime;

    DoctorSession(Patient patient, int sessionDuration, long acceptTime) {
        this.patient = patient;
        this.sessionDuration = sessionDuration;
        this.acceptTime = acceptTime;
    }
}