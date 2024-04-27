document.addEventListener('DOMContentLoaded', function() {
    carregarAlunos();
    carregarProfessores();
    carregarDisciplinas();

    const form = document.getElementById('turmaForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        registrarTurma();
    });
});

function carregarAlunos() {
    fetch('http://localhost:8080/aluno')
        .then(response => response.json())
        .then(data => {
            const alunosCheckboxes = document.getElementById('alunosCheckboxes');
            data.forEach(aluno => {
                let checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = aluno.id_aluno;
                checkbox.name = 'alunos';
                checkbox.value = aluno.id_aluno;

                let label = document.createElement('label');
                label.htmlFor = aluno.id_aluno;
                label.appendChild(document.createTextNode(`${aluno.nome_aluno} (${aluno.emailAluno})`));

                let br = document.createElement('br');

                alunosCheckboxes.appendChild(checkbox);
                alunosCheckboxes.appendChild(label);
                alunosCheckboxes.appendChild(br);
            });
        });
}

function carregarProfessores() {
    fetch('http://localhost:8080/professor')
        .then(response => response.json())
        .then(data => {
            const professoresCheckboxes = document.getElementById('professoresCheckboxes');
            data.forEach(professor => {
                let checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = professor.id_professor;
                checkbox.name = 'professores';
                checkbox.value = professor.id_professor;

                let label = document.createElement('label');
                label.htmlFor = professor.id_professor;
                label.appendChild(document.createTextNode(professor.nome_professor ));

                let br = document.createElement('br');

                professoresCheckboxes.appendChild(checkbox);
                professoresCheckboxes.appendChild(label);
                professoresCheckboxes.appendChild(br);
            });
        });
}

function carregarDisciplinas() {
    fetch('http://localhost:8080/disciplina')
        .then(response => response.json())
        .then(data => {
            const disciplinasCheckboxes = document.getElementById('disciplinasCheckboxes');
            data.forEach(disciplina => {
                let checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = disciplina.idDisciplina;
                checkbox.name = 'disciplinas';
                checkbox.value = disciplina.idDisciplina;

                let label = document.createElement('label');
                label.htmlFor = disciplina.idDisciplina;
                label.appendChild(document.createTextNode(disciplina.nomeDisciplina));

                let br = document.createElement('br');

                disciplinasCheckboxes.appendChild(checkbox);
                disciplinasCheckboxes.appendChild(label);
                disciplinasCheckboxes.appendChild(br);
            });
        });
}
function registrarTurma() {
    const nomeTurma = document.getElementById('nome_turma').value;
    const alunosSelecionados = Array.from(document.querySelectorAll('input[name="alunos"]:checked')).map(el => ({id_aluno: parseInt(el.value)}));
    const professoresSelecionados = Array.from(document.querySelectorAll('input[name="professores"]:checked')).map(el => ({id_professor: parseInt(el.value)}));
    const disciplinasSelecionadas = Array.from(document.querySelectorAll('input[name="disciplinas"]:checked')).map(el => ({idDisciplina: parseInt(el.value)}));

    const turmaData = {
        nome_turma: nomeTurma,
        alunos: alunosSelecionados,
        professores: professoresSelecionados,
        disciplinas: disciplinasSelecionadas
    };

    fetch('http://localhost:8080/turma', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(turmaData)
    })
    .then(response => response.json())
    .then(data => alert('Turma registrada com sucesso!'))
    .catch(error => alert('Erro ao registrar turma'));
}
