package com.amigoscode;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class SoftwareEngineerService {
    private final SoftwareEngineerRepository softwareEngineerRepository;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository) {
        this.softwareEngineerRepository = softwareEngineerRepository;
    }

    public List<SoftwareEngineer> getSoftwareEngineers() {
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(SoftwareEngineer softwareEngineer) {
        softwareEngineerRepository.save(softwareEngineer);
    }

    public SoftwareEngineer getEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id).orElse(null);

    }

    public void removeEngineer(Integer id) {
        softwareEngineerRepository.deleteById(id);
    }


    public void updateEngeneer(Integer id, SoftwareEngineer engineer) {
        softwareEngineerRepository.findById(id).ifPresent(engineerToUpdate -> {
            engineerToUpdate.setNickName(engineer.getNickName());
            engineerToUpdate.setTechStack(engineer.getTechStack());
            softwareEngineerRepository.save(engineerToUpdate);
        });
    }
}
