package views;

import models.MyNode;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class NodeRender extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if(((DefaultMutableTreeNode) value).getUserObject() != null){
			MyNode node =  (MyNode) ((DefaultMutableTreeNode) value).getUserObject();
			setIcon(new ImageIcon(getClass().getResource(node.getData().pathIcon())));
			setFont(new Font("Candara", Font.PLAIN, 12));
			setForeground(Color.BLACK);
		}
		return this;
	}
}