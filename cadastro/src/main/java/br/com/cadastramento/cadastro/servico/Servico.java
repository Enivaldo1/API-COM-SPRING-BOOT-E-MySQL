package br.com.cadastramento.cadastro.servico;

import br.com.cadastramento.cadastro.modelo.Mensagem;
import br.com.cadastramento.cadastro.modelo.Pessoa;
import br.com.cadastramento.cadastro.repositorio.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Servico {

    @Autowired
    private Repositorio acao;

    @Autowired
    private Mensagem mensagem;



    //Para fazer CADASTROS
    public ResponseEntity<?> cadastrar(Pessoa obj){
        if (obj.getNome().equals("")){
            mensagem.setMensagem("É necessário um nome para continuar o cadastro");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("A idade informada não é válida!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getEmail().equals("")) {
            mensagem.setMensagem("O e-mail informado não é válido!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(acao.save(obj),HttpStatus.CREATED);
        }

    }

    // Para EDITAR cadastros
    public ResponseEntity<?> editar(Pessoa obj) {
        //VALIDAÇÃO  DO CÓDIGO
        if (acao.countByCodigo(obj.getCodigo()) == 0) {
            mensagem.setMensagem("O código informado não existe!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND); //tbm podia ser o BAD_REQUEST

            //VALIDAÇÃO DO NOME
        } else if (obj.getNome().equals("")) {
            mensagem.setMensagem("É necessário informar um nome!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

            //VALIDAÇÃO DA IDADE
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("Informe uma idade válida!");
            return new ResponseEntity<>(mensagem,HttpStatus.BAD_REQUEST);

        }else if(obj.getEmail().equals("")) {
            mensagem.setMensagem("Informe um e-mail válido!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(acao.save(obj),HttpStatus.OK);
        }


    }
    //Para REMOVER cadatros
    public ResponseEntity<?> deletar(int codigo){
        if (acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("O código informado não existe. A ação de deletar falhou.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

        }else {
            Pessoa pessoa = acao.findByCodigo(codigo);
            acao.delete(pessoa);
            mensagem.setMensagem("Cadasto removido com sucesso.");
            return new ResponseEntity<>(mensagem,HttpStatus.OK);
        }
    }
    //Para SELECIONAR por NOME
    public ResponseEntity<?> selecionarPorNome(String obj){
        if (acao.findByNome(obj).equals("")){
            mensagem.setMensagem("Informe um nome válido.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else {
           return new ResponseEntity<>(acao.findByNome(obj), HttpStatus.OK);
        }
    }

}
