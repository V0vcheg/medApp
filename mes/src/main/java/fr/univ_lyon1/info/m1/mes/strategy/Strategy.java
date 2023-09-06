package fr.univ_lyon1.info.m1.mes.strategy;

import fr.univ_lyon1.info.m1.mes.daos.PatientDao;
import fr.univ_lyon1.info.m1.mes.model.Patient;


/**.
 * L'interface representant les strategy
*/
public interface Strategy {
    /**.
     * @param dao le dao dont on veut rechercher
     * @param ssid le ssid du patient recherché
     * @return renvoie un patient correspondant, null sinon
     */

    Patient find(PatientDao dao, String ssid);
}
