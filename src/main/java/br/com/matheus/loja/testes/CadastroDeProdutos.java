package br.com.matheus.loja.testes;

import br.com.matheus.loja.DAO.CategoriaDAO;
import br.com.matheus.loja.DAO.ProdutoDAO;
import br.com.matheus.loja.modelo.Categoria;
import br.com.matheus.loja.modelo.Produto;
import br.com.matheus.loja.util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class CadastroDeProdutos {
    public static void main(String[] args)  {
        cadastrarProduto();

        EntityManager em = JPAutil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto produto = produtoDAO.buscaPorId(1l);

        System.out.println(produto.getNome());

        produtoDAO.listar().forEach(p -> System.out.println(p.getDescricao()));
        System.out.println("////////");
        produtoDAO.buscarPorNome("notebook").forEach(p -> System.out.println(p.getDescricao()));
        System.out.println("////////");
        produtoDAO.buscarPorNomeDaCategoria("informatica").forEach(p -> System.out.println(p.getNome()));
//        produtoDAO.buscarPorNomeLike("notebook").forEach(p -> System.out.println(p.getDescricao()));

        System.out.println("\n" + produtoDAO.buscarPrecoComNomeDoProduto("notebook"));
    }

    private static void cadastrarProduto() {
        Categoria informatica = new Categoria("informatica");
        Produto notebook = new Produto("notebook", "256gb SSD", new BigDecimal("3000"), informatica);

        //padrao factory e DAO
        EntityManager em = JPAutil.getEntityManager();

        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);


        //com um framework nao é necessario controlar a transacao com begin, commit  e close
        em.getTransaction().begin();
        categoriaDAO.cadastrar(informatica);
        produtoDAO.cadastrar(notebook);

        em.getTransaction().commit();
        em.close();
//        em.remove(notebook);
    }
}
