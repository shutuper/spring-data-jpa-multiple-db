package com.shutuper.springdatajpamultipledb.example.service.impl;

import com.shutuper.springdatajpamultipledb.example.repository.PrimaryDBEntityRepository;
import com.shutuper.springdatajpamultipledb.example.service.PrimaryDBService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class PrimaryDBServiceImpl implements PrimaryDBService {

	PrimaryDBEntityRepository primaryDBEntityRepository;

	@Override
	@Transactional("primaryDataBaseTransactionManager")
	public void deleteById(UUID id) {
		primaryDBEntityRepository.deleteById(id);
	}

}
