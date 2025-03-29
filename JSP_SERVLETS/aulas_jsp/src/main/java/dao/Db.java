package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Aula;
import model.AulaDto;

public class Db {

	private static Db instance = null;

	private final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
	private final String USER = "sa";
	private final String PASSWORD = "";

	private Db() {
		this.criarTabela();
		this.reset();
	}

	public static Db getInstance() {
		if (instance == null) {
			instance = new Db();
		}
		return instance;
	}


	private void criarTabela() {
		String query = 
				  "CREATE TABLE AULA ("
				+ "    ID BIGINT AUTO_INCREMENT PRIMARY KEY,"
				+ "    COD_DISCIPLINA INT,"
				+ "    ASSUNTO VARCHAR(255),"
				+ "    DURACAO INT,"
				+ "    DATA VARCHAR(20),"
				+ "    HORARIO VARCHAR(20)"
				+ ")";
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st = conn.createStatement();
			st.execute(query);
			st.close();
			conn.close();
		} catch (Exception e) {
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
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st = conn.createStatement();
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
			rs.close();
			st.close();
			conn.close();
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
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				aula.setId(rs.getLong("ID"));
				aula.setCodDisciplina(rs.getInt("COD_DISCIPLINA"));
				aula.setAssunto(rs.getString("ASSUNTO"));
				aula.setDuracao(rs.getInt("DURACAO"));
				aula.setData(rs.getString("DATA"));
				aula.setHorario(rs.getString("HORARIO"));
				rs.close();
				pst.close();
				conn.close();
				return aula;
			}
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
		return null;
	}

	// CRUD CREATE
	public boolean create(Aula a) {
		boolean sucesso = false;
		String query = "INSERT INTO AULA (COD_DISCIPLINA, ASSUNTO, DURACAO, DATA, HORARIO) "
				+ "VALUES (?,?,?,?,?)";
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, Integer.toString(a.getCodDisciplina()));
			pst.setString(2, a.getAssunto());
			pst.setString(3, Integer.toString(a.getDuracao()));
			pst.setString(4, a.getData());
			pst.setString(5, a.getHorario());
			pst.execute();
			pst.close();
			conn.close();
			sucesso = true;
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
		return sucesso;
	}

	// CRUD DELETE
	public boolean deleteAll() {
		boolean sucesso = false;
		String query = "DELETE FROM AULA";
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st = conn.createStatement();
			st.execute(query);
			st.close();
			conn.close();
			sucesso = true;
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
		return sucesso;
	}

	// CRUD DELETE
	public boolean delete(String id) {
		String query = "DELETE FROM AULA WHERE ID = ?";
		boolean sucesso = false;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, id);
			pst.execute();
			pst.close();
			conn.close();
			sucesso = true; // transação bem sucedida
		} catch (Exception e) {
			// TODO: o que fazer se algo deu errado
		}
		return sucesso;
	}

	// CRUD UPDATE
	public boolean update(Aula aula) {
		boolean sucesso = false;
		String query =
				  "UPDATE AULA SET "
				+ "COD_DISCIPLINA = ?, ASSUNTO = ?, DURACAO = ?, DATA = ?, HORARIO = ? "
				+ "WHERE ID = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, aula.getCodDisciplina());
			pst.setString(2, aula.getAssunto());
			pst.setInt(3, aula.getDuracao());
			pst.setString(4, aula.getData());
			pst.setString(5, aula.getHorario());
			pst.setLong(6, aula.getId());
			pst.execute();
			pst.close();
			conn.close();
			sucesso = true; // transação bem sucedida
		} catch (Exception e) {
			// TODO: o que fazer se deu errado
		}
		return sucesso;
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