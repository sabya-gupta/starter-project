package com.maybank.labs.starter.events;

import org.springframework.context.ApplicationEvent;

public class StarterEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	private StarterEventTypesEnums starterEventType;;
	private Object eventData;
	private String message;

	public StarterEvent(final Object source, final StarterEventTypesEnums starterEventType, final Object eventData,
			final String message) {
		super(source);
		this.starterEventType = starterEventType;
		this.eventData = eventData;
		this.message = message;
	}

	public StarterEventTypesEnums getstarterEventType() {
		return starterEventType;
	}

	public void setStarterEventType(final StarterEventTypesEnums starterEventType) {
		this.starterEventType = starterEventType;
	}

	public Object getEventData() {
		return eventData;
	}

	public void setEventData(final Object eventData) {
		this.eventData = eventData;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

}
