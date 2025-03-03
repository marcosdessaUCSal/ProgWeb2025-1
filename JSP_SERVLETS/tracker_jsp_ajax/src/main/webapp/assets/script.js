
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

// USANDO AJAX
function procurar(codigo) {
	let req = new XMLHttpRequest();
	req.open('POST', 'TrackerServlet', true);
	req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	req.onreadystatechange = () => {
			if (req.readyState == 4 && req.status == 200) {
				window.location.reload(true);
			}
		}
	req.send(`codigo=${codigo}`);
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



