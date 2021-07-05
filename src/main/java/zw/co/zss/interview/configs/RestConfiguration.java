package zw.co.zss.interview.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class RestConfiguration {

    private final HttpClientConfig httpClientConfig;
    @Value("${sms.endpoint.user}")
    private String smsApiUser;
    @Value("${sms.endpoint.password}")
    private String smsPassword;
    public static final String SMS_REST_TEMPLATE = "SMS_REST_TEMPLATE";

    @Bean
    @Primary
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, zw.co.zss.interview.configs.LoggingRequestInterceptor loggingInterceptor) {
        final SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(1 * 1000);
        clientHttpRequestFactory.setReadTimeout(1 * 1000);
        final ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(clientHttpRequestFactory);
        final RestTemplate restTemplate = restTemplateBuilder
                .interceptors(Collections.singletonList(loggingInterceptor))
                .build();
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }


    @Bean(name = SMS_REST_TEMPLATE)
    public RestTemplate smsRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .basicAuthentication(smsApiUser, smsPassword)
                .setConnectTimeout(httpClientConfig.connectTimeout)
                .setReadTimeout(httpClientConfig.getReadTimeout())
                .build();

    }
}


