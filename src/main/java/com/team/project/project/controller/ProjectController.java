package com.team.project.project.controller;

import com.team.project.project.dto.ProjectDTO;
import com.team.project.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    @Value("${paging.size}")
    private int pageSize;

    @GetMapping()
    public ResponseEntity<List<ProjectDTO>> findPaginated(@RequestParam(name = "page") int pageNumber) {
        List<ProjectDTO> projects = projectService.findPaginated(pageNumber,pageSize);
        HttpHeaders headers = projectService.getNumberOfPagesAndElementsSum(pageNumber,pageSize);
        return new ResponseEntity<>(projects, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> findById(@PathVariable(value = "id") Integer id) {
        ProjectDTO projectDTO = projectService.findById(id);
        return new ResponseEntity<>(projectDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> save(@RequestBody ProjectDTO projectDTO){
        ProjectDTO projectReturned = projectService.save(projectDTO);
        return new ResponseEntity<>(projectReturned, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProjectDTO> update(@RequestBody ProjectDTO projectDTO){
        ProjectDTO projectReturned = projectService.update(projectDTO);
        return new ResponseEntity<>(projectReturned, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDTO> deleteById(@PathVariable(value = "id") Integer id){
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
