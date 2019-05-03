package br.senaigo.mobile.service;

import org.springframework.stereotype.Service;

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.interfaces.GenericOperations;

@Service
public interface OrderService extends GenericOperations<Order> {

}
