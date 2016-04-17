package br.univel.jshare.comun;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import br.dagostini.jshare.comum.pojos.Arquivo;

public interface IServer extends Remote {

	public static final String NOME_SERVICO = "JShare";

	/*
	 * *Recebe informações de um novo cliente
	 */

	public void registrarCliente(Cliente c) throws RemoteException;

	/*
	 * Recebe a lista de arquivos disponíveis no cliente.
	 */
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException;

	/*
	 * Usado quando um cliente deseja procurar um arquivo pelo nome, o servidor
	 * lê todos os arquivos publicados e retorna um mapa contendo os resultados
	 * em cada cliente.
	 */

	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException;

	/*
	 * Recebe informações do arquivo e retorna o arquivo em formato de array de
	 * bytes.
	 * 
	 */

	public byte[] baixarArquivo(Arquivo arq) throws RemoteException;

	/*
	 * Desconecta o cliente, tornado também indisponível seus arquivos para as
	 * buscas.
	 * 
	 */

	public void desconectar(Cliente c) throws RemoteException;

}
