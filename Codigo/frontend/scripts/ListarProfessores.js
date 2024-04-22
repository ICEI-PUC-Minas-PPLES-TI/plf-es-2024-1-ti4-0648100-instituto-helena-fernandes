fetch('http://localhost:8080/professor')
    .then(response => response.json())
    .then(professores => {
        const lista = document.getElementById('lista-professores');
        professores.forEach(professor => {
            const item = document.createElement('li');
            item.classList.add('professor-item');

            // Nome do professor
            const nomeProfessor = document.createElement('h2');
            nomeProfessor.textContent = professor.nome_professor;

            // Disciplina do professor
            const disciplinaProfessor = document.createElement('p');
            disciplinaProfessor.textContent = `Disciplina: ${professor.disciplina_professor}`;

            // Botão de detalhes
            const botaoDetalhes = document.createElement('button');
            botaoDetalhes.textContent = 'Detalhes';

            // Div para detalhes do professor
            const detalhesDiv = document.createElement('div');
            detalhesDiv.classList.add('detalhes');
            detalhesDiv.style.display = 'none'; // Esconde por padrão

            // Configurar o clique no botão para mostrar/esconder os detalhes
            botaoDetalhes.addEventListener('click', function() {
                if (detalhesDiv.style.display === 'none') {
                    detalhesDiv.innerHTML = `
                        <p><strong>CPF:</strong> ${professor.cpf_professor}</p>
                        <p><strong>Data de Nascimento:</strong> ${professor.data_nascimento}</p>
                        <p><strong>Formação:</strong> ${professor.formacao_professor}</p>
                        <p><strong>Disciplina:</strong> ${professor.disciplina_professor}</p>
                        <p><strong>Email:</strong> ${professor.email_professor}</p>
                    `;
                    detalhesDiv.style.display = 'block';
                } else {
                    detalhesDiv.style.display = 'none';
                }
            });

            // Adicionar elementos ao item
            item.appendChild(nomeProfessor);
            item.appendChild(disciplinaProfessor);
            item.appendChild(botaoDetalhes);
            item.appendChild(detalhesDiv);

            // Adicionar o item à lista
            lista.appendChild(item);
        });
    })
    .catch(error => {
        console.error('Erro ao buscar professores:', error);
        alert('Ocorreu um erro ao buscar os professores. Por favor, tente novamente.');
    });
