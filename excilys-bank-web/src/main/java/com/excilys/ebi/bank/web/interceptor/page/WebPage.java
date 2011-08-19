package com.excilys.ebi.bank.web.interceptor.page;

public enum WebPage {

	ROOT(null, null),

	BANK(ROOT, "page.bank.title"),

	ACCOUNTS(BANK, null),

	ACCOUNT(ACCOUNTS, null),

	ACCOUNT_OPERATIONS(ACCOUNT, "page.bank.account.operations.title"),

	CARDS(ACCOUNT, null),

	CARD_OPERATIONS(CARDS, "page.bank.account.cards.operations.title"),

	TRANSFERS(ACCOUNT, null),

	TRANSFER_OPERATIONS(TRANSFERS, "page.bank.account.transfers.operations.title"),

	TRANSFER_PERFORM(TRANSFERS, "page.bank.account.transfers.perform.title"),

	ADMIN(ROOT, "page.admin.title");

	public static final String PAGE_MODEL = "page";

	private final WebPage parent;

	private final String titleKey;

	private WebPage(WebPage parent, String titleKey) {
		this.parent = parent;
		this.titleKey = titleKey;
	}

	public boolean isDisplayable() {
		return titleKey != null;
	}

	public boolean hasAncestor(WebPage page) {

		if (this == page) {
			return true;

		} else if (parent == null) {
			return false;

		} else {
			return parent.hasAncestor(page);
		}
	}
}
