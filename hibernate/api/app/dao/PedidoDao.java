package dao;

import java.util.List;

import models.Pedido;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

public class PedidoDao extends GenericDao {

	public PedidoDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> pedidosCliente(Integer id) {
		return super.session.createCriteria(Pedido.class)
				.setFetchMode("vendedor", FetchMode.JOIN)
				.setFetchMode("cliente", FetchMode.JOIN).add(Restrictions.eq("cliente.id", id)).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> todos(){
		return super.session.createCriteria(Pedido.class)
				.setFetchMode("vendedor", FetchMode.JOIN)
				.setFetchMode("cliente", FetchMode.JOIN).list();
		
	}
	
	public Pedido consultarPedido(Integer id){
		return (Pedido) super.session.createCriteria(Pedido.class)
				.setFetchMode("vendedor", FetchMode.JOIN)
				.setFetchMode("cliente", FetchMode.JOIN).add(Restrictions.eq("pedido.id", id)).uniqueResult();
		
	}
	
}
