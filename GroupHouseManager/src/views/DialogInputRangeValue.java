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

import constants.Constant;
import presenters.Action;
import presenters.Presenter;

public class DialogInputRangeValue extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JTextArea txFirstValue;
	private JTextArea txSecondValue;
	private GridSystem gridSystem;

	public DialogInputRangeValue(Presenter presenter) {
		setSize(550, Constant.SIZE_DIALOG_HEIGHT);
		setTitle(Constant.REPORTS_TXT);
		getContentPane().setBackground(Constant.MY_COLOR_WHITE);
		setIconImage(new ImageIcon(getClass().getResource(Constant.PATH_ICON_APP)).getImage());
		setLocationRelativeTo(null);
		
		gridSystem = new GridSystem(this);
		
		JLabel lbTitle = new JLabel(Constant.MESSAGE_REPORT_TXT, SwingConstants.CENTER);
		lbTitle.setFont(Constant.FONT_CAMBRIA_DATA);
		add(lbTitle, gridSystem.insertComponent(1, 0, 12, 1));
		
		txFirstValue = new JTextArea();
		txFirstValue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
				Constant.MY_ORANGE_COLOR, 2, true), Constant.INPUT_FIRST_VALUE_TXT));
		add(txFirstValue, gridSystem.insertComponent(3, 1, 4, 1));
		
		txSecondValue = new JTextArea();
		txSecondValue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
				Constant.MY_ORANGE_COLOR, 2, true), Constant.INPUT_SECOND_VALUE_TXT));
		add(txSecondValue, gridSystem.insertComponent(3, 6, 4, 1));
		
		JButton btnFilter = configBtn(Constant.FILTER_TXT, Action.FILTER_DEBT, presenter);
		add(btnFilter, gridSystem.insertComponent(6, 4, 3, 1));
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
	
	public int getFirstValue() {
		return Integer.valueOf(txFirstValue.getText());
	}
	
	public int getSecondValue() {
		return Integer.valueOf(txSecondValue.getText());
	}

}
