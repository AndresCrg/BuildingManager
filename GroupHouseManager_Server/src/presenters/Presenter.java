package presenters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.xml.sax.SAXException;
import models.Apartment;
import models.Bill;
import models.Building;
import models.CoOwnership;
import models.House;
import models.Invoice;
import models.NTree;
import models.MyNode;
import models.TypeBill;
import models.TypeCoOwnership;
import models.User;
import network.Connection;
import network.IPresenter;
import network.Server;
import persistence.FileManager;

public class Presenter implements IPresenter{
	
	private static final String XML_EXTENSION = ".xml";
	private static final String DATA_FOLDER_N_TREE_CLIENT = "./data/FolderNTree/";
	private static final String DATA_NTREE_OWNERSHIP_PATH = "./data/FolderNTree/nTreeOwnerships.xml";
	private static final String DATA_NTREE_PRIVILEGES_PATH = "./data/FolderNTree/nTreePrivileges.xml";
	private static final String HOUSE_CREATED_MESSAGE = "House created!";
	private static final String BUILDING_CREATED_MESSAGE = "Building created!";
	private static final String APARTMENT_CREATED_MESSAGE = "Apartment created!";
	private static final String CO_OWNERSHIP_CREATED_MESSAGE = "Co-Ownership created!";
	private static final String USER_CREATED_MESSAGE = "User created!";
	private static final String NODE_REMOVED_MESSAGE = "Node removed!";
	private static final String OWNERSHIP_ASSIGNED_MESSAGE = "Ownership assigned";
	private NTree nTreeOwnership;
	private NTree nTreePrivileges;
	private Server server;

	public Presenter() throws SAXException, IOException, ParserConfigurationException,
	TransformerFactoryConfigurationError, TransformerException {
		nTreeOwnership = FileManager.readXmlNTreeOwnership(DATA_NTREE_OWNERSHIP_PATH);
		nTreePrivileges = FileManager.readXmlNTreePrivileges(DATA_NTREE_PRIVILEGES_PATH);
		this.server = new Server(this);
		intiServer();
	}

	private void intiServer() {
		try {
			server.acceptConnections();
			server.manageProxy();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, e.toString());
		}
	}
	
	private void createBill(MyNode nodeData) throws SAXException, IOException, ParserConfigurationException,
	TransformerFactoryConfigurationError, TransformerException {
		nTreeOwnership.add(nodeData.getId(), new MyNode(new Bill(TypeBill.INTERNET)));
		nTreeOwnership.add(nodeData.getId(), new MyNode(new Bill(TypeBill.LIGHT)));
		nTreeOwnership.add(nodeData.getId(), new MyNode(new Bill(TypeBill.WATER)));
		nTreeOwnership.add(nodeData.getId(), new MyNode(new Bill(TypeBill.GAS)));
	}

	@Override
	public void addHouse(int idFather) {
		MyNode nodeHouse;
		try {
			nodeHouse = new MyNode(new House());
			nTreeOwnership.add(idFather, nodeHouse);
			createBill(nodeHouse);
			Logger.getGlobal().info(HOUSE_CREATED_MESSAGE);
			FileManager.writeXMLNTreeOwnership(nTreeOwnership,DATA_NTREE_OWNERSHIP_PATH);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addBuilding(int idFather) {
		MyNode nodeBuilding;
		try {
			nodeBuilding = new MyNode(new Building());
			nTreeOwnership.add(idFather, nodeBuilding);
			Logger.getGlobal().info(BUILDING_CREATED_MESSAGE);
			FileManager.writeXMLNTreeOwnership(nTreeOwnership, DATA_NTREE_OWNERSHIP_PATH);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addApartment(int idFather) {
		MyNode nodeApartment;
		try {
			nodeApartment = new MyNode(new Apartment());
			nTreeOwnership.add(idFather, nodeApartment);
			createBill(nodeApartment);
			Logger.getGlobal().info(APARTMENT_CREATED_MESSAGE);
			FileManager.writeXMLNTreeOwnership(nTreeOwnership, DATA_NTREE_OWNERSHIP_PATH);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addCoOwnership(int idFather, String coOwnership) {
		MyNode nodeCoOwnership;
		try {
			nodeCoOwnership = new MyNode(new CoOwnership(TypeCoOwnership.valueOf(coOwnership)));
			nTreeOwnership.add(idFather, nodeCoOwnership);
			Logger.getGlobal().info(CO_OWNERSHIP_CREATED_MESSAGE);
			FileManager.writeXMLNTreeOwnership(nTreeOwnership, DATA_NTREE_OWNERSHIP_PATH);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addUser(int idFather, String userName) {
		MyNode nodeUser;
		try {
			nodeUser = new MyNode(new User(userName));
			nTreePrivileges.add(idFather, nodeUser);
			Logger.getGlobal().info(USER_CREATED_MESSAGE);
			FileManager.writeXMLNTreePrivileges(nTreePrivileges, DATA_NTREE_PRIVILEGES_PATH);
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void assignOwnershipToClient(int idNode, int idFather) {
		try {
			MyNode nodeOwnership = nTreeOwnership.search(idNode);
			nodeOwnership.getData().setBePurchased(false);
			nTreePrivileges.add(idFather, nodeOwnership);
			FileManager.writeXMLNTreePrivileges(nTreePrivileges, DATA_NTREE_PRIVILEGES_PATH);
			FileManager.writeXMLNTreeOwnership(nTreeOwnership, DATA_NTREE_OWNERSHIP_PATH);
			Logger.getGlobal().info(OWNERSHIP_ASSIGNED_MESSAGE);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void remove(int idNode) {
		try {
			nTreeOwnership.remove(idNode);
			Logger.getGlobal().info(NODE_REMOVED_MESSAGE);
			FileManager.writeXMLNTreeOwnership(nTreeOwnership, DATA_NTREE_OWNERSHIP_PATH);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createSubNTreeClient(String nameClient) {
		try {
			NTree copyPrivileges = nTreePrivileges;
			copyPrivileges.getSubTree(nameClient);
			NTree sub = copyPrivileges.getSubTree(nameClient);
			sub.print();
			FileManager.writeXMLNTreeOwnershipsClient(sub, DATA_FOLDER_N_TREE_CLIENT + nameClient + XML_EXTENSION);
		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void createInvoice(int idFather, String userName, String typeInvoice, String date,
			int value) {
		try {
			nTreeOwnership.add(idFather, new MyNode(new Invoice(date, value)));
			FileManager.writeXMLNTreeOwnership(nTreeOwnership, DATA_NTREE_OWNERSHIP_PATH);
			sendInfoToAdmin();
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
	
	private void sendInfoToAdmin() {
		for (Connection connection : server.getConnections()) {
			if (connection.getId() == 1) {
				connection.sendInfo();
			}
		}
	}

	public static void main(String[] args) {
		try {
			new Presenter();
		} catch (SAXException | IOException | ParserConfigurationException | TransformerFactoryConfigurationError
				| TransformerException e) {
			e.printStackTrace();
		}
	}
}