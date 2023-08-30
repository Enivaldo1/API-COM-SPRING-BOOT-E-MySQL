package br.com.cadastramento.cadastro.repositorio;

import br.com.cadastramento.cadastro.modelo.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {

    List<Pessoa> findAll();
    Pessoa findByCodigo(int codigo);
    List<Pessoa> findByNome(String name);


    int countByCodigo(int codigo);

}

