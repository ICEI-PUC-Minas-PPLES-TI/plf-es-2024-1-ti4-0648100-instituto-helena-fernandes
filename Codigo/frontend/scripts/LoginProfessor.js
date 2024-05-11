function login() {
    let emailProfessor = document.getElementById('emailProfessor').value;
    let senhaProfessor = document.getElementById('senha_professor').value;

    fetch("http://localhost:8080/professor/login", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            emailProfessor: emailProfessor,
            senha_professor: senhaProfessor
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
        if (data === -1) {
            alert("Credenciais inválidas. Por favor, tente novamente.");
        } else {
            // Redirecionar para a página do professor com o ID do professor como parâmetro na URL
            window.location.href = `../views/TelaProfessor.html?id=${data}`;
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
        alert("Ocorreu um erro ao fazer login. Por favor, tente novamente mais tarde.");
    });
}
