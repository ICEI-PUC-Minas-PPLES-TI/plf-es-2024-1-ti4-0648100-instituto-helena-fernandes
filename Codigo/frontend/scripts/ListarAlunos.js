fetch('http://localhost:8080/aluno?status_aluno=0')
    .then(response => response.json())
    .then(alunos => {
        const lista = document.getElementById('lista-alunos');
        alunos.forEach(aluno => {
            const item = document.createElement('li');
            item.textContent = aluno.nome_aluno;

            // Criar botão de detalhes
            const botaoDetalhes = document.createElement('button');
            botaoDetalhes.textContent = 'Detalhes';

            // Criar um div para detalhes do aluno
            const detalhesDiv = document.createElement('div');
            detalhesDiv.style.display = 'none'; // Esconde por padrão
            detalhesDiv.innerHTML = `
                <strong><p>Email:</strong> ${aluno.emailAluno}</p>
                <strong><p>CPF:</strong> ${aluno.cpf_aluno || 'Não informado'}</p>
                <strong><p>Data de Nascimento:</strong> ${aluno.data_nascimento || 'Não informado'}</p>
                <strong><p>Alergia:</strong> ${aluno.alergia || 'Não informado'}</p>
                <strong><p>Cidade:</strong> ${aluno.cidade || 'Não informado'}</p>
                <strong><p>Bairro:</strong> ${aluno.bairro || 'Não informado'}</p>
                <strong><p>Rua:</strong> ${aluno.rua || 'Não informado'}</p>
                <strong><p>Número da Casa:</strong> ${aluno.numero_casa || 'Não informado'}</p>
                <strong><p>Mais Informações:</strong> ${aluno.mais_informacoes || 'Não informado'}</p>
                <strong><p>Nome do Responsável:</strong> ${aluno.nome_responsavel || 'Não informado'}</p>
                <strong><p>CPF do Responsável:</strong> ${aluno.cpf_responsavel || 'Não informado'}</p>
                <strong><p>Telefone do Responsável:</strong> ${aluno.telefone_responsavel || 'Não informado'}</p>
                <button class="botao_aprovar" data-id_aluno="${aluno.id_aluno}">Aprovar</button>
                <button class="botao_reprovar" data-id_aluno="${aluno.id_aluno}">Reprovar</button>
            `;
            
            console.log('Botão de aprovar criado com data-id_aluno:', aluno.id);

            // Configurar o clique no botão para mostrar/esconder os detalhes
            botaoDetalhes.onclick = function() {
                detalhesDiv.style.display = detalhesDiv.style.display === 'none' ? 'block' : 'none';
            };

            // Adicionar texto, botão de detalhes e detalhes ao item da lista
            item.appendChild(botaoDetalhes);
            item.appendChild(detalhesDiv);

            // Adicionar o item da lista ao DOM
            lista.appendChild(item);
        });

        // Adicionar eventos de clique aos botões de aprovar e reprovar
        document.querySelectorAll('.botao_aprovar').forEach(button => {
            button.addEventListener('click', function() {
                const idAluno = this.getAttribute('data-id_aluno');
                console.log('Botão associado ao id:', idAluno);
                if (idAluno) {
                    // Confirmar a ação do cliente
                    const confirmacao = confirm('Tem certeza que deseja aprovar este aluno?');
                    if (confirmacao) {
                        atualizarStatusAluno(idAluno, 1);
                    } else {
                        console.log('Ação cancelada pelo cliente.');
                    }
                } else {
                    console.error('ID do aluno não encontrado.');
                }
            });

        });        

        document.querySelectorAll('.botao_reprovar').forEach(button => {
            button.addEventListener('click', function() {
                const idAluno = this.getAttribute('data-id_aluno');
                if (idAluno) {
                    const confirmacao = confirm('Tem certeza que deseja reprovar este aluno?');
                    if (confirmacao) {
                        atualizarStatusAluno(idAluno, 2);                        
                    } else {
                        console.log('Ação cancelada pelo cliente.');
                    }
                } else {
                    console.error('ID do aluno não encontrado.');
                }
            });
        });
    })

// Função para atualizar o status do aluno
function atualizarStatusAluno(idAluno, novoStatus) {
    // Falta a lógica de atualizar no banco de dados mas eu ainda n consigo pegar o id corretamente então n mexi nisso ainda --Arthur
    fetch(`http://localhost:8080/aluno/${idAluno}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            status_aluno: novoStatus,
        }),
    })
    .then(response => {
        if (response.ok) {
            alert('Situação do aluno alterada com sucesso!');
            window.location.reload();
        } else {
            console.error('Erro ao atualizar aluno:', response.statusText);
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Ocorreu um erro ao atualizar o aluno. Por favor, tente novamente.');
    });
    console.log(`Atualizando status do aluno ${idAluno} para ${novoStatus}`);
}