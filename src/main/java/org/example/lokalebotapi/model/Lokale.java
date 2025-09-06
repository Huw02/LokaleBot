package org.example.lokalebotapi.model;

public class Lokale {

    String time;
    String subject;
    String teacher;
    String room;


    public Lokale(String time, String subject, String teacher, String room) {
        this.time = time;
        this.subject = subject;
        this.teacher = teacher;
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Lokale{" +
                "time='" + time + '\'' +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
