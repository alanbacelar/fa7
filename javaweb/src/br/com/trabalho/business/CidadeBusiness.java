package br.com.trabalho.business;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.trabalho.dao.CidadeDao;
import br.com.trabalho.model.Cidade;
import br.com.trabalho.model.Estado;

@ApplicationScoped
@ManagedBean(name = "cidadeBusiness")
public class CidadeBusiness {

	@ManagedProperty("#{cidadeDao}")
	private CidadeDao cidadeDao;
	
		public void setCidadeDao(CidadeDao cidadeDao) {
		this.cidadeDao = cidadeDao;
	}

	public void salvarCidade(Cidade cidade) throws PibInvalidoException {
		cidadeDao.salvarCidade(cidade);
	}

	public void incluirCidade(Cidade cidade) {	
		cidadeDao.salvarCidade(cidade);
	}

	public void excluirCidade(Cidade cidade) {
		cidadeDao.excluirCidade(cidade);
	}

	public Cidade consultarCidade(Cidade cidade) {
		return cidadeDao.selecionar(cidade);
	}
	
	public List<Cidade> consultarCidadePorEstado(Estado estado) {
		return cidadeDao.listarCidadesPorEstado(estado);
	}
	
	public void alterarCidade(Cidade cidade){
		cidadeDao.salvarCidade(cidade);
	}
	
	public List<Cidade> listarCidades() {
		return cidadeDao.getLista();
	}
	

}
