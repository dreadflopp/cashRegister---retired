/**
 * Author: Mattias Lindell
 * Last edit: 18-03-12
 * Desc: File handler saves to and loads from xml
 */
package cashregister;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileHandler {
	
	// Methods	
	public static void saveToXML(History history, String fileName){
		// Check if fileName ends with '.xml'.
		// If fileName doesn't end with '.xml', append '.xml'.
		if (!fileName.endsWith(".xml") && !fileName.isEmpty()) {
			fileName += ".xml";
		}
		
		// Create JAXB marshaller for saving drawing to XML
		try {
		JAXBContext context = JAXBContext.newInstance(History.class);
		Marshaller marshaller = context.createMarshaller();
		
		// Set marshaller to indent
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		// Save to file
		marshaller.marshal(history, new File(fileName));
		
		} catch (JAXBException e){
			System.err.println(e + "/nFile not saved!");
		} catch (NullPointerException e) {
			System.err.println("No file name has been set. File not saved!");
		}
	}
	
	public static History loadFromXML(String fileName) throws FileNotLoadedException {
		
		// Create JAXB unmarshaller for for loading XML to drawing
		// JAXBContext throws JAXBException
		// Unmarshaller throws JAXBException
		try {
		JAXBContext context = JAXBContext.newInstance(History.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		// Read from file
		History history = (History) unmarshaller.unmarshal(new File(fileName));		
		
		return history;
		} catch (JAXBException e) {
			System.err.println(e + "/nFile not loaded!");
                        throw new FileNotLoadedException();
		} catch (IllegalArgumentException e) {
			System.err.println("File not found. Check that a file with the name '" + fileName + "' exists!");
                        throw new FileNotLoadedException();
		}	
	}
        
        public static boolean fileExist(String filename) {
            // If fileName doesn't end with '.xml', append '.xml'.
		if (!filename.endsWith(".xml")) {
			filename += ".xml";
		}
                
                File file = new File(filename);
                if (file.exists() || file.isDirectory()) {
                    return true;
                }
                return false;
        }
}
