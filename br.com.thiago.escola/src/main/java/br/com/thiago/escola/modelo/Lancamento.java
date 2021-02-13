package br.com.thiago.escola.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "Lancamento", schema = "public")
@SequenceGenerator(name = "lancamento_seq", sequenceName = "lancamento_seq", initialValue = 1, allocationSize = 1)
public class Lancamento implements Serializable {

	private static final int VALOR_DO_DIVISOR = 3;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lancamento_seq")
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@Getter
	@Setter
	@NotNull(message = "Nota1: campo obrigatório")
	@Column(name = "nota1", nullable = false, precision = 10, scale = 2)
	private BigDecimal nota1;

	@Getter
	@Setter
	@NotNull(message = "Nota2: campo obrigatório")
	@Column(name = "nota2", nullable = false, precision = 10, scale = 2)
	private BigDecimal nota2;

	@Getter
	@Setter
	@NotNull(message = "Nota3: campo obrigatório")
	@Column(name = "nota3", nullable = false, precision = 10, scale = 2)
	private BigDecimal nota3;

	@Getter
	@Column(name = "media", nullable = false)
	private String media;

	@NotNull(message = "fk_aluno: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_aluno")
	@Getter
	@Setter
	@JoinColumn(name = "codigo_aluno", referencedColumnName = "codigo", nullable = false)
	private Aluno aluno;

	@NotNull(message = "fk_professor: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_professor")
	@Getter
	@Setter
	@JoinColumn(name = "codigo_professor", referencedColumnName = "codigo", nullable = false)
	private Professor professor;

	@NotNull(message = "fk_materia: campo Obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "fk_materia")
	@Getter
	@Setter
	@JoinColumn(name = "codigo_materia", referencedColumnName = "codigo", nullable = false)
	private Materia materia;

	public Lancamento() {
	}

	public Lancamento(Long codigo, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3, String media, Aluno aluno,
			Professor professor, Materia materia) {
		this.codigo = codigo;
		this.nota1 = nota1;
		this.nota2 = nota2;
		this.nota3 = nota3;
		this.media = media;
		this.aluno = aluno;
		this.professor = professor;
		this.materia = materia;
	}

	public boolean isEditado() {
		return this.getCodigo() != null;
	}

	// calcula a média da nota
	public String calculaMedia() {
		BigDecimal resultado = BigDecimal.ZERO;
		DecimalFormat dcf = new DecimalFormat();

		resultado = resultado.add(nota1.add(nota2).add(nota3).divide(BigDecimal.valueOf(VALOR_DO_DIVISOR)));

		dcf.setMaximumFractionDigits(2);
		return this.media = dcf.format(resultado);

	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Código: ").append(codigo)
				       .append(", Nota1: ").append(nota1)
				       .append(", Nota2").append(nota2)
				       .append(", Nota3").append(nota3)
				       .append(", Média").append(media)
				       .append(", Aluno: ").append(aluno)
				       .append(", Professor").append(professor)
				       .append(" Máteria: ").append(materia);
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
		Lancamento other = (Lancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
