package Entity;


import lombok.*;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    private long id;
    public String description;
    public Date dateOfCreating;
    protected String sql;

    public Feedback(String description, Date dateOfCreating){
        this.description = description;
        this.dateOfCreating = dateOfCreating;
    }


}
