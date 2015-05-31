package dao;

import java.util.List;

import models.ItensPedido;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

public class ItensPedidoDao extends GenericDao {
	
	public ItensPedidoDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<ItensPedido> listarItensPedido(Integer id) {
		
		return super.session.createCriteria(ItensPedido.class)
				.setFetchMode("produto", FetchMode.JOIN)
				.setFetchMode("pedido", FetchMode.JOIN)
				.setFetchMode("pedido.cliente", FetchMode.JOIN)
				.setFetchMode("pedido.vendedor", FetchMode.JOIN)
				.add(Restrictions.eq("pedido.id", id)).list();
	}
	

}
