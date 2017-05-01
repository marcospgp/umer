import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resources.CreateVehicleDialog;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.util.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.UIManager;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JList;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JPanel driversContainer;
	private JPanel clientsContainer;
	private JPanel tripsContainer;
	
	// Estes mapas são usados para manter uma referência aos dados apresentados na UI
	// Isto torna possível remover dados da UI a partir de um identificador
	private Map<String, JLabel> drivers = new HashMap<String, JLabel>();
	private Map<String, JLabel> clients = new HashMap<String, JLabel>();

	/**
	 * Iniciar a GUI
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					
					for	(int i = 0; i < 200; i++) {
						frame.insertClient("Cliente " + i, Integer.toString(i));
					}
					
					for	(int i = 0; i < 190; i++) {
						frame.removeClient(Integer.toString(i));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criar a Frame
	 */
	public GUI() {

		/*
		 * Opções da janela 
		 */
		setMinimumSize(new Dimension(800, 600));
		setTitle("Umer");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/resources/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		/*
		 *  Content Pane
		 */
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800, 600));
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setBackground(new Color(96, 125, 139));
		contentPane.setToolTipText("UMER");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		/*
		 * Panel top
		 */
		JPanel pnlTop = new JPanel();
		pnlTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlTop.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.X_AXIS));
		
		/*
		 * Panel top inner
		 */
		JPanel pnlTopInner = new JPanel();
		pnlTopInner.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlTopInner.setBackground(new Color(249, 249, 249));
		pnlTopInner.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		pnlTop.add(pnlTopInner);
		
		// Botões do panel top inner
		
		JButton btnNewButton = new JButton("Iniciar Sess\u00E3o");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showLoginDialog();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pnlTopInner.add(btnNewButton);
		
		JButton registerClient = new JButton("Registar Cliente");
		registerClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showRegisterClientDialog();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pnlTopInner.add(registerClient);
		
		JButton registerDriver = new JButton("Registar Condutor");
		registerDriver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showRegisterDriverDialog();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		pnlTopInner.add(registerDriver);
		
		JButton btnCriarVeculo = new JButton("Criar Ve\u00EDculo");
		pnlTopInner.add(btnCriarVeculo);
		
		/*
		 * Panel left
		 */
		JPanel pnlLeft = new JPanel();
		contentPane.add(pnlLeft, BorderLayout.WEST);
		pnlLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlLeft.setBackground(new Color(0, 0, 0, 0));
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.X_AXIS));
		
		/*
		 * Panel left inner
		 */
		JPanel pnlLeftInner = new JPanel();
		pnlLeft.add(pnlLeftInner);
		pnlLeftInner.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlLeftInner.setBackground(new Color(249, 249, 249));
		
		// Label do painel da esquerda
		JLabel lblPorFavorInicie = new JLabel("Por favor inicie sess\u00E3o para iniciar uma viagem");
		pnlLeftInner.add(lblPorFavorInicie);
		
		/*
		 * Panel right
		 */
		JPanel pnlRight = new JPanel();
		pnlRight.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlRight.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(pnlRight, BorderLayout.EAST);
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.X_AXIS));
		
		/*
		 * Panel right inner
		 */
		JPanel pnlRightInner = new JPanel();
		pnlRightInner.setSize(new Dimension(200, 0));
		pnlRightInner.setMinimumSize(new Dimension(150, 10));
		pnlRightInner.setPreferredSize(new Dimension(220, 10));
		pnlRightInner.setBackground(new Color(249, 249, 249));
		pnlRight.add(pnlRightInner);
		pnlRightInner.setLayout(new BorderLayout(0, 0));
		
		// Scroll pane da direita
		JScrollPane scrollPaneUsers = new JScrollPane();
		pnlRightInner.add(scrollPaneUsers);
		
		// Painel scrollable que contém informação sobre utilizadores registados
		JPanel pnlUsers = new JPanel();
		pnlUsers.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlUsers.setBackground(new Color(249, 249, 249));
		scrollPaneUsers.setViewportView(pnlUsers);
		pnlUsers.setLayout(new BoxLayout(pnlUsers, BoxLayout.Y_AXIS));
		
		// Label "Clientes Registados"
		JLabel lblRegClients = new JLabel("Clientes Registados");
		lblRegClients.setFont(new Font("Arial", Font.BOLD, 14));
		pnlUsers.add(lblRegClients);
		
		// Contentor dos clientes registados
		JPanel pnlClients = new JPanel();
		pnlClients.setBackground(new Color(249, 249, 249));
		pnlClients.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlUsers.add(pnlClients);
		pnlClients.setLayout(new BoxLayout(pnlClients, BoxLayout.Y_AXIS));
		
		this.clientsContainer = pnlClients;
		
		// Label "Condutores Registados"
		JLabel lblRegDrivers = new JLabel("Condutores Registados");
		lblRegDrivers.setFont(new Font("Arial", Font.BOLD, 14));
		pnlUsers.add(lblRegDrivers);
		
		// Contentor dos condutores registados
		JPanel pnlDrivers = new JPanel();
		pnlDrivers.setBackground(new Color(249, 249, 249));
		pnlDrivers.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlUsers.add(pnlDrivers);
		pnlDrivers.setLayout(new BoxLayout(pnlDrivers, BoxLayout.Y_AXIS));
		
		this.driversContainer = pnlDrivers;
		
		/*
		 * Panel center
		 */
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlCenter.setBackground(new Color(0, 0, 0, 0));
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout(0, 0));
		
		/*
		 * Panel center inner
		 */
		JPanel pnlCenterInner = new JPanel();
		pnlCenterInner.setPreferredSize(new Dimension(200, 10));
		pnlCenterInner.setMinimumSize(new Dimension(150, 10));
		pnlCenterInner.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlCenterInner.setBackground(new Color(249, 249, 249));
		pnlCenter.add(pnlCenterInner);
		pnlCenterInner.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTrips = new JScrollPane();
		pnlCenterInner.add(scrollPaneTrips);
		
		JPanel pnlTripsContainer = new JPanel();
		scrollPaneTrips.setViewportView(pnlTripsContainer);
		pnlTripsContainer.setLayout(new BoxLayout(pnlTripsContainer, BoxLayout.Y_AXIS));
		
		JLabel lblTrips = new JLabel("Hist\u00F3rico de Viagens");
		lblTrips.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrips.setFont(new Font("Arial", Font.BOLD, 14));
		pnlTripsContainer.add(lblTrips);
		
		JPanel pnlTrips = new JPanel();
		pnlTripsContainer.add(pnlTrips);
	}
	
	private void insertDriver(String driverString, String driverId) {
		
		if (this.drivers.containsKey(driverId)) {
			return;
		}
		
		JLabel newLabel = new JLabel(driverString);
		this.driversContainer.add(newLabel);
		this.driversContainer.revalidate();
		this.driversContainer.repaint();
		
		// Guardar label no mapa de forma a permitir apagá-la através do seu identificador
		this.drivers.put(driverId, newLabel);
	}
	
	private void insertClient(String clientString, String clientId) {
		
		if (this.clients.containsKey(clientId)) {
			return;
		}
		
		JLabel newLabel = new JLabel(clientString);
		this.clientsContainer.add(newLabel);
		this.clientsContainer.revalidate();
		this.clientsContainer.repaint();
		
		// Guardar label no mapa de forma a permitir apagá-la através do seu identificador
		this.clients.put(clientId, newLabel);
	}
	
	private void removeDriver(String driverId) {
		
		if(!this.drivers.containsKey(driverId)) {
			return;
		}
		
		this.driversContainer.remove(this.drivers.get(driverId));
		this.driversContainer.revalidate();
		this.driversContainer.repaint();
		
		this.drivers.remove(driverId);
	}
	
	private void removeClient(String clientId) {
		
		if(!this.clients.containsKey(clientId)) {
			return;
		}
		
		this.clientsContainer.remove(this.clients.get(clientId));
		this.clientsContainer.revalidate();
		this.clientsContainer.repaint();
		
		this.clients.remove(clientId);
	}
	
	private void showLoginDialog() {

		LoginDialog dialog = new LoginDialog();
		dialog.customShow();
		
		String[] result = dialog.getResult(); // result[0] = email, result[1] = password
		
		if (result == null) {
			// Não se obteve um resultado válido
			return;
		}
		
		// TODO - Enviar informação de login para a classe Umer
		insertDriver("wo " + result[0], "wo " + result[0]);
		insertDriver("la " + result[1], "la " + result[1]);
	}
	
	private void showRegisterDriverDialog() {
		RegisterDriverDialog dialog = new RegisterDriverDialog();
		dialog.customShow();
		
		String[] result = dialog.getResult(); // [email, name, password, address, birthdate]
		
		if (result == null) {
			// Não se obteve um resultado válido
			return;
		}
		
		// TODO - Enviar informação para a classe Umer
	}
	
	private void showRegisterClientDialog() {
		RegisterClientDialog dialog = new RegisterClientDialog();
		dialog.customShow();
		
		String[] result = dialog.getResult(); // [email, name, password, address, birthdate, posX, posY]
		
		if (result == null) {
			// Não se obteve um resultado válido
			return;
		}
		
		try {
			double posX = Double.parseDouble(result[5]);
			double posY = Double.parseDouble(result[6]);
		} catch(NumberFormatException ex) {
			// O utilizador não inseriu uma posição válida
			return;
		}
		
		// TODO - Enviar informação para a classe Umer
	}
	
	private void showCreateVehicleDialog() {
		CreateVehicleDialog dialog = new CreateVehicleDialog();
		dialog.customShow();
		
		String[] result = dialog.getResult(); // [identifier, type, posX, posY]
		
		if (result == null) {
			// Não se obteve um resultado válido
			return;
		}
		
		try {
			double posX = Double.parseDouble(result[5]);
			double posY = Double.parseDouble(result[6]);
		} catch(NumberFormatException ex) {
			// O utilizador não inseriu uma posição válida
			return;
		}
		
		// TODO - Enviar informação do carro
	}
	
	public void updateTrips(String[] trips) {
		
		clearTrips();
		
		for(int i = 0; i < trips.length; i++) {
			
			insertTrip();
		}
	}
	
	private void clearTrips() {
		
	}
	
	private void insertTrip(String trip) {
		
	}
}
