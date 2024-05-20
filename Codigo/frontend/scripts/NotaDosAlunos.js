// Função para extrair parâmetros da URL
function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

// Função para carregar os alunos da turma
function carregarAlunosDaTurma(idTurma) {
    fetch(`http://localhost:8080/turma/${idTurma}/alunos`)
        .then(response => response.json())
        .then(alunos => {
            const listaAlunos = document.getElementById('lista-alunos');
            alunos.forEach(aluno => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${aluno.nome_aluno}</td>
                    <td><input type="number" name="prova1" data-id-aluno="${aluno.id_aluno}" /></td>
                    <td><input type="number" name="prova2" data-id-aluno="${aluno.id_aluno}" /></td>
                    <td><input type="number" name="prova3" data-id-aluno="${aluno.id_aluno}" /></td>
                    <td><input type="number" name="trabalho1" data-id-aluno="${aluno.id_aluno}" /></td>
                    <td><input type="number" name="trabalho2" data-id-aluno="${aluno.id_aluno}" /></td>
                    <td><input type="number" name="trabalho3" data-id-aluno="${aluno.id_aluno}" /></td>
                `;
                listaAlunos.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar alunos:', error);
            alert('Não foi possível carregar os alunos. Por favor, tente novamente.');
        });
}

// Função para salvar as notas
function salvarNotas(event) {
    event.preventDefault();

    const idTurma = getParameterByName('id_turma');
    console.log('IdTurma:', idTurma);
    const idProfessor = getParameterByName('id_professor');
    console.log('idProfessor',idProfessor);
    const idDisciplina = getParameterByName('id_disciplina');
    console.log('idDisciplina:', idDisciplina);

    if (!idTurma || !idProfessor || !idDisciplina) {
        alert('ID da turma, professor ou disciplina não encontrado.');
        return;
    }

    const inputs = document.querySelectorAll('input[type="number"]');
    const notas = [];

    inputs.forEach(input => {
        const idAluno = input.getAttribute('data-id-aluno');
        const nomeNota = input.getAttribute('name');
        const valorNota = input.value;

        let nota = notas.find(n => n.id_aluno == idAluno);
        if (!nota) {
            nota = {
                id_aluno: idAluno,
                id_professor: idProfessor,
                id_disciplina: idDisciplina,
                id_turma: idTurma
            };

            notas.push(nota);
        }

        nota[nomeNota] = valorNota;
    });

    // Log the data to be sent
    console.log('Dados enviados:', notas);

    fetch('http://localhost:8080/notas/multiple', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(notas)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Falha ao salvar notas');
        }
        alert('Notas salvas com sucesso!');
    })
    .catch(error => {
        console.error('Erro ao salvar notas:', error);
        alert('Não foi possível salvar as notas. Por favor, tente novamente.');
    });
}

// Carregar os alunos quando o documento estiver pronto
document.addEventListener('DOMContentLoaded', function() {
    const idTurma = getParameterByName('id_turma');
    if (idTurma) {
        carregarAlunosDaTurma(idTurma);
    } else {
        alert('ID da turma não encontrado.');
    }

    document.getElementById('notas-form').addEventListener('submit', salvarNotas);
});
