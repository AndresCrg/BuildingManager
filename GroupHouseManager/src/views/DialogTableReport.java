package views;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import constants.Constant;
import presenters.Presenter;

public class DialogTableReport extends JDialog {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;

	public DialogTableReport(Presenter presenter) {
		setIconImage(new ImageIcon(getClass().getResource(Constant.PATH_ICON_APP)).getImage());
		setTitle(Constant.SEARCH_TITLE);
		setSize(Constant.SIZE_DIALOG_WIDTH, Constant.SIZE_DIALOG_WIDTH);
		setLocationRelativeTo(null);
		this.model = new DefaultTableModel();
		this.table = new JTable(model);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(Constant.MY_COLOR_BLACK);
		table.getTableHeader().setForeground(Constant.MY_COLOR_WHITE);
		table.setFont(Constant.FONT_CAMBRIA_DATA);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public Object[] createObj(DefaultMutableTreeNode node) {
		return new Object[] {node.getChildCount()};
	}

	public DefaultTableModel getModelTable() {
		return (DefaultTableModel) table.getModel();
	}
}