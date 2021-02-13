package br.com.thiago.escola.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Professor", schema = "public")
@SequenceGenerator(name = "professor_seq", sequenceName = "professor_seq", initialValue = 1, allocationSize = 1)
public class Professor extends Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@Getter
	@Setter
	@NotNull(message = "Salário: campo obrigatório")
	@Column(name = "salario", precision = 10, scale = 2)
	private BigDecimal salario;

	@Getter
	@Setter
	@OneToMany(mappedBy = "professor")
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	public Professor() {
	}

	public Professor(String nome, String cpf, String email, Long codigo, BigDecimal salario,
			List<Lancamento> lancamentos) {
		super(nome, cpf, email);
		this.codigo = codigo;
		this.salario = salario;
		this.lancamentos = lancamentos;
	}

	public boolean isEditado() {
		return this.getCodigo() != null;
	}
	
	// Polimorfismo
	@Override
	public String toString() {
	     StringBuilder builder = new StringBuilder(super.toString())
	    		 .append(", Codigo: ").append(codigo)
	    		 .append(", Salário: ").append(salario);
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
		Professor other = (Professor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
