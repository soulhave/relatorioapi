package br.com.RelatorioAPIDeveloper.gui;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.calendar.JDateSelector;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.RelatorioAPIDeveloper.delegate.RelatorioDelegate;
import br.com.relatorioAPIDeveloper.bean.ReportBean;
import br.com.relatorioAPIDeveloper.bean.TaskBean;

public class InserirTaskGUI extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7477342019557605183L;
	private JTextField nomeRelatorio;
	private JTable table;
	private JTextField tarefa;
	private JPanel relatorio;
	private JPanel tarefas;
	private JPanel acoes;
	private JDateSelector dataInicial;
	private JDateSelector dataFinal;
	private JButton btnLimpar;
	private Checkbox tarefasExecutadas;
	
	/*Report*/
	private ReportBean report;
	private List<TaskBean> tasks = new ArrayList<TaskBean>();
	
	public InserirTaskGUI() {
		setTitle("Inserir Tasks Relatório");
		getContentPane().setLayout(null);
		
		setSize(720,500);  
		setLocationRelativeTo(null);
		
		relatorio = new JPanel();
		relatorio.setBorder(new TitledBorder(null, "Relatório:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		relatorio.setBounds(10, 11, 686, 51);
		getContentPane().add(relatorio);
		relatorio.setLayout(null);
		
		JLabel lblNomeDoRelatrio = new JLabel("Nome do Relatório:");
		lblNomeDoRelatrio.setBounds(10, 16, 102, 20);
		relatorio.add(lblNomeDoRelatrio);
		
		nomeRelatorio = new JTextField();
		nomeRelatorio.setBounds(109, 16, 384, 20);
		relatorio.add(nomeRelatorio);
		nomeRelatorio.setColumns(10);
		
		tarefasExecutadas = new Checkbox("Preencher Tarefas Executadas?");
		tarefasExecutadas.setBounds(499, 16, 177, 22);
		relatorio.add(tarefasExecutadas);
		
		tarefas = new JPanel();
		tarefas.setBorder(new TitledBorder(null, "Adicionar Tarefas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tarefas.setBounds(10, 71, 441, 112);
		getContentPane().add(tarefas);
		tarefas.setLayout(null);
		
		JLabel lblTask = new JLabel("Tarefa:");
		lblTask.setBounds(12, 15, 53, 20);
		tarefas.add(lblTask);
		
		tarefa = new JTextField();
		tarefa.setColumns(10);
		tarefa.setBounds(70, 15, 360, 20);
		tarefas.add(tarefa);
		
		JLabel lblDataInicio = new JLabel("Data Ínicio:");
		lblDataInicio.setBounds(10, 46, 102, 20);
		tarefas.add(lblDataInicio);
		
		JLabel lblDataFim = new JLabel("Data fim:");
		lblDataFim.setBounds(10, 77, 102, 20);
		tarefas.add(lblDataFim);
		
		dataInicial = new JDateSelector();
		dataInicial.setBounds(70, 45, 160, 21);
		tarefas.add(dataInicial);
		
		dataFinal = new JDateSelector();
		dataFinal.setBounds(70, 76, 160, 21);
		tarefas.add(dataFinal);
		
		JButton btnAdd = new JButton("Adicionar"); //Adiciona Task
		btnAdd.addActionListener(actionInsertTask());
		btnAdd.setBounds(240, 74, 91, 23);
		tarefas.add(btnAdd);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(339, 74, 91, 23);
		tarefas.add(btnLimpar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 194, 686, 253);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(470, 400));
		table.setAutoCreateRowSorter(true);
		table.setBorder(null);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(getNewTableModel());
		columnStyle();
		scrollPane.setViewportView(table);
		
		acoes = new JPanel();
		acoes.setBounds(461, 73, 235, 110);
		getContentPane().add(acoes);
		acoes.setLayout(null);
		acoes.setBorder(new TitledBorder(null, "Ações", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton draw = new JButton("Draw");
		draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrawGUI drawGUI = new DrawGUI();
				drawGUI.setAlwaysOnTop(true);;
				drawGUI.setVisible(true);
			}
		});
		
		draw.setBounds(22, 63, 91, 23);
		acoes.add(draw);
		
		JButton salvar = new JButton("Salvar"); //Salva o relatório
		salvar.addActionListener(actionSalvar());
		salvar.setBounds(22, 29, 91, 23);
		acoes.add(salvar);
		
		JButton excluir = new JButton("Excluir");
		excluir.setBounds(121, 29, 91, 23);
		acoes.add(excluir);
		
		JButton sair = new JButton("Sair");
		sair.setBounds(121, 63, 91, 23);
		acoes.add(sair);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{nomeRelatorio, tarefa, dataInicial, dataFinal, btnAdd, btnLimpar, salvar, excluir, draw, sair, table, lblDataInicio, getContentPane(), relatorio, lblNomeDoRelatrio, tarefas, lblDataFim, scrollPane, acoes, lblTask}));
	}



	/*Actions Listener*/
	/**
	 * Insere uma nova task
	 * @return
	 */
	private ActionListener actionInsertTask() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TaskBean task = new TaskBean();
				task.setTask(getTarefa().getText()); //Nome da tarefa
				task.setDataInicio(getDataInicial().getSelectedDate()); //Data Inicial
				task.setDataFim(getDataFinal().getSelectedDate()); //Data Final
				tasks.add(task);
				
				/*Atualiza os dados*/
				refreshTable();
			}

		};
	}
	
	/**
	 * Salva os dados no XML
	 * @return
	 */
	private ActionListener actionSalvar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onSalvar();
			}
		};
	}

	private void onSalvar() {
		report = new ReportBean();
		report.setReport(nomeRelatorio.getText());
		report.setExecutado(tarefasExecutadas.getState());
		report.setTasks(tasks);
		RelatorioDelegate.getInstance().salvarRelatorio(report);
		JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
		this.setVisible(false);
	}
	
	/*Atualiza Table*/
	public void refreshTable() {
		final DefaultTableModel model = getNewTableModel();
		
		for (TaskBean t : tasks) {
			   String[] data = new String[4];
	            data[0] = t.getTask();
	            data[1] = t.getDataInicio().toString();
	            data[2] = t.getDataFim().toString();
	            data[3] = "Excluir";
	            model.addRow(data);
		}
		
		model.fireTableDataChanged();
		getTable().setModel(model);
		columnStyle();
		getTable().updateUI();
	}
	
	private void columnStyle() {
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(348);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(51);
	}


	private DefaultTableModel getNewTableModel() {
		return new DefaultTableModel(
			null,
			getHeader()
		) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	}


	private String[] getHeader() {
		return new String[] {
			"TAREFA", "DATA \u00CDNICIO", "DATA FIM", "EXCLUIR"
		};
	}
	
	/*Getter and setter*/
	public JTextField getNomeRelatorio() {
		return nomeRelatorio;
	}

	public void setNomeRelatorio(JTextField nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTarefa() {
		return tarefa;
	}

	public void setTarefa(JTextField tarefa) {
		this.tarefa = tarefa;
	}

	public JPanel getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(JPanel relatorio) {
		this.relatorio = relatorio;
	}

	public JPanel getTarefas() {
		return tarefas;
	}

	public void setTarefas(JPanel tarefas) {
		this.tarefas = tarefas;
	}

	public JPanel getAcoes() {
		return acoes;
	}

	public void setAcoes(JPanel acoes) {
		this.acoes = acoes;
	}

	public JDateSelector getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(JDateSelector dataInicial) {
		this.dataInicial = dataInicial;
	}

	public JDateSelector getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(JDateSelector dataFinal) {
		this.dataFinal = dataFinal;
	}

	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	public void setBtnLimpar(JButton btnLimpar) {
		this.btnLimpar = btnLimpar;
	}

	public ReportBean getReport() {
		return report;
	}

	public void setReport(ReportBean report) {
		this.report = report;
	}

	public List<TaskBean> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskBean> tasks) {
		this.tasks = tasks;
	}






	public Checkbox getTarefasExecutadas() {
		return tarefasExecutadas;
	}






	public void setTarefasExecutadas(Checkbox tarefasExecutadas) {
		this.tarefasExecutadas = tarefasExecutadas;
	}

}