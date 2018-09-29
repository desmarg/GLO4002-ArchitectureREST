package api.transaction.buyTransaction;

import application.StockService;
import domain.*;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface BuyTransactionMapper {
    BuyTransactionMapper INSTANCE = Mappers.getMapper(BuyTransactionMapper.class);

    default Transaction buyTransactionDtoToTransaction(PostBuyTransactionDto postBuyTransactionDto) {
        DateTime date = postBuyTransactionDto.getDate();
        Stock stock = postBuyTransactionDto.getStock();
        Long quantity = postBuyTransactionDto.getQuantity();
        TransactionType transactionType = postBuyTransactionDto.getTransactionTypeType();
        Credits stockPrice = StockService.getStockPrice(stock, date);


        return new Transaction(transactionType, quantity, date, stock, stockPrice);
    }

    @Mappings({
            @Mapping(source = "transactionId", target = "transactionId"),
            @Mapping(source = "transactionType", target = "transactionType"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "stock", target = "stock"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "purchasedPrice", target = "price"),
    })
    GetBuyTransactionDto transactionToGetBuyTransactionDto(Transaction transaction);
}
