package fr.univ_lyon1.info.m1.mes.controllerTests;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.controller.CreatePatientController;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Profil;

public class CreatePatientControllerTest {

    CreatePatientController controller;
    MES model;

    @BeforeEach
    public void setUp() {        
        controller = new CreatePatientController();
        model = new MES();   
        
        controller.setModel(model);
    }

    @Test
    public void createPatientProfilTest() {
        controller.notifyProfilPatientCreated("laura.foo", "4321", "Laura Foo", "45678");
        

        Profil p = model.getProfil("laura.foo");

      
        assertThat(p.getLogin(), is("laura.foo"));
        assertThat(p.getType(), is("patient"));
        assertThat(p.getPassword(), is("4321"));
        assertThat(p.getIdProfil(), is("45678"));
    }

    @Test
    public void createPatientTest() {
        controller.notifyProfilPatientCreated("laura.foo", "4321", "Laura Foo", "45678");
        

        Patient p = model.getPatient("45678");

      
        assertThat(p.getName(), is("Laura Foo"));
        assertThat(p.getSSID(), is("45678"));
    }
}
