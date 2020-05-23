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
	public void sendOrderCreated(Integer orderId) {
		final OrderMessage message = new OrderMessage(OrderMessageEvent.CREATED, new OrderDetails().id(orderId));
		this.template.convertAndSend("/topic/orders", message);
	}
}
