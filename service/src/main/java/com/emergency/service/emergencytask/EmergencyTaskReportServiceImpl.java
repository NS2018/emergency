package com.emergency.service.emergencytask;

import com.emergency.module.entity.EmergencyTask;
import com.emergency.module.entity.PreserveFile;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

@Service
public class EmergencyTaskReportServiceImpl implements EmergencyTaskReportService {
    @Override
    public void exportPDFReport(EmergencyTask task, OutputStream outputStream) {

    }

    @Override
    public void createPDFReport(EmergencyTask task) {

    }

    @Override
    public void showFileAttachment(Document document, List<PreserveFile> files, PdfFont textChinese) {

    }
}
