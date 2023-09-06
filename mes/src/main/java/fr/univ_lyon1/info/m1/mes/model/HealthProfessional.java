package fr.univ_lyon1.info.m1.mes.model;

import java.util.List;
import java.util.ArrayList;

/**.
 * La classe representant les hps
*/
public class HealthProfessional { 
    private final String name;
    private List<Prescription> predefPrescr = new ArrayList<>();
    /**.
     * @param name le nom du hp
     */
    public HealthProfessional(final String name) {
        if (name == null || name.strip() == "") {
            throw new IllegalArgumentException("Empty argument");
        }
        this.name = name;
    }
    /**.
     * @return renvoie le nom du hp
     */
    public String getName() {
        return name;
    }
    /**.
     * @return renvoie la liste des presciptions prédéfinies du hp
     */
    public List<Prescription> getPredefPrescr() {
        return this.predefPrescr;
    }
}
