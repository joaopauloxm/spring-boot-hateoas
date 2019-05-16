package br.senaigo.mobile.controlller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.interfaces.GenericOperationsController;
import br.senaigo.mobile.service.OrderService;
/**
 * 
 * @author bruno
 *
 */
@RestController("/orders")
public class OrderController implements GenericOperationsController<Order> {
	
	/**
	 * @see http://appsdeveloperblog.com/spring-boot-logging-with-loggerfactory/
	 */
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	public OrderService orderService;
	
	@Override
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				 produces = {MediaType.APPLICATION_JSON_VALUE,
						 	 MediaType.APPLICATION_XML_VALUE,
						 	 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public Resource<Order> post(@RequestBody Order order) {
		orderService.post(order);
		
		Link link = linkTo(OrderController.class).slash(order.getId()).withSelfRel();
		Resource<Order> result = new Resource<Order>(order,link);
		return result;
	}

	@Override
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void put(@RequestBody Order entity) {
		// TODO Auto-generated method stub

	}

	@Override
	@DeleteMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Order entity) {
		// TODO Auto-generated method stub

	}

	@Override
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.APPLICATION_XML_VALUE,
							 MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resources<Order> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping(value="/{id}",
				consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
	 			produces = {MediaType.APPLICATION_JSON_VALUE,
	 						MediaType.APPLICATION_XML_VALUE,
	 						MediaTypes.HAL_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public Resource<Order> get(@PathVariable("id") Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PatchMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void patch(@RequestBody Order entity) {
		// TODO Auto-generated method stub

	}

}
