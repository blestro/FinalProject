package com.promineotech.phone.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
  private Long customerPK;
  private String customerId;
  private String firstName;
  private String lastName;
  private String email;
  

//  public Long getCustomerPK() {
//    return customerPK;
//  }
}
