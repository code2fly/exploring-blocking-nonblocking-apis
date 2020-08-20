package com.mparch.learning.reactiveprogramming;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
public class ReactiveProgrammingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveProgrammingApplication.class, args);
	}


	@Configuration
	static class WebConfig implements WebMvcConfigurer {

		HttpMessageConverter messagePackMessageConverter() {
			MediaType msgpackMediaType = new MediaType("application", "x-msgpack");
			return new AbstractJackson2HttpMessageConverter(new ObjectMapper(new MessagePackFactory()), msgpackMediaType) {
			};
		}

		@Override
		public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
			 converters.add(messagePackMessageConverter());
		}
	}

}
