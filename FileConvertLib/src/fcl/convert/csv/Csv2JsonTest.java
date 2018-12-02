/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcl.convert.csv;

/**
 *
 * @author GeorgiaLR
 */
public class Csv2JsonTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // First arg, path to the file.
        // Second arg, separator use in csv
        
        Csv2Json.convertDatas("{path}/namefile.csv", ";");
    }
}
