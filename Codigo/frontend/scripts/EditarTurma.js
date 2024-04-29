document.addEventListener('DOMContentLoaded', function() {
    const turmaId = new URLSearchParams(window.location.search).get('id');
    if (turmaId) {
        carregarTurma(turmaId);
    }

    const form = document.getElementById('turmaForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        editarTurma(turmaId);
    });
});

function carregarTurma(turmaId) {
    fetch(`http://localhost:8080/turma/${turmaId}`)
        .then(response => response.json())
        .then(turma => {
            document.getElementById('nome_turma').value = turma.nome_turma;

            carregarAlunos(turma.alunos.map(a => a.id_aluno));
            carregarProfessores(turma.professores.map(p => p.id_professor));
            carregarDisciplinas(turma.disciplinas.map(d => d.idDisciplina));
        })
        .catch(error => {
            console.error('Erro ao carregar a turma:', error);
            alert('Erro ao carregar a turma. Por favor, tente novamente.');
        });
}

function carregarAlunos(alunosSelecionados = []) {
    fetch('http://localhost:8080/aluno?status_aluno=1')
        .then(response => response.json())
        .then(data => {
            const alunosCheckboxes = document.getElementById('alunosCheckboxes');
            alunosCheckboxes.innerHTML = ''; // Limpar antes de adicionar

            data.forEach(aluno => {
                let checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = aluno.id_aluno;
                checkbox.name = 'alunos';
                checkbox.value = aluno.id_aluno;

                // Marcar checkbox se o aluno já estiver na turma
                if (alunosSelecionados.includes(aluno.id_aluno)) {
                    checkbox.checked = true;
                }

                let label = document.createElement('label');
                label.htmlFor = aluno.id_aluno;
                label.appendChild(document.createTextNode(`${aluno.nome_aluno} (${aluno.emailAluno})`));

                alunosCheckboxes.appendChild(checkbox);
                alunosCheckboxes.appendChild(label);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar alunos:', error);
            alert('Erro ao carregar os alunos. Por favor, tente novamente.');
        });
}

function carregarProfessores(professoresSelecionados = []) {
    fetch('http://localhost:8080/professor')
        .then(response => response.json())
        .then(data => {
            const professoresCheckboxes = document.getElementById('professoresCheckboxes');
            professoresCheckboxes.innerHTML = ''; // Limpar antes de adicionar

            data.forEach(professor => {
                let checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = professor.id_professor;
                checkbox.name = 'professores';
                checkbox.value = professor.id_professor;

                // Marcar checkbox se o professor já estiver na turma
                if (professoresSelecionados.includes(professor.id_professor)) {
                    checkbox.checked = true;
                }

                let label = document.createElement('label');
                label.htmlFor = professor.id_professor;
                label.appendChild(document.createTextNode(professor.nome_professor));

                professoresCheckboxes.appendChild(checkbox);
                professoresCheckboxes.appendChild(label);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar professores:', error);
            alert('Erro ao carregar os professores. Por favor, tente novamente.');
        });
}

function carregarDisciplinas(disciplinasSelecionadas = []) {
    fetch('http://localhost:8080/disciplina')
        .then(response => response.json())
        .then(data => {
            const disciplinasCheckboxes = document.getElementById('disciplinasCheckboxes');
            disciplinasCheckboxes.innerHTML = ''; // Limpar antes de adicionar

            data.forEach(disciplina => {
                let checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.id = disciplina.idDisciplina;
                checkbox.name = 'disciplinas';
                checkbox.value = disciplina.idDisciplina;

                // Marcar checkbox se a disciplina já estiver na turma
                if (disciplinasSelecionadas.includes(disciplina.idDisciplina)) {
                    checkbox.checked = true;
                }

                let label = document.createElement('label');
                label.htmlFor = disciplina.idDisciplina;
                label.appendChild(document.createTextNode(disciplina.nomeDisciplina));

                disciplinasCheckboxes.appendChild(checkbox);
                disciplinasCheckboxes.appendChild(label);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar disciplinas:', error);
            alert('Erro ao carregar as disciplinas. Por favor, tente novamente.');
        });
}

function editarTurma(turmaId) {
    const nomeTurma = document.getElementById('nome_turma').value;
    const alunosSelecionados = Array.from(document.querySelectorAll('input[name="alunos"]:checked')).map(el => ({ id_aluno: parseInt(el.value) }));
    const professoresSelecionados = Array.from(document.querySelectorAll('input[name="professores"]:checked')).map(el => ({ id_professor: parseInt(el.value) }));
    const disciplinasSelecionadas = Array.from (document.querySelectorAll('input[name="disciplinas"]:checked')).map(el => ({ idDisciplina: parseInt(el.value) }));

    const turmaData = {
        nome_turma: nomeTurma,
        alunos: alunosSelecionados,
        professores: professoresSelecionados,
        disciplinas: disciplinasSelecionadas,
    };

    fetch(`http://localhost:8080/turma/${turmaId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(turmaData)
    })
    .then(response => response.json())
    .then(data => alert('Turma editada com sucesso!'))
    .catch(error => {
        console.error('Erro ao editar turma:', error);
        alert('Erro ao editar a turma. Por favor, tente novamente.');
    });
}
