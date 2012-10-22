//
//  ========================================================================
//  Copyright (c) 1995-2012 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.client;

import java.util.Collections;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.util.Attributes;

public class HttpConversation implements Attributes
{
    private final Map<String, Object> attributes = new ConcurrentHashMap<>();
    private final Deque<HttpExchange> exchanges = new ConcurrentLinkedDeque<>();
    private final HttpClient client;
    private final long id;
    private volatile Response.Listener listener;
    private volatile HttpExchange last;

    public HttpConversation(HttpClient client, long id)
    {
        this.client = client;
        this.id = id;
    }

    public long id()
    {
        return id;
    }

    public Deque<HttpExchange> exchanges()
    {
        return exchanges;
    }

    public Response.Listener listener()
    {
        return listener;
    }

    public void listener(Response.Listener listener)
    {
        this.listener = listener;
    }

    /**
     * @return the exchange that has been identified as the last of this conversation
     * @see #last(HttpExchange)
     */
    public HttpExchange last()
    {
        return last;
    }

    /**
     * Remembers the given {@code exchange} as the last of this conversation.
     *
     * @param exchange the exchange that is the last of this conversation
     * @see #last()
     */
    public void last(HttpExchange exchange)
    {
        if (last == null)
            last = exchange;
    }

    public void complete()
    {
        client.removeConversation(this);
    }

    @Override
    public Object getAttribute(String name)
    {
        return attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object attribute)
    {
        attributes.put(name, attribute);
    }

    @Override
    public void removeAttribute(String name)
    {
        attributes.remove(name);
    }

    @Override
    public Enumeration<String> getAttributeNames()
    {
        return Collections.enumeration(attributes.keySet());
    }

    @Override
    public void clearAttributes()
    {
        attributes.clear();
    }

    public boolean abort()
    {
        HttpExchange exchange = exchanges.peekLast();
        return exchange != null && exchange.abort();
    }

    @Override
    public String toString()
    {
        return String.format("%s[%d]", HttpConversation.class.getSimpleName(), id);
    }
}
