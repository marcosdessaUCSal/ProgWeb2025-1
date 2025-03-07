<%@page import="model.AulaDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
		HttpSession sessao = request.getSession();
		if (sessao.getAttribute("lista") != null) {
			ArrayList<AulaDto> lista = (ArrayList<AulaDto>) sessao.getAttribute("lista");
			for (AulaDto a: lista) {
				%>
				<div class="container-aula">
		            <div class="container-linha1">
		                <div class="info">Data: <span class="texto"><%= a.getDataPadraoBr() %></span></div>
		                <div class="info">Hora: <span class="texto"><%= a.horario %></span></div>
		                <div class="info">Duração(h): <span class="texto"><%= a.duracao %></span></div>
		            </div>
		            <div class="container-linha2">
		                <div class="info">Disciplina: <span class="texto"><%= a.disciplina %></span></div>
		                <div class="info">Assunto: <span class="texto"><%= a.assunto %></span></div>
		            </div>
		            <div class="container-btns">
		                <div></div>
		                <div class="btn" onclick="editarAula(<%= a.id %>)">EDITAR</div>
		                <div class="btn" onclick="deleta(<%= a.id %>)">REMOVER</div>
		            </div>
		        </div>				
				<%
			}
		}
		%>