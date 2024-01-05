package model;

import java.time.LocalDate;

public class Member {

    private LocalDate birthday;
    private int age;
    private String currentTier;
    private String nextTier;
    private String futureDate;

    // Member Constructor
    public Member(LocalDate birthday, int age,String nextTier, String futureDate) {
        this.birthday = birthday;
        this.age = age;
        this.nextTier = nextTier;
        this.futureDate = futureDate;
    }

    public LocalDate getBirthday(){
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCurrentTier() {
        return currentTier;
    }

    public void setCurrentTier(String currentTier) {
        this.currentTier = currentTier;
    }

    public void setNextTier(String nextTier) {
        this.nextTier = nextTier;
    }

    public String getNextTier() {
        return nextTier;
    }

    public String getFutureDate() {
        return futureDate;
    }

    public void setFutureDate(String futureDate) {
        this.futureDate = futureDate;
    }

}
