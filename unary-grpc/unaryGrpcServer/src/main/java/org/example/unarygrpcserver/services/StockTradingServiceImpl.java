package org.example.unarygrpcserver.services;

import com.stockTrading.grpc.StockRequestDTO;
import com.stockTrading.grpc.StockResponseDTO;
import com.stockTrading.grpc.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.example.unarygrpcserver.models.Stock;
import org.example.unarygrpcserver.repos.StockRepository;
import org.springframework.grpc.server.service.GrpcService;

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
}
