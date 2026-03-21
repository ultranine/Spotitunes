package com.ultranine.spotitunes.io;

import com.ultranine.spotitunes.entities.Test;
import com.ultranine.spotitunes.service.DatabaseAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class IOController {
    @Autowired
    private DatabaseAccessor accessor;

    @GetMapping("/test/{testID}")
    public Test dbTest(@PathVariable String testID) {
        try {
            long id = Long.parseLong(testID);
            return accessor.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
