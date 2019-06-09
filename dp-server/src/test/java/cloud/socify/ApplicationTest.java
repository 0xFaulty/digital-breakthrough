package cloud.socify;

import cloud.socify.model.Gender;
import cloud.socify.model.StudentInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ApplicationTest {

    private String[] metricsArray = {
            "Gain Score Model",
            "Value-Added Models (VAM)",
            "Computer-Adaptive Approaches",
            "Skill common rating",
            "Student Growth Percentile Model (SGP)"
    };

    private String[] eventTypeArray = {
            "Курс обучения",
            "Дополнительное обучение",
            "Олимпиада",
            "Заочный курс"
    };

    private String[] userTypeArray = {
            "institution",
            "company",
            "student"
    };

    private List<String> emails = new ArrayList<>();
    private List<String> educationProgram = new ArrayList<>();
    private List<String> company = new ArrayList<>();
    private List<String> institution = new ArrayList<>();
    private List<String> institutionRating = new ArrayList<>();
    private List<String> event = new ArrayList<>();
    private List<String> eventType = Arrays.asList(eventTypeArray);
    private List<String> eventRating = new ArrayList<>();
    private List<String> skill = new ArrayList<>();
    private List<String> vacancy = new ArrayList<>();
    private List<StudentInfo> studentInfo = new ArrayList<>();
    private List<String> metric = Arrays.asList(metricsArray);
    private List<String> userType = Arrays.asList(userTypeArray);

    private List<String> companySql = new ArrayList<>();
    private List<String> institutionSql = new ArrayList<>();
    private List<String> eventSql = new ArrayList<>();
    private List<String> skillSql = new ArrayList<>();
    private List<String> vacancySql = new ArrayList<>();
    private List<String> metricSql = new ArrayList<>();
    private List<String> eventSkillSql = new ArrayList<>();
    private List<String> vacancySkillSql = new ArrayList<>();
    private List<String> educationProgramSql = new ArrayList<>();
    private List<String> companyVacancySql = new ArrayList<>();
    private List<String> userTypeSql = new ArrayList<>();
    private List<String> studentInfoSql = new ArrayList<>();
    private List<String> userSql = new ArrayList<>();
    private List<String> userEventSql = new ArrayList<>();
    private List<String> userMetricSql = new ArrayList<>();

    private int freeId(List<Integer> usedIds, int max) {
        int i;
        while (!usedIds.contains(i = rnd(max))) {
            usedIds.add(i);
            return i;
        }
        throw new RuntimeException();
    }

    private static int rnd(int max) {
        return (int) (Math.random() * ++max);
    }

    private double rndRating() {
        return new Random().nextDouble();
    }

    @Test
    public void name() throws IOException {
        readCompany();
        readInstitution();
        readSkills();
        readEvent();
        readVacancy();
        readEmails();
        readStudentInfo();
        readEducationProgram();

        List<Integer> usedIds = new ArrayList<>();
        String cEP = "INSERT INTO  education_program (institutionId, name, rating, description) VALUES (%s, '%s', %s, '');";
        for (int i = 0; i < institution.size(); i++) {
            for (Integer num : rndEducationProgramName())
                educationProgramSql.add(String.format(cEP, i, educationProgram.get(num), rndRating()));
        }
        String cUT = "INSERT INTO user_type (name) VALUES ('%s');";
        for (String s : userType)
            userTypeSql.add(String.format(cUT, s));
        String cU = "INSERT INTO user (password, email, userType) VALUES ('%s', '%s', '%s');";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        for (String s : emails) {
            String type = userType.get(rnd(userType.size() - 1));
            userSql.add(String.format(cU, encoder.encode(UUID.randomUUID().toString()), s, type));
        }
        String cP = "INSERT INTO company (userId, name, rating, description) VALUES (%s, '%s', %s, '');";
        for (String s : company)
            companySql.add(String.format(cP, freeId(usedIds, userSql.size()), s, rndRating()));
        String cI = "INSERT INTO institution (userId, name, rating, description) VALUES (%s, '%s', %s, '');";
        usedIds = new ArrayList<>();
        for (int i = 0; i < institution.size(); i++)
            institutionSql.add(String.format(cI, freeId(usedIds, userSql.size()), institution.get(i), institutionRating.get(i)));
        String cS = "INSERT INTO skill (name, rating, description) VALUES ('%s', %s, '');";
        for (String s : skill)
            skillSql.add(String.format(cS, s, rndRating()));
        String cE = "INSERT INTO event (eventType, name, rating, description) VALUES  ('%s', '%s', %s, '');";
        for (String s : event) {
            String type = eventType.get(rnd(eventType.size() - 1));
            eventSql.add(String.format(cE, type, s, rndRating()));
        }
        String cV = "INSERT INTO vacancy (name, rating) VALUES ('%s', %s);";
        for (String s : vacancy)
            vacancySql.add(String.format(cV, s, rndRating()));
        String cM = "INSERT INTO metric (name, rating, description) VALUES ('%s', %s, '');";
        for (String s : metric)
            metricSql.add(String.format(cM, s, rndRating()));
        String cES = "INSERT INTO event_skill (eventId, skillId) VALUES (%s, %s, '');";
        for (int i = 0; i < event.size(); i++) {
            for (Integer skillId : rndSkillsId())
                eventSkillSql.add(String.format(cES, i, skillId));
        }
        String cVS = "INSERT INTO vacancy_skill (vacancyId, skillId) VALUES (%s, %s, '');";
        for (int i = 0; i < vacancy.size(); i++) {
            for (Integer skillId : rndSkillsId())
                vacancySkillSql.add(String.format(cVS, i, skillId));
        }
        String cCV = "INSERT INTO company_vacancy (companyId, vacancyId, salary, currencyType, description) VALUES (%s, %s, %s, 'RUB', '');";
        for (int i = 0; i < company.size(); i++) {
            for (Integer vacancyId : rndVacancyId())
                companyVacancySql.add(String.format(cCV, i, vacancyId, rnd(100) * 10 + rnd(10) * 10));
        }
        String cUE = "INSERT INTO user_event (userId, eventId, eventDate, rating) VALUES (%s, %s, '%s', %s);";
        for (int i = 0; i < userSql.size(); i++) {
            if (userSql.get(i).contains("student")) {
                for (Integer num : rndEventId()) {
                    StudentInfo sInfo = this.studentInfo.get(i);
                    userEventSql.add(String.format(cUE, i, num, sInfo.getBirthDate(), rndRating()));
                }
            }
        }
        String cUM = "INSERT INTO user_metric (userId, metricId, value) VALUES (%s, %s, %s);";
        for (int i = 0; i < userSql.size(); i++) {
            for (int j = 0; j < metric.size(); j++) {
                userMetricSql.add(String.format(cUM, i, j, rndRating()));
            }
        }
        String cSI = "INSERT INTO student_info (userId, firstName, secondName, middleName, photo, gender, birthDate) VALUES (%s, '%s', '%s', '%s', '%s', '%s', '%s');";
        for (int i = 0; i < userSql.size(); i++) {
            if (userSql.get(i).contains("student")) {
                StudentInfo sInfo = studentInfo.get(i);
                studentInfoSql.add(String.format(cSI, i, sInfo.getFirstName(),
                        sInfo.getSecondName(), sInfo.getMiddleName(),
                        sInfo.getPhoto(), sInfo.getGender(), sInfo.getBirthDate()));
            }
        }

        Files.write(toPath("company.sql"), companySql);
        Files.write(toPath("institution.sql"), institutionSql);
        Files.write(toPath("event.sql"), eventSql);
        Files.write(toPath("skill.sql"), skillSql);
        Files.write(toPath("vacancy.sql"), vacancySql);
        Files.write(toPath("metric.sql"), metricSql);
        Files.write(toPath("eventSkill.sql"), eventSkillSql);
        Files.write(toPath("vacancySkill.sql"), vacancySkillSql);
        Files.write(toPath("educationProgram.sql"), educationProgramSql);
        Files.write(toPath("companyVacancy.sql"), companyVacancySql);
        Files.write(toPath("userType.sql"), userTypeSql);
        Files.write(toPath("studentInfo.sql"), studentInfoSql);
        Files.write(toPath("user.sql"), userSql);
        Files.write(toPath("userEvent.sql"), userEventSql);
        Files.write(toPath("userMetric.sql"), userMetricSql);
    }

    private Path toPath(String f) {
        return new File(f).toPath();
    }

    private Set<Integer> rndEventId() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < rnd(30) + 2; i++) {
            set.add(rnd(event.size() - 1));
        }
        return set;
    }

    private Set<Integer> rndEducationProgramName() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < rnd(3) + 2; i++) {
            set.add(rnd(educationProgram.size() - 1));
        }
        return set;
    }

    private Set<Integer> rndVacancyId() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < rnd(3) + 2; i++) {
            set.add(rnd(vacancy.size() - 1));
        }
        return set;
    }

    private Set<Integer> rndSkillsId() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < rnd(5) + 2; i++) {
            set.add(rnd(skill.size() - 1));
        }
        return set;
    }

    private void readStudentInfo() throws IOException {
        Path path = new File("src\\main\\resources\\data\\student_info.set").toPath();
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            String[] split = string.split(",");
            StudentInfo sInfo = new StudentInfo();
            sInfo.setFirstName(split[0]);
            sInfo.setSecondName(split[1]);
            sInfo.setMiddleName(split[2]);
            sInfo.setPhoto(split[3]);
            sInfo.setGender(Gender.valueOf(split[4].toUpperCase()));
            String[] date = split[5].split("/");
            sInfo.setBirthDate(LocalDate.of(Integer.valueOf(date[2]), Integer.valueOf(date[1]), Integer.valueOf(date[0])));
            studentInfo.add(sInfo);
        }
    }

    private void readEmails() throws IOException {
        Path path = new File("src\\main\\resources\\data\\email.set").toPath();
        emails = Files.readAllLines(path);
    }

    private void readEducationProgram() throws IOException {
        Path path = new File("src\\main\\resources\\data\\education_programm.set").toPath();
        educationProgram = Files.readAllLines(path);
    }

    private void readVacancy() throws IOException {
        File file = new File("src\\main\\resources\\data\\vacancy.set");
        Path path = file.toPath();
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            String[] split = string.split(",");
            vacancy.add(split[0]);
        }
    }

    private void readEvent() throws IOException {
        File file = new File("src\\main\\resources\\data\\event.set");
        Path path = file.toPath();
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            String[] split = string.split(",");
            event.add(split[1]);
            eventRating.add(split[split.length - 1]);
        }
    }

    private void readSkills() throws IOException {
        File file = new File("src\\main\\resources\\data\\hard_skill.set");
        Path path = file.toPath();
        List<String> strings = Files.readAllLines(path);
        skill.addAll(strings);
        File file2 = new File("src\\main\\resources\\data\\soft_skill.set");
        Path path2 = file2.toPath();
        List<String> strings2 = Files.readAllLines(path2);
        skill.addAll(strings2);
        Collections.shuffle(skill);
    }

    private void readInstitution() throws IOException {
        File file = new File("src\\main\\resources\\data\\institution.set");
        Path path = file.toPath();
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            String[] split = string.split(",");
            institution.add(split[0]);
            institutionRating.add(split[1]);
        }
    }

    private void readCompany() throws IOException {
        File file = new File("src\\main\\resources\\data\\company.set");
        Path path = file.toPath();
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            String[] split = string.split(",");
            company.add(split[0]);
        }
    }
}