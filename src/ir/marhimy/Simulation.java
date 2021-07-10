package ir.marhimy;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Simulation {
    public Simulation() {

        final List<Patient> initList = new ArrayList<>();
        initList.add(PatientInstantiation.instantiatePatient());
        initList.add(PatientInstantiation.instantiatePatient());
        initList.add(PatientInstantiation.instantiatePatient());
        initList.add(PatientInstantiation.instantiatePatient());

        final QueueHolder queueHolder = new QueueHolder(initList);

        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("A", 3, queueHolder));
        doctorList.add(new Doctor("B", 4, queueHolder));

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
        }).start();
    }
}
