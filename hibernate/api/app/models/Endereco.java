package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "endereco")
public class Endereco extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = -8329398961902370895L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer id;
    
    @Column(name = "logradouro")
    @Basic(optional = false)
    private String logradouro;
    
    @Column(name = "numero")
    @Basic(optional = false)
    private Integer numero;

    @Column(name = "cidade")
    @Basic(optional = false)
    private String cidade;

    @Column(name = "uf")
    @Basic(optional = false)
    private String uf;
    
    @Column(name = "cep")
    @Basic(optional = false)
    private String cep;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}