package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BaseDeDadosSingleton;
import model.Rastreio;

import java.io.IOException;

@WebServlet("/TrackerServlet")
public class TrackerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TrackerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	// ABORDAGEM ESCREVENDO DADOS NA SESSÃO
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		HttpSession sessao = request.getSession();
		Rastreio rastreio = null;
		if (codigo != null) {
			rastreio = BaseDeDadosSingleton.getInstance().procurarPorCodigo(codigo);
		}
		sessao.setAttribute("registros", rastreio);
		String visivel = rastreio == null? "mensagem-visivel" : "mensagem-invisivel";
		sessao.setAttribute("visivel", visivel);
	}

}
