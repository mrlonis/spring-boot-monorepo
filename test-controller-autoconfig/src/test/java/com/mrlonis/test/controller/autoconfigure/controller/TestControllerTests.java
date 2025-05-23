package com.mrlonis.test.controller.autoconfigure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestControllerTests {
    @Test
    void testController() {
        var controller = new TestController();
        assertEquals("Hello World!", controller.test());
    }
}
