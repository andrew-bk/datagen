package org.ak;

import org.ak.datagen.data.*;
import org.ak.datagen.format.CSVFormatter;
import org.ak.datagen.output.ConsoleWriter;
import org.ak.datagen.structure.TabularDataStructure;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

/**
 * TODO
 *
 * - formatting of output (e.g. date formats)
 * - xml schema
 * - jaxb binding
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

        HashSet<String> transactionStatusCodes = new HashSet<>();
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

        TabularDataStructure tabularDataStructure = new TabularDataStructure.TabularDataStructureBuilder()
                .withRow(
                        new IntegerSequenceDatum("SALE_ID",1,1),
                        new IntegerSequenceDatum("TRANSACTION_ID",1,1),
                        new ConstantIntegerDatum("INPUT_FILE_ID", inputFileId),
                        new StringSetDatum("TRANSACTION_STATUS_CODE", transactionStatusCodes),
                        new ConstantDateDatum("TRANSACTION_DATE", new Date()),
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
                       // "SKU_TYPE"        No        NUMBER(10,0)            Needs IntegerSetDatum
                        new ConstantDateDatum("START_DATE", new Date()),
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
}
