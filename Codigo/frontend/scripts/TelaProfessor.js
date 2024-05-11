// Função para obter o parâmetro ID da URL
function getProfessorIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}

// Função para atualizar os links dos botões com o ID do professor como parâmetro
function atualizarLinksComIdProfessor() {
    const idProfessor = getProfessorIdFromUrl();

    const links = document.querySelectorAll('.bt a');
    links.forEach(link => {
        const href = link.getAttribute('href');
        if (href.includes('?')) {
            link.setAttribute('href', `${href}&id=${idProfessor}`);
        } else {
            link.setAttribute('href', `${href}?id=${idProfessor}`);
        }
    });
}

// Chamar a função para atualizar os links com o ID do professor ao carregar a página
document.addEventListener('DOMContentLoaded', atualizarLinksComIdProfessor);
