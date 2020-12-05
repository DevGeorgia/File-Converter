/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.lib.json;

import java.io.*;

import org.json.simple.*;

import javax.json.*;

/**
 *
 */
public class JsonToCsv {
    
    
    private void convertDatas(File input) {
        try {
            // Creation du lecteur
            InputStream is = new FileInputStream(input);
            InputStreamReader isr = new InputStreamReader(is);
            FileReader fr = new FileReader(input);

            JsonReader lecteur = Json.createReader(fr);

            // Lecture : on sait que c'est un objet pas un tableau
            JsonValue jsonValeur = (JsonValue) lecteur.read();
            String lsContenu = parcourirArbre2String(jsonValeur, null);
            System.out.println(lsContenu);

            // Fermeture du lecteur
            lecteur.close();
            // Fermeture du flux
            fr.close();

        } catch (IOException e) {
            System.out.println("Main : " + e.getMessage());
        }
    } /// main

    /**
     *
     * @param arbre
     * @param key
     * @return
     */
    public static String parcourirArbre2String(JsonValue arbre, String key) {

        StringBuilder lsb = new StringBuilder();

        try {

            switch (arbre.getValueType()) {
                case OBJECT:
                    JsonObject objet = (JsonObject) arbre;
                    for (String cle : objet.keySet()) {
                        lsb.append(cle);
                        lsb.append(" : ");
                        lsb.append(objet.get(cle));
                        lsb.append("; ");
                    }
                    break;
                case ARRAY:
                    JsonArray tableau = (JsonArray) arbre;
                    for (JsonValue valeur : tableau) {
                        lsb.append(parcourirArbre2String(valeur, null));
                        lsb.append("\n");
                    }
                    break;
                case STRING:
                    JsonString chaine = (JsonString) arbre;
                    lsb.append(chaine);
                    break;
                case NUMBER:
                    JsonNumber numerique = (JsonNumber) arbre;
                    lsb.append(numerique.toString());
                    break;
                case TRUE:
                case FALSE:
                case NULL:
                    lsb.append("Type : ");
                    lsb.append(arbre.getValueType().toString());
                    break;
            }

        } catch (Exception e) {
            lsb.append(e.getMessage());
        }
        return lsb.toString();
    } /// parcourirArbre

}
