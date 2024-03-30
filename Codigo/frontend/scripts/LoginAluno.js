async function login() {
    console.log("Botão ok")
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
            senha_aluno: senhaAluno // Corrigido para corresponder ao nome no backend
        })
    })
    .then(response => {
        if (response.ok) {
            alert("Conta bem-sucedida!");
            // Aqui você pode adicionar outras ações em caso de sucesso, como redirecionar para outra página.
        } else {
            alert("Credenciais inválidas. Por favor, tente novamente.");
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
        alert("Ocorreu um erro ao fazer login. Por favor, tente novamente mais tarde.");
    });
}
