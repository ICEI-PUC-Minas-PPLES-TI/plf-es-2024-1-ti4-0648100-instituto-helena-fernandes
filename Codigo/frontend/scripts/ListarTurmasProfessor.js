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

// Função para carregar as turmas do banco de dados
function carregarTurmas() {
    fetch('http://localhost:8080/turmas')
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

// Chamar a função para carregar as turmas quando a página carregar
document.addEventListener('DOMContentLoaded', carregarTurmas);
