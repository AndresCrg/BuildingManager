package views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import constants.Constant;
import presenters.Action;
import presenters.Presenter;

public class Menu extends JMenuBar{

	private static final long serialVersionUID = 1L;
	
	public Menu(Presenter presenter) {
		setBackground(Constant.MY_COLOR_WHITE);
		
		JMenu menuFile = new JMenu(Constant.REPORTS_TXT);
		JMenuItem itemGenerateReport = new JMenuItem(Constant.GENERATE_REPORT_TXT);
		itemGenerateReport.setActionCommand(Action.SHOW_DIALOG_REPORT_DEBT.toString());
		itemGenerateReport.addActionListener(presenter);
		menuFile.add(itemGenerateReport);
		
		JMenuItem itemGenerateTableReport = new JMenuItem(Constant.GENERATE_REPORT_TABLE_TXT);
		itemGenerateTableReport.setActionCommand(Action.GENERATE_TABLE_REPORT.toString());
		itemGenerateTableReport.addActionListener(presenter);
		menuFile.add(itemGenerateTableReport);
		add(menuFile);
	}
}