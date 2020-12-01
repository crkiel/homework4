package com.homework4.homework4.Models;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepo extends CrudRepository<Weather,String> {
}
