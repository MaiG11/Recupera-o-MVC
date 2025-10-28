package com.maira.mediaescolar.model;

// Importa as anotações do JPA (para salvar no banco depois)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Diz que esta classe é uma tabela no banco de dados
public class Aluno {

    @Id // Indica o identificador único (chave primária)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    private Long id;

    private String nome;
    private double nota1;
    private double nota2;
    private double media;

    // Método que calcula a média do aluno
    public void calcularMedia() {
        this.media = (nota1 + nota2) / 2;
    }

    // Getters e Setters (permitem acessar e alterar os dados)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
}

