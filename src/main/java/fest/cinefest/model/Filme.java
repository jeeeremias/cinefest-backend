package fest.cinefest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Filme implements Serializable {
	
	@Id
	private Integer idFilme;
	
	@Column
	private String tipo;
	
	@Column
	private String nome;
	
	@Column
	private String cidade;
	
	@Column
	private String estado;
	
	@Column
	private Integer ano;
	
	@Column
	private String genero;
	
	@Column
	private String duracao;
	
	@Column
	private String dataExibicao;
	
	@Column
	private String horaExibicao;
	
	@Column
	private String diretor;
	
	@Column
	private String descricaoCurta;
	
	@Column(length = 5000)
	private String descricaoCompleta;
	
	@Column(length = 5000)
	private String curriculoDiretor;
	
	@Column
	private String emailDiretor;
	
	@OneToMany(mappedBy = "filme", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Imagem> imagens;

	@OneToMany(mappedBy = "filme", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Voto> votos;

	public Filme() {
		super();
	}

	public Filme(Integer idFilme, String tipo, String nome, String cidade,
			String estado, Integer ano, String genero, String duracao,
			String dataExibicao, String horaExibicao, String diretor,
			String descricaoCurta, String descricaoCompleta,
			String curriculoDiretor, String emailDiretor) {
		super();
		this.idFilme = idFilme;
		this.tipo = tipo;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
		this.ano = ano;
		this.genero = genero;
		this.duracao = duracao;
		this.dataExibicao = dataExibicao;
		this.horaExibicao = horaExibicao;
		this.diretor = diretor;
		this.descricaoCurta = descricaoCurta;
		this.descricaoCompleta = descricaoCompleta;
		this.curriculoDiretor = curriculoDiretor;
		this.emailDiretor = emailDiretor;
	}

	public Integer getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public String getDataExibicao() {
		return dataExibicao;
	}

	public void setDataExibicao(String dataExibicao) {
		this.dataExibicao = dataExibicao;
	}

	public String getHoraExibicao() {
		return horaExibicao;
	}

	public void setHoraExibicao(String horaExibicao) {
		this.horaExibicao = horaExibicao;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getDescricaoCurta() {
		return descricaoCurta;
	}

	public void setDescricaoCurta(String descricaoCurta) {
		this.descricaoCurta = descricaoCurta;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public String getCurriculoDiretor() {
		return curriculoDiretor;
	}

	public void setCurriculoDiretor(String curriculoDiretor) {
		this.curriculoDiretor = curriculoDiretor;
	}

	public String getEmailDiretor() {
		return emailDiretor;
	}

	public void setEmailDiretor(String emailDiretor) {
		this.emailDiretor = emailDiretor;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}
}
