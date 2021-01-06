package com.teamscollab.manager.core.model;

public class MostWinnerMostLoser {

    private TeamEntity teamMostWinner;

    private TeamEntity teamMostLoser;

    public MostWinnerMostLoser(TeamEntity teamMostWinner, TeamEntity teamMostLoser) {
        this.teamMostWinner = teamMostWinner;
        this.teamMostLoser = teamMostLoser;
    }

    public TeamEntity getTeamMostWinner() {
        return teamMostWinner;
    }

    public void setTeamMostWinner(TeamEntity teamMostWinner) {
        this.teamMostWinner = teamMostWinner;
    }

    public TeamEntity getTeamMostLoser() {
        return teamMostLoser;
    }

    public void setTeamMostLoser(TeamEntity teamMostLoser) {
        this.teamMostLoser = teamMostLoser;
    }
}
