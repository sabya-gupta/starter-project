package com.maybank.labs.starter.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("StarterExampleEventExecutor")
public class StarterEventExecutorSampleImpl implements StarterEventExecutorInterface {

	private final Logger logger = LogManager.getLogger(this.getClass());


	@Override
	public void execute(final StarterEvent evt) {
		final String str = (String) evt.getEventData();
		try {
			Thread.sleep(5000);
		} catch (final InterruptedException e) {
			logger.error(e.toString());
		}
		logger.info(str);
	}

}
