package api.transaction;

import domain.Transaction;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface GetBuyTransactionMapper {
    GetBuyTransactionMapper INSTANCE = Mappers.getMapper(GetBuyTransactionMapper.class);

    @Mappings({
            @Mapping(source = "accountNumber", target = "accountNumber"),
            @Mapping(source = "investorId", target = "investorId"),
            @Mapping(source = "investorProfile", target = "investorProfile"),
            @Mapping(source = "credits", target = "credits")
    })
    Transaction BuyTransactionDtoToTransaction(BuyTransactionDto buyTransactionDto);
}
