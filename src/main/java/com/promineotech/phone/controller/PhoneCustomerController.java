/**
 * 
 */
package com.promineotech.phone.controller;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.phone.Constants;
import com.promineotech.phone.entity.Customer;
import com.promineotech.phone.entity.Phone;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Blestro
 *
 */
@Validated
@RequestMapping("/customer")
@OpenAPIDefinition(info = @Info(title = "Phone Sales Services"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface PhoneCustomerController {

  // @formatter:off
  @Operation(
      summary = "Returns a Customer",
      description = "Returns a Customer given an optional email.",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "A Customer is returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Customer.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No phones were found with the input criteria.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "email", 
              allowEmptyValue = false, 
              required = false, 
              description = "The email (i.e., 'samirrivera@gmail.com'))")
      }
      )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  Customer fetchCustomer(@RequestParam(required = false) String email);
  
}

