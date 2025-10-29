package com.maira.mediaescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.maira.mediaescolar.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
