fetch('http://localhost:8080/aluno?status_aluno=0')
    .then(response => response.json())
    .then(alunos => {
        const lista = document.getElementById('lista-alunos');
        
        // Verifica se não há alunos pendentes
        if (alunos.length === 0) {
            const mensagem = document.createElement('h1');
            mensagem.textContent = 'Nenhuma pré-matrícula pendente de aprovação.';
            mensagem.style.textAlign = 'center';
            mensagem.style.marginTop = '60px';
            mensagem.style.color = '#FFF';
            
            lista.appendChild(mensagem);
        } else {
            // Se houver alunos, percorre a lista normalmente
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
                    <strong><p>Série do aluno:</strong> ${aluno.serie || 'Não informado'}</p>
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
        }

        // Botões de aprovar e reprovar aluno
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
    .catch(error => {
        console.error('Erro ao obter alunos:', error);
        // Tratar o erro conforme necessário
    });

// Função para atualizar o status do aluno
function atualizarStatusAluno(idAluno, novoStatus) {
    fetch(`http://localhost:8080/aluno/${idAluno}/status`, {
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
