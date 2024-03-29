async function login() {

    let email = document.getElementById('email').value;
    let senha = document.getElementById('senha').value;
        
    console.log("Email: " + email + " Senha: " + senha); 

    fetch("http://localhost:8080/aluno/login", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            senha: senha
        })
    })
    .then(response => {
        if (response.ok) {
            alert("Login bem-sucedido!");
        } else {
            alert("Credenciais inválidas. Por favor, tente novamente.");
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
        alert("Ocorreu um erro ao fazer login. Por favor, tente novamente mais tarde.");
    });

}

function limpar() {
    email.value = "";
    senha.value = "";
}

// formulario.addEventListener('submit', function(event) {
//     event.preventDefault();
//     login();
//     limpar();
// });
