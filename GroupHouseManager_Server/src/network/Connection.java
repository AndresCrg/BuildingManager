package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.xml.sax.SAXException;

import persistence.FileManager;

public class Connection {

	private static final String MESSAGE_USER_IS_ALREADY_REGISTERED = "The user is already registered, please try another name.";
	private static final String WELCOME_MESSAGE = "Welcome to Luxury Apartments";
	private static final String NEW_REQUEST_AVALIBLE_MESSAGE = "New request avalible in Admin";
	private static final String USER_ADMIN = "Administrator";
	private static final String USER_CLIENT = "Client";
	private static final String SHOW_NTREE = "Show Tree";
	private static final String ADD_HOUSE_TXT = "Add House";
	private static final String ADD_BUILDING_TXT = "Add Building";
	private static final String ADD_APARTMENT_TXT = "Add Aparment";
	private static final String ADD_CO_OWNERSHIP_TXT = "Co-Ownership";
	private static final String ADD_USER_TXT = "Add User";
	private static final String REMOVE_TXT = "Remove";
	private static final String ASSIGN_OWNERSHIP_TO_CLIENT_TXT = "Assign ownership";
	private static final String REQUEST_GENERATE_INVOICE = "Generate invoice";
	private static final String VALIDATE_USER_ADMIN = "VALIDATE_USER_ADMIN";
	private static final String NOTIFICATION_LOGIN = "NOTIFICATION_LOGIN";
	private static final String NOTIFICATION_HOUSE_ADDED = "NOTIFICATION_HOUSE_ADDED";
	private static final String NOTIFICATION_BUILDING_ADDED = "NOTIFICATION_BUILDING_ADDED";
	private static final String NOTIFICATION_APARTMENT_ADDED = "NOTIFICATION_APARTMENT_ADDED";
	private static final String NOTIFICATION_NODE_REMOVED = "NOTIFICATION_NODE_REMOVED";
	private static final String NOTIFICATION_CO_OWNERSHIP_ADDED = "NOTIFICATION_CO_OWNERSHIP_ADDED";
	private static final String NOTIFICATION_USER_ADDED = "NOTIFICATION_USER_ADDED";
	private static final String NOTIFICATION_OWNERSHIP_ASSIGNED = "NOTIFICATION_OWNERSHIP_ASSIGNED";
	private static final String NOTIFICATION_USER_IS_ALREADY = "NOTIFICATION_USER_IS_ALREADY";
	private static final String REQUEST_VALIDATE_USER_CLIENT = "REQUEST_VALIDATE_USER_CLIENT";
	private static final String NOTIFICATION_ADMIN_NOT_FOUND = "NOTIFICATION_ADMIN_NOT_FOUND";
	private static final String NOTIFICATION_TREE_MODIFIED = "NOTIFICATION_TREE_MODIFIED";
	private static final String USER_NOT_FOUND_MESSAGE = "User not found!";
	private static final String XML_EXTENSION = ".xml";
	private static final String DATA_NTREE_OWNERSHIP_PATH = "./data/FolderNTree/nTreeOwnerships.xml";
	private static final String DATA_USER_ADMIN_PATH = "./data/FolderUsers/userAdmin.xml";
	private static final String DATA_NTREE_PRIVILEGES_PATH = "./data/FolderNTree/nTreePrivileges.xml";
	private static final String DATA_FOLDER_N_TREE_CLIENT = "./data/FolderNTree/";
	private static final String DATA_USERS_PATH = "./data/FolderUsers/users.xml";
	
	private static int currentID = 1;
	private Socket socketConnect;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private IPresenter iPresenter;
	private int id;

	public Connection(Socket socketConnect, IPresenter iPresenter) throws IOException {
		this.socketConnect = socketConnect;
		this.iPresenter = iPresenter;
		this.id = currentID;
		inputStream = new DataInputStream(socketConnect.getInputStream());
		outputStream = new DataOutputStream(socketConnect.getOutputStream());
		currentID++;
	}

	public void manageRequest() {
		try {
			if (socketConnect.getInputStream().available() > 0) {
				String request = inputStream.readUTF();
				Logger.getGlobal().log(Level.INFO, request);
				switch (request) {
				case USER_ADMIN:
					manageAdmin();
					break;
				case USER_CLIENT:
					manageClient();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void manageAdmin() throws IOException {
		String request = inputStream.readUTF();
		Logger.getGlobal().log(Level.INFO, NEW_REQUEST_AVALIBLE_MESSAGE);
		Logger.getGlobal().log(Level.INFO, request);
		switch (request) {
		case VALIDATE_USER_ADMIN:
			validateUserAdmin();
			break;
		case SHOW_NTREE:

			break;
		case ADD_HOUSE_TXT:
			addHouse();
			break;
		case ADD_BUILDING_TXT:
			addBuilding();
			break;
		case ADD_APARTMENT_TXT:
			addApartment();
			break;
		case ADD_CO_OWNERSHIP_TXT:
			addCoOwnership();
			break;
		case ADD_USER_TXT:
			addUser();
			break;
		case ASSIGN_OWNERSHIP_TO_CLIENT_TXT:
			assignOwnerToClient();
			break;
		case REMOVE_TXT:
			remove();
			break;
		default:
			break;
		}
	}
	
	private void manageClient() throws IOException {
		String request = inputStream.readUTF();
		switch (request) {
		case REQUEST_VALIDATE_USER_CLIENT:
			validateUserClient();
			break;
		case REQUEST_GENERATE_INVOICE:
			generateInvoice();
			break;
		default:
			break;
		}
	}

	private void validateUserClient() {
		try {
			String nameClient = inputStream.readUTF();
			if (FileManager.isRegistered(nameClient, DATA_USERS_PATH)) {
				iPresenter.createSubNTreeClient(nameClient);
				outputStream.writeUTF(NOTIFICATION_LOGIN);
				outputStream.writeUTF(WELCOME_MESSAGE);
				outputStream.writeBoolean(true);
				outputStream.writeUTF(FileManager.xmlToString(DATA_FOLDER_N_TREE_CLIENT + nameClient + XML_EXTENSION));
			}else {
				outputStream.writeUTF(NOTIFICATION_LOGIN);
				outputStream.writeUTF(USER_NOT_FOUND_MESSAGE);
				outputStream.writeBoolean(false);
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void validateUserAdmin() {
		try {
			if (FileManager.isRegistered(inputStream.readUTF(), DATA_USER_ADMIN_PATH)) {
				outputStream.writeUTF(NOTIFICATION_LOGIN);
				outputStream.writeUTF(WELCOME_MESSAGE);
				outputStream.writeBoolean(true);
				outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
				outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_PRIVILEGES_PATH));
			}else {
				outputStream.writeUTF(NOTIFICATION_ADMIN_NOT_FOUND);
				outputStream.writeUTF(USER_NOT_FOUND_MESSAGE);
				outputStream.writeBoolean(false);
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private void addUser() {
		try {
			int id = inputStream.readInt();
			String userName = inputStream.readUTF();
			if (!FileManager.isRegistered(userName, DATA_USERS_PATH)) {
				FileManager.writeXMLUserClient(userName);
				iPresenter.addUser(id, userName);
				outputStream.writeUTF(NOTIFICATION_USER_ADDED);
				outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_PRIVILEGES_PATH));
			}else {
				outputStream.writeUTF(NOTIFICATION_USER_IS_ALREADY);
				outputStream.writeUTF(MESSAGE_USER_IS_ALREADY_REGISTERED);
			}
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException
				| SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private void addHouse() {
		try {
			iPresenter.addHouse(inputStream.readInt());
			outputStream.writeUTF(NOTIFICATION_HOUSE_ADDED);
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addBuilding() {
		try {
			iPresenter.addBuilding(inputStream.readInt());
			outputStream.writeUTF(NOTIFICATION_BUILDING_ADDED);
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addApartment() {
		try {
			iPresenter.addApartment(inputStream.readInt());
			outputStream.writeUTF(NOTIFICATION_APARTMENT_ADDED);
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addCoOwnership() {
		try {
			iPresenter.addCoOwnership(inputStream.readInt(), inputStream.readUTF());
			outputStream.writeUTF(NOTIFICATION_CO_OWNERSHIP_ADDED);
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void assignOwnerToClient() {
		try {
			iPresenter.assignOwnershipToClient(inputStream.readInt(), inputStream.readInt());
			outputStream.writeUTF(NOTIFICATION_OWNERSHIP_ASSIGNED);
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_PRIVILEGES_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void generateInvoice() {
		try {
			iPresenter.createInvoice(inputStream.readInt(), inputStream.readUTF(),
					inputStream.readUTF(), inputStream.readUTF(),
					inputStream.readInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void remove() {
		try {
			iPresenter.remove(inputStream.readInt());
			outputStream.writeUTF(NOTIFICATION_NODE_REMOVED);
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendInfo() {
		try {
			outputStream.writeUTF(NOTIFICATION_TREE_MODIFIED);
			outputStream.writeUTF(FileManager.xmlToString(DATA_NTREE_OWNERSHIP_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	
}