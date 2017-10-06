# Mala direta

Este projeto possui o postman em sua raiz, e é bem legal dar uma olhada nele para entender como funciona as chamadas das APIs.

O Projeto está utilizando docker, para roda-lo é necessario gerar uma imagem, para isso rode o seguinte comando: 
- `--Criar imagem` - ./mvnw install dockerfile:build
- `--Criar Container` - docker run -d -p 8080:8080 --name escola-maladireta escola/maladireta 
- `--Rodar projeto` - docker start escola-maladireta
- `--Acess agora` - localhost:8080

Para um maior entendimento pode-se acessar a pagina http://localhost:8080/swagger-ui.html. Você terá as operações descritas no swagger.

### Fluxo das operações

Primeiro passo: (Todas operações dessa seção são GET)
- `--Obtem as mensagens a serem enviadas(Neste caso apenas vai listar as materias dos alunos)`: http://localhost:8080/maladireta
- `--Obtem as mensagens a serem enviadas(Neste caso apenas vai listar as materias dos alunos reprovados)`: http://localhost:8080/maladireta?search="reproved"
- `--Obtem as mensagens a serem enviadas(Neste caso apenas vai listar as materias dos alunos aprovados)`: http://localhost:8080/maladireta?search="approved"

Segundo passo:(Essa operação é um POST)
- `--Envia a mensagem aos alunos(Basta passar o mesmo array recebido das chamadas anteriores)`: http://127.0.0.1:8080/maladireta
