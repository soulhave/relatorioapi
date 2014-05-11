package br.com.relatorioAPIDeveloper.DAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.relatorioAPIDeveloper.bean.DefaultBean;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlMap;


public abstract class DefaultDAO<T extends DefaultBean> {

	private static final String DATA_BASE_STORAGE = "../xml"+File.separator+"RAPID";

	private XmlMap xmlMap;
	
	public DefaultDAO() {
		PersistenceStrategy strategy = new FilePersistenceStrategy(new File(DATA_BASE_STORAGE));
		xmlMap = new XmlMap(strategy);
	}
	
	public void create(T t) {
		xmlMap.put(t.getId(), t);
	}

	public void delete(T t) {
		xmlMap.remove(t.getId());
	}

	public void update(T t) {
		xmlMap.put(t.getId(), t);
	}

	@SuppressWarnings("unchecked")
	public T findByKey(Object id) {
		return ((T) xmlMap.get(id));
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return new ArrayList<>(xmlMap.values()) ;
	}

}
