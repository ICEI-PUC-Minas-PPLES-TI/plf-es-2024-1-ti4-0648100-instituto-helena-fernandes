document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('formProfessor');
    const disciplinasSelect = document.getElementById('disciplinas');

    // Função para carregar disciplinas do servidor e preencher o select
    function carregarDisciplinas() {
        fetch('http://localhost:8080/disciplina')
            .then(response => response.json())
            .then(data => {
                data.forEach(disciplina => {
                    const option = document.createElement('option');
                    option.value = disciplina.id_disciplina;
                    option.text = disciplina.nomeDisciplina;
                    disciplinasSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Erro ao carregar disciplinas:', error));
    }

    // Chamada para carregar as disciplinas quando a página carregar
    carregarDisciplinas();
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Convertendo o valor da disciplina para BigInt
        const disciplinaId = BigInt(disciplinasSelect.value);
        const formData = {
            nome_professor: document.getElementById('nome_professor').value,
            cpf_professor: document.getElementById('cpf_professor').value,            
            data_nascimento: document.getElementById('data_nascimento').value,     
            formacao_professor: document.getElementById('formacao_professor').value,
            disciplina: disciplinaId.toString(), // Convertendo BigInt para string
            email_professor: document.getElementById('email_professor').value
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
            form.reset();  // Limpa o formulário após o envio
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Erro ao cadastrar o professor. Detalhe: ' + error.message);
        });
    });
    
});
