package br.com.RelatorioAPIComponent.renderer;

import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final List<JButton> buttons = Arrays.asList(new JButton("v"),
			new JButton("e"));

	public ButtonsPanel() {
		super();
		setOpaque(true);
		for (JButton b : buttons) {
			b.setFocusable(false);
			b.setRolloverEnabled(false);
			add(b);
		}
	}
}
