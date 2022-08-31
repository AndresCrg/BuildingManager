package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.xml.sax.SAXException;
import constants.Constant;
import persistence.FileManager;

public class Client {

	private static final int PORT = 3005;
	private static final String HOST = "localhost";
	private static final String NOTIFICATION_LOGIN = "NOTIFICATION_LOGIN";
	private static final String NOTIFICATION_HOUSE_ADDED = "NOTIFICATION_HOUSE_ADDED";
	private static final String NOTIFICATION_BUILDING_ADDED = "NOTIFICATION_BUILDING_ADDED";
	private static final String NOTIFICATION_APARTMENT_ADDED = "NOTIFICATION_APARTMENT_ADDED";
	private static final String NOTIFICATION_NODE_REMOVED = "NOTIFICATION_NODE_REMOVED";
	private static final String NOTIFICATION_CO_OWNERSHIP_ADDED = "NOTIFICATION_CO_OWNERSHIP_ADDED";
	private static final String NOTIFICATION_USER_ADDED = "NOTIFICATION_USER_ADDED";
	private static final String NOTIFICATION_OWNERSHIP_ASSIGNED = "NOTIFICATION_OWNERSHIP_ASSIGNED";
	private static final String NOTIFICATION_USER_IS_ALREADY = "NOTIFICATION_USER_IS_ALREADY";
	private static final String NOTIFICATION_ADMIN_NOT_FOUND = "NOTIFICATION_ADMIN_NOT_FOUND";
	private static final String NOTIFICATION_TREE_MODIFIED = "NOTIFICATION_TREE_MODIFIED";




	private Socket client;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private IPresenter iPresenter;

	public Client(IPresenter iPresenter) throws UnknownHostException, IOException {
		this.iPresenter = iPresenter;
		client = new Socket(HOST, PORT);
		inputStream = new DataInputStream(client.getInputStream());
		outputStream = new DataOutputStream(client.getOutputStream());
		reading();
	}

	private void reading() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(!client.isClosed()) {
						if (inputStream.available() > 0) {
							String request = inputStream.readUTF();
							System.out.println("Peticion -> " + request);
							switch (request) {
							case NOTIFICATION_LOGIN:
								iPresenter.receivedNotificationLogIn(inputStream.readUTF(),
										inputStream.readBoolean(),
										FileManager.receivedXmlFile(inputStream.readUTF()),
										FileManager.readXmlNTreePrivileges(inputStream.readUTF()));
								break;
							case NOTIFICATION_ADMIN_NOT_FOUND:
								iPresenter.receivedNotificationAdminNotFound(inputStream.readUTF(),
										inputStream.readBoolean());
							case NOTIFICATION_HOUSE_ADDED:
								iPresenter.receivedNtreeOwnership(FileManager.receivedXmlFile(inputStream.readUTF()));
								break;
							case NOTIFICATION_BUILDING_ADDED:
								iPresenter.receivedNtreeOwnership(FileManager.receivedXmlFile(inputStream.readUTF()));
								break;
							case NOTIFICATION_APARTMENT_ADDED:
								iPresenter.receivedNtreeOwnership(FileManager.receivedXmlFile(inputStream.readUTF()));
								break;
							case NOTIFICATION_NODE_REMOVED:
								iPresenter.receivedNtreeOwnership(FileManager.receivedXmlFile(inputStream.readUTF()));
								break;
							case NOTIFICATION_CO_OWNERSHIP_ADDED:
								iPresenter.receivedNtreeOwnership(FileManager.receivedXmlFile(inputStream.readUTF()));
								break;
							case NOTIFICATION_USER_ADDED:
								iPresenter.receivedNtreePrivileges(
										FileManager.readXmlNTreePrivileges(inputStream.readUTF()));
								break;
							case NOTIFICATION_USER_IS_ALREADY:
								iPresenter.receivedMessageUserIsAlready(inputStream.readUTF());
								break;
							case NOTIFICATION_OWNERSHIP_ASSIGNED:
								iPresenter.receivedNtreeOwnership(
										FileManager.receivedXmlFile(inputStream.readUTF()));
								iPresenter.receivedNtreePrivileges(
										FileManager.readXmlNTreePrivileges(inputStream.readUTF()));
								break;
							case NOTIFICATION_TREE_MODIFIED:
								iPresenter.receivedNtreeOwnership(FileManager.receivedXmlFile(inputStream.readUTF()));
								break;
							}
						}
					}
				} catch (IOException | SAXException | ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void sendUserNameToValidate(String userName) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.REQUEST_VALIDATE_USER_ADMIN);
			outputStream.writeUTF(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createHouse(int id) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.ADD_HOUSE_TXT);
			outputStream.writeInt(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createBuilding(int id) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.ADD_BUILDING_TXT);
			outputStream.writeInt(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createApartment(int id) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.ADD_APARTMENT_TXT);
			outputStream.writeInt(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createCoOwnership(int id, String coOwnership) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.ADD_CO_OWNERSHIP_TXT);
			outputStream.writeInt(id);
			outputStream.writeUTF(coOwnership);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createUser(int id, String userName) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.ADD_USER_TXT);
			outputStream.writeInt(id);
			outputStream.writeUTF(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void remove(int id) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.REMOVE_TXT);
			outputStream.writeInt(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void assignOwnershipToClient(int idNode, int idFather) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_ADMIN);
			outputStream.writeUTF(Constant.ASSIGN_OWNERSHIP_TO_CLIENT_TXT);
			outputStream.writeInt(idNode);
			outputStream.writeInt(idFather);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}