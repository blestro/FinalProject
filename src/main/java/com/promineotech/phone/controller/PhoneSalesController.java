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
@RequestMapping("/phones")
@OpenAPIDefinition(info = @Info(title = "Phone Sales Services"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface PhoneSalesController {

  // @formatter:off
  @Operation(
      summary = "Returns a list of Phones",
      description = "Returns a list Jeeps given an optional brand.",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "A list of Phones is returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Phone.class))),
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
              name = "brand", 
              allowEmptyValue = false, 
              required = false, 
              description = "The brand name (i.e., 'APPLE'))")
      }
      )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Phone> fetchPhones(@RequestParam(required = false) String phone);
  
}

