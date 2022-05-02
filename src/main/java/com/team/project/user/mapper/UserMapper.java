package com.team.project.user.mapper;

import com.team.project.user.dto.UserDTO;
import com.team.project.user.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    UserDTO toDTO(User user);

    @InheritInverseConfiguration(name = "toDTO")
    User fromDTO(UserDTO userDTO);

    List<UserDTO> listToDTO(Collection<User> users);
}
