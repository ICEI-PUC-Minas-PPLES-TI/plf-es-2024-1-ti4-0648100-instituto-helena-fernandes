document.addEventListener("DOMContentLoaded", function () {
    const idTurma = getParameterByName("id_turma");
    if (idTurma) {
        carregarAlunosDaTurma(idTurma);
    } else {
        alert("ID da turma não encontrado.");
    }
});

// Função para extrair parâmetros da URL
function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return "";
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

// Função para carregar os alunos da turma junto com as notas
async function carregarAlunosDaTurma(idTurma) {
    try {
        const response = await fetch(`http://localhost:8080/turma/${idTurma}/alunos`);
        if (!response.ok) {
            throw new Error("Falha ao buscar alunos");
        }
        const alunos = await response.json();
        const listaAlunos = document.getElementById("lista-alunos");
        for (const aluno of alunos) {
            const notas = await carregarNotasDoAluno(aluno.idAluno, idTurma);
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${aluno.nome_aluno}</td>
                <td>${notas.prova1 ?? 0}</td>
                <td>${notas.prova2 ?? 0}</td>
                <td>${notas.prova3 ?? 0}</td>
                <td>${notas.trabalho1 ?? 0}</td>
                <td>${notas.trabalho2 ?? 0}</td>
                <td>${notas.trabalho3 ?? 0}</td>
                <td><button type="button" onclick="abrirDetalhes(${aluno.idAluno}, ${idTurma}, ${notas.idNota}, ${notas.idDisciplina})">Detalhes</button>
                </td>
            `;
            listaAlunos.appendChild(row);
        }
    } catch (error) {
        console.error("Erro ao carregar alunos:", error);
        alert("Não foi possível carregar os alunos. Por favor, tente novamente.");
    }
}

// Função para carregar as notas de um aluno específico
async function carregarNotasDoAluno(idAluno, idTurma) {
    try {
        const response = await fetch(`http://localhost:8080/notas/aluno/${idAluno}/turmas/${idTurma}`);
        if (!response.ok) { 
            throw new Error("Falha ao buscar notas do aluno");
        }
        const notas = await response.json();
        return notas.length > 0 ? notas[0] : {};
    } catch (error) {
        console.error("Erro ao carregar notas do aluno:", error);
        return {};
    }
}

// Função para abrir o card de detalhes de um aluno específico
function abrirDetalhes(idAluno, idTurma, idNota, idDisciplina) {
    // Remover qualquer card existente antes de criar um novo
    const cardExistente = document.getElementById("detalhes-card");
    if (cardExistente) {
        cardExistente.remove();
    }

    const card = document.createElement("div");
    card.id = "detalhes-card";
    card.className = "detalhes-card";
    card.innerHTML = `
        <h2>Editar Notas do Aluno</h2>
        <form id="form-detalhes">
            <input type="hidden" name="id_aluno" value="${idAluno}">
            <input type="hidden" name="id_turma" value="${idTurma}">
            <label>P1: <input type="number" name="prova1"></label>
            <label>P2: <input type="number" name="prova2"></label>
            <label>P3: <input type="number" name="prova3"></label>
            <label>T1: <input type="number" name="trabalho1"></label>
            <label>T2: <input type="number" name="trabalho2"></label>
            <label>T3: <input type="number" name="trabalho3"></label>
            <button type="button" onclick="salvarDetalhes(${idAluno}, ${idTurma}, ${idNota}, ${idDisciplina})">Salvar</button>
            <button type="button" onclick="fecharDetalhes()">Fechar</button>
        </form>
    `;

    document.body.appendChild(card);

    carregarNotasParaDetalhes(idAluno, idTurma);
}


// Função para carregar as notas de um aluno específico no card de detalhes
async function carregarNotasParaDetalhes(idAluno, idTurma) {
    try {
        const notas = await carregarNotasDoAluno(idAluno, idTurma);
        const form = document.getElementById("form-detalhes");
        form.elements["prova1"].value = notas.prova1 ?? 0;
        form.elements["prova2"].value = notas.prova2 ?? 0;
        form.elements["prova3"].value = notas.prova3 ?? 0;
        form.elements["trabalho1"].value = notas.trabalho1 ?? 0;
        form.elements["trabalho2"].value = notas.trabalho2 ?? 0;
        form.elements["trabalho3"].value = notas.trabalho3 ?? 0;
    } catch (error) {
        console.error("Erro ao carregar notas para detalhes:", error);
    }
}

// Função para salvar os detalhes de um aluno específico
async function salvarDetalhes(idAluno, idTurma, idNota, idDisciplina) {
    const form = document.getElementById("form-detalhes");
    const notas = {
        notaProva1: parseFloat(form.elements["prova1"].value) || 0,
        notaProva2: parseFloat(form.elements["prova2"].value) || 0,
        notaProva3: parseFloat(form.elements["prova3"].value) || 0,
        notaTrabalho1: parseFloat(form.elements["trabalho1"].value) || 0,
        notaTrabalho2: parseFloat(form.elements["trabalho2"].value) || 0,
        notaTrabalho3: parseFloat(form.elements["trabalho3"].value) || 0
    };

    try {
        const response = await fetch(`http://localhost:8080/notas/${idNota}/turma/${idTurma}/disciplina/${idDisciplina}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(notas),
        });

        if (!response.ok) {
            throw new Error("Falha ao salvar detalhes");
        }
        alert("Detalhes salvos com sucesso!");
        fecharDetalhes();
        carregarAlunosDaTurma(idTurma); // Atualizar a lista de alunos e notas
    } catch (error) {
        console.error("Erro ao salvar detalhes:", error);
        alert("Não foi possível salvar os detalhes. Por favor, tente novamente.");
    }
}


// Função para fechar o card de detalhes
function fecharDetalhes() {
    const card = document.getElementById("detalhes-card");
    if (card) {
        card.remove();
    }
}
