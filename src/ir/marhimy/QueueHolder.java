package ir.marhimy;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueHolder extends Thread {

    private static final int MAX = 200;
    ArrayBlockingQueue<Patient> queue = new ArrayBlockingQueue<Patient>(MAX);

    public QueueHolder(List<Patient> initList) {
        initList.forEach(patient -> queue.add(patient));
    }

    @Override
    public void run() {
        while (true) {
            try {
                put();
                //System.out.println("(Queue): " + queue.size() + " patients in line ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void put() throws InterruptedException {
        while (queue.size() == MAX) {
            System.out.println("(Queue): queue is full, waiting for any doctor to accept a patient");
            wait();
        }

        Patient patient = PatientInstantiation.instantiatePatient();
        int arrivalTime = Distribution.calculateRandomPatientArrivalTime();
        long w = Utils.mapMinutesToCpuMillis(arrivalTime);
        System.out.println(
                "(Queue): Patient " + patient.id + " is coming in " + arrivalTime + " minutes");
        wait();
        sleep(w);
        queue.add(patient);
        System.out.println(
                "(Queue): Patient " + patient.id + " has been enqueued");
        notify();
    }

    public synchronized Patient get(Doctor doctor) throws InterruptedException {
        notify();
        while (queue.size() == 0) {
            System.out.println("(Dr. " + doctor.id + "): I see no patients here, waiting for a patient to come");
            wait();
        }

        System.out.println("(Dr. " + doctor.id + "): I see " + queue.size() + " patients in line");
        Patient res = queue.take();
        return res;
    }
}