class Registro {
    constructor(data, cidade, descr) {
        this.data = data;
        this.cidade = cidade;
        this.descr = descr;
    }
}

// contém todos os registros de uma encomenda
class Rastreio {
    constructor(cod, registros) {
        this.registros = registros; // espera-se um array de registros
        this.codigo = cod;
    }
}

// contém todos os rastreios
class BaseDeDados {
    constructor() {
        this.rastreios = [];
    }

    inserirRastreio(rastreio) {
        this.rastreios.push(rastreio);
    }

    procurarPorCodigo(cod) {
        let rast = null;
        for (let rastreio of this.rastreios) {
            if (rastreio.codigo == cod) {
                rast = rastreio;
                break;
            }
        }
        return rast;
    }
}

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

// limpa os registros no container
function limpaRegistros() {
    document.getElementById('registros').innerHTML = "";
}

// simula um banco de dados
function populaDados() {
    let baseDeDados = new BaseDeDados();

    // rastreio 1
    let registros = new Array();
    registros.push(new Registro('20/02/2024', 'CAMPINAS', 'DOCUMENTAÇÃO PRONTA'));
    registros.push(new Registro('21/02/2024', 'CAMPINAS', 'PACOTE ENVIADO'));
    registros.push(new Registro('21/02/2024', 'CAMPINAS', 'CHEGOU À UNIDADE'));
    registros.push(new Registro('21/02/2024', 'CAMPINAS', 'SAIU DA UNIDADE'));
    registros.push(new Registro('23/02/2024', 'FEIRA DE SANTANA', 'CHEGOU À UNIDADE'));
    registros.push(new Registro('24/02/2024', 'FEIRA DE SANTANA', 'SAIU DA UNIDADE'));
    registros.push(new Registro('25/02/2024', 'SALVADOR', 'CHEGOU AO DESTINO FINAL'));
    let rastreio = new Rastreio('ABC1234', registros);
    baseDeDados.inserirRastreio(rastreio);

    // rastreio 2
    registros = new Array();
    registros.push(new Registro('10/02/2025', 'MIAMI', 'DADO RECEBIDO'));
    registros.push(new Registro('10/02/2025', 'MIAMI', 'DOCUMENTACAO COMPLETA'));
    registros.push(new Registro('12/02/2025', 'MIAMI', 'Receptáculo recebido em instalações de transporte dos EUA'));
    registros.push(new Registro('12/02/2025', 'MIAMI', 'RECEBIDO'));
    registros.push(new Registro('12/02/2025', 'MIAMI', 'X-RAY SCAN'));
    registros.push(new Registro('12/02/2025', 'MIAMI', 'COLOCADO EM CONTAINER'));
    registros.push(new Registro('12/02/2025', 'MIAMI', 'PROCESADO'));
    registros.push(new Registro('12/02/2025', 'MIAMI', 'ENVIADO'));
    registros.push(new Registro('13/02/2025', 'MIAMI', 'O voo partiu'));
    registros.push(new Registro('14/02/2025', 'SÃO PAULO', 'CHEGOU NO PAIS'));
    registros.push(new Registro('14/02/2025', 'SÃO PAULO', 'Entrada aduaneira'));
    registros.push(new Registro('14/02/2025', 'SÃO PAULO', 'ENVIO LIBERADO'));
    registros.push(new Registro('14/02/2025', 'SÃO BERNARDO DO CAMPO', 'ENTREGUE A TRANSPORTADORA LOCAL'));
    registros.push(new Registro('17/02/2025', 'SÃO BERNARDO DO CAMPO', 'EM TRÂNSITO'));
    registros.push(new Registro('21/02/2025', 'SALVADOR', 'OBJETO Recebido'));
    registros.push(new Registro('21/02/2025', 'SALVADOR', 'Preparada para a transferencia'));
    registros.push(new Registro('21/02/2025', 'SALVADOR', 'OBJETO Recebido'));
    rastreio = new Rastreio('ABC4321', registros);
    baseDeDados.inserirRastreio(rastreio);

    return baseDeDados;
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
    let rastreio = bd.procurarPorCodigo(codigo);
    if (rastreio != null) {
        document.getElementById('mensagem').style.visibility = 'hidden';
        escreveRegistros(rastreio.registros);
    } else {
        limpaRegistros();
        // TODO: EXIBIR MENSAGEM DE ERRO
        document.getElementById('mensagem').style.visibility = 'visible';
    }
}

// o onclick se direciona a isto
function procurarClick() {
    let cod = document.getElementById('codigo').value;
    cod = cod.trim();
    cod = cod.toUpperCase();
    procurar(cod);
}


// INICIALIZANDO ===================================================

var bd = populaDados();

// sobre o botão procurar
const inputText = document.getElementById('codigo');
const btnProcurar = document.getElementById('btn-procurar');
// listener para monitorar botão (claro / escuro)
inputText.addEventListener('input', atualizaBotao);

