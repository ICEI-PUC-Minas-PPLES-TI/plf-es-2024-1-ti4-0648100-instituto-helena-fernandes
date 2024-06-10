document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const idAluno = urlParams.get('idAluno');

    if (idAluno) {
        // Atualiza os links dos botões com o ID do aluno
        document.getElementById('linkVisualizarNotas').href = `./VisualizarNota.html?idAluno=${idAluno}`;
        document.getElementById('linkVisualizarHorarios').href = `./VisualizarHorariosAluno.html?idAluno=${idAluno}`;
    } else {
        alert("ID do aluno não encontrado.");
    }
});
