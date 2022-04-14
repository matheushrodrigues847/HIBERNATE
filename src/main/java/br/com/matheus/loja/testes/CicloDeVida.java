package br.com.matheus.loja.testes;

import br.com.matheus.loja.modelo.Categoria;
import br.com.matheus.loja.util.JPAutil;

import javax.persistence.EntityManager;
import java.util.List;

public class CicloDeVida {
    public static void main(String[] args){
        EntityManager em = JPAutil.getEntityManager();

        em.getTransaction().begin();

        //estado transiente --  a jpa n está gerenciando essa entidade
        Categoria informatica = new Categoria("informatica");

        //estado em gerenciamento -- jpa está olhando
        em.persist(informatica);
        informatica.setNome("celulares");

        //gravado no banco
        em.flush();
        //estado detached -- fechado
        em.clear();

        //retornando para o estado de gerenciamento
        informatica = em.merge(informatica);
        informatica.setNome("eletrodomesticos");
        em.flush();
        //volta pra o management
//        Categoria c = em.find(Categoria.class, 1);
        //gravando
        em.remove(informatica);

        //o flush nao termina a transacao, sendo possivel fazer um rollback, diferente do commit
        em.flush();

//        List<Categoria> categorias = (List<Categoria>) em.createQuery("select nome from Categoria ");
//        categorias.forEach(c -> System.out.println(c.getNome()));


    }
}
