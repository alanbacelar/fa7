package br.com.trabalho.dao;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.trabalho.model.Estado;

@ApplicationScoped
@ManagedBean(name = "estadoDao")
public class EstadoDao extends GenericListDao<Estado> {

	public EstadoDao() {
		for (int i = 1; i <= 10; i++) {
			Estado estado = new Estado();
			estado.setNome("Nome do estado " + i);
			estado.setSigla("EST" + i);
			
			salvar(estado);
		}
	}

	public void salvarEstado(Estado estado) {
		super.salvar(estado);
	}

	public void excluirEstado(Estado estado) {
		super.excluir(estado);
	}

	public Estado selecionarEstado(Estado estado) {
		return super.consultar(estado);
	}

	public List<Estado> listarEstados() {
		return super.listar();
	}
}
