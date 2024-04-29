document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('formDisciplina');

    // Preencher o formulário com os dados da disciplina a ser editada
    const urlParams = new URLSearchParams(window.location.search);
    const idDisciplina = urlParams.get('id');
    const nomeDisciplina = decodeURIComponent(urlParams.get('nome'));
    const cargaHoraria = decodeURIComponent(urlParams.get('cargaHoraria'));

    document.getElementById('nomeDisciplina').value = nomeDisciplina;
    document.getElementById('cargaHoraria').value = cargaHoraria;

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        
        // Obtendo os novos valores do formulário
        const novoNomeDisciplina = document.getElementById('nomeDisciplina').value;
        const novaCargaHoraria = document.getElementById('cargaHoraria').value;

        // Construindo o objeto formData com os novos valores
        const formData = {
            nomeDisciplina: novoNomeDisciplina,
            cargaHoraria: novaCargaHoraria
        };
        
        fetch(`http://localhost:8080/disciplina/${idDisciplina}`, {
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
            alert('Disciplina atualizada com sucesso!');
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Mude os campos para poder alterar');
        });
    });
});
