package com.maira.mediaescolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maira.mediaescolar.model.Aluno;
import com.maira.mediaescolar.repository.AlunoRepository;

import org.springframework.ui.Model;

@Controller
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    // Método para listar alunos com paginação
    @GetMapping("/")
    public String listarAlunos(Model model, @RequestParam(defaultValue = "0") int page) {
        int tamanhoPagina = 5; // 5 alunos por página
        Pageable configuracaoPagina = PageRequest.of(page, tamanhoPagina);
        Page<Aluno> paginaAlunos = alunoRepository.findAll(configuracaoPagina);

        model.addAttribute("paginaAlunos", paginaAlunos);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("novoAluno", new Aluno()); // Para o formulário de adicionar aluno
        return "index";
    }

    // Método para adicionar novo aluno
    @PostMapping("/adicionar")
    public String adicionarAluno(@ModelAttribute Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/"; // volta para a lista
    }
}
