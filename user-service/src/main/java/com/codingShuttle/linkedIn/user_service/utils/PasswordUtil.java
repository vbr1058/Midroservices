package com.codingShuttle.linkedIn.user_service.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Vinay B R
 * @project LinkedIn
 * @package com.codingShuttle.linkedIn.user_service.utils
 * @since 24/09/2024 - 03:17 pm
 */
public class PasswordUtil {

    public static String hashPassword(String planTextPassword){
        return BCrypt.hashpw(planTextPassword,BCrypt.gensalt());
    }

    public static boolean checkPassword(String planTextPassword,String hashedPassword){
        return BCrypt.checkpw(planTextPassword,hashedPassword);
    }
}
