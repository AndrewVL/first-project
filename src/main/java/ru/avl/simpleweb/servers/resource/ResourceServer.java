package ru.avl.simpleweb.servers.resource;

import resources.TestResource;

public interface ResourceServer {
    int getAge();

    String getName();

    void setResource(TestResource resource);
}
