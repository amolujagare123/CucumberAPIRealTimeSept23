package utility.POJO.chatServer.GetObject;

import utility.POJO.chatServer.CreateChatUserPOJO;

import java.util.ArrayList;

public class CreateUserObject {

    public static CreateChatUserPOJO getCreateUserPojoObject(String username,String password,String email
            ,String name ,String surname, String nickName)
    {
        CreateChatUserPOJO ob = new CreateChatUserPOJO();
        ob.setUsername(username);
        ob.setPassword(password);
        ob.setEmail(email);
        ob.setName(name);
        ob.setSurname(surname);
        ob.setChat_nickname(nickName);

        ArrayList<Integer> forDept = new ArrayList<Integer>(){{
            add(1);
            add(2);
        }};
        ob.setDepartments(forDept);
        ArrayList<Integer> forDeptRead = new ArrayList<Integer>(){{
            add(2);
        }};
        ob.setDepartments_read(forDeptRead);
        ArrayList<Integer> forGroups = new ArrayList<Integer>(){{
            add(1);
        }};
        ob.setDepartment_groups(forGroups);
        ob.setUser_groups(forGroups);

        return ob;
    }
}
