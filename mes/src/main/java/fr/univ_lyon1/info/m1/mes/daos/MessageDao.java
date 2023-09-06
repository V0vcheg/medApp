package fr.univ_lyon1.info.m1.mes.daos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.model.Message;
/**.
 * La classe representant le dao des messages
*/
public class MessageDao extends AbstractMapDao<Message> {
    private int cpt = 0;
    @Override
    protected Serializable getKeyForElement(final Message element) {
        return element.getId();
    }
    /**.
     * Le dao est initialisé avec la classe IniConfigDao à partir de initconfig.xml
    */
    public MessageDao() {
        InitConfigDao initMessage = new InitConfigDao();
        List<Message> listMessage =  initMessage.getInitMessages();
        for (Message m : listMessage) {
            this.add(m);
        }
    }
    /**.
     * @param id id du message 
     * @return renvoie la liste des messages correspondantes
    */
    public List<Message> findById(final String id) {
        List<Message> list = new ArrayList<Message>();
        for (Message m : this.getCollection().values()) {
            if (m.getExpediteur().equals(id) || m.getDestinataire().equals(id)) {
                list.add(m);
            }
        }
        return list;
    }
    /**.
     * @param exp l'expediteur du message 
     * @return renvoie la liste des messages correspondantes
    */
    public List<Message> findByExpediteur(final String exp) {
        List<Message> list = new ArrayList<Message>();
        for (Message m : this.getCollection().values()) {
            if (m.getExpediteur().equals(exp)) {
                list.add(m);
            }
        }
        return list;
    }
    /**.
     * @param dst le destinataire du message 
     * @return renvoie la liste des messages correspondantes
    */
    public List<Message> findByDestinataire(final String dst) {
        List<Message> list = new ArrayList<Message>();
        for (Message m : this.getCollection().values()) {
            if (m.getDestinataire().equals(dst)) {
                list.add(m);
            }
        }
        return list;
    }
    /**.
     * Trouve la liste des message entre deux profils
     * @param login1 le 1er profil
     * @param login2 le 2er profil
     * @return renvoie la liste des messages correspondantes
    */
    public List<Message> findConversation(final String login1, final String login2) {
        List<Message> list = new ArrayList<Message>();
        for (Message m : this.getCollection().values()) {
            if (m.getExpediteur().equals(login1) && m.getDestinataire().equals(login2)
            || m.getExpediteur().equals(login2) && m.getDestinataire().equals(login1)) {
                list.add(m);
            }
        }
        return list;
    }
    @Override
    public String add(final Message element) {
        element.setId(cpt);
        Serializable key = getKeyForElement(element);
        cpt++;
        this.getCollection().put(key, element);
        return element.getId();
    }
}
