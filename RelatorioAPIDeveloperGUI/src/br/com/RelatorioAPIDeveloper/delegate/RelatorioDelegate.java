package br.com.RelatorioAPIDeveloper.delegate;

import java.util.List;

import br.com.relatorioAPIDeveloper.bean.ReportBean;
import br.com.relatorioAPIDeveloper.service.ReportService;

public class RelatorioDelegate {

	private static RelatorioDelegate S_INSTANCE; 
	private static ReportService REPORT_SERVICE;
	
	public RelatorioDelegate() {
		REPORT_SERVICE = new ReportService();
	}
	
	public void salvarRelatorio(ReportBean b) {
		REPORT_SERVICE.salvar(b);
	}
	
	public List<ReportBean> findAllRelatorio() {
		return REPORT_SERVICE.findAll();
	}

	public ReportBean findById(Object id) {
		return REPORT_SERVICE.findById(id);
	}
	
	public static RelatorioDelegate getInstance(){
		if(S_INSTANCE==null){
			S_INSTANCE = new RelatorioDelegate();
		}
		
		return S_INSTANCE;
	}
	
}
