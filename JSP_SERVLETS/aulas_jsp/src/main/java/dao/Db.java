package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Aula;
import model.AulaDto;

public class Db {

	private static Db instance = null;
	private Connection connection = null;

	private String driver;
	private String url;
	private String user;
	private String password;

	private Db() {
		this.confDB();
		this.conectar();
		this.criarTabela();
		this.reset();
	}

	public static Db getInstance() {
		if (instance == null) {
			instance = new Db();
		}
		return instance;
	}

	private void confDB() {
		try {
			this.driver = "org.h2.Driver";
			this.url = "jdbc:h2:mem:testdb";
			this.user = "sa";
			this.password = "";
			Class.forName(this.driver);
		} catch (Exception e) {
			// TODO: o que fazer se algo deu errado
			e.printStackTrace();
		}
	}

	// Inicia a conexão com o banco de dados
	private void conectar() {
		try {
			this.connection = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (Exception e) {
			// TODO: o que fazer se algo deu errado
		}
	}

	private void criarTabela() {
		String query = "CREATE TABLE AULA ("
				+ "    ID BIGINT AUTO_INCREMENT PRIMARY KEY,"
				+ "    COD_DISCIPLINA INT,"
				+ "    ASSUNTO VARCHAR(255),"
				+ "    DURACAO INT,"
				+ "    DATA VARCHAR(20),"
				+ "    HORARIO VARCHAR(20)"
				+ ")";
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			this.connection.commit();
		} catch (Exception e) {
			// TODO: o que fazer se algo deu errado
		}
	}

	// Encerra a conexão
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO: o que fazer se algo deu errado
		}
	}

	/*
	 * ****************************************************************
	 * CRUD
	 * ****************************************************************
	 */

	// CRUD READ
	public List<Aula> findAll() {
		String query = "SELECT ID, COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO FROM AULA;";
		List<Aula> lista = new ArrayList<>();
		try {
			Statement st = this.connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Aula aula = new Aula();
				aula.setId(rs.getLong("ID"));
				aula.setCodDisciplina(rs.getInt("COD_DISCIPLINA"));
				aula.setAssunto(rs.getString("ASSUNTO"));
				aula.setDuracao(rs.getInt("DURACAO"));
				aula.setData(rs.getString("DATA"));
				aula.setHorario(rs.getString("HORARIO"));
				lista.add(aula);
			}
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
		return lista;
	}

	public Aula findById(String id) {
		String query = "SELECT ID, COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO FROM AULA "
				+ "WHERE ID = ?";
		Aula aula = new Aula();
		try {
			PreparedStatement pst = this.connection.prepareStatement(query);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				aula.setId(rs.getLong("ID"));
				aula.setCodDisciplina(rs.getInt("COD_DISCIPLINA"));
				aula.setAssunto(rs.getString("ASSUNTO"));
				aula.setDuracao(rs.getInt("DURACAO"));
				aula.setData(rs.getString("DATA"));
				aula.setHorario(rs.getString("HORARIO"));
				return aula;
			}
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
		return null;
	}

	// CRUD CREATAE
	public void create(Aula a) {
		String query = "INSERT INTO AULA (COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO) "
				+ "VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pst = this.connection.prepareStatement(query);
			pst.setString(1, Integer.toString(a.getCodDisciplina()));
			pst.setString(2, a.getAssunto());
			pst.setString(3, Integer.toString(a.getDuracao()));
			pst.setString(4, a.getData());
			pst.setString(5, a.getHorario());
			pst.execute();
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
	}

	// CRUD DELETE
	public void deleteAll() {
		String query = "DELETE FROM AULA";
		try {
			Statement st = this.connection.createStatement();
			st.execute(query);
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
	}

	// CRUD DELETE
	public void delete(String id) {
		String query = "DELETE FROM AULA WHERE ID = ?";
		try {
			PreparedStatement pst = this.connection.prepareStatement(query);
			pst.setString(1, id);
			pst.execute();
		} catch (Exception e) {
			// TODO: o que fazer se algo deu errado
		}
	}

	// CRUD UPDATE
	public void update(AulaDto dto) {
		String query = "UPDATE AULA SET "
				+ "COD_DISCIPLINA = ?, ASSUNTO = ?, DURACAO = ?, DATA = ?, HORARIO = ? "
				+ "WHERE ID = ?";
		try {
			PreparedStatement pst = this.connection.prepareStatement(query);
			pst.setString(1, dto.codDisciplina);
			pst.setString(2, dto.assunto);
			pst.setString(3, dto.duracao);
			pst.setString(4, dto.data);
			pst.setString(5, dto.horario);
			pst.setString(6, dto.id);
			pst.execute();
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
	}
	
	
	// CONVERSÕES ENVOLVENDO DTOS
	public List<AulaDto> convertToDto(List<Aula> aulas) {
		return aulas.stream()
				.map(AulaDto::new)
				.collect(Collectors.toList());
	}

	/*
	 * PARA EFEITO DE TESTES
	 */

	public void reset() {
		this.deleteAll();
		this.popularTabela();
	}

	// popula o banco de dados a partir de DTOs
	public void popularTabela() {
		AulaDto dto = new AulaDto();

		dto.codDisciplina = "1";
		dto.assunto = "Derivadas";
		dto.duracao = "2";
		dto.data = "2024-04-12";
		dto.horario = "14:00";
		this.create(new Aula(dto));

		dto.codDisciplina = "3";
		dto.assunto = "Coordenadas Cartesianas";
		dto.duracao = "2";
		dto.data = "2024-04-13";
		dto.horario = "14:00";
		this.create(new Aula(dto));

		dto.codDisciplina = "4";
		dto.assunto = "O Problema dos Três Corpos";
		dto.duracao = "4";
		dto.data = "2024-04-14";
		dto.horario = "14:00";
		this.create(new Aula(dto));
	}

}