package com.maira.mediaescolar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Diz que essa classe será salva no banco de dados
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID único do aluno

    // 5 atributos obrigatórios
    private String nome;
    private int idade;
    private String turma;
    private double nota1;
    private double nota2;

    // Construtor vazio (necessário para JPA)
    public Aluno() {}

    // Construtor com todos os atributos
    public Aluno(String nome, int idade, String turma, double nota1, double nota2) {
        this.nome = nome;
        this.idade = idade;
        this.turma = turma;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    // GETTERS e SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public String getTurma() { return turma; }
    public void setTurma(String turma) { this.turma = turma; }

    public double getNota1() { return nota1; }
    public void setNota1(double nota1) { this.nota1 = nota1; }

    public double getNota2() { return nota2; }
    public void setNota2(double nota2) { this.nota2 = nota2; }

    // MÉTODO PARA CALCULAR MÉDIA (REQUISITO DE VALIDAÇÃO/CÁLCULO)
    public double calcularMedia() {
        return (nota1 + nota2) / 2;
    }
}
