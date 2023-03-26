/**
 * 
 */
package com.promineotech.phone.service;

import javax.validation.Valid;
import com.promineotech.phone.entity.Order;
import com.promineotech.phone.entity.OrderDeleteRequest;
import com.promineotech.phone.entity.OrderRequest;
import com.promineotech.phone.entity.OrderTypeRequest;

/**
 * @author Blestro
 *
 */
public interface PhoneOrderService {

  /**
   * @param orderRequest
   * @return
   */
  Order createOrder(@Valid OrderRequest orderRequest);

  /**
   * @param orderTypeRequest
   * @return
   */
  void updateOrderType(@Valid OrderTypeRequest orderTypeRequest);

  /**
   * @param orderNumber
   */
  void deleteOrder(@Valid OrderDeleteRequest orderNumber);

  /**
   * @param orderNumber
   * @return
   */
  Order getOrder(Long orderNumber, String customerId);

}
