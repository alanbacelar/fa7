package br.com.trabalho.managedbean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.trabalho.business.CidadeBusiness;
import br.com.trabalho.business.EstadoBusiness;
import br.com.trabalho.model.Cidade;
import br.com.trabalho.model.Estado;

@ManagedBean
@SessionScoped
public class EstadoBean {
	@ManagedProperty("#{estadoBusiness}")
	private EstadoBusiness estadoBusiness;
	
	@ManagedProperty("#{cidadeBusiness}")
	private CidadeBusiness cidadeBusiness;
	
	private Estado estado = new Estado();

	private String msgSucesso;

	public EstadoBusiness getEstadoBusiness() {
		return estadoBusiness;
	}

	public void setEstadoBusiness(EstadoBusiness estadoBusiness) {
		this.estadoBusiness = estadoBusiness;
	}
	
	public CidadeBusiness getCidadeBusiness() {
		return cidadeBusiness;
	}

	public void setCidadeBusiness(CidadeBusiness cidadeBusiness) {
		this.cidadeBusiness = cidadeBusiness;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public List<Estado> getListaEstados() {
		return estadoBusiness.listarEstados();
	}
	
	public String excluirEstado(){
		estadoBusiness.excluirEstado(estado);
		return "listar-estados.xhtml";
	}
	
	public String editarCidade(){
		return "editar-cidade.xhtml";
	}
	
	public String novoEstado() {
		estado = new Estado();
		return "estado-editar.xhtml";
	}
	
	public List<Cidade> getCidades() {
		return cidadeBusiness.consultarCidadePorEstado(estado);
	}
	
	public String getMsgSucesso() {
		return msgSucesso;
	}
	
	public void setMsgSucesso(String msgSucesso) {
		this.msgSucesso = msgSucesso;
	}
	
	public String salvarEstado() {
		try {
			if(estado.getId() == null)
				estadoBusiness.incluirEstado(estado);
			else
				estadoBusiness.alterarEstado(estado);
			
			return "listar-estados.xhtml?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setDetail(e.getMessage());
			FacesContext.getCurrentInstance().addMessage("estadoForm", message);
			return null;
		}
	}

}
