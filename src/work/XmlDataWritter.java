package work;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import co.kr.sumin.array.support.basic.InnerAction;

public class XmlDataWritter {
	public static void main(String[] args) throws Exception{
		File file = new File("c:/test/xml/test.xml");
		Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		TestVO vo = new TestVO();
		ArrayList<String> from = new ArrayList();
		from.add("1번");
		from.add("2번");
		from.add("3번");
		from.add("4번");
		vo.setFrom(from);
		vo.setTo("테스트 To 입력");
		XmlDataWritter.writter(xml, vo);
		XmlParser.save(xml,"c:/test/xml/test2.xml");

	}
	
	
	public static void writter(Document xml, TestVO vo) throws Exception{
		Field[] fields = vo.getClass().getDeclaredFields();
		for(int i=0; i<fields.length; i++){
			fields[i].setAccessible(true);
			LicenseMapper mapper = fields[i].getDeclaredAnnotation(LicenseMapper.class);
			if(mapper != null) {
				String[] location = mapper.tagLocation().split("/");
				String[] parents = new String[location.length-1];
				String target = location[location.length-1];
				for(int j=0; j<location.length-1; j++) {
					parents[j] = location[j];
				}
				Element element = XmlParser.getElement(xml, parents, target);
				if(fields[i].get(vo) instanceof Collection){
					foreach(xml,element,fields[i],(Collection)fields[i].get(vo));
				} else {
					String data = fields[i].get(vo).toString();
					XmlParser.setValue(xml, element, data);
				}
			}
		}
	}
	public static void foreach(Document xml,Element element,Field field,Collection list){
		LicenseMapper mapper = field.getDeclaredAnnotation(LicenseMapper.class);
		Iterator<String> iter = list.iterator();
		int count = mapper.startNum();
		int end = mapper.endNum();
		Element parent = (Element)element.getParentNode();
		Element after = getAfterChildElement(parent, element);
		System.out.println("after : "+after.getNodeName());
		while(iter.hasNext() && end >= (count)){
			String data = iter.next();
			if( !(mapper.startNum() == count) ){
				Element add = (Element)parent.insertBefore(element.cloneNode(true), after);
				XmlParser.setValue(xml, add, data);
				System.out.println(parent.getNodeName());
				System.out.println(add.getFirstChild().getNodeValue());
			} else {
				XmlParser.setValue(xml, element, data);
				System.out.println(count);
			}
			count++;
		}
	}
	public static Element getAfterChildElement(Element parent,Element target){
		NodeList list = parent.getChildNodes();
		for(int i=0; i<list.getLength(); i++) {
			System.out.println("type : " + list.item(i).getNodeType());
			if(list.item(i).isEqualNode(target)){
				for(int j=i+1; j<list.getLength(); j++) {
					if(list.item(j).getNodeType() == Node.ELEMENT_NODE)
						return (Element)list.item(j);
				}
			}
		}
		return null;
	}
}

