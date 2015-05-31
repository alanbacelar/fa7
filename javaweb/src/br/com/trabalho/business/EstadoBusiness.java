package br.com.trabalho.business;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.trabalho.dao.CidadeDao;
import br.com.trabalho.dao.EstadoDao;
import br.com.trabalho.model.Cidade;
import br.com.trabalho.model.Estado;
@ApplicationScoped
@ManagedBean(name = "estadoBusiness")
public class EstadoBusiness {

	@ManagedProperty("#{estadoDao}")
	private EstadoDao estadoDao;

	@ManagedProperty("#{cidadeDao}")
	private CidadeDao cidadeDao;

	public void setEstadoDao(EstadoDao estadoDao) {
		this.estadoDao = estadoDao;
	}

	public void setCidadeDao(CidadeDao cidadeDao) {
		this.cidadeDao = cidadeDao;
	}

	public void salvarEstado(Estado estado) throws PibInvalidoException {
		validarCidades(estado);
		cidadeDao.salvarCidades(estado.getCidades());
		estadoDao.salvarEstado(estado);
	}


	private void validarCidades(Estado estado) throws PibInvalidoException {
		if (estado.getCidades() != null) {
			for (Cidade cidade : estado.getCidades()) {
				Double populacao = cidade.getPopulacao().doubleValue();
				if (populacao.compareTo(cidade.getPib()) > 0) {
					throw new PibInvalidoException();
				} else {
					cidade.setEstado(estado);
				}
			}
		}
	}
	
	public void incluirEstado(Estado estado) {	
		estadoDao.salvarEstado(estado);
	}
	
	public void incluirCidade(Cidade cidade) {	
		cidadeDao.salvarCidade(cidade);
	}

	public void excluirEstado(Estado estado) {
		estadoDao.excluirEstado(estado);
	}

	public Estado consultarEstado(Estado estado) {
		return estadoDao.selecionarEstado(estado);
	}

	public List<Estado> listarEstados() {
		return estadoDao.listarEstados();
	}
	
	public void alterarEstado(Estado estado){
		estadoDao.salvarEstado(estado);
	}
	
	public void alterarCidade(Cidade cidade){
		cidadeDao.salvarCidade(cidade);
	}

}
