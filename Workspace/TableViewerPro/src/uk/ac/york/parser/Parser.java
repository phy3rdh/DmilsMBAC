package uk.ac.york.parser;

import uk.ac.york.viewer.TableEntry;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Parser extends DefaultHandler {
	
	private ArrayList<TableEntry> entryList;
	private SAXParserFactory parserFactory;
	private SAXParser parser;
 
      public Parser()
      {
    	  try
    	  {
    		  entryList = new ArrayList<TableEntry>();
    		  parserFactory = SAXParserFactory.newInstance();
    		  parser = parserFactory.newSAXParser();
    	  }
    	  catch(SAXException ex)
    	  {}
    	  catch(ParserConfigurationException ex2)
    	  {}
      }
      
      public void parse(String fileName)
      {
    	  try
    	  {
    		  parser.parse(fileName, this);
    	  }
    	  catch(SAXException ex)
    	  {
    		  
    	  }
    	  catch(IOException ex2)
    	  {
    		  
    	  }
      }

      public ArrayList getElements()
      {
    	  return entryList;
      }
     
      public void startElement(String uri, String localName,
             String qName, Attributes attributes) throws SAXException {
             if (qName.equalsIgnoreCase("entry")) {
                    entryList.add(new TableEntry(attributes.getValue("id"),attributes.getValue("role"),attributes.getValue("object")));
             }
      }

      public void endElement(String uri, String localName, String qName)
                    throws SAXException {

      }
}