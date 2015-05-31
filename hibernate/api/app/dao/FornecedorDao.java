package dao;

import java.util.List;

import models.Fornecedor;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;


public class FornecedorDao extends GenericDao {

	public FornecedorDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> todos() {
		return super.session.createCriteria(Fornecedor.class).setFetchMode("endereco", FetchMode.JOIN).list();
	}

	public Fornecedor consultarFornecedor(Integer id) throws Exception {
		return (Fornecedor) super.session.createCriteria(Fornecedor.class)
				.add(Restrictions.eq("id", id))
				.setFetchMode("endereco", FetchMode.JOIN)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listaFornecedor(){
		String consulta = "from Fornecedor";
		return super.session.createQuery(consulta).list();
	}
}
