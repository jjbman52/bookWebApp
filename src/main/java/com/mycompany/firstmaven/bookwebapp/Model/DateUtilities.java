/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.firstmaven.bookwebapp.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author jordanrehbein
 */
public class DateUtilities {
    
    /**
     * Default format if one is not provided - MM-dd-yyyy HH:mm
     */
    private DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
    
    /**
     * Converts a LocalDateTime Object into a String using the default formatter
     * @param dateTime LocalDateTime Object
     * @return defaultFormat.format(dateTime) String
     * @throws IllegalArgumentException if dateTime is null
     */
    
    public final String toString(LocalDateTime dateTime) throws IllegalArgumentException{
        if(dateTime == null){
            throw new IllegalArgumentException("LocalDateTime cannot be null");
        }
        return defaultFormat.format(dateTime);
    }
    
    /**
     * Converts a LocalDateTime Object into a String using a custom format
     * @param dateTime LocalDateTime Object
     * @param format Custom Format
     * @return return stringDateTime
     * @throws IllegalArgumentException if dateTime is null
     */
    
    public final String toCustomeFormattedString(LocalDateTime dateTime, DateTimeFormatter format) throws IllegalArgumentException{
        if(dateTime == null){
            throw new IllegalArgumentException("LocalDateTime cannot be null");
        }
        String stringDateTime = dateTime.format(format);
        return stringDateTime;
    }
    
    /**
     * Converts a formatted string using the pattern MM-dd-yyyy HH:mm into a LocalDateTime object
     * @param dateTimeString MM-dd-yyyy HH:mm
     * @return dateTime LocalDateTime Object
     * @throws IllegalArgumentException if dateTimeString is null
     */
    public final LocalDateTime convertFormattedStringToLocalDateTime(String dateTimeString) throws IllegalArgumentException{
        if(dateTimeString == null){
            throw new IllegalArgumentException("dateTimeString cannot be null");
        } else {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
            return dateTime;
        }
    }
    
    /**
     * Gets the difference of some time amount between two dates, times or both
     * @param startDate LocalDateTime
     * @param endDate LocalDateTime
     * @param unit ChronoUnit
     * @return difference long
     * @throws IllegalArgumentException if startDate, endDate, or unit is null
     */
    
    public final long getDifferenceDateTime(LocalDateTime startDate, LocalDateTime endDate, ChronoUnit unit) throws IllegalArgumentException{
        if(startDate == null || endDate == null || unit == null){
            throw new IllegalArgumentException("StartDate, EndDate, and Unit cannot be null");
        }
        long difference = 0;
        
        difference = unit.between(startDate, endDate);
        
        return difference;
    }
}
