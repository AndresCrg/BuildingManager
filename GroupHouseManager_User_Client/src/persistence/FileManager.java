package persistence;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import models.Apartment;
import models.Bill;
import models.House;
import models.IOwnership;
import models.MyNode;
import models.NTree;
import models.TypeBill;
import models.User;

public class FileManager {

	private static final String LVL_ONE_TXT = "lvlOne";
	private static final String ATTRIBUTE_ID = "id";
	private static final String ATTRIBUTE_TYPE_OWNERSHIP = "type";
	private static final String HOUSE = "House";
	private static final String APARTMENT = "Apartment";
	private static final String WATER_TXT = "Water";
	private static final String LIGHT_TXT = "Light";
	private static final String GAS_TXT = "Gas";
	private static final String INTERNET_TXT = "Internet";
	private static final String ATTRIBUTE_CAN_BE_PURCHASED = "isAvalible";

	public static NTree readXmlNTreeClient(String xmlFile) throws SAXException, IOException, ParserConfigurationException,
	TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(xmlFile)));
		System.out.println(xmlFile);
		document.getDocumentElement().normalize();
		Element rootElement = document.getDocumentElement();
		int id = Integer.valueOf(rootElement.getAttribute(ATTRIBUTE_ID));
		String type = rootElement.getAttribute(ATTRIBUTE_TYPE_OWNERSHIP);

		NTree nTree = new NTree(new MyNode(id, new User(type)));
		NodeList nodeList = document.getElementsByTagName(LVL_ONE_TXT);
		readAllNodesNTreeClient(nTree, nodeList);
		return nTree;
	}

	private static void readAllNodesNTreeClient(NTree nTree, NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element ownershipElement = (Element) nodo;
				int id = Integer.valueOf(ownershipElement.getAttribute(ATTRIBUTE_ID));
				String type = ownershipElement.getAttribute(ATTRIBUTE_TYPE_OWNERSHIP);
				boolean ownershipAvalible = Boolean.parseBoolean(ownershipElement.getAttribute(ATTRIBUTE_CAN_BE_PURCHASED));
				if (type.equals(APARTMENT) || type.equals(HOUSE)) {
					nTree.add(Integer.valueOf(ownershipElement.getParentNode().getAttributes()
							.getNamedItem(ATTRIBUTE_ID).getNodeValue()),
							new MyNode(id, getTypeOwnershipPrivileges(type, ownershipAvalible)));
				}else {
					nTree.add(Integer.valueOf(ownershipElement.getParentNode().getAttributes()
							.getNamedItem(ATTRIBUTE_ID).getNodeValue()),
							new MyNode(id, new User(type)));
				}
			}
			if (nodo.hasChildNodes()) {
				readAllNodesNTreeClient(nTree, nodo.getChildNodes());
			}
		}
	}

	private static IOwnership getTypeOwnershipPrivileges(String type, boolean isAvalible) {
		IOwnership ownership = null;
		switch (type) {
		case APARTMENT:
			ownership = new Apartment();
			ownership.setBePurchased(isAvalible);
			break;
		case HOUSE:
			ownership = new House();
			ownership.setBePurchased(isAvalible);
			break;
		case GAS_TXT:
			ownership = new Bill(TypeBill.valueOf(type.toUpperCase()));
			break;
		case INTERNET_TXT:
			ownership = new Bill(TypeBill.valueOf(type.toUpperCase()));
			break;
		case LIGHT_TXT:
			ownership = new Bill(TypeBill.valueOf(type.toUpperCase()));
			break;
		case WATER_TXT:
			ownership = new Bill(TypeBill.valueOf(type.toUpperCase()));
			break;
		default:
			break;
		}
		return ownership;
	}
}