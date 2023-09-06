package fr.univ_lyon1.info.m1.mes.model;



import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.mes.factory.HPFactory;
import fr.univ_lyon1.info.m1.mes.factory.PatientFactory;
import fr.univ_lyon1.info.m1.mes.observer.Observable;
import fr.univ_lyon1.info.m1.mes.observer.Observer;
import fr.univ_lyon1.info.m1.mes.strategy.Strategy;
import fr.univ_lyon1.info.m1.mes.utils.EasyDateFormatter;

import fr.univ_lyon1.info.m1.mes.daos.MeetingDao;
import fr.univ_lyon1.info.m1.mes.daos.MessageDao;
import fr.univ_lyon1.info.m1.mes.daos.PatientDao;
import fr.univ_lyon1.info.m1.mes.daos.PrescriptionDao;
import fr.univ_lyon1.info.m1.mes.daos.ProfessionalDao;
import fr.univ_lyon1.info.m1.mes.daos.ProfilDao;

/**.
 * Implémentation du model de l'application
*/

public class MES implements Observable {

    private PatientDao patientDao;
    private ProfessionalDao professionalDao;
    private PrescriptionDao prescriptionDao;
    private ProfilDao profilDao;
    private MessageDao messageDao;
    private MeetingDao meetingDao;

    private List<Observer> observerList = new ArrayList<Observer>();


    public MES() {
     
        patientDao = new PatientDao();
        professionalDao = new ProfessionalDao();
        prescriptionDao = new PrescriptionDao();
        profilDao = new ProfilDao();
        messageDao = new MessageDao();
        meetingDao = new MeetingDao();
    }
    
    /**.
     * Crée un nouveau patient en passant par le DAO patient
     * @param name du nouveau patient
     * @param ssid du nouveau patient
     * @return Le patient créé et ajouté au DAO 
     */
    public Patient createPatient(final String name, final String ssid) {
        final Patient p = PatientFactory.createPatient("Patient", name, ssid);
        patientDao.add(p);
        return p;
    }  
    

    /**.
     * Crée un nouveau HP en passant par le DAO HP
     * @param domain expertise du professionnel
     * @param name nom du professionnel
     */
    public void createHealthProfessional(final String domain, final String name) {
        final HealthProfessional hp = HPFactory.createHP(domain, name);
        professionalDao.add(hp);
    };

    /**.
     * Crée un nouveau profil en passant par le DAO profil
     * @param login du nouveau profil
     * @param psswd du nouveau profil
     */
    public void createProfil(final String login, final String psswd, 
                final String type, final String id) {
                    
        final Profil p = new Profil(login, psswd, type, id);
        profilDao.add(p);

    }

    /**.
     * Crée un nouvelle presciption en passant par le DAO de prescription
     * @param p nouvelle prescription déjà initialisée
     */
    public void createPrescription(final Prescription p) {
        prescriptionDao.add(p);
        notifyObservers();
    }

    /**.
     * Crée un nouveau message en passant par le DAO message
     * @param exp expediteur du message
     * @param dst destinataire du messae
     * @param text contenu du messae
     */
    public void createMessage(final String exp, final String dst, final String text) {
        Date date = new Date(System.currentTimeMillis());
        String formatDate = EasyDateFormatter.dateToString(date);
        messageDao.add(new Message(exp, dst, text, formatDate));
        notifyObservers();
    }

    /**.
     * Crée un nouveau RDV en passant par le DAO meeting
     * @param date date du rdv
     * @param ssid du patient concerné par le rdv
     * @param hpID professionnel ayant planifié le rdv
     */
    public void createMeeting(final String date, final String ssid, final String hpID) {
        meetingDao.add(new Meeting(date, ssid, hpID));
        notifyObservers();
    }


    /**.
     * Supprime un patient du DAO patient
     * @param ssid du patient à supprimer
     */
    public void removePatient(final String ssid) {
        patientDao.deleteById(ssid);
    }

    /**.
     * Supprime un prescription du DAO prescription
     * @param id de la prescription à supprimer
     */
    public void removePrescription(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Empty argument : " + id);
        }
        prescriptionDao.deleteById(id);
        notifyObservers();
    }
    /**.
     * Vérification des identifiants 
     * @param login 
     * @param psswd 
     * @return true si le profil existe false sinon
     */
    public boolean login(final String login, final String psswd) {
        return profilDao.authentificated(login, psswd);
    }
    /**.
     * @return renvoie la liste de tous les patients
     */
    public List<Patient> getPatients() {
        return patientDao.findAll();
    }

    /**.
     * @return renvoie la liste de noms des tous les patients
     */
    public List<String> getAllPatientsName() {
        List<String> allPatientName = new ArrayList<>();
        for (Patient p : patientDao.findAll()) {
            allPatientName.add(p.getName());
        }
        return allPatientName;
    }
    /**.
     * @param name nom du patient
     * @return renvoie le patient trouvé dans le DAO
     */
    public Patient getPatientByName(final String name) {
        return patientDao.findByName(name);
    }


    /**.
     * @param name le nom du HP
     * @return renvoie le HP trouvé dans le DAO
     */
    public HealthProfessional getHP(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("Empty argument : " + name);
        }
        return professionalDao.findOne(name);
    }


    public Profil getProfil(final String login) {
        return profilDao.findOne(login);
    }
    /**.
     * @param login login du profil
     * @return renvoie le type du profil (HP ou Patient) trouvé dans le DAO
     */
    public String getProfilType(final String login) {
        return profilDao.findOne(login).getType();
    }
    /**.
     * @return renvoie les logins de tous les HP
     */
    public List<String> getAllHPLogin() {
        return profilDao.findAllHPLogin();
    }
    /**.
     * @return renvoie les profils de tous les HP
     */
    public List<Profil> getAllHP() {
        return profilDao.findAllHP();
    }


    /**.
     * @return renvoie les profils de tous les patients
     */
    public List<Profil> getAllPatientProfil() {
        return profilDao.findAllPatientProfil();
    }
   

    public List<Message> getMessagesByDest(final String login) {
        return messageDao.findByDestinataire(login);
    }

    /**.
     * Renvoie les messages entre les profils
     * @param p1 login du 1er profil
     * @param p2 login du 2eme profil
     * @return renvoie les listes des messages
     */
    public List<Message> getMessagesConv(final String p1, final String p2) {
        return messageDao.findConversation(p1, p2);
    }
    /**.
     * @return renvoie tous les HPs 
     */
    public List<HealthProfessional> getHealthProfessionals() {
        return professionalDao.findAll();
    }
    /**.
     * @param ssid ssid du patient
     * @return renvoie tous les prescriptions prescrit au patient
     */
    public List<Prescription> getPrescPatient(final String ssid) {
        if (ssid == null) {
            throw new IllegalArgumentException("Empty argument : " + ssid);
        }
        return prescriptionDao.findByPatient(ssid);
    }
    /**.
     * @return renvoie tous les types de hp possibles 
     */
    public List<String> getAllDomainHP() {
        return professionalDao.getAllDomain();
    }

    /**.
     * @param profil profil du patient
     * @return renvoie tous les meetings de ce patient 
     */
    public List<Meeting> getPatientMeetings(final String profil) {
        return meetingDao.findAllByPatient(profil);
    }

    /**.
     * @param profil profil du hp
     * @return renvoie tous les meetings de cet hp
     */
    public List<Meeting> getHPMeetings(final String profil) {
        return meetingDao.findAllByHP(profil);
    }

    /**.
     * @param ssid ssid du patient
     * @return renvoie le patient recherché
    */
    public Patient getPatient(final String ssid) {
        if (ssid == null) {
            throw new IllegalArgumentException("Empty argument : " + ssid);
        }
        return patientDao.findOne(ssid);
    }

    /**.
     * Recherche d'un patient selon une stratégie spécifique
     * @param strategy par SSID, Nom ou Préfixe
     * @param search SSID, Nom ou Préfixe du patient recherché
     * @return Patient recherché
     */
    public Patient search(final Strategy strategy, final String search) {
        if (search == null) {
            throw new IllegalArgumentException("Empty argument : " + search);
        }
        return strategy.find(patientDao, search);

    }

  

    /**.
     * Ajout de l'observer dans la liste
     * @param o l'observer
    */
    public void registerObserver(final Observer o) {
        observerList.add(o);
    }

    /**.
     * Suppression de l'observer de la liste
     * @param o l'observer
    */
    public void unRegisterObserver(final Observer o) {
        observerList.remove(o);
    }

    /**.
     * Update de tous les observer
    */
    public void notifyObservers() {       
        for (Observer obs : observerList) {
            obs.update();
        }
    }
   
    
    

}
