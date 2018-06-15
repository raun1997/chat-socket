package com.blogspot.sontx.chatsocket.server.model;

import com.blogspot.sontx.chatsocket.lib.bean.Request;
import com.blogspot.sontx.chatsocket.server.event.RequestReceivedEvent;
import com.blogspot.sontx.chatsocket.server.model.handler.RequestHandler;
import com.blogspot.sontx.chatsocket.server.model.handler.RequestHandlerFactory;
import lombok.extern.log4j.Log4j;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Routes incomming requests from clients to handlers.
 */
@Log4j
public class RequestRouter {
    private final RequestHandlerFactory requestHandlerFactory;

    public RequestRouter(RequestHandlerFactory requestHandlerFactory) {
        this.requestHandlerFactory = requestHandlerFactory;
    }

    public void start() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onRequestReceived(RequestReceivedEvent event) {
        Request request = event.getRequest();
        RequestHandler handler = requestHandlerFactory.create(request.getCode());
        try {
            handler.handle(event);
        } catch (Exception e) {
            log.error("Handle request", e);
        }
    }

    public void stop() {
        EventBus.getDefault().unregister(this);
    }
}
