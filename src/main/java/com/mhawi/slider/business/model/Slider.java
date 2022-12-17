package com.mhawi.slider.business.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "SLIDER")
public class Slider extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "SLIDE_NAME")
    private String slideName;

    @Column(name = "FILE_NAME")
    private String filename;

    @Enumerated(EnumType.STRING)
    @Column(name = "SLIDE_TYPE")
    private SlideType slideType;

    @Column(name = "DURATION", precision = 10, scale = 2)
    private Double duration;

    @Column(name = "ORDERED")
    private Integer ordered;

    @Column(name = "STATUS")
    private Boolean status = Boolean.TRUE;

    @Column(name = "FROM_DATE")
    public LocalDate fromDate;

    @Column(name = "TO_DATE")
    public LocalDate toDate;

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public SlideType getSlideType() {
        return slideType;
    }

    public void setSlideType(SlideType slideType) {
        this.slideType = slideType;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDateTime) {
        this.fromDate = fromDateTime;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDateTime) {
        this.toDate = toDateTime;
    }
}
