package com.emergency.service.emergencytask;

import com.emergency.module.entity.EmergencyTask;
import com.emergency.module.entity.PreserveFile;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;

import java.io.OutputStream;

public interface EmergencyTaskReportService {

    void exportPDFReport(EmergencyTask task, OutputStream outputStream);

    void createPDFReport(EmergencyTask task);

    void showFileAttachment(Document document, java.util.List<PreserveFile> files,
                            PdfFont textChinese);
}
