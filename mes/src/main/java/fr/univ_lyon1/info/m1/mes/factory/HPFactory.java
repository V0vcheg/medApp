package fr.univ_lyon1.info.m1.mes.factory;

import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Dentist;
import fr.univ_lyon1.info.m1.mes.model.Generaliste;
import fr.univ_lyon1.info.m1.mes.model.Homeopath;
import fr.univ_lyon1.info.m1.mes.model.Psycho;

/**.
 * La classe representant la creation des differents sous-type des hps
*/
public final class HPFactory {
 
    private HPFactory() {  }
    /**.
     * Creer un sous-type de hp en fonction de typeString
     * HP possible : Dentist, Generaliste, Homeopath, Psycho
     * @param typeString le type de hp à creer
     * @param name le nom du hp
     * @throws IllegalArgumentException si typeSting saisi n'est pas inclu
    */
    public static HealthProfessional createHP(final String typeString, 
        final String name) {
        if (typeString == null) { 
            return new HealthProfessional(name);
        } else if (typeString.equalsIgnoreCase("Dentist")) {
            return new Dentist(name);
        } else if (typeString.equalsIgnoreCase("Generaliste")) {
            return new Generaliste(name);
        } else if (typeString.equalsIgnoreCase("Homeopath")) {
            return new Homeopath(name);
        } else if (typeString.equalsIgnoreCase("Psycho")) {
            return new Psycho(name);
        } else {
            throw new IllegalArgumentException("Wrong type of HealthProfessional : " + typeString);
        }
    }


}
