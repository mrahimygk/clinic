package ir.marhimy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Simulation {

    public final Map<Patient, PatientStats> sessionDurations = new HashMap<>();

    public Simulation() {

        final List<Patient> initList = new ArrayList<>();
        initList.add(PatientInstantiation.instantiatePatient());
        /*initList.add(PatientInstantiation.instantiatePatient());
        initList.add(PatientInstantiation.instantiatePatient());
        initList.add(PatientInstantiation.instantiatePatient());*/

        final QueueHolder queueHolder = new QueueHolder(initList, this);

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("A", 3, queueHolder, this));
        doctorList.add(new Doctor("B", 4, queueHolder, this));

        queueHolder.start();
        doctorList.forEach(Thread::start);

        new Thread(() -> {
            try {
                sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            queueHolder.stop();
            doctorList.forEach(Thread::stop);
            System.out.println("END");
            sessionDurations.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(e -> {
                        final Patient p = e.getKey();
                        final PatientStats d = e.getValue();
                        System.out.println(p.toString() + ", \t" + d.toString());
                    });
            doctorList.forEach(doctor -> {
                System.out.println(doctor.toString());
            });
        }).start();
    }

    public void putPatientDuration(Patient patient, int sessionDuration, String selectedDoctor, long acceptTime) {
        if (sessionDurations.containsKey(patient)) {
            final PatientStats currentStats = sessionDurations.get(patient);
            sessionDurations.put(patient, currentStats.copyWith(
                    null,
                    selectedDoctor,
                    sessionDuration,
                    null,
                    acceptTime));
        } else {
            sessionDurations.put(patient, new PatientStats(
                    null,
                    selectedDoctor,
                    sessionDuration,
                    null, null, acceptTime));
        }
    }

    public void putPatientPosition(Patient patient, int position, long enqueueTime) {
        if (sessionDurations.containsKey(patient)) {
            final PatientStats currentStats = sessionDurations.get(patient);
            sessionDurations.put(patient, currentStats.copyWith(
                    position,
                    null,
                    null,
                    null,
                    null));
        } else {
            sessionDurations.put(patient, new PatientStats(
                    position,
                    null,
                    null,
                    null, enqueueTime, null));
        }
    }
}
