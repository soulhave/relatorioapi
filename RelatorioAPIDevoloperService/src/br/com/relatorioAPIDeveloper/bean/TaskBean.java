package br.com.relatorioAPIDeveloper.bean;

import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Task")
public class TaskBean extends DefaultBean {
	@XStreamAlias("task")
	private String task;
	@XStreamAlias("datainicio")
	private Date dataInicio;
	@XStreamAlias("datafim")
	private Date DataFim;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return DataFim;
	}

	public void setDataFim(Date dataFim) {
		DataFim = dataFim;
	}

	@Override
	public Object getId() {
		return getTask();
	}

}
