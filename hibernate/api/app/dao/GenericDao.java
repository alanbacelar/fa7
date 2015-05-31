package dao;

import java.util.List;

import models.BaseModel;

import org.hibernate.Session;

import factory.SessionHibernateFactory;

public abstract class GenericDao {
	protected final Session session;

	public GenericDao() {
		this.session = SessionHibernateFactory.getHibernateSession();
	}

	public void begin() {
		session.beginTransaction();
	}

	public void commit() {
		session.getTransaction().commit();
	}

	public void rollback() {
		session.getTransaction().rollback();
	}

	public void salvar(BaseModel base) {
		session.saveOrUpdate(base);
	}

	public void deletar(BaseModel base) {
		session.delete(base);
	}

	public boolean isConnected() {
		return session.isConnected();
	}

	public void close() {
		session.close();
	}

	public Session getSession() {
		return session;
	}

	@SuppressWarnings({ "rawtypes" })
	public Long count(Class base) {
		StringBuilder consulta = new StringBuilder();

		consulta.append("select count(*) from ");
		consulta.append(base.getName());

		return (Long) session.createQuery(consulta.toString()).uniqueResult();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaseModel> listarTodos(Class base){
		StringBuilder consulta = new StringBuilder();

		consulta.append("select * from ");
		consulta.append(base.getName());

		return session.createQuery(consulta.toString()).list();
    }
}
