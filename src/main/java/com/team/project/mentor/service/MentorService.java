package com.team.project.mentor.service;

import com.team.project.mentor.dto.MentorDTO;
import com.team.project.mentor.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;


}
