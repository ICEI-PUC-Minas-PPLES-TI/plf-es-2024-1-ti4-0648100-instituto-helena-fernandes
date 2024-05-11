// Função para criar um item de professor na lista
function criarItemProfessor(professor) {
    const item = document.createElement('li');
    item.classList.add('card'); // Adicionar a classe 'card' ao elemento <li>

    // Nome do professor
    const nomeProfessor = document.createElement('h2');
    nomeProfessor.textContent = professor.nome_professor;

    // Disciplina do professor (somente o nome da disciplina)
    const disciplinaProfessor = document.createElement('p');
    disciplinaProfessor.textContent = `Disciplina: ${professor.disciplina.nomeDisciplina}`;

    // Botão de detalhes
    const botaoDetalhes = document.createElement('button');
    botaoDetalhes.textContent = 'Detalhes';
    botaoDetalhes.classList.add('botao-detalhes', 'botao-azul'); // Adicionar classes 'botao-detalhes' e 'botao-azul'

    // Adicionar evento de clique ao botão de detalhes
    botaoDetalhes.addEventListener('click', function() {
        exibirDetalhesProfessor(professor, item); // Passar o item como parâmetro
    });

    // Adicionar elementos ao item
    item.appendChild(nomeProfessor);
    item.appendChild(disciplinaProfessor);
    item.appendChild(botaoDetalhes);

    return item;
}
// Função para exibir os detalhes do professor
function exibirDetalhesProfessor(professor, item) { 
    // Preencher o card de detalhes com as informações do professor
    const detalhesConteudo = document.getElementById('detalhes-conteudo');
    detalhesConteudo.innerHTML = `
        <p><strong>Nome:</strong> ${professor.nome_professor}</p>
        <p><strong>CPF:</strong> ${professor.cpf_professor}</p>
        <p><strong>Data de Nascimento:</strong> ${professor.data_nascimento}</p>
        <p><strong>Formação:</strong> ${professor.formacao_professor}</p>
        <p><strong>Disciplina:</strong> ${professor.disciplina.nomeDisciplina}</p>
        <p><strong>Email:</strong> ${professor.emailProfessor}</p>
    `;

    // Exibir o overlay e o card de detalhes
    document.getElementById('detalhes-overlay').style.display = 'block';
    document.getElementById('detalhes-card').style.display = 'block';

    // Configurar o clique no botão de editar para redirecionar para a página de edição do professor
    document.getElementById('botao-editar').addEventListener('click', function() {
        // Redirecionar para a página de edição (EditarProfessor.html)
        window.location.href = `./EditarProfessor.html?id=${professor.id_professor}&nome=${encodeURIComponent(professor.nome_professor)}&cpf=${encodeURIComponent(professor.cpf_professor)}&dataNascimento=${encodeURIComponent(professor.data_nascimento)}&formacao=${encodeURIComponent(professor.formacao_professor)}&disciplina=${encodeURIComponent(professor.disciplina.nomeDisciplina)}&email=${encodeURIComponent(professor.email_professor)}`;
    
    });

    // Configurar o clique no botão de excluir para enviar a solicitação de exclusão para o backend
    document.getElementById('botao-excluir').addEventListener('click', function() {
        if (confirm(`Tem certeza de que deseja excluir o professor ${professor.nome_professor}?`)) {
            fetch(`http://localhost:8080/professor/${professor.id_professor}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao excluir o professor');
                }
                // Remova o item da lista após a exclusão bem-sucedida
                item.remove(); 
                alert('Professor excluído com sucesso!');
            })
            .catch(error => {
                console.error('Erro ao excluir o professor:', error);
                alert('Ocorreu um erro ao excluir o professor. Por favor, tente novamente.');
            });
        }
    });
}

// Buscar professores da API e adicionar à lista
fetch('http://localhost:8080/professor')
    .then(response => response.json())
    .then(professores => {
        const lista = document.getElementById('lista-professores');
        professores.forEach(professor => {
            const item = criarItemProfessor(professor);
            lista.appendChild(item);
        });
    })
    .catch(error => {
        console.error('Erro ao buscar professores:', error);
        alert('Ocorreu um erro ao buscar os professores. Por favor, tente novamente.');
    });

// Configurar o clique no botão de voltar para ocultar os detalhes do professor
document.getElementById('botao-voltar').addEventListener('click', function() {
    document.getElementById('detalhes-overlay').style.display = 'none';
    document.getElementById('detalhes-card').style.display = 'none';
});
