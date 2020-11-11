package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks")
public final class Task implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField TASK_DETAILS = field("taskDetails");
  public static final QueryField TASK_TITLE = field("taskTitle");
  public static final QueryField TASK_STATE_OF_DOING = field("taskStateOfDoing");
  public static final QueryField FILEKEY = field("filekey");
  public static final QueryField ADDRESS = field("address");
  public static final QueryField APART_OF = field("taskApartOfId");
  public static final QueryField LATLON = field("taskLatlonId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String taskDetails;
  private final @ModelField(targetType="String") String taskTitle;
  private final @ModelField(targetType="String") String taskStateOfDoing;
  private final @ModelField(targetType="String") String filekey;
  private final @ModelField(targetType="String") String address;
  private final @ModelField(targetType="Team") @BelongsTo(targetName = "taskApartOfId", type = Team.class) Team apartOf;
  private final @ModelField(targetType="Coordinates") @BelongsTo(targetName = "taskLatlonId", type = Coordinates.class) Coordinates latlon;
  public String getId() {
      return id;
  }
  
  public String getTaskDetails() {
      return taskDetails;
  }
  
  public String getTaskTitle() {
      return taskTitle;
  }
  
  public String getTaskStateOfDoing() {
      return taskStateOfDoing;
  }
  
  public String getFilekey() {
      return filekey;
  }
  
  public String getAddress() {
      return address;
  }
  
  public Team getApartOf() {
      return apartOf;
  }
  
  public Coordinates getLatlon() {
      return latlon;
  }
  
  private Task(String id, String taskDetails, String taskTitle, String taskStateOfDoing, String filekey, String address, Team apartOf, Coordinates latlon) {
    this.id = id;
    this.taskDetails = taskDetails;
    this.taskTitle = taskTitle;
    this.taskStateOfDoing = taskStateOfDoing;
    this.filekey = filekey;
    this.address = address;
    this.apartOf = apartOf;
    this.latlon = latlon;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getTaskDetails(), task.getTaskDetails()) &&
              ObjectsCompat.equals(getTaskTitle(), task.getTaskTitle()) &&
              ObjectsCompat.equals(getTaskStateOfDoing(), task.getTaskStateOfDoing()) &&
              ObjectsCompat.equals(getFilekey(), task.getFilekey()) &&
              ObjectsCompat.equals(getAddress(), task.getAddress()) &&
              ObjectsCompat.equals(getApartOf(), task.getApartOf()) &&
              ObjectsCompat.equals(getLatlon(), task.getLatlon());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTaskDetails())
      .append(getTaskTitle())
      .append(getTaskStateOfDoing())
      .append(getFilekey())
      .append(getAddress())
      .append(getApartOf())
      .append(getLatlon())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("taskDetails=" + String.valueOf(getTaskDetails()) + ", ")
      .append("taskTitle=" + String.valueOf(getTaskTitle()) + ", ")
      .append("taskStateOfDoing=" + String.valueOf(getTaskStateOfDoing()) + ", ")
      .append("filekey=" + String.valueOf(getFilekey()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("apartOf=" + String.valueOf(getApartOf()) + ", ")
      .append("latlon=" + String.valueOf(getLatlon()))
      .append("}")
      .toString();
  }
  
  public static TaskDetailsStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Task justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      taskDetails,
      taskTitle,
      taskStateOfDoing,
      filekey,
      address,
      apartOf,
      latlon);
  }
  public interface TaskDetailsStep {
    BuildStep taskDetails(String taskDetails);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep taskTitle(String taskTitle);
    BuildStep taskStateOfDoing(String taskStateOfDoing);
    BuildStep filekey(String filekey);
    BuildStep address(String address);
    BuildStep apartOf(Team apartOf);
    BuildStep latlon(Coordinates latlon);
  }
  

  public static class Builder implements TaskDetailsStep, BuildStep {
    private String id;
    private String taskDetails;
    private String taskTitle;
    private String taskStateOfDoing;
    private String filekey;
    private String address;
    private Team apartOf;
    private Coordinates latlon;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          taskDetails,
          taskTitle,
          taskStateOfDoing,
          filekey,
          address,
          apartOf,
          latlon);
    }
    
    @Override
     public BuildStep taskDetails(String taskDetails) {
        Objects.requireNonNull(taskDetails);
        this.taskDetails = taskDetails;
        return this;
    }
    
    @Override
     public BuildStep taskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
        return this;
    }
    
    @Override
     public BuildStep taskStateOfDoing(String taskStateOfDoing) {
        this.taskStateOfDoing = taskStateOfDoing;
        return this;
    }
    
    @Override
     public BuildStep filekey(String filekey) {
        this.filekey = filekey;
        return this;
    }
    
    @Override
     public BuildStep address(String address) {
        this.address = address;
        return this;
    }
    
    @Override
     public BuildStep apartOf(Team apartOf) {
        this.apartOf = apartOf;
        return this;
    }
    
    @Override
     public BuildStep latlon(Coordinates latlon) {
        this.latlon = latlon;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String taskDetails, String taskTitle, String taskStateOfDoing, String filekey, String address, Team apartOf, Coordinates latlon) {
      super.id(id);
      super.taskDetails(taskDetails)
        .taskTitle(taskTitle)
        .taskStateOfDoing(taskStateOfDoing)
        .filekey(filekey)
        .address(address)
        .apartOf(apartOf)
        .latlon(latlon);
    }
    
    @Override
     public CopyOfBuilder taskDetails(String taskDetails) {
      return (CopyOfBuilder) super.taskDetails(taskDetails);
    }
    
    @Override
     public CopyOfBuilder taskTitle(String taskTitle) {
      return (CopyOfBuilder) super.taskTitle(taskTitle);
    }
    
    @Override
     public CopyOfBuilder taskStateOfDoing(String taskStateOfDoing) {
      return (CopyOfBuilder) super.taskStateOfDoing(taskStateOfDoing);
    }
    
    @Override
     public CopyOfBuilder filekey(String filekey) {
      return (CopyOfBuilder) super.filekey(filekey);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
    }
    
    @Override
     public CopyOfBuilder apartOf(Team apartOf) {
      return (CopyOfBuilder) super.apartOf(apartOf);
    }
    
    @Override
     public CopyOfBuilder latlon(Coordinates latlon) {
      return (CopyOfBuilder) super.latlon(latlon);
    }
  }
  
}
