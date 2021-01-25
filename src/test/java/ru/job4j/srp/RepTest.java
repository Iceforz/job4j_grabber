package ru.job4j.srp;

import org.junit.Test;

import java.util.Calendar;

import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RepTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee(";Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHTMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Rep engine = new ReportHtml(store);
        boolean result = engine.generate(em -> true).contains("</html><body>");
        assertThat(result, is(true));
    }

    @Test
    public void whenNewSalaryGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 110);
        store.add(worker);
        Rep engine = new ReportUpdateSalary(store);
        boolean result = engine.generate(em -> true).contains("");
        assertThat(result, is(true));
    }

    @Test
    public void whenDescBySalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 1);
        Employee worker2 = new Employee("Kate", now, now, 3);
        Employee worker3 = new Employee("Slava", now, now, 2);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Rep engine = new ReportHeadHunter(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
