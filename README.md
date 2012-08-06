cpf-cnpj-validator
==================

Bean Validator de CPF e CNPJ utilizando annotations.

Exemplo:

```java
public class Person() {
  @Id
  @GeneratedValue
  Long id;
  
  @CPF(required = true)
  String cpf;

  @CNPJ(required = true)
  String cnpj;
}
```