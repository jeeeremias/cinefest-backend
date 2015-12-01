package fest.cinefest.model;

import java.util.List;

public class Filme {
	private int id;
	private String nome;
	private String descricao;
	private String autor;
	private String duracao;
	private List<Integer> imagens;
	
	public Filme(String nome, String descrição, String autor, String duracao) {
		super();
		this.nome = nome;
		this.descricao = descrição;
		this.autor = autor;
		this.duracao = duracao;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public List<Integer> getImagens() {
		return imagens;
	}
	public void setImagens(List<Integer> imagens) {
		this.imagens = imagens;
	}
}
