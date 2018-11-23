package team.boolbee.poc.hibernate.interceptors;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.type.Type;

import team.boolbee.poc.hibernate.interceptors.model.MissingDefaultCtorObject;
import team.boolbee.poc.hibernate.interceptors.model.Sale;

// Ocasionalmente, es posible encontrarse con la necesidad de mantener de forma persistente
// un POJO que no disponga de un constructor predeterminado.

// Hibernate exige que todos los POJO que se almacenen deben proporcionar un constructor
// predeterminado. Utiliza la reflexión para construir un objeto antes de realizar el poblado de datos.
// La forma mas sencilla de hacerlo es con un constructor sin argumentos.

// Sin embargo, se pueden encontrar situaciones en las que se está trabajando con clases de terceros
// para las que no se dispone de código fuente o con grandes cuerpos de objetos generados para los cuales
// es extremadamente incómodo administrar todos los cambios en el código fuente.

// En estas circunstancias, es posible utilizar un mecanismo interceptor en la configuración de hibernate
// para reemplazar la lógica predeterminada de creación de objetos. Esta técnica se puede utilizar siempre
// que exista alguna forma de instanciar los objetos POJO alternativa al constructor por defecto.

public class InstantiationInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 5940476829416689908L;

	@Override
	public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
		if (entityName.equals(MissingDefaultCtorObject.class.getName())) {
			return new MissingDefaultCtorObject(null);
		} else {
			// Some other class - continue to default handling
			return super.instantiate(entityName, entityMode, id);
		}
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Sale) {
			System.out.println("Sales has");
		}

		return super.onSave(entity, id, state, propertyNames, types);
	}

}