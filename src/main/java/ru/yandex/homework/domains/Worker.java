package ru.yandex.homework.domains;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "workers")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(of = "name", callSuper = true)
public class Worker extends EntityTemplate {
    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;
    @OneToOne(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Salary salary;
}
