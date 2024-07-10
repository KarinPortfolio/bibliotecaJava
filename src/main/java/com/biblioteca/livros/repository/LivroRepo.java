package com.biblioteca.livros.repository;


import com.biblioteca.livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepo  extends JpaRepository<Livro,Long> {

    boolean existsByNome(String nome);

    @Query("SELECT DISTINCT b.idioma FROM Livro b ORDER BY b.idioma")
    List<String> bucasidiomas();

    @Query("SELECT b FROM Livro b WHERE idioma = :idiomaSelecionado")
    List<Livro> buscarPorIdioma(String idiomaSelecionado);

    Livro findByNome(String nome);

    List<Livro> findTop10ByOrderByQuantidadeDeDownloadsDesc();

    @Query("SELECT b FROM Livro b WHERE b.autor.nome ILIKE %:pesquisa%")
    List<Livro> encontrarLivrosPorAutor(String pesquisa);
}
