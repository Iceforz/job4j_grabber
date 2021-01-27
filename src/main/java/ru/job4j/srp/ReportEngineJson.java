package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportEngineJson implements Rep {
    private final Store store;

    public ReportEngineJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary; ");
        int id = 1;
        for (Employee employee : store.findBy(filter)) {
            text.append(id).append("name").append(id)
                   .append(System.lineSeparator())
                    .append(id).append(employee.getName()).append(id).append(",")
                    .append(System.lineSeparator())
                    .append(id).append("hired").append(id)
                    .append(System.lineSeparator())
                    .append(employee.getHired()).append(",")
                    .append(System.lineSeparator())
                    .append(id).append("fired").append(id)
                    .append(System.lineSeparator())
                    .append(employee.getFired()).append(",")
                    .append(System.lineSeparator())
                    .append(id).append("salary").append(id)
                    .append(System.lineSeparator())
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
