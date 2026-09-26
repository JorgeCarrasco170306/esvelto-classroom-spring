package com.esvelto.classroom.institutions.service;

import com.esvelto.classroom.auth.models.User;
import com.esvelto.classroom.auth.repository.UserRepository;
import com.esvelto.classroom.files.storage.StorageService;
import com.esvelto.classroom.institutions.DTO.InstitutionMapper;
import com.esvelto.classroom.institutions.DTO.InstitutionRequestDTO;
import com.esvelto.classroom.institutions.DTO.InstitutionResponseDTO;
import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.institutions.repository.InstitutionRepository;
import com.esvelto.classroom.teacher.models.Teacher;
import com.esvelto.classroom.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InstitutionServiceImpl implements InstitutionService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final StorageService storageService;
    private final InstitutionMapper institutionMapper;

    @Override
    public Page<InstitutionResponseDTO> findAll(Pageable pageable) {
        return institutionRepository.findAll(pageable).map(institutionMapper::toDto);
    }

    @Override
    public InstitutionResponseDTO findById(UUID uuid) {
        var institution = institutionRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "institution not found"));

        return institutionMapper.toDto(institution);
    }

    @Override
    public InstitutionResponseDTO create(InstitutionRequestDTO requestDto) {
        return null;
    }

    @Override
    public InstitutionResponseDTO create(User user, InstitutionRequestDTO requestDto, MultipartFile image) {

        Teacher teacher = teacherRepository.findByUser_Id(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "teacher not found"));

        String imageUrl = storageService.uploadFile(image, "esvelto/institutions");

        Institution institution = new Institution();
        institution.setTeacher(teacher);
        institution.setImageUrl(imageUrl);
        institution.setName(requestDto.name());

        institutionRepository.save(institution);
        return institutionMapper.toDto(institution);

    }

    @Override
    public InstitutionResponseDTO update(UUID uuid, InstitutionRequestDTO requestDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {
        institutionRepository.deleteById(uuid);
    }
}
