package br.com.RelatorioAPIDeveloper.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import br.com.RelatorioAPIDeveloper.delegate.DrawDelegate;
import br.com.relatorioAPIDeveloper.bean.ReportBean;

public class DrawGUI extends JDialog{
//http://alvinalexander.com/blog/post/jfc-swing/how-create-simple-swing-html-viewer-browser-java
	/**
	 * 
	 */
	private static final long serialVersionUID = -7477342019557605183L;
	private JEditorPane htmlView;
	private JButton sair;
	private JButton copy;
	
	public DrawGUI() {

	}
	
	public DrawGUI(ReportBean b){
		super();
		setTitle("Desenho Report");
		getContentPane().setLayout(null);
		
		htmlView = new JEditorPane();
		htmlView.setBounds(10, 11, 684, 406);
		getContentPane().add(htmlView);
		
		copy = new JButton("Copiar");
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		copy.setBounds(259, 428, 91, 23);
		getContentPane().add(copy);
		
		sair = new JButton("Sair");
		sair.setBounds(360, 428, 91, 23);
		getContentPane().add(sair);
		
		setSize(720,500);  
		setLocationRelativeTo(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane()}));
		HTMLDocument doc = new HTMLDocument();
		HTMLEditorKit kit = new HTMLEditorKit();
		try {
			final String drawReport = DrawDelegate.getInstance().drawReport(b);
			System.out.println(drawReport);
			kit.insertHTML(doc, doc.getLength(), drawReport, 0, 0, null);
		} catch (BadLocationException | IOException e) {
			e.printStackTrace();
		}
		htmlView.setEditorKit(kit);
		htmlView.setDocument(doc);
//		htmlView.setText();
		//htmlView.setVisible(true);
		//htmlView.setEditable(false);
	}

	public JEditorPane getHtmlView() {
		return htmlView;
	}

	public void setHtmlView(JEditorPane htmlView) {
		this.htmlView = htmlView;
	}

	public JButton getSair() {
		return sair;
	}

	public void setSair(JButton sair) {
		this.sair = sair;
	}

	public JButton getCopy() {
		return copy;
	}

	public void setCopy(JButton copy) {
		this.copy = copy;
	}

}