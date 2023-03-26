/**
 * 
 */
package com.promineotech.phone.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.phone.dao.PhoneOrderDao;
import com.promineotech.phone.entity.Customer;
import com.promineotech.phone.entity.Order;
import com.promineotech.phone.entity.OrderDeleteRequest;
import com.promineotech.phone.entity.OrderRequest;
import com.promineotech.phone.entity.OrderTypeRequest;
import com.promineotech.phone.entity.Phone;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Blestro
 *
 */

@Service
@Slf4j
public class DefaultPhoneOrderService implements PhoneOrderService {

  @Autowired
  private PhoneOrderDao phoneOrderDao;
  
  @Override
  public Order createOrder(@Valid OrderRequest orderRequest) {
    Customer customer = getCustomer(orderRequest);
    
    List<Phone> phones = getPhones(orderRequest);
        
    BigDecimal price = new BigDecimal("0");
    
    String orderType = orderRequest.getOrderType();
    
    for (Phone phone: phones) {
      // Big Decimals are immutable and need to have a new var
      price = price.add(phone.getBasePrice());
    }
    
    log.info("Service Customer={}, Phone={}, Price={}, Order Type={}", customer, phones, price, orderType);
    //return phoneOrderDao.saveOrder(customer, phone);
    return phoneOrderDao.saveOrder(customer, phones, price, orderType);
  }
  
  @Override
  public void updateOrderType(@Valid OrderTypeRequest orderTypeRequest) {
    // TODO Auto-generated method stub
    phoneOrderDao.updateOrderType(orderTypeRequest);
  }
  

  private List<Phone> getPhones(OrderRequest orderRequest) {
    return phoneOrderDao.fetchPhones(orderRequest.getPhones());
  }

  /**
   * 
   * @param orderRequest
   * @return
   */
  private Customer getCustomer(OrderRequest orderRequest) {
    return phoneOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID="
            + orderRequest.getCustomer() + " was not found"));
  }

  @Override
  public void deleteOrder(@Valid OrderDeleteRequest orderNumber) {
    phoneOrderDao.deleteOrder(orderNumber);
  }

  @Override
  public Order getOrder(Long orderNumber, String customerId) {
    Customer customer = phoneOrderDao.fetchCustomer(customerId)
        .orElseThrow(() -> new NoSuchElementException("Customer with ID="
            + customerId + " was not found"));
    
    Order order = phoneOrderDao.getOrder(orderNumber); 
    
    List<Phone> phones = phoneOrderDao.getPhones(orderNumber);
    
    log.info("Phones for Order={}", phones);
    
    Order full_order = phoneOrderDao.buildOrder(customer, order, phones);
    
    return full_order;
  }

  
}
