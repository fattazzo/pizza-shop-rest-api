package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * ErrorData
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class ErrorData implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("userTitle")
	private String userTitle;

	@JsonProperty("userMessage")
	private String userMessage;

	@JsonProperty("internal")
	private ErrorInternal internal = null;

	@JsonProperty("constraintErrors")
	private List<ConstraintError> constraintErrors = null;

	public ErrorData addConstraintErrorsItem(ConstraintError constraintErrorsItem) {
		if (this.constraintErrors == null) {
			this.constraintErrors = new ArrayList<>();
		}
		this.constraintErrors.add(constraintErrorsItem);
		return this;
	}

	public ErrorData constraintErrors(List<ConstraintError> constraintErrors) {
		this.constraintErrors = constraintErrors;
		return this;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ErrorData errorData = (ErrorData) o;
		return Objects.equals(this.userTitle, errorData.userTitle)
				&& Objects.equals(this.userMessage, errorData.userMessage)
				&& Objects.equals(this.internal, errorData.internal)
				&& Objects.equals(this.constraintErrors, errorData.constraintErrors);
	}

	/**
	 * if it is valued contains a list of constraints not respected in the current
	 * post/put request
	 *
	 * @return constraintErrors
	 */

	public List<ConstraintError> getConstraintErrors() {
		return constraintErrors;
	}

	/**
	 * Get internal
	 *
	 * @return internal
	 */

	public ErrorInternal getInternal() {
		return internal;
	}

	/**
	 * The error message to show (depending on the locale used when creating the
	 * session)
	 *
	 * @return userMessage
	 */
	@NotNull

	public String getUserMessage() {
		return userMessage;
	}

	/**
	 * The title for the popup error message
	 *
	 * @return userTitle
	 */
	@NotNull

	public String getUserTitle() {
		return userTitle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userTitle, userMessage, internal, constraintErrors);
	}

	public ErrorData internal(ErrorInternal internal) {
		this.internal = internal;
		return this;
	}

	public void setConstraintErrors(List<ConstraintError> constraintErrors) {
		this.constraintErrors = constraintErrors;
	}

	public void setInternal(ErrorInternal internal) {
		this.internal = internal;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
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
		sb.append("class ErrorData {\n");

		sb.append("    userTitle: ").append(toIndentedString(userTitle)).append("\n");
		sb.append("    userMessage: ").append(toIndentedString(userMessage)).append("\n");
		sb.append("    internal: ").append(toIndentedString(internal)).append("\n");
		sb.append("    constraintErrors: ").append(toIndentedString(constraintErrors)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	public ErrorData userMessage(String userMessage) {
		this.userMessage = userMessage;
		return this;
	}

	public ErrorData userTitle(String userTitle) {
		this.userTitle = userTitle;
		return this;
	}
}
