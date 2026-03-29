package com.mrlonis.test.controller.autoconfigure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExampleControllerTests {
    @Test
    void testController() {
        var controller = new ExampleController();
        assertEquals("Hello World!", controller.test());
    }
}
