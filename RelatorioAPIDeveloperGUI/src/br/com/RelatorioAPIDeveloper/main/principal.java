package br.com.RelatorioAPIDeveloper.main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.RelatorioAPIDeveloper.gui.InserirTaskGUI;
import br.com.RelatorioAPIDeveloper.gui.ListaReportGUI;

public class principal {
	public static void main(String[] args) {
		 if (!SystemTray.isSupported()) {
		      System.out.println("SystemTray is not supported");
		      return;
		    }

		    SystemTray tray = SystemTray.getSystemTray();
		    Toolkit toolkit = Toolkit.getDefaultToolkit();
		    Image image = toolkit.getImage("img/icon.png");

		    PopupMenu menu = new PopupMenu();

		    MenuItem incluirRelatorio = new MenuItem("Incluir Relatório");
		    incluirRelatorio.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  (new InserirTaskGUI()).setVisible(true);
		      }
		    });
		    menu.add(incluirRelatorio);
		    
		    MenuItem listarRelatorio = new MenuItem("Listar Relatórios");
		    listarRelatorio.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		(new ListaReportGUI()).setVisible(true);
		    	}
		    });
		    menu.add(listarRelatorio);

		    menu.addSeparator();
		    
		    MenuItem sair = new MenuItem("Sair");
		    sair.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		      }
		    });
		    
		    menu.add(sair);
		    TrayIcon icon = new TrayIcon(image, "Sistema de Geração de TimeLine", menu);
		    icon.setImageAutoSize(true);

		    try {
				tray.add(icon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
	}
}
