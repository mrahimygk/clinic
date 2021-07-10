package ir.marhimy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Simulation {

    public Map<Patient, PatientStats> sessionDurations = new HashMap<>();

    public Simulation() {

        final List<Patient> initList = new ArrayList<>();
        initList.add(PatientInstantiation.instantiatePatient());
        /*initList.add(PatientInstantiation.instantiatePatient());
        initList.add(PatientInstantiation.instantiatePatient());
        initList.add(PatientInstantiation.instantiatePatient());*/

        final QueueHolder queueHolder = new QueueHolder(initList);

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("A", 3, queueHolder, this));
        doctorList.add(new Doctor("B", 4, queueHolder, this));

        queueHolder.start();
        doctorList.forEach(Thread::start);

        new Thread(() -> {
            try {
                sleep(1 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            queueHolder.stop();
            doctorList.forEach(Thread::stop);
            System.out.println("END");
            sessionDurations.forEach((p, d) -> System.out.println(p.id + ": " + d.sessionDuration));
        }).start();
    }

    public void putPatientDuration(Patient patient, int sessionDuration) {
        if (sessionDurations.containsKey(patient)) {
            final PatientStats currentStats = sessionDurations.get(patient);
            sessionDurations.put(patient, currentStats.copyWith(
                    null,
                    null,
                    sessionDuration,
                    null));
        } else {
            sessionDurations.put(patient, new PatientStats(
                    null,
                    null,
                    sessionDuration,
                    null));
        }
    }
}
