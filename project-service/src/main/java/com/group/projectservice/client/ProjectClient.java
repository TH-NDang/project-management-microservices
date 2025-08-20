package com.group.projectservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group.projectservice.external.User;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface ProjectClient {

    @GetMapping("/api/users/{id}")
    User getUserById(@PathVariable Long id);
}
