const formulario = document.querySelector("form");

const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");


function cadastrar() {

    fetch("/http://localhost:8080/login",
    {
        headers: {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            email: Iemail.value,
            senha: Isenha.value
        })
    })
    .then(function (res) { console.log (res) })
    .catch(function (res) { console.log(res) })
};

function limpar(){
    email: Iemail.value = "";
    senha: Isenha.value = "";
};

formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    
    cadastrar();
    limpar();
});