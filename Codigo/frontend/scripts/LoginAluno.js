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
        if (data === -1) {
            alert("Credenciais inválidas. Por favor, tente novamente.");
        } else {
            // Faz uma nova requisição para buscar o status do aluno
            fetch(`http://localhost:8080/aluno/${Math.abs(data)}`) // Usa o valor absoluto do ID para a requisição
            .then(response => response.json())
            .then(aluno => {
                switch (aluno.status_aluno) {
                    case "1":
                        window.location.href = "../views/TelaAceito.html";
                        break;
                    case "2":
                        window.location.href = "../views/TelaRecusado.html";
                        break;
                    case "0":
                        window.location.href = "../views/ConfirmacaoAluno.html";
                        break;
                    default:
                        window.location.href = `../views/FormularioAluno.html?idAluno=${data}`;
                }
            })
            .catch(error => {
                console.error('Erro ao buscar o status do aluno:', error);
                alert("Ocorreu um erro ao buscar informações do aluno. Por favor, tente novamente mais tarde.");
            });
        }
    })
    .catch(error => {
        console.error('Erro ao fazer login:', error);
        alert("Ocorreu um erro ao fazer login. Por favor, tente novamente mais tarde.");
    });
    
    
}
