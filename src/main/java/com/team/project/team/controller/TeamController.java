package com.team.project.team.controller;

import com.team.project.team.dto.TeamDTO;
import com.team.project.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;
    @Value("${paging.size}")
    private int pageSize;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping()
    public ResponseEntity<List<TeamDTO>> findPaginated(@RequestParam(name = "page") int pageNumber) {
        List<TeamDTO> students = teamService.findPaginated(pageNumber,pageSize);
        HttpHeaders headers = teamService.getNumberOfPagesAndElementsSum(pageNumber,pageSize);
        return new ResponseEntity<>(students, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable(value = "id") Integer id) {
        TeamDTO teamDTO = teamService.findById(id);
        return new ResponseEntity<>(teamDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeamDTO> save(@RequestBody TeamDTO teamDTO){
        TeamDTO teamReturned = teamService.save(teamDTO);
        return new ResponseEntity<>(teamReturned, HttpStatus.CREATED);
    }

    @PutMapping("/assign")
    public ResponseEntity<?> assignTeams(@RequestBody Boolean assign){
        if(assign){
            teamService.assignTeamsToMentors();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Użytkownik z takim emailem już istnieje.");
    }

    @PutMapping
    public ResponseEntity<TeamDTO> update(@RequestBody TeamDTO teamDTO){
        TeamDTO teamReturned = teamService.update(teamDTO);
        return new ResponseEntity<>(teamReturned, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamDTO> deleteById(@PathVariable(value = "id") Integer id){
        teamService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
