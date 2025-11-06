package com.amigoscode;

import com.amigoscode.dto.PeopleSummaryDto;
import com.amigoscode.dto.SoftwareEngineerResponse;
import com.amigoscode.peope.People;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {

    private final SoftwareEngineerService softwareEngineerService;

    public SoftwareEngineerController(SoftwareEngineerService softwareEngineerService) {
        this.softwareEngineerService = softwareEngineerService;
    }

    @GetMapping
    public List<SoftwareEngineerResponse> getEngineers() {
        return softwareEngineerService.getSoftwareEngineers()
                .stream()
                .map(SoftwareEngineerController::toDto)
                .toList();
    }

    @GetMapping("{id}")
    public SoftwareEngineerResponse getEngineerById(@PathVariable Integer id) {
        SoftwareEngineer se = softwareEngineerService.getEngineerById(id);
        return se == null ? null : toDto(se);
    }

    @PostMapping
    public void addNewSoftwareEngineerService(@RequestBody SoftwareEngineer softwareEngineer) {
        softwareEngineerService.insertSoftwareEngineer(softwareEngineer);
    }

    @DeleteMapping("{id}")
    public void removeSoftwareEngineer(@PathVariable Integer id) {
        softwareEngineerService.removeEngineer(id);
    }

    @PutMapping("{id}")
    public void updateEngineer(@PathVariable Integer id, @RequestBody SoftwareEngineer engineer) {
        softwareEngineerService.updateEngeneer(id, engineer);
    }

    private static SoftwareEngineerResponse toDto(SoftwareEngineer se) {
        People p = se.getPeople();
        PeopleSummaryDto pDto = p == null ? null : new PeopleSummaryDto(
                p.getId(),
                p.getName(),
                p.getAge(),
                p.getGender()
        );
        return new SoftwareEngineerResponse(
                se.getId(),
                se.getNickName(),
                se.getTechStack(),
                pDto
        );
    }
}
