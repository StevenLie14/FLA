package model;

public class HighScore {

    private String restoname;
    private Integer score;

    public HighScore(String restoname,Integer score) {
        this.restoname = restoname;
        this.score = score;
    }

    public String getRestoname() {
        return restoname;
    }

    public void setRestoname(String restoname) {
        this.restoname = restoname;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
