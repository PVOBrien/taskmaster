//package com.pvobrien.github.taskmaster.DELETEME;
//
//
//import androidx.annotation.NonNull;
//import androidx.core.util.ObjectsCompat;
//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//
//import com.amplifyframework.core.model.Model;
//import com.amplifyframework.core.model.annotations.ModelConfig;
//import com.amplifyframework.core.model.annotations.ModelField;
//import com.amplifyframework.core.model.query.predicate.QueryField;
//
//import java.util.Objects;
//import java.util.UUID;
//
//import static com.amplifyframework.core.model.query.predicate.QueryField.field;
//
///** This is an auto generated class representing the TaskLocal type in your schema. */
//@SuppressWarnings("all")
//@Entity
//@ModelConfig(pluralName = "Tasks")
//public final class TaskCopy implements Model {
//  public static final QueryField ID = field("id");
//  public static final QueryField TASK_DETAILS = field("taskDetails");
//  public static final QueryField TASK_TITLE = field("taskTitle");
//  public static final QueryField TASK_STATE_OF_DOING = field("taskStateOfDoing");
//
//  @NonNull
//  @PrimaryKey
//  public final @ModelField(targetType="ID", isRequired = true) String id;
//  public final @ModelField(targetType="String", isRequired = true) String taskDetails;
//  public final @ModelField(targetType="String") String taskTitle;
//  public final @ModelField(targetType="String") String taskStateOfDoing;
//
//  public String getId() {
//      return id;
//  }
//  public String getTaskDetails() {
//      return taskDetails;
//  }
//  public String getTaskTitle() {
//      return taskTitle;
//  }
//  public String getTaskStateOfDoing() {
//      return taskStateOfDoing;
//  }
//
//  public TaskCopy(String id, String taskDetails, String taskTitle, String taskStateOfDoing) {
//    this.id = id;
//    this.taskDetails = taskDetails;
//    this.taskTitle = taskTitle;
//    this.taskStateOfDoing = taskStateOfDoing;
//  }
//
//  @Override
//   public boolean equals(Object obj) {
//      if (this == obj) {
//        return true;
//      } else if(obj == null || getClass() != obj.getClass()) {
//        return false;
//      } else {
//      TaskCopy task = (TaskCopy) obj;
//      return ObjectsCompat.equals(getId(), task.getId()) &&
//              ObjectsCompat.equals(getTaskDetails(), task.getTaskDetails()) &&
//              ObjectsCompat.equals(getTaskTitle(), task.getTaskTitle()) &&
//              ObjectsCompat.equals(getTaskStateOfDoing(), task.getTaskStateOfDoing());
//      }
//  }
//
//  @Override
//   public int hashCode() {
//    return new StringBuilder()
//      .append(getId())
//      .append(getTaskDetails())
//      .append(getTaskTitle())
//      .append(getTaskStateOfDoing())
//      .toString()
//      .hashCode();
//  }
//
//  @Override
//   public String toString() {
//    return new StringBuilder()
//      .append("TaskLocal {")
//      .append("id=" + String.valueOf(getId()) + ", ")
//      .append("taskDetails=" + String.valueOf(getTaskDetails()) + ", ")
//      .append("taskTitle=" + String.valueOf(getTaskTitle()) + ", ")
//      .append("taskStateOfDoing=" + String.valueOf(getTaskStateOfDoing()))
//      .append("}")
//      .toString();
//  }
//
//  public static TaskDetailsStep builder() {
//      return new Builder();
//  }
//
//  /**
//   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
//   * This is a convenience method to return an instance of the object with only its ID populated
//   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
//   * in a relationship.
//   * @param id the id of the existing item this instance will represent
//   * @return an instance of this model with only ID populated
//   * @throws IllegalArgumentException Checks that ID is in the proper format
//   */
//  public static TaskCopy justId(String id) {
//    try {
//      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
//    } catch (Exception exception) {
//      throw new IllegalArgumentException(
//              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
//              "of an existing object with only its ID field for sending as a mutation parameter. When " +
//              "creating a new object, use the standard builder method and leave the ID field blank."
//      );
//    }
//    return new TaskCopy(
//      id,
//      null,
//      null,
//      null
//    );
//  }
//
//  public CopyOfBuilder copyOfBuilder() {
//    return new CopyOfBuilder(id,
//      taskDetails,
//      taskTitle,
//      taskStateOfDoing);
//  }
//  public interface TaskDetailsStep {
//    BuildStep taskDetails(String taskDetails);
//  }
//
//
//  public interface BuildStep {
//    TaskCopy build();
//    BuildStep id(String id) throws IllegalArgumentException;
//    BuildStep taskTitle(String taskTitle);
//    BuildStep taskStateOfDoing(String taskStateOfDoing);
//  }
//
//
//  public static class Builder implements TaskDetailsStep, BuildStep {
//    private String id;
//    private String taskDetails;
//    private String taskTitle;
//    private String taskStateOfDoing;
//    @Override
//     public TaskCopy build() {
//        String id = this.id != null ? this.id : UUID.randomUUID().toString();
//
//        return new TaskCopy(
//          id,
//          taskDetails,
//          taskTitle,
//          taskStateOfDoing);
//    }
//
//    @Override
//     public BuildStep taskDetails(String taskDetails) {
//        Objects.requireNonNull(taskDetails);
//        this.taskDetails = taskDetails;
//        return this;
//    }
//
//    @Override
//     public BuildStep taskTitle(String taskTitle) {
//        this.taskTitle = taskTitle;
//        return this;
//    }
//
//    @Override
//     public BuildStep taskStateOfDoing(String taskStateOfDoing) {
//        this.taskStateOfDoing = taskStateOfDoing;
//        return this;
//    }
//
//    /**
//     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
//     * This should only be set when referring to an already existing object.
//     * @param id id
//     * @return Current Builder instance, for fluent method chaining
//     * @throws IllegalArgumentException Checks that ID is in the proper format
//     */
//    public BuildStep id(String id) throws IllegalArgumentException {
//        this.id = id;
//
//        try {
//            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
//        } catch (Exception exception) {
//          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
//                    exception);
//        }
//
//        return this;
//    }
//  }
//
//
//  public final class CopyOfBuilder extends Builder {
//    private CopyOfBuilder(String id, String taskDetails, String taskTitle, String taskStateOfDoing) {
//      super.id(id);
//      super.taskDetails(taskDetails)
//        .taskTitle(taskTitle)
//        .taskStateOfDoing(taskStateOfDoing);
//    }
//
//    @Override
//     public CopyOfBuilder taskDetails(String taskDetails) {
//      return (CopyOfBuilder) super.taskDetails(taskDetails);
//    }
//
//    @Override
//     public CopyOfBuilder taskTitle(String taskTitle) {
//      return (CopyOfBuilder) super.taskTitle(taskTitle);
//    }
//
//    @Override
//     public CopyOfBuilder taskStateOfDoing(String taskStateOfDoing) {
//      return (CopyOfBuilder) super.taskStateOfDoing(taskStateOfDoing);
//    }
//  }
//
//}
