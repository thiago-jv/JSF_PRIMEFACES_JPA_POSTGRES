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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Materia", schema = "public")
@SequenceGenerator(name = "materia_seq", sequenceName = "materia_seq", initialValue = 1, allocationSize = 1)
public class Materia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materia_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@Getter
	@Setter
	@NotEmpty(message = "Descrição: campo obrigatório")
	@Size(min = 1, max = 90, message = "Valor máximo {max} e mínimo {min} de caracteres")
	@Column(name = "descricao", nullable = false, length = 90)
	private String descricao;

	@Getter
	@Setter
	@OneToMany(mappedBy = "materia")
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	public Materia() {
	}

	public Materia(Long codigo, String descricao, List<Lancamento> lancamentos) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.lancamentos = lancamentos;
	}

	public boolean isEditado() {
		return this.codigo != null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Código: ").append(codigo)
				       .append(", Descrição").append(descricao);
		return  builder.toString();
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
		Materia other = (Materia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
