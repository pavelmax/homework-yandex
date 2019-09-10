package ru.yandex.homework.domains;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "salaries")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(of = "value", callSuper = true)
public class Salary extends EntityTemplate {
    @Column(name = "value", nullable = false)
    private BigDecimal value;
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false, unique = true)
    private Worker worker;
}
