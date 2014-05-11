package br.com.relatorioAPIDeveloper.bean;

/**
 * Abstract Method
 * @author Mendes
 *
 */
public abstract class DefaultBean {
	public abstract Object getId();
	
	public final String getNameClass() {
		return getClass().getSimpleName();
	}
}
