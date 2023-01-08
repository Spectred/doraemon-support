package pers.spectred.translate.observer.event;

import org.springframework.context.ApplicationEvent;

public class TranslateSuccessEvent extends ApplicationEvent {

    public TranslateSuccessEvent(Object source) {
        super(source);
    }
}
