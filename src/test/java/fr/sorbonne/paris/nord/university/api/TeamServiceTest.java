package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void shouldReturnAllTeams(){
        // Given.

        // When.
        List<TeamEntity> result = teamService.getTeamFromDB();

        // Then.
        assertThat(result).isNotNull().isNotEmpty();
    }

    @Test
    public void givenExistingId_whenFindTeamById_thenExpectedTeamInResult(){
        // Given.
        Long id=1L;
        // When.
        String name="PSG";
        TeamEntity result = teamService.findTeamById(id);

        // Then.
        assertThat(result).isNotNull();
        assertThat(result.getName()).
                isNotNull().
                isNotEmpty().
                isEqualTo(name);

    }

    @Test
    public void shouldReturnTheExpectedTeam_whenInsertTeamInDB(){
        // Given.
        Long id = 6L;
        String name="LOL";
        String slogan="Laugh Out Loud";
        TeamEntity team= new TeamEntity();
        team.setId(id);
        team.setName(name);
        team.setSlogan(slogan);

        // When.
        teamService.insertTeamInDB(team);
        TeamEntity result = teamService.findTeamById(id);

        // Then.
        assertThat(result).isNotNull();
        assertThat(result.getName()).
                isNotNull().
                isNotEmpty().
                isEqualTo(name);
        assertThat(result.getSlogan()).
                isNotNull().
                isNotEmpty().
                isEqualTo(slogan);
    }

    @Test
    public void shouldReturnTheExpectedTeam_whenUpdateTeamInDB(){
        // Given.
        String name="LOL";
        String slogan="Laugh Out Loud";
        TeamEntity team= new TeamEntity();
        team.setId(5L);
        team.setName(name);
        team.setSlogan(slogan);

        // When.
        teamService.updateTeamInDB(team);
        TeamEntity result = teamService.findTeamById(5L);

        // Then.
        assertThat(result).isNotNull();
        assertThat(result.getName()).
                isNotNull().
                isNotEmpty().
                isEqualTo(name);
        assertThat(result.getSlogan()).
                isNotNull().
                isNotEmpty().
                isEqualTo(slogan);
    }
    @Test
    public void shouldReturnEmpty_whenDeleteTeamById(){
        // Given.

        // When.
        teamService.deleteTeamById(2L);
        TeamEntity result = teamService.findTeamById(2L);

        // Then.
        assertThat(result).isNull();
    }


}
