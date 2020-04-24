package com.fattazzo.pizzashop.config.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.type.descriptor.sql.BinaryTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgreSQLDialectCustom extends PostgreSQL82Dialect {

	protected static final Logger logger = LoggerFactory.getLogger(PostgreSQLDialectCustom.class);

	public PostgreSQLDialectCustom() {
		registerColumnType(Types.BLOB, "bytea");
		logger.info("Custom Postgre SQL Dialect loaded");
	}

	@Override
	public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
		if (sqlTypeDescriptor.getSqlType() == java.sql.Types.BLOB) {
			return BinaryTypeDescriptor.INSTANCE;
		}
		return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
	}
}