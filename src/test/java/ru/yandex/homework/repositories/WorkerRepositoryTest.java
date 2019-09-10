package ru.yandex.homework.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.homework.domains.Salary;
import ru.yandex.homework.domains.Worker;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkerRepositoryTest {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @Transactional
    public void saveWorkerInDb() {
        Worker petr = createWorker("Petr", new BigDecimal(15000));
        workerRepository.save(petr);
        Worker loaded = testEntityManager.find(Worker.class, petr.getId());
        assertThat(loaded.getId(), is(petr.getId()));
        assertThat(loaded.getName(), is(petr.getName()));
    }

    private Worker createWorker(String name, BigDecimal salaryValue) {
        Worker worker = new Worker();
        worker.setName(name);
        Salary salary = new Salary();
        salary.setValue(salaryValue);
        salary.setWorker(worker);
        worker.setSalary(salary);
        return worker;
    }
}