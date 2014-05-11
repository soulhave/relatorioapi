package br.com.relatorioAPIDeveloper.service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import br.com.relatorioAPIDeveloper.bean.ReportBean;
import br.com.relatorioAPIDeveloper.bean.TaskBean;
import br.com.relatorioAPIDeveloper.utils.Utilitario;


public class DrawService {

	private static final String RESTANTE = "RESTANTE";
	private static final String EXECUTADO = "EXECUTADO";
	private static final String PLANEJADO = "PLANEJADO";
	private static final String DRAW_REPLACE = "@{drawReplace}";
	private final static String TEMPLATE = "html"+File.separator+"template.html";
	private final static String FAKE_HTML = "../html"+File.separator;
	
	/**
	 * Desennha todo o relatório.
	 * @param b
	 */
	public String drawReport(ReportBean b){
		final String texto = Utilitario.lerTexto(TEMPLATE);
		String html = "";
		if(b.getExecutado()){
			html = printReportExecuted(texto,b);
		}else{
			html = printReportPlain(texto,b);
		}
		
		final String dateFormat = FAKE_HTML+Utilitario.dateFormat(Utilitario.getDateNow())+".html";
		Utilitario.escreverTexto(dateFormat, html);
		
		return dateFormat;
	}

	/**
	 * Printa relatório planejado.
	 * @param t template
	 * @param b Report Bean
	 * @return
	 */
	private String printReportPlain(String t, ReportBean b) {
		String linhaTratamento = "";
		
		for(TaskBean task:b.getTasks()){
			linhaTratamento+=montaLinha(task.getTask(),PLANEJADO,task.getDataInicio(),task.getDataFim());
		}
		
		return replace(t, linhaTratamento);
	}

	/**
	 * Realiza o Replace do Draw
	 * @param t template
	 * @param retorno dados tratados
	 * @return dados preparados.
	 */
	private String replace(String t, String retorno) {
		retorno = retorno.substring(0, retorno.length()-1);
		retorno = t.replace(DRAW_REPLACE, retorno);
		return retorno;
	}

	/**
	 * Printa relatório desejado com situação atual consolidado.
	 * Colocando de cada cor os prazos que já foram executados
	 * @param t template
	 * @param b report tratados
	 * @return retorno de dados preparados
	 */
	private String printReportExecuted(String t, ReportBean b) {
		String linhaRetorno = "";
		final Date dataAtual = Utilitario.getDateNow();
		
		for(TaskBean task:b.getTasks()){
			/*
			 * 1-Se Data Inicio Menor que data atual e data atual menor que data
			 * fim: Monta Linha Executado para Inicio até hoje, Monta linha hoje
			 * até a data fim para o restante.
			 */
			if(task.getDataInicio().before(dataAtual) && task.getDataFim().after(dataAtual)){
				linhaRetorno+=montaLinha(task.getTask(),EXECUTADO,task.getDataInicio(),dataAtual);
				linhaRetorno+=montaLinha(task.getTask(),RESTANTE,dataAtual,task.getDataFim());
				continue;
			}
			/*
			 * 2-Se Data Inicio Menor que data atual e data atual maior que data
			 * fim: Monta uma linha somente com todo o executado.
			 */
			if(task.getDataInicio().before(dataAtual) && task.getDataFim().before(dataAtual)){
				linhaRetorno+=montaLinha(task.getTask(),EXECUTADO,task.getDataInicio(),task.getDataFim());
				continue;
			}
			/*
			 * 4-Se data inicio Maior que data atual e data final menor do que
			 * data atual: Monta uma linha como todos a executar.
			 */
			if(task.getDataInicio().after(dataAtual) && task.getDataFim().after(dataAtual)){
				linhaRetorno+=montaLinha(task.getTask(),PLANEJADO,dataAtual,task.getDataFim());
				continue;
			}
			/* 3-Se Data Inicio Maior que data Final Não insere linha */
			if(task.getDataInicio().before(task.getDataFim())){
				System.out.println("ERRO:NÃO TEM COMO DATA INICIO SER MAIOR QUE DATA FIM!");
			}
		}
		return replace(t, linhaRetorno);
	}

	/**
	 * monta linha padrão
	 * @param task
	 * @param situacao
	 * @param dataInicio
	 * @param dataFim
	 * @return monta
	 */
	private String montaLinha(String task, String situacao, Date dataInicio, Date dataFim){
		return "['"+task.trim()+"','"+situacao.trim()+"',new Date("+anoMesDia(dataInicio)+"),new Date("+anoMesDia(dataFim)+")],";
	}
	
	/**
	 * Obtem String de ano mes e dia, no formato desejado
	 * @param date
	 * @return string no formato AAAA,MM,DD
	 */
	private String anoMesDia(Date date){
		String retorno = "";
		final Calendar cal = Utilitario.dateToCalendar(date);
		retorno += cal.get(Calendar.YEAR)+",";
		retorno += cal.get(Calendar.MONTH)+",";
		retorno += cal.get(Calendar.DAY_OF_MONTH);
		return retorno; 
	}
	
}