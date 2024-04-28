// Função para criar um item de professor na lista
function criarItemProfessor(professor) {
    const item = document.createElement('li');
    item.classList.add('professor-item');

    // Nome do professor
    const nomeProfessor = document.createElement('h2');
    nomeProfessor.textContent = professor.nome_professor;

    // Disciplina do professor (somente o nome da disciplina)
    const disciplinaProfessor = document.createElement('p');
    disciplinaProfessor.textContent = `Disciplina: ${professor.disciplina.nomeDisciplina}`;

    // Botão de detalhes
    const botaoDetalhes = document.createElement('button');
    botaoDetalhes.textContent = 'Detalhes';
    botaoDetalhes.classList.add('botao-detalhes');

    // Botão de editar
    const botaoEditar = document.createElement('button');
    botaoEditar.textContent = 'Editar';
    botaoEditar.classList.add('botao-editar');

    // Botão de excluir
    const botaoExcluir = document.createElement('button');
    botaoExcluir.textContent = 'Excluir';
    botaoExcluir.classList.add('botao-excluir');

    // Div para detalhes do professor
    const detalhesDiv = document.createElement('div');
    detalhesDiv.classList.add('detalhes');
    detalhesDiv.style.display = 'none'; // Esconde por padrão

    // Configurar o clique no botão de detalhes para mostrar/esconder os detalhes
    botaoDetalhes.addEventListener('click', function() {
        if (detalhesDiv.style.display === 'none') {
            detalhesDiv.innerHTML = `
                <p><strong>CPF:</strong> ${professor.cpf_professor}</p>
                <p><strong>Data de Nascimento:</strong> ${professor.data_nascimento}</p>
                <p><strong>Formação:</strong> ${professor.formacao_professor}</p>
                <p><strong>Disciplina:</strong> ${professor.disciplina.nomeDisciplina}</p>
                <p><strong>Email:</strong> ${professor.email_professor}</p>
            `;
            detalhesDiv.style.display = 'block';
        } else {
            detalhesDiv.style.display = 'none';
        }
    });

    // Configurar o clique no botão de excluir para enviar a solicitação de exclusão para o backend
    botaoExcluir.addEventListener('click', function() {
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

    // Configurar o clique no botão de editar para redirecionar para a página de edição do professor
    // Configurar o clique no botão de editar para redirecionar para a página de edição do professor
    botaoEditar.addEventListener('click', function() {
        // Redirecionar para a página de edição (EditarProfessor.html) com os parâmetros de URL
        window.location.href = `./EditarProfessor.html?id=${professor.id_professor}&nome=${encodeURIComponent(professor.nome_professor)}&cpf=${encodeURIComponent(professor.cpf_professor)}&dataNascimento=${encodeURIComponent(professor.data_nascimento)}&formacao=${encodeURIComponent(professor.formacao_professor)}&disciplina=${encodeURIComponent(professor.disciplina.nomeDisciplina)}&email=${encodeURIComponent(professor.email_professor)}`;
    });


    // Adicionar elementos ao item
    item.appendChild(nomeProfessor);
    item.appendChild(disciplinaProfessor);
    item.appendChild(botaoDetalhes);
    item.appendChild(botaoEditar);
    item.appendChild(botaoExcluir);
    item.appendChild(detalhesDiv);

    return item;
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
