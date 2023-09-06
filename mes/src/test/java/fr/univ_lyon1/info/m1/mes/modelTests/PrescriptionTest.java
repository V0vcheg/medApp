package fr.univ_lyon1.info.m1.mes.modelTests;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.model.Prescription;

public class PrescriptionTest {
    Prescription p;

    @BeforeEach
    public void setUp() {
        p = new Prescription("1234", "Dr. Who", "pommade");
    }

    @Test
    public void PrescriptionPatientTest() {
        String patient = p.getPatient();
        assertThat(patient, is("1234"));
    }

    @Test
    public void PrescriptionHPTest() {
        String hp = p.getHealthProfessional();
        assertThat(hp, is("Dr. Who"));
    }

    @Test
    public void PrescriptionContentTest() {
        String content = p.getContent();
        assertThat(content, is("pommade"));
    }

    @Test
    public void getWrongPrescriptionHPTest() {
        String hp = p.getHealthProfessional();
        assertThat(hp, not("Dr.Who"));
    }
}
