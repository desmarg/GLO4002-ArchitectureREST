package api.transaction;

import domain.transaction.Transaction;

public class TransactionToTransactionGetDtoAssembler {

    public static TransactionDto createTransactionGetDto(Transaction transaction) {
        return TransactionDtoFactory.createTransactionDto(transaction);
    }
}
