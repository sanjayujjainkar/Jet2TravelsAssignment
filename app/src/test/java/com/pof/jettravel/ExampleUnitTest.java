package com.pof.jettravel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void countMeeting() {
        assertEquals(4, 2 + 2);
        List<Integer> firstDay = new ArrayList<>();
        firstDay.add(1); firstDay.add(2); firstDay.add(3); firstDay.add(3); firstDay.add(3);

        List<Integer> lastDay = new ArrayList<>();
        lastDay.add(2); lastDay.add(2); lastDay.add(3); lastDay.add(4); lastDay.add(4);

        System.out.println(countMeetings(firstDay, lastDay));
        assertEquals(4, countMeetings(firstDay, lastDay));

    }

    private int countMeetings(List<Integer> firstDay, List<Integer> lastDay) {
        // Write your code here
        Set<Integer> meetingDays = new HashSet<>();
        for(int i=0; i<firstDay.size(); i++) {
            //find available day in given range.
            for(int j=firstDay.get(i); j<=lastDay.get(i); j++) {
                if(!meetingDays.contains(j)) {
                    meetingDays.add(j);
                    break;
                }
            }
        }

        return meetingDays.size();
    }

    private List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        List<Integer> list = new ArrayList<Integer>();
        for(Integer day: queries) {

            int index = day-1;
            int i=index;
            int j=index;
            boolean isFound = false;
            while(i>=0 && j<=stockData.size()-1) {
                if(i>0) i--;
                if(j<stockData.size()-1) j++;

                if(stockData.get(i)<stockData.get(index)) {
                    list.add(i+1);
                    isFound = true;
                    break;
                } else if(stockData.get(j)<stockData.get(index)) {
                    list.add(j+1);
                    isFound = true;
                    break;
                } else if(i==0 && j==stockData.size()-1) {
                    break;
                }
            }
            if(!isFound) {
                list.add(-1);
            }
        }
        return list;
    }




}