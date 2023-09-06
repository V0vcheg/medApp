package fr.univ_lyon1.info.m1.mes.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Meeting;
/**.
 * La classe representant le dao des meetings
*/
public class MeetingDao extends AbstractMapDao<Meeting> {
    @Override
    protected Serializable getKeyForElement(final Meeting element) {
        return element.getId();
    }
    /**.
     * Le dao est initialisé avec la classe IniConfigDao à partir de initconfig.xml
    */
    public MeetingDao() {
        InitConfigDao initMeeting = new InitConfigDao();
        List<Meeting> listMeeting =  initMeeting.getInitMeetings();
        for (Meeting m : listMeeting) {
            this.add(m);
        }
    }
    /**.
     * @param ssid ssid du patient 
     * @return renvoie le meeting avec le patient
    */
    public Meeting findByPatient(final String ssid) {
        for (Meeting m : this.getCollection().values()) {
            if (m.getPatient().equals(ssid)) {
                return m;
            }
        }
        return null;
    }
    /**.
     * @param ssid ssid du patient 
     * @return renvoie la liste des meetings avec le patient
    */
    public List<Meeting> findAllByPatient(final String ssid) {
        List<Meeting> list = new ArrayList<>();
        for (Meeting m : this.getCollection().values()) {
            if (m.getPatient().equals(ssid)) {
                list.add(m);
            }
        }
        return list;
    }
    /**.
     * @param hpID l'id du hp 
     * @return renvoie la liste des meetings avec le hp
    */
    public List<Meeting> findAllByHP(final String hpID) {
        List<Meeting> list = new ArrayList<>();
        for (Meeting m : this.getCollection().values()) {
            if (m.getHP().equals(hpID)) {
                list.add(m);
            }
        }
        return list;
    }
    /**.
     * @param hpID l'id du hp 
     * @return renvoie le meetings avec le hp
    */
    public Meeting findByHP(final String hpID) {
        for (Meeting m : this.getCollection().values()) {
            if (m.getHP().equals(hpID)) {
                return m;
            }
        }
        return null;
    }
    
}
