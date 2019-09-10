package ru.yandex.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.yandex.homework.domains.Salary;

import java.math.BigDecimal;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query("update Salary s set s.value = :newPrice where s.worker.id = :workerId")
    @Modifying
    int updateSalaryByWorkerId(@Param(value = "newPrice") BigDecimal newPrice, @Param(value = "workerId") Long workerId);
}
