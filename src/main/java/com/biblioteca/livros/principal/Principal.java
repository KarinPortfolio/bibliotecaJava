package com.biblioteca.livros.principal;

import com.biblioteca.livros.model.Autor;
import com.biblioteca.livros.model.DadosAutor;
import com.biblioteca.livros.model.DadosLivro;
import com.biblioteca.livros.model.Livro;
import com.biblioteca.livros.repository.LivroRepo;
import com.biblioteca.livros.repository.AutorRepo;
import com.biblioteca.livros.service.ConsumoApi;
import com.biblioteca.livros.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
public class Principal {
    private final Scanner sc = new Scanner(System.in);
    private final ConsumoApi requisicao = new ConsumoApi();
    private AutorRepo repositorioAutor;
    private LivroRepo repositorioLivro;
    private final ConverteDados conversor = new ConverteDados();

    public Principal() {
        this.repositorioLivro = repositorioLivro;
    }

    public void showMenu () {
            //System.out.println(headerBase);
            String menu = """
                            
                    *** Escolha a opção ***
                    1 - Buscar livro
                    2 - Listar livros
                    3 - Listar autores
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livro por idioma            
                    0 - Sair
                    ***********************
                    """;
            var opcao = -1;
            while (opcao != 0) {
                System.out.println(menu);
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        buscarLivro();
                        break;
                    case 2:
                        buscarLivros();
                        break;
                    case 3:
                        buscarAutores();
                        break;
                    case 4:
                        buscarAutoresVivosPorAno();
                        break;
                    case 5:
                        buscarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("S A I N D O ...");
                        break;
                    default:
                        System.out.println("\n\n=============   O p ç ã o   I n v á l i d a  (Digite um número de 0 a 8)   ================\n\n");
                }
            }


        }

    private void buscarLivro () {
        final ConsumoApi client = new ConsumoApi();

        System.out.println("\nQual livro deseja buscar?");
            var buscaDoUsuario = sc.nextLine();
        String ADDRESS = "https://gutendex.com/books?search=";
        var dados = requisicao.consumo(ADDRESS + buscaDoUsuario.replace(" ", "%20"));
            salvarNoDb(dados);


    }

        private void salvarNoDb (String dados){
            try {
                Livro livro = new Livro(conversor.getData(dados, DadosLivro.class));
                Autor autor = new Autor(conversor.getData(dados, DadosAutor.class));
                Autor autorDb = null;
                Livro livroDb = null;
                 if (!repositorioAutor.existsByNome(autor.getNome())){
                   repositorioAutor.save(autor);
                  autorDb = autor;
                }else{
                  autorDb = repositorioAutor.findByNome(autor.getNome());
                 }
                if (!repositorioLivro.existsByNome(livro.getNome())){
                  livro.setAutor(autorDb);
                  repositorioLivro.save(livro);
                  livroDb = livro;
                }else{
                   livroDb = repositorioLivro.findByNome(livro.getNome());
                 }
                System.out.println(livroDb);
            } catch (NullPointerException e) {
                System.out.println("\n\n*** não listado  ***\n\n");
            }

        }


        private void buscarLivros () {
                System.out.println("\n\n*** funcionalidade ainda não implementada  ***\n\n");
        }

        private void buscarAutores () {
            System.out.println("\n\n*** funcionalidade ainda não implementada  ***\n\n");
        }

        private void buscarAutoresVivosPorAno () {
            System.out.println("\n\n*** funcionalidade ainda não implementada  ***\n\n");
        }

        private void buscarLivrosPorIdioma () {
        System.out.println("\n\n*** funcionalidade ainda não implementada  ***\n\n");
        }

    }






