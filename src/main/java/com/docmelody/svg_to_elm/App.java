package com.docmelody.svg_to_elm;

import org.xml.sax.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStreamReader;
import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser sp = spf.newSAXParser();
            XMLReader xmlReader = sp.getXMLReader();
            xmlReader.setContentHandler(new SvgToElm());
            xmlReader.parse(new InputSource(new InputStreamReader(System.in)));
        } catch(SAXException e) {
            System.err.println("Error parsing stdin: " + e.getMessage());
        } catch(ParserConfigurationException e) {
            System.err.println("Error configuring SAX parser: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading stdin: " + e.getMessage());
        }
    }
}
