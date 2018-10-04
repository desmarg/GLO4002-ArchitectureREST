package api.transaction;

import api.response.TransactionResponse;
import domain.Transaction;

public class TransactionToTransactionGetDtoAssembler {

    public static TransactionResponse createTransactionGetDto(Transaction transaction) {
        return TransactionDtoFactory.createTransactionDto(transaction);
    }
}
