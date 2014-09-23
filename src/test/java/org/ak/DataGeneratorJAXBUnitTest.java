package org.ak;

import org.ak.datagen.config.*;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 */
public class DataGeneratorJAXBUnitTest {

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
    public void testThatGivenAConfigFileContainingACSVElementContainingAnIntegerTagWithTheNameAttributeSetAndTheConstantAttributeSetThenInGeneratedDataDescriptionCSVConfigContainsOneIntegerDatumDescriptionWithItsNameAndConstantAttributesSet() throws JAXBException, SAXException {

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

        assertThat(dataDescription.getCsvConfig().getDatumDescriptions(), hasSize(1) );
        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(IntegerDatumDescription.class));

        IntegerDatumDescription integerDatumDescription = (IntegerDatumDescription) datumDescription;

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

        assertThat(dataDescription.getCsvConfig().getDatumDescriptions(), hasSize(3));
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

        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(IntegerDatumDescription.class));

        IntegerDatumDescription integerDatumDescription = (IntegerDatumDescription) datumDescription;
        assertThat(integerDatumDescription.getFrom(), is(expectedFrom));
        assertThat(integerDatumDescription.getTo(), is(expectedTo));
    }

    @Test
    public void testGivenAConfigFileWithACSVConfigContainingAnIntegerElementWithACommaSeparatedListOfIntegersInItsBodyWhenTheDataDescriptionIsGeneratedThenTheIntegerDatumDescriptionContainsASetOfTheIntegersInTheElementBody() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String csvValues = "1,2,3,4,5";
        Set<Integer> expectedSet = Arrays.stream(csvValues.split(","))
                .map(Integer::parseInt)
                .collect(toSet());

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

        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(IntegerDatumDescription.class));

        IntegerDatumDescription integerDatumDescription = (IntegerDatumDescription) datumDescription;

        assertThat(integerDatumDescription.getSet(), is(expectedSet));
    }

    @Test
    public void testGivenAnXmlFileWithACSVConfigContainingAnIntegerElementContainingAnEmptyBodyWhenDataDescriptionIsGeneratedThenTheGeneratedIntegerDatumDescriptionWillContainAnEmptySet() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                        "\n" +
                        "<data xmlns=\"http://datagen.ak.org\"\n" +
                        "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                        "\n" +
                        "    <csv>\n" +
                        "        <integer name=\"integerset\"></integer>\n" +
                        "    </csv>\n" +
                        "\n" +
                        "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(IntegerDatumDescription.class));

        IntegerDatumDescription integerDatumDescription = (IntegerDatumDescription) datumDescription;

        assertThat(integerDatumDescription.getSet().size(), is(0));
    }

    @Test
    public void testGivenAConfigFileWithACSVConfigContainingASequenceTagWithTheStartAndStepAttributesSetWhenTheDatDescriptionIsGeneratedThenAnSequenceDatumDescriptionIsConstructedWithTheStartAndStepAttributesSet() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String expectedName = "sequenceName";
        int expectedStart = 10;
        int expectedStep = 1;
        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                        "\n" +
                        "<data xmlns=\"http://datagen.ak.org\"\n" +
                        "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                        "\n" +
                        "    <csv>\n" +
                        "        <sequence name=\""+expectedName+"\" start=\""+expectedStart+"\" step=\""+expectedStep+"\" />\n" +
                        "    </csv>\n" +
                        "\n" +
                        "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(SequenceDatumDescription.class));

        SequenceDatumDescription sequenceDatumDescription = (SequenceDatumDescription) datumDescription;
        assertThat(sequenceDatumDescription.getName(),  is(expectedName));
        assertThat(sequenceDatumDescription.getStart(), is(expectedStart));
        assertThat(sequenceDatumDescription.getStep(),  is(expectedStep));
    }

    @Test
    public void testGivenAConfigFileWithACSVConfigContainingASequenceTagWithTheNameAndStartAttributesSetAndTheStepAttributeNotSetWhenDataDescriptionIsGeneratedThenDataDescriptionContainsASequenceDatumDescriptionWithTheStartAttributeSetAndTheStepAttributeIsNull() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String expectedName = "sequenceName";
        int expectedStart = 10;

        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                        "\n" +
                        "<data xmlns=\"http://datagen.ak.org\"\n" +
                        "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                        "\n" +
                        "    <csv>\n" +
                        "        <sequence name=\""+expectedName+"\" start=\""+expectedStart+"\" />\n" +
                        "    </csv>\n" +
                        "\n" +
                        "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(SequenceDatumDescription.class));

        SequenceDatumDescription sequenceDatumDescription = (SequenceDatumDescription) datumDescription;
        assertThat(sequenceDatumDescription.getStart(), is(expectedStart));
        assertThat(sequenceDatumDescription.getStep(),  is(nullValue()));
    }

    @Test
    public void testGivenAConfigFileWithACSVConfigContainingASequenceTagFollowedByTwoIntegerTagsWhenTheDataDescriptionIsGeneratedThenItContainsOneSequenceDatumDescriptionAndTwoIntegerDatumDescriptionInTheGivenOrder() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                        "\n" +
                        "<data xmlns=\"http://datagen.ak.org\"\n" +
                        "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                        "\n" +
                        "    <csv>\n" +
                        "        <sequence name=\"ONE\" start=\"1\" />\n" +
                        "        <integer  name=\"TWO\" constant=\"1\" />\n" +
                        "        <integer  name=\"THREE\" constant=\"1\" />\n" +
                        "    </csv>\n" +
                        "\n" +
                        "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);
        List<DatumDescription> datumDescriptions = dataDescription.getCsvConfig().getDatumDescriptions();

        assertThat(datumDescriptions, hasSize(3));

        assertThat(datumDescriptions.get(0), instanceOf(SequenceDatumDescription.class));
        assertThat(datumDescriptions.get(0).getName(), is("ONE"));
        assertThat(datumDescriptions.get(1), instanceOf(IntegerDatumDescription.class));
        assertThat(datumDescriptions.get(1).getName(), is("TWO"));
        assertThat(datumDescriptions.get(2), instanceOf(IntegerDatumDescription.class));
        assertThat(datumDescriptions.get(2).getName(), is("THREE"));
    }

    @Test
    public void testThatGivenAConfigFileContainingACSVElementContainingATextTagWithTheNameAttributeSetAndTheConstantAttributeSetThenInGeneratedDataDescriptionCSVConfigContainsOneStringDatumDescriptionWithItsNameAndConstantAttributesSet() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String expectedName = "name";
        String expectedConstantValue = "the_constant";
        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                        "\n" +
                        "<data xmlns=\"http://datagen.ak.org\"\n" +
                        "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                        "\n" +
                        "    <csv>\n" +
                        "        <text name=\"" + expectedName + "\" constant=\"" + expectedConstantValue + "\"/>\n" +
                        "    </csv>\n" +
                        "\n" +
                        "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        assertThat(dataDescription.getCsvConfig().getDatumDescriptions(), hasSize(1) );
        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(StringDatumDescription.class));

        StringDatumDescription stringDatumDescription = (StringDatumDescription) datumDescription;

        assertThat(stringDatumDescription.getName(), is(expectedName));
        assertThat(stringDatumDescription.getConstant(), is(expectedConstantValue));
    }

    @Test
    public void testGivenAConfigFileWithACSVConfigContainingAnTextElementWithACommaSeparatedListOfStringsInItsBodyWhenTheDataDescriptionIsGeneratedThenTheStringDatumDescriptionContainsASetOfTheStringsInTheElementBody() throws JAXBException, SAXException {

        DataGenerator dataGenerator = new DataGenerator();

        String csvValues = "THESE,ARE,COMMA,SEPARATED";
        Set<String> expectedSet = Arrays.stream(csvValues.split(","))
                .collect(toSet());

        StringReader reader = new StringReader(
                "<?xml version=\"1.0\"?>\n" +
                        "\n" +
                        "<data xmlns=\"http://datagen.ak.org\"\n" +
                        "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                        "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                        "\n" +
                        "    <csv>\n" +
                        "        <text name=\"stringset\">"+csvValues+"</text>\n" +
                        "    </csv>\n" +
                        "\n" +
                        "</data>"
        );

        DataDescription dataDescription = dataGenerator.loadConfig(reader);

        DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
        assertThat(datumDescription, instanceOf(StringDatumDescription.class));

        StringDatumDescription stringDatumDescription = (StringDatumDescription) datumDescription;

        assertThat(stringDatumDescription.getSet(), is(expectedSet));
    }

}
