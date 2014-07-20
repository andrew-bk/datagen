package org.ak;


import org.ak.datagen.config.DataDescription;


import org.ak.datagen.config.IntegerDatumDescription;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import static org.junit.Assert.fail;

/**
 * Unit test for simple App.
 */
public class DataGeneratorTest
{

    @Test
    public void testThatGivenAConfigFileContainingAnEmptyCSVElementWithNoAttributesSetThenInGeneratedDataDescriptionCSVConfigIsNotNullAndItFileAttributeIsNullAndItsDelimiterAttributeIsAComma() throws JAXBException, SAXException, FileNotFoundException {

        DataGenerator dataGenerator = new DataGenerator();

        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<data xmlns=\"http://datagen.ak.org\"\n" +
                "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                "\n" +
                "    <csv/>\n" +
                "\n" +
                "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        assertThat(dataDescription.getCsvConfig(), is(notNullValue()) );
        assertThat(dataDescription.getCsvConfig().getFile(), is(nullValue()));
        assertThat(dataDescription.getCsvConfig().getDelimiter(), is(","));

    }

    @Test
    public void testThatGivenAConfigFileContainingAnEmptyCSVElementWithTheFileAndDelimiterAttributesSetThenInGeneratedDataDescriptionCSVConfigIsNotNullAndItsFileAttributeIsTheSameAsInTheFileAndItsDelimiterAttributeIsTheSameAsInTheFile() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String expectedFile = "C:\\file.txt";
        String expectedDelimiter = "/t";
        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<data xmlns=\"http://datagen.ak.org\"\n" +
                "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                "\n" +
                "    <csv file=\""+ expectedFile +"\" delimiter=\""+ expectedDelimiter +"\"/>\n" +
                "\n" +
                "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        assertThat(dataDescription.getCsvConfig(), is(notNullValue()) );
        assertThat(dataDescription.getCsvConfig().getFile(), is(expectedFile));
        assertThat(dataDescription.getCsvConfig().getDelimiter(), is(expectedDelimiter));
    }

    @Test
    public void testThatGivenAConfigFileContainingACSVElementContainingAnIntegerTagWithTheNameCAttributeSetAndTheConstantAttributeSetThenInGeneratedDataDescriptionCSVConfigContainsOneIntegerDatumDescriptionWithItsNameAndConstantAttributesSet() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String expectedName = "name";
        int expectedConstantValue = 123;
        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<data xmlns=\"http://datagen.ak.org\"\n" +
                "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                "\n" +
                "    <csv>\n" +
                "        <integer name=\"" + expectedName + "\" constant=\"" + expectedConstantValue + "\"/>\n" +
                "    </csv>\n" +
                "\n" +
                "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        assertThat(dataDescription.getCsvConfig().getIntegerDatumDescriptions(), hasSize(1) );
        IntegerDatumDescription integerDatumDescription = dataDescription.getCsvConfig().getIntegerDatumDescriptions().get(0);

        assertThat(integerDatumDescription.getName(), is(expectedName));
        assertThat(integerDatumDescription.getConstant(), is(expectedConstantValue));
    }

    @Test
    public void testGivenAConfigFileContainingACSVElementContainingThreeIntegerTagsThenInGeneratedDataDescriptionCSVConfigContainsThreeIntegerDatumDescriptions() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<data xmlns=\"http://datagen.ak.org\"\n" +
                "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                "\n" +
                "    <csv>\n" +
                "        <integer name=\"one\" constant=\"1\"/>\n" +
                "        <integer name=\"two\" constant=\"2\"/>\n" +
                "        <integer name=\"three\" constant=\"3\"/>\n" +
                "    </csv>\n" +
                "\n" +
                "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        assertThat(dataDescription.getCsvConfig().getIntegerDatumDescriptions(), hasSize(3) );
    }

    @Test
    public void testGivenAConfigFileContainingACSVElementContainingAnIntegerElementWithTheFromAndToAttributesSetWhenTheDataDescriptionIsGeneratedThenAnIntegerDatumDescriptionWillBeCreatedWithTheFromAndToAttributesSet() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        int expectedFrom = 1;
        int expectedTo = 10;
        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<data xmlns=\"http://datagen.ak.org\"\n" +
                "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                "\n" +
                "    <csv>\n" +
                "        <integer name=\"integerRange\" from=\""+expectedFrom+"\" to=\""+expectedTo+"\" />\n" +
                "    </csv>\n" +
                "\n" +
                "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        IntegerDatumDescription integerDatumDescription = dataDescription.getCsvConfig().getIntegerDatumDescriptions().get(0);

        assertThat(integerDatumDescription.getFrom(), is(expectedFrom));
        assertThat(integerDatumDescription.getTo(), is(expectedTo));
    }

    @Test
    public void testGivenAConfigFileWithACSVConfigContainingAnIntegerElementWithACommaSeparatedListOfIntegersInItsBodyWhenTheDataDescriptionIsGeneratedThenTheIntegerDatumDescriptionContainsASetOfTheIntegersInTheElementBody() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String csvValues = "1,2,3,4,5";
        Set<Integer> expectedSet = new HashSet<>();
        for(String s : csvValues.split(",")) {
            expectedSet.add(Integer.parseInt(s));
        }

        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                        "\n" +
                        "<data xmlns=\"http://datagen.ak.org\"\n" +
                        "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                        "\n" +
                        "    <csv>\n" +
                        "        <integer name=\"integerset\">"+csvValues+"</integer>\n" +
                        "    </csv>\n" +
                        "\n" +
                        "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        IntegerDatumDescription integerDatumDescription = dataDescription.getCsvConfig().getIntegerDatumDescriptions().get(0);

        assertThat(integerDatumDescription.getSet(), is(expectedSet));
    }
}
