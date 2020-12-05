/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.lib.csv;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;

/**
 * 
 */
public class CsvToJson {

    public static String convertCsvToJson(String csvPath, String lsSeparator) {

        String csvFileNameExt = FilenameUtils.getName(csvPath);
        String csvFileName = FilenameUtils.removeExtension(csvFileNameExt);

        File csvFile = new File(csvPath);
        String csvDirectory = csvFile.getParent();
        System.out.println("csvDirectory : " + csvDirectory);
        
        JSONArray objetArray = new JSONArray();

        try (FileReader lfrFichier = new FileReader(csvFile); BufferedReader lbrBuffer = new BufferedReader(lfrFichier)) {
            String lsLigne;
            String lsEntetes = lbrBuffer.readLine();
            String[] tEntetes = lsEntetes.split(lsSeparator);
            int sEntetes = tEntetes.length;
            System.out.println(sEntetes);

            Map<String, String> mapFile;
            String[] tValeurs;

            while ((lsLigne = lbrBuffer.readLine()) != null) {

                if (lsLigne.trim().length() > 0) {

                    mapFile = new LinkedHashMap();
                    tValeurs = lsLigne.split(lsSeparator);

                    for (int i = 0; i < sEntetes; i++) {
                        mapFile.put(tEntetes[i], tValeurs[i]);
                    }
                    objetArray.add(mapFile);
                }
            }

        } catch (IOException e) {
            System.err.println("Erreur de fichier : " + e.getMessage());
        }

        try (FileWriter fwTableau = new FileWriter(csvDirectory + File.separator + csvFileName + ".json")) {
            fwTableau.write(objetArray.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String newFilePath = csvDirectory + File.separator + csvFileName + ".json";
        return newFilePath;
    }

}
