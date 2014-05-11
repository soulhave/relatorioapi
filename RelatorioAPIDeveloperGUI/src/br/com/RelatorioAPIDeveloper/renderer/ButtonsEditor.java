package br.com.RelatorioAPIDeveloper.renderer;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

import br.com.RelatorioAPIComponent.renderer.ButtonsPanel;
import br.com.RelatorioAPIDeveloper.delegate.DrawDelegate;
import br.com.RelatorioAPIDeveloper.delegate.RelatorioDelegate;

public class ButtonsEditor extends ButtonsPanel implements TableCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonsEditor(final JTable table) {
		super();

		MouseListener ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ButtonModel m = ((JButton) e.getSource()).getModel();
				if (m.isPressed()
						&& table.isRowSelected(table.getEditingRow())
						&& e.isControlDown()) {
					setBackground(table.getBackground());
				}
			}
		};
		buttons.get(0).addMouseListener(ml);
		buttons.get(1).addMouseListener(ml);

		buttons.get(0).addActionListener(new ActionListener() { //Evento Botão A
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.convertRowIndexToModel(table.getEditingRow());
				Object o = table.getModel().getValueAt(row, 0);
				try {
					final String drawReport = DrawDelegate.getInstance().drawReport(RelatorioDelegate.getInstance().findById(o));
					File file = new File(drawReport);
					file = new File(file.getCanonicalPath());
					Desktop.getDesktop().browse(file.toURI());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				fireEditingStopped();
			}
		});

		buttons.get(1).addActionListener(new ActionListener() { //Evento botão B
			@Override
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(table, "Editing: " + o);
//				int row = table.convertRowIndexToModel(table.getEditingRow());
//				Object o = table.getModel().getValueAt(row, 0);
				fireEditingStopped();
				JOptionPane.showMessageDialog(table, "Função Excluir sendo implementada.");
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fireEditingStopped();
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table,
			Object value, boolean isSelected, int row, int column) {
		this.setBackground(table.getSelectionBackground());
		return this;
	}

	@Override
	public Object getCellEditorValue() {
		return "";
	}

	// Copid from AbstractCellEditor
	// protected EventListenerList listenerList = new EventListenerList();
	transient protected ChangeEvent changeEvent = null;

	@Override
	public boolean isCellEditable(EventObject e) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		fireEditingStopped();
		return true;
	}

	@Override
	public void cancelCellEditing() {
		fireEditingCanceled();
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		listenerList.add(CellEditorListener.class, l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		listenerList.remove(CellEditorListener.class, l);
	}

	public CellEditorListener[] getCellEditorListeners() {
		return listenerList.getListeners(CellEditorListener.class);
	}

	protected void fireEditingStopped() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == CellEditorListener.class) {
				// Lazily create the event:
				if (changeEvent == null)
					changeEvent = new ChangeEvent(this);
				((CellEditorListener) listeners[i + 1])
						.editingStopped(changeEvent);
			}
		}
	}

	protected void fireEditingCanceled() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == CellEditorListener.class) {
				// Lazily create the event:
				if (changeEvent == null)
					changeEvent = new ChangeEvent(this);
				((CellEditorListener) listeners[i + 1])
						.editingCanceled(changeEvent);
			}
		}
	}
}
