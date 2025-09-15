package com.projeto.projetoAcademia.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class principalControle {
	
	@GetMapping("/projetoAcademia")
	public String acessarPrincipal() {
		return "projetoAcademia/home";
	}

}
