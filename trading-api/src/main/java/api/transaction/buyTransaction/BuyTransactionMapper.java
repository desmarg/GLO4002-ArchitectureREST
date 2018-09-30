package api.transaction.buyTransaction;

import application.StockService;
import domain.Credits;
import domain.DateTime;
import domain.stock.Stock;
import domain.transaction.Transaction;
import domain.transaction.TransactionId;
import domain.transaction.TransactionType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BuyTransactionMapper {
    BuyTransactionMapper INSTANCE = Mappers.getMapper(BuyTransactionMapper.class);

    GetBuyTransactionDto transactionToGetBuyTransactionDto(Transaction transaction);

    default Transaction buyTransactionDtoToTransaction(PostBuyTransactionDto postBuyTransactionDto) {
        DateTime date = postBuyTransactionDto.getDate();
        Stock stock = postBuyTransactionDto.getStock();
        Long quantity = postBuyTransactionDto.getQuantity();
        TransactionType transactionType = TransactionType.valueOf(postBuyTransactionDto.getType());
        TransactionId transactionId = new TransactionId();
        Credits stockPrice = StockService.getStockPrice(stock, date);

        return new Transaction(transactionType, quantity, date, stock, stockPrice, transactionId);
    }
}
