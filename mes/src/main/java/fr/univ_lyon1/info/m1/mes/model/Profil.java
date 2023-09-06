package fr.univ_lyon1.info.m1.mes.model;

/**.
 * La classe representant les profils des utilisateurs
*/
public class Profil {
    private final String login;
    private final String password;
    private final String type;
    private final String id;

    /**.
     * @param login le login du profil 
     * @param password le mot de passe du profil
     * @param type le type du profil (patient ou HP)
     * @param id l'id du profil
     */
    public Profil(final String login, final String password,
         final String type, final String id) {
        this.login = login;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    /**.
     * @return renvoie le login du profil
     */
    public String getLogin() {
        return login;
    }
    /**.
     * @return renvoie le mdp du profil
     */
    public String getPassword() {
        return password;
    }
    /**.
     * @return renvoie le type du profil (patient/hp)
     */
    public String getType() {
        return type;
    }
    /**.
     * @return renvoie l'id du profil
     */
    public String getIdProfil() {
        return id;
    }

    /**.
     * toString utilisé pour l'ObservableList
     * @return renvoie le login du profil
     */
    @Override
    public String toString() {
        return login;
    }
}
