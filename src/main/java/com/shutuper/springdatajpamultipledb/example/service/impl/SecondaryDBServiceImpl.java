package com.shutuper.springdatajpamultipledb.example.service.impl;

import com.shutuper.springdatajpamultipledb.example.repository.SecondaryDBEntityRepository;
import com.shutuper.springdatajpamultipledb.example.service.SecondaryDBService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class SecondaryDBServiceImpl implements SecondaryDBService {

	SecondaryDBEntityRepository secondaryDBEntityRepository;

	@Override
	@Transactional("secondaryDataBaseTransactionManager")
	public void deleteById(UUID id) {
		secondaryDBEntityRepository.deleteById(id);
	}

}
