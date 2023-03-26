/**
 * 
 */
package com.promineotech.phone.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.promineotech.phone.entity.Customer;
import com.promineotech.phone.entity.Order;
import com.promineotech.phone.entity.OrderDeleteRequest;
import com.promineotech.phone.entity.OrderRequest;
import com.promineotech.phone.entity.OrderTypeRequest;
import com.promineotech.phone.entity.Phone;
import com.promineotech.phone.entity.PhoneType;

/**
 * @author Blestro
 *
 */
public interface PhoneOrderDao {

  /**
   * @param customer
   * @param phone
   * @return
   */
  Order saveOrder(Customer customer, List<Phone> phone, BigDecimal price, String orderType);

  /**
   * @param customer
   * @return
   */
  Optional<Customer> fetchCustomer(String customer);

  /**
   * @param phone
   * @param trim_level
   * @param storage
   * @param brand
   * @param color
   * @return
   */
  Optional<Phone> fetchPhone(PhoneType phone, String trim_level, Long storage, String brand, String color);

  /**
   * @param list
   * @return
   */
  List<Phone> fetchPhones(List<@NotNull Phone> list);

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
  Order getOrder(Long orderNumber, Customer customer);

}
