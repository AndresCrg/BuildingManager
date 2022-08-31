package views;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import constants.Constant;
import presenters.Actions;
import presenters.Presenter;

public class Menu extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	public Menu(Presenter presenter) {
		setBackground(Constant.MY_COLOR_WHITE);
		
		JMenu menuFile = new JMenu(Constant.REPORTS_TXT);
		JMenuItem itemGenerateReport = new JMenuItem(Constant.GENERATE_REPORT_TXT);
		itemGenerateReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		itemGenerateReport.setActionCommand(Actions.GENERATE_REPORT.toString());
		itemGenerateReport.addActionListener(presenter);
		menuFile.add(itemGenerateReport);
		add(menuFile);
	}
}