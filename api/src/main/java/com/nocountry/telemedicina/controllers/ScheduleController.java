package com.nocountry.telemedicina.controllers;

import com.nocountry.telemedicina.dto.response.ScheduleResponseDTO;
import org.springframework.web.bind.annotation.*;

import com.nocountry.telemedicina.models.Schedule;
import com.nocountry.telemedicina.services.IScheduleService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Tag(name = "API de Turnos de Atención", description = "Se pueden visualizar los dias en los que atiende un especialista y ver los dias y horarios en los que atiende")
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    IScheduleService scheduleService;

    @GetMapping("/date")
    public ResponseEntity<List<ScheduleResponseDTO>> findschedulesByDate(
            @RequestParam(name = "day", required = true) LocalDate day,
            @RequestParam("specialistId") UUID specialistId) {

        List<ScheduleResponseDTO> schedules = scheduleService.findSchedulesByDate(day, specialistId);

        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<List<ScheduleResponseDTO>> findschedulesBySpecialistId(
            @PathVariable("specialistId") UUID specialistId) {

        List<ScheduleResponseDTO> schedules = scheduleService.findSchedulesBySpecialist(specialistId);

        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping("/schedule-id")
    public ResponseEntity<ScheduleResponseDTO> getMethodName(@RequestParam("scheduleId") UUID scheduleId) {

        ScheduleResponseDTO schedule = scheduleService.findScheduleById(scheduleId);
        return ResponseEntity.ok().body(schedule);
    }

}
