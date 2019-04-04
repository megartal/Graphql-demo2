package au.com.brolly.graphqldemo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class StockPriceUpdate {
    private final String stockCode;
    private final String dateTime;
    private final BigDecimal stockPrice;
    private final BigDecimal stockPriceChange;

    public StockPriceUpdate(String stockCode, LocalDateTime dateTime, BigDecimal stockPrice, BigDecimal stockPriceChange) {
        this.stockCode = stockCode;
        this.dateTime = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        this.stockPrice = stockPrice;
        this.stockPriceChange = stockPriceChange;
    }
}
