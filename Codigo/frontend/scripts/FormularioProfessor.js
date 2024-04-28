document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('formProfessor');
    const disciplinasSelect = document.getElementById('disciplinas');

    // Função para carregar disciplinas do servidor e preencher o select
    function carregarDisciplinas() {
        fetch('http://localhost:8080/disciplina')
            .then(response => response.json())
            .then(data => {
                data.forEach(disciplina => {
                    // Criar a opção do select com o ID como valor e o nome como texto
                    const option = document.createElement('option');
                    option.value = disciplina.idDisciplina;
                    option.text = disciplina.nomeDisciplina;
                    option.setAttribute('data-carga-horaria', disciplina.cargaHoraria); 
                    disciplinasSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Erro ao carregar disciplinas:', error));
    }

    // Chamada para carregar as disciplinas quando a página carregar
    carregarDisciplinas();

    

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Obtendo os valores selecionados do formulário
        const nomeProfessor = document.getElementById('nome_professor').value;
        const cpfProfessor = document.getElementById('cpf_professor').value;
        const dataNascimento = document.getElementById('data_nascimento').value;
        const formacaoProfessor = document.getElementById('formacao_professor').value;
        const disciplinaId = disciplinasSelect.options[disciplinasSelect.selectedIndex].value;
        const emailProfessor = document.getElementById('email_professor').value;

        // Obtendo a carga horária da disciplina selecionada
        const cargaHoraria = parseFloat(disciplinasSelect.options[disciplinasSelect.selectedIndex].getAttribute('data-carga-horaria'));

        // Construindo o objeto formData com todos os valores
        const formData = {
            nome_professor: nomeProfessor,
            cpf_professor: cpfProfessor,
            data_nascimento: dataNascimento,
            formacao_professor: formacaoProfessor,
            disciplina: {
                idDisciplina: disciplinaId,
                nomeDisciplina: disciplinasSelect.options[disciplinasSelect.selectedIndex].text,
                cargaHoraria: cargaHoraria
            },
            email_professor: emailProfessor
        };
        
        fetch('http://localhost:8080/professor', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            alert('Professor cadastrado com sucesso!');
            form.reset();  
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Erro ao cadastrar o professor. Detalhe: ' + error.message);
        });
    });
});
