package br.com.relatorioAPIDeveloper.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("report")
public class ReportBean extends DefaultBean {
	
	@XStreamAlias("report")
	private String report;
	@XStreamAlias("executado")
	private Boolean executado;
	@XStreamAlias("tasks")
	private List<TaskBean> tasks;

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public Boolean getExecutado() {
		return executado;
	}

	public void setExecutado(Boolean executado) {
		this.executado = executado;
	}

	public List<TaskBean> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskBean> tasks) {
		this.tasks = tasks;
	}

	@Override
	public Object getId() {
		return getReport();
	}
}
