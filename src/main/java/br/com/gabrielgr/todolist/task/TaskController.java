package br.com.gabrielgr.todolist.task;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  private TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/")
  public ResponseEntity<Object> createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    try {
      UUID idUser = (UUID) request.getAttribute("idUser");
      TaskModel createdTask = taskService.create(taskModel, idUser);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    UUID idUser = (UUID) request.getAttribute("idUser");
    List<TaskModel> tasks = this.taskService.findAllTasksByUserId(idUser);
    return tasks;
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody TaskModel taskModel,
      HttpServletRequest request) {
    try {
      UUID idUser = (UUID) request.getAttribute("idUser");
      TaskModel updatedTask = this.taskService.update(id, taskModel, idUser);
      return ResponseEntity.ok(updatedTask);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id, HttpServletRequest request) {
    try {
      UUID idUser = (UUID) request.getAttribute("idUser");
      this.taskService.delete(id, idUser);
      return ResponseEntity.ok().build();

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

}