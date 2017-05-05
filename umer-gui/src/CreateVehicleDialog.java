

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
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;

public class CreateVehicleDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdentifier;
	private JTextField txtPosX;
	private JTextField txtPosY;
	private JTextField txtType;
	private JCheckBox hasWaitingList;
	
	private boolean userPressedOk = false;

	/**
	 * Create the dialog.
	 */
	public CreateVehicleDialog() {
		setResizable(false);
		setTitle("Registar Condutor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateVehicleDialog.class.getResource("/resources/logo.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {30, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblIdentifier = new JLabel("Identificador");
			GridBagConstraints gbc_lblIdentifier = new GridBagConstraints();
			gbc_lblIdentifier.anchor = GridBagConstraints.EAST;
			gbc_lblIdentifier.insets = new Insets(0, 0, 5, 5);
			gbc_lblIdentifier.gridx = 2;
			gbc_lblIdentifier.gridy = 0;
			contentPanel.add(lblIdentifier, gbc_lblIdentifier);
		}
		{
			txtIdentifier = new JTextField();
			GridBagConstraints gbc_txtIdentifier = new GridBagConstraints();
			gbc_txtIdentifier.insets = new Insets(0, 0, 5, 0);
			gbc_txtIdentifier.anchor = GridBagConstraints.WEST;
			gbc_txtIdentifier.gridx = 3;
			gbc_txtIdentifier.gridy = 0;
			contentPanel.add(txtIdentifier, gbc_txtIdentifier);
			txtIdentifier.setColumns(30);
		}
		{
			JLabel lblType = new JLabel("Tipo");
			GridBagConstraints gbc_lblType = new GridBagConstraints();
			gbc_lblType.anchor = GridBagConstraints.EAST;
			gbc_lblType.insets = new Insets(0, 0, 5, 5);
			gbc_lblType.gridx = 2;
			gbc_lblType.gridy = 1;
			contentPanel.add(lblType, gbc_lblType);
		}
		{
			txtType = new JTextField();
			txtType.setColumns(30);
			GridBagConstraints gbc_txtType = new GridBagConstraints();
			gbc_txtType.anchor = GridBagConstraints.WEST;
			gbc_txtType.insets = new Insets(0, 0, 5, 0);
			gbc_txtType.gridx = 3;
			gbc_txtType.gridy = 1;
			contentPanel.add(txtType, gbc_txtType);
		}
		{
			JLabel lblPosition = new JLabel("Posi\u00E7\u00E3o");
			GridBagConstraints gbc_lblPosition = new GridBagConstraints();
			gbc_lblPosition.anchor = GridBagConstraints.EAST;
			gbc_lblPosition.insets = new Insets(0, 0, 5, 5);
			gbc_lblPosition.gridx = 2;
			gbc_lblPosition.gridy = 2;
			contentPanel.add(lblPosition, gbc_lblPosition);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.anchor = GridBagConstraints.WEST;
			gbc_panel.fill = GridBagConstraints.VERTICAL;
			gbc_panel.gridx = 3;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel label = new JLabel("X");
				panel.add(label);
			}
			{
				txtPosX = new JTextField();
				txtPosX.setColumns(10);
				panel.add(txtPosX);
			}
			{
				JLabel label = new JLabel("Y");
				panel.add(label);
			}
			{
				txtPosY = new JTextField();
				txtPosY.setColumns(10);
				panel.add(txtPosY);
			}
		}
		{
			JLabel lblListaDeEspera = new JLabel("Lista de espera");
			GridBagConstraints gbc_lblListaDeEspera = new GridBagConstraints();
			gbc_lblListaDeEspera.insets = new Insets(0, 0, 0, 5);
			gbc_lblListaDeEspera.gridx = 2;
			gbc_lblListaDeEspera.gridy = 3;
			contentPanel.add(lblListaDeEspera, gbc_lblListaDeEspera);
		}
		{
			hasWaitingList = new JCheckBox("");
			GridBagConstraints gbc_chckbxHasWaitingList = new GridBagConstraints();
			gbc_chckbxHasWaitingList.anchor = GridBagConstraints.WEST;
			gbc_chckbxHasWaitingList.gridx = 3;
			gbc_chckbxHasWaitingList.gridy = 3;
			contentPanel.add(hasWaitingList, gbc_chckbxHasWaitingList);
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
		
		result[0] = txtIdentifier.getText();
		result[1] = txtType.getText().replaceAll("[^A-Za-z0-9]", "").toUpperCase(); // Only alphanumeric and then convert to uppercase
		result[2] = txtPosX.getText();
		result[3] = txtPosY.getText();
		result[4] = String.valueOf(hasWaitingList.isSelected());
		
		for (int i = 0; i < result.length; i++) {

			if (result[i].isEmpty()) {
				return null;
			}
		}
		
		return result;
	}
}
