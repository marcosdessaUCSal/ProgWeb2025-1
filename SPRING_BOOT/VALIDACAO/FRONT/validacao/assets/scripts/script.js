var arrayEmails = [];


/*
    =====================================================
        INTERAÇÃO COM O USUÁRIO
    =====================================================
*/

function mudarParaDir() {
    const colEsq = document.querySelector('#col-esq');
    const colDir = document.querySelector('#col-dir');
    const areaEsq = document.querySelector('#area-esq');
    const areaDir = document.querySelector('#area-dir');
    const btnEsq = document.querySelector('#btn-esq');
    const btnDir = document.querySelector('#btn-dir');

    colEsq.classList.remove('tamanho-pequeno');
    colEsq.classList.add('tamanho-grande');
    areaEsq.classList.remove('invisivel');
    areaEsq.classList.add('visivel');
    btnEsq.classList.remove('invisivel');
    btnEsq.classList.add('visivel');

    colDir.classList.remove('tamanho-grande');
    colDir.classList.add('tamanho-pequeno');
    areaDir.classList.remove('visivel');
    areaDir.classList.add('invisivel');
    btnDir.classList.remove('visivel');
    btnDir.classList.add('invisivel');
}

function mudarParaEsq() {
    const colEsq = document.querySelector('#col-esq');
    const colDir = document.querySelector('#col-dir');
    const areaEsq = document.querySelector('#area-esq');
    const areaDir = document.querySelector('#area-dir');
    const btnEsq = document.querySelector('#btn-esq');
    const btnDir = document.querySelector('#btn-dir');

    colEsq.classList.remove('tamanho-grande');
    colEsq.classList.add('tamanho-pequeno');
    areaEsq.classList.remove('visivel');
    areaEsq.classList.add('invisivel');
    btnEsq.classList.remove('visivel');
    btnEsq.classList.add('invisivel');

    colDir.classList.remove('tamanho-pequeno');
    colDir.classList.add('tamanho-grande');
    areaDir.classList.remove('invisivel');
    areaDir.classList.add('visivel');
    btnDir.classList.remove('invisivel');
    btnDir.classList.add('visivel');
}

// ENVIAR EMAIL PARA O SERVIDOR
function enviar(comMascara) {
    let emailCapturado = '';
    if (!comMascara) {
        emailCapturado = document.getElementById('area-esq').value;
        enviarParaApi(emailCapturado);
    } else {
        emailCapturado = document.getElementById('area-dir').value;
        const btnDir = document.querySelector('#btn-dir');
        if (btnDir.classList.contains('btn-inativo')) {
            esconderRegistros();
            setTimeout(() => {
                mostrarMensagens(`Email inválido: ${emailCapturado}`);
            }, 150);
            return;
        }
        if (btnDir.classList.contains('btn-ativo')) {
            enviarParaApi(emailCapturado);
        }
    }
}

// MOSTRAR REGISTROS
function verEmails(array) {
    const containerReg = document.querySelector('#cont-reg');
    let conteudo = '';
    let linha = '';

    conteudo += `<div class="topo-registros" id="topo-registros">`;
    conteudo += `</div>`;
    conteudo += `<div class="cabecalho-registros">`;
    conteudo += `   <div>ID</div>`;
    conteudo += `   <div>EMAIL</div>`;
    conteudo += `</div>`;

    for (let item of array) {
        linha = `<div class="linha-reg">`;
        linha += `  <div class="col-id">${item.id}</div>`;
        linha += `  <div class="col-email">${item.email}</div>`;
        linha += `</div>`;
        conteudo += linha;
    }

    if (array.length == 0) {
        conteudo += '<div class="linha-reg">';
        conteudo += '  <div>---</div>';
        conteudo += '  <div>NENHUM ITEM A SER EXIBIDO</div>';
        conteudo += '</div>';
    }

    conteudo += `<div class="base-registros"></div>`;

    containerReg.innerHTML = conteudo;

    containerReg.classList.remove('invisivel');
    containerReg.classList.add('visivel');

}

// ESCONDER REGISTROS
function esconderRegistros() {
    const containerReg = document.querySelector('#cont-reg');

    containerReg.classList.remove('visivel');
    containerReg.classList.add('invisivel');
}

function limparEmails() {
    clear();
}

// Para notificações que possuem apenas um string como mensagem
function mostrarMensagens(msg) {
    const containerReg = document.querySelector('#cont-reg');

    let conteudo = '';
    conteudo += '<div class="topo-registros" id="topo-registros">';
    conteudo += '   <div class="msg-titulo">MENSAGEM</div>';
    conteudo += '   <div class="linha-msg">';
    conteudo += `       <div>${msg}</div>`;
    conteudo += `   </div>`;
    conteudo += '</div>';
    conteudo += '<div class="base-registros"></div>';

    containerReg.innerHTML = conteudo;

    containerReg.classList.remove('invisivel');
    containerReg.classList.add('visivel');
}


// Para notificações de exceções
function mostrarException(excObj) {
    const containerReg = document.querySelector('#cont-reg');

    let conteudo = '';
    conteudo += '<div class="topo-registros" id="topo-registros">';
    conteudo += '   <div class="msg-titulo">EXCEPTION</div>';
    conteudo += '</div>';
    conteudo += ' <div class="linha-reg">';
    conteudo += '   <div class="col-nome">TÍTULO:</div>';
    conteudo += `   <div class="col-texto">${excObj.titulo}</div>`;
    conteudo += '</div>';
    conteudo += ' <div class="linha-reg">';
    conteudo += '   <div class="col-nome">DETALHE:</div>';
    conteudo += `   <div class="col-texto">${excObj.detalhe}</div>`;
    conteudo += '</div>';
    conteudo += ' <div class="linha-reg">';
    conteudo += '   <div class="col-nome">TIMESTAMP:</div>';
    conteudo += `   <div class="col-texto">${excObj.timestamp}</div>`;
    conteudo += '</div>';
    conteudo += '<div class="base-registros"></div>';

    containerReg.innerHTML = conteudo;

    containerReg.classList.remove('invisivel');
    containerReg.classList.add('visivel');
}

function adicionaListener() {
    const textarea = document.getElementById('area-dir');

    textarea.addEventListener('input',
        () => {
            const btn = document.querySelector('#btn-dir');
            // Expressão regular básica para validação de e-mail
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            const email = textarea.value.trim();

            // Ativar o botão??
            if (email == '' || !emailRegex.test(email)) {
                btn.classList.remove('btn-ativo');
                btn.classList.add('btn-inativo');
                esconderRegistros();
            } else {
                btn.classList.remove('btn-inativo');
                btn.classList.add('btn-ativo');
            }
        }

    );
}


/*
    =====================================================
        COMUNICAÇÃO COM O SERVIDOR
    =====================================================
*/

function getEmails() {
    fetch('http://localhost:8080/validacao/getAll', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            arrayEmails = data;
            esconderRegistros();
            setTimeout(() => {
                verEmails(arrayEmails);
            }, 150);
        })
}

function limpar() {
    esconderRegistros();
    fetch('http://localhost:8080/validacao/clear', {
        method: 'DELETE'
    })
        .then(response => response.text())
        .then(msg => {
            esconderRegistros();
            setTimeout(() => {
                mostrarMensagens(msg);
            }, 150);
        })
        .catch(
            e => {
                console.log(e);
            }
        );
}

function mock() {
    esconderRegistros();
    fetch('http://localhost:8080/validacao/mock', {
        method: 'PUT'
    })
        .then(response => response.text())
        .then(msg => {
            esconderRegistros();
            setTimeout(() => {
                mostrarMensagens(msg);
            }, 150);
        })
        .catch(
            e => {
                console.log(e);
            }
        );
}

function enviarParaApi(strEmail) {
    fetch('http://localhost:8080/validacao/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: null,
            email: strEmail
        })
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(excepttion => { throw excepttion });
            }
            return response.text()
        })
        .then(msg => {
            esconderRegistros();
            setTimeout(() => {
                mostrarMensagens(msg);
            }, 150);
        })
        .catch(
            excepttion => {
                esconderRegistros();
                setTimeout(() => {
                    mostrarException(excepttion)
                }, 150);
            }
        );
}




/*
    =====================================================
        INÍCIO DE TUDO
    =====================================================
*/

// adiciona listener para validação de email no form direito
adicionaListener();