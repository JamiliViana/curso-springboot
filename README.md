# Cenário de testes

## Métodos Controllers
<br>

### **Get All Menores:**
<br>

Quando eu chamo: http://localhost:8080/promocoes/menorQue/Xn o logar do 'X' deverá ser um número equivalente ao valor que será de referência para receber os número menores que esse valor.    
<br> **SE** existir valores menores registrados, deve retornar status 200, e um body:       
~~~~Kotlin 
{
    "id": 1,
    "descricao": "descrição x",
    "local": "local x",
    "isAllInclusive": false,
    "qtdDias" 3,
    "preco": 9999,99
}   
// e mais promoções caso houver, pois é uma lista...
~~~~
**SE NÃO** existir valores menores registrados, deve retornar status 404, e um body:       
~~~~Kotlin
{
    "error": "Promocoes não localizada"
    "message": "Promocões com o valor menor que X não localizada"
}
~~~~
<br>

### **Get Id:**
<br>

Quando eu chamo: http://localhost:8080/promocoes/X no lugar do 'X' deve ser um número equivalente ao valor do Id que desejo buscar/procurar.    
<br> **SE** existir uma promoção cadastrada com esse Id, deve retornar status 200, e um body:       
~~~~Kotlin 
{
    "id": X,
    "descricao": "descrição x",
    "local": "local x",
    "isAllInclusive": false,
    "qtdDias" 9,
    "preco": 9999,99
}   
~~~~
**SE NÃO** existir valores menores registrados, deve retornar status 404, e um body:       
~~~~Kotlin
{
    "error": "Promocao nao localizada"
    "message": "Promocao X não localizada"
}
~~~~
<br>

### **Get All:**
<br>

Quando eu chamo: http://localhost:8080/promocoes/ eu recebo todas as promoções cadstradas.

<br> **SE** existir qualquer promoção, deve retornar status 200, e um body:       
~~~~Kotlin 
{
    "id": X,
    "descricao": "descrição x",
    "local": "local x",
    "isAllInclusive": false,
    "qtdDias" 9,
    "preco": 9999,99
}   
// E se possuir mais de uma, irá repetir o body...
~~~~
**SE NÃO** existir nenhuma promoção cadastrada, deve retornar status 404, e um body:       
~~~~Kotlin
{
    "error": "Promocao nao localizada"
    "message": "Não possui promoções cadastradas"
}
~~~~
<br>

### **Create:**
<br>

Quando eu chamo POST: http://localhost:8080/promocoes/ eu passo todos os parâmetros necesários para cadastrar uma nova promoção.

<br> **SE** passar todos o parâmetros corretos, deve retornar status 201, e um body:       
~~~~Kotlin 
{
    "message": "OK",
    "dataHora": "2022-12-09T13:55:44.670+00:00"
}   
~~~~
**SE NÃO** passar algum parâmetro correto ou deixar de passar algum paramêtro, deve retornar status 406, e um body:       
~~~~Kotlin
{
    "error": "Promocao nao cadastrada"
    "message": "Não foi possivel concluir o cadastro por falta de dados, ou dados incorretos"
}
~~~~
<br>

### **Delete:**
<br>

Quando eu chamo DELETE: http://localhost:8080/promocoes/X o 'X' é o valor referente à promoção que desejo excluir.

<br> **SE** a promoção existir, deve retornar status 202, e um body:       
~~~~Kotlin 
{
    "message": "OK! Promoção X excluida com sucesso!",
    "dataHora": "2022-12-09T13:55:44.670+00:00"
}   
~~~~
**SE NÃO** existir está promoção, deve retornar status 404, e um body:       
~~~~Kotlin
{
    "error": "Promocao inexistente"
    "message": "A promoção X não existe"
}
~~~~
<br>

### **Update:**
<br>

Quando eu chamo PUT: http://localhost:8080/promocoes/X o 'X' é o valor referente à promoção que desejo alterar algum dado.

<br> **SE** os dados estiverem corretos, deve retornar status 202, e um body:       
~~~~Kotlin 
{
    "message": "OK! Promoção X alterada com sucesso!",
    "dataHora": "2022-12-09T13:55:44.670+00:00"
    "id": X,
    "descricao": "descrição x",
    "local": "local x",
    "isAllInclusive": false,
    "qtdDias" 9,
    "preco": 9999,99

}   
~~~~
**SE NÃO** existir está promoção, deve retornar status 404, e um body:       
~~~~Kotlin
{
    "error": "Promocao inexistente"
    "message": "Promoção não pode ser alterada, pois não foi possivel encontra-la"
}
~~~~
<br>

### **Count:**
<br>

Quando eu chamo GET: http://localhost:8080/promocoes/count irei receber a contagem de promoções cadastradas no total.

<br> **SE** possuir promoções cadastrads, deve retornar status 200, e um body:       
~~~~Kotlin 
{
    "count": X //O 'X' é o total da contagem
}   
~~~~
**SE NÃO** existir promoções cadastradas, deve retornar status 404, e um body:       
~~~~Kotlin
{
    "error": "Tabela Vazia"
    "message": "Não possui nenhum dado na tabela"
}
~~~~
<br>

### **Ordenados:**
<br>

Quando eu chamo GET: http://localhost:8080/promocoes/ordenados irei receber as promoções cadastradas em ordem alfabética por local.

<br> **SE** possuir promoções cadastrads, deve retornar status 200, e um body:       
~~~~Kotlin 
{
    "id": 5,
    "descricao": "Viagem de Natal e Ano Novo",
    "local": "Campos de Jordão",
    "isAllInclusive": false,
    "qtdDias": 6,
    "preco": 550.0
}   
// E vai se repetir de acordo com a quantidade de promoções, e serão organizadas de acordo com a ordem alfabetica do local.
~~~~
**SE NÃO** existir promoções cadastradas, deve retornar status 404, e um body:       
~~~~Kotlin
{
    "error": "Tabela Vazia"
    "message": "Não foi possivel ordenar, pois a lista está vazia"
}
~~~~
<br>

