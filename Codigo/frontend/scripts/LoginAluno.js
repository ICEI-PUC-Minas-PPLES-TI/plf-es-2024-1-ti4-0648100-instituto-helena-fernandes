function login() {
    let emailAluno = document.getElementById('emailAluno').value;
    let senhaAluno = document.getElementById('senha_aluno').value;

    fetch("http://localhost:8080/aluno/login", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            emailAluno: emailAluno,
            senha_aluno: senhaAluno
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // Retorna o corpo da resposta como JSON
        } else {
            throw new Error('Login inválido'); // Lança um erro para ser capturado pelo bloco catch
        }
    })
    .then(data => {
        if (data !== -1) { // Verifica se o ID do aluno é diferente de -1 (indicando um login inválido)
            window.location.href = `../views/FormularioAluno.html?idAluno=${data}`;
        } else {
            alert("Credenciais inválidas. Por favor, tente novamente.");
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
        alert("Ocorreu um erro ao fazer login. Por favor, tente novamente mais tarde.");
    });
}
