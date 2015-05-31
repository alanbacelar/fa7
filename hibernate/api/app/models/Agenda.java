package models;

import play.data.format.Formats;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="agenda_id_seq")
@Table(name = "agenda", uniqueConstraints = @UniqueConstraint(columnNames={"id_vendedor", "id_cliente", "data"}))
public class Agenda extends BaseModel {

    private static final long serialVersionUID = 3322492327382107596L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_agenda")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_vendedor", nullable = false)
    private Vendedor vendedor;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_cliente", nullable = false)
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    @Formats.DateTime(pattern = "YYYY-MM-dd HH:mm:ss")
    @Column(name = "data", unique = true)
    @Basic(optional = false)
    protected Date data;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda", fetch=FetchType.LAZY)
    private List<Visita> visitasAgendadas;

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

	public List<Visita> getVisitasAgendadas() {
		return visitasAgendadas;
	}

	public void setVisitasAgendadas(List<Visita> visitasAgendadas) {
		this.visitasAgendadas = visitasAgendadas;
	}
}