package puzzle;

import java.util.List;

public interface UserService {
    CustomUser getUserByLogin(String login);
    void addUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
    void delete(CustomUser customUser);
    List<CustomUser> getUsers();
}
