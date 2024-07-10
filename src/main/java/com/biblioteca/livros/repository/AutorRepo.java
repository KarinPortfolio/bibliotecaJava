package com.biblioteca.livros.repository;

import com.biblioteca.livros.model.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AutorRepo extends JpaRepository<Autor, Long>{
    Boolean existsByNome(String nome);

    Autor findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.dataDeFalecimento >= :anoSelecionado AND :anoSelecionado >= a.dataDeNascimento")
    List<Autor> buscarPorAnoDeFalecimento(int anoSelecionado);

    @Query("SELECT a FROM Autor a WHERE a.nome ILIKE %:pesquisa%")
    List<Autor> encontrarPorNome(String pesquisa);
}