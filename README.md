# JDBC Usuario CRUD

Implementação de um CRUD de Usuários utilizando **Java** e **JDBC (MySQL)** com `PreparedStatement`.

## 🛠️ Banco de Dados
Execute este script no MySQL antes de rodar:

```sql
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100)
);
```

Requisitos:
 - Driver MySQL Connector/J
 - Java 17+
