package com.example.grpcClient.services;

import com.stockTrading.grpc.StockRequestDTO;
import com.stockTrading.grpc.StockResponseDTO;
import com.stockTrading.grpc.StockTradingServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {

    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub serviceBlockingStub;

    public StockResponseDTO getStockPrice(String stockSymbol){
        StockRequestDTO requestDTO = StockRequestDTO.newBuilder()
                .setStockSymbol(stockSymbol)
                .build();
        return serviceBlockingStub.getStockPrice(requestDTO);
    }
}
