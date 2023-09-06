package fr.univ_lyon1.info.m1.mes.factory;
import fr.univ_lyon1.info.m1.mes.model.Patient;

/**.
 * La classe representant la creation de patient
*/
public final class PatientFactory {

    private PatientFactory() { }
    /**.
     * Creer un patient en fonction de typeString
     * Patient possible : Patient
     * @param typeString le type de patient à creer
     * @param name le nom du patient
     * @param ssID ssId du patient
     * @throws IllegalArgumentException si typeSting saisi n'est pas inclu
    */
    public static Patient createPatient(final String typeString, final String name, 
        final String ssID) {
        if (typeString == null) {
            return null;
        } else if (typeString.equalsIgnoreCase("Patient")) {
            return new Patient(name, ssID);
        } else {
            throw new IllegalArgumentException("Wrong type of patient : " + typeString);
        }
    }
}
