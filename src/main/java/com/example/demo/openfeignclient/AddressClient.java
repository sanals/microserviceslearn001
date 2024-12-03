package com.example.demo.openfeignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.view.AddressView;

@FeignClient(
		name = "microserives-selenium-address",  //give same name as in properties file "spring.application.name" or same as one registered in eureka
		path = "/address-app/api" //give the value of "server.servlet.context-path"
		)
public interface AddressClient {

    @GetMapping("/address/{empId}")
    public ResponseEntity<AddressView> getEmployeeByEmployeeId(@PathVariable Integer empId);
}
