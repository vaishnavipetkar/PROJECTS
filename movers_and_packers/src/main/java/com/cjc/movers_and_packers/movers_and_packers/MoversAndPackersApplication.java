package com.cjc.movers_and_packers.movers_and_packers;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MoversAndPackersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoversAndPackersApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}


}
