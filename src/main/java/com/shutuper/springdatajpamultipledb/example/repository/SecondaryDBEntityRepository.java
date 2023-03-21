package com.shutuper.springdatajpamultipledb.example.repository;

import com.shutuper.springdatajpamultipledb.config.db.filter.SecondaryRepository;
import com.shutuper.springdatajpamultipledb.example.repository.entity.SecondaryDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@SecondaryRepository
public interface SecondaryDBEntityRepository extends JpaRepository<SecondaryDBEntity, UUID> {
}
