package fr.univ_lyon1.info.m1.mes.model;

/**.
 * La classe representant les prescriptions
*/
public class Prescription {
    private final String hp;
    private final String content;
    private final String patient;
    private final String comment;
    private String id = null;
    /**.
     * @param ssid le ssid du patient à qui on a prescrit
     * @param hp le nom du hp qui prescrit
     * @param content le nom de la prescription
     * la variable comment est null dans ce cas
     */
    public Prescription(final String ssid, final String hp, final String content) {
        /*if (ssid == null || hp == null || content == null) {
            throw new IllegalArgumentException("Empty argument");
        }*/
        this.hp = hp;
        this.content = content;
        this.patient = ssid;
        this.comment = null;
    }
    /**.
     * @param ssid le ssid du patient à qui on a prescrit
     * @param hp le nom du hp qui prescrit
     * @param content le nom de la prescription
     * @param comment le commentaire de la prescription visible qu'au medecin
     */
    public Prescription(final String ssid, final String hp, final String content,
                         final String comment) {
        /*if (ssid == null || hp == null || content == null) {
            throw new IllegalArgumentException("Empty argument");
        }*/
        this.hp = hp;
        this.content = content;
        this.patient = ssid;
        this.comment = comment;
    }
    /**.
     * @return renvoie le nom de la prescription
     */
    public String getContent() {
        return content;
    }
    /**.
     * @return renvoie le nom du hp de la prescription
     */
    public String getHealthProfessional() {
        return hp;
    }
    /**.
     * @return renvoie l'id de la prescription
     */
    public String getId() {
        return this.id;
    }
    /**.
     * @return renvoie le ssid du patient de la prescription
     */
    public String getPatient() {
        return this.patient;
    }
    /**.
     * @return renvoie le commentaire de la prescription
     */
    public String getComment() {
        return this.comment;
    }
    /**.
     * Affecter un id à la prescription
     * @param i l'id à attribuer
     */
    public void setId(final int i) {
        this.id = Integer.toString(i);
    }

}
