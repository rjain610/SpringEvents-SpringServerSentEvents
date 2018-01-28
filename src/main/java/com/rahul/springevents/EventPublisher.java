package com.rahul.springevents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * Custom Listener for registration
 * 
 * @author rahul
 *
 */
@Component
public class EventPublisher implements ApplicationListener<RegisterEvent> {

	private static List<SseEmitter> ssES = new ArrayList();


	/**
	 * Called when the <b>applicationEventPublisher</b> publishes the
	 * <b>RegisterEvent</b>
	 */
	@Override
	public void onApplicationEvent(RegisterEvent arg0) {

		System.out.println("Published" + arg0);
		for (SseEmitter emitter : ssES) {
			try {
				emitter.send(arg0.toString());
				emitter.complete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Contains the event data wrapped inside the SseEmitter
	 * 
	 * @return
	 */
	public SseEmitter getSSE() {
		SseEmitter emitter = new SseEmitter();
		ssES.add(emitter);
		
		return emitter;
	}

}
