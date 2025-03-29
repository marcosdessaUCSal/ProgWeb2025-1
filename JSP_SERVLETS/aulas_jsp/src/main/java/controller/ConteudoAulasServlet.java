package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import dao.Db;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.servlet.http.HttpSession;
import model.AulaDto;

@WebServlet("/getConteudoAulas")
public class ConteudoAulasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Db db = Db.getInstance();
		List<AulaDto> lista = db.convertToDto(db.findAll());
		session.removeAttribute("lista");
		session.setAttribute("lista", lista);
		
		// configurando a resposta
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        
        // criando um writer para conter o conteúdo JSP a ser processado
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        // encaminhar o conteúdo processado ao dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("dados_aulas.jsp");
        dispatcher.include(request, new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return writer;
            }
        });
        
        // enviando a resposta (formato string) para o javascript
        writer.flush();
        response.getWriter().write(stringWriter.toString());
        
        
	}

}
