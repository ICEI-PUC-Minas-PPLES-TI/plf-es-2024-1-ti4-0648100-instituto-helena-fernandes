function login() {
    let emailAdmin = document.getElementById('emailAdmin').value;
    let senhaAdmin = document.getElementById('senha_admin').value;

    fetch("http://localhost:8080/administrador/login", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            emailAdmin: emailAdmin,
            senha_admin: senhaAdmin
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
            window.location.href = `../views/TelaAdministrador.html?id=${data}`;
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
        alert("Credenciais inválidas. Por favor, tente novamente.");
    });
}
