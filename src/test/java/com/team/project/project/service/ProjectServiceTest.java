package com.team.project.project.service;


import com.team.project.project.entity.Project;
import com.team.project.project.exception.ProjectNotFoundException;
import com.team.project.project.mapper.ProjectMapper;
import com.team.project.project.mapper.ProjectMapperImpl;
import com.team.project.project.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.equalTo;


import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    Project testProject;

    ProjectMapper projectMapper = new ProjectMapperImpl();
    @Mock
    ProjectMapper projectMapperMock;
    @Mock
    ProjectRepository projectRepositoryMock;
    @InjectMocks
    ProjectService projectService;


    @BeforeEach
    public void setup(){
        testProject = new Project();
        testProject.setId(1);
        testProject.setName("Room booking system");
        testProject.setDescription("Create system allowing to book room in hotel through internet");
        testProject.setOwner(null);
        //ProjectDTO testProjectDTO = projectMapper.toDTO(testProject);
    }
    @Test
    public void isCreatedCorrect(){
        //given
        //when*
        when(projectRepositoryMock.save(testProject)).thenReturn(testProject);
        when(projectMapperMock.toDTO(any())).thenReturn(projectMapper.toDTO(testProject));
        when(projectMapperMock.fromDTO(any())).thenReturn(testProject);

        Project savedProject = projectMapper.fromDTO(projectService.save(projectMapper.toDTO(testProject)));
        //then
        assertThat(testProject.getName(), equalTo(savedProject.getName()));
        assertThat(testProject.getDescription(), equalTo(savedProject.getDescription()));
    }

    @Test
    public void isCreatedOneTime(){
        //given
        //when
        when(projectRepositoryMock.save(any())).thenReturn(testProject);
        when(projectMapperMock.toDTO(any())).thenReturn(projectMapper.toDTO(testProject));
        when(projectMapperMock.fromDTO(any())).thenReturn(testProject);

        Project savedProject = projectMapper.fromDTO(projectService.save(projectMapper.toDTO(testProject)));

        //then
        verify(projectRepositoryMock, times(1)).save(testProject);
        assertEquals(savedProject, testProject);
    }

    @Test
    public void isFoundById(){
        //given
        Integer id = 1;
        //when
        when(projectRepositoryMock.findById(id)).thenReturn(Optional.of(testProject));
        when(projectMapperMock.toDTO(any())).thenReturn(projectMapper.toDTO(testProject));
        Project foundProject = projectMapper.fromDTO(projectService.findById(id));
        //then
        assertThat(foundProject.getId(), equalTo(testProject.getId()));
        assertThat(foundProject.getName(), equalTo(testProject.getName()));
        assertThat(foundProject.getDescription(), equalTo(testProject.getDescription()));
    }

    @Test
    public void isNotFoundById(){
        //given
        Integer id = 20;
        //when
        when(projectRepositoryMock.findById(id)).thenThrow(ProjectNotFoundException.class);
        //then
        assertThrows(ProjectNotFoundException.class, () -> projectService.findById(id));
    }

    @Test
    public void isDeletedOnce(){
        //given
        Integer id = 1;
        //when
        doNothing().when(projectRepositoryMock).deleteById(id);
        projectService.deleteById(id);
        //then
        verify(projectRepositoryMock, times(1)).deleteById(id);
    }
}
