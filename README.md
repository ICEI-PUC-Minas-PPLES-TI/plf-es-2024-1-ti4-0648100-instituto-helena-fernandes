# Instituto Helena Fernandes

Este trabalho apresenta o desenvolvimento de um projeto de software para o Instituto Helena Fernandes, visando aprimorar os processos internos da instituição. O objetivo  ́e criar um sistema abrangente que proporcione uma experiência mais eficiente e integrada para alunos, professores e equipe administrativa. 

## Alunos integrantes da equipe

* Arthur Capanema Bretas
* Gabriel Vítor de Oliveira Morais
* Igor Miranda Santos
* Júlia Borges Araújo Silva
* Letícia Rodrigues Blom de Paula
  
## Professores responsáveis

* Soraia Lúcia da Silva
* Lucila Ishitani

## Instruções de utilização

## Passo a Passo

1. Clone este repositório do GitHub e baixe a base de dados de nome `mysql-ihc` localizada  na pasta `Codigo/Banco de Dados`. Instale-o no MySQL Workbench.
  
2. Dentro do repositório navegue até a pasta `Codigo > backend> src > main> java > resources` e abra o arquivo de configuração. Altere a senha do banco de dados no campo `spring.datasource.password` para a sua senha do banco de dados.

3. Navegue até a pasta `Codigo > backend > src > main > java` e execute o arquivo `DemoApplication.java.` Isso pode ser feito com um IDE como o VsCode, IntelliJ IDEA ou Eclipse. Certifique-se de que o banco de dados está rodando antes de iniciar a aplicação.

4. Agora, você pode iniciar a interface frontend. Navegue até a pasta `Codigo > frontend > views` e clique com o botão direito sobre o arquivo desejado. Selecione a opção `Open with Live Server`. Isso deve ser feito para cada funcionalidade específica conforme explicado abaixo:

   - Login do Aluno: Para acessar a funcionalidade de login do aluno, abra o arquivo `LoginAluno.html` com o Live Server.
   
   - Login do Professor: Para acessar a funcionalidade de login do professor, abra o arquivo `LoginProfessor.html` com o Live Server.
   
   - Login do Administrador: Para acessar a funcionalidade de login do administrador, abra o arquivo `LoginAdministrador.html` com o Live Server.

       Utilize as seguintes credenciais para funções de administrador:
        - Email: `admin`
        - Senha: `123`
