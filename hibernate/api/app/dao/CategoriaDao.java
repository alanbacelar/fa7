package dao;

import models.Categoria;

import java.util.List;


public class CategoriaDao extends GenericDao {

	public CategoriaDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> todos() {
		return super.session.createCriteria(Categoria.class).list();
	}

}
