package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * ErrorResponse
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("error")
	private ErrorData error = null;

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ErrorResponse errorResponse = (ErrorResponse) o;
		return Objects.equals(this.error, errorResponse.error);
	}

	public ErrorResponse error(ErrorData error) {
		this.error = error;
		return this;
	}

	/**
	 * Get error
	 * 
	 * @return error
	 */
	@NotNull

	public ErrorData getError() {
		return error;
	}

	@Override
	public int hashCode() {
		return Objects.hash(error);
	}

	public void setError(ErrorData error) {
		this.error = error;
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
		sb.append("class ErrorResponse {\n");

		sb.append("    error: ").append(toIndentedString(error)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
