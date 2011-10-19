package com.excilys.ebi.bank.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.excilys.ebi.bank.model.YearMonth;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatus;

public interface OperationDaoCustom {

	Page<Operation> findNonCardByAccountIdAndYearMonth(Integer accountId, YearMonth yearMonth, Pageable pageable);

	BigDecimal sumResolvedAmountByCardAndYearMonth(Card card, YearMonth yearMonth);

	List<Operation> sumResolvedAmountByAccountIdAndYearMonthAndSignGroupByCard(Integer accountId, YearMonth yearMonth, OperationSign sign);

	BigDecimal sumResolvedAmountByAccountIdAndYearMonthAndSign(Integer accountId, YearMonth yearMonth, OperationSign sign);

	Page<Operation> findCardOperationsByAccountIdAndYearMonthAndStatus(Integer accountId, YearMonth yearMonth, OperationStatus status, Pageable pageable);

	BigDecimal sumCardAmountByAccountIdAndYearMonthAndSignAndStatus(Integer accountId, YearMonth yearMonth, OperationSign sign, OperationStatus status);

	Page<Operation> findCardOperationsByCardIdAndYearMonthAndStatus(Integer cardId, YearMonth yearMonth, OperationStatus status, Pageable pageable);

	BigDecimal sumCardAmountByCardIdAndYearMonthAndSignAndStatus(Integer cardId, YearMonth yearMonth, OperationSign sign, OperationStatus status);

	Page<Operation> findTransferByAccountId(Integer accountId, Pageable pageable);
}
