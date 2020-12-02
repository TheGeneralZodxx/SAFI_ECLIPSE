package com.safi.managedBeans;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

public class ViewExpiredExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public ViewExpiredExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() throws FacesException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        for (Iterator<ExceptionQueuedEvent> iter = getUnhandledExceptionQueuedEvents().iterator(); iter.hasNext();) {
            Throwable exception = iter.next().getContext().getException();

            if (exception instanceof ViewExpiredException) {
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "index");
                facesContext.renderResponse();
                iter.remove();
            }
             if (exception instanceof java.lang.UnsupportedOperationException) {
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "index");
                facesContext.renderResponse();
                iter.remove();
            }
        }

        getWrapped().handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

}

