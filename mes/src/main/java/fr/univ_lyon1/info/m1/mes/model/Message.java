package fr.univ_lyon1.info.m1.mes.model;
/**.
 * La classe representant les messages
*/
public class Message {
    private String id;
    private String expediteur;
    private String destinataire;
    private String text;
    private String date;
    /**.
     * @param expediteur l'expediteur du message
     * @param destinataire le destinataire du message
     * @param text le contenu du message
     * @param date le moment (date + heure) de l'envoi du message
     */
    public Message(final String expediteur, final String destinataire, 
                                        final String text, final String date) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.text = text;
        this.date = date;
    }
    /**.
     * Affecter un id au message
     * @param id l'id à attribuer
     */
    public void setId(final int id) {
        this.id = Integer.toString(id);
    }
    /**.
     * @return renvoie l'expediteur du message
     */
    public String getExpediteur() {
        return expediteur;
    }
    /**.
     * @return renvoie le destinataire du message
     */
    public String getDestinataire() {
        return destinataire;
    }
    /**.
     * @return renvoie l'id du message
     */
    public String getId() {
        return id;
    }
    /**.
     * @return renvoie le contenu du message
     */
    public String getText() {
        return text;
    }
    /**.
     * @return renvoie la date d'envoi du message
     */
    public String getDate() {
        return date;
    }
    
}
