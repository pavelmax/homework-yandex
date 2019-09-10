package ru.yandex.homework.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class UpdateSalaryRequest {
    @NotNull
    private BigDecimal newSalaryValue;
}
