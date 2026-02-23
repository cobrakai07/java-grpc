package com.example.grpcClient;

import com.example.grpcClient.services.StockClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication implements CommandLineRunner {
	private StockClientService stockClientService;
	public GrpcClientApplication(StockClientService stockClientService){
		this.stockClientService = stockClientService;
	}
	public static void main(String[] args) {
		SpringApplication.run(GrpcClientApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("GRPC client response"+stockClientService.getStockPrice("TCS"));
	}
}
