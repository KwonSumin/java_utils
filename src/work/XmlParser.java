package work;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import co.kr.sumin.array.support.basic.InnerAction;

public class XmlParser {
	public static void main(String[] args)  throws Exception{
		File file = new File("c:/test/xml/test.xml");
		Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		NodeList nodes = xml.getElementsByTagName("test");
		Element element = (Element)nodes.item(0);
		Element clone = (Element)element.cloneNode(true);
		NodeList childs = element.getChildNodes();
		
		for(int i=0; i<childs.getLength(); i++) {
			Node item = childs.item(i);
			if( item.getNodeType() != Node.TEXT_NODE )
				element.removeChild(item);
		}
		
		List<Node> list = getElement(clone, new Filter<Node>() {
			
			@Override
			public boolean execute(Node node) throws Exception {
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element item = (Element) node;
					if(item.getNodeName().equals("name")) return true;
					System.out.println(node.getNodeName());
				}
				return false;
			}
		});
		
		XmlParser.setValue(xml,(Element) list.get(0), "1");
		appendNodeList(clone.getChildNodes(), element);

		XmlParser.setValue(xml,(Element) list.get(0), "2");
		appendNodeList(clone.getChildNodes(), element);

		XmlParser.setValue(xml,(Element) list.get(0), "3");
		appendNodeList(clone.getChildNodes(), element);
		XmlParser.save(xml,"c:/test/xml/result.xml");
	}
	
	
	
	public static List<Node> getElement(Element target,Filter filter) throws Exception{
		List<Node> list = new ArrayList();
		NodeList nodes = target.getChildNodes();
		for(int i=0; i<nodes.getLength(); i++) {
			if( filter.execute(nodes.item(i)) ) {
				Node item = nodes.item(i);
				list.add(item);
			}
		}
		return list;
	}
	
	
	
	public static void appendNodeList(NodeList list,Element target){
		int length = list.getLength();
		for(int i=0; i<length; i++) {
			Node item = list.item(i).cloneNode(true);
			target.appendChild(item);
		}
	}
	
	public static void appendNodeList(NodeList list,Element target,ForEach foreach)throws Exception{
		int length = list.getLength();
		for(int i=0; i<length; i++) {
			Node item = list.item(i).cloneNode(true);
			target.appendChild(item);
			foreach.execute(i, item);
		}
	}
	
	public static ArrayList<Element> getElementBySelector(Document xml,String selector){
		ArrayList<Element> result = new ArrayList<Element>();
		String[] select = selector.split(" ");
		String[] parent = new String[select.length-1];
		for(int i=0; i<parent.length; i++) parent[i] = select[i];
		String target = select[select.length-1];
		
		NodeList nodes = xml.getElementsByTagName(target);
		boolean findParentFlag = false;
		/*
		for(int i=0; i<nodes.getLength(); i++) {
			Node item = nodes.item(i);
			
			for(int j=0; j<parent.length; j++) {
				
				if( !hasParentElement((Element)item, parent[j]) ){
					break;
				}
				if(parent.length-1 == j)
					result.add((Element)item);
			}
			
		}
		*/
		for(int i=0; i< nodes.getLength();i++) {
			if ( hasParentElement((Element)nodes.item(i), parent)) result.add((Element)nodes.item(i));
		}
		
		
		return result;	
	}
	
	
	public static boolean hasParentElement(Element target,String parent){
		
		Node parentNode = target.getParentNode();
		while(parentNode != null){
			if(parentNode.getNodeName().equals(parent)) return true;
			parentNode = parentNode.getParentNode();
		}
		
		return false;
	}
	public static boolean hasParentElement(Element target,String[] parent){
		
		for(int i=0; i<parent.length; i++) {
			if(! hasParentElement(target, parent[i]) ) return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 	@description :
	 * 		해당 엘리먼트 삭제.
	 * 		해당 엘리먼트 삭제를 위하여 엘리먼트 자식Node를
	 * 		loop돌려서 부모 객체의 removeChild()로 삭제.
	 */
	public static void removeElement(Element t) {
		Node parent = t.getParentNode();
		NodeList list=  parent.getChildNodes();
		for(int i=0; i<list.getLength();i++) {
			if( list.item(i).getNodeName().equals(t.getNodeName()) ){
				parent.removeChild(list.item(i));
			}
		}
	}
	
	public static Element setValue(Document xml,Element element,String value) {
		element.getFirstChild().setNodeValue(value);
		return element;
	}
	/*
	 * 	@description : 
	 * 		엘리먼트 자식중 nodeType에 해당하는 Node반환
	 * 		Node.ELEMENT_TYPE 등 참조.
	 */
	private static Node getNode(Element element,short nodeType){
		NodeList list = element.getChildNodes();
		for(int i=0; i<list.getLength(); i++) {
			if(list.item(i).getNodeType() == nodeType)return list.item(i);
		}
		return null;
	}
	public static String getValue(Element element){
		return getNode(element, Node.TEXT_NODE).getNodeValue();
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

interface Filter<T>{
	boolean execute(T target)throws Exception;
}
interface ForEach{
	void execute(int idx,Object obj)throws Exception;
}
