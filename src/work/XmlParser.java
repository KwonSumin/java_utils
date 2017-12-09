package work;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XmlParser {
	
	

	public static void main(String[] args)  throws Exception{
		File file = new File("c:/test/xml/test.xml");
		Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		String[] parents = {"note","test1"};
		System.out.println(XmlParser.setValue(xml, XmlParser.getElement(xml, parents, "inner"), "변경했습니다123"));
		XmlParser.save(xml,"c:/test/xml/test.xml");
	}
	public static Element setValue(Document xml,Element element,String value) {
		element.getFirstChild().setNodeValue(value);
		return element;
	}
	public String getValue(Element element){
		return element.getChildNodes().item(0).getNodeValue();
	}
	public static Element getElement(Document xml,String[] parents,String target)throws Exception{
		Element result = null;
		
		NodeList nodes = xml.getElementsByTagName(target);
		for(int i=0; i<nodes.getLength(); i++) {
			Element element = (Element)nodes.item(i);
			if(hasParents(parents,element)){
				result = element;
			}
		}
		return result;
	}
	
	
	public static Element getElement(Document xml,String[] parents,String target,String attrName,String attrValue)throws Exception{
		Element result = null;
		try{
			NodeList nodes = xml.getElementsByTagName(target);
			for(int i=0; i<nodes.getLength(); i++) {
				Element element = (Element)nodes.item(i);
				if(element.getAttribute(attrName).equals(attrValue)) {
					if(hasParents(parents,element)){
						result = element;
					}
				}
			}
		}catch(Exception e){e.printStackTrace();;throw e;}
		return result;
	}
	
	
	public static boolean hasParents(String[] parents,Element elements) throws Exception{
		for(int i=parents.length-1; i>=0; i--) {
			Element parent = (Element) elements.getParentNode();
			if(parent.getNodeName().equals(parents[i])){}else{
				return false;
			}
			elements = parent;
		}
		return true;
	}
	public static void save(Document xml,String savePath) throws TransformerException{
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(xml);
		StreamResult result = new StreamResult(new File(savePath));
		transformer.transform(source, result);

		System.out.println("File saved!");
    }
}

