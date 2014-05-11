package br.com.relatorioAPIDeveloper.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilitario {
	
	/**
	 * Ler arquivo de texto e retorna String com dados lidos. 
	 * @param arq
	 * @return
	 */
	public static String lerTexto(String arq) {
		File file = new File(arq);

		try {
			// Indicamos o arquivo que será lido
			FileReader fileReader = new FileReader(file);

			// Criamos o objeto bufferReader que nos
			// oferece o método de leitura readLine()
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha = "";
			String texto = "";

			while ((linha = bufferedReader.readLine()) != null) {
				texto += linha;
			}

			fileReader.close();
			bufferedReader.close();
			return texto;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/**
	 * Grava o arquivo em texto no local 
	 * passado como parametro
	 * @param file
	 * @param texto
	 */
	public static void escreverTexto(String file,String texto) {
		File arquivo = new File(file);  
        FileOutputStream fos;
		try {
			fos = new FileOutputStream(arquivo);
			fos.write(texto.getBytes());  
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public static Calendar dateToCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	public static Date getDateNow() {
		return new Date(System.currentTimeMillis());
	}
	
	public static String dateFormat(Date now){
		final DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS0");
		return format.format(now);
	}
}
