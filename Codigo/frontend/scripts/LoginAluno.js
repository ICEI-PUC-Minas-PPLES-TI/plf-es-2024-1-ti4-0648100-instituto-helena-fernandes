const formulario = document.querySelector("form");

const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");

function login() {
    fetch("http://localhost:8080/aluno/login", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: Iemail.value,
            senha: Isenha.value,
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
    Iemail.value = "";
    Isenha.value = "";
}

formulario.addEventListener('submit', function(event) {
    event.preventDefault();
    login();
    limpar();
});
