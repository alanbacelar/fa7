package br.com.trabalho.model;

import java.io.Serializable;

public class Cep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5872430653991465858L;
	
	private String regiao;
	
	private String sufixo;
	
	public Cep(String regiao, String sufixo) {
		super();
		this.regiao = regiao;
		this.sufixo = sufixo;
	}

	public String getSufixo() {
		return sufixo;
	}

	public void setSufixo(String sufixo) {
		this.sufixo = sufixo;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
}
