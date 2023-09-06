package fr.univ_lyon1.info.m1.mes.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import javax.naming.NameNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.daos.PrescriptionDao;

import fr.univ_lyon1.info.m1.mes.model.Prescription;


public class PrescriptionDaoTest {

    PrescriptionDao prescDaoTest;


    @BeforeEach
    public void setUp() {
        try{
            prescDaoTest = new PrescriptionDao();
            assertEquals(4, prescDaoTest.findAll().size());            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
    @Test
    public void ProfessionalDaoAdd() {
        Prescription prescTest = new Prescription("123", "Dr. Dent", "testContent");

        prescDaoTest.add(prescTest);
        assertEquals(5, prescDaoTest.findAll().size());     
        assertEquals("123", prescDaoTest.findOne(prescTest.getId()).getPatient());
    }

    @Test
    public void ProfessionalDaoDelete() {
        Prescription prescTest = new Prescription("123", "Dr. Dent", "testContent");

        prescDaoTest.add(prescTest);
        assertEquals(5, prescDaoTest.findAll().size());     
        assertEquals("123", prescDaoTest.findOne(prescTest.getId()).getPatient());

        try {
            prescDaoTest.delete(prescTest);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            fail(e.getMessage());
        }
        assertEquals(4, prescDaoTest.findAll().size());
    }

    @Test
    public void ProfessionalDaoFindByPatient() {
        Prescription prescTest = new Prescription("123", "Dr. Dent", "testContent");
        Prescription prescTest2 = new Prescription("123", "Dr. Dent", "testContent2");

        prescDaoTest.add(prescTest);
        prescDaoTest.add(prescTest2);

        assertEquals(6, prescDaoTest.findAll().size());
        assertEquals(2, prescDaoTest.findByPatient("123").size());
        assertEquals("testContent", prescDaoTest.findByPatient("123").get(0).getContent());
        assertEquals("testContent2", prescDaoTest.findByPatient("123").get(1).getContent());
    }


}
