package br.com.daily.backend.modules.goals;


import br.com.daily.backend.modules.goals.domain.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {

    @Autowired
    GoalRepository repository;

    @GetMapping
    public List<Goal> fetchAllGoalsByUser(@RequestParam Long userId){
        return repository.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Goal> createGoal(@RequestBody Goal request){
        request.setCreationDate(LocalDateTime.now());
        repository.save(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteGoal(@RequestParam Long goalId){
        repository.deleteById(goalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
