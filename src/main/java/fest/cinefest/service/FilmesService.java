package fest.cinefest.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import fest.cinefest.mock.FilmesMock;
import fest.cinefest.model.Filme;

@Service
public class FilmesService {

	@Autowired
    private JdbcTemplate jt;
	
	private final String selectFilmes = "SELECT nome, descricao, autor, duracao, id  FROM \"Filme\" ORDER BY nome LIMIT ? OFFSET ?;";
	private final String selectFilme = "SELECT nome, descricao, autor, duracao, id  FROM \"Filme\" WHERE id = ?;";
	private final String selectImagem = "SELECT id FROM \"Imagem\" WHERE id_filme = ?;";
	private final String selectIdFilme = "SELECT MAX(id)  FROM \"Filme\";";
	private final String insertFilme = "INSERT INTO \"Filme\"(nome, descricao, autor, duracao, id) VALUES (?, ?, ?, ?, ?);";
	private final String insertImagem = "INSERT INTO \"Imagem\"(id_filme, id) VALUES (?, ?);";

	public List<Filme> getFilmes(int limit, int offset) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		Filme filme;
		List<Filme> filmes = new ArrayList<Filme>();
		ps = jt.getDataSource().getConnection().prepareStatement(selectFilmes);
		ps.setInt(1, limit);
		ps.setInt(2, offset);
		rs = ps.executeQuery();
		while (rs.next()) {
			filme = new Filme(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			filme.setId(rs.getInt(5));
			filme.setImagens(new ArrayList<Integer>());
			filmes.add(filme);
		}
		ps.close();
		
		for (Filme filme2 : filmes) {
			ps = jt.getDataSource().getConnection().prepareStatement(selectImagem);
			ps.setInt(1, filme2.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				filme2.getImagens().add(rs.getInt(1));
			}
			ps.close();
		}
		return filmes;
	}
	
	public Filme getFilme(int id) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		Filme filme = null;
		ps = jt.getDataSource().getConnection().prepareStatement(selectFilme);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		if (rs.next()) {
			filme = new Filme(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			filme.setId(rs.getInt(5));
			filme.setImagens(new ArrayList<Integer>());
			ps.close();
			
			ps = jt.getDataSource().getConnection().prepareStatement(selectImagem);
			ps.setInt(1, filme.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				filme.getImagens().add(rs.getInt(1));
			}
		}
		ps.close();
		return filme;
	}
	
	public byte[] getImagem(int id) throws IOException {
		BufferedImage imagem = ImageIO.read(getClass().getResourceAsStream("/imagens/imagem" + id + ".jpg"));
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ImageIO.write(imagem, "jpg", bao);
		return bao.toByteArray();
	}
	
	public void mockDataBase(int qtde) throws SQLException {
		PreparedStatement ps1;
		PreparedStatement ps2;
		ResultSet rs;
		int idFilme = 0;
		
		ps1 = jt.getDataSource().getConnection().prepareStatement(selectIdFilme);
		rs = ps1.executeQuery();
		if (rs.next()) {
			idFilme = rs.getInt(1) + 1;
		}
		ps1.close();
		ps2 = jt.getDataSource().getConnection().prepareStatement(insertImagem);
		ps1 = jt.getDataSource().getConnection().prepareStatement(insertFilme);
		
		for (int c = 0, d = 0; c < qtde; c ++, d ++, idFilme ++) {
			if (d == 3) {
				d = 0;
			}			
			ps1.setString(1, FilmesMock.nomes[d]);
			ps1.setString(2, FilmesMock.descricoes[d]);
			ps1.setString(3, FilmesMock.autores[d]);
			ps1.setString(4, "18:37");
			ps1.setInt(5, idFilme);
			ps1.executeUpdate();
			for (int e = 0; e < 4; e ++) {
				ps2.setInt(1, idFilme);
				ps2.setInt(2, FilmesMock.imagens[d][e]);
				ps2.executeUpdate();
			}
		}
		ps2.close();
		ps1.close();
	}
	
	public void cleanDatabase() throws SQLException {
		PreparedStatement ps;
		
		ps = jt.getDataSource().getConnection().prepareStatement("DELETE FROM \"Imagem\";");
		ps.executeUpdate();
		ps.close();
		
		ps = jt.getDataSource().getConnection().prepareStatement("DELETE FROM \"Filme\";");
		ps.executeUpdate();
		ps.close();
	}
}
