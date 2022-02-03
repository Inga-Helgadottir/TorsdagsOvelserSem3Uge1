import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
    private GetFromUserTable gfut;
//    US1 - As a user I want to see a list of all user on the system by their names only
//    US2 - As a user I want to see details of a specific user from the list
//    US3 - As a user I want to edit my own user details

    @BeforeEach
    void setUp() throws SQLException {
        gfut = new GetFromUserTable();
        Connection con = null;
        try {
            con = DBconnector.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (id, fname, lname, pw, phone, address) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1);
            ps.setString(2, "Hans");
            ps.setString(3, "Hansen");
            ps.setString(4, "Hemmelig123");
            ps.setString(5, "40404040");
            ps.setString(6,"Rolighedsvej 3");
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    US1 - As a user I want to see a list of all users on the system by their names only
    @org.junit.jupiter.api.Test
    public void getUser() {
        ArrayList<String> expected = new ArrayList<>();
        String expectedName = "Hans";
        ArrayList<String> actual = gfut.getUserName();
        expected.add(expectedName);
        assertEquals(expected, actual);
    }

    //    US2 - As a user I want to see details of a specific user from the list
    @org.junit.jupiter.api.Test
    public void getUserById() {
        User expected = new User("Hans", "Hansen", "Hemmelig123", "40404040", "Rolighedsvej 3");
        User actual = gfut.getUserById(1);
        assertEquals(expected.name, actual.name);
        assertEquals(expected.lastName, actual.lastName);
        assertEquals(expected.password, actual.password);
        assertEquals(expected.phoneNbr, actual.phoneNbr);
        assertEquals(expected.address, actual.address);
    }


//    US3 - As a user I want to edit my own user details
    @org.junit.jupiter.api.Test
    public void editUser(){
//        User u = new User("Hans", "Hansen", "Hemmelig123", "40404040", "Rolighedsvej 3");
//        String actual = u.lastName;
//        String expected = "Hansen";
//        assertEquals(expected, actual);

        User user = new User("Hans", "Hansens", "Hemmelig123", "40404040", "Rolighedsvej 3");
        gfut.editUserByName(user, "Hans");
        User user1 = gfut.getUserByName("Hans");
        String actual2 = user1.lastName;
        String expected2 = "Hansens";
        assertEquals(expected2, actual2);
    }

    @AfterEach
    void tearDown() {
        Connection con = null;
        try {
            con = DBconnector.connection();
            String SQL = "DELETE FROM startcode_test.usertable WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 1);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
