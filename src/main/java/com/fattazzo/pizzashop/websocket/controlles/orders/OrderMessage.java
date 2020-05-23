package com.fattazzo.pizzashop.websocket.controlles.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderMessage {

	private final OrderMessageEvent event;

	private final Object content;
}
