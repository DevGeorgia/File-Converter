/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcl.convert.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author GeorgiaLR
 */
public class Csv2Xml {
    
    /**
     * 
     * @param csvFile
     * @return 
     */
    public static String initiateXml(String csvFile) {
        String filePath = "";
        String fileName = "";
        String xmlFile = "";
        String newPath = "";
        
        try {
            String fileNameExt = FilenameUtils.getName(csvFile);
            filePath = FilenameUtils.getPath(csvFile);
            System.out.println(filePath);
            fileName = FilenameUtils.removeExtension(fileNameExt);
            xmlFile = fileName + ".xml";
            System.out.println(xmlFile);

            // --- Partie 1 : Creation du DOM en memoire
            Document treeDom = new Document(new Element("app"));

            // --- Partie 2 : fermeture ou Output (dom2fichier)
            // --- Ecriture sur le DD dans le document XML
            // --- du contenu de l'arbre DOM qui est en RAM
            XMLOutputter endOne = new XMLOutputter(Format.getPrettyFormat());
            endOne.output(treeDom, new FileOutputStream(filePath + xmlFile));

            System.out.println("Fichier XML créé");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        newPath = filePath + xmlFile;
        return newPath;
    }

    /**
     *
     * @param csvFile
     * @param lsSeparator
     */
    public static void convertDatastoData(String csvFile, String lsSeparator) {

        try {

            FileReader lfrFichier = new FileReader(csvFile);
            BufferedReader lbrBuffer = new BufferedReader(lfrFichier);
            String lsLigne;
            String lsEntetes = lbrBuffer.readLine();
            String[] tEntetes = lsEntetes.split(lsSeparator);
            int sEntetes = tEntetes.length;
            
            String newPath = Csv2Xml.initiateXml(csvFile);
            
            // --- Creation du document avec sa racine
            SAXBuilder sxb = new SAXBuilder();

            // --- Partie 1 : Ouverture ou Input (fichier2dom)
            // --- Creation d'un arbre DOM
            // --- Lecture du document XML a partir du DD
            // --- et creation en RAM de l'arbre DOM
            Document treeDom = sxb.build(new File(newPath));
            System.out.println(treeDom);
            // --- Recuperation de l'element racine
            Element app = treeDom.getRootElement();
            System.out.println(app);
            String record = "record";

            String[] tValeurs;

            while ((lsLigne = lbrBuffer.readLine()) != null) {
                if (lsLigne.trim().length() > 0) {
                    tValeurs = lsLigne.split(lsSeparator);
                    Element nLine = new Element(record);
                    System.out.println(nLine);
                    for (int i = 0; i < sEntetes; i++) {
                        // --- Creation et affecttaions des attributs
                        Attribute Attrib = new Attribute(tEntetes[i], tValeurs[i]);
                        nLine.setAttribute(Attrib);
                        System.out.println(Attrib);

                        // --- Ajout de l'element
                    }
                    app.addContent(nLine);
                }
            }
            // --- Fermeture ou Output (dom2fichier)
            // --- Ecriture sur le DD dans le document XML
            // --- du contenu de l'arbre DOM qui est en RAM
            XMLOutputter endTwo = new XMLOutputter(Format.getPrettyFormat());
            endTwo.output(treeDom, new FileOutputStream(newPath));
        } /// try
        catch (IOException | JDOMException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param csvFile
     * @param lsSeparator
     */
    public static void convertDatastoDoc(String csvFile, String lsSeparator) {

        try {

            FileReader lfrFichier = new FileReader(csvFile);
            BufferedReader lbrBuffer = new BufferedReader(lfrFichier);

            String lsLigne;
            String lsEntetes = lbrBuffer.readLine();
            String[] tEntetes = lsEntetes.split(lsSeparator);

           String newPath = Csv2Xml.initiateXml(csvFile);
           
            // --- Creation du document avec sa racine
            SAXBuilder sxb = new SAXBuilder();
            // --- Partie 1 : Ouverture ou Input (fichier2dom)
            // --- Creation d'un arbre DOM
            // --- Lecture du document XML a partir du DD
            // --- et creation en RAM de l'arbre DOM
            Document treeDom = sxb.build(new File(newPath));
            System.out.println(treeDom);
            // --- Recuperation de l'element racine
            Element app = treeDom.getRootElement();
            System.out.println(app);

            String[] tValeurs;

            while ((lsLigne = lbrBuffer.readLine()) != null) {

                if (lsLigne.trim().length() > 0) {
                    Element record = new Element("record");
                    System.out.println(record);

                    tValeurs = lsLigne.split(lsSeparator);

                    for (int i = 0; i < tValeurs.length; i++) {

                        Element element = new Element(tEntetes[i]);
                        System.out.println(element);

                        element.setText(tValeurs[i]);
                        System.out.println(tValeurs[i]);

                        record.addContent(element);

                    }

                    app.addContent(record);
                    System.out.println(record);
                }

            }
            // --- Fermeture ou Output (dom2fichier)
            // --- Ecriture sur le DD dans le document XML
            // --- du contenu de l'arbre DOM qui est en RAM
            XMLOutputter endTwo = new XMLOutputter(Format.getPrettyFormat());
            endTwo.output(treeDom, new FileOutputStream(newPath));
        } /// try
        catch (IOException | JDOMException e) {
            System.err.println(e.getMessage());
        }

    }
}
