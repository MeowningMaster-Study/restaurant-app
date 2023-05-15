package com.example.backendjakartaee

import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.Produces
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerResponseContext
import jakarta.ws.rs.container.ContainerResponseFilter
import jakarta.ws.rs.core.Application
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.ext.Provider
import java.io.IOException

@ApplicationPath("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class MyApplication : Application()

@Provider
class CorsFilter : ContainerResponseFilter {
    @Throws(IOException::class)
    override fun filter(
        requestContext: ContainerRequestContext,
        responseContext: ContainerResponseContext
    ) {
        responseContext.headers.add(
            "Access-Control-Allow-Origin", "*"
        )
        responseContext.headers.add(
            "Access-Control-Allow-Credentials", "true"
        )
        responseContext.headers.add(
            "Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization"
        )
        responseContext.headers.add(
            "Access-Control-Allow-Methods",
            "GET, POST, PUT, DELETE, OPTIONS, HEAD"
        )
    }
}