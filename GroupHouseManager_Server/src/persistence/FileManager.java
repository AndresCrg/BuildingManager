package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import models.Apartment;
import models.Bill;
import models.Building;
import models.CoOwnership;
import models.House;
import models.IOwnership;
import models.MyNode;
import models.NTree;
import models.Privileges;
import models.ResidentialCommunity;
import models.TypeBill;
import models.TypeCoOwnership;
import models.User;

public class FileManager {

	private static final String LVL_ONE_TXT = "lvlOne";
	private static final String USER_HAS_BEE_CREATED_SUCCESFULL = "Usuario registrado con exito!";
	private static final String ID_TAG = "id";
	private static final String ROOT_TAG = "database";
	private static final String USER_TAG = "user";
	private static final String ATTRIBUTE_ID = "id";
	private static final String ROOT_TAG_TREE = "root";
	private static final String ATTRIBUTE_TYPE_OWNERSHIP = "type";
	private static final String XML_FILE_CREATED_MESSAGE = "Xml created";
	private static final String RESIDENTIAL_COMMUNITY = "Residential Community";
	private static final String HOUSE = "House";
	private static final String BUILDING = "Building";
	private static final String APARTMENT = "Apartment";
	private static final String BBQ_ZONE = "BBQ";
	private static final String COMMUNITY_HALL = "Hall";
	private static final String GYM = "Gym";
	private static final String PARK = "Park";
	private static final String SWIMMING_POOL = "Swimming";
	private static final String WATER_TXT = "Water";
	private static final String LIGHT_TXT = "Light";
	private static final String GAS_TXT = "Gas";
	private static final String INTERNET_TXT = "Internet";
	private static final String PRIVILEGES_TXT = "Privileges";
	private static final String LVL_FOUR_TXT = "lvlFour";
	private static final String DB_ID_PATH = "./data/FolderId/idNode.xml";
	private static final String ATTRIBUTE_CAN_BE_PURCHASED = "isAvalible";
	private static final String DATA_USERS_PATH = "./data/FolderUsers/users.xml";

	public static void writeIdFile(long id) throws ParserConfigurationException, TransformerFactoryConfigurationError,
	TransformerException {
		Document document = DocumentBuilderFactory.newInstance().
				newDocumentBuilder().newDocument();

		Element rootTag = document.createElement(ROOT_TAG);
		document.appendChild(rootTag);

		Element idTag = document.createElement(ID_TAG);
		idTag.setTextContent(String.valueOf(id));
		rootTag.appendChild(idTag);

		Source source = new DOMSource(document);
		Result result = new StreamResult(new File(DB_ID_PATH));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result);
	}

	public static long readIdFile() throws SAXException, IOException, ParserConfigurationException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(DB_ID_PATH));
		document.getDocumentElement().normalize();
		Element idTag = (Element) document.getElementsByTagName(ID_TAG).item(0);
		return Long.valueOf(idTag.getTextContent());
	}

	public static void writeXMLUserClient(String userName) throws ParserConfigurationException,
	TransformerFactoryConfigurationError, TransformerException, SAXException, IOException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
	            .parse(new File(DATA_USERS_PATH));
		doc.getDocumentElement().normalize();
		Element rootTag = doc.getDocumentElement();
		if (!isRegistered(userName, DATA_USERS_PATH)) {
			Element userNameTag = doc.createElement(USER_TAG);
			userNameTag.setTextContent(userName);
			rootTag.appendChild(userNameTag);
			Logger.getGlobal().info(USER_HAS_BEE_CREATED_SUCCESFULL);
		}
		Source source = new DOMSource(doc);
		Result result = new StreamResult(new File(DATA_USERS_PATH));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
	}

	public static ArrayList<String> readXMLUsers() throws SAXException, IOException, ParserConfigurationException {
		ArrayList<String> clients = new ArrayList<String>();
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(DATA_USERS_PATH));
		doc.getDocumentElement().normalize();
		NodeList userList = doc.getElementsByTagName(USER_TAG);
		for (int i = 0; i < userList.getLength(); i++) {
			Node nodo = userList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element userElement = (Element) nodo;
				String name = userElement.getTextContent();
				clients.add(name);
			}
		}
		return clients;
	}

	public static boolean isRegistered(String userName, String fileName) throws SAXException, IOException, ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(fileName));
		doc.getDocumentElement().normalize();
		NodeList userList = doc.getElementsByTagName(USER_TAG);
		for (int i = 0; i < userList.getLength(); i++) {
			Node nodo = userList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element userElement = (Element) nodo;
				String name = userElement.getTextContent();
				if (name.equals(userName)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void writeXMLNTreeOwnership(NTree nTree, String pathFile) throws ParserConfigurationException, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().
				newDocumentBuilder().newDocument();
		Element rootTag = document.createElement(ROOT_TAG_TREE);
		rootTag.setAttribute(ATTRIBUTE_ID, String.valueOf(nTree.getRoot().getId()));
		rootTag.setAttribute(ATTRIBUTE_TYPE_OWNERSHIP, nTree.getRoot().getData().getTypeOwnership());
		document.appendChild(rootTag);

		createNodeOwnership(nTree.getRoot(), rootTag, document);

		Source source = new DOMSource(document);
		Result result = new StreamResult(new File(pathFile));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
		Logger.getGlobal().info(XML_FILE_CREATED_MESSAGE);
	}

	private static void createNodeOwnership(MyNode base, Element elementBase, Document document) {
		for (MyNode  node : base.getChildrenList()) {
			Element elementNode = document.createElement(node.getData().getLevelName());
			elementNode.setAttribute(ATTRIBUTE_ID, String.valueOf(node.getId()));
			elementNode.setAttribute(ATTRIBUTE_TYPE_OWNERSHIP, node.getData().getTypeOwnership());
			elementNode.setAttribute(ATTRIBUTE_CAN_BE_PURCHASED, String.valueOf(node.getData().canBePurchased()));
			elementBase.appendChild(elementNode);
			createNodeOwnership(node, elementNode, document);
		}
	}

	public static NTree readXmlNTreeOwnership(String xmlFile) throws SAXException, IOException, ParserConfigurationException,
	TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
		document.getDocumentElement().normalize();

		Element rootElement = document.getDocumentElement();
		int id = Integer.valueOf(rootElement.getAttribute(ATTRIBUTE_ID));
		String type = rootElement.getAttribute(ATTRIBUTE_TYPE_OWNERSHIP);

		NTree nTree = new NTree(new MyNode(id, getTypeOwnership(type, false)));
		NodeList nodeList = document.getElementsByTagName(LVL_ONE_TXT);
		readAllNodesNTreeOwnership(nTree, nodeList);
		return nTree;
	}

	private static void readAllNodesNTreeOwnership(NTree nTree, NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nodo = nodeList.item(i);
			if (nodo.getNodeType() == Node.ELEMENT_NODE) {
				Element ownershipElement = (Element) nodo;
				int id = Integer.valueOf(ownershipElement.getAttribute(ATTRIBUTE_ID));
				String type = ownershipElement.getAttribute(ATTRIBUTE_TYPE_OWNERSHIP);
				boolean canBePurchase = Boolean.parseBoolean(ownershipElement.getAttribute(ATTRIBUTE_CAN_BE_PURCHASED));
				nTree.add(Integer.valueOf(ownershipElement.getParentNode().getAttributes()
						.getNamedItem(ATTRIBUTE_ID).getNodeValue()),
						new MyNode(id, getTypeOwnership(type, canBePurchase)));
			}
			if (nodo.hasChildNodes()) {
				readAllNodesNTreeOwnership(nTree, nodo.getChildNodes());
			}
		}
	}

	private static IOwnership getTypeOwnership(String type, boolean canBePurchase) {
		IOwnership ownership = null;
		switch (type) {
		case RESIDENTIAL_COMMUNITY:
			ownership = new ResidentialCommunity();
			break;
		case HOUSE:
			ownership = new House();
			ownership.setBePurchased(canBePurchase);
			break;
		case BUILDING:
			ownership = new Building();
			break;
		case APARTMENT:
			ownership = new Apartment();
			ownership.setBePurchased(canBePurchase);
			break;
		case BBQ_ZONE:
			ownership = new CoOwnership(TypeCoOwnership.valueOf(type.toUpperCase()));
			break;
		case COMMUNITY_HALL:
			ownership = new CoOwnership(TypeCoOwnership.valueOf(type.toUpperCase()));
			break;
		case GYM:
			ownership = new CoOwnership(TypeCoOwnership.valueOf(type.toUpperCase()));
			break;
		case PARK:
			ownership = new CoOwnership(TypeCoOwnership.valueOf(type.toUpperCase()));
			break;
		case SWIMMING_POOL:
			ownership = new CoOwnership(TypeCoOwnership.valueOf(type.toUpperCase()));
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
		case PRIVILEGES_TXT:
			ownership = new Privileges();
			break;
		default:
			break;
		}
		return ownership;
	}

	public static void writeXMLNTreePrivileges(NTree nTree, String pathFile) throws ParserConfigurationException, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().
				newDocumentBuilder().newDocument();
		Element rootTag = document.createElement(ROOT_TAG_TREE);
		rootTag.setAttribute(ATTRIBUTE_ID, String.valueOf(nTree.getRoot().getId()));
		rootTag.setAttribute(ATTRIBUTE_TYPE_OWNERSHIP, nTree.getRoot().getData().getTypeOwnership());
		document.appendChild(rootTag);

		createNodePrivileges(nTree.getRoot(), rootTag, document);

		Source source = new DOMSource(document);
		Result result = new StreamResult(new File(pathFile));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
		Logger.getGlobal().info(XML_FILE_CREATED_MESSAGE);
	}

	private static void createNodePrivileges(MyNode base, Element elementBase, Document document) {
		for (MyNode  node : base.getChildrenList()) {
			Element elementNode = document.createElement(node.getData().getLevelName());
			elementNode.setAttribute(ATTRIBUTE_ID, String.valueOf(node.getId()));
			elementNode.setAttribute(ATTRIBUTE_TYPE_OWNERSHIP, node.getData().getTypeOwnership());
			elementNode.setAttribute(ATTRIBUTE_CAN_BE_PURCHASED, String.valueOf(node.getData().canBePurchased()));
			elementBase.appendChild(elementNode);
			createNodePrivileges(node, elementNode, document);
		}
	}

	public static NTree readXmlNTreePrivileges(String xmlFile) throws SAXException, IOException, ParserConfigurationException,
	TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
		document.getDocumentElement().normalize();

		Element rootElement = document.getDocumentElement();
		int id = Integer.valueOf(rootElement.getAttribute(ATTRIBUTE_ID));
		String type = rootElement.getAttribute(ATTRIBUTE_TYPE_OWNERSHIP);

		NTree nTree = new NTree(new MyNode(id, getTypeOwnership(type, false)));
		NodeList nodeList = document.getElementsByTagName(LVL_FOUR_TXT);
		readAllNodesNTreePrivileges(nTree, nodeList);
		return nTree;
	}

	private static void readAllNodesNTreePrivileges(NTree nTree, NodeList nodeList) {
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
				readAllNodesNTreePrivileges(nTree, nodo.getChildNodes());
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
		default:
			break;
		}
		return ownership;
	}
	
	public static NTree readXmlNTreeMyOwnership(String xmlFile) throws SAXException, IOException, ParserConfigurationException,
	TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
		document.getDocumentElement().normalize();

		Element rootElement = document.getDocumentElement();
		int id = Integer.valueOf(rootElement.getAttribute(ATTRIBUTE_ID));
		String type = rootElement.getAttribute(ATTRIBUTE_TYPE_OWNERSHIP);

		NTree nTree = new NTree(new MyNode(id, getTypeOwnership(type, false)));
		NodeList nodeList = document.getElementsByTagName(LVL_FOUR_TXT);
		readXmlNTreeMyOwnership(nTree, nodeList);
		return nTree;
	}

	private static void readXmlNTreeMyOwnership(NTree nTree, NodeList nodeList) {
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
				readXmlNTreeMyOwnership(nTree, nodo.getChildNodes());
			}
		}
	}
	
	public static void writeXMLNTreeOwnershipsClient(NTree nTree, String pathFile) throws ParserConfigurationException, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().
				newDocumentBuilder().newDocument();
		Element rootTag = document.createElement(ROOT_TAG_TREE);
		rootTag.setAttribute(ATTRIBUTE_ID, String.valueOf(nTree.getRoot().getId()));
		rootTag.setAttribute(ATTRIBUTE_TYPE_OWNERSHIP, nTree.getRoot().getData().getTypeOwnership());
		document.appendChild(rootTag);

		writeXMLNTreeOwnershipsClient(nTree.getRoot(), rootTag, document);

		Source source = new DOMSource(document);
		Result result = new StreamResult(new File(pathFile));
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, result);
		Logger.getGlobal().info(XML_FILE_CREATED_MESSAGE);
	}

	private static void writeXMLNTreeOwnershipsClient(MyNode base, Element elementBase, Document document) {
		for (MyNode  node : base.getChildrenList()) {
			if (node.getData().getLevelName().equals("lvlTwo")) {
				node.getData().setLevelName("lvlOne");
			}
			Element elementNode = document.createElement(node.getData().getLevelName());
			elementNode.setAttribute(ATTRIBUTE_ID, String.valueOf(node.getId()));
			elementNode.setAttribute(ATTRIBUTE_TYPE_OWNERSHIP, node.getData().getTypeOwnership());
			elementNode.setAttribute(ATTRIBUTE_CAN_BE_PURCHASED, String.valueOf(node.getData().canBePurchased()));
			elementBase.appendChild(elementNode);
			writeXMLNTreeOwnershipsClient(node, elementNode, document);
		}
	}

	public static String xmlToString(String xmlFile) throws IOException {
		Reader fileReader = new FileReader(new File(xmlFile));
		BufferedReader bufReader = new BufferedReader(fileReader);
		StringBuilder sb = new StringBuilder();
		String line = bufReader.readLine();
		while( line != null){
			sb.append(line).append("\n");
			line = bufReader.readLine();
		} 
		String xmlToString = sb.toString();
		bufReader.close();
		return xmlToString;
	}

	public static void main(String[] args) {
		try {
			System.out.println(FileManager.readXMLUsers());
			FileManager.writeXMLUserClient("Cristian");
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
	}
}