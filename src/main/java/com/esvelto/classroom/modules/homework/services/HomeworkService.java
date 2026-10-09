package com.esvelto.classroom.modules.homework.services;

import com.esvelto.classroom.modules.base.services.GenericService;
import com.esvelto.classroom.modules.homework.dtos.HomeworkRequest;
import com.esvelto.classroom.modules.homework.dtos.HomeworkResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface HomeworkService extends GenericService<HomeworkResponse, HomeworkRequest, UUID> {
}
