package converter.lib.csv;

class CsvToXmlTest {

    @org.junit.jupiter.api.Test
    void initiateXmlTest() {

    }

    @org.junit.jupiter.api.Test
    void convertCsvToXmlWithAttributesTest() {


        // TODO code application logic here
        String starterFile = "{path}/nameFile.csv";
        String lsSeparator = ";";

        //Chose this one if you want a xml data format
        CsvToXml.convertCsvToXmlWithAttributes(starterFile, lsSeparator);
    }

    @org.junit.jupiter.api.Test
    void convertCsvToXmlWithElementsTest() {

        // TODO code application logic here
        String starterFile = "{path}/nameFile.csv";
        String lsSeparator = ";";

        //Chose this one if you want a xml doc format
        CsvToXml.convertCsvToXmlWithElements(starterFile, lsSeparator);
    }
}