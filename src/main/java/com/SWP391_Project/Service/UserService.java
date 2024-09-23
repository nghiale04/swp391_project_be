package com.SWP391_Project.Service;

import com.SWP391_Project.Entity.User;
import com.SWP391_Project.model.UserRegisterDTO;

public interface UserService {
    User createUser(UserRegisterDTO userRegisterDTO) throws Exception;
    String login(String phoneNumber, String password) throws Exception;
}
