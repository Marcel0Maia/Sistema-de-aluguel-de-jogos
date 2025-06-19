package projeto.aluguel_jogos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIOS_JOGOS")
public class UsuarioJogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @Column(name = "tempo_de_aluguel")
    private int tempoDeAluguel; // em dias: 1, 7 ou 30

    public UsuarioJogo() {}

    public UsuarioJogo(Usuario usuario, Jogo jogo, int tempoDeAluguel) {
        this.usuario = usuario;
        this.jogo = jogo;
        this.tempoDeAluguel = tempoDeAluguel;
    }

    // Getters e setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public int getTempoDeAluguel() {
        return tempoDeAluguel;
    }

    public void setTempoDeAluguel(int tempoDeAluguel) {
        this.tempoDeAluguel = tempoDeAluguel;
    }
}