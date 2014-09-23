package org.ak;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import org.ak.datagen.config.DataDescription;
import org.ak.datagen.config.DatumDescription;
import org.ak.datagen.config.RandomStringDatumDescription;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.StringReader;

/**
 */
public class StringDatumJAXBTest {

    @Test
    public void testGivenAConfigFileContainingARandomTextElementAndTheMinLengthAndTheMaxLengthAttributesSetWhenTheDataDescriptionIsGeneratedThenARandomStringDatumDescriptionIsCreatedWithAllTheAttributesSet() {

        try {

            DataGenerator dataGenerator = new DataGenerator();

            String expectedName = "randomText";
            int expectedMinLength = 1;
            int expectedMaxLength = 10;
            StringReader reader = new StringReader(
                    "<?xml version=\"1.0\"?>\n" +
                            "\n" +
                            "<data xmlns=\"http://datagen.ak.org\"\n" +
                            "      xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                            "      xsi:schemaLocation=\"http://datagen.ak.org datagenerator.xsd\">\n" +
                            "\n" +
                            "    <csv>\n" +
                            "        <randomText name=\""+expectedName+"\" minLength=\""+expectedMinLength+"\" maxLength=\""+expectedMaxLength+"\" />\n" +
                            "    </csv>\n" +
                            "\n" +
                            "</data>"
            );

            DataDescription dataDescription = dataGenerator.loadConfig(reader);

            DatumDescription datumDescription = dataDescription.getCsvConfig().getDatumDescriptions().get(0);
            assertThat(datumDescription, instanceOf(RandomStringDatumDescription.class));

            RandomStringDatumDescription randomStringDatumDescription = (RandomStringDatumDescription) datumDescription;
            assertThat(randomStringDatumDescription.getMinLength(), is(expectedMinLength));
            assertThat(randomStringDatumDescription.getMaxLength(), is(expectedMaxLength));

        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
            fail("Unexpected exception thrown whilst unmarshalling data");
        }
    }
}
