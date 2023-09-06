package fr.univ_lyon1.info.m1.mes.model;

/**.
 * La classe representant les patients
*/
public class Patient {

    private final String name;
    private final String ssID;

    /**.
     * @param name le nom du patient
     * @param ssID le ssID du patient
     */
    public Patient(final String name, final String ssID) {
        if (name == null || ssID == null) {
            throw new IllegalArgumentException("Empty argument");
        }
        this.name = name;
        this.ssID = ssID;
    }

    /**.
     * @return renvoie le nom du patient
     */
    public String getName() {
        return name;
    }
    /**.
     * @return renvoie le ssid du patient
     */
    public String getSSID() {
        return ssID;
    }
    /**.
     * toString utilisé pour l'ObservableList
     * @return renvoie le nom du patient
     */
    @Override
    public String toString() {
        return name;
    }
}
