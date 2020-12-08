package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.html.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Grabber implements Grab {

    private final Properties cfg = new Properties();

    private String url;

    public Store store() throws SQLException {
        return new PsqlStore(cfg);
    }

        public Scheduler scheduler() throws SchedulerException {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            return scheduler;
        }

        public void cfg() throws IOException {
            try (InputStream in = PsqlStore.class.getClassLoader()
                    .getResourceAsStream("app.properties")) {
                cfg.load(in);
            }
        }

        private void setUrl() {
            url = cfg.getProperty("url");
        }

        @Override
        public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException {
            setUrl();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            data.put("parse", parse);
            data.put("link", url);
            JobDetail job = newJob(GrabJob.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder steps = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("steps")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(steps)
                    .build();
            scheduler.scheduleJob(job, trigger);
        }

        public static class GrabJob implements Job {
            @Override
            public void execute(JobExecutionContext context) {
                JobDataMap map = context.getJobDetail().getJobDataMap();
                Store store = (Store) map.get("store");
                Parse parse = (Parse) map.get("parse");
                String url = (String) map.get("link");
                for (Post post : parse.list(url)) {
                    store.save(post);
                }
            }
        }

        public static void main(String[]args) throws Exception {
            Grabber grab = new Grabber();
            grab.cfg();
            Scheduler scheduler = grab.scheduler();
            Store store = grab.store();
            grab.init(new SqlRuParser(), store, scheduler);
        }
    }

