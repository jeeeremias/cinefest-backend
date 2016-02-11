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
	@JsonIgnore
	private Filme filme;

	public Voto() {
		super();
	}

}
