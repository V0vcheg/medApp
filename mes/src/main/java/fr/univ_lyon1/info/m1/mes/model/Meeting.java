package fr.univ_lyon1.info.m1.mes.model;
import java.util.Random;

/**.
 * La classe representant les meetings
*/
public class Meeting {
    private String date;
    private String patientSSID;
    private String hpID;
    private String id;
    private String hour;
    /**.
     * @param formatDate le date prevu pour le meeting
     * @param ssid le ssid du patient concerné
     * @param hpID l'id du hp concerné
     * l'id du meeting se forme en fonction du ssid et hpID
     * l'heure pour le moment est attribué aleatoirement avec fonction membre randomHour()
     */
    public Meeting(final String formatDate, final String ssid, final String hpID) {
        this.date = formatDate;
        this.patientSSID = ssid;
        this.hpID = hpID;
        this.id = Integer.toString((formatDate + ssid + hpID).hashCode());
        this.hour = randomHour();
        
    }
    /**.
     * Génération auto d'une heure
     * @return renvoie un string de l'heure generée
     */
    public String randomHour() {
        Random random = new Random();
        int hour = 8 + random.nextInt(17);
        int randomMin = 0 + random.nextInt(59);
        String minute;
        if (randomMin < 10) {
            minute = "0" + Integer.toString(randomMin);
        } else {
            minute = Integer.toString(randomMin);
        }
        return Integer.toString(hour) + ":" + minute;
    }
    /**.
     * @return renvoie la date du meeting
     */
    public String getDate() {
        return date;
    }
    /**.
     * @return renvoie l'id du meeting
     */
    public String getId() {
        return id;
    }
    /**.
     * @return renvoie le ssid du patient du meeting
     */
    public String getPatient() {
        return patientSSID;
    }
    /**.
     * @return renvoie l'id du HP du meeting
     */
    public String getHP() {
        return hpID;
    }
    /**.
     * @return renvoie l'heure du meeting
     */
    public String getHour() {
        return hour;
    }
    
}
