import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

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
import javax.swing.JOptionPane;

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
import javax.swing.JTextField;

public class GUI extends JFrame {

    private JPanel contentPane;
    private JPanel driversContainer;
    private JPanel clientsContainer;
    private JPanel vehiclesContainer;
    private JPanel tripsContainer;
    private JPanel loggedInClientContainer;
    private JPanel loggedInDriverContainer;
    private JPanel loggedOutLeftPanelContainer;
    private JPanel pendingRatingsContainer;
    private JButton logoutButton;
    private JButton loginButton;
    private JLabel loggedInClientInfo;
    private JLabel loggedInDriverInfo;
    private JLabel emptyRatingsNotifier;
    private JLabel dateLabel;

    private boolean isAvailable; // Se o condutor logado atualmente está disponível
    private JLabel driverAvailableLabel;
    private JLabel driverUnavailableLabel;

    // Formulário de iniciar viagem
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtVehicleID;
    private JTextField vehicleFinancesInput;

    // Estes mapas são usados para manter uma referência aos dados apresentados na UI
    // Isto torna possível remover dados da UI a partir de um identificador
    private Map<String, JLabel> drivers = new HashMap<String, JLabel>();
    private Map<String, JLabel> clients = new HashMap<String, JLabel>();

    // Guarda os text fields dos ratings pendentes
    private JTextField[] ratingInputs;
    private JTextField txtFinancesVehicleId;

    /**
     * Criar a Frame
     */
    public GUI() {

        /*
         * Op��es da janela
         */
        setMinimumSize(new Dimension(900, 600));
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
        pnlTopInner.setPreferredSize(new Dimension(10, 80));
        pnlTopInner.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTopInner.setBackground(new Color(249, 249, 249));
        pnlTop.add(pnlTopInner);

        // Bot�es do panel top inner

        JButton btnLogin = new JButton("Iniciar Sess\u00E3o");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showLoginDialog();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        pnlTopInner.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        pnlTopInner.add(btnLogin);

        this.loginButton = btnLogin;

        JButton btnLogout = new JButton("Terminar Sess\u00E3o");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        pnlTopInner.add(btnLogout);

        this.logoutButton = btnLogout;

        JButton btnSave = new JButton("Guardar");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAppState();
            }
        });
        pnlTopInner.add(btnSave);


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
        btnCriarVeculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCreateVehicleDialog();
            }
        });
        pnlTopInner.add(btnCriarVeculo);

        JButton btnAssociateDriver = new JButton("Associar Condutor a Ve\u00EDculo");
        btnAssociateDriver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAssignDriverToVehicleDialog();
            }
        });
        pnlTopInner.add(btnAssociateDriver);

        JButton btn10MostSpending = new JButton("10 clientes que mais gastam");
        btn10MostSpending.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                show10MostSpendingClients();
            }
        });

        JButton btn5WorstDrivers = new JButton("5 piores condutores");
        btn5WorstDrivers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                show5LessReliableDrivers();
            }
        });
        pnlTopInner.add(btn5WorstDrivers);
        pnlTopInner.add(btn10MostSpending);

        JPanel pnlFinances = new JPanel();
        pnlFinances.setOpaque(false);
        pnlTopInner.add(pnlFinances);

        txtFinancesVehicleId = new JTextField();
        pnlFinances.add(txtFinancesVehicleId);
        txtFinancesVehicleId.setText("ID do ve\u00EDculo");
        txtFinancesVehicleId.setColumns(10);

        this.vehicleFinancesInput = txtFinancesVehicleId;

        JButton btnSeeFinances = new JButton("Ver finan\u00E7as");
        pnlFinances.add(btnSeeFinances);
        btnSeeFinances.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showVehicleFinances();
            }
        });

        JPanel pnlCurrentTime = new JPanel();
        pnlCurrentTime.setOpaque(false);
        pnlTopInner.add(pnlCurrentTime);

        JLabel lblCurrentDate = new JLabel("Data atual:");
        pnlCurrentTime.add(lblCurrentDate);
        lblCurrentDate.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblCurrentDateValue = new JLabel("");
        pnlCurrentTime.add(lblCurrentDateValue);
        lblCurrentDateValue.setBorder(new EmptyBorder(0, 5, 0, 0));

        this.dateLabel = lblCurrentDateValue;

        JButton btnFastforward = new JButton("+1 hora");
        pnlCurrentTime.add(btnFastforward);
        btnFastforward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fastForward((double) 3600);
            }
        });

        /*
         * Panel left
         */
        JPanel pnlLeft = new JPanel();
        contentPane.add(pnlLeft, BorderLayout.WEST);
        pnlLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlLeft.setBackground(new Color(0, 0, 0, 0));

        /*
         * Panel left inner
         */
        pnlLeft.setLayout(new BorderLayout(0, 0));
        JPanel pnlLeftInner = new JPanel();
        pnlLeftInner.setAlignmentY(Component.TOP_ALIGNMENT);
        pnlLeft.add(pnlLeftInner, BorderLayout.CENTER);
        pnlLeftInner.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlLeftInner.setBackground(new Color(249, 249, 249));
        pnlLeftInner.setLayout(new BoxLayout(pnlLeftInner, BoxLayout.Y_AXIS));

        JPanel pnlLoggedOutStartTripContainer = new JPanel();
        pnlLoggedOutStartTripContainer.setOpaque(false);
        pnlLeftInner.add(pnlLoggedOutStartTripContainer);

        this.loggedOutLeftPanelContainer = pnlLoggedOutStartTripContainer;

        // Label do painel da esquerda
        JLabel lblPorFavorInicie = new JLabel("Por favor inicie sess\u00E3o para iniciar uma viagem");
        pnlLoggedOutStartTripContainer.add(lblPorFavorInicie);

        JPanel pnlLoggedInClientContainer = new JPanel();
        pnlLoggedInClientContainer.setOpaque(false);
        pnlLeftInner.add(pnlLoggedInClientContainer);
        pnlLoggedInClientContainer.setLayout(new BoxLayout(pnlLoggedInClientContainer, BoxLayout.Y_AXIS));

        this.loggedInClientContainer = pnlLoggedInClientContainer;

        JLabel lblUtilizador = new JLabel("Utilizador");
        lblUtilizador.setFont(new Font("Arial", Font.BOLD, 14));
        lblUtilizador.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInClientContainer.add(lblUtilizador);

        JLabel lblLoggedInClientInfo = new JLabel("Informa\u00E7\u00E3o");
        lblLoggedInClientInfo.setBorder(new EmptyBorder(10, 0, 10, 0));
        lblLoggedInClientInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInClientContainer.add(lblLoggedInClientInfo);

        this.loggedInClientInfo = lblLoggedInClientInfo;

        JButton btnGetTripHistory_01 = new JButton("Registo de viagens");
        btnGetTripHistory_01.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTripHistory();
            }
        });
        btnGetTripHistory_01.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInClientContainer.add(btnGetTripHistory_01);

        JLabel lblStartTrip = new JLabel("Nova Viagem");
        lblStartTrip.setAlignmentY(Component.TOP_ALIGNMENT);
        lblStartTrip.setBorder(new EmptyBorder(25, 0, 10, 0));
        lblStartTrip.setFont(new Font("Arial", Font.BOLD, 14));
        lblStartTrip.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInClientContainer.add(lblStartTrip);

        JPanel pnlStartTripForm = new JPanel();
        pnlStartTripForm.setAlignmentY(Component.TOP_ALIGNMENT);
        pnlStartTripForm.setMaximumSize(new Dimension(32767, 70));
        pnlLoggedInClientContainer.add(pnlStartTripForm);
        pnlStartTripForm.setOpaque(false);
        GridBagLayout gbl_pnlStartTripForm = new GridBagLayout();
        gbl_pnlStartTripForm.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl_pnlStartTripForm.rowHeights = new int[] {0, 0};
        gbl_pnlStartTripForm.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_pnlStartTripForm.rowWeights = new double[]{0.0, 0.0};
        pnlStartTripForm.setLayout(gbl_pnlStartTripForm);

        JLabel lblVehicleIdentifier = new JLabel("Identificador do ve\u00EDculo");
        GridBagConstraints gbc_lblVehicleIdentifier = new GridBagConstraints();
        gbc_lblVehicleIdentifier.anchor = GridBagConstraints.EAST;
        gbc_lblVehicleIdentifier.insets = new Insets(0, 0, 5, 5);
        gbc_lblVehicleIdentifier.gridx = 0;
        gbc_lblVehicleIdentifier.gridy = 0;
        pnlStartTripForm.add(lblVehicleIdentifier, gbc_lblVehicleIdentifier);

        txtVehicleID = new JTextField();
        GridBagConstraints gbc_txtVehicleID = new GridBagConstraints();
        gbc_txtVehicleID.insets = new Insets(0, 0, 5, 5);
        gbc_txtVehicleID.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtVehicleID.gridx = 2;
        gbc_txtVehicleID.gridy = 0;
        pnlStartTripForm.add(txtVehicleID, gbc_txtVehicleID);
        txtVehicleID.setColumns(5);

        JLabel lblDestino = new JLabel("Destino");
        GridBagConstraints gbc_lblDestino = new GridBagConstraints();
        gbc_lblDestino.anchor = GridBagConstraints.EAST;
        gbc_lblDestino.insets = new Insets(0, 0, 5, 5);
        gbc_lblDestino.gridx = 0;
        gbc_lblDestino.gridy = 1;
        pnlStartTripForm.add(lblDestino, gbc_lblDestino);

        JLabel lblX = new JLabel("X");
        GridBagConstraints gbc_lblX = new GridBagConstraints();
        gbc_lblX.anchor = GridBagConstraints.EAST;
        gbc_lblX.insets = new Insets(0, 0, 5, 5);
        gbc_lblX.gridx = 1;
        gbc_lblX.gridy = 1;
        pnlStartTripForm.add(lblX, gbc_lblX);

        txtX = new JTextField();
        GridBagConstraints gbc_txtX = new GridBagConstraints();
        gbc_txtX.anchor = GridBagConstraints.WEST;
        gbc_txtX.insets = new Insets(0, 0, 5, 5);
        gbc_txtX.gridx = 2;
        gbc_txtX.gridy = 1;
        pnlStartTripForm.add(txtX, gbc_txtX);
        txtX.setColumns(5);

        JLabel lblY = new JLabel("Y");
        GridBagConstraints gbc_lblY = new GridBagConstraints();
        gbc_lblY.anchor = GridBagConstraints.EAST;
        gbc_lblY.insets = new Insets(0, 0, 5, 5);
        gbc_lblY.gridx = 3;
        gbc_lblY.gridy = 1;
        pnlStartTripForm.add(lblY, gbc_lblY);

        txtY = new JTextField();
        GridBagConstraints gbc_txtY = new GridBagConstraints();
        gbc_txtY.insets = new Insets(0, 0, 5, 0);
        gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtY.gridx = 4;
        gbc_txtY.gridy = 1;
        pnlStartTripForm.add(txtY, gbc_txtY);
        txtY.setColumns(5);

        JButton btnStartTrip = new JButton("Iniciar");
        btnStartTrip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTrip(txtVehicleID.getText(), txtX.getText(), txtY.getText());
            }
        });
        btnStartTrip.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInClientContainer.add(btnStartTrip);

        JLabel lblPendingRatings = new JLabel("Avalia\u00E7\u00F5es Pendentes");
        lblPendingRatings.setBorder(new EmptyBorder(25, 0, 10, 0));
        lblPendingRatings.setFont(new Font("Arial", Font.BOLD, 14));
        lblPendingRatings.setAlignmentX(0.5f);
        pnlLoggedInClientContainer.add(lblPendingRatings);

        JPanel pnlPendingRatings = new JPanel();
        pnlPendingRatings.setOpaque(false);
        pnlLoggedInClientContainer.add(pnlPendingRatings);
        pnlPendingRatings.setLayout(new BoxLayout(pnlPendingRatings, BoxLayout.Y_AXIS));

        this.pendingRatingsContainer = pnlPendingRatings;

        JLabel lblEmptyNotifier = new JLabel("(nada)");
        lblEmptyNotifier.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlPendingRatings.add(lblEmptyNotifier);

        this.emptyRatingsNotifier = lblEmptyNotifier;

        JPanel pnlLoggedInDriverContainer = new JPanel();
        pnlLoggedInDriverContainer.setOpaque(false);
        pnlLeftInner.add(pnlLoggedInDriverContainer);
        pnlLoggedInDriverContainer.setLayout(new BoxLayout(pnlLoggedInDriverContainer, BoxLayout.Y_AXIS));

        this.loggedInDriverContainer = pnlLoggedInDriverContainer;

        JLabel lblCondutor_1 = new JLabel("Condutor");
        lblCondutor_1.setFont(new Font("Arial", Font.BOLD, 14));
        lblCondutor_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInDriverContainer.add(lblCondutor_1);

        JLabel lblLoggedInDriverInfo = new JLabel("Informa\u00E7\u00E3o");
        lblLoggedInDriverInfo.setBorder(new EmptyBorder(10, 0, 10, 0));
        lblLoggedInDriverInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInDriverContainer.add(lblLoggedInDriverInfo);

        this.loggedInDriverInfo = lblLoggedInDriverInfo;

        JButton btnGetTripHistory_02 = new JButton("Registo de viagens");
        btnGetTripHistory_02.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showTripHistory();
            }
        });
        btnGetTripHistory_02.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInDriverContainer.add(btnGetTripHistory_02);

        JPanel pnlLoggedInDriverAvailableContainer = new JPanel();
        pnlLoggedInDriverContainer.add(pnlLoggedInDriverAvailableContainer);
        pnlLoggedInDriverAvailableContainer.setBorder(new EmptyBorder(25, 0, 0, 0));
        pnlLoggedInDriverAvailableContainer.setOpaque(false);
        pnlLoggedInDriverAvailableContainer.setMaximumSize(new Dimension(32767, 50));
        pnlLoggedInDriverAvailableContainer.setLayout(new BoxLayout(pnlLoggedInDriverAvailableContainer, BoxLayout.X_AXIS));

        JLabel lblDisponivel = new JLabel("Dispon\u00EDvel");
        lblDisponivel.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblDisponivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlLoggedInDriverAvailableContainer.add(lblDisponivel);

        this.driverAvailableLabel = lblDisponivel;

        JLabel lblNaoDisponivel = new JLabel("N\u00E3o dispon\u00EDvel");
        lblNaoDisponivel.setBorder(new EmptyBorder(0, 0, 0, 5));
        pnlLoggedInDriverAvailableContainer.add(lblNaoDisponivel);

        this.driverUnavailableLabel = lblNaoDisponivel;

        JButton btnToggleAvailability = new JButton("Alterar disponibilidade");
        btnToggleAvailability.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnToggleAvailability.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                toggleAvailability();
            }
        });
        pnlLoggedInDriverAvailableContainer.add(btnToggleAvailability);

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
        pnlRightInner.setPreferredSize(new Dimension(300, 10));
        pnlRightInner.setBackground(new Color(249, 249, 249));
        pnlRight.add(pnlRightInner);
        pnlRightInner.setLayout(new BorderLayout(0, 0));

        // Scroll pane da direita
        JScrollPane scrollPaneUsers = new JScrollPane();
        pnlRightInner.add(scrollPaneUsers);

        // Painel scrollable que cont�m informa��o sobre utilizadores registados
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
        JPanel pnlRegClients = new JPanel();
        pnlRegClients.setBackground(new Color(249, 249, 249));
        pnlRegClients.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlUsers.add(pnlRegClients);
        pnlRegClients.setLayout(new BoxLayout(pnlRegClients, BoxLayout.Y_AXIS));

        this.clientsContainer = pnlRegClients;

        // Label "Condutores Registados"
        JLabel lblRegDrivers = new JLabel("Condutores Registados");
        lblRegDrivers.setFont(new Font("Arial", Font.BOLD, 14));
        pnlUsers.add(lblRegDrivers);

        // Contentor dos condutores registados
        JPanel pnlRegDrivers = new JPanel();
        pnlRegDrivers.setBackground(new Color(249, 249, 249));
        pnlRegDrivers.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlUsers.add(pnlRegDrivers);
        pnlRegDrivers.setLayout(new BoxLayout(pnlRegDrivers, BoxLayout.Y_AXIS));

        this.driversContainer = pnlRegDrivers;

        JLabel lblRegVehicles = new JLabel("Ve\u00EDculos Registados");
        lblRegVehicles.setFont(new Font("Arial", Font.BOLD, 14));
        pnlUsers.add(lblRegVehicles);

        JPanel pnlRegVehicles = new JPanel();
        pnlRegVehicles.setBackground(new Color(249, 249, 249));
        pnlRegVehicles.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlUsers.add(pnlRegVehicles);
        pnlRegVehicles.setLayout(new BoxLayout(pnlRegVehicles, BoxLayout.Y_AXIS));

        this.vehiclesContainer = pnlRegVehicles;

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
        pnlCenterInner.setBorder(new EmptyBorder(10, 0, 0, 0));
        pnlCenterInner.setPreferredSize(new Dimension(200, 10));
        pnlCenterInner.setMinimumSize(new Dimension(150, 10));
        pnlCenterInner.setBackground(new Color(249, 249, 249));
        pnlCenter.add(pnlCenterInner);
        pnlCenterInner.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPaneTrips = new JScrollPane();
        scrollPaneTrips.setBorder(null);
        pnlCenterInner.add(scrollPaneTrips);

        JPanel pnlTripsContainer = new JPanel();
        pnlTripsContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlTripsContainer.setBackground(new Color(249, 249, 249));
        scrollPaneTrips.setViewportView(pnlTripsContainer);
        pnlTripsContainer.setLayout(new BoxLayout(pnlTripsContainer, BoxLayout.Y_AXIS));

        JPanel pnlTrips = new JPanel();
        pnlTrips.setAlignmentX(Component.LEFT_ALIGNMENT);
        pnlTrips.setOpaque(false);
        pnlTripsContainer.add(pnlTrips);
        pnlTrips.setLayout(new BoxLayout(pnlTrips, BoxLayout.Y_AXIS));

        this.tripsContainer = pnlTrips;

        JLabel lblTrips = new JLabel("Hist\u00F3rico de Viagens");
        pnlCenterInner.add(lblTrips, BorderLayout.NORTH);
        lblTrips.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTrips.setHorizontalAlignment(SwingConstants.CENTER);
        lblTrips.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void insertDriver(String driver) {

        JLabel newLabel = new JLabel(driver);
        newLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
        newLabel.setBorder(new EmptyBorder(0, 0, 3, 0));
        this.driversContainer.add(newLabel);
        this.driversContainer.revalidate();
        this.driversContainer.repaint();
    }

    private void insertClient(String client) {

        JLabel newLabel = new JLabel(client);
        newLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
        newLabel.setBorder(new EmptyBorder(0, 0, 3, 0));
        this.clientsContainer.add(newLabel);
        this.clientsContainer.revalidate();
        this.clientsContainer.repaint();
    }

    private void insertVehicle(String vehicle) {

        JLabel newLabel = new JLabel(vehicle);
        newLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
        newLabel.setBorder(new EmptyBorder(0, 0, 3, 0));
        this.vehiclesContainer.add(newLabel);
        this.vehiclesContainer.revalidate();
        this.vehiclesContainer.repaint();
    }

    private void insertTrip(String trip) {

        JLabel newLabel = new JLabel(trip);
        newLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
        newLabel.setBorder(new EmptyBorder(0, 0, 3, 0));
        this.tripsContainer.add(newLabel);
        this.tripsContainer.revalidate();
        this.tripsContainer.repaint();
    }

    private void saveAppState() {
        Umer.saveAppState();
    }

    private void clearDrivers() {
        this.driversContainer.removeAll();
    }

    private void clearClients() {
        this.clientsContainer.removeAll();
    }

    private void clearVehicles() {
        this.vehiclesContainer.removeAll();
    }

    private void clearTrips() {
        this.tripsContainer.removeAll();
    }

    private void showLoginDialog() {

        LoginDialog dialog = new LoginDialog();
        dialog.customShow();

        String[] result = dialog.getResult(); // result[0] = email, result[1] = password

        if (result == null) {
            // N�o se obteve um resultado v�lido
            return;
        }

        // Classe Umer retorna se o utilizador � condutor ou cliente
        Boolean isDriver = Umer.login(result[0], result[1]);

        if (isDriver == null) {
            // A tentativa de login falhou
            return;
        }

        this.loginButton.setVisible(false);
        this.logoutButton.setVisible(true);
        this.loggedOutLeftPanelContainer.setVisible(false);

        if (isDriver) {
            this.loggedInClientContainer.setVisible(false);
            this.loggedInDriverContainer.setVisible(true);
        } else {
            this.loggedInDriverContainer.setVisible(false);
            this.loggedInClientContainer.setVisible(true);
        }
    }

    private void showRegisterDriverDialog() {
        RegisterDriverDialog dialog = new RegisterDriverDialog();
        dialog.customShow();

        String[] result = dialog.getResult(); // [email, name, password, address, birthdate]

        if (result == null) {
            // N�o se obteve um resultado v�lido
            return;
        }

        Umer.registerDriver(result[0], result[1], result[2], result[3], result[4]);
    }

    private void showRegisterClientDialog() {
        RegisterClientDialog dialog = new RegisterClientDialog();
        dialog.customShow();

        String[] result = dialog.getResult(); // [email, name, password, address, birthdate, posX, posY]

        if (result == null) {
            // N�o se obteve um resultado v�lido
            return;
        }

        double posX;
        double posY;

        try {
            posX = Double.parseDouble(result[5]);
            posY = Double.parseDouble(result[6]);
        } catch(NumberFormatException ex) {
            // O utilizador n�o inseriu uma posi��o v�lida
            return;
        }

        Umer.registerClient(result[0], result[1], result[2], result[3], result[4], posX, posY);
    }

    private void showCreateVehicleDialog() {
        CreateVehicleDialog dialog = new CreateVehicleDialog();
        dialog.customShow();

        String[] result = dialog.getResult(); // [identifier, type, posX, posY, hasWaitingList]

        if (result == null) {
            // N�o se obteve um resultado v�lido
            return;
        }

        double posX;
        double posY;

        try {
            posX = Double.parseDouble(result[2]);
            posY = Double.parseDouble(result[3]);
        } catch(NumberFormatException ex) {
            // O utilizador n�o inseriu uma posi��o v�lida
            return;
        }

        boolean hasWaitingList = Boolean.parseBoolean(result[4]);

        Umer.createVehicle(posX, posY, result[0], result[1], hasWaitingList);
    }


    private void showAssignDriverToVehicleDialog() {
        AssignDriverToVehicleDialog dialog = new AssignDriverToVehicleDialog();
        dialog.customShow();

        String[] result = dialog.getResult(); // [email do condutor, id do ve�culo]

        if (result == null) {
            return;
        }

        Umer.assignDriverToVehicle(result[0], result[1]);
    }

    private void logout() {

        this.loggedInClientContainer.setVisible(false);
        this.loggedInDriverContainer.setVisible(false);
        this.logoutButton.setVisible(false);
        this.loginButton.setVisible(true);
        this.loggedOutLeftPanelContainer.setVisible(true);

        Umer.logout();
    }

    private void fastForward(Double seconds) {
        Umer.fastForward(seconds);
    }

    private void startTrip(String vehicleId, String destX, String destY) {

        double x;
        double y;

        try {
            x = Double.parseDouble(destX);
            y = Double.parseDouble(destY);
        } catch(NumberFormatException ex) {
            // O utilizador n�o inseriu um destino v�lido
            return;
        }

        Umer.startTrip(vehicleId, x, y);
    }

    private void rateDriver(String driverEmail, String rating) {

        double ratingNum;

        try {
            ratingNum = Double.parseDouble(rating);
        } catch (NumberFormatException ex) {
            return;
        }

        Umer.rateDriver(driverEmail, ratingNum);
    }

    private void toggleAvailability() {

        if (this.isAvailable) {

            this.driverAvailableLabel.setVisible(false);
            this.driverUnavailableLabel.setVisible(true);

        } else {

            this.driverUnavailableLabel.setVisible(false);
            this.driverAvailableLabel.setVisible(true);
        }

        Umer.toggleAvailability();
    }

    private void showPopup(String titleBar, String text) {
        JOptionPane.showMessageDialog(null, text, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showTripHistory() {

        String trips = String.join("\n", Umer.getTripHistory());

        this.showPopup("Registo de viagens", trips);
    }

    private void show10MostSpendingClients() {
        this.showPopup("10 clientes que gastam mais", Umer.getTop10SpendingClients());
    }

    private void show5LessReliableDrivers() {
        this.showPopup("5 piores condutores", Umer.getTop5LessReliableDrivers());
    }

    private void showVehicleFinances() {

        String id = this.vehicleFinancesInput.getText();

        if (id == null || id == "") {
            return;
        }

        this.showPopup("Finanças do veículo " + id, Umer.getVehicleFinances(id));
    }

    public void updateLoggedDriverInfo(String driverString, boolean isAvailable) {

        this.loggedInDriverInfo.setText(driverString);

        this.isAvailable = isAvailable;

        this.driverAvailableLabel.setVisible(isAvailable);
        this.driverUnavailableLabel.setVisible(!isAvailable);
    }

    public void updateLoggedClientInfo(String clientString, String[] ratingPendingEmails) {

        // Classe listener para os ratings
        final class RatingActionListener implements ActionListener {
            private int index;
            private String email;

            public RatingActionListener(int index, String driverEmail) {
                this.index = index;
                this.email = driverEmail;
            }

            public void actionPerformed(ActionEvent e) {
                rateDriver(email, ratingInputs[index].getText());
            }
        }

        this.loggedInClientInfo.setText(clientString);

        if (ratingPendingEmails.length == 0) {
            this.emptyRatingsNotifier.setVisible(true);
        } else {
            this.emptyRatingsNotifier.setVisible(false);
        }

        // Reinicializar o array que vai guardar as inputs de ratings
        this.ratingInputs = new JTextField[ratingPendingEmails.length];

        for (int i = 0; i < ratingPendingEmails.length; i++) {

            // Inserir input de rating na GUI

            JPanel pnlPendingRating = new JPanel();
            pnlPendingRating.setOpaque(false);
            pnlPendingRating.setMaximumSize(new Dimension(32767, 50));
            pnlPendingRating.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            JLabel lblCondutor = new JLabel(ratingPendingEmails[i]);
            pnlPendingRating.add(lblCondutor);

            JTextField txtRating = new JTextField();
            pnlPendingRating.add(txtRating);
            txtRating.setColumns(10);

            this.ratingInputs[i] = txtRating;

            JButton btnSubmeter = new JButton("Submeter");
            btnSubmeter.addActionListener(new RatingActionListener(i, ratingPendingEmails[i]));
            pnlPendingRating.add(btnSubmeter);

            this.pendingRatingsContainer.add(pnlPendingRating);
        }
    }

    private void updateTime(String time) {
        this.dateLabel.setText(time);
    }

    private void updateDrivers(String[] drivers) {
        this.clearDrivers();

        for (String driver : drivers) {
            this.insertDriver(driver);
        }
    }

    private void updateClients(String[] clients) {
        this.clearClients();

        for (String client : clients) {
            this.insertClient(client);
        }
    }

    private void updateVehicles(String[] vehicles) {
        this.clearVehicles();

        for (String vehicle : vehicles) {
            this.insertVehicle(vehicle);
        }
    }

    private void updateTrips(String[] trips) {
        this.clearTrips();

        for (String trip : trips) {
            this.insertTrip(trip);
        }
    }

    public void startGUILoop() {

        this.logout();

        // Atualizar a GUI constantemente através de um swing timer

        Timer t = new Timer(200, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTrips(Umer.getTripsUnderWay());
                updateDrivers(Umer.getAllDrivers());
                updateClients(Umer.getAllClients());
                updateVehicles(Umer.getAllVehicles());
                updateTime(Umer.getTimeNow());

                if (Umer.userHasUpdates()) {

                    String loggedDriverInfo = Umer.getLoggedDriverInfo();

                    if (loggedDriverInfo != null) {

                        updateLoggedDriverInfo(loggedDriverInfo, Umer.getLoggedDriverAvailability());
                    }

                    String[] loggedClientInfo = Umer.getLoggedClientInfo(); // [client string, driver email 0, driver email 1, ...]

                    if (loggedClientInfo != null) {

                        String driverEmails[] = Arrays.copyOfRange(loggedClientInfo, 1, loggedClientInfo.length);

                        updateLoggedClientInfo(loggedClientInfo[0], driverEmails);
                    }

                    Umer.userUpdated();
                }
            }
        });
        t.start();
    }
}
