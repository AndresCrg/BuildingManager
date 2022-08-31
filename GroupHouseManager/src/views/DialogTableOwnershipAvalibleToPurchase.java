package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import constants.Constant;
import models.MyNode;
import presenters.Action;
import presenters.Presenter;

public class DialogTableOwnershipAvalibleToPurchase extends JDialog{

	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private TableCellEditor tabCellEditor;
	private TableCellRenderer tabCellRenderer;
	private Presenter presenter;
	
	public DialogTableOwnershipAvalibleToPurchase(Presenter presenter) {
		setIconImage(new ImageIcon(getClass().getResource(Constant.PATH_ICON_APP)).getImage());
		setTitle(Constant.SEARCH_TITLE);
		setSize(Constant.SIZE_DIALOG_WIDTH, Constant.SIZE_DIALOG_WIDTH);
		setLocationRelativeTo(null);
		this.presenter = presenter;
		this.model = new DefaultTableModel();
		this.model.setColumnIdentifiers(new String [] {Constant.ID_TXT, Constant.TYPE_OWNERSHIP_TXT,
				Constant.PURCHASE_TXT});
		this.table = new JTable(model);
		table.setRowHeight(30);
		table.getTableHeader().setBackground(Constant.MY_COLOR_BLACK);
		table.getTableHeader().setForeground(Constant.MY_COLOR_WHITE);
		table.setFont(Constant.FONT_CAMBRIA_DATA);
		this.tabCellRenderer = new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				return (JButton) value;
			}
		};
		table.setDefaultEditor(Object.class, null);
		this.tabCellEditor = new TableCellEditor() {
			@Override
			public boolean stopCellEditing() {
				return true;
			}
			
			@Override
			public boolean shouldSelectCell(EventObject anEvent) {
				return false;
			}
			
			@Override
			public void removeCellEditorListener(CellEditorListener l) {}
			
			@Override
			public boolean isCellEditable(EventObject anEvent) {
				return true;
			}
			
			@Override
			public Object getCellEditorValue() {
				return null;
			}
			
			@Override
			public void cancelCellEditing() {}
			
			@Override
			public void addCellEditorListener(CellEditorListener l) {}
			
			@Override
			public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
				return (JButton) value;
			}
		};
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	private void dataTable(){
		table.getColumn(Constant.PURCHASE_TXT).setCellEditor(tabCellEditor);
		table.getColumn(Constant.PURCHASE_TXT).setCellRenderer(tabCellRenderer);
	}
	
	private Object[] createObj(MyNode node) {
		return new Object[] {node.getId(), node.getData().getTypeOwnership(), btnPurchase(node.getId())};
	}
	
	public void addMatches(ArrayList<MyNode> nodeList) {
		model.setRowCount(0);
		for (MyNode node : nodeList) {
			model.addRow(createObj(node));
		}
		model.fireTableStructureChanged();
		dataTable();
		revalidate();
		repaint();
	}
	
	private JButton btnPurchase(long id) {
		JButton btnState = new JButton(new ImageIcon(getClass().getResource(Constant.ICON_PATH_BTN_PURCHASE)));
		btnState.setName(String.valueOf(id));
		btnState.setActionCommand(Action.PURCHASE_OWNERSHIP.toString());
		btnState.addActionListener(presenter);
		btnState.setBackground(Constant.MY_COLOR_GREEN);
		btnState.setEnabled(true);
		return btnState;
	}
}