package fr.univ_lyon1.info.m1.mes.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import javax.naming.NameNotFoundException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.daos.PatientDao;
import fr.univ_lyon1.info.m1.mes.model.Patient;


public class PatientDaoTest {

    PatientDao patienDaoTest;


    @BeforeEach
    public void setUp() {
        try{
            patienDaoTest = new PatientDao();
            assertEquals(3, patienDaoTest.findAll().size());            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
    @Test
    public void PatientDaoAdd() {
        Patient p_test = new Patient("Mr. P", "123");

        patienDaoTest.add(p_test);
        assertEquals(4, patienDaoTest.findAll().size());
        assertEquals("Mr. P", patienDaoTest.findByName("Mr. P").getName());

    }

    @Test
    public void PatientDaoDelete() {
        Patient p_test = new Patient("Mr. P", "123");

        patienDaoTest.add(p_test);
        assertEquals(4, patienDaoTest.findAll().size());
        assertEquals("Mr. P", patienDaoTest.findByName("Mr. P").getName());

        try {
            patienDaoTest.delete(p_test);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            fail(e.getMessage());
        }
        assertEquals(3, patienDaoTest.findAll().size());
    }

    @Test
    public void PatientDaoFindByName() {
        Patient p_test = new Patient("Mr. P", "123");

        patienDaoTest.add(p_test);
        assertEquals(p_test, patienDaoTest.findByName("Mr. P"));

    }

    @Test
    public void PatientDaoFindBySSID() {
        Patient p_test = new Patient("Mr. P", "123");

        patienDaoTest.add(p_test);
        assertEquals(p_test, patienDaoTest.findBySSID("123"));
    }

    @Test
    public void PatientDaoFindByPrefixe() {
        Patient p_test = new Patient("Mr. P", "123");

        patienDaoTest.add(p_test);
        assertEquals(p_test, patienDaoTest.findByPrefixe("M"));
    }

}
