package com.teamscollab.manager.core.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.teamscollab.manager.core.model.MatchEntity;
import com.teamscollab.manager.core.model.TeamEntity;
import com.teamscollab.manager.core.repository.MatchRepository;
import com.teamscollab.manager.core.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
=======
import javax.persistence.EntityManagerFactory;
import java.util.List;
>>>>>>> estrutura-codigo

@Service
public class MatchServiceImpl implements MatchService {


    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

<<<<<<< HEAD
    @Autowired
    private EntityManagerFactory entityManagerFactory;

=======
>>>>>>> estrutura-codigo
    @Override
    public MatchEntity getMatch(int i) {
        return matchRepository.getOne(i);
    }

    @Override
<<<<<<< HEAD
    public MatchEntity matchDataUpdater(MatchEntity match) {
        if (match.getScoreA() > match.getScoreB()) { //time A ganha

            //DECIDE O GANHADOR DA PARTIDA + SEU NOME
            match.setTeamWinner(match.getTeamA().getName());

            //ADICIONA VITÓRIAS AO CONTADOR DE VITÓRIAS E DERROTAS
            match.getTeamA().setWins(match.getTeamA().getWins() + 1); //adiciona uma vitória ao time A
            match.getTeamB().setLosses(match.getTeamB().getLosses() + 1); //adiciona uma derrota ao time B

            //ADICIONA GOALS AOS TIMES
=======
    public boolean deleteMatch(Integer id) {
        MatchEntity match = matchRepository.getOne(id);

        // Retorna false caso não exista a partida
        if (!matchRepository.findById(id).isPresent())
            return false;

        //descontar wins e losses
        //descontar goals taken, goals maden, goals balance = 0;

        TeamEntity teamA = match.getTeamA();
        TeamEntity teamB = match.getTeamB();

        if (match.getScoreA() > match.getScoreB()){
            teamA.setWins(teamA.getWins() - 1);
            teamB.setLosses(teamB.getLosses() - 1);
        } else if (match.getScoreB() > match.getScoreA()) {
            teamB.setWins(teamB.getWins() - 1);
            teamA.setLosses(teamA.getLosses() - 1);
        }

        teamA.setGoalsMaden(teamA.getGoalsMaden() - match.getScoreA());
        teamA.setGoalsTaken(teamA.getGoalsTaken() - match.getScoreB());
        teamA.setGoalsBalance(teamA.getGoalsBalance() - match.getScoreA() + match.getScoreB());

        teamB.setGoalsMaden(teamB.getGoalsMaden() - match.getScoreB());
        teamB.setGoalsTaken(teamB.getGoalsTaken() - match.getScoreA());
        teamB.setGoalsBalance(teamB.getGoalsBalance() - match.getScoreB() + match.getScoreA());

        matchRepository.deleteById(id);

        return true;
    }

    /**
     * Update all teams involved data.
     *  + Increases wins and loses to each team.
     *  + Increases the amount of goals to each team.
     *
     * @param match -> Match to update.
     * @return MatchEntity -> Returns the updated match.
     */
    @Override
    public MatchEntity matchDataUpdater(MatchEntity match) {
        if (match.getScoreA() > match.getScoreB()) { //time A ganha

            // DECIDE O GANHADOR DA PARTIDA + SEU NOME
            match.setTeamWinner(match.getTeamA());

            // ADICIONA VITÓRIAS AO CONTADOR DE VITÓRIAS E DERROTAS
            match.getTeamA().setWins(match.getTeamA().getWins() + 1); //adiciona uma vitória ao time A
            match.getTeamB().setLosses(match.getTeamB().getLosses() + 1); //adiciona uma derrota ao time B

            // ADICIONA GOALS AOS TIMES
>>>>>>> estrutura-codigo
            updateGoalsSettler(match);
            matchRepository.save(match);
        } else if (match.getScoreB() > match.getScoreA()) { // time B ganha

<<<<<<< HEAD
            //DECIDE O GANHADOR DA PARTIDA + SEU NOME
            match.setTeamWinner(match.getTeamB().getName());

            //ADICIONA VITÓRIAS AO CONTADOR DE VITÓRIAS E DERROTAS
            match.getTeamB().setWins(match.getTeamB().getWins() + 1); //adiciona uma vitória ao time A
            match.getTeamA().setLosses(match.getTeamA().getLosses() + 1); //adiciona uma derrota ao time B

            //ADICIONA GOALS AOS TIMES
=======
            // DECIDE O GANHADOR DA PARTIDA + SEU NOME
            match.setTeamWinner(match.getTeamB());

            // ADICIONA VITÓRIAS AO CONTADOR DE VITÓRIAS E DERROTAS
            match.getTeamB().setWins(match.getTeamB().getWins() + 1); //adiciona uma vitória ao time A
            match.getTeamA().setLosses(match.getTeamA().getLosses() + 1); //adiciona uma derrota ao time B

            // ADICIONA GOALS AOS TIMES
>>>>>>> estrutura-codigo
            updateGoalsSettler(match);
            matchRepository.save(match);
        } else {
            updateGoalsSettler(match);
<<<<<<< HEAD
            match.setTeamWinner("Draw");
=======
            match.setTeamWinner(null);
>>>>>>> estrutura-codigo
            matchRepository.save(match);
        }

        return match;
    }

    @Override
    public List<MatchEntity> listAllMatches() {
        return matchRepository.findAll();
    }

    @Override
    public MatchEntity saveMatch(MatchEntity match) {
        MatchEntity savedMatch = null;

        if (match.getId() == null) {
            TeamEntity teamA = teamRepository.getOne(match.getTeamA().getId());
            TeamEntity teamB = teamRepository.getOne(match.getTeamB().getId());

            if (teamA.getId() != null && teamB.getId() != null &&
                    !teamB.getName().isEmpty() && !teamA.getName().isEmpty()) {

                match.setTeamA(teamA);
                match.setTeamB(teamB);
                savedMatch = matchRepository.save(match);
                matchDataUpdater(savedMatch);

            } else {

                match.setTeamA(teamA);
                match.setTeamB(teamB);
                savedMatch = matchRepository.save(match);

            }
        }

        return savedMatch;
    }

    private void updateGoalsSettler(MatchEntity match) {

        match.getTeamA().setGoalsMaden(match.getTeamA().getGoalsMaden() + match.getScoreA());
        match.getTeamA().setGoalsTaken(match.getTeamA().getGoalsTaken() + match.getScoreB());
        match.getTeamA().setGoalsBalance(match.getTeamA().getGoalsBalance() + match.getScoreA() - match.getScoreB());

        match.getTeamB().setGoalsMaden(match.getTeamB().getGoalsMaden() + match.getScoreB());
        match.getTeamB().setGoalsTaken(match.getTeamB().getGoalsTaken() + match.getScoreA());
        match.getTeamB().setGoalsBalance(match.getTeamB().getGoalsBalance() + match.getScoreB() - match.getScoreA());
    }

<<<<<<< HEAD
    public MatchEntity greatestGoalsBalance() {
        List<MatchEntity> matchToCompare = listAllMatches();

=======
    /**
     * Find match with largest goals difference.
     * Ex: 6x0 (6) is largest than 3x1 (2).
     *
     * @return MatchEntity -> Match with largest goals difference.
     */
    public MatchEntity greatestGoalsBalance() {
        List<MatchEntity> matchToCompare = listAllMatches();

        // Verify if there is matches in the list.
>>>>>>> estrutura-codigo
        if (matchToCompare.isEmpty()) {
            return null;
        }

        MatchEntity greatestMatch = matchToCompare.get(0);
        int balanceMatch = Math.abs(greatestMatch.getScoreA() - greatestMatch.getScoreB());

        for (MatchEntity comparingMatch : matchToCompare) {
            int balanceNow = Math.abs(comparingMatch.getScoreA() - comparingMatch.getScoreB());

            if (balanceNow >= balanceMatch) {

                if (balanceNow == balanceMatch) {
                    int goalSumA, goalSumB;

                    goalSumA = comparingMatch.getScoreA() + comparingMatch.getScoreB();
                    goalSumB = greatestMatch.getScoreA() + greatestMatch.getScoreB();

                    if (goalSumA > goalSumB) {
                        greatestMatch = comparingMatch;
                        return greatestMatch;
                    }
                } else {
                    greatestMatch = comparingMatch;
                    balanceMatch = balanceNow;
                }
            }
        }

        return greatestMatch;
    }

}