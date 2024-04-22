// Função para enviar dados do formulário para o servidor
function enviarDadosDoFormulario() {
    // Obter os valores dos campos do formulário
    const nomeProfessor = document.getElementById('nome_professor').value;
    const cpfProfessor = document.getElementById('cpf_professor').value;
    const dataNascimento = document.getElementById('data_nascimento').value;
    const formacaoProfessor = document.getElementById('formacao_professor').value;
    const disciplinaProfessor = document.getElementById('disciplina_professor').value;
    const emailProfessor = document.getElementById('email_professor').value;

    // Construir o objeto de dados para enviar
    const dadosProfessor = {
        nome: nomeProfessor,
        cpf: cpfProfessor,
        dataNascimento: dataNascimento,
        formacao: formacaoProfessor,
        disciplina: disciplinaProfessor,
        email: emailProfessor
    };

    // URL do servidor backend
    const url = 'http://localhost:8080/backend/professores';

    // Opções da solicitação HTTP
    const opcoes = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dadosProfessor)
    };

    // Realizar a solicitação HTTP
    fetch(url, opcoes)
    .then(response => {
        if (!response.ok) {
            throw new Error('A resposta do servidor não foi OK');
        }
        return response.json();
    })
    .then(dadosResposta => {
        console.log('Dados salvos com sucesso:', dadosResposta);
        // Aqui você pode limpar o formulário ou redirecionar o usuário
    })
    .catch(erro => {
        console.error('Falha ao salvar os dados:', erro);
    });
}

// Adicionar ouvinte de evento para o formulário
document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    form.addEventListener('submit', function(evento) {
        evento.preventDefault(); // Prevenir o envio padrão do formulário
        enviarDadosDoFormulario();
    });
});
