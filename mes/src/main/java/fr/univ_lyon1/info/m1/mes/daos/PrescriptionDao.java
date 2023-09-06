package fr.univ_lyon1.info.m1.mes.daos;   

import fr.univ_lyon1.info.m1.mes.model.Prescription;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**.
 * La classe representant le dao des prescriptions
*/
public class PrescriptionDao extends AbstractMapDao<Prescription> {
    private int cpt = 0;
    @Override
    protected Serializable getKeyForElement(final Prescription element) {
        return element.getId();
    }
    /**.
     * Le dao est initialisé avec la classe IniConfigDao à partir de initconfig.xml
    */
    public PrescriptionDao() {

        InitConfigDao initPrescription = new InitConfigDao();
        List<Prescription> listPrescription =  initPrescription.getInitConfigPresc();
        for (Prescription p : listPrescription) {
            this.add(p);
        }
    }
    /**.
     * @param ssid ssid du patient 
     * @return renvoie la liste des prescriptions prescrits au patient
    */
    public List<Prescription> findByPatient(final String ssid) {
        List<Prescription> list = new ArrayList<Prescription>();
        for (Prescription p: this.getCollection().values()) {
            if (p.getPatient().equals(ssid)) {
                list.add(p);
            }
        }
        return list;
    }
    @Override
    public String add(final Prescription element) {
        element.setId(cpt);
        Serializable key = getKeyForElement(element);
        cpt++;
        this.getCollection().put(key, element);
        return element.getId();
    }
}
