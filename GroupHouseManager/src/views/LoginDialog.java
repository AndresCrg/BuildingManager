package views;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import constants.Constant;
import presenters.Presenter;

public class LoginDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private PanelLeftLogin pnlLeftLogin;
	private PanelRightLogin pnlRightLogin;
	
	public LoginDialog(Presenter presenter) {
		setSize(900, 550);
		setUndecorated(true); 
		getContentPane().setBackground(Constant.MY_COLOR_WHITE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(1, 2));
		
		pnlLeftLogin = new PanelLeftLogin();
		add(pnlLeftLogin);
		
		pnlRightLogin = new PanelRightLogin(presenter);
		add(pnlRightLogin);
		
		setVisible(true);
	}
	
	public String getUserName() {
		return pnlRightLogin.getUserName();
	}
	
	public void clearField() {
		pnlRightLogin.clearField();
	}
	
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public static void main(String[] args) {
		new LoginDialog(null);
	}
}