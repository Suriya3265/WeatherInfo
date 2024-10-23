package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Weatherinfo;

public interface WeatherDataRepository extends JpaRepository<Weatherinfo, Long>{

	Optional<Weatherinfo> findByPincodeAndDate(String pincode, LocalDate date);
}
