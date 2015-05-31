package dao;

import models.Visita;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class VisitaDao extends GenericDao {

	public VisitaDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Visita> todos() {
		return super.session.createCriteria(Visita.class).list();
	}
	
	public Visita consultarVisitaPorPosicao(Integer latitude, Integer longitude){
		Visita visita = (Visita) super.session.createCriteria(Visita.class)
    		    .add( Restrictions.eq("latitude", longitude) )
    		    .add(Restrictions.eq("latitude", longitude)).uniqueResult(); 
		return visita;
	}
}
