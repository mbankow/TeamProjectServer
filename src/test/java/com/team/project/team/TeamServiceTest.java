package com.team.project.team;


import com.team.project.team.dto.TeamDTO;
import com.team.project.team.entity.Team;
import com.team.project.team.exception.TeamNotFoundException;
import com.team.project.team.mapper.TeamMapper;
import com.team.project.team.mapper.TeamMapperImpl;
import com.team.project.team.repository.TeamRepository;
import com.team.project.team.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    Team testTeam;
    TeamMapper teamMapper = new TeamMapperImpl();
    @Mock
    TeamMapper teamMapperMock;
    @Mock
    TeamRepository teamRepositoryMock;
    @InjectMocks
    TeamService teamService;

    @BeforeEach
    public void setup(){
        testTeam = new Team();
        testTeam.setId(1);
        testTeam.setName("Snowflakes");
        testTeam.setCreateAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        testTeam.setMentor(null);
        testTeam.setStudents(null);
        testTeam.setChoices(null);
    }

    @Test
    public void isCreatedCorrect(){

        when(teamRepositoryMock.save(any(Team.class))).thenReturn(testTeam);
        when(teamMapperMock.fromDTO(any(TeamDTO.class))).thenReturn(testTeam);
        when(teamMapperMock.toDTO(any(Team.class))).thenReturn(teamMapper.toDTO(testTeam));

        TeamDTO savedTeam = teamService.save(teamMapper.toDTO(testTeam));

        assertThat(savedTeam.getId(), equalTo(testTeam.getId()));
        assertThat(savedTeam.getName(), equalTo(testTeam.getName()));
    }

    @Test
    public void isCreatedOneTime(){
        when(teamRepositoryMock.save(any())).thenReturn(testTeam);
        when(teamMapperMock.fromDTO(any(TeamDTO.class))).thenReturn(testTeam);
        when(teamMapperMock.toDTO(any(Team.class))).thenReturn(teamMapper.toDTO(testTeam));

        Team savedTeam = teamMapper.fromDTO(teamService.save(teamMapper.toDTO(testTeam)));

        verify(teamRepositoryMock, times(1)).save(testTeam);
        assertEquals(savedTeam, testTeam);
    }

    @Test
    public void IsFoundById(){
        Integer id = 1;

        when(teamRepositoryMock.findById(id)).thenReturn(Optional.of(testTeam));
        when(teamMapperMock.toDTO(any())).thenReturn(teamMapper.toDTO(testTeam));
        Team savedTeam = teamMapper.fromDTO(teamService.findById(id));

        assertEquals(testTeam, savedTeam);
        assertEquals(testTeam.getId(), savedTeam.getId());
        assertEquals(testTeam.getName(), savedTeam.getName());
    }

    @Test
    public void isNotFoundById(){
        Integer id = 20;

        when(teamRepositoryMock.findById(id)).thenThrow(TeamNotFoundException.class);

        assertThrows(TeamNotFoundException.class, () -> teamService.findById(id));
    }

    @Test
    public void isDeletedOnce(){
        Integer id = 1;

        doNothing().when(teamRepositoryMock).deleteById(id);
        teamService.deleteById(id);

        verify(teamRepositoryMock, times(1)).deleteById(id);
    }

}
