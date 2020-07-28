package com.fattazzo.pizzashop.model.api;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fattazzo.pizzashop.model.api.DeliveryAddress;
import com.fattazzo.pizzashop.model.api.Group;
import com.fattazzo.pizzashop.model.api.SocialTypeEnum;
import com.fattazzo.pizzashop.model.api.User;
import com.fattazzo.pizzashop.model.api.UserStatus;
import com.fattazzo.pizzashop.model.api.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UserDetails
 */
@Validated
public class UserDetails extends User  {
  @JsonProperty("password")
  private String password = null;

  @JsonProperty("groups")
  @Valid
  private List<Group> groups = new ArrayList<>();

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
      this.deliveryAddresses = new ArrayList<>();
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
        Objects.equals(this.groups, userDetails.groups) &&
        Objects.equals(this.deliveryAddresses, userDetails.deliveryAddresses) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(password, groups, deliveryAddresses, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
