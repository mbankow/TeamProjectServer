package com.team.project.project.service;

import com.team.project.project.entity.Project;
import com.team.project.project.mapper.ProjectMapper;
import com.team.project.project.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectServiceTest {

    @Autowired
    ProjectMapper projectMapper;
    @Mock
    ProjectMapper projectMapperMock;
    @Mock
    ProjectRepository projectRepositoryMock;
    @InjectMocks
    ProjectService projectServiceMock;


    @Test
    public void isCreatedCorrectName(){
        //given
        Project project = new Project();
        project.setId(1);
        project.setName("Room booking system");
        project.setDescription("Create system allowing to book room in hotel through internet");
        project.setOwner(null);
        //when
        ;
        //then

    }


}
