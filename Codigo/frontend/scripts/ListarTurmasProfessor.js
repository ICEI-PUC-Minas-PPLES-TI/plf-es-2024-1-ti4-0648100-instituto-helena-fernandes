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
function criarCard(turma) {
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
        window.location.href = `notaDosAlunos.html?id_turma=${turma.id_turma}`;
    });

    // Adicionar elementos ao item
    item.appendChild(nomeTurma);
    item.appendChild(detalhesButton);

    return item;
}

// Função para carregar as turmas do professor
function carregarTurmasDoProfessor(idProfessor) {
    fetch(`http://localhost:8080/professor/${idProfessor}/turmas`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao buscar turmas');
            }
            return response.json();
        })
        .then(turmas => {
            const listaTurmas = document.getElementById('lista-turmas');
            turmas.forEach(turma => {
                const item = criarCard(turma);
                listaTurmas.appendChild(item);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar turmas:', error);
            alert('Não foi possível carregar as turmas. Por favor, tente novamente.');
        });
}

// Carregar as turmas quando o documento estiver pronto
document.addEventListener('DOMContentLoaded', function() {
    const idProfessor = getParameterByName('id'); // Obtendo o ID do professor da URL
    carregarTurmasDoProfessor(idProfessor);
});
