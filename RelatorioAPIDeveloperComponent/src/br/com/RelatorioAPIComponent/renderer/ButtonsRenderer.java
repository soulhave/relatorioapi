package br.com.RelatorioAPIComponent.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonsRenderer extends ButtonsPanel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public ButtonsRenderer() {
		super();
		setName("Table.cellRenderer");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		this.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		return this;
	}
}
