package fr.univ_lyon1.info.m1.mes.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Profil;

/**.
 * La classe representant le dao des profils
*/
public class ProfilDao extends AbstractMapDao<Profil> {
    @Override
    protected Serializable getKeyForElement(final Profil element) {
        return element.getLogin();
    }
    
    /**.
     * Le dao est initialisé avec la classe IniConfigDao à partir de initconfig.xml
    */
    public ProfilDao() {
        InitConfigDao initProfil = new InitConfigDao();
        List<Profil> listProfil =  initProfil.getInitConfigProfil();
        for (Profil p : listProfil) {
            this.add(p);
        }
    }

    /**.
     * @return renvoie la liste des logins des tous les hps
    */
    public List<String> findAllHPLogin() {
        List<String> allHPLogin = new ArrayList<>();
        for (Profil p : this.getCollection().values()) {
            if (p.getType().equals("professional")) {
                allHPLogin.add(p.getLogin());
            }
        }
        return allHPLogin;
    }

 

    /**.
     * @return renvoie la liste des logins des tous les patients
    */
    public List<String> findAllPatientLogin() {
        List<String> allPatientLogin = new ArrayList<>();
        for (Profil p : this.getCollection().values()) {
            if (p.getType().equals("patient")) {
                allPatientLogin.add(p.getLogin());
            }
           
        }
        return allPatientLogin;
    }


    /**.
     * @return renvoie la liste des profils des tous les patients
    */
    public List<Profil> findAllPatientProfil() {
        List<Profil> allPatientLogin = new ArrayList<>();
        for (Profil p : this.getCollection().values()) {
            if (p.getType().equals("patient")) {
                allPatientLogin.add(p);
            }
           
        }
        return allPatientLogin;
    }


    /**.
     * @return renvoie la liste des profils des tous les hps
    */
    public List<Profil> findAllHP() {
        List<Profil> allHPLogin = new ArrayList<>();
        for (Profil p : this.getCollection().values()) {
            if (p.getType().equals("professional")) {
                allHPLogin.add(p);
            }
        }
        return allHPLogin;
    }

    /**.
     * Verifie la correspandance entre le login et le mdp
     * @param login login du profil
     * @param password mdp du profil
     * @return vrai si mdp correspand, faux sinon
    */
    public boolean authentificated(final String login, final String password) {
        return findOne(login) != null && findOne(login).getPassword().equals(password);
    }
}


