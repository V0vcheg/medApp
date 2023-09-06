package fr.univ_lyon1.info.m1.mes.daoTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.naming.NameNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.daos.ProfessionalDao;
import fr.univ_lyon1.info.m1.mes.model.Dentist;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;


public class ProfessionalDaoTest {

    ProfessionalDao profDaoTest;


    @BeforeEach
    public void setUp() {
        try{
            profDaoTest = new ProfessionalDao();
            assertEquals(4, profDaoTest.findAll().size());            
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
    @Test
    public void ProfessionalDaoAdd() {
        HealthProfessional hp_test = new Dentist("Dr. Dent");

        profDaoTest.add(hp_test);
       // assertEquals(5, profDaoTest.findAll().size());
        assertEquals("Dr. Dent", profDaoTest.findByName("Dr. Dent").getName());

    }

    @Test
    public void ProfessionalDaoDelete() {
        HealthProfessional hp_test = new Dentist("Dr. Dent");

        profDaoTest.add(hp_test);
        assertEquals(5, profDaoTest.findAll().size());
        assertEquals("Dr. Dent", profDaoTest.findByName("Dr. Dent").getName());

        try {
            profDaoTest.delete(hp_test);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            fail(e.getMessage());
        }
        assertEquals(4, profDaoTest.findAll().size());
    }

}
