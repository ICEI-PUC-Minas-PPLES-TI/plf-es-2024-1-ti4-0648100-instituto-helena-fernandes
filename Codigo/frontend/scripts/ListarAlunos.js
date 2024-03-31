// Configura o clique no botão para mostrar/esconder os detalhes

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
            
           <strong> <p>Email:  </strong>${aluno.emailAluno}</p>
           <strong> <p>CPF:</strong> ${aluno.cpf_aluno || 'Não informado'}</p>
           <strong> <p>Data de Nascimento:</strong> ${aluno.data_nascimento || 'Não informado'}</p>
           <strong>  <p>Alergia:</strong> ${aluno.alergia || 'Não informado'}</p>
           <strong> <p>Cidade:</strong> ${aluno.cidade || 'Não informado'}</p>
           <strong> <p>Bairro:</strong> ${aluno.bairro || 'Não informado'}</p>
           <strong> <p>Rua: </strong>${aluno.rua || 'Não informado'}</p>
           <strong>  <p>Número da Casa:</strong> ${aluno.numero_casa || 'Não informado'}</p>
           <strong> <p>Mais Informações: </strong>${aluno.mais_informacoes || 'Não informado'}</p>
           <strong> <p>Nome do Responsável: </strong>${aluno.nome_responsavel || 'Não informado'}</p>
           <strong> <p>CPF do Responsável:</strong> ${aluno.cpf_responsavel || 'Não informado'}</p>
           <strong>  <p>Telefone do Responsável:</strong> ${aluno.telefone_responsavel || 'Não informado'}</p>
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