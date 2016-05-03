package br.univel.jshare.Tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.univel.jshare.auxiliar.Auxiliar;
import br.univel.jshare.comun.Cliente;
import br.univel.jshare.comun.IServer;
import br.univel.jshare.ler.LerIp;
import br.univel.jshare.ler.ListarDiretoriosArquivos;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class Tela extends JFrame {

	private JPanel contentPane;
	private JTextField txt_Nome;
	private JTextField txt_IpServer;
	private JTextField txt_NomeArq;
	private JTextField txt_PortaServer;
	private JButton btnConectar;
	private JButton btnBuscarArq;
	private JLabel lblLocal_IP;
	private JScrollPane scrollPane;
	private JComboBox cbx_IpLocal;
	private JLabel lblPortaLocal;
	private JTextField txt_PortaLocal;
	private JButton btnIniciarServer;
	private JButton btnParar_Server;
	private JList list;
	private JScrollPane scrollPane_1;
	private JButton btnFechar;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 460);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 120, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNome = new JLabel("Nome do Usuário");
		lblNome.setFont(new Font("Courier New", Font.BOLD, 12));
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.fill = GridBagConstraints.VERTICAL;
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);

		txt_Nome = new JTextField();
		GridBagConstraints gbc_txt_Nome = new GridBagConstraints();
		gbc_txt_Nome.gridwidth = 3;
		gbc_txt_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_txt_Nome.fill = GridBagConstraints.BOTH;
		gbc_txt_Nome.gridx = 1;
		gbc_txt_Nome.gridy = 0;
		contentPane.add(txt_Nome, gbc_txt_Nome);
		txt_Nome.setColumns(10);

		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFechar.setFont(new Font("Consolas", Font.BOLD, 11));
		GridBagConstraints gbc_btnFechar = new GridBagConstraints();
		gbc_btnFechar.fill = GridBagConstraints.BOTH;
		gbc_btnFechar.insets = new Insets(0, 0, 5, 0);
		gbc_btnFechar.gridx = 6;
		gbc_btnFechar.gridy = 0;
		contentPane.add(btnFechar, gbc_btnFechar);

		JLabel lblServer_IP = new JLabel("IP do Servidor");
		lblServer_IP.setFont(new Font("Courier New", Font.BOLD, 12));
		GridBagConstraints gbc_lblServer_IP = new GridBagConstraints();
		gbc_lblServer_IP.fill = GridBagConstraints.VERTICAL;
		gbc_lblServer_IP.anchor = GridBagConstraints.EAST;
		gbc_lblServer_IP.insets = new Insets(0, 0, 5, 5);
		gbc_lblServer_IP.gridx = 0;
		gbc_lblServer_IP.gridy = 1;
		contentPane.add(lblServer_IP, gbc_lblServer_IP);

		txt_IpServer = new JTextField();
		GridBagConstraints gbc_txt_IpServer = new GridBagConstraints();
		gbc_txt_IpServer.gridwidth = 2;
		gbc_txt_IpServer.fill = GridBagConstraints.BOTH;
		gbc_txt_IpServer.insets = new Insets(0, 0, 5, 5);
		gbc_txt_IpServer.gridx = 1;
		gbc_txt_IpServer.gridy = 1;
		contentPane.add(txt_IpServer, gbc_txt_IpServer);
		txt_IpServer.setColumns(10);

		JLabel lblPorta_Server = new JLabel("Porta do Servidor");
		lblPorta_Server.setFont(new Font("Courier New", Font.BOLD, 12));
		GridBagConstraints gbc_lblPorta_Server = new GridBagConstraints();
		gbc_lblPorta_Server.fill = GridBagConstraints.VERTICAL;
		gbc_lblPorta_Server.anchor = GridBagConstraints.EAST;
		gbc_lblPorta_Server.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta_Server.gridx = 0;
		gbc_lblPorta_Server.gridy = 2;
		contentPane.add(lblPorta_Server, gbc_lblPorta_Server);

		txt_PortaServer = new JTextField();
		txt_PortaServer.setText("2233");
		GridBagConstraints gbc_txt_PortaServer = new GridBagConstraints();
		gbc_txt_PortaServer.gridwidth = 2;
		gbc_txt_PortaServer.insets = new Insets(0, 0, 5, 5);
		gbc_txt_PortaServer.fill = GridBagConstraints.BOTH;
		gbc_txt_PortaServer.gridx = 1;
		gbc_txt_PortaServer.gridy = 2;
		contentPane.add(txt_PortaServer, gbc_txt_PortaServer);
		txt_PortaServer.setColumns(10);

		btnConectar = new JButton("Conectar");
		btnConectar.setFont(new Font("Consolas", Font.BOLD, 11));
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.fill = GridBagConstraints.BOTH;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 3;
		gbc_btnConectar.gridy = 2;
		contentPane.add(btnConectar, gbc_btnConectar);

		JLabel lblNome_Arq = new JLabel("Nome do Arquivo");
		lblNome_Arq.setFont(new Font("Courier New", Font.BOLD, 12));
		GridBagConstraints gbc_lblNome_Arq = new GridBagConstraints();
		gbc_lblNome_Arq.fill = GridBagConstraints.VERTICAL;
		gbc_lblNome_Arq.anchor = GridBagConstraints.EAST;
		gbc_lblNome_Arq.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome_Arq.gridx = 0;
		gbc_lblNome_Arq.gridy = 3;
		contentPane.add(lblNome_Arq, gbc_lblNome_Arq);

		txt_NomeArq = new JTextField();
		GridBagConstraints gbc_txt_NomeArq = new GridBagConstraints();
		gbc_txt_NomeArq.gridwidth = 2;
		gbc_txt_NomeArq.fill = GridBagConstraints.BOTH;
		gbc_txt_NomeArq.insets = new Insets(0, 0, 5, 5);
		gbc_txt_NomeArq.gridx = 1;
		gbc_txt_NomeArq.gridy = 3;
		contentPane.add(txt_NomeArq, gbc_txt_NomeArq);
		txt_NomeArq.setColumns(10);

		btnBuscarArq = new JButton("Buscar Arquivo");
		btnBuscarArq.setFont(new Font("Consolas", Font.BOLD, 11));
		GridBagConstraints gbc_btnBuscarArq = new GridBagConstraints();
		gbc_btnBuscarArq.fill = GridBagConstraints.BOTH;
		gbc_btnBuscarArq.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscarArq.gridx = 3;
		gbc_btnBuscarArq.gridy = 3;
		contentPane.add(btnBuscarArq, gbc_btnBuscarArq);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);

		lblLocal_IP = new JLabel("IP local");
		lblLocal_IP.setFont(new Font("Courier New", Font.BOLD, 12));
		GridBagConstraints gbc_lblLocal_IP = new GridBagConstraints();
		gbc_lblLocal_IP.fill = GridBagConstraints.VERTICAL;
		gbc_lblLocal_IP.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocal_IP.gridx = 0;
		gbc_lblLocal_IP.gridy = 5;
		contentPane.add(lblLocal_IP, gbc_lblLocal_IP);

		cbx_IpLocal = new JComboBox();
		GridBagConstraints gbc_cbx_IpLocal = new GridBagConstraints();
		gbc_cbx_IpLocal.gridwidth = 2;
		gbc_cbx_IpLocal.insets = new Insets(0, 0, 5, 5);
		gbc_cbx_IpLocal.fill = GridBagConstraints.BOTH;
		gbc_cbx_IpLocal.gridx = 1;
		gbc_cbx_IpLocal.gridy = 5;
		contentPane.add(cbx_IpLocal, gbc_cbx_IpLocal);

		lblPortaLocal = new JLabel("Porta Local");
		lblPortaLocal.setFont(new Font("Courier New", Font.BOLD, 12));
		GridBagConstraints gbc_lblPortaLocal = new GridBagConstraints();
		gbc_lblPortaLocal.fill = GridBagConstraints.VERTICAL;
		gbc_lblPortaLocal.insets = new Insets(0, 0, 5, 5);
		gbc_lblPortaLocal.gridx = 3;
		gbc_lblPortaLocal.gridy = 5;
		contentPane.add(lblPortaLocal, gbc_lblPortaLocal);

		txt_PortaLocal = new JTextField();
		txt_PortaLocal.setText("2234");
		GridBagConstraints gbc_txt_PortaLocal = new GridBagConstraints();
		gbc_txt_PortaLocal.insets = new Insets(0, 0, 5, 5);
		gbc_txt_PortaLocal.fill = GridBagConstraints.BOTH;
		gbc_txt_PortaLocal.gridx = 4;
		gbc_txt_PortaLocal.gridy = 5;
		contentPane.add(txt_PortaLocal, gbc_txt_PortaLocal);
		txt_PortaLocal.setColumns(10);

		btnIniciarServer = new JButton("Iniciar Serviço");
		btnIniciarServer.setFont(new Font("Consolas", Font.BOLD, 11));
		GridBagConstraints gbc_btnIniciarServer = new GridBagConstraints();
		gbc_btnIniciarServer.fill = GridBagConstraints.VERTICAL;
		gbc_btnIniciarServer.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciarServer.gridx = 5;
		gbc_btnIniciarServer.gridy = 5;
		contentPane.add(btnIniciarServer, gbc_btnIniciarServer);

		btnParar_Server = new JButton("Parar Serviço");
		btnParar_Server.setFont(new Font("Consolas", Font.BOLD, 11));
		GridBagConstraints gbc_btnParar_Server = new GridBagConstraints();
		gbc_btnParar_Server.fill = GridBagConstraints.VERTICAL;
		gbc_btnParar_Server.insets = new Insets(0, 0, 5, 0);
		gbc_btnParar_Server.gridx = 6;
		gbc_btnParar_Server.gridy = 5;
		contentPane.add(btnParar_Server, gbc_btnParar_Server);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 7;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 6;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
	}

	private static String IpServer = null;

	private static String PortaServer = null;

	private Map<String, Cliente> MapClientes = new HashMap<>();

	private Map<Cliente, List<Arquivo>> ListMapArquivos = new HashMap<>();

	private IServer servico = null;

	private Cliente cliente = null;

	private void configurar() {

		cbx_IpLocal.addItem(new LerIp().retornarIP());

		txt_Nome.setText("Alexandre Henrique Noro");
		txt_IpServer.setText("127.0.0.1");
		txt_NomeArq.setText("DadosAluno.txt");

		btnBuscarArq.setEnabled(false);
		btnParar_Server.setEnabled(false);

		btnBuscarArq.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Buscar();

			}
		});

		btnIniciarServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IniciarServer();

			}
		});

		btnParar_Server.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PararServer();

			}
		});

		btnConectar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				conectar(txt_IpServer.getText(), txt_PortaServer.getText());

			}
		});

		list.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 2) {
						list.getSelectedIndex();
					}
				}
			}
		});

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				finalizar();
			}
		});
		
		btnFechar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				finalizar();
				
			}
		});

	}

	protected void finalizar() {
		// TODO Auto-generated method stub

	}

	protected void conectar(String hostServer, String portaServer) {
		
		Auxiliar aux = new Auxiliar();

		try {
			hostServer = aux.verificaIP(hostServer);
			int porta = aux.verificaPorta(portaServer);
			
			instanciarCliente();
			
			servico.registrarCliente(cliente);
			servico.publicarListaArquivos(cliente, new ListarDiretoriosArquivos().listarArquivos());
			
			btnBuscarArq.setEnabled(true);
			
		} catch (Exception e) {

		}
	}

	private void instanciarCliente() {
		// TODO Auto-generated method stub
		
	}

	protected void PararServer() {
		// TODO Auto-generated method stub

	}

	protected void IniciarServer() {
		// TODO Auto-generated method stub

	}

	protected void Buscar() {
		// TODO Auto-generated method stub

	}

}
