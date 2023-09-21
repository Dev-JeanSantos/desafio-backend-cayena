package com.cayena.backendkotlin

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class BackendKotlinApplication

fun main(args: Array<String>) {
	runApplication<BackendKotlinApplication>(*args)
}

@Bean
fun configurer(
	@Value("\${spring.application.name}") applicationName: String?
): MeterRegistryCustomizer<MeterRegistry>? {
	return MeterRegistryCustomizer { registry: MeterRegistry ->
		registry.config().commonTags("application", applicationName)
	}
}