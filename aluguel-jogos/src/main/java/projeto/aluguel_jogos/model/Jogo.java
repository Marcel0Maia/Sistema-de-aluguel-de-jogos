package projeto.aluguel_jogos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JOGO")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String requisitosSistema;
    private String desenvolvedor;
    private String publicador;
    private String genero;
    private Double preco;
    private String imagemUrl;
    private boolean emDestaque = false;
    private boolean noCarrossel = false;

    @ManyToMany(mappedBy = "jogos")
    private List<Usuario> usuarios = new ArrayList<>();

    // Construtor vazio obrigatório
    public Jogo() {
    }

    public Jogo(String nome, String descricao , String requisitosSistema, String desenvolvedor, String publicador, String genero, Double preco, String imagemUrl) {
        this.nome = nome;
        this.descricao = descricao;
        this.requisitosSistema = requisitosSistema;
        this.desenvolvedor = desenvolvedor;
        this.publicador = publicador;
        this.genero = genero;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
    }

    // Getters e Setters

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

    public String getRequisitosSistema() {
        return requisitosSistema;
    }

    public void setRequisitosSistema(String requisitosSistema) {
        this.requisitosSistema = requisitosSistema;
    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    public String getPublicador() {
        return publicador;
    }

    public void setPublicador(String publicador) {
        this.publicador = publicador;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public boolean isEmDestaque() { return emDestaque; }

    public void setEmDestaque(boolean emDestaque) { this.emDestaque = emDestaque; }

    public boolean isNoCarrossel() { return noCarrossel; }

    public void setNoCarrossel(boolean noCarrossel) { this.noCarrossel = noCarrossel; }

    public List<Usuario> getUsuarios() { return usuarios; }

    public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }

}
