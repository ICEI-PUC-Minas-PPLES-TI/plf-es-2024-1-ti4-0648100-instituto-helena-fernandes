document.addEventListener("DOMContentLoaded", async function () {
  const idTurma = getParameterByName("id_turma");
  const idDisciplina = getParameterByName("id_disciplina");

  if (!idTurma || !idDisciplina) {
    alert("IDs da turma ou disciplina não encontrados.");
    return;
  }

  // Função para buscar os detalhes da turma
  async function obterDetalhesTurma(idTurma) {
    try {
      const response = await fetch(`http://localhost:8080/turma/${idTurma}`);
      if (!response.ok) {
        throw new Error("Falha ao buscar detalhes da turma");
      }
      const turma = await response.json();
      return turma;
    } catch (error) {
      console.error("Erro ao carregar detalhes da turma:", error);
      return null;
    }
  }

  // Atualiza o título da turma
  async function atualizarTituloTurma() {
    const turma = await obterDetalhesTurma(idTurma);
    if (turma && turma.nome_turma) {
      const titulo = document.getElementById("titulo-turma");
      titulo.textContent = turma.nome_turma;
    }
  }

  await atualizarTituloTurma();

  // Carrega as notas dos alunos
  const listaAlunos = document.getElementById("lista-alunos");
  try {
    const response = await fetch(
      `http://localhost:8080/turma/${idTurma}/disciplinas/${idDisciplina}/notas`
    );
    if (!response.ok) {
      throw new Error("Falha ao buscar notas dos alunos");
    }
    const notas = await response.json();
    console.log("Notas dos Alunos:", notas);

    for (const nota of notas) {
      const row = document.createElement("tr");
      row.innerHTML = `
                <td>${nota.nomeAluno}</td>
                <td>${nota.notaProva1}</td>
                <td>${nota.notaProva2}</td>
                <td>${nota.notaProva3}</td>
                <td>${nota.notaTrabalho1}</td>
                <td>${nota.notaTrabalho2}</td>
                <td>${nota.notaTrabalho3}</td>
                        <td>${
                          nota.notaProva1 +
                          nota.notaProva2 +
                          nota.notaProva3 +
                          nota.notaTrabalho1 +
                          nota.notaTrabalho2 +
                          nota.notaTrabalho3
                        }</td>
                <td><button type="button" onclick="abrirDetalhes(${
                  nota.idAluno
                }, ${idTurma}, ${
        nota.idNota
      }, ${idDisciplina})">Detalhes</button></td>
            `;
      listaAlunos.appendChild(row);
    }
  } catch (error) {
    console.error("Erro ao carregar notas dos alunos:", error);
    alert(
      "Não foi possível carregar as notas dos alunos. Por favor, tente novamente."
    );
  }
});

function getParameterByName(name, url = window.location.href) {
  name = name.replace(/[\[\]]/g, "\\$&");
  const regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
    results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return "";
  return decodeURIComponent(results[2].replace(/\+/g, " "));
}

async function carregarNotasDoAluno(idAluno, idTurma) {
  const idDisciplina = getParameterByName("id_disciplina");
  try {
    console.log(
      `Carregando notas do aluno: aluno: ${idAluno}, turma: ${idTurma}`
    );
    const response = await fetch(
      `http://localhost:8080/turma/${idTurma}/disciplinas/${idDisciplina}/notas`
    );
    if (!response.ok) {
      throw new Error("Falha ao buscar notas do aluno");
    }
    const notas = await response.json();
    return notas.length > 0 ? notas[0] : {};
  } catch (error) {
    console.error("Erro ao carregar notas do aluno:", error);
    return {};
  }
}

async function abrirDetalhes(idAluno, idTurma, idNota, idDisciplina) {
  const cardExistente = document.getElementById("detalhes-card");
  if (cardExistente) {
    cardExistente.remove();
  }
  console.log("idNota " + idNota);

  const card = document.createElement("div");
  card.id = "detalhes-card";
  card.className = "detalhes-card";

  try {
    const response = await fetch(
      `http://localhost:8080/turma/${idTurma}/disciplinas/${idDisciplina}/alunos/${idAluno}/notas`
    );

    if (!response.ok) {
      throw new Error("Falha ao buscar notas dos alunos");
    }

    const notas = await response.json();
    console.log("Notas dos Alunos:", notas);

    const row = document.createElement("tr");
    card.innerHTML = `
            <h2>Editar Notas do Aluno</h2>
            <form id="form-detalhes">
                <input type="hidden" name="id_aluno" value="${idAluno}">
                <input type="hidden" name="id_turma" value="${idTurma}">
                <input type="hidden" name="id_nota" value="${idNota}">
                <input type="hidden" name="id_disciplina" value="${idDisciplina}">
                <label>P1: <input type="number" name="prova1" value="${
                  notas.notaProva1 ?? 0
                }"></label>
                <label>P2: <input type="number" name="prova2" value="${
                  notas.notaProva2 ?? 0
                }"></label>
                <label>P3: <input type="number" name="prova3" value="${
                  notas.notaProva3 ?? 0
                }"></label>
                <label>T1: <input type="number" name="trabalho1" value="${
                  notas.notaTrabalho1 ?? 0
                }"></label>
                <label>T2: <input type="number" name="trabalho2" value="${
                  notas.notaTrabalho2 ?? 0
                }"></label>
                <label>T3: <input type="number" name="trabalho3" value="${
                  notas.notaTrabalho3 ?? 0
                }"></label>
                <button type="button" onclick="salvarDetalhes(${idAluno}, ${idTurma}, ${idNota}, ${idDisciplina})">Salvar</button>
                <button type="button" onclick="fecharDetalhes()">Fechar</button>
            </form>
        `;
  } catch (error) {
    console.error("Erro ao carregar notas para detalhes:", error);
    card.innerHTML = "<p>Erro ao carregar notas do aluno. Tente novamente.</p>";
  }

  document.body.appendChild(card);
}

async function carregarNotasParaDetalhes(idAluno, idTurma) {
  try {
    const notas = await carregarNotasDoAluno(idAluno, idTurma);
    const form = document.getElementById("form-detalhes");
    form.elements["prova1"].value = notas.prova1 ?? 0;
    form.elements["prova2"].value = notas.prova2 ?? 0;
    form.elements["prova3"].value = notas.prova3 ?? 0;
    form.elements["trabalho1"].value = notas.trabalho1 ?? 0;
    form.elements["trabalho2"].value = notas.trabalho2 ?? 0;
    form.elements["trabalho3"].value = notas.trabalho3 ?? 0;
  } catch (error) {
    console.error("Erro ao carregar notas para detalhes:", error);
  }
}

async function salvarDetalhes(idAluno, idTurma, idNota, idDisciplina) {
  const form = document.getElementById("form-detalhes");
  const idProfessor = getParameterByName("id_professor");
  const notas = {
    idAluno: idAluno,
    notaProva1: parseFloat(form.elements["prova1"].value) || 0,
    notaProva2: parseFloat(form.elements["prova2"].value) || 0,
    notaProva3: parseFloat(form.elements["prova3"].value) || 0,
    notaTrabalho1: parseFloat(form.elements["trabalho1"].value) || 0,
    notaTrabalho2: parseFloat(form.elements["trabalho2"].value) || 0,
    notaTrabalho3: parseFloat(form.elements["trabalho3"].value) || 0,
  };

  try {
    let response;

    if (idNota == null || idNota === "undefined") {
      // Cria nova nota
      response = await fetch(
        `http://localhost:8080/turma/${idTurma}/disciplina/${idDisciplina}/professor/${idProfessor}/notas`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(notas),
        }
      );
    } else {
      // Atualiza nota existente
      response = await fetch(
        `http://localhost:8080/notas/aluno/${idAluno}/turma/${idTurma}/disciplina/${idDisciplina}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(notas),
        }
      );
    }

    if (!response.ok) {
      throw new Error("Falha ao salvar detalhes");
    }
    alert("Detalhes salvos com sucesso!");
    fecharDetalhes();
    location.reload();
  } catch (error) {
    console.error("Erro ao salvar detalhes:", error);
    alert("Não foi possível salvar os detalhes. Por favor, tente novamente.");
  }
}

function fecharDetalhes() {
  const card = document.getElementById("detalhes-card");
  if (card) {
    card.remove();
  }
}
