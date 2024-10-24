package com.santanderdesafio.santander;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class SantanderApplicationTests {

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(8081); // Porta onde o WireMock vai interceptar
        wireMockServer.start();
        WireMock.configureFor("localhost", 8081);
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testInterceptRequest() {
        // Simula a interceptação de uma requisição GET
        stubFor(get(urlEqualTo("/cep-logs"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("Response from WireMock")));

        // Aqui você chamaria a API, por exemplo com RestTemplate ou HttpClient
        // E verificaria se a resposta foi interceptada corretamente

        // Verifica se houve uma requisição GET para "/api/test"
        verify(getRequestedFor(urlEqualTo("/cep-logs")));
    }
}
