package br.com.RelatorioAPIDeveloper.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.RelatorioAPIComponent.renderer.ButtonsRenderer;
import br.com.RelatorioAPIDeveloper.delegate.RelatorioDelegate;
import br.com.RelatorioAPIDeveloper.renderer.ButtonsEditor;
import br.com.relatorioAPIDeveloper.bean.ReportBean;

public class ListaReportGUI extends JDialog{
//http://java-swing-tips.blogspot.com.br/2009/10/multiple-jbuttons-in-jtable-cell.html
	/**
	 * 
	 */
	private static final long serialVersionUID = -7477342019557605183L;
	private JTable table;
	
	public ListaReportGUI() {
		setTitle("Relatórios");
		getContentPane().setLayout(null);
		
		setSize(720,500);  
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBounds(10, 11, 686, 427);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(470, 400));
		table.setAutoCreateRowSorter(true);
		table.setBorder(null);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setModel(getNewTableModel());
		columnStyle();
		scrollPane.setViewportView(table);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{table, getContentPane(), scrollPane}));
		refreshTable();
		
	}

	/**
	 * 
	 */
	private void columnStyle() {
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(549);

		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(74);
		TableColumn column = table.getColumnModel().getColumn(1);
        column.setCellRenderer(new ButtonsRenderer());
        column.setCellEditor(new ButtonsEditor(table));
	}

	/**
	 * 
	 * @return
	 */
	private DefaultTableModel getNewTableModel() {
		return new DefaultTableModel(
			null,
			new String[] {
				"RELATÓRIOS", "AÇÕES"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	}
	
	/*Atualiza Table*/
	public void refreshTable() {
		final DefaultTableModel model = getNewTableModel();
		final List<ReportBean> findAllRelatorio = RelatorioDelegate.getInstance().findAllRelatorio();
		
		for (ReportBean t : findAllRelatorio) {
			   	Object[] data = new String[2];
	            data[0] = t.getReport();
				data[1] = "";
	            model.addRow(data);
		}
		
		model.fireTableDataChanged();
		getTable().setModel(model);
		columnStyle();
		getTable().updateUI();
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}