package converter.lib.csv;

class CsvToJsonTest {

    @org.junit.jupiter.api.Test
    void convertCsvToJsonTest() {

        CsvToJson.convertCsvToJson("{path}/namefile.csv", ";");
    }
}