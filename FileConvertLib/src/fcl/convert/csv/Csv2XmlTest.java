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
public class Csv2XmlTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String starterFile = "{path}/nameFile.csv";
        String lsSeparator = ";";
        
        //Chose this one if you want a xml doc format
        //Csv2Xml.convertDatastoDoc(starterFile, lsSeparator);
        
        //Chose this one if you want a xml data format
        Csv2Xml.convertDatastoData(starterFile, lsSeparator);
    }
    
}
