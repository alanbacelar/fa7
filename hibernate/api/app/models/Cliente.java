package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="cliente_id_seq")
@Table(name = "cliente")
public class Cliente extends Pessoa {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699449860340032136L;

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
	@Column(name = "id_cliente")
	private Integer id;
	
	@Column(name = "preferencial", nullable = false)
	private Boolean preferencial = false;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente", fetch=FetchType.LAZY)
	private List<Pedido> pedidos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPreferencial() {
		return preferencial;
	}

	public void setPreferencial(Boolean preferencial) {
		this.preferencial = preferencial;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
