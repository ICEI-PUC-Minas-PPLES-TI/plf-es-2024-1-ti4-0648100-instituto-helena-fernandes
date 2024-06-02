document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);
    const idAluno = urlParams.get('idAluno');

    if (idAluno) {
        fetch(`http://localhost:8080/notas/aluno/${idAluno}/disciplinas`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro na resposta do servidor');
                }
                return response.json();
            })
            .then(notasList => {
                const notasBody = document.getElementById('notas-body');
                notasBody.innerHTML = ''; // Limpar qualquer conteúdo anterior
                notasList.forEach(nota => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${nota.disciplinas.map(d => d.nomeDisciplina).join(', ')}</td>
                        <td>${nota.prova1}</td>
                        <td>${nota.prova2}</td>
                        <td>${nota.prova3}</td>
                        <td>${nota.trabalho1}</td>
                        <td>${nota.trabalho2}</td>
                        <td>${nota.trabalho3}</td>
                    `;
                    notasBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Erro ao carregar notas:', error);
                alert('Não foi possível carregar as notas. Por favor, tente novamente.');
            });
    } else {
        alert("ID do aluno não encontrado.");
    }
});
