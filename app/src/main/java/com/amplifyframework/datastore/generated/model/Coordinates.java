package com.amplifyframework.datastore.generated.model;


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

/** This is an auto generated class representing the Coordinates type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Coordinates")
public final class Coordinates implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField LATITUDE = field("latitude");
  public static final QueryField LONGITUDE = field("longitude");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Float") Float latitude;
  private final @ModelField(targetType="Float") Float longitude;
  public String getId() {
      return id;
  }
  
  public Float getLatitude() {
      return latitude;
  }
  
  public Float getLongitude() {
      return longitude;
  }
  
  private Coordinates(String id, Float latitude, Float longitude) {
    this.id = id;
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Coordinates coordinates = (Coordinates) obj;
      return ObjectsCompat.equals(getId(), coordinates.getId()) &&
              ObjectsCompat.equals(getLatitude(), coordinates.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), coordinates.getLongitude());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getLatitude())
      .append(getLongitude())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Coordinates {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static Coordinates justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Coordinates(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      latitude,
      longitude);
  }
  public interface BuildStep {
    Coordinates build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep latitude(Float latitude);
    BuildStep longitude(Float longitude);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private Float latitude;
    private Float longitude;
    @Override
     public Coordinates build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Coordinates(
          id,
          latitude,
          longitude);
    }
    
    @Override
     public BuildStep latitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(Float longitude) {
        this.longitude = longitude;
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
    private CopyOfBuilder(String id, Float latitude, Float longitude) {
      super.id(id);
      super.latitude(latitude)
        .longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder latitude(Float latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder longitude(Float longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
  }
  
}
