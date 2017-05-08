import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AssignDriverToVehicleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField txtDriverEmail;
	private JTextField txtVehicleID;
	
	private boolean userPressedOk = false;

	/**
	 * Create the dialog.
	 */
	public AssignDriverToVehicleDialog() {
		setResizable(false);
		setTitle("Associar Condutor a Ve\u00EDculo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AssignDriverToVehicleDialog.class.getResource("/resources/logo.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.rowHeights = new int[] {0};
		gbl_contentPanel.columnWidths = new int[] {30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblDriverEmail = new JLabel("Email do Condutor");
			GridBagConstraints gbc_lblDriverEmail = new GridBagConstraints();
			gbc_lblDriverEmail.anchor = GridBagConstraints.EAST;
			gbc_lblDriverEmail.insets = new Insets(0, 0, 5, 5);
			gbc_lblDriverEmail.gridx = 0;
			gbc_lblDriverEmail.gridy = 0;
			contentPanel.add(lblDriverEmail, gbc_lblDriverEmail);
		}
		{
			txtDriverEmail = new JTextField();
			GridBagConstraints gbc_txtDriverEmail = new GridBagConstraints();
			gbc_txtDriverEmail.insets = new Insets(0, 0, 5, 5);
			gbc_txtDriverEmail.anchor = GridBagConstraints.WEST;
			gbc_txtDriverEmail.gridx = 1;
			gbc_txtDriverEmail.gridy = 0;
			contentPanel.add(txtDriverEmail, gbc_txtDriverEmail);
			txtDriverEmail.setColumns(30);
		}
		{
			JLabel lblVehicleID = new JLabel("ID do Ve\u00EDculo");
			GridBagConstraints gbc_lblVehicleID = new GridBagConstraints();
			gbc_lblVehicleID.anchor = GridBagConstraints.EAST;
			gbc_lblVehicleID.insets = new Insets(0, 0, 5, 5);
			gbc_lblVehicleID.gridx = 0;
			gbc_lblVehicleID.gridy = 1;
			contentPanel.add(lblVehicleID, gbc_lblVehicleID);
		}
		{
			txtVehicleID = new JTextField();
			GridBagConstraints gbc_txtVehicleID = new GridBagConstraints();
			gbc_txtVehicleID.insets = new Insets(0, 0, 5, 5);
			gbc_txtVehicleID.anchor = GridBagConstraints.WEST;
			gbc_txtVehicleID.gridx = 1;
			gbc_txtVehicleID.gridy = 1;
			contentPanel.add(txtVehicleID, gbc_txtVehicleID);
			txtVehicleID.setColumns(30);
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
		
		String[] result = new String[2];
		
		result[0] = txtDriverEmail.getText();
		result[1] = txtVehicleID.getText();

		for (int i = 0; i < result.length; i++) {

			if (result[i].isEmpty()) {
				return null;
			}
		}
		
		return result;
	}
}
