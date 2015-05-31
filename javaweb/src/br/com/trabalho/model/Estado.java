package br.com.trabalho.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estado extends EntidadeBase implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1962391368025442461L;

	/**
	 * Nome do estado.
	 */
	private String nome;

	/**
	 * Sigla do estado.
	 */
	private String sigla;

	/**
	 * Cidades pertendcentes ao estado.
	 */
	private List<Cidade> cidades = new ArrayList<Cidade>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidades.add(cidade);
	}
	
	public void removeCidade(Cidade cidade) {
		this.cidades.remove(cidade);
	}
}
