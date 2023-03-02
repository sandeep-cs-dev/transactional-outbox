package com.outboxdemo.orderservice.repository;

import com.outboxdemo.orderservice.model.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, Integer> {
}
