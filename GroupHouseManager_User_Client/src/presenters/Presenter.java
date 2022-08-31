package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import constants.Constant;
import models.MyNode;
import models.NTree;
import network.Client;
import network.IPresenter;
import views.DialogAddInvoice;
import views.LoginDialog;
import views.MainWindow;

public class Presenter extends MouseAdapter implements ActionListener, TreeSelectionListener, IPresenter{

	private NTree nTreeClient;
	private Client client;
	private MyNode node;
	private MainWindow mainWindow;
	private LoginDialog loginDialog;
	private DialogAddInvoice dialogAddInvoice;

	public Presenter() {
		try {
			client = new Client(this);
			node = new MyNode(null);
			loginDialog = new LoginDialog(this);
			loginDialog.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void receivedNotificationLogIn(String message, boolean isRegistered, NTree nTreeClient) {
		JOptionPane.showMessageDialog(null, message);
		if (isRegistered) {
			loginDialog.setVisible(false);
			this.nTreeClient = nTreeClient;
			mainWindow = new MainWindow(this);
			mainWindow.paintNTreeClient(nTreeClient.getRoot());
		}else {
			loginDialog.clearField();
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		selectedNode(mainWindow.getTreeUIClient());
	}

	public void selectedNode(JTree treeUI) {
		DefaultMutableTreeNode nodeUI = (DefaultMutableTreeNode)
				treeUI.getLastSelectedPathComponent();
		if (nodeUI == null) {
			return;
		}else {
			node = (MyNode) nodeUI.getUserObject();
		}
	}

	private boolean canAddInvoice() {
		if (node.getData().getTypeOwnership().equals(Constant.INTERNET_TXT) ||
				node.getData().getTypeOwnership().equals(Constant.GAS_TXT) ||
				node.getData().getTypeOwnership().equals(Constant.WATER_TXT) ||
				node.getData().getTypeOwnership().equals(Constant.LIGHT_TXT)) {
			return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int selRow = mainWindow.getTreeUIClient().getRowForLocation(e.getX(), e.getY());
		if (selRow >- 1 && e.getClickCount() == 2 && !e.isConsumed()){
			if (canAddInvoice()) {
				dialogAddInvoice = new DialogAddInvoice(this, node.getData().getTypeOwnership().toUpperCase());
				dialogAddInvoice.setVisible(true);
			}
		}
		mainWindow.getTreeUIClient().setSelectionRow(selRow);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case VALIDATE_LOGIN_USER:
			validateLogin();
			break;
		case CANCEL:
			break;
		case GENERATE_REPORT:
			break;
		case GAS:
			generateInvoice(((JButton)e.getSource()).getActionCommand());
			break;
		case INTERNET:
			generateInvoice(((JButton)e.getSource()).getActionCommand());
			break;
		case LIGHT:
			generateInvoice(((JButton)e.getSource()).getActionCommand());
			break;
		case WATER:
			generateInvoice(((JButton)e.getSource()).getActionCommand());
			break;
		case REMOVE:
			break;
		case EXIT:
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private void validateLogin() {
		client.sendUserNameToValidate(loginDialog.getUserName());
	}

	private void generateInvoice(String invoice) {
		client.generateInvoice(node.getId(), nTreeClient.getRoot().getData().getTypeOwnership(),
				invoice, dialogAddInvoice.getDate(),
				dialogAddInvoice.getValueInvoice());
		dialogAddInvoice.setVisible(false);
	}

	@Override
	public void receivedNtree(NTree nTree) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		new Presenter();
	}
}