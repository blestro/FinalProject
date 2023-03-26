package com.promineotech.phone.entity;

import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class OrderRequest {
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String customer;
  
  private List<@NotNull Phone> phones;
  
  @NotNull
  @Length(max = 30)
  @Pattern(regexp = "[\\w\\s]*")
  private String orderType;
  
//  @NotNull
//  private PhoneType phone;
//  
//  @NotNull
//  @Length(max = 30)
//  @Pattern(regexp = "[\\w\\s]*")
//  private String trim_level;
//  
//  @Positive
//  @Min(100)
//  @Max(2000)
//  private int storage;
//  
//  @NotNull
//  @Length(max = 30)
//  @Pattern(regexp = "[\\w\\s]*")
//  private String brand;
//  
//  @NotNull
//  @Length(max = 30)
//  @Pattern(regexp = "[\\w\\s]*")
//  private String color;

}
