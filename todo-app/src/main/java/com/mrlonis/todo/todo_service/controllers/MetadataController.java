package com.mrlonis.todo.todo_service.controllers;

import com.mrlonis.todo.todo_service.services.MetadataService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metadata")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class MetadataController {
    private MetadataService metadataService;

    @GetMapping("/pis")
    public List<String> getPis() {
        return metadataService.getAllPis();
    }

    @GetMapping("/sprints")
    public List<Integer> getSprints() {
        return metadataService.getAllSprints();
    }
}
