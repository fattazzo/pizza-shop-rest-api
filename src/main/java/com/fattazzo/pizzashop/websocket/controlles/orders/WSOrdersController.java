package com.fattazzo.pizzashop.websocket.controlles.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fattazzo.pizzashop.model.api.OrderDetails;

@Controller
@CrossOrigin(origins = "*")
public class WSOrdersController {

	private final SimpMessagingTemplate template;

	@Autowired
	public WSOrdersController(SimpMessagingTemplate template) {
		super();
		this.template = template;
	}

	@MessageMapping("/orders")
	// @SendTo("/topic/orders")
	public void send(OrderMessageEvent event, Integer orderId) {
		final OrderMessage message = new OrderMessage(event, new OrderDetails().id(orderId));
		this.template.convertAndSend("/topic/orders", message);
	}

	public void sendOrderCreated(Integer orderId) {
		send(OrderMessageEvent.CREATED, orderId);
	}

	public void sendOrderUpdated(Integer orderId) {
		send(OrderMessageEvent.UPDATED, orderId);
	}
}
