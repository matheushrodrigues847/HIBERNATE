package br.com.matheus.loja.DAO;

import br.com.matheus.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO {
    private EntityManager em;

    public ProdutoDAO(EntityManager em){
        this.em = em;
    }

    //baixo acoplamento com o banco de dados
    //menos verboso em comparacao com o jdbc
    public void cadastrar(Produto produto){
        em.persist(produto);
    }

    public Produto buscaPorId(Long id){

        return em.find(Produto.class, id);
    }

    public List<Produto> listar(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(jpql, Produto.class)
                .setParameter(1, nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        //join muito menos verboso -- p.categoria
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1";
        return em.createQuery(jpql, Produto.class)
                .setParameter(1, nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoComNomeDoProduto(String nome){
        //join muito menos verboso -- p.categoria
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter(1, nome)
                .getSingleResult();
    }

//    public List<Produto> buscarPorNomeLike(String nome){
//        String jpql = "SELECT p FROM Produto p WHERE p.nome LIKE '%:nome%'";
//        return em.createQuery(jpql, Produto.class)
//                .setParameter("%" + nome + "%", nome)
//                .getResultList();
//    }
}
