package fr.univ_lyon1.info.m1.mes.utils;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;

public interface EasyImage {

    /**.
     * Cherche une image dans le dossier "/src/main/img"
     * @param chemin chemin du fichier
     * @return Image recherchée
     */
    static Image findImage(final String chemin) {
        try {
            
            File file = new File(chemin);
            String localUrl = file.toURI().toURL().toString();
            return new Image(localUrl);
        } catch (NullPointerException e) {
            System.out.println("image null");
        } catch (MalformedURLException m) {
            System.out.println(m.getMessage());
        }
        return null;
        
    }
}
