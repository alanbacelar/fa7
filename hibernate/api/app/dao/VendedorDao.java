package dao;

import java.util.List;

import models.Pedido;
import models.Vendedor;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class VendedorDao extends GenericDao {

	public VendedorDao() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Vendedor> todos() {
		return super.session.createCriteria(Vendedor.class).setFetchMode("endereco", FetchMode.JOIN).list();
	}
	
	public Vendedor consultarVendedor(Integer id) throws Exception {
		return (Vendedor) super.session.createCriteria(Vendedor.class)
				  				.add(Restrictions.eq("id", id))
								.setFetchMode("endereco", FetchMode.JOIN)
								.addOrder(Order.asc("nome"))
								.uniqueResult();
    }
	
	public Vendedor consultarPessoaCPF(String cpf) throws Exception {
    	String consulta = "from Vendedor v where v.cpf = :cpf";
    	return (Vendedor)super.session.createQuery(consulta).setParameter("cpf", cpf).uniqueResult();
	}


	public List<Pedido> consultarPedidoPorCliente() throws Exception {
		return  super.session.createCriteria(Pedido.class)
					.setFetchMode("vendedor", FetchMode.JOIN)
					.setFetchMode("cliente", FetchMode.JOIN)
					.setProjection(
							Projections.projectionList()
									.add(Projections.groupProperty("cliente.id"))
									.add(Projections.groupProperty("pessoa.nome"))
					)
					.list();
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> listarQuantidadeClientesVendedor(){
		return super.session.createCriteria(Pedido.class).setFetchMode("cliente", FetchMode.JOIN)
				.setFetchMode("vendedor", FetchMode.JOIN)
				.setProjection(Projections.projectionList()
				.add(Projections.groupProperty("cliente.id"))).list();
	}

}
