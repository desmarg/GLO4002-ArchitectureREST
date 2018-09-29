package api.transaction;

import domain.Transaction;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface BuyTransactionMapper {
    BuyTransactionMapper INSTANCE = Mappers.getMapper(BuyTransactionMapper.class);

    @Mappings({
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "stock", target = "stock"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "transactionType", target = "transactionType")
    })
    Transaction BuyTransactionDtoToTransaction(PostBuyTransactionDto postBuyTransactionDto);

    @Mappings({
            @Mapping(source = "transactionId", target = "transactionId"),
            @Mapping(source = "transactionType", target = "transactionType"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "stock", target = "stock"),
            @Mapping(source = "quantity", target = "quantity"),
            @Mapping(source = "purchasedPrice", target = "quantity"),
    })
    GetBuyTransactionDto transactionToGetBuyTransactionDto(Transaction transaction);
}
