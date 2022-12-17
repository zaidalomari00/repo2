package com.mhawi.slider.presentation.mb;

import com.mhawi.slider.business.dto.FileMap;
import com.mhawi.slider.business.model.SlideType;
import com.mhawi.slider.business.model.Slider;
import com.mhawi.slider.business.service.SliderService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mp4parser.IsoFile;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class SliderMB implements Serializable {

    @Inject
    private SliderService sliderService;
    @Inject
    private CacheManager cacheManager;
    @Value("${file.path}")
    public String filePath;
    private UploadedFile file;
    private FileMap fileMap = new FileMap();
    private List<Slider> selectedSliders;
    private Slider selectedSlider;
    private List<Slider> sliders;

    @PostConstruct
    public void init() {
        sliders = sliderService.getSliders();

    }

    public void deleteFile() {
        fileMap = new FileMap();
        selectedSlider.setSlideType(null);
        selectedSlider.setFilename(null);
    }

    public void openNew() {
        this.selectedSlider = new Slider();
        fileMap = new FileMap();
        this.selectedSlider.setDuration(5D);
    }


    public void handleFileUpload(FileUploadEvent event) throws IOException {
        String filename = UUID.randomUUID().toString().replace("-", "") + event.getFile().getFileName();
        FileMap.Builder builder = FileMap.builder().fileName(filename)
                .fileContent(event.getFile().getContent())
                .fileContentType(event.getFile().getContentType())
                .fileSize(event.getFile().getSize())
                .fileInputStream(event.getFile().getInputStream());

        selectedSlider.setSlideType(SlideType.IMAGE);
        fileMap = builder.build();
        selectedSlider.setFilename(fileMap.getFileName());
        try {
            File dir = new File(filePath);
            dir.mkdirs();
            File targetFile = new File(filePath + fileMap.getFileName());
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(fileMap.getContent());
            IOUtils.closeQuietly(outStream);

            if (event.getFile().getContentType().toLowerCase().contains("mp4")) {

                IsoFile isoFile = new IsoFile(targetFile);
                double lengthInSeconds = isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                        isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
                selectedSlider.setDuration(lengthInSeconds);
                selectedSlider.setSlideType(SlideType.VIDEO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void saveSlider() throws IOException {
        if (this.selectedSlider.getId() == null) {
            this.selectedSlider = sliderService.save(this.selectedSlider);
            this.sliders.add(this.selectedSlider);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Slider Added"));
        } else {
            int i = this.sliders.indexOf(this.selectedSlider);
            this.selectedSlider = sliderService.save(this.selectedSlider);
            this.sliders.set(i, this.selectedSlider);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Slider Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageSliderDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sliders");
    }

    public void deleteSlider() {
        sliderService.deleteSlider(this.selectedSlider);
        this.sliders.remove(this.selectedSlider);
        this.selectedSlider = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Slider Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sliders");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedSliders()) {
            int size = this.selectedSliders.size();
            return size > 1 ? size + " Slider selected" : "1 Slider selected";
        }

        return "Delete";
    }

    public void onRowReorder(ReorderEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Row Moved", "From: " + event.getFromIndex() + ", To:" + event.getToIndex());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void clearCache() {

        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        System.out.println("Clear Cache Done ..");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clear Cache Done .."));
        PrimeFaces.current().ajax().update("form:messages");


    }

    public boolean hasSelectedSliders() {
        return this.selectedSliders != null && !this.selectedSliders.isEmpty();
    }

    public void deleteSelectedSliders() {
        this.sliders.removeAll(this.selectedSliders);
        sliderService.deleteAll(this.selectedSliders);
        this.selectedSliders = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sliders Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-sliders");
        PrimeFaces.current().executeScript("PF('dtSliders').clearFilters()");
    }

    public List<Slider> getSliders() {
        return sliders;
    }

    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
    }

    public Slider getSelectedSlider() {
        return selectedSlider;
    }

    public void setSelectedSlider(Slider selectedSlider) {
        this.selectedSlider = selectedSlider;
    }

    public List<Slider> getSelectedSliders() {
        return selectedSliders;
    }

    public void setSelectedSliders(List<Slider> selectedSliders) {
        this.selectedSliders = selectedSliders;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public FileMap getFileMap() {
        return fileMap;
    }

    public void setFileMap(FileMap fileMap) {
        this.fileMap = fileMap;
    }
}
