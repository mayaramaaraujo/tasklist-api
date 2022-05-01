package com.tasklist.api.repositories;

import com.tasklist.api.models.Task;
import com.tasklist.api.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    private User user = new User();
    private Task task = new Task();
    private List<Task> taskList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        user.setUsername("username");
        task.setTitle("task1");
        task.setDescription("task1description");
        task.setUser(user);

        userRepository.save(user);
        taskRepository.save(task);
    }

    @Test
    public void testFindByUserId() {
        List<Task> list = taskRepository.findAllByUserId(user.getId());
        assertEquals(list.size(), 1);
    }

}
