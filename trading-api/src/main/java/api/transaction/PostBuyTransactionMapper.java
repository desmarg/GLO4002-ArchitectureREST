package api.transaction;

import domain.Transaction;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface PostBuyTransactionMapper {
    PostBuyTransactionMapper INSTANCE = Mappers.getMapper(PostBuyTransactionMapper.class);

    @Mappings({
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorProfile", target = "investorProfile"),
            @Mapping(source = "credits", target = "credits")
    })
    PostBuyTransactionDto transactionToPostBuyTransactionDto(Transaction transaction);
}
