/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.lib.csv;

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
 */
public class CsvToXml {
    
    /**
     * 
     * @param csvPath
     * @return 
     */
    public static String initiateXml(String csvPath) {

        String fileName = "";
        String xmlFile = "";
        String newPath = "";

        File csvFile = new File(csvPath);
        String csvDirectory = csvFile.getParent();
        System.out.println("csvDirectory : " + csvDirectory);
        
        try {

            String fileNameExt = FilenameUtils.getName(csvPath);

            fileName = FilenameUtils.removeExtension(fileNameExt);
            xmlFile = fileName + ".xml";
            System.out.println(xmlFile);

            // --- Partie 1 : Creation du DOM en memoire
            Document treeDom = new Document(new Element("app"));

            // --- Partie 2 : fermeture ou Output (dom2fichier)
            // --- Ecriture sur le DD dans le document XML
            // --- du contenu de l'arbre DOM qui est en RAM
            XMLOutputter endOne = new XMLOutputter(Format.getPrettyFormat());
            endOne.output(treeDom, new FileOutputStream(csvDirectory + xmlFile));

            System.out.println("Fichier XML créé");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        newPath = csvDirectory + File.separator + xmlFile;
        return newPath;
    }

    /**
     *
     * @param csvPath
     * @param lsSeparator
     */
    public static String convertCsvToXmlWithAttributes(String csvPath, String lsSeparator) {

        String newPath = "";

        try {

            FileReader lfrFichier = new FileReader(csvPath);
            BufferedReader lbrBuffer = new BufferedReader(lfrFichier);
            String lsLigne;
            String lsEntetes = lbrBuffer.readLine();
            String[] tEntetes = lsEntetes.split(lsSeparator);
            int sEntetes = tEntetes.length;
            
            newPath = CsvToXml.initiateXml(csvPath);
            System.out.println("new Path : " + newPath);
            
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
        return newPath;
    }

    /**
     *
     * @param csvPath
     * @param lsSeparator
     */
    public static String convertCsvToXmlWithElements(String csvPath, String lsSeparator) {

        String newPath = "";

        try {

            FileReader lfrFichier = new FileReader(csvPath);
            BufferedReader lbrBuffer = new BufferedReader(lfrFichier);

            String lsLigne;
            String lsEntetes = lbrBuffer.readLine();
            String[] tEntetes = lsEntetes.split(lsSeparator);

           newPath = CsvToXml.initiateXml(csvPath);
           System.out.println("new Path : " + newPath);
           
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
        return newPath;
    }
}
