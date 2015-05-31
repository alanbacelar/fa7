package dao;

import models.Endereco;

import org.hibernate.criterion.Restrictions;

public class EnderecoDao extends GenericDao {

	public EnderecoDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Endereco consultarEnderecoCep(String cep, Integer numero){
		Endereco endereco = (Endereco) super.session.createCriteria(Endereco.class)
    		    .add( Restrictions.eq("cep", cep) )
    		    .add(Restrictions.eq("numero", numero)).uniqueResult(); 
		return endereco;
	}
	
	public Endereco consultarEnderecoPorPosicao(Integer latitude, Integer longitude){
		Endereco endereco = (Endereco) super.session.createCriteria(Endereco.class)
    		    .add( Restrictions.eq("latitude", longitude) )
    		    .add(Restrictions.eq("latitude", longitude)).uniqueResult(); 
		return endereco;
	}
}
