package fr.univ_lyon1.info.m1.mes.daos;


import fr.univ_lyon1.info.m1.mes.model.Patient;

import java.io.Serializable;
import java.util.List;

/**.
 * La classe representant le dao des patients
*/
public class PatientDao extends AbstractMapDao<Patient> {
    @Override
    protected Serializable getKeyForElement(final Patient element) {
        return element.getSSID();
    }
    /**.
     * Le dao est initialisé avec la classe IniConfigDao à partir de initconfig.xml
    */
    public PatientDao() {

        InitConfigDao initPatient = new InitConfigDao();
        List<Patient> listPatient =  initPatient.getInitConfigPatient();
        for (Patient p : listPatient) {
            this.add(p);
        }
    }
    /**.
     * @param name le nom du patient 
     * @return renvoie le patient correspondant, null sinon
    */
    public Patient findByName(final String name) {
        for (Patient p: this.getCollection().values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
    /**.
     * @param ssid le ssid du patient 
     * @return renvoie le patient correspondant, null sinon
    */
    public Patient findBySSID(final String ssid) {
        for (Patient p: this.getCollection().values()) {
            if (p.getSSID().equals(ssid)) {
                return p;
            }
        }
        return null;
    }
    /**.
     * @param prefix le prefix du nom du patient 
     * @return renvoie le patient correspondant, null sinon
    */
    public Patient findByPrefixe(final String prefix) {
        int length = prefix.length();
        for (Patient p: this.getCollection().values()) {
            String pref = p.getName().substring(0, length);
            if (pref.equals(prefix)) {
                return p;
            }
        }
        return null;
    }
}
