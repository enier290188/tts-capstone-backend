package com.tts.capstone.weather.repository;

import com.tts.capstone.weather.entity.ZipCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCodeRepository extends CrudRepository<ZipCode, Long> {
}
