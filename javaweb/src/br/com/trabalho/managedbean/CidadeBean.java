package br.com.trabalho.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.trabalho.business.CidadeBusiness;
import br.com.trabalho.model.Cidade;
import br.com.trabalho.model.Estado;

@ManagedBean
@SessionScoped
public class CidadeBean {
	@ManagedProperty("#{cidadeBusiness}")
	private CidadeBusiness cidadeBusiness;
	
	private Cidade cidade = new Cidade();

	private String msgSucesso;

	public CidadeBusiness getCidadeBusiness() {
		return cidadeBusiness;
	}

	public void setCidadeBusiness(CidadeBusiness estadoBusiness) {
		this.cidadeBusiness = estadoBusiness;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public List<Cidade> getCidades() {
		return cidadeBusiness.listarCidades();
	}
	
	public Estado getEstado() {
		return this.cidade.getEstado();
	}
	
	public void setEstado(Estado estado) {
		this.cidade.setEstado(estado);
	}
	
	public String getMsgSucesso() {
		return msgSucesso;
	}
	
	public void setMsgSucesso(String msgSucesso) {
		this.msgSucesso = msgSucesso;
	}
	
	public String excluirCidade(){
		cidadeBusiness.excluirCidade(cidade);
		return "estado-editar.xhtml";
	}
	
	public String novaCidade(Estado estado) {
		this.cidade = new Cidade();
		this.cidade.setEstado(estado);

		return "cidade-editar";
	}
	
	public String salvarCidade() {
		try {
			
			if(cidade.getId() == null) {
				cidadeBusiness.incluirCidade(cidade);
			} else {
				cidadeBusiness.alterarCidade(cidade);
			}
			
			return "estado-editar.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setDetail(e.getMessage());
			
			FacesContext.getCurrentInstance().addMessage("cidadeForm", message);
			return null;
		}
		
	}
}
