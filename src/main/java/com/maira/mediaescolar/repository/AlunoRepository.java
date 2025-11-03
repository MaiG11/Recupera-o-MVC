package com.maira.mediaescolar.repository; 
// Pacote onde a interface está localizada, organiza o código em pastas

import org.springframework.data.jpa.repository.JpaRepository; 
// Importa a interface JpaRepository do Spring, que ajuda a criar funções prontas de banco de dados

import com.maira.mediaescolar.model.Aluno; 
// Importa a classe Aluno para usar como tipo de dado no repositório

public interface AlunoRepository extends JpaRepository<Aluno, Long> { 
// Cria a interface AlunoRepository que "herda" o JpaRepository
// JpaRepository já tem funções prontas para salvar, editar, deletar, listar dados
// <Aluno, Long> significa que vai trabalhar com a classe Aluno e o ID é do tipo Long

} 
// Fecha a interface
