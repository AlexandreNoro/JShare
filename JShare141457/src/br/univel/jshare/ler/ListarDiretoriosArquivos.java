package br.univel.jshare.ler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;

public class ListarDiretoriosArquivos {

	private File dirStart = new File(".\\Share\\Download\\.");
	
	public List<Diretorio> listarDiretorios(){
		
		List<Diretorio> listaDiretorios = new ArrayList<>();
		for (File file : dirStart.listFiles()) {
			if (!file.isFile()) {
				Diretorio dir = new Diretorio();
				dir.setNome(file.getName());
				listaDiretorios.add(dir);
			}
		}
		
		
		return listaDiretorios;
		
	}
	
	public List<Arquivo> listarArquivos(){
		
		List<Arquivo> listaArquivos = new ArrayList<>();
		
		for (File file : dirStart.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				arq.setFile(file);
				listaArquivos.add(arq);
			}
		}
		
		
		return listaArquivos;
		
	}
	
	
}
