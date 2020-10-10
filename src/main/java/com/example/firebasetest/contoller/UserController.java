package com.example.firebasetest.contoller;
import com.example.firebasetest.Dao.UserRepository;
import com.example.firebasetest.bean.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


/*

Get All User:- /getAllUsers
Get Single User:-/getUserDetails
Parameter:- String id, User object
Update User:-/updateUser
Parameter:- String id, User object
Delete User:- /deleteUser
Parameter:- String id
Save User:- /createUser
Parameter:- String id, User object
GetUserById:-/getUserDetails
Parameter:- String id
 */

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllUsers")
    public List<Users> getAllUser() throws ExecutionException, InterruptedException {
        return userRepository.getAllUsers();
    }

    @GetMapping("/getUserDetails")
    public Users getUser(@RequestParam String id ) throws InterruptedException, ExecutionException{
        return userRepository.getUserDetails(id);

    }

    @PostMapping("/createUser")
    public String createUser(@RequestParam String id ,@RequestBody Users user ) throws InterruptedException, ExecutionException {
        return userRepository.saveUserDetails(user,id);
    }

  @PutMapping("/updateUser")
    public String updateUser(@RequestParam String id,@RequestBody Users user  ) throws InterruptedException, ExecutionException {
        return userRepository.updateUserDetails(id,user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        return userRepository.deleteUser(id);
    }
}


