package br.univel.jshare.Tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import br.univel.jshare.auxiliar.Auxiliar;
import br.univel.jshare.ler.LeituraEscritadeArquivos;
import br.univel.jshare.ler.LerIp;
import br.univel.jshare.ler.ListarDiretoriosArquivos;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class Tela extends JFrame implements IServer {

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
	private JButton btnDownload;

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
		contentPane.setBackground(Color.LIGHT_GRAY);
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
		txt_Nome.setText("Alexandre Henrique Noro");
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
				finalizar();
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
		txt_IpServer.setText("127.0.0.1");
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
		txt_PortaServer.setText("2234");
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
		txt_NomeArq.setText("DadosAluno.txt");
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

		btnDownload = new JButton("Download ");
		btnDownload.setFont(new Font("Consolas", Font.BOLD, 11));
		GridBagConstraints gbc_btnDownload = new GridBagConstraints();
		gbc_btnDownload.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDownload.insets = new Insets(0, 0, 5, 0);
		gbc_btnDownload.gridx = 6;
		gbc_btnDownload.gridy = 3;
		contentPane.add(btnDownload, gbc_btnDownload);

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
		txt_PortaLocal.setText("2233");
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

		configurar();

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
				conectar(txt_IpServer.getText(), txt_PortaServer.getText(), 0);

			}
		});

		btnDownload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Download();

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

	protected void conectar(String hostServer, String portaServer, int erro) {

		Auxiliar aux = new Auxiliar();

		try {
			hostServer = aux.verificaIP(hostServer);
			int porta = aux.verificaPorta(portaServer);

			instanciarCliente();
			servico = (IServer) Naming.lookup("rmi://" + hostServer + ":" + porta + "/" + IServer.NOME_SERVICO);

			servico.registrarCliente(cliente);
			servico.publicarListaArquivos(cliente, new ListarDiretoriosArquivos().listarArquivos());

			btnBuscarArq.setEnabled(true);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null,
					e.getMessage() + "\n\n ERRO AO CONECTAR. VERIFIQUE SE O SERVIDOR ESTÁ ATIVO \n\n");

			e.printStackTrace();
			if (erro < 2) {
				conectar(IpServer, PortaServer, erro + 1);
				JOptionPane.showMessageDialog(this, "Reconectando ao servidor!!");

			} else {
				JOptionPane.showMessageDialog(this, "Não foi possível conectar ao servidor");
			}
		}
	}

	protected void finalizar() {

		try {
			for (int i = 0; i < MapClientes.size(); i++) {
				desconectar(MapClientes.get(i));
			}
			if (servico != null)
				servico.desconectar(cliente);
			if (iServer != null)
				PararServer();
		} catch (Exception e1) {

			return;

		}

	}

	protected void Buscar() {
		list.removeAll();
		ListMapArquivos.clear();
		try {
			ListMapArquivos = servico.procurarArquivo(txt_NomeArq.getText().trim());
			for (Map.Entry<Cliente, List<Arquivo>> entry : ListMapArquivos.entrySet()) {
				addListaPesquisa(entry.getValue());
			}
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar");
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Reconectando ao servidor");
			conectar(IpServer, PortaServer, 0);
		}
	}

	private void Download() {
		try {
			int vlr = list.getSelectedIndex();

			if (vlr > -1) {
				String nomeArq = (String) list.getModel().getElementAt(vlr);
				for (Map.Entry<Cliente, List<Arquivo>> entry : ListMapArquivos.entrySet()) {
					for (Arquivo arq : entry.getValue()) {
						if (nomeArq.equals(arq.getNome())) {

							servico = null;
							txt_IpServer.setText(entry.getKey().getIp());
							txt_PortaServer.setText(String.valueOf(entry.getKey().getPorta()));

							conectar(txt_IpServer.getText(), txt_PortaServer.getText(), 0);

							escreverDowload(servico.baixarArquivo(arq), arq.getFile());

							return;
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Selecione o item que deseja baixar");
			}
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao realizar Download");
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Reconectando ao servidor");
			conectar(IpServer, PortaServer, 0);

		}
	}

	private void escreverDowload(byte[] dados, File nome) {
		new LeituraEscritadeArquivos().escreva(new File(".\\Share\\Upload\\" + "Cópia de " + nome.getName()), dados);
	}

	private void instanciarCliente() {
		Auxiliar aux = new Auxiliar();
		if (cliente == null) {
			if (IpServer == null || PortaServer == null) {
				IpServer = txt_IpServer.getText();
				PortaServer = txt_PortaServer.getText();
			}

			cliente = new Cliente();
			cliente.setNome(aux.verificaNome(txt_Nome.getText()));
			cliente.setIp(aux.verificaIP(cbx_IpLocal.getSelectedItem().toString()));
			cliente.setPorta(aux.verificaPorta(txt_PortaLocal.getText()));
		} else {
			cliente.setNome(aux.verificaNome(txt_Nome.getText()));
			cliente.setIp(aux.verificaIP(cbx_IpLocal.getSelectedItem().toString()));
			cliente.setPorta(aux.verificaPorta(txt_PortaLocal.getText()));
		}
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");

	private Map<String, Cliente> mapClienteServer = new HashMap<>();

	private Map<Cliente, List<Arquivo>> ListArqServer = new HashMap<>();

	private IServer iServer;

	private Registry registryClienteServer;

	protected void IniciarServer() {

		try {
			iServer = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registryClienteServer = LocateRegistry
					.createRegistry(new Auxiliar().verificaPorta(txt_PortaLocal.getText()));
			registryClienteServer.rebind(IServer.NOME_SERVICO, iServer);

			mostrarNaTela("Serviço iniciado");

			cbx_IpLocal.setEnabled(false);
			txt_PortaLocal.setEnabled(false);
			btnIniciarServer.setEnabled(false);

			btnParar_Server.setEnabled(true);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage() + "\n\n Erro ao iniciar Serviço");
			e.printStackTrace();
		}

	}

	protected void PararServer() {

		mostrarNaTela("Servidor esta parando o serviço");

		DesconectarTodosClientes();

		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registryClienteServer, true);

			txt_PortaLocal.setEnabled(true);
			btnIniciarServer.setEnabled(true);

			btnParar_Server.setEnabled(false);

			mostrarNaTela("Servidor Parado!!");

		} catch (Exception e) {
			return;
		}
	}

	private void addListaPesquisa(List<Arquivo> value) throws RemoteException {
		list.removeAll();

		ListModel<String> model = new AbstractListModel<String>() {
			@Override
			public int getSize() {
				return value.size();
			}

			@Override
			public String getElementAt(int index) {
				return value.get(index).getNome();
			}
		};
		list.setModel(model);

	}

	private void mostrarNaTela(String string) {
		textArea.append(sdf.format(new Date()));
		textArea.append(" -> ");
		textArea.append(string);
		textArea.append("\n");

	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		
		mostrarNaTela("Foi pesquisado o \"Arquivo\" ->" + nome);

		if (nome.length() == 0) {
			return ListArqServer;
		}
		Map<Cliente, List<Arquivo>> resultMapArq = new HashMap<>();
		for (Map.Entry<Cliente, List<Arquivo>> entry : ListArqServer.entrySet()) {
			List<Arquivo> listArquivo = new ArrayList<>();
			for (Arquivo arq : entry.getValue()) {
				if (arq.getNome().equals(nome)) {
					listArquivo.add(arq);
				}

			}
			if (listArquivo.size() > 0) {
				resultMapArq.put(entry.getKey(), listArquivo);
			}
		}
		return resultMapArq;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		File file = new File(".\\Share\\Download\\" + arq.getNome());
		byte[] dados = new LeituraEscritadeArquivos().leia(file);
		mostrarNaTela("Feito dowload do -> " + arq.getNome());
		return dados;
	}

	protected void DesconectarTodosClientes() {
		mostrarNaTela("Desconectando todos os clientes do Servidor");

	}

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mostrarNaTela(c.getNome() + ", com ip:" + c.getIp() + " se conectou.");
		mapClienteServer.put(c.getIp(), c);

	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		for (Arquivo arquivo : lista) {
			mostrarNaTela("Cliente:" + c.getNome() + "/ Publico arquivo: " + arquivo.getNome() + " : "
					+ arquivo.getTamanho());
		}
		ListArqServer.put(c, lista);

	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		mapClienteServer.remove(c);
		ListArqServer.remove(c);
		mostrarNaTela("Cliente: " + c.getNome().toUpperCase() + " desconectado!");

	}

}
