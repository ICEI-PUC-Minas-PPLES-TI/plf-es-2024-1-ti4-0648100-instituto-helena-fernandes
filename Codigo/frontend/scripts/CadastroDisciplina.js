const formulario = document.querySelector("form");

function cadastrar() {
    const nomeDisciplina = document.getElementById("nome_disciplina").value;

    const cargaHoraria = document.getElementById("carga_horaria").value;

    fetch("http://localhost:8080/disciplina", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nomeDisciplina: nomeDisciplina,
            cargaHoraria: cargaHoraria
        })
    })
    .then(function (response) {
        if (response.ok) {
            alert("Disciplina cadastrada com sucesso!");
            // Redirecionamento após sucesso (opcional)
            // window.location.href = "../views/PaginaSucesso.html";
        } else {
            console.error('Erro ao cadastrar disciplina:', response.statusText);
            alert("Erro ao cadastrar disciplina. Verifique os dados e tente novamente.");
        }
    })
    .catch(function (error) {
        console.error('Erro ao cadastrar disciplina:', error);
        alert("Erro ao cadastrar disciplina. Verifique a conexão com o servidor e tente novamente.");
    });
};

function limpar() {
    document.getElementById("nome_disciplina").value = "";
    document.getElementById("carga_horaria").value = "";
};

formulario.addEventListener('submit', function (event) {
    event.preventDefault();
    cadastrar();
    limpar();
});
