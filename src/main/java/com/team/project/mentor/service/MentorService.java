package com.team.project.mentor.service;

import com.team.project.mentor.dto.MentorDTO;
import com.team.project.mentor.entity.Mentor;
import com.team.project.mentor.exception.MentorNotFoundException;
import com.team.project.mentor.mapper.MentorMapper;
import com.team.project.mentor.repository.MentorRepository;
import com.team.project.util.Headers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;
    private final MentorMapper mentorMapper;

    public List<MentorDTO> findPaginated(int pageNumber, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return mentorMapper.listToDTO((mentorRepository.findAll(pageable)).getContent());
    }

    public HttpHeaders getNumberOfPagesAndElementsSum(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.add(Headers.NUMBER_OF_PAGES, String.valueOf(mentorRepository.findAll(pageable).getTotalPages()));
        headers.add(Headers.TOTAL_ITEMS, String.valueOf(mentorRepository.findAll(pageable).getTotalElements()));
        return headers;
    }

    public MentorDTO save(MentorDTO mentorDTO){
        Mentor mentor = mentorMapper.fromDTO(mentorDTO);
        return mentorMapper.toDTO(mentorRepository.save(mentor));
    }

    public MentorDTO update(MentorDTO mentorDTO){
        return mentorMapper.toDTO(mentorRepository.save(mentorMapper.fromDTO(mentorDTO)));
    }

    public MentorDTO findById(Long id){
        Mentor mentor = mentorRepository.findById(id).orElseThrow(() -> new MentorNotFoundException("Mentor by ID" + id + " was not found"));
        return mentorMapper.toDTO(mentor);
    }

    public void deteleById(Long id){
        mentorRepository.deleteById(id);
    }
}
