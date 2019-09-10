package ru.yandex.homework.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.homework.domains.Salary;
import ru.yandex.homework.domains.Worker;
import ru.yandex.homework.exceptions.NotFountException;
import ru.yandex.homework.repositories.SalaryRepository;
import ru.yandex.homework.repositories.WorkerRepository;
import ru.yandex.homework.requests.UpdateSalaryRequest;
import ru.yandex.homework.requests.WorkerRequest;
import ru.yandex.homework.responses.WorkerResponse;

@Service(value = "BaseWorkerService")
@Slf4j
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    @Transactional
    public WorkerResponse createWorker(WorkerRequest workerRequest) {
        Worker worker = builderWorker(workerRequest);
        Salary salary = builderSalary(workerRequest);
        salary.setWorker(worker);
        worker.setSalary(salary);
        return WorkerResponse.fromEntity(workerRepository.save(worker));
    }

    @Override
    @Transactional
    public void updateSalaryToWorker(Long workerId, UpdateSalaryRequest salaryRequest) {
        int count = salaryRepository.updateSalaryByWorkerId(salaryRequest.getNewSalaryValue(), workerId);
        if (count < 1) {
            throw new NotFountException("not found worker");
        }
    }

    @Override
    @Transactional
    public void deleteWorkers() {
        salaryRepository.deleteAllInBatch();
        workerRepository.deleteAllInBatch();
    }

    @Override
    public Page<WorkerResponse> getWorkers(Pageable pageable) {
        return workerRepository.findAll(pageable).map(WorkerResponse::fromEntity);
    }

    private Salary builderSalary(WorkerRequest workerRequest) {
        Salary salary = new Salary();
        salary.setValue(workerRequest.getSalaryValue());
        return salary;
    }

    private Worker builderWorker(WorkerRequest workerRequest) {
        Worker worker = new Worker();
        worker.setName(workerRequest.getName());
        return worker;
    }
}
