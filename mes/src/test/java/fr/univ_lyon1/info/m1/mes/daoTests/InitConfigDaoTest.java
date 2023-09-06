package fr.univ_lyon1.info.m1.mes.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.daos.InitConfigDao;
import fr.univ_lyon1.info.m1.mes.model.Dentist;
import fr.univ_lyon1.info.m1.mes.model.Generaliste;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Homeopath;
import fr.univ_lyon1.info.m1.mes.model.Meeting;
import fr.univ_lyon1.info.m1.mes.model.Message;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.Profil;
import fr.univ_lyon1.info.m1.mes.model.Psycho;


public class InitConfigDaoTest {
    InitConfigDao initTest;
    
    @BeforeEach
    public void setUp() {
        initTest = new InitConfigDao();           
    }

    @Test
    public void PatientInitTest() {
        List<Patient> listPatient =  initTest.getInitConfigPatient();

        assertEquals("Alice Foo", listPatient.get(0).getName());
        assertEquals("299010212345678", listPatient.get(0).getSSID());

        assertEquals("Bob Bar", listPatient.get(1).getName());
        assertEquals("199010212345678", listPatient.get(1).getSSID());

        assertEquals("Charles Boz", listPatient.get(2).getName());
        assertEquals("102020212345678", listPatient.get(2).getSSID());
    }

    @Test
    public void HpInitTest() {
        List<HealthProfessional> listHP =  initTest.getInitConfigProf();

        assertEquals("Dr. Who", listHP.get(0).getName());
        assertEquals(Dentist.class, listHP.get(0).getClass());

        assertEquals("Dr. Hulk", listHP.get(1).getName());
        assertEquals(Generaliste.class, listHP.get(1).getClass());

        assertEquals("Dr. Hahnemann", listHP.get(2).getName());
        assertEquals(Homeopath.class, listHP.get(2).getClass());

        assertEquals("Dr. Strange", listHP.get(3).getName());

        assertEquals(Psycho.class, listHP.get(3).getClass());

    }

    @Test
    public void ProfilInitTest() {
        List<Profil> listProfils =  initTest.getInitConfigProfil();

        assertEquals("alice.foo", listProfils.get(0).getLogin());
        assertEquals("299010212345678", listProfils.get(0).getIdProfil());
        assertEquals("aa", listProfils.get(0).getPassword());

        assertEquals("bob.bar", listProfils.get(1).getLogin());
        assertEquals("199010212345678", listProfils.get(1).getIdProfil());
        assertEquals("bb", listProfils.get(1).getPassword());

        assertEquals("charles.boz", listProfils.get(2).getLogin());
        assertEquals("102020212345678", listProfils.get(2).getIdProfil());
        assertEquals("cc", listProfils.get(2).getPassword());
    }

    @Test
    public void PrescsInitTest() {
        List<Prescription> listPrescs =  initTest.getInitConfigPresc();

        assertEquals("299010212345678", listPrescs.get(0).getPatient());
        assertEquals("Dr. Who", listPrescs.get(0).getHealthProfessional());
        assertEquals("One apple a day", listPrescs.get(0).getContent());

        assertEquals("299010212345678", listPrescs.get(1).getPatient());
        assertEquals("Dr. Who", listPrescs.get(1).getHealthProfessional());
        assertEquals("Sport twice a week", listPrescs.get(1).getContent());

        assertEquals("199010212345678", listPrescs.get(2).getPatient());
        assertEquals("Dr. Hulk", listPrescs.get(2).getHealthProfessional());

        assertEquals("You're not sick", listPrescs.get(2).getContent());


        assertEquals("102020212345678", listPrescs.get(3).getPatient());
        assertEquals("Dr. Hahnemann", listPrescs.get(3).getHealthProfessional());
        assertEquals("Snake oil", listPrescs.get(3).getContent());
    }

    @Test
    public void MessagesInitTest() {
        List<Message> messagesTest =  initTest.getInitMessages();

        assertEquals("dr.who", messagesTest.get(0).getExpediteur());
        assertEquals("alice.foo", messagesTest.get(0).getDestinataire());
        assertEquals("Bonjour,Alice. Comment Allez-vous?", messagesTest.get(0).getText());
        assertEquals("2022-12-10 15:00:00", messagesTest.get(0).getDate());

        assertEquals("alice.foo", messagesTest.get(1).getExpediteur());
        assertEquals("dr.who", messagesTest.get(1).getDestinataire());
        assertEquals("Bonjour, Dr.Who. Très Bien et vous?", messagesTest.get(1).getText());
        assertEquals("2022-12-10 19:00:00", messagesTest.get(1).getDate());
    }

    @Test
    public void MeetingInitTest() {
        List<Meeting> meetingsTest =  initTest.getInitMeetings();

        assertEquals("Dr. Who", meetingsTest.get(0).getHP());

        assertEquals("2023-01-01", meetingsTest.get(0).getDate());

        assertEquals("299010212345678", meetingsTest.get(0).getPatient());

    }

    
}
