package fr.univ_lyon1.info.m1.mes.daos;  


import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**.
 * La classe representant le dao des hps
*/
public class ProfessionalDao extends AbstractMapDao<HealthProfessional> {
    
    @Override
    protected Serializable getKeyForElement(final HealthProfessional element) {
        return element.getName();
    }
    /**.
     * Le dao est initialisé avec la classe IniConfigDao à partir de initconfig.xml
    */
    public ProfessionalDao() {

        InitConfigDao initProfessional = new InitConfigDao();
        List<HealthProfessional> listProfessional =  initProfessional.getInitConfigProf();
        for (HealthProfessional p : listProfessional) {
            this.add(p);
        }
    }
    /**.
     * @param name le nom du hp 
     * @return renvoie le patient correspondant, null sinon
    */
    public HealthProfessional findByName(final String name) {
        for (HealthProfessional p: this.getCollection().values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
    /**.
     * Renvoie la liste des domaines possibles :
     * Dentist/Generaliste/Homeopath/Psycho
     * @return la liste des domaines
    */
    public List<String> getAllDomain() {
        List<String> allDomainList = new ArrayList<>();
        allDomainList.add("Dentist");
        allDomainList.add("Generaliste");
        allDomainList.add("Homeopath");
        allDomainList.add("Psycho");
        return allDomainList;
    }

}
