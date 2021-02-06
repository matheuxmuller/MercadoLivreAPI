# Testes com Spring Boot (Mercado Livre API)

### endpoint usuarios/cadastrar (usuarios/logar também)

```
{
    "usuario": "labaxuria@suricantas.com",
    "senha": "ramanaias"
}
```

### endpoint categoria

``` 
{
    "nome": "Tecnologia"
}
```

### endpoint categoria (com filha)

``` 
{
    "nome": "Celulares",
    "id": 1
}
```

### endpoint produto

``` 
{
    "quantidade": 2,
    "descricao": "Iphone XR",
    "categoria": "Celulares",
    "caracteristicasRequest": {
        "nome": "Apple",
        "valor": 2000.00,
        "descricao": "Do caralho"
    },
    "idUsuario": 1
}
```
