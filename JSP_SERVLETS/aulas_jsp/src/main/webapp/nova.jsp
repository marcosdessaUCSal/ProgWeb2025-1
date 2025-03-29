<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
    <meta charset="UTF-8">
    <title>Aulas</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
<header class="container-cabecalho">
    <h3>Nova aula</h3>
</header>
<nav class="container-nav">
    <div class="btn-nav" onclick="enviarNovaAula()">ENVIAR</div>
    <div class="btn-nav" onclick="calcelarNovaAula()">CANCELAR</div>
</nav>

<div class="container-geral">
    <div class="container-aula-edit" id="msg-id" hidden="hidden">
        <div class="texto">
            Erro ao tentar registrar dados
        </div>
    </div>
    <div class="container-aula-edit">
        <div class="container-linha1">
            <div class="info">Data: <input id="data-id" type="date" class="inp-data"></div>
            <div class="info">Horário: <input id="hora-id" type="text" class="inp-hora"></div>
            <div class="info">Duração: <input id="dur-id" type="number" class="inp-dur"></div>
        </div>
        <div class="container-linha2">
            <div class="info">Disciplina:
                <select name="" id="disc-id" class="inp-disc">
                    <option value="0">-- escolha --</option>
                    <option value="1">CÁLCULO</option>
                    <option value="2">LÓGICA</option>
                    <option value="3">GEOMETRIA</option>
                    <option value="4">FÍSICA</option>
                    <option value="5">COMPILADORES</option>
                </select>
            </div>
            <div class="info">Assunto: <input id="ass-id" type="text" class="inp-ass"></div>
        </div>
    </div>
</div>

<script src="assets/script.js"></script>

</body>

</html>