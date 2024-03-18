package com.Electronicdevices.Electronic_Category_Details;

import com.Electronicdevices.Electronic_Category_Details.Service.ElectronicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ElectronicCategoryDetailsApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(ElectronicCategoryDetailsApplication.class, args);
	}
     @Autowired
     public ElectronicService electronicService;
}
