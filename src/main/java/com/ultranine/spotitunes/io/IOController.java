package com.ultranine.spotitunes.io;

import com.ultranine.spotitunes.entities.Account;
import com.ultranine.spotitunes.entities.Song;
import com.ultranine.spotitunes.service.DatabaseAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class IOController {
    @Autowired
    private DatabaseAccessor accessor;

    @PostMapping("/addAccount")
    public AccountData addAccount(@RequestBody TransientAccount account) {
        return accessor.registerAccount(account);
    }

    @PostMapping("/attemptLogin")
    public AccountData attemptLogin(@RequestBody TransientAccount account) {
        return accessor.attemptLogin(account);
    }

    @GetMapping("/test/{testID}")
    public Song dbTest(@PathVariable String testID) {
        try {
            long id = Long.parseLong(testID);
            return accessor.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @GetMapping("audio/{songHandle}")
    public ResponseEntity<InputStreamResource> getSongAudio(@PathVariable String songHandle) {
        // TODO: Make this use the resource root
        File audioFile = new File("src/main/resources/audio/" + songHandle + ".wav");

        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(audioFile));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + audioFile.getName());
            headers.add(HttpHeaders.CONTENT_TYPE, "audio/wav");
            headers.setContentLength(Files.size(audioFile.toPath()));
            return ResponseEntity.ok().headers(headers).body(resource);
        }
        catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("getSongList")
    public List<Song> getSongList() {
        return accessor.getAllSongs();
    }

    @GetMapping("getUserList")
    public List<AccountData> getUserList() {
        return accessor.getAllUsers();
    }
}
