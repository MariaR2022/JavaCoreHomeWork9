package JavaCoreHomeWork9;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


interface Student {
    String getName();
    List<Course> getAllCourses();
}
interface Course {}

/* реализация объектов данных */
class CourseClass implements Course {
    String title; // название

    public CourseClass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}

class StudentClass implements Student {
    private String name;
    private List<Course> courses;

    public StudentClass(String name, String courses) {
        this.name = name;
        this.courses = new ArrayList<Course>();
        for (String i: courses.split(", ")) this.courses.add(new CourseClass(i));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Course> getAllCourses() {
        return this.courses;
    }

    @Override
    public String toString() {
        return "Студент \'" + name + '\'' + ", записан на курсы = " + courses ;
    }
}

/* Работа с данными в точке входа */
public class Application {

    static Set<String> task1(List<StudentClass> students) {

        return students.stream()
                .map(x -> x.getAllCourses())
                .flatMap(x->x.stream())
                .map(x-> x.toString())
                .collect(Collectors.toSet());
    }

    static List<Student> task2 (List<StudentClass> students) {

        List<Student> res = students.stream()
                .sorted((x,y) -> (int) (y.getAllCourses().size() - x.getAllCourses().size()) )
                .limit(3)
                .collect(Collectors.toList());
        return res;
    }

    static List<Student> task3 (List<StudentClass> students, String toFind) {

        List<Student> res = students.stream()
                .filter(x -> x.getAllCourses().toString().contains(toFind))
                .collect(Collectors.toList());
        return res;

    }

    public static void main(String[] args) {

        List<StudentClass> students = new ArrayList<>();
        students.add(new StudentClass("Ольга", "Вязание на спицах, Вязание крючком, Шитье, Английский язык, Кулинария"));
        students.add(new StudentClass("Инна", "Вязание на спицах, Вязание крючком, Шитье, Английский язык"));
        students.add(new StudentClass("Анна", "Вязание на спицах, Вязание крючком, Шитье"));
        students.add(new StudentClass("Марина", "Вязание на спицах, Вязание крючком"));
        students.add(new StudentClass("Наталья", "Вязание на спицах"));
        students.add(new StudentClass("Людмила", "Шитье, Английский язык"));
        students.add(new StudentClass("Елена", "Первая помощь, Йога"));


        System.out.println("Все студенты: ");
        students.stream().forEach(System.out::println);


        System.out.println("-- Задание №1 ---------------------------------");

        System.out.println(task1(students));
        System.out.println(students.stream()
                .map(x -> x.getAllCourses())
                .flatMap(x->x.stream())
                .map(x-> x.toString())
                .distinct()
                .collect(Collectors.toList())
        );
        System.out.println("-- Задание №2 ---------------------------------");
        task2(students).stream().forEach(System.out::println);

        System.out.println("-- Задание №3 ---------------------------------");
        String COURSE_NAME = "Кулинария";
        task3(students,COURSE_NAME).stream().forEach(System.out::println);
    }
}