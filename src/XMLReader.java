
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

	public static Map<String, VideoCategory> loadCategoriesXml(String filePath) {
		Map<String, VideoCategory> categoryList = null;
		try {
			File file = new File(filePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			// System.out.println("Root element " +
			// doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("Category");
			// System.out.println("Information of all employees");
			categoryList = new HashMap<String, VideoCategory>();

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					String cateName = ((Node) fstNm.item(0)).getNodeValue();
					// System.out.println("Category Name : " + ((Node)
					// fstNm.item(0)).getNodeValue());
					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("price");
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					float rentPrice = Float.parseFloat(((Node) lstNm.item(0)).getNodeValue());
					// System.out.println("Rent Price : " + ((Node)
					// lstNm.item(0)).getNodeValue());
					VideoCategory tempCategory = new VideoCategory(cateName, rentPrice);
					categoryList.put(cateName, tempCategory);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoryList;
	}

	public static List<Video> loadVideosXml(String filePath) {
		List<Video> videoList = null;
		try {
			File file = new File(filePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			// System.out.println("Root element " +
			// doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("Video");
			// System.out.println("Information of all employees");
			videoList = new ArrayList<Video>(nodeLst.getLength());

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					String videoName = ((Node) fstNm.item(0)).getNodeValue();
					// System.out.println("Category Name : " + ((Node)
					// fstNm.item(0)).getNodeValue());
					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("category");
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					String categoryName = ((Node) lstNm.item(0)).getNodeValue();
					// System.out.println("Rent Price : " + ((Node)
					// lstNm.item(0)).getNodeValue());
					Video tempVideo = new Video(videoName, categoryName);
					videoList.add(tempVideo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videoList;
	}

	public static List<Customer> loadCustomersXml(String filePath) {
		List<Customer> custList = null;
		try {
			File file = new File(filePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			// System.out.println("Root element " +
			// doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("customer");
			// System.out.println("Information of all employees");
			custList = new ArrayList<Customer>(nodeLst.getLength());

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);
				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					String custName = ((Node) fstNm.item(0)).getNodeValue();
					// System.out.println("Category Name : " + ((Node)
					// fstNm.item(0)).getNodeValue());
					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("type");
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					String custType = ((Node) lstNm.item(0)).getNodeValue();
					// System.out.println("Rent Price : " + ((Node)
					// lstNm.item(0)).getNodeValue());
					Customer cust = new Customer(custName, custType);
					custList.add(cust);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custList;
	}
}
