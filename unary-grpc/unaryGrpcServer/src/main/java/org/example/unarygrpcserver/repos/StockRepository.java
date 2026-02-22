package org.example.unarygrpcserver.repos;

import org.example.unarygrpcserver.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByStockSymbol(String stockSymbol);
}


//INSERT INTO STOCKS (LAST_UPDATED, PRICE, STOCK_SYMBOL) VALUES
//('2026-02-22 09:15:30', 1825.50, 'TCS'),
//        ('2026-02-22 09:45:10', 3420.75, 'INFY'),
//        ('2026-02-22 10:05:22', 2550.00, 'HDFCBANK'),
//        ('2026-02-22 10:30:45', 1498.25, 'ICICIBANK'),
//        ('2026-02-22 11:10:05', 987.60, 'WIPRO'),
//        ('2026-02-22 11:40:18', 3125.90, 'RELIANCE'),
//        ('2026-02-22 12:05:55', 720.45, 'SBIN'),
//        ('2026-02-22 12:30:33', 410.80, 'ITC'),
//        ('2026-02-22 13:15:47', 2750.10, 'LT'),
//        ('2026-02-22 14:00:00', 530.25, 'AXISBANK');