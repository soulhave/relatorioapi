package br.com.RelatorioAPIDeveloper.delegate;

import br.com.relatorioAPIDeveloper.bean.ReportBean;
import br.com.relatorioAPIDeveloper.service.DrawService;

public class DrawDelegate {

	private static DrawDelegate S_INSTANCE; 
	private static DrawService DRAW_SERVICE;
	
	public DrawDelegate() {
		DRAW_SERVICE = new DrawService();
	}

	public String drawReport(ReportBean b){
		return DRAW_SERVICE.drawReport(b);
	}
	
	public static DrawDelegate getInstance(){
		if(S_INSTANCE==null){
			S_INSTANCE = new DrawDelegate();
		}
		
		return S_INSTANCE;
	}
	
}
