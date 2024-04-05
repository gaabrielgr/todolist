package br.com.gabrielgr.todolist.task;

import org.springframework.stereotype.Service;

import br.com.gabrielgr.todolist.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

  private ITaskRepository taskRepository;

  public TaskService(ITaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskModel create(TaskModel taskModel, UUID idUser) throws Exception {
    LocalDateTime currentDate = LocalDateTime.now();

    if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
      throw new Exception("A data de início / data de término devem ser maior que a data atual.");
    }

    if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      throw new Exception("A data de início deve ser menor que a data de término.");
    }

    taskModel.setIdUser(idUser);
    return taskRepository.save(taskModel);

  }

  public List<TaskModel> findAllTasksByUserId(UUID idUser) {
    return taskRepository.findByIdUser(idUser);
  }

  public TaskModel update(UUID taskId, TaskModel updatedTask, UUID idUser) throws Exception {
    TaskModel existingTask = taskRepository.findByIdAndIdUser(taskId, idUser);

    if (existingTask == null) {
      throw new Exception("Tarefa não encontrada.");
    }

    if (!existingTask.getIdUser().equals(idUser)) {
      throw new Exception("Você não tem permissão para alterar essa tarefa.");
    }
    Utils.copyNonNullProperties(updatedTask, existingTask);
    return taskRepository.save(existingTask);
  }

  public void delete(UUID taskId, UUID idUser) throws Exception {
    TaskModel existingTask = taskRepository.findByIdAndIdUser(taskId, idUser);

    if (existingTask == null) {
      throw new Exception("Tarefa não encontrada.");
    }

    if (!existingTask.getIdUser().equals(idUser)) {
      throw new Exception("Você não tem permissão para deletar essa tarefa.");
    }

     taskRepository.delete(existingTask);
  }

}
