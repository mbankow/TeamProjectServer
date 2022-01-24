package com.team.project.student.service;

import com.team.project.student.dto.StudentDTO;
import com.team.project.student.entity.Student;
import com.team.project.student.exception.StudentNotFoundException;
import com.team.project.student.mapper.StudentMapper;
import com.team.project.student.repository.StudentRepository;
import com.team.project.util.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentDTO> findPaginated(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return studentMapper.listToDTO((studentRepository.findAll(pageable)).getContent());
    }

    public HttpHeaders getNumberOfPagesAndElementsSum(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Headers.NUMBER_OF_PAGES, String.valueOf(studentRepository.findAll(pageable).getTotalPages()));
        headers.add(Headers.TOTAL_ITEMS, String.valueOf(studentRepository.findAll(pageable).getTotalElements()));
        return headers;
    }

    public StudentDTO save(StudentDTO studentDTO){
        Student student = studentMapper.fromDTO(studentDTO);
        return studentMapper.toDTO(studentRepository.save(student));
    }

    public StudentDTO update(StudentDTO studentDTO){
        return studentMapper.toDTO(studentRepository.save(studentMapper.fromDTO(studentDTO)));
    }

    public StudentDTO findById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student by ID" + id + " was not found"));
        return studentMapper.toDTO(student);
    }

    public void deteleById(Long id){
        studentRepository.deleteById(id);
    }
}
