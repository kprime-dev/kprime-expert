package it.unibz.cs.semint.kprime.expert.adapter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

class ExpertHttp {

     fun request(expertUrl: String): Pair<Int,String> {
        val client: HttpClient = HttpClient.newBuilder()
//                .version(Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
//                .proxy(ProxySelector.of(InetSocketAddress("proxy.example.com", 80)))
//                .authenticator(Authenticator.getDefault())
                .build()
        val request: HttpRequest = HttpRequest.newBuilder()
                .uri(URI.create(expertUrl))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json")
                .GET()
//                .POST(BodyPublishers.ofFile(Paths.get("file.json")))
                .build()
        val response: HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())
        return Pair(response.statusCode(),response.body())
    }

     fun put(expertUrl: String, payload: ExpertPayload): Pair<Int,String> {
        val payloadString = jacksonObjectMapper().writeValueAsString(payload)
        val client: HttpClient = HttpClient.newBuilder()
//                .version(Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
//                .proxy(ProxySelector.of(InetSocketAddress("proxy.example.com", 80)))
//                .authenticator(Authenticator.getDefault())
                .build()
        val request: HttpRequest = HttpRequest.newBuilder()
                .uri(URI.create(expertUrl))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(payloadString))
//                .POST(BodyPublishers.ofFile(Paths.get("file.json")))
                .build()
        val response: HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())
        return Pair(response.statusCode(),response.body())
    }

    fun post(expertUrl: String, payloadText: String): Pair<Int,String> {
        val client: HttpClient = HttpClient.newBuilder()
//                .version(Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
//                .proxy(ProxySelector.of(InetSocketAddress("proxy.example.com", 80)))
//                .authenticator(Authenticator.getDefault())
            .build()
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create(expertUrl))
            .timeout(Duration.ofSeconds(20))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(payloadText))
            .build()
        val response: HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())
        return Pair(response.statusCode(),response.body())
    }

}