# Testes com Spring Boot (Mercado Livre API)

### Endpoint /cadastrar
```
{
    "login": "teste@teste.com",
    "senha": "testando"
}
```


#### Endpoint /logar
```
{
    "login": "teste@teste.com",
    "senha": "testando"
}
```
* * *


> Partindo deste ponto, na aba "Authentication" do postman, selecionar "Bearer Token" e inserir o hash que será gerado no endpoint /authentication


* * *

### Endpoint /categorias
``` 
{
    "nome": "Casa"
}
```

### Endpoint /categorias
``` 
{
    "nome": "Cama, Mesa e Banho",
    "nomeCategoriaMae": "Casa"
}
```

### Endpoint /produtos
``` 
{
    "nome": "Conjunto Cama Box",
    "valor": 79.90,
    "quantidade": 100,
    "caracteristicas": [
        {
            "nome": "Fronhas",
            "descricao": "para travesseiros grandes"
        },
        {
            "nome": "Colcha",
            "descricao": "para dimensões de cama box"
        },
        {
            "nome": "Lençol",
            "descricao": "300 fios persas"
        }
    ],
    "descricao": "Baratíssimo",
    "nomeCategoria": "Cama, Mesa e Banho"
}
```

### Endpoint /produtos/{id}/imagens
``` 
No Postman selecionar o método POST: 

1. Body > form-data
2. No campo "KEY", mudar de "Text" para "File" & passar o nome da variável, no meu caso é "imagens"
3. No campo "VALUE" clicar em "Select Files" e selecionar a sua imagem no computador
4. Dar "Send" na requisição pra retornar http status 200
```
* * *

> OBS: No momento de inserção das imagens, caso o token autenticado seja diferente do token do usuário que registrou o produto, irá retornar um  http status 403

* * *

### Endpoint /produtos/{id}/opiniao
``` 
{
    "notas": 4,
    "titulo": "Produto bom",
    "descricao": "Itens de ótima qualidade",
    "nomeProduto": "Conjunto Cama Box"
}
```


### Endpoint /produtos/{id}/pergunta
``` 
{
    "titulo": "Este produto tem boa durabilidade?"
    "nomeProduto": "Conjunto Cama Box"
}
```

* * *

> OBS: No momento de registro de uma pergunta de um usuário, o vendedor-dono do produto recebe um "e-mail falso" via console, com um fake-link pra pergunta, o e-mail do remetente e do serviço de perguntas do Mercado Livre.

* * *

