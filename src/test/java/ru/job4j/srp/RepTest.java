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
        StringBuilder expect = new StringBuilder();
        expect.append("</html><body>")
                .append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</html><body>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenNewSalaryGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 110);
        store.add(worker);
        Rep engine = new ReportUpdateSalary(store);
       StringBuilder expect = new StringBuilder()
        .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
               .append(worker.getName()).append(";")
               .append(worker.getHired()).append(";")
               .append(worker.getFired()).append(";")
               .append(worker.getSalary()).append(" ")
               .append("тыс. руб.;")
               .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
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

    @Test
    public void generateEngineJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngineJson engine = new ReportEngineJson(store);
        int id = 1;
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append(id).append("name").append(id)
                .append(System.lineSeparator())
                .append(id).append(worker.getName()).append(id).append(",")
                .append(System.lineSeparator())
                .append(id).append("hired").append(id)
                .append(System.lineSeparator())
                .append(worker.getHired()).append(",")
                .append(System.lineSeparator())
                .append(id).append("fired").append(id)
                .append(System.lineSeparator())
                .append(worker.getFired()).append(",")
                .append(System.lineSeparator())
                .append(id).append("salary").append(id)
                .append(System.lineSeparator())
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void generateEngineXml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 110);
        store.add(worker);
        ReportEngineXml engine = new ReportEngineXml(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append("<worker>")
                .append("<name>")
                .append(worker.getName()).append("</name>")
                .append("<hired>")
                .append(worker.getHired()).append("</hired>")
                .append("<fired>")
                .append(worker.getFired()).append("</fired>")
                .append("<salary>")
                .append(worker.getSalary()).append("</salary>")
                .append("</employer/>");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}

