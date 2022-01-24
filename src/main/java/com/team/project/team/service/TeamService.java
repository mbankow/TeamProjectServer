package com.team.project.team.service;

import com.team.project.team.dto.TeamDTO;
import com.team.project.team.entity.Team;
import com.team.project.team.exception.TeamNotFoundException;
import com.team.project.team.mapper.TeamMapper;
import com.team.project.team.repository.TeamRepository;
import com.team.project.util.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public List<TeamDTO> findPaginated(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return teamMapper.listToDTO((teamRepository.findAll(pageable)).getContent());
    }

    public HttpHeaders getNumberOfPagesAndElementsSum(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Headers.NUMBER_OF_PAGES, String.valueOf(teamRepository.findAll(pageable).getTotalPages()));
        headers.add(Headers.TOTAL_ITEMS, String.valueOf(teamRepository.findAll(pageable).getTotalElements()));
        return headers;
    }

    public TeamDTO save(TeamDTO teamDTO){
        Team team = teamMapper.fromDTO(teamDTO);
        return teamMapper.toDTO(teamRepository.save(team));
    }

    public TeamDTO update(TeamDTO teamDTO){
        return teamMapper.toDTO(teamRepository.save(teamMapper.fromDTO(teamDTO)));
    }

    public TeamDTO findById(Long id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("Team by ID" + id + " was not found"));
        return teamMapper.toDTO(team);
    }

    public void deleteById(Long id){
        teamRepository.deleteById(id);
    }
}
