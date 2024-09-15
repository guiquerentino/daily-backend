package br.com.daily.backend.modules.goals;

import br.com.daily.backend.core.exceptions.GenericException;
import br.com.daily.backend.modules.goals.domain.Goal;
import br.com.daily.backend.modules.goals.domain.dto.GoalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoalService {

    @Autowired
    private GoalRepository repository;

    public GoalRecord createGoal(GoalRecord goal) {
        Goal goalDB = new Goal();

        goalDB.setCreatedBy(goal.createdBy());
        goalDB.setScheduledTime(goal.scheduledTime());
        goalDB.setCompleted(goal.isCompleted());
        goalDB.setUserId(goal.userId());
        goalDB.setTitle(goal.title());
        goalDB.setAllDay(goal.isAllDay());

        return Goal.mapToRecord(repository.save(goalDB));
    }

    public List<GoalRecord> getAllGoalsByUser(Long userId) {
        List<Goal> goals = repository.findByUserId(userId);
        List<GoalRecord> response = new ArrayList<GoalRecord>();

        for (Goal goal : goals) {
            response.add(Goal.mapToRecord(goal));
        }
        return response;
    }

    public GoalRecord updateGoal(GoalRecord goal) {
        Optional<Goal> goalOptional = repository.findById(goal.id());

        if (goalOptional.isPresent()) {

            Goal goalDB = goalOptional.get();
            goalDB.setTitle(goal.title());
            goalDB.setCompleted(goal.isCompleted());
            goalDB.setAllDay(goal.isAllDay());
            goalDB.setUserId(goal.userId());
            goalDB.setScheduledTime(goal.scheduledTime());

            return Goal.mapToRecord(repository.save(goalDB));
        }

        throw new GenericException("GOAL_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public void deleteGoal(Long id) {
        Optional<Goal> optionalGoal = repository.findById(id);

        if (optionalGoal.isPresent()) {
            repository.delete(optionalGoal.get());
        } else {
            throw new GenericException("GOAL_NOT_FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
