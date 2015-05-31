package models;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.beanutils.BeanUtils;

@MappedSuperclass
public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9127819341547937253L;
	
	@Column(nullable=false, name="registro_ativo")
	protected Boolean registroAtivo = true;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_atualizacao")
	protected Date dataAtualizacao = new Date();
	

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Boolean getRegistroAtivo() {
		return registroAtivo;
	}

	public void setRegistroAtivo(Boolean registroAtivo) {
		this.registroAtivo = registroAtivo;
	}

	@Override
	public Object clone() {
		try {
			Object obj = new Object();
			BeanUtils.copyProperties(obj, this);
			return obj;
		} catch (IllegalAccessException e) {
			return null;
		} catch (InvocationTargetException e) {
			return null;
		}
	}

}
