package com.mhawi.slider.presentation.mb;

import com.mhawi.slider.business.model.Slider;
import com.mhawi.slider.business.service.SliderService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * SliderViewMb.java
 *
 * @author Malek Yaseen <ma.yaseen@ats-ware.com>
 * @since Oct 13, 2021
 */

@Named
@ViewScoped
public class SliderViewMb implements Serializable {


    @Inject
    private SliderService sliderService;

    private List<Slider> sliders;

    @PostConstruct
    public void init() {
        sliders = sliderService.findActive();
    }

    public List<Slider> getSliders() {
        return sliders;
    }


}
