package api.transaction;

import domain.transaction.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

//    @Mappings({
//            @Mapping(source = "accountNumber", target = "accountNumber"),
//            @Mapping(source = "credits", target = "credits")
//    })
//    TransactionGetDto transactionToGetBuyTransactionDto(Transaction transaction);

}
