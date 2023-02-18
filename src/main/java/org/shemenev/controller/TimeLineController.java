package org.shemenev.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shemenev.DAO.TimeLineDAO;
import org.shemenev.TotApplication;
import org.shemenev.model.TimeLine;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Содержит контроллеры приложения:
 *  1. добавить новую строку
 *  2. корректировать строку (поля activity & comment)
 *  3. получить все строки за СЕГОДНЯ по ID клиента
 *  4. получить все строки по ID клиента (за все время)
 *  5. получить все строки по ID клиента в УКАЗАННУЮ дату (dd/MM/yyyy)
 *  6. получить строку по ID строки
 *  7. удалить строку по ID строки
 *  8.
 *  9.
 */
@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    private final static Logger logger = LogManager.getLogger(TimeLineController.class.getName());

    private final TimeLineDAO timeLineDAO;

    public TimeLineController(TimeLineDAO timeLineDAO) {
        this.timeLineDAO = timeLineDAO;
    }


    @PostMapping
    public Long addTimeLine(@RequestBody TimeLine timeLine){
        logger.info("добавление новой строки: ={}", timeLine );

        return timeLineDAO.addTimeLine(timeLine);
    }

    @PutMapping
    public void correctTimeline(@RequestBody TimeLine timeLine){

        timeLineDAO.correctTimeLine(timeLine);
    }

    @GetMapping("/all/today/{id}")
    public List<TimeLine> getAllTimeLinesByClientIdToday(@PathVariable long id){

        return timeLineDAO.getAllTimeLinesByClientIdToday(id);
    }

    @GetMapping("/all/{id}")
    public List<TimeLine> getAllTimeLinesByClientID(@PathVariable long id){

        return timeLineDAO.getAllTimeLinesByClientID(id);
    }

    // это была БОЛЬ
    // является ли проблемой @RequestParam? TODO
    @GetMapping("/all/bydate/{id}")
    public List<TimeLine> getAllTimeLinesOnSpecifiedDate(
            @RequestParam (value = "date")
            @DateTimeFormat(pattern="dd/MM/yyyy", fallbackPatterns = { "yyyy/MM/dd", "dd.MM.yyyy" }) Date date,
            @PathVariable ("id") long id) {

        return timeLineDAO.getAllTimeLinesOnSpecifiedDate(date, id);
    }

    @GetMapping("{id}")
    public TimeLine getTimeLineById(@PathVariable long id){

        return timeLineDAO.getTimeLineById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeLineByID(@PathVariable long id){

        timeLineDAO.deleteTimeLineByID(id);
    }



}
