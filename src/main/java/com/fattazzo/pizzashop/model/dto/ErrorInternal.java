package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Available only in the debug environment
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class ErrorInternal implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("exception")
	private String exception;

	@JsonProperty("stack")
	private String stack;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ErrorInternal errorInternal = (ErrorInternal) o;
		return Objects.equals(this.exception, errorInternal.exception)
				&& Objects.equals(this.stack, errorInternal.stack);
	}

	public ErrorInternal exception(String exception) {
		this.exception = exception;
		return this;
	}

	/**
	 * Get exception
	 * 
	 * @return exception
	 */
	@NotNull

	public String getException() {
		return exception;
	}

	/**
	 * Get stack
	 * 
	 * @return stack
	 */

	public String getStack() {
		return stack;
	}

	@Override
	public int hashCode() {
		return Objects.hash(exception, stack);
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

	public ErrorInternal stack(String stack) {
		this.stack = stack;
		return this;
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ErrorInternal {\n");

		sb.append("    exception: ").append(toIndentedString(exception)).append("\n");
		sb.append("    stack: ").append(toIndentedString(stack)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
