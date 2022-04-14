package br.com.matheus.loja.DAO;

import br.com.matheus.loja.modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDAO {
    private EntityManager em;

    public CategoriaDAO(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Categoria categoria){
        em.persist(categoria);
    }

    public void atualizar(Categoria categoria){
        this.em.merge(categoria);
    }

    public void delete(Categoria categoria){
        categoria = em.merge(categoria);
        this.em.remove(categoria);
    }
}
