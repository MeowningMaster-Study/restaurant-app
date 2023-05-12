package com.example.backendjakartaee

import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.Application
import jakarta.ws.rs.core.MediaType

@ApplicationPath("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class MainApplication : Application()