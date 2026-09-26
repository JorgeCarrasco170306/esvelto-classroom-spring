package com.esvelto.classroom.curso.service;

import com.esvelto.classroom.curso.DTO.CursoMapper;
import com.esvelto.classroom.curso.DTO.CursoRequestDTO;
import com.esvelto.classroom.curso.DTO.CursoResponseDTO;
import com.esvelto.classroom.curso.models.Curso;
import com.esvelto.classroom.curso.repository.CursoRepository;
import com.esvelto.classroom.institutions.models.Institution;
import com.esvelto.classroom.institutions.repository.InstitutionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final InstitutionRepository institutionRepository;
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    @Override
    public Page<CursoResponseDTO> findAll(Pageable pageable) {
        return cursoRepository.findAll(pageable).map(cursoMapper::toDto);
    }

    @Override
    public CursoResponseDTO findById(UUID uuid) {
        Curso curso = cursoRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));

        return cursoMapper.toDto(curso);
    }

    @Override
    public CursoResponseDTO create(CursoRequestDTO requestDto) {

        Curso curso = cursoMapper.toEntity(requestDto);

        List<Institution> institutions = institutionRepository.findAllById(requestDto.institutionsIds());

        if (institutions.size() != requestDto.institutionsIds().size()) {
            throw new EntityNotFoundException("Una o más instituciones no fueron encontradas");
        }

        institutions.forEach(curso::addInstitution);

        Curso saved = cursoRepository.save(curso);
        return cursoMapper.toDto(saved);

    }

    @Override
    public CursoResponseDTO update(UUID uuid, CursoRequestDTO requestDto) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {
        cursoRepository.deleteById(uuid);
    }
}
