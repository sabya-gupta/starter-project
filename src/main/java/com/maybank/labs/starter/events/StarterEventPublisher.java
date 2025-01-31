package com.maybank.labs.starter.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class StarterEventPublisher {

	private ApplicationEventPublisher applicationEventPublisher;

	public StarterEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		super();
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void publishEvent(final StarterEvent starterEvent) {
		applicationEventPublisher.publishEvent(starterEvent);
	}
}
