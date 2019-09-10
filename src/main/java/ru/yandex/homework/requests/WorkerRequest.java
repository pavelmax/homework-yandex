package ru.yandex.homework.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class WorkerRequest {
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private BigDecimal salaryValue;
}
