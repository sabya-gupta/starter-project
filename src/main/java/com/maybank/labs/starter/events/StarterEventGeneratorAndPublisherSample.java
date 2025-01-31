package com.maybank.labs.starter.events;

import org.springframework.stereotype.Service;

@Service
public class StarterEventGeneratorAndPublisherSample {

	private StarterEventPublisher starterEventPublisher;

	public StarterEventGeneratorAndPublisherSample(StarterEventPublisher starterEventPublisher) {
		super();
		this.starterEventPublisher = starterEventPublisher;
	}

	public void generateAsyncEvent() {
		final String eventData = "ASYNC EVENT";
		final StarterEvent de = new StarterEvent(getClass(), StarterEventTypesEnums.ASYNC, eventData,
				"ExampleEventExecutor");
		starterEventPublisher.publishEvent(de);
	}

	public void generateSyncEvent() {
		final String eventData = "SYNC EVENT";
		final StarterEvent de = new StarterEvent(getClass(), StarterEventTypesEnums.SYNC, eventData, "ExampleEventExecutor");
		starterEventPublisher.publishEvent(de);
	}

}
