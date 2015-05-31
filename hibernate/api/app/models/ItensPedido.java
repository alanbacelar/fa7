package models;


import javax.persistence.*;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="itens_pedido_id_seq")
@Table(name = "itens_pedido")
public class ItensPedido extends BaseModel {

    private static final long serialVersionUID = -1339717517200607306L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_item")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="id_produto", nullable = false)
    @Basic(optional = false)
    private Produto produto;
   
    @Column(name = "quantidade")
    @Basic(optional = false)
    private Integer quantidade;
    
    @Transient
    private Double total;
    

    public ItensPedido() {
		// TODO Auto-generated constructor stub
	}

    public Double getTotal() {

        if(total == null){
            setTotal();
        }

        return total;
	}
	
	public void setTotal(){
		this.total = this.getProduto().getPreco()*this.getQuantidade();
	}
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
  
}