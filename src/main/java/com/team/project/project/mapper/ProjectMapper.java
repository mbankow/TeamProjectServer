package com.team.project.project.mapper;

import com.team.project.project.dto.ProjectDTO;
import com.team.project.project.entity.Project;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectMapper {
    @Mapping(target = "id", source = "id")
    ProjectDTO toDTO(Project project);

    @InheritInverseConfiguration(name = "toDTO")
    Project fromDTO(ProjectDTO projectdto);

    List<ProjectDTO> listToDTO(Collection<Project> projects);
}
