package com.teamscollab.manager.core.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "matches", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "team_a", referencedColumnName = "team_id", nullable = false)
    private TeamEntity teamA;

    @ManyToOne
    @JoinColumn(name = "team_b", referencedColumnName = "team_id", nullable = false)
    private TeamEntity teamB;

    @Basic
    @Column(name = "score_a")
    private Integer scoreA;

    @Basic
    @Column(name = "score_b")
    private Integer scoreB;

    private String teamWinner;

    public MatchEntity() {
    }

    public MatchEntity(TeamEntity teamA, TeamEntity teamB, Integer scoreA, Integer scoreB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScoreA() {
        return scoreA;
    }

    public void setScoreA(Integer scoreA) {
        this.scoreA = scoreA;
    }

    public Integer getScoreB() {
        return scoreB;
    }

    public void setScoreB(Integer scoreB) {
        this.scoreB = scoreB;
    }

    public TeamEntity getTeamA() {
        return teamA;
    }

    public void setTeamA(TeamEntity teamA) {
        this.teamA = teamA;
    }

    public TeamEntity getTeamB() {
        return teamB;
    }

    public void setTeamB(TeamEntity teamB) {
        this.teamB = teamB;
    }

    public String getTeamWinner() {
        return teamWinner;
    }

    public void setTeamWinner(String teamWinner) {
        this.teamWinner = teamWinner;
    }
}

