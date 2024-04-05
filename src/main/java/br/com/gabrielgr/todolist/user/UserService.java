package br.com.gabrielgr.todolist.user;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserService {
  private IUserRepository userRepository;

  public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserModel create(UserModel userModel) {
    
    var user = this.userRepository.findByUsername(userModel.getUsername());
    if(user != null){

      throw new RuntimeException("Usuário já existe"); 
    }

    var passwordHashRed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

    userModel.setPassword(passwordHashRed);

    return userRepository.save(userModel);
  }

  
}
