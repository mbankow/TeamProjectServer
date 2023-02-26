package com.team.project.project.service;

import com.team.project.project.dto.ProjectDTO;
import com.team.project.project.entity.Project;
import com.team.project.project.exception.ProjectNotFoundException;
import com.team.project.project.mapper.ProjectMapper;
import com.team.project.project.repository.ProjectRepository;
import com.team.project.util.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectDTO> findPaginated(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return projectMapper.listToDTO((projectRepository.findAll(pageable)).getContent());
    }

    public HttpHeaders getNumberOfPagesAndElementsSum(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Headers.NUMBER_OF_PAGES, String.valueOf(projectRepository.findAll(pageable).getTotalPages()));
        headers.add(Headers.TOTAL_ITEMS, String.valueOf(projectRepository.findAll(pageable).getTotalElements()));
        return headers;
    }

    public ProjectDTO save(ProjectDTO projectDTO){
        Project project = projectMapper.fromDTO(projectDTO);
        return projectMapper.toDTO(projectRepository.save(project));
    }

    public ProjectDTO update(ProjectDTO projectDTO){
        return projectMapper.toDTO(projectRepository.save(projectMapper.fromDTO(projectDTO)));
    }

    public ProjectDTO findById(Integer id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project by ID" + id + " was not found"));
        return projectMapper.toDTO(project);
    }

    public void deleteById(Integer id){
        projectRepository.deleteById(id);
    }
}
