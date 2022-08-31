package views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import constants.Constant;
import presenters.Action;
import presenters.Presenter;

public class DialogAddUser extends JDialog{

	private static final long serialVersionUID = 1L;
	private GridSystem gridSystem;
	private JTextArea txUser;
	
	public DialogAddUser(Presenter presenter) {
		setSize(Constant.SIZE_DIALOG_WIDTH, Constant.SIZE_DIALOG_HEIGHT);
		setTitle(Constant.ADD_USER_TXT);
		setIconImage(new ImageIcon(getClass().getResource(Constant.PATH_ICON_APP)).getImage());
		setLocationRelativeTo(null);
		
		gridSystem = new GridSystem(this);
		
		ImageIcon image = new ImageIcon(((new ImageIcon(getClass().
				getResource(Constant.PATH_ICON_ADD_USER))).getImage()).
				getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH));
		JLabel lbTitle = new JLabel(Constant.ADD_USER_TXT, image, SwingConstants.CENTER);
		lbTitle.setFont(Constant.FONT_CANDARA_DATA);
		add(lbTitle, gridSystem.insertComponent(1, 1, 11, 1));
		
		txUser = new JTextArea();
		txUser.setFont(Constant.FONT_CAMBRIA_DATA);
		txUser.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Constant.MY_COLOR_BLACK, 1, true),
				Constant.ENTER_YOUR_USER_NAME_MESSAGE, TitledBorder.ABOVE_TOP, TitledBorder.TOP,
				Constant.FONT_CAMBRIA_DATA, Constant.MY_COLOR_BLACK));
		add(txUser, gridSystem.insertComponent(3, 2, 8, 1));
		
		JButton btnAddInvoice = configBtn(Constant.ADD_TXT, Action.ADD_USER, presenter);
		add(btnAddInvoice, gridSystem.insertComponent(6, 2, 3, 1));
		
		JButton btnCancel = configBtn(Constant.CANCEL_TXT, Action.CANCEL, presenter);
		add(btnCancel, gridSystem.insertComponent(6, 8, 2, 1));
	}
	
	private JButton configBtn(String message, Action action, Presenter presenter) {
		JButton btnAux = new JButton(message);
		btnAux.setFont(Constant.FONT_CANDARA_DATA);
		btnAux.setBorder(BorderFactory.createLineBorder(Constant.MY_COLOR_BLACK));
		btnAux.setBackground(Constant.MY_ORANGE_COLOR);
		btnAux.setForeground(Constant.MY_COLOR_BLACK);
		btnAux.setActionCommand(action.toString());
		btnAux.addActionListener(presenter);
		btnAux.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAux.setBackground(Constant.MY_ORANGE_COLOR);
				btnAux.setForeground(Constant.MY_COLOR_BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAux.setBackground(Constant.MY_COLOR_GREEN);
				btnAux.setForeground(Color.WHITE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		return btnAux;
	}
	
	public String getUserName() {
		return txUser.getText();
	}

	public void clearField() {
		txUser.setText("");
	}
}