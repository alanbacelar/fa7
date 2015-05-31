package models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@SequenceGenerator(initialValue=1, name="SEQUENCE", sequenceName="categoria_id_seq")
@Table(name = "categoria")
public class Categoria extends BaseModel {

    public Categoria() {

    }

    public Categoria(Integer id) {
        this.id = id;
    }


    private static final long serialVersionUID = -1339717517200607306L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "nome")
    @Basic(optional = false)
    private String nome;


    @ManyToMany(
            cascade = {CascadeType.PERSIST},
            mappedBy = "categorias",
            fetch = FetchType.LAZY,
            targetEntity = Produto.class
    )
    private List<Produto> produtos;

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

}