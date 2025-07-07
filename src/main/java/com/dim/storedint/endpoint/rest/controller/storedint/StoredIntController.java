package com.dim.storedint.endpoint.rest.controller.storedint;


import com.dim.storedint.PojaGenerated;
import com.dim.storedint.service.StoredIntService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PojaGenerated
@RestController
@AllArgsConstructor
public class StoredIntController {
    StoredIntService storedIntService;

    @GetMapping("/stored-int")
    public String store_int() {
        return storedIntService.storeInt();
    }
}
