package br.com.matheus.loja.modelo;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//mapeando a entidade produtos
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
//    @Column(name = "desc")
    private String descricao;
    private BigDecimal preco;
    private LocalDateTime dataHora = LocalDateTime.now();

    //por padrao o JPA ira salvar esse atributo como int, salvando a posicao do atributo do enum
    //portanto é preciso definir
//    @Enumerated(EnumType.STRING)  --> usado para enum

    //cardinalidade n p/ 1
    @ManyToOne
    private Categoria categoria;

    public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Produto() {}

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
