package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/hello")
    public String getTeams() {
        return "Hello World";
    }

    @GetMapping("/teams")
    public List<TeamEntity> teams() {
        return teamService.getTeamFromDB();
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<TeamEntity> team(@PathVariable Long id) {
        TeamEntity team = teamService.findTeamById(id);
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }

    @PutMapping("/teams/{id}")
    void updateTeam(@RequestBody TeamEntity newTeam, @PathVariable Long id) {
        teamService.findTeamById(id).setId(newTeam.getId());
        teamService.findTeamById(id).setName(newTeam.getName());
        teamService.findTeamById(id).setSlogan(newTeam.getSlogan());
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamEntity> createTeam(@RequestBody TeamEntity newTeam){

        return new ResponseEntity<TeamEntity>(teamService.insertTeamInDB(newTeam), HttpStatus.CREATED);
    }



}
