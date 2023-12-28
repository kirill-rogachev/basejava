package com.basejava.webapp;

import com.basejava.webapp.model.*;

import java.time.LocalDate;
import java.util.List;

import static com.basejava.webapp.model.SectionType.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.addContact(ContactType.PHONE, "+7 921 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.addSection(OBJECTIVE, new TextSection(
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.addSection(PERSONAL, new TextSection(
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.addSection(ACHIEVEMENT, new ListSection(List.of(
                "Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения " +
                        "автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов " +
                        "на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin " +
                        "проект для комплексных DIY смет",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный" +
                        "maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие" +
                        "(JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция " +
                        "с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.")));

        resume.addSection(QUALIFICATIONS, new ListSection(List.of(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, " +
                        "SQLite, MS SQL, HSQLDB")));

        Period period1 = new Period("Автор проекта", "Создание, организация и проведение Java онлайн " +
                "проектов и стажировок.", LocalDate.of(2013, 10, 1), LocalDate.now());
        Organization workOrganization1 = new Organization("Java Online Projects", "http://javaops.ru/",
                List.of(period1));

        Period period2 = new Period("Старший разработчик (backend)", "Проектирование и разработка " +
                "онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, " +
                "PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1));
        Organization workOrganization2 = new Organization("Wrike", "https://www.wrike.com/", List.of(period2));

        resume.addSection(EXPERIENCE, new OrganizationSection(List.of(workOrganization1, workOrganization2)));

        Period period3 = new Period("Functional Programming Principles in Scala' by Martin Odersky", "",
                LocalDate.of(2013, 3, 1), LocalDate.of(2013, 3, 1));
        Organization educationOrganization1 = new Organization("Coursera",
                "https://www.coursera.org/course/progfun", List.of(period3));

        Period period4 = new Period("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на " +
                "UML.'", "", LocalDate.of(2011, 3, 1), LocalDate.of(2011,
                3, 1));
        Organization educationOrganization2 = new Organization("Luxoft",
                "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", List.of(period4));

        resume.addSection(EDUCATION, new OrganizationSection(List.of(educationOrganization1, educationOrganization2)));

        Period period5 = new Period("Аспирантура (программист С, С++)", "",
                LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1));

        Period period6 = new Period("Инженер (программист Fortran, C)", "",
                LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1));

        Organization educationOrganization3 = new Organization("Санкт-Петербургский национальный " +
                "исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                List.of(period5, period6));
        resume.addSection(EDUCATION, new OrganizationSection(List.of(educationOrganization1, educationOrganization2,
                educationOrganization3)));

        System.out.println(resume.getFullName() + "\n");
        System.out.println(resume.getContact(ContactType.PHONE) + "\n" + resume.getContact(ContactType.SKYPE));
        System.out.println(resume.getContact(ContactType.MAIL) + "\n" + resume.getContact(ContactType.GITHUB));
        System.out.println(resume.getContact(ContactType.STACKOVERFLOW) + "\n" + resume.getContact(ContactType.HOMEPAGE));
        System.out.println("----------------------------");
        System.out.println(OBJECTIVE + "\n" + resume.getSection(OBJECTIVE) + "\n");
        System.out.println(PERSONAL + "\n" + resume.getSection(PERSONAL) + "\n");

        System.out.println(ACHIEVEMENT);
        ListSection achievements = (ListSection) resume.getSection(ACHIEVEMENT);
        for (String item : achievements.getList()) {
            System.out.println(item);
        }

        System.out.println("\n" + QUALIFICATIONS);
        ListSection qualifications = (ListSection) resume.getSection(QUALIFICATIONS);
        for (String item : qualifications.getList()) {
            System.out.println(item);
        }

        System.out.println("\n" + EXPERIENCE);
        OrganizationSection experience = (OrganizationSection) resume.getSection(EXPERIENCE);
        for (Organization organization : experience.getOrganizations()) {
            System.out.println(organization + "\n");
        }

        System.out.println("\n" + EDUCATION);
        OrganizationSection education = (OrganizationSection) resume.getSection(EDUCATION);
        for (Organization organization : education.getOrganizations()) {
            System.out.println(organization);
        }
    }
}
