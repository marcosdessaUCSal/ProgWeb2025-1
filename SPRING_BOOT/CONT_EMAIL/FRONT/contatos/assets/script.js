class Contato {
    constructor(id, nome, email, marcado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.marcado = marcado;
    }
}

var arrayContatos = [];
var editando = false;


// =================================================
//      EXIBIÇÃO DE DADOS
// =================================================

function exibeContatos() {
    fetch('http://localhost:8080/contatos/lista', {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            arrayContatos.length = 0;
            data.forEach(item => {
                arrayContatos.push(new Contato(
                    item.id, item.nome, item.email, item.marcado
                ));
            });
            mostraNaTela();
        })
        .catch();
}

// =================================================
//      CONSTRUÇÃO DE ELEMENTOS GRÁFICOS
// =================================================

function mostraNaTela() {
    const area = document.getElementById('contatos');
    let conteudo = '';
    let registroAtual = '';
    let nome = '';
    let email = '';
    let selecionado = false;
    let id = 0;
    for (let c of arrayContatos) {
        id = c.id;
        nome = c.nome;
        email = c.email;
        selecionado = c.marcado;
        registroAtual = `<div class="registro" onclick="mude(${id})">`;
        if (!selecionado) {
            registroAtual += `<input type="checkbox" onchange="mude(${id})">`;
        } else {
            registroAtual += `<input type="checkbox" checked onchange="mude(${id})">`;
        }
        registroAtual += `<div class="contato">${nome}</div>`;
        registroAtual += `<div class="email">${email}</div>`;
        registroAtual += `</div>`;
        conteudo += registroAtual;
    }
    area.innerHTML = conteudo;

}

// =================================================
//      ALTERAÇÕES NOS DADOS
// =================================================

function mude(id) {
    if (editando) {
        mostraNaTela();
        return;
    }
    fetch(`http://localhost:8080/contatos/inverteMarcado/${id}`, {
        method: 'PUT'
    })
        .then(() => {
            exibeContatos();
        })
        .catch(
            e => {
                console.log(e);
            }
        );
}

function marcarTudo() {
    if (editando) return;
    fetch('http://localhost:8080/contatos/marcaTudo', {
        method: 'PUT'
    })
        .then(() => exibeContatos())
        .catch(
            e => {
                console.log(e);
            }
        );
}

function desmarcarTudo() {
    if (editando) return;
    fetch('http://localhost:8080/contatos/desmarcaTudo', {
        method: 'PUT'
    })
        .then(() => exibeContatos())
        .catch(
            e => {
                console.log(e);
            }
        );
}

function removerMarcados() {
    if (editando) return;
    fetch('http://localhost:8080/contatos/removeMarcados', {
        method: 'DELETE'
    })
        .then(() => exibeContatos())
        .catch(
            e => {
                console.log(e);
            }
        );
}

function reset() {
    if (editando) return;
    fetch('http://localhost:8080/contatos/reset', {
        method: 'PUT'
    })
        .then(() => exibeContatos())
        .catch(
            e => {
                console.log(e);
            }
        );
}

function adicionarContato() {
    editando = false;
    let nome = document.getElementById('nome').value;
    let email = document.getElementById('email').value;
    if (!contatoValidado(nome, email)) {
        const novo = document.getElementById('novo');
        novo.classList.remove('novo-visivel');
        novo.classList.add('novo-escondido');
        return;
    }
    fetch('http://localhost:8080/contatos/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: null,
            nome: nome,
            email: email,
            marcado: false
        })
    })
        .then(() => {
            exibeContatos();
            const novo = document.getElementById('novo');
            novo.classList.remove('novo-visivel');
            novo.classList.add('novo-escondido');
            document.getElementById('nome').value = '';
            document.getElementById('email').value = '';
        })
        .catch(
            e => {
                // TODO: fazer um tratamento de exceções
            }
        );
}

// Mostra input de novo contato
function novoContato() {
    if (editando) return;
    editando = true;
    const novo = document.getElementById('novo');
    novo.classList.remove('novo-escondido');
    novo.classList.add('novo-visivel');
}

function cancel() {
    editando = false;
    document.getElementById('nome').value = '';
    document.getElementById('email').value = '';
    const novo = document.getElementById('novo');
    novo.classList.remove('novo-visivel');
    novo.classList.add('novo-escondido');
}

function contatoValidado(nome, email) {
    nome = nome.trim();
    email = email.trim();
    if (nome == '' && email == '') return false;
    return true;
}







// =================================================
//      INICIANDO TUDO
// =================================================

exibeContatos();