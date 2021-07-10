package ir.marhimy;

/**
 * simple patient instantiation
 * might be useful for adding some other criteria to the simulation
 */
public class PatientInstantiation {

    private static int number = 1;
    private static final StringBuilder builder = new StringBuilder();

    public static Patient instantiatePatient() {
        builder.setLength(0);
        return new Patient(builder.append(number++).toString());
    }
}
