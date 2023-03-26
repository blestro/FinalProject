/**
 * 
 */
package com.promineotech.phone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.phone.entity.Phone;
import com.promineotech.phone.service.PhoneSalesService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */

@RestController
@Slf4j
public class BasicPhoneSalesController implements PhoneSalesController {
  
  @Autowired
  private PhoneSalesService phoneSalesService;
  
  @Override
  public List<Phone> fetchPhones(String phone) {
    log.info("Phone={}", phone);
    return phoneSalesService.fetchPhones(phone);
  }

}
