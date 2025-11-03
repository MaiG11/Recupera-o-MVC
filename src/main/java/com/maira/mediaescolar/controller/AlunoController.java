package com.maira.mediaescolar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maira.mediaescolar.model.Aluno;

import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AlunoController {

    // Lista em memória para armazenar alunos
    private List<Aluno> alunos = new ArrayList<>();
    private long proximoId = 1; // para simular IDs automáticos

    // Método para listar alunos com paginação simples
    @GetMapping("/")
    public String listarAlunos(Model model, @RequestParam(defaultValue = "0") int page) {
        int tamanhoPagina = 5;
        int inicio = page * tamanhoPagina;
        int fim = Math.min(inicio + tamanhoPagina, alunos.size());
        List<Aluno> paginaAlunos = alunos.subList(inicio, fim);

        model.addAttribute("paginaAlunos", paginaAlunos);
        model.addAttribute("paginaAtual", page);
        model.addAttribute("novoAluno", new Aluno());
        return "index";
    }

    // Método para adicionar novo aluno com validação simples
    @PostMapping("/adicionar")
    public String adicionarAluno(@ModelAttribute Aluno aluno, Model model) {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            model.addAttribute("erro", "O nome do aluno não pode estar vazio.");
            return listarAlunos(model, 0);
        }

        aluno.setId(proximoId++);
        alunos.add(aluno);
        return "redirect:/";
    }

    // Método para excluir um aluno pelo ID
    @GetMapping("/excluir")
    public String excluirAluno(@RequestParam Long id) {
        alunos.removeIf(a -> a.getId().equals(id));
        return "redirect:/";
    }

    // Método para mostrar formulário de edição
    @GetMapping("/editar")
    public String mostrarEditar(@RequestParam Long id, Model model) {
        Aluno aluno = alunos.stream()
                            .filter(a -> a.getId().equals(id))
                            .findFirst()
                            .orElse(null);
        if (aluno == null) {
            return "redirect:/";
        }
        model.addAttribute("alunoParaEditar", aluno);
        return "editar";
    }

    // Método para salvar edição do aluno
    @PostMapping("/editar")
    public String salvarEdicao(@ModelAttribute Aluno aluno, Model model) {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            model.addAttribute("erro", "O nome do aluno não pode estar vazio.");
            model.addAttribute("alunoParaEditar", aluno);
            return "editar";
        }

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(aluno.getId())) {
                alunos.set(i, aluno); // atualiza aluno na lista
                break;
            }
        }
        return "redirect:/";
    }
}
