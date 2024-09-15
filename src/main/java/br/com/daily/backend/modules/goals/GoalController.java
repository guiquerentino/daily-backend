package br.com.daily.backend.modules.goals;


import br.com.daily.backend.modules.goals.domain.Goal;
import br.com.daily.backend.modules.goals.domain.dto.GoalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {

    @Autowired
    GoalService service;

    @PostMapping
    public ResponseEntity<GoalRecord> createGoal(@RequestBody GoalRecord request){
        return new ResponseEntity<>(service.createGoal(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GoalRecord>> fetchAllGoalsByUser(@RequestParam Long userId){
        return new ResponseEntity<>(service.getAllGoalsByUser(userId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GoalRecord> updateGoal(@RequestBody GoalRecord request){
        return new ResponseEntity<>(service.updateGoal(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteGoal(@RequestParam Long goalId){
        service.deleteGoal(goalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
