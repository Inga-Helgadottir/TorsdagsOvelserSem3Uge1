import java.sql.*;
import java.util.ArrayList;

public class GetFromUserTable {
    public ArrayList<String> getUserName() {
        ArrayList<String> names = new ArrayList<>();
        Connection con = null;
        try {
            con = DBconnector.connection();
            String SQL = "SELECT fname from startcode_test.usertable";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("fname");
                names.add(name);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }
    public User getUserByName(String fname) {
        Connection con = null;
        try {
            con = DBconnector.connection();
            String SQL = "SELECT * from startcode_test.usertable WHERE fname = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fname);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("fname");
                String lastName = rs.getString("lname");
                String password = rs.getString("pw");
                String phoneNbr = rs.getString("phone");
                String address = rs.getString("address");
                User u = new User(name, lastName, password, phoneNbr, address);
                return u;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        ArrayList<String> names = new ArrayList<>();
        Connection con = null;
        try {
            con = DBconnector.connection();
            String SQL = "SELECT * from startcode_test.usertable WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("fname");
                String lastName = rs.getString("lname");
                String password = rs.getString("pw");
                String phoneNbr = rs.getString("phone");
                String address = rs.getString("address");
                User u = new User(name, lastName, password, phoneNbr, address);
                return u;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editUserByName(User u, String name) {
        Connection con = null;
        try {
            con = DBconnector.connection();
            String SQL = "update startcode_test.usertable set `fname` = ?, `lname` = ?, `pw` = ?, `phone` = ?, `address` = ? where fname = ?";
            try(PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);){
                ps.setString(1, u.name);
                ps.setString(2, u.lastName);
                ps.setString(3, u.password);
                ps.setString(4, u.phoneNbr);
                ps.setString(5, u.address);
                ps.setString(6, name);
                ps.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
