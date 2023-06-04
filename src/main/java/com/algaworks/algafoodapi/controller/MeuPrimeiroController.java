package com.algaworks.algafoodapi.controller;

import com.algaworks.algafoodapi.di.model.Cliente;
import com.algaworks.algafoodapi.di.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Scanner;

@Controller
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("Controller ativção: "+ativacaoClienteService);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Cliente joao = new Cliente("Joao", "djasdasd@htomailcom", "32323221");
        Scanner in = new Scanner(System.in);
        System.out.println("Voce deseja Ativar ou desativar um cliente?");
        int escolha = in.nextInt();
        if (escolha == 1) {
            ativacaoClienteService.ativar(joao);
        } else if (escolha == 2) {
            ativacaoClienteService.desativar(joao);
        }

        return "Olá mundo82";
    }
}