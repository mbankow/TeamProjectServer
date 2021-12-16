package com.team.project.mentor.mapper;

import com.team.project.mentor.dto.MentorDTO;
import com.team.project.mentor.entity.Mentor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MentorMapper {
    @Mapping(target = "id", source = "id")
    MentorDTO toDTO(Mentor mentor);

    @InheritInverseConfiguration(name = "toDTO")
    Mentor fromDTO(MentorDTO mentordto);

    List<MentorDTO> listToDTO(Collection<Mentor> mentors);
}
