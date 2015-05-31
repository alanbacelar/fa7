package models;

import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(initialValue=1, name="SEQUENCE", sequenceName="produto_id_seq")
@Table(name = "produto")
public class Produto extends BaseModel {

    private static final long serialVersionUID = -1339717517200607306L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_produto")
    private Integer id;

    @Column(name = "nome")
    @Basic(optional = false)
    private String nome;

    @Column(name = "preco")
    @Basic(optional = false)
    private Double preco;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name="id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @Column(name = "estoque")
    @Basic(optional = false)
    private Integer estoque;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "produto")
    private Set<ItensPedido> itensPedidos;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            targetEntity=models.Categoria.class
    )
    @JoinTable(
            name="produto_categoria",
            joinColumns=@JoinColumn(name="id_produto"),
            inverseJoinColumns=@JoinColumn(name="id_categoria")
    )
    private List<Categoria> categorias;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Set<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(Set<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}