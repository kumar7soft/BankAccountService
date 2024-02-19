package com.thrivefuse.bas.repository;

import com.thrivefuse.bas.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
