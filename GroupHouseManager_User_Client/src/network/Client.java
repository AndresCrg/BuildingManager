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
										FileManager.readXmlNTreeClient(inputStream.readUTF()));
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
			outputStream.writeUTF(Constant.REQUEST_USER_CLIENT);
			outputStream.writeUTF(Constant.REQUEST_VALIDATE_USER_CLIENT);
			outputStream.writeUTF(userName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateInvoice(int idFather, String userName,String invoice, String date,
			int valueInvoice) {
		try {
			outputStream.writeUTF(Constant.REQUEST_USER_CLIENT);
			outputStream.writeUTF(Constant.REQUEST_GENERATE_INVOICE);
			outputStream.writeInt(idFather);
			outputStream.writeUTF(userName);
			outputStream.writeUTF(invoice);
			outputStream.writeUTF(date);
			outputStream.writeInt(valueInvoice);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}