package entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TELEFONE")
public class Telefone {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "S_TELEFONE")
	@SequenceGenerator(name = "S_TELEFONE", sequenceName = "S_TELEFONE", allocationSize = 1)
	private long id;

	@Column(name = "ddd")
	private int ddd;

	@Column(name = "numero")
	private String numero;

	@Column(name = "tipo")
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "email_pessoa", referencedColumnName = "email", nullable = false)
	private Pessoa pessoa;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
