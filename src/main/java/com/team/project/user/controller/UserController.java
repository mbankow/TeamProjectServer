package com.team.project.user.controller;

import com.team.project.user.dto.UserDTO;
import com.team.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Value("${paging.size}")
    private int pageSize;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findPaginated(@RequestParam(name = "page") int pageNumber) {
        List<UserDTO> users = userService.findPaginated(pageNumber,pageSize);
        HttpHeaders headers = userService.getNumberOfPagesAndElementsSum(pageNumber,pageSize);
        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") Long id) {
        UserDTO userDTO = userService.findById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        UserDTO userReturned = userService.save(userDTO);
        return new ResponseEntity<>(userReturned, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO){
        UserDTO userReturned = userService.update(userDTO);
        return new ResponseEntity<>(userReturned, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteById(@PathVariable(value = "id") Long id) {
        userService.deteleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
