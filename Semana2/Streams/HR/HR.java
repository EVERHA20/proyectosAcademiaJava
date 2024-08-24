import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HR {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Felix", "Developer", 80000, 9.0, LocalDate.of(2022, 6, 15)),
            new Employee("Manuel", "Designer", 75000, 8.5, LocalDate.of(2021, 11, 23)),
            new Employee("Carlos", "Manager", 95000, 7.5, LocalDate.of(2020, 1, 10)),
            new Employee("Jorge", "Developer", 82000, 9.2, LocalDate.of(2022, 3, 25)),
            new Employee("Alan", "Tester", 70000, 8.0, LocalDate.of(2023, 7, 5)),
            new Employee("Elias", "Scrum Master", 50000, 9.7, LocalDate.of(2023, 3, 12)),
            new Employee("Ruben", "Tester", 70000, 9.4, LocalDate.of(2020, 6, 7))
        );

        Optional<List<Employee>> optionalTopEmployees = employees.stream()
            .filter(Employee::isEligibleForRaise)
            .filter(e -> e.getPerformance() >= 9.0)
            .sorted((e1, e2) -> Double.compare(e2.calculateRaiseScore(), e1.calculateRaiseScore()))
            .limit(3)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                list -> list.isEmpty() ? Optional.<List<Employee>>empty() : Optional.of(list)
            ));

        System.out.println("Top Candidates for a Raise:");
        System.out.printf("%-15s %-15s %-8s %-10s %-10s%n",
                "Name", "Position", "Salary", "Performance", "Tenure");
        optionalTopEmployees.ifPresentOrElse(
            list -> {
                list.forEach(System.out::println);
                System.out.println("\nDefault Top Pick for a Raise:");
                list.stream().findFirst().ifPresent(System.out::println);
            },
            () -> System.out.println("No candidates eligible for a raise.")
        );
    }
}