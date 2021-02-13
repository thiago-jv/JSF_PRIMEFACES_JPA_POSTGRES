package br.com.thiago.escola.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@NotEmpty(message = "Nome: campo obrigatório")
	@Column(name = "nome", length = 90, nullable = false)
	private String nome;

	@Getter
	@Setter
	@NotEmpty(message = "Cpf: campo obrigatório")
	@Size(min = 11, max = 11, message = "Valor máximo {max} e mínimo {min} de caracteres")
	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;

	@Getter
	@Setter
	@Email(message = "E-mal incorreto")
	@NotEmpty(message = "Email: campo obrigatório")
	@Size(min = 1, max = 90, message = "Valor máximo {max} e mínimo {min} de caracteres")
	@Column(name = "email", nullable = false, length = 90)
	private String email;

	// construtor sem parametros
	public Pessoa() {
	}

	// constutor com parametros
	public Pessoa(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	// toString para imprimir os objetos
	@Override
	public String toString() {
		StringBuilder info = new StringBuilder("Nome: ").append(nome)
				                       .append(", Cpf: ").append(cpf)
				                       .append(", Email: ").append(email);
    return info.toString();
	}
	
	// Equals para comparação de objetos
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Pessoa) {
			Pessoa p = (Pessoa) obj;// cast para Pessoa
			if(this.nome.equals(p.getNome()) 
			  && this.cpf.equals(p.getCpf()) 
		        && this.email.equals(p.getEmail())) {
				 return true;
			}
		}
		return false;
	}
}

