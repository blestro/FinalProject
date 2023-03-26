/**
 * 
 */
package com.promineotech.phone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.phone.entity.Customer;
import com.promineotech.phone.service.PhoneCustomerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */
@Slf4j
@RestController
public class BasicPhoneCustomerController implements PhoneCustomerController {

  @Autowired
  private PhoneCustomerService phoneCustomerService;
  
  @Override
  public Customer fetchCustomer(String email) {
    log.info("Customer email={}", email);
    return phoneCustomerService.fetchCustomer(email);
  }

}
