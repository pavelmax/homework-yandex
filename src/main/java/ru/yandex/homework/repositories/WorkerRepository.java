package ru.yandex.homework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.homework.domains.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}