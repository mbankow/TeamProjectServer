package com.team.project.user.service;

import com.team.project.user.dto.UserDTO;
import com.team.project.user.entity.User;
import com.team.project.user.exception.UserNotFoundException;
import com.team.project.user.mapper.UserMapper;
import com.team.project.user.repository.UserRepository;
import com.team.project.util.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> findPaginated(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return userMapper.listToDTO((userRepository.findAll(pageable)).getContent());
    }

    public HttpHeaders getNumberOfPagesAndElementsSum(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Headers.NUMBER_OF_PAGES, String.valueOf(userRepository.findAll(pageable).getTotalPages()));
        headers.add(Headers.TOTAL_ITEMS, String.valueOf(userRepository.findAll(pageable).getTotalElements()));
        return headers;
    }

    public UserDTO save(UserDTO userDTO){
        User user = userMapper.fromDTO(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO update(UserDTO userDTO){
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userDTO)));
    }

    public UserDTO findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User by ID" + id + " was not found"));
        return userMapper.toDTO(user);
    }

    public void deteleById(Long id){
        userRepository.deleteById(id);
    }
}
