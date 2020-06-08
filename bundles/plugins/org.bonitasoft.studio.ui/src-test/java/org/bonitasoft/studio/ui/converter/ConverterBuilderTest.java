/**
 * Copyright (C) 2017 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.ui.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.junit.Rule;
import org.junit.Test;

public class ConverterBuilderTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_create_and_use_string_to_int_converter() {

        IConverter stringToInt = ConverterBuilder.<String, Integer> newConverter()
                .fromType(String.class)
                .toType(Integer.class)
                .withConvertFunction(Integer::parseInt)
                .create();

        IConverter intToString = ConverterBuilder.<Integer, String> newConverter()
                .fromType(Integer.class)
                .toType(String.class)
                .withConvertFunction(String::valueOf)
                .create();

        A a = new A();
        B b = new B();

        DataBindingContext ctx = new DataBindingContext();
        IObservableValue<String> observeA = PojoProperties.value("a").observe(a);
        IObservableValue<Integer> observeB = PojoProperties.value("b").observe(b);
        ctx.bindValue(observeA, observeB, UpdateStrategyFactory.updateValueStrategy().withConverter(stringToInt).create(),
                UpdateStrategyFactory.updateValueStrategy().withConverter(intToString).create());

        observeA.setValue("123");
        assertThat(b.getB()).isEqualTo(123);

        observeB.setValue(321);
        assertThat(a.getA()).isEqualTo("321");
    }

    @Test
    public void should_create_and_use_advanced_converter() {

        IConverter humanToStudent = ConverterBuilder.<Human, Student> newConverter()
                .fromType(Human.class)
                .toType(Student.class)
                .withConvertFunction(human -> {
                    Student student = new Student();
                    student.setAge(human.getAge());
                    student.setMail(human.getName() + "@gmail.com");
                    return student;
                }).create();

        IConverter studentToHuman = ConverterBuilder.<Student, Human> newConverter()
                .fromType(Student.class)
                .toType(Human.class)
                .withConvertFunction(student -> {
                    Human human = new Human();
                    human.setAge(student.getAge());
                    String name = student.getMail().split("@")[0];
                    human.setName(name);
                    return human;
                }).create();

        Container container = new Container();

        DataBindingContext ctx = new DataBindingContext();
        IObservableValue<Human> observeHuman = PojoProperties.value("human").observe(container);
        IObservableValue<Student> observeStudent = PojoProperties.value("student").observe(container);
        ctx.bindValue(observeHuman, observeStudent,
                UpdateStrategyFactory.updateValueStrategy().withConverter(humanToStudent).create(),
                UpdateStrategyFactory.updateValueStrategy().withConverter(studentToHuman).create());

        observeHuman.setValue(new Human());
        assertThat(container.getStudent().getAge()).isEqualTo(20);
        assertThat(container.getStudent().getMail()).isEqualTo("human@gmail.com");

        observeStudent.setValue(new Student());
        assertThat(container.getHuman().getAge()).isEqualTo(30);
        assertThat(container.getHuman().getName()).isEqualTo("student");
    }

    class A {

        private String a = "";

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    class B {

        private Integer b = 0;

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }
    }

    class Human {

        Human() {
            name = "human";
            age = 20;
        }

        Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name;
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class Student {

        private String mail;
        private int age;

        public Student() {
            mail = "student@gmail.com";
            age = 30;
        }

        public Student(String mail, int age) {
            this.mail = mail;
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public String getMail() {
            return mail;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }
    }

    class Container {

        private Human human = new Human("h", 0);
        private Student student = new Student("s", 0);

        public Human getHuman() {
            return human;
        }

        public Student getStudent() {
            return student;
        }

        public void setHuman(Human human) {
            this.human = human;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }
}
