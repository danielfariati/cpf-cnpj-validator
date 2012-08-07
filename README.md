cpf-cnpj-validator
==================

Bean Validator de CPF e CNPJ utilizando annotations.

Exemplo de Uso:

```java
public class Person() {
  @Id
  @GeneratedValue
  Long id;
  
  @CPF(required = true) // indica que dado é obrigatório
  String cpf;

  @CNPJ(message = "CNPJ inválido. Por favor, verifique.") // modifica mensagem padrão de erro
  String cnpj;
}
```

Maven:

```xml
<dependency>
  <groupId>com.danielfariati</groupId>
  <artifactId>cpf-cnpj-validator</artifactId>
  <version>1.0</version>
</dependency>
```