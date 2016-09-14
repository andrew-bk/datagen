# datagen

Work in progress. 

Simple utility for generating random data that conforms to a given data specification.

Example configuration for generating CSV file.  The 'name' attributes will be used as column names in the resulting file

    <?xml version="1.0"?>
    
    <data xmlns="http://datagen.ak.org"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://datagen.ak.org datagenerator.xsd">
    
        <!--
        If 'file' is not specified, the data is output to the console.
        If 'delimiter' is not specified then it defaults to comma
        -->
        <csv file="C:\output.csv" delimiter="\t">
        
            <integer  name="A Constant Integer" constant="1"/>
            <integer  name="An integer in the given range" from="0" to="10"/>
            <integer  name="An integer from a set of integers">1,5,3,8,23</integer>
            <sequence name="Integers in a sequence" start="0" step="10" />
            <date name="Just the given date" constant="24/06/2014" displayFormat="yyyyMMdd"/>
            <text name="Just the given string" constant="CONSTANT"/>
            <randomText name="Garbage text of a given size" minLength="1" maxLength="10"/>
            <text name="One String from a set os Strings">ONE,TWO,THREE</text>
        
        </csv>
    
    </data>
