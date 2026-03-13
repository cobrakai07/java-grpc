package org.example.unarygrpcserver.services;

import com.stockTrading.grpc.StockRequestDTO;
import com.stockTrading.grpc.StockResponseDTO;
import com.stockTrading.grpc.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.example.unarygrpcserver.models.Stock;
import org.example.unarygrpcserver.repos.StockRepository;
import org.springframework.grpc.server.service.GrpcService;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@GrpcService
@AllArgsConstructor
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {
    private final StockRepository stockRepository;
    @Override
    public void getStockPrice(StockRequestDTO requestDTO, StreamObserver<StockResponseDTO> responseObserver) {
        String stockSymbol = requestDTO.getStockSymbol();
        Stock stock = stockRepository.findByStockSymbol(stockSymbol);

        StockResponseDTO stockResponseDTO = StockResponseDTO.newBuilder()
                .setPrice(stock.getPrice())
                .setStockSymbol(stock.getStockSymbol())
                .setTimestamps(stock.getLastUpdated().toString())
                .build();

        responseObserver.onNext(stockResponseDTO);
        responseObserver.onCompleted();
    }

    @Override
    public void subscribeStockPrice(StockRequestDTO request, StreamObserver<StockResponseDTO> responseObserver) {
        String symbol = request.getStockSymbol();
        try {
            for (int i = 0; i < 10; i++) {
                StockResponseDTO stockResponseDTO = StockResponseDTO.newBuilder()
                        .setPrice(new Random().nextDouble(200))
                        .setStockSymbol(symbol)
                        .setTimestamps(Instant.now().toString())
                        .build();
                responseObserver.onNext(stockResponseDTO);
                TimeUnit.SECONDS.sleep(1);
            }
            responseObserver.onCompleted();
        } catch (InterruptedException e) {
        responseObserver.onError(e);
    }
    }
}
