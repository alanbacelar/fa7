package models;

import play.data.format.Formats;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="SEQUENCE", sequenceName="visita_id_seq")
@Table(name = "visita")
public class Visita extends BaseModel {

    private static final long serialVersionUID = -5672269537992623726L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "id_visita")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @Basic(optional = false)
    private Agenda agenda;

    @Column(name = "latitude")
    @Basic(optional = false)
    private Double latitude = 0.0;

    @Column(name = "longitude")
    @Basic(optional = false)
    private Double longitude = 0.0;

    @Temporal(TemporalType.TIMESTAMP)
    @Formats.DateTime(pattern = "YYYY-MM-dd HH:mm:ss")
    @Column(name = "hora_inicio")
    @Basic(optional = false)
    protected Date horaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Formats.DateTime(pattern = "YYYY-MM-dd HH:mm:ss")
    @Column(name = "hora_fim")
    @Basic(optional = false)
    protected Date horaFim;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }
}