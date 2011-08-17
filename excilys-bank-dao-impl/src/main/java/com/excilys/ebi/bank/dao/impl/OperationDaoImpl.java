package com.excilys.ebi.bank.dao.impl;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.excilys.ebi.bank.dao.OperationDaoCustom;
import com.excilys.ebi.bank.model.Range;
import com.excilys.ebi.bank.model.entity.Card;
import com.excilys.ebi.bank.model.entity.Operation;
import com.excilys.ebi.bank.model.entity.QAccount;
import com.excilys.ebi.bank.model.entity.QCard;
import com.excilys.ebi.bank.model.entity.QOperation;
import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatus;
import com.excilys.ebi.bank.model.entity.ref.OperationType;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Repository
public class OperationDaoImpl extends QueryDslRepositorySupport implements OperationDaoCustom {

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	protected JPQLQuery applyPagination(JPQLQuery query, Pageable pageable) {
		return query.offset(pageable.getOffset()).limit(pageable.getPageSize());
	}

	@Override
	public Page<Operation> findNonCardByAccountNumberAndDateRange(String accountNumber, Range<DateTime> range, Pageable pageable) {

		QOperation operation = QOperation.operation;
		QAccount account = QAccount.account;

		BooleanExpression predicate = operation.type.id.ne(OperationType.CARD).and(account.number.eq(accountNumber));
		predicate = addOperationDateRangeExpression(predicate, operation, range);

		JPQLQuery countQuery = from(operation).innerJoin(operation.account, account).where(predicate);
		JPQLQuery query = applyPagination(from(operation).innerJoin(operation.account, account).where(predicate).orderBy(operation.date.desc()), pageable);

		return new PageImpl<Operation>(query.list(operation), pageable, countQuery.count());
	}

	@Override
	public BigDecimal sumResolvedAmountByCardAndDateRange(Card card, Range<DateTime> range) {

		QOperation operation = QOperation.operation;

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(operation.status.id.eq(OperationStatus.RESOLVED)).and(operation.card.eq(card));
		predicate = addOperationDateRangeExpression(predicate, operation, range);

		return from(operation).where(predicate).uniqueResult(operation.amount.sum());
	}

	private Predicate buildOperationSignPredicate(OperationSign sign) {
		QOperation operation = QOperation.operation;
		return sign == OperationSign.CREDIT ? operation.amount.gt(OperationSign.ZERO) : operation.amount.lt(OperationSign.ZERO);
	}

	@Override
	public BigDecimal sumResolvedAmountByAccountNumberAndDateRangeAndSign(String accountNumber, Range<DateTime> range, OperationSign sign) {

		QOperation operation = QOperation.operation;
		QAccount account = QAccount.account;

		BooleanExpression predicate = account.number.eq(accountNumber).and(buildOperationSignPredicate(sign)).and(operation.status.id.eq(OperationStatus.RESOLVED));
		predicate = addOperationDateRangeExpression(predicate, operation, range);

		return from(operation).innerJoin(operation.account, account).where(predicate).uniqueResult(operation.amount.sum());
	}

	@Override
	public Page<Operation> findCardOperationsByAccountNumberAndDateRangeAndStatus(String accountNumber, Range<DateTime> range, OperationStatus status, Pageable pageable) {

		QOperation operation = QOperation.operation;
		QAccount account = QAccount.account;

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(account.number.eq(accountNumber)).and(operation.status.id.eq(status));
		predicate = addOperationDateRangeExpression(predicate, operation, range);

		JPQLQuery countQuery = from(operation).innerJoin(operation.account, account).where(predicate);
		JPQLQuery query = applyPagination(from(operation).innerJoin(operation.account, account).where(predicate).orderBy(operation.date.desc()), pageable);

		return new PageImpl<Operation>(query.list(operation), pageable, countQuery.count());
	}

	@Override
	public BigDecimal sumCardAmountByAccountNumberAndDateRangeAndSignAndStatus(String accountNumber, Range<DateTime> range, OperationSign sign, OperationStatus status) {

		QOperation operation = QOperation.operation;
		QAccount account = QAccount.account;

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(account.number.eq(accountNumber)).and(buildOperationSignPredicate(sign))
				.and(operation.status.id.eq(status));
		predicate = addOperationDateRangeExpression(predicate, operation, range);

		return from(operation).innerJoin(operation.account, account).where(predicate).uniqueResult(operation.amount.sum());
	}

	@Override
	public Page<Operation> findCardOperationsByCardNumberAndDateRangeAndStatus(String cardNumber, Range<DateTime> range, OperationStatus status, Pageable pageable) {

		QOperation operation = QOperation.operation;
		QCard card = QCard.card;

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(card.number.eq(cardNumber)).and(operation.status.id.eq(status));
		predicate = addOperationDateRangeExpression(predicate, operation, range);

		JPQLQuery countQuery = from(operation).innerJoin(operation.card, card).where(predicate);
		JPQLQuery query = applyPagination(from(operation).innerJoin(operation.card, card).where(predicate).orderBy(operation.date.desc()), pageable);

		return new PageImpl<Operation>(query.list(operation), pageable, countQuery.count());
	}

	@Override
	public BigDecimal sumCardAmountByCardNumberAndDateRangeAndSignAndStatus(String cardNumber, Range<DateTime> range, OperationSign sign, OperationStatus status) {

		QOperation operation = QOperation.operation;
		QCard card = QCard.card;

		BooleanExpression predicate = operation.type.id.eq(OperationType.CARD).and(card.number.eq(cardNumber)).and(buildOperationSignPredicate(sign))
				.and(operation.status.id.eq(status));
		predicate = addOperationDateRangeExpression(predicate, operation, range);

		return from(operation).innerJoin(operation.card, card).where(predicate).uniqueResult(operation.amount.sum());
	}

	@Override
	public Page<Operation> findTransferByAccountNumber(String accountNumber, Pageable pageable) {

		QOperation operation = QOperation.operation;
		QAccount account = QAccount.account;

		BooleanExpression predicate = operation.type.id.eq(OperationType.TRANSFER).and(account.number.eq(accountNumber));

		JPQLQuery countQuery = from(operation).innerJoin(operation.account, account).where(predicate);
		JPQLQuery query = applyPagination(from(operation).innerJoin(operation.account, account).where(predicate).orderBy(operation.date.desc()), pageable);

		return new PageImpl<Operation>(query.list(operation), pageable, countQuery.count());
	}

	private BooleanExpression addOperationDateRangeExpression(BooleanExpression predicate, QOperation operation, Range<DateTime> range) {
		return range != null ? predicate.and(operation.date.between(range.getFrom(), range.getTo())) : predicate;
	}
}
