// Função para extrair parâmetros da URL
function getParameterByName(name, url = window.location.href) {
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}// Função para carregar as turmas do professor
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
                const item = document.createElement('li');
                item.textContent = turma.nome_turma;

                // Criar um botão para cada turma
                const botao = document.createElement('button');
                botao.textContent = 'Ver Notas';
                botao.addEventListener('click', function() {
                    // Redirecionar para a página notaDosAlunos.html com o id_turma
                    window.location.href = `notaDosAlunos.html?id_turma=${turma.id_turma}`;
                });
                item.appendChild(botao);

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
