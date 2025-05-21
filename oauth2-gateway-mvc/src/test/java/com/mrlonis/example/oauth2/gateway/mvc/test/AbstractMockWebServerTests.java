package com.mrlonis.example.oauth2.gateway.mvc.test;

import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractMockWebServerTests {
    protected static MockWebServer mockWebServer;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeAll
    static void setupMockWebServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("MOCK_WEB_SERVER_PORT", () -> mockWebServer.getPort());
        String wellKnowOpenidConfigurationResponse = "{\"issuer\":\"http://localhost:" + mockWebServer.getPort()
                + "\",\"authorization_endpoint\":\"http://localhost:" + mockWebServer.getPort()
                + "/oauth2/authorize\",\"device_authorization_endpoint\":\"http://localhost:"
                + mockWebServer.getPort()
                + "/oauth2/device_authorization\",\"token_endpoint\":\"http://localhost:"
                + mockWebServer.getPort()
                + "/oauth2/token\",\"token_endpoint_auth_methods_supported\":[\"client_secret_basic\",\"client_secret_post\",\"client_secret_jwt\",\"private_key_jwt\",\"tls_client_auth\",\"self_signed_tls_client_auth\"],\"jwks_uri\":\"http://localhost:"
                + mockWebServer.getPort()
                + "/oauth2/jwks\",\"userinfo_endpoint\":\"http://localhost:"
                + mockWebServer.getPort()
                + "/userinfo\",\"end_session_endpoint\":\"http://localhost:"
                + mockWebServer.getPort()
                + "/connect/logout\",\"response_types_supported\":[\"code\"],\"grant_types_supported\":[\"authorization_code\",\"client_credentials\",\"refresh_token\",\"urn:ietf:params:oauth:grant-type:device_code\",\"urn:ietf:params:oauth:grant-type:token-exchange\"],\"revocation_endpoint\":\"http://localhost:"
                + mockWebServer.getPort()
                + "/oauth2/revoke\",\"revocation_endpoint_auth_methods_supported\":[\"client_secret_basic\",\"client_secret_post\",\"client_secret_jwt\",\"private_key_jwt\",\"tls_client_auth\",\"self_signed_tls_client_auth\"],\"introspection_endpoint\":\"http://localhost:"
                + mockWebServer.getPort()
                + "/oauth2/introspect\",\"introspection_endpoint_auth_methods_supported\":[\"client_secret_basic\",\"client_secret_post\",\"client_secret_jwt\",\"private_key_jwt\",\"tls_client_auth\",\"self_signed_tls_client_auth\"],\"code_challenge_methods_supported\":[\"S256\"],\"tls_client_certificate_bound_access_tokens\":true,\"subject_types_supported\":[\"public\"],\"id_token_signing_alg_values_supported\":[\"RS256\"],\"scopes_supported\":[\"openid\"]}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(wellKnowOpenidConfigurationResponse));
    }

    @AfterAll
    static void teardownMockWebServer() throws IOException {
        mockWebServer.shutdown();
    }
}
