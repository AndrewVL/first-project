package ru.avl.simpleweb.servers.resource;

import resources.TestResource;

public class ResourceServerImpl implements ResourceServer {
    private TestResource resource = null;

    @Override
    public int getAge() {
        return resource.getAge();
    }

    @Override
    public String getName() {
        return resource.getName();
    }

    @Override
    public void setResource(TestResource resource) {
        this.resource = resource;
    }
}
