package com.professor.traficinspiration.model;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.professor.traficinspiration.ApplicationContext;
import com.professor.traficinspiration.model.tasks.CheckInstallTask;
import com.professor.traficinspiration.model.tasks.CommentTask;
import com.professor.traficinspiration.model.tasks.FindTask;
import com.professor.traficinspiration.model.tasks.OpenTask;
import com.professor.traficinspiration.model.tasks.ReopenTask;
import com.professor.traficinspiration.model.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Order {

    long id;
    String name;

    @SerializedName("package_name")
    String packageName;

    double payment;
    boolean finished;
    boolean payed;

    // transient
    Date openDate = new Date(0);

    @SerializedName("open_count")
    int openCount;		// количество открытий для выполнения заказа

    @SerializedName("open_interval")
    int openInterval;	// интервал открытий (в днях)

    @SerializedName("img_url")
    String imageUrl;

    @SerializedName("key_words")
    String keywords;


    transient Drawable iconImage;

    //    double additionalOpenPayment;

    @SerializedName("review")
    int review;

    boolean comment;

    @SerializedName("needed_reviews")
    int neededReviews;

    @SerializedName("done_reviews")
    int doneReviews;


    // transient
    List<Task> taskList;

    public Order() {
    }


    //    int tasks; 101101 = 45
//    int tasksStatus; 110111 = 56

    public Order(long id, String name, double payment, String packageName, List<Task> taskList) {
        this.id = id;
        this.name = name;
        this.payment = payment;
        this.packageName = packageName;

        this.openCount = 2;
        this.openInterval = 1;

        this.setTaskList(taskList);
    }

    public Order(
            long id,
            String name,
            String packageName,
            double payment,
            String finished,
            String payed,
            long openDate,
            int openCount,
            int openInterval,
            String comment,
            String tasksStatusString,
            String imageUrl,
            String keywords) {

        this.id = id;
        this.name = name;
        this.packageName = packageName;
        this.payment = payment;
        this.finished = Boolean.parseBoolean(finished);
        this.payed = Boolean.parseBoolean(payed);
        this.openDate = new Date(openDate);
        this.openCount = openCount;
        this.openInterval = openInterval;
        this.comment = Boolean.parseBoolean(comment);
        this.imageUrl = imageUrl;
        this.keywords = keywords;

        List<Task> taskList = new ArrayList<>(Arrays.asList(
                new FindTask(this),
                new CheckInstallTask(this),
                new OpenTask(this)
        ));

        if (Boolean.parseBoolean(comment)) {
            taskList.add(new CommentTask(this));
        }

        for (int i = 1; i < openCount; i++) {
            taskList.add(new ReopenTask(openInterval));
        }
        this.setTaskList(taskList);

        setTasksStatus(tasksStatusString);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public int getOpenCount() {
        return openCount;
    }

    public void setOpenCount(int openCount) {
        this.openCount = openCount;
    }

    public int getOpenInterval() {
        return openInterval;
    }

    public void setOpenInterval(int openInterval) {
        this.openInterval = openInterval;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Drawable getIconImage() {
        return iconImage;
    }

    public void setIconImage(Drawable iconImage) {
        this.iconImage = iconImage;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public int getNeededReviews() {
        return neededReviews;
    }

    public void setNeededReviews(int neededReviews) {
        this.neededReviews = neededReviews;
    }

    public int getDoneReviews() {
        return doneReviews;
    }

    public void setDoneReviews(int doneReviews) {
        this.doneReviews = doneReviews;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;

        for (Task task: taskList) {
            task.setOrder(this);
        }
    }

    public void checkCompletion() {
        for (Task task: taskList) {
            if (!task.isCompleted()) {
                return;
            }
        }


        this.finished = true;

        if (!this.payed) {
            ApplicationContext.getMessageService().completeOrder(this);
        }
    }

    public String getTasksStatus() {
        StringBuilder tasksStatusString = new StringBuilder();

        for (Task task: taskList) {
            if (task.isCompleted()) {
                tasksStatusString.append(1);
            } else {
                tasksStatusString.append(0);
            }
        }

        return tasksStatusString.toString();
    }

    public void setTasksStatus(String tasksStatusString) {

        int counter = 0;
        for (Task task: taskList) {
            task.setCompleted("1".equals("" + tasksStatusString.charAt(counter)));
            counter++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        return packageName != null ? packageName.equals(order.packageName) : order.packageName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (packageName != null ? packageName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", payment=" + payment +
                ", finished=" + finished +
                ", payed=" + payed +
                ", openDate=" + openDate +
                ", openCount=" + openCount +
                ", openInterval=" + openInterval +
                ", taskList=" + taskList +
                '}';
    }
}
