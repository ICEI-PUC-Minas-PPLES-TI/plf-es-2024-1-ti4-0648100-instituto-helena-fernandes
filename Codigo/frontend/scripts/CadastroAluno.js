const formulario = document.querySelector("form");

const Inome = document.querySelector(".nome_aluno");
const Iemail = document.querySelector(".email_aluno");
const Isenha = document.querySelector(".senha_aluno");


function cadastrar() {

    fetch("http://localhost:8080/aluno",
    {
        headers: {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome_aluno: Inome.value,
            email_aluno: Iemail.value,
            senha_aluno: Isenha.value
        })
    })
    .then(function (res) { console.log (res) })
    .catch(function (res) { console.log (res) })
};

function limpar(){
    nome: Inome.value = "";
    email: Iemail.value = "";
    senha: Isenha.value = "";
};

formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    
    cadastrar();
    limpar();
});