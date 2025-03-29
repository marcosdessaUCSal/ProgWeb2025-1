package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.Db;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Aula;
import model.AulaDto;

@WebServlet(urlPatterns = { "/nova", "/edit", "/aulas/*" })
public class AulasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AulasServlet() {
		super();
	}

	// REDIRECIONA PARA PÁGINAS DE CRIAÇÃO/EDIÇÃO DE AULAS (método GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();
		if (acao.equals("/nova")) {
			novaAula(request, response);
		} else if (acao.equals("/edit")) {
			editarAula(request, response);
		}
	}

	// MÉTODO POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path.equals("/novaAula")) {
			// registra uma aula
			registraNovaAula(request, response);
		} else if (path.equals("/atualizaSessao")) {
			// atualiza a sessão da página
			atualizaSessao(request, response);
		}
	}

	// MÉTODO PUT
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getPathInfo();
		if (path.equals("/atualizarAula")) {
			// atualiza uma aula
			atualizarAula(request, response);
		} else if (path.equals("/reset")) {
			// faz um reset em todos os dados do banco
			reset(request, response);
		}

	}

	// MÉTODO DELETE
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path.equals("/removerContato")) {
			// remove a aula com o id especificado
			removeAula(request, response);
		}
	}

	/*
	 * MÉTODOS PRIVADOS
	 */

	// redireciona para página de nova aula (nova.jsp)
	private void novaAula(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("nova.jsp");
		rd.forward(request, response);
	}

	// redireciona para página de editar aula (edit.jsp)
	private void editarAula(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		Db db = Db.getInstance();
		Aula aula = db.findById(id);
		if (aula != null) {
			session.setAttribute("dto", new AulaDto(aula));
			RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
			rd.forward(request, response);
		}
	}

	// insere nova aula no banco de dados
	private void registraNovaAula(HttpServletRequest request, HttpServletResponse response) {
		try {
			// obtendo JSON com o dto da aula
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();
			AulaDto dto = gson.fromJson(reader, AulaDto.class);

			// tentando criar o novo registro no banco de dados
			boolean sucesso = Db.getInstance().create(new Aula(dto));
			if (sucesso) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.sendError(HttpServletResponse.SC_CONFLICT, "Problema na atulização.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// atualiza os campos do registro de uma aula
	private void atualizarAula(HttpServletRequest request, HttpServletResponse response) {
		try {
			// obtendo o JSON com o dto da aula
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();
			AulaDto dto = gson.fromJson(reader, AulaDto.class);

			// tentando registrar a atualização no banco de dados
			boolean sucesso = Db.getInstance().update(new Aula(dto));
			if (sucesso) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.sendError(HttpServletResponse.SC_CONFLICT, "Problema na atulização.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void atualizaSessao(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Db db = Db.getInstance();
		ArrayList<AulaDto> lista = (ArrayList<AulaDto>) db.convertToDto(db.findAll());
		session.removeAttribute("lista");
		session.setAttribute("lista", lista);
	}

	// remove uma aula dado o id
	private void removeAula(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// obtendo o parâmetro id
		String id = request.getParameter("id");
		if (id == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro 'id' é obrigatório.");
			return;
		}

		try {
			boolean sucesso = Db.getInstance().delete(id);
			if (sucesso) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Recurso não encontrado.");
			}
		} catch (Exception e) {
			// TODO: DECIDIR O QUE FAZER
		}
	}

	// reinicia todos os dados em memória
	private void reset(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Db db = Db.getInstance();
		db.reset();
		ArrayList<AulaDto> lista = (ArrayList<AulaDto>) db.convertToDto(db.findAll());
		session.removeAttribute("lista");
		session.setAttribute("lista", lista);
	}

}
