package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import constants.Constant;
import presenters.Actions;
import presenters.Presenter;

public class LoginDialog extends JDialog{

	private static final Color COLOR_BACKGROUD_LOGIN = Color.decode("#fadc5d");
	private static final long serialVersionUID = 1L;
	private GridSystem gridSystem;
	private JTextArea txtUser;


	public LoginDialog(Presenter presenter) {
		setSize(400, 500);
		setUndecorated(true); 
		getContentPane().setBackground(COLOR_BACKGROUD_LOGIN);
		setLocationRelativeTo(null);
		
		gridSystem = new GridSystem(this);
		
		JLabel lbLogo = new JLabel(new ImageIcon(createImageIcon("/imgs/loginClient.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		add(lbLogo, gridSystem.insertComponent(1, 1, 12, 1));

		JLabel lbIconUser = new JLabel(new ImageIcon(createImageIcon("/imgs/userLogin.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)), SwingConstants.LEFT);
		lbIconUser.setHorizontalAlignment(SwingConstants.LEFT);
		add(lbIconUser, gridSystem.insertComponent(2, 1, 1, 1));
		
		txtUser = new JTextArea(1,20);
		txtUser.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtUser.setBackground(COLOR_BACKGROUD_LOGIN);
		txtUser.setForeground(Constant.MY_COLOR_BLACK);
		txtUser.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLACK), "Enter your username:", TitledBorder.LEADING, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 18), Color.BLACK));
		txtUser.setAlignmentX(CENTER_ALIGNMENT);
		add(txtUser, gridSystem.insertComponent(2, 2, 6, 1));

		JButton btnExit = configBtn(presenter, Actions.EXIT, "/imgs/btnExitIcon.png");
		add(btnExit, gridSystem.insertComponent(4, 2, 1, 1));
		JButton btnLogin = configBtn(presenter, Actions.VALIDATE_LOGIN_USER, "/imgs/btnLoginUser.png"); 
		add(btnLogin, gridSystem.insertComponent(4, 5, 1, 1));
	}
	
	public JButton configBtn(Presenter presenter, Actions myAction, String path) {
		JButton btn = new JButton(new ImageIcon(getClass().getResource(path)));
		btn.setOpaque(true);
		btn.setPreferredSize(new Dimension(110, 40));
		btn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btn.setBackground(COLOR_BACKGROUD_LOGIN);
		btn.setFocusable(false);
		btn.setActionCommand(myAction.toString());
		btn.addActionListener(presenter);
		return btn;
	}
	
	public String getUserName() {
		return txtUser.getText();
	}
	
	public void clearField() {
		txtUser.setText("");
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
}