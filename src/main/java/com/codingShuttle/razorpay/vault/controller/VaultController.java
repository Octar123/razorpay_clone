package com.codingShuttle.razorpay.vault.controller;

import com.codingShuttle.razorpay.vault.dto.request.TokenizeRequest;
import com.codingShuttle.razorpay.vault.dto.response.TokenizeResponse;
import com.codingShuttle.razorpay.vault.service.VaultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vault")
public class VaultController {

    private final VaultService vaultService;

    UUID merchantId = UUID.fromString("23e918a2-01b5-41d6-9a38-031906b0e108"); //TODO: replace it with merchant context

    @PostMapping("/tokenize")
    public ResponseEntity<TokenizeResponse> tokenize(@Valid @RequestBody TokenizeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaultService.tokenize(request, merchantId));
    }
}
