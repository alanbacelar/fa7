package models;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="pedido_id_seq")
@Table(name = "pedido")
public class Pedido extends BaseModel {

    private static final long serialVersionUID = -1339717517200607306L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_pedido")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_vendedor", nullable = false)
    private Vendedor vendedor;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_cliente", nullable = false)
    private Cliente cliente;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", fetch=FetchType.LAZY)
    private List<ItensPedido> itensPedido;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

	public List<ItensPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	
    
    
}