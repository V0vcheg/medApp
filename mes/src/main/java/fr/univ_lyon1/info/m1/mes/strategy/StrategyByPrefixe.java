package fr.univ_lyon1.info.m1.mes.strategy;

import fr.univ_lyon1.info.m1.mes.daos.PatientDao;
import fr.univ_lyon1.info.m1.mes.model.Patient;

/**.
 * La classe representant le recherche par prefixe du patient
*/
public class StrategyByPrefixe implements Strategy {
    /**.
     * @param dao le dao dont on veut rechercher
     * @param ssid le ssid du patient recherché
     * @return renvoie un patient correspondant, null sinon
     */
    @Override
    public Patient find(final PatientDao dao, final String ssid) {
        // TODO Auto-generated method stub
        return dao.findByPrefixe(ssid);
    }
    /**.
     * toString utilisé pour l'ObservableList
     * @return renvoie le type de recherche
    */ 
    @Override
    public String toString() {
        return "by Préfixe";
    }
}
