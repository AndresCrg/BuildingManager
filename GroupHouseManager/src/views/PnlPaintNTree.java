package views;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import models.MyNode;
import presenters.Presenter;

public class PnlPaintNTree extends JPanel{

	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode rootUI;
	private DefaultTreeModel model;
	private JTree treeUI;

	public PnlPaintNTree(Presenter presenter) {
		rootUI = new DefaultMutableTreeNode();
		model = new DefaultTreeModel(rootUI);
		treeUI = new JTree(model);
		treeUI.addTreeSelectionListener(presenter);
		treeUI.setCellRenderer(new NodeRender());
		treeUI.addMouseListener(presenter);
		add(treeUI);
	}

	public void paintNTree(MyNode root) {
		rootUI = new DefaultMutableTreeNode(root);
		paintNTree(root, rootUI);
		model.setRoot(rootUI);
	}

	private void paintNTree(MyNode base, DefaultMutableTreeNode baseUI) {
		for (MyNode node : base.getChildrenList()) {
			DefaultMutableTreeNode current = new DefaultMutableTreeNode(node);
			baseUI.add(current);
			paintNTree(node, current);
		}
	}
	
	public DefaultTreeModel getModelTreeUI() {
		return (DefaultTreeModel) treeUI.getModel();
	}
	
	public DefaultMutableTreeNode getRootUI() {
		return (DefaultMutableTreeNode) model.getRoot();
	}

	public JTree getTreeUI() {
		return treeUI;
	}
}