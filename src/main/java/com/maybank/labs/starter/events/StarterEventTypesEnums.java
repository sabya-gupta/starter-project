package com.maybank.labs.starter.events;

public enum StarterEventTypesEnums {
	SYNC ("SYNC"), ASYNC ("ASYNC");
	
	private String eventType;

	StarterEventTypesEnums(String eventType) {
		this.setEventType(eventType);
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
