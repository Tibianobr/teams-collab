package com.teamscollab.manager.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "acron", length = 10)
    private String acron;

    private Integer wins = 0;

    private Integer losses = 0;

    private Integer goalsMaden = 0;

    private Integer goalsTaken = 0;

    private Integer goalsBalance = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcron() {
        return acron;
    }

    public void setAcron(String acron) {
        this.acron = acron;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getGoalsMaden() {
        return goalsMaden;
    }

    public void setGoalsMaden(Integer goalsMaden) {
        this.goalsMaden = goalsMaden;
    }

    public Integer getGoalsTaken() {
        return goalsTaken;
    }

    public void setGoalsTaken(Integer goalsTaken) {
        this.goalsTaken = goalsTaken;
    }

    public Integer getGoalsBalance() {
        return goalsBalance;
    }

    public void setGoalsBalance(Integer goalsBalance) {
        this.goalsBalance = goalsBalance;
    }
}
