<%@page import="model.Registro"%>
<%@page import="model.Rastreio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tracker</title>
<link rel="stylesheet" href="assets/style.css">
</head>
<body>

	<div class="container">
		<!-- HEADER -->
		<header class="meu-header">
			<div class="figura">
				<img src="assets/img/fast.svg" alt="">
			</div>
			<hgroup>
				<h2>FLASHTRANSP</h2>
				<h3>A MELHOR LOGÍSTICA PARA TRANSPORTE</h3>
			</hgroup>
		</header>

		<!-- CORPO DA PÁGINA -->
		<%
		HttpSession sessao = request.getSession();
		String classeVisivel = "mensagem-invisivel";
		if (sessao.getAttribute("visivel") != null) {
			classeVisivel = (String) sessao.getAttribute("visivel");
		}
		%>
		<div class="corpo">
			<!--  -->
			<div class="esquerda">
				<div class="linha-esq">Escreva o código do seu pacote</div>
				<input type="text" class="input-codigo" id="codigo">
				<div id="btn-procurar" class="btn-inativo" onclick="procurarClick()">
					PROCURAR</div>
				<div class="<%= classeVisivel %>" id="mensagem">CÓDIGO NÃO
					ENCONTRADO. POR FAVOR, TENTE OUTRA VEZ.</div>
			</div>

			<!-- MOVIMENTAÇÕES -->
			<div class="direita">
				<div class="topo">
					<div>DATA</div>
					<div>LOCAL</div>
					<div>DESCRIÇÃO</div>
				</div>
				<!-- área dos registros -->
				<div id="registros">
					<%
					Rastreio rastreio = (Rastreio) sessao.getAttribute("registros");
					if (rastreio != null) {
						String classeLinha;
						Registro reg;
						for (int i = rastreio.getRegistros().size() - 1; i >= 0; i--) {
							classeLinha = (rastreio.getRegistros().size() - i) % 2 == 0 ?
									"linha-escura" : "linha-clara";
							reg = rastreio.getRegistros().get(i);
							%>
							<div class="<%= classeLinha %>">
								<div><%= reg.data %></div>
								<div><%= reg.cidade %></div>
								<div><%= reg.descr %></div>
							</div>
							<%
						}
					}
					%>
				</div>
				<div class="base"></div>

			</div>
		</div>
	</div>

	<script src="assets/script.js"></script>

</body>
</html>
