package study.boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class boj10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Student> list = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            list.add(new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {

                if (o1.kor == o2.kor) {
                    if (o1.eng == o2.eng) {
                        if (o1.math == o2.math) {
                            return o1.name.compareTo(o2.name);
                        }
                        return o2.math - o1.math; // 수학 내림차순
                    }
                    return o1.eng - o2.eng; // 영어 오름차순
                }
                return o2.kor - o1.kor; // 국어 내림차순
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Student s : list) {
            sb.append(s.name).append("\n");
        }
        System.out.println(sb);
    }

    static class Student {
        String name;
        int kor;
        int eng;
        int math;

        Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }
    }
}
