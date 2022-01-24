package com.team.project.student.mapper;

import com.team.project.student.dto.StudentDTO;
import com.team.project.student.entity.Student;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentMapper {
    @Mapping(target = "id", source = "id")
    StudentDTO toDTO(Student student);

    @InheritInverseConfiguration(name = "toDTO")
    Student fromDTO(StudentDTO studentDTO);

    List<StudentDTO> listToDTO(Collection<Student> students);
}
