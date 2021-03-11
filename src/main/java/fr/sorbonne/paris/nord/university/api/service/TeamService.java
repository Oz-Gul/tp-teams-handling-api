package fr.sorbonne.paris.nord.university.api.service;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    //Récuperer toutes les équipes en base de données
    public List<TeamEntity> getTeamFromDB() {
        return teamRepository.findAll();
    }

    //Récuperer une équipe en base à partir de son ID
    public TeamEntity findTeamById(Long id) {
        return (TeamEntity) teamRepository.findById(id).orElse(null);
    }

    //Insérer une équipe en base
    @Transactional
    public TeamEntity  insertTeamInDB(TeamEntity team) {
       return teamRepository.save(team);
    }

    //Modifier une équipe en base
    @Transactional
    public void updateTeamInDB(TeamEntity team) {
        teamRepository.save(team);
    }

    //Supprimer une équipe existante de la base de données à partir de son ID
    @Transactional
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}
