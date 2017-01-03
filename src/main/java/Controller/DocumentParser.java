package Controller;

import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by simon on 2017-01-03.
 */
public class DocumentParser {

   private XPath xPath;
   private int totalHits, totalPages, page, size;
   private Document doc;
   private RestTemplate restTemplate;

   public void setupRestTemplate(){
       restTemplate = new RestTemplate();
       XPathFactory xPathFactory = XPathFactory.newInstance();
       xPath = xPathFactory.newXPath();
   }

    public void setupDocument(int i, String uri){

        try {
            doc = convertStringToDoc(restTemplate.getForObject(uri,
                    String.class, i));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    private static Document convertStringToDoc(String xmlStr)
            throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        documentBuilder = dbfactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new InputSource(
                new StringReader(xmlStr)));
        return document;

    }

    public Document getDoc() {
        return doc;
    }

    public void getHeader() {

        if (doc != null) {
            try {
                page = Integer.parseInt(xPath.evaluate(
                        "/sr/pagination/page", doc));
                size = Integer.parseInt(xPath.evaluate(
                        "/sr/pagination/size", doc));
                totalHits = Integer.parseInt(xPath.evaluate(
                        "/sr/pagination/totalhits", doc));
                totalPages = Integer.parseInt(xPath.evaluate(
                        "/sr/pagination/totalpages", doc));
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        }
    }

    public XPath getxPath() {
        return xPath;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
