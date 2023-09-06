package fr.univ_lyon1.info.m1.mes.modelTests;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.model.Dentist;
import fr.univ_lyon1.info.m1.mes.model.Generaliste;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Homeopath;

import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.Psycho;


public class HealthProTest {

    private HealthProfessional dentist;
    private HealthProfessional generaliste;
    private HealthProfessional homeopath;
    private HealthProfessional psycho;
    

    @BeforeEach
    public void setUp() {
        dentist = new Dentist("Dr. Dent");
        generaliste = new Generaliste("Dr. Gen");
        homeopath = new Homeopath("Dr. Home");; 
        psycho = new Psycho("Dr. Psi");
    }


    @Test
    /**
     * Tests to check if getName() is working properly for each HP class.
    */
    public void HealthProfessionalName() {
        assertThat(dentist.getName(), is("Dr. Dent"));
        assertThat(generaliste.getName(), is("Dr. Gen"));
        assertThat(homeopath.getName(), is("Dr. Home"));
        assertThat(psycho.getName(), is("Dr. Psi"));
    }

    @Test
    /**
     * Tests the handling of empty name in name parameter
    */
    public void CreateHpWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> { new Dentist("");}); //empty
        assertThrows(IllegalArgumentException.class, () -> { new Dentist(" ");}); //white space
    }

    @Test
    /**
     * Tests of the HP's prescriptions list and add prescription functionnality 
    */
    public void GetPrescriptionsTest() {
        Prescription testP = new Prescription("123", "Dr. Dent", "Test presc content");
        
        assertThat(dentist.getPredefPrescr(), instanceOf(List.class ));

        dentist.getPredefPrescr().add(testP);
        assertEquals(dentist.getPredefPrescr().get(0).getPatient(), "123");
    }

    public void getWrongHealthProfessionalName() {
        // Given
        HealthProfessional hp = new HealthProfessional("Dr. John");

        // When
        String name = hp.getName();

        // Then
        assertThat(name, not("Dr.John")); 
    }


}
