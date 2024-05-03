package com.scaler.splitwisejune22.commands;

import com.scaler.splitwisejune22.commandsOld.CommandKeywords;
import com.scaler.splitwisejune22.controllers.UserController;
import com.scaler.splitwisejune22.dtos.RegisterUserRequestDto;
import com.scaler.splitwisejune22.dtos.RegisterUserResponseDto;
import com.scaler.splitwisejune22.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Component
public class  RegisterUserCommand implements Command {
    private UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean canExecute(String input) {
        // Register vinsmokesanji 003 namisswwaann
        // ["Register", "vinsmokesanji", "003", "namisswwaann"]
        // Step 1: Read inputs
        List<String> params = Arrays.stream(input.split(" ")).toList();
        // Step 2. Validate the inputs.
        if (params.size() != 4) {
            return false;
        }
        // notice that we use constants for command keywords and don't use literal strings so they can be changed at one place
        if (!params.get(0).equals(CommandKeywords.REGISTER_USER_COMMAND)) {
            return false;
        }
        return true;
    }

    @Override
    public void execute(String input) {
        List<String> commandTokens = Arrays.stream(input.split(" ")).toList();
        String username = commandTokens.get(1);
        String phoneNumber = commandTokens.get(2);
        String password = commandTokens.get(3);

        RegisterUserRequestDto registerUserRequestDto = new RegisterUserRequestDto();
        registerUserRequestDto.setPassword(password);
        registerUserRequestDto.setUsername(username);
        registerUserRequestDto.setPhoneNumber(phoneNumber);

        RegisterUserResponseDto response = userController.registerUser(registerUserRequestDto);

        System.out.println(response.getUser());
        System.out.println("User Registered");
    }
}
