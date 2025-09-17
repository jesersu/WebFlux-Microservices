package com.chapi.api;

import com.chapi.api.DTO.registry.UsuarioRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.ErrorResponse;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/usecase/path",
                    beanClass = Handler.class,
                    beanMethod = "listenGETUseCase",
                    operation = @Operation(summary = "Get use case", description = "Returns something")
            ),
            @RouterOperation(
                    path = "/api/usecase/otherpath",
                    beanClass = Handler.class,
                    beanMethod = "listenPOSTUseCase",
                    operation = @Operation(summary = "Post use case", description = "Processes something")
            ),
            @RouterOperation(
                    path = "/api/otherusercase/path",
                    beanClass = Handler.class,
                    beanMethod = "listenGETOtherUseCase",
                    operation = @Operation(summary = "Other GET use case", description = "Fetch other case")
            ),
            @RouterOperation(
                    path = "/api/v1/usuarios",
                    method = {org.springframework.web.bind.annotation.RequestMethod.POST},
                    beanClass = Handler.class,
                    beanMethod = "saveUser",
                    operation = @Operation(
                            operationId = "saveUser",
                            summary = "Save user",
                            description = "Registers a new user",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "User data to register",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = UsuarioRequestDTO.class),
                                            examples = {
                                                    @io.swagger.v3.oas.annotations.media.ExampleObject(
                                                            name = "User Example",
                                                            summary = "Example of a user registration request",
                                                            value = """
                                                            {
                                                                "nombre": "Juan",
                                                                "apellido": "Perez",
                                                                "email": "juan.perez@example.com",
                                                                "documentoIdentidad": "12345678",
                                                                "telefono": "+51 999888777",
                                                                "salarioBase": 2500
                                                            }
                                                            """
                                                    )
                                            }
                                    )
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "User successfully saved"),
                                    @ApiResponse(
                                            responseCode = "400",
                                            description = "Error en la petición",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = ErrorResponse.class),
                                                    examples = {
                                                            @ExampleObject(
                                                                    name = "Error",
                                                                    value = """
                                                                    {
                                                                      "code": "DATOS_INVALIDOS",
                                                                      "message": "El DNI es obligatorio"
                                                                    }
                                                                    """
                                                            )
                                                    }
                                            )
                                    ),
                                    @ApiResponse(
                                            responseCode = "500",
                                            description = "Error interno del servidor",
                                            content = @Content(
                                                    mediaType = "application/json",
                                                    schema = @Schema(implementation = ErrorResponse.class),
                                                    examples = {
                                                            @ExampleObject(
                                                                    name = "Error Interno",
                                                                    value = """
                                                                    {
                                                                      "code": "ERROR_INTERNO",
                                                                      "message": "Error del servidor"
                                                                    }
                                                                    """
                                                            )
                                                    }
                                            )
                                    )
                            }
                    )
            )




    })
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/usecase/path"), handler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase))
                .and(route(POST("/api/v1/usuarios"), handler::saveUser))
                .and(route(GET("/favicon.ico"), request -> ServerResponse.ok().build()));

    }
}
