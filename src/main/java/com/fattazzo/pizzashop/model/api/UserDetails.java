package com.fattazzo.pizzashop.model.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * UserDetails
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-26T14:55:34.957Z[GMT]")
public class UserDetails extends User  {
  @JsonProperty("password")
  private String password = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("groups")
  @Valid
  private List<Group> groups = new ArrayList<Group>();

  @JsonProperty("deliveryAddresses")
  @Valid
  private List<DeliveryAddress> deliveryAddresses = null;

  public UserDetails password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Login password
   * @return password
  **/
  @ApiModelProperty(value = "Login password")
  
    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserDetails firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * First name
   * @return firstName
  **/
  @ApiModelProperty(value = "First name")
  
    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UserDetails lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Last name
   * @return lastName
  **/
  @ApiModelProperty(value = "Last name")
  
    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserDetails groups(List<Group> groups) {
    this.groups = groups;
    return this;
  }

  public UserDetails addGroupsItem(Group groupsItem) {
    this.groups.add(groupsItem);
    return this;
  }

  /**
   * List of assigned `Group`
   * @return groups
  **/
  @ApiModelProperty(required = true, value = "List of assigned `Group`")
      @NotNull
    @Valid
    public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  public UserDetails deliveryAddresses(List<DeliveryAddress> deliveryAddresses) {
    this.deliveryAddresses = deliveryAddresses;
    return this;
  }

  public UserDetails addDeliveryAddressesItem(DeliveryAddress deliveryAddressesItem) {
    if (this.deliveryAddresses == null) {
      this.deliveryAddresses = new ArrayList<DeliveryAddress>();
    }
    this.deliveryAddresses.add(deliveryAddressesItem);
    return this;
  }

  /**
   * List of delivery address
   * @return deliveryAddresses
  **/
  @ApiModelProperty(value = "List of delivery address")
      @Valid
    public List<DeliveryAddress> getDeliveryAddresses() {
    return deliveryAddresses;
  }

  public void setDeliveryAddresses(List<DeliveryAddress> deliveryAddresses) {
    this.deliveryAddresses = deliveryAddresses;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDetails userDetails = (UserDetails) o;
    return Objects.equals(this.password, userDetails.password) &&
        Objects.equals(this.firstName, userDetails.firstName) &&
        Objects.equals(this.lastName, userDetails.lastName) &&
        Objects.equals(this.groups, userDetails.groups) &&
        Objects.equals(this.deliveryAddresses, userDetails.deliveryAddresses) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, firstName, lastName, groups, deliveryAddresses, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    groups: ").append(toIndentedString(groups)).append("\n");
    sb.append("    deliveryAddresses: ").append(toIndentedString(deliveryAddresses)).append("\n");
    sb.append("}");
    return sb.toString();
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
}
