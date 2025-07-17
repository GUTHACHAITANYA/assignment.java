import java.sql.*;
import java.util.Scanner;

public class assignemnt2 {
    private static final String DB_URL = "jdbc:mysql://bytexldb.com:5051/db_43pffu7nr?useSSL=false";
    private static final String USER = "user_43pffu7nr";
    private static final String PASSWORD = "p43pffu7nr";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Department ID: ");
        int departmentId = scanner.nextInt();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT id, name, department_id FROM employees WHERE department_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, departmentId);

            ResultSet rs = pstmt.executeQuery();

            boolean found = false;
            System.out.println("\nEmployees in Department ID " + departmentId + ":");
            System.out.println("---------------------------------------------");

            while (rs.next()) {
                found = true;
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int deptId = rs.getInt("department_id");

                System.out.printf("ID: %d | Name: %s | Department ID: %d\n", id, name, deptId);
            }

            if (!found) {
                System.out.println("No employees found in the specified department.");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        scanner.close();
    }
}
