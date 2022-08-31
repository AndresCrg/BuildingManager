package views;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import constants.Constant;
import presenters.Presenter;

public class DialogAddInvoice extends JDialog{

	private static final long serialVersionUID = 1L;
	private static final String FORMAT_DATE = "MMMM/yyyy";
	private GridSystem gridSystem;
	private JDateChooser dateChooser;
	private JTextArea txValueInvoice;

	public DialogAddInvoice(Presenter presenter, String action) {
		setSize(Constant.SIZE_DIALOG_WIDTH, Constant.SIZE_DIALOG_HEIGHT);
		getContentPane().setBackground(Constant.MY_COLOR_WHITE);
		setTitle(Constant.ADD_INVOICE_TXT);
		setIconImage(new ImageIcon(getClass().getResource(Constant.PATH_ICON_APP)).getImage());
		setLocationRelativeTo(null);
		
		gridSystem = new GridSystem(this);

		JLabel lbTitle = new JLabel(Constant.ADD_INVOICE_TXT);
		lbTitle.setFont(Constant.FONT_CANDARA_DATA);
		lbTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		add(lbTitle, gridSystem.insertComponent(1, 5, 12, 1));
		
		dateChooser = new JDateChooser();
		dateChooser.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Constant.MY_COLOR_BLACK, 1, true),
				Constant.ENTER_DATE_INVOICE_MESSAGE, TitledBorder.ABOVE_TOP, TitledBorder.TOP,
				Constant.FONT_CAMBRIA_DATA, Constant.MY_COLOR_BLACK));
		add(dateChooser, gridSystem.insertComponent(3, 3, 7, 1));
		
		txValueInvoice = new JTextArea(8, 1);
		txValueInvoice.setFont(Constant.FONT_CAMBRIA_DATA);
		txValueInvoice.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Constant.MY_COLOR_BLACK, 1, true),
				Constant.ENTER_VALUE_INVOICE_MESSAGE, TitledBorder.ABOVE_TOP, TitledBorder.TOP,
				Constant.FONT_CAMBRIA_DATA, Constant.MY_COLOR_BLACK));
		add(txValueInvoice, gridSystem.insertComponent(4, 3, 7, 1));
		
		JButton btnAddInvoice = configBtn(Constant.ADD_TXT, action, presenter);
		add(btnAddInvoice, gridSystem.insertComponent(6, 5, 3, 1));
	}

	private JButton configBtn(String message, String action, Presenter presenter) {
		JButton btnAux = new JButton(message);
		btnAux.setFont(Constant.FONT_CANDARA_DATA);
		btnAux.setBorder(BorderFactory.createLineBorder(Constant.MY_COLOR_BLACK));
		btnAux.setBackground(Constant.MY_ORANGE_COLOR);
		btnAux.setForeground(Constant.MY_COLOR_BLACK);
		btnAux.setActionCommand(action);
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
	
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		String date = sdf.format(dateChooser.getDate());
		System.out.println(date);
		return date;
	}
	
	public int getValueInvoice() {
		return Integer.valueOf(txValueInvoice.getText());
	}
}