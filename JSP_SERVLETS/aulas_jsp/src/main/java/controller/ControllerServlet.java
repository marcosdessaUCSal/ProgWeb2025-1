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

@WebServlet(urlPatterns = {"/ControllerServlet", "/aulas_jsp", "/nova", "/edit" })
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/nova")) {
			RequestDispatcher rd = request.getRequestDispatcher("nova.jsp");
			rd.forward(request, response);
		} else if (action.equals("/edit")) {
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BufferedReader reader = request.getReader();
		Gson gson = new Gson();
		
		// obtendo o código da request (a 'operação' requerida)
		Msg msg = gson.fromJson(reader, Msg.class);
		
		switch (msg.op) {
		case "START_SESSION":
			this.poeDadosNaSessao(session);
			break;
		case "RESET":
			this.reset(session);
			break;
		case "CREATE":
			this.create(new Aula(msg.dto));
			break;
		case "UPDATE":
			this.update(msg.dto);
			break;
		case "DELETE":
			this.delete(msg.dto.id);
			break;
		}
	}

	private void poeDadosNaSessao(HttpSession session) {
		Db db = Db.getInstance();
		ArrayList<AulaDto> lista = (ArrayList<AulaDto>) db.convertToDto(db.findAll());
		session.removeAttribute("lista");
		session.setAttribute("lista", lista);
	}

	private void reset(HttpSession session) {
		Db db = Db.getInstance();
		db.reset();
		poeDadosNaSessao(session);
	}

	private void create(Aula aula) {
		Db db = Db.getInstance();
		db.create(aula);
	}

	private void delete(String id) {
		Db db = Db.getInstance();
		db.delete(id);
	}

	private void update(AulaDto dto) {
		Db db = Db.getInstance();
		db.update(dto);
	}
	
	private class Msg {
		String op;
		AulaDto dto;
	}

}