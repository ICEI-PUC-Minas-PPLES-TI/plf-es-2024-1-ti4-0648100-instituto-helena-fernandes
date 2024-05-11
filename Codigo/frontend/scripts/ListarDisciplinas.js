// Função para criar um item de disciplina na lista
function criarItemDisciplina(disciplina) {
    const item = document.createElement('li');
    item.classList.add('disciplina-item');

    // Nome da disciplina
    const nomeDisciplina = document.createElement('h2');
    nomeDisciplina.textContent = disciplina.nomeDisciplina;

    // Carga horária da disciplina
    const cargaHoraria = document.createElement('p');
    cargaHoraria.textContent = `Carga Horária: ${disciplina.cargaHoraria}`;

    // Botão de editar
    const botaoEditar = document.createElement('button');
    botaoEditar.textContent = 'Editar';
    botaoEditar.classList.add('botao-editar');

    // Botão de excluir
    const botaoExcluir = document.createElement('button');
    botaoExcluir.textContent = 'Excluir';
    botaoExcluir.classList.add('botao-excluir');

    // Configurar o clique no botão de excluir para enviar a solicitação de exclusão para o backend
    botaoExcluir.addEventListener('click', function() {
        if (confirm(`Tem certeza de que deseja excluir a disciplina ${disciplina.nomeDisciplina}?`)) {
            fetch(`http://localhost:8080/disciplina/${disciplina.idDisciplina}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao excluir a disciplina');
                }
                // Remover o item da lista após a exclusão bem-sucedida
                item.remove();
                alert('Disciplina excluída com sucesso!');
            })
            .catch(error => {
                console.error('Erro ao excluir a disciplina:', error);
                alert('Ocorreu um erro ao excluir a disciplina. Por favor, tente novamente.');
            });
        }
    });

    // Configurar o clique no botão de editar para redirecionar para a página de edição da disciplina
    botaoEditar.addEventListener('click', function() {
        // Redirecionar para a página de edição (EditarDisciplina.html) com os parâmetros de URL
        window.location.href = `./EditarDisciplina.html?id=${disciplina.idDisciplina}&nome=${encodeURIComponent(disciplina.nomeDisciplina)}&cargaHoraria=${disciplina.cargaHoraria}`;
    });

    // Adicionar elementos ao item
    item.appendChild(nomeDisciplina);
    item.appendChild(cargaHoraria);
    item.appendChild(botaoEditar);
    item.appendChild(botaoExcluir);

    return item;
}

// Buscar disciplinas da API e adicionar à lista
fetch('http://localhost:8080/disciplina')
    .then(response => response.json())
    .then(disciplinas => {
        const lista = document.getElementById('lista-disciplinas');
        disciplinas.forEach(disciplina => {
            const item = criarItemDisciplina(disciplina);
            lista.appendChild(item);
        });
    })
    .catch(error => {
        console.error('Erro ao buscar disciplinas:', error);
        alert('Ocorreu um erro ao buscar as disciplinas. Por favor, tente novamente.');
    });
