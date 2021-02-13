package br.com.thiago.util.jsf;

public class Navegacao {

	public static String redirecionar(String url) {
		return url + "?faces-redirect=true";
	}
}
