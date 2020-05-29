/*
 * Copyright (c) 2019. 启明星辰信息技术集团股份有限公司 版权所有
 * 本源代码受法律保护，侵权必究！
 *
 */

package com.emergency.service.emergencytask;

/**
 * @author gengyuanbo
 * 2019/01/03
 */
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.layout.element.AbstractElement;
import com.itextpdf.layout.element.ILeafElement;
import com.itextpdf.layout.renderer.IRenderer;

public class AnnotationElement extends AbstractElement<AnnotationElement> implements ILeafElement{

    private PdfAnnotation annotation;

    public AnnotationElement(PdfAnnotation annotation) {
        this.annotation = annotation;
    }

    @Override
    protected IRenderer makeNewRenderer() {
        return new AnnotationRenderer(annotation);
    }


}
