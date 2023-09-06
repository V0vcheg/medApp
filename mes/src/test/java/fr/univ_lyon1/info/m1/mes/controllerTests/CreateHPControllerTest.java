package fr.univ_lyon1.info.m1.mes.controllerTests;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.controller.CreateHPController;
import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.MES;
import fr.univ_lyon1.info.m1.mes.model.Profil;


public class CreateHPControllerTest {

    CreateHPController controller;
    MES model;

    @BeforeEach
    public void setUp() {        
        controller = new CreateHPController();
        model = new MES();   
        
        controller.setModel(model);
    }

    

    @Test
    public void createProfessionalProfilTest() {
        controller.notifyProfilHPCreated("dr.test", "1234", "Dr.Test", "Generaliste");
        

        Profil p = model.getProfil("dr.test");

      
        assertThat(p.getLogin(), is("dr.test"));
        assertThat(p.getType(), is("professional"));
        assertThat(p.getPassword(), is("1234"));
        assertThat(p.getIdProfil(), is("Dr.Test"));
    }

    @Test
    public void createHealthProfessionalTest() {
        controller.notifyProfilHPCreated("dr.test", "1234", "Dr.Test", "Dentist");
        
        HealthProfessional hp = model.getHP("Dr.Test");

        assertThat(hp.getName(), is("Dr.Test"));
    }


    
    
}
