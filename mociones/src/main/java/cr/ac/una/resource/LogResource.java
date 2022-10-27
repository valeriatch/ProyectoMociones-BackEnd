package cr.ac.una.resource;

import cr.ac.una.entity.Log;
import cr.ac.una.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/Log")
public class LogResource {

    private final LogRepository LR;

    @GetMapping
    public ResponseEntity<Collection<Log>> listarLogs() {
        Collection<Log> logs = new ArrayList<>();
        LR.findAll().forEach(logs::add);
        return ResponseEntity.ok().body(logs);
    }
}
