import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

public class RegisterDriverDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtEmail;
	private JTextField txtName;
	private JTextField txtPassword;
	private JTextField txtAddress;
	private JTextField txtBirthdate;
	
	private boolean userPressedOk = false;

	/**
	 * Create the dialog.
	 */
	public RegisterDriverDialog() {
		setResizable(false);
		setTitle("Registar Condutor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterDriverDialog.class.getResource("/resources/logo.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {30, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblEmail = new JLabel("Email");
			GridBagConstraints gbc_lblEmail = new GridBagConstraints();
			gbc_lblEmail.anchor = GridBagConstraints.EAST;
			gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblEmail.gridx = 2;
			gbc_lblEmail.gridy = 0;
			contentPanel.add(lblEmail, gbc_lblEmail);
		}
		{
			txtEmail = new JTextField();
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
			gbc_txtEmail.anchor = GridBagConstraints.WEST;
			gbc_txtEmail.gridx = 3;
			gbc_txtEmail.gridy = 0;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(30);
		}
		{
			JLabel lblName = new JLabel("Nome");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 2;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			txtName = new JTextField();
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.anchor = GridBagConstraints.WEST;
			gbc_txtName.insets = new Insets(0, 0, 5, 0);
			gbc_txtName.gridx = 3;
			gbc_txtName.gridy = 1;
			contentPanel.add(txtName, gbc_txtName);
			txtName.setColumns(30);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.anchor = GridBagConstraints.EAST;
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 2;
			gbc_lblPassword.gridy = 2;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			txtPassword = new JTextField();
			GridBagConstraints gbc_txtPassword = new GridBagConstraints();
			gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
			gbc_txtPassword.anchor = GridBagConstraints.WEST;
			gbc_txtPassword.gridx = 3;
			gbc_txtPassword.gridy = 2;
			contentPanel.add(txtPassword, gbc_txtPassword);
			txtPassword.setColumns(30);
		}
		{
			JLabel lblAddress = new JLabel("Endere\u00E7o");
			GridBagConstraints gbc_lblAddress = new GridBagConstraints();
			gbc_lblAddress.anchor = GridBagConstraints.EAST;
			gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
			gbc_lblAddress.gridx = 2;
			gbc_lblAddress.gridy = 3;
			contentPanel.add(lblAddress, gbc_lblAddress);
		}
		{
			txtAddress = new JTextField();
			GridBagConstraints gbc_txtAddress = new GridBagConstraints();
			gbc_txtAddress.insets = new Insets(0, 0, 5, 0);
			gbc_txtAddress.anchor = GridBagConstraints.WEST;
			gbc_txtAddress.gridx = 3;
			gbc_txtAddress.gridy = 3;
			contentPanel.add(txtAddress, gbc_txtAddress);
			txtAddress.setColumns(30);
		}
		{
			JLabel lblBirthdate = new JLabel("Nascimento");
			GridBagConstraints gbc_lblBirthdate = new GridBagConstraints();
			gbc_lblBirthdate.anchor = GridBagConstraints.EAST;
			gbc_lblBirthdate.insets = new Insets(0, 0, 5, 5);
			gbc_lblBirthdate.gridx = 2;
			gbc_lblBirthdate.gridy = 4;
			contentPanel.add(lblBirthdate, gbc_lblBirthdate);
		}
		{
			txtBirthdate = new JTextField();
			GridBagConstraints gbc_txtBirthdate = new GridBagConstraints();
			gbc_txtBirthdate.insets = new Insets(0, 0, 5, 0);
			gbc_txtBirthdate.anchor = GridBagConstraints.WEST;
			gbc_txtBirthdate.gridx = 3;
			gbc_txtBirthdate.gridy = 4;
			contentPanel.add(txtBirthdate, gbc_txtBirthdate);
			txtBirthdate.setColumns(30);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						userPressedOk = true;
						
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void customShow() {
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public String[] getResult() {
		
		if (!this.userPressedOk) {
			return null;
		}
		
		String[] result = new String[5];
		
		result[0] = txtEmail.getText();
		result[1] = txtName.getText();
		result[2] = txtPassword.getText();
		result[3] = txtAddress.getText();
		result[4] = txtBirthdate.getText();
		
		for (int i = 0; i < result.length; i++) {

			if (result[i].isEmpty()) {
				return null;
			}
		}
		
		return result;
	}
}
