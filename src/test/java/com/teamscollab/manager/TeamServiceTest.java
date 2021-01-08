package com.teamscollab.manager;

import com.teamscollab.manager.core.model.TeamEntity;
import com.teamscollab.manager.core.repository.TeamRepository;
import com.teamscollab.manager.core.service.TeamService;
import com.teamscollab.manager.core.service.TeamServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TeamServiceTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TeamServiceImpl teamServiceImpl;

    @BeforeClass
    public static void setup()
    {
        System.out.println("BEFORE");
    }

    @Test
    public void listById_TeamExists()
    {
        // GIVEN
        when(teamRepository.getOne(1)).thenReturn(createTeam("TEAM TEST"));

        // WHEN
        TeamEntity teamEntity = teamServiceImpl.listById(1);

        // THEN
        assertEquals(teamEntity.getName(),"TEAM TEST");
        assertNotNull(teamEntity);
        verify(teamRepository,times(1)).getOne(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void listById_TeamNotExists()
    {
        // GIVEN
        when(teamRepository.getOne(1)).thenThrow(EntityNotFoundException.class);

        // WHEN
        teamServiceImpl.listById(1);
        //THEN
        // expected
    }

    @Test
    public void calculateWinnerAndLoser_ListOfTeams()
    {
        // GIVEN
        TeamEntity teamWins = createTeamWithWinsAndLosses("TEAM WINS", 11, 5);
        TeamEntity teamTest = createTeamWithWinsAndLosses("TEAM TEST",10,9);
        TeamEntity teamDefeat = createTeamWithWinsAndLosses("TEAM DEFEAT",3,20);
        List<TeamEntity> teams = Arrays.asList(teamWins,teamDefeat,teamTest);

        // WHEN
        HashMap<String,TeamEntity> result = teamServiceImpl.calculateWinnerAndLoser(teams);

        //THEN
        assertThat(result.get("Winner"), samePropertyValuesAs(teamWins));
      //  assertEquals(result.get("Loser"),teamDefeat);
    }



    private TeamEntity createTeam(String teamName)
    {
        TeamEntity team = new TeamEntity();
        team.setName(teamName);
        team.setAcron(teamName.substring(0,3));
        return team;
    }

    private TeamEntity createTeamWithWinsAndLosses(String teamName,Integer wins, Integer losses)
    {
        TeamEntity team = new TeamEntity();
        team.setName(teamName);
        team.setAcron(teamName.substring(0,3));
        team.setWins(wins);
        team.setLosses(losses);
        return team;
    }
}
