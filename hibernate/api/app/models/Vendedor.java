package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vendedor")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Vendedor extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621094817508679486L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vendedor")
	private Integer id;
	
	@Column(name = "comissao")
	@Basic(optional = true)
	private Double comissao = 0.10;

	@Column(name = "total_vendas")
	@Basic(optional = true)
	private Integer totalVendas = 0;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "vendedor")
	private Set<Pedido> pedidos;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getComissao() {
		return comissao;
	}

	public void setComissao(Double comissao) {
		this.comissao = comissao;
	}

	public Integer getTotalVendas() {
		return totalVendas;
	}

	public void setTotalVendas(Integer totalVendas) {
		this.totalVendas = totalVendas;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
