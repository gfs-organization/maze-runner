package com.example.mazerunner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mazerunner.navigation.MazeRunner;

@RestController
@RequestMapping("/mazerunner")
public final class MazeController {

    @Autowired
    private MazeRunner mazeRunner;

    @CrossOrigin(origins = "*")
    @PutMapping("/{level}")
    public String nextDirection(@RequestBody final List<String> directions, @PathVariable("level") final int mazeLevel) {
        String message = "";
        try {
            message = mazeRunner.runTheMaze(mazeLevel, directions);
        } catch (final IllegalArgumentException ex) {
            message = ex.getMessage();
        }
        return message;
    }

}
