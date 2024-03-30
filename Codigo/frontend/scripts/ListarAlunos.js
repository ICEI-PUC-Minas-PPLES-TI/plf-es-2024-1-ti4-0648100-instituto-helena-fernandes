fetch('http://localhost:8080/aluno')
.then(response => response.json())
.then(alunos => {
    const lista = document.getElementById('lista-alunos');
    alunos.forEach(aluno => {
        const item = document.createElement('li');
        item.textContent = aluno.nome_aluno; // Corrigindo para nome_aluno

        // Criar botão de detalhes
        const botaoDetalhes = document.createElement('button');
        botaoDetalhes.textContent = 'Detalhes';
        
        // Criar um div para detalhes do aluno
        const detalhesDiv = document.createElement('div');
        detalhesDiv.style.display = 'none'; // Esconde por padrão
        detalhesDiv.innerHTML = `
            
            <p>Email: ${aluno.emailAluno}</p>
            <p>CPF: ${aluno.cpf_aluno || 'Não informado'}</p>
            <p>Data de Nascimento: ${aluno.data_nascimento || 'Não informado'}</p>
            <p>Alergia: ${aluno.alergia || 'Não informado'}</p>
            <p>Cidade: ${aluno.cidade || 'Não informado'}</p>
            <p>Bairro: ${aluno.bairro || 'Não informado'}</p>
            <p>Rua: ${aluno.rua || 'Não informado'}</p>
            <p>Número da Casa: ${aluno.numero_casa || 'Não informado'}</p>
            <p>Mais Informações: ${aluno.mais_informacoes || 'Não informado'}</p>
            <p>Nome do Responsável: ${aluno.nome_responsavel || 'Não informado'}</p>
            <p>CPF do Responsável: ${aluno.cpf_responsavel || 'Não informado'}</p>
            <p>Telefone do Responsável: ${aluno.telefone_responsavel || 'Não informado'}</p>
            <button id="botao_aprovar">Aprovar</button>
            <button id="botao_reprovar">Reprovar</button>
        `;
        // Configura o clique no botão para mostrar/esconder os detalhes
        botaoDetalhes.onclick = function() {
            detalhesDiv.style.display = detalhesDiv.style.display === 'none' ? 'block' : 'none';
        };

        // Adiciona o texto, botão e detalhes ao item da lista
        item.appendChild(botaoDetalhes);
        item.appendChild(detalhesDiv);

        // Adiciona o item da lista ao DOM
        lista.appendChild(item);
    });
})
.catch(error => console.error('Falha ao buscar dados dos alunos:', error));