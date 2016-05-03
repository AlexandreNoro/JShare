package br.univel.jshare.auxiliar;

public class Auxiliar {

	public String verificaNome(String nome) {
		nome.trim();
		if (nome.length() == 0) {
			throw new RuntimeException("Digite um nome para prosseguir!!");

		}
		return nome;

	}

	public String verificaIP(String ip) {
		ip.trim();

		if (!ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			throw new RuntimeException("O IP está incorreto!");

		}
		return ip;
	}

	public int verificaPorta(String strPorta) {
		strPorta.trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			throw new RuntimeException("A porta de ser numeros e no máximo 5 dígitos");
		}
		int intPorta = Integer.parseInt(strPorta);
		if (intPorta < 1024 || intPorta > 65535) {
			throw new RuntimeException("A porta tem que ser entre 1024 á 65535! ");
		}
		return intPorta;
	}

}
