package br.com.trabalho.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.model.EntidadeBase;

public class GenericListDao<T extends EntidadeBase>{

	private final List<T> dados = new ArrayList<T>();

	public List<T> getLista(){
		return dados;
	}

	protected void salvar(T dado) {
		if (dado.getId() == null || dado.getId() <= 0) {
			dado.setId(proximoId());
			dados.add(dado);
		} else {
			int pos = -1;
			for(EntidadeBase dadoAux: dados){
				if (dadoAux.getId().compareTo(dado.getId()) >= 0){
					pos = dados.indexOf(dadoAux);
					break;
				}
			}
			if (pos != -1)
				dados.set(pos, dado);
		}
	}
	
	protected void excluir(T dado){
		dados.remove(dado);
	}
	
	protected List<T> listar(){
		return dados;
	}
	
	protected T consultar(T dado) {
		if (dados.contains(dado)) {
			return dados.get(dados.indexOf(dado));
		}
		return null;
	}
	
	private Long proximoId() {
		Long proximo = 1L;
		for (EntidadeBase dado : dados) {
			if (dado.getId().compareTo(proximo) >= 0) {
				proximo = dado.getId() + 1;
			}
		}
		return proximo;
	}
}
