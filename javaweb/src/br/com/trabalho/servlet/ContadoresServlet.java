package br.com.trabalho.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contadores")
public class ContadoresServlet extends HttpServlet {

	private static final long serialVersionUID = -3162419944663739724L;

	public ContadoresServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/contadores.jsp").forward(request, response);
	}

}