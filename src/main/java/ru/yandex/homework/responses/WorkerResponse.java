package ru.yandex.homework.responses;

import lombok.Data;
import ru.yandex.homework.domains.Worker;

import java.math.BigDecimal;

@Data
public class WorkerResponse {
    private Long id;
    private String name;
    private BigDecimal salaryValue;

    public static WorkerResponse fromEntity(final Worker worker) {
        WorkerResponse response = new WorkerResponse();
        response.setId(worker.getId());
        response.setName(worker.getName());
        response.setSalaryValue(worker.getSalary() != null ? worker.getSalary().getValue() : new BigDecimal(0));
        return response;
    }
}
