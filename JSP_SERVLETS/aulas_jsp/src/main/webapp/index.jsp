<%@page import="model.AulaDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="assets/style.css">
<title>Aulas</title>
</head>
<body>
	<header class="container-cabecalho">
	    <h3>Minhas Aulas</h3>
	</header>
	<nav class="container-nav">
		<div class="btn-nav" onclick="novaAula()">NOVA</div>
		<div class="btn-nav" onclick="reset()">RESET</div>
	</nav>
	<div class="container-geral" id="area-aulas"></div>
	<script src="assets/script.js"></script>
</body>
</html>