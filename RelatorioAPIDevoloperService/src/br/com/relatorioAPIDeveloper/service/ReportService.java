package br.com.relatorioAPIDeveloper.service;

import java.util.List;

import br.com.relatorioAPIDeveloper.DAO.ReportDAO;
import br.com.relatorioAPIDeveloper.bean.ReportBean;

public class ReportService {

	public void salvar(ReportBean r) {
		// Fazer validações
		validacao();
		(new ReportDAO()).create(r);
	}

	public ReportBean findById(Object id) {
		return (new ReportDAO()).findByKey(id);
	}
	
	public List<ReportBean> findAll() {
		return (new ReportDAO()).findAll();
	}

	private boolean validacao() {
		return true;
	}

}
