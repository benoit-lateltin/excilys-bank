package com.excilys.ebi.bank.web.controller.account.operations;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.excilys.ebi.bank.model.entity.Operation;

@Component
public class OperationsTableConverter implements Converter<Page<Operation>, OperationsTable> {

	@Override
	public OperationsTable convert(Page<Operation> source) {

		/*
		 * For the brave souls who get this far: You are the chosen ones, the
		 * valiant knights of programming who toil away, without rest, fixing
		 * our most awful code. To you, true saviors, kings of men, I say this:
		 * never gonna give you up, never gonna let you down, never gonna run
		 * around and desert you. Never gonna make you cry, never gonna say
		 * goodbye. Never gonna tell a lie and hurt you.
		 */

		OperationsTable table = new OperationsTable();
		table.setNumber(source.getNumber());
		table.setNumberOfElements(source.getNumberOfElements());
		table.setSize(source.getSize());
		table.setTotalElements(source.getTotalElements());
		table.setTotalPages(source.getTotalPages());
		table.setHasContent(source.hasContent());
		table.setHasNextPage(source.hasNextPage());
		table.setHasPreviousPage(source.hasPreviousPage());
		table.setFirstPage(source.isFirstPage());
		table.setLastPage(source.isLastPage());
		table.setStartIndex(source.getTotalElements() > 0 ? source.getSize() * source.getNumber() + 1 : 0);
		table.setPageIndex(source.getTotalElements() > 0 ? source.getNumber() + 1 : 0);
		table.setEndIndex(source.getSize() * source.getNumber() + source.getNumberOfElements());

		for (Operation operation : source.getContent()) {

			OperationsLine line = new OperationsLine();
			line.setAmount(operation.getAmount());
			line.setDate(operation.getDate().toString("MM/dd/yyyy"));
			line.setName(operation.getName());
			line.setStatus(operation.getStatus().getId());
			table.getLines().add(line);
		}

		for (int i = 0; i < source.getSize() - source.getNumberOfElements(); i++) {
			table.getEmptyLines().add(StringUtils.EMPTY);
		}

		return table;
	}
}