document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const formData = {
            nome_professor: document.getElementById('nome_professor').value,
            cpf_professor: document.getElementById('cpf_professor').value,
            data_nascimento: document.getElementById('data_nascimento').value,
            formacao_professor: document.getElementById('formacao_professor').value,
            disciplina_professor: document.getElementById('disciplina_professor').value,
            email_professor: document.getElementById('email_professor').value,
        };
        console.log(formData)
        fetch('http://localhost:8080/api/professores', {
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
