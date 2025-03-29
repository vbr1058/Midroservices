package com.codingShuttle.linkedIn.connections_service.auth;

/**
 * @author Vinay B R
 * @project Live LinkedIn
 * @package com.codingShuttle.linkedIn.post_service.auth
 * @since 16/10/2024 - 12:33 am
 */
public class UserContextHolder {

    private static final  ThreadLocal<Long> currentUserId=new ThreadLocal<>();

    public static Long getCurrentUserId(){
        return currentUserId.get();
    }

    public static void setCurrentUserId(Long userId){
        currentUserId.set(userId);
    }
    public static void clear(){
        currentUserId.remove();
    }

}
