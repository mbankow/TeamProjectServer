package com.team.project.team.service;

import com.team.project.team.dto.TeamDTO;
import com.team.project.team.entity.Team;
import com.team.project.team.exception.TeamNotFoundException;
import com.team.project.team.mapper.TeamMapper;
import com.team.project.team.repository.TeamRepository;
import com.team.project.user.entity.User;
import com.team.project.user.service.UserService;
import com.team.project.util.Headers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final UserService userService;

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

    public TeamDTO findById(Integer id){
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException("Team by ID" + id + " was not found"));
        return teamMapper.toDTO(team);
    }

    public void deleteById(Integer id){
        teamRepository.deleteById(id);
    }

    public void assignTeamsToMentors(){
        List<User> mentors = userService.getMentors();
        List<Team> teams = teamRepository.findAll();
        boolean assigned;
        teams.sort(Comparator.comparing(o -> o.getCreateAt()));
        for(Team team : teams) {
            assigned = false;
            String[] chooses = team.getChoices().split(",");
            for (String chose: chooses ) {
                log.debug(chose + " wpetli1");
                for(User mentor: mentors){
                    log.debug(mentor.getFirm()+ " w petli2");
                    if(mentor.getAssignedTeams().size() < 6 && mentor.getFirm().equals(chose)){
                        log.debug(mentor.getFirm()+ " w petli3");
                        team.setMentor(mentor);
                        assigned = true;
                    }
                    if(assigned)
                        break;
                }
                if(assigned)
                    break;
            }
        }
        for(Team team :teams){
            teamRepository.save(team);
        }
    }
}
