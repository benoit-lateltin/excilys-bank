package com.excilys.ebi.bank.dao;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excilys.ebi.bank.model.Range;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatus;

public interface OperationDaoCustom {

	Page<Operation> findNonCardByAccountNumberAndDateRange(String accountNumber, Range<DateTime> range, Pageable pageable);

	BigDecimal sumResolvedAmountByCardAndDateRange(Card card, Range<DateTime> range);

	BigDecimal sumResolvedAmountByAccountNumberAndDateRangeAndSign(String accountNumber, Range<DateTime> range, OperationSign sign);

	Page<Operation> findCardOperationsByAccountNumberAndDateRangeAndStatus(String accountNumber, Range<DateTime> range, OperationStatus status, Pageable pageable);

	BigDecimal sumCardAmountByAccountNumberAndDateRangeAndSignAndStatus(String accountNumber, Range<DateTime> range, OperationSign sign, OperationStatus status);

	Page<Operation> findCardOperationsByCardNumberAndDateRangeAndStatus(String cardNumber, Range<DateTime> range, OperationStatus status, Pageable pageable);

	BigDecimal sumCardAmountByCardNumberAndDateRangeAndSignAndStatus(String cardNumber, Range<DateTime> range, OperationSign sign, OperationStatus status);

	Page<Operation> findTransferByAccountNumber(String accountNumber, Pageable pageable);
}
