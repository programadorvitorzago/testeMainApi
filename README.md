# mainAPI
API com recursos de verificação.

Para implementar este método  de checagem de senha, a primeira análise foi se isto seria um recurso com uso intenso. Deduzi que não. Em tese, você só verifica se uma  senha atende aos requisitos de segurança no momento do cadastro ou alteração. Sendo assim, resolvi utilizar uma abordagem mais elegante para a solução. Esta abordagem permite a parametrização dos critérios de segurança de senha, possibilitando sua configuração independe do código. Essa abordagem também facilita caso haja a necessidade de verificar o porquê que determinada entrada não foi validada.

Com relação à questão de segurança, sabemos que não é correto deixar dados sensíveis na _QueryString_, porém, no intuito de facilitar o teste, os parâmetrospodem ser  passados através da URL de requisição. As requisições devem ser do tipo POST.

## Execução
Para executar a API utilizar o comando Docker para carregar a imagem do DockerHub.
```
docker pull programadorvitorzago/main-api:latest
```

Utilize o comando para iniciar a execução configurando a porta desejada. No exemplo a porta no host está como 8081
```
docker run -d -p 8081:8080 --name main-api-container programadorvitorzago/main-api
```

## Para utilizar a API.
Existem os seguintes métodos (path):
 - **/mainAPI/IsAlive**
   - Retorna "OK" para qualquer requisiçao. Útil para verificar se a API está on-line.
 - **/mainAPI/validacao?password=X&whyNot=Y**
   - Parâmetro 'password': Obrigatório: senha a ser testada.
   - Parâmetro 'whyNot': Define se é retornado um texto com o porquê de não ter sido validado.
    
Exemplos:
Verificar se a API está on-line:
```
curl -X POST "http://localhost:8081/mainAPI/IsAlive"
```
   - Retorna "OK" para qualquer chamada.

Para testar a senha "1234567890!aA";
```
curl -X POST "http://localhost:8081/mainAPI/validacao?password=1234567890!aA"
```

Para testar a senha e explicar porque não foi aprovada:
```
curl -X POST "http://localhost:8081/mainAPI/validacao?password=1234567890!ABC&whyNot=1"
```
