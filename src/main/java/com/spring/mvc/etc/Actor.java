package com.spring.mvc.etc;

import lombok.*;

@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder


public class Actor {


    private  String actorName;
    private  int actorAge;
    private  boolean hasPhone;
}
