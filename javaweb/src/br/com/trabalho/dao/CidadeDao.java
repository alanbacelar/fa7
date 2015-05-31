package br.com.trabalho.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import br.com.trabalho.model.Cidade;
import br.com.trabalho.model.Estado;

@ApplicationScoped
@ManagedBean(name = "cidadeDao")
public class CidadeDao extends GenericListDao<Cidade> {
	
	public CidadeDao() {	
		Cidade cidade = new Cidade();
		cidade.setNome("CIDADE DO ESTADO 1");
		cidade.setPopulacao(new Long(1));
		cidade.setDataConstituicao(new Date());
		cidade.setPib(1.1);
		
		Estado estado = new Estado();
		estado.setId(new Long(1));
		cidade.setEstado(estado);

		salvar(cidade);

	}
	
	public void salvarCidade(Cidade cidade) {
		super.salvar(cidade);
	}

	public void excluirCidade(Cidade cidade) {
		super.excluir(cidade);
	}

	public Cidade selecionar(Cidade cidade) {
		return super.consultar(cidade);
	}

	public List<Cidade> listarCidades() {
		return super.listar();
	}
	
	public List<Cidade> listarCidadesPorEstado(Estado estado) {
		List<Cidade> cidades = super.listar();
		List<Cidade> cidadesDoEstado = new ArrayList<Cidade>();
		
		for(Cidade cidade: cidades) {
			if (cidade.getEstado().getId() == estado.getId()) {
				cidadesDoEstado.add(cidade);
			}
		}

		return cidadesDoEstado;
	}

	public void salvarCidades(List<Cidade> cidades) {
		if (cidades != null) {
			for (Cidade cidade : cidades) {
				salvarCidade(cidade);
			}
		}
	}
}
