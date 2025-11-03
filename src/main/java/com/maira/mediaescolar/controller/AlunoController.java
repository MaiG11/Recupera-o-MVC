package com.maira.mediaescolar.controller; // Aqui estamos dizendo onde está guardada essa classe no projeto

// Esses import servem para usar funcionalidades do Spring e trabalhar com listas
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maira.mediaescolar.model.Aluno; // Aqui importamos a classe Aluno que criamos

import org.springframework.ui.Model; // Serve para enviar informações para as páginas HTML
import java.util.ArrayList; // Serve para criar listas que podem mudar de tamanho
import java.util.List; // Tipo de lista

@Controller // Diz que essa classe vai “controlar” o que acontece quando alguém entra nas páginas
public class AlunoController {

    // Aqui criamos uma lista para guardar os alunos na memória (ainda não temos banco)
    private List<Aluno> alunos = new ArrayList<>();
    private long proximoId = 1; // Aqui guardamos o próximo número que vamos usar como ID do aluno

    // Esse método vai mostrar a lista de alunos na página inicial
    @GetMapping("/") // Diz que quando abrir a página principal "/" esse método será usado
    public String listarAlunos(Model model, @RequestParam(defaultValue = "0") int page) {
        int tamanhoPagina = 5; // Mostra 5 alunos por página
        int inicio = page * tamanhoPagina; // Calcula de onde começar a página
        int fim = Math.min(inicio + tamanhoPagina, alunos.size()); // Calcula onde terminar a página
        List<Aluno> paginaAlunos = alunos.subList(inicio, fim); // Pega só os alunos dessa página

        model.addAttribute("paginaAlunos", paginaAlunos); // Envia essa lista para a página
        model.addAttribute("paginaAtual", page); // Envia o número da página atual
        model.addAttribute("novoAluno", new Aluno()); // Cria um aluno vazio para o formulário
        return "index"; // Diz para abrir o arquivo index.html
    }

    // Esse método serve para adicionar um aluno novo
    @PostMapping("/adicionar") // Quando apertar o botão "Adicionar" do formulário, este método será chamado
    public String adicionarAluno(@ModelAttribute Aluno aluno, Model model) {
        // Aqui a gente verifica se o nome está vazio
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            model.addAttribute("erro", "O nome do aluno não pode estar vazio."); // Mostra a mensagem de erro
            return listarAlunos(model, 0); // Volta para a página inicial mostrando o erro
        }

        aluno.setId(proximoId++); // Dá um número de ID para o aluno
        alunos.add(aluno); // Coloca o aluno na lista
        return "redirect:/"; // Volta para a página inicial para ver a lista atualizada
    }

    // Esse método serve para apagar um aluno
    @GetMapping("/excluir") // Quando clicar no link de excluir, este método será chamado
    public String excluirAluno(@RequestParam Long id) {
        alunos.removeIf(a -> a.getId().equals(id)); // Procura o aluno pelo ID e remove da lista
        return "redirect:/"; // Volta para a página inicial
    }

    // Esse método mostra o formulário para editar os dados de um aluno
    @GetMapping("/editar") // Quando clicar no link de editar, este método será chamado
    public String mostrarEditar(@RequestParam Long id, Model model) {
        Aluno aluno = alunos.stream() // Procura na lista
                            .filter(a -> a.getId().equals(id)) // Acha o aluno com o ID igual
                            .findFirst() // Pega só o primeiro que encontrar
                            .orElse(null); // Se não encontrar, retorna null
        if (aluno == null) { // Se não achou aluno
            return "redirect:/"; // Volta para a página inicial
        }
        model.addAttribute("alunoParaEditar", aluno); // Envia o aluno encontrado para o formulário
        return "editar"; // Abre o arquivo editar.html
    }

    // Esse método salva as alterações feitas no aluno
    @PostMapping("/editar") // Quando apertar o botão "Salvar" no formulário de edição, este método é chamado
    public String salvarEdicao(@ModelAttribute Aluno aluno, Model model) {
        // Verifica se o nome está vazio
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            model.addAttribute("erro", "O nome do aluno não pode estar vazio."); // Mostra o erro
            model.addAttribute("alunoParaEditar", aluno); // Mantém os dados que estavam no formulário
            return "editar"; // Volta para a página de edição
        }

        // Procura o aluno na lista e atualiza os dados
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId().equals(aluno.getId())) {
                alunos.set(i, aluno); // Troca o aluno antigo pelo novo
                break; // Para de procurar
            }
        }
        return "redirect:/"; // Volta para a página inicial
    }
}
