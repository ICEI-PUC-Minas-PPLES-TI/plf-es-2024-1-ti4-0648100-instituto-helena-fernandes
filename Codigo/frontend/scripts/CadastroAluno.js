const formulario = document.querySelector("form");

const Inome = document.querySelector(".nome_aluno");
const Iemail = document.querySelector(".email_aluno");
const Isenha = document.querySelector(".senha_aluno");

function cadastrar() {
    fetch("http://localhost:8080/aluno", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome_aluno: Inome.value,
            email_aluno: Iemail.value,
            senha_aluno: Isenha.value
        })
    })
    .then(function (response) {
        if (response.ok) {
            // Redirecionamento após sucesso
            window.location.href = "../views/ConfirmacaoAluno.html";
        } else {
            // Tratar caso não seja possível cadastrar o aluno
            console.error('Erro ao cadastrar aluno:', response.statusText);
        }
    })
    .catch(function (error) {
        console.error('Erro ao cadastrar aluno:', error);
    });
};

function limpar() {
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
};

formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    cadastrar();
    limpar();
});
