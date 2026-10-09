import java.util.ArrayList;
import java.util.List;

public class Main {
    record UserData(String name, String email, int age) {}

    public static void main(String[] args) {
        List<UserData> cases = List.of(
                new UserData("Asha", "asha@example.com", 28),
                new UserData("", "missingname@example.com", 34),
                new UserData("Ravi", "invalid-email", 25),
                new UserData("Mina", "mina@example.com", 15));

        System.out.println("=== Test Data Validator ===");
        int passed = 0;
        for (int i = 0; i < cases.size(); i++) {
            UserData data = cases.get(i);
            List<String> errors = validate(data);
            boolean valid = errors.isEmpty();
            if (valid) passed++;
            System.out.printf("Case %d: %s%n", i + 1, valid ? "VALID" : "INVALID -> " + errors);
        }
        System.out.printf("Summary: %d valid, %d invalid%n", passed, cases.size() - passed);
    }

    static List<String> validate(UserData data) {
        List<String> errors = new ArrayList<>();
        if (data.name() == null || data.name().isBlank()) errors.add("name is required");
        if (data.email() == null || !data.email().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            errors.add("email format is invalid");
        }
        if (data.age() < 18) errors.add("age must be 18 or above");
        return errors;
    }
}
