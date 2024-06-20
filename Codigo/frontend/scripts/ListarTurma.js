function criarItemTurma(turma) {
    const item = document.createElement('li');
    item.classList.add('card'); // Adicionar a classe 'card' ao elemento <li>

    // Contêiner para o conteúdo da turma
    const turmaContainer = document.createElement('div');
    turmaContainer.classList.add('turma-container'); // Adicionando uma classe específica ao contêiner

    // Nome da turma
    const nomeTurma = document.createElement('h2');
    nomeTurma.textContent = turma.nome_turma;

    // Alunos da turma
    const alunosTexto = turma.alunos.map(aluno => aluno.nome_aluno).join(', ');
    const alunosTurma = document.createElement('p');
    alunosTurma.textContent = `Alunos: ${alunosTexto}`;

    // Professores da turma
    const professoresTexto = turma.professores.map(professor => professor.nome_professor).join(', ');
    const professoresTurma = document.createElement('p');
    professoresTurma.textContent = `Professores: ${professoresTexto}`;

    // Disciplinas da turma
    const disciplinasTexto = turma.disciplinas.map(disciplina => disciplina.nomeDisciplina).join(', ');
    const disciplinasTurma = document.createElement('p');
    disciplinasTurma.textContent = `Disciplinas: ${disciplinasTexto}`;

    // Botão de detalhes
    const botaoDetalhes = document.createElement('button');
    botaoDetalhes.textContent = 'Detalhes';
    botaoDetalhes.classList.add('botao-detalhes', 'botao-excluir');

    // Adicionar evento de clique ao botão de detalhes
    botaoDetalhes.addEventListener('click', function() {
        exibirDetalhesTurma(turma, item);
    });

    // Adicionar elementos ao contêiner
    turmaContainer.appendChild(nomeTurma);
    turmaContainer.appendChild(botaoDetalhes);

    // Adicionar o contêiner ao item
    item.appendChild(turmaContainer);

    return item;
}

// Função para exibir os detalhes da turma
function exibirDetalhesTurma(turma, item) {
    const detalhesConteudo = document.getElementById('detalhes-conteudo');
    detalhesConteudo.innerHTML = `
        <p><strong>Nome:</strong> ${turma.nome_turma}</p>
        <p><strong>Alunos:</strong> ${turma.alunos ? turma.alunos.map(a => a.nome_aluno).join(', ') : 'Nenhum'}</p>
        <p><strong>Professores:</strong> ${turma.professores ? turma.professores.map(p => p.nome_professor).join(', ') : 'Nenhum'}</p>
        <p><strong>Disciplinas:</strong> ${turma.disciplinas ? turma.disciplinas.map(d => d.nomeDisciplina).join(', ') : 'Nenhuma'}</p>
    `;

    document.getElementById('detalhes-overlay').style.display = 'block';
    document.getElementById('detalhes-card').style.display = 'block';

    document.getElementById('botao-editar').addEventListener('click', function() {
        window.location.href = `./EditarTurma.html?id=${turma.idTurma}&nome=${encodeURIComponent(turma.nome_turma)}&alunos=${encodeURIComponent(JSON.stringify(turma.alunos.map(p => p.nome_aluno)))}&professores=${encodeURIComponent(JSON.stringify(turma.professores.map(d => d.nome_professor)))}&disciplinas=${encodeURIComponent(JSON.stringify(turma.disciplinas.map(d => d.nomeDisciplina)))}`;
    });
    

    document.getElementById('botao-excluir').addEventListener('click', function() {
        if (confirm(`Tem certeza de que deseja excluir a turma ${turma.nome_turma}?`)) {
            fetch(`http://localhost:8080/turma/${turma.idTurma}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (!response.ok) {
                    throw error ('Erro ao excluir a turma');
                }
                item.remove();
                alert('Turma excluída com sucesso!');
            })
            .catch(error => {
                console.error('Erro ao excluir a turma:', error);
                alert('Ocorreu um erro ao excluir a turma. Por favor, tente novamente.');
            });
        }
    });
}

// Buscar turmas da API e adicionar à lista
fetch('http://localhost:8080/turma')
    .then(response => response.json())
    .then(turmas => {
        const lista = document.getElementById('lista-turma');
        turmas.forEach(turma => {
            const item = criarItemTurma(turma);
            lista.appendChild(item);
        });
    })
    .catch(error => {
        console.error('Erro ao buscar turmas:', error);
        alert('Ocorreu um erro ao buscar as turmas. Por favor, tente novamente.');
    });

document.getElementById('botao-voltar').addEventListener('click', function() {
    document.getElementById('detalhes-overlay').style.display = 'none';
    document.getElementById('detalhes-card').style.display = 'none';
});
