package ru.yandex.homework.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.yandex.homework.domains.Salary;
import ru.yandex.homework.domains.Worker;
import ru.yandex.homework.repositories.SalaryRepository;
import ru.yandex.homework.repositories.WorkerRepository;
import ru.yandex.homework.requests.WorkerRequest;
import ru.yandex.homework.responses.WorkerResponse;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkerServiceImplTest {

    @Configuration
    public static class Config {
        @Bean
        public WorkerService baseWorkerService() {
            return new WorkerServiceImpl();
        }
    }


    @Autowired
    private WorkerService workerService;

    @MockBean
    private WorkerRepository workerRepository;

    @MockBean
    private SalaryRepository salaryRepository;

    @Test
    public void createWorker() {
        Worker worker = createWorkerForRepository(1l, "Alex", new BigDecimal(7000));
        when(workerRepository.save(Mockito.any(Worker.class))).thenReturn(worker);

        WorkerRequest request = createWorkRequest("Alex", new BigDecimal(7000));

        WorkerResponse response = workerService.createWorker(request);
        assertThat(response.getName(), is(request.getName()));
        assertThat(response.getSalaryValue(), is(request.getSalaryValue()));
        assertNotNull(response.getId());
    }

    private WorkerRequest createWorkRequest(String name, BigDecimal value) {
        WorkerRequest workerRequest = new WorkerRequest();
        workerRequest.setName("Alex");
        workerRequest.setSalaryValue(value);
        return workerRequest;
    }

    private Worker createWorkerForRepository(Long id, String name, BigDecimal value) {
        Worker worker = new Worker();
        Salary salary = new Salary();
        salary.setValue(value);
        worker.setId(id);
        worker.setSalary(salary);
        worker.setName(name);
        return worker;
    }

}