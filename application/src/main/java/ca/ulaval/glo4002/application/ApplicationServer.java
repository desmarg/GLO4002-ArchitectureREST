package ca.ulaval.glo4002.application;

import ca.ulaval.glo4002.stocks.StocksServer;
import trading.TradingServer;


public class ApplicationServer {
    public static void main(String[] args) throws InterruptedException {
        Thread stocks = new Thread(new StocksServer(args));
        Thread trading = new Thread(new TradingServer());

        stocks.start();
        stocks.join();

        trading.start();
        trading.join();
    }
}
