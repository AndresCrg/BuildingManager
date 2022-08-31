package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.transform.TransformerFactoryConfigurationError;
import models.NTree;
import models.MyNode;
import network.Client;
import network.IPresenter;
import views.LoginDialog;
import views.MainWindow;
import views.PopUpMenu;

public class Presenter extends MouseAdapter implements ActionListener, TreeSelectionListener, IPresenter{

	private NTree nTreeOwnership;
	private NTree nTreePrivileges;
	private MainWindow mainWindow;
	private MyNode node;
	private Client client;
	private LoginDialog loginDialog;

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
	public void receivedNotificationLogIn(String message, boolean isAdmin, NTree nTreeOwnership,
			NTree nTreePrivileges) {
		JOptionPane.showMessageDialog(null, message);
		if (isAdmin) {
			loginDialog.setVisible(false);
			this.nTreeOwnership = nTreeOwnership;
			this.nTreePrivileges = nTreePrivileges;
			mainWindow = new MainWindow(this);
			mainWindow.paintNTreeOwnership(nTreeOwnership.getRoot());
			mainWindow.paintNTreePrivileges(nTreePrivileges.getRoot());
		}
	}
	
	@Override
	public void receivedNotificationAdminNotFound(String message, boolean isAdmin) {
		JOptionPane.showMessageDialog(null, message, null, JOptionPane.WARNING_MESSAGE);
		if (!isAdmin) {
			loginDialog.clearField();
		}
	}

	@Override
	public void receivedNtreeOwnership(NTree nTree) {
		nTreeOwnership = nTree;
		mainWindow.paintNTreeOwnership(nTreeOwnership.getRoot());
	}
	
	@Override
	public void receivedNtreePrivileges(NTree nTree) {
		nTreePrivileges = nTree;
		mainWindow.paintNTreePrivileges(nTreePrivileges.getRoot());
	}
	
	@Override
	public void receivedMessageUserIsAlready(String message) {
		JOptionPane.showMessageDialog(null, message, null, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (e.getSource().equals(mainWindow.getTreeUIOwnership())) {
			selectedNode(mainWindow.getTreeUIOwnership());
		}else if (e.getSource().equals(mainWindow.getTreeUIPrivileges())) {
			selectedNode(mainWindow.getTreeUIPrivileges());
		}
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

	public boolean canAddApartment() {
		return node.getData().canAddApartment();
	}

	public boolean canAddInvoice() {
		return node.getData().canAddInvoice();
	}

	public boolean canAddUser() {
		return node.getData().canAddUser();
	}

	public boolean canBePurchased() {
		return node.getData().canBePurchased();
	}

	public boolean canIPurchase() {
		return node.getData().canIPurchase();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		PopUpMenu popUpMenu =  new PopUpMenu();
		if (e.getComponent() == mainWindow.getTreeUIOwnership()) {
			showPopUpMenuOwnership(e, popUpMenu);
		}else if (e.getComponent() == mainWindow.getTreeUIPrivileges()) {
			showPopUpPrivileges(e, popUpMenu);
		}
	}

	public void showPopUpMenuOwnership(MouseEvent e, PopUpMenu popUpMenu) {
		int selRow = mainWindow.getTreeUIOwnership().getRowForLocation(e.getX(), e.getY());
		if (selRow >- 1 && e.getButton() == MouseEvent.BUTTON3){
			if (node.getId() == nTreeOwnership.getRoot().getId() && node.isLeaf()) {
				popUpMenu.popUpRootLeaf(this);
			}else if(node.getId() == nTreeOwnership.getRoot().getId() && !node.isLeaf()){
				popUpMenu.popUpRootFull(this);
			}
			if (canAddApartment()) {
				popUpMenu.popUpBuilding(this);
			}
		}
		mainWindow.getTreeUIOwnership().setSelectionRow(selRow);
		popUpMenu.show(mainWindow.getTreeUIOwnership(), e.getX(), e.getY());
	}

	public void showPopUpPrivileges(MouseEvent e, PopUpMenu popUpMenu) {
		int selRow = mainWindow.getTreeUIPrivileges().getRowForLocation(e.getX(), e.getY());
		if (selRow >- 1 && e.getButton() == MouseEvent.BUTTON3){
			if (canAddUser()) {
				popUpMenu.popUpPrivileges(this);
			}
			if (canIPurchase()) {
				popUpMenu.popUpUserOwnership(this);
			}
		}
		mainWindow.getTreeUIPrivileges().setSelectionRow(selRow);
		popUpMenu.show(mainWindow.getTreeUIPrivileges(), e.getX(), e.getY());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Action.valueOf(e.getActionCommand())) {
		case ADD_APARTMENT:
			addApartment();
			break;
		case ADD_BUILDING:
			addBuilding();
			break;
		case ADD_HOUSE:
			addHouse();
			break;
		case REMOVE:
			remove();
			break;
		case BBQ:
			addCoOwnership(((JMenuItem)e.getSource()).getActionCommand());
			break;
		case HALL:
			addCoOwnership(((JMenuItem)e.getSource()).getActionCommand());
			break;
		case GYM:
			addCoOwnership(((JMenuItem)e.getSource()).getActionCommand());
			break;
		case PARK:
			addCoOwnership(((JMenuItem)e.getSource()).getActionCommand());
			break;
		case SWIMMING:
			addCoOwnership(((JMenuItem)e.getSource()).getActionCommand());
			break;
		case CANCEL:
			cancel();
			break;
		case ADD_USER:
			addUser();
			break;
		case SHOW_DIALOG_ADD_USER:
			showDialogAddUser();
			break;
		case SHOW_DIALOG_PURCHASE_OWNERSHIP:
			showDialogPurchaseOwnership();
			break;
		case PURCHASE_OWNERSHIP:
			purchase((JButton)e.getSource());
			break;
		case VALIDATE_LOGIN_USER:
			validateLogin();
			break;
		case SHOW_DIALOG_REPORT_DEBT:
			showDialogReportDebt();
			break;
		case GENERATE_TABLE_REPORT:
			generateReportTable();
			break;
		case FILTER_DEBT:
			filterDebt();
			break;
		case EXIT:
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private void filterDebt() {
		nTreeOwnership.getDebt(mainWindow.getFirstValue(), mainWindow.getSecondValue());
		mainWindow.paintNTreeOwnership(nTreeOwnership.getRoot());
		mainWindow.hiddenDialog(mainWindow.getDialogInputRangeValue());
	}

	private void showDialogReportDebt() {
		mainWindow.showDialog(mainWindow.getDialogInputRangeValue());
	}

	private void validateLogin() {
		client.sendUserNameToValidate(loginDialog.getUserName());
	}

	private void addHouse() {
		try {
			client.createHouse(node.getId());
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	private void addBuilding() {
		try {
			client.createBuilding(node.getId());
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	private void addApartment() {
		try {
			client.createApartment(node.getId());
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}

	private void addCoOwnership(String coOwnership) {
		client.createCoOwnership(node.getId(), coOwnership);
	}
	
	private void addUser() {
		client.createUser(node.getId(), mainWindow.getUserNameUser());
		mainWindow.hiddenDialog(mainWindow.getDialogAddUser());
		mainWindow.clearFieldUser();
	}

	private void showDialogAddUser() {
		mainWindow.showDialog(mainWindow.getDialogAddUser());
	}
	
	private void showDialogPurchaseOwnership() {
		mainWindow.addMatches(nTreeOwnership.getNodesAvaliblesForPurchase());
		mainWindow.showDialog(mainWindow.getDialogPurchase());
	}
	
	private void cancel() {
		
	}

	private void remove() {
		client.remove(node.getId());
	}

	private void purchase(JButton btn) {
		client.assignOwnershipToClient(Integer.parseInt(btn.getName()), node.getId());
		mainWindow.hiddenDialog(mainWindow.getDialogPurchase());
	}
	
	private void generateReportTable() {
		String data;
		mainWindow.getModelTable().setRowCount(0);
		for (int i = 0; i < mainWindow.getModelTreeUI().getChildCount(mainWindow.getRootUI()); i++) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) mainWindow.getRootUI().getChildAt(i);
			Object obj[] = new Object[node.getChildCount()];
			MyNode nodeHeadLogic = (MyNode) node.getUserObject();
			data = nodeHeadLogic.getData().getTypeOwnership();
			for (int j = 0; j < node.getChildCount(); j++) {
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(j);
				MyNode nodeLogicChild = (MyNode) child.getUserObject();
				obj[j] = nodeLogicChild.getData().getTypeOwnership();
			}
			mainWindow.getModelTable().addColumn(data, obj);
		}
		mainWindow.showDialog(mainWindow.getDialogTableReport());
	}
}