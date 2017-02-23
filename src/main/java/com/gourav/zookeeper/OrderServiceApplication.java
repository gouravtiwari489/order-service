package com.gourav.zookeeper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class OrderServiceApplication {
	
	@Autowired
	DiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	
	@RefreshScope
	@RestController
	class MessageRestController {

		@Value("${test}")
	    private String bye;
	    /*@Value("${doge.wow}")
	    private String wo;
	    
	    @Value("${doge.such}")
	    private String such;
	    
	    @Value("${doge.very}")
	    private String very;*/

	    @RequestMapping("/messages")
	    String getMessage() {
	        //return this.wo + this.such+this.very;
	    	return this.bye;
	    }
	}
	
	@RequestMapping("/getServiceUrl") 
	public List<ServiceInstance> serviceUrl() {
	    List<ServiceInstance> list = discoveryClient.getInstances("order");
	    if (list != null && list.size() > 0 ) {
	        return list;
	    }
	    return null;
	}
	
	@RequestMapping("/getCostingServiceUrl") 
	public List<ServiceInstance> costingServiceUrl() {
	    List<ServiceInstance> list = discoveryClient.getInstances("costing");
	    if (list != null && list.size() > 0 ) {
	        return list;
	    }
	    return null;
	}
}
