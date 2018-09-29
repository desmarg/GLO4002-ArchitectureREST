package api.transaction.sellTransaction;

import domain.transaction.Transaction;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface SellTransactionMapper {
    SellTransactionMapper INSTANCE = Mappers.getMapper(SellTransactionMapper.class);

    @Mappings({
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorProfile", target = "investorProfile"),
            @Mapping(source = "credits", target = "credits")
    })
    Transaction SellTransactionDtoToTransaction(PostSellTransactionDto postSellTransactionDto);

    @Mappings({
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorProfile", target = "investorProfile"),
            @Mapping(source = "credits", target = "credits")
    })
    GetSellTransactionDto transactionToGetSellTransactionDto(Transaction transaction);
}
