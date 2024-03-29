const formulario = document.querySelector("form");

const cpf_aluno = document.querySelector('cpf_aluno');

        const data_nascimento = document.querySelector('data_nascimento');
        const alergia = document.querySelector('alergia');
        const cidade = document.querySelector('cidade');
        const bairro =  document.querySelector('bairro');
        const rua = document.querySelector('rua');
        const numero_casa = document.querySelector('numero_casa');
        const mais_informacoes = document.querySelector('mais_informacoes');
        const nome_responsavel = document.querySelector('nome_responsavel');
        const cpf_responsavel = document.querySelector('cpf_responsavel');
        const telefone_responsavel = document.querySelector('telefone_responsavel');

        console.log("Cpf: " + cpf_aluno + "Nascimento: " + data_nascimento); 

        function atualizar() {

        // ID do aluno que será atualizado (você pode definir isso dinamicamente com base no seu sistema)
        const id_aluno = 2; // Aqui assumimos que o ID é 1, substitua pelo valor correto

        // Faz a requisição PUT para a rota de atualização no backend
        fetch(`http://localhost:8080/aluno/${id_aluno}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                cpf_aluno: cpf_aluno,
                data_nascimento: data_nascimento,                
                alergia: alergia,
                cidade: cidade,
                bairro: bairro,
                rua: rua,
                cpf_aluno: cpf_aluno,
                numero_casa: numero_casa,
                mais_informacoes: mais_informacoes,
                nome_responsavel: nome_responsavel,
                cpf_responsavel: cpf_responsavel,
                telefone_responsavel: telefone_responsavel
              }),
        })
        .then(response => {
            if (response.ok) {
                // Aqui você pode decidir o que fazer após o sucesso da atualização
            alert('Aluno atualizado com sucesso!');
            // Por exemplo, redirecionar para outra página
            window.location.href = '../views/ConfirmacaoAluno.html';
            } else {
                console.error('Erro ao cadastrar aluno:', response.statusText);
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            // Trate o erro de acordo com sua necessidade
            alert('Ocorreu um erro ao atualizar o aluno. Por favor, tente novamente.');
        });
    }

