package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
@SpringBootTest
public class TeamControllerTest {

    @InjectMocks
    TeamController teamController;
    @Mock
    TeamService teamService;

    TeamEntity ALLO = new TeamEntity(1L,"ALLO","Allooo");
    TeamEntity HOLA = new TeamEntity(2L,"HOLA","No Se");
    TeamEntity JSP = new TeamEntity(3L,"JSP","Je Sais Pas");

    List<TeamEntity> VDB = List.of(ALLO,HOLA,JSP);
    TeamEntity newTeam;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(teamController);
    }

    @Test
    public void givenUrlRequestGetThenOK(){
        when(teamService.getTeamFromDB()).thenReturn(VDB);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("teams")
                .then()
                .statusCode(200);
    }

    @Test
    public void givenUrlRequestByIdGetThenOK(){
        when(teamService.findTeamById(1L)).thenReturn(ALLO);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("teams/{id}",1L)
                .then()
                .statusCode(200);
    }

    @Test
    public void givenUrlRequestByIdGetThen404(){
        when(teamService.findTeamById(1L)).thenReturn(ALLO);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("teams/{id}",7L)
                .then()
                .statusCode(404);
    }

    @Test
    public void givenUrlRequestDeleteThenOK(){

        given().when()
                .delete("teams/{id}",1L)
                .then()
                .statusCode(200);
    }


}
