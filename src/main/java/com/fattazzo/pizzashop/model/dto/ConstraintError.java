package com.fattazzo.pizzashop.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * ConstraintError
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class ConstraintError implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("fieldName")
	private String fieldName;

	@JsonProperty("constraintsNotRespected")
	private List<String> constraintsNotRespected = null;

	public ConstraintError addConstraintsNotRespectedItem(String constraintsNotRespectedItem) {
		if (this.constraintsNotRespected == null) {
			this.constraintsNotRespected = new ArrayList<>();
		}
		this.constraintsNotRespected.add(constraintsNotRespectedItem);
		return this;
	}

	public ConstraintError constraintsNotRespected(List<String> constraintsNotRespected) {
		this.constraintsNotRespected = constraintsNotRespected;
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
		final ConstraintError constraintError = (ConstraintError) o;
		return Objects.equals(this.fieldName, constraintError.fieldName)
				&& Objects.equals(this.constraintsNotRespected, constraintError.constraintsNotRespected);
	}

	public ConstraintError fieldName(String fieldName) {
		this.fieldName = fieldName;
		return this;
	}

	/**
	 * a list of constraints Not Respected
	 * 
	 * @return constraintsNotRespected
	 */

	public List<String> getConstraintsNotRespected() {
		return constraintsNotRespected;
	}

	/**
	 * the field name
	 * 
	 * @return fieldName
	 */

	public String getFieldName() {
		return fieldName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fieldName, constraintsNotRespected);
	}

	public void setConstraintsNotRespected(List<String> constraintsNotRespected) {
		this.constraintsNotRespected = constraintsNotRespected;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
		sb.append("class ConstraintError {\n");

		sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
		sb.append("    constraintsNotRespected: ").append(toIndentedString(constraintsNotRespected)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
