package fr.univ_lyon1.info.m1.mes.daos;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



import fr.univ_lyon1.info.m1.mes.model.HealthProfessional;
import fr.univ_lyon1.info.m1.mes.model.Meeting;
import fr.univ_lyon1.info.m1.mes.model.Patient;
import fr.univ_lyon1.info.m1.mes.model.Prescription;
import fr.univ_lyon1.info.m1.mes.model.Message;
import fr.univ_lyon1.info.m1.mes.model.Profil;

import fr.univ_lyon1.info.m1.mes.factory.HPFactory;
import fr.univ_lyon1.info.m1.mes.factory.PatientFactory;

/**.
 * La classe representant l'initialisation des daos avec des données lues dans initconfig.xml
*/
public class InitConfigDao {
    private Document initDocument;
    /**.
     * Initialisation de la classe par lecture de initconfig.xml
     */
    public InitConfigDao() {
        try {
            init();
        } catch (ParserConfigurationException p) { 
        } catch (SAXException s) { }
    }
    /**.
     * Initialisation de la classe par lecture de initconfig.xml
     */
    public void init() throws ParserConfigurationException, SAXException {
        try {
            File file = 
            new File("src/main/config/initconfig.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = (Document) db.parse(file);
            document.getDocumentElement().normalize();
            this.initDocument = document;
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**.
     * Initialisation du dao des patiens
     */
    public List<Patient> getInitConfigPatient() {

        List<Patient> initPatient = new ArrayList<Patient>();     
        NodeList nList = initDocument.getElementsByTagName("patient");            
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
                Element eElement = (Element) nNode;
                String ssid = eElement.getAttribute("ssid");  
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                initPatient.add(PatientFactory.createPatient("Patient", name, ssid));
            }
        }
        return initPatient;            
    }
    /**.
     * Initialisation du dao des profils
     */
    public List<Profil> getInitConfigProfil() {

        List<Profil> initProfil = new ArrayList<Profil>();     
        NodeList nList = initDocument.getElementsByTagName("patient");            
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
                Element eElement = (Element) nNode;
                String ssid = eElement.getAttribute("ssid");  
                String login = eElement.getElementsByTagName("login").item(0).getTextContent();
                String password = eElement.getElementsByTagName("password")
                                                        .item(0).getTextContent();
                initProfil.add(new Profil(login, password, "patient", ssid));
            }
        }
        nList = initDocument.getElementsByTagName("professional");            
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
                Element eElement = (Element) nNode;
                String name = eElement.getElementsByTagName("nameP").item(0).getTextContent(); 
                String login = eElement.getElementsByTagName("login").item(0).getTextContent();
                String password = eElement.getElementsByTagName("password")
                                                        .item(0).getTextContent();
                initProfil.add(new Profil(login, password, "professional", name));
            }
        }
        return initProfil;            
    }
    /**.
     * Initialisation du dao des HPs
     */
    public List<HealthProfessional> getInitConfigProf() { 

        List<HealthProfessional> initProfessional = new ArrayList<HealthProfessional>();       
        initDocument.getDocumentElement().normalize();
        NodeList nList = initDocument.getElementsByTagName("professional");
        for (int i = 0; i < nList.getLength(); i++) { 
             
            Node nNode = nList.item(i);
                
            if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
                    
                    Element eElement = (Element) nNode;
                    String type = eElement.getAttribute("type");
                    String name = eElement.getElementsByTagName("nameP").item(0).getTextContent();
                    HealthProfessional hp = HPFactory.createHP(type, name);
                    String pr = eElement.getElementsByTagName("presc")
                                    .item(0).getTextContent();
                    String cm = eElement.getElementsByTagName("comment")
                                    .item(0).getTextContent();             
                    hp.getPredefPrescr().add(new Prescription("", "", pr, cm));
                    initProfessional.add(hp);
                }
            }
        return initProfessional;    
    }
    /**.
     * Initialisation du dao des prescriptions avec des prescriptions prédéfinis
     */
    public List<Prescription> getInitConfigPresc() {

        List<Prescription> initPresc = new ArrayList<Prescription>();       
        NodeList nList = initDocument.getElementsByTagName("prescription");
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
                Element eElement = (Element) nNode;
                String patientSSID = eElement
                                        .getElementsByTagName("patientPr")
                                        .item(0).getTextContent();
                String hp = eElement
                                .getElementsByTagName("prescripteur")
                                .item(0).getTextContent();
                String content = eElement
                                    .getElementsByTagName("content")
                                    .item(0).getTextContent();
                initPresc.add(new Prescription(patientSSID, hp, content));
            }
        }
        return initPresc;            
    }
    /**.
     * Initialisation du dao des messages
     */
    public List<Message> getInitMessages() {
        List<Message> initMsg = new ArrayList<Message>();
       
        NodeList nList = initDocument.getElementsByTagName("message");
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
                Element eElement = (Element) nNode;
                String dest = eElement
                                        .getElementsByTagName("dest")
                                        .item(0).getTextContent();
                String exp = eElement
                                .getElementsByTagName("exp")
                                .item(0).getTextContent();
                String text = eElement
                                    .getElementsByTagName("text")
                                    .item(0).getTextContent();
                String date = eElement
                                    .getElementsByTagName("date")
                                    .item(0).getTextContent();

                initMsg.add(new Message(exp, dest, text, date));
            }
        }
        return initMsg;  
    }
    /**.
     * Initialisation du dao des meetings
     */
    public List<Meeting> getInitMeetings() {
        List<Meeting> initMeet = new ArrayList<Meeting>();
       
        NodeList nList = initDocument.getElementsByTagName("meeting");
        for (int i = 0; i < nList.getLength(); i++) { 
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) { 
                Element eElement = (Element) nNode;
                String date = eElement
                                        .getElementsByTagName("dateM")
                                        .item(0).getTextContent();
                String ssid = eElement
                                    .getElementsByTagName("patientM")
                                    .item(0).getTextContent();
                String hp = eElement
                                    .getElementsByTagName("hp")
                                    .item(0).getTextContent();
                initMeet.add(new Meeting(date, ssid, hp));
            }
        }
        return initMeet;  
    }

}

