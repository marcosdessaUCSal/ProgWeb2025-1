// RESET
function reset() {
	fetch('aulas/reset', {
		method: 'PUT'
	})
		.then(response => {
			if (!response.ok) {
				// decidir o que fazer a respeito
			}
			return;
		})
		.then(dados => {
			atualizaSessao();
			window.location.href = "/aulas_jsp";
		})
		.catch(
			e => {
				// TODO: fazer um tratamento de exceções
			}
		);
}

// NOVA AULA
function novaAula() {
	window.location.href = "nova";
}

// CANCELA NOVA AULA (OU EDIÇÃO)
function calcelarNovaAula() {
	window.location.href = "/aulas_jsp";
}

// EDITA UMA AULA COM ID ESPECIFICADO
function editarAula(id) {
	window.location.href = "edit?id=" + id;
}

// ENVIA CONTEÚDO DA NOVA AULA
function enviarNovaAula() {
	// obtém os valores a partir do formulário
	let data = document.getElementById('data-id').value;
	let horario = document.getElementById('hora-id').value;
	let duracao = document.getElementById('dur-id').value;
	let codDisciplina = document.getElementById('disc-id').value;
	let assunto = document.getElementById('ass-id').value;
	// verificando a validação
	if (!validaNovaAula(data, horario, duracao, codDisciplina, assunto)) {
		document.getElementById('msg-id').style.display = 'block';
		return;
	}
	const dto = {
		disciplina: '',
		codDisciplina: codDisciplina,
		assunto: assunto,
		duracao: duracao,
		data: data,
		horario: horario
	};
	// enviando os dados via fetch
	fetch('aulas/novaAula', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(dto)
	})
		.then(response => {
			if (!response.ok) {
				// decidir o que fazer a respeito
			} else if (response.status == 409) {
				// conflito ao atualizar o recurso
			}
			return;
		})
		.then(dados => {
			atualizaSessao();
			window.location.href = "/aulas_jsp";
		})
		.catch(
			e => {
				// TODO: decidir o que fazer
			}
		);
}

// ENVIA CONTEÚDO EM EDIÇÃO
function enviarEdit() {
	// obtém os valores a partir do formulário
	let id = document.getElementById('id').innerHTML;
	let data = document.getElementById('data-id').value;
	let horario = document.getElementById('hora-id').value;
	let duracao = document.getElementById('dur-id').value;
	let codDisciplina = document.getElementById('disc-id').value;
	let assunto = document.getElementById('ass-id').value;
	const dto = {
		id: id,
		disciplina: '',
		codDisciplina: codDisciplina,
		assunto: assunto,
		duracao: duracao,
		data: data,
		horario: horario
	};
	if (!validaNovaAula(data, horario, duracao, codDisciplina, assunto)) {
		document.getElementById('msg-id').style.display = 'block';
		return;
	}
	// enviando os dados ao servidor
	fetch('aulas/atualizarAula', {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(dto)
	})
		.then(response => {
			if (!response.ok) {
				// decidir o que fazer a respeito
			} else if (response.status == 409) {
				// problema ao atualizar o recurso
			}
			return;
		})
		.then(dados => {
			atualizaSessao();
			window.location.href = "/aulas_jsp";
		})
		.catch(
			e => {
				// TODO: decidir o que fazer
			}
		);
}

// DELETA UMA AULA
function deleta(id) {
	// enviando os dados ao servidor
	fetch(`aulas/removerContato?id=${id}`, {
		method: 'DELETE'
	})
		.then(response => {
			if (response.status == 204) {
				// RECURSO DELETADO COM SUCESSO
			}
			switch (response.status) {
				case 202:
					// RECURSO DELETADO COM SUCESSO
					break;
				case 404:
					// RECURSO NÃO ENCONTRADO
					break;
				case 400:
					// PARÂMETRO INVÁLIDO OU AUSENTE
					break;
				default:
					// ERRO AO DELETAR RECURSO (USAR O CÓDIGO response.status)
					break;
			}
			atualizaSessao();
			window.location.href = "/aulas_jsp";
		})
		.catch(
			e => {
				// TODO: DECIDIR O QUE FAZER
			}
		);
}


function atualizaSessao() {
	fetch('aulas/atualizaSessao', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ op: 'START_SESSION' })
	})
		.then(response => {
			if (!response.ok) {
				// decidir o que fazer a respeito
			}
			return
		})
		.then(dados => {
			exibeAulasDoBd();
		})
		.catch(
			e => {
				// TODO: fazer um tratamento de exceções
			}
		);
}


function inicializaBanco() {
	fetch('ControllerServlet', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({ op: 'RESET' })
	})
		.then(response => {
			if (!response.ok) {
				// decidir o que fazer a respeito
			}
			return
		})
		.then(dados => {
			exibeAulasDoBd();
		})
		.catch(
			e => {
				// TODO: fazer um tratamento de exceções
			}
		);
}

// EXIBE NA PÁGINA PRINCIPAL AS AULAS REGISTRADAS NO BD
function exibeAulasDoBd() {
	fetch('getConteudoAulas', {
		method: "GET"
	})
		.then(
			response => {
				if (!response.ok) {
					// TODO: DECIDA O QUE FAZER
				}
				return response.text();
			}
		)
		.then(dados => {
			document.getElementById('area-aulas').innerHTML = dados;
		})
		.catch(
			erro => {
				// TODO: DECIDA O QUE FAZER
			}
		);
}



// ============================================================
// 			VALIDAÇÕES

validaNovaAula = function(data, horario, duracao, codDisciplina, assunto) {
	c1 = codDisciplina == '0';
	c2 = horario == '';
	c3 = duracao <= 0;
	c4 = data == '';
	c5 = assunto == '';
	if (c1 || c2 || c3 || c4 || c5) {
		return false;
	}
	return true;
}





// ===================================================================================
// 		INICIALIZA O PROCESSAMENTO

exibeAulasDoBd();
