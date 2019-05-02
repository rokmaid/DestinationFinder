package roommodels;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "citycodes")
public class CityCode {


    @PrimaryKey(autoGenerate = true)
    private int id ;

    private String code ;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
