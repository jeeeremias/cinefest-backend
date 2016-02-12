package fest.cinefest.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Voto implements Serializable {

	@Id
	@GeneratedValue
	private Integer idVoto;
	
	@Column
	private String dia;
	
	@ManyToOne
	@JoinColumn(name = "idFilme")
	private Filme filme;

	public Voto() {
		super();
	}
	
	public Integer getIdVoto() {
		return idVoto;
	}

	public void setIdVoto(Integer idVoto) {
		this.idVoto = idVoto;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
}
