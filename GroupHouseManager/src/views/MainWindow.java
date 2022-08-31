package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import constants.Constant;
import models.MyNode;
import presenters.Presenter;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private PnlPaintNTree pnlPaintNTreeOwnership;
	private PnlPaintNTree pnlPaintNTreePrivileges;
	private DialogAddUser dialogAddUser;
	private DialogTableOwnershipAvalibleToPurchase dialogPurchase;
	private DialogTableReport dialogTableReport;
	private DialogInputRangeValue dialogInputRangeValue;
	
	public MainWindow(Presenter presenter) {
		setTitle(Constant.TITLE_APP);
		setIconImage(new ImageIcon(getClass().getResource(Constant.PATH_ICON_APP)).getImage());
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		menu = new Menu(presenter);
		add(menu, BorderLayout.PAGE_START);
		
		pnlPaintNTreeOwnership = new PnlPaintNTree(presenter);
		pnlPaintNTreePrivileges = new PnlPaintNTree(presenter);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(Constant.OWNERSHIP_TXT, pnlPaintNTreeOwnership);
		tabbedPane.addTab(Constant.PRIVILEGES_TXT, pnlPaintNTreePrivileges);
		add(tabbedPane, BorderLayout.CENTER);
		
		dialogAddUser = new DialogAddUser(presenter);
		dialogPurchase = new DialogTableOwnershipAvalibleToPurchase(presenter);
		dialogTableReport = new DialogTableReport(presenter);
		dialogInputRangeValue = new DialogInputRangeValue(presenter);
		
		setVisible(true);
	}
	
	public void paintNTreeOwnership(MyNode root) {
		pnlPaintNTreeOwnership.paintNTree(root);
	}
	
	public JTree getTreeUIOwnership() {
		return pnlPaintNTreeOwnership.getTreeUI();
	}
	
	public void paintNTreePrivileges(MyNode root) {
		pnlPaintNTreePrivileges.paintNTree(root);
	}
	
	public JTree getTreeUIPrivileges() {
		return pnlPaintNTreePrivileges.getTreeUI();
	}

	public PnlPaintNTree getPnlPaintNTreeOwnership() {
		return pnlPaintNTreeOwnership;
	}

	public PnlPaintNTree getPnlPaintNTreePrivileges() {
		return pnlPaintNTreePrivileges;
	}

	public DialogAddUser getDialogAddUser() {
		return dialogAddUser;
	}
	
	public DialogTableOwnershipAvalibleToPurchase getDialogPurchase() {
		return dialogPurchase;
	}

	public DialogTableReport getDialogTableReport() {
		return dialogTableReport;
	}
	
	public DialogInputRangeValue getDialogInputRangeValue() {
		return dialogInputRangeValue;
	}

	public DefaultTableModel getModelTable() {
		return dialogTableReport.getModelTable();
	}
	
	public DefaultTreeModel getModelTreeUI() {
		return pnlPaintNTreePrivileges.getModelTreeUI();
	}
	
	public DefaultMutableTreeNode getRootUI() {
		return pnlPaintNTreePrivileges.getRootUI();
	}
	
	public Object[] createObj(DefaultMutableTreeNode node) {
		return dialogTableReport.createObj(node);
	}

	public void showDialog(JDialog dialog) {
		dialog.setVisible(true);
	}
	
	public void hiddenDialog(JDialog dialog) {
		dialog.setVisible(false);
	}
	
	public void clearFieldUser() {
		dialogAddUser.clearField();
	}
	
	public String getUserNameUser() {
		return dialogAddUser.getUserName();
	}
	
	public void addMatches(ArrayList<MyNode> nodeList) {
		dialogPurchase.addMatches(nodeList);
	}
	
	public int getFirstValue() {
		return dialogInputRangeValue.getFirstValue();
	}
	
	public int getSecondValue() {
		return dialogInputRangeValue.getSecondValue();
	}
}