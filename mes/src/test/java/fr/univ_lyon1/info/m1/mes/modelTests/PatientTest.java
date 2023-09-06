package fr.univ_lyon1.info.m1.mes.modelTests;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import fr.univ_lyon1.info.m1.mes.model.Patient;

public class PatientTest {
    Patient p;
    
    @BeforeEach
    public void setUp() {
        p = new Patient("toto", "987654");
    }


    @Test
    public void PatientNameTest() { 
        String name = p.getName();
        assertThat(name, is("toto")); 
    }

    @Test
    public void PatientSSIDTest() { 
        String ssid = p.getSSID();
        assertThat(ssid, is("987654"));
    }

    @Test
    public void getWrongPatientNameTest() { 
        String name = p.getName();
        assertThat(name, not("Toto"));
    }
    
}
