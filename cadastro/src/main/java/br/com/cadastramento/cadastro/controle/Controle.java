package br.com.cadastramento.cadastro.controle;

import br.com.cadastramento.cadastro.modelo.Pessoa;
import br.com.cadastramento.cadastro.repositorio.Repositorio;
import br.com.cadastramento.cadastro.servico.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controle {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Servico servico;


    //CRIANDO ROTA DE CADASTROS
    @PostMapping("/cadastro")
    private ResponseEntity<?> cadastro(@RequestBody Pessoa pessoa) {
        return servico.cadastrar(pessoa);
    }

    //CRIANDO ROTA DE EDITAR CADASTRO
    @PutMapping("/editCad")
    private ResponseEntity<?> editar(@RequestBody Pessoa obj) {
        return servico.editar(obj);
    }

    //CRIANDO ROTA PARA DELETAR CADASTRO
    @DeleteMapping("/deleteCad/{codigo}")
    private  ResponseEntity<?> deletar(@PathVariable int codigo) {
       return servico.deletar(codigo);
    }
    //CRIANDO ROTA PARA CONSULTA
    @GetMapping("cadastros/{nome}")
    public ResponseEntity<?> selecionarPorNome(@PathVariable String nome){
        return servico.selecionarPorNome(nome);
    }


    //CRIANDO PRIMEIRA ROTA
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Olá seja bem-vindo! :3";
    }

    //RETORNA A LISTA DE CADASTROS
    @GetMapping("/cadastros")
    public List<Pessoa> cadastros() {
        return acao.findAll();
    }

    //ResponseEntity

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
