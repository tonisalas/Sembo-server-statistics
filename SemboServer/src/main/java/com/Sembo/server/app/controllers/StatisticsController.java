package com.Sembo.server.app.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sembo.server.app.data.StatisticsData;
import com.Sembo.server.app.services.SemboExternalService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
		
	@Autowired
	SemboExternalService sembo;
	
	@GetMapping({"","/"})
	public List<StatisticsData> read() throws InterruptedException, ExecutionException {
					
		return 	sembo.getSemboHotels();
		
	}
	
	
		
}
