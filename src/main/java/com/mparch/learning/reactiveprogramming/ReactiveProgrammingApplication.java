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


	/*
	Best serialization format -
	If you are looking for a battle-tested, strongly typed serialization format, then Protocol Buffers is a great choice.
	 If you also need a variety of built-in RPC mechanisms, then Thrift is worth investigating.
	 If you are already exchanging or working with JSON, then MessagePack is almost a drop-in optimization.
	  And finally, if you like the strongly typed aspects, but want the flexibility of easy interoperability with dynamic languages, then Avro may be your best bet at this point in time
	 */
	@Configuration
	static class WebConfig implements WebMvcConfigurer {

		HttpMessageConverter messagePackMessageConverter() {
			MediaType msgPackMediaType = new MediaType("application", "x-msgpack");
			return new AbstractJackson2HttpMessageConverter(new ObjectMapper(new MessagePackFactory()), msgPackMediaType) {
			};
		}

		@Override
		public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
			 converters.add(messagePackMessageConverter());
		}
	}

}
