package ru.yandex.homework.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.yandex.homework.requests.UpdateSalaryRequest;
import ru.yandex.homework.requests.WorkerRequest;
import ru.yandex.homework.responses.WorkerResponse;

public interface WorkerService {
    WorkerResponse createWorker(WorkerRequest workerRequest);

    void updateSalaryToWorker(Long workerId, UpdateSalaryRequest salaryRequest);

    void deleteWorkers();

    Page<WorkerResponse> getWorkers(Pageable pageable);
}
