package ru.avl.simpleweb.sax;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFileSAX {
    private static final Logger logger = Log.getLogger(ReadXMLFileSAX.class);

    public static Object readXML(String xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            parser.parse(xmlFile, handler);

            return handler.getObject();
        } catch (Exception e) {
            logger.warn(e);
        }
        return null;
    }
}
