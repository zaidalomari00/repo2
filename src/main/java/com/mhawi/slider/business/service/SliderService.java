package com.mhawi.slider.business.service;

import com.mhawi.slider.business.model.Slider;
import com.mhawi.slider.business.repo.SliderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class SliderService implements Serializable {


    @Value("${file.path}")
    public String filePath;
    @Autowired
    private SliderRepo sliderRepo;
    @Autowired
    private SimpMessagingTemplate template;

    @Cacheable("allSliders")
    public List<Slider> getSliders() {
        return sliderRepo.findAllOrderByOrdered();
    }

    @Cacheable("activeSliders")
    public List<Slider> findActive() {
        List<Slider> activeSliders = sliderRepo.findActive();
        return activeSliders;
    }


    @Transactional
    @CacheEvict(cacheNames = {"activeSliders", "allSliders"}, allEntries = true)
    public Slider save(Slider selectedSlider) {
        selectedSlider = sliderRepo.save(selectedSlider);
        updateWebSocket(selectedSlider);
        return selectedSlider;
    }

    @CacheEvict(cacheNames = {"activeSliders", "allSliders"}, allEntries = true)
    @Transactional
    public void deleteSlider(Slider selectedSlider) {
        sliderRepo.delete(selectedSlider);
        updateWebSocket(selectedSlider);
    }

    @CacheEvict(cacheNames = {"activeSliders", "allSliders"}, allEntries = true)
    @Transactional
    public void deleteAll(List<Slider> selectedSliders) {
        sliderRepo.deleteAll(selectedSliders);
        template.convertAndSend("/topic/updateSlider", selectedSliders);
    }

    private void updateWebSocket(Slider selectedSlider) {
        template.convertAndSend("/topic/updateSlider", selectedSlider);
    }
}
