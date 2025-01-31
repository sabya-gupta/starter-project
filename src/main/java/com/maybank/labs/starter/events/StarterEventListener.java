package com.maybank.labs.starter.events;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.maybank.labs.starter.config.ApplicationContextProvider;

@Component
public class StarterEventListener implements ApplicationListener<StarterEvent> {

	@Override
	public void onApplicationEvent(final StarterEvent event) {

		if (event.getstarterEventType().equals(StarterEventTypesEnums.SYNC)) {
			handleEvent(event);
		} else {
			final Runnable r = () -> handleEvent(event);
			final Thread t = new Thread(r);
			t.start();
		}
	}

	private void handleEvent(final StarterEvent event) {
		final String className = event.getMessage();

		final ApplicationContext appCtx = ApplicationContextProvider.getApplicationContext();
		final StarterEventExecutorInterface dex = (StarterEventExecutorInterface) appCtx.getBean(className);
		dex.execute(event);

	}

}
