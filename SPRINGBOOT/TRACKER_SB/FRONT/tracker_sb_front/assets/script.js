
// escreve os registros no container
function escreveRegistros(listaReg) {
	let classeLinha = ''; // estilo do css (linha clara/escura)
	let codHtml = '';
	let str = '';
	for (let i = listaReg.length - 1; i >= 0; i--) {
		classeLinha = (listaReg.length - i) % 2 == 0 ? 'linha-escura' : 'linha-clara';
		str = `<div class="${classeLinha}">`;
		str += `<div>${listaReg[i].data}</div>`;
		str += `<div>${listaReg[i].cidade}</div>`;
		str += `<div>${listaReg[i].descr}</div>`;
		str += `</div>`;
		codHtml += str;
	}
	document.getElementById('registros').innerHTML = codHtml;
}


// INTERAÇÃO COM O USUÁRIO ===================================================================

// para monitorar botão (ativo / inativo) -> responde a um listener
function atualizaBotao() {
	if (inputText.value.trim() != '') {
		btnProcurar.classList.remove('btn-inativo');
		btnProcurar.classList.add('btn-ativo');
	} else {
		btnProcurar.classList.remove('btn-ativo');
		btnProcurar.classList.add('btn-inativo');
	}
}


function procurar(codigo) {
	console.log('cheguei ao procurar')
	fetch('http://localhost:8080/pesquisar', {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: codigo
	})
		.then(
			response => {
				if (!response.ok) {
					// TODO: DECIDA O QUE FAZER
				}
				return response.json();
			}
		)
		.then(dados => {
			escreveRegistros(dados);
			if (dados.length == 0) {
				document.getElementById('mensagem').style.visibility = 'visible';
			} else {
				document.getElementById('mensagem').style.visibility = 'hidden';
			}
		})
		.catch(
			erro => {
				// TODO: DECIDA O QUE FAZER
			}
		);
}

// o onclick se direciona a isto
function procurarClick() {
	let cod = document.getElementById('codigo').value;
	cod = cod.trim();
	cod = cod.toUpperCase();
	if (cod != '') {
		procurar(cod);
	}
}


// INICIALIZANDO ===================================================

// sobre o botão procurar
const inputText = document.getElementById('codigo');
const btnProcurar = document.getElementById('btn-procurar');
// listener para monitorar botão (claro / escuro)
inputText.addEventListener('input', atualizaBotao);

