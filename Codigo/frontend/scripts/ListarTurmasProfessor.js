// Função para criar um item de turma na lista
function criarItemTurma(turma) {
    const item = document.createElement('li');
    item.classList.add('card'); // Adicionar a classe 'card' ao elemento <li>

    // Nome da turma
    const nomeTurma = document.createElement('h2');
    nomeTurma.textContent = turma.nomeTurma;

    // Período da turma
    const periodoTurma = document.createElement('p');
    periodoTurma.textContent = `Período: ${turma.periodo}`;

    // Adicionar elementos ao item
    item.appendChild(nomeTurma);
    item.appendChild(periodoTurma);

    return item;
}

// Função para obter o ID do professor da URL
function getProfessorIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}

// Função para carregar o nome da disciplina do professor
function carregarNomeDisciplina() {
    const idProfessor = getProfessorIdFromUrl();

    // Verifica se o ID do professor foi obtido corretamente
    if (!idProfessor) {
        console.error('ID do professor não encontrado na URL');
        alert('O ID do professor não foi encontrado na URL. Por favor, tente novamente.');
        return;
    }

    fetch(`http://localhost:8080/professor/${idProfessor}/disciplinas`)
        .then(response => response.json())
        .then(disciplinas => {
            if (disciplinas.length > 0) {
                const nomeDisciplina = disciplinas[0].nome_disciplina; // Assume que o professor só tem uma disciplina
                document.querySelector('.text-center h1').textContent = nomeDisciplina;
            } else {
                console.error('Nenhuma disciplina encontrada para o professor');
                alert('Nenhuma disciplina encontrada para o professor. Por favor, verifique as configurações.');
            }
        })
        .catch(error => {
            console.error('Erro ao carregar disciplina:', error);
            alert('Ocorreu um erro ao carregar a disciplina. Por favor, tente novamente.');
        });
}

// Função para carregar as turmas do banco de dados para um professor específico
function carregarTurmas() {
    const idProfessor = getProfessorIdFromUrl();

    // Verifica se o ID do professor foi obtido corretamente
    if (!idProfessor) {
        console.error('ID do professor não encontrado na URL');
        alert('O ID do professor não foi encontrado na URL. Por favor, tente novamente.');
        return;
    }

    fetch(`http://localhost:8080/professor/${idProfessor}/turmas`)
        .then(response => response.json())
        .then(turmas => {
            const listaTurmas = document.getElementById('lista-turmas');
            listaTurmas.innerHTML = ''; // Limpar a lista antes de adicionar as turmas

            turmas.forEach(turma => {
                const itemTurma = criarItemTurma(turma);
                listaTurmas.appendChild(itemTurma);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar turmas:', error);
            alert('Ocorreu um erro ao carregar as turmas. Por favor, tente novamente.');
        });
}

// Chamar a função para carregar o nome da disciplina quando a página carregar
document.addEventListener('DOMContentLoaded', carregarNomeDisciplina);

// Chamar a função para carregar as turmas quando a página carregar
document.addEventListener('DOMContentLoaded', carregarTurmas);
