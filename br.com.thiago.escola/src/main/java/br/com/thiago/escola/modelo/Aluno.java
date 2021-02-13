package br.com.thiago.escola.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Aluno", schema = "public")
@SequenceGenerator(name = "aluno_seq", sequenceName = "aluno_seq", initialValue = 1, allocationSize = 1)
public class Aluno extends Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aluno_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@Getter
	@Setter
	@OneToMany(mappedBy = "aluno")
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	public Aluno() {
	}

	public Aluno(String nome, String cpf, String email, Long codigo, List<Lancamento> lancamentos) {
		super(nome, cpf, email);
		this.codigo = codigo;
		this.lancamentos = lancamentos;
	}

	public boolean isEditado() {
		return this.getCodigo() != null;
	}
	
	
	// Polimorfismo
	@Override
	public String toString() {
     StringBuilder builder = new StringBuilder(super.toString())
    		 .append(", Codigo: ").append(codigo);
	return builder.toString();
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
