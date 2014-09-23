package org.ak;

import org.ak.datagen.config.CSVConfig;
import org.ak.datagen.config.DataDescription;
import org.ak.datagen.data.*;
import org.ak.datagen.format.CSVFormatter;
import org.ak.datagen.output.ConsoleWriter;
import org.ak.datagen.structure.TabularDataStructure;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.Reader;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * TODO
 *
 * Date formatting:
 * - how do we cope with date, date + time or just time as specified as the value for constant or from/to
 * - displayFormat needs to be added to the DateRange and ConstantDate Datums
 *
 * XML Configuration
 *
 * Notes
 * - the datumdescriptino tests are for testing getDautm, the jaxb tests shoudl go in DataGeneratorTest
 *
 * - make the timeone pattern work for the latets test in DaetDatumDeescriptionTest
 * - do tests for when the given constnat date has too much or too little information for each of the 3 types of date (LocalDate, LocalDateTime, ZonedDateTime)
 * - move on to DateRangeDatum
 *
 * - consider putting public String format() in the Datum parent with generate().toString() as default impl
 * - add JAXB bindings and getDatum() code for ConstantDateDatum
 * - add JAXB bindings and getDatu() code for DateRangeDatum
 * - add LocalDate and LocalDateTime support to DateRangeDatum
 *
 * - make dateTimePattern user configurable
 * - split up all of the JAXB tests by tag in the same way as StringDatumJAXBTest and make the tests fail if there is xml parse/xsd errors
 * - cannot mix tags inside the CSV tag
 *
 * ---- Once all elements and attributes for existing datums are covered by xml schema and bindings
 * - Move on to creating the Writer, formatter and TabularDataStructure.  Start with the datums/datastructure
 * - formatting of output (e.g. date formats)                      *
 * - Do the rest of DataGenerator, loading the config from cli arg
 * - make the DateDatumDescription.inputDateFormat user configurable
 *
 * -- change the illegalArgumentExceptions to DataMisconfigurationExceptions in the Datums to make it more user friendly
 * -- Integration testing from XML config to Writer output
 *
 *
 * -- Should do some usability testing where I try misconfiguring the xml and see what the error message quality is like
 *
 */
public class DataGenerator
{
    /**
     *
     * @param args
     */
    public static void main( String[] args )
    {
        int inputFileId = args.length > 0 ? Integer.parseInt(args[0]) : 1;

        HashSet<String> transactionStatusCodes = new HashSet<String>();
        transactionStatusCodes.add("2");
        transactionStatusCodes.add("9");
        transactionStatusCodes.add("26F");

        HashSet<String> countryIsoCodes = new HashSet<>();
        countryIsoCodes.addAll(Arrays.asList("GB", "FR", "DK", "DE", "IT", "NO", "SE"));
        HashSet<String> currencyIsoCodes = new HashSet<>();
        currencyIsoCodes.addAll(Arrays.asList("GBP", "EUR", "USD", "NOK", "SEK"));

        HashSet<String> skus = new HashSet<>();
        skus.add("PSN_SKU_ID");
        HashSet<String> titleIds = new HashSet<>();
        titleIds.add("PSN_TITLE_ID");
        HashSet<String> transactionTypeCodes = new HashSet<>();
        transactionTypeCodes.add("2");
        transactionTypeCodes.add("9");
        transactionTypeCodes.add("26F");
        HashSet<String> marginShareFlags = new HashSet<>();
        marginShareFlags.add("C");
        marginShareFlags.add("S");
        HashSet<String> contentClasses = new HashSet<>();
        contentClasses.add("GAME");
        HashSet<String> sapGLAccountCodes = new HashSet<>();
        sapGLAccountCodes.add("161100");
        sapGLAccountCodes.add("400000");
        sapGLAccountCodes.add("235110");

        HashSet<Integer> skuTypes = new HashSet<>();
        skuTypes.add(1);
        skuTypes.add(2);

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(
                        new IntegerSequenceDatum("SALE_ID",1,1),
                        new IntegerSequenceDatum("TRANSACTION_ID",1,1),
                        new ConstantIntegerDatum("INPUT_FILE_ID", inputFileId),
                        new StringSetDatum("TRANSACTION_STATUS_CODE", transactionStatusCodes),
                        ///new ConstantDateDatum("TRANSACTION_DATE", new Date()),
                        new StringSetDatum("COUNTRY_CODE", countryIsoCodes),
                        new StringSetDatum("CURRENCY_CODE", currencyIsoCodes),
                        new ConstantStringDatum("FUNDING_TYPE", "FUNDING_TYPE"),
                        new ConstantStringDatum("FUNDING_PROVIDER", "FUNDING_PROVIDER"),
                        new ConstantStringDatum("FUNDING_INSTRUMENT", "FUNDING_INSTRUMENT"),
                        new ConstantStringDatum("MANUFACTURER_ID", "MANUFACTURER_ID"),
                        new StringSetDatum("SKU", skus),
                        new IntegerRangeDatum("QUANTITY", 1, 10),
                        new DecimalRangeDatum("TOTAL_AMOUNT", 0, 20, 2),
                        new StringSetDatum("CONTEXT_TITLE_ID", titleIds),
                        new IntegerRangeDatum("AMORTIZEABLE",0,1),
                        new IntegerSetDatum("SKU_TYPE", skuTypes),
                       // new ConstantDateDatum("START_DATE", new Date()),
                        new IntegerRangeDatum("DURATION",1,1000),
                        new IntegerRangeDatum("ORDER_ITEM_ID",1,100000),
                        new IntegerRangeDatum("PSN_SKU_PRODUCT_ID",100000,1000000),
                        new StringSetDatum("TRANSACTION_TYPE_CODE", transactionTypeCodes),
                        new DecimalRangeDatum("CALC_FR_FEE_AMOUNT",0,5,2),
                        new DecimalRangeDatum("CALC_NED_FEE_AMOUNT",0,5,2),
                        new DecimalRangeDatum("CALC_WSP_AMOUNT",0,5,2),
                        new IntegerRangeDatum("PROFIT_CENTRE",1,10000),
                        new DecimalRangeDatum("ORIGINAL_PRICE",0,10,2),
                        new DecimalRangeDatum("TAX_AMOUNT",0,5,2),
                        new DecimalRangeDatum("TOTAL_AMOUNT_EXCL_TAX",0,5,2),
                        new IntegerSequenceDatum("ORIG_TRANSACTION_ID", 1, 1),
                        new IntegerRangeDatum("SUBSCRIPTION_FLAG", 0,1),
                        new StringSetDatum("SP_SAP_GL_ACCOUNT_CODE", sapGLAccountCodes),
                        new DecimalRangeDatum("CALC_TOTAL_AMOUNT_EXCL_TAX_EUR",0,5,2),
                        new DecimalRangeDatum("CALC_TP_AMOUNT",0,5,2),
                        new StringSetDatum("CALC_MARGIN_SHARE_FLAG",marginShareFlags),
                        new StringSetDatum("CONTENT_CLASS",contentClasses))
                .numberOfRows(10)
                .build();

        new ConsoleWriter().write(new CSVFormatter(tabularDataStructure));


    }

    public DataGenerator() {
        /*
        Reader reader = new FileReader("config_filename.xml");
        DataDescription dataDescription = loadConfig(reader);
        dataDescription.generateData();

         */
    }

    public DataDescription loadConfig(Reader reader) throws JAXBException, SAXException {

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(this.getClass().getResource("/datagenerator.xsd"));

        JAXBContext jaxbContext = JAXBContext.newInstance(DataDescription.class, CSVConfig.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);
       /* unmarshaller.setEventHandler(new ValidationEventHandler() {
            @Override
            public boolean handleEvent(ValidationEvent event) {
                System.out.println("\nEVENT");
                System.out.println("SEVERITY:  " + event.getSeverity());
                System.out.println("MESSAGE:  " + event.getMessage());
                System.out.println("LINKED EXCEPTION:  " + event.getLinkedException());
                System.out.println("LOCATOR");
                System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
                System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
                System.out.println("    OFFSET:  " + event.getLocator().getOffset());
                System.out.println("    OBJECT:  " + event.getLocator().getObject());
                System.out.println("    NODE:  " + event.getLocator().getNode());
                System.out.println("    URL:  " + event.getLocator().getURL());
                return true;
            }
        });
         */
        return (DataDescription) unmarshaller.unmarshal(reader);
    }

}
