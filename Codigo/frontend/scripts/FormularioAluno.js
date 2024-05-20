function atualizar() {
    // Extrair o ID do aluno da URL
    let urlParams = new URLSearchParams(window.location.search);
    let idAluno = urlParams.get('idAluno');

    console.log("Id aluno: " + idAluno);

    let cpf_aluno = document.getElementById('cpf_aluno').value;
    let data_nascimento = document.getElementById('data_nascimento').value;
    let serie = document.getElementById('serie').value;
    let alergia = document.getElementById('alergia').value;
    let cidade = document.getElementById('cidade').value;
    let bairro =  document.getElementById('bairro').value;
    let rua = document.getElementById('rua').value;
    let numero_casa = document.getElementById('numero_casa').value;
    let mais_informacoes = document.getElementById('mais_informacoes').value;
    let nome_responsavel = document.getElementById('nome_responsavel').value;
    let cpf_responsavel = document.getElementById('cpf_responsavel').value;
    let telefone_responsavel = document.getElementById('telefone_responsavel').value;
    let status = 0; // Define o status como 0 (Em análise)

    // Faz a requisição PUT para a rota de atualização no backend
    fetch(`http://localhost:8080/aluno/${idAluno}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            cpf_aluno: cpf_aluno,
            data_nascimento: data_nascimento,   
            serie: serie,             
            alergia: alergia,
            cidade: cidade,
            bairro: bairro,
            rua: rua,
            numero_casa: numero_casa,
            mais_informacoes: mais_informacoes,
            nome_responsavel: nome_responsavel,
            cpf_responsavel: cpf_responsavel,
            telefone_responsavel: telefone_responsavel,
            status_aluno: status
          }),
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '../views/ConfirmacaoAluno.html';
        } else {
            console.error('Erro ao atualizar aluno:', response.statusText);
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Ocorreu um erro ao atualizar o aluno. Por favor, tente novamente.');
    });
}
