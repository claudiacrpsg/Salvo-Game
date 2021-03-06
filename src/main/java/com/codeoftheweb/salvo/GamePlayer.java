package com.codeoftheweb.salvo;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Ship> ships = new HashSet<>();

    @OneToMany(mappedBy = "gamePlayer", fetch = FetchType.EAGER)
    private Set<Salvo> salvoes = new HashSet<>();

    public GamePlayer() { }

    public GamePlayer (Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    public Set<Ship> getShips() {
        return this.ships;
    }

    public void addShip (Ship ship){
        this.ships.add(ship);
        ship.setGamePlayer(this);
    }

    public Set<Salvo> getSalvoes() {
        return this.salvoes;
    }

    public void addSalvo (Salvo salvo){
        this.salvoes.add(salvo);
        salvo.setGamePlayer(this);
    }

    public Score getScores(){ //this method allows access to scores via gameplayers
        return this.player.getScore(this.game);
    }


    @Override
    public String toString() {
        return "GamePlayer{" +
                "id=" + id +
                ", player=" + player +
                ", game=" + game +
                ", ships=" + ships +
                '}';
    }

}