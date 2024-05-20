// Função para extrair parâmetros da URL
function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

// Função para criar um elemento de lista com a classe 'card'
function criarCard(turma, idProfessor, idDisciplina) {
    const item = document.createElement('li');
    item.classList.add('card'); // Adiciona a classe 'card' ao elemento <li>

    // Nome da turma
    const nomeTurma = document.createElement('h2');
    nomeTurma.textContent = 'Turma ' + turma.nome_turma;

    // Botão de detalhes
    const detalhesButton = document.createElement('button');
    detalhesButton.textContent = 'Gerenciar Notas';
    detalhesButton.classList.add('buttonDetalhes'); // Adiciona a classe 'buttonDetalhes' ao botão
    detalhesButton.addEventListener('click', function() {
        // Redireciona para a página de detalhes da turma
        window.location.href = `notaDosAlunos.html?id_turma=${turma.id_turma}&id_professor=${idProfessor}&id_disciplina=${idDisciplina}`;
    });

    // Adicionar elementos ao item
    item.appendChild(nomeTurma);
    item.appendChild(detalhesButton);

    return item;
}
async function obterDisciplinaDoProfessor(idProfessor) {
    try {
        const response = await fetch(`http://localhost:8080/professor/${idProfessor}`);
        if (!response.ok) {
            throw new Error('Falha ao buscar disciplina do professor');
        }
        const data = await response.json();
        console.log('Dados do professor:', data); // Log para depuração
        console.log('Disciplina do professor:', data.disciplina); // Verifique a estrutura do objeto disciplina
        return data.disciplina.idDisciplina; // Acesse diretamente o idDisciplina do objeto disciplina
    } catch (error) {
        console.error('Erro ao carregar disciplina:', error);
        return null;
    }
}


async function carregarTurmasDoProfessor(idProfessor) {
    try {
        const response = await fetch(`http://localhost:8080/professor/${idProfessor}/turmas`);
        if (!response.ok) {
            throw new Error('Falha ao buscar turmas');
        }
        const turmas = await response.json();
        const listaTurmas = document.getElementById('lista-turmas');
        
        // Obter a disciplina do professor uma vez
        const idDisciplina = await obterDisciplinaDoProfessor(idProfessor);
        if (idDisciplina === null) {
            throw new Error('Disciplina do professor não encontrada');
        }

        turmas.forEach(turma => {
            const item = criarCard(turma, idProfessor, idDisciplina);
            listaTurmas.appendChild(item);
        });
    } catch (error) {
        console.error('Erro ao carregar turmas:', error);
        alert('Não foi possível carregar as turmas. Por favor, tente novamente.');
    }
}


// Carregar as turmas quando o documento estiver pronto
document.addEventListener('DOMContentLoaded', function() {
    const idProfessor = getParameterByName('id'); // Obtendo o ID do professor da URL
    console.log('idProfessor:', idProfessor); // Adicionado para depuração
    carregarTurmasDoProfessor(idProfessor);
});
