package CalorieCounter.Modell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Class for reading the player scores and saved games from an xml file.
 */
public class Reader {
    /**
     * The logger of this class.
     */
    private static Logger logger = LoggerFactory.getLogger(Reader.class);
    /**
     * The {@code DocumentBuilder} of this class.
     * @see DocumentBuilder
     */
    private DocumentBuilder db;

    /**
     * Returns the password of the database connection.
     * @return password of the database connection
     */
    public String getPassword(){
        Document doc = null;
        try {
            doc = db.parse(getClass().getResourceAsStream("/META-INF/pwd.xml"));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            NodeList n = doc.getElementsByTagName("pwd");
            return n.item(0).getTextContent();
        }
        return null;
    }
    /**
     * Constructs a {@code Reader} object.
     */
    public Reader(){
        try {
            this.db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Error in creating the DocumentBuilder!", e);
        }
    }

}