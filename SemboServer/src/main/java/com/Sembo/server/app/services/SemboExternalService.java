package com.Sembo.server.app.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Sembo.server.app.data.StatisticsData;
import com.Sembo.server.app.entities.Hotel;
import com.Sembo.server.app.utils.Utils;

@Service
public class SemboExternalService {
	
	private final String passwordHeader = "tonisalas@gmail.com";
	private final String url = "https://developers.sembo.com";
	private final String header = "X-API-Key";
	private final String requestUrl = "/sembo/hotels-test/countries/{0}/hotels";
	
	private final String[] countries = new String[]{"es","it","fr"};
		
			
	public List<StatisticsData> getSemboHotels() throws InterruptedException, ExecutionException{
			
		List<StatisticsData> lStatistics = new ArrayList<StatisticsData>();
		
		ConcurrentHashMap<String, CompletableFuture<List<Hotel>>> hHotels = new ConcurrentHashMap<String, CompletableFuture<List<Hotel>>>();
		
		for (String country : countries) {
			hHotels.put(country, getSemboHotelsByCountry(country));
		}
		
		for (String key: hHotels.keySet()) {
			StatisticsData statistics = new StatisticsData();
			
			Integer totalScore = 0;
			
			List<Hotel>	lHotels = hHotels.get(key).get();
			
			lHotels.sort(Comparator.comparingInt(Hotel::getScore).reversed());
			
			Integer i = 0;
			
			for (Hotel hotel : lHotels) {
											
				totalScore += hotel.getScore();
				
				if (i < 3) {
					statistics.getTop().add(hotel.getName());
				}
				
				i++;
			}
			
			statistics.setCountry(key);
			statistics.setAverage(totalScore / Double.valueOf(lHotels.size()));
			
			lStatistics.add(statistics);
		}
		
		
		
		return lStatistics; 
	}
	
	@Async
	private CompletableFuture<List<Hotel>> getSemboHotelsByCountry(String country) throws InterruptedException, ExecutionException {
		boolean succes = false;		
		CompletableFuture<List<Hotel>> lHotel = null;
		while (!succes) {
			try {
				
				RestTemplate restTemplate = new RestTemplate();
				
				HttpHeaders headers = new HttpHeaders();
				headers.add(header, Utils.encryptSHA1(passwordHeader));
				
				ResponseEntity<Hotel[]> response = restTemplate.exchange(url + requestUrl.replace("{0}", country), HttpMethod.GET, new HttpEntity<Object>(headers), Hotel[].class);
				
				lHotel = CompletableFuture.completedFuture(Arrays.asList(response.getBody()));
				succes = true;
			} catch (Exception ex) {
				succes = false;
			}
			
		}
		
		return lHotel;
	}
	
		
}
