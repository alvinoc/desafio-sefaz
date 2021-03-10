# Desafio Sefaz

### Funcionalidades:

1. Criar um usuário
2. Logar no sistema
3. Lista de usuários
4. Excluir um usuário e seus fones
5. Atualizar um usuário
6. Criar, excluir e atualizar fones dos usuários

*Obs:*
1. usuário não vê o próprio user na lista de usuários para não excluir ele mesmo.
2. Não é possível excluir o email, visto que é o identificador único do usuário
3. Só é possível excluir um telefone caso o usuário possua mais de um
4. Não é possível editar o telefone


### Especificações do projeto:
- [x] Java EE
- [x] Aplicação em camadas
- [x] Uso de banco de dados relacional/sql (foi usado o data source H2)
- [x] Maven
- [x] Persistência utilizando JDBC ou JPA no mínimo java 8 (foi usado JPA)
- [x] Utilizar na interface JSF/Primefaces ou JSP com jQuery e Ajax (Foi usado JSF/Primefaces)
- [ ] Disponibilizar a aplicação em serviço de hospedagem online (Heroku, AWS, Digital Ocean, etc).
- [x] Disponibilizar o código em repositório Git online (foi usado o github)
- [x] Uso de EJBs para controle transacional.
- [x] Uso de servidor de aplicação Wildfly ou OpenLiberty (foi usado o wildfly 20.0.1.Final)
- [ ] Testes unitários com jUnit
