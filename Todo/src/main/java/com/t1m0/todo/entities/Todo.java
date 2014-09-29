package com.t1m0.todo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "todo")
@Table(name = "tbl_todos")
@SuppressWarnings("serial")
@JsonIgnoreProperties({ "new" })
public class Todo extends AbstractPersistable<Long> {

  public enum PRIORITY {
    LOW, MEDIUM, HIGH;

    @Override
    @JsonIgnore
    public String toString() {
      String str = null;
      switch (this) {
        case LOW:
          str = "LOW";
          break;
        case MEDIUM:
          str = "MEDIUM";
          break;
        case HIGH:
          str = "HIGH";
          break;
      }
      return str;
    }
  }

  @Column(name = "title", nullable = false)
  private String   title       = null;
  @Column(name = "description", nullable = true)
  private String   description = null;
  @Column(name = "priority", nullable = true)
  private PRIORITY priority    = null;
  @Column(name = "dueDate", nullable = true)
  private Date     dueDate     = null;

  public Todo() {
    super();
  }

  public Todo(String title) {
    super();
    this.title = title;
  }

  public Todo(String title, PRIORITY priority) {
    super();
    this.title = title;
    this.priority = priority;
  }

  public Todo(String title, String description, PRIORITY priority) {
    super();
    this.title = title;
    this.description = description;
    this.priority = priority;
  }

  public Todo(String title, String description, PRIORITY priority, Date dueDate) {
    super();
    this.title = title;
    this.description = description;
    this.priority = priority;
    this.dueDate = dueDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PRIORITY getPriority() {
    return priority;
  }

  public void setPriority(PRIORITY priority) {
    this.priority = priority;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = (prime * result) + ((description == null) ? 0 : description.hashCode());
    result = (prime * result) + ((dueDate == null) ? 0 : dueDate.hashCode());
    result = (prime * result) + ((priority == null) ? 0 : priority.hashCode());
    result = (prime * result) + ((title == null) ? 0 : title.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Todo other = (Todo) obj;
    if (description == null) {
      if (other.description != null) {
        return false;
      }
    } else if (!description.equals(other.description)) {
      return false;
    }
    if (dueDate == null) {
      if (other.dueDate != null) {
        return false;
      }
    } else if (!dueDate.equals(other.dueDate)) {
      return false;
    }
    if (priority != other.priority) {
      return false;
    }
    if (title == null) {
      if (other.title != null) {
        return false;
      }
    } else if (!title.equals(other.title)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Todo [title=" + title + ", description=" + description + ", priority=" + priority + ", dueDate=" + dueDate + "]";
  }

}
