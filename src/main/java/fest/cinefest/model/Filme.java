package fest.cinefest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Filme implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer idFilme;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String autor;
	
	@Column
	private String duracao;
	
	@OneToMany(mappedBy = "filme", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Imagem> imagens;
	
	public Filme() {
		super();
	}

	public Filme(String nome, String descrição, String autor, String duracao) {
		super();
		this.nome = nome;
		this.descricao = descrição;
		this.autor = autor;
		this.duracao = duracao;
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDuracao() {
		return duracao;
	}
	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}
	public List<Imagem> getImagens() {
		return imagens;
	}
	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	public void addImagem(Imagem imagem) {
		this.imagens.add(imagem);
	}
}
