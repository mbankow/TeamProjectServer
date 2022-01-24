package com.team.project.team.mapper;

import com.team.project.team.dto.TeamDTO;
import com.team.project.team.entity.Team;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TeamMapper {
    @Mapping(target = "id", source = "id")
    TeamDTO toDTO(Team team);

    @InheritInverseConfiguration(name = "toDTO")
    Team fromDTO(TeamDTO teamDTO);

    List<TeamDTO> listToDTO(Collection<Team> teams);
}
