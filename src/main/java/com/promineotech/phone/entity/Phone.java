/**
 * 
 */
package com.promineotech.phone.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Blestro
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
  
  private Long phonePk;
  private PhoneType phoneId;
  private String trimLevel;
  private Long storage;
  private String brand;
  private String color;
  private BigDecimal basePrice;
  
  @JsonIgnore
  public Long getModelPK() {
    return phonePk;
  }
  
}
