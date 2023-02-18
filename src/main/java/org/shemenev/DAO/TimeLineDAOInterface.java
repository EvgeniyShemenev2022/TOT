package org.shemenev.DAO;

import org.shemenev.model.TimeLine;

import java.util.Date;
import java.util.List;

public interface TimeLineDAOInterface {

    Long addTimeLine (TimeLine newTimeline);

    void correctTimeLine(TimeLine correctedTimeLine);

    List<TimeLine> getAllTimeLinesByClientIdToday(long id);

    List<TimeLine> getAllTimeLinesByClientID(long id);

    List<TimeLine> getAllTimeLinesOnSpecifiedDate(Date specifiedDate, long id);

    TimeLine getTimeLineById (long id);

    void deleteTimeLineByID (long id);

}
