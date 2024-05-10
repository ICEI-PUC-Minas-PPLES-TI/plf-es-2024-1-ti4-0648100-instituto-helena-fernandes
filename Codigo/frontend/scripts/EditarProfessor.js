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

    // Preencher o formulário com os dados do professor a ser editado
    const urlParams = new URLSearchParams(window.location.search);
    const idProfessor = urlParams.get('id');
    const nomeProfessor = decodeURIComponent(urlParams.get('nome'));
    const cpfProfessor = decodeURIComponent(urlParams.get('cpf'));
    const dataNascimento = decodeURIComponent(urlParams.get('dataNascimento'));
    const formacaoProfessor = decodeURIComponent(urlParams.get('formacao'));
    const disciplinaProfessor = decodeURIComponent(urlParams.get('disciplina'));
    const emailProfessor = decodeURIComponent(urlParams.get('email'));

    document.getElementById('nome_professor').value = nomeProfessor;
    document.getElementById('cpf_professor').value = cpfProfessor;
    document.getElementById('data_nascimento').value = dataNascimento;
    document.getElementById('formacao_professor').value = formacaoProfessor;
    document.getElementById('emailProfessor').value = emailProfessor;
    // Selecionar a disciplina correspondente no select
    const disciplinaOption = Array.from(disciplinasSelect.options).find(option => option.text === disciplinaProfessor);
    if (disciplinaOption) {
        disciplinaOption.selected = true;
    }

    // Preencher o campo disciplina com o nome da disciplina correspondente
    document.getElementById('disciplinas').value = disciplinaProfessor;

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Obtendo os novos valores do formulário
        const novoNomeProfessor = document.getElementById('nome_professor').value;
        const novoCpfProfessor = document.getElementById('cpf_professor').value;
        const novaDataNascimento = document.getElementById('data_nascimento').value;
        const novaFormacaoProfessor = document.getElementById('formacao_professor').value;
        const novaDisciplinaId = disciplinasSelect.options[disciplinasSelect.selectedIndex].value;
        const novoEmailProfessor = document.getElementById('emailProfessor').value;

        // Obtendo a carga horária da disciplina selecionada
        const novaCargaHoraria = parseFloat(disciplinasSelect.options[disciplinasSelect.selectedIndex].getAttribute('data-carga-horaria'));

        // Construindo o objeto formData com os novos valores
        const formData = {
            nome_professor: novoNomeProfessor,
            cpf_professor: novoCpfProfessor,
            data_nascimento: novaDataNascimento,
            formacao_professor: novaFormacaoProfessor,
            disciplina: {
                idDisciplina: novaDisciplinaId,
                nomeDisciplina: disciplinasSelect.options[disciplinasSelect.selectedIndex].text,
                cargaHoraria: novaCargaHoraria
            },
            emailProfessor: novoEmailProfessor
        };
        
        fetch(`http://localhost:8080/professor/${idProfessor}`, {
            method: 'PUT',
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
            alert('Professor atualizado com sucesso!');
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Mude os campos para poder alterar');
        });
    });
});
