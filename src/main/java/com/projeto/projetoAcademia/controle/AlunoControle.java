package com.projeto.projetoAcademia.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.projetoAcademia.modelos.Aluno;
import com.projeto.projetoAcademia.repositorios.AlunoRepositorio;

@Controller
public class AlunoControle {
	
	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	@GetMapping("/cadastroAluno")
	public ModelAndView cadastrar(Aluno aluno) {
		
		ModelAndView mv = new ModelAndView("projetoAcademia/alunos/cadastro");
		mv.addObject("aluno", aluno);
		//mv.addObject("listaCidades", cidadeRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/salvarAluno")
	public ModelAndView salvar(Aluno aluno, BindingResult result) {
		if (result.hasErrors()) {
			return cadastrar(aluno);
		}
		alunoRepositorio.saveAndFlush(aluno);
		
		return cadastrar(new Aluno());
	}
	
	@GetMapping("/editarAluno/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Aluno> aluno = alunoRepositorio.findById(id); 
		
		return cadastrar(aluno.get());
	}
	
	@GetMapping("/listarAluno")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("projetoAcademia/alunos/lista");
		
		mv.addObject("listaAluno", alunoRepositorio.findAll());
		return mv;
		
	}
	
	@GetMapping("/removerAluno/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) { 
		Optional<Aluno> aluno = alunoRepositorio.findById(id);
		
		alunoRepositorio.delete(aluno.get());

		return listar();
	}
}
