Desafio — Sistema de Biblioteca
Uma biblioteca precisa de um sistema para controlar seus livros e usuários.

O sistema deve permitir que um usuário alugue um livro da biblioteca.

Regras do sistema
Um usuário pode alugar um livro.
Um livro só pode estar alugado para um usuário por vez.
Um usuário pode ter mais de um livro alugado.
Um livro que já estiver alugado não pode ser alugado novamente.
Quando um usuário devolver um livro, o livro deverá ficar disponível novamente para outro usuário.
O sistema deve informar quando um livro não estiver disponível para aluguel.
Exemplo
Imagine que existam:

Usuário: João
Usuário: Maria
Livro: Java para Iniciantes
Livro: POO na Prática
João aluga Java para Iniciantes.

Depois, Maria tenta alugar o mesmo livro.

O sistema deve impedir o aluguel, pois o livro já está alugado para João.

Maria pode, entretanto, alugar POO na Prática.

Desafio
A partir dessa situação, desenvolva um sistema utilizando Programação Orientada a Objetos (POO).

Antes de começar a programar, identifique:

Quais são os objetos envolvidos?
Quais classes deverão existir?
Quais são os atributos de cada classe?
Quais são os métodos de cada classe?
Como os objetos Usuário e Livro se relacionam?
Onde deverá ficar a responsabilidade de alugar um livro?
Como o sistema saberá se um livro está disponível?
O que deve acontecer quando um livro for devolvido?
Requisito mínimo
O sistema deverá permitir:

Cadastrar usuários;
Cadastrar livros;
Listar usuários;
Listar livros;
Realizar aluguel;
Realizar devolução;
Consultar se um livro está disponível;
Exibir quais livros estão alugados por determinado usuário.
Regra principal
Um livro não pode estar alugado para dois usuários ao mesmo tempo.

O sistema deve garantir essa regra independentemente da sequência de operações realizadas pelo usuário.

Desafio extra
Implemente também:

Limite de livros que cada usuário pode alugar;
Busca de livros pelo título;
Histórico de aluguéis;
Data de aluguel e devolução;
Multa por atraso na devolução.
