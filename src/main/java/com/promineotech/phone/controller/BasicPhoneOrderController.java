/**
 * 
 */
package com.promineotech.phone.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.phone.entity.Order;
import com.promineotech.phone.entity.OrderDeleteRequest;
import com.promineotech.phone.entity.OrderRequest;
import com.promineotech.phone.entity.OrderTypeRequest;
import com.promineotech.phone.service.PhoneOrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */

@RestController
@Slf4j
public class BasicPhoneOrderController implements PhoneOrderController {

  @Autowired
  private PhoneOrderService phoneOrderService;
  
  @Override
  public Order createOrder(@Valid OrderRequest orderRequest) {
    log.info("Order={}", orderRequest);
    return phoneOrderService.createOrder(orderRequest);
  }

  @Override
  public void updateOrderType(@Valid OrderTypeRequest orderTypeRequest) {
    // TODO Auto-generated method stub
    phoneOrderService.updateOrderType(orderTypeRequest);
  }

  @Override
  public void deleteOrder(@Valid OrderDeleteRequest orderNumber) {
    phoneOrderService.deleteOrder(orderNumber);
    
  }

  @Override
  public Order getOrder(Long orderNumber, String customerId) {
    // TODO Auto-generated method stub
    return phoneOrderService.getOrder(orderNumber, customerId);
  }
  

}
