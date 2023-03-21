package com.shutuper.springdatajpamultipledb.example.repository;

import com.shutuper.springdatajpamultipledb.config.db.filter.SecondaryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@SecondaryRepository
public interface PrimaryDBEntityRepository extends JpaRepository<PrimaryDBEntityRepository, UUID> {

}
