package br.univel.jshare.ler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LeituraEscritadeArquivos {

	public static void main(String[] args) {

		String nomeArquivo = "DadosAluno.txt";

		new LeituraEscritadeArquivos(new File(".\\Share\\Download\\" + nomeArquivo));
	}

	public LeituraEscritadeArquivos() {
	}

	public LeituraEscritadeArquivos(File f) {

		System.out.println("Início da leitura ");

		byte[] dados = leia(f);

		System.out.println("Início da copia ");

		escreva(new File(".\\Share\\Upload\\" + " Cópia de " + f.getName()), dados);

	}

	public void escreva(File arq, byte[] dados) {

		try {
			Files.write(Paths.get(arq.getPath()), dados, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public byte[] leia(File arq) {

		Path path = Paths.get(arq.getPath());

		try {
			byte[] dados = Files.readAllBytes(path);
			return dados;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
