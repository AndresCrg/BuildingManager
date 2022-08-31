package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import constants.Constant;
import presenters.Action;
import presenters.Presenter;

public class PanelRightLogin extends JPanel{

	private static final String IMG_ICON_USER_REGISTER_SMALL_SIZE = "/imgs/iconUserRegisterSmallSize.png";
	private static final String IMG_ICON_USER_REGISTER_MAIN = "/imgs/iconUserRegister.png";
	private static final Font FONT_TEXT_TITLE_REGISTER = new Font("Calibri", Font.BOLD, 30);
	private static final long serialVersionUID = 1L;
	private GridSystem gridSystem;
	private JTextArea txEnterUserName;

	public PanelRightLogin(Presenter presenter) {
		setBackground(Constant.MY_COLOR_WHITE);
		gridSystem = new GridSystem(this);
		
		JLabel lbTitleRegister = new JLabel(Constant.LOGIN_NOW_TEXT);
		lbTitleRegister.setFont(FONT_TEXT_TITLE_REGISTER);
		lbTitleRegister.setAlignmentX(SwingConstants.CENTER);
		add(lbTitleRegister, gridSystem.insertComponent(1, 3, 1, 1));
		
		JLabel lbIconUserRegister = new JLabel(new ImageIcon(createImageIcon(IMG_ICON_USER_REGISTER_MAIN).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
		add(lbIconUserRegister, gridSystem.insertComponent(2, 3, 1, 1));
		
		JLabel lbIconUser = new JLabel(new ImageIcon(createImageIcon(IMG_ICON_USER_REGISTER_SMALL_SIZE).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)), SwingConstants.LEFT);
		lbIconUser.setHorizontalAlignment(SwingConstants.LEFT);
		add(lbIconUser, gridSystem.insertComponent(3, 2, 1, .5));

		txEnterUserName = new JTextArea(1,20);
		txEnterUserName.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.BLACK), "Enter your name:", TitledBorder.LEADING, TitledBorder.TOP, new Font("Calibri", Font.PLAIN, 18), Color.BLACK));
		txEnterUserName.setFont(new Font("Calibri", Font.PLAIN, 16));
		txEnterUserName.setAlignmentX(CENTER_ALIGNMENT);
		add(txEnterUserName, gridSystem.insertComponent(3, 3, 1, .5));
		
		JPanel panelButtoms = new JPanel();
		panelButtoms.setBackground(Constant.MY_COLOR_WHITE);

		JButton btnExit = configBtn(presenter, Action.EXIT, Constant.PATH_BTN_EXIT);
		panelButtoms.add(btnExit);

		JButton btnLogin = configBtn(presenter, Action.VALIDATE_LOGIN_USER, Constant.PATH_BTN_LOGIN);
		panelButtoms.add(btnLogin);
		
		add(panelButtoms, gridSystem.insertComponent(6, 3, 1, .5));
	}
	
	public JButton configBtn(Presenter presenter, Action myAction, String path) {
		JButton btn = new JButton(new ImageIcon(getClass().getResource(path)));
		btn.setOpaque(true);
		btn.setPreferredSize(new Dimension(125, 55));
		btn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btn.setBackground(Constant.MY_COLOR_WHITE);
		btn.setFocusable(false);
		btn.setActionCommand(myAction.toString());
		btn.addActionListener(presenter);
		return btn;
	}
	
	public String getUserName() {
		return txEnterUserName.getText();
	}
	
	public void clearField() {
		txEnterUserName.setText("");
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