/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.usneha.fp.web.registrationSys;

import java.util.TimeZone;
import javax.faces.convert.DateTimeConverter;

/**
 *
 * @author snehaupadhyay
 */
public class CustomDateConverter extends DateTimeConverter {

    public CustomDateConverter() {
        super();
        setTimeZone(TimeZone.getDefault());
        setPattern(STRING_ID);
        setDateStyle(DATE_ID);
        
    }
    
}
