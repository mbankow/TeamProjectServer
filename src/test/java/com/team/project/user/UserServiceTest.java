package com.team.project.user;

import com.team.project.user.dto.UserDTO;
import com.team.project.user.entity.User;
import com.team.project.user.enumeration.RoleName;
import com.team.project.user.exception.UserNotFoundException;
import com.team.project.user.mapper.UserMapper;
import com.team.project.user.mapper.UserMapperImpl;
import com.team.project.user.repository.UserRepository;
import com.team.project.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    User testUser;
    UserMapper userMapper = new UserMapperImpl();
    @Mock
    UserMapper userMapperMock;
    @Mock
    UserRepository userRepositoryMock;
    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setup(){
        testUser = new User();
        testUser.setId(1);
        testUser.setName("Konrad");
        testUser.setLastName("Kowalski");
        testUser.setEmail("konkow@email.com");
        testUser.setPassword("123123");
        testUser.setRole(RoleName.ROLE_MENTOR);
        testUser.setFirm("Nowaki");
        testUser.setProjects(null);
        testUser.setAssignedTeams(null);
        testUser.setTeamAffilation(null);
    }

    @Test
    public void isCreatedCorrect(){
        when(userRepositoryMock.save(testUser)).thenReturn(testUser);
        when(userMapperMock.toDTO(any(User.class))).thenReturn(userMapper.toDTO(testUser));
        when(userMapperMock.fromDTO(any(UserDTO.class))).thenReturn(testUser);

        User savedUser = userMapper.fromDTO(userService.save(userMapper.toDTO(testUser)));

        assertEquals(testUser, savedUser);
    }

    @Test
    public void isCreatedOneTime(){
        when(userRepositoryMock.save(testUser)).thenReturn(testUser);
        when(userMapperMock.toDTO(any(User.class))).thenReturn(userMapper.toDTO(testUser));
        when(userMapperMock.fromDTO(any(UserDTO.class))).thenReturn(testUser);

        User savedUser = userMapper.fromDTO(userService.save(userMapper.toDTO(testUser)));

        verify(userRepositoryMock, times(1)).save(testUser);
        assertEquals(savedUser, testUser);
    }

    @Test
    public void isFoundByEmail(){
        String email = testUser.getEmail();

        when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.of(testUser));
        when(userMapperMock.toDTO(any(User.class))).thenReturn(userMapper.toDTO(testUser));

        User foundUser = userMapper.fromDTO(userService.getUserByEmail(email));

        assertEquals(testUser, foundUser);
    }

    @Test
    public void isNotFoundById(){
        String email = "aaa@bbb.com";

        when(userRepositoryMock.findByEmail(email)).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(email));
    }

    @Test
    public void isDeletedOnce(){
        Integer id = 1;

        doNothing().when(userRepositoryMock).deleteById(id);
        userService.deteleById(id);

        verify(userRepositoryMock, times(1)).deleteById(id);
    }

}
