package fr.univ_lyon1.info.m1.mes.modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;


public class MesTests {

    private MES model;

    @BeforeEach
    public void setUp() {
        model = new MES();
    }

    /**
     * Tests for creating and getting patient in model
    */
    @Test
    public void createPatientTest() {
        model.createPatient("Mr. Pat", "123");
        assertEquals(4, model.getPatients().size());


        Patient p_test = model.getPatient("123");
        assertThrows(IllegalArgumentException.class, () -> { model.getPatient(null);});

        assertEquals(p_test.getName(), "Mr. Pat");
        assertEquals(p_test.getSSID(), "123");
    }

    /**
     * Tests for creating and getting healthPro in model
    */
    @Test
    public void createHPTest() {
        model.createHealthProfessional("Dentist", "Dr. Dent");
        assertEquals(5, model.getHealthProfessionals().size());

        HealthProfessional hp_test = model.getHP("Dr. Dent");
        assertThrows(IllegalArgumentException.class, () -> { model.getHP(null);});

        assertEquals(hp_test.getName(), "Dr. Dent");
    }


    /**
     * Tests for creating and getting prescriptions in model
    */
    @Test
    public void createPrescTest() {

        model.createPatient("Mr. Pat", "123");
        model.createHealthProfessional("Dentist", "Dr. Dent");
        model.createPrescription(new Prescription("123", "Dr. Dent", "Test content"));

        
        assertEquals(1, model.getPrescPatient("123").size());
        assertEquals(model.getPrescPatient("123").get(0).getPatient(), "123");
        
        assertThrows(IllegalArgumentException.class, () -> { model.getPrescPatient(null);});
    }

    /**
     * Tests for removing patient from model
    */
    @Test
    public void removePatientTest() {
        model.createPatient("Mr. Pat", "123");
        assertEquals(4, model.getPatients().size());

        model.removePatient("123");
        assertEquals(3, model.getPatients().size());
    }

    /**
     * Tests for removing prescriptions from model
    */
    @Test
    public void removePrescription() {
        model.createPatient("Mr. Pat", "123");
        model.createHealthProfessional("Dentist", "Dr. Dent");

        Prescription test_p = new Prescription("123", "Dr. Dent", "Test content");
        model.createPrescription(test_p);
        assertEquals(1, model.getPrescPatient("123").size());

        model.removePrescription(test_p.getId());
        assertEquals(0, model.getPrescPatient("123").size());
    }
}
