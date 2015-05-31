package utils;

import java.io.Serializable;
import java.util.Calendar;

import models.BaseModel;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class AuditorInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2605490249583898526L;
	
	private Object atualizarDataRegistro(Object entity, String[] propertyNames){
		BaseModel base = (BaseModel) entity;
		
		for (int i = 0; i < propertyNames.length; i++) {
			if ("dataAtualizacao".equals(propertyNames[i])) {
				
				Calendar dt = Calendar.getInstance();
				
				System.out.println("Data="+dt.getTime());
				
				base.setDataAtualizacao(dt.getTime());
				
				System.out.println("Base data="+base.getDataAtualizacao());
			}
		}
		return base;
	}
	
	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		entity = atualizarDataRegistro(entity, propertyNames);
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}
	
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		// TODO Auto-generated method stub
		
		entity = atualizarDataRegistro(entity, propertyNames);
		return super.onSave(entity, id, state, propertyNames, types);
	}

}
