package ru.yandex.homework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.yandex.homework.requests.UpdateSalaryRequest;
import ru.yandex.homework.requests.WorkerRequest;
import ru.yandex.homework.responses.WorkerResponse;
import ru.yandex.homework.services.WorkerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class WorkerController {
    @Autowired
    @Qualifier("BaseWorkerService")
    private WorkerService workerService;

    @PostMapping("/workers")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole(ROLE_USER)")
    public WorkerResponse createAction(@Valid @RequestBody WorkerRequest workerRequest) {
        return workerService.createWorker(workerRequest);
    }

    @PatchMapping("/workers/{workerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAction(@Valid @PathVariable Long workerId, @RequestBody UpdateSalaryRequest salaryRequest) {
        workerService.updateSalaryToWorker(workerId, salaryRequest);
    }

    @DeleteMapping("/workers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAction() {
        workerService.deleteWorkers();
    }

    @GetMapping("/workers")
    public Page<WorkerResponse> getAction(Pageable pageable) {
        return workerService.getWorkers(pageable);
    }

    @GetMapping("/index")
    public String test() {
        return "test";
    }
}
