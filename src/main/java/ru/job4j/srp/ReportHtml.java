package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportHtml implements Rep {
    private Store store;

    public ReportHtml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("</html><body>")
                .append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }

              text.append("</html><body>").append(System.lineSeparator());
        return text.toString();
    }
}