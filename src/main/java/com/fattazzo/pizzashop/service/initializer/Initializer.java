package com.fattazzo.pizzashop.service.initializer;

public interface Initializer {

	/**
	 * Execure all initializer operations
	 */
	void init();

	/**
	 * Priority used for execute all Initializer in right order
	 *
	 * @return priority, less priority execute first
	 */
	int priority();
}
