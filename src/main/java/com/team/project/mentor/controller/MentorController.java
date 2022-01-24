package com.team.project.mentor.controller;

import com.team.project.mentor.dto.MentorDTO;
import com.team.project.mentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mentor")
public class MentorController {
    private final MentorService mentorService;
    @Value("${paging.size}")
    private int pageSize;

    @GetMapping()
    public ResponseEntity<List<MentorDTO>> findPaginated(@RequestParam(name = "page") int pageNumber) {
        List<MentorDTO> mentors = mentorService.findPaginated(pageNumber,pageSize);
        HttpHeaders headers = mentorService.getNumberOfPagesAndElementsSum(pageNumber,pageSize);
        return new ResponseEntity<>(mentors, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentorDTO> findById(@PathVariable(name = "id") Long id) {
        MentorDTO mentorDTO = mentorService.findById(id);
        return new ResponseEntity<>(mentorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MentorDTO> save(@RequestBody MentorDTO mentorDTO){
        MentorDTO mentorReturned = mentorService.save(mentorDTO);
        return new ResponseEntity<>(mentorReturned, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MentorDTO> update(@RequestBody MentorDTO mentorDTO){
        MentorDTO mentorReturned = mentorService.update(mentorDTO);
        return new ResponseEntity<>(mentorReturned, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MentorDTO> deleteById(@PathVariable(value = "id") Long id) {
        mentorService.deteleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
