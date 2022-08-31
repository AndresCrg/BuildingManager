package views;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTree;
import constants.Constant;
import models.MyNode;
import presenters.Presenter;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private Menu menu;
	private PnlPaintNTree pnlPaintNTreeClient;
	
	public MainWindow(Presenter presenter) {
		setTitle(Constant.TITLE_APP);
		setIconImage(new ImageIcon(getClass().getResource(Constant.PATH_ICON_APP)).getImage());
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		menu = new Menu(presenter);
		add(menu, BorderLayout.PAGE_START);
		
		pnlPaintNTreeClient = new PnlPaintNTree(presenter);
		add(pnlPaintNTreeClient, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public void paintNTreeClient(MyNode root) {
		pnlPaintNTreeClient.paintNTree(root);
	}
	
	public JTree getTreeUIClient() {
		return pnlPaintNTreeClient.getTreeUI();
	}

	public PnlPaintNTree getPnlPaintNTreeClient() {
		return pnlPaintNTreeClient;
	}
}