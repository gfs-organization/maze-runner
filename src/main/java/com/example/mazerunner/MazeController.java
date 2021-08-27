package com.example.mazerunner;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> nextDirection(@RequestBody final List<String> directions, @PathVariable("level") final int mazeLevel) {
        String message = "";
        try {
            message = mazeRunner.runTheMaze(mazeLevel, directions);
        } catch (final IllegalArgumentException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(message, OK);
    }

}
