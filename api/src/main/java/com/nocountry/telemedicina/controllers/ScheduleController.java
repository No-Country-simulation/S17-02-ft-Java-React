package com.nocountry.telemedicina.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nocountry.telemedicina.models.Schedule;
import com.nocountry.telemedicina.services.IScheduleService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "API de Turnos de Atención", description = "Se pueden visualizar los dias en los que atiende un especialista y ver los dias y horarios en los que atiende")
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    IScheduleService scheduleService;

    @GetMapping("/date")
    public ResponseEntity<List<Schedule>> findschedulesByDate(
            @RequestParam(name = "day", required = true) LocalDate day,
            @RequestParam("specialistId") UUID specialistId) {

        List<Schedule> schedules = scheduleService.findSchedulesByDate(day, specialistId);

        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping("/specialist")
    public ResponseEntity<List<Schedule>> findschedulesBySpecialistId(
            @RequestParam("specialistId") UUID specialistId) {

        List<Schedule> schedules = scheduleService.findSchedulesBySpecialist(specialistId);

        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping("/schedule-id")
    public ResponseEntity<Schedule> getMethodName(@RequestParam("scheduleId") UUID scheduleId) {

        Schedule schedule = scheduleService.findScheduleById(scheduleId);
        return ResponseEntity.ok().body(schedule);
    }

}
