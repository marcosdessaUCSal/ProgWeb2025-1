package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BaseDeDadosSingleton;
import model.Rastreio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import dto.RegistroDto;

@WebServlet(urlPatterns = { "/TrackerServlet" })
public class TrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TrackerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	// ABORDAGEM RESPONDENDO REQUISIÇÃO POR JSON
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			// obtendo o código da request
			BufferedReader reader = request.getReader();
			Gson gson = new Gson();
			String codigo = gson.fromJson(reader, Msg.class).codigo;
			
			// preparando a response
			ArrayList<RegistroDto> resposta;
			Rastreio rastreio = BaseDeDadosSingleton.getInstance().procurarPorCodigo(codigo);
			if (rastreio != null) {
				resposta = rastreio.getDtos();
			} else {
				resposta = new ArrayList<RegistroDto>();
			}
			gson = new Gson();
			String json = gson.toJson(resposta);
			
			// configurando http
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			// incluindo o json na response definitivamente
			PrintWriter saida = response.getWriter();
			saida.print(json);
			saida.flush();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// mensagem (json) enviada pelo cliente
	private class Msg {
		String codigo;
	}

}
