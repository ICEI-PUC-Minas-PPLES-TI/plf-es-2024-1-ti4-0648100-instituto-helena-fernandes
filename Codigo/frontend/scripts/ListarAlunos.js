fetch('http://localhost:8080/aluno')
.then(response => response.json())
.then(alunos => {
    const lista = document.getElementById('lista-alunos');
    alunos.forEach(aluno => {
        const item = document.createElement('li');
        item.textContent = aluno.nome_aluno;
        lista.appendChild(item);
    });
})
.catch(error => console.error('Falha ao buscar dados dos alunos:', error));