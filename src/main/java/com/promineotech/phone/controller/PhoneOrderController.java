/**
 * 
 */
package com.promineotech.phone.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.phone.entity.Order;
import com.promineotech.phone.entity.OrderDeleteRequest;
import com.promineotech.phone.entity.OrderRequest;
import com.promineotech.phone.entity.OrderTypeRequest;


/**
 * @author Blestro
 *
 */

@RequestMapping("/orders")
@Validated
public interface PhoneOrderController {

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  Order getOrder(@RequestParam(required = true) Long orderNumber, @RequestParam(required = true) String customerId);
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order createOrder(@Valid @RequestBody OrderRequest orderRequest);
  
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  void updateOrderType(@Valid @RequestBody OrderTypeRequest orderTypeRequest);
  
  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  void deleteOrder(@Valid @RequestBody OrderDeleteRequest orderNumber);
  
}
