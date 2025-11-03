package com.maira.mediaescolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Diz que essa classe representa uma entidade do banco de dados
@Entity
public class Aluno {

    // ID único do aluno, gerado automaticamente pelo JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos do aluno
    private String nome; // nome do aluno
    private int idade;   // idade do aluno
    private String turma; // turma do aluno
    private double nota1; // primeira nota
    private double nota2; // segunda nota

    // Construtor vazio necessário para o JPA criar objetos
    public Aluno() {}

    // Construtor completo, útil se quisermos criar aluno já preenchido
    public Aluno(String nome, int idade, String turma, double nota1, double nota2) {
        this.nome = nome;
        this.idade = idade;
        this.turma = turma;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    // GETTERS e SETTERS
    // Permitem acessar e alterar os valores dos atributos

    public Long getId() {
        return id; // retorna o ID do aluno
    }

    public void setId(Long id) {
        this.id = id; // define o ID do aluno
    }

    public String getNome() {
        return nome; // retorna o nome
    }

    public void setNome(String nome) {
        this.nome = nome; // define o nome
    }

    public int getIdade() {
        return idade; // retorna a idade
    }

    public void setIdade(int idade) {
        this.idade = idade; // define a idade
    }

    public String getTurma() {
        return turma; // retorna a turma
    }

    public void setTurma(String turma) {
        this.turma = turma; // define a turma
    }

    public double getNota1() {
        return nota1; // retorna a nota1
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1; // define a nota1
    }

    public double getNota2() {
        return nota2; // retorna a nota2
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2; // define a nota2
    }

    // MÉTODO PARA CALCULAR A MÉDIA DO ALUNO
    public double calcularMedia() {
        return (nota1 + nota2) / 2; // retorna a média entre nota1 e nota2
    }
}

