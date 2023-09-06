package fr.univ_lyon1.info.m1.mes.controllerTests;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.univ_lyon1.info.m1.mes.controller.ProfilPatientController;
import fr.univ_lyon1.info.m1.mes.model.MES;


public class ProfilPatientControllerTest {

    ProfilPatientController controller;
    MES model;

    @BeforeEach
    public void setUp() {        
        controller = new ProfilPatientController();
        model = new MES();   
        
        controller.setModel(model);
        controller.setProfil("alice.foo");
    }

    @Test
    public void addPrescriptionTest() { 
        assertEquals(2, model.getPrescPatient("299010212345678").size());
        controller.notifyPrescriptionAdd("299010212345678", "nice");
        assertEquals(3, model.getPrescPatient("299010212345678").size());
        assertEquals("nice", model.getPrescPatient("299010212345678").get(2).getContent());
    }


    @Test
    public void removePrescriptionTest() {
        assertEquals(2, model.getPrescPatient("299010212345678").size());
        controller.notifyPrescriptionRemoved(model.getPrescPatient("299010212345678").get(1).getId(), "299010212345678");
        assertEquals(1, model.getPrescPatient("299010212345678").size());
    }
    
}
